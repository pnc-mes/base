package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetCMBBInfo.*;
import pnc.mesadmin.dto.GetCMGCInfo.*;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoReqBD00;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoRes;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoResB;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoResD;
import pnc.mesadmin.dto.GetCMWFInfo.*;
import pnc.mesadmin.dto.GetCMWInfo.*;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.CommonIService;
import pnc.mesadmin.service.SNIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：通用查询业务信息
 * 创建人：pjf
 * 创建时间：2020-10-14
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class CommonService implements CommonIService {
    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private WFSpecDAO wfSpecDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private WODAO woDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private SerialRuleDAO serialRuleDAO;

    @Resource
    private PdFamilyDAO pdFamilyDAO;

    @Resource
    private SNIService sniService;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private ReMaDAO reMaDAO;

    @Resource
    private ModeCDAO modeCDAO;

    @Resource
    private MsgCDAO msgCDAO;

    @Resource
    private BCirDAO bCirDAO;

    @Resource
    private WFDAO wfdao;

    @Resource
    private WFCirDAO wfCirDAO;

    @Resource
    private DevGpDtlDAO devGpDtlDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private MaWFDAO maWFDAO;

    @Resource
    private HoldDAO holdDAO;

    @Resource
    private UnHoldDAO unHoldDAO;

    // 根据物料版本ruid查询
    @Override
    public GetCMWFInfoRes getCMWFInfo(GetCMWFInfoReqBD00 objEGetCMWFInfoReqBD00) {
        GetCMWFInfoRes objEGetCMWFInfoRes = new GetCMWFInfoRes();
        GetCMWFInfoResB objEGetCMWFInfoResB = new GetCMWFInfoResB();
        GetCMWFInfoResD objEGetCMWFInfoResD = new GetCMWFInfoResD();

        GetCMWFInfoResDMaInfo objEGetCMWFInfoResDMaInfo = new GetCMWFInfoResDMaInfo();
        List<GetCMWFInfoResDReMaInfo> objEGetCMWFInfoResDReMaInfos = new ArrayList<GetCMWFInfoResDReMaInfo>();
        GetCMWFInfoResDMUnitInfo objEGetCMWFInfoResDMUnitInfo = new GetCMWFInfoResDMUnitInfo();

        List<GetCMWFInfoResDWFInfo> objEGetCMWFInfoResDWFInfos = new ArrayList<>();
        GetCMWFInfoResDWFInfo objEGetCMWFInfoResDWFInfo = null;

        List<GetCMWFInfoResDWSpecInfo> getCMWFInfoResDWSpecInfos = null;

        GetCMWFInfoResDWSpecInfo objEGetCMWFInfoResDWSpecInfo = null;

        // 根据版本的ruid查询MaInfo
        MaVerInfo objMaVerInfo = maVerDAO.SelectByRuid(objEGetCMWFInfoReqBD00.getMVerRd());
        if(objMaVerInfo != null){
            objEGetCMWFInfoResDMaInfo.setMaVerRd(objMaVerInfo.getRuid());
            objEGetCMWFInfoResDMaInfo.setMaCode(objMaVerInfo.getMaterialCode());
            objEGetCMWFInfoResDMaInfo.setMaName(objMaVerInfo.getMaterialName());

            //查询物料
            MaterialInfo materialInfo = materialDAO.SelectByGuid(objMaVerInfo.getMaGd());
            if(materialInfo != null){
                objEGetCMWFInfoResDMaInfo.setMaDes(materialInfo.getMaterialDes());
            }

            objEGetCMWFInfoResDMaInfo.setVersion(objMaVerInfo.getVersion());
            objEGetCMWFInfoResDMaInfo.setIsBatch(objMaVerInfo.getIsBatch());
            //获取单位信息
            UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objMaVerInfo.getUnitGd());
            if(objEUnitInfo != null){
                objEGetCMWFInfoResDMUnitInfo.setUnitRd(objEUnitInfo.getRuid());
                objEGetCMWFInfoResDMUnitInfo.setUnitName(objEUnitInfo.getUnitName());
                objEGetCMWFInfoResDMaInfo.setUnitInfo(objEGetCMWFInfoResDMUnitInfo);
            }
            objEGetCMWFInfoResD.setMaInfo(objEGetCMWFInfoResDMaInfo);

            List<MaWFInfo> maWFInfos = maWFDAO.SelectByMaVerGd(objMaVerInfo.getGuid());
            for(MaWFInfo maWFInfo : maWFInfos) {
                // 得到物料版本以后再通过关联的工艺流程版本得到工艺流程信息
                //WFInfo objEWFInfo = wfdao.SelectByGuid(objMaVerInfo.getwFGd());
                WFVerInfo objWFVerInfo = wfVerDAO.SelectByGuid(maWFInfo.getwFVerGd());
                    /*if (objEWFInfo != null) {
                        objWFVerInfo = wfVerDAO.SelectByGuid(objEWFInfo.getVerGd());
                    }*/
                if (objWFVerInfo != null) {
                    objEGetCMWFInfoResDWFInfo = new GetCMWFInfoResDWFInfo();
                    WFInfo objEWFInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
                    if (objEWFInfo != null) {
                        objEGetCMWFInfoResDWFInfo.setWFRd(objEWFInfo.getRuid());
                    }
                    objEGetCMWFInfoResDWFInfo.setWFVerRd(objWFVerInfo.getRuid());
                    objEGetCMWFInfoResDWFInfo.setWFName(objWFVerInfo.getwFName());

                    getCMWFInfoResDWSpecInfos = new ArrayList<>();

                    // 得到工艺流程版本信息之后再通过工艺流程版本ID获取下面所有的工艺工序
                    List<WFSpecInfo> wfSpecInfos = wfSpecDAO.SelectByWFVerGd(objWFVerInfo.getGuid());
                    // 遍历工艺工序的工序ID，查询所有的工序版本信息
                    for (WFSpecInfo wfSpecInfo : wfSpecInfos) {
                        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                        if (specVerInfo != null) {
                            objEGetCMWFInfoResDWSpecInfo = new GetCMWFInfoResDWSpecInfo();
                            objEGetCMWFInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                            objEGetCMWFInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                            if (objWFVerInfo.getsSpecVerGd().equals(specVerInfo.getGuid())) {
                                getCMWFInfoResDWSpecInfos.add(0, objEGetCMWFInfoResDWSpecInfo);
                            } else {
                                getCMWFInfoResDWSpecInfos.add(objEGetCMWFInfoResDWSpecInfo);
                            }
                        }
                    }

                    objEGetCMWFInfoResDWFInfo.setSpecInfo(getCMWFInfoResDWSpecInfos);
                    objEGetCMWFInfoResDWFInfos.add(objEGetCMWFInfoResDWFInfo);
                }
            }

            //ReMaInfo
            List<ReMaInfo> objEReMaInfos = reMaDAO.SelectByMaVerGd(objMaVerInfo.getGuid());
            if(objEReMaInfos == null) {
                objEReMaInfos = new ArrayList<ReMaInfo>(Collections.EMPTY_LIST);
            }
            for(ReMaInfo reMaInfo : objEReMaInfos){
                GetCMWFInfoResDReMaInfo objEGetCMWFInfoResDReMaInfo = new GetCMWFInfoResDReMaInfo();
                //查询物料
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(reMaInfo.getReMaVerGd());
                if(objEMaVerInfo != null){
                    objEGetCMWFInfoResDReMaInfo.setMaVerRd(objEMaVerInfo.getRuid());
                }
                objEGetCMWFInfoResDReMaInfo.setMaCode(reMaInfo.getMaterialCode());
                objEGetCMWFInfoResDReMaInfo.setMaName(reMaInfo.getMaterialName());
                objEGetCMWFInfoResDReMaInfo.setVersion(reMaInfo.getVersion());
                objEGetCMWFInfoResDReMaInfos.add(objEGetCMWFInfoResDReMaInfo);
            }

            objEGetCMWFInfoResD.setMaInfo(objEGetCMWFInfoResDMaInfo);
            objEGetCMWFInfoResD.setWFInfo(objEGetCMWFInfoResDWFInfos);
            objEGetCMWFInfoResD.setReMaInfo(objEGetCMWFInfoResDReMaInfos);
            objEGetCMWFInfoResB.setData(objEGetCMWFInfoResD);
            objEGetCMWFInfoResB.setMsgCode("0x00000");
            objEGetCMWFInfoResB.setMsgDes("成功");
            objEGetCMWFInfoRes.setBody(objEGetCMWFInfoResB);
            return objEGetCMWFInfoRes;
        }else{
            throw new SystemException("MG000001","没有查询到数据");
        }
    }

    //获取物料关联信息
    @Override
    public GetCMWFInfoRes getCMWFInfoByWoCode(GetCMWFInfoReqBD01 objEGetCMWFInfoReqBD01) {
        GetCMWFInfoRes objEGetCMWFInfoRes = new GetCMWFInfoRes();
        GetCMWFInfoResB objEGetCMWFInfoResB = new GetCMWFInfoResB();
        GetCMWFInfoResD objEGetCMWFInfoResD = new GetCMWFInfoResD();

        GetCMWFInfoResDMaInfo objEGetCMWFInfoResDMaInfo = new GetCMWFInfoResDMaInfo();
        List<GetCMWFInfoResDReMaInfo> objEGetCMWFInfoResDReMaInfos = new ArrayList<GetCMWFInfoResDReMaInfo>();

        List<GetCMWFInfoResDWFInfo> objEGetCMWFInfoResDWFInfos = new ArrayList<>();
        GetCMWFInfoResDWFInfo objEGetCMWFInfoResDWFInfo = null;

        List<GetCMWFInfoResDWSpecInfo> getCMWFInfoResDWSpecInfos = null;

        GetCMWFInfoResDWSpecInfo objEGetCMWFInfoResDWSpecInfo = null;

        // 根据工单号查询到物料版本
        WoInfo woInfo = woDAO.SelectWoInfoBywoCode(objEGetCMWFInfoReqBD01.getWoCode());
        if(woInfo != null){
            // 根据版本的查询MaInfo
            MaVerInfo objMaVerInfo = maVerDAO.SelectMaverInfo(woInfo.getMaVerGd());

            objEGetCMWFInfoResDMaInfo.setMaVerRd(objMaVerInfo.getRuid());
            objEGetCMWFInfoResDMaInfo.setMaCode(objMaVerInfo.getMaterialCode());
            objEGetCMWFInfoResDMaInfo.setMaName(objMaVerInfo.getMaterialName());
            objEGetCMWFInfoResDMaInfo.setVersion(objMaVerInfo.getVersion());
            objEGetCMWFInfoResDMaInfo.setIsBatch(objMaVerInfo.getIsBatch());
            objEGetCMWFInfoResD.setMaInfo(objEGetCMWFInfoResDMaInfo);

            List<MaWFInfo> maWFInfos = maWFDAO.SelectByMaVerGd(objMaVerInfo.getGuid());
            for(MaWFInfo maWFInfo : maWFInfos) {
                // 得到物料版本以后再通过关联的工艺流程版本得到工艺流程信息
                //WFInfo objEWFInfo = wfdao.SelectByGuid(objMaVerInfo.getwFGd());
                WFVerInfo objWFVerInfo = wfVerDAO.SelectByGuid(maWFInfo.getwFVerGd());
                    /*if (objEWFInfo != null) {
                        objWFVerInfo = wfVerDAO.SelectByGuid(objEWFInfo.getVerGd());
                    }*/
                if (objWFVerInfo != null) {
                    objEGetCMWFInfoResDWFInfo = new GetCMWFInfoResDWFInfo();
                    WFInfo objEWFInfo = wfdao.SelectByGuid(objWFVerInfo.getWfGd());
                    if (objEWFInfo != null) {
                        objEGetCMWFInfoResDWFInfo.setWFRd(objEWFInfo.getRuid());
                    }
                    objEGetCMWFInfoResDWFInfo.setWFRd(objWFVerInfo.getRuid());
                    objEGetCMWFInfoResDWFInfo.setWFName(objWFVerInfo.getwFName());

                    // 得到工艺流程版本信息之后再通过工艺流程版本ID获取下面所有的工艺工序
                    List<WFSpecInfo> wfSpecInfos = wfSpecDAO.SelectByWFVerGd(objWFVerInfo.getGuid());

                    getCMWFInfoResDWSpecInfos = new ArrayList<>();

                    // 遍历工艺工序的工序ID，查询所有的工序版本信息
                    for (WFSpecInfo wfSpecInfo : wfSpecInfos) {
                        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                        if (specVerInfo != null) {
                            objEGetCMWFInfoResDWSpecInfo = new GetCMWFInfoResDWSpecInfo();
                            objEGetCMWFInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                            objEGetCMWFInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                            if (objWFVerInfo.getsSpecVerGd().equals(specVerInfo.getGuid())) {
                                getCMWFInfoResDWSpecInfos.add(0, objEGetCMWFInfoResDWSpecInfo);
                            } else {
                                getCMWFInfoResDWSpecInfos.add(objEGetCMWFInfoResDWSpecInfo);
                            }
                        }
                    }
                    objEGetCMWFInfoResDWFInfo.setSpecInfo(getCMWFInfoResDWSpecInfos);
                    objEGetCMWFInfoResDWFInfos.add(objEGetCMWFInfoResDWFInfo);
                }
            }

            //ReMaInfo
            List<ReMaInfo> objEReMaInfos = reMaDAO.SelectByMaVerGd(objMaVerInfo.getGuid());
            if(objEReMaInfos == null) {
                objEReMaInfos = new ArrayList<ReMaInfo>(Collections.EMPTY_LIST);
            }
            for(ReMaInfo reMaInfo : objEReMaInfos){
                GetCMWFInfoResDReMaInfo objEGetCMWFInfoResDReMaInfo = new GetCMWFInfoResDReMaInfo();
                //查询物料
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(reMaInfo.getReMaVerGd());
                if(objEMaVerInfo != null){
                    objEGetCMWFInfoResDReMaInfo.setMaVerRd(objEMaVerInfo.getRuid());
                }
                objEGetCMWFInfoResDReMaInfo.setMaCode(reMaInfo.getMaterialCode());
                objEGetCMWFInfoResDReMaInfo.setMaName(reMaInfo.getMaterialName());
                objEGetCMWFInfoResDReMaInfo.setVersion(reMaInfo.getVersion());
                objEGetCMWFInfoResDReMaInfos.add(objEGetCMWFInfoResDReMaInfo);
            }

            objEGetCMWFInfoResD.setMaInfo(objEGetCMWFInfoResDMaInfo);
            objEGetCMWFInfoResD.setWFInfo(objEGetCMWFInfoResDWFInfos);
            objEGetCMWFInfoResD.setReMaInfo(objEGetCMWFInfoResDReMaInfos);
            objEGetCMWFInfoResB.setData(objEGetCMWFInfoResD);
            objEGetCMWFInfoResB.setMsgCode("0x00000");
            objEGetCMWFInfoResB.setMsgDes("成功");
            objEGetCMWFInfoRes.setBody(objEGetCMWFInfoResB);
            return objEGetCMWFInfoRes;
        }else{
            throw new SystemException("MG000001","没有查询到数据");
        }
    }

    //获取批次信息
    @Override
    public GetCMBBInfoRes GetGetCMBBInfoRes(GetCMBBInfoReq00 argGetCMBBInfoReq00) {
        GetCMBBInfoRes objEGetCMBBInfoRes=new GetCMBBInfoRes();
        GetCMBBInfoResB objEGetCMBBInfoResB=new GetCMBBInfoResB();
        GetCMBBInfoResD objEGetCMBBInfoResD=new GetCMBBInfoResD();
        GetCMBBInfoResDUnitInfo objEGetCMBBInfoResDUnitInfo = new GetCMBBInfoResDUnitInfo();

        //查询批次
        BInfo objEBInfo=bdao.selectBatchInfoByBatch(argGetCMBBInfoReq00.getBatch());
        if(objEBInfo==null){
            throw new SystemException("xxx","无此记录!");
        }
        objEGetCMBBInfoResD.setBatch(objEBInfo.getBatch());

        if("01".equals(objEBInfo.getWoSource())){ //生产工单
            //查询工单
            WoInfo objEWoInfo = woDAO.SelectWoInfoByGuid(objEBInfo.getWoGd());
            if(objEWoInfo != null) {
                objEGetCMBBInfoResD.setWoCode(objEWoInfo.getWoCode());

                //查询工艺流程版本
                WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEBInfo.getwFVerGd());
                if(objEWFVerInfo != null){
                    GetCMBBInfoResDWFInfo objEGetCMBBInfoResDWFInfo = new GetCMBBInfoResDWFInfo();
                    List<GetCMBBInfoResDWSpecInfo> objEGetCMBBInfoResDWSpecInfos = new ArrayList<GetCMBBInfoResDWSpecInfo>();
                    GetCMBBInfoResDWSpecInfo objEGetCMBBInfoResDWSpecInfo = null;

                    //查询工艺流程
                    WFInfo objEWFInfo = wfdao.SelectByGuid(objEWFVerInfo.getWfGd());
                    if(objEWFInfo != null){
                        objEGetCMBBInfoResDWFInfo.setWFRd(objEWFInfo.getRuid());
                    }
                    objEGetCMBBInfoResDWFInfo.setWFVerRd(objEWFVerInfo.getRuid());
                    objEGetCMBBInfoResDWFInfo.setWFName(objEWFVerInfo.getwFName());

                    //当前工序、非正常工序
                    BCirInfo objEBCirInfo = bCirDAO.SelectByBatch(objEBInfo.getBatch());
                    if(objEBCirInfo != null){
                        GetCMBBInfoResDWCSpecInfo objEGetCMBBInfoResDWCSpecInfo = new GetCMBBInfoResDWCSpecInfo();
                        List<GetCMBBInfoResDWCPath> objEGetCMBBInfoResDWCPaths = new ArrayList<GetCMBBInfoResDWCPath>();
                        //查询当前工序
                        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEBCirInfo.getSpecVerGd());
                        if(objESpecVerInfo == null){
                            throw new SystemException("", "批次所在的当前工序不存在");
                        }
                        objEGetCMBBInfoResDWCSpecInfo.setSpecVerRd(objESpecVerInfo.getRuid());
                        objEGetCMBBInfoResDWCSpecInfo.setSpecName(objESpecVerInfo.getSpecName());

                        //查询工艺工序信息
                        WFSpecInfo objEWFSpecInfo = wfSpecDAO.SelectByWSVerGd(objEWFVerInfo.getGuid(), objESpecVerInfo.getGuid());
                        if(objEWFSpecInfo == null){
                            throw new SystemException("", "批次所在的当前工序流转信息不存在");
                        }
                        //下一道可选工序、返工工序
                        if("00".equals(objEWFSpecInfo.geteOSpec()) || "00".equals(objEWFSpecInfo.geteRSpec())){
                            List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(objEWFSpecInfo.getGuid());
                            if(objEWFCirInfos != null){
                                GetCMBBInfoResDWCPath objEGetCMBBInfoResDWCPath = null;
                                SpecVerInfo specVerInfo = null;
                                for(WFCirInfo wfCirInfo : objEWFCirInfos){
                                    objEGetCMBBInfoResDWCPath = new GetCMBBInfoResDWCPath();
                                    specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfCirInfo.getSpecVerGd());
                                    if(specVerInfo != null){
                                        objEGetCMBBInfoResDWCPath.setSpecVerRd(specVerInfo.getRuid());
                                        objEGetCMBBInfoResDWCPath.setSpecName(specVerInfo.getSpecName());
                                        objEGetCMBBInfoResDWCPath.setRouteType(wfCirInfo.getRouteType());
                                        objEGetCMBBInfoResDWCPaths.add(objEGetCMBBInfoResDWCPath);
                                    }
                                }
                            }
                        }

                        objEGetCMBBInfoResDWCSpecInfo.setPath(objEGetCMBBInfoResDWCPaths);
                        objEGetCMBBInfoResDWFInfo.setCSpecInfo(objEGetCMBBInfoResDWCSpecInfo);
                    }

                    //工艺流程下所有工序
                    List<WFSpecInfo> wfSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFVerInfo.getGuid());
                    Map<String, String> wsMap = new HashMap<>(16);
                    List<String> wfspecids = new ArrayList<>();
                    for(WFSpecInfo wfSpecInfo : wfSpecInfos){
                        wsMap.put(wfSpecInfo.getSpecVerGd(), wfSpecInfo.getnSpecVerGd());
                    }
                    String specGd = objEWFVerInfo.getsSpecVerGd();
                    for(int i=0, len=wfSpecInfos.size(); i<len; i++){
                        if(wsMap.containsKey(specGd)){
                            wfspecids.add(specGd);
                            specGd = wsMap.get(specGd);
                        }
                    }
                    // 遍历工艺工序的工序ID，查询所有的工序版本信息
                    for (String id : wfspecids) {
                        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(id);
                        if(specVerInfo != null){
                            objEGetCMBBInfoResDWSpecInfo = new GetCMBBInfoResDWSpecInfo();
                            objEGetCMBBInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                            objEGetCMBBInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                                /*if(objEWFVerInfo.getsSpecVerGd().equals(specVerInfo.getGuid())){
                                    objEGetCMBBInfoResDWSpecInfos.add(0, objEGetCMBBInfoResDWSpecInfo);
                                }else{
                                    objEGetCMBBInfoResDWSpecInfos.add(objEGetCMBBInfoResDWSpecInfo);
                                }*/
                            objEGetCMBBInfoResDWSpecInfos.add(objEGetCMBBInfoResDWSpecInfo);

                                /*if("00".equals(wfSpecInfo.geteOSpec()) || "00".equals(wfSpecInfo.geteRSpec())){
                                    //非正常流转信息
                                    List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(wfSpecInfo.getGuid());
                                    if(objEWFCirInfos != null){
                                        specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                                        if(specVerInfo != null){
                                            objEGetCMBBInfoResDWSpecInfo = new GetCMBBInfoResDWSpecInfo();
                                            objEGetCMBBInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                                            objEGetCMBBInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                                            objEGetCMBBInfoResDWSpecInfos.add(objEGetCMBBInfoResDWSpecInfo);
                                        }
                                    }
                                }*/
                        }
                    }

                    objEGetCMBBInfoResDWFInfo.setSpecInfo(objEGetCMBBInfoResDWSpecInfos);
                    objEGetCMBBInfoResD.setWFInfo(objEGetCMBBInfoResDWFInfo);
                }
            }
        }

        if(objEGetCMBBInfoResD.getWoCode() == null){
            objEGetCMBBInfoResD.setWoCode("");
        }

        //查询物料版本标识
        /**/
        MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo != null) {
            objEGetCMBBInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
            objEGetCMBBInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
            objEGetCMBBInfoResD.setMaName(objEMaVerInfo.getMaterialName());
            objEGetCMBBInfoResD.setMaDes(objEMaVerInfo.getMaterialDes());
            objEGetCMBBInfoResD.setMaType(objEMaVerInfo.getMaterialType());
            objEGetCMBBInfoResD.setVersion(objEMaVerInfo.getVersion());
            objEGetCMBBInfoResD.setMaStatus(objEMaVerInfo.getStatus());
            objEGetCMBBInfoResD.setNum(objEBInfo.getNum());
            objEGetCMBBInfoResD.setCanNum(objEBInfo.getCanNum());
            objEGetCMBBInfoResD.setIQCStatus(objEBInfo.getiQCStatus());

            //查询单位
            UnitInfo unitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
            if(unitInfo != null){
                objEGetCMBBInfoResDUnitInfo.setUnitRd(unitInfo.getRuid());
                objEGetCMBBInfoResDUnitInfo.setUnitName(unitInfo.getUnitName());
            }

            objEGetCMBBInfoResD.setUnitInfo(objEGetCMBBInfoResDUnitInfo);
            objEGetCMBBInfoResD.setStatus(objEBInfo.getStatus());
        }
        objEGetCMBBInfoResB.setData(objEGetCMBBInfoResD);
        objEGetCMBBInfoRes.setBody(objEGetCMBBInfoResB);
        return objEGetCMBBInfoRes;
    }

    //根据物料ID获取序号信息  (By-pjf)
    @Override
    public GetCMSRInfoRes GetCMSRInfoByMV(GetCMSRInfoReqBD00 argBD00) {
        GetCMSRInfoRes objEGetCMSRInfoRes = new GetCMSRInfoRes();
        GetCMSRInfoResB objEGetCMSRInfoResB = new GetCMSRInfoResB();
        List<GetCMSRInfoResD> objEGetCMSRInfoResDs = new ArrayList<GetCMSRInfoResD>();

        //获取物料版本信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argBD00.getMaVerRd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料信息不存在");
        }

        for(int i=0; i<argBD00.getCount(); i++){
            //查询序号规则
            SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaVerInfo.getSerialRuleGd());
            if(objESerialRuleInfo == null){

                //通过产品家族找序号
                PdFamilyInfo objEPdFamilyInfo = pdFamilyDAO.SelectByGuid(objEMaVerInfo.getPdFamilyGd());
                if(objEPdFamilyInfo == null){
                    throw new SystemException("", "物料没有序号规则");
                }
                objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEPdFamilyInfo.getSerialRuleGd());

                if(objESerialRuleInfo == null){
                    throw new SystemException("", "物料没有序号规则");
                }
            }

            String batch = sniService.GetCreateSR(objESerialRuleInfo);

            GetCMSRInfoResD objEGetCMSRInfoResD = new GetCMSRInfoResD();
            objEGetCMSRInfoResD.setSRCode(batch);
            objEGetCMSRInfoResDs.add(objEGetCMSRInfoResD);
        }

        objEGetCMSRInfoResB.setData(objEGetCMSRInfoResDs);
        objEGetCMSRInfoRes.setBody(objEGetCMSRInfoResB);

        return objEGetCMSRInfoRes;
    }

    //查询全局配置信息00
    @Override
    public GetCMGCInfoRes GetModeName(GetCMGCInfoReqBD00 argGetCMGCInfoReqBD00) {
        GetCMGCInfoRes objEGetCMGCInfoRes=new GetCMGCInfoRes();
        GetCMGCInfoResB objEGetCMGCInfoResB=new GetCMGCInfoResB();
        List<GetCMGCInfoResD> dataList=new ArrayList<GetCMGCInfoResD>();

        //查询模式名称
        ModeCInfo objEModeCInfo=modeCDAO.SelectmodeName(argGetCMGCInfoReqBD00.getModeName());
        if(objEModeCInfo==null){
            List<ModeCInfo> objEModeCInfos=modeCDAO.SelectAllModeCInfo();

            if(objEModeCInfos!=null && objEModeCInfos.size()>0){
                for(ModeCInfo obj:objEModeCInfos) {
                    GetCMGCInfoResD objEGetCMGCInfoResD = new GetCMGCInfoResD();

                    objEGetCMGCInfoResD.setName(obj.getModeName());
                    objEGetCMGCInfoResD.setValue(obj.getModeValue());

                    dataList.add(objEGetCMGCInfoResD);
                }
            }
        }else{
            GetCMGCInfoResD objEGetCMGCInfoResD = new GetCMGCInfoResD();
            objEGetCMGCInfoResD.setName(objEModeCInfo.getModeName());
            objEGetCMGCInfoResD.setValue(objEModeCInfo.getModeValue());
            dataList.add(objEGetCMGCInfoResD);
        }

        objEGetCMGCInfoResB.setData(dataList);
        objEGetCMGCInfoRes.setBody(objEGetCMGCInfoResB);
        return objEGetCMGCInfoRes;
    }

    //查询全局配置信息01
    @Override
    public GetCMGCInfoRes GetMsgName(GetCMGCInfoReqBD01 argGetCMGCInfoReqBD01) {
        GetCMGCInfoRes objEGetCMGCInfoRes=new GetCMGCInfoRes();
        GetCMGCInfoResB objEGetCMGCInfoResB=new GetCMGCInfoResB();
        List<GetCMGCInfoResD> dataList=new ArrayList<GetCMGCInfoResD>();

        //查询消息名称
        MsgCInfo objEMsgCInfo=msgCDAO.SelectmsgName(argGetCMGCInfoReqBD01.getMsgName());
        if(objEMsgCInfo==null){
            List<MsgCInfo> objEMsgCInfos=msgCDAO.SelectAllMsgCInfo();

            if(objEMsgCInfos!=null && objEMsgCInfos.size()>0){
                for(MsgCInfo obj:objEMsgCInfos) {
                    GetCMGCInfoResD objEGetCMGCInfoResD = new GetCMGCInfoResD();

                    objEGetCMGCInfoResD.setName(obj.getMsgName());
                    objEGetCMGCInfoResD.setValue(obj.getMsgValue());

                    dataList.add(objEGetCMGCInfoResD);
                }
            }
        }else{
            GetCMGCInfoResD objEGetCMGCInfoResD = new GetCMGCInfoResD();
            objEGetCMGCInfoResD.setName(objEMsgCInfo.getMsgName());
            objEGetCMGCInfoResD.setValue(objEMsgCInfo.getMsgValue());
            dataList.add(objEGetCMGCInfoResD);
        }

        objEGetCMGCInfoResB.setData(dataList);
        objEGetCMGCInfoRes.setBody(objEGetCMGCInfoResB);
        return objEGetCMGCInfoRes;
    }

    //获取工单关联信息
    @Override
    public GetCMWInfoRes GetCMW(GetCMWInfoReqBD00 argBD00) {
        GetCMWInfoRes objEGetCMWInfoRes = new GetCMWInfoRes();
        GetCMWInfoResB objEGetCMWInfoResB = new GetCMWInfoResB();
        GetCMWInfoResD objEGetCMWInfoResD = new GetCMWInfoResD();
        List<GetCMWInfoResDBInfo> objEGetCMWInfoResDBInfos = new ArrayList<GetCMWInfoResDBInfo>();

        //查询工单
        WoInfo objEWoInfo = woDAO.SelectWoInfoBywoCode(argBD00.getWoCode());
        if(objEWoInfo == null){
            throw new SystemException("", argBD00.getWoCode() + "工单不存在");
        }
        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEWoInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", argBD00.getWoCode() + "工单所生产的产品已不存在");
        }
        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if(objEUnitInfo != null){
            GetCMWInfoResDUnitInfo objEGetCMWInfoResDUnitInfo = new GetCMWInfoResDUnitInfo();
            objEGetCMWInfoResDUnitInfo.setUnitRd(objEUnitInfo.getRuid());
            objEGetCMWInfoResDUnitInfo.setUnitName(objEUnitInfo.getUnitName());
            objEGetCMWInfoResD.setUnitInfo(objEGetCMWInfoResDUnitInfo);
        }

        objEGetCMWInfoResD.setWoCode(objEWoInfo.getWoCode());
        objEGetCMWInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
        objEGetCMWInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
        objEGetCMWInfoResD.setMaName(objEMaVerInfo.getMaterialName());
        objEGetCMWInfoResD.setMaType(objEMaVerInfo.getMaterialType());
        objEGetCMWInfoResD.setVersion(objEMaVerInfo.getVersion());
        objEGetCMWInfoResD.setMaStatus(objEMaVerInfo.getStatus());
        objEGetCMWInfoResD.setNum(objEWoInfo.getNum());
        //TODO 在制品数量、未创批数量、已完工数量、状态
        objEGetCMWInfoResD.setWIPNum(0f);
        objEGetCMWInfoResD.setUnCBatNum(0f);
        objEGetCMWInfoResD.setFinishNum(0f);
        objEGetCMWInfoResD.setStatus("");

        //查询该工单下面已经创建的批次
        List<BInfo> objEBInfos = bdao.SelectByWoGd(objEWoInfo.getGuid());
        if(objEBInfos != null && objEBInfos.size() > 0){
            GetCMWInfoResDBInfo objEGetCMWInfoResDBInfo = null;
            for(BInfo bInfo : objEBInfos){
                objEGetCMWInfoResDBInfo = new GetCMWInfoResDBInfo();
                objEGetCMWInfoResDBInfo.setBatch(bInfo.getBatch());
                objEGetCMWInfoResDBInfos.add(objEGetCMWInfoResDBInfo);
            }
        }

        //查询工艺流程
        //WFInfo objEWFInfo = wfdao.SelectByGuid(objEMaVerInfo.getwFGd());
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEWoInfo.getwFVerGd());
            /*if(objEWFInfo != null){
                objEWFVerInfo = wfVerDAO.SelectByGuid(objEWFInfo.getVerGd());
            }*/
        if(objEWFVerInfo != null){
            GetCMWInfoResDWFInfo objEGetCMWInfoResDWFInfo = new GetCMWInfoResDWFInfo();
            List<GetCMWInfoResDWSpecInfo> objEGetCMWInfoResDWSpecInfos = new ArrayList<>();
            GetCMWInfoResDWSpecInfo objEGetCMWInfoResDWSpecInfo = null;
            SpecVerInfo specVerInfo = null;

            objEGetCMWInfoResDWFInfo.setWFVerRd(objEWFVerInfo.getRuid());
            objEGetCMWInfoResDWFInfo.setWFName(objEWFVerInfo.getwFName());
            //查询该流程下面的工序
            List<WFSpecInfo> wfSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFVerInfo.getGuid());
            // 遍历工艺工序的工序ID，查询所有的工序版本信息
            for (WFSpecInfo wfSpecInfo : wfSpecInfos) {
                specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                if (specVerInfo != null) {
                    objEGetCMWInfoResDWSpecInfo = new GetCMWInfoResDWSpecInfo();
                    objEGetCMWInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                    objEGetCMWInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                    objEGetCMWInfoResDWSpecInfo.setSpecStatus(specVerInfo.getStatus());
                    objEGetCMWInfoResDWSpecInfo.setDevGInfo(readDev(specVerInfo.getDevGrGd()));

                    if(objEWFVerInfo.getsSpecVerGd().equals(specVerInfo.getGuid())){
                        objEGetCMWInfoResDWSpecInfos.add(0, objEGetCMWInfoResDWSpecInfo);
                    }else{
                        objEGetCMWInfoResDWSpecInfos.add(objEGetCMWInfoResDWSpecInfo);
                    }

                        /*if("00".equals(wfSpecInfo.geteOSpec()) || "00".equals(wfSpecInfo.geteRSpec())){
                            //非正常流转信息
                            List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(wfSpecInfo.getGuid());
                            if(objEWFCirInfos != null){
                                specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                                if(specVerInfo != null){
                                    objEGetCMWInfoResDWSpecInfo = new GetCMWInfoResDWSpecInfo();
                                    objEGetCMWInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                                    objEGetCMWInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                                    objEGetCMWInfoResDWSpecInfo.setDevGInfo(readDev(specVerInfo.getDevGrGd()));

                                    objEGetCMWInfoResDWSpecInfos.add(objEGetCMWInfoResDWSpecInfo);
                                }
                            }
                        }*/
                }
            }

            objEGetCMWInfoResDWFInfo.setSpecInfo(objEGetCMWInfoResDWSpecInfos);
            objEGetCMWInfoResD.setWFInfo(objEGetCMWInfoResDWFInfo);
        }

        objEGetCMWInfoResD.setBInfo(objEGetCMWInfoResDBInfos);
        objEGetCMWInfoResB.setData(objEGetCMWInfoResD);
        objEGetCMWInfoRes.setBody(objEGetCMWInfoResB);

        return objEGetCMWInfoRes;
    }

    /**
     * 查询批次信息(新)
     * @param request
     * @return
     */
    @Override
    public PageResult<GetCMBBInfoResD> GetBatchInfos(BaseRequest<GetCMBBInfoReq00> request) {
        List<GetCMBBInfoResD> resDList = new ArrayList<>();
        GetCMBBInfoResD resD = null;

        IPage<BInfo> iPage = bdao.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));
        for(BInfo bInfo : iPage.getRecords()){
            resD = new GetCMBBInfoResD();
            resD.setBatch(bInfo.getBatch());
            resD.setNum(bInfo.getNum());
            resD.setCanNum(bInfo.getCanNum());
            resD.setStatus(bInfo.getStatus());

            MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
            if(objEMaVerInfo != null) {
                resD.setMaCode(objEMaVerInfo.getMaterialCode());
                resD.setMaName(objEMaVerInfo.getMaterialName());
                resD.setMaDes(objEMaVerInfo.getMaterialDes());
            }
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    /**
     * 查询冻结批次记录
     * @param request
     * @return
     */
    @Override
    public PageResult<HoldInfo> GetAllHoldInfo(BaseRequest request) {
        IPage<HoldInfo> iPage = holdDAO.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        return new PageResult<>(iPage, iPage.getRecords());
    }

    /**
     * 查询解冻批次记录
     * @param request
     * @return
     */
    @Override
    public PageResult<UnHoldInfo> GetAllUnHoldInfo(BaseRequest request) {
        IPage<UnHoldInfo> iPage = unHoldDAO.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        return new PageResult<>(iPage, iPage.getRecords());
    }

    //根据工序查询设备
    public List<GetCMWInfoResDWSDevGInfo> readDev(String id){
        List<GetCMWInfoResDWSDevGInfo> objEGetCMWInfoResDWSDevGInfos = new ArrayList<>();
        GetCMWInfoResDWSDevGInfo objEGetCMWInfoResDWSDevGInfo = null;
        DevInfo objEDevInfo = null;
        DevStateInfo objEDevStateInfo = null;

        //查询设备组明细
        List<DevGpDtlInfo> objEDevGpDtInfos = devGpDtlDAO.SelectByguid(id);
        if(objEDevGpDtInfos != null && objEDevGpDtInfos.size() > 0){
            for(DevGpDtlInfo devGpDtlInfo : objEDevGpDtInfos) {
                //查询设备
                objEDevInfo = devDAO.SelectByguid(devGpDtlInfo.getDevGd());
                if(objEDevInfo != null){
                    objEGetCMWInfoResDWSDevGInfo = new GetCMWInfoResDWSDevGInfo();
                    objEGetCMWInfoResDWSDevGInfo.setDevRd(objEDevInfo.getRuid());
                    objEGetCMWInfoResDWSDevGInfo.setDevName(objEDevInfo.getDevName());
                        /*//查询设备状态
                        objEDevStateInfo = devStateDAO.SelectByDevGd(objEDevInfo.getDevSMGd());
                        if(objEDevStateInfo != null){
                            objEGetCMWInfoResDWSDevGInfo.setDevStatus(objEDevStateInfo.getStatus());
                        }*/
                    objEGetCMWInfoResDWSDevGInfos.add(objEGetCMWInfoResDWSDevGInfo);
                }
            }
        }

        return objEGetCMWInfoResDWSDevGInfos;
    }
}
