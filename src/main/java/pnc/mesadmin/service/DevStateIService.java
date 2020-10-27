package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoRes;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoReq00;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveDevSInfo.SaveDevSInfoReq00;
import pnc.mesadmin.dto.SaveDevSInfo.SaveDevSInfoReq01;
import pnc.mesadmin.dto.SaveDevSInfo.SaveDevSInfoReq02;
import pnc.mesadmin.dto.SaveDevSInfo.SaveDevSInfoRes;

import java.util.List;

/**
 * Created by PNC on 2017/8/18.
 */
public interface DevStateIService {
    //dto 查询设备状态列表
    GetAllDevSInfoRes QALLGetAllDevSInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询设备状态信息
    GetDevSInfoRes GetGetDevSInfoRes(GetDevSInfoReq00 argGetDevSInfoReq00);

    //新增设备状态信息
    SaveDevSInfoRes AddSaveDevSInfoRes(SaveDevSInfoReq00 argSaveDevSInfoRes);

    //删除设备状态信息
    SaveDevSInfoRes RmSaveDevSInfoRes(SaveDevSInfoReq01 argSaveSaveDevSInfoReq01);

    //更新设备状态信息
    SaveDevSInfoRes ModSaveDevSInfoRes(SaveDevSInfoReq02 argSaveSaveDevSInfoReq02);
}
