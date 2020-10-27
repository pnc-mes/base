package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllWOBInfo.*;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoRes;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoResB;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoResD;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWFInfo;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWSDevGInfo;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWSpecInfo;
import pnc.mesadmin.dto.GetWOInfo.*;
import pnc.mesadmin.dto.SaveWOInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.WOIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工单实现层
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class WOService implements WOIService {
    @Resource
    private WODAO wODAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private OrderLineDAO orderLineDAO;

    @Resource
    private BDAO batchDAO;

    @Resource
    private UrgencyDAO urgencyDAO;

    @Resource
    private PackSpecificationDAO packSpecificationDAO;

    @Resource
    private PickBatchDAO pickBatchDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private WoTypeDAO woTypeDAO;

    @Resource
    private ModeCDAO modeCDAO;

    @Resource
    private PrintTBFDAO printTBFDAO;

    @Resource
    private PrinterDAO printerDAO;

    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private BAttachDAO bAttachDAO;

//    @Resource
//    private HisPrintLogIService hisPrintLogIService;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private BomDAO bomDAO;

    @Resource
    private WFDAO wfdao;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;
    @Resource
    private LineDao lineDao;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private WFSpecDAO wfSpecDAO;

    @Resource
    private BCirDAO bCirDAO;

    @Resource
    private DevGpDtlDAO devGpDtlDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private DevStateDAO devStateDAO;

    //dto查询工单列表信息
    @Override
    public PageResult<GetAllWOInfoRes> QALLGetAllNewWOInfoRes(BaseRequest req) {
        GetAllWOInfoRes objEGetAllWOInfoRes = new GetAllWOInfoRes();
        GetAllWOInfoResB objEGetAllWOInfoResB = new GetAllWOInfoResB();

        List<GetAllWOInfoResD> objEGetAllWOInfoResDs = new ArrayList<GetAllWOInfoResD>();

        Page<WoInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<WoInfo> woInfoIPage = wODAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));


        List<WoInfo> objEWoInfos=woInfoIPage.getRecords();
        if (objEWoInfos != null && objEWoInfos.size() > 0) {
            for (WoInfo obj : objEWoInfos) {
                GetAllWOInfoResD objEGetAllWOInfoResD = new GetAllWOInfoResD();
                objEGetAllWOInfoResD.setWoRd(obj.getRuid());
                objEGetAllWOInfoResD.setWoCode(obj.getWoCode());
                //工单类型
                GetWOInfoResBDWOT objEGetWOInfoResBDWOT = new GetWOInfoResBDWOT();
                WoTypeInfo objEWoTypeInfo = woTypeDAO.SelectWoTypeByGuid(obj.getWoTGd());
                if (objEWoTypeInfo != null) {
                    objEGetAllWOInfoResD.setWoTName(objEWoTypeInfo.getWoTName());
                    objEGetAllWOInfoResD.setWotRd(objEWoTypeInfo.getRuid());
                }
                //紧急度
                UrgencyInfo objEUrgencyInfo = urgencyDAO.SelectByguid(obj.getUrcyGd());
                if (objEUrgencyInfo != null) {
                    objEGetAllWOInfoResD.setUrcyDes(objEUrgencyInfo.getUrcyDes());
                    objEGetAllWOInfoResD.setUrcyRd(objEUrgencyInfo.getRuid());
                }
                objEGetAllWOInfoResD.setNum(obj.getNum());
                objEGetAllWOInfoResD.setUnCBatNum(obj.getUnCBatNum());
                objEGetAllWOInfoResD.setFinishNum(obj.getFinishNum());
                objEGetAllWOInfoResD.setStatus(obj.getStatus());
                if("00".equals(obj.getStatus())){
                    objEGetAllWOInfoResD.setStatusText("待处理");
                } else if ("01".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("下达");
                } else if ("02".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("取消");
                } else if ("03".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("处理中");
                } else if ("04".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("冻结");
                } else if ("05".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("终止");
                } else if ("06".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("完成");
                } else if ("07".equals(obj.getStatus())) {
                    objEGetAllWOInfoResD.setStatusText("关闭");
                }

                BomVerInfo bomVerInfo=bomVerDAO.SelectBomVerInfo(obj.getBomVerGd());
                if(bomVerInfo!=null){
                    objEGetAllWOInfoResD.setBomCode(bomVerInfo.getBomCode());
                    objEGetAllWOInfoResD.setBomVer(bomVerInfo.getVersion());
                }


                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                objEGetAllWOInfoResD.setJStartDate(format.format(obj.getjStartDate()));
                objEGetAllWOInfoResD.setJFinishDate(format.format(obj.getjFinishDate()));


                //物料
                MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                if(maVerInfo!=null){
                    objEGetAllWOInfoResD.setMaterialName(maVerInfo.getMaterialName());
                    objEGetAllWOInfoResD.setMaterialCode(maVerInfo.getMaterialCode());
                    objEGetAllWOInfoResD.setMaterialRd(maVerInfo.getRuid());
                    objEGetAllWOInfoResD.setMaverGd(maVerInfo.getGuid());
                    UnitInfo unitInfo=unitDAO.SelectByGuid(maVerInfo.getUnitGd());

                    if(unitInfo!=null){
                        objEGetAllWOInfoResD.setUnitName(unitInfo.getUnitName());
                    }
                }

                WFVerInfo wfVerInfo = wfVerDAO.SelectByGuid(obj.getwFVerGd());
                if(wfVerInfo!=null){
                    WFInfo wfInfo=wfdao.SelectByGuid(wfVerInfo.getWfGd());
                    if(wfInfo!=null){
                        objEGetAllWOInfoResD.setWfName(wfInfo.getwFName());
                        objEGetAllWOInfoResD.setWfRd(wfInfo.getRuid());
                    }

                }

                List<GetAllWOInfoResD.LineInfo> lineInfos=new ArrayList<>();
                //获取工单下面的线体
                List<OrderLineinfo> orderLineinfos=orderLineDAO.SelectOrderLineByGuid(obj.getGuid());
                String lineNames="";
                if(orderLineinfos!=null&&orderLineinfos.size()>0){
                    for(OrderLineinfo ob:orderLineinfos){
                        GetAllWOInfoResD.LineInfo lineInfo=new GetAllWOInfoResD.LineInfo();
                        Lineinfo lineinfo=lineDao.SelectLineInfoByguid(ob.getLineGd());
                        if(lineinfo!=null){
                            lineInfo.setLineRd(lineinfo.getRuid());
                            lineInfo.setLineName(lineinfo.getLineName());
                            lineInfos.add(lineInfo);
                        }
                    }
                }
                objEGetAllWOInfoResD.setLineInfo(lineInfos);
                objEGetAllWOInfoResD.setCreateTime(CommonUtils.getFormat(obj.getCreateTime()));
                objEGetAllWOInfoResD.setCreator(obj.getCreator());
                objEGetAllWOInfoResD.setLastModifyMan(obj.getLastModifyMan());
                objEGetAllWOInfoResD.setLastModifyTime(CommonUtils.getFormat(obj.getLastModifyTime()));
                objEGetAllWOInfoResD.setRemark(obj.getRemark());
                objEGetAllWOInfoResDs.add(objEGetAllWOInfoResD);
            }
        }
        objEGetAllWOInfoResB.setData(objEGetAllWOInfoResDs);
        objEGetAllWOInfoResB.setData(objEGetAllWOInfoResDs);
        objEGetAllWOInfoRes.setBody(objEGetAllWOInfoResB);
        return new PageResult(woInfoIPage.getTotal(), woInfoIPage.getPages(), woInfoIPage.getCurrent(), woInfoIPage.getSize(), objEGetAllWOInfoResDs);
    }

    @Override
    public GetAllWOInfoRes QALLGetAllWOInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllWOInfoRes objEGetAllWOInfoRes = new GetAllWOInfoRes();
        GetAllWOInfoResB objEGetAllWOInfoResB = new GetAllWOInfoResB();

        List<GetAllWOInfoResD> objEGetAllWOInfoResDs = new ArrayList<GetAllWOInfoResD>();

        List<WoInfo> objEWoInfos = baseIService.QALLBaseInfo(WODAO.class, "SelectWoInfo",
                argInitDataFields, argPageInfo);
        if (objEWoInfos != null && objEWoInfos.size() > 0) {
            for (WoInfo obj : objEWoInfos) {
                GetAllWOInfoResD objEGetAllWOInfoResD = new GetAllWOInfoResD();
                objEGetAllWOInfoResD.setWoRd(obj.getRuid());
                objEGetAllWOInfoResD.setWoCode(obj.getWoCode());
                objEGetAllWOInfoResDs.add(objEGetAllWOInfoResD);
            }
        }
        objEGetAllWOInfoResB.setData(objEGetAllWOInfoResDs);
        objEGetAllWOInfoResB.setData(objEGetAllWOInfoResDs);
        objEGetAllWOInfoRes.setBody(objEGetAllWOInfoResB);
        return objEGetAllWOInfoRes;
    }


    @Override
    public BaseRes QALLGetAllWoLineALl(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<OrderLineinfo> orderLineinfoList = baseIService.QALLBaseInfo(OrderLineDAO.class, "SelectAllWoGdOrderLineinfo",
                argInitDataFields, argPageInfo);
        List<String> woGd = new ArrayList<>();
        for (OrderLineinfo mdoel : orderLineinfoList) {
            woGd.add(mdoel.getWoGd());
        }
        String resData = woGd.toString();
        resData = resData.substring(1, resData.length() - 1);
        baseResBody.setData(resData);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //dto查询批次信息，根据工单id
    @Override
    public GetAllWOBInfoRes GetGetAllWOBInfoRes(GetAllWOBInfoReqBD00 argGetAllWOBInfoReqBD00) {
        GetAllWOBInfoRes objEGetAllWOBInfoRes = new GetAllWOBInfoRes();
        GetAllWOBInfoResB objEGetAllWOBInfoResB = new GetAllWOBInfoResB();
        List<GetAllWOBInfoResD> objEGetAllWOBInfoResDs = new ArrayList<GetAllWOBInfoResD>();

        //获取工单信息
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argGetAllWOBInfoReqBD00.getWoRd());
        if (objEWoInfo != null) {

            //根据工单里的标识到彼此中查询信息
            List<BInfo> objEBInfos = batchDAO.SelectBatchByGuidAnd00(objEWoInfo.getGuid());
            if (objEBInfos != null || objEBInfos.size() != 0) {
                for (BInfo obj : objEBInfos) {
                    GetAllWOBInfoResD objEGetAllWOBInfoResD = new GetAllWOBInfoResD();
                    objEGetAllWOBInfoResD.setWoBRd(obj.getRuid());
                    objEGetAllWOBInfoResD.setBatch(obj.getBatch());
                    objEGetAllWOBInfoResD.setNum(obj.getNum());
                    objEGetAllWOBInfoResD.setCanNum(obj.getCanNum());

                    MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                    if (objEMaVerInfo != null) {
                        objEGetAllWOBInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
                        objEGetAllWOBInfoResD.setMaName(objEMaVerInfo.getMaterialName() + objEMaVerInfo.getRuid());
                        objEGetAllWOBInfoResD.setMaterialDes(objEMaVerInfo.getMaterialDes());
                    }
                    //单位
                    UnitInfo objEUnitInfo = unitDAO.SelectByGuid(obj.getUnitGd());
                    if (objEUnitInfo != null) {

                        objEGetAllWOBInfoResD.setUnitName(objEUnitInfo.getUnitName());
                    } else {
                        objEGetAllWOBInfoResD.setUnitName("");
                    }
                    //工序
                    BCirInfo CirInfo = bCirDAO.SelectByBatch(obj.getBatch());
                    if (CirInfo != null) {
                        SpecVerInfo objSpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(CirInfo.getSpecVerGd());
                        if (objSpecVerInfo != null) {
                            objEGetAllWOBInfoResD.setSpecName(objSpecVerInfo.getSpecName());
                        } else {
                            objEGetAllWOBInfoResD.setSpecName("");
                        }
                    } else {

                        objEGetAllWOBInfoResD.setSpecName("");
                    }

                    if ("00".equals(obj.getStatus())) {
                        objEGetAllWOBInfoResD.setStatus("待处理");
                    } else if ("01".equals(obj.getStatus())) {
                        objEGetAllWOBInfoResD.setStatus("正在处理");
                    } else if ("02".equals(obj.getStatus())) {
                        objEGetAllWOBInfoResD.setStatus("完工");
                    } else {
                        objEGetAllWOBInfoResD.setStatus("待处理");
                    }

                    objEGetAllWOBInfoResD.setJFDate(DateUtil.format(obj.getjFinishDate()));
                    objEGetAllWOBInfoResD.setSFDate(DateUtil.format(obj.getsFinishDate()));
                    objEGetAllWOBInfoResDs.add(objEGetAllWOBInfoResD);
                }
            }
        }
        objEGetAllWOBInfoResB.setData(objEGetAllWOBInfoResDs);
        objEGetAllWOBInfoRes.setBody(objEGetAllWOBInfoResB);

        return objEGetAllWOBInfoRes;
    }

    //dto查询工单信息，根据工单id
    @Override
    public GetWOInfoRes GetGetWOInfoRes(GetWOInfoReqBD00 argGetWOInfoReqBD00) {
        GetWOInfoRes objEGetWOInfoRes = new GetWOInfoRes();
        GetWOInfoResB objEGetWOInfoResB = new GetWOInfoResB();
        GetWOInfoResD objEGetWOInfoResD = new GetWOInfoResD();
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argGetWOInfoReqBD00.getWoRd());
        if (objEWoInfo != null) {
            objEGetWOInfoResD.setWoRd(objEWoInfo.getRuid());
            objEGetWOInfoResD.setWoCode(objEWoInfo.getWoCode());
            objEGetWOInfoResD.setNum(objEWoInfo.getNum());
            objEGetWOInfoResD.setOrderCode(objEWoInfo.getOrderCode());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            objEGetWOInfoResD.setJStartDate(format.format(objEWoInfo.getjStartDate()));
            objEGetWOInfoResD.setJFinishDate(format.format(objEWoInfo.getjFinishDate()));
            objEGetWOInfoResD.setCreator(objEWoInfo.getCreator());
            objEGetWOInfoResD.setCreateTime(DateUtil.format(objEWoInfo.getCreateTime()));
            objEGetWOInfoResD.setLastModifyMan(objEWoInfo.getLastModifyMan());
            objEGetWOInfoResD.setLastModifyTime(DateUtil.format(objEWoInfo.getLastModifyTime()));
            objEGetWOInfoResD.setRemark(objEWoInfo.getRemark());
            objEGetWOInfoResD.setFinishNum(objEWoInfo.getFinishNum());
            objEGetWOInfoResD.setUnCBatNum(objEWoInfo.getUnCBatNum());
            objEGetWOInfoResD.setStatus(objEWoInfo.getStatus());
            objEGetWOInfoResD.setDSource(objEWoInfo.getdSource());
            //物料版本
            GetWOInfoResBDMa objEGetWOInfoResBDMa = new GetWOInfoResBDMa();

            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEWoInfo.getMaVerGd());
            if (objEMaVerInfo != null) {
                MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                if (objEMaterialInfo != null) {
                    objEGetWOInfoResBDMa.setMaVerRd(objEMaVerInfo.getRuid());
                    objEGetWOInfoResBDMa.setMaCode(objEMaterialInfo.getMaterialCode());
                    objEGetWOInfoResBDMa.setMaName(objEMaterialInfo.getMaterialName());
                    objEGetWOInfoResBDMa.setMaDes(objEMaterialInfo.getMaterialDes());
                }
            }
            objEGetWOInfoResD.setMaInfo(objEGetWOInfoResBDMa);

            //暂般
            PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByGuid(objEWoInfo.getTrayPackGd());
            GetWOInfoResBDTrayPack objEGetWOInfoResBDTrayPack = new GetWOInfoResBDTrayPack();
            if (objEPackSpecificationInfo != null) {
                objEGetWOInfoResBDTrayPack.setPackSpRd(objEPackSpecificationInfo.getRuid());
                objEGetWOInfoResBDTrayPack.setPackName(objEPackSpecificationInfo.getPackName());
            }

            //箱包
            PackSpecificationInfo objEPackSpecificationInfo1 = packSpecificationDAO.SelectPackSpecificationByGuid(objEWoInfo.getBoxPackGd());
            GetWOInfoResBDBoxPack objEGetWOInfoResBDBoxPack = new GetWOInfoResBDBoxPack();
            if (objEPackSpecificationInfo1 != null) {
                objEGetWOInfoResBDBoxPack.setPackSpRd(objEPackSpecificationInfo1.getRuid());
                objEGetWOInfoResBDBoxPack.setPackName(objEPackSpecificationInfo1.getPackName());
            }
            //工单类型
            GetWOInfoResBDWOT objEGetWOInfoResBDWOT = new GetWOInfoResBDWOT();
            WoTypeInfo objEWoTypeInfo = woTypeDAO.SelectWoTypeByGuid(objEWoInfo.getWoTGd());
            if (objEWoTypeInfo != null) {
                objEGetWOInfoResBDWOT.setWTRd(objEWoTypeInfo.getRuid());
                objEGetWOInfoResBDWOT.setWTName(objEWoTypeInfo.getWoTName());
            }
            //线体   乔帅阳
  /*          List<GetWOInfoResBDOrderLine> objGetDevInfoResDLine = new ArrayList<>();
            List<OrderLineinfo> OrderList = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
            if(OrderList!=null&&OrderList.size()>0){
                for(OrderLineinfo obj:OrderList){
                    GetWOInfoResBDOrderLine orderLineinfo = new GetWOInfoResBDOrderLine();
                    Lineinfo lineinfo=lineDao.SelectLineInfoByguid(obj.getLineGd());
                    if(lineinfo!=null){
                        orderLineinfo.setLineRd(lineinfo.getRuid());
                        orderLineinfo.setLineName(lineinfo.getLineName());
                    }
                    objGetDevInfoResDLine.add(orderLineinfo);
                }
            }*/
            List<GetWOInfoResBDOrderLine> objGetWOInfoResBDOrderLines = new ArrayList<GetWOInfoResBDOrderLine>();
            if (objEWoInfo.getGuid() != null) {
                List<OrderLineinfo> orderLineinfos = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
                if (orderLineinfos != null && orderLineinfos.size() > 0) {
                    for (OrderLineinfo orderLineinfo : orderLineinfos) {
                        GetWOInfoResBDOrderLine orderLineinfo1 = new GetWOInfoResBDOrderLine();
                        Lineinfo Lineinfo = lineDao.SelectLineInfoByguid(orderLineinfo.getLineGd());
                        if (Lineinfo != null) {
                            orderLineinfo1.setLineRd(Lineinfo.getRuid());
                            orderLineinfo1.setLineName(Lineinfo.getLineName());
                        }
                        objGetWOInfoResBDOrderLines.add(orderLineinfo1);
                    }
                }
            }
            //单位
            GetWOInfoResBDUnit objEGetWOInfoResBDUnit = new GetWOInfoResBDUnit();
            UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEWoInfo.getUnitGd());
            if (objEUnitInfo != null) {
                objEGetWOInfoResBDUnit.setUnitRd(objEUnitInfo.getRuid());
                objEGetWOInfoResBDUnit.setUnitName(objEUnitInfo.getUnitName());
            }

            //todo 添加工序操作
            WFVerInfo wfVerInfo = wfVerDAO.SelectByGuid(objEWoInfo.getwFVerGd());
            if (wfVerInfo != null) {
                GetCMWInfoResDWFInfo objEGetCMWInfoResDWFInfo = new GetCMWInfoResDWFInfo();
                List<GetCMWInfoResDWSpecInfo> objEGetCMWInfoResDWSpecInfos = new ArrayList<>();
                GetCMWInfoResDWSpecInfo objEGetCMWInfoResDWSpecInfo = null;
                SpecVerInfo specVerInfo = null;

                objEGetCMWInfoResDWFInfo.setWFVerRd(wfVerInfo.getRuid());
                objEGetCMWInfoResDWFInfo.setWFName(wfVerInfo.getwFName());
                //查询该流程下面的工序
                List<WFSpecInfo> wfSpecInfos = wfSpecDAO.SelectByWFVerGd(wfVerInfo.getGuid());
                // 遍历工艺工序的工序ID，查询所有的工序版本信息
                for (WFSpecInfo wfSpecInfo : wfSpecInfos) {
                    specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                    if (specVerInfo != null) {
                        objEGetCMWInfoResDWSpecInfo = new GetCMWInfoResDWSpecInfo();
                        objEGetCMWInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                        objEGetCMWInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                        objEGetCMWInfoResDWSpecInfo.setSpecStatus(specVerInfo.getStatus());
                        objEGetCMWInfoResDWSpecInfo.setDevGInfo(readDev(specVerInfo.getDevGrGd()));

                        if (wfVerInfo.getsSpecVerGd().equals(specVerInfo.getGuid())) {
                            objEGetCMWInfoResDWSpecInfos.add(0, objEGetCMWInfoResDWSpecInfo);
                        } else {
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
                objEGetWOInfoResD.setWFInfo(objEGetCMWInfoResDWFInfo);
            }


            //紧急度
            GetWOInfoResBDUrcy objEGetWOInfoResBDUrcy = new GetWOInfoResBDUrcy();
            UrgencyInfo objEUrgencyInfo = urgencyDAO.SelectByguid(objEWoInfo.getUrcyGd());
            if (objEUrgencyInfo != null) {
                objEGetWOInfoResBDUrcy.setUrcyRd(objEUrgencyInfo.getRuid());
                objEGetWOInfoResBDUrcy.setUrcyDes(objEUrgencyInfo.getUrcyDes());
            }
            objEGetWOInfoResD.setUrcyInfo(objEGetWOInfoResBDUrcy);
            objEGetWOInfoResD.setWOTInfo(objEGetWOInfoResBDWOT);
            objEGetWOInfoResD.setBoxPackInfo(objEGetWOInfoResBDBoxPack);
            objEGetWOInfoResD.setTrayPackInfo(objEGetWOInfoResBDTrayPack);
            objEGetWOInfoResD.setUnitInfo(objEGetWOInfoResBDUnit);
            /* objEGetWOInfoResD.setOrderLineInfo(objGetDevInfoResDLine);*/
            objEGetWOInfoResD.setLineInfo(objGetWOInfoResBDOrderLines);
        }
        objEGetWOInfoResB.setData(objEGetWOInfoResD);
        objEGetWOInfoRes.setBody(objEGetWOInfoResB);
        return objEGetWOInfoRes;
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
                   /* //查询设备状态
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

    //dto新增工单信息
    @Override
    public SaveWOInfoRes AddSaveWOInfoRes(SaveWOInfoReqBD00 argSaveWOInfoReqBD00) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();
        SaveWOInfoResB objESaveWOInfoResB = new SaveWOInfoResB();
        SaveWOInfoResD objESaveWOInfoResD = new SaveWOInfoResD();

        WoInfo objEWoInfo = new WoInfo();
        objEWoInfo.setGuid(CommonUtils.getRandomNumber());

        //查询工单信息--LFZ
        List<WoInfo> objeWoInfo = wODAO.SelectWoInfo();

        //逻辑校验保存的工单号不能相同--LFZ
        for (WoInfo obj : objeWoInfo) {
            if (!"".equals(argSaveWOInfoReqBD00.getWoCode()) && obj.getWoCode().equals(argSaveWOInfoReqBD00.getWoCode())) {
                throw new SystemException("MG0006F", "工单号已存在");
            }
        }

        //查询代码生成
        CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("03");

        String SCode = "";

        if (!"".equals(argSaveWOInfoReqBD00.getWoCode())) {
            objEWoInfo.setWoCode(argSaveWOInfoReqBD00.getWoCode());
        } else if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
            SCode = gConfigIService.GetCreateSC(codeRuleInfo);
            objEWoInfo.setWoCode(SCode);
        } else {
            throw new SystemException("", "请输入{工单号}，或请到全局配置进行代码生成配置");
        }


        MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argSaveWOInfoReqBD00.getMaVerRd());
        if (objEMaVerInfo == null) {
            throw new SystemException("xxx", "物料版本信息不存在");


        }
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if (objEUnitInfo == null) {
            throw new SystemException("xxx", "单位信息不存在");
        }
        objEWoInfo.setUnitGd(objEUnitInfo.getGuid());

        /*if(null == objEMaVerInfo.getBomVerGd() || "".equals(objEMaVerInfo.getBomVerGd())){
            throw new SystemException("xxx","该物料下面的BOM不能为空");
        }*/

        //工单类型
        WoTypeInfo objEWoTypeInfo = woTypeDAO.SelectWoTypeByRuid(argSaveWOInfoReqBD00.getWTRd());
        if (objEWoTypeInfo == null) {
            throw new SystemException("xxx", "工单类型不能为空");
        }
        objEWoInfo.setWoTGd(objEWoTypeInfo.getGuid());
        //紧急度
        UrgencyInfo objUrgencyInfo = urgencyDAO.SelectByRuid(argSaveWOInfoReqBD00.getUrcyRd());
        if (objUrgencyInfo == null) {
            throw new SystemException("xxx", "紧急度不能为空");
        }
        objEWoInfo.setUrcyGd(objUrgencyInfo.getGuid());

        //暂般
        PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argSaveWOInfoReqBD00.getTrayPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEWoInfo.setTrayPackGd(objEPackSpecificationInfo.getGuid());
        } else {
            objEWoInfo.setTrayPackGd("");
        }

        //工艺流程
        WFInfo wfInfo=wfdao.SelectWFInfo(argSaveWOInfoReqBD00.getWfVerRd());
        if(wfInfo!=null){
           List<WFVerInfo>  wfVerInfo = wfVerDAO.SelectByWFGd(wfInfo.getGuid());
            if (wfVerInfo.size()<0||  wfVerInfo == null) {
                throw new SystemException("", "工艺流程信息不能为空");
            }
            objEWoInfo.setwFVerGd(wfVerInfo.get(0).getGuid());
        }


        //BOM
        /*BomInfo objEBomInfo = bomDAO.SelectByGuid(objEMaVerInfo.getBomGd());
        if(objEBomInfo != null) {
            objEWoInfo.setBomVerGd(objEBomInfo.getVerGd());
        }*/

        //箱包
        PackSpecificationInfo objEPackSpecificationInfo1 = packSpecificationDAO.SelectPackSpecificationByRuid(argSaveWOInfoReqBD00.getBoxPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEWoInfo.setBoxPackGd(objEPackSpecificationInfo1.getGuid());
        } else {
            objEWoInfo.setBoxPackGd("");
        }

        //线体
        if (argSaveWOInfoReqBD00.getLineInfo().size() <= 0) {
            throw new SystemException("xxx", "线体不能为空");
        }
        for (SaveOrderLineInfoReq00OrderList saveOrderLineInfoReq00OrderList : argSaveWOInfoReqBD00.getLineInfo()) {
            OrderLineinfo orderLineinfo = new OrderLineinfo();
            Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(saveOrderLineInfoReq00OrderList.getLineRd());
            if (lineinfo != null) {
                orderLineinfo.setLineGd(lineinfo.getGuid());
                orderLineinfo.setWoGd(objEWoInfo.getGuid());
                orderLineDAO.InsertOrderLineinfo(orderLineinfo);
            }
        }


        objEWoInfo.setMaVerGd(objEMaVerInfo.getGuid());
        objEWoInfo.setOrderCode(argSaveWOInfoReqBD00.getOrderCode());
        objEWoInfo.setNum(argSaveWOInfoReqBD00.getNum());
        objEWoInfo.setFinishNum(0.00f);
        objEWoInfo.setUnCBatNum(argSaveWOInfoReqBD00.getNum());


        //字符串转日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(argSaveWOInfoReqBD00.getJStartDate());
            objEWoInfo.setjStartDate(date);
            date = format.parse(argSaveWOInfoReqBD00.getJFinishDate());
            objEWoInfo.setjFinishDate(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        objEWoInfo.setStatus("00");
        objEWoInfo.setdSource("01");
        objEWoInfo.setRemark(argSaveWOInfoReqBD00.getRemark());
        objEWoInfo.setCreateTime(new Date());
        objEWoInfo.setCreator(CommonUtils.readUser().getRealName());
        objEWoInfo.setpEndFlag("00");
        objEWoInfo.setpKEFlag("00");


        if (wODAO.InsertWoInfo(objEWoInfo) <= 0) {
            throw new SystemException("xxx", "新增工单信息失败");
        }
        objEWoInfo = wODAO.SelectWoInfoByGuid(objEWoInfo.getGuid());
        objESaveWOInfoResD.setWoRd(objEWoInfo.getRuid());
        objESaveWOInfoResD.setWoCode(objEWoInfo.getWoCode());
        objESaveWOInfoResB.setData(objESaveWOInfoResD);
        objESaveWOInfoRes.setBody(objESaveWOInfoResB);
        return objESaveWOInfoRes;
    }

    //dto删除工单信息
    @Override
    public SaveWOInfoRes RmSaveWOInfoRes(SaveWOInfoReqBD01 argSaveWOInfoReqBD01) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();
        SaveWOInfoResB objESaveWOInfoResB = new SaveWOInfoResB();
        SaveWOInfoResD objESaveWOInfoResD = new SaveWOInfoResD();

        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argSaveWOInfoReqBD01.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "工单不存在!");
        }

        if ("00".equals(objEWoInfo.getdSource())) {
            throw new SystemException("", "外部数据不能删除!");
        }

        if (!"00".equals(objEWoInfo.getStatus()) && !"02".equals(objEWoInfo.getStatus())) {
            throw new SystemException("", objEWoInfo.getWoCode() + "工单状态不是创建或取消!");
        }

        if ("00".equals(objEWoInfo.getStatus())) {


            List<OrderLineinfo> orderLineinfos = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
            if (orderLineinfos != null && orderLineinfos.size() > 0) {
                for (OrderLineinfo orderLineinfo : orderLineinfos) {
                    if (orderLineDAO.deleteOrderLineinfoByruid(orderLineinfo.getRuid()) <= 0) {
                        throw new SystemException("xxx", "生产生产线别失败");
                    }
                }
            }
            if (wODAO.DeleteWoInfo(argSaveWOInfoReqBD01.getWoRd()) <= 0) {
                throw new SystemException("xxx", "删除工单信息失败");
            }

        } else if ("02".equals(objEWoInfo.getStatus())) {
            if (objEWoInfo.getwIPNum() != 0) {
                throw new SystemException("", "在制品数量不为零,不允许删除");
            }
            if (wODAO.DeleteWoInfo(argSaveWOInfoReqBD01.getWoRd()) <= 0) {
                throw new SystemException("xxx", "删除工单信息失败");
            }
        }


        objESaveWOInfoResB.setData(objESaveWOInfoResD);
        objESaveWOInfoRes.setBody(objESaveWOInfoResB);
        return objESaveWOInfoRes;
    }

    //dto更新工单信息
    @Override
    public SaveWOInfoRes ModSaveWOInfoRes(SaveWOInfoReqBD02 argSaveWOInfoReqBD02) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();
        SaveWOInfoResB objESaveWOInfoResB = new SaveWOInfoResB();
        SaveWOInfoResD objESaveWOInfoResD = new SaveWOInfoResD();
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argSaveWOInfoReqBD02.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("xxx", "查询工单信息失败");
        }

        if ("00".equals(objEWoInfo.getdSource())) {
            throw new SystemException("", "外部数据不能更新!");
        }

        if (!"00".equals(objEWoInfo.getStatus()) && !"02".equals(objEWoInfo.getStatus())) {
            throw new SystemException("", objEWoInfo.getWoCode() + "工单状态不是创建或取消!");
        }

        //紧急度
        UrgencyInfo objUrgencyInfo = urgencyDAO.SelectByRuid(argSaveWOInfoReqBD02.getUrcyRd());
        if (objUrgencyInfo == null) {
            throw new SystemException("xxx", "紧急度不能为空");
        }
        objEWoInfo.setUrcyGd(objUrgencyInfo.getGuid());

        //只有工单创建
        if ("00".equals(objEWoInfo.getStatus())) {
            //查询工单号--LFZ
            WoInfo objeWoInfo = wODAO.SelectWoInfoBywoCode(argSaveWOInfoReqBD02.getWoCode());

            if (objeWoInfo != null && !objeWoInfo.getWoCode().equals(objEWoInfo.getWoCode())) {
                throw new SystemException("MG0006F", "更新失败，工单号已存在");
            }

            //查询代码生成
            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("03");

            String SCode = "";

            if (!"".equals(argSaveWOInfoReqBD02.getWoCode())) {
                objEWoInfo.setWoCode(argSaveWOInfoReqBD02.getWoCode());
            } else if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                SCode = gConfigIService.GetCreateSC(codeRuleInfo);
                objEWoInfo.setWoCode(SCode);
            } else {
                throw new SystemException("", "请输入{工单号}，或请到全局配置进行代码生成配置");
            }

            MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(argSaveWOInfoReqBD02.getMaVerRd());
            if (objEMaVerInfo == null) {
                throw new SystemException("xxx", "物料版本信息不存在");
            }
            /*BomInfo objEBomInfo = bomDAO.SelectByGuid(objEMaVerInfo.getBomGd());
            if(objEBomInfo != null) {
                //BOM
                objEWoInfo.setBomVerGd(objEBomInfo.getVerGd());
            }*/
            objEWoInfo.setMaVerGd(objEMaVerInfo.getGuid());
            if(objEMaVerInfo.getUnitGd()!=null){
                UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
                if (objEUnitInfo == null) {
                    throw new SystemException("xxx", "单位信息不存在");
                }
                objEWoInfo.setUnitGd(objEUnitInfo.getGuid());
            }

        }

        //工单类型
        WoTypeInfo objEWoTypeInfo = woTypeDAO.SelectWoTypeByRuid(argSaveWOInfoReqBD02.getWTRd());
        if (objEWoTypeInfo == null) {
            throw new SystemException("xxx", "工单类型不能为空");
        }
        objEWoInfo.setWoTGd(objEWoTypeInfo.getGuid());

        WFInfo wfInfo=wfdao.SelectWFInfo(argSaveWOInfoReqBD02.getWfVerRd());
        if(wfInfo!=null){
           List<WFVerInfo> wfVerInfo = wfVerDAO.SelectByWFGd(wfInfo.getGuid());
            if (wfVerInfo.size()<0||wfInfo==null) {
                throw new SystemException("", "工艺流程信息不能为空");
            }
            objEWoInfo.setwFVerGd(wfVerInfo.get(0).getGuid());
        }
        //工艺流程



    /*    //乔帅阳   线别
        List<OrderLineinfo> orderLineinfoo=orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
        if(orderLineinfoo!=null&&orderLineinfoo.size()>0){
            for(OrderLineinfo ol:orderLineinfoo){
                if(orderLineDAO.deleteOrderLineinfoByruid(ol.getRuid())<=0){
                    throw new SystemException("xxx","删除关联信息表失败");
                }
            }
        }
        if(argSaveWOInfoReqBD02.getOrderLineList()!=null*//*||"".equals(argSaveWOInfoReqBD00.getOrderLineList())||argSaveWOInfoReqBD00.getOrderLineList().size()<=0*//*){
            for(SaveOrderLineInfoReq02OrderList obj:argSaveWOInfoReqBD02.getOrderLineList()){
                OrderLineinfo orderLineinfo = new  OrderLineinfo();
                orderLineinfo.setWoGd(objEWoInfo.getGuid());
                Lineinfo lineinfo=lineDao.SelectLineInfoBygruid(obj.getLineRd());
                if(lineinfo!=null){
                    orderLineinfo.setLineGd(lineinfo.getGuid());
                }
                orderLineDAO.InsertOrderLineinfo(orderLineinfo);
            }
        }*/
