package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllMaPerionInfo.GetAllMaPerionInfoRes;
import pnc.mesadmin.dto.GetAllMaPerionInfo.GetAllMaPerionInfoResB;
import pnc.mesadmin.dto.GetAllMaPerionInfo.GetAllMaPerionInfoResD;
import pnc.mesadmin.dto.GetMaPerionInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveMaPerionInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.MaPerionIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by haozan on 2018/9/6.
 */
@Service
@Transactional
public class MaPerionService implements MaPerionIService {


    @Resource
    private BaseIService baseIService;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private EmailDistributionDAO emailDistributionDAO;

    @Resource
    private EmailMessageDAO emailMessageDAO;

    @Resource
    private OnLineMaPeriodDAO onLineMaPeriodDAO;

    //获取在线物料有效期列表信息
    @Override
    public GetAllMaPerionInfoRes GetAllMaPerionInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllMaPerionInfoRes getAllMaPerionInfoRes=new GetAllMaPerionInfoRes();
        GetAllMaPerionInfoResB getAllMaPerionInfoResB=new GetAllMaPerionInfoResB();
        List<GetAllMaPerionInfoResD> getAllMaPerionInfoResDS=new ArrayList<GetAllMaPerionInfoResD>();
        List<OnLineMaPeriodInfo> onLineMaPeriodInfos = baseIService.QALLBaseInfo(OnLineMaPeriodDAO.class, "selectAllMaPeriod",
                argInitDataFields, argPageInfo);

