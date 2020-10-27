package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoRes;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoResB;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoResD;
import pnc.mesadmin.dto.GetCyclePlanInfo.*;
import pnc.mesadmin.dto.SaveCyclePlanInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CyclePlanIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:54
 * @Description:
 */
@Service
@Transactional
public class CyclePlanService  implements CyclePlanIService {
    @Resource
    private BaseIService baseIService;

    @Resource
    private CyclePlanDAO cyclePlanDAO;

    @Resource
    private CyclePlanDtlDAO cyclePlanDtlDAO;

    @Resource
    private CyclePlanTiggerRuleDAO cyclePlanTiggerRuleDAO;

    @Resource
    private ReasonDAO reasonDAO;

    @Resource
    private FileVerDAO fileVerDAO;

    @Resource
    private EmailDistributionDAO emailDistributionDAO;

    @Resource
    private EmailMessageDAO emailMessageDAO;

    @Resource
    private TaskDAO taskDAO;


    @Resource
    private ToolPMDAO toolPMDAO;

    @Resource
    private CarrierPMDAO  carrierPMDAO;

    @Resource
    private DevPMDAO devPMDAO;



    @Resource
    private DoPMInfoDAO doPMInfoDAO;
    //列表
    @Override
    public GetAllCyclePlanInfoRes QALLGetAllCyclePlanInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCyclePlanInfoRes getAllCyclePlanInfoRes=new GetAllCyclePlanInfoRes();
        GetAllCyclePlanInfoResB getAllCyclePlanInfoResB=new GetAllCyclePlanInfoResB();
        List<GetAllCyclePlanInfoResD> getAllCyclePlanInfoResDS=new ArrayList<GetAllCyclePlanInfoResD>();
        List<CyclePlanInfo> cyclePlanInfos=baseIService.QALLBaseInfo(CyclePlanDAO.class,"selectAllCyclePlanInfo",argInitDataFields,argPageInfo);
        if(cyclePlanInfos!=null&&cyclePlanInfos.size()>0){
            for(CyclePlanInfo cyclePlanInfo:cyclePlanInfos){
                GetAllCyclePlanInfoResD getAllCyclePlanInfoResD=new GetAllCyclePlanInfoResD();
                getAllCyclePlanInfoResD.setCyclePlanRd(cyclePlanInfo.getRuid());
                getAllCyclePlanInfoResD.setCyclePlanName(cyclePlanInfo.getCyclePlanName());
                getAllCyclePlanInfoResDS.add(getAllCyclePlanInfoResD);

            }
        }



