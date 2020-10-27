package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllDotaskInfo.*;
import pnc.mesadmin.entity.common.DJSelectInfo;

import java.util.List;

public interface DoTaskService {
    //获取保养列表信息
    BaseRes GetAllDoTask00(GetAllDotaskRes00 req);

    //获取点检列表信息
    BaseRes GetAllDoTask01(GetAllDotaskRes01 req);

    //保存保养信息
    BaseRes SavePMACInfo00(SaveDotaskRes req);

    //保存点检信息
    BaseRes SavePMACInfo01(SaveDoCheckRes req);

    //生成保养/点检列表
    BaseRes SaveCreateTaskInfo(SaveCreateTaskReqest req);

    //查询所有点检信息
    List<DJSelectInfo> GetAllDJSelectInfo(String checkobjtype, String checkobjgd,
                                          String status, String shiftname, String linename, String createtime1, String createtime2);

    //查询所有保养信息
    List<DJSelectInfo> SelectAllBYSelectInfo(String checkobjtype, String checkobjgd,
                                             String status, String shiftname, String linename, String createtime1, String createtime2);

}
