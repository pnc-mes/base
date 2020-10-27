package pnc.mesadmin.service;

import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoReq00;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoReq01;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoRes;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/10/22 17:49
 * @Description:
 */
public interface DevMonitorIService {
    //Res
    SaveDExLogInfoRes AddSaveDExLogInfoRes(SaveDExLogInfoReq00 saveDExLogInfoReq00);

    SaveDExLogInfoRes AddSaveDExLogInfoReq01(SaveDExLogInfoReq01 saveDExLogInfoReq01);



}
