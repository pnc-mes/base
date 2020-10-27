package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllDevFInfo.GetAllDevFInfoRes;
import pnc.mesadmin.dto.GetAllDevFInfo.GetAllDevFInfoResB;
import pnc.mesadmin.dto.GetAllDevFInfo.GetAllDevFInfoResD;
import pnc.mesadmin.dto.GetDevFInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveDevFInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DeviceFIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备家族信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class DeviceFService implements DeviceFIService{

    @Resource
    private DevFamilyDAO devFamilyDAO;

    @Resource
    private BaseIService baseIService;
    @Resource
    private DevCheckPlanDAO devCheckPlanDAO;

    @Resource
    private ToolFamilyDAO toolFamilyDAO;

    @Resource
    private CyclePlanDAO cyclePlanDAO;

    @Resource
    private FrePlanDAO frePlanDAO;

    @Resource
    private CheckPlanDAO checkPlanDAO;

    @Resource
    private DevFamilyCheckPlanDAO devFamilyCheckPlanDAO;

    @Resource
    private DevFamilyPMDAO devFamilyPMDAO;



    //查询设备家族列表信息
    public GetAllDevFInfoRes QALLGetAllDevFInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllDevFInfoRes objEGetAllDevFInfoRes=new GetAllDevFInfoRes();
        GetAllDevFInfoResB objEGetAllDevFInfoResB=new GetAllDevFInfoResB();
        List<GetAllDevFInfoResD> objEGetAllDevFInfoResDs=new ArrayList<GetAllDevFInfoResD>();

        List<DevFamilyInfo> objEDevFamilyInfo= baseIService.QALLBaseInfo(DevFamilyDAO.class, "SelectAllDevFamilyInfo",
                argInitDataFields, argPageInfo);


        if(objEDevFamilyInfo!=null||objEDevFamilyInfo.size()>0) {

            for (DevFamilyInfo obj : objEDevFamilyInfo) {
                GetAllDevFInfoResD objGetAllDevFInfoResD = new GetAllDevFInfoResD();
                objGetAllDevFInfoResD.setDevFRd(obj.getRuid());
                objGetAllDevFInfoResD.setDevFName(obj.getFamilyName());

                objEGetAllDevFInfoResDs.add(objGetAllDevFInfoResD);
            }
        }
        objEGetAllDevFInfoResB.setData(objEGetAllDevFInfoResDs);
        objEGetAllDevFInfoRes.setBody(objEGetAllDevFInfoResB);

        return objEGetAllDevFInfoRes;
    }

    //查询设备家族信息
    public GetDevFInfoRes GetGetDevFInfoRes(GetDevFInfoReqBD00 argGetDevFInfoReqBD00) {
        GetDevFInfoRes objEGetDevFInfoRes=new GetDevFInfoRes();
        GetDevFInfoResB objEGetDevFInfoResB=new GetDevFInfoResB();
        GetDevFInfoResD objEGetDevFInfoResD=new GetDevFInfoResD();

        //查询设备家族信息
        DevFamilyInfo objDevFamilyInfo= devFamilyDAO.SelectBydevFRd(argGetDevFInfoReqBD00.getDevFRd());

        if(objDevFamilyInfo!=null) {
            objEGetDevFInfoResD.setDevFRd(objDevFamilyInfo.getRuid());
            objEGetDevFInfoResD.setDevFName(objDevFamilyInfo.getFamilyName());
            objEGetDevFInfoResD.setCreator(objDevFamilyInfo.getCreator());
            objEGetDevFInfoResD.setCreateTime(DateUtil.format(objDevFamilyInfo.getCreateTime()));
            objEGetDevFInfoResD.setLastModifyMan(objDevFamilyInfo.getLastModifyMan());
            objEGetDevFInfoResD.setLastModifyTime(DateUtil.format(objDevFamilyInfo.getLastModifyTime()));
            objEGetDevFInfoResD.setRemark(objDevFamilyInfo.getRemark());


            //保养计划
            List<GetDevFInfoResDPM>  getDevFInfoResDPMS=new ArrayList<GetDevFInfoResDPM>();
            List<DevFamilyPMInfo> devFamilyPMInfos=devFamilyPMDAO.selectDevFamilyPMInfoByDevFamilyGd(objDevFamilyInfo.getGuid());
            if(devFamilyPMInfos!=null&&devFamilyPMInfos.size()>0){
                for(DevFamilyPMInfo devFamilyPMInfo:devFamilyPMInfos){
                    GetDevFInfoResDPM getDevInfoResDPM=new GetDevFInfoResDPM();

                    if(devFamilyPMInfo.getpMType().equals("00")){
                        CyclePlanInfo cyclePlanInfo =cyclePlanDAO.selectCyclePlanInfoByGuid(devFamilyPMInfo.getpMGd());
                        getDevInfoResDPM.setPMType(devFamilyPMInfo.getpMType());
                        getDevInfoResDPM.setPMName(cyclePlanInfo.getCyclePlanName());
                        getDevInfoResDPM.setPMRd(cyclePlanInfo.getRuid());
                    }
                    if(devFamilyPMInfo.getpMType().equals("01")){
                        FrePlanInfo frePlanInfo =frePlanDAO.selectFrePlanByGuid(devFamilyPMInfo.getpMGd());
                        getDevInfoResDPM.setPMType(devFamilyPMInfo.getpMType());
                        getDevInfoResDPM.setPMName(frePlanInfo.getFrePlanName());
                        getDevInfoResDPM.setPMRd(frePlanInfo.getRuid());
                    }


                    getDevFInfoResDPMS.add(getDevInfoResDPM);
                }
                objEGetDevFInfoResD.setPMInfo(getDevFInfoResDPMS);
            }

            //点检计划
            List<GetDevFInfoResDCheckPlan> getDevInfoResDCheckPlans=new ArrayList<GetDevFInfoResDCheckPlan>();
            List<DevFamilyCheckPlanInfo> devFamilyCheckPlanInfos=devFamilyCheckPlanDAO.selectDevFamilyCheckPlanInfoByDevFamilyGd(objDevFamilyInfo.getGuid());
            if(devFamilyCheckPlanInfos!=null&&devFamilyCheckPlanInfos.size()>0){
                for(DevFamilyCheckPlanInfo devCheckPlanInfo:devFamilyCheckPlanInfos){
                    GetDevFInfoResDCheckPlan getDevInfoResDCheckPlan=new GetDevFInfoResDCheckPlan();
                    CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByGuid(devCheckPlanInfo.getCheckPlanGd());
                    if(checkPlanInfo!=null){
                        getDevInfoResDCheckPlan.setCheckPlanRd(checkPlanInfo.getRuid());
                        getDevInfoResDCheckPlan.setCheckPlanName(checkPlanInfo.getCheckPlanName());
                    }
                    getDevInfoResDCheckPlans.add(getDevInfoResDCheckPlan);
                }
                objEGetDevFInfoResD.setCheckPlanInfo(getDevInfoResDCheckPlans);
            }
        }

        objEGetDevFInfoResB.setData(objEGetDevFInfoResD);
        objEGetDevFInfoRes.setBody(objEGetDevFInfoResB);
        return objEGetDevFInfoRes;
    }

    //新增设备家族信息
    public SaveDevFInfoRes AddSaveDevFInfoRes(SaveDevFInfoReqBD00 argSaveDevFInfoReqBD00) {
        SaveDevFInfoRes objESaveDevFInfoRes=new SaveDevFInfoRes();
        SaveDevFInfoResB objESaveDevFInfoResB=new SaveDevFInfoResB();

        DevFamilyInfo objEDevFamilyInfo=new DevFamilyInfo();

        objEDevFamilyInfo.setGuid(CommonUtils.getRandomNumber());
        List<DevFamilyInfo> objEDevFamilyInfos= devFamilyDAO.SelectAllDevFamilyInfo();
        for(DevFamilyInfo obj:objEDevFamilyInfos){
            if(argSaveDevFInfoReqBD00.getDevFName().equals(obj.getFamilyName())){
                throw  new SystemException("MG0006F","设备家族名称已存在");
            }
        }

        if(argSaveDevFInfoReqBD00.getDevFName().equals("")){
            throw  new SystemException("MG0003F","设备家族名称不能为空");
        }

        objEDevFamilyInfo.setFamilyName(argSaveDevFInfoReqBD00.getDevFName());
        objEDevFamilyInfo.setRemark(argSaveDevFInfoReqBD00.getRemark());
        objEDevFamilyInfo.setCreateTime(new Date());
        objEDevFamilyInfo.setCreator(CommonUtils.readUser().getRealName());

        devFamilyDAO.InsertDevFamilyInfo(objEDevFamilyInfo);

        //设备保养
        if(argSaveDevFInfoReqBD00.getPMInfo().size()>0){
            for(SaveDevFInfoReqBD00PM saveDevFInfoReqBD00PM:argSaveDevFInfoReqBD00.getPMInfo()){
                DevFamilyPMInfo devFamilyPMInfo=new DevFamilyPMInfo();
                devFamilyPMInfo.setGuid(CommonUtils.getRandomNumber());
                devFamilyPMInfo.setDevFamilyGd(objEDevFamilyInfo.getGuid());
                if("00".equals(saveDevFInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveDevFInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        devFamilyPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    devFamilyPMInfo.setpMType(saveDevFInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveDevFInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveDevFInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        devFamilyPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    devFamilyPMInfo.setpMType(saveDevFInfoReqBD00PM.getPMType());
                }
                devFamilyPMDAO.insertDevCheckPlanInfo(devFamilyPMInfo);
            }
        }

        //点检计划
        if(argSaveDevFInfoReqBD00.getCheckPlanInfo().size()>0){
            for(SaveDevFInfoReqBD00CheckPlan saveDevFInfoReqBD00CheckPlan:argSaveDevFInfoReqBD00.getCheckPlanInfo()){
                DevFamilyCheckPlanInfo devFamilyCheckPlanInfo=new DevFamilyCheckPlanInfo();
                devFamilyCheckPlanInfo.setGuid(CommonUtils.getRandomNumber());
                devFamilyCheckPlanInfo.setDevFamilyGd(objEDevFamilyInfo.getGuid());
                CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(saveDevFInfoReqBD00CheckPlan.getCheckPlanRd());
                if(checkPlanInfo!=null){
                    devFamilyCheckPlanInfo.setCheckPlanGd(checkPlanInfo.getGuid());
                }
                devFamilyCheckPlanDAO.insertDevFamilyCheckPlanInfo(devFamilyCheckPlanInfo);
            }
        }


        objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
        return objESaveDevFInfoRes;
    }

    //删除设备家族信息
    public SaveDevFInfoRes RmSaveDevFInfoRes(SaveDevFInfoReqBD01 argSaveDevFInfoReqBD01) {
        SaveDevFInfoRes objESaveDevFInfoRes=new SaveDevFInfoRes();
        SaveDevFInfoResB objESaveDevFInfoResB=new SaveDevFInfoResB();
        DevFamilyInfo devFamilyInfo=devFamilyDAO.SelectBydevFRd(argSaveDevFInfoReqBD01.getDevFRd());
        if(devFamilyInfo!=null){
            if(devFamilyDAO.DeleteDevFamilyInfo(devFamilyInfo.getRuid())<=0){
                throw  new SystemException("MG_MES2001013020001F","删除设备家族失败");
            }

            List<DevFamilyPMInfo> devFamilyPMInfos = devFamilyPMDAO.selectDevFamilyPMInfoByDevFamilyGd(devFamilyInfo.getGuid());
            if (devFamilyPMInfos != null && devFamilyPMInfos.size() > 0) {
                for (DevFamilyPMInfo devFamilyPMInfo : devFamilyPMInfos) {
                    if (devFamilyPMDAO.deleteDevCheckPlanInfo(devFamilyPMInfo.getRuid()) <= 0) {
                        throw new SystemException("xx", "删除失败");
                    }
                }
            }
            List<DevFamilyCheckPlanInfo> devFamilyCheckPlanInfos = devFamilyCheckPlanDAO.selectDevFamilyCheckPlanInfoByDevFamilyGd(devFamilyInfo.getGuid());
            if (devFamilyCheckPlanInfos != null && devFamilyCheckPlanInfos.size() > 0) {
                for (DevFamilyCheckPlanInfo devFamilyCheckPlanInfo : devFamilyCheckPlanInfos) {
                    if (devFamilyCheckPlanDAO.deleteDevFamilyCheckPlanInfo(devFamilyCheckPlanInfo.getRuid()) <= 0) {
                        throw new SystemException("xx", "删除失败");
                    }
                }
            }
        }


        objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
        return objESaveDevFInfoRes;
    }

    //更新设备家族信息
    public SaveDevFInfoRes ModSaveDevFInfoRes(SaveDevFInfoReqBD02 argSaveDevFInfoReqBD02) {
        SaveDevFInfoRes objESaveDevFInfoRes=new SaveDevFInfoRes();
        SaveDevFInfoResB objESaveDevFInfoResB=new SaveDevFInfoResB();

        DevFamilyInfo objEDevFamilyInfo=devFamilyDAO.SelectBydevFRd(argSaveDevFInfoReqBD02.getDevFRd());
        objEDevFamilyInfo.setRuid(argSaveDevFInfoReqBD02.getDevFRd());

        //名称不允许重复
        DevFamilyInfo devFamilyInfoName=devFamilyDAO.SelectByfamilyName(argSaveDevFInfoReqBD02.getDevFName());

        if(devFamilyInfoName!=null&&!devFamilyInfoName.getFamilyName().equals(objEDevFamilyInfo.getFamilyName())){
            throw new SystemException("MG_MES3001013030002F","设备家族名称已存在");
        }

        objEDevFamilyInfo.setFamilyName(argSaveDevFInfoReqBD02.getDevFName());
        objEDevFamilyInfo.setRemark(argSaveDevFInfoReqBD02.getRemark());
        objEDevFamilyInfo.setLastModifyTime(new Date());
        objEDevFamilyInfo.setLastModifyMan(CommonUtils.readUser().getRealName());

        List<DevFamilyPMInfo> devFamilyPMInfos = devFamilyPMDAO.selectDevFamilyPMInfoByDevFamilyGd(objEDevFamilyInfo.getGuid());
        if (devFamilyPMInfos != null && devFamilyPMInfos.size() > 0) {
            for (DevFamilyPMInfo devFamilyPMInfo : devFamilyPMInfos) {
                if (devFamilyPMDAO.deleteDevCheckPlanInfo(devFamilyPMInfo.getRuid()) <= 0) {
                    throw new SystemException("xx", "删除失败");
                }
            }
        }
        List<DevFamilyCheckPlanInfo> devFamilyCheckPlanInfos = devFamilyCheckPlanDAO.selectDevFamilyCheckPlanInfoByDevFamilyGd(objEDevFamilyInfo.getGuid());
        if (devFamilyCheckPlanInfos != null && devFamilyCheckPlanInfos.size() > 0) {
            for (DevFamilyCheckPlanInfo devFamilyCheckPlanInfo : devFamilyCheckPlanInfos) {
                if (devFamilyCheckPlanDAO.deleteDevFamilyCheckPlanInfo(devFamilyCheckPlanInfo.getRuid()) <= 0) {
                    throw new SystemException("xx", "删除失败");
                }
            }
        }




        //设备保养
        if(argSaveDevFInfoReqBD02.getPMInfo().size()>0){
            for(SaveDevFInfoReqBD02PM saveDevFInfoReqBD00PM:argSaveDevFInfoReqBD02.getPMInfo()){
                DevFamilyPMInfo devFamilyPMInfo=new DevFamilyPMInfo();
                devFamilyPMInfo.setGuid(CommonUtils.getRandomNumber());
                devFamilyPMInfo.setDevFamilyGd(objEDevFamilyInfo.getGuid());
                if("00".equals(saveDevFInfoReqBD00PM.getPMType())){
                    CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveDevFInfoReqBD00PM.getPMRd());
                    if(cyclePlanInfo!=null){
                        devFamilyPMInfo.setpMGd(cyclePlanInfo.getGuid());
                    }
                    devFamilyPMInfo.setpMType(saveDevFInfoReqBD00PM.getPMType());
                }
                if("01".equals(saveDevFInfoReqBD00PM.getPMType())){
                    FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveDevFInfoReqBD00PM.getPMRd());
                    if(frePlanInfo!=null){
                        devFamilyPMInfo.setpMGd(frePlanInfo.getGuid());
                    }
                    devFamilyPMInfo.setpMType(saveDevFInfoReqBD00PM.getPMType());
                }
                devFamilyPMDAO.insertDevCheckPlanInfo(devFamilyPMInfo);
            }
        }

        //点检计划
        if(argSaveDevFInfoReqBD02.getCheckPlanInfo().size()>0){
            for(SaveDevFInfoReqBD02CheckPlan saveDevFInfoReqBD00CheckPlan:argSaveDevFInfoReqBD02.getCheckPlanInfo()){
                DevFamilyCheckPlanInfo devFamilyCheckPlanInfo=new DevFamilyCheckPlanInfo();
                devFamilyCheckPlanInfo.setGuid(CommonUtils.getRandomNumber());
                devFamilyCheckPlanInfo.setDevFamilyGd(objEDevFamilyInfo.getGuid());
                CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(saveDevFInfoReqBD00CheckPlan.getCheckPlanRd());
                if(checkPlanInfo!=null){
                    devFamilyCheckPlanInfo.setCheckPlanGd(checkPlanInfo.getGuid());
                }
                devFamilyCheckPlanDAO.insertDevFamilyCheckPlanInfo(devFamilyCheckPlanInfo);
            }
        }

















        if(devFamilyDAO.UpdateDevFamilyInfo(objEDevFamilyInfo)<=0){
            throw  new SystemException("MG_MES2001013030002F","更新设备家族信息失败");
        }

        objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
        return objESaveDevFInfoRes;
    }
}
