package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllWfInfo.*;
import pnc.mesadmin.dto.GetWfSInfo.*;
import pnc.mesadmin.dto.GetWfVInfo.*;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoRes;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoResB;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoResD;
import pnc.mesadmin.dto.SaveWfInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.WFIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class WFService implements WFIService{

    @Resource
    private WFDAO wfdao;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private WFSpecDAO wfSpecDAO;

    @Resource
    private WFCirDAO wfCirDAO;

    @Resource
    private BCirDAO bCirDAO;

    @Resource
    private BaseIService baseIService;

    private static final String[] MATYPE = {"00", "01"};

    //获取流程信息
    @Override
    public GetAllWfInfoRes GetAllWfInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllWfInfoRes objEGetAllWfInfoRes = new GetAllWfInfoRes();
        GetAllWfInfoResB objEGetAllWfInfoResB = new GetAllWfInfoResB();
        List<GetAllWfInfoResD> objEGetAllWfInfoResDs = new ArrayList<GetAllWfInfoResD>();

        //查询所有的工艺信息
        List<WFInfo> objEWFInfos = baseIService.QALLBaseInfo(WFDAO.class, "SelectAllWFInfo",
                argInitDataFields, argPageInfo);

        if(objEWFInfos != null && objEWFInfos.size() > 0){
            //throw new SystemException("", "工艺信息列表为空");
            for(WFInfo wfInfo : objEWFInfos){
                GetAllWfInfoResD objEGetAllWfInfoResD = new GetAllWfInfoResD();
                objEGetAllWfInfoResD.setWfRd(wfInfo.getRuid());
                objEGetAllWfInfoResD.setWfName(wfInfo.getwFName());

                //查询默认
                WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(wfInfo.getVerGd());
                if(objEWFVerInfo != null){
                    objEGetAllWfInfoResD.setWfVerRd(objEWFVerInfo.getRuid());
                    objEGetAllWfInfoResD.setVersion(objEWFVerInfo.getVersion());
                }

                objEGetAllWfInfoResDs.add(objEGetAllWfInfoResD);
            }
        }

        objEGetAllWfInfoResB.setData(objEGetAllWfInfoResDs);
        objEGetAllWfInfoRes.setBody(objEGetAllWfInfoResB);

        return objEGetAllWfInfoRes;
    }

    /**
     * 获取流程列表信息(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllWfInfoResD> GetAllWfNew(BaseRequest req) {
        List<GetAllWfInfoResD> resDList = new ArrayList<GetAllWfInfoResD>();
        GetAllWfInfoResD resD = null;
        WFInfo wfInfo = null;

        IPage<WFVerInfo> iPage = wfVerDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        for(WFVerInfo wfVerInfo : iPage.getRecords()){
            resD = new GetAllWfInfoResD();
            resD.setWfName(wfVerInfo.getwFName());
            resD.setWfVerRd(wfVerInfo.getRuid());
            resD.setVersion(wfVerInfo.getVersion());

            //查询主表
            wfInfo = wfdao.SelectByGuid(wfVerInfo.getWfGd());
            if(wfInfo != null){
                resD.setWfRd(wfInfo.getRuid());
            }

            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //获取流程版本列表信息
    @Override
    public GetWfVTreeInfoRes GetWfVTreeInfo(GetWfVTreeInfoReqBD00 argBD00) throws SystemException {
        GetWfVTreeInfoRes objEGetWfVTreeInfoRes = new GetWfVTreeInfoRes();
        GetWfVTreeInfoResB objEGetWfVTreeInfoResB = new GetWfVTreeInfoResB();
        List<GetWfVTreeInfoResD> objEGetWfVTreeInfoResDs = new ArrayList<GetWfVTreeInfoResD>();

        //查询流程
        WFInfo objEWFInfo = wfdao.SelectWFInfo(argBD00.getWfRd());

        if(objEWFInfo != null){

            //查询流程版本
            List<WFVerInfo> objEWFVerInfos = wfVerDAO.SelectByWFGd(objEWFInfo.getGuid());

            for(WFVerInfo wfVerInfo : objEWFVerInfos){

                GetWfVTreeInfoResD objEGetWfVTreeInfoResD = new GetWfVTreeInfoResD();
                objEGetWfVTreeInfoResD.setWfVerRd(wfVerInfo.getRuid());
                objEGetWfVTreeInfoResD.setVersion(wfVerInfo.getVersion());
                objEGetWfVTreeInfoResDs.add(objEGetWfVTreeInfoResD);
            }
        }

        objEGetWfVTreeInfoResB.setData(objEGetWfVTreeInfoResDs);
        objEGetWfVTreeInfoRes.setBody(objEGetWfVTreeInfoResB);

        return objEGetWfVTreeInfoRes;
    }

    //获取流程版本信息--WfRd
    @Override
    public GetWfVInfoRes GetWfVInfo(GetWfVInfoReqBD00 argBD00) throws SystemException {
        GetWfVInfoRes objEGetWfVInfoRes = new GetWfVInfoRes();
        GetWfVInfoResB objEGetWfVInfoResB = new GetWfVInfoResB();
        GetWfVInfoResD objEGetWfVInfoResD = new GetWfVInfoResD();

        //查询工艺信息
        WFInfo objEWFInfo = wfdao.SelectWFInfo(argBD00.getWfRd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }
        //查询工艺版本信息
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEWFInfo.getVerGd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "工艺版本信息为空");
        }

        objEGetWfVInfoResD.setWfRd(objEWFInfo.getRuid());
        objEGetWfVInfoResD.setWfVerRd(objEWFVerInfo.getRuid());
        objEGetWfVInfoResD.setWfName(objEWFVerInfo.getwFName());
        objEGetWfVInfoResD.setVersion(objEWFVerInfo.getVersion());
        objEGetWfVInfoResD.setIsDef(objEWFVerInfo.getIsDef());
        objEGetWfVInfoResD.setWFJson(CheckSpec(objEWFVerInfo.getwFJson()));
        objEGetWfVInfoResD.setStatus(objEWFVerInfo.getStatus());
        objEGetWfVInfoResD.setCreator(objEWFVerInfo.getCreator());
        objEGetWfVInfoResD.setCreateTime(DateUtil.format(objEWFVerInfo.getCreateTime()));
        objEGetWfVInfoResD.setLastModifyMan(objEWFVerInfo.getLastModifyMan());
        objEGetWfVInfoResD.setLastModifyTime(DateUtil.format(objEWFVerInfo.getLastModifyTime()));
        objEGetWfVInfoResD.setRemark(objEWFVerInfo.getRemark());
        objEGetWfVInfoResD.setWFType(objEWFVerInfo.getwFType());
        objEGetWfVInfoResB.setData(objEGetWfVInfoResD);
        objEGetWfVInfoRes.setBody(objEGetWfVInfoResB);

        return objEGetWfVInfoRes;
    }

    //获取流程版本信息--WfVerRd
    @Override
    public GetWfVInfoRes GetWfVInfo(GetWfVInfoReqBD01 argBD01) throws SystemException {
        GetWfVInfoRes objEGetWfVInfoRes = new GetWfVInfoRes();
        GetWfVInfoResB objEGetWfVInfoResB = new GetWfVInfoResB();
        GetWfVInfoResD objEGetWfVInfoResD = new GetWfVInfoResD();

        //查询工艺版本信息
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByRuid(argBD01.getWfVerRd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "工艺版本信息为空");
        }
        //查询工艺信息
        WFInfo objEWFInfo = wfdao.SelectByGuid(objEWFVerInfo.getWfGd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }

        objEGetWfVInfoResD.setWfRd(objEWFInfo.getRuid());
        objEGetWfVInfoResD.setWfVerRd(objEWFVerInfo.getRuid());
        objEGetWfVInfoResD.setWfName(objEWFVerInfo.getwFName());
        objEGetWfVInfoResD.setVersion(objEWFVerInfo.getVersion());
        objEGetWfVInfoResD.setIsDef(objEWFVerInfo.getIsDef());
        objEGetWfVInfoResD.setWFJson(CheckSpec(objEWFVerInfo.getwFJson()));
        objEGetWfVInfoResD.setStatus(objEWFVerInfo.getStatus());
        objEGetWfVInfoResD.setCreator(objEWFVerInfo.getCreator());
        objEGetWfVInfoResD.setCreateTime(DateUtil.format(objEWFVerInfo.getCreateTime()));
        objEGetWfVInfoResD.setLastModifyMan(objEWFVerInfo.getLastModifyMan());
        objEGetWfVInfoResD.setLastModifyTime(DateUtil.format(objEWFVerInfo.getLastModifyTime()));
        objEGetWfVInfoResD.setRemark(objEWFVerInfo.getRemark());
        objEGetWfVInfoResD.setWFType(objEWFVerInfo.getwFType());
        objEGetWfVInfoResB.setData(objEGetWfVInfoResD);
        objEGetWfVInfoRes.setBody(objEGetWfVInfoResB);

        return objEGetWfVInfoRes;
    }

    //获取流程工序信息
    @Override
    public GetWfSInfoRes GetWfSInfo(GetWfSInfoReqBD00 argBD00) throws SystemException {
        GetWfSInfoRes objEGetWfSInfoRes = new GetWfSInfoRes();
        GetWfSInfoResB objEGetWfSInfoResB = new GetWfSInfoResB();
        GetWfSInfoResD objEGetWfSInfoResD = new GetWfSInfoResD();
        List<GetWfSInfoResDCondition> objEGetWfSInfoResDConditions = new ArrayList<GetWfSInfoResDCondition>();

        //查询工艺版本信息
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByRuid(argBD00.getWfVerRd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "工艺版本信息为空");
        }

        //查询工序版本信息
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBD00.getSpecVerRd());
        if(objESpecVerInfo == null){
            throw new SystemException("", "工序版本信息为空");
        }

        objEGetWfSInfoResD.setSpecVerRd(objESpecVerInfo.getRuid());
        objEGetWfSInfoResD.setSpecName(objESpecVerInfo.getSpecName());
        //判断是否是起始工序或是结束工序
        if(objEWFVerInfo.getsSpecVerGd().equals(objESpecVerInfo.getGuid())){
            objEGetWfSInfoResD.setSeSpecType("00");
        }/*else if(objEWFVerInfo.geteSpecVerGd().equals(objESpecVerInfo.getGuid())){
            objEGetWfSInfoResD.setSeSpecType("01");
        }*/

        //流程条件
        WFSpecInfo objEWFSpecInfo = wfSpecDAO.SelectByWSVerGd(objEWFVerInfo.getGuid(), objESpecVerInfo.getGuid());
        if(objEWFSpecInfo == null){
            throw new SystemException("", "工艺工序为空");
        }
        List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(objEWFSpecInfo.getGuid());
        for(WFCirInfo wfCirInfo : objEWFCirInfos){
            GetWfSInfoResDCondition objEGetWfSInfoResDCondition = new GetWfSInfoResDCondition();
            objEGetWfSInfoResDCondition.setWfCirRd(wfCirInfo.getRuid());
            objEGetWfSInfoResDCondition.setExpression(wfCirInfo.getExpression());
            //objEGetWfSInfoResDCondition.setRoutePath(wfCirInfo);
            objEGetWfSInfoResDCondition.setRouteType(wfCirInfo.getRouteType());
            objEGetWfSInfoResDConditions.add(objEGetWfSInfoResDCondition);
        }

        objEGetWfSInfoResD.setConfition(objEGetWfSInfoResDConditions);
        objEGetWfSInfoResB.setData(objEGetWfSInfoResD);
        objEGetWfSInfoRes.setBody(objEGetWfSInfoResB);

        return objEGetWfSInfoRes;
    }

    //新增流程信息
    @Override
    public SaveWfInfoRes AddWfInfo(SaveWfInfoReqBD00 argBD00) throws SystemException {
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        //工艺Guid
        String wfGuid = CommonUtils.getRandomNumber();
        //工艺版本信息
        String wfVerGuid = CommonUtils.getRandomNumber();

        //查询判断工艺名称存不存在
        WFInfo objWfInfoByName = wfdao.SelectByName(argBD00.getWfName());
        if(objWfInfoByName != null){
            throw new SystemException("", "该工艺名称已存在");
        }
        if(argBD00.getWFType()==null||"".equals(argBD00.getWFType())){
            throw new SystemException("", "新增失败，该工艺类型不能为空");
        }
        //插入工艺
        WFInfo objEWFInfo = new WFInfo();
        objEWFInfo.setGuid(wfGuid);
        objEWFInfo.setwFName(argBD00.getWfName());
        objEWFInfo.setVerGd(wfVerGuid);
        objEWFInfo.setCreator(userName);
        objEWFInfo.setCreateTime(date);
        if(wfdao.InsertWFInfo(objEWFInfo) <= 0){
            throw new SystemException("", "新增工艺失败");
        }

        //插入工艺工序
        List<SaveWfInfoReqBD00WfInfo> objESaveWfInfoReqBD00WfInfos = argBD00.getWfInfo();
        if(objESaveWfInfoReqBD00WfInfos == null && objESaveWfInfoReqBD00WfInfos.size() <= 0){
            throw new SystemException("", "至少要有一个工艺流程");
        }

        //判断工序是否重复
        Set<Integer> set = new HashSet<Integer>();
        for(SaveWfInfoReqBD00WfInfo wfInfo : objESaveWfInfoReqBD00WfInfos){
            set.add(wfInfo.getSpecVerRd());
        }
        if(set.size() < objESaveWfInfoReqBD00WfInfos.size()){
            throw new SystemException("", "不能存在重复工序");
        }

        for(SaveWfInfoReqBD00WfInfo wfInfo : objESaveWfInfoReqBD00WfInfos){
            //工艺工序Guid
            String wfSpecGuid = CommonUtils.getRandomNumber();
            WFSpecInfo wfSpecInfo = new WFSpecInfo();
            wfSpecInfo.setGuid(wfSpecGuid);
            wfSpecInfo.setWfVerGd(wfVerGuid);


            //查询工序版本
            SpecVerInfo specVerInfo1 = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getSpecVerRd());
            if(specVerInfo1 == null){
                throw new SystemException("", "工序版本信息为空");
            }
            wfSpecInfo.setSpecVerGd(specVerInfo1.getGuid());

            //插入非正常工序流转
            List<SaveWfInfoReqBD00WOSpec> objESaveWfInfoReqBD00WOSpecs = wfInfo.getOSpec();
            List<SaveWfInfoReqBD00WRSpec> objESaveWfInfoReqBD00WRSpecs = wfInfo.getRSpec();
            if(objESaveWfInfoReqBD00WOSpecs != null && objESaveWfInfoReqBD00WOSpecs.size() > 0){
                for(SaveWfInfoReqBD00WOSpec woSpec : objESaveWfInfoReqBD00WOSpecs){
                    WFCirInfo objEWFCirInfo = new WFCirInfo();
                    objEWFCirInfo.setGuid(CommonUtils.getRandomNumber());
                    objEWFCirInfo.setwFSpecGd(wfSpecGuid);
                    objEWFCirInfo.setExpression(woSpec.getExpression());
                    //查询工序版本
                    SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(woSpec.getOSpecVerRd());
                    if(specVerInfo == null){
                        throw new SystemException("", "工序版本信息为空");
                    }
                    objEWFCirInfo.setSpecVerGd(specVerInfo.getGuid());
                    objEWFCirInfo.setRouteType("01");
                    objEWFCirInfo.setCreator(userName);
                    objEWFCirInfo.setCreateTime(date);
                    if(wfCirDAO.InsertWFCir(objEWFCirInfo) <= 0){
                        throw new SystemException("", "可选路线添加失败");
                    }
                }
                wfSpecInfo.seteOSpec("00");
            }
            if(objESaveWfInfoReqBD00WRSpecs != null && objESaveWfInfoReqBD00WRSpecs.size() > 0){
                for(SaveWfInfoReqBD00WRSpec wrSpec : objESaveWfInfoReqBD00WRSpecs){
                    WFCirInfo objEWFCirInfo = new WFCirInfo();
                    objEWFCirInfo.setGuid(CommonUtils.getRandomNumber());
                    objEWFCirInfo.setwFSpecGd(wfSpecGuid);
                    objEWFCirInfo.setExpression(wrSpec.getExpression());
                    //查询工序版本
                    SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(wrSpec.getRSpecVerRd());
                    if(specVerInfo == null){
                        throw new SystemException("", "工序版本信息为空");
                    }
                    objEWFCirInfo.setSpecVerGd(specVerInfo.getGuid());
                    objEWFCirInfo.setRouteType("02");
                    objEWFCirInfo.setCreator(userName);
                    objEWFCirInfo.setCreateTime(date);
                    if(wfCirDAO.InsertWFCir(objEWFCirInfo) <= 0){
                        throw new SystemException("", "返工路线添加失败");
                    }
                }
                wfSpecInfo.seteRSpec("00");
            }

            //查询下一道工序
            if(!(wfInfo.getNSpecVerRd() == 0)){
                SpecVerInfo objESpecVerInfoLast = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getNSpecVerRd());
                if(objESpecVerInfoLast == null){
                    throw new SystemException("", "下一道工序为空");
                }
                wfSpecInfo.setnSpecVerGd(objESpecVerInfoLast.getGuid());
            }
            wfSpecInfo.setCreator(userName);
            wfSpecInfo.setCreateTime(date);
            if(wfSpecDAO.InsertWFSpec(wfSpecInfo) <= 0){
                throw new SystemException("", "工艺工序插入失败");
            }

        }

        //插入工艺版本
        WFVerInfo objEWFVerInfo = new WFVerInfo();
        objEWFVerInfo.setGuid(wfVerGuid);
        objEWFVerInfo.setWfGd(wfGuid);
        objEWFVerInfo.setwFName(argBD00.getWfName());
        objEWFVerInfo.setVersion(argBD00.getVersion());
        objEWFVerInfo.setIsDef("00");
        objEWFVerInfo.setStatus(argBD00.getStatus());
        objEWFVerInfo.setwFJson(argBD00.getWFJson());
        objEWFVerInfo.setsSpecVerGd("");


        for(SaveWfInfoReqBD00WfInfo wfInfo : objESaveWfInfoReqBD00WfInfos){
            //TODO 开始工序
            if("00".equals(wfInfo.getSeSpecType())){
                //查询工序版本
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getSpecVerRd());
                if(specVerInfo == null){
                    throw new SystemException("", "工序版本信息为空");
                }
                objEWFVerInfo.setsSpecVerGd(specVerInfo.getGuid());
                break;
            }

            //TODO 结束工序
            /*specVerInfo = specVerDAO.SelectSpecVerInfoByruid(objESaveWfInfoReqBD00WfInfos.get(objESaveWfInfoReqBD00WfInfos.size() - 1).getSpecVerRd());
            objEWFVerInfo.seteSpecVerGd(specVerInfo.getGuid());*/
        }
        for(int i=0;i<MATYPE.length;i++){
            if(argBD00.getWFType().equals(MATYPE[i])){
                objEWFVerInfo.setwFType(MATYPE[i]);
            }
        }

        objEWFVerInfo.setCreator(userName);
        objEWFVerInfo.setCreateTime(date);
        objEWFVerInfo.setRemark(argBD00.getRemark());
        if(wfVerDAO.InsertWFVerInfo(objEWFVerInfo) <= 0){
            throw new SystemException("", "工艺版本新增失败");
        }

        return objESaveWfInfoRes;
    }

    //删除流程信息
    @Override
    public SaveWfInfoRes RmWfInfo(SaveWfInfoReqBD01 argBD01) throws SystemException {
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();

        //查询工艺
        WFInfo objEWFInfo = wfdao.SelectWFInfo(argBD01.getWfRd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }
        //查询工艺版本
        List<WFVerInfo> objEWFVerInfs = wfVerDAO.SelectByWFGd(objEWFInfo.getGuid());
        if(objEWFVerInfs == null || objEWFVerInfs.size() <= 0){
            throw new SystemException("","工艺版本列表为空");
        }
        for (WFVerInfo wfVerInfo : objEWFVerInfs){
            //查询工艺工序
            List<WFSpecInfo> objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(wfVerInfo.getGuid());
            for(WFSpecInfo wfSpecInfo : objEWFSpecInfos){
                //删除非正常工序流转
                wfCirDAO.DeleteByWFSpecGd(wfSpecInfo.getGuid());
            }
            //删除工艺工序
            wfSpecDAO.DeleteByWFVerGd(wfVerInfo.getGuid());
        }

        //删除工艺版本
        if(wfVerDAO.DeleteByWFGd(objEWFInfo.getGuid()) <= 0){
            throw new SystemException("", "删除工艺版本信息失败");
        }

        //删除工艺
        if(wfdao.DeleteWFInfo(objEWFInfo.getRuid()) <= 0){
            throw new SystemException("", "删除工艺信息失败");
        }

        return objESaveWfInfoRes;
    }

    //编辑流程信息
    @Override
    public SaveWfInfoRes ModWfInfo(SaveWfInfoReqBD02 argBD02) throws SystemException {
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        //判断是否为默认版本--默认为false
        boolean v = false;
        if("00".equals(argBD02.getIsDef())){
            v = true;
        }
        if(argBD02.getWFType()==null||"".equals(argBD02.getWFType())){
            throw new SystemException("", "修改失败，该工艺类型不能为空");
        }

        //查询工艺
        WFInfo objEWFInfo = wfdao.SelectWFInfo(argBD02.getWfRd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }

        //查询判断工艺名称存不存在
        WFInfo objEWfInfoByName = wfdao.SelectByName(argBD02.getWfName());
        if(objEWfInfoByName != null && !objEWFInfo.getwFName().equals(objEWfInfoByName.getwFName())){
            throw new SystemException("", "该工艺名称已存在");
        }

        //查询工艺版本
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByRuid(argBD02.getWfVerRd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "工艺版本信息为空");
        }

        //查询判断工艺版本存不存在
        WFVerInfo objWfVerInfoByVersion = wfVerDAO.SelectByVersion(objEWFInfo.getGuid(), argBD02.getVersion());
        if(objWfVerInfoByVersion != null && !objWfVerInfoByVersion.getVersion().equals(objEWFVerInfo.getVersion())){
            throw new SystemException("", "该工艺版本已存在");
        }

        //判断是不是把默认版本改为非默认
        if("00".equals(objEWFVerInfo.getIsDef()) && "01".equals(argBD02.getIsDef())){
            throw new SystemException("", "必须要有一个默认版本");
        }

        if(v){
            //修改工艺信息
            objEWFInfo.setwFName(argBD02.getWfName());
            objEWFInfo.setVerGd(objEWFVerInfo.getGuid());
            objEWFInfo.setLastModifyMan(userName);
            objEWFInfo.setLastModifyTime(date);


            if(wfdao.UpdateWFInfo(objEWFInfo) <= 0){
                throw new SystemException("", "修改工艺信息失败");
            }

            //修改其他子节点状态
            WFVerInfo wfVerInfo = new WFVerInfo();
            wfVerInfo.setWfGd(objEWFInfo.getGuid());
            wfVerInfo.setwFName(objEWFInfo.getwFName());
            wfVerInfo.setIsDef("01");
            if(wfVerDAO.UpdateWFVerInfoByWFGd(wfVerInfo) <= 0){
                throw new SystemException("", "修改工艺信息失败");
            }

            objEWFVerInfo.setwFName(argBD02.getWfName());
        }

        //修改工艺工序(修改之前先删除)
        //查询工艺工序
        List<WFSpecInfo> objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFVerInfo.getGuid());
        for(WFSpecInfo wfSpecInfo : objEWFSpecInfos){
            //删除非正常工序流转
            wfCirDAO.DeleteByWFSpecGd(wfSpecInfo.getGuid());
        }
        //删除工艺工序
        wfSpecDAO.DeleteByWFVerGd(objEWFVerInfo.getGuid());

        List<SaveWfInfoReqBD02WfInfo> objESaveWfInfoReqBD02WfInfos = argBD02.getWfInfo();
        if(objESaveWfInfoReqBD02WfInfos == null && objESaveWfInfoReqBD02WfInfos.size() <= 0){
            throw new SystemException("", "至少要有一个工艺流程");
        }

        //判断工序是否重复
        Set<Integer> set = new HashSet<Integer>();
        for(SaveWfInfoReqBD02WfInfo wfInfo : objESaveWfInfoReqBD02WfInfos){
            set.add(wfInfo.getSpecVerRd());
        }
        if(set.size() < objESaveWfInfoReqBD02WfInfos.size()){
            throw new SystemException("", "不能存在重复工序");
        }

        for(SaveWfInfoReqBD02WfInfo wfInfo : objESaveWfInfoReqBD02WfInfos){
            //工艺工序Guid
            String wfSpecGuid = CommonUtils.getRandomNumber();
            WFSpecInfo wfSpecInfo = new WFSpecInfo();
            wfSpecInfo.setGuid(wfSpecGuid);
            wfSpecInfo.setWfVerGd(objEWFVerInfo.getGuid());
            //查询工序版本
            SpecVerInfo specVerInfo1 = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getSpecVerRd());
            if(specVerInfo1 == null){
                throw new SystemException("", "工序版本信息为空");
            }
            wfSpecInfo.setSpecVerGd(specVerInfo1.getGuid());

            //插入非正常工序流转
            List<SaveWfInfoReqBD02WOSpec> objESaveWfInfoReqBD02WOSpecs = wfInfo.getOSpec();
            List<SaveWfInfoReqBD02WRSpec> objESaveWfInfoReqBD02WRSpecs = wfInfo.getRSpec();
            if(objESaveWfInfoReqBD02WOSpecs != null && objESaveWfInfoReqBD02WOSpecs.size() > 0){
                for(SaveWfInfoReqBD02WOSpec woSpec : objESaveWfInfoReqBD02WOSpecs){
                    WFCirInfo objEWFCirInfo = new WFCirInfo();
                    objEWFCirInfo.setGuid(CommonUtils.getRandomNumber());
                    objEWFCirInfo.setwFSpecGd(wfSpecGuid);
                    objEWFCirInfo.setExpression(woSpec.getExpression());
                    //查询工序版本
                    SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(woSpec.getOSpecVerRd());
                    if(specVerInfo == null){
                        throw new SystemException("", "工序版本信息为空");
                    }
                    objEWFCirInfo.setSpecVerGd(specVerInfo.getGuid());
                    objEWFCirInfo.setRouteType("01");
                    objEWFCirInfo.setCreator(userName);
                    objEWFCirInfo.setCreateTime(date);
                    if(wfCirDAO.InsertWFCir(objEWFCirInfo) <= 0){
                        throw new SystemException("1", "可选路线添加失败");
                    }
                }
                wfSpecInfo.seteOSpec("00");
            }
            if(objESaveWfInfoReqBD02WRSpecs != null && objESaveWfInfoReqBD02WRSpecs.size() > 0){
                for(SaveWfInfoReqBD02WRSpec wrSpec : objESaveWfInfoReqBD02WRSpecs){
                    WFCirInfo objEWFCirInfo = new WFCirInfo();
                    objEWFCirInfo.setGuid(CommonUtils.getRandomNumber());
                    objEWFCirInfo.setwFSpecGd(wfSpecGuid);
                    objEWFCirInfo.setExpression(wrSpec.getExpression());
                    //查询工序版本
                    SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(wrSpec.getRSpecVerRd());
                    if(specVerInfo == null){
                        throw new SystemException("1", "工序版本信息为空");
                    }
                    objEWFCirInfo.setSpecVerGd(specVerInfo.getGuid());
                    objEWFCirInfo.setRouteType("02");
                    objEWFCirInfo.setCreator(userName);
                    objEWFCirInfo.setCreateTime(date);
                    if(wfCirDAO.InsertWFCir(objEWFCirInfo) <= 0){
                        throw new SystemException("", "返工路线添加失败");
                    }
                }
                wfSpecInfo.seteRSpec("00");
            }

            //查询下一道工序
            if(!(wfInfo.getNSpecVerRd() == 0)) {
                SpecVerInfo objESpecVerInfoLast = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getNSpecVerRd());
                if (objESpecVerInfoLast == null) {
                    throw new SystemException("1", "下一道工序为空");
                }
                wfSpecInfo.setnSpecVerGd(objESpecVerInfoLast.getGuid());
            }
            wfSpecInfo.setCreator(userName);
            wfSpecInfo.setCreateTime(date);
            if(wfSpecDAO.InsertWFSpec(wfSpecInfo) <= 0){
                throw new SystemException("1", "工艺工序插入失败");
            }

        }

        //修改工艺版本信息
        objEWFVerInfo.setVersion(argBD02.getVersion());
        objEWFVerInfo.setIsDef(argBD02.getIsDef());
        objEWFVerInfo.setStatus(argBD02.getStatus());
        objEWFVerInfo.setwFJson(argBD02.getWFJson());
        objEWFVerInfo.setLastModifyMan(userName);
        objEWFVerInfo.setLastModifyTime(date);
        objEWFVerInfo.setRemark(argBD02.getRemark());
        for(int i=0;i<MATYPE.length;i++){
            if(argBD02.getWFType().equals(MATYPE[i])){
                objEWFVerInfo.setwFType(MATYPE[i]);
            }
        }

        for(SaveWfInfoReqBD02WfInfo wfInfo : objESaveWfInfoReqBD02WfInfos) {
            //TODO 开始工序
            if ("00".equals(wfInfo.getSeSpecType())) {
                //查询工序版本
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getSpecVerRd());
                if (specVerInfo == null) {
                    throw new SystemException("1", "工序版本信息为空");
                }
                objEWFVerInfo.setsSpecVerGd(specVerInfo.getGuid());
                break;
            }
        }

        if(wfVerDAO.UpdateWFVerInfo(objEWFVerInfo) <= 0){
            throw new SystemException("", "修改工艺版本信息失败");
        }

        return objESaveWfInfoRes;
    }

    //复制流程信息
    @Override
    public SaveWfInfoRes AddCopyWfInfo(SaveWfInfoReqBD03 argBD03) throws SystemException {
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();

        //当前用户
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        //当前时间
        Date date = new Date();
        //工艺Guid
        String wfGuid = CommonUtils.getRandomNumber();
        //工艺版本信息
        String wfVerGuid = CommonUtils.getRandomNumber();

        //查询工艺
        WFInfo objEWFInfo = wfdao.SelectWFInfo(argBD03.getWfRd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }
        //查询默认工艺版本
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEWFInfo.getVerGd());
        if(objEWFVerInfo == null){
            throw new SystemException("", "工艺版本信息为空");
        }
        //查询工艺工序
        List<WFSpecInfo> objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFVerInfo.getGuid());

        //插入工艺
        objEWFInfo.setGuid(wfGuid);
        objEWFInfo.setVerGd(wfVerGuid);
        objEWFInfo.setCreator(userName);
        objEWFInfo.setCreateTime(date);
        objEWFInfo.setLastModifyMan(null);
        objEWFInfo.setLastModifyTime(null);
        wfdao.InsertWFInfo(objEWFInfo);

        WFInfo wfInfo = wfdao.SelectByGuid(wfGuid);

        objEWFInfo.setwFName(objEWFInfo.getwFName() + wfInfo.getRuid());

        if(wfdao.UpdateWFInfo(objEWFInfo) <= 0){
            throw new SystemException("", "改工艺流程复制失败");
        }

        objEWFVerInfo.setwFName(objEWFInfo.getwFName());

        //插入工艺工序
        for(WFSpecInfo wfSpecInfo : objEWFSpecInfos){
            //工艺工序Guid
            String wFSpecGuid = CommonUtils.getRandomNumber();
            //查询非正常流转
            List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(wfSpecInfo.getGuid());
            for(WFCirInfo wfCirInfo : objEWFCirInfos){
                //插入非正常流转
                wfCirInfo.setGuid(CommonUtils.getRandomNumber());
                wfCirInfo.setwFSpecGd(wFSpecGuid);
                wfCirInfo.setCreator(userName);
                wfCirInfo.setCreateTime(date);
                wfCirInfo.setLastModifyMan(null);
                wfCirInfo.setLastModifyTime(null);
                if(wfCirDAO.InsertWFCir(wfCirInfo) <= 0){
                    throw new SystemException("", "复制非正常流转信息失败");
                }
            }

            wfSpecInfo.setGuid(wfVerGuid);
            wfSpecInfo.setCreator(userName);
            wfSpecInfo.setCreateTime(date);
            wfSpecInfo.setLastModifyMan(null);
            wfSpecInfo.setLastModifyTime(null);
            if(wfSpecDAO.InsertWFSpec(wfSpecInfo) <= 0){
                throw new SystemException("", "复制工艺工序信息失败");
            }

        }

        //插入工艺版本
        objEWFVerInfo.setGuid(wfVerGuid);
        objEWFVerInfo.setWfGd(wfGuid);
        objEWFVerInfo.setCreator(userName);
        objEWFVerInfo.setCreateTime(date);
        objEWFVerInfo.setLastModifyMan(null);
        objEWFVerInfo.setLastModifyTime(null);
        if(wfVerDAO.InsertWFVerInfo(objEWFVerInfo) <= 0){
            throw new SystemException("", "插入工艺版本信息失败");
        }

        return objESaveWfInfoRes;
    }

    //新增流程版本信息
    @Override
    public SaveWfInfoRes AddWfVerInfo(SaveWfInfoReqBD04 argBD04) throws SystemException {
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        //工艺版本信息
        String wfVerGuid = CommonUtils.getRandomNumber();
        //判断是否为默认版本--默认为false
        boolean v = false;
        if("00".equals(argBD04.getIsDef())){
            v = true;
        }
        if(argBD04.getWFType()==null||"".equals(argBD04.getWFType())){
            throw new SystemException("", "新增失败，该工艺类型不能为空");
        }
        //查询工艺
        WFInfo objEWFInfo = wfdao.SelectWFInfo(argBD04.getWfRd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }

        if(v){
            //查询判断工艺名称存不存在
            WFInfo objEWfInfoByName = wfdao.SelectByName(argBD04.getWfName());
            if(objEWfInfoByName != null && !objEWFInfo.getwFName().equals(objEWfInfoByName.getwFName())){
                throw new SystemException("", "该工艺名称已存在");
            }

            //修改工艺信息
            objEWFInfo.setwFName(argBD04.getWfName());
            objEWFInfo.setVerGd(wfVerGuid);
            objEWFInfo.setLastModifyMan(userName);
            objEWFInfo.setLastModifyTime(date);

            if(wfdao.UpdateWFInfo(objEWFInfo) <= 0){
                throw new SystemException("", "修改工艺信息失败");
            }

            //修改其他子节点状态
            WFVerInfo wfVerInfo = new WFVerInfo();
            wfVerInfo.setWfGd(objEWFInfo.getGuid());
            wfVerInfo.setwFName(objEWFInfo.getwFName());
            wfVerInfo.setIsDef("01");
            if(wfVerDAO.UpdateWFVerInfoByWFGd(wfVerInfo) <= 0){
                throw new SystemException("", "修改工艺信息失败");
            }
        }

        //查询判断工艺版本存不存在
        WFVerInfo objWfVerInfoByVersion = wfVerDAO.SelectByVersion(objEWFInfo.getGuid(), argBD04.getVersion());
        if(objWfVerInfoByVersion != null){
            throw new SystemException("", "该工艺版本已存在");
        }

        //插入工艺工序
        List<SaveWfInfoReqBD04WfInfo> objESaveWfInfoReqBD04WfInfos = argBD04.getWfInfo();
        if(objESaveWfInfoReqBD04WfInfos != null && objESaveWfInfoReqBD04WfInfos.size() > 0){
            for(SaveWfInfoReqBD04WfInfo wfInfo : objESaveWfInfoReqBD04WfInfos){
                //工艺工序Guid
                String wfSpecGuid = CommonUtils.getRandomNumber();
                WFSpecInfo wfSpecInfo = new WFSpecInfo();
                wfSpecInfo.setGuid(wfSpecGuid);
                wfSpecInfo.setWfVerGd(wfVerGuid);
                //查询工序版本
                SpecVerInfo specVerInfo1 = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getSpecVerRd());
                if(specVerInfo1 == null){
                    throw new SystemException("1", "工序版本信息为空");
                }
                wfSpecInfo.setSpecVerGd(specVerInfo1.getGuid());

                //插入非正常工序流转
                List<SaveWfInfoReqBD04WOSpec> objESaveWfInfoReqBD04WOSpecs = wfInfo.getOSpec();
                List<SaveWfInfoReqBD04WRSpec> objESaveWfInfoReqBD04WRSpecs = wfInfo.getRSpec();
                if(objESaveWfInfoReqBD04WOSpecs != null && objESaveWfInfoReqBD04WOSpecs.size() > 0){
                    for(SaveWfInfoReqBD04WOSpec woSpec : objESaveWfInfoReqBD04WOSpecs){
                        WFCirInfo objEWFCirInfo = new WFCirInfo();
                        objEWFCirInfo.setGuid(CommonUtils.getRandomNumber());
                        objEWFCirInfo.setwFSpecGd(wfSpecGuid);
                        objEWFCirInfo.setExpression(woSpec.getExpression());
                        //查询工序版本
                        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(woSpec.getOSpecVerRd());
                        if(specVerInfo == null){
                            throw new SystemException("1", "工序版本信息为空");
                        }
                        objEWFCirInfo.setSpecVerGd(specVerInfo.getGuid());
                        objEWFCirInfo.setRouteType("01");
                        objEWFCirInfo.setCreator(userName);
                        objEWFCirInfo.setCreateTime(date);
                        if(wfCirDAO.InsertWFCir(objEWFCirInfo) <= 0){
                            throw new SystemException("", "可选路线添加失败");
                        }
                    }
                    wfSpecInfo.seteOSpec("00");
                }
                if(objESaveWfInfoReqBD04WRSpecs != null && objESaveWfInfoReqBD04WRSpecs.size() > 0){
                    for(SaveWfInfoReqBD04WRSpec wrSpec : objESaveWfInfoReqBD04WRSpecs){
                        WFCirInfo objEWFCirInfo = new WFCirInfo();
                        objEWFCirInfo.setGuid(CommonUtils.getRandomNumber());
                        objEWFCirInfo.setwFSpecGd(wfSpecGuid);
                        objEWFCirInfo.setExpression(wrSpec.getExpression());
                        //查询工序版本
                        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(wrSpec.getRSpecVerRd());
                        if(specVerInfo == null){
                            throw new SystemException("1", "工序版本信息为空");
                        }
                        objEWFCirInfo.setSpecVerGd(specVerInfo.getGuid());
                        objEWFCirInfo.setRouteType("02");
                        objEWFCirInfo.setCreator(userName);
                        objEWFCirInfo.setCreateTime(date);
                        if(wfCirDAO.InsertWFCir(objEWFCirInfo) <= 0){
                            throw new SystemException("1", "返工路线添加失败");
                        }
                    }
                    wfSpecInfo.seteRSpec("00");
                }

                //查询下一道工序
                if(!(wfInfo.getNSpecVerRd() == 0)) {
                    SpecVerInfo objESpecVerInfoLast = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getNSpecVerRd());
                    if (objESpecVerInfoLast == null) {
                        throw new SystemException("2", "下一道工序为空");
                    }
                    wfSpecInfo.setnSpecVerGd(objESpecVerInfoLast.getGuid());
                }
                wfSpecInfo.setCreator(userName);
                wfSpecInfo.setCreateTime(date);
                if(wfSpecDAO.InsertWFSpec(wfSpecInfo) <= 0){
                    throw new SystemException("", "工艺工序插入失败");
                }

            }
        }

        WFVerInfo objEWFVerInfo = new WFVerInfo();
        objEWFVerInfo.setGuid(wfVerGuid);
        objEWFVerInfo.setWfGd(objEWFInfo.getGuid());
        if(v){
            objEWFVerInfo.setwFName(argBD04.getWfName());
        }else{
            objEWFVerInfo.setwFName(objEWFInfo.getwFName());
        }
        objEWFVerInfo.setVersion(argBD04.getVersion());
        objEWFVerInfo.setIsDef(argBD04.getIsDef());
        objEWFVerInfo.setStatus(argBD04.getStatus());
        objEWFVerInfo.setwFJson(argBD04.getWFJson());

        for(SaveWfInfoReqBD04WfInfo wfInfo : objESaveWfInfoReqBD04WfInfos) {
            //TODO 开始工序
            if ("00".equals(wfInfo.getSeSpecType())) {
                //查询工序版本
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(wfInfo.getSpecVerRd());
                if (specVerInfo == null) {
                    throw new SystemException("3", "工序版本信息为空");
                }
                objEWFVerInfo.setsSpecVerGd(specVerInfo.getGuid());
                break;
            }
        }
        for(int i=0;i<MATYPE.length;i++){
            if(argBD04.getWFType().equals(MATYPE[i])){
                objEWFVerInfo.setwFType(MATYPE[i]);
            }
        }

        objEWFVerInfo.setCreator(userName);
        objEWFVerInfo.setCreateTime(date);
        objEWFVerInfo.setRemark(argBD04.getRemark());
        if(wfVerDAO.InsertWFVerInfo(objEWFVerInfo) <= 0){
            throw new SystemException("", "工艺版本新增失败");
        }

        return objESaveWfInfoRes;
    }

    //删除流程版本信息
    @Override
    public SaveWfInfoRes RmWfVerInfo(SaveWfInfoReqBD05 argBD05) throws SystemException {
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();

        //查询工艺版本
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByRuid(argBD05.getWfVerRd());
        if(objEWFVerInfo == null){
            throw new SystemException("","工艺版本列表为空");
        }
        //查询工艺
        WFInfo objEWFInfo = wfdao.SelectByGuid(objEWFVerInfo.getWfGd());
        if(objEWFInfo == null){
            throw new SystemException("", "工艺信息为空");
        }
        //判断当前版本是不是默认版本
        if(objEWFVerInfo.getGuid().equals(objEWFInfo.getVerGd())){
            //查询工艺版本
            List<WFVerInfo> objEWFVerInfs = wfVerDAO.SelectByWFGd(objEWFInfo.getGuid());
            if(objEWFVerInfs == null || objEWFVerInfs.size() <= 0){
                throw new SystemException("","工艺版本列表为空");
            }
            for (WFVerInfo wfVerInfo : objEWFVerInfs){
                //查询工艺工序
                List<WFSpecInfo> objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(wfVerInfo.getGuid());
                for(WFSpecInfo wfSpecInfo : objEWFSpecInfos){
                    //删除非正常工序流转
                    wfCirDAO.DeleteByWFSpecGd(wfSpecInfo.getGuid());
                }
                //删除工艺工序
                wfSpecDAO.DeleteByWFVerGd(wfVerInfo.getGuid());
            }

            //删除工艺版本
            if(wfVerDAO.DeleteByWFGd(objEWFInfo.getGuid()) <= 0){
                throw new SystemException("", "删除工艺版本信息失败");
            }

            //删除工艺
            if(wfdao.DeleteWFInfo(objEWFInfo.getRuid()) <= 0){
                throw new SystemException("", "删除工艺信息失败");
            }
        }else{
            //查询工艺工序
            List<WFSpecInfo> objEWFSpecInfos = wfSpecDAO.SelectByWFVerGd(objEWFVerInfo.getGuid());
            for(WFSpecInfo wfSpecInfo : objEWFSpecInfos){
                //删除非正常工序流转
                wfCirDAO.DeleteByWFSpecGd(wfSpecInfo.getGuid());
            }
            //删除工艺工序
            wfSpecDAO.DeleteByWFVerGd(objEWFVerInfo.getGuid());

            //删除工艺版本
            if(wfVerDAO.DeleteWFVerInfo(objEWFVerInfo.getRuid()) <= 0){
                throw new SystemException("", "删除工艺版本信息失败");
            }
        }

        return objESaveWfInfoRes;
    }

    //判断当前工序能不能删除
    @Override
    public SaveWfInfoRes GetWfVerInfo(SaveWfInfoReqBD06 argBD06) throws SystemException {
        //查询工艺流程
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByRuid(argBD06.getWfVerRd());
        if(objEWFVerInfo == null){
            throw new SystemException("0x00000","工艺流程版本不存在!");
        }
        //查询工序版本
        SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(argBD06.getSpecVerRd());
        if(objESpecVerInfo == null){
            throw new SystemException("0x00000","工序版本不存在!");
        }
        int num = bCirDAO.SelectByWFVerGdSpecVerGd(objEWFVerInfo.getGuid(), objESpecVerInfo.getGuid());
        if(num > 0){
            throw new SystemException("0x00000", "工序已被使用，删除可能会导致无法出站!");
        }

        return new SaveWfInfoRes();
    }

    //验证流程中工序是否存在
    public String CheckSpec(String json){

        String[] allData = json.split("~");

        if(allData.length < 2){
            return "";
        }

        String data = allData[1];

        //List<String> obj = new ArrayList<String>();

        /*String regex1 = "\\{[^}]*\\}";

        Pattern pattern1 = Pattern.compile(regex1);

        Matcher matcher1 = pattern1.matcher(data);

        while (matcher1.find()){
            obj.add(matcher1.group());
        }*/

        List<String> obj1 = new ArrayList<String>();

        JSONArray jsonArray = JSONArray.fromObject(data);

        for(int i=0; i<jsonArray.size(); i++){
            String s_ruid = (String) jsonArray.optJSONObject(i).get("blockSpecVerRd");

            if("".equals(s_ruid)){
                //obj.remove(i);
                break;
            }

            //查询工序版本
            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(Integer.valueOf(s_ruid));
            if(objESpecVerInfo == null){
                break;
            }
            obj1.add(jsonArray.getString(i));
        }

        /*for(int i=0; i<obj.size(); i++){

            String regex2 = "\"blockSpecVerRd\":\"(\\d*)";

            Pattern pattern = Pattern.compile(regex2);
            Matcher matcher = pattern.matcher(obj.get(i));

            while (matcher.find()){
                String ss = matcher.group();
                String ruid = ss.substring(ss.lastIndexOf("\"") + 1);

                if("".equals(ruid)){
                    obj.remove(i);
                    break;
                }

                //查询工序版本
                SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(Integer.valueOf(ruid));
                if(objESpecVerInfo == null){
                    break;
                }
                obj1.add(obj.get(i));
            }
        }*/

        StringBuilder sb = new StringBuilder();
        sb.append(allData[0]);
        sb.append("~[");
        for(String s : obj1){
            sb.append(s);
            sb.append(",");
        }

        if(obj1.size() > 0){
            sb.deleteCharAt(sb.length()-1);
        }

        sb.append("]");

        return sb.toString();
    }
}
