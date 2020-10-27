package pnc.mesadmin.dao;

import pnc.mesadmin.entity.TaskInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 09:16
 * @Description:
 */
public interface TaskDAO {
    //查询所有任务列表
    List<TaskInfo> selectAllTaskInfo();

    //查询单个的任务列表信息
    TaskInfo selectTaskInfoByRuid(int ruid);

    //查询单个历史任务信息根据taskListName
    TaskInfo selectTaskInfoByTaskName(String taskName);

    //新增历史任务信息
    void insertTaskInfo(TaskInfo taskInfo);

    //删除历史任务信息
    int deleteTaskInfo(int ruid);

    //修改任务历史信息
    int updateTaskInfo(TaskInfo taskInfo);

    //查询单个历史任务信息根据guid
    TaskInfo selectTaskInfoByGuid(String guid);
}
