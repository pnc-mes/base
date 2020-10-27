package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllShiftsInfo.GetAllShiftsInfoResD;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoRes;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoResD;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoRes;
import pnc.mesadmin.dto.SaveShiftInfo.SaveShiftInfoReqBD03;
import pnc.mesadmin.dto.SaveShiftInfo.SaveShiftInfoRes;
import pnc.mesadmin.dto.SaveUrcyInfo.SaveUrcyInfoReqBD00;
import pnc.mesadmin.dto.SaveUrcyInfo.SaveUrcyInfoReqBD01;
import pnc.mesadmin.dto.SaveUrcyInfo.SaveUrcyInfoReqBD02;
import pnc.mesadmin.dto.SaveUrcyInfo.SaveUrcyInfoRes;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.entity.UrgencyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息Service实现类接口
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
public interface UrgencyIService {

    GetAllUrcyInfoRes QALLselectAllUrgencyInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;
    //查询紧急代码列表信息
    PageResult<GetAllUrcyInfoResD> QALLselectAllUrgencyInfoNew(BaseRequest req) throws SystemException;

    //查询紧急代码信息
    GetUrcyInfoRes GetselectByRuid(int urcyRd) throws SystemException;

    //保存紧急代码信息
    SaveUrcyInfoRes AddinsertUrgencyInfo(SaveUrcyInfoReqBD00 busData00, UrgencyInfo urgencyInfo) throws SystemException;

    //更新紧急代码信息
    SaveUrcyInfoRes ModupdateUrgencyInfo(SaveUrcyInfoReqBD02 busData02, UrgencyInfo urgencyInfo) throws SystemException;

    //删除紧急代码信息
    SaveUrcyInfoRes RmdeleteUrgencyInfo(int ruid) throws SystemException;

    //获取当前紧急代码信息
//    UrgencyInfo GetUrgencyInfo() throws SystemException;
}
