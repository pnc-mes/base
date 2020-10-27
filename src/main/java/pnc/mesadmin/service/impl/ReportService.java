package pnc.mesadmin.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dao.OnlineAfterVoDAO;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllKCRecInfo.GetAllKCRecInfoRes;
import pnc.mesadmin.dto.GetAllKCRecInfo.GetAllKCRecInfoResB;
import pnc.mesadmin.dto.GetAllKCRecInfo.GetAllKCRecInfoResD;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoRes;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoResB;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoResD;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoResD1;
import pnc.mesadmin.dto.GetAllProdInfo.GetAllProdInfoResB;
import pnc.mesadmin.dto.GetAllProdInfo.GetAllProdInfoResD;
import pnc.mesadmin.dto.GetAllProdInfo.GetAllProdInfoResDSpecPDInfo;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSWareInfoRes;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSWareInfoResB;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSWareInfoResD;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSum;
import pnc.mesadmin.dto.GetAllWIPInfo.GetAllWIPInfoResB;
import pnc.mesadmin.dto.GetAllWIPInfo.GetAllWIPInfoResD;
import pnc.mesadmin.dto.GetOTInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.entity.vo.OnlineAfterVo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ReportIService;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪实现层
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：WWW
 * 修改时间：
 */
@Transactional
@Service
public class ReportService implements ReportIService {

    @Resource
    private BDAO bDAO;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private DoMaDAO doMaDAO;

    @Resource
    private DoDCDAO doDCDAO;

    @Resource
    private DoRcDAO doRcDAO;

    @Resource
    private InstockDtlDAO instockDtlDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private WODAO woDAO;

    @Resource
    private WFVerDAO wfVerDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private ProdRepDAO prodRepDAO;

    @Resource
    private PickBatchDAO pickBatchDAO;

    @Resource
    private RkTkDAO rkTkDAO;

    @Resource
    private RkTkIDAO rkTkIDAO;

    @Resource
    private CkDtlDAO ckDtlDAO;

    @Resource
    private CkTkDAO ckTkDAO;

    @Resource
    private PurChDAO purChDAO;

    @Resource
    private NPickDAO nPickDAO;

    @Resource
    private NRetMaInfoDAO nRetMaInfoDAO;

    @Resource
    private InCDAO inCDAO;

    @Resource
    private RetMaInfoDAO retMaInfoDAO;

    @Resource
    private PickDAO pickDAO;

    @Resource
    private WFDAO wfdao;

    @Resource
    private LineDao lineDao;

    @Resource
    private DevExpectLogDAO devExpectLogDAO;

    @Resource
    private OnlineAfterVoDAO onlineAfterVoDAO;

    @Override
    public GetAllWIPInfoResB QALLWIPInfo(List<InitDataField> initDataFieldList, PageInfo pageInfo) {
        GetAllWIPInfoResB objGetAllWIPInfoResB = new GetAllWIPInfoResB();
        List<GetAllWIPInfoResD> objGetAllWIPInfoResDList = new ArrayList<GetAllWIPInfoResD>(Collections.<GetAllWIPInfoResD>emptyList());
        GetAllWIPInfoResD objGetAllWIPInfoResD = null;

        List<WoInfo> wipreportList = new ArrayList<WoInfo>(Collections.<WoInfo>emptyList());
        InitDataField objInitDataField = new InitDataField();
        objInitDataField.setFieldName("Status");
        objInitDataField.setFieldOpt("in (");
        objInitDataField.setFieldVal("03','04','05') and '1' = '1");
        initDataFieldList.add(objInitDataField);
        //1.先查询工单
        wipreportList = baseIService.QALLBaseInfo(WODAO.class, "SelectWoInfo", initDataFieldList, null);

        //2.根据工单里面的产品Gd查询相应的产品信息
        List<String> maVerGdList = new ArrayList<String>(Collections.<String>emptyList());
        for (WoInfo woInfo : wipreportList) {
            maVerGdList.add(woInfo.getMaVerGd());
        }

        List<MaVerInfo> materialInfoList = new ArrayList<MaVerInfo>(Collections.<MaVerInfo>emptyList());
        if (maVerGdList.size() > 0) {
            materialInfoList = maVerDAO.SelectMaVerListByGuidList(maVerGdList);
            for (WoInfo woInfo : wipreportList) {
                for (MaVerInfo maVerInfo : materialInfoList) {
                    if (maVerInfo.getGuid().equals(woInfo.getMaVerGd())) {
                        objGetAllWIPInfoResD = new GetAllWIPInfoResD();
                        objGetAllWIPInfoResD.setWoCode(woInfo.getWoCode());
                        objGetAllWIPInfoResD.setMaCode(maVerInfo.getMaterialCode());
                        objGetAllWIPInfoResD.setMaName(maVerInfo.getMaterialName());
                        objGetAllWIPInfoResD.setMaDes(materialDAO.SelectByGuid(maVerInfo.getMaGd()).getMaterialDes());
                        //根据WFVerGd查询工艺流程名称
                        //根据工单的WFGd查询，工艺流程名称
                        WFVerInfo wfVerInfo = wfVerDAO.SelectByGuid(woInfo.getwFVerGd());
                        if (wfVerInfo != null) {
                            objGetAllWIPInfoResD.setWFName(wfVerInfo.getwFName());
                        } else {
                            objGetAllWIPInfoResD.setWFName("");
                        }
                        objGetAllWIPInfoResD.setNum(woInfo.getNum());
                        objGetAllWIPInfoResD.setUnTNum(woInfo.getNum() - woInfo.getFinishNum() - woInfo.getwIPNum());
                        objGetAllWIPInfoResD.setWIPNum(woInfo.getwIPNum());
                        objGetAllWIPInfoResD.setFinishNum(woInfo.getFinishNum());
                        objGetAllWIPInfoResD.setJStartDate(DateUtil.format(woInfo.getjStartDate()));
                        objGetAllWIPInfoResD.setReleaseDate(DateUtil.format(woInfo.getReleaseDate()));
                        objGetAllWIPInfoResD.setSStartDate(DateUtil.format(woInfo.getsStartDate()));
                        objGetAllWIPInfoResDList.add(objGetAllWIPInfoResD);
                        break;
                    }
                }
            }
        }
        objGetAllWIPInfoResB.setData(objGetAllWIPInfoResDList);
        objGetAllWIPInfoResB.setMsgCode("0x00000");
        objGetAllWIPInfoResB.setMsgDes("成功");
        return objGetAllWIPInfoResB;
    }

    @Override
    public GetAllProdInfoResB QALLProdRepInfo(List<InitDataField> initDataFieldList, PageInfo pageInfo) {
        GetAllProdInfoResB objGetAllProdInfoResB = new GetAllProdInfoResB();
        List<GetAllProdInfoResD> objGetAllProdInfoResDList = new ArrayList<GetAllProdInfoResD>(Collections.<GetAllProdInfoResD>emptyList());
        GetAllProdInfoResD objGetAllProdInfoResD = null;
        List<GetAllProdInfoResDSpecPDInfo> objGetAllProdInfoResDCenterPDInfoList = new ArrayList<GetAllProdInfoResDSpecPDInfo>(Collections.<GetAllProdInfoResDSpecPDInfo>emptyList());
        GetAllProdInfoResDSpecPDInfo objGetAllProdInfoResDCenterPDInfo = null;

        //查询所有的工序
        Set<String> specList = new HashSet<String>(Collections.<String>emptyList());
        List<ProdRepInfo> prodRepInfoList = new ArrayList<ProdRepInfo>(Collections.<ProdRepInfo>emptyList());
        prodRepInfoList = baseIService.QALLBaseInfo(ProdRepDAO.class, "SelectProdRepInfo", initDataFieldList, null);
        if (prodRepInfoList.size() > 0) {
            for (ProdRepInfo prodRepInfo : prodRepInfoList) {
                specList.add(DateUtil.format(prodRepInfo.getProdDate()));
            }

            for (String prodDate : specList) {
                objGetAllProdInfoResDCenterPDInfoList = new ArrayList<GetAllProdInfoResDSpecPDInfo>(Collections.<GetAllProdInfoResDSpecPDInfo>emptyList());
                objGetAllProdInfoResD = new GetAllProdInfoResD();
                objGetAllProdInfoResD.setProdDate(prodDate);
                for (ProdRepInfo prodRepInfo : prodRepInfoList) {
                    objGetAllProdInfoResDCenterPDInfo = new GetAllProdInfoResDSpecPDInfo();
                    objGetAllProdInfoResDCenterPDInfo.setSpecName(prodRepInfo.getSpecName());
                    objGetAllProdInfoResDCenterPDInfo.setMINum(prodRepInfo.getMiNum());
                    objGetAllProdInfoResDCenterPDInfo.setMONum(prodRepInfo.getMoNum());
                    objGetAllProdInfoResDCenterPDInfo.setWHours(prodRepInfo.getwHours());
                    objGetAllProdInfoResDCenterPDInfo.setYield(prodRepInfo.getYield());
                    if (prodDate.equals(DateUtil.format(prodRepInfo.getProdDate()))) {
                        objGetAllProdInfoResDCenterPDInfoList.add(objGetAllProdInfoResDCenterPDInfo);
                    }
                }
                objGetAllProdInfoResD.setSpecPDInfo(objGetAllProdInfoResDCenterPDInfoList);
                objGetAllProdInfoResDList.add(objGetAllProdInfoResD);
            }
            Collections.reverse(objGetAllProdInfoResDList);
            objGetAllProdInfoResB.setData(objGetAllProdInfoResDList);

        } else {

            objGetAllProdInfoResB.setData(new ArrayList<GetAllProdInfoResD>(Collections.<GetAllProdInfoResD>emptyList()));
        }
        objGetAllProdInfoResB.setMsgCode("0x00000");
        objGetAllProdInfoResB.setMsgDes("成功");

        return objGetAllProdInfoResB;
    }

    public GetOTInfoRes GetGetOTInfoRes(GetOTInfoReqBD00 argGetOTInfoReqBD00) {
        GetOTInfoRes objEGetGetOTInfoRes = new GetOTInfoRes();
        GetOTInfoResB objEGetOTInfoResB = new GetOTInfoResB();
        GetOTInfoResD objEGetOTInfoResD = new GetOTInfoResD();
        BInfo objEBInfo = bDAO.selectBatchInfoByBatch(argGetOTInfoReqBD00.getBatch());
        if (objEBInfo != null) {

        } else {
            throw new SystemException("xx", "该批次不存在");
        }

        GetOTInfoResBDBase objEGetOTInfoResBDBase = new GetOTInfoResBDBase();
        objEGetOTInfoResBDBase.setBatch(objEBInfo.getBatch());



        Lineinfo lineinfso=lineDao.SelectLineInfoByguid(objEBInfo.getLineGd());
        if(lineinfso!=null){
            objEGetOTInfoResBDBase.setLineName(lineinfso.getLineName());
        }else {
            objEGetOTInfoResBDBase.setLineName("");
        }

        objEGetOTInfoResBDBase.setCanNum(objEBInfo.getCanNum());
        WoInfo woInfo = woDAO.SelectWoInfoByGuid(objEBInfo.getWoGd());
        if (woInfo != null) {
            objEGetOTInfoResBDBase.setWoCode(woInfo.getWoCode());
            BomVerInfo bomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
            if (bomVerInfo != null) {
                objEGetOTInfoResBDBase.setBOMName(bomVerInfo.getBomName());
            }
        }
        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if (maVerInfo != null) {
            objEGetOTInfoResBDBase.setMaName(maVerInfo.getMaterialName());
            objEGetOTInfoResBDBase.setMaCode(maVerInfo.getMaterialCode());
            objEGetOTInfoResBDBase.setMaterialType(maVerInfo.getMaterialType());
        }

        WFVerInfo wfVerInfo = wfVerDAO.SelectByGuid(objEBInfo.getwFVerGd());
        if (wfVerInfo != null) {
            objEGetOTInfoResBDBase.setWFName(wfVerInfo.getwFName());
        }
        objEGetOTInfoResBDBase.setStatus(objEBInfo.getStatus());
        objEGetOTInfoResBDBase.setIsPack(objEBInfo.getPackStatus());
        objEGetOTInfoResD.setBaseInfo(objEGetOTInfoResBDBase);
        List<AffairInfo> objEAffairInfos = affairDAO.SelectAffairInfoBybatch(argGetOTInfoReqBD00.getBatch());

        //  int totalAffairInfo=objEAffairInfos.size();
        List<DoMaInfo> objEDoMaInfos = doMaDAO.SelectDoMaInfoBybatch(argGetOTInfoReqBD00.getBatch());

        int totalDoMaInfo = objEDoMaInfos.size();
        List<DoDCInfo> objEDoDCInfos = doDCDAO.SelectDoDCInfoDISTINCTBybatch(argGetOTInfoReqBD00.getBatch());

        List<GetOTInfoResBDDoExcp> getOTInfoResBDDoExcps=new ArrayList<GetOTInfoResBDDoExcp>();
        List<BCirExcpInfo> bCirExcpInfos=devExpectLogDAO.SelectAllBCirExcpInfo(argGetOTInfoReqBD00.getBatch());


        if(bCirExcpInfos!=null&&bCirExcpInfos.size()>0){
            for(BCirExcpInfo bCirExcpInfo:bCirExcpInfos){
                GetOTInfoResBDDoExcp getOTInfoResBDDoExcp=new GetOTInfoResBDDoExcp();
                getOTInfoResBDDoExcp.setLineName(bCirExcpInfo.getLineName());
                getOTInfoResBDDoExcp.setSpecName(bCirExcpInfo.getSpecName());
                getOTInfoResBDDoExcp.setStationName(bCirExcpInfo.getStationName());
                getOTInfoResBDDoExcp.setMsg(bCirExcpInfo.getMsg());
                getOTInfoResBDDoExcp.setExecor(bCirExcpInfo.getExecor());
                getOTInfoResBDDoExcp.setOptTime(DateUtil.format(bCirExcpInfo.getCreateTime()));
                getOTInfoResBDDoExcps.add(getOTInfoResBDDoExcp);
            }
        }
        objEGetOTInfoResD.setDoExcpInfo(getOTInfoResBDDoExcps);





        // int totalDoDCInfo=objEDoDCInfos.size();

        List<DoRcInfo> objEDoRcInfos = doRcDAO.SelectDoRcInfoBybatch(argGetOTInfoReqBD00.getBatch());
        //int totalDoRcInfo=objEDoRcInfos.size();

        List<GetOTInfoResBDAffair> objEGetOTInfoResBDAffairs = new ArrayList<GetOTInfoResBDAffair>();
        for (AffairInfo obj : objEAffairInfos) {
            GetOTInfoResBDAffair objEGetOTInfoResBDAffair = new GetOTInfoResBDAffair();
            if (obj.getSpecName() == null || "".equals(obj.getSpecName())) {
                objEGetOTInfoResBDAffair.setSpecName("");
            } else {
                objEGetOTInfoResBDAffair.setSpecName(obj.getSpecName());
            }
            String opttype = obj.getOptType();
            switch (opttype) {
                case "00":
                    objEGetOTInfoResBDAffair.setOptType("进站");
                    break;
                case "01":
                    objEGetOTInfoResBDAffair.setOptType("出站");
                    break;
                case "02":
                    objEGetOTInfoResBDAffair.setOptType("上机");
                    break;
                case "03":
                    objEGetOTInfoResBDAffair.setOptType("下机");
                    break;
                case "04":
                    objEGetOTInfoResBDAffair.setOptType("打开");
                    break;
                case "05":
                    objEGetOTInfoResBDAffair.setOptType("关闭");
                    break;
                case "06":
                    objEGetOTInfoResBDAffair.setOptType("非标准移动");
                    break;
                case "07":
                    objEGetOTInfoResBDAffair.setOptType("不良");
                    break;
                case "08":
                    objEGetOTInfoResBDAffair.setOptType("报废");
                    break;
                case "09":
                    objEGetOTInfoResBDAffair.setOptType("冻结");
                    break;
                case "10":
                    objEGetOTInfoResBDAffair.setOptType("解冻");
                    break;
                case "11":
                    objEGetOTInfoResBDAffair.setOptType("更改数量");
                    break;
                case "12":
                    objEGetOTInfoResBDAffair.setOptType("拆批");
                    break;
                case "13":
                    objEGetOTInfoResBDAffair.setOptType("合批");
                    break;
                case "14":
                    objEGetOTInfoResBDAffair.setOptType("批次创建");
                    break;
                case "15":
                    objEGetOTInfoResBDAffair.setOptType("批次IQC");
                    break;
                case "16":
                    objEGetOTInfoResBDAffair.setOptType("原材料入库");
                    break;
                case "17":
                    objEGetOTInfoResBDAffair.setOptType("领料出库");
                    break;
                case "18":
                    objEGetOTInfoResBDAffair.setOptType("生产退料");
                    break;
                case "19":
                    objEGetOTInfoResBDAffair.setOptType("在线重工");
                    break;
                case "20":
                    objEGetOTInfoResBDAffair.setOptType("产成品入库");
                    break;
                default:
                    break;
            }
            Lineinfo lineinfo=lineDao.SelectLineInfoByguid(obj.getLineGd());
            if(lineinfo!=null){
                objEGetOTInfoResBDAffair.setLineName(lineinfo.getLineName());
            }else{
                objEGetOTInfoResBDAffair.setLineName("");
            }

            objEGetOTInfoResBDAffair.setOptor(obj.getCreator());
            objEGetOTInfoResBDAffair.setOptTime(DateUtil.format(obj.getCreateTime()));
            objEGetOTInfoResBDAffairs.add(objEGetOTInfoResBDAffair);
        }

        objEGetOTInfoResD.setAffairInfo(objEGetOTInfoResBDAffairs);


        List<GetOTInfoResBDEndData> getOTInfoResBDEndData=new ArrayList<GetOTInfoResBDEndData>();
        OnlineAfterVo onlineAfterVo1=onlineAfterVoDAO.selectOnlineAfterVoYHCheckDAOByBatch(argGetOTInfoReqBD00.getBatch());
        if(onlineAfterVo1!=null){
            GetOTInfoResBDEndData getOTInfoResBDEndData11=new GetOTInfoResBDEndData();
            getOTInfoResBDEndData11.setStationName("压后目检");
            getOTInfoResBDEndData11.setBadName(onlineAfterVo1.getBadName());
            getOTInfoResBDEndData11.setBadCode(onlineAfterVo1.getBadCode());
            getOTInfoResBDEndData11.setCreateTime(DateUtil.format(onlineAfterVo1.getCreateTime()));
            getOTInfoResBDEndData11.setCreator(onlineAfterVo1.getCreator());
            getOTInfoResBDEndData11.setGradeName(onlineAfterVo1.getGradeName());
            getOTInfoResBDEndData.add(getOTInfoResBDEndData11);
        }

        OnlineAfterVo onlineAfterVo=onlineAfterVoDAO.selectOnlineAfterVoPELDAOByBatch(argGetOTInfoReqBD00.getBatch());
        if(onlineAfterVo!=null){
            GetOTInfoResBDEndData getOTInfoResBDEndData1=new GetOTInfoResBDEndData();
            getOTInfoResBDEndData1.setStationName("前EL");
            getOTInfoResBDEndData1.setBadName(onlineAfterVo.getBadName());
            getOTInfoResBDEndData1.setBadCode(onlineAfterVo.getBadCode());
            getOTInfoResBDEndData1.setCreateTime(DateUtil.format(onlineAfterVo.getCreateTime()));
            getOTInfoResBDEndData1.setCreator(onlineAfterVo.getCreator());
            getOTInfoResBDEndData1.setGradeName(onlineAfterVo.getGradeName());
            getOTInfoResBDEndData.add(getOTInfoResBDEndData1);
        }
        OnlineAfterVo onlineAfterVo2=onlineAfterVoDAO.selectOnlineAfterVoTELDAOByBatch(argGetOTInfoReqBD00.getBatch());
        if(onlineAfterVo2!=null){
            GetOTInfoResBDEndData getOTInfoResBDEndData2=new GetOTInfoResBDEndData();
            getOTInfoResBDEndData2.setStationName("后EL");
            getOTInfoResBDEndData2.setBadName(onlineAfterVo2.getBadName());
            getOTInfoResBDEndData2.setBadCode(onlineAfterVo2.getBadCode());
            getOTInfoResBDEndData2.setCreateTime(DateUtil.format(onlineAfterVo2.getCreateTime()));
            getOTInfoResBDEndData2.setCreator(onlineAfterVo2.getCreator());
            getOTInfoResBDEndData2.setGradeName(onlineAfterVo2.getGradeName());
            getOTInfoResBDEndData.add(getOTInfoResBDEndData2);
        }

        OnlineAfterVo onlineAfterVo3=onlineAfterVoDAO.selectOnlineAfterVoPELDAOByBatch(argGetOTInfoReqBD00.getBatch());
        if(onlineAfterVo3!=null){
            GetOTInfoResBDEndData getOTInfoResBDEndData3=new GetOTInfoResBDEndData();
            getOTInfoResBDEndData3.setStationName("终检");
            getOTInfoResBDEndData3.setBadName(onlineAfterVo3.getBadName());
            getOTInfoResBDEndData3.setBadCode(onlineAfterVo3.getBadCode());
            getOTInfoResBDEndData3.setCreateTime(DateUtil.format(onlineAfterVo3.getCreateTime()));
            getOTInfoResBDEndData3.setCreator(onlineAfterVo3.getCreator());
            getOTInfoResBDEndData3.setGradeName(onlineAfterVo3.getGradeName());
            getOTInfoResBDEndData.add(getOTInfoResBDEndData3);
        }

        objEGetOTInfoResD.setEndData(getOTInfoResBDEndData);

        List<GetOTInfoResBDDoMa> objEGetOTInfoResBDDoMas = new ArrayList<GetOTInfoResBDDoMa>();
        for (DoMaInfo obj1 : objEDoMaInfos) {
            GetOTInfoResBDDoMa objEGetOTInfoResBDDoMa = new GetOTInfoResBDDoMa();
            Lineinfo lineinfo2=lineDao.SelectLineInfoByguid(obj1.getLineGd());
            if(lineinfo2!=null){
                objEGetOTInfoResBDDoMa.setLineName(lineinfo2.getLineName());
            }else {
                objEGetOTInfoResBDDoMa.setLineName("");
            }

            if ("00".equals(obj1.getConSMode())){
                objEGetOTInfoResBDDoMa.setBatch("");
                objEGetOTInfoResBDDoMa.setConSMode("仅显示");
                objEGetOTInfoResBDDoMa.setSuppBatch("");
            }else if("01".equals(obj1.getConSMode())){
                objEGetOTInfoResBDDoMa.setBatch(obj1.getDoBatch());
                objEGetOTInfoResBDDoMa.setConSMode("批次");
                objEGetOTInfoResBDDoMa.setSuppBatch("");
            }else if("02".equals(obj1.getConSMode())){
                objEGetOTInfoResBDDoMa.setBatch(obj1.getF1());
                objEGetOTInfoResBDDoMa.setConSMode("序号");
                objEGetOTInfoResBDDoMa.setSuppBatch(obj1.getF1());
            }

            if (obj1.getSpecName() == null || "".equals(obj1.getSpecName())) {
                objEGetOTInfoResBDDoMa.setSpecName("");
            } else {
                objEGetOTInfoResBDDoMa.setSpecName(obj1.getSpecName());
            }

            if (obj1.getDevName() == null || "".equals(obj1.getDevName())) {
                objEGetOTInfoResBDDoMa.setDevName("");
            } else {
                objEGetOTInfoResBDDoMa.setDevName(obj1.getDevName());
            }
            objEGetOTInfoResBDDoMa.setMaName(obj1.getMaterialName());
            objEGetOTInfoResBDDoMa.setDoNum(obj1.getDoNum());

            objEGetOTInfoResBDDoMa.setOptor(obj1.getCreator());
            objEGetOTInfoResBDDoMa.setUnitName(obj1.getUnitName());
            objEGetOTInfoResBDDoMas.add(objEGetOTInfoResBDDoMa);
        }
        objEGetOTInfoResD.setDoMaInfo(objEGetOTInfoResBDDoMas);

        List<GetOTInfoResBDDoDC> objEGetOTInfoResBDDoDCs = new ArrayList<GetOTInfoResBDDoDC>();

        String DevName = "";
        String CreatOR = "";
        for (DoDCInfo obj2 : objEDoDCInfos) {
            GetOTInfoResBDDoDC objEGetOTInfoResBDDoDC = new GetOTInfoResBDDoDC();

            Lineinfo lineinfo3=lineDao.SelectLineInfoByguid(obj2.getLineGd());
            if(lineinfo3!=null){
                objEGetOTInfoResBDDoDC.setLineName(lineinfo3.getLineName());
            }else {
                objEGetOTInfoResBDDoDC.setLineName("");
            }

            if (obj2.getSpecName() == null || "".equals(obj2.getSpecName())) {
                objEGetOTInfoResBDDoDC.setSpecName("");
            } else {
                objEGetOTInfoResBDDoDC.setSpecName(obj2.getSpecName());
            }
            //其他值
            DoDCInfo onthervalue = doDCDAO.SelectDoDCInfoItemNameBybatchspecName(argGetOTInfoReqBD00.getBatch(), obj2.getSpecName());
            if (onthervalue != null) {
                DevName = onthervalue.getDevName();
                CreatOR = onthervalue.getCreator();
                if(DevName==null||"".equals(DevName)){
                    objEGetOTInfoResBDDoDC.setDevName("");
                }else {
                    objEGetOTInfoResBDDoDC.setDevName(DevName);
                }
                objEGetOTInfoResBDDoDC.setOptor(CreatOR);
            }

            List<DoDCInfo> objEDoDCInfosItemName = doDCDAO.SelectDoDCInfoItemNameBybatchCValue(argGetOTInfoReqBD00.getBatch(), obj2.getSpecName(),"00");
            String newName = "";
            for (DoDCInfo obj : objEDoDCInfosItemName) {
                newName += "" + obj.getItemName() +":"+ obj.getcValue()+" ";
            }
            objEGetOTInfoResBDDoDC.setDCContent(newName);

            List<DoDCInfo> objEDoDCInfosItemNames = doDCDAO.SelectDoDCInfoItemNameBybatchCValue(argGetOTInfoReqBD00.getBatch(), obj2.getSpecName(),"01");
            String afNewName = "";
            for (DoDCInfo obj : objEDoDCInfosItemNames) {
                afNewName += "" + obj.getItemName() +":"+ obj.getcValue()+" ";
            }
            objEGetOTInfoResBDDoDC.setAftDCContent(afNewName);

            objEGetOTInfoResBDDoDCs.add(objEGetOTInfoResBDDoDC);
        }

        List<GetOTInfoResBDDoRC> objEGetOTInfoResBDDoRCs = new ArrayList<GetOTInfoResBDDoRC>();
        String DevName1 = "";
        String CreatOR1 = "";
        for (DoRcInfo obj : objEDoRcInfos) {
            GetOTInfoResBDDoRC objEGetOTInfoResBDDoRC = new GetOTInfoResBDDoRC();
            if (obj.getSpecName() != null || !"".equals(obj.getSpecName())) {
                objEGetOTInfoResBDDoRC.setSpecName(obj.getSpecName());
            } else {
                objEGetOTInfoResBDDoRC.setSpecName("");
            }
            //其他值
            DoRcInfo onthervalue = doRcDAO.SelectDoRCInfoItemNameBybatchspecName(argGetOTInfoReqBD00.getBatch(), obj.getSpecName());
            if (onthervalue != null) {
                DevName1 = onthervalue.getDevName();
                CreatOR1 = onthervalue.getCreator();
                if(DevName1==null||"".equals(DevName1)){
                    objEGetOTInfoResBDDoRC.setDevName("");
                }else {
                    objEGetOTInfoResBDDoRC.setDevName(DevName1);
                }
                objEGetOTInfoResBDDoRC.setOptor(CreatOR1);
            }
            List<DoRcInfo> objEDoDCInfosItemName = doRcDAO.SelectDoRCInfoItemNameBybatchReaCode(argGetOTInfoReqBD00.getBatch(), obj.getSpecName());
            String newName1 = "";
            for (DoRcInfo obj5 : objEDoDCInfosItemName) {
                newName1 += obj5.getReaCode() + "、" + obj5.getReaDes();
            }

            objEGetOTInfoResBDDoRC.setRCContent(newName1);
            objEGetOTInfoResBDDoRCs.add(objEGetOTInfoResBDDoRC);
        }

        objEGetOTInfoResD.setDoRCInfo(objEGetOTInfoResBDDoRCs);
        objEGetOTInfoResD.setDoDCInfo(objEGetOTInfoResBDDoDCs);
        objEGetOTInfoResB.setData(objEGetOTInfoResD);
        objEGetGetOTInfoRes.setBody(objEGetOTInfoResB);
        return objEGetGetOTInfoRes;

    }

    //库存预警
    @Override
    public GetAllSWareInfoRes QALLGetAllSWareInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllSWareInfoRes objEGetAllSWareInfoRes = new GetAllSWareInfoRes();

        GetAllSWareInfoResB body = new GetAllSWareInfoResB();

        // 获取库存预警报表信息
        List<GetAllSWareInfoResD> dataList = new ArrayList<GetAllSWareInfoResD>();

        //查询库存明细所有信息
        List<InstockDtlInfo> objEInstockDtlInfo = baseIService.QALLBaseInfo(InstockDtlDAO.class, "SelectAllInstockDtlInfo", argInitDataFields, argPageInfo);

        Map<String, Float> map1 = new HashMap<String, Float>();

        if (objEInstockDtlInfo != null && objEInstockDtlInfo.size() > 0) {
            for (InstockDtlInfo obj : objEInstockDtlInfo) {

                //查询物料版本信息
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());

                if (objEMaVerInfo != null && objEMaVerInfo.getMaterialType().equals("01")) {
                    //查询批次信息
                    BInfo objEBInfo = bDAO.selectBatchInfoByBatch(obj.getBatch());
                    if (objEBInfo != null && !objEBInfo.getBad().equals("00")) {
                        if (map1.containsKey(obj.getMaVerGd())) {
                            float num = map1.get(obj.getMaVerGd());
                            map1.put(obj.getMaVerGd(), num + objEBInfo.getCanNum());
                        } else {
                            map1.put(obj.getMaVerGd(), objEBInfo.getCanNum());
                        }
                    }

                }
            }
            for (Map.Entry<String, Float> entry : map1.entrySet()) {
                GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();

                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(entry.getKey());

                if (objEMaVerInfo != null) {
                    //查询单位
                    UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());

                    if (objEMaVerInfo.getMinSNum() != 0.0 || objEMaVerInfo.getMaxSNum() != 0.0) {
                        if (entry.getValue() < objEMaVerInfo.getMinSNum() || entry.getValue() > objEMaVerInfo.getMaxSNum()) {
                            objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                            objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                            objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                            objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                            objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                            if (objEUnitInfo != null) {
                                objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                            } else {
                                objEGetAllSWareInfoResD.setUnitName("");
                            }
                            dataList.add(objEGetAllSWareInfoResD);
                        }
                    }
                }
            }
        }

        body.setData(dataList);
        objEGetAllSWareInfoRes.setBody(body);
        return objEGetAllSWareInfoRes;
    }

    //导出库存预警
    public ByteArrayOutputStream ExportSWareInfo(GetAllSum argGetAllSum, List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            // 获取库存预警报表信息
            List<GetAllSWareInfoResD> dataList = new ArrayList<GetAllSWareInfoResD>();

            //查询库存明细所有信息
            List<InstockDtlInfo> objEInstockDtlInfo = baseIService.QALLBaseInfo(InstockDtlDAO.class, "SelectAllInstockDtlInfo", argInitDataFields, argPageInfo);

            Map<String, Float> map1 = new HashMap<String, Float>();

            if (objEInstockDtlInfo != null && objEInstockDtlInfo.size() > 0) {
                for (InstockDtlInfo obj : objEInstockDtlInfo) {

                    //查询物料版本信息
                    MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());

                    if (objEMaVerInfo != null && objEMaVerInfo.getMaterialType().equals("01")) {
                        //查询批次信息
                        BInfo objEBInfo = bDAO.selectBatchInfoByBatch(obj.getBatch());
                        if (objEBInfo != null && !objEBInfo.getBad().equals("00")) {
                            if (map1.containsKey(obj.getMaVerGd())) {
                                float num = map1.get(obj.getMaVerGd());
                                map1.put(obj.getMaVerGd(), num + objEBInfo.getCanNum());
                            } else {
                                map1.put(obj.getMaVerGd(), objEBInfo.getCanNum());
                            }
                        }

                    }
                }
                for (Map.Entry<String, Float> entry : map1.entrySet()) {
                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();

                    MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(entry.getKey());

                    if (objEMaVerInfo != null) {
                        //查询单位
                        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());

                        if (objEMaVerInfo.getMinSNum() != 0.0 || objEMaVerInfo.getMaxSNum() != 0.0) {
                            if (entry.getValue() < objEMaVerInfo.getMinSNum() || entry.getValue() > objEMaVerInfo.getMaxSNum()) {
                                objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                if (objEUnitInfo != null) {
                                    objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                } else {
                                    objEGetAllSWareInfoResD.setUnitName("");
                                }
                                dataList.add(objEGetAllSWareInfoResD);
                            }
                        }
                    }
                }
            }

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"物料代码", "物料名称", "库存数量", "最低库存数", "最大库存数", "单位"}};

            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }


            /**********************************预警数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dataList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(dataList.get(i).getMaCode());
                    } else if (j == 1) {
                        cell3.setCellValue(dataList.get(i).getMaName());
                    } else if (j == 2) {
                        cell3.setCellValue(dataList.get(i).getStoreNum());
                    } else if (j == 3) {
                        cell3.setCellValue(dataList.get(i).getMinSNum());
                    } else if (j == 4) {
                        cell3.setCellValue(dataList.get(i).getMaxSNum());
                    } else if (j == 5) {
                        cell3.setCellValue(dataList.get(i).getUnitName());
                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
            }
        } finally {
            if (wb != null) try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        exportSWareInfoResB.setMsgCode("");
        exportSWareInfoResB.setMsgDes("成功!");

        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        exportSWareInfoRes.setBody(exportSWareInfoResB);

        return null;

    }

    //库存变动报表
    @Override
    public GetAllKCRecInfoRes QALLGetAllKCRecInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllKCRecInfoRes objEGetAllKCRecInfoRes = new GetAllKCRecInfoRes();

        GetAllKCRecInfoResB body = new GetAllKCRecInfoResB();

        List<GetAllKCRecInfoResD> dataList = new ArrayList<GetAllKCRecInfoResD>();

        //入库信息
        List<RkDtlInfo> objERkDtlInfo = baseIService.QALLBaseInfo(RkTkIDAO.class, "SelectAllRkDtlInfo", argInitDataFields, argPageInfo);

        if (objERkDtlInfo != null && objERkDtlInfo.size() > 0) {
            for (RkDtlInfo obj : objERkDtlInfo) {

                GetAllKCRecInfoResD objEGetAllKCRecInfoResD = new GetAllKCRecInfoResD();

                //查询入库任务表信息
                RkTkInfo objERkTkInfo = rkTkDAO.SelectRKTkInfoByrkCode(obj.getRkCode());
                if (objERkTkInfo != null) {
                    if ("00".equals(objERkTkInfo.getAssSource())) {
                        objEGetAllKCRecInfoResD.setOptType("原材料入库");
                        //查询采购单信息
                        PurChInfo objEPurChInfo = purChDAO.SelectPurChInfoByPurChCode(objERkTkInfo.getAssCode());
                        if (objEPurChInfo != null) {
                            objEGetAllKCRecInfoResD.setCreateor(objEPurChInfo.getCreator());
                            objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objEPurChInfo.getCreateTime()));
                        } else {
                            objEGetAllKCRecInfoResD.setCreateor("");
                            objEGetAllKCRecInfoResD.setCreateTime("");
                        }
                    }
                    if ("01".equals(objERkTkInfo.getAssSource())) {
                        objEGetAllKCRecInfoResD.setOptType("原材料入库");
                        //查询来料收货单信息
                        InCInfo objEInCInfo = inCDAO.SelectByInCCode(objERkTkInfo.getAssCode());
                        if (objEInCInfo != null) {
                            objEGetAllKCRecInfoResD.setCreateor(objEInCInfo.getCreator());
                            objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objEInCInfo.getCreateTime()));
                        } else {
                            objEGetAllKCRecInfoResD.setCreateor("");
                            objEGetAllKCRecInfoResD.setCreateTime("");
                        }
                    }
                    if ("02".equals(objERkTkInfo.getAssSource())) {
                        objEGetAllKCRecInfoResD.setOptType("退料入库");
                        //查询快速退料信息
                        RetMaInfo objERetMaInfo = retMaInfoDAO.SelectRetMaInfoByRetCode(objERkTkInfo.getAssCode());
                        if (objERetMaInfo != null) {
                            objEGetAllKCRecInfoResD.setCreateor(objERetMaInfo.getCreator());
                            objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objERetMaInfo.getCreateTime()));
                        } else {
                            objEGetAllKCRecInfoResD.setCreateor("");
                            objEGetAllKCRecInfoResD.setCreateTime("");
                        }
                    }
                    if ("04".equals(objERkTkInfo.getAssSource())) {
                        objEGetAllKCRecInfoResD.setOptType("退料入库");
                        //查询退料信息
                        NRetMaInfo objENRetMaInfo = nRetMaInfoDAO.SelectRetMaInfoByRetCode(objERkTkInfo.getAssCode());
                        if (objENRetMaInfo != null) {
                            objEGetAllKCRecInfoResD.setCreateor(objENRetMaInfo.getCreator());
                            objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objENRetMaInfo.getCreateTime()));
                        } else {
                            objEGetAllKCRecInfoResD.setCreateor("");
                            objEGetAllKCRecInfoResD.setCreateTime("");
                        }
                    }

                    objEGetAllKCRecInfoResD.setOrderCode(objERkTkInfo.getAssCode());
                }
                objEGetAllKCRecInfoResD.setMaCode(obj.getMaterialCode());
                objEGetAllKCRecInfoResD.setMaName(obj.getMaterialName());
                objEGetAllKCRecInfoResD.setBatch(obj.getBatch());
                objEGetAllKCRecInfoResD.setNum(obj.getNum());
                objEGetAllKCRecInfoResD.setUnitName(obj.getUnitName());
                objEGetAllKCRecInfoResD.setDealor(obj.getCreator());
                objEGetAllKCRecInfoResD.setDealTime(DateUtil.format(obj.getCreateTime()));

                dataList.add(objEGetAllKCRecInfoResD);
            }
        }

        //出库信息
        List<CkDtlInfo> objECkDtlInfo = baseIService.QALLBaseInfo(CkDtlDAO.class, "SelectAllCkDtlInfo", argInitDataFields, argPageInfo);

        if (objECkDtlInfo != null && objECkDtlInfo.size() > 0) {
            for (CkDtlInfo obj : objECkDtlInfo) {

                GetAllKCRecInfoResD objEGetAllKCRecInfoResD = new GetAllKCRecInfoResD();

                //查询入库任务表信息
                CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByCkCode(obj.getCkCode());
                if (objECkTkInfo != null) {
                    if ("00".equals(objECkTkInfo.getAssSource())) {
                        objEGetAllKCRecInfoResD.setOptType("领料出库");
                        //查询有工单领料单信息
                        PickInfo objEPickInfo = pickDAO.SelectPickInfoByPickCode(objECkTkInfo.getAssCode());
                        if (objEPickInfo != null) {
                            objEGetAllKCRecInfoResD.setCreateor(objEPickInfo.getCreator());
                            objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objEPickInfo.getCreateTime()));
                        } else {
                            objEGetAllKCRecInfoResD.setCreateor("");
                            objEGetAllKCRecInfoResD.setCreateTime("");
                        }
                    }

                    if ("01".equals(objECkTkInfo.getAssSource())) {
                        objEGetAllKCRecInfoResD.setOptType("领料出库");
                        //查询无工单领料单信息
                        NPickInfo objENPickInfo = nPickDAO.SelectNPickInfoByPickCode(objECkTkInfo.getAssCode());
                        if (objENPickInfo != null) {
                            objEGetAllKCRecInfoResD.setCreateor(objENPickInfo.getCreator());
                            objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objENPickInfo.getCreateTime()));
                        } else {
                            objEGetAllKCRecInfoResD.setCreateor("");
                            objEGetAllKCRecInfoResD.setCreateTime("");
                        }
                    }
                    objEGetAllKCRecInfoResD.setOrderCode(objECkTkInfo.getAssCode());
                }
                objEGetAllKCRecInfoResD.setMaCode(obj.getMaterialCode());
                objEGetAllKCRecInfoResD.setMaName(obj.getMaterialName());
                objEGetAllKCRecInfoResD.setBatch(obj.getBatch());
                objEGetAllKCRecInfoResD.setNum(obj.getNum());
                objEGetAllKCRecInfoResD.setUnitName(obj.getUnitName());
                objEGetAllKCRecInfoResD.setDealor(obj.getCreator());
                objEGetAllKCRecInfoResD.setDealTime(DateUtil.format(obj.getCreateTime()));

                dataList.add(objEGetAllKCRecInfoResD);
            }
        }

        body.setData(dataList);
        objEGetAllKCRecInfoRes.setBody(body);
        return objEGetAllKCRecInfoRes;
    }

    //导出库存变动
    @Override
    public ByteArrayOutputStream ExportGetAllKCRecInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetAllKCRecInfoResD> dataList = new ArrayList<GetAllKCRecInfoResD>();

            //入库信息
            List<RkDtlInfo> objERkDtlInfo = baseIService.QALLBaseInfo(RkTkIDAO.class, "SelectAllRkDtlInfo", argInitDataFields, argPageInfo);

            if (objERkDtlInfo != null && objERkDtlInfo.size() > 0) {
                for (RkDtlInfo obj : objERkDtlInfo) {

                    GetAllKCRecInfoResD objEGetAllKCRecInfoResD = new GetAllKCRecInfoResD();

                    //查询入库任务表信息
                    RkTkInfo objERkTkInfo = rkTkDAO.SelectRKTkInfoByrkCode(obj.getRkCode());
                    if (objERkTkInfo != null) {
                        if ("00".equals(objERkTkInfo.getAssSource())) {
                            objEGetAllKCRecInfoResD.setOptType("原材料入库");
                            //查询采购单信息
                            PurChInfo objEPurChInfo = purChDAO.SelectPurChInfoByPurChCode(objERkTkInfo.getAssCode());
                            if (objEPurChInfo != null) {
                                objEGetAllKCRecInfoResD.setCreateor(objEPurChInfo.getCreator());
                                objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objEPurChInfo.getCreateTime()));
                            } else {
                                objEGetAllKCRecInfoResD.setCreateor("");
                                objEGetAllKCRecInfoResD.setCreateTime("");
                            }
                        }
                        if ("01".equals(objERkTkInfo.getAssSource())) {
                            objEGetAllKCRecInfoResD.setOptType("原材料入库");
                            //查询来料收货单信息
                            InCInfo objEInCInfo = inCDAO.SelectByInCCode(objERkTkInfo.getAssCode());
                            if (objEInCInfo != null) {
                                objEGetAllKCRecInfoResD.setCreateor(objEInCInfo.getCreator());
                                objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objEInCInfo.getCreateTime()));
                            } else {
                                objEGetAllKCRecInfoResD.setCreateor("");
                                objEGetAllKCRecInfoResD.setCreateTime("");
                            }
                        }
                        if ("02".equals(objERkTkInfo.getAssSource())) {
                            objEGetAllKCRecInfoResD.setOptType("退料入库");
                            //查询快速退料信息
                            RetMaInfo objERetMaInfo = retMaInfoDAO.SelectRetMaInfoByRetCode(objERkTkInfo.getAssCode());
                            if (objERetMaInfo != null) {
                                objEGetAllKCRecInfoResD.setCreateor(objERetMaInfo.getCreator());
                                objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objERetMaInfo.getCreateTime()));
                            } else {
                                objEGetAllKCRecInfoResD.setCreateor("");
                                objEGetAllKCRecInfoResD.setCreateTime("");
                            }
                        }
                        if ("04".equals(objERkTkInfo.getAssSource())) {
                            objEGetAllKCRecInfoResD.setOptType("退料入库");
                            //查询退料信息
                            NRetMaInfo objENRetMaInfo = nRetMaInfoDAO.SelectRetMaInfoByRetCode(objERkTkInfo.getAssCode());
                            if (objENRetMaInfo != null) {
                                objEGetAllKCRecInfoResD.setCreateor(objENRetMaInfo.getCreator());
                                objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objENRetMaInfo.getCreateTime()));
                            } else {
                                objEGetAllKCRecInfoResD.setCreateor("");
                                objEGetAllKCRecInfoResD.setCreateTime("");
                            }
                        }
                        objEGetAllKCRecInfoResD.setOrderCode(objERkTkInfo.getAssCode());
                    }
                    objEGetAllKCRecInfoResD.setMaCode(obj.getMaterialCode());
                    objEGetAllKCRecInfoResD.setMaName(obj.getMaterialName());
                    objEGetAllKCRecInfoResD.setBatch(obj.getBatch());
                    objEGetAllKCRecInfoResD.setNum(obj.getNum());
                    objEGetAllKCRecInfoResD.setUnitName(obj.getUnitName());
                    objEGetAllKCRecInfoResD.setDealor(obj.getCreator());
                    objEGetAllKCRecInfoResD.setDealTime(DateUtil.format(obj.getCreateTime()));

                    dataList.add(objEGetAllKCRecInfoResD);
                }
            }

            //出库信息
            List<CkDtlInfo> objECkDtlInfo = baseIService.QALLBaseInfo(CkDtlDAO.class, "SelectAllCkDtlInfo", argInitDataFields, argPageInfo);

            if (objECkDtlInfo != null && objECkDtlInfo.size() > 0) {
                for (CkDtlInfo obj : objECkDtlInfo) {

                    GetAllKCRecInfoResD objEGetAllKCRecInfoResD = new GetAllKCRecInfoResD();

                    //查询入库任务表信息
                    CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByCkCode(obj.getCkCode());
                    if (objECkTkInfo != null) {
                        if ("00".equals(objECkTkInfo.getAssSource())) {
                            objEGetAllKCRecInfoResD.setOptType("领料出库");
                            //查询无工单领料单信息
                            PickInfo objEPickInfo = pickDAO.SelectPickInfoByPickCode(objECkTkInfo.getAssCode());
                            if (objEPickInfo != null) {
                                objEGetAllKCRecInfoResD.setCreateor(objEPickInfo.getCreator());
                                objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objEPickInfo.getCreateTime()));
                            } else {
                                objEGetAllKCRecInfoResD.setCreateor("");
                                objEGetAllKCRecInfoResD.setCreateTime("");
                            }
                        }

                        if ("01".equals(objECkTkInfo.getAssSource())) {
                            objEGetAllKCRecInfoResD.setOptType("领料出库");
                            //查询领料单信息
                            NPickInfo objENPickInfo = nPickDAO.SelectNPickInfoByPickCode(objECkTkInfo.getAssCode());
                            if (objENPickInfo != null) {
                                objEGetAllKCRecInfoResD.setCreateor(objENPickInfo.getCreator());
                                objEGetAllKCRecInfoResD.setCreateTime(DateUtil.format(objENPickInfo.getCreateTime()));
                            } else {
                                objEGetAllKCRecInfoResD.setCreateor("");
                                objEGetAllKCRecInfoResD.setCreateTime("");
                            }
                        }
                        objEGetAllKCRecInfoResD.setOrderCode(objECkTkInfo.getAssCode());
                    }
                    objEGetAllKCRecInfoResD.setMaCode(obj.getMaterialCode());
                    objEGetAllKCRecInfoResD.setMaName(obj.getMaterialName());
                    objEGetAllKCRecInfoResD.setBatch(obj.getBatch());
                    objEGetAllKCRecInfoResD.setNum(obj.getNum());
                    objEGetAllKCRecInfoResD.setUnitName(obj.getUnitName());
                    objEGetAllKCRecInfoResD.setDealor(obj.getCreator());
                    objEGetAllKCRecInfoResD.setDealTime(DateUtil.format(obj.getCreateTime()));

                    dataList.add(objEGetAllKCRecInfoResD);
                }
            }


            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"类型", "物料代码", "物料名称", "单号", "批号", "数量", "单位", "制单人", "制单时间", "处理人", "处理时间"}};

            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }


            /**********************************库存变动数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dataList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(dataList.get(i).getOptType());
                    } else if (j == 1) {
                        cell3.setCellValue(dataList.get(i).getMaCode());
                    } else if (j == 2) {
                        cell3.setCellValue(dataList.get(i).getMaName());
                    } else if (j == 3) {
                        cell3.setCellValue(dataList.get(i).getOrderCode());
                    } else if (j == 4) {
                        cell3.setCellValue(dataList.get(i).getBatch());
                    } else if (j == 5) {
                        cell3.setCellValue(dataList.get(i).getNum());
                    } else if (j == 6) {
                        cell3.setCellValue(dataList.get(i).getUnitName());
                    } else if (j == 7) {
                        cell3.setCellValue(dataList.get(i).getCreateor());
                    } else if (j == 8) {
                        cell3.setCellValue(dataList.get(i).getCreateTime());
                    } else if (j == 9) {
                        cell3.setCellValue(dataList.get(i).getDealor());
                    } else if (j == 10) {
                        cell3.setCellValue(dataList.get(i).getDealTime());
                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
            }
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        exportSWareInfoResB.setMsgCode("");
        exportSWareInfoResB.setMsgDes("成功!");

        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        exportSWareInfoRes.setBody(exportSWareInfoResB);

        return null;

    }

    //筛选汇总库存预警
    @Override
    public GetAllSWareInfoRes GetAllSum(GetAllSum argGetAllSum, List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllSWareInfoRes objEGetAllSWareInfoRes = new GetAllSWareInfoRes();
        GetAllSWareInfoResB objEGetAllSWareInfoResB = new GetAllSWareInfoResB();
        List<GetAllSWareInfoResD> objEGetAllSWareInfoResDs = new ArrayList<GetAllSWareInfoResD>();
        //查询库存明细所有信息
        List<InstockDtlInfo> objEInstockDtlInfo = baseIService.QALLBaseInfo(InstockDtlDAO.class, "SelectAllInstockDtlInfo", argInitDataFields, argPageInfo);
        Map<String, Float> map1 = new HashMap<String, Float>();

        if (objEInstockDtlInfo != null && objEInstockDtlInfo.size() > 0) {
            for (InstockDtlInfo obj : objEInstockDtlInfo) {

                //查询物料版本信息
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());

                if (objEMaVerInfo != null && objEMaVerInfo.getMaterialType().equals("01")) {
                    //查询批次信息
                    BInfo objEBInfo = bDAO.selectBatchInfoByBatch(obj.getBatch());
                    if (objEBInfo != null && !objEBInfo.getBad().equals("00")) {
                        if (map1.containsKey(obj.getMaVerGd())) {
                            float num = map1.get(obj.getMaVerGd());
                            map1.put(obj.getMaVerGd(), num + objEBInfo.getCanNum());
                        } else {
                            map1.put(obj.getMaVerGd(), objEBInfo.getCanNum());
                        }
                    }
                }
            }
            for (Map.Entry<String, Float> entry : map1.entrySet()) {


                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(entry.getKey());


                if (objEMaVerInfo != null) {
                    //查询单位
                    UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());

                    if (objEMaVerInfo.getMinSNum() != 0.0 || objEMaVerInfo.getMaxSNum() != 0.0) {
                        if (entry.getValue() < objEMaVerInfo.getMinSNum() || entry.getValue() > objEMaVerInfo.getMaxSNum()) {
                            if ("".equals(argGetAllSum.getMaCode()) && ("".equals(argGetAllSum.getMaName()))) {
                                if (argGetAllSum.getSumNum() != null) {
                                    if (argGetAllSum.getSumNum() <= entry.getValue()) {
                                        GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                        objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                        objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                        objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                        objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                        objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                        if (objEUnitInfo != null) {
                                            objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                        } else {
                                            objEGetAllSWareInfoResD.setUnitName("");
                                        }
                                        objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                    }
                                }

                            }
                            if ("".equals(argGetAllSum.getMaName()) && (argGetAllSum.getSumNum() == null)) {
                                if (objEMaVerInfo.getMaterialCode().toLowerCase().indexOf(argGetAllSum.getMaCode().toLowerCase()) >= 0) {
                                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                    objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                    objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                    objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                    objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                    objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                    if (objEUnitInfo != null) {
                                        objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                    } else {
                                        objEGetAllSWareInfoResD.setUnitName("");
                                    }
                                    objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                }
                            }
                            if (("".equals(argGetAllSum.getMaCode())) && (argGetAllSum.getSumNum() == null)) {
                                if (objEMaVerInfo.getMaterialName().toLowerCase().indexOf(argGetAllSum.getMaName().toLowerCase()) >= 0) {
                                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                    objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                    objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                    objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                    objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                    objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                    if (objEUnitInfo != null) {
                                        objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                    } else {
                                        objEGetAllSWareInfoResD.setUnitName("");
                                    }
                                    objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                }
                            }
                            if ("".equals(argGetAllSum.getMaCode()) && argGetAllSum.getSumNum() != null && !"".equals(argGetAllSum.getMaName())) {
                                if (objEMaVerInfo.getMaterialName().toLowerCase().indexOf(argGetAllSum.getMaName().toLowerCase()) >= 0 && (argGetAllSum.getSumNum() <= entry.getValue())) {
                                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                    objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                    objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                    objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                    objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                    objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                    if (objEUnitInfo != null) {
                                        objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                    } else {
                                        objEGetAllSWareInfoResD.setUnitName("");
                                    }
                                    objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                }
                            }
                            if (!"".equals(argGetAllSum.getMaCode()) && argGetAllSum.getSumNum() != null && "".equals(argGetAllSum.getMaName())) {
                                if (objEMaVerInfo.getMaterialCode().toLowerCase().indexOf(argGetAllSum.getMaCode().toLowerCase()) >= 0 && (argGetAllSum.getSumNum() <= entry.getValue())) {
                                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                    objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                    objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                    objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                    objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                    objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                    if (objEUnitInfo != null) {
                                        objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                    } else {
                                        objEGetAllSWareInfoResD.setUnitName("");
                                    }
                                    objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                }
                            }
                            if (!"".equals(argGetAllSum.getMaCode()) && argGetAllSum.getSumNum() != null && !"".equals(argGetAllSum.getMaName())) {
                                if (objEMaVerInfo.getMaterialCode().toLowerCase().indexOf(argGetAllSum.getMaCode().toLowerCase()) >= 0 && objEMaVerInfo.getMaterialName().toLowerCase().indexOf(argGetAllSum.getMaName().toLowerCase()) >= 0 && argGetAllSum.getSumNum() <= entry.getValue()) {
                                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                    objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                    objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                    objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                    objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                    objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                    if (objEUnitInfo != null) {
                                        objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                    } else {
                                        objEGetAllSWareInfoResD.setUnitName("");
                                    }
                                    objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                }
                            }
                            if (!"".equals(argGetAllSum.getMaCode()) && argGetAllSum.getSumNum() == null && !"".equals(argGetAllSum.getMaName())) {
                                if ((objEMaVerInfo.getMaterialCode().toLowerCase().indexOf(argGetAllSum.getMaCode().toLowerCase()) >= 0 && objEMaVerInfo.getMaterialName().toLowerCase().indexOf(argGetAllSum.getMaName().toLowerCase()) >= 0)) {
                                    GetAllSWareInfoResD objEGetAllSWareInfoResD = new GetAllSWareInfoResD();
                                    objEGetAllSWareInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                                    objEGetAllSWareInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                                    objEGetAllSWareInfoResD.setMinSNum(objEMaVerInfo.getMinSNum());
                                    objEGetAllSWareInfoResD.setMaxSNum(objEMaVerInfo.getMaxSNum());
                                    objEGetAllSWareInfoResD.setStoreNum(entry.getValue());
                                    if (objEUnitInfo != null) {
                                        objEGetAllSWareInfoResD.setUnitName(objEUnitInfo.getUnitName());
                                    } else {
                                        objEGetAllSWareInfoResD.setUnitName("");
                                    }
                                    objEGetAllSWareInfoResDs.add(objEGetAllSWareInfoResD);
                                }
                            }
                        }
                    }
                }
            }
        }
        objEGetAllSWareInfoResB.setData(objEGetAllSWareInfoResDs);
        objEGetAllSWareInfoRes.setBody(objEGetAllSWareInfoResB);
        return objEGetAllSWareInfoRes;
    }

    //产线余料报表
    @Override
    public GetAllLYMaInfoRes QALLGetAllLYMaInfoRes(GetAllLYMaInfoResD1 argGetAllLYMaInfoResD1) throws SystemException {
        GetAllLYMaInfoRes objEGetAllLYMaInfoRes = new GetAllLYMaInfoRes();

        GetAllLYMaInfoResB body = new GetAllLYMaInfoResB();

        // 获取产线余料报表信息
        List<GetAllLYMaInfoResD> dataList = new ArrayList<GetAllLYMaInfoResD>();

        //查询在线领用物料批次表
        List<PickBatchInfo> objEPickBatchInfo = pickBatchDAO.SelectPickBatchInfoByBaoBiao(argGetAllLYMaInfoResD1.getMaCode(), argGetAllLYMaInfoResD1.getMaName(), argGetAllLYMaInfoResD1.getStatus(), argGetAllLYMaInfoResD1.getCreateTime(), argGetAllLYMaInfoResD1.getCreateTime1(), argGetAllLYMaInfoResD1.getWoCode(), argGetAllLYMaInfoResD1.getBatch());

        if (objEPickBatchInfo != null && objEPickBatchInfo.size() > 0) {
            for (PickBatchInfo obj : objEPickBatchInfo) {
                GetAllLYMaInfoResD objEGetAllLYMaInfoResD = new GetAllLYMaInfoResD();
                //查询批次信息
                BInfo objeBInfo = bDAO.selectBatchInfoByBatch(obj.getBatch());

                if (objeBInfo != null) {
                    //查询物料版本信息
                    MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objeBInfo.getMaVerGd());

                    if (objEMaVerInfo != null) {
                        MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        objEGetAllLYMaInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                        objEGetAllLYMaInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                        if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objEGetAllLYMaInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        } else {
                            objEGetAllLYMaInfoResD.setMaDes("");
                        }
                    } else {
                        objEGetAllLYMaInfoResD.setMaCode("");
                        objEGetAllLYMaInfoResD.setMaName("");
                    }

                    objEGetAllLYMaInfoResD.setNum(objeBInfo.getCanNum());

                    //查询单位信息
                    UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objeBInfo.getUnitGd());
                    if (objEUnitInfo != null) {
                        objEGetAllLYMaInfoResD.setUnitName(objEUnitInfo.getUnitName());
                    } else {
                        objEGetAllLYMaInfoResD.setUnitName("");
                    }
                    if ("00".equals(objeBInfo.getStatus())) {
                        objEGetAllLYMaInfoResD.setStatus("待消耗");
                    } else if ("01".equals(objeBInfo.getStatus())) {
                        objEGetAllLYMaInfoResD.setStatus("消耗中");
                    } else if ("02".equals(objeBInfo.getStatus())) {
                        objEGetAllLYMaInfoResD.setStatus("冻结");
                    } else if ("03".equals(objeBInfo.getStatus())) {
                        objEGetAllLYMaInfoResD.setStatus("报废");
                    } else {
                        objEGetAllLYMaInfoResD.setStatus("");
                    }
                }
                if ("00".equals(obj.getAssSource())) {
                    WoInfo objEWoInfo = woDAO.SelectWoInfoBywoCode(obj.getAssCode());
                    if (objEWoInfo != null) {
                        objEGetAllLYMaInfoResD.setWoCode(objEWoInfo.getWoCode());
                    } else {
                        objEGetAllLYMaInfoResD.setWoCode("");
                    }
                }else{
                    objEGetAllLYMaInfoResD.setWoCode("");
                }
                objEGetAllLYMaInfoResD.setBatch(obj.getBatch());
                if ("00".equals(obj.getSource())) {
                    objEGetAllLYMaInfoResD.setSource("领料");
                } else if ("01".equals(obj.getSource())) {
                    objEGetAllLYMaInfoResD.setSource("拆除");
                } else if ("02".equals(obj.getSource())) {
                    objEGetAllLYMaInfoResD.setSource("自建");
                } else {
                    objEGetAllLYMaInfoResD.setSource("");
                }

                dataList.add(objEGetAllLYMaInfoResD);
            }
        }

        body.setData(dataList);
        objEGetAllLYMaInfoRes.setBody(body);
        return objEGetAllLYMaInfoRes;
    }

    //导出余料报表
    @Override
    public ByteArrayOutputStream ExportYMaInfo(GetAllLYMaInfoResD1 argGetAllLYMaInfoResD1) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            // 获取产线余料报表信息
            List<GetAllLYMaInfoResD> dataList = new ArrayList<GetAllLYMaInfoResD>();

            //查询在线领用物料批次表
            List<PickBatchInfo> objEPickBatchInfo = pickBatchDAO.SelectPickBatchInfoByBaoBiao(argGetAllLYMaInfoResD1.getMaCode(), argGetAllLYMaInfoResD1.getMaName(), argGetAllLYMaInfoResD1.getStatus(), argGetAllLYMaInfoResD1.getCreateTime(), argGetAllLYMaInfoResD1.getCreateTime1(), argGetAllLYMaInfoResD1.getWoCode(), argGetAllLYMaInfoResD1.getBatch());

            if (objEPickBatchInfo != null && objEPickBatchInfo.size() > 0) {
                for (PickBatchInfo obj : objEPickBatchInfo) {
                    GetAllLYMaInfoResD objEGetAllLYMaInfoResD = new GetAllLYMaInfoResD();
                    //查询批次信息
                    BInfo objeBInfo = bDAO.selectBatchInfoByBatch(obj.getBatch());

                    if (objeBInfo != null) {
                        //查询物料版本信息
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objeBInfo.getMaVerGd());

                        if (objEMaVerInfo != null) {
                            objEGetAllLYMaInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                            objEGetAllLYMaInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                        } else {
                            objEGetAllLYMaInfoResD.setMaCode("");
                            objEGetAllLYMaInfoResD.setMaName("");
                        }
                        objEGetAllLYMaInfoResD.setNum(objeBInfo.getCanNum());

                        //查询单位信息
                        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objeBInfo.getUnitGd());
                        if (objEUnitInfo != null) {
                            objEGetAllLYMaInfoResD.setUnitName(objEUnitInfo.getUnitName());
                        } else {
                            objEGetAllLYMaInfoResD.setUnitName("");
                        }
                        if ("00".equals(objeBInfo.getStatus())) {
                            objEGetAllLYMaInfoResD.setStatus("待消耗");
                        }
                        if ("01".equals(objeBInfo.getStatus())) {
                            objEGetAllLYMaInfoResD.setStatus("消耗中");
                        }
                        if ("02".equals(objeBInfo.getStatus())) {
                            objEGetAllLYMaInfoResD.setStatus("冻结");
                        }
                        if ("03".equals(objeBInfo.getStatus())) {
                            objEGetAllLYMaInfoResD.setStatus("报废");
                        }
                    }
                    if ("00".equals(obj.getAssSource())) {
                        WoInfo objEWoInfo = woDAO.SelectWoInfoBywoCode(obj.getAssCode());
                        if (objEWoInfo != null) {
                            objEGetAllLYMaInfoResD.setWoCode(objEWoInfo.getWoCode());
                        } else {
                            objEGetAllLYMaInfoResD.setWoCode("");
                        }
                    }
                    objEGetAllLYMaInfoResD.setBatch(obj.getBatch());
                    if ("00".equals(obj.getSource())) {
                        objEGetAllLYMaInfoResD.setSource("领料");
                    } else if ("01".equals(obj.getSource())) {
                        objEGetAllLYMaInfoResD.setSource("拆除");
                    } else if ("02".equals(obj.getSource())) {
                        objEGetAllLYMaInfoResD.setSource("自建");
                    } else {
                        objEGetAllLYMaInfoResD.setSource("");
                    }

                    dataList.add(objEGetAllLYMaInfoResD);
                }
            }

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"批次号", "物料代码", "物料名称", "数量", "单位", "状态", "来源","工单号"}};

            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }


            /**********************************批次查询数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dataList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 7) {
                        cell3.setCellValue(dataList.get(i).getWoCode());
                    } else if (j == 0) {
                        cell3.setCellValue(dataList.get(i).getBatch());
                    } else if (j == 1) {
                        cell3.setCellValue(dataList.get(i).getMaCode());
                    } else if (j == 2) {
                        cell3.setCellValue(dataList.get(i).getMaName());
                    } else if (j == 3) {
                        cell3.setCellValue(dataList.get(i).getNum());
                    } else if (j == 4) {
                        cell3.setCellValue(dataList.get(i).getUnitName());
                    } else if (j == 5) {
                        cell3.setCellValue(dataList.get(i).getStatus());
                    } else if (j == 6) {
                        cell3.setCellValue(dataList.get(i).getSource());
                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
            }
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        exportSWareInfoResB.setMsgCode("");
        exportSWareInfoResB.setMsgDes("成功!");

        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        exportSWareInfoRes.setBody(exportSWareInfoResB);

        return null;

    }

}
