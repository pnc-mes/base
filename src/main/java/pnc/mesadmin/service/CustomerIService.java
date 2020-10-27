package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoRes;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoRes;
import pnc.mesadmin.dto.SaveCusInfo.SaveCusInfoReqBD00;
import pnc.mesadmin.dto.SaveCusInfo.SaveCusInfoReqBD02;
import pnc.mesadmin.dto.SaveCusInfo.SaveCusInfoReqBD03;
import pnc.mesadmin.dto.SaveCusInfo.SaveCusInfoRes;
import pnc.mesadmin.entity.CustomerInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：客户信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface CustomerIService {

    PageResult<GetAllCusInfoRes> QALLGetAllCusInfoRes(BaseRequest req);

    //查询客户列表信息
    GetAllCusInfoRes QALLselectAllCustomerInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询客户信息
    GetCusInfoRes GetselectByRuid(int cusRd) throws SystemException;

    //保存客户信息
    SaveCusInfoRes AddinsertCustomerInfo(SaveCusInfoReqBD00 busData00, CustomerInfo customerInfo) throws SystemException;

    //更新客户信息
    SaveCusInfoRes ModupdateCustomerInfo(SaveCusInfoReqBD02 busData02, CustomerInfo customerInfo) throws SystemException;

    //复制客户信息
    SaveCusInfoRes copyCustomerInfo(SaveCusInfoReqBD03 busData03, CustomerInfo customerInfo) throws SystemException;

    //删除客户信息
    SaveCusInfoRes RmdeleteCustomerInfo(int ruid) throws SystemException;
}
