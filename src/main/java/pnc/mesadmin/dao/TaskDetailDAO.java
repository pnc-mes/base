package pnc.mesadmin.dao;

import pnc.mesadmin.entity.TaskDetailInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 10:35
 * @Description:
 */
public interface TaskDetailDAO {
    //根据主表标识taskListGd 查询明细信息
    List<TaskDetailInfo> selectTaskDetailInfoBytaskGd(String taskGd);

    //新增任务明细
    void insertTaskDetailInfo(TaskDetailInfo taskDetailInfo);

    //删除任务明细
    int deleteTaskDetailInfo(int ruid);

}
