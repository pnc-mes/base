package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoRes;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoResB;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoResD;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetCheckPlanInfo.*;
import pnc.mesadmin.dto.SaveCheckPlanInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.CheckPlanIService;
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
 * @Date: 2018/9/10 09:07
 * @Description:
 */
@Transactional
@Service
public class CheckPlanService implements CheckPlanIService {
    @Resource
    private BaseIService baseIService;

    @Resource
    private CheckPlanDAO checkPlanDAO;

    @Resource
    private CheckPlanDtlDAO checkPlanDtlDAO;

    @Resource
    private CheckPlanTiggerRuleDAO checkPlanTiggerRuleDAO;

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
    private DevCheckPlanDAO devCheckPlanDAO;

    @Resource
    private CarrierCheckPlanInfoDAO carrierCheckPlanInfoDAO;

    @Resource
    private ToolCheckPlanInfoDAO toolCheckPlanInfoDAO;

    @Resource
    private DoCheckInfoDAO doCheckInfoDAO;

    //列表
    @Override
    public GetAllCheckPlanInfoRes QALLGetAllCheckPlanInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllCheckPlanInfoRes getAllCheckPlanInfoRes = new GetAllCheckPlanInfoRes();
        GetAllCheckPlanInfoResB getAllCheckPlanInfoResB = new GetAllCheckPlanInfoResB();
        List<GetAllCheckPlanInfoResD> getAllCyclePlanInfoResDS = new ArrayList<GetAllCheckPlanInfoResD>();
        List<CheckPlanInfo> checkPlanInfos = baseIService.QALLBaseInfo(CheckPlanDAO.class, "selectAllCheckPlanInfo", argInitDataFields, argPageInfo);
        if (checkPlanInfos != null && checkPlanInfos.size() > 0) {
            for (CheckPlanInfo checkPlanInfo : checkPlanInfos) {
                GetAllCheckPlanInfoResD getAllCyclePlanInfoResD = new GetAllCheckPlanInfoResD();
                getAllCyclePlanInfoResD.setCheckPlanRd(checkPlanInfo.getRuid());
                getAllCyclePlanInfoResD.setCheckPlanName(checkPlanInfo.getCheckPlanName());
                getAllCyclePlanInfoResDS.add(getAllCyclePlanInfoResD);
            }
        }