/*        //修改线体   乔帅阳
        if(argSaveWOInfoReqBD02.getLineRd()==0){
            throw new SystemException("xxx","线体不能为空");
        }
        OrderLineinfo orderLineinfo = new  OrderLineinfo();
        Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(argSaveWOInfoReqBD02.getLineRd());
        if(lineinfo!=null){
            orderLineinfo.setLineGd(lineinfo.getGuid()) ;
        }
        orderLineinfo.setWoGd(objEWoInfo.getGuid());
        orderLineDAO.UpdateOrderLineinfo(orderLineinfo);*/


        //修改线体   乔帅阳
        if (argSaveWOInfoReqBD02.getLineInfo().size() <= 0) {
            throw new SystemException("xxx", "线体不能为空");
        }
        List<OrderLineinfo> orderLineinfos = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
        if (orderLineinfos != null && orderLineinfos.size() > 0) {
            for (OrderLineinfo orderLineinfo : orderLineinfos) {
                if (orderLineDAO.deleteOrderLineinfoByruid(orderLineinfo.getRuid()) <= 0) {
                    throw new SystemException("xxx", "生产生产线别失败");
                }
            }
        }

        for (SaveOrderLineInfoReq02OrderList saveOrderLineInfoReq00OrderList : argSaveWOInfoReqBD02.getLineInfo()) {
            OrderLineinfo orderLineinfo = new OrderLineinfo();
            Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(saveOrderLineInfoReq00OrderList.getLineRd());
            if (lineinfo != null) {
                orderLineinfo.setLineGd(lineinfo.getGuid());
                orderLineinfo.setWoGd(objEWoInfo.getGuid());
                orderLineDAO.InsertOrderLineinfo(orderLineinfo);
            }
        }




        /*if(argSaveWOInfoReqBD02.getLineRd()==0){
            throw new SystemException("xxx","线体不能为空");
        }
        OrderLineinfo ol = orderLineDAO.SelectOLineByGuid(objEWoInfo.getGuid());
        if(ol!=null){
        if(orderLineDAO.deleteOrderLineinfoByruid(ol.getRuid())<=0){
            throw new SystemException("xxx","删除关联信息表失败");
          }
        }
        OrderLineinfo orderLineinfo = new  OrderLineinfo();
        Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(argSaveWOInfoReqBD02.getLineRd());
        if(lineinfo!=null){
            orderLineinfo.setLineGd(lineinfo.getGuid()) ;
        }
        orderLineinfo.setWoGd(objEWoInfo.getGuid());
        orderLineDAO.InsertOrderLineinfo(orderLineinfo);*/
        //暂般
        PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByRuid(argSaveWOInfoReqBD02.getTrayPSpRd());
        if (objEPackSpecificationInfo != null) {
            objEWoInfo.setTrayPackGd(objEPackSpecificationInfo.getGuid());
        } else {
            objEWoInfo.setTrayPackGd("");
        }
        //箱包
        PackSpecificationInfo objEPackSpecificationInfo1 = packSpecificationDAO.SelectPackSpecificationByRuid(argSaveWOInfoReqBD02.getBoxPSpRd());
        if (objEPackSpecificationInfo1 != null) {
            objEWoInfo.setBoxPackGd(objEPackSpecificationInfo1.getGuid());
        } else {
            objEWoInfo.setBoxPackGd("");
        }

        objEWoInfo.setOrderCode(argSaveWOInfoReqBD02.getOrderCode());
        //字符串转日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date date1 = null;
        try {
            date = format.parse(argSaveWOInfoReqBD02.getJStartDate());
            objEWoInfo.setjStartDate(date);
            date1 = format.parse(argSaveWOInfoReqBD02.getJFinishDate());
            objEWoInfo.setjFinishDate(date1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        objEWoInfo.setRemark(argSaveWOInfoReqBD02.getRemark());
        objEWoInfo.setLastModifyTime(new Date());
        objEWoInfo.setLastModifyMan(CommonUtils.readUser().getRealName());

        if (argSaveWOInfoReqBD02.getNum() > objEWoInfo.getNum()) {
            float newNum = argSaveWOInfoReqBD02.getNum() - objEWoInfo.getNum();//6
            objEWoInfo.setUnCBatNum(objEWoInfo.getUnCBatNum() + newNum);
            objEWoInfo.setNum(argSaveWOInfoReqBD02.getNum());
            if (wODAO.UpdateWoInfo(objEWoInfo) <= 0) {
                throw new SystemException("xxx", "更新工单信息失败");
            }
        } else {
            //获取从页面传过来的数量，得出已创建批次数量
            float value = argSaveWOInfoReqBD02.getNum() - (objEWoInfo.getNum() - objEWoInfo.getUnCBatNum());
            //得出本身未创建数量
            float unValue = objEWoInfo.getNum() - objEWoInfo.getUnCBatNum();
            //如果传过来的数量大于等于得出本身未创建数量
            if (argSaveWOInfoReqBD02.getNum() >= unValue) {
                //更新
                objEWoInfo.setUnCBatNum(value);
                objEWoInfo.setNum(argSaveWOInfoReqBD02.getNum());
                if (wODAO.UpdateWoInfo(objEWoInfo) <= 0) {
                    throw new SystemException("xxx", "更新工单信息失败");
                }
            }
            //抛出异常
            else {
                throw new SystemException("Xxx", "更新失败，数量不能低于" + unValue);
            }
        }

        objESaveWOInfoResB.setData(objESaveWOInfoResD);
        objESaveWOInfoRes.setBody(objESaveWOInfoResB);
        return objESaveWOInfoRes;
    }

    //dto根据批次id查询批次信息
    @Override
    public GetAllWOBInfoRes GetGetAllWOBInfoReqBD01(GetAllWOBInfoReqBD01 argGetAllWOBInfoReqBD01) {
        GetAllWOBInfoRes objEGetAllWOBInfoRes = new GetAllWOBInfoRes();
        GetAllWOBInfoResB objEGetAllWOBInfoResB = new GetAllWOBInfoResB();
        List<GetAllWOBInfoResD> objEGetAllWOBInfoResDs = new ArrayList<GetAllWOBInfoResD>();
        //根据批次id查批次信息
        BInfo objEBInfo = batchDAO.SelectBatchInfoByruid(argGetAllWOBInfoReqBD01.getBRd());
        if (objEBInfo == null) {
            throw new SystemException("xxx", "查询批次信息失败");
        }
        //无工单来源
        if ("00".equals(objEBInfo.getWoSource())) {
            throw new SystemException("xxx", "无来源工单，查询失败");
        }
        //有工单来源
        if ("01".equals(objEBInfo.getWoSource())) {
            List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEBInfo.getWoGd());
            //遍历所有的批次，获取工单号
            for (BInfo obj : objEBInfos) {
                GetAllWOBInfoResD objEGetAllWOBInfoResD = new GetAllWOBInfoResD();
                objEGetAllWOBInfoResD.setWoBRd(obj.getRuid());
                objEGetAllWOBInfoResD.setBatch(obj.getBatch());
                objEGetAllWOBInfoResD.setNum(obj.getNum());
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getGuid());
                if (objEMaVerInfo != null) {
                    objEGetAllWOBInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
                    objEGetAllWOBInfoResD.setMaName(objEMaVerInfo.getMaterialName() + objEMaVerInfo.getRuid());
                }//单位
                UnitInfo objEUnitInfo = unitDAO.SelectByGuid(obj.getUnitGd());
               /* if(objEUnitInfo==null){
                    throw new SystemException("xxx","查询单位信息失败");
                }*/
                objEGetAllWOBInfoResD.setUnitName(objEUnitInfo.getUnitName());
/*
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(obj.getSpecVerGd());
                objEGetAllWOBInfoResD.setSpecName(specVerInfo.getSpecName());*/

                if ("00".equals(obj.getStatus())) {
                    objEGetAllWOBInfoResD.setStatus("待处理");
                } else if ("01".equals(obj.getStatus())) {
                    objEGetAllWOBInfoResD.setStatus("正在处理");
                } else if ("02".equals(obj.getStatus())) {
                    objEGetAllWOBInfoResD.setStatus("完工");
                } else {
                    objEGetAllWOBInfoResD.setStatus("待处理");
                }
                objEGetAllWOBInfoResD.setJFDate(DateUtil.format(obj.getjFinishDate()));
                objEGetAllWOBInfoResD.setSFDate(DateUtil.format(obj.getsFinishDate()));
                objEGetAllWOBInfoResDs.add(objEGetAllWOBInfoResD);
            }
        }
        /*
            //根据工单号，查询所属工单信息
           WoInfo objEWoInfo=wODAO.SelectWoInfoByGuid(obj.getWoGd());
          if (objEWoInfo==null){
              throw new SystemException("xxx","查询所属工单信息失败");
          }
          //判断是否属于该工单号
          if(obj.getWoGd().equals(objEWoInfo.getGuid())){
              //由于一个工单号，有多个信息，所有此时查询是其他的工单信息
              //  if (argGetAllWOBInfoReqBD01.getBRd()!=obj.getRuid()){

                }
       }*/
        objEGetAllWOBInfoResB.setData(objEGetAllWOBInfoResDs);
        objEGetAllWOBInfoRes.setBody(objEGetAllWOBInfoResB);
        return objEGetAllWOBInfoRes;
    }


    @Override
    public MBaseRes GetAllWoinfoByLineRd(GetAllWOBInfoReqBD03 request) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        if (request.getLineRd() == null)
            throw new SystemException("EEEE", "线体标识不能为空");
        List<WoInfo> responses = wODAO.SelectAllWoInfoByLineRd(request.getLineRd());
        baseResBody.setData(responses);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //下达
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD03 argBD03) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD03.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        switch (objEWoInfo.getStatus()) {
            case "01":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为下达");
            case "03":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为处理中");
            case "04":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为冻结");
            case "05":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为终止");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEWoInfo.getMaVerGd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", "工单所对应的物料不存在");
        }
        if (null == objEWoInfo.getBomVerGd() || "".equals(objEWoInfo.getBomVerGd())) {
            BomInfo objEBomInfo = bomDAO.SelectByGuid(objEMaVerInfo.getBomGd());
            if (objEBomInfo == null) {
                throw new SystemException("", "工单所生产的物料BOM不存在!");
            }
            if (bomVerDAO.SelectBomVerInfo(objEBomInfo.getVerGd()) == null) {
                throw new SystemException("", "工单所生产的物料BOM不存在!");
            }

            objEWoInfo.setBomVerGd(objEBomInfo.getVerGd());
        }
        if (null == objEWoInfo.getwFVerGd() || "".equals(objEWoInfo.getwFVerGd())) {
            WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEWoInfo.getwFVerGd());
            if (objEWFVerInfo == null) {
                throw new SystemException("", "工单所生产的物料工艺流程不存在!");
            }
            objEWoInfo.setwFVerGd(objEWFVerInfo.getGuid());
        }

        //如果工单状态是取消，则解冻该工单下面所有批次
        /*if ("02".equals(objEWoInfo.getStatus())) {
            //查询该工单下面所有批次
            List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
            if (objEBInfos != null) {
                for (BInfo bInfo : objEBInfos) {
                    if ("02".equals(bInfo.getStatus())) {
                        bInfo.setStatus("00");

                        if (batchDAO.UpdateBatchInfoByRuid(bInfo) <= 0) {
                            throw new SystemException("", bInfo.getBatch() + "批次解冻失败");
                        }
                    }
                }
            }
        }*/
        objEWoInfo.setStatus("01");
        objEWoInfo.setLastModifyMan(userName);
        objEWoInfo.setLastModifyTime(date);

        //以工单下达创建批次
        //判断该工单是否创过批次
       /* ModeCInfo objEModeCInfo = modeCDAO.SelectmodeName("M4");
        if (objEModeCInfo != null && "M4_00".equals(objEModeCInfo.getModeValue())) {
            List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
            if (objEBInfos == null || objEBInfos.size() <= 0) {
                objEWoInfo.setUnCBatNum(0f);
                wODAO.UpdateWoInfo(objEWoInfo);

              //  createBatch(objEWoInfo, argBD03, userName, date);
            } else {
                wODAO.UpdateWoInfo(objEWoInfo);
            }
        } else {
            wODAO.UpdateWoInfo(objEWoInfo);
        }*/
        wODAO.UpdateWoInfo(objEWoInfo);
        return objESaveWOInfoRes;
    }

    //取消
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD04 argBD04) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD04.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        switch (objEWoInfo.getStatus()) {
            case "00":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为创建");
            case "02":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为取消");
            case "03":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为处理中");
            case "04":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为冻结");
            case "05":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为终止");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        //查询该工单下面所有批次
        List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
        if (objEBInfos == null) {
            throw new SystemException("", "该工单下面没有批次信息");
        }
        for (BInfo bInfo : objEBInfos) {
            if ("01".equals(bInfo.getStatus())) {
                throw new SystemException("", bInfo.getBatch() + "批次还在处理中");
            } else if ("00".equals(bInfo.getStatus())) {
                bInfo.setStatus("02");

                if (batchDAO.UpdateBatchInfoByRuid(bInfo) <= 0) {
                    throw new SystemException("", bInfo.getBatch() + "批次冻结失败");
                }
            }
        }

        objEWoInfo.setStatus("02");
        wODAO.UpdateWoInfo(objEWoInfo);

        return objESaveWOInfoRes;
    }

    //冻结
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD05 argBD05) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD05.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        switch (objEWoInfo.getStatus()) {
            case "00":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为创建");
            case "01":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为下达");
            case "02":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为取消");
            case "04":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为冻结");
            case "05":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为终止");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        //查询该工单下面所有批次
        List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
        if (objEBInfos == null) {
            throw new SystemException("", "该工单下面没有批次信息");
        }
        for (BInfo bInfo : objEBInfos) {
            if ("01".equals(bInfo.getStatus())) {
                throw new SystemException("", bInfo.getBatch() + "批次还在处理中，请把该批次进行下机操作");
            } else if ("00".equals(bInfo.getStatus())) {
                bInfo.setStatus("02");

                if (batchDAO.UpdateBatchInfoByRuid(bInfo) <= 0) {
                    throw new SystemException("", bInfo.getBatch() + "批次冻结失败");
                }
            }
        }

        objEWoInfo.setStatus("04");
        wODAO.UpdateWoInfo(objEWoInfo);

        return objESaveWOInfoRes;
    }

    //解冻
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD06 argBD06) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD06.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        switch (objEWoInfo.getStatus()) {
            case "00":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为创建");
            case "01":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为下达");
            case "02":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为取消");
            case "03":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为处理中");
            case "05":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为终止");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        //查询该工单下面所有批次
        List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
        if (objEBInfos == null) {
            throw new SystemException("", "该工单下面没有批次信息");
        }
        for (BInfo bInfo : objEBInfos) {
            if ("02".equals(bInfo.getStatus())) {
                bInfo.setStatus("00");

                if (batchDAO.UpdateBatchInfoByRuid(bInfo) <= 0) {
                    throw new SystemException("", bInfo.getBatch() + "批次解冻失败");
                }
            }
        }

        objEWoInfo.setStatus("03");
        wODAO.UpdateWoInfo(objEWoInfo);

        return objESaveWOInfoRes;
    }

    //终止
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD07 argBD07) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD07.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        switch (objEWoInfo.getStatus()) {
            case "00":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为创建");
            case "01":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为下达");
            case "02":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为取消");
            case "04":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为冻结");
            case "05":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为终止");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        boolean flag = true; //判断工单下的批次是否全部冻结(true 全部冻结)

        //查询该工单下面所有批次
        List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
        if (objEBInfos == null) {
            throw new SystemException("", "该工单下面没有批次信息");
        }
        for (BInfo bInfo : objEBInfos) {
            if ("01".equals(bInfo.getStatus())) {
                throw new SystemException("", bInfo.getBatch() + "批次还在处理中，请把该批次进行下机操作");
            } else if ("00".equals(bInfo.getStatus())) {
                bInfo.setStatus("02");

                if (batchDAO.UpdateBatchInfoByRuid(bInfo) <= 0) {
                    throw new SystemException("", bInfo.getBatch() + "批次冻结失败");
                }
            }
        }

        objEWoInfo.setStatus("05");
        wODAO.UpdateWoInfo(objEWoInfo);

        return objESaveWOInfoRes;
    }

    //开始
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD08 argBD08) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD08.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        switch (objEWoInfo.getStatus()) {
            case "00":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为创建");
            case "01":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为下达");
            case "02":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为取消");
            case "03":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为处理中");
            case "04":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为冻结");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        //查询该工单下面所有批次,并解除已冻结批次
        List<BInfo> objEBInfos = batchDAO.SelectByWoGd(objEWoInfo.getGuid());
        if (objEBInfos == null) {
            throw new SystemException("", "该工单下面没有批次信息");
        }
        for (BInfo bInfo : objEBInfos) {
            if ("02".equals(bInfo.getStatus())) {
                bInfo.setStatus("00");

                if (batchDAO.UpdateBatchInfoByRuid(bInfo) <= 0) {
                    throw new SystemException("", bInfo.getBatch() + "批次解冻失败");
                }
            }
        }

        objEWoInfo.setStatus("03");
        wODAO.UpdateWoInfo(objEWoInfo);

        return objESaveWOInfoRes;
    }

    //关闭
    @Override
    public SaveWOInfoRes ModSaveWO(SaveWOInfoReqBD09 argBD09) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();

        //查询工单
        WoInfo objEWoInfo = wODAO.SelectWoInfoByruid(argBD09.getWoRd());
        if (objEWoInfo == null) {
            throw new SystemException("", "该工单不存在");
        }
        if (objEWoInfo.getwIPNum() != 0f) {
            throw new SystemException("", objEWoInfo.getWoCode() + "工单在制品已经入库完成");
        }

        switch (objEWoInfo.getStatus()) {
            case "00":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为创建");
            case "01":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为下达");
            case "02":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为取消");
            case "04":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为冻结");
            case "05":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为终止");
            case "06":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为完成");
            case "07":
                throw new SystemException("", objEWoInfo.getWoCode() + "工单状态为关闭");
            default:
                break;
        }

        //查询在线领用物料批次
        List<PickBatchInfo> objEPickBatchInfos = pickBatchDAO.SelectPBByAssCodeAndAssSource(objEWoInfo.getWoCode(), "00");
        if (objEPickBatchInfos != null && objEPickBatchInfos.size() > 0) {
            throw new SystemException("", objEWoInfo.getWoCode() + "工单领取物料还未处理完");
        }

        objEWoInfo.setStatus("07");
        wODAO.UpdateWoInfo(objEWoInfo);

        return objESaveWOInfoRes;
    }

    /**
     * 工单创建批次
     *
     * @param argWoInfo
     * @param userName
     * @param date
     */
    private void createBatch(WoInfo argWoInfo, SaveWOInfoReqBD03 argBD03, String userName, Date date) {
        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(argWoInfo.getMaVerGd());
        if (objEMaVerInfo == null) {
            throw new SystemException("", argWoInfo.getWoCode() + "工单的物料不存在");
        }
        //获取打印模板信息
        PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD03.getPrintTRd());
        if (objEPrintTInfo == null) {
            throw new SystemException("", "打印模板信息为空");
        }

        //获取打印机信息
        PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(argBD03.getPrintRd());
        if (objEPrinterInfo == null) {
            throw new SystemException("", "打印机信息为空");
        }

        //判断批次是否重复
        if (batchDAO.selectBatchInfoByBatch(argWoInfo.getWoCode()) != null) {
            throw new SystemException("", argWoInfo.getWoCode() + "批次已经存在");
        }

        //查询工艺版本信息
        WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(argWoInfo.getwFVerGd());
        if (objEWFVerInfo == null) {
            throw new SystemException("", objEMaVerInfo.getMaterialName() + "产品工艺流程不存在!");
        }

        BInfo objEBInfo = new BInfo();
        objEBInfo.setGuid(CommonUtils.getRandomNumber());
        objEBInfo.setWoSource("01");
        objEBInfo.setWoGd(argWoInfo.getGuid());
        objEBInfo.setbType("00");
        objEBInfo.setMaVerGd(objEMaVerInfo.getGuid());
        objEBInfo.setBatch(argWoInfo.getWoCode());
        objEBInfo.setNum(argWoInfo.getNum());
        objEBInfo.setCanNum(argWoInfo.getNum());
        objEBInfo.setConsuNum(0f);
        objEBInfo.setUnitGd(objEMaVerInfo.getUnitGd());
        objEBInfo.setwFVerGd(objEWFVerInfo.getGuid());
        objEBInfo.setSpecVerGd(objEWFVerInfo.getsSpecVerGd());
        objEBInfo.setPrintTGd(objEPrintTInfo.getGuid());
        objEBInfo.setbSource("00");
        objEBInfo.setjStartDate(argWoInfo.getjStartDate());
        objEBInfo.setjFinishDate(argWoInfo.getjFinishDate());
        objEBInfo.setBad("01");
        objEBInfo.setiQCStatus("00".equals(objEMaVerInfo.getIsExem()) ? "01" : "00");
        objEBInfo.setCkResult("03");
        objEBInfo.setStatus("00");
        objEBInfo.setInStockStatus("02");
        objEBInfo.setwFStatus("00");
        objEBInfo.setPackStatus("01");
        objEBInfo.setCreator(userName);
        objEBInfo.setCreateTime(date);
        batchDAO.InsertBatch(objEBInfo);

        //批次事物操作
        AffairInfo objEAffairInfo = new AffairInfo();
        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
        objEAffairInfo.setBatch(objEBInfo.getBatch());
        objEAffairInfo.setSpecVerGd("");
        objEAffairInfo.setSpecName("");
        objEAffairInfo.setOptType("14");
        objEAffairInfo.setCreator(userName);
        objEAffairInfo.setCreateTime(date);
        affairDAO.InsertAffairInfo(objEAffairInfo);

        //批次附加表
        BAttachInfo objEBAttachInfo = new BAttachInfo();
        objEBAttachInfo.setGuid(CommonUtils.getRandomNumber());
        objEBAttachInfo.setBatch(objEBInfo.getBatch());
        objEBAttachInfo.setCreator(userName);
        objEBAttachInfo.setCreateTime(date);
        bAttachDAO.InsertBAttachInfo(objEBAttachInfo);

        //将可能需要打印的对象写入map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("MaVerInfo", objEMaVerInfo);
        map.put("BInfo", objEBInfo);
        map.put("WoInfo", argWoInfo);

        PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
        objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
        objEPrintTaskInfo.setReCode(objEBInfo.getBatch());
        objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
        objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
        objEPrintTaskInfo.setBarCode(objEBInfo.getBatch());
        objEPrintTaskInfo.setOrderType("02");
        objEPrintTaskInfo.setBarType("02");
        objEPrintTaskInfo.setPrintCount(argBD03.getPrintCount());
        objEPrintTaskInfo.setPrintCopy(argBD03.getPrintCopy());
        objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
        objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
        objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
        objEPrintTaskInfo.setCreator(userName);
        objEPrintTaskInfo.setCreateTime(date);

//        objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));

//        hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, objEPrinterInfo.getPrinterSer(), argBD03.getIsPrint());
    }
}