        if(onLineMaPeriodInfos!=null&&onLineMaPeriodInfos.size()>0){
            for(OnLineMaPeriodInfo onLineMaPeriodInfo:onLineMaPeriodInfos){
                GetAllMaPerionInfoResD getAllMaPerionInfoResD=new GetAllMaPerionInfoResD();
                getAllMaPerionInfoResD.setMaPerionRd(onLineMaPeriodInfo.getRuid());
                getAllMaPerionInfoResD.setMaPeriodName(onLineMaPeriodInfo.getMaPeriodName());
                getAllMaPerionInfoResDS.add(getAllMaPerionInfoResD);
            }
        }
        getAllMaPerionInfoResB.setData(getAllMaPerionInfoResDS);
        getAllMaPerionInfoRes.setBody(getAllMaPerionInfoResB);
        return getAllMaPerionInfoRes;
    }

    //获取在线物料有效期信息
    @Override
    public GetMaPerionInfoRes GetMaPerionInfo(GetMaPerionInfoReq00 getMaPerionInfoReq00) {
        GetMaPerionInfoRes getMaPerionInfoRes=new GetMaPerionInfoRes();
        GetMaPerionInfoResB getMaPerionInfoResB=new GetMaPerionInfoResB();
        GetMaPerionInfoResD getMaPerionInfoResD=new GetMaPerionInfoResD();
        if("".equals(getMaPerionInfoReq00.getMaPerionRd())||getMaPerionInfoReq00.getMaPerionRd()<=0){
            throw new SystemException("xx","查询失败，在线物料有效期标识不能为空");
        }
        OnLineMaPeriodInfo onLineMaPeriodInfo=onLineMaPeriodDAO.selectMaPeriodByRuid(getMaPerionInfoReq00.getMaPerionRd());
        if(onLineMaPeriodInfo==null){
            throw new SystemException("xx","查询失败，该信息不存在");
        }

        getMaPerionInfoResD.setMaPerionRd(onLineMaPeriodInfo.getRuid());
        getMaPerionInfoResD.setMaPeriodName(onLineMaPeriodInfo.getMaPeriodName());
        getMaPerionInfoResD.setDescription(onLineMaPeriodInfo.getDescription());
        getMaPerionInfoResD.setPeriodTime(onLineMaPeriodInfo.getPeriodTime());
        getMaPerionInfoResD.setPresetTime(onLineMaPeriodInfo.getPresetTime());

        //邮件分发 ID
        PresetEmailDistributionInfo presetEmailDistributionInfo=new PresetEmailDistributionInfo();
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByGuid(onLineMaPeriodInfo.getPresetEmailDistributionGd());
        if(emailDistributionInfo!=null){
            presetEmailDistributionInfo.setEmailDistributionRd(emailDistributionInfo.getRuid());
            presetEmailDistributionInfo.setEmailDistributionName(emailDistributionInfo.getDistributionName());
            getMaPerionInfoResD.setPresetEmailDistributionInfo(presetEmailDistributionInfo);
        }

        //邮件消息 ID
        PresetEmailMessageInfo presetEmailMessageInfo=new PresetEmailMessageInfo();
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageInfoByGuid(onLineMaPeriodInfo.getPresetEmailMessageGd());
        if(emailMessageInfo!=null){
            presetEmailMessageInfo.setEmailMessageRd(emailMessageInfo.getRuid());
            presetEmailMessageInfo.setEmailMessageName(emailMessageInfo.getEmailName());
            getMaPerionInfoResD.setPresetEmailMessageInfo(presetEmailMessageInfo);
        }
        //超时采取行为
        getMaPerionInfoResD.setOverTimeAction(onLineMaPeriodInfo.getOverTimeAction());

        //超时设置邮件分发
        OverdueEmailDistributionInfo overdueEmailDistributionInfo=new OverdueEmailDistributionInfo();
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByGuid(onLineMaPeriodInfo.getOverdueEmailDistributionGd());
        if(emailDistributionInfo1!=null){
            overdueEmailDistributionInfo.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            overdueEmailDistributionInfo.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
            getMaPerionInfoResD.setOverdueEmailDistributionInfo(overdueEmailDistributionInfo);
        }

        //超时设置邮件消息
        OverdueEmailMessageInfo overdueEmailMessageInfo=new OverdueEmailMessageInfo();
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageInfoByGuid(onLineMaPeriodInfo.getOverdueEmailMessageGd());
        if(emailMessageInfo1!=null){
            overdueEmailMessageInfo.setEmailMessageRd(emailMessageInfo1.getRuid());
            overdueEmailMessageInfo.setEmailMessageName(emailMessageInfo1.getEmailName());
            getMaPerionInfoResD.setOverdueEmailMessageInfo(overdueEmailMessageInfo);
        }



        getMaPerionInfoResD.setCreator(onLineMaPeriodInfo.getCreator());
        getMaPerionInfoResD.setCreateTime(DateUtil.format(onLineMaPeriodInfo.getCreateTime()));
        getMaPerionInfoResD.setLastModifyMan(onLineMaPeriodInfo.getLastModifyMan());
        getMaPerionInfoResD.setLastModifyTime(DateUtil.format(onLineMaPeriodInfo.getLastModifyTime()));
        getMaPerionInfoResD.setRemark(onLineMaPeriodInfo.getRemark());

        getMaPerionInfoResB.setData(getMaPerionInfoResD);
        getMaPerionInfoRes.setBody(getMaPerionInfoResB);
        return getMaPerionInfoRes;
    }

    //00保存在线物料有效期信息
    @Override
    public SaveMaPerionInfoRes AddSaveMaPerionInfo(SaveMaPerionInfoReq00 saveMaPerionInfoReq00) {
        SaveMaPerionInfoRes saveMaPerionInfoRes=new SaveMaPerionInfoRes();
        SaveMaPerionInfoResB saveMaPerionInfoResB=new SaveMaPerionInfoResB();

        if(saveMaPerionInfoReq00.getMaPeriodName()==null||"".equals(saveMaPerionInfoReq00.getMaPeriodName())){
            throw new SystemException("xxx","新增失败，在线物料有效期名称不能为空");
        }
        if (saveMaPerionInfoReq00.getPeriodTime()==null||"".equals(saveMaPerionInfoReq00.getPeriodTime())){
            throw new SystemException("xxx","新增失败，有效期时间");
        }
        if (saveMaPerionInfoReq00.getPresetTime()==null||"".equals(saveMaPerionInfoReq00.getPresetTime())){
            throw new SystemException("xxx","新增失败，提前提醒时间");
        }

        OnLineMaPeriodInfo onLineMaPeriodInfo=onLineMaPeriodDAO.selectAllMaPeriodName(saveMaPerionInfoReq00.getMaPeriodName());
        if(onLineMaPeriodInfo!=null){
            throw new SystemException("xxx","新增失败，名称已存在");
        }
        OnLineMaPeriodInfo onLineMaPeriodInfos=new OnLineMaPeriodInfo();
        onLineMaPeriodInfos.setGuid(CommonUtils.getRandomNumber());
        onLineMaPeriodInfos.setMaPeriodName(saveMaPerionInfoReq00.getMaPeriodName());
        if(saveMaPerionInfoReq00.getDescription()!=null){
            onLineMaPeriodInfos.setDescription(saveMaPerionInfoReq00.getDescription());
        }
        //超时采取行动
        if(saveMaPerionInfoReq00.getOverTimeAction()!=null){
            onLineMaPeriodInfos.setOverTimeAction(saveMaPerionInfoReq00.getOverTimeAction());
        }
        //有效期时间
        onLineMaPeriodInfos.setPeriodTime(saveMaPerionInfoReq00.getPeriodTime());
        //提前提醒时间
        onLineMaPeriodInfos.setPresetTime(saveMaPerionInfoReq00.getPresetTime());

        //提醒设置
        if(saveMaPerionInfoReq00.getPresetEmailDistributionRd()!=null){
            EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaPerionInfoReq00.getPresetEmailDistributionRd());
            //提醒邮件接收人 ID
            if(emailDistributionInfo!=null){
                onLineMaPeriodInfos.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
            }
            //提醒邮件内容 ID
            EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveMaPerionInfoReq00.getPresetEmailMessageRd());
            if(emailMessageInfo!=null){
                onLineMaPeriodInfos.setPresetEmailMessageGd(emailMessageInfo.getGuid());
            }
            //过期提醒ID
            EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaPerionInfoReq00.getOverEmailDistributionRd());
            if(emailDistributionInfo1!=null){
                onLineMaPeriodInfos.setOverdueEmailDistributionGd(emailDistributionInfo1.getGuid());
            }

            //过期提醒ID
            EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveMaPerionInfoReq00.getOverEmailMessageRd());
            if(emailMessageInfo1!=null){
                onLineMaPeriodInfos.setOverdueEmailMessageGd(emailMessageInfo1.getGuid());
            }
        }



       /* MaxTimeWindowInfo maxTimeWindowInfo2=maxTimeWindowDAO.selectFourMaxTimeWindowInfo(maVerInfo.getGuid(),wfVerInfo.getGuid(),specInfo.getGuid(),specInfo1.getGuid());
        if(maxTimeWindowInfo2!=null){
            throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"开始工序"+specInfo.getSpecName()+"结束工序"+specInfo1.getSpecName()+"已存在，不可重复设置");
        }*/

        onLineMaPeriodInfos.setRemark(saveMaPerionInfoReq00.getRemark());
        onLineMaPeriodInfos.setCreator(CommonUtils.readUser().getRealName());
        onLineMaPeriodInfos.setCreateTime(new Date());

        onLineMaPeriodDAO.insertMaPeriod(onLineMaPeriodInfos);

        saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
        return saveMaPerionInfoRes;
    }

    //01删除在线物料有效期信息
    @Override
    public SaveMaPerionInfoRes RmSaveMaPerionInfo(SaveMaPerionInfoReq01 saveMaPerionInfoReq01) {
        SaveMaPerionInfoRes saveMaPerionInfoRes=new SaveMaPerionInfoRes();
        SaveMaPerionInfoResB saveMaPerionInfoResB=new SaveMaPerionInfoResB();
        SaveMaPerionInfoResD saveMaPerionInfoResD=new SaveMaPerionInfoResD();
        if(saveMaPerionInfoReq01.getMaPerionRd()==null||"".equals(saveMaPerionInfoReq01.getMaPerionRd())){
            throw new SystemException("xxx","删除失败，在线物料有效期标识不存在");
        }
        OnLineMaPeriodInfo onLineMaPeriodInfo=onLineMaPeriodDAO.selectMaPeriodByRuid(saveMaPerionInfoReq01.getMaPerionRd());
        if(onLineMaPeriodInfo!=null){
            if(onLineMaPeriodDAO.deleteMaPeriod(onLineMaPeriodInfo.getRuid())<=0){
                throw new SystemException("xxx","删除失败，该信息不存在");
            }
        }
        saveMaPerionInfoResB.setData(saveMaPerionInfoResD);
        saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
        return saveMaPerionInfoRes;
    }

    //02修改在线物料有效期信息
    @Override
    public SaveMaPerionInfoRes ModSaveMaPerionInfo(SaveMaPerionInfoReq02 saveMaPerionInfoReq02) {
        SaveMaPerionInfoRes saveMaPerionInfoRes=new SaveMaPerionInfoRes();
        SaveMaPerionInfoResB saveMaPerionInfoResB=new SaveMaPerionInfoResB();
        if(saveMaPerionInfoReq02.getMaPerionRd()== null||"".equals(saveMaPerionInfoReq02.getMaPerionRd())){
            throw new SystemException("xxx","修改失败，在线物料有效期修改标识不能为空");
        }

        if(saveMaPerionInfoReq02.getMaPeriodName()==null||"".equals(saveMaPerionInfoReq02.getMaPeriodName())){
            throw new SystemException("xxx","修改失败，在线物料有效期名称不能为空");
        }
        if (saveMaPerionInfoReq02.getPeriodTime()==null||"".equals(saveMaPerionInfoReq02.getPeriodTime())){
            throw new SystemException("xxx","修改失败，有效期时间不能为空");
        }
        if (saveMaPerionInfoReq02.getPresetTime()<=0||"".equals(saveMaPerionInfoReq02.getPresetTime())){
            throw new SystemException("xxx","修改失败，提前提醒时间不能为空");
        }

        OnLineMaPeriodInfo onLineMaPeriodInfo=onLineMaPeriodDAO.selectMaPeriodByRuid(saveMaPerionInfoReq02.getMaPerionRd());
        OnLineMaPeriodInfo onLineMaPeriodInfo1=onLineMaPeriodDAO.selectAllMaPeriodName(saveMaPerionInfoReq02.getMaPeriodName());
        if(onLineMaPeriodInfo1!=null&&!onLineMaPeriodInfo.getMaPeriodName().equals(saveMaPerionInfoReq02.getMaPeriodName())){
            throw new SystemException("xxx","修改失败，名称已存在");
        }

        onLineMaPeriodInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        onLineMaPeriodInfo.setLastModifyTime(new Date());

        onLineMaPeriodInfo.setMaPeriodName(saveMaPerionInfoReq02.getMaPeriodName());
        if(saveMaPerionInfoReq02.getDescription()!=null){
            onLineMaPeriodInfo.setDescription(saveMaPerionInfoReq02.getDescription());
        }

        onLineMaPeriodInfo.setPeriodTime(saveMaPerionInfoReq02.getPeriodTime());
        onLineMaPeriodInfo.setPresetTime(saveMaPerionInfoReq02.getPresetTime());
        onLineMaPeriodInfo.setOverTimeAction(saveMaPerionInfoReq02.getOverTimeAction());
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaPerionInfoReq02.getPresetEmailDistributionRd());
        if(emailDistributionInfo!=null){
            onLineMaPeriodInfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }else {
            onLineMaPeriodInfo.setPresetEmailDistributionGd("");
        }

        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveMaPerionInfoReq02.getPresetEmailMessageRd());
        if(emailMessageInfo!=null){
            onLineMaPeriodInfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }else {
            onLineMaPeriodInfo.setPresetEmailMessageGd("");
        }

        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaPerionInfoReq02.getOverEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            onLineMaPeriodInfo.setOverdueEmailDistributionGd(emailDistributionInfo1.getGuid());
        }else {
            onLineMaPeriodInfo.setOverdueEmailDistributionGd("");
        }

        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveMaPerionInfoReq02.getOverEmailMessageRd());
        if(emailMessageInfo1!=null){
            onLineMaPeriodInfo.setOverdueEmailMessageGd(emailMessageInfo1.getGuid());
        }else {
            onLineMaPeriodInfo.setOverdueEmailMessageGd("");
        }
       /* MaxTimeWindowInfo maxTimeWindowInfo3=maxTimeWindowDAO.selectFourMaxTimeWindowInfo(maVerInfo.getGuid(),wfVerInfo.getGuid(),specInfo.getGuid(),specInfo1.getGuid());
        if(maxTimeWindowInfo3!=null&&!maxTimeWindowInfo.getMaxTimeWindowName().equals(saveMaxTimeInfoReq02.getMaxTimeWindowName())){
            throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"开始工序"+specInfo.getSpecName()+"结束工序"+specInfo1.getSpecName()+"已存在，不可重复设置");
        }*/


        onLineMaPeriodInfo.setRemark(saveMaPerionInfoReq02.getRemark());
        if(onLineMaPeriodDAO.updateMaPeriod(onLineMaPeriodInfo)<=0){
            throw new SystemException("xxx","修改失败最大时间失败");
        }

        saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
        return saveMaPerionInfoRes;
    }
}
