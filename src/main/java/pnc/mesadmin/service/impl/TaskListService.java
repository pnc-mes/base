package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DCDAO;
import pnc.mesadmin.dao.TaskDAO;
import pnc.mesadmin.dao.TaskDetailDAO;
import pnc.mesadmin.dto.GetAllTaskInfo.GetAllTaskInfoRes;
import pnc.mesadmin.dto.GetAllTaskInfo.GetAllTaskInfoResB;
import pnc.mesadmin.dto.GetAllTaskInfo.GetAllTaskInfoResD;
import pnc.mesadmin.dto.GetTaskInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveTaskInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.TaskDetailInfo;
import pnc.mesadmin.entity.TaskInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.TaskListIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 10:22
 * @Description:
 */
@Transactional
@Service
public class TaskListService implements TaskListIService {
    @Resource
    private TaskDAO taskDAO;

    @Resource
    private TaskDetailDAO taskDetailDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private DCDAO dcdao;

    //获取任务列表信息
    @Override
    public GetAllTaskInfoRes QALLGetAllTaskListInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllTaskInfoRes getAllTaskInfoRes =new GetAllTaskInfoRes();
        GetAllTaskInfoResB getAllTaskInfoResB = new GetAllTaskInfoResB();
        List<GetAllTaskInfoResD> getAllTaskInfoResDS =new ArrayList<GetAllTaskInfoResD>();
        List<TaskInfo> taskInfos =baseIService.QALLBaseInfo(TaskDAO.class,"selectAllTaskInfo",argInitDataFields,argPageInfo);
        if(taskInfos !=null&& taskInfos.size()>0){
            for(TaskInfo taskInfo : taskInfos){
                GetAllTaskInfoResD getAllTaskInfoResD =new GetAllTaskInfoResD();
                getAllTaskInfoResD.setTaskRd(taskInfo.getRuid());
                getAllTaskInfoResD.setTaskName(taskInfo.getTaskName());
                getAllTaskInfoResD.setStatus(taskInfo.getStatus());
                getAllTaskInfoResDS.add(getAllTaskInfoResD);
            }
        }
        getAllTaskInfoResB.setData(getAllTaskInfoResDS);
        getAllTaskInfoRes.setBody(getAllTaskInfoResB);
        return getAllTaskInfoRes;
    }

    //新增任务列表信息
    @Override
    public SaveTaskInfoRes ADDSaveTaskListInfoRes(SaveTaskInfoReq00 saveTaskInfoReq00) {
        SaveTaskInfoRes saveTaskInfoRes =new SaveTaskInfoRes();
        SaveTaskInfoResB saveTaskInfoResB =new SaveTaskInfoResB();
        if(saveTaskInfoReq00.getTaskName()==null||"".equals(saveTaskInfoReq00.getTaskName())){
            throw new SystemException("xx","新增失败，任务列表名称不能为空");
        }

        TaskInfo taskInfo = taskDAO.selectTaskInfoByTaskName(saveTaskInfoReq00.getTaskName());
        if(taskInfo !=null){
            throw new SystemException("xx","新增失败，任务列表名称已存在");
        }
        TaskInfo taskInfo1 =new TaskInfo();
        taskInfo1.setGuid(CommonUtils.getRandomNumber());
        taskInfo1.setTaskName(saveTaskInfoReq00.getTaskName());
        taskInfo1.setDescription(saveTaskInfoReq00.getDescription());
        taskInfo1.setCreator(CommonUtils.readUser().getRealName());
        taskInfo1.setCreateTime(new Date());
        taskInfo1.setStatus(saveTaskInfoReq00.getStatus());
        taskInfo1.setRemark(saveTaskInfoReq00.getRemark());
        taskInfo1.setTaskType(saveTaskInfoReq00.getTaskType());
        int i=1;
        if(saveTaskInfoReq00.getTaskDetailInfo()!=null&& saveTaskInfoReq00.getTaskDetailInfo().size()>0){
            for(SaveTaskInfoReq00TaskDetail saveTaskInfoReq00TaskDetail : saveTaskInfoReq00.getTaskDetailInfo()){
                TaskDetailInfo taskDetailInfo =new TaskDetailInfo();

                if(saveTaskInfoReq00TaskDetail.getTaskItemName()==null||"".equals(saveTaskInfoReq00TaskDetail.getTaskItemName())){
                   throw new SystemException("xxx","新增失败，第"+i+"行的任务描述名称不能为空");
                }
                if(saveTaskInfoReq00TaskDetail.getSureType()==null||"".equals(saveTaskInfoReq00TaskDetail.getSureType())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的确认方式不能为空");
                }
            /*    if(saveTaskInfoReq00TaskDetail.getMinExCount()<=0||"".equals(saveTaskInfoReq00TaskDetail.getMinExCount())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的最小执行次数不能小于零");
                }*/
                if(saveTaskInfoReq00TaskDetail.getMaxExCount()<=0||"".equals(saveTaskInfoReq00TaskDetail.getMaxExCount())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的最大执行次数不能小于零");
                }
                taskDetailInfo.setGuid(CommonUtils.getRandomNumber());
                taskDetailInfo.setTaskItemName(saveTaskInfoReq00TaskDetail.getTaskItemName());
                taskDetailInfo.setSureType(saveTaskInfoReq00TaskDetail.getSureType());
  /*              taskDetailInfo.setMinExCount(saveTaskInfoReq00TaskDetail.getMinExCount());*/
                taskDetailInfo.setMaxExCount(saveTaskInfoReq00TaskDetail.getMaxExCount());
                taskDetailInfo.setTaskGd(taskInfo1.getGuid());
                taskDetailInfo.setCreator(CommonUtils.readUser().getRealName());
                taskDetailInfo.setCreateTime(new Date());
                taskDetailDAO.insertTaskDetailInfo(taskDetailInfo);
            }
        }

       taskDAO.insertTaskInfo(taskInfo1);

        saveTaskInfoRes.setBody(saveTaskInfoResB);
        return saveTaskInfoRes;
    }

    //删除
    @Override
    public SaveTaskInfoRes RmSaveTaskListInfoRes(SaveTaskInfoReq01 saveTaskInfoReq01) {
        SaveTaskInfoRes saveTaskInfoRes =new SaveTaskInfoRes();
        SaveTaskInfoResB saveTaskInfoResB =new SaveTaskInfoResB();
        if("".equals(saveTaskInfoReq01.getTaskRd())|| saveTaskInfoReq01.getTaskRd()<=0){
            throw new SystemException("xx","删除失败，该信息不能为空");
        }
        TaskInfo taskInfo = taskDAO.selectTaskInfoByRuid(saveTaskInfoReq01.getTaskRd());
        if(taskInfo !=null){
            //删细表
            List<TaskDetailInfo> taskDetailInfos = taskDetailDAO.selectTaskDetailInfoBytaskGd(taskInfo.getGuid());
            if(taskDetailInfos !=null&& taskDetailInfos.size()>=1){
                for (TaskDetailInfo taskDetailInfo : taskDetailInfos){
                    if(taskDetailDAO.deleteTaskDetailInfo(taskDetailInfo.getRuid())<=0){
                        throw new SystemException("xx","删除细表失败，该信息不存在");
                    }
                }
            }
            if(taskDAO.deleteTaskInfo(taskInfo.getRuid())<=0){
                throw new SystemException("xx","删除主表失败，该信息不存在");
            }
        }else {
            throw new SystemException("xx","删除失败，该信息不存在");
        }

        saveTaskInfoRes.setBody(saveTaskInfoResB);
        return saveTaskInfoRes;
    }

    //修改
    @Override
    public SaveTaskInfoRes ModSaveTaskListInfoRes(SaveTaskInfoReq02 saveTaskInfoReq02) {
        SaveTaskInfoRes saveTaskInfoRes =new SaveTaskInfoRes();
        SaveTaskInfoResB saveTaskInfoResB =new SaveTaskInfoResB();
        if(saveTaskInfoReq02.getTaskName()==null||"".equals(saveTaskInfoReq02.getTaskName())){
            throw new SystemException("xx","修改失败，任务列表名称不能为空");
        }
        TaskInfo taskInfo = taskDAO.selectTaskInfoByRuid(saveTaskInfoReq02.getTaskRd());
        TaskInfo taskInfo1 = taskDAO.selectTaskInfoByTaskName(saveTaskInfoReq02.getTaskName());
        if(taskInfo ==null){
            throw new SystemException("xx","修改失败，该信息不存在");
        }
        if(taskInfo1 !=null&&!taskInfo.getTaskName().equals(saveTaskInfoReq02.getTaskName())){
            throw new SystemException("xx","修改失败，该名称已存在");
        }
        //删细表
        List<TaskDetailInfo> taskDetailInfos = taskDetailDAO.selectTaskDetailInfoBytaskGd(taskInfo.getGuid());
        if(taskDetailInfos !=null&& taskDetailInfos.size()>=1){
            for (TaskDetailInfo taskDetailInfo : taskDetailInfos){
                if(taskDetailDAO.deleteTaskDetailInfo(taskDetailInfo.getRuid())<=0){
                    throw new SystemException("xx","删除细表失败，该信息不存在");
                }
            }
        }
        //再新增
        int i=1;
        if(saveTaskInfoReq02.getTaskDetailInfo()!=null&& saveTaskInfoReq02.getTaskDetailInfo().size()>0){
            for(SaveTaskInfoReq02TaskDetail saveTaskInfoReq00TaskDetail : saveTaskInfoReq02.getTaskDetailInfo()){
                TaskDetailInfo taskDetailInfo =new TaskDetailInfo();

                if(saveTaskInfoReq00TaskDetail.getTaskItemName()==null||"".equals(saveTaskInfoReq00TaskDetail.getTaskItemName())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的任务描述名称不能为空");
                }
                if(saveTaskInfoReq00TaskDetail.getSureType()==null||"".equals(saveTaskInfoReq00TaskDetail.getSureType())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的确认方式不能为空");
                }
             /*   if(saveTaskInfoReq00TaskDetail.getMinExCount()<=0||"".equals(saveTaskInfoReq00TaskDetail.getMinExCount())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的最小执行次数不能小于零");
                }*/
                if(saveTaskInfoReq00TaskDetail.getMaxExCount()<=0||"".equals(saveTaskInfoReq00TaskDetail.getMaxExCount())){
                    throw new SystemException("xxx","新增失败，第"+i+"行的最大执行次数不能小于零");
                }
                taskDetailInfo.setGuid(CommonUtils.getRandomNumber());
                taskDetailInfo.setTaskItemName(saveTaskInfoReq00TaskDetail.getTaskItemName());
                taskDetailInfo.setSureType(saveTaskInfoReq00TaskDetail.getSureType());
/*                taskDetailInfo.setMinExCount(saveTaskInfoReq00TaskDetail.getMinExCount());*/
                taskDetailInfo.setMaxExCount(saveTaskInfoReq00TaskDetail.getMaxExCount());
                taskDetailInfo.setCreator(CommonUtils.readUser().getRealName());
                taskDetailInfo.setTaskGd(taskInfo.getGuid());
                taskDetailInfo.setCreateTime(new Date());
                taskDetailDAO.insertTaskDetailInfo(taskDetailInfo);
            }
        }


        taskInfo.setRemark(saveTaskInfoReq02.getRemark());
        taskInfo.setDescription(saveTaskInfoReq02.getDescription());
        taskInfo.setTaskName(saveTaskInfoReq02.getTaskName());
        taskInfo.setLastModifyTime(new Date());
        taskInfo.setStatus(saveTaskInfoReq02.getStatus());
        taskInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        taskInfo.setTaskType(saveTaskInfoReq02.getTaskType());
        if(taskDAO.updateTaskInfo(taskInfo)<=0){
            throw new SystemException("xx","更新主表信息失败");
        }

        saveTaskInfoRes.setBody(saveTaskInfoResB);
        return saveTaskInfoRes;
    }

    //获取任务信息
    @Override
    public GetTaskInfoRes GetGetTaskListInfoRes(GetTaskInfoReq00 getTaskInfoReq00) {
        GetTaskInfoRes getTaskInfoRes =new GetTaskInfoRes();
        GetTaskInfoResB getTaskInfoResB =new GetTaskInfoResB();
        GetTaskInfoResD getTaskInfoResD =new GetTaskInfoResD();
        List<GetTaskInfoResDTaskDatial> getTaskInfoResDTaskDatials =new ArrayList<GetTaskInfoResDTaskDatial>();


        if(getTaskInfoReq00.getTaskRd()<=0){
            throw new SystemException("xxx","查询失败，该信息不能为空");
        }

        TaskInfo taskInfo = taskDAO.selectTaskInfoByRuid(getTaskInfoReq00.getTaskRd());
        if(taskInfo !=null){
            getTaskInfoResD.setTaskRd(taskInfo.getRuid());
            getTaskInfoResD.setTaskName(taskInfo.getTaskName());
            getTaskInfoResD.setDescription(taskInfo.getDescription());
            getTaskInfoResD.setStatus(taskInfo.getStatus());
            getTaskInfoResD.setTaskType(taskInfo.getTaskType());
            getTaskInfoResD.setCreator(taskInfo.getCreator());
            getTaskInfoResD.setCreateTime(DateUtil.format(taskInfo.getCreateTime()));
            getTaskInfoResD.setLastModifyMan(taskInfo.getLastModifyMan());
            getTaskInfoResD.setLastModifyTime(DateUtil.format(taskInfo.getLastModifyTime()));
            getTaskInfoResD.setRemark(taskInfo.getRemark());
            List<TaskDetailInfo> taskDetailInfos = taskDetailDAO.selectTaskDetailInfoBytaskGd(taskInfo.getGuid());
            if(taskDetailInfos !=null&& taskDetailInfos.size()>0){
                for(TaskDetailInfo taskDetailInfo : taskDetailInfos){
                    GetTaskInfoResDTaskDatial getTaskInfoResDTaskDatial =new GetTaskInfoResDTaskDatial();
                    getTaskInfoResDTaskDatial.setTaskDetailRd(taskDetailInfo.getRuid());
                    getTaskInfoResDTaskDatial.setMaxExCount(taskDetailInfo.getMaxExCount());
                    getTaskInfoResDTaskDatial.setTaskItemName(taskDetailInfo.getTaskItemName());
                    getTaskInfoResDTaskDatial.setSureType(taskDetailInfo.getSureType());
                    getTaskInfoResDTaskDatials.add(getTaskInfoResDTaskDatial);
                }
                getTaskInfoResD.setTaskDetailInfo(getTaskInfoResDTaskDatials);
            }

        }
        getTaskInfoResB.setData(getTaskInfoResD);
        getTaskInfoRes.setBody(getTaskInfoResB);
        return getTaskInfoRes;
    }
}
