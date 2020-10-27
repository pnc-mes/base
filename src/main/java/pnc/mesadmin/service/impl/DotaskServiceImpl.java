package pnc.mesadmin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllDotaskInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.DoTaskService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.entity.common.DJSelectInfo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DotaskServiceImpl implements DoTaskService {

    @Resource
    DoPMInfoDAO doPMInfoDAO;
    @Resource
    DoPMDTInfoDAO doPMDTInfoDAO;
    @Resource
    DevGpDAO devGpDAO;
    @Resource
    DevDAO devDAO;
    @Resource
    ToolDao toolDao;
    @Resource
    CarrierDao carrierDao;
    @Resource
    CheckPlanDAO checkPlanDAO;
    @Resource
    DoCheckInfoDAO doCheckInfoDAO;
    @Resource
    DoCheckDtInfoDAO doCheckDtInfoDAO;
    @Resource
    TaskDAO taskDAO;
    @Resource
    TaskDetailDAO taskDetailDAO;
    @Resource
    DevStateDAO devStateDAO;
    @Resource
    DevSMDtDAO devSMDtDAO;

    //获取保养列表信息
    @Override
    public BaseRes GetAllDoTask00(GetAllDotaskRes00 req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllDotaskResB> getAllDotaskRespon = new ArrayList<>();
        DoPMInfo doPMInfo = new DoPMInfo();
        doPMInfo.setPMType(req.getPMType());
        doPMInfo.setPMObjType(req.getPMObjType());
        if (req.getPMObjRd() == null || req.getPMObjType() == null) {
            throw new SystemException("EEEE", "请选择查询条件！");
        }
        if (req.getPMObjType().equals("00")) {
            DevInfo devInfo = devDAO.SelectBydevRd(req.getPMObjRd());
            doPMInfo.setPMObjGd(devInfo.getGuid());
        } else if (req.getPMObjType().equals("01")) {
            ToolInfo toolInfo = toolDao.SelectToolInfoByruid(req.getPMObjRd());
            doPMInfo.setPMObjGd(toolInfo.getGuid());
        } else if (req.getPMObjType().equals("02")) {
            CarrierInfo carrierInfo = carrierDao.SelectCarrierInfoByruid(req.getPMObjRd());
            doPMInfo.setPMObjGd(carrierInfo.getGuid());
        }
        doPMInfo.setStatus(req.getStatus());
        List<DoPMInfo> doPMInfoList = doPMInfoDAO.SelectByDotaskReq(doPMInfo);
        if (doPMInfoList.size() > 0) {
            for (DoPMInfo model : doPMInfoList) {
                GetAllDotaskResB getAllDotaskResB = new GetAllDotaskResB();
                getAllDotaskResB.setDoTaskName(model.getPMName());
                getAllDotaskResB.setDoTaskRd(model.getRuid());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (model.getMaTime() != null) {
                    getAllDotaskResB.setMaTime(format.format(model.getMaTime()));
                } else {
                    getAllDotaskResB.setMaTime("");
                }

                getAllDotaskResB.setStatus(model.getStatus());
                List<DoPMDtlInfo> doPMDtlInfoList = doPMDTInfoDAO.SelectDoPMDtlInfoByDoPMGd(model.getGuid());
                List<GetAllDotaskResB.DoTaskDtlInfo> doTaskDtlInfos = new ArrayList<>();
                if (doPMDtlInfoList.size() > 0) {
                    for (DoPMDtlInfo modelD : doPMDtlInfoList) {
                        GetAllDotaskResB.DoTaskDtlInfo doTaskDtlInfo = new GetAllDotaskResB.DoTaskDtlInfo();
                        doTaskDtlInfo.setDoTaskDtlRd(modelD.getRuid());
                        doTaskDtlInfo.setSureType(modelD.getSureType());
                        if (modelD.getSureType().equals("00")) {
                            doTaskDtlInfo.setExecResult(modelD.getExecResult00());
                        } else if (modelD.getSureType().equals("01")) {
                            doTaskDtlInfo.setExecResult(modelD.getExecResult02());
                        }
                        doTaskDtlInfo.setTaskItemName(modelD.getTaskItemName());
                        doTaskDtlInfos.add(doTaskDtlInfo);
                    }
                    getAllDotaskResB.setDoTaskDtlInfo(doTaskDtlInfos);
                }
                getAllDotaskRespon.add(getAllDotaskResB);
            }
        }
        baseResBody.setData(getAllDotaskRespon);

        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    //获取点检列表信息
    @Override
    public BaseRes GetAllDoTask01(GetAllDotaskRes01 req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        List<GetAllDotaskResB> getAllDotaskResB = new ArrayList<>();
        DoCheckInfo doCheckInfo = new DoCheckInfo();
        if (req.getCheckObjRd() == null) {
            throw new SystemException("EEEE", "请选择查询条件！");
        }
        DevInfo devInfo = devDAO.SelectBydevRd(req.getCheckObjRd());
        doCheckInfo.setCheckObjGd(devInfo.getGuid());
        doCheckInfo.setStatus(req.getStatus());
        List<DoCheckInfo> doCheckInfoList = doCheckInfoDAO.SelectDoCheckInfoBy(doCheckInfo);

        if (doCheckInfoList.size() > 0) {
            for (DoCheckInfo model : doCheckInfoList) {
                GetAllDotaskResB getAllDotaskResB1 = new GetAllDotaskResB();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                getAllDotaskResB1.setMaTime(format.format(model.getMaTime()));
                getAllDotaskResB1.setDoTaskRd(model.getRuid());
                getAllDotaskResB1.setDoTaskName(model.getCheckName());
                getAllDotaskResB1.setStatus(model.getStatus());
                List<DoCheckDtlInfo> doCheckDtlInfos = doCheckDtInfoDAO.SelectDoCheckInfoBy(model.getGuid());
                List<GetAllDotaskResB.DoTaskDtlInfo> doTaskDtlInfos = new ArrayList<>();
                if (doCheckDtlInfos.size() > 0) {
                    for (DoCheckDtlInfo modelD : doCheckDtlInfos) {
                        GetAllDotaskResB.DoTaskDtlInfo doTaskDtlInfo = new GetAllDotaskResB.DoTaskDtlInfo();
                        doTaskDtlInfo.setDoTaskDtlRd(modelD.getRuid());
                        doTaskDtlInfo.setSureType(modelD.getSureType());
                        if (modelD.getSureType().equals("00")) {
                            doTaskDtlInfo.setExecResult(modelD.getExecResult00());
                        } else if (modelD.getSureType().equals("01")) {
                            doTaskDtlInfo.setExecResult(modelD.getExecResult02());
                        }
                        doTaskDtlInfo.setTaskItemName(modelD.getTaskItemName());
                        doTaskDtlInfos.add(doTaskDtlInfo);
                    }
                    getAllDotaskResB1.setDoTaskDtlInfo(doTaskDtlInfos);
                }
                getAllDotaskResB.add(getAllDotaskResB1);
            }
        }
        baseResBody.setData(getAllDotaskResB);
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes SavePMACInfo00(SaveDotaskRes req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        DoPMInfo doPMInfo = new DoPMInfo();
        doPMInfo.setRuid(req.getDoPMRd());
        if (req.getPMDtl().size() > 0) {
            for (SaveDotaskRes.PMDtl model : req.getPMDtl()) {
                DoPMDtlInfo doPMDtlInfo = doPMDTInfoDAO.SelectDoPMDtlInfoByRuid(model.getDoPMDtlRd());
                if (model.getDoPMResult().equals("00") || model.getDoPMResult().equals("01")) {
                    if (!doPMDtlInfo.getSureType().equals("00")) {
                        throw new SystemException("EEEE", "保存失败，类型不匹配！");
                    }
                    doPMDtlInfo.setExecResult00(model.getDoPMResult());
                } else {
                    doPMDtlInfo.setExecResult02(model.getDoPMResult());
                }
                doPMDtlInfo.setLastModifyTime(new Date());
                doPMDtlInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                doPMDTInfoDAO.UpdateDoPMDtlInfoByRuid(doPMDtlInfo);
            }
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes SavePMACInfo01(SaveDoCheckRes req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        DoCheckInfo doCheckInfo = new DoCheckInfo();
        doCheckInfo.setRuid(req.getDoCheckRd());
        if (req.getCheckDtl().size() > 0) {
            for (SaveDoCheckRes.CheckDtl model : req.getCheckDtl()) {
                DoCheckDtlInfo doCheckDtlInfo = doCheckDtInfoDAO.SelectDoCheckInfoByRuid(model.getDoCheckDtlRd());
                if (model.getDoCheckResult().equals("00") || model.getDoCheckResult().equals("01")) {
                    if (!doCheckDtlInfo.getSureType().equals("00")) {
                        throw new SystemException("EEEE", "保存失败，类型不匹配！");
                    }
                    doCheckDtlInfo.setExecResult00(model.getDoCheckResult());
                } else {
                    doCheckDtlInfo.setExecResult02(model.getDoCheckResult());
                }
                doCheckDtlInfo.setLastModifyTime(new Date());
                doCheckDtlInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                doCheckDtInfoDAO.UpdaDoCheckDtlInfoByRuid(doCheckDtlInfo);
            }
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }

    @Override
    public BaseRes SaveCreateTaskInfo(SaveCreateTaskReqest req) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (req.getTaskRd() == null)
            throw new SystemException("EEEE", "参数异常！");
        if (req.getObjRd() == null)
            throw new SystemException("EEEE", "参数异常！");
        if (StringUtils.isBlank(req.getTaskType()))
            throw new SystemException("EEEE", "参数异常！");
        if (StringUtils.isBlank(req.getObjType()))
            throw new SystemException("EEEE", "参数异常！");
        if (StringUtils.isBlank(req.getMaTime()))
            throw new SystemException("EEEE", "参数异常！");
        TaskInfo taskInfo = taskDAO.selectTaskInfoByRuid(req.getTaskRd());
        if (taskInfo == null)
            throw new SystemException("EEEE", "暂无任务信息！");
        List<TaskDetailInfo> taskDetailInfos = taskDetailDAO.selectTaskDetailInfoBytaskGd(taskInfo.getGuid());
        if (taskDetailInfos == null || taskDetailInfos.size() == 0)
            throw new SystemException("EEEE", "暂无任务明细信息！");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String throwName = req.getTaskType() == "00" ? "保养" : "点检"; //定义报错提示名称
        String baseName; //定义对象名称
        String baseGuid; //定义对象名称
        //对象类型 00#设备 01#工具 02#载具
        if (req.getObjType().equals("00")) { //设备
            DevInfo devInfo = devDAO.SelectBydevRd(req.getObjRd());
            if (devInfo == null)
                throw new SystemException("EEEE", "暂无设备信息！");
            /*DevStateInfo devStateInfo = devStateDAO.SelectDevStateInfoByGuid(devInfo.getDevSGd());
            if (devStateInfo == null)
                throw new SystemException("EEEE", "暂无设备状态信息！");
            if (devStateInfo.getStatus().equals("01"))
                throw new SystemException("EEEE", "当前" + devInfo.getDevName() + devStateInfo.getDevSName() + "不可用，不能产生" + throwName + "+的任务！");*/
            baseName = devInfo.getDevName() + sdf.format(new Date());
            baseGuid = devInfo.getGuid();
        } else if (req.getObjType().equals("01")) {//工具
            ToolInfo toolInfo = toolDao.SelectToolInfoByruid(req.getObjRd());
            if (toolInfo == null)
                throw new SystemException("EEEE", "暂无工具信息！");
            baseName = toolInfo.getToolName() + sdf.format(new Date());
            baseGuid = toolInfo.getGuid();
        } else if (req.getObjType().equals("02")) { //载具
            CarrierInfo carrierInfo = carrierDao.SelectCarrierInfoByruid(req.getObjRd());
            if (carrierInfo == null)
                throw new SystemException("EEEE", "暂无载具信息！");
            baseName = carrierInfo.getCarrierName() + sdf.format(new Date());
            baseGuid = carrierInfo.getGuid();
        } else {
            throw new SystemException("EEEE", "参数异常！");
        }
        //新增保养/点检信息
        if (req.getTaskType().equals("00")) { //保养
            DoPMInfo doPMInfo = new DoPMInfo();
            doPMInfo.setGuid(CommonUtils.getRandomNumber());
            doPMInfo.setCreator(CommonUtils.readUser().getRealName());
            doPMInfo.setCreateTime(new Date());
            doPMInfo.setPMName(baseName);
            doPMInfo.setPMObjGd(baseGuid);
            doPMInfo.setPMObjType(req.getObjType());
            doPMInfo.setPMType("00"); //周期性保养
            doPMInfo.setMaTime(DateUtil.format2(req.getMaTime())); //周期性保养
            doPMInfo.setStatus("00");
            for (TaskDetailInfo model : taskDetailInfos) {
                DoPMDtlInfo doPMDtlInfo = new DoPMDtlInfo();
                doPMDtlInfo.setGuid(CommonUtils.getRandomNumber());
                doPMDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                doPMDtlInfo.setCreateTime(new Date());
                doPMDtlInfo.setDoPMGd(doPMInfo.getGuid());
                doPMDtlInfo.setTaskItemName(model.getTaskItemName());
                doPMDtlInfo.setSureType(model.getSureType());
                doPMDTInfoDAO.InsertDoPMDtl(doPMDtlInfo);
            }
            doPMInfoDAO.InsertDoPm(doPMInfo);
        } else if (req.getTaskType().equals("01")) { //点检
            DoCheckInfo doCheckInfo = new DoCheckInfo();
            doCheckInfo.setGuid(CommonUtils.getRandomNumber());
            doCheckInfo.setCreator(CommonUtils.readUser().getRealName());
            doCheckInfo.setCreateTime(new Date());
            doCheckInfo.setCheckObjGd(baseGuid);
            doCheckInfo.setCheckName(baseName);
            doCheckInfo.setMaTime(DateUtil.format2(req.getMaTime()));
            doCheckInfo.setStatus("00");
            for (TaskDetailInfo model : taskDetailInfos) {
                DoCheckDtlInfo doCheckDtlInfo = new DoCheckDtlInfo();
                doCheckDtlInfo.setGuid(CommonUtils.getRandomNumber());
                doCheckDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                doCheckDtlInfo.setCreateTime(new Date());
                doCheckDtlInfo.setDoCheckGd(doCheckInfo.getGuid());
                doCheckDtlInfo.setTaskItemName(model.getTaskItemName());
                doCheckDtlInfo.setSureType(model.getSureType());
                doCheckDtInfoDAO.InsertDoCheckDtlInfo(doCheckDtlInfo);
            }
            doCheckInfoDAO.InsertDoCheckInfo(doCheckInfo);
        } else {
            throw new SystemException("EEEE", "参数异常！");
        }
        baseResBody.setMsgCode("0x00000");
        baseResBody.setMsgDes("成功");
        baseResponse.setBody(baseResBody);
        return baseResponse;
    }


    @Override
    public List<DJSelectInfo> GetAllDJSelectInfo(String checkobjtype, String checkobjgd, String status, String shiftname, String linename, String createtime1, String createtime2) {
        String Guid="";
        if("00".equals(checkobjtype)){
            if(checkobjgd!=null){
                DevInfo devInfo=devDAO.SelectBydevRd(Integer.valueOf(checkobjgd));
                if(devInfo!=null){
                    Guid=devInfo.getGuid();
                }
            }

        }
        else if("01".equals(checkobjtype)){
            if(checkobjgd!=null){
                ToolInfo toolInfo=toolDao.SelectToolInfoByruid(Integer.valueOf(checkobjgd));
                if(toolInfo!=null){
                    Guid=toolInfo.getGuid();
                }
            }

        }else {
            if(checkobjgd!=null){
                CarrierInfo carrierInfo=carrierDao.SelectCarrierInfoByruid(Integer.valueOf(checkobjgd));
                if(carrierInfo!=null){
                    Guid=carrierInfo.getGuid();
                }
            }

        }
        return doCheckInfoDAO.SelectAllDJSelectInfo(checkobjtype,Guid,status,shiftname,linename,createtime1,createtime2);
    }


    @Override
    public List<DJSelectInfo> SelectAllBYSelectInfo(String checkobjtype, String checkobjgd, String status, String shiftname, String linename, String createtime1, String createtime2) {
        String Guid="";
        if("00".equals(checkobjtype)){
            if(checkobjgd!=null){
                DevInfo devInfo=devDAO.SelectBydevRd(Integer.valueOf(checkobjgd));
                if(devInfo!=null){
                    Guid=devInfo.getGuid();
                }
            }

        }
        else if("01".equals(checkobjtype)){
            if(checkobjgd!=null){
                ToolInfo toolInfo=toolDao.SelectToolInfoByruid(Integer.valueOf(checkobjgd));
                if(toolInfo!=null){
                    Guid=toolInfo.getGuid();
                }
            }

        }else {
            if(checkobjgd!=null){
                CarrierInfo carrierInfo=carrierDao.SelectCarrierInfoByruid(Integer.valueOf(checkobjgd));
                if(carrierInfo!=null){
                    Guid=carrierInfo.getGuid();
                }
            }

        }
        return doCheckInfoDAO.SelectAllBYSelectInfo(checkobjtype,Guid,status,shiftname,linename,createtime1,createtime2);
    }
}