        getAllCheckPlanInfoResB.setData(getAllCyclePlanInfoResDS);
        getAllCheckPlanInfoRes.setBody(getAllCheckPlanInfoResB);
        return getAllCheckPlanInfoRes;
    }

    @Override
    public PageResult<GetAllCheckPlanInfoResD> QALLGetAllCheckPlanNewRes(BaseRequest req) {
        List<GetAllCheckPlanInfoResD> resDList = new ArrayList<GetAllCheckPlanInfoResD>();
        GetAllCheckPlanInfoResD resD = null;

        IPage<CheckPlanInfo> iPage = checkPlanDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));
        //赋值dto并返回
        for (CheckPlanInfo obj : iPage.getRecords()) {
            resD = new GetAllCheckPlanInfoResD();
            resD.setCheckPlanRd(obj.getRuid());
            resD.setCheckPlanName(obj.getCheckPlanName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }

    //单个
    @Override
    public GetCheckPlanInfoRes GetGetCheckPlanInfoRes(GetCheckPlanInfoReq00 getCheckPlanInfoReq00) {
        GetCheckPlanInfoRes getCheckPlanInfoRes = new GetCheckPlanInfoRes();
        GetCheckPlanInfoResB getCheckPlanInfoResB = new GetCheckPlanInfoResB();
        GetCheckPlanInfoResD getCheckPlanInfoResD = new GetCheckPlanInfoResD();
        if (getCheckPlanInfoReq00.getCheckPlanRd() <= 0) {
            throw new SystemException("xxx", "查询失败，该标识不能为空");
        }
        CheckPlanInfo checkPlanInfo = checkPlanDAO.selectCheckPlanInfoByRuid(getCheckPlanInfoReq00.getCheckPlanRd());
        if (checkPlanInfo == null) {
            throw new SystemException("xxx", "查询失败，该信息不存在");
        }

        getCheckPlanInfoResD.setCheckPlanRd(checkPlanInfo.getRuid());
        getCheckPlanInfoResD.setCheckPlanName(checkPlanInfo.getCheckPlanName());
        getCheckPlanInfoResD.setStatus(checkPlanInfo.getStatus());
        getCheckPlanInfoResD.setDescription(checkPlanInfo.getDescription());
        getCheckPlanInfoResD.setCheckPlanType(checkPlanInfo.getCheckPlanType());
        getCheckPlanInfoResD.setReference(checkPlanInfo.getReference());
        getCheckPlanInfoResD.setStarDate(DateUtil.formatPattern2(checkPlanInfo.getStarDate()));
        getCheckPlanInfoResD.setEndDate(DateUtil.formatPattern2(checkPlanInfo.getEndDate()));
        getCheckPlanInfoResD.setMTType(checkPlanInfo.getmTType());

        GetCheckPlanInfoResDDay getCheckPlanInfoResDDay = new GetCheckPlanInfoResDDay();

        CheckPlanTiggerRulelnfo checkPlanTiggerRulelnfo = checkPlanTiggerRuleDAO.selectCheckPlanTiggerRulelnfoByCheckPlanGd(checkPlanInfo.getGuid());
        if (checkPlanTiggerRulelnfo != null) {
            getCheckPlanInfoResDDay.setTimeContent(checkPlanTiggerRulelnfo.getTimeContent());
            getCheckPlanInfoResDDay.setTimeType(checkPlanTiggerRulelnfo.getTimeType());
        }
        getCheckPlanInfoResD.setDayInfo(getCheckPlanInfoResDDay);
        getCheckPlanInfoResD.setTimeType(checkPlanTiggerRulelnfo.getTimeType());
        GetCheckPlanInfoResDWeek getCheckPlanInfoResDWeek = new GetCheckPlanInfoResDWeek();
        getCheckPlanInfoResDWeek.setWeekContent(checkPlanTiggerRulelnfo.getWeekContent());
        getCheckPlanInfoResD.setWeekInfo(getCheckPlanInfoResDWeek);

        GetCheckPlanInfoResDMonth getCyclePlanInfoResDMonth = new GetCheckPlanInfoResDMonth();
        getCyclePlanInfoResDMonth.setMonthContent(checkPlanTiggerRulelnfo.getMonthContent());
        getCheckPlanInfoResD.setMonthInfo(getCyclePlanInfoResDMonth);

        GetCheckPlanInfoResDYear getCheckPlanInfoResDYear = new GetCheckPlanInfoResDYear();
        getCheckPlanInfoResDYear.setYearContent(checkPlanTiggerRulelnfo.getYearContent());
        getCheckPlanInfoResD.setYearInfo(getCheckPlanInfoResDYear);

        GetCheckPlanInfoResDDes getCheckPlanInfoResDDes = new GetCheckPlanInfoResDDes();
        getCheckPlanInfoResDDes.setDesContent(checkPlanTiggerRulelnfo.getDesContent());
        getCheckPlanInfoResD.setDesInfo(getCheckPlanInfoResDDes);
      //保养作业书
        GetCheckPlanInfoResFileInfo GetCheckPlanInfoResFileInfo = new GetCheckPlanInfoResFileInfo();
        FileVerInfo FileVerInfo =  fileVerDAO.SelectByFileVerGd(checkPlanInfo.getFileVerGd());
        if(FileVerInfo!=null){
            GetCheckPlanInfoResFileInfo.setFileVerRd(String.valueOf(FileVerInfo.getRuid()));
            GetCheckPlanInfoResFileInfo.setFileName(FileVerInfo.getFileName());
            getCheckPlanInfoResD.setFileInfo(GetCheckPlanInfoResFileInfo);
        }


        GetCheckPlanInfoResDQuarter getCheckPlanInfoResDQuarter = new GetCheckPlanInfoResDQuarter();
        getCheckPlanInfoResDQuarter.setQuarterContent(checkPlanTiggerRulelnfo.getQuarterContent());
        getCheckPlanInfoResD.setQuarterInfo(getCheckPlanInfoResDQuarter);
        getCheckPlanInfoResD.setUpTime(checkPlanTiggerRulelnfo.getUpTime());
        getCheckPlanInfoResD.setDownTime(checkPlanTiggerRulelnfo.getDownTime());
        getCheckPlanInfoResD.setPresetTime(checkPlanTiggerRulelnfo.getPresetTime());

        GetCheckPlanInfoResDPresetEmailDistribution getCheckPlanInfoResDPresetEmailDistribution = new GetCheckPlanInfoResDPresetEmailDistribution();
        EmailDistributionInfo emailDistributionInfo = emailDistributionDAO.SelectEmailDistributionInfoByGuid(checkPlanTiggerRulelnfo.getPresetEmailDistributionGd());
        if (emailDistributionInfo != null) {
            getCheckPlanInfoResDPresetEmailDistribution.setEmailDistributionRd(emailDistributionInfo.getRuid());
            getCheckPlanInfoResDPresetEmailDistribution.setEmailDistributionName(emailDistributionInfo.getDistributionName());
        }
        getCheckPlanInfoResD.setPresetEmailDistributionInfo(getCheckPlanInfoResDPresetEmailDistribution);

        GetCheckPlanInfoResDPresetEmailMessage getCheckPlanInfoResDPresetEmailMessage = new GetCheckPlanInfoResDPresetEmailMessage();
        EmailMessageInfo emailMessageInfo = emailMessageDAO.SelectEmailMessageInfoByGuid(checkPlanTiggerRulelnfo.getPresetEmailMessageGd());
        if (emailMessageInfo != null) {
            getCheckPlanInfoResDPresetEmailMessage.setEmailMessageRd(emailMessageInfo.getRuid());
            getCheckPlanInfoResDPresetEmailMessage.setEmailMessageName(emailMessageInfo.getEmailName());
        }
        getCheckPlanInfoResD.setPresetEmailMessageInfo(getCheckPlanInfoResDPresetEmailMessage);

        GetCheckPlanInfoResDStartEmailDistribution getCheckPlanInfoResDStartEmailDistribution = new GetCheckPlanInfoResDStartEmailDistribution();
        EmailDistributionInfo emailDistributionInfo1 = emailDistributionDAO.SelectEmailDistributionInfoByGuid(checkPlanTiggerRulelnfo.getStartEmailDistributionGd());
        if (emailDistributionInfo1 != null) {
            getCheckPlanInfoResDStartEmailDistribution.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            getCheckPlanInfoResDStartEmailDistribution.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
        }
        getCheckPlanInfoResD.setStartEmailDistributionInfo(getCheckPlanInfoResDStartEmailDistribution);

        GetCheckPlanInfoResDStartEmailMessage getCheckPlanInfoResDStartEmailMessage = new GetCheckPlanInfoResDStartEmailMessage();
        EmailMessageInfo emailMessageInfo1 = emailMessageDAO.SelectEmailMessageInfoByGuid(checkPlanTiggerRulelnfo.getStartEmailMessageGd());
        if (emailMessageInfo1 != null) {
            getCheckPlanInfoResDStartEmailMessage.setEmailMessageRd(emailMessageInfo1.getRuid());
            getCheckPlanInfoResDStartEmailMessage.setEmailMessageName(emailMessageInfo1.getEmailName());
        }
        getCheckPlanInfoResD.setStartEmailMessageInfo(getCheckPlanInfoResDStartEmailMessage);


        GetCheckPlanInfoResDOverdueEmailDistribution getCheckPlanInfoResDOverdueEmailDistribution = new GetCheckPlanInfoResDOverdueEmailDistribution();
        EmailDistributionInfo emailDistributionInfo2 = emailDistributionDAO.SelectEmailDistributionInfoByGuid(checkPlanTiggerRulelnfo.getOverdueEmailDistributionGd());
        if (emailDistributionInfo2 != null) {
            getCheckPlanInfoResDOverdueEmailDistribution.setEmailDistributionRd(emailDistributionInfo2.getRuid());
            getCheckPlanInfoResDOverdueEmailDistribution.setEmailDistributionName(emailDistributionInfo2.getDistributionName());
        }
        getCheckPlanInfoResD.setOverdueEmailDistributionInfo(getCheckPlanInfoResDOverdueEmailDistribution);


        GetCheckPlanInfoResDOverdueEmailMessage getCheckPlanInfoResDOverdueEmailMessage = new GetCheckPlanInfoResDOverdueEmailMessage();
        EmailMessageInfo emailMessageInfo2 = emailMessageDAO.SelectEmailMessageInfoByGuid(checkPlanTiggerRulelnfo.getOverdueEmailMessageGd());
        if (emailMessageInfo2 != null) {
            getCheckPlanInfoResDOverdueEmailMessage.setEmailMessageRd(emailMessageInfo2.getRuid());
            getCheckPlanInfoResDOverdueEmailMessage.setEmailMessageName(emailMessageInfo2.getEmailName());
        }
        getCheckPlanInfoResD.setOverdueEmailMessageInfo(getCheckPlanInfoResDOverdueEmailMessage);
        getCheckPlanInfoResD.setCreator(checkPlanInfo.getCreator());
        getCheckPlanInfoResD.setCreateTime(DateUtil.format(checkPlanInfo.getCreateTime()));
        getCheckPlanInfoResD.setLastModifyMan(checkPlanInfo.getLastModifyMan());
        getCheckPlanInfoResD.setLastModifyTime(DateUtil.format(checkPlanInfo.getLastModifyTime()));
        getCheckPlanInfoResD.setRemark(checkPlanInfo.getRemark());

        List<GetCheckPlanInfoDCheckPlanDtl> getCheckPlanInfoDCheckPlanDtls = new ArrayList<GetCheckPlanInfoDCheckPlanDtl>();
        List<CheckPlanDtllnfo> checkPlanDtllnfos = checkPlanDtlDAO.selectCheckPlanDtllnfoByCheckPlanGd(checkPlanInfo.getGuid());
        if (checkPlanDtllnfos != null && checkPlanDtllnfos.size() > 0) {
            for (CheckPlanDtllnfo checkPlanDtllnfo : checkPlanDtllnfos) {
                GetCheckPlanInfoDCheckPlanDtl getCheckPlanInfoDCheckPlanDtl = new GetCheckPlanInfoDCheckPlanDtl();
                getCheckPlanInfoDCheckPlanDtl.setCheckPlanDtlRd(checkPlanDtllnfo.getRuid());
                TaskInfo taskInfo = taskDAO.selectTaskInfoByGuid(checkPlanDtllnfo.getTaskGd());
                if (taskInfo != null) {
                    getCheckPlanInfoDCheckPlanDtl.setTaskRd(taskInfo.getRuid());
                }
/*                getCheckPlanInfoDCheckPlanDtl.setTaskName(checkPlanDtllnfo.getTaskName());*/
                getCheckPlanInfoDCheckPlanDtl.setTaskItemName(checkPlanDtllnfo.getTaskItemName());
                getCheckPlanInfoDCheckPlanDtl.setSureType(checkPlanDtllnfo.getSureType());
           /*     getCheckPlanInfoDCheckPlanDtl.setMinExCount(checkPlanDtllnfo.getMinExCount());*/
                getCheckPlanInfoDCheckPlanDtl.setMaxExCount(checkPlanDtllnfo.getMaxExCount());
                getCheckPlanInfoDCheckPlanDtls.add(getCheckPlanInfoDCheckPlanDtl);
            }
            getCheckPlanInfoResD.setCheckPlanDtlInfo(getCheckPlanInfoDCheckPlanDtls);
        }

        getCheckPlanInfoResB.setData(getCheckPlanInfoResD);
        getCheckPlanInfoRes.setBody(getCheckPlanInfoResB);
        return getCheckPlanInfoRes;
    }

    //保存
    @Override
    public SaveCheckPlanInfoRes AddSaveCheckPlanInfoRes(SaveCheckPlanInfoReq00 saveCheckPlanInfoReq00) {
        SaveCheckPlanInfoRes saveCheckPlanInfoRes = new SaveCheckPlanInfoRes();
        SaveCheckPlanInfoResB saveCheckPlanInfoResB = new SaveCheckPlanInfoResB();

        if (saveCheckPlanInfoReq00.getCheckPlanName() == null || "".equals(saveCheckPlanInfoReq00.getCheckPlanName())) {
            throw new SystemException("xxx", "新增失败，名称不能为空");
        }

        if (saveCheckPlanInfoReq00.getCheckPlanType()== null || "".equals(saveCheckPlanInfoReq00.getCheckPlanType())) {
            throw new SystemException("xxx", "新增失败，点检计划类型不能为空");
        }

        if (saveCheckPlanInfoReq00.getStarDate() == null || "".equals(saveCheckPlanInfoReq00.getStarDate())) {
            throw new SystemException("xxx", "新增失败，起算日期不能为空");
        }

        if (saveCheckPlanInfoReq00.getEndDate() == null || "".equals(saveCheckPlanInfoReq00.getEndDate())) {
            throw new SystemException("xxx", "新增失败，截止日期不能为空");
        }

        if (saveCheckPlanInfoReq00.getMTType() == null || "".equals(saveCheckPlanInfoReq00.getMTType())) {
            throw new SystemException("xxx", "新增失败，保养周期不能为空");
        }

        if (saveCheckPlanInfoReq00.getCheckPlanDtlInfo().size() <= 0) {
            throw new SystemException("xxx", "新增失败，保养内容清单不能为空");
        }

        CheckPlanInfo checkPlanInfo1 = checkPlanDAO.selectCheckPlanInfoByCheckPlanName(saveCheckPlanInfoReq00.getCheckPlanName());
        if (checkPlanInfo1 != null) {
            throw new SystemException("xxx", "新增失败，名称已存在");
        }


        CheckPlanInfo checkPlanInfo = new CheckPlanInfo();
        checkPlanInfo.setGuid(CommonUtils.getRandomNumber());
        checkPlanInfo.setCheckPlanName(saveCheckPlanInfoReq00.getCheckPlanName());
        checkPlanInfo.setStatus(saveCheckPlanInfoReq00.getStatus());
        checkPlanInfo.setReference(saveCheckPlanInfoReq00.getReference());
        checkPlanInfo.setDescription(saveCheckPlanInfoReq00.getDescription());
        checkPlanInfo.setCheckPlanType(saveCheckPlanInfoReq00.getCheckPlanType());
        checkPlanInfo.setStarDate(DateUtil.format2(saveCheckPlanInfoReq00.getStarDate()));
        checkPlanInfo.setEndDate(DateUtil.format2(saveCheckPlanInfoReq00.getEndDate()));
        checkPlanInfo.setmTType(saveCheckPlanInfoReq00.getMTType());
        checkPlanInfo.setCreator(CommonUtils.readUser().getRealName());
        checkPlanInfo.setRemark(saveCheckPlanInfoReq00.getRemark());
        checkPlanInfo.setCreateTime(new Date());
        FileVerInfo fileVerInfo=fileVerDAO.SelectByfileVerRd(saveCheckPlanInfoReq00.getFileVerRd());
        if(fileVerInfo!=null){
            checkPlanInfo.setFileVerGd(fileVerInfo.getGuid());
        }else {
            throw new SystemException("xx","文件不存在");
        }

        checkPlanDAO.insertCheckPlanInfo(checkPlanInfo);

        CheckPlanTiggerRulelnfo checkPlanTiggerRulelnfo = new CheckPlanTiggerRulelnfo();
        checkPlanTiggerRulelnfo.setGuid(CommonUtils.getRandomNumber());
        checkPlanTiggerRulelnfo.setCheckPlanGd(checkPlanInfo.getGuid());
        checkPlanTiggerRulelnfo.setTimeType(saveCheckPlanInfoReq00.getDayInfo().getTimeType());
        checkPlanTiggerRulelnfo.setTimeContent(saveCheckPlanInfoReq00.getDayInfo().getTimeContent());
        checkPlanTiggerRulelnfo.setWeekContent(saveCheckPlanInfoReq00.getWeekInfo().getWeekContent());
        checkPlanTiggerRulelnfo.setMonthContent(saveCheckPlanInfoReq00.getMonthInfo().getMonthContent());
        checkPlanTiggerRulelnfo.setYearContent(saveCheckPlanInfoReq00.getYearInfo().getYearContent());
        checkPlanTiggerRulelnfo.setDesContent(saveCheckPlanInfoReq00.getDesInfo().getDesContent());
        checkPlanTiggerRulelnfo.setQuarterContent(saveCheckPlanInfoReq00.getQuarterInfo().getQuarterContent());

        checkPlanTiggerRulelnfo.setUpTime(saveCheckPlanInfoReq00.getUpTime()==null||"".equals(saveCheckPlanInfoReq00.getUpTime())?0.0f:saveCheckPlanInfoReq00.getUpTime());
        checkPlanTiggerRulelnfo.setDownTime(saveCheckPlanInfoReq00.getDownTime()==null||"".equals(saveCheckPlanInfoReq00.getDownTime())?0.0f:saveCheckPlanInfoReq00.getDownTime());
        checkPlanTiggerRulelnfo.setPresetTime(saveCheckPlanInfoReq00.getPresetTime()==null||"".equals(saveCheckPlanInfoReq00.getPresetTime())?0.0f:saveCheckPlanInfoReq00.getPresetTime());


        //提前邮件提醒
        EmailDistributionInfo emailDistributionInfo = emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCheckPlanInfoReq00.getPresetEmailDistributionRd());
        if (emailDistributionInfo != null) {
            checkPlanTiggerRulelnfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }
        //提前邮件内容提醒
        EmailMessageInfo emailMessageInfo = emailMessageDAO.SelectEmailMessageByID(saveCheckPlanInfoReq00.getPresetEmailMessageRd());
        if (emailMessageInfo != null) {
            checkPlanTiggerRulelnfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }


        //到期
        EmailDistributionInfo emailDistributionInfo1 = emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCheckPlanInfoReq00.getStartEmailDistributionRd());
        if (emailDistributionInfo1 != null) {
            checkPlanTiggerRulelnfo.setStartEmailDistributionGd(emailDistributionInfo1.getGuid());
        }
        EmailMessageInfo emailMessageInfo1 = emailMessageDAO.SelectEmailMessageByID(saveCheckPlanInfoReq00.getStartEmailMessageRd());
        if (emailMessageInfo1 != null) {
            checkPlanTiggerRulelnfo.setStartEmailMessageGd(emailMessageInfo1.getGuid());
        }
        //过期
        EmailDistributionInfo emailDistributionInfo2 = emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCheckPlanInfoReq00.getOverEmailDistributionRd());
        if (emailDistributionInfo2 != null) {
            checkPlanTiggerRulelnfo.setOverdueEmailDistributionGd(emailDistributionInfo2.getGuid());
        }
        EmailMessageInfo emailMessageInfo2 = emailMessageDAO.SelectEmailMessageByID(saveCheckPlanInfoReq00.getOverEmailMessageRd());
        if (emailMessageInfo2 != null) {
            checkPlanTiggerRulelnfo.setOverdueEmailMessageGd(emailMessageInfo2.getGuid());
        }
        checkPlanTiggerRulelnfo.setCreator(CommonUtils.readUser().getRealName());
        checkPlanTiggerRulelnfo.setCreateTime(new Date());
        checkPlanTiggerRuleDAO.insertCheckPlanTiggerRulelnfo(checkPlanTiggerRulelnfo);

        for (SaveCheckPlanInfoReq00CyclePlanDtl saveCheckPlanInfoReq00CyclePlanDtl : saveCheckPlanInfoReq00.getCheckPlanDtlInfo()) {
            CheckPlanDtllnfo checkPlanDtllnfo = new CheckPlanDtllnfo();
          if(!Pattern.compile("^[0-9]*[1-9][0-9]*$").matcher(String.valueOf(saveCheckPlanInfoReq00CyclePlanDtl.getMaxExCount())).matches()){
              throw new SystemException("xxx", "新增失败，最大执行次数请输入正整数");
          }
            checkPlanDtllnfo.setCreator(CommonUtils.readUser().getRealName());
            checkPlanDtllnfo.setCreateTime(new Date());
            TaskInfo taskInfo = taskDAO.selectTaskInfoByRuid(saveCheckPlanInfoReq00CyclePlanDtl.getTaskRd());
            if (taskInfo != null) {
                checkPlanDtllnfo.setTaskGd(taskInfo.getGuid());
            }

            checkPlanDtllnfo.setGuid(CommonUtils.getRandomNumber());
            checkPlanDtllnfo.setCheckPlanGd(checkPlanInfo.getGuid());
            checkPlanDtllnfo.setTaskName(saveCheckPlanInfoReq00CyclePlanDtl.getTaskName());
            checkPlanDtllnfo.setTaskItemName(saveCheckPlanInfoReq00CyclePlanDtl.getTaskItemName());
            checkPlanDtllnfo.setSureType(saveCheckPlanInfoReq00CyclePlanDtl.getSureType());
     /*       checkPlanDtllnfo.setMinExCount(saveCheckPlanInfoReq00CyclePlanDtl.getMinExCount());*/
            checkPlanDtllnfo.setMaxExCount(Integer.valueOf(saveCheckPlanInfoReq00CyclePlanDtl.getMaxExCount()));
            checkPlanDtlDAO.insertCheckPlanDtllnfo(checkPlanDtllnfo);
        }
        saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
        return saveCheckPlanInfoRes;
    }

    //删除
    @Override
    public SaveCheckPlanInfoRes RmSaveCheckPlanInfoRes(SaveCheckPlanInfoReq01 saveCheckPlanInfoReq01) {
        SaveCheckPlanInfoRes saveCheckPlanInfoRes = new SaveCheckPlanInfoRes();
        SaveCheckPlanInfoResB saveCheckPlanInfoResB = new SaveCheckPlanInfoResB();

        if (saveCheckPlanInfoReq01.getCheckPlanRd() <= 0) {
            throw new SystemException("xxx", "删除失败，该标识不能为空");
        }

        CheckPlanInfo checkPlanInfo = checkPlanDAO.selectCheckPlanInfoByRuid(saveCheckPlanInfoReq01.getCheckPlanRd());
        if (checkPlanInfo == null) {
            throw new SystemException("xxx", "删除失败，该信息不存在");
        }
        List<DoCheckInfo> doCheckInfos = doCheckInfoDAO.SelectDoCheckInfosByCheckTaskGd(checkPlanInfo.getGuid());
        if (doCheckInfos != null && doCheckInfos.size()>0) {
            throw  new SystemException("xxx","当前点检计划不能进行删除，因为有未处理完成的点检任务");
        }

        checkPlanDAO.deleteCheckPlanInfo(saveCheckPlanInfoReq01.getCheckPlanRd());
        checkPlanTiggerRuleDAO.deleteCheckPlanTiggerRulelnfo(checkPlanInfo.getGuid());
        checkPlanDtlDAO.deleteCheckPlanDtllnfo(checkPlanInfo.getGuid());
        devCheckPlanDAO.detelteByCheckPlanGd(checkPlanInfo.getGuid());
        toolCheckPlanInfoDAO.deleteByCheckPlanGd(checkPlanInfo.getGuid());
        carrierCheckPlanInfoDAO.deleteByCheckPlanGd(checkPlanInfo.getGuid());


        saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
        return saveCheckPlanInfoRes;
    }

    //修改

    @Override
    public SaveCheckPlanInfoRes ModSaveCheckPlanInfoRes(SaveCheckPlanInfoReq02 saveCheckPlanInfoReq02) {
        SaveCheckPlanInfoRes saveCheckPlanInfoRes = new SaveCheckPlanInfoRes();
        SaveCheckPlanInfoResB saveCheckPlanInfoResB = new SaveCheckPlanInfoResB();

        if(saveCheckPlanInfoReq02.getCheckPlanRd()<=0){
            throw new SystemException("xxx","修改失败，该标识不存在");
        }
        CheckPlanInfo checkPlanInfo=checkPlanDAO.selectCheckPlanInfoByRuid(saveCheckPlanInfoReq02.getCheckPlanRd());
        if(checkPlanInfo==null){
            throw new SystemException("xxx","修改失败，该信息不存在");
        }
        if(saveCheckPlanInfoReq02.getCheckPlanName()==null||"".equals(saveCheckPlanInfoReq02.getCheckPlanName())){
            throw new SystemException("xxx","修改失败，名称不能为空");
        }


        if(saveCheckPlanInfoReq02.getStarDate()==null||"".equals(saveCheckPlanInfoReq02.getStarDate())){
            throw new SystemException("xxx","修改失败，起算日期不能为空");
        }

        if(saveCheckPlanInfoReq02.getEndDate()==null||"".equals(saveCheckPlanInfoReq02.getEndDate())){
            throw new SystemException("xxx","修改失败，截止日期不能为空");
        }

        if(saveCheckPlanInfoReq02.getMTType()==null||"".equals(saveCheckPlanInfoReq02.getMTType())){
            throw new SystemException("xxx","修改失败，保养周期不能为空");
        }

        if(saveCheckPlanInfoReq02.getCheckPlanDtlInfo().size()<=0){
            throw new SystemException("xxx","修改失败，保养内容清单不能为空");
        }

        CheckPlanInfo checkPlanInfo1=checkPlanDAO.selectCheckPlanInfoByCheckPlanName(saveCheckPlanInfoReq02.getCheckPlanName());
        if(checkPlanInfo1!=null&&!checkPlanInfo1.getCheckPlanName().equals(checkPlanInfo.getCheckPlanName())){
            throw new SystemException("xxx","修改失败，名称已存在");
        }
        FileVerInfo fileVerInfo=fileVerDAO.SelectByfileVerRd(saveCheckPlanInfoReq02.getFileVerRd());
        if(fileVerInfo!=null){
            checkPlanInfo.setFileVerGd(fileVerInfo.getGuid());
        }else {
            throw new SystemException("xx","文件不存在");
        }

        checkPlanInfo.setCheckPlanName(saveCheckPlanInfoReq02.getCheckPlanName());
        checkPlanInfo.setStatus(saveCheckPlanInfoReq02.getStatus());
        checkPlanInfo.setDescription(saveCheckPlanInfoReq02.getDescription());
        checkPlanInfo.setCheckPlanType(saveCheckPlanInfoReq02.getCheckPlanType());
        checkPlanInfo.setReference(saveCheckPlanInfoReq02.getReference());
        checkPlanInfo.setStarDate(DateUtil.format2(saveCheckPlanInfoReq02.getStarDate()));
        checkPlanInfo.setEndDate(DateUtil.format2(saveCheckPlanInfoReq02.getEndDate()));
        checkPlanInfo.setmTType(saveCheckPlanInfoReq02.getMTType());
        checkPlanInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        checkPlanInfo.setLastModifyTime(new Date());
        checkPlanInfo.setRemark(saveCheckPlanInfoReq02.getRemark());
        checkPlanInfo.setCreateTime(new Date());
        if(checkPlanDAO.updateCheckPlanInfo(checkPlanInfo)<=0){
            throw new SystemException("xxx","修改失败");
        }




        CheckPlanTiggerRulelnfo checkPlanTiggerRulelnfo=checkPlanTiggerRuleDAO.selectCheckPlanTiggerRulelnfoByCheckPlanGd(checkPlanInfo.getGuid());
        checkPlanTiggerRulelnfo.setRuid(checkPlanTiggerRulelnfo.getRuid());
        checkPlanTiggerRulelnfo.setGuid(CommonUtils.getRandomNumber());
        checkPlanTiggerRulelnfo.setCheckPlanGd(checkPlanInfo.getGuid());
        checkPlanTiggerRulelnfo.setTimeType(saveCheckPlanInfoReq02.getDayInfo().getTimeType());
        checkPlanTiggerRulelnfo.setTimeContent(saveCheckPlanInfoReq02.getDayInfo().getTimeContent());
        checkPlanTiggerRulelnfo.setWeekContent(saveCheckPlanInfoReq02.getWeekInfo().getWeekContent());
        checkPlanTiggerRulelnfo.setMonthContent(saveCheckPlanInfoReq02.getMonthInfo().getMonthContent());
        checkPlanTiggerRulelnfo.setYearContent(saveCheckPlanInfoReq02.getYearInfo().getYearContent());
        checkPlanTiggerRulelnfo.setDesContent(saveCheckPlanInfoReq02.getDesInfo().getDesContent());
        checkPlanTiggerRulelnfo.setQuarterContent(saveCheckPlanInfoReq02.getQuarterInfo().getQuarterContent());
        checkPlanTiggerRulelnfo.setUpTime(saveCheckPlanInfoReq02.getUpTime()==null||"".equals(saveCheckPlanInfoReq02.getUpTime())?0.0f:saveCheckPlanInfoReq02.getUpTime());
        checkPlanTiggerRulelnfo.setDownTime(saveCheckPlanInfoReq02.getDownTime()==null||"".equals(saveCheckPlanInfoReq02.getDownTime())?0.0f:saveCheckPlanInfoReq02.getDownTime());
        checkPlanTiggerRulelnfo.setPresetTime(saveCheckPlanInfoReq02.getPresetTime()==null||"".equals(saveCheckPlanInfoReq02.getPresetTime())?0.0f:saveCheckPlanInfoReq02.getPresetTime());


        //提前邮件提醒
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCheckPlanInfoReq02.getPresetEmailDistributionRd());
        if(emailDistributionInfo!=null){
            checkPlanTiggerRulelnfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
        }else {
            checkPlanTiggerRulelnfo.setPresetEmailDistributionGd("");
        }
        //提前邮件内容提醒
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveCheckPlanInfoReq02.getPresetEmailMessageRd());
        if(emailMessageInfo!=null){
            checkPlanTiggerRulelnfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
        }else {
            checkPlanTiggerRulelnfo.setPresetEmailMessageGd("");
        }
        //到期
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCheckPlanInfoReq02.getStartEmailDistributionRd());
        if(emailDistributionInfo1!=null){
            checkPlanTiggerRulelnfo.setStartEmailDistributionGd(emailDistributionInfo1.getGuid());
        }else {
            checkPlanTiggerRulelnfo.setStartEmailDistributionGd("");
        }
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveCheckPlanInfoReq02.getStartEmailMessageRd());
        if(emailMessageInfo1!=null){
            checkPlanTiggerRulelnfo.setStartEmailMessageGd(emailMessageInfo1.getGuid());
        }else {
            checkPlanTiggerRulelnfo.setStartEmailMessageGd("");
        }

        //过期
        EmailDistributionInfo emailDistributionInfo2=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveCheckPlanInfoReq02.getOverEmailDistributionRd());
        if(emailDistributionInfo2!=null){
            checkPlanTiggerRulelnfo.setOverdueEmailDistributionGd(emailDistributionInfo2.getGuid());
        }else {
            checkPlanTiggerRulelnfo.setOverdueEmailDistributionGd("");
        }
        EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageByID(saveCheckPlanInfoReq02.getOverEmailMessageRd());
        if(emailMessageInfo2!=null){
            checkPlanTiggerRulelnfo.setOverdueEmailMessageGd(emailMessageInfo2.getGuid());
        }else {
            checkPlanTiggerRulelnfo.setOverdueEmailMessageGd("");
        }
        checkPlanTiggerRulelnfo.setCreator(CommonUtils.readUser().getRealName());
        checkPlanTiggerRulelnfo.setCreateTime(new Date());
        if(checkPlanTiggerRuleDAO.updateCheckPlanTiggerRulelnfo(checkPlanTiggerRulelnfo)<=0){
            throw new SystemException("xxx","修改规则信息失败");
        }

         if(checkPlanDtlDAO.deleteCheckPlanDtllnfo(checkPlanInfo.getGuid())<=0){
             throw new SystemException("xxx","删除细表信息失败");
        }

        for(SaveCheckPlanInfoReq02CyclePlanDtl saveCheckPlanInfoReq02CyclePlanDtl:saveCheckPlanInfoReq02.getCheckPlanDtlInfo()){
            CheckPlanDtllnfo checkPlanDtllnfo=new CheckPlanDtllnfo();
            TaskInfo taskInfo=taskDAO.selectTaskInfoByRuid(saveCheckPlanInfoReq02CyclePlanDtl.getTaskRd());
            if(taskInfo!=null){
                checkPlanDtllnfo.setTaskGd(taskInfo.getGuid());
            }
            if(!Pattern.compile("^[0-9]*[1-9][0-9]*$").matcher(String.valueOf(saveCheckPlanInfoReq02CyclePlanDtl.getMaxExCount())).matches()){
                throw new SystemException("xxx", "修改失败，最大执行次数请输入正整数");
            }
            checkPlanDtllnfo.setCreator(CommonUtils.readUser().getRealName());
            checkPlanDtllnfo.setCreateTime(new Date());
            checkPlanDtllnfo.setGuid(CommonUtils.getRandomNumber());
            checkPlanDtllnfo.setCheckPlanGd(checkPlanInfo.getGuid());
            checkPlanDtllnfo.setTaskName(saveCheckPlanInfoReq02CyclePlanDtl.getTaskName());
            checkPlanDtllnfo.setTaskItemName(saveCheckPlanInfoReq02CyclePlanDtl.getTaskItemName());
            checkPlanDtllnfo.setSureType(saveCheckPlanInfoReq02CyclePlanDtl.getSureType());
         /*   checkPlanDtllnfo.setMinExCount(saveCheckPlanInfoReq02CyclePlanDtl.getMinExCount());*/
            checkPlanDtllnfo.setMaxExCount(saveCheckPlanInfoReq02CyclePlanDtl.getMaxExCount());
            checkPlanDtlDAO.insertCheckPlanDtllnfo(checkPlanDtllnfo);
        }

        saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
        return saveCheckPlanInfoRes;
    }
}