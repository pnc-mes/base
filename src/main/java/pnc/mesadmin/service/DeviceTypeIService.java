package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoRes;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoResD;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoRes;
import pnc.mesadmin.dto.SaveDevTypeInfo.SaveDevTypeInfoReqBD00;
import pnc.mesadmin.dto.SaveDevTypeInfo.SaveDevTypeInfoReqBD01;
import pnc.mesadmin.dto.SaveDevTypeInfo.SaveDevTypeInfoReqBD02;
import pnc.mesadmin.dto.SaveDevTypeInfo.SaveDevTypeInfoRes;
import pnc.mesadmin.entity.DevTypeInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备类型信息Service实现类接口
 * 创建人：蒋顾毅
 * 创建时间：2020-9-17
 * 修改人：
 * 修改时间：
 */
public interface DeviceTypeIService {
    //查询紧急代码列表信息
    PageResult<GetAllDevTypeInfoResD> QALLselectAllDevTypeInfo(BaseRequest req) throws SystemException;

    //查询紧急代码信息
    GetDevTypeInfoRes GetselectByRuid(int devTypeRd) throws SystemException;

    //保存紧急代码信息
    SaveDevTypeInfoRes AddinsertDevTypeInfo(SaveDevTypeInfoReqBD00 busData00, DevTypeInfo devTypeInfo) throws SystemException;

    //更新紧急代码信息
    SaveDevTypeInfoRes ModupdateDevTypeInfo(SaveDevTypeInfoReqBD02 busData02, DevTypeInfo devTypeInfo) throws SystemException;

    //删除紧急代码信息
    SaveDevTypeInfoRes RmdeleteDevTypeInfo(int ruid) throws SystemException;
}
