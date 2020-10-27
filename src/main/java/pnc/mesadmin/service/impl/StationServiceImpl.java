package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoReqBD00;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoRes;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoResB;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoResD;
import pnc.mesadmin.dto.GetStationDto.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.StationService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.entity.common.StationApiInfo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Resource
    private StationDao stationDao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private WODAO wODAO;
    @Resource
    private CoTypeDAO coTypeDAO;
    @Resource
    private CustomerDAO customerDAO;
    @Resource
    CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    DevDAO devDAO;
    @Resource
    LineDao lineDao;
    @Resource
    SpecDAO specDAO;
    @Resource
    SpecVerDAO specVerDAO;
    @Resource
    PrinterDAO printerDAO;

    @Resource
    ExStationDtlDao exStationDtlDao;

    @Resource
    ResDAO resDAO;

    @Resource
    DevGpDAO devGpDAO;
    @Resource
    DevGpDtlDAO devGpDtlDAO;
    @Resource
    UserDAO userDAO;

    @Resource
    PrintTDAO printTDAO;

    @Resource
    private StationUDMDAO stationUDMDAO;

    @Resource
    private StationIntegratedDAO stationIntegratedDAO;

    @Resource
    private StationAlarmDAO stationAlarmDAO;

    @Resource
    private StationAlarmRevDAO stationAlarmRevDAO;

    @Resource
    private DevMapInfoDAO devMapInfoDAO;

    @Resource
    private StationProdDevDAO stationProdDevDAO;

    @Resource
    private StationApiDAO stationApiDAO;

    //获取工位列表信息
    @Override
    public BaseRes GetAllStationInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<StationInfo> stationInfoList = baseIService.QALLBaseInfo(StationDao.class, "SelectAllStationInfo",
                argInitDataFields, argPageInfo);
        List<GetAllStationInfoResB> responseList = new ArrayList<>();
        for (StationInfo model : stationInfoList) {
            GetAllStationInfoResB response = new GetAllStationInfoResB();
            response.setStationRd(model.getRuid());
            response.setStationName(model.getStationName());
            Lineinfo lineinfo = lineDao.SelectLineInfoByguid(model.getLineGd());
            responseList.add(response);
        }
        baseResBody.setData(responseList);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //获取工位列表信息【树】
    @Override
    public BaseRes GetAllStationInfoTree(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<Lineinfo> lineinfoList = baseIService.QALLBaseInfo(LineDao.class, "SelectAllLineInfo",
                argInitDataFields, argPageInfo);
        List<GetAllStationInfoTreeResB> responseList = new ArrayList<>();
        for (Lineinfo model : lineinfoList) {
            GetAllStationInfoTreeResB response = new GetAllStationInfoTreeResB();
            response.setLineRd(model.getRuid());
            response.setLineName(model.getLineName());
            List<GetAllTreeStationInfoListResB> stationInfoResBList = stationDao.SelectStationInfoByLineGd(model.getGuid());
            response.setStationList(stationInfoResBList);
            responseList.add(response);
        }
        baseResBody.setData(responseList);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes GetAllStationInfoByLineRd(GetAllReq req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(Integer.valueOf(req.getBusData()));
        List<GetAllTreeStationInfoListResB> stationInfoList = stationDao.SelectStationInfoByLineGd(lineinfo.getGuid());
        GetAllTreeStationInfoListResB response = new GetAllTreeStationInfoListResB();
        response.setId(stationInfoList.size());
        response.setName(lineinfo.getLineName());
        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //获取详情信息
    @Override
    public BaseRes GetStationInfo(GetStationInfoRes request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        StationInfo stationInfo = stationDao.SelectByRuId(request.getStationRd());

        GetStationInfoResB response = new GetStationInfoResB();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        response.setStationRd(stationInfo.getRuid());
        response.setStationName(stationInfo.getStationName());
        //生产设备信息
        List<GetStationInfoResB.AssDevInfo> devInfos = new ArrayList<>();
        List<StationProdDevInfo> stationProdDevInfos = stationProdDevDAO.SelectByStationGd(stationInfo.getGuid());
        if (stationProdDevInfos != null && stationProdDevInfos.size() > 0) {
            for (StationProdDevInfo stationProdDevInfo : stationProdDevInfos) {
                DevInfo devInfoModel = devDAO.SelectByguid(stationProdDevInfo.getAssDevGd());
                GetStationInfoResB.AssDevInfo devInfo = new GetStationInfoResB.AssDevInfo();
                if (devInfoModel != null) {
                    devInfo.setDevRd(devInfoModel.getRuid());
                    devInfo.setDevName(devInfoModel.getDevName());
                    devInfos.add(devInfo);
                }
            }
        }


        response.setAssDevInfo(devInfos);
        //输出线体信息
        GetStationInfoResB.LineInfo lineInfo = new GetStationInfoResB.LineInfo();
        if (stationInfo.getLineGd() != null) {
            Lineinfo lineinfoModel = lineDao.SelectLineInfoByguid(stationInfo.getLineGd());
            if (lineinfoModel != null) {
                lineInfo.setLineRd(lineinfoModel.getRuid());
                lineInfo.setLineName(lineinfoModel.getLineName());
            }
        }
        response.setLineInfo(lineInfo);
        //输出工序信息
        GetStationInfoResB.SpecInfo specInfo = new GetStationInfoResB.SpecInfo();
        if (stationInfo.getSpecVerGd() != null) {
            SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(stationInfo.getSpecVerGd());
            if (specVerInfo != null) {
                SpecInfo specInfoModel = specDAO.SelectSpecInfoByguid(specVerInfo.getSpecGd());
                if (specInfoModel != null) {
                    specInfo.setSpecRd(specInfoModel.getRuid());
                    specInfo.setSpecName(specInfoModel.getSpecName());
                }
            }
        }
        response.setSpecInfo(specInfo);
        //输出打印信息
        GetStationInfoResB.PrinterInfo printerInfo = new GetStationInfoResB.PrinterInfo();
        if (stationInfo.getPinterGd() != null) {
            PrinterInfo printerInfoModel = printerDAO.SelectByGuid(stationInfo.getPinterGd());
            if (printerInfoModel != null) {
                printerInfo.setPrintRd(printerInfoModel.getRuid());
                printerInfo.setPrName(printerInfoModel.getPrinterName());
            }
        }
        response.setPrinterInfo(printerInfo);

        //输出打印模板信息
        GetStationInfoResB.PrintTInfo printTInfo = new GetStationInfoResB.PrintTInfo();
        if (stationInfo.getPrintTGd() != null) {
            PrintTInfo printTInfoModel = printTDAO.SelectByGuid(stationInfo.getPrintTGd());
            if (printTInfoModel != null) {
                printTInfo.setPrintTRd(printTInfoModel.getRuid());
                printTInfo.setPrintTName(printTInfoModel.getTempName());
            }
        }
        response.setPrintTInfo(printTInfo);
        //打印份数
        response.setPrintCopy(stationInfo.getPrintCopy());
        //输出设备或者用户信息或者菜单信息
        GetStationInfoResB.OStation oStation = new GetStationInfoResB.OStation();
        List<GetStationInfoResB.ExDetail> exDetailList = new ArrayList<>();
        List<ExStationDtlInfo> exStationDtlInfoList = exStationDtlDao.SelectAllExStationDtlInfoBySGD(stationInfo.getGuid());
        response.setExecType(stationInfo.getExecType());
        if (stationInfo.getExecType().equals("00")) {//设备
            List<ExStationDtlInfo> exStationDtlInfoList1 = exStationDtlDao.SelectAllExStationDtlInfoBySGD1(stationInfo.getGuid());
            if (exStationDtlInfoList.size() > 0) {
                List<ExStationDtlInfo> lists = new ArrayList<>();
                for (ExStationDtlInfo model : exStationDtlInfoList) {
                    if (model.getExecGGd() == null || model.getExecGGd() == "") {
                        lists.add(model);
                    }

                }
                for (ExStationDtlInfo model : exStationDtlInfoList1) {
                    if (model != null) {
                        lists.add(model);
                    }

                }
                for (ExStationDtlInfo models : lists) {


                    GetStationInfoResB.ExDetail exDetail = new GetStationInfoResB.ExDetail();
                    //输出设备信息
                    if ((models.getExecGd() != null && models.getExecGd() != "") && (models.getExecGGd() == null || models.getExecGGd() == "")) {
                        DevInfo devInfoExDetail = devDAO.SelectByguid(models.getExecGd());
                        if (devInfoExDetail != null) {
                            exDetail.setExecRd(devInfoExDetail.getRuid());
                            exDetail.setExecName(devInfoExDetail.getDevName());
                        }
                    }
                    //输出设备组信息
                    if (models.getExecGGd() != null && models.getExecGGd() != "") {
                        DevGpInfo devGpInfo = devGpDAO.SelectByguid(models.getExecGGd());
                        if (devGpInfo != null) {
                            exDetail.setExecGRd(devGpInfo.getRuid());
                            exDetail.setExecGName(devGpInfo.getDevGpName());
                        }
                    }
                    exDetailList.add(exDetail);
                }
            }
        } else if (stationInfo.getExecType().equals("01")) {//产线作业人
            if (exStationDtlInfoList.size() > 0) {
                for (ExStationDtlInfo model : exStationDtlInfoList) {
                    GetStationInfoResB.ExDetail exDetail = new GetStationInfoResB.ExDetail();
                    if (model.getExecGd() != null) {
                        UserInfo userInfo = userDAO.SelectUserRd(model.getExecGd());
                        if (userInfo != null) {
                            exDetail.setExecRd(userInfo.getRuid());
                            exDetail.setExecName(userInfo.getUserName());
                        }
                    }
                    exDetailList.add(exDetail);
                }
            }
        } else if (stationInfo.getExecType().equals("02")) {//关联页面菜单
            if (exStationDtlInfoList.size() > 0) {
                for (ExStationDtlInfo model : exStationDtlInfoList) {
                    GetStationInfoResB.ExDetail exDetail = new GetStationInfoResB.ExDetail();
                    if (model.getExecGd() != null) {
                        ResInfo resInfo = resDAO.SelectByGuid(model.getExecGd());
                        if (resInfo != null) {
                            exDetail.setExecRd(resInfo.getRuid());
                            exDetail.setExecName(resInfo.getResName());
                        }
                    }
                    exDetailList.add(exDetail);
                }
            }
        }
        response.setExDetail(exDetailList);

        oStation.setStart(stationInfo.getStart());
        oStation.setUp(stationInfo.getUp());
        oStation.setDown(stationInfo.getDown());
        oStation.setEnd(stationInfo.getEnd());
        //添加载具解绑 00#载具解绑 01#未设置
        oStation.setCarrierUnBind(stationInfo.getCarrierUnBind());

        //打印标签 00#打印标签 01#未设置
        oStation.setPrintLabel(stationInfo.getPrintLabel());
        oStation.setCarrierType(stationInfo.getCarrierType());
        //添加执行类型
        response.setOStation(oStation);
        //上下料信息
        List<GetStationInfoResB.UDM> udms = new ArrayList<>();
        List<StationUDMInfo> stationUDMInfos = stationUDMDAO.seleceStationGd(stationInfo.getGuid());
        if (stationUDMInfos.size() > 0) {
            for (StationUDMInfo stationUDMInfo : stationUDMInfos) {
                GetStationInfoResB.UDM udm = new GetStationInfoResB.UDM();
                udm.setUDMType(stationUDMInfo.getUDMType());
                udm.setUDMCarrierType(stationUDMInfo.getUDMCarrierType());
                udm.setDMArea(stationUDMInfo.getDMArea());
                udm.setUDMIndex(stationUDMInfo.getUDMIndex());
                udms.add(udm);
            }
        }
        response.setUDM(udms);
        //设备集成信息
        List<GetStationInfoResB.Integrated> integrateds = new ArrayList<>();
        List<StationIntegratedInfo> stationIntegratedInfos = stationIntegratedDAO.selectStationGD(stationInfo.getGuid());
        if (stationIntegratedInfos.size() > 0) {
            for (StationIntegratedInfo stationIntegratedInfo : stationIntegratedInfos) {
                GetStationInfoResB.Integrated integrated = new GetStationInfoResB.Integrated();
                //根据设备标识查询设备信息
                DevInfo devInfo1 = devDAO.SelectByguid(stationIntegratedInfo.getDevGd());
                integrated.setDevRd(devInfo1.getRuid());
                integrated.setDevName(devInfo1.getDevName());
                //根据映射设备标识查询映射设备信息
                DevMapInfo devInfo2 = devMapInfoDAO.SelectGuidDevMap(stationIntegratedInfo.getDevMapGd());
                integrated.setDevMapRd(devInfo2.getRuid());
                integrated.setDevMapName(devInfo2.getDevMapName());
                integrated.setTriggerType(stationIntegratedInfo.getTriggerType());
                integrated.setCmdText(stationIntegratedInfo.getCmdText());
                integrated.setCmdType(stationIntegratedInfo.getCmdType());
                integrated.setCmdVal(stationIntegratedInfo.getCmdVal());

                integrateds.add(integrated);
            }
        }
        response.setIntegrated(integrateds);
        //警报提示信息
        List<GetStationInfoResB.Alarm> alarms = new ArrayList<>();
        List<StationAlarmInfo> stationAlarmInfos = stationAlarmDAO.selectStationAlarm(stationInfo.getGuid());
        if (stationAlarmInfos.size() > 0) {
            for (StationAlarmInfo stationAlarmInfo : stationAlarmInfos) {
                GetStationInfoResB.Alarm alarm = new GetStationInfoResB.Alarm();
                alarm.setExcepType(stationAlarmInfo.getExcepType());
                alarm.setAlarmType(stationAlarmInfo.getAlarmType());
                alarm.setAlarmRev(stationAlarmInfo.getAlarmRev());
                List<GetStationInfoResB.AlarmRevDtl> alarmRevDtls = new ArrayList<>();
                //判断是否指定人员
                if (stationAlarmInfo.getAlarmRev().equals("01")) {
                    //根据警报提示标识查询警报接收人员信息
                    List<StationAlarmRevInfo> stationAlarmRevInfos = stationAlarmRevDAO.selectStationAlarmRevInfo(stationAlarmInfo.getGuid());
                    if (stationAlarmRevInfos.size() > 0) {
                        for (StationAlarmRevInfo stationAlarmRevInfo : stationAlarmRevInfos) {
                            GetStationInfoResB.AlarmRevDtl alarmRevDtl = new GetStationInfoResB.AlarmRevDtl();
                            //根据用户标识查询用户信息
                            UserInfo userInfo = userDAO.SelectUserRd(stationAlarmRevInfo.getUserGd());
                            alarmRevDtl.setUserRd(userInfo.getRuid());
                            alarmRevDtl.setUserName(userInfo.getUserName());
                            alarmRevDtls.add(alarmRevDtl);
                        }
                        alarm.setAlarmRevDtl(alarmRevDtls);
                    }
                }
                alarms.add(alarm);
            }
            response.setAlarm(alarms);
        }


        //接口集成信息
        List<GetStationInfoResB.StationAPI> stationAPIS = new ArrayList<>();
        List<StationApiInfo> stationApiInfos = stationApiDAO.SelectStationGd(stationInfo.getGuid());
        if (stationApiInfos.size() > 0) {

            for (StationApiInfo stationApiInfo : stationApiInfos) {
                GetStationInfoResB.StationAPI API = new GetStationInfoResB.StationAPI();
                API.setAPIUrl(stationApiInfo.getAPIUrl());
                API.setTriggerType(stationApiInfo.getTriggerType());
                API.setSysVal(stationApiInfo.getSysVal());
                stationAPIS.add(API);
            }
            response.setStationAPI(stationAPIS);

        }

        response.setStatus(stationInfo.getStatus());
        response.setCreator(stationInfo.getCreator());
        response.setCreateTime(format.format(stationInfo.getCreateTime()));
        if (stationInfo.getLastModifyTime() != null) {
            response.setLastModifyMan(stationInfo.getLastModifyMan());
            response.setLastModifyTime(format.format(stationInfo.getLastModifyTime()));
        }
        response.setRemark(stationInfo.getRemark());
        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //新增工位信息
    @Override
    public BaseRes AddStationInfo(SaveStationInfoRes request) {
        SaveStationInfoRes.OStation oStation = request.getOStation();
        if (oStation.getDown() == null || oStation.getEnd() == null || oStation.getUp() == null || oStation.getStart() == null ||
                oStation.getCarrierUnBind() == null || oStation.getPrintLabel() == null) {
            throw new SystemException("EEEE", "新增失败，请选择作业行为！");
        }
        if (request.getSpecRd() == null || request.getLineRd() == null) {
            throw new SystemException("EEEE", "新增失败，必穿参数不能为空");
        }
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (request.getStationName() == null || request.getStationName() == "") {
            throw new SystemException("EEEE", "新增失败，工位名称不能为空");
        }
        GetStationInfoResB response = new GetStationInfoResB();

        List<StationInfo> stationInfoList = stationDao.SelectAllStationInfoByName(request.getStationName());
        if (stationInfoList.size() > 0) {
            throw new SystemException("EEEE", "新增失败，工位名称重复");
        }
        //创建工位对象添加工位信息
        StationInfo stationInfo = new StationInfo();
        stationInfo.setGuid(CommonUtils.getRandomNumber());
        stationInfo.setStationName(request.getStationName());

        //生产设备标识
        addStatuonDev(request, stationInfo);
        //线体标识
        if (request.getLineRd() != null) {
            Lineinfo lineinfoModel = lineDao.SelectLineInfoBygruid(request.getLineRd());
            if (lineinfoModel != null) {
                stationInfo.setLineGd(lineinfoModel.getGuid());
            }
        }
        //工序标识
        if (request.getSpecRd() != null) {
            SpecInfo specInfoModel = specDAO.SelectSpecInfoByruid(request.getSpecRd());
            if (specInfoModel != null) {
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByspecGd00(specInfoModel.getGuid());
                if (specVerInfo != null) {
                    stationInfo.setSpecVerGd(specVerInfo.getGuid());
                }
            }
        }
        //输出打印信息
        if (request.getPrintRd() != null) {
            PrinterInfo printerInfo = printerDAO.SelectByRuid(request.getPrintRd());
            if (printerInfo != null) {
                stationInfo.setPinterGd(printerInfo.getGuid());
            }
        }

        //输出打印模板信息
        if (request.getPrintTRd() != null) {
            PrintTInfo printTInfo = printTDAO.SelectPrintTInfo(request.getPrintTRd());
            if (printTInfo != null) {
                stationInfo.setPrintTGd(printTInfo.getGuid());
            }
        }
        //执行实体类型
        stationInfo.setExecType(request.getExecType());
        //状态
        stationInfo.setStatus(request.getStatus());
        //进站
        stationInfo.setStart(oStation.getStart());
        //上机
        stationInfo.setUp(oStation.getUp());
        //下机
        stationInfo.setDown(oStation.getDown());
        //出站
        stationInfo.setEnd(oStation.getEnd());
        //载具解绑
        stationInfo.setCarrierUnBind(oStation.getCarrierUnBind());
        //打印标签
        stationInfo.setPrintLabel(oStation.getPrintLabel());
        //打印份数
        if (request.getPrintCopy() != null && request.getPrintCopy().equals("")) {
            stationInfo.setPrintCopy(request.getPrintCopy());
        } else {
            stationInfo.setPrintCopy(1);
        }
        //作业载体
        if (oStation.getCarrierType() == null) {
            throw new SystemException("", "作业载体不能为空");
        }
        stationInfo.setCarrierType(oStation.getCarrierType());

        stationInfo.setCreator(CommonUtils.readUser().getRealName());
        stationInfo.setCreateTime(new Date());
        stationInfo.setRemark(request.getRemark());
        /*stationInfo.setUpMa(request.getUpMa());
        stationInfo.setDownMa(request.getDownMa());*/
        //插入工位信息
        stationDao.InsertStationInfo(stationInfo);

        //添加执行实体类信息
        if (request.getExDetail() != null || request.getExDetail().size() > 0) {
            SaveOperateModel(request, stationInfo);
        }

        //添加上下料配置信息
        addStationUDMInfo(request, stationInfo);
        //添加设备集成信息
        addStationIntegratedInfo(request, stationInfo);
        //添加警报提示信息
        addStationAlarmInfo(request, stationInfo);
        //添加接口集成
        addStationAPI(request, stationInfo);

        baseResBody.setData(response);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //删除工位信息
    @Override
    public BaseRes RmDelStationInfo(SaveStationInfoRes request) {
        BaseRes baseResponse = new BaseRes();
        StationInfo stationInfo = stationDao.SelectByRuId(request.getStationRd());
        //删除生产设备
        stationProdDevDAO.deleteStation(stationInfo.getGuid());
        //删除执行实体信息
        exStationDtlDao.delExStationDtlInfoByStationGuid(stationInfo.getGuid());
        //删除上下料信息
        int i = stationUDMDAO.deleteStationUDMInfo(stationInfo.getGuid());
        //删除设备集成信息
        int si = stationIntegratedDAO.delectStationIntegratedInfo(stationInfo.getGuid());
        //删除警报提示信息
        stationAlarmDAO.deleteStationAlarmInfo(stationInfo.getGuid());
        //删除指定警报人员信息
        stationAlarmRevDAO.deleteStationAlarmRevInfo(stationInfo.getGuid());
        //删除接口集成
        stationApiDAO.DelAll(stationInfo.getGuid());
        stationDao.delStationInfo(request.getStationRd());
        return baseResponse;
    }

    //修改
    @Override
    public BaseRes AddSaveStationInfo(SaveStationInfoRes request) {
        SaveStationInfoRes.OStation oStation = request.getOStation();
        if (oStation.getDown() == null || oStation.getEnd() == null || oStation.getUp() == null || oStation.getStart() == null ||
                oStation.getCarrierUnBind() == null || oStation.getPrintLabel() == null) {
            throw new SystemException("EEEE", "修改失败，请选择作业行为！");
        }
        if (request.getSpecRd() == null || request.getLineRd() == null) {
            throw new SystemException("EEEE", "修改失败，必穿参数不能为空");
        }
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();

        if (request.getStationName() == null || request.getStationName() == "") {
            throw new SystemException("EEEE", "修改失败，工位名称不能为空");
        }
        StationInfo stationInfo1 = stationDao.SelectByRuId(request.getStationRd());
        List<StationInfo> stationInfoList = stationDao.SelectAllStationInfoByName(request.getStationName());
        if (stationInfoList.size() > 0) {
            int r = request.getStationRd();
            if (r != stationInfoList.get(0).getRuid()) {
                throw new SystemException("EEEE", "修改失败，工位名称重复");
            }
        }
        //修改工位信息
        StationInfo stationInfo = new StationInfo();
        stationInfo.setRuid(request.getStationRd());
        stationInfo.setGuid(stationInfo1.getGuid());
        stationInfo.setStationName(request.getStationName());
        //生产设备标识
        //清空生产设备信息
        stationProdDevDAO.deleteStation(stationInfo.getGuid());
        //添加设备标识
        addStatuonDev(request, stationInfo);
        //线体标识
        if (request.getLineRd() != null) {
            Lineinfo lineinfoModel = lineDao.SelectLineInfoBygruid(request.getLineRd());
            if (lineinfoModel != null) {
                stationInfo.setLineGd(lineinfoModel.getGuid());
            }
        }
        //工序版本标识
        if (request.getSpecRd() != null) {
            SpecInfo specInfoModel = specDAO.SelectSpecInfoByruid(request.getSpecRd());
            if (specInfoModel != null) {
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByspecGd00(specInfoModel.getGuid());
                if (specVerInfo != null) {
                    stationInfo.setSpecVerGd(specVerInfo.getGuid());
                }
            }
        }
        //打印机标识
        if (request.getPrintRd() != null) {
            PrinterInfo printerInfo = printerDAO.SelectByRuid(request.getPrintRd());
            if (printerInfo != null) {
                stationInfo.setPinterGd(printerInfo.getGuid());
            }
        }

        //打印模板标识
        if (request.getPrintTRd() != null) {
            PrintTInfo printTInfo = printTDAO.SelectPrintTInfo(request.getPrintTRd());
            if (printTInfo != null) {
                stationInfo.setPrintTGd(printTInfo.getGuid());
            }
        }
        //打印份数
        if (request.getPrintCopy() != null) {
            stationInfo.setPrintCopy(request.getPrintCopy());
        } else {
            stationInfo.setPrintCopy(1);
        }


        //执行实体类型
        if (request.getExDetail() != null || request.getExDetail().size() > 0) {
            int s = exStationDtlDao.delExStationDtlInfoByStationGuid(stationInfo.getGuid());
            //添加执行实体信息
            SaveOperateModel(request, stationInfo);
        }
        stationInfo.setExecType(request.getExecType());
        //状态
        stationInfo.setStatus(request.getStatus());
        //进站
        stationInfo.setStart(oStation.getStart());
        //上机
        stationInfo.setUp(oStation.getUp());
        //下机
        stationInfo.setDown(oStation.getDown());
        //出站
        stationInfo.setEnd(oStation.getEnd());
        //载具解绑
        stationInfo.setCarrierUnBind(oStation.getCarrierUnBind());
        //打印标签
        stationInfo.setPrintLabel(oStation.getPrintLabel());
        //作业载体
        stationInfo.setCarrierType(oStation.getCarrierType());
        stationInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        stationInfo.setLastModifyTime(new Date());
        stationInfo.setRemark(request.getRemark());

        stationDao.SaveStationInfo(stationInfo);
        stationInfo = stationDao.SelectByRuId(request.getStationRd());

        //修改上下料信息
        //删除上下料信息
        int u = stationUDMDAO.deleteStationUDMInfo(stationInfo.getGuid());

        //添加上下料配置信息
        addStationUDMInfo(request, stationInfo);

        //修改设备集成
        //删除设备集成信息
        int si = stationIntegratedDAO.delectStationIntegratedInfo(stationInfo.getGuid());
        //添加设备集成信息
        addStationIntegratedInfo(request, stationInfo);
        //删除警报提示信息
        int a = stationAlarmDAO.deleteStationAlarmInfo(stationInfo.getGuid());
        //删除指定警报人员信息
        int ar = stationAlarmRevDAO.deleteStationAlarmRevInfo(stationInfo.getGuid());
        //添加警报信息
        addStationAlarmInfo(request, stationInfo);

        //删除接口集成
        stationApiDAO.DelAll(stationInfo.getGuid());
        //添加接口集成
        addStationAPI(request, stationInfo);

        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //获取工位列表信息2
    @Override
    public GetAllStationTInfoRes GetAllStationTInfo(GetAllStationTInfoReqBD00 getAllStationTInfoReqBD00) {
        GetAllStationTInfoRes getAllStationTInfoRes = new GetAllStationTInfoRes();
        GetAllStationTInfoResB getAllStationTInfoResB = new GetAllStationTInfoResB();
        List<GetAllStationTInfoResD> getAllStationTInfoResD = new ArrayList<GetAllStationTInfoResD>();


        if (getAllStationTInfoReqBD00.getLineRd() == null || getAllStationTInfoReqBD00.getLineRd().equals("")) {
            throw new SystemException("", "请选择线体ID");
        }
        if (getAllStationTInfoReqBD00.getDevRd() != null && getAllStationTInfoReqBD00.getUserRd() != null) {

        } else {
            Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(getAllStationTInfoReqBD00.getLineRd());
            String ExecGd = null;
            if (getAllStationTInfoReqBD00.getDevRd() != null) {
                DevInfo devInfo = devDAO.SelectBydevRd(getAllStationTInfoReqBD00.getDevRd());
                ExecGd = devInfo.getGuid();
            }

            if (getAllStationTInfoReqBD00.getUserRd() != null) {
                UserInfo userInfo = userDAO.SelectUserInfoByruid(getAllStationTInfoReqBD00.getUserRd());
                ExecGd = userInfo.getGuid();
            }
            List<StationInfo> stationInfo = stationDao.SelectStationTInfo(lineinfo.getGuid());
            if (stationInfo != null) {
                for (StationInfo s : stationInfo) {
                    GetAllStationTInfoResD data = new GetAllStationTInfoResD();
                    //查询执行实体明细
                    List<ExStationDtlInfo> exStationDtlInfos = exStationDtlDao.SelectExStationDtlInfo(s.getGuid(), ExecGd);
                    data.setLineRd(lineinfo.getRuid());
                    data.setLineName(lineinfo.getLineName());
                    //查询工序信息
                    SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(s.getSpecVerGd());
                    data.setSpecVerRd(specVerInfo.getRuid());
                    data.setSpecName(specVerInfo.getSpecName());
                    data.setStationRd(s.getRuid());
                    data.setStationName(s.getStationName());
                    List<GetAllStationTInfoResD.ExecList> ExecList = new ArrayList<>();
                    if (exStationDtlInfos == null || exStationDtlInfos.size() == 0) {

                    } else {

                        for (ExStationDtlInfo e : exStationDtlInfos) {
                            GetAllStationTInfoResD.ExecList execList = new GetAllStationTInfoResD.ExecList();
                            //根据 设备Gd/用户Gd查询关联信息
                            DevInfo d = devDAO.SelectByguid(e.getExecGd());
                            if (d != null) {
                                execList.setExecRd(d.getRuid());
                                execList.setExecName(d.getDevName());
                            } else {
                                UserInfo u = userDAO.SelectUserRd(e.getExecGd());
                                execList.setExecRd(u.getRuid());
                                execList.setExecName(u.getUserName());
                            }
                            ExecList.add(execList);
                        }
                        data.setExecList(ExecList);
                        getAllStationTInfoResD.add(data);

                    }
                }


            }
        }


        getAllStationTInfoResB.setData(getAllStationTInfoResD);
        getAllStationTInfoRes.setBody(getAllStationTInfoResB);

        return getAllStationTInfoRes;
    }

    @Override
    public BaseRes AddSaveStationInfo03(SaveStationInfoRes reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        Integer SStationRd = reqBD00.getSStationRd();
        Integer ExexRd = reqBD00.getExexRd();
        Integer TStationRd = reqBD00.getTStationRd();

        if (SStationRd == null || SStationRd.equals("")) {
            throw new SystemException("", "源工位ID不能为空");
        }
        if (ExexRd == null || ExexRd.equals("")) {
            throw new SystemException("", "执行实体ID不能为空");
        }
        if (TStationRd == null || TStationRd.equals("")) {
            throw new SystemException("", "目标工位ID不能为空");
        }
        //查询原工位信息
        StationInfo stationInfo = stationDao.SelectByRuId(SStationRd);
        //根据 原工位ID和执行实体ID查询 执行实体明细信息
        ExStationDtlInfo exStationDtlInfo = new ExStationDtlInfo();
        String ExexGd = null;
        if (stationInfo.getExecType().equals("00")) {//设备
            DevInfo devInfo = devDAO.SelectBydevRd(ExexRd);
            ExexGd = devInfo.getGuid();
            if (ExexGd == null) {
                throw new SystemException("", "设备不存在");
            }
            exStationDtlInfo = exStationDtlDao.SelectSIDEID(stationInfo.getGuid(), ExexGd);
        } else if (stationInfo.getExecType().equals("01")) {//产线作业人
            UserInfo userInfo = userDAO.SelectUserInfoByruid(ExexRd);
            ExexGd = userInfo.getGuid();
            if (ExexGd == null) {
                throw new SystemException("", "产线作业人不存在");
            }
            exStationDtlInfo = exStationDtlDao.SelectSIDEID(stationInfo.getGuid(), ExexGd);
        }

        if (exStationDtlInfo == null) {
            throw new SystemException("", "原工位无此执行实体信息");
        }

        //查询原目标信息
        StationInfo TstationInfo = stationDao.SelectByRuId(TStationRd);
        //根据 目标工位ID和执行实体ID查询 执行实体明细信息
        if (!stationInfo.getExecType().equals(TstationInfo.getExecType())) {
            throw new SystemException("", "原工位和目标工位执行实体类型不匹配,无法操作!");
        }

        ExStationDtlInfo TexStationDtlInfo = exStationDtlDao.SelectSIDEID(TstationInfo.getGuid(), ExexGd);


        if (TexStationDtlInfo != null) {
            throw new SystemException("", "目标工位已存在此执行实体信息");
        }
        //修改执行实体信息
        String userName = CommonUtils.readUser().getUserName();
        int i = exStationDtlDao.UpdataStationGd(TstationInfo.getGuid(), ExexGd, reqBD00.getRemark(), new Date(), userName);

        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;

    }

    //复制工位
    @Override
    public BaseRes AddSaveStationInfo04(SaveStationInfoRes reqBD00) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (reqBD00.getSLineRd() == reqBD00.getTLineRd()) {
            throw new SystemException("EEEE", "拷贝线体和目标线体不能一样!");
        }
        //s拷贝线体 t目标线体
        //删除原有目标线体所以工位s信息，然后根据拷贝线体查询出的工位信息做新增操作
        Lineinfo tLineinfo = lineDao.SelectLineInfoBygruid(reqBD00.getTLineRd());
        Lineinfo sLineinfo = lineDao.SelectLineInfoBygruid(reqBD00.getSLineRd());
        List<StationInfo> stationInfoList = stationDao.SelectStationTInfo(sLineinfo.getGuid());
        if (stationInfoList == null || stationInfoList.size() == 0) {
            throw new SystemException("EEEE", "拷贝线体里面暂无工位信息!");
        }
        stationDao.delStationInfoByLineGd(tLineinfo.getGuid());
        for (StationInfo model : stationInfoList) {
            List<StationUDMInfo> stationUDMInfoList = stationUDMDAO.seleceStationGd(model.getGuid());
            model.setGuid(CommonUtils.getRandomNumber());
            model.setLineGd(tLineinfo.getGuid());
            model.setCreateTime(new Date());
            model.setCreator(CommonUtils.readUser().getRealName());
            stationDao.InsertStationInfo(model);
            for (StationUDMInfo udmodel : stationUDMInfoList) {
                udmodel.setGuid(CommonUtils.getRandomNumber());
                udmodel.setStationGd(model.getGuid());
                udmodel.setCreateTime(new Date());
                udmodel.setCreator(CommonUtils.readUser().getRealName());
                stationUDMDAO.insertStationUDMInfo(udmodel);
            }
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //添加执行实体信息
    public void SaveOperateModel(SaveStationInfoRes request, StationInfo stationInfo) {
        List<DevGpDtlInfo> verifyList = new ArrayList<>();
        SaveStationInfoRes.OStation oStation = request.getOStation();
        for (SaveStationInfoRes.ExDetail model : request.getExDetail()) {
            ExStationDtlInfo exDetail = new ExStationDtlInfo();
            exDetail.setGuid(CommonUtils.getRandomNumber());
            exDetail.setCreateTime(new Date());
            exDetail.setCreator(CommonUtils.readUser().getRealName());
            exDetail.setLastModifyMan(CommonUtils.readUser().getRealName());
            exDetail.setLastModifyTime(new Date());
            exDetail.setStationGd(stationInfo.getGuid());
            exDetail.setRemark(request.getRemark());

            if (request.getExecType().equals("00")) {//设备信息
                exDetail.setExecType("00");

                //设备组信息
                if (model.getExecGRd() != null) {
                    DevGpInfo devGpInfo = devGpDAO.SelectDevGpById(model.getExecGRd());
                    if (devGpInfo != null) {
                        exDetail.setExecGd("");
                        exDetail.setExecGGd(devGpInfo.getGuid());
                        //  exStationDtlDao.InsertExStationDtlInfo(exDetail);
                        //输出设备组里面所有的设备信息
                        List<DevGpDtlInfo> devGpDtlInfoList = devGpDtlDAO.SelectByguid(devGpInfo.getGuid());
                        if (devGpDtlInfoList.size() > 0) {
                            verifyList.addAll(devGpDtlInfoList);
                            for (DevGpDtlInfo devGpDModel : devGpDtlInfoList) {
                                DevInfo devInfoExDetail = devDAO.SelectByguid(devGpDModel.getDevGd());
                                if (devInfoExDetail != null) {
                                    List<ExStationDtlInfo> exStationDtlInfoList = exStationDtlDao.SelectAllByExecGd(devInfoExDetail.getGuid());
                                    if (exStationDtlInfoList.size() > 0) {
                                        throw new SystemException("EEEE", devInfoExDetail.getDevName() + "设备信息重复！");
                                    }
                                    exDetail.setExecGd(devInfoExDetail.getGuid());
                                    //暂用工位明细ID字段代替设备明细字段以便下方判断使用
                                    exDetail.setRuid(devInfoExDetail.getRuid());
                                    exDetail.setExecGGd(devGpInfo.getGuid());
                                    //定义校验list
                                    List<DevGpDtlInfo> verifyList1 = new ArrayList<>();
                                    for (DevGpDtlInfo exmodel : verifyList) {
                                        if (exmodel.getDevGd().equals(devInfoExDetail.getGuid())) {
                                            verifyList1.add(exmodel);
                                        }
                                    }
                                    //提示报错信息
                                    if (verifyList1.size() > 1) {
                                        DevGpInfo devGpInfo1 = devGpDAO.SelectByguid(verifyList1.get(0).getDevGpGd());
                                        DevGpInfo devGpInfo2 = devGpDAO.SelectByguid(verifyList1.get(1).getDevGpGd());
                                        DevInfo devInfo = devDAO.SelectByguid(verifyList1.get(0).getDevGd());
                                        throw new SystemException("EEEE", devInfo.getDevName() + "同时包含在【" + devGpInfo1.getDevGpName() + "】和【" + devGpInfo2.getDevGpName() + "】设备组下面，请前往修改一个！");
                                    }
                                    Boolean isTrue = true;
                                    for (SaveStationInfoRes.ExDetail modelVerfy : request.getExDetail()) {
                                        if (modelVerfy.getExecRd() != null) {
                                            if (modelVerfy.getExecRd() == exDetail.getRuid()) {
                                                isTrue = false;
                                                break;
                                            }

                                        }
                                    }
                                    if (isTrue) {
                                        exStationDtlDao.InsertExStationDtlInfo(exDetail);
                                    }
                                }
                            }
                        } else {
                            throw new SystemException("EEEE", devGpInfo.getDevGpName() + "中没有设备信息，请先去添加设备信息!");
                        }
                    } else {
                        throw new SystemException("EEEE", "设备组信息系统未找到");
                    }
                }

                //设备信息
                if (model.getExecRd() != null) {
                    DevInfo devInfoExDetail = devDAO.SelectBydevRd(model.getExecRd());
                    if (devInfoExDetail != null) {
                        List<ExStationDtlInfo> exStationDtlInfoList = exStationDtlDao.SelectAllByExecGd(devInfoExDetail.getGuid());
                        if (exStationDtlInfoList.size() > 0) {
                            throw new SystemException("EEEE", devInfoExDetail.getDevName() + "设备信息重复！");
                        }
                        exDetail.setExecGd(devInfoExDetail.getGuid());
                        exStationDtlDao.InsertExStationDtlInfo(exDetail);
                    }
                }
            } else if (request.getExecType().equals("01")) { //用户信息
                exDetail.setExecType("01");
                UserInfo userInfo = userDAO.SelectUserInfoByruid(model.getExecRd());
                if (userInfo != null) {
                    List<ExStationDtlInfo> exStationDtlInfoList = exStationDtlDao.SelectAllByExecGdByUSer(userInfo.getGuid());
                    if (exStationDtlInfoList.size() > 0) {
                        throw new SystemException("EEEE", "产线作业人员不允许出现重复");
                    }
                    exDetail.setExecGd(userInfo.getGuid());
                    exStationDtlDao.InsertExStationDtlInfo(exDetail);
                }
            } else if (request.getExecType().equals("02")) { //关联页面菜单
                exDetail.setExecType("02");
                ResInfo resInfo = resDAO.SelectResByRuid(model.getExecRd());
                if (resInfo != null) {
                    List<ExStationDtlInfo> exStationDtlInfoList = exStationDtlDao.SelectAllByResGdAndLineGd(resInfo.getGuid(), stationInfo.getLineGd());
                    if (exStationDtlInfoList.size() > 0) {
                        StationInfo stationInfoRes = stationDao.SelectByGuId(exStationDtlInfoList.get(0).getStationGd());
                        throw new SystemException("EEEE", "同一线体下只能关联一个菜单,当前所选择菜单【" + resInfo.getResName() + "】已经关联到【" + stationInfoRes.getStationName() + "】！");
                    }
                    exDetail.setExecGd(resInfo.getGuid());
                    exStationDtlDao.InsertExStationDtlInfo(exDetail);
                }
            } else {
                throw new SystemException("EEEE", "保存失败，参数有误");
            }
        }
    }

    //添加生产设备信息
    public void addStatuonDev(SaveStationInfoRes request, StationInfo stationInfo) {
        //生产设备标识
        List<SaveStationInfoRes.AssDevInfo> AssDevInfos = request.getAssDevInfo();
        if (AssDevInfos != null && AssDevInfos.size() > 0) {
            StationProdDevInfo stationProdDevInfo = new StationProdDevInfo();
            stationProdDevInfo.setStationGd(stationInfo.getGuid());
            stationProdDevInfo.setCreator(CommonUtils.readUser().getRealName());
            stationProdDevInfo.setCreateTime(new Date());
            for (SaveStationInfoRes.AssDevInfo assDevInfo : AssDevInfos) {
                DevInfo devInfoModel = devDAO.SelectBydevRd(assDevInfo.getDevRd());
                stationProdDevInfo.setGuid(CommonUtils.getRandomNumber());

                if (devInfoModel != null) {
                    stationProdDevInfo.setAssDevGd(devInfoModel.getGuid());
                    stationProdDevDAO.insertByStation(stationProdDevInfo);
                }
            }
        }
    }

    //添加上下料配置
    public void addStationUDMInfo(SaveStationInfoRes request, StationInfo stationInfo) {
        List<SaveStationInfoRes.UDM> udms = request.getUDM();
        if (udms.size() > 0) {
            int i = 1;
            for (SaveStationInfoRes.UDM udm : udms) {
                if (udm.getUDMType() == null) {
                    throw new SystemException("", "请选择上下料类型");
                }
                if (udm.getUDMCarrierType() == null) {
                    throw new SystemException("", "请选择上下料载体类型");
                }
                StationUDMInfo stationUDMInfo = new StationUDMInfo();
                stationUDMInfo.setGuid(CommonUtils.getRandomNumber());
                stationUDMInfo.setStationGd(stationInfo.getGuid());
                stationUDMInfo.setUDMType(udm.getUDMType());
                if (udm.getDMArea() != null) {
                    stationUDMInfo.setDMArea(udm.getDMArea());
                }

                stationUDMInfo.setUDMCarrierType(udm.getUDMCarrierType());
                if (udm.getUDMCarrierType().equals("01")) {
                    stationUDMInfo.setDMArea(udm.getDMArea());
                }
                stationUDMInfo.setUDMIndex(i);
                stationUDMInfo.setCreator(CommonUtils.readUser().getRealName());
                stationUDMInfo.setCreateTime(new Date());
                //插入上下料信息
                int s = stationUDMDAO.insertStationUDMInfo(stationUDMInfo);
                i++;
            }

        }
    }

    //添加设备集成信息
    public void addStationIntegratedInfo(SaveStationInfoRes request, StationInfo stationInfo) {
        List<SaveStationInfoRes.Integrated> integrateds = request.getIntegrated();
        if (integrateds.size() > 0) {
            for (SaveStationInfoRes.Integrated integrated : integrateds) {
                if (integrated.getDevRd() == null) {
                    throw new SystemException("", "设备ID不能为空!");
                }
                if (integrated.getDevMapRd() == null) {
                    throw new SystemException("", "映射设备ID不能为空!");
                }
                if (integrated.getTriggerType() == null) {
                    throw new SystemException("", "触发点不能为空!");
                }
                if (integrated.getCmdText() == null) {
                    throw new SystemException("", "执行指令不能为空!");
                }
                if (integrated.getCmdType() == null) {
                    throw new SystemException("", "指令类型不能为空!");
                }
                if (integrated.getCmdVal() == null) {
                    throw new SystemException("", "指令值不能为空!");
                }
                StationIntegratedInfo stationIntegratedInfo = new StationIntegratedInfo();
                stationIntegratedInfo.setGuid(CommonUtils.getRandomNumber());
                //添加工位标识
                stationIntegratedInfo.setStationGd(stationInfo.getGuid());
                //查询设备信息
                DevInfo devInfo = devDAO.SelectBydevRd(integrated.getDevRd());
                //设备标识
                stationIntegratedInfo.setDevGd(devInfo.getGuid());
                //查询映射设备信息
                DevMapInfo devMapInfo = devMapInfoDAO.SelectRuidDevMap(integrated.getDevMapRd());
                //映射设备标识
                stationIntegratedInfo.setDevMapGd(devMapInfo.getGuid());
                //触发点
                stationIntegratedInfo.setTriggerType(integrated.getTriggerType());
                //执行指令
                stationIntegratedInfo.setCmdText(integrated.getCmdText());
                //指令类型
                stationIntegratedInfo.setCmdType(integrated.getCmdType());
                //指令值
                stationIntegratedInfo.setCmdVal(integrated.getCmdVal());
                stationIntegratedInfo.setCreator(CommonUtils.readUser().getRealName());
                stationIntegratedInfo.setCreateTime(new Date());
                //插入设备集成信息
                stationIntegratedDAO.insertStationIntegratedInfo(stationIntegratedInfo);
            }
        }
    }

    //添加警报信息
    public void addStationAlarmInfo(SaveStationInfoRes request, StationInfo stationInfo) {
        List<SaveStationInfoRes.Alarm> alarms = request.getAlarm();
        if (alarms.size() > 0) {
            for (SaveStationInfoRes.Alarm alarm : alarms) {
                if (alarm.getExcepType() == null) {
                    throw new SystemException("", "异常行为不能为空");
                }
                if (alarm.getAlarmType() == null) {
                    throw new SystemException("", "警报方式不能为空");
                }
                if (alarm.getAlarmRev() == null) {
                    throw new SystemException("", "警报接收者不能为空");
                }
                StationAlarmInfo stationAlarmInfo = new StationAlarmInfo();
                stationAlarmInfo.setGuid(CommonUtils.getRandomNumber());
                stationAlarmInfo.setStationGd(stationInfo.getGuid());
                //异常行为
                stationAlarmInfo.setExcepType(alarm.getExcepType());
                //警报方式
                stationAlarmInfo.setAlarmType(alarm.getAlarmType());
                //警报接受方式
                stationAlarmInfo.setAlarmRev(alarm.getAlarmRev());
                //判断是否警报接受是否指定
                if (alarm.getAlarmRev().equals("01")) {
                    //添加指定接受者
                    List<SaveStationInfoRes.AlarmRevDtl> alarmRevDtls = alarm.getAlarmRevDtl();
                    if (alarmRevDtls == null || alarmRevDtls.size() <= 0) {
                        throw new SystemException("", "请选择指定人员信息!");
                    }
                    for (SaveStationInfoRes.AlarmRevDtl alarmRevDtl : alarmRevDtls) {
                        if (alarmRevDtl.getUserRd() == null) {
                            throw new SystemException("", "请选择警报接收人员");
                        }
                        StationAlarmRevInfo stationAlarmRevInfo = new StationAlarmRevInfo();
                        stationAlarmRevInfo.setGuid(CommonUtils.getRandomNumber());
                        stationAlarmRevInfo.setStationGd(stationInfo.getGuid());
                        stationAlarmRevInfo.setStationAlarmGd(stationAlarmInfo.getGuid());
                        //通过用户ID查询用户信息
                        UserInfo userInfo = userDAO.SelectUserInfoByruid(alarmRevDtl.getUserRd());
                        stationAlarmRevInfo.setUserGd(userInfo.getGuid());
                        stationAlarmRevInfo.setCreator(CommonUtils.readUser().getRealName());
                        stationAlarmRevInfo.setCreateTime(new Date());
                        //插入警报指定者信息
                        int ar = stationAlarmRevDAO.insertStationAlarmRevInfo(stationAlarmRevInfo);

                    }

                }
                stationAlarmInfo.setCreator(CommonUtils.readUser().getRealName());
                stationAlarmInfo.setCreateTime(new Date());
                //插入警报提示信息
                int a = stationAlarmDAO.insertStationAlarmInfo(stationAlarmInfo);

            }
        }
    }

    //添加接口集成信息
    public void addStationAPI(SaveStationInfoRes request, StationInfo stationInfo) {
        List<SaveStationInfoRes.StationAPI> StationAPI = request.getStationAPI();
        if (StationAPI.size() > 0) {
            for (SaveStationInfoRes.StationAPI s : StationAPI) {
                StationApiInfo stationApiInfo = new StationApiInfo();
                stationApiInfo.setGuid(CommonUtils.getRandomNumber());
                stationApiInfo.setStationGd(stationInfo.getGuid());
                stationApiInfo.setAPIUrl(s.getAPIUrl());
                stationApiInfo.setTriggerType(s.getTriggerType());
                stationApiInfo.setSysVal(s.getSysVal());
                stationApiInfo.setCreator(CommonUtils.readUser().getRealName());
                stationApiInfo.setCreateTime(new Date());
                stationApiInfo.setRemark(request.getRemark());

                //插入接口集成信息
                stationApiDAO.InserBy(stationApiInfo);

            }

        }

    }

}
