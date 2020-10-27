package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllTaskInfo.GetAllTaskInfoRes;
import pnc.mesadmin.dto.GetTaskInfo.GetTaskInfoReq00;
import pnc.mesadmin.dto.GetTaskInfo.GetTaskInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveTaskInfo.SaveTaskInfoReq00;
import pnc.mesadmin.dto.SaveTaskInfo.SaveTaskInfoReq01;
import pnc.mesadmin.dto.SaveTaskInfo.SaveTaskInfoReq02;
import pnc.mesadmin.dto.SaveTaskInfo.SaveTaskInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 10:22
 * @Description:
 */
public interface TaskListIService {
    //获取任务列表信息
    GetAllTaskInfoRes QALLGetAllTaskListInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //新增任务列表信息
    SaveTaskInfoRes ADDSaveTaskListInfoRes(SaveTaskInfoReq00 saveTaskInfoReq00);

    //删除
    SaveTaskInfoRes RmSaveTaskListInfoRes(SaveTaskInfoReq01 saveTaskInfoReq01);

    //修改
    SaveTaskInfoRes ModSaveTaskListInfoRes(SaveTaskInfoReq02 saveTaskInfoReq02);

    //获取任务信息
    GetTaskInfoRes GetGetTaskListInfoRes(GetTaskInfoReq00 getTaskInfoReq00);
}
