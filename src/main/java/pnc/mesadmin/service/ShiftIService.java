package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllShiftsInfo.GetAllShiftsInfoResD;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoRes;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveShiftInfo.SaveShiftInfoReqBD00;
import pnc.mesadmin.dto.SaveShiftInfo.SaveShiftInfoReqBD02;
import pnc.mesadmin.dto.SaveShiftInfo.SaveShiftInfoReqBD03;
import pnc.mesadmin.dto.SaveShiftInfo.SaveShiftInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.ShiftInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：班别管理Service接口
 * 创建人：乔帅阳
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public interface ShiftIService {
    //获取班别列表信息
    PageResult<GetAllShiftsInfoResD> QALLselectAllShiftsInfoInfo(BaseRequest req) throws SystemException;

    //获取班别信息
    GetShiftInfoRes GetselectByShiftsId(int ShiftRd) throws SystemException;
    //保存班别信息
    SaveShiftInfoRes AddinsertShiftInfo(SaveShiftInfoReqBD00 busData00) throws SystemException;
    //更新班别信息
    SaveShiftInfoRes ModupdateCustomerInfo(SaveShiftInfoReqBD02 busData02) throws SystemException;
    //删除班别信息
    SaveShiftInfoRes RmdeleteShiftInfo(int Ruid) throws SystemException;
    //复制班别信息
    SaveShiftInfoRes copyShiftInfo(SaveShiftInfoReqBD03 busData03, ShiftInfo shiftInfo) throws SystemException;
    //获取当前班别信息
    ShiftInfo GetShiftInfo() throws SystemException;
}
