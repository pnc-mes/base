package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoRes;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoResB;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoResD;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResD;
import pnc.mesadmin.dto.GetFrePlanInfo.*;
import pnc.mesadmin.dto.GetFrePlanInfo.FileInfo;
import pnc.mesadmin.dto.GetFrePlanInfo.FrePlanDtlInfo;
import pnc.mesadmin.dto.SaveFrePlanInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.FrePlanIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by HAOZAN on 2018/9/7.
 */
@Service
@Transactional
public class FrePlanService implements FrePlanIService {


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

    @Resource
    private FrePlanDAO frePlanDAO;

    @Resource
    private FrePlanDtlDAO frePlanDtlDAO;

    @Resource
    private FrePlanTiggerRuleDAO frePlanTiggerRuleDAO;

    @Resource
    private ReasonDAO reasonDAO;

    @Resource
    private FileVerDAO fileVerDAO;

    @Resource
    private TaskDAO taskDAO;

    //获取次数保养计划列表信息
    @Override
    public GetAllFrePlanInfoRes GetAllFrePlanInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllFrePlanInfoRes getAllFrePlanInfoRes=new GetAllFrePlanInfoRes();
        GetAllFrePlanInfoResB getAllFrePlanInfoResB=new GetAllFrePlanInfoResB();
        List<GetAllFrePlanInfoResD> getAllFrePlanInfoResDS=new ArrayList<GetAllFrePlanInfoResD>();
        List<FrePlanInfo> frePlanDAOS = baseIService.QALLBaseInfo(FrePlanDAO.class, "selectAllFrePlan",
                argInitDataFields, argPageInfo);

