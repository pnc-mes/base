package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllMinTimeWindowInfo.GetAllMinTimeWindowInfoRes;
import pnc.mesadmin.dto.GetAllMinTimeWindowInfo.GetAllMinTimeWindowInfoResB;
import pnc.mesadmin.dto.GetAllMinTimeWindowInfo.GetAllMinTimeWindowInfoResD;
import pnc.mesadmin.dto.GetMinTimedowInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveMinTimedowInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.MinTimeWindowIServie;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 乔帅阳 on 2018/7/30.
 */

@Transactional
@Service
public class MinTimeWindowServie implements MinTimeWindowIServie{

    @Resource
    private BaseIService baseIService;

    @Resource
    private EmailDistributionDAO emailDistributionDAO;

    @Resource
    private EmailMessageDAO emailMessageDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private MinTimeWindowDao minTimeWindowDao;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private WFVerDAO wfVerDAO;

    //获取最小时间管控列表信息
    @Override
    public GetAllMinTimeWindowInfoRes QALLGetAllMinTimeWindowInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {

        GetAllMinTimeWindowInfoRes objGetAllMinTimeWindowInfoRes=new GetAllMinTimeWindowInfoRes();
        GetAllMinTimeWindowInfoResB objGetAllMinTimeWindowInfoResB=new GetAllMinTimeWindowInfoResB();

        List<GetAllMinTimeWindowInfoResD> objGetAllMinTimeWindowInfoResDs=new ArrayList<GetAllMinTimeWindowInfoResD>();

        List<MinTimeWindowInfo> objMinTimeWindowInfo= baseIService.QALLBaseInfo(MinTimeWindowDao.class, "SelectMinTimeWindowInfo",
                argInitDataFields, argPageInfo);
        if(objMinTimeWindowInfo!=null && objMinTimeWindowInfo.size()>0){
            for(MinTimeWindowInfo obj:objMinTimeWindowInfo){
                GetAllMinTimeWindowInfoResD objGetAllMinTimeWindowInfoResD=new GetAllMinTimeWindowInfoResD();
                objGetAllMinTimeWindowInfoResD.setMinTimeWindowRd(obj.getRuid());
                objGetAllMinTimeWindowInfoResD.setMinTimeWindowName(obj.getMinTimeWindowName());
                objGetAllMinTimeWindowInfoResDs.add(objGetAllMinTimeWindowInfoResD);
            }
        }
        objGetAllMinTimeWindowInfoResB.setData(objGetAllMinTimeWindowInfoResDs);
        objGetAllMinTimeWindowInfoRes.setBody(objGetAllMinTimeWindowInfoResB);
        return objGetAllMinTimeWindowInfoRes;
    }

    //获取最小时间管控信息
    @Override
    public GetMinTimedowInfoRes GetGetMinTimedowInfoRes(GetMinTimedowInfoReqBD00 argGetMinTimedowInfoReqBD00) {
        GetMinTimedowInfoRes objGetMinTimedowInfoRes = new GetMinTimedowInfoRes();
        GetMinTimedowInfoResB objGetMinTimedowInfoResB = new GetMinTimedowInfoResB();
        GetMinTimedowInfoResD objGetMinTimedowInfoResD = new GetMinTimedowInfoResD();
        MinTimeWindowInfo minTimeWindowInfo = minTimeWindowDao.SelectMinTimeWindowInfoByruid(argGetMinTimedowInfoReqBD00.getMinTimeWindowRd());
        if(minTimeWindowInfo!=null){
            objGetMinTimedowInfoResD.setMinTimeWindoRd(minTimeWindowInfo.getRuid());
            objGetMinTimedowInfoResD.setMinTimeWindowName(minTimeWindowInfo.getMinTimeWindowName());
            objGetMinTimedowInfoResD.setDescription(minTimeWindowInfo.getDescription());
            objGetMinTimedowInfoResD.setMinTime(minTimeWindowInfo.getMinTime());
            objGetMinTimedowInfoResD.setOverTimeAction(minTimeWindowInfo.getOverTimeAction());
            objGetMinTimedowInfoResD.setCreator(minTimeWindowInfo.getCreator());
            objGetMinTimedowInfoResD.setCreateTime(DateUtil.format(minTimeWindowInfo.getCreateTime()));
            objGetMinTimedowInfoResD.setLastModifyMan(minTimeWindowInfo.getLastModifyMan());
            objGetMinTimedowInfoResD.setLastModifyTime(DateUtil.format(minTimeWindowInfo.getLastModifyTime()));
            objGetMinTimedowInfoResD.setRemark(minTimeWindowInfo.getRemark());
            //产品
            /*MaterialInfo materialInfo = materialDAO.SelectByGuid(minTimeWindowInfo.getMaGd());
            GetMinTimedowInfoResDMaterialInfo objGetMinTimedowInfoResDMaterialInfo = new GetMinTimedowInfoResDMaterialInfo();
            if(objGetMinTimedowInfoResDMaterialInfo!=null){
                objGetMinTimedowInfoResDMaterialInfo.setMaterialRd(materialInfo.getRuid());
                objGetMinTimedowInfoResDMaterialInfo.setMaterialName(materialInfo.getMaterialName());
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(materialInfo.getVerGd());
                if(maVerInfo!=null){
                    objGetMinTimedowInfoResDMaterialInfo.setMaverRd(maVerInfo.getRuid());
                }
            }*/
            GetMinTimedowInfoResDMaterialInfo objGetMinTimedowInfoResDMaterialInfo = new GetMinTimedowInfoResDMaterialInfo();
            MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(minTimeWindowInfo.getMaVerGd());
            if(maVerInfo!=null){
                objGetMinTimedowInfoResDMaterialInfo.setMaVerRd(maVerInfo.getRuid());
                objGetMinTimedowInfoResDMaterialInfo.setMaName(maVerInfo.getMaterialName());
                objGetMinTimedowInfoResD.setMaInfo(objGetMinTimedowInfoResDMaterialInfo);
            }else {
                objGetMinTimedowInfoResD.setMaInfo(null);
            }

            //工序
            SpecVerInfo specInfo = specVerDAO.SelectSpecVerInfoByguid(minTimeWindowInfo.getSpecVerGd());
            GetMinTimedowInfoResDSpecGdInfo objGetMinTimedowInfoResDSpecGdInfo = new GetMinTimedowInfoResDSpecGdInfo();
            if(specInfo!=null){
                objGetMinTimedowInfoResDSpecGdInfo.setSpecVerRd(specInfo.getRuid());
                objGetMinTimedowInfoResDSpecGdInfo.setSpecName(specInfo.getSpecName());
                objGetMinTimedowInfoResD.setSpecInfo(objGetMinTimedowInfoResDSpecGdInfo);
            }else {
                objGetMinTimedowInfoResD.setSpecInfo(null);
            }
            //设备组
            DevInfo devInfo = devDAO.SelectByguid(minTimeWindowInfo.getDevGd());
            GetMinTimedowInfoResDDevgpInfo objGetMinTimedowInfoResDDevgpInfo = new GetMinTimedowInfoResDDevgpInfo();
            if(devInfo!=null){
                objGetMinTimedowInfoResDDevgpInfo.setDevRd(devInfo.getRuid());
                objGetMinTimedowInfoResDDevgpInfo.setDevName(devInfo.getDevName());
                objGetMinTimedowInfoResD.setDevInfo(objGetMinTimedowInfoResDDevgpInfo);
            }else {
                objGetMinTimedowInfoResD.setDevInfo(null);
            }
            //到期提醒(邮件通知组)
            EmailDistributionInfo emailDistributionInfo = emailDistributionDAO.SelectEmailDistributionInfoByGuid(minTimeWindowInfo.getOverdueEmailDistributionGd());
            GetMinTimedowInfoResDEmailDistributionInfo objGetMinTimedowInfoResDEmailDistributionInfo = new GetMinTimedowInfoResDEmailDistributionInfo();
            if(emailDistributionInfo!=null){
                objGetMinTimedowInfoResDEmailDistributionInfo.setEmailDistributionRd(emailDistributionInfo.getRuid());
                objGetMinTimedowInfoResDEmailDistributionInfo.setEmailDistributionName(emailDistributionInfo.getDistributionName());
                objGetMinTimedowInfoResD.setEmailDistributionInfo(objGetMinTimedowInfoResDEmailDistributionInfo);
            }else {
                objGetMinTimedowInfoResD.setEmailDistributionInfo(null);
            }
            //提醒(邮件内容)
            EmailMessageInfo emailMessageInfo = emailMessageDAO.SelectEmailMessageInfoByGuid(minTimeWindowInfo.getOverdueEmailMessageGd());
            GetMinTimedowInfoResDEmailMessageInfo objGetMinTimedowInfoResDEmailMessageInfo = new GetMinTimedowInfoResDEmailMessageInfo();
            if(emailMessageInfo!=null){
                objGetMinTimedowInfoResDEmailMessageInfo.setEmailMessageRd(emailMessageInfo.getRuid());
                objGetMinTimedowInfoResDEmailMessageInfo.setEmailMessageName(emailMessageInfo.getEmailName());
                objGetMinTimedowInfoResD.setEmailMessageInfo(objGetMinTimedowInfoResDEmailMessageInfo);
            }else {
                objGetMinTimedowInfoResD.setEmailMessageInfo(null);
            }

            GetMinTimedowInfoResDWFInfo getMinTimedowInfoResDWFInfo=new GetMinTimedowInfoResDWFInfo();
            WFVerInfo wfVerInfo=wfVerDAO.SelectByGuid(minTimeWindowInfo.getwFVerGd());
            if(wfVerInfo!=null){
                getMinTimedowInfoResDWFInfo.setWfVerRd(wfVerInfo.getRuid());
                getMinTimedowInfoResDWFInfo.setWFName(wfVerInfo.getwFName());
                objGetMinTimedowInfoResD.setWFInfo(getMinTimedowInfoResDWFInfo);
            }else {
                objGetMinTimedowInfoResD.setWFInfo(null);
            }

        }
        objGetMinTimedowInfoResB.setData(objGetMinTimedowInfoResD);
        objGetMinTimedowInfoRes.setBody(objGetMinTimedowInfoResB);
        return objGetMinTimedowInfoRes;
    }

    //新增最小时间管控信息
    @Override
    public SaveMinTimedowInfoRes AddSaveMinTimedowInfoRes(SaveMinTimedowInfoReqBD00 argSaveWOInfoReqBD00) {
        SaveMinTimedowInfoRes objSaveMinTimedowInfoRes = new SaveMinTimedowInfoRes();
        SaveMinTimedowInfoResB objSaveMinTimedowInfoResB = new SaveMinTimedowInfoResB();
        SaveMinTimedowInfoResD objSaveMinTimedowInfoResD = new SaveMinTimedowInfoResD();
        MinTimeWindowInfo minTimeWindowInfo = new MinTimeWindowInfo();
        minTimeWindowInfo.setGuid(CommonUtils.getRandomNumber());
        if("".equals(argSaveWOInfoReqBD00.getMinTimeWindowName())){
            throw new SystemException("MG0006F","最小时间管控名称不能为空");
        }
        if("".equals(argSaveWOInfoReqBD00.getMinTime())||argSaveWOInfoReqBD00.getMinTime()<=0.0F){
            throw new SystemException("MG0006F","最小时间不能为空");
        }
        if("".equals(argSaveWOInfoReqBD00.getOverTimeAction())){
            throw new SystemException("MG0006F","超时采取行动不能为空");
        }
        if(argSaveWOInfoReqBD00.getMaVerRd()<=0){
            throw new SystemException("xxx","产品不能为空");
        }
        if(argSaveWOInfoReqBD00.getWfVerRd()<=0){
            throw new SystemException("xxx","工艺流程不能为空");
        }
        MinTimeWindowInfo minTimeWindowInfos = minTimeWindowDao.SelectMinTimeWindowInfoByName(argSaveWOInfoReqBD00.getMinTimeWindowName());
        if(minTimeWindowInfos!=null){
            throw new SystemException("MG0006F","最小时间管控名称已存在");
        }
        MaVerInfo maVerInfo=maVerDAO.SelectByRuid(argSaveWOInfoReqBD00.getMaVerRd());
        if(maVerInfo!=null){
            minTimeWindowInfo.setMaVerGd(maVerInfo.getGuid());
        }
        //产品
       /* MaterialInfo materialInfo = materialDAO.SelectMaterialInfo(argSaveWOInfoReqBD00.getMaGd());
        if(materialInfo!=null){
            minTimeWindowInfo.setMaGd(materialInfo.getGuid());
        }*/
       //工艺
        WFVerInfo wfVerInfo=wfVerDAO.SelectByRuid(argSaveWOInfoReqBD00.getWfVerRd());
        if(wfVerInfo!=null){
            minTimeWindowInfo.setwFVerGd(wfVerInfo.getGuid());
        }
        //工序
        SpecVerInfo specInfo = specVerDAO.SelectSpecVerInfoByruid(argSaveWOInfoReqBD00.getSpecVerRd());
        if(specInfo==null){
            throw new SystemException("MG0006F","工序不能为空");
        }
        minTimeWindowInfo.setSpecVerGd(specInfo.getGuid());
        //设备组
        DevInfo devInfo = devDAO.SelectBydevRd(argSaveWOInfoReqBD00.getDevRd());
        if(devInfo!=null){
            minTimeWindowInfo.setDevGd(devInfo.getGuid());
            MinTimeWindowInfo minTimeWindowInfo2=minTimeWindowDao.SelectByMaWfSpecGd(maVerInfo.getGuid(),wfVerInfo.getGuid(),specInfo.getGuid(),devInfo.getGuid());
            if(minTimeWindowInfo2!=null){
                throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"工序"+specInfo.getSpecName()+"设备"+devInfo.getDevName()+"已存在，不可重复设置");
            }
        }else {
            MinTimeWindowInfo minTimeWindowInfo1=minTimeWindowDao.SelectByMaWfSpecGd(maVerInfo.getGuid(),wfVerInfo.getGuid(),specInfo.getGuid(),null);
            if(minTimeWindowInfo1!=null){
                    throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"工序"+specInfo.getSpecName()+"已存在，不可重复设置");
            }
        }


        //到期提醒(邮件通知组)
        EmailDistributionInfo emailDistributionInfo = emailDistributionDAO.SelectEmailDistributionInfoByRuid(argSaveWOInfoReqBD00.getEmailDistributionRd());
        if(emailDistributionInfo!=null){
            minTimeWindowInfo.setOverdueEmailDistributionGd(emailDistributionInfo.getGuid());
        }
        //提醒(邮件内容)
        EmailMessageInfo emailMessageInfo = emailMessageDAO.SelectEmailMessageByID(argSaveWOInfoReqBD00.getEmailMessageRd());
        if(emailMessageInfo!=null){
            minTimeWindowInfo.setOverdueEmailMessageGd(emailMessageInfo.getGuid());
        }


        minTimeWindowInfo.setMinTimeWindowName(argSaveWOInfoReqBD00.getMinTimeWindowName());
        minTimeWindowInfo.setDescription(argSaveWOInfoReqBD00.getDescription());
        minTimeWindowInfo.setMinTime(argSaveWOInfoReqBD00.getMinTime());
        minTimeWindowInfo.setOverTimeAction(argSaveWOInfoReqBD00.getOverTimeAction());
        minTimeWindowInfo.setRemark(argSaveWOInfoReqBD00.getRemark());
        minTimeWindowInfo.setCreator(CommonUtils.readUser().getRealName());
        minTimeWindowInfo.setCreateTime(new Date());
        minTimeWindowInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        minTimeWindowInfo.setLastModifyTime(new Date());
        if(minTimeWindowDao.InsertMinTimeWindowInfo(minTimeWindowInfo)<=0){
            throw new SystemException("MG0006F","新增最小时间管控失败");
        }
        objSaveMinTimedowInfoResB.setData(objSaveMinTimedowInfoResD);
        objSaveMinTimedowInfoRes.setBody(objSaveMinTimedowInfoResB);
        return objSaveMinTimedowInfoRes;
    }

    //删除最小时间管控信息
    @Override
    public SaveMinTimedowInfoRes RmSaveMinTimedowInfoRes(SaveMinTimedowInfoReqBD01 argSaveMinTimedowInfoReqBD01) {
        SaveMinTimedowInfoRes objSaveMinTimedowInfoRes = new  SaveMinTimedowInfoRes();
        SaveMinTimedowInfoResB objSaveMinTimedowInfoResB = new SaveMinTimedowInfoResB();
        SaveMinTimedowInfoResD objSaveMinTimedowInfoResD = new SaveMinTimedowInfoResD();
        MinTimeWindowInfo minTimeWindowInfo = minTimeWindowDao.SelectMinTimeWindowInfoByruid(argSaveMinTimedowInfoReqBD01.getMinTimedowInfoRd());
        if(minTimeWindowInfo==null){
            throw new SystemException("MG0006F","最小时间管控不存在");
        }
        if(minTimeWindowDao.DeleteMinTimeWindowInfo(argSaveMinTimedowInfoReqBD01.getMinTimedowInfoRd())<=0){
            throw new SystemException("MG0006F","删除最小时间管控失败");
        }
        objSaveMinTimedowInfoResB.setData(objSaveMinTimedowInfoResD);
        objSaveMinTimedowInfoRes.setBody(objSaveMinTimedowInfoResB);
        return objSaveMinTimedowInfoRes;
    }

    //更新最小时间管控信息
    @Override
    public SaveMinTimedowInfoRes ModSaveWOInfoRes(SaveMinTimedowInfoReqBD02 argSaveMinTimedowInfoReqBD02) {
        SaveMinTimedowInfoRes objSaveMinTimedowInfoRes = new SaveMinTimedowInfoRes();
        SaveMinTimedowInfoResB objSaveMinTimedowInfoResB = new SaveMinTimedowInfoResB();
        SaveMinTimedowInfoResD objSaveMinTimedowInfoResD = new SaveMinTimedowInfoResD();

        if("".equals(argSaveMinTimedowInfoReqBD02.getMinTimeWindowName())||argSaveMinTimedowInfoReqBD02.getMinTimeWindowName()==null){
            throw new SystemException("MG0006F","最小时间管控名称不能为空");
        }
        if("".equals(argSaveMinTimedowInfoReqBD02.getMinTime())||argSaveMinTimedowInfoReqBD02.getMinTime()<=0.0f){
            throw new SystemException("MG0006F","最小时间不能为空");
        }
        if("".equals(argSaveMinTimedowInfoReqBD02.getOverTimeAction())){
            throw new SystemException("MG0006F","超时采取行动不能为空");
        }
        if(argSaveMinTimedowInfoReqBD02.getMaVerRd()<=0){
            throw new SystemException("xxx","产品不能为空");
        }
        if(argSaveMinTimedowInfoReqBD02.getWfVerRd()<=0){
            throw new SystemException("xxx","工艺流程不能为空");
        }
        MinTimeWindowInfo MinTimeWindowInfo = minTimeWindowDao.SelectMinTimeWindowInfoByruid(argSaveMinTimedowInfoReqBD02.getMinTimeWindowRd());
        if(MinTimeWindowInfo==null){
            throw new SystemException("xxx","信息不存在");
        }
        MinTimeWindowInfo minTimeWindowInfos = minTimeWindowDao.SelectMinTimeWindowInfoByName(argSaveMinTimedowInfoReqBD02.getMinTimeWindowName());
        if(minTimeWindowInfos!=null&&!minTimeWindowInfos.getMinTimeWindowName().equals(MinTimeWindowInfo.getMinTimeWindowName())){
            throw new SystemException("MG0006F","最小时间管控名称已存在");
        }
        WFVerInfo wfVerInfo=wfVerDAO.SelectByRuid(argSaveMinTimedowInfoReqBD02.getWfVerRd());
        if(wfVerInfo!=null){
            MinTimeWindowInfo.setwFVerGd(wfVerInfo.getGuid());
        }

        //工序
        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(argSaveMinTimedowInfoReqBD02.getSpecVerRd());
        if(specVerInfo==null){
            throw new SystemException("xxx","工序信息不能为空");
        }
        MinTimeWindowInfo.setSpecVerGd(specVerInfo.getGuid());
        //产品
        MaVerInfo maVerInfo=maVerDAO.SelectByRuid(argSaveMinTimedowInfoReqBD02.getMaVerRd());
        if(maVerInfo==null){
            throw new SystemException("xxx","产品信息不存在");
        }else {
            MinTimeWindowInfo.setMaVerGd(maVerInfo.getGuid());
        }
        //设备组
        DevInfo devInfo = devDAO.SelectBydevRd(argSaveMinTimedowInfoReqBD02.getDevRd());
        if(devInfo!=null){
            MinTimeWindowInfo.setDevGd(devInfo.getGuid());
            MinTimeWindowInfo minTimeWindowInfo2=minTimeWindowDao.SelectByMaWfSpecGd(maVerInfo.getGuid(),wfVerInfo.getGuid(),specVerInfo.getGuid(),devInfo.getGuid());


            if(minTimeWindowInfo2!=null&&!minTimeWindowInfo2.getRuid().equals(MinTimeWindowInfo.getRuid())){
                throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"工序"+specVerInfo.getSpecName()+"设备"+devInfo.getDevName()+"已存在，不可重复设置");
            }
        }else {
            MinTimeWindowInfo minTimeWindowInfo1=minTimeWindowDao.SelectByMaWfSpecGd(maVerInfo.getGuid(),wfVerInfo.getGuid(),specVerInfo.getGuid(),null);
            if(minTimeWindowInfo1!=null&&!minTimeWindowInfo1.getRuid().equals(MinTimeWindowInfo.getRuid())){
                throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"工序"+specVerInfo.getSpecName()+"已存在，不可重复设置");
            }
        }



        //到期提醒(邮件通知组)
        EmailDistributionInfo emailDistributionInfo = emailDistributionDAO.SelectEmailDistributionInfoByRuid(argSaveMinTimedowInfoReqBD02.getEmailDistributionRd());
        if(emailDistributionInfo!=null){
            MinTimeWindowInfo.setOverdueEmailDistributionGd(emailDistributionInfo.getGuid());
        }else{
            MinTimeWindowInfo.setOverdueEmailDistributionGd("");
        }
        //提醒(邮件内容)
        EmailMessageInfo emailMessageInfo = emailMessageDAO.SelectEmailMessageByID(argSaveMinTimedowInfoReqBD02.getEmailMessageRd());
        if(emailMessageInfo!=null){
            MinTimeWindowInfo.setOverdueEmailMessageGd(emailMessageInfo.getGuid());
        }else {
            MinTimeWindowInfo.setOverdueEmailMessageGd("");
        }
        MinTimeWindowInfo.setMinTimeWindowName(argSaveMinTimedowInfoReqBD02.getMinTimeWindowName());
        MinTimeWindowInfo.setDescription(argSaveMinTimedowInfoReqBD02.getDescription());
        MinTimeWindowInfo.setMinTime(argSaveMinTimedowInfoReqBD02.getMinTime());
        MinTimeWindowInfo.setOverTimeAction(argSaveMinTimedowInfoReqBD02.getOverTimeAction());
        MinTimeWindowInfo.setRemark(argSaveMinTimedowInfoReqBD02.getRemark());
        if(minTimeWindowDao.UpdateMinTimeWindowInfo(MinTimeWindowInfo)<=0){
            throw new SystemException("xxx","最小时间管控更新失败");
        }
        objSaveMinTimedowInfoResB.setData(objSaveMinTimedowInfoResD);
        objSaveMinTimedowInfoRes.setBody(objSaveMinTimedowInfoResB);
        return objSaveMinTimedowInfoRes;
    }

}
