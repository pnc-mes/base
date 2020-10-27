package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllDevInfo.DevSaveRequest;
import pnc.mesadmin.dto.GetAllDevInfo.GetDevListsRequest;
import pnc.mesadmin.utils.BaseResponse;

/**
 * @Description
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
public interface DeviceIService {

    //获取设备列表
    BaseResponse GetDevList(GetDevListsRequest request);

    //获取设备详情
    BaseResponse GetDevDetails(DevSaveRequest request);

    //新增设备
    BaseResponse AddDevInfo(DevSaveRequest request);

    //删除设备
    BaseResponse RmDevInfo(DevSaveRequest request);

    //更新设备
    BaseResponse ModSaveDevInfo(DevSaveRequest request);
}
