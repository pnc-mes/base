package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllMaxTimeInfo.GetAllMaxTimeInfoRes;
import pnc.mesadmin.dto.GetAllMaxTimeInfo.GetAllMaxTimeInfoResB;
import pnc.mesadmin.dto.GetAllMaxTimeInfo.GetAllMaxTimeInfoResD;
import pnc.mesadmin.dto.GetMaxTimeInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveMaxTimeInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.MaxTimeWindowIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:27
 * @Description:
 */
@Transactional
@Service
public class MaxTimeWindowService implements MaxTimeWindowIService {
    @Resource
    private MaxTimeWindowDAO maxTimeWindowDAO;

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
    private MaVerDAO maVerDAO;

    @Resource
    private WFVerDAO wfVerDAO;



    //查询最大时间列表
    @Override
    public GetAllMaxTimeInfoRes QALLGetAllMaxTimeInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllMaxTimeInfoRes getAllMaxTimeInfoRes=new GetAllMaxTimeInfoRes();
        GetAllMaxTimeInfoResB getAllMaxTimeInfoResB=new GetAllMaxTimeInfoResB();
        List<GetAllMaxTimeInfoResD> getAllMaxTimeInfoResDS=new ArrayList<GetAllMaxTimeInfoResD>();
        List<MaxTimeWindowInfo> maxTimeWindowInfos = baseIService.QALLBaseInfo(MaxTimeWindowDAO.class, "selectAllMaxTimeWindowInfo",
                argInitDataFields, argPageInfo);