        getAllCyclePlanInfoResB.setData(getAllCyclePlanInfoResDS);
        getAllCyclePlanInfoRes.setBody(getAllCyclePlanInfoResB);
        return getAllCyclePlanInfoRes;
    }

    @Override
    public PageResult<GetAllCyclePlanInfoResD> QALLGetAllCyclePlanNewRes(BaseRequest req) {
        List<GetAllCyclePlanInfoResD> resDList = new ArrayList<GetAllCyclePlanInfoResD>();
        GetAllCyclePlanInfoResD resD = null;

        IPage<CyclePlanInfo> iPage = cyclePlanDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));
        //赋值dto并返回
        for (CyclePlanInfo obj : iPage.getRecords()) {
            resD = new GetAllCyclePlanInfoResD();
            resD.setCyclePlanRd(obj.getRuid());
            resD.setCyclePlanName(obj.getCyclePlanName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    //保存
    @Override
    public SaveCyclePlanInfoRes AddSaveCyclePlanInfoRes(SaveCyclePlanInfoReq00 saveCyclePlanInfoReq00) {
        SaveCyclePlanInfoRes saveCyclePlanInfoRes=new SaveCyclePlanInfoRes();
        SaveCyclePlanInfoResB saveCyclePlanInfoResB=new SaveCyclePlanInfoResB();

        if(saveCyclePlanInfoReq00.getCyclePlanName()==null||"".equals(saveCyclePlanInfoReq00.getCyclePlanName())){
            throw new SystemException("xxx","新增失败，名称不能为空");
        }

        if(saveCyclePlanInfoReq00.getReaRd()<=0){
            throw new SystemException("xxx","新增失败，保养原因不能为空");
        }

        if(saveCyclePlanInfoReq00.getFileVerRd()<=0){
            throw new SystemException("xxx","新增失败，保养标准作业书不能为空");
        }

        if(saveCyclePlanInfoReq00.getStarDate()==null||"".equals(saveCyclePlanInfoReq00.getStarDate())){
            throw new SystemException("xxx","新增失败，起算日期不能为空");
        }

        if(saveCyclePlanInfoReq00.getEndDate()==null||"".equals(saveCyclePlanInfoReq00.getEndDate())){
            throw new SystemException("xxx","新增失败，截止日期不能为空");
        }

        if(saveCyclePlanInfoReq00.getMTType()==null||"".equals(saveCyclePlanInfoReq00.getMTType())){
            throw new SystemException("xxx","新增失败，保养周期不能为空");
        }

        if(saveCyclePlanInfoReq00.getCyclePlanDtlInfo().size()<=0){
            throw new SystemException("xxx","新增失败，保养内容清单不能为空");
        }

        CyclePlanInfo cyclePlanInfo1=cyclePlanDAO.selectCyclePlanInfoByCyclePlanName(saveCyclePlanInfoReq00.getCyclePlanName());
        if(cyclePlanInfo1!=null){
            throw new SystemException("xxx","新增失败，名称已存在");
        }

        CyclePlanInfo cyclePlanInfo=new CyclePlanInfo();
        cyclePlanInfo.setGuid(CommonUtils.getRandomNumber());
        cyclePlanInfo.setCyclePlanName(saveCyclePlanInfoReq00.getCyclePlanName());
        cyclePlanInfo.setStatus(saveCyclePlanInfoReq00.getStatus());
        cyclePlanInfo.setDescription(saveCyclePlanInfoReq00.getDescription());

        ReaCodeInfo reaCodeInfo=reasonDAO.SelectReacodeInfoByruid(saveCyclePlanInfoReq00.getReaRd());
        if(reaCodeInfo!=null){
            cyclePlanInfo.setReaCodeGd(reaCodeInfo.getGuid());
        }else {
            throw new SystemException("xx","原因代码不存在");
        }

        FileVerInfo fileVerInfo=fileVerDAO.SelectByfileVerRd(saveCyclePlanInfoReq00.getFileVerRd());
        if(fileVerInfo!=null){
            cyclePlanInfo.setFileVerGd(fileVerInfo.getGuid());
        }else {
            throw new SystemException("xx","文件不存在");
        }
        cyclePlanInfo.setReference(saveCyclePlanInfoReq00.getReference());
        cyclePlanInfo.setStarDate(DateUtil.format2(saveCyclePlanInfoReq00.getStarDate()));
        cyclePlanInfo.setEndDate(DateUtil.format2(saveCyclePlanInfoReq00.getEndDate()));
        cyclePlanInfo.setmTType(saveCyclePlanInfoReq00.getMTType());
        cyclePlanInfo.setCreator(CommonUtils.readUser().getRealName());
        cyclePlanInfo.setRemark(saveCyclePlanInfoReq00.getRemark());
        cyclePlanInfo.setCreateTime(new Date());
        cyclePlanDAO.insertCyclePlanInfo(cyclePlanInfo);

        CyclePlanTiggerRulelnfo cyclePlanTiggerRulelnfo=new CyclePlanTiggerRulelnfo();
        cyclePlanTiggerRulelnfo.setGuid(CommonUtils.getRandomNumber());
        cyclePlanTiggerRulelnfo.setCyclePlanGd(cyclePlanInfo.getGuid());
        cyclePlanTiggerRulelnfo.setTimeType(saveCyclePlanInfoReq00.getDayInfo().getTimeType());
        cyclePlanTiggerRulelnfo.setTimeContent(saveCyclePlanInfoReq00.getDayInfo().getTimeContent());
        cyclePlanTiggerRulelnfo.setWeekContent(saveCyclePlanInfoReq00.getWeekInfo().getWeekContent());
        cyclePlanTiggerRulelnfo.setMonthContent(saveCyclePlanInfoReq00.getMonthInfo().getMonthContent());
        cyclePlanTiggerRulelnfo.setYearContent(saveCyclePlanInfoReq00.getYearInfo().getYearContent());
        cyclePlanTiggerRulelnfo.setDesContent(saveCyclePlanInfoReq00.getDesInfo().getDesContent());
        cyclePlanTiggerRulelnfo.setQuarterContent(saveCyclePlanInfoReq00.getQuarterInfo().getQuarterContent());
        cyclePlanTiggerRulelnfo.setUpTime(saveCyclePlanInfoReq00.getUpTime()==null||"".equals(saveCyclePlanInfoReq00.getUpTime())?0.0f:saveCyclePlanInfoReq00.getUpTime());
        cyclePlanTiggerRulelnfo.setDownTime(saveCyclePlanInfoReq00.getDownTime()==null||"".equals(saveCyclePlanInfoReq00.getDownTime())?0.0f:saveCyclePlanInfoReq00.getDownTime());
        cyclePlanTiggerRulelnfo.setPresetTime(saveCyclePlanInfoReq00.getPresetTime()==null||"".equals(saveCyclePlanInfoReq00.getPresetTime())?0.0f:saveCyclePlanInfoReq00.getPresetTime());


        //提前邮件提醒
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCyclePlanInfoReq00.getPresetEmailDistributionRd());
        if(emailDistributionInfo!=null){
            cyclePlanTiggerRulelnfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }
        //提前邮件内容提醒
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveCyclePlanInfoReq00.getPresetEmailMessageRd());
        if(emailMessageInfo!=null){
            cyclePlanTiggerRulelnfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }
        //到期
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCyclePlanInfoReq00.getStartEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            cyclePlanTiggerRulelnfo.setStartEmailDistributionGd(emailDistributionInfo1.getGuid());
        }
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveCyclePlanInfoReq00.getStartEmailMessageRd());
        if(emailMessageInfo1!=null){
            cyclePlanTiggerRulelnfo.setStartEmailMessageGd(emailMessageInfo1.getGuid());
        }
        //cyclePlanTiggerRulelnfo.setOverTimeAction(saveCyclePlanInfoReq00.getOverTimeAction());

        //过期
        EmailDistributionInfo emailDistributionInfo2=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCyclePlanInfoReq00.getOverEmailDistributionRd());
        if(emailDistributionInfo2!=null){
            cyclePlanTiggerRulelnfo.setOverdueEmailDistributionGd(emailDistributionInfo2.getGuid());
        }
        EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageByID(saveCyclePlanInfoReq00.getOverEmailMessageRd());
        if(emailMessageInfo2!=null){
            cyclePlanTiggerRulelnfo.setOverdueEmailMessageGd(emailMessageInfo2.getGuid());
        }
        cyclePlanTiggerRulelnfo.setCreator(CommonUtils.readUser().getRealName());
        cyclePlanTiggerRulelnfo.setCreateTime(new Date());
        cyclePlanTiggerRuleDAO.insertCyclePlanTiggerRulelnfo(cyclePlanTiggerRulelnfo);

        for(SaveCyclePlanInfoReq00CyclePlanDtl saveCyclePlanInfoReq00CyclePlanDtl:saveCyclePlanInfoReq00.getCyclePlanDtlInfo()){
            CyclePlanDtllnfo cyclePlanDtllnfo=new CyclePlanDtllnfo();
            if(!Pattern.compile("^[0-9]*[1-9][0-9]*$").matcher(saveCyclePlanInfoReq00CyclePlanDtl.getMaxExCount()).matches()){
                throw new SystemException("xxx", "新增失败，最大执行次数请输入正整数");
            }
            cyclePlanDtllnfo.setCreator(CommonUtils.readUser().getRealName());
            cyclePlanDtllnfo.setCreateTime(new Date());
            TaskInfo taskInfo=taskDAO.selectTaskInfoByRuid(saveCyclePlanInfoReq00CyclePlanDtl.getTaskRd());
            if(taskInfo!=null){
                cyclePlanDtllnfo.setTaskGd(taskInfo.getGuid());
            }

            cyclePlanDtllnfo.setGuid(CommonUtils.getRandomNumber());
            cyclePlanDtllnfo.setCyclePlanGd(cyclePlanInfo.getGuid());
            cyclePlanDtllnfo.setTaskName(saveCyclePlanInfoReq00CyclePlanDtl.getTaskName());
            cyclePlanDtllnfo.setTaskItemName(saveCyclePlanInfoReq00CyclePlanDtl.getTaskItemName());
            cyclePlanDtllnfo.setSureType(saveCyclePlanInfoReq00CyclePlanDtl.getSureType());
      /*      cyclePlanDtllnfo.setMinExCount(saveCyclePlanInfoReq00CyclePlanDtl.getMinExCount());*/
            cyclePlanDtllnfo.setMaxExCount(Integer.valueOf(saveCyclePlanInfoReq00CyclePlanDtl.getMaxExCount()));
            cyclePlanDtlDAO.insertCyclePlanDtllnfo(cyclePlanDtllnfo);
        }
        saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
        return saveCyclePlanInfoRes;
    }

    //删除
    @Override
    public SaveCyclePlanInfoRes RmSaveCyclePlanInfoRes(SaveCyclePlanInfoReq01 saveCyclePlanInfoReq01) {
        SaveCyclePlanInfoRes saveCyclePlanInfoRes=new SaveCyclePlanInfoRes();
        SaveCyclePlanInfoResB saveCyclePlanInfoResB=new SaveCyclePlanInfoResB();
        if(saveCyclePlanInfoReq01.getCyclePlanRd()<=0){
            throw new SystemException("xxx","删除失败，该标识不能为空");
        }

        CyclePlanInfo cyclePlanInfo = cyclePlanDAO.selectCyclePlanInfoByRuid(saveCyclePlanInfoReq01.getCyclePlanRd());
        if (cyclePlanInfo == null) {
            throw new SystemException("xxx", "删除失败，该信息不存在");
        }
        List<DoPMInfo> doPMInfos = doPMInfoDAO.SelectByPMTaskGd(cyclePlanInfo.getGuid());
        if (doPMInfos != null && doPMInfos.size()>0) {
            throw new SystemException("xxx", "当前保养计划不能进行删除，因为有未处理完成的保养任务");
        }

        cyclePlanDAO.deleteCyclePlanInfo(saveCyclePlanInfoReq01.getCyclePlanRd());
        cyclePlanTiggerRuleDAO.deleteCyclePlanTiggerRulelnfoByGuid(cyclePlanInfo.getGuid());
        cyclePlanDtlDAO.deleteCyclePlanDtlInfoByGuid(cyclePlanInfo.getGuid());
        toolPMDAO.deleteByPMGd(cyclePlanInfo.getGuid());
        devPMDAO.deleteByPMGd(cyclePlanInfo.getGuid());
        carrierPMDAO.deleteByPMGd(cyclePlanInfo.getGuid());
        saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
        return saveCyclePlanInfoRes;
    }

    //修改
    @Override
    public SaveCyclePlanInfoRes ModSaveCyclePlanInfoReq02(SaveCyclePlanInfoReq02 saveCyclePlanInfoReq02) {
        SaveCyclePlanInfoRes saveCyclePlanInfoRes=new SaveCyclePlanInfoRes();
        SaveCyclePlanInfoResB saveCyclePlanInfoResB=new SaveCyclePlanInfoResB();
        if(saveCyclePlanInfoReq02.getCyclePlanRd()<=0){
            throw new SystemException("xxx","修改失败，该标识不存在");
        }
        CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(saveCyclePlanInfoReq02.getCyclePlanRd());
        if(cyclePlanInfo==null){
            throw new SystemException("xxx","修改失败，该信息不存在");
        }
        if(saveCyclePlanInfoReq02.getCyclePlanName()==null||"".equals(saveCyclePlanInfoReq02.getCyclePlanName())){
            throw new SystemException("xxx","修改失败，名称不能为空");
        }

        if(saveCyclePlanInfoReq02.getReaRd()<=0){
            throw new SystemException("xxx","修改失败，保养原因不能为空");
        }

        if(saveCyclePlanInfoReq02.getFileVerRd()<=0){
            throw new SystemException("xxx","修改失败，保养标准作业书不能为空");
        }

        if(saveCyclePlanInfoReq02.getStarDate()==null||"".equals(saveCyclePlanInfoReq02.getStarDate())){
            throw new SystemException("xxx","修改失败，起算日期不能为空");
        }

        if(saveCyclePlanInfoReq02.getEndDate()==null||"".equals(saveCyclePlanInfoReq02.getEndDate())){
            throw new SystemException("xxx","修改失败，截止日期不能为空");
        }

        if(saveCyclePlanInfoReq02.getMTType()==null||"".equals(saveCyclePlanInfoReq02.getMTType())){
            throw new SystemException("xxx","修改失败，保养周期不能为空");
        }

        if(saveCyclePlanInfoReq02.getCyclePlanDtlInfo().size()<=0){
            throw new SystemException("xxx","修改失败，保养内容清单不能为空");
        }

        CyclePlanInfo cyclePlanInfo1=cyclePlanDAO.selectCyclePlanInfoByCyclePlanName(saveCyclePlanInfoReq02.getCyclePlanName());
        if(cyclePlanInfo1!=null&&!cyclePlanInfo1.getCyclePlanName().equals(cyclePlanInfo.getCyclePlanName())){
            throw new SystemException("xxx","修改失败，名称已存在");
        }
        cyclePlanInfo.setCyclePlanName(saveCyclePlanInfoReq02.getCyclePlanName());
        cyclePlanInfo.setStatus(saveCyclePlanInfoReq02.getStatus());
        cyclePlanInfo.setDescription(saveCyclePlanInfoReq02.getDescription());

        ReaCodeInfo reaCodeInfo=reasonDAO.SelectReacodeInfoByruid(saveCyclePlanInfoReq02.getReaRd());
        if(reaCodeInfo!=null){
            cyclePlanInfo.setReaCodeGd(reaCodeInfo.getGuid());
        }else {
            throw new SystemException("xx","原因代码不存在");
        }

        FileVerInfo fileVerInfo=fileVerDAO.SelectByfileVerRd(saveCyclePlanInfoReq02.getFileVerRd());
        if(fileVerInfo!=null){
            cyclePlanInfo.setFileVerGd(fileVerInfo.getGuid());
        }else {
            throw new SystemException("xx","文件不存在");
        }
        cyclePlanInfo.setReference(saveCyclePlanInfoReq02.getReference());
        cyclePlanInfo.setStarDate(DateUtil.format2(saveCyclePlanInfoReq02.getStarDate()));

        cyclePlanInfo.setEndDate(DateUtil.format2(saveCyclePlanInfoReq02.getEndDate()));
        cyclePlanInfo.setmTType(saveCyclePlanInfoReq02.getMTType());
        cyclePlanInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        cyclePlanInfo.setLastModifyTime(new Date());
        cyclePlanInfo.setRemark(saveCyclePlanInfoReq02.getRemark());
        cyclePlanInfo.setCreateTime(new Date());
       if(cyclePlanDAO.updateCyclePlanInfo(cyclePlanInfo)<=0){
           throw new SystemException("xxx","修改失败");
       }




        CyclePlanTiggerRulelnfo cyclePlanTiggerRulelnfo=cyclePlanTiggerRuleDAO.selectCyclePlanTiggerRulelnfoByCyclePlanGd(cyclePlanInfo.getGuid());
        cyclePlanTiggerRulelnfo.setRuid(cyclePlanTiggerRulelnfo.getRuid());
        cyclePlanTiggerRulelnfo.setGuid(CommonUtils.getRandomNumber());
        cyclePlanTiggerRulelnfo.setCyclePlanGd(cyclePlanInfo.getGuid());
        cyclePlanTiggerRulelnfo.setTimeType(saveCyclePlanInfoReq02.getDayInfo().getTimeType());
        cyclePlanTiggerRulelnfo.setTimeContent(saveCyclePlanInfoReq02.getDayInfo().getTimeContent());
        cyclePlanTiggerRulelnfo.setWeekContent(saveCyclePlanInfoReq02.getWeekInfo().getWeekContent());
        cyclePlanTiggerRulelnfo.setMonthContent(saveCyclePlanInfoReq02.getMonthInfo().getMonthContent());
        cyclePlanTiggerRulelnfo.setYearContent(saveCyclePlanInfoReq02.getYearInfo().getYearContent());
        cyclePlanTiggerRulelnfo.setDesContent(saveCyclePlanInfoReq02.getDesInfo().getDesContent());
        cyclePlanTiggerRulelnfo.setQuarterContent(saveCyclePlanInfoReq02.getQuarterInfo().getQuarterContent());
        cyclePlanTiggerRulelnfo.setUpTime(saveCyclePlanInfoReq02.getUpTime()==null||"".equals(saveCyclePlanInfoReq02.getUpTime())?0.0f:saveCyclePlanInfoReq02.getUpTime());
        cyclePlanTiggerRulelnfo.setDownTime(saveCyclePlanInfoReq02.getDownTime()==null||"".equals(saveCyclePlanInfoReq02.getDownTime())?0.0f:saveCyclePlanInfoReq02.getDownTime());
        cyclePlanTiggerRulelnfo.setPresetTime(saveCyclePlanInfoReq02.getPresetTime()==null||"".equals(saveCyclePlanInfoReq02.getPresetTime())?0.0f:saveCyclePlanInfoReq02.getPresetTime());

        //提前邮件提醒
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCyclePlanInfoReq02.getPresetEmailDistributionRd());
        if(emailDistributionInfo!=null){
            cyclePlanTiggerRulelnfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }else {
            cyclePlanTiggerRulelnfo.setPresetEmailDistributionGd("");
        }
        //提前邮件内容提醒
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveCyclePlanInfoReq02.getPresetEmailMessageRd());
        if(emailMessageInfo!=null){
            cyclePlanTiggerRulelnfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }else {
            cyclePlanTiggerRulelnfo.setPresetEmailMessageGd("");
        }
        //到期
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCyclePlanInfoReq02.getStartEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            cyclePlanTiggerRulelnfo.setStartEmailDistributionGd(emailDistributionInfo1.getGuid());
        }else {
            cyclePlanTiggerRulelnfo.setStartEmailDistributionGd("");
        }
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveCyclePlanInfoReq02.getStartEmailMessageRd());
        if(emailMessageInfo1!=null){
            cyclePlanTiggerRulelnfo.setStartEmailMessageGd(emailMessageInfo1.getGuid());
        }else {
            cyclePlanTiggerRulelnfo.setStartEmailMessageGd("");
        }
        //cyclePlanTiggerRulelnfo.setOverTimeAction(saveCyclePlanInfoReq02.getOverTimeAction());

        //过期
        EmailDistributionInfo emailDistributionInfo2=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCyclePlanInfoReq02.getOverEmailDistributionRd());
        if(emailDistributionInfo2!=null){
            cyclePlanTiggerRulelnfo.setOverdueEmailDistributionGd(emailDistributionInfo2.getGuid());
        }else {
            cyclePlanTiggerRulelnfo.setOverdueEmailDistributionGd("");
        }
        EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageByID(saveCyclePlanInfoReq02.getOverEmailMessageRd());
        if(emailMessageInfo2!=null){
            cyclePlanTiggerRulelnfo.setOverdueEmailMessageGd(emailMessageInfo2.getGuid());
        }else {
            cyclePlanTiggerRulelnfo.setOverdueEmailMessageGd("");
        }
        cyclePlanTiggerRulelnfo.setCreator(CommonUtils.readUser().getRealName());
        cyclePlanTiggerRulelnfo.setCreateTime(new Date());
        if(cyclePlanTiggerRuleDAO.updateCyclePlanTiggerRulelnfo(cyclePlanTiggerRulelnfo)<=0){
            throw new SystemException("xxx","修改规则信息失败");
        }


        List<CyclePlanDtllnfo>  cyclePlanDtllnfos=cyclePlanDtlDAO.selectCyclePlanDtllnfoByCyclePlanGd(cyclePlanInfo.getGuid());
        if(cyclePlanDtllnfos!=null&&cyclePlanDtllnfos.size()>0){
            for(CyclePlanDtllnfo cyclePlanDtllnfo:cyclePlanDtllnfos){
                if(cyclePlanDtlDAO.deleteCyclePlanDtllnfo(cyclePlanDtllnfo.getRuid())<=0){
                    throw new SystemException("xxx","删除细表信息失败");
                }
            }
        }
        for(SaveCyclePlanInfoReq02CyclePlanDtl saveCyclePlanInfoReq02CyclePlanDtl:saveCyclePlanInfoReq02.getCyclePlanDtlInfo()){
            CyclePlanDtllnfo cyclePlanDtllnfo=new CyclePlanDtllnfo();
            TaskInfo taskInfo=taskDAO.selectTaskInfoByRuid(saveCyclePlanInfoReq02CyclePlanDtl.getTaskRd());
            if(taskInfo!=null){
                cyclePlanDtllnfo.setTaskGd(taskInfo.getGuid());
            }
            if(!Pattern.compile("^[0-9]*[1-9][0-9]*$").matcher(saveCyclePlanInfoReq02CyclePlanDtl.getMaxExCount()).matches()){
                throw new SystemException("xxx", "修改失败，最大执行次数请输入正整数");
            }
            cyclePlanDtllnfo.setCreator(CommonUtils.readUser().getRealName());
            cyclePlanDtllnfo.setCreateTime(new Date());
            cyclePlanDtllnfo.setGuid(CommonUtils.getRandomNumber());
            cyclePlanDtllnfo.setCyclePlanGd(cyclePlanInfo.getGuid());
            cyclePlanDtllnfo.setTaskName(saveCyclePlanInfoReq02CyclePlanDtl.getTaskName());
            cyclePlanDtllnfo.setTaskItemName(saveCyclePlanInfoReq02CyclePlanDtl.getTaskItemName());
            cyclePlanDtllnfo.setSureType(saveCyclePlanInfoReq02CyclePlanDtl.getSureType());
         /*   cyclePlanDtllnfo.setMinExCount(saveCyclePlanInfoReq02CyclePlanDtl.getMinExCount());*/
            cyclePlanDtllnfo.setMaxExCount(Integer.valueOf(saveCyclePlanInfoReq02CyclePlanDtl.getMaxExCount()));
            cyclePlanDtlDAO.insertCyclePlanDtllnfo(cyclePlanDtllnfo);
        }

        saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
        return saveCyclePlanInfoRes;
    }

    //获取单个
    @Override
    public GetCyclePlanInfoRes GetGetCyclePlanInfoRes(GetCyclePlanInfoReq00 getCyclePlanInfoReq00) {
        GetCyclePlanInfoRes getCyclePlanInfoRes=new GetCyclePlanInfoRes();
        GetCyclePlanInfoResB getCyclePlanInfoResB=new GetCyclePlanInfoResB();
        GetCyclePlanInfoResD getCyclePlanInfoResD=new GetCyclePlanInfoResD();
        if(getCyclePlanInfoReq00.getCyclePlanRd()<=0){
            throw new SystemException("xxx","查询失败，该标识不能为空");
        }
        CyclePlanInfo cyclePlanInfo=cyclePlanDAO.selectCyclePlanInfoByRuid(getCyclePlanInfoReq00.getCyclePlanRd());
        if(cyclePlanInfo==null){
            throw new SystemException("xxx","查询失败，该信息不存在");
        }
        getCyclePlanInfoResD.setCyclePlanRd(cyclePlanInfo.getRuid());
        getCyclePlanInfoResD.setCyclePlanName(cyclePlanInfo.getCyclePlanName());
        getCyclePlanInfoResD.setStatus(cyclePlanInfo.getStatus());
        getCyclePlanInfoResD.setDescription(cyclePlanInfo.getDescription());
        getCyclePlanInfoResD.setReference(cyclePlanInfo.getReference());
        GetCyclePlanInfoResDRea getCyclePlanInfoResDRea=new GetCyclePlanInfoResDRea();
        ReaCodeInfo reaCodeInfo=reasonDAO.SelectReacodeInfoByguid(cyclePlanInfo.getReaCodeGd());
        if(reaCodeInfo!=null){
            getCyclePlanInfoResDRea.setReaRd(reaCodeInfo.getRuid());
            getCyclePlanInfoResDRea.setReaDes(reaCodeInfo.getReaDes());
            getCyclePlanInfoResD.setReaInfo(getCyclePlanInfoResDRea);
        }

        GetCyclePlanInfoResDFile getCyclePlanInfoResDFile=new GetCyclePlanInfoResDFile();
        FileVerInfo fileVerInfo=fileVerDAO.SelectByFileVerGd(cyclePlanInfo.getFileVerGd());
        if(fileVerInfo!=null){
            getCyclePlanInfoResDFile.setFileVerRd(fileVerInfo.getRuid());
            getCyclePlanInfoResDFile.setFileName(fileVerInfo.getFileName());
            getCyclePlanInfoResD.setFileInfo(getCyclePlanInfoResDFile);
        }

        getCyclePlanInfoResD.setStarDate(DateUtil.formatPattern2(cyclePlanInfo.getStarDate()));
        getCyclePlanInfoResD.setEndDate(DateUtil.formatPattern2(cyclePlanInfo.getEndDate()));
        getCyclePlanInfoResD.setMTType(cyclePlanInfo.getmTType());

        GetCyclePlanInfoResDDay  getCyclePlanInfoResDDay=new GetCyclePlanInfoResDDay();

        CyclePlanTiggerRulelnfo cyclePlanTiggerRulelnfo=cyclePlanTiggerRuleDAO.selectCyclePlanTiggerRulelnfoByCyclePlanGd(cyclePlanInfo.getGuid());
        if(cyclePlanTiggerRulelnfo!=null){
            getCyclePlanInfoResDDay.setTimeContent(cyclePlanTiggerRulelnfo.getTimeContent());
            getCyclePlanInfoResDDay.setTimeType(cyclePlanTiggerRulelnfo.getTimeType());
        }
        getCyclePlanInfoResD.setDayInfo(getCyclePlanInfoResDDay);

        GetCyclePlanInfoResDWeek getCyclePlanInfoResDWeek=new GetCyclePlanInfoResDWeek();
        getCyclePlanInfoResDWeek.setWeekContent(cyclePlanTiggerRulelnfo.getWeekContent());
        getCyclePlanInfoResD.setWeekInfo(getCyclePlanInfoResDWeek);

        GetCyclePlanInfoResDMonth getCyclePlanInfoResDMonth=new GetCyclePlanInfoResDMonth();
        getCyclePlanInfoResDMonth.setMonthContent(cyclePlanTiggerRulelnfo.getMonthContent());
        getCyclePlanInfoResD.setMonthInfo(getCyclePlanInfoResDMonth);

        GetCyclePlanInfoResDYear getCyclePlanInfoResDYear=new GetCyclePlanInfoResDYear();
        getCyclePlanInfoResDYear.setYearContent(cyclePlanTiggerRulelnfo.getYearContent());
        getCyclePlanInfoResD.setYearInfo(getCyclePlanInfoResDYear);

        GetCyclePlanInfoResDDes getCyclePlanInfoResDDes=new GetCyclePlanInfoResDDes();
        getCyclePlanInfoResDDes.setDesContent(cyclePlanTiggerRulelnfo.getDesContent());
        getCyclePlanInfoResD.setDesInfo(getCyclePlanInfoResDDes);

        GetCyclePlanInfoResDQuarter getCyclePlanInfoResDQuarter=new GetCyclePlanInfoResDQuarter();
        getCyclePlanInfoResDQuarter.setQuarterContent(cyclePlanTiggerRulelnfo.getQuarterContent());
        getCyclePlanInfoResD.setQuarterInfo(getCyclePlanInfoResDQuarter);
        getCyclePlanInfoResD.setUpTime(cyclePlanTiggerRulelnfo.getUpTime());
        getCyclePlanInfoResD.setDownTime(cyclePlanTiggerRulelnfo.getDownTime());
        getCyclePlanInfoResD.setPresetTime(cyclePlanTiggerRulelnfo.getPresetTime());

        GetCyclePlanInfoResDPresetEmailDistribution getCyclePlanInfoResDPresetEmailDistribution=new GetCyclePlanInfoResDPresetEmailDistribution();
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByGuid(cyclePlanTiggerRulelnfo.getPresetEmailDistributionGd());
        if(emailDistributionInfo!=null) {
            getCyclePlanInfoResDPresetEmailDistribution.setEmailDistributionRd(emailDistributionInfo.getRuid());
            getCyclePlanInfoResDPresetEmailDistribution.setEmailDistributionName(emailDistributionInfo.getDistributionName());
        }
        getCyclePlanInfoResD.setPresetEmailDistributionInfo(getCyclePlanInfoResDPresetEmailDistribution);

        GetCyclePlanInfoResDPresetEmailMessage getCyclePlanInfoResDPresetEmailMessage=new GetCyclePlanInfoResDPresetEmailMessage();
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageInfoByGuid(cyclePlanTiggerRulelnfo.getPresetEmailMessageGd());
        if(emailMessageInfo!=null){
            getCyclePlanInfoResDPresetEmailMessage.setEmailMessageRd(emailMessageInfo.getRuid());
            getCyclePlanInfoResDPresetEmailMessage.setEmailMessageName(emailMessageInfo.getEmailName());
        }
        getCyclePlanInfoResD.setPresetEmailMessageInfo(getCyclePlanInfoResDPresetEmailMessage);

        GetCyclePlanInfoResDStartEmailDistribution getCyclePlanInfoResDStartEmailDistribution=new GetCyclePlanInfoResDStartEmailDistribution();
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByGuid(cyclePlanTiggerRulelnfo.getStartEmailDistributionGd());
        if(emailDistributionInfo1!=null) {
            getCyclePlanInfoResDStartEmailDistribution.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            getCyclePlanInfoResDStartEmailDistribution.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
        }
        getCyclePlanInfoResD.setStartEmailDistributionInfo(getCyclePlanInfoResDStartEmailDistribution);

        GetCyclePlanInfoResDStartEmailMessage getCyclePlanInfoResDStartEmailMessage=new GetCyclePlanInfoResDStartEmailMessage();
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageInfoByGuid(cyclePlanTiggerRulelnfo.getStartEmailMessageGd());
        if(emailMessageInfo1!=null){
            getCyclePlanInfoResDStartEmailMessage.setEmailMessageRd(emailMessageInfo1.getRuid());
            getCyclePlanInfoResDStartEmailMessage.setEmailMessageName(emailMessageInfo1.getEmailName());
        }
        getCyclePlanInfoResD.setStartEmailMessageInfo(getCyclePlanInfoResDStartEmailMessage);

        //getCyclePlanInfoResD.setOverTimeAction(cyclePlanTiggerRulelnfo.getOverTimeAction());

        GetCyclePlanInfoResDOverdueEmailDistribution getCyclePlanInfoResDOverdueEmailDistribution=new GetCyclePlanInfoResDOverdueEmailDistribution();
        EmailDistributionInfo emailDistributionInfo2=emailDistributionDAO.SelectEmailDistributionInfoByGuid(cyclePlanTiggerRulelnfo.getOverdueEmailDistributionGd());
        if(emailDistributionInfo2!=null) {
            getCyclePlanInfoResDOverdueEmailDistribution.setEmailDistributionRd(emailDistributionInfo2.getRuid());
            getCyclePlanInfoResDOverdueEmailDistribution.setEmailDistributionName(emailDistributionInfo2.getDistributionName());
        }
        getCyclePlanInfoResD.setOverdueEmailDistributionInfo(getCyclePlanInfoResDOverdueEmailDistribution);


        GetCyclePlanInfoResDOverdueEmailMessage getCyclePlanInfoResDOverdueEmailMessage=new GetCyclePlanInfoResDOverdueEmailMessage();
        EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageInfoByGuid(cyclePlanTiggerRulelnfo.getOverdueEmailMessageGd());
        if(emailMessageInfo2!=null){
            getCyclePlanInfoResDOverdueEmailMessage.setEmailMessageRd(emailMessageInfo2.getRuid());
            getCyclePlanInfoResDOverdueEmailMessage.setEmailMessageName(emailMessageInfo2.getEmailName());
        }
        getCyclePlanInfoResD.setOverdueEmailMessageInfo(getCyclePlanInfoResDOverdueEmailMessage);
        getCyclePlanInfoResD.setCreator(cyclePlanInfo.getCreator());
        getCyclePlanInfoResD.setCreateTime(DateUtil.format(cyclePlanInfo.getCreateTime()));
        getCyclePlanInfoResD.setLastModifyMan(cyclePlanInfo.getLastModifyMan());
        getCyclePlanInfoResD.setLastModifyTime(DateUtil.format(cyclePlanInfo.getLastModifyTime()));
        getCyclePlanInfoResD.setRemark(cyclePlanInfo.getRemark());

        List<GetCyclePlanInfoResDCyclePlanDtl> getCyclePlanInfoResDCyclePlanDtls=new ArrayList<GetCyclePlanInfoResDCyclePlanDtl>();
        List<CyclePlanDtllnfo> cyclePlanDtllnfos=cyclePlanDtlDAO.selectCyclePlanDtllnfoByCyclePlanGd(cyclePlanInfo.getGuid());
        if(cyclePlanDtllnfos!=null&&cyclePlanDtllnfos.size()>0){
            for(CyclePlanDtllnfo cyclePlanDtllnfo:cyclePlanDtllnfos){
                GetCyclePlanInfoResDCyclePlanDtl getCyclePlanInfoResDCyclePlanDtl=new GetCyclePlanInfoResDCyclePlanDtl();
                getCyclePlanInfoResDCyclePlanDtl.setCyclePlanDtlRd(cyclePlanDtllnfo.getRuid());
                TaskInfo taskInfo=taskDAO.selectTaskInfoByGuid(cyclePlanDtllnfo.getTaskGd());
                if(taskInfo!=null){
                    getCyclePlanInfoResDCyclePlanDtl.setTaskRd(taskInfo.getRuid());
                }
                getCyclePlanInfoResDCyclePlanDtl.setTaskName(cyclePlanDtllnfo.getTaskName());
                getCyclePlanInfoResDCyclePlanDtl.setTaskItemName(cyclePlanDtllnfo.getTaskItemName());
                getCyclePlanInfoResDCyclePlanDtl.setSureType(cyclePlanDtllnfo.getSureType());
    /*            getCyclePlanInfoResDCyclePlanDtl.setMinExCount(cyclePlanDtllnfo.getMinExCount());*/
                getCyclePlanInfoResDCyclePlanDtl.setMaxExCount(cyclePlanDtllnfo.getMaxExCount());
                getCyclePlanInfoResDCyclePlanDtls.add(getCyclePlanInfoResDCyclePlanDtl);
            }
            getCyclePlanInfoResD.setCyclePlanDtlInfo(getCyclePlanInfoResDCyclePlanDtls);
        }

        getCyclePlanInfoResB.setData(getCyclePlanInfoResD);
        getCyclePlanInfoRes.setBody(getCyclePlanInfoResB);
        return getCyclePlanInfoRes;
    }
}