        if(frePlanDAOS!=null&&frePlanDAOS.size()>0){
            for(FrePlanInfo frePlanInfo:frePlanDAOS){
                GetAllFrePlanInfoResD getAllFrePlanInfoResD=new GetAllFrePlanInfoResD();
                getAllFrePlanInfoResD.setFrePlanRd(frePlanInfo.getRuid());
                getAllFrePlanInfoResD.setFrePlanName(frePlanInfo.getFrePlanName());
                getAllFrePlanInfoResDS.add(getAllFrePlanInfoResD);
            }
        }
        getAllFrePlanInfoResB.setData(getAllFrePlanInfoResDS);
        getAllFrePlanInfoRes.setBody(getAllFrePlanInfoResB);
        return getAllFrePlanInfoRes;
    }

    @Override
    public PageResult<GetAllFrePlanInfoResD> QALLGetAllFrePlanNewRes(BaseRequest req) {
        List<GetAllFrePlanInfoResD> resDList = new ArrayList<GetAllFrePlanInfoResD>();
        GetAllFrePlanInfoResD resD = null;

        IPage<FrePlanInfo> iPage = frePlanDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (FrePlanInfo obj : iPage.getRecords()) {
            resD = new GetAllFrePlanInfoResD();
            resD.setFrePlanRd(obj.getRuid());
            resD.setFrePlanName(obj.getFrePlanName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }


    //获取次数保养计划信息
    @Override
    public GetFrePlanInfoRes GetFrePlanInfo(GetFrePlanInfoReq00 getFrePlanInfoReq00) {
        GetFrePlanInfoRes getFrePlanInfoRes=new GetFrePlanInfoRes();
        GetFrePlanInfoResB getFrePlanInfoResB=new GetFrePlanInfoResB();
        GetFrePlanInfoResD getFrePlanInfoResD=new GetFrePlanInfoResD();
        if("".equals(getFrePlanInfoReq00.getFrePlanRd())||getFrePlanInfoReq00.getFrePlanRd()==null){
            throw new SystemException("xx","查询失败，保养计划标识不能为空");
        }
        FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(getFrePlanInfoReq00.getFrePlanRd());
        if(frePlanInfo==null){
            throw new SystemException("xx","查询失败，该信息不存在");
        }

        getFrePlanInfoResD.setFrePlanRd(frePlanInfo.getRuid());
        getFrePlanInfoResD.setFrePlanName(frePlanInfo.getFrePlanName());
        getFrePlanInfoResD.setStatus(frePlanInfo.getStatus());
        getFrePlanInfoResD.setDescription(frePlanInfo.getDescription());
        getFrePlanInfoResD.setReference(frePlanInfo.getReference());
        //获取原因信息
        if(frePlanInfo.getReaCodeGd()!=null){
            ReaCodeInfo reaCodeInfo=reasonDAO.SelectReacodeInfoByguid(frePlanInfo.getReaCodeGd());
            if(reaCodeInfo!=null){
                ReaInfo reaInfo=new ReaInfo();
                reaInfo.setReaRd(reaCodeInfo.getRuid());
                reaInfo.setReaDes(reaCodeInfo.getReaDes());
                getFrePlanInfoResD.setReaInfo(reaInfo);
            }
        }

        //获取文件版本
        if(frePlanInfo.getFileVerGd()!=null){
            FileVerInfo fileVerInfo=fileVerDAO.SelectByFileVerGd(frePlanInfo.getFileVerGd());
            if(fileVerInfo!=null){
                FileInfo fileInfo=new FileInfo();
                fileInfo.setFileVerRd(fileVerInfo.getRuid());
                fileInfo.setFileName(fileVerInfo.getFileName());
                getFrePlanInfoResD.setFileInfo(fileInfo);
            }
        }
        getFrePlanInfoResD.setUseNum(frePlanInfo.getUseNum());
        getFrePlanInfoResD.setUnitType(frePlanInfo.getUnitType());

        //查询上下线公差
        FrePlanTiggerRulelnfo frePlanTiggerRulelnfo=frePlanTiggerRuleDAO.selectFrePlanGd(frePlanInfo.getGuid());
        if(frePlanTiggerRulelnfo==null){
            throw new SystemException("","次数保养计划触发规则信息未关联到");

        }
        getFrePlanInfoResD.setUpUseNum(frePlanTiggerRulelnfo.getUpUseNum());
        getFrePlanInfoResD.setDownUseNum(frePlanTiggerRulelnfo.getDownUseNum());
        getFrePlanInfoResD.setPresetUseNum(frePlanTiggerRulelnfo.getPresetUseNum());

        //提前邮件分发 ID
        PresetEmailDistributionInfo presetEmailDistributionInfo=new PresetEmailDistributionInfo();
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByGuid(frePlanTiggerRulelnfo.getPresetEmailDistributionGd());
        if(emailDistributionInfo!=null){
            presetEmailDistributionInfo.setEmailDistributionRd(emailDistributionInfo.getRuid());
            presetEmailDistributionInfo.setEmailDistributionName(emailDistributionInfo.getDistributionName());
            getFrePlanInfoResD.setPresetEmailDistributionInfo(presetEmailDistributionInfo);
        }

        //提前邮件消息 ID
        PresetEmailMessageInfo presetEmailMessageInfo=new PresetEmailMessageInfo();
        EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageInfoByGuid(frePlanTiggerRulelnfo.getPresetEmailMessageGd());
        if(emailMessageInfo!=null){
            presetEmailMessageInfo.setEmailMessageRd(emailMessageInfo.getRuid());
            presetEmailMessageInfo.setEmailMessageName(emailMessageInfo.getEmailName());
            getFrePlanInfoResD.setPresetEmailMessageInfo(presetEmailMessageInfo);
        }

        //到期邮件分发 ID
        StartEmailDistributionInfo startEmailDistributionInfo=new StartEmailDistributionInfo();
        EmailDistributionInfo emailDistributionInfo2=emailDistributionDAO.SelectEmailDistributionInfoByGuid(frePlanTiggerRulelnfo.getStartEmailDistributionGd());
        if(emailDistributionInfo2!=null){
            startEmailDistributionInfo.setEmailDistributionRd(emailDistributionInfo2.getRuid());
            startEmailDistributionInfo.setEmailDistributionName(emailDistributionInfo2.getDistributionName());
            getFrePlanInfoResD.setStartEmailDistributionInfo(startEmailDistributionInfo);
        }

        //到期邮件消息 ID
        StartEmailMessageInfo startEmailMessageInfo=new StartEmailMessageInfo();
        EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageInfoByGuid(frePlanTiggerRulelnfo.getStartEmailMessageGd());
        if(emailMessageInfo2!=null){
            startEmailMessageInfo.setEmailMessageRd(emailMessageInfo2.getRuid());
            startEmailMessageInfo.setEmailMessageName(emailMessageInfo2.getEmailName());
            getFrePlanInfoResD.setStartEmailMessageInfo(startEmailMessageInfo);
        }

        //超时采取行为
        //getFrePlanInfoResD.setOverTimeAction(frePlanTiggerRulelnfo.getOverTimeAction());

        //超时设置邮件分发
        OverdueEmailDistributionInfo overdueEmailDistributionInfo=new OverdueEmailDistributionInfo();
        EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByGuid(frePlanTiggerRulelnfo.getOverdueEmailDistributionGd());
        if(emailDistributionInfo1!=null){
            overdueEmailDistributionInfo.setEmailDistributionRd(emailDistributionInfo1.getRuid());
            overdueEmailDistributionInfo.setEmailDistributionName(emailDistributionInfo1.getDistributionName());
            getFrePlanInfoResD.setOverdueEmailDistributionInfo(overdueEmailDistributionInfo);
        }

        //超时设置邮件消息
        OverdueEmailMessageInfo overdueEmailMessageInfo=new OverdueEmailMessageInfo();
        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageInfoByGuid(frePlanTiggerRulelnfo.getOverdueEmailMessageGd());
        if(emailMessageInfo1!=null){
            overdueEmailMessageInfo.setEmailMessageRd(emailMessageInfo1.getRuid());
            overdueEmailMessageInfo.setEmailMessageName(emailMessageInfo1.getEmailName());
            getFrePlanInfoResD.setOverdueEmailMessageInfo(overdueEmailMessageInfo);
        }

        List<FrePlanDtlInfo> frePlanDtlInfo=new ArrayList<FrePlanDtlInfo>();

        //查询次数保养计划明细信息

        List<FrePlanDtlDataInfo> frePlanDtlDataInfos=frePlanDtlDAO.selectAllFrePlanDtlInfo(frePlanInfo.getGuid());
        for(FrePlanDtlDataInfo frePlanDtlDataInfo:frePlanDtlDataInfos){
            FrePlanDtlInfo frePlanDtlInfo1=new  FrePlanDtlInfo();
            frePlanDtlInfo1.setFrePlanDtlRd(frePlanDtlDataInfo.getRuid());
            TaskInfo taskInfo=taskDAO.selectTaskInfoByGuid(frePlanDtlDataInfo.getTaskGd());
            if(taskInfo!=null) {
                frePlanDtlInfo1.setTaskRd(taskInfo.getRuid());
            }
            frePlanDtlInfo1.setTaskName(frePlanDtlDataInfo.getTaskName());
            frePlanDtlInfo1.setTaskItemName(frePlanDtlDataInfo.getTaskItemName());
            frePlanDtlInfo1.setSureType(frePlanDtlDataInfo.getSureType());
            frePlanDtlInfo1.setMaxExCount(frePlanDtlDataInfo.getMaxExCount());
     /*       frePlanDtlInfo1.setMinExCount(frePlanDtlDataInfo.getMinExCount());*/
            frePlanDtlInfo.add(frePlanDtlInfo1);
        }
        getFrePlanInfoResD.setFrePlanDtlInfo(frePlanDtlInfo);

        getFrePlanInfoResD.setCreator(frePlanTiggerRulelnfo.getCreator());
        getFrePlanInfoResD.setCreateTime(DateUtil.format(frePlanTiggerRulelnfo.getCreateTime()));
        getFrePlanInfoResD.setLastModifyMan(frePlanTiggerRulelnfo.getLastModifyMan());
        getFrePlanInfoResD.setLastModifyTime(DateUtil.format(frePlanTiggerRulelnfo.getLastModifyTime()));
        getFrePlanInfoResD.setRemark(frePlanTiggerRulelnfo.getRemark());

        getFrePlanInfoResB.setData(getFrePlanInfoResD);
        getFrePlanInfoRes.setBody(getFrePlanInfoResB);
        return getFrePlanInfoRes;
    }

    //00保存次数保养计划信息
    @Override
    public SaveFrePlanInfoRes AddSaveFrePlanInfo(SaveFrePlanInfoReq00 saveFrePlanInfoReq00) {
        SaveFrePlanInfoRes saveFrePlanInfoRes=new SaveFrePlanInfoRes();
        SaveFrePlanInfoResB saveFrePlanInfoResB=new SaveFrePlanInfoResB();

        if(saveFrePlanInfoReq00.getFrePlanName()==null||"".equals(saveFrePlanInfoReq00.getFrePlanName())){
            throw new SystemException("xxx","新增失败，名称不能为空");
        }

        if(saveFrePlanInfoReq00.getStatus()==null){
            throw new SystemException("xxx","新增失败，状态不能为空");
        }
        if(saveFrePlanInfoReq00.getReaRd()==null){
            throw new SystemException("xxx","新增失败，原因代码不能为空");
        }
        if(saveFrePlanInfoReq00.getFileVerRd()<=0){
            throw new SystemException("xxx","新增失败，保养标准作业书不能为空");
        }

        if(saveFrePlanInfoReq00.getUseNum()==null||"".equals(saveFrePlanInfoReq00.getUseNum())){
            throw new SystemException("xxx","新增失败，使用量不能为空");
        }

        if(saveFrePlanInfoReq00.getUnitType()==null||"".equals(saveFrePlanInfoReq00.getUnitType())){
            throw new SystemException("xxx","新增失败，单位类型不能为空");
        }


        FrePlanInfo frePlanInfo=frePlanDAO.selectAllFrePlanName(saveFrePlanInfoReq00.getFrePlanName());
        if(frePlanInfo!=null){
            throw new SystemException("xxx","新增失败，名称已存在");
        }

        FrePlanInfo frePlanInfo1=new FrePlanInfo();
        frePlanInfo1.setReference(saveFrePlanInfoReq00.getReference());
        frePlanInfo1.setGuid(CommonUtils.getRandomNumber());
        frePlanInfo1.setFrePlanName(saveFrePlanInfoReq00.getFrePlanName());
        frePlanInfo1.setStatus(saveFrePlanInfoReq00.getStatus());
        frePlanInfo1.setDescription(saveFrePlanInfoReq00.getDescription());
        frePlanInfo1.setReference(saveFrePlanInfoReq00.getReference());
        //查询原因代码
        ReaCodeInfo reaCodeInfo=reasonDAO.SelectReacodeInfoByruid(saveFrePlanInfoReq00.getReaRd());
        if(reaCodeInfo!=null){
            frePlanInfo1.setReaCodeGd(reaCodeInfo.getGuid());
        }else {
            throw new SystemException("xx","原因代码不存在");
        }

        //查询保养标准作业书标识
        FileVerInfo fileVerInfo=fileVerDAO.SelectByfileVerRd(saveFrePlanInfoReq00.getFileVerRd());
        if(fileVerInfo!=null){
            frePlanInfo1.setFileVerGd(fileVerInfo.getGuid());
        }else {
            throw new SystemException("xx","文件不存在");
        }
        frePlanInfo1.setUseNum(saveFrePlanInfoReq00.getUseNum());
        frePlanInfo1.setUnitType(saveFrePlanInfoReq00.getUnitType());
        frePlanInfo1.setCreator(CommonUtils.readUser().getRealName());
        frePlanInfo1.setRemark(saveFrePlanInfoReq00.getRemark());
        frePlanInfo1.setCreateTime(new Date());
        frePlanDAO.insertFrePlan(frePlanInfo1);

        //添加次数保养计划触发规则信息
        FrePlanTiggerRulelnfo frePlanTiggerRulelnfo=new FrePlanTiggerRulelnfo();
        frePlanTiggerRulelnfo.setGuid(CommonUtils.getRandomNumber());
        frePlanTiggerRulelnfo.setFrePlanGd(frePlanInfo1.getGuid());
        frePlanTiggerRulelnfo.setUpUseNum(saveFrePlanInfoReq00.getUpUseNum());
        frePlanTiggerRulelnfo.setDownUseNum(saveFrePlanInfoReq00.getDownUseNum());
        frePlanTiggerRulelnfo.setPresetUseNum(saveFrePlanInfoReq00.getPresetUseNum());

        //提前邮件提醒
        if(saveFrePlanInfoReq00.getPresetEmailDistributionRd()!=null){
            EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveFrePlanInfoReq00.getPresetEmailDistributionRd());
            if(emailDistributionInfo!=null){
                frePlanTiggerRulelnfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
            }
        }
        //提前邮件内容提醒
        if(saveFrePlanInfoReq00.getPresetEmailMessageRd()!=null){
            EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveFrePlanInfoReq00.getPresetEmailMessageRd());
            if(emailMessageInfo!=null){
                frePlanTiggerRulelnfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
            }
        }
        //到期邮件提醒
        if(saveFrePlanInfoReq00.getStartEmailDistributionRd()!=null){
            EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveFrePlanInfoReq00.getStartEmailDistributionRd());
            if(emailDistributionInfo1!=null){
                frePlanTiggerRulelnfo.setStartEmailDistributionGd(emailDistributionInfo1.getGuid());
            }

        }
        //到期邮件内容提醒
        if(saveFrePlanInfoReq00.getStartEmailMessageRd()!=null){
            EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectEmailMessageByID(saveFrePlanInfoReq00.getStartEmailMessageRd());
            if(emailMessageInfo1!=null){
                frePlanTiggerRulelnfo.setStartEmailMessageGd(emailMessageInfo1.getGuid());
            }

        }
        //frePlanTiggerRulelnfo.setOverTimeAction(saveFrePlanInfoReq00.getOverTimeAction());

        //过期邮件提醒
        if(saveFrePlanInfoReq00.getOverEmailDistributionRd()!=null){

            EmailDistributionInfo emailDistributionInfo2=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveFrePlanInfoReq00.getOverEmailDistributionRd());
            if(emailDistributionInfo2!=null){
                frePlanTiggerRulelnfo.setOverdueEmailDistributionGd(emailDistributionInfo2.getGuid());
            }

        }
        //过期期邮件内容提醒
        if(saveFrePlanInfoReq00.getOverEmailMessageRd()!=null){
            EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageByID(saveFrePlanInfoReq00.getOverEmailMessageRd());
            if(emailMessageInfo2!=null){
                frePlanTiggerRulelnfo.setOverdueEmailMessageGd(emailMessageInfo2.getGuid());
            }
        }
        frePlanTiggerRulelnfo.setCreator(CommonUtils.readUser().getRealName());
        frePlanTiggerRulelnfo.setCreateTime(new Date());

        frePlanTiggerRuleDAO.insertTiggerRulelnfo(frePlanTiggerRulelnfo);

        //添加次数保养计划明细信
        for(pnc.mesadmin.dto.SaveFrePlanInfo.FrePlanDtlInfo frePlanDtlInfos:saveFrePlanInfoReq00.getFrePlanDtlInfo()){

            if(!Pattern.compile("^[0-9]*[1-9][0-9]*$").matcher(frePlanDtlInfos.getMaxExCount()).matches()){
                throw new SystemException("xxx", "新增失败，最大执行次数请输入正整数");
            }
            FrePlanDtlDataInfo frePlanDtlDataInfo=new FrePlanDtlDataInfo();
            frePlanDtlDataInfo.setCreator(CommonUtils.readUser().getRealName());
            frePlanDtlDataInfo.setCreateTime(new Date());
            frePlanDtlDataInfo.setGuid(CommonUtils.getRandomNumber());
            frePlanDtlDataInfo.setFrePlanGd(frePlanInfo1.getGuid());
            TaskInfo taskInfo=taskDAO.selectTaskInfoByRuid(frePlanDtlInfos.getTaskRd());
            if(taskInfo==null){
                throw new SystemException("","保存失败,请选择正确的任务信息");
            }
            frePlanDtlDataInfo.setTaskGd(taskInfo.getGuid());
            if(frePlanDtlInfos.getTaskName()==null){
                throw new SystemException("","保存失败,任务名称不能为空");
            }
            frePlanDtlDataInfo.setTaskName(frePlanDtlInfos.getTaskName());
            if(frePlanDtlInfos.getTaskItemName()==null){
                throw new SystemException("","保存失败,任务明细项名称不能为空");
            }
            frePlanDtlDataInfo.setTaskItemName(frePlanDtlInfos.getTaskItemName());
            if(frePlanDtlInfos.getSureType()==null){
                throw new SystemException("","保存失败,确认方式不能为空");
            }
            frePlanDtlDataInfo.setSureType(frePlanDtlInfos.getSureType());
/*            if(frePlanDtlInfos.getMinExCount()==null){
                throw new SystemException("","保存失败,最小执行次数不能为空");
            }
            frePlanDtlDataInfo.setMinExCount(frePlanDtlInfos.getMinExCount());*/
            if(frePlanDtlInfos.getMaxExCount()==null){
                throw new SystemException("","保存失败,最大执行次数不能为空");
            }
            frePlanDtlDataInfo.setMaxExCount(Integer.valueOf(frePlanDtlInfos.getMaxExCount()));
            frePlanDtlDAO.insertFrePlanDtlDataInfo(frePlanDtlDataInfo);
        }
        saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
        return saveFrePlanInfoRes;
    }

    //01删除次数保养计划信息
    @Override
    public SaveFrePlanInfoRes RmSaveFrePlanInfo(SaveFrePlanInfoReq01[] saveFrePlanInfoReq01s) {
        SaveFrePlanInfoRes saveFrePlanInfoRes=new SaveFrePlanInfoRes();
        SaveFrePlanInfoResB saveFrePlanInfoResB=new SaveFrePlanInfoResB();
        for(SaveFrePlanInfoReq01 saveFrePlanInfoReq01:saveFrePlanInfoReq01s){
            if(saveFrePlanInfoReq01.getFrePlanRd()==null){
                throw new SystemException("","删除失败,请选择对应的次数保养计划再删除!");
            }

            FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveFrePlanInfoReq01.getFrePlanRd());
            if(frePlanInfo==null){
                throw new SystemException("xxx","删除失败，该信息不存在");
            }
            if(frePlanDAO.deleteFrePlan(saveFrePlanInfoReq01.getFrePlanRd())<=0){
                throw new SystemException("xxx","删除该信息失败");
            }
            //删除触发规则信息
            FrePlanTiggerRulelnfo frePlanTiggerRulelnfo=frePlanTiggerRuleDAO.selectFrePlanGd(frePlanInfo.getGuid());
            if(frePlanTiggerRulelnfo!=null){
                if(frePlanTiggerRuleDAO.deleteFrePlanGd(frePlanTiggerRulelnfo.getRuid())<=0){
                    throw new SystemException("xxx","删除关联信息失败");
                }
            }
            //删除次数保养明细
            List<FrePlanDtlDataInfo>  frePlanDtlDataInfos=frePlanDtlDAO.selectAllFrePlanDtlInfo(frePlanInfo.getGuid());
            if(frePlanDtlDataInfos!=null&&frePlanDtlDataInfos.size()>0){
                for(FrePlanDtlDataInfo frePlanDtlDataInfo:frePlanDtlDataInfos){
                    if(frePlanDtlDAO.deleteFrePlanDtlInfo(frePlanDtlDataInfo.getRuid())<=0){
                        throw new SystemException("xxx","删除细表信息失败");
                    }
                }
            }

        }
        saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
        return saveFrePlanInfoRes;
    }

    //02修改次数保养计划信息
    @Override
    public SaveFrePlanInfoRes ModSaveFrePlanInfo(SaveFrePlanInfoReq02 saveFrePlanInfoReq02) {
        SaveFrePlanInfoRes saveFrePlanInfoRes=new SaveFrePlanInfoRes();
        SaveFrePlanInfoResB saveFrePlanInfoResB=new SaveFrePlanInfoResB();

        FrePlanInfo frePlanInfo=frePlanDAO.selectFrePlan(saveFrePlanInfoReq02.getFrePlanRd());
        if(saveFrePlanInfoReq02.getFrePlanRd()==null){
            throw new SystemException("xxx","修改失败，该标识不存在");
        }
        if(frePlanInfo==null){
            throw new SystemException("xxx","修改失败，该信息不存在");
        }
        if(saveFrePlanInfoReq02.getFrePlanName()==null||"".equals(saveFrePlanInfoReq02.getFrePlanName())){
            throw new SystemException("xxx","修改失败，名称不能为空");
        }
        if(saveFrePlanInfoReq02.getStatus()==null||"".equals(saveFrePlanInfoReq02.getStatus())){
            throw new SystemException("xxx","修改失败，状态不能为空");
        }

        if(saveFrePlanInfoReq02.getReaRd()==null){
            throw new SystemException("xxx","修改失败，保养原因不能为空");
        }

        if(saveFrePlanInfoReq02.getFileVerRd()==null){
            throw new SystemException("xxx","修改失败，保养标准作业书不能为空");
        }
        if(saveFrePlanInfoReq02.getUseNum()==null){
            throw new SystemException("xxx","修改失败，使用量不能为空");
        }
        if(saveFrePlanInfoReq02.getUnitType()==null){
            throw new SystemException("xxx","修改失败，单位类型不能为空");
        }


        //修改次数保养信息
        FrePlanInfo frePlanInfo1=frePlanDAO.selectAllFrePlanName(saveFrePlanInfoReq02.getFrePlanName());
        if(frePlanInfo1!=null&&!frePlanInfo1.getFrePlanName().equals(frePlanInfo.getFrePlanName())){
            throw new SystemException("xxx","修改失败，名称已存在");
        }
        frePlanInfo.setReference(saveFrePlanInfoReq02.getReference());
        frePlanInfo.setFrePlanName(saveFrePlanInfoReq02.getFrePlanName());
        frePlanInfo.setStatus(saveFrePlanInfoReq02.getStatus());
        frePlanInfo.setReference(saveFrePlanInfoReq02.getReference());
        if(saveFrePlanInfoReq02.getDescription()!=null){
            frePlanInfo.setDescription(saveFrePlanInfoReq02.getDescription());
        }


        //查询原因代码
        ReaCodeInfo reaCodeInfo=reasonDAO.SelectReacodeInfoByruid(saveFrePlanInfoReq02.getReaRd());
        if(reaCodeInfo!=null){
            frePlanInfo.setReaCodeGd(reaCodeInfo.getGuid());
        }else {
            throw new SystemException("xx","原因代码不存在");
        }

        //查询任务新
        FileVerInfo fileVerInfo=fileVerDAO.SelectByfileVerRd(saveFrePlanInfoReq02.getFileVerRd());
        if(fileVerInfo!=null){
            frePlanInfo.setFileVerGd(fileVerInfo.getGuid());
        }else {
            throw new SystemException("xx","文件不存在");
        }
        frePlanInfo.setUseNum(saveFrePlanInfoReq02.getUseNum());
        frePlanInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        frePlanInfo.setLastModifyTime(new Date());
        frePlanInfo.setRemark(saveFrePlanInfoReq02.getRemark());

        if(frePlanDAO.updateFrePlan(frePlanInfo)<=0){
            throw new SystemException("xxx","修改失败");
        }



        //修改次数保养规则信息
        FrePlanTiggerRulelnfo frePlanTiggerRulelnfo=frePlanTiggerRuleDAO.selectFrePlanGd(frePlanInfo.getGuid());
        if(saveFrePlanInfoReq02.getUpUseNum()==null){
            frePlanTiggerRulelnfo.setUpUseNum(0);
        }else {
            frePlanTiggerRulelnfo.setUpUseNum(saveFrePlanInfoReq02.getUpUseNum());
        }
        if(saveFrePlanInfoReq02.getDownUseNum()==null){
            frePlanTiggerRulelnfo.setDownUseNum(0);
        }else {
            frePlanTiggerRulelnfo.setDownUseNum(saveFrePlanInfoReq02.getDownUseNum());
        }

        if(saveFrePlanInfoReq02.getPresetUseNum()!=null){
            frePlanTiggerRulelnfo.setPresetUseNum(saveFrePlanInfoReq02.getPresetUseNum());
        }

        //提前邮件提醒
        if(saveFrePlanInfoReq02.getPresetEmailDistributionRd()!=null){
            EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveFrePlanInfoReq02.getPresetEmailDistributionRd());
            if(emailDistributionInfo!=null){
                frePlanTiggerRulelnfo.setPresetEmailDistributionGd(emailDistributionInfo.getGuid());
            }
        }else {
            frePlanTiggerRulelnfo.setPresetEmailDistributionGd("");
        }
        //提前邮件内容提醒
        if(saveFrePlanInfoReq02.getPresetEmailMessageRd()!=null){

            EmailMessageInfo emailMessageInfo=emailMessageDAO.SelectEmailMessageByID(saveFrePlanInfoReq02.getPresetEmailMessageRd());
            if(emailMessageInfo!=null){
                frePlanTiggerRulelnfo.setPresetEmailMessageGd(emailMessageInfo.getGuid());
            }
        }else {
            frePlanTiggerRulelnfo.setPresetEmailMessageGd("");
        }
        //到期
        if(saveFrePlanInfoReq02.getStartEmailDistributionRd()!=null){

            EmailDistributionInfo emailDistributionInfo1=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveFrePlanInfoReq02.getStartEmailDistributionRd());
            if(emailDistributionInfo1!=null){
                frePlanTiggerRulelnfo.setStartEmailDistributionGd(emailDistributionInfo1.getGuid());
            }

        }else {
            frePlanTiggerRulelnfo.setStartEmailDistributionGd("");
        }
        if(saveFrePlanInfoReq02.getStartEmailMessageRd()!=null) {
            EmailMessageInfo emailMessageInfo1 = emailMessageDAO.SelectEmailMessageByID(saveFrePlanInfoReq02.getStartEmailMessageRd());
            if (emailMessageInfo1 != null) {
                frePlanTiggerRulelnfo.setStartEmailMessageGd(emailMessageInfo1.getGuid());
            }
        }else {
            frePlanTiggerRulelnfo.setStartEmailMessageGd("");
        }
        //frePlanTiggerRulelnfo.setOverTimeAction(saveFrePlanInfoReq02.getOverTimeAction());

        //过期
        if(saveFrePlanInfoReq02.getOverEmailDistributionRd()!=null) {
            EmailDistributionInfo emailDistributionInfo2 = emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveFrePlanInfoReq02.getOverEmailDistributionRd());
            if (emailDistributionInfo2 != null) {
                frePlanTiggerRulelnfo.setOverdueEmailDistributionGd(emailDistributionInfo2.getGuid());
            }
        }else {
            frePlanTiggerRulelnfo.setOverdueEmailDistributionGd("");
        }
        if(saveFrePlanInfoReq02.getOverEmailMessageRd()!=null) {
            EmailMessageInfo emailMessageInfo2 = emailMessageDAO.SelectEmailMessageByID(saveFrePlanInfoReq02.getOverEmailMessageRd());
            if (emailMessageInfo2 != null) {
                frePlanTiggerRulelnfo.setOverdueEmailMessageGd(emailMessageInfo2.getGuid());
            }
        }else {
            frePlanTiggerRulelnfo.setOverdueEmailMessageGd("");
        }
        frePlanTiggerRulelnfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        frePlanTiggerRulelnfo.setLastModifyTime(new Date());
        if(frePlanTiggerRuleDAO.updateFrePlanInfo(frePlanTiggerRulelnfo)<=0){
            throw new SystemException("xxx","修改规则信息失败");
        }


        //修改次数保养明细信息
        List<FrePlanDtlDataInfo>  frePlanDtlDataInfos=frePlanDtlDAO.selectAllFrePlanDtlInfo(frePlanInfo.getGuid());
        if(frePlanDtlDataInfos!=null&&frePlanDtlDataInfos.size()>0){
            for(FrePlanDtlDataInfo frePlanDtlDataInfo:frePlanDtlDataInfos){
                if(frePlanDtlDAO.deleteFrePlanDtlInfo(frePlanDtlDataInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除细表信息失败");
                }
            }
        }
        for(pnc.mesadmin.dto.SaveFrePlanInfo.FrePlanDtlInfo frePlanDtlInfo:saveFrePlanInfoReq02.getFrePlanDtlInfo()){
            FrePlanDtlDataInfo frePlanDtlDataInfo=new FrePlanDtlDataInfo();
            if(!Pattern.compile("^[0-9]*[1-9][0-9]*$").matcher(frePlanDtlInfo.getMaxExCount()).matches()){
                throw new SystemException("xxx", "修改失败，最大执行次数请输入正整数");
            }
            frePlanDtlDataInfo.setCreator(CommonUtils.readUser().getRealName());
            frePlanDtlDataInfo.setCreateTime(new Date());
            frePlanDtlDataInfo.setGuid(CommonUtils.getRandomNumber());
            frePlanDtlDataInfo.setFrePlanGd(frePlanInfo.getGuid());
            //查询任务信息
            TaskInfo taskInfo=taskDAO.selectTaskInfoByRuid(frePlanDtlInfo.getTaskRd());
            if(taskInfo==null){
                throw new SystemException("","请输入填写正确的任务信息");
            }
            frePlanDtlDataInfo.setTaskGd(taskInfo.getGuid());
            frePlanDtlDataInfo.setTaskName(frePlanDtlInfo.getTaskName());
            frePlanDtlDataInfo.setTaskItemName(frePlanDtlInfo.getTaskItemName());
            frePlanDtlDataInfo.setSureType(frePlanDtlInfo.getSureType());
  /*          frePlanDtlDataInfo.setMinExCount(frePlanDtlInfo.getMinExCount());*/
            frePlanDtlDataInfo.setMaxExCount(Integer.valueOf(frePlanDtlInfo.getMaxExCount()));
            frePlanDtlDAO.insertFrePlanDtlDataInfo(frePlanDtlDataInfo);
        }

        saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
        return saveFrePlanInfoRes;
    }
}