        if(maxTimeWindowInfos!=null&&maxTimeWindowInfos.size()>0){
            for(MaxTimeWindowInfo maxTimeWindowInfo:maxTimeWindowInfos){
                GetAllMaxTimeInfoResD getAllMaxTimeInfoResD=new GetAllMaxTimeInfoResD();
                getAllMaxTimeInfoResD.setMaxTimeRd(maxTimeWindowInfo.getRuid());
                getAllMaxTimeInfoResD.setMaxTimeWindowName(maxTimeWindowInfo.getMaxTimeWindowName());
                getAllMaxTimeInfoResDS.add(getAllMaxTimeInfoResD);
            }
        }
        getAllMaxTimeInfoResB.setData(getAllMaxTimeInfoResDS);
        getAllMaxTimeInfoRes.setBody(getAllMaxTimeInfoResB);
        return getAllMaxTimeInfoRes;
    }

    //查询单条信息
    @Override
    public GetMaxTimeInfoRes GetGetMaxTimeInfoRes(GetMaxTimeInfoReq00 getMaxTimeInfoReq00) {
        GetMaxTimeInfoRes getMaxTimeInfoRes=new GetMaxTimeInfoRes();
        GetMaxTimeInfoResB getMaxTimeInfoResB=new GetMaxTimeInfoResB();
        GetMaxTimeInfoResD getMaxTimeInfoResD=new GetMaxTimeInfoResD();
        if("".equals(getMaxTimeInfoReq00.getMaxTimeRd())||getMaxTimeInfoReq00.getMaxTimeRd()<=0){
            throw new SystemException("xx","查询失败，最大时间标识不能为空");
        }
        MaxTimeWindowInfo maxTimeWindowInfo=maxTimeWindowDAO.selectMaxTimeWindowInfoByRuid(getMaxTimeInfoReq00.getMaxTimeRd());
        if(maxTimeWindowInfo==null){
            throw new SystemException("xx","查询失败，该信息不存在");
        }

        getMaxTimeInfoResD.setMaxTimeRd(maxTimeWindowInfo.getRuid());
        getMaxTimeInfoResD.setMaxTimeWindowName(maxTimeWindowInfo.getMaxTimeWindowName());
        getMaxTimeInfoResD.setDescription(maxTimeWindowInfo.getDescription());

        GetMaxTimeInfoResDMa getMaxTimeInfoResDMaver=new GetMaxTimeInfoResDMa();

       /* MaterialInfo materialInfo=materialDAO.SelectByGuid(maxTimeWindowInfo.getMaGd());
        if(materialInfo!=null){
            getMaxTimeInfoResDMaver.setMaRd(materialInfo.getRuid());
            getMaxTimeInfoResDMaver.setMaName(materialInfo.getMaterialName());
            MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(materialInfo.getVerGd());
            if(maVerInfo!=null){
                getMaxTimeInfoResDMaver.setMaverRd(maVerInfo.getRuid());
            }
        }*/
        MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(maxTimeWindowInfo.getMaVerGd());
        if(maVerInfo!=null){
            getMaxTimeInfoResDMaver.setMaVerRd(maVerInfo.getRuid());
            getMaxTimeInfoResDMaver.setMaName(maVerInfo.getMaterialName());
        }


        GetMaxTimeInfoResDStartSpec getMaxTimeInfoResDStartSpec=new GetMaxTimeInfoResDStartSpec();
        SpecVerInfo specInfo=specVerDAO.SelectSpecVerInfoByguid(maxTimeWindowInfo.getStartSpecVerGd());
        if(specInfo!=null){
            getMaxTimeInfoResDStartSpec.setSpecName(specInfo.getSpecName());
            getMaxTimeInfoResDStartSpec.setSpecVerRd(specInfo.getRuid());
        }

        GetMaxTimeInfoResDEndSpec getMaxTimeInfoResDEndSpec=new GetMaxTimeInfoResDEndSpec();
        SpecVerInfo specInfo1=specVerDAO.SelectSpecVerInfoByguid(maxTimeWindowInfo.getEndSpecVerGd());
        if(specInfo1!=null){
            getMaxTimeInfoResDEndSpec.setSpecName(specInfo1.getSpecName());
            getMaxTimeInfoResDEndSpec.setSpecVerRd(specInfo1.getRuid());
        }
        GetMaxTimeInfoResDPresetEmailDistribution getMaxTimeInfoResDPresetEmailDistribution=new GetMaxTimeInfoResDPresetEmailDistribution();
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByGuid(maxTimeWindowInfo.getPresetEmailDistributionGd());
        if(emailDistributionInfo!=null){
            getMaxTimeInfoResDPresetEmailDistribution.setEmailDistributionRd(emailDistributionInfo.getRuid());
            getMaxTimeInfoResDPresetEmailDistribution.setEmailDistributionName(emailDistributionInfo.getDistributionName());
            getMaxTimeInfoResD.setPresetEmailDistributionInfo(getMaxTimeInfoResDPresetEmailDistribution);
        }else {
            getMaxTimeInfoResD.setPresetEmailDistributionInfo(null);
        }

        GetMaxTimeInfoResDPresetEmailMessage getMaxTimeInfoResDPresetEmailMessage=new GetMaxTimeInfoResDPresetEmailMessage();
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageInfoByGuid(maxTimeWindowInfo.getPresetEmailMessageGd());
        if(emailMessageInfo!=null){
            getMaxTimeInfoResDPresetEmailMessage.setEmailMessageRd(emailMessageInfo.getRuid());
            getMaxTimeInfoResDPresetEmailMessage.setEmailMessageName(emailMessageInfo.getEmailName());
            getMaxTimeInfoResD.setPresetEmailMessageInfo(getMaxTimeInfoResDPresetEmailMessage);
        }else {
            getMaxTimeInfoResD.setPresetEmailMessageInfo(null);
        }

        GetMaxTimeInfoResDOverdueEmailDistribution getMaxTimeInfoResDOverdueEmailDistribution=new   GetMaxTimeInfoResDOverdueEmailDistribution();
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByGuid(maxTimeWindowInfo.getOverdueEmailDistributionGd());
        if(emailDistributionInfo1!=null){
            getMaxTimeInfoResDOverdueEmailDistribution.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            getMaxTimeInfoResDOverdueEmailDistribution.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
            getMaxTimeInfoResD.setOverdueEmailDistributionInfo(getMaxTimeInfoResDOverdueEmailDistribution);
        }else {
            getMaxTimeInfoResD.setOverdueEmailDistributionInfo(null);
        }

        GetMaxTimeInfoResDOverdueEmailMessage getMaxTimeInfoResDOverdueEmailMessage=new GetMaxTimeInfoResDOverdueEmailMessage();
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageInfoByGuid(maxTimeWindowInfo.getOverdueEmailMessageGd());
        if(emailMessageInfo1!=null){
            getMaxTimeInfoResDOverdueEmailMessage.setEmailMessageRd(emailMessageInfo1.getRuid());
            getMaxTimeInfoResDOverdueEmailMessage.setEmailMessageName(emailMessageInfo1.getEmailName());
            getMaxTimeInfoResD.setOverdueEmailMessageInfo(getMaxTimeInfoResDOverdueEmailMessage);
        }else {
            getMaxTimeInfoResD.setOverdueEmailMessageInfo(null);
        }
        GetMaxTimeInfoResDWF getMaxTimeInfoResDWF=new GetMaxTimeInfoResDWF();
        //WFInfo wfInfo=wfdao.SelectByGuid(maxTimeWindowInfo.getW);
        WFVerInfo wfVerInfo=wfVerDAO.SelectByGuid(maxTimeWindowInfo.getwFVerGd());
        if(wfVerInfo!=null){
            getMaxTimeInfoResDWF.setWfVerRd(wfVerInfo.getRuid());
            getMaxTimeInfoResDWF.setWFName(wfVerInfo.getwFName());
            getMaxTimeInfoResD.setWFInfo(getMaxTimeInfoResDWF);
        }else {
            getMaxTimeInfoResD.setWFInfo(null);
        }

        getMaxTimeInfoResD.setEndSpecVerInfo(getMaxTimeInfoResDEndSpec);
        getMaxTimeInfoResD.setStartSpecVerInfo(getMaxTimeInfoResDStartSpec);
        getMaxTimeInfoResD.setMaInfo(getMaxTimeInfoResDMaver);
        getMaxTimeInfoResD.setMaxTime(maxTimeWindowInfo.getMaxTime());
        getMaxTimeInfoResD.setPresetTime(maxTimeWindowInfo.getPresetTime());
        getMaxTimeInfoResD.setOverTimeAction(maxTimeWindowInfo.getOverTimeAction());
        getMaxTimeInfoResD.setCreator(maxTimeWindowInfo.getCreator());
        getMaxTimeInfoResD.setCreateTime(DateUtil.format(maxTimeWindowInfo.getCreateTime()));
        getMaxTimeInfoResD.setLastModifyMan(maxTimeWindowInfo.getLastModifyMan());
        getMaxTimeInfoResD.setLastModifyTime(DateUtil.format(maxTimeWindowInfo.getLastModifyTime()));
        getMaxTimeInfoResD.setRemark(maxTimeWindowInfo.getRemark());

        getMaxTimeInfoResB.setData(getMaxTimeInfoResD);
        getMaxTimeInfoRes.setBody(getMaxTimeInfoResB);
        return getMaxTimeInfoRes;
    }

    //保存最大时间信息
    @Override
    public SaveMaxTimeInfoRes AddSaveMaxTimeInfoRes(SaveMaxTimeInfoReq00 saveMaxTimeInfoReq00) {
        SaveMaxTimeInfoRes saveMaxTimeInfoRes=new SaveMaxTimeInfoRes();
        SaveMaxTimeInfoResB saveMaxTimeInfoResB=new SaveMaxTimeInfoResB();

        if(saveMaxTimeInfoReq00.getMaxTimeWindowName()==null||"".equals(saveMaxTimeInfoReq00.getMaxTimeWindowName())){
            throw new SystemException("xxx","新增失败，最大时间名称不能为空");
        }
        if (saveMaxTimeInfoReq00.getMaVerRd()<=0||"".equals(saveMaxTimeInfoReq00.getMaVerRd())){
            throw new SystemException("xxx","新增失败，产品不能为空");
        }
        if (saveMaxTimeInfoReq00.getWfVerRd()<=0||"".equals(saveMaxTimeInfoReq00.getWfVerRd())){
            throw new SystemException("xxx","新增失败，工艺流程不能为空");
        }
        if(saveMaxTimeInfoReq00.getStartSpecVerRd()<=0||"".equals(saveMaxTimeInfoReq00.getStartSpecVerRd())){
            throw new SystemException("xxx","新增失败，开始工序不能为空");
        }
        if(saveMaxTimeInfoReq00.getEndSpecVerRd()<=0||"".equals(saveMaxTimeInfoReq00.getEndSpecVerRd())){
            throw new SystemException("xxx","新增失败，结束工序不能为空");
        }
        if(saveMaxTimeInfoReq00.getMaxTime()<=0||"".equals(saveMaxTimeInfoReq00.getMaxTime())){
            throw new SystemException("xxx","新增失败，最大时间名称不能为空");
        }
        if(saveMaxTimeInfoReq00.getOverTimeAction()==null||"".equals(saveMaxTimeInfoReq00.getOverTimeAction())){
            throw new SystemException("xxx","新增失败，超时采取行动不能为空");
        }
        MaxTimeWindowInfo maxTimeWindowInfo1=maxTimeWindowDAO.selectAllMaxTimeWindowInfoByMaxTimeWindowName(saveMaxTimeInfoReq00.getMaxTimeWindowName());
        if(maxTimeWindowInfo1!=null){
            throw new SystemException("xxx","新增失败，最大名称已存在");
        }
        MaxTimeWindowInfo maxTimeWindowInfo=new MaxTimeWindowInfo();
        maxTimeWindowInfo.setGuid(CommonUtils.getRandomNumber());
        maxTimeWindowInfo.setMaxTimeWindowName(saveMaxTimeInfoReq00.getMaxTimeWindowName());
        maxTimeWindowInfo.setDescription(saveMaxTimeInfoReq00.getDescription());

        /*MaterialInfo materialInfo=materialDAO.SelectMaterialInfo(saveMaxTimeInfoReq00.getMaRd());
        if(materialInfo!=null){
            maxTimeWindowInfo.setMaGd(materialInfo.getGuid());
        }*/
        MaVerInfo maVerInfo=maVerDAO.SelectByRuid(saveMaxTimeInfoReq00.getMaVerRd());
        if(maVerInfo!=null){
            maxTimeWindowInfo.setMaVerGd(maVerInfo.getGuid());
        }
        WFVerInfo wfVerInfo=wfVerDAO.SelectByRuid(saveMaxTimeInfoReq00.getWfVerRd());
        if(wfVerInfo!=null){
            maxTimeWindowInfo.setwFVerGd(wfVerInfo.getGuid());
        }

        SpecVerInfo specInfo=specVerDAO.SelectSpecVerInfoByruid(saveMaxTimeInfoReq00.getStartSpecVerRd());
        if(specInfo!=null){
            maxTimeWindowInfo.setStartSpecVerGd(specInfo.getGuid());
        }

        SpecVerInfo specInfo1=specVerDAO.SelectSpecVerInfoByruid(saveMaxTimeInfoReq00.getEndSpecVerRd());
        if(specInfo1!=null){
            maxTimeWindowInfo.setEndSpecVerGd(specInfo1.getGuid());
        }

        maxTimeWindowInfo.setMaxTime(saveMaxTimeInfoReq00.getMaxTime());
        maxTimeWindowInfo.setPresetTime(saveMaxTimeInfoReq00.getPresetTime());
        maxTimeWindowInfo.setOverTimeAction(saveMaxTimeInfoReq00.getOverTimeAction());
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaxTimeInfoReq00.getPresetEmailDistributionRd());
        if(emailDistributionInfo!=null){
            maxTimeWindowInfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }else {
            maxTimeWindowInfo.setPresetEmailDistributionGd("");
        }

        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveMaxTimeInfoReq00.getPresetEmailMessageRd());
        if(emailMessageInfo!=null){
            maxTimeWindowInfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }else {
            maxTimeWindowInfo.setPresetEmailMessageGd("");
        }
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaxTimeInfoReq00.getOverEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            maxTimeWindowInfo.setOverdueEmailDistributionGd(emailDistributionInfo1.getGuid());
        }else {
            maxTimeWindowInfo.setOverdueEmailDistributionGd("");
        }


        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveMaxTimeInfoReq00.getOverEmailMessageRd());
        if(emailMessageInfo1!=null){
            maxTimeWindowInfo.setOverdueEmailMessageGd(emailMessageInfo1.getGuid());
        }else {
            maxTimeWindowInfo.setOverdueEmailMessageGd("");
        }

        MaxTimeWindowInfo maxTimeWindowInfo2=maxTimeWindowDAO.selectFourMaxTimeWindowInfo(maVerInfo.getGuid(),wfVerInfo.getGuid(),specInfo.getGuid(),specInfo1.getGuid());
        if(maxTimeWindowInfo2!=null){
            throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"开始工序"+specInfo.getSpecName()+"结束工序"+specInfo1.getSpecName()+"已存在，不可重复设置");
        }

        maxTimeWindowInfo.setRemark(saveMaxTimeInfoReq00.getRemark());
        maxTimeWindowInfo.setCreator(CommonUtils.readUser().getRealName());
        maxTimeWindowInfo.setCreateTime(new Date());

        maxTimeWindowDAO.insertMaxTimeWindowInfo(maxTimeWindowInfo);

        saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
        return saveMaxTimeInfoRes;
    }

    //删除最大信息
    @Override
    public SaveMaxTimeInfoRes RmSaveMaxTimeInfoRes(SaveMaxTimeInfoReq01 saveMaxTimeInfoReq01) {
        SaveMaxTimeInfoRes saveMaxTimeInfoRes=new SaveMaxTimeInfoRes();
        SaveMaxTimeInfoResB saveMaxTimeInfoResB=new SaveMaxTimeInfoResB();
        SaveMaxTimeInfoResD saveMaxTimeInfoResD=new SaveMaxTimeInfoResD();
        if(saveMaxTimeInfoReq01.getMaxTimeRd()<=0||"".equals(saveMaxTimeInfoReq01.getMaxTimeRd())){
            throw new SystemException("xxx","删除失败，最大时间标识不存在");
        }
        MaxTimeWindowInfo maxTimeWindowInfo=maxTimeWindowDAO.selectMaxTimeWindowInfoByRuid(saveMaxTimeInfoReq01.getMaxTimeRd());
        if(maxTimeWindowInfo!=null){
            if(maxTimeWindowDAO.deleteMaxTimeWindowInfo(maxTimeWindowInfo.getRuid())<=0){
                throw new SystemException("xxx","删除失败，该信息不存在");
            }
        }
        saveMaxTimeInfoResB.setData(saveMaxTimeInfoResD);
        saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
        return saveMaxTimeInfoRes;
    }

    //修改最大信息
    @Override
    public SaveMaxTimeInfoRes ModSaveMaxTimeInfoRes(SaveMaxTimeInfoReq02 saveMaxTimeInfoReq02) {
        SaveMaxTimeInfoRes saveMaxTimeInfoRes=new SaveMaxTimeInfoRes();
        SaveMaxTimeInfoResB saveMaxTimeInfoResB=new SaveMaxTimeInfoResB();
        if(saveMaxTimeInfoReq02.getMaxTimeRd()<=0||"".equals(saveMaxTimeInfoReq02.getMaxTimeRd())){
            throw new SystemException("xxx","修改失败，最大修改标识不能为空");
        }

        if(saveMaxTimeInfoReq02.getMaxTimeWindowName()==null||"".equals(saveMaxTimeInfoReq02.getMaxTimeWindowName())){
            throw new SystemException("xxx","修改失败，最大时间名称不能为空");
        }
        if (saveMaxTimeInfoReq02.getMaVerRd()<=0||"".equals(saveMaxTimeInfoReq02.getMaVerRd())){
            throw new SystemException("xxx","修改失败，产品不能为空");
        }
        if (saveMaxTimeInfoReq02.getWfVerRd()<=0||"".equals(saveMaxTimeInfoReq02.getWfVerRd())){
            throw new SystemException("xxx","修改失败，工艺流程不能为空");
        }
        if(saveMaxTimeInfoReq02.getStartSpecVerRd()<=0||"".equals(saveMaxTimeInfoReq02.getStartSpecVerRd())){
            throw new SystemException("xxx","修改失败，开始工序不能为空");
        }
        if(saveMaxTimeInfoReq02.getEndSpecVerRd()<=0||"".equals(saveMaxTimeInfoReq02.getEndSpecVerRd())){
            throw new SystemException("xxx","修改失败，结束工序不能为空");
        }
        if(saveMaxTimeInfoReq02.getMaxTime()<=0||"".equals(saveMaxTimeInfoReq02.getMaxTime())){
            throw new SystemException("xxx","修改失败，最大时间名称不能为空");
        }
        if(saveMaxTimeInfoReq02.getOverTimeAction()==null||"".equals(saveMaxTimeInfoReq02.getOverTimeAction())){
            throw new SystemException("xxx","修改失败，超时采取行动不能为空");
        }

        MaxTimeWindowInfo maxTimeWindowInfo=maxTimeWindowDAO.selectMaxTimeWindowInfoByRuid(saveMaxTimeInfoReq02.getMaxTimeRd());
        MaxTimeWindowInfo maxTimeWindowInfo1=maxTimeWindowDAO.selectAllMaxTimeWindowInfoByMaxTimeWindowName(saveMaxTimeInfoReq02.getMaxTimeWindowName());
        if(maxTimeWindowInfo1!=null&&!maxTimeWindowInfo.getMaxTimeWindowName().equals(saveMaxTimeInfoReq02.getMaxTimeWindowName())){
            throw new SystemException("xxx","修改失败，名称已存在");
        }

        maxTimeWindowInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        maxTimeWindowInfo.setLastModifyTime(new Date());

        maxTimeWindowInfo.setMaxTimeWindowName(saveMaxTimeInfoReq02.getMaxTimeWindowName());
        maxTimeWindowInfo.setDescription(saveMaxTimeInfoReq02.getDescription());

        /*MaterialInfo materialInfo=materialDAO.SelectMaterialInfo(saveMaxTimeInfoReq02.getMaRd());
        if(materialInfo!=null){
            maxTimeWindowInfo.setMaGd(materialInfo.getGuid());
        }*/
        MaVerInfo maVerInfo=maVerDAO.SelectByRuid(saveMaxTimeInfoReq02.getMaVerRd());
        if(maVerInfo!=null){
            maxTimeWindowInfo.setMaVerGd(maVerInfo.getGuid());
        }
        SpecVerInfo specInfo=specVerDAO.SelectSpecVerInfoByruid(saveMaxTimeInfoReq02.getStartSpecVerRd());
        if(specInfo!=null){
            maxTimeWindowInfo.setStartSpecVerGd(specInfo.getGuid());
        }
        WFVerInfo wfVerInfo=wfVerDAO.SelectByRuid(saveMaxTimeInfoReq02.getWfVerRd());
        if(wfVerInfo!=null){
            maxTimeWindowInfo.setwFVerGd(wfVerInfo.getGuid());
        }
        SpecVerInfo specInfo1=specVerDAO.SelectSpecVerInfoByruid(saveMaxTimeInfoReq02.getEndSpecVerRd());
        if(specInfo1!=null){
            maxTimeWindowInfo.setEndSpecVerGd(specInfo1.getGuid());
        }

        maxTimeWindowInfo.setMaxTime(saveMaxTimeInfoReq02.getMaxTime());
        maxTimeWindowInfo.setPresetTime(saveMaxTimeInfoReq02.getPresetTime());
        maxTimeWindowInfo.setOverTimeAction(saveMaxTimeInfoReq02.getOverTimeAction());
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaxTimeInfoReq02.getPresetEmailDistributionRd());
        if(emailDistributionInfo!=null){
            maxTimeWindowInfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }else {
            maxTimeWindowInfo.setPresetEmailDistributionGd("");
        }

        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveMaxTimeInfoReq02.getPresetEmailMessageRd());
        if(emailMessageInfo!=null){
            maxTimeWindowInfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }else {
            maxTimeWindowInfo.setPresetEmailMessageGd("");
        }

        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveMaxTimeInfoReq02.getOverEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            maxTimeWindowInfo.setOverdueEmailDistributionGd(emailDistributionInfo1.getGuid());
        }else {
            maxTimeWindowInfo.setOverdueEmailDistributionGd("");
        }

        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveMaxTimeInfoReq02.getOverEmailMessageRd());
        if(emailMessageInfo1!=null){
            maxTimeWindowInfo.setOverdueEmailMessageGd(emailMessageInfo1.getGuid());
        }else {
            maxTimeWindowInfo.setOverdueEmailMessageGd("");
        }
        MaxTimeWindowInfo maxTimeWindowInfo3=maxTimeWindowDAO.selectFourMaxTimeWindowInfo(maVerInfo.getGuid(),wfVerInfo.getGuid(),specInfo.getGuid(),specInfo1.getGuid());
        if(maxTimeWindowInfo3!=null&&maxTimeWindowInfo3.getRuid()!=maxTimeWindowInfo.getRuid()){
            throw new SystemException("xxx","产品"+maVerInfo.getMaterialName()+"工艺流程"+wfVerInfo.getwFName()+"开始工序"+specInfo.getSpecName()+"结束工序"+specInfo1.getSpecName()+"已存在，不可重复设置");
        }


        maxTimeWindowInfo.setRemark(saveMaxTimeInfoReq02.getRemark());
        if(maxTimeWindowDAO.updateMaxTimeWindowInfo(maxTimeWindowInfo)<=0){
            throw new SystemException("xxx","修改失败最大时间失败");
        }

        saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
        return saveMaxTimeInfoRes;
    }
}
