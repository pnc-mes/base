package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevSMInfo.GetAllDevSMInfoRes;
import pnc.mesadmin.dto.GetAllDevSMInfo.GetAllDevSMInfoResD;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetDevSMInfo.GetDevSMInfoRes;
import pnc.mesadmin.dto.SaveDevSMInfo.SaveDevSMInfoReq00;
import pnc.mesadmin.dto.SaveDevSMInfo.SaveDevSMInfoReq01;
import pnc.mesadmin.dto.SaveDevSMInfo.SaveDevSMInfoReq02;
import pnc.mesadmin.dto.SaveDevSMInfo.SaveDevSMInfoRes;

import java.util.List;

/**
 * Created by PNC on 2017/8/21.
 */
public interface DevSMIService {
    //dto获取设备状态模型信息
    GetAllDevSMInfoRes QALLGetAllDevSMInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    /**
     * dto获取设备状态模型信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllDevSMInfoResD> QALLGetAllDevSMNew(BaseRequest req);

    //获取设备状态模型信息
    GetDevSMInfoRes GetGetDevSMInfoRes(int ruid) throws SystemException;

    //新增设备状态模型
    SaveDevSMInfoRes AddSaveDevSMInfoRes(SaveDevSMInfoReq00 argSaveDevSMInfoReq00);

    //删除设备状态模型信息
    SaveDevSMInfoRes RmSaveDevSMInfoRes(SaveDevSMInfoReq01 argSaveDevSMInfoReq01);

    //dto更新设备状态模型
    SaveDevSMInfoRes ModSaveDevSMInfoRes(SaveDevSMInfoReq02 argSaveDevSMInfoReq02);
}
