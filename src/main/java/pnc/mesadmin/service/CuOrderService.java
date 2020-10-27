package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCuOrderInfo.GetAllCuOrderInfoRes;
import pnc.mesadmin.dto.GetAllCuOrderInfo.GetCuOrderReqBD00;
import pnc.mesadmin.dto.GetAllCuOrderInfo.SaveCuOrderReqBD00;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

public interface CuOrderService {
    //获取订单列表信息
    GetAllCuOrderInfoRes GetAllCOInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取订单关联工单信息
    GetAllCuOrderInfoRes GetAllCOBInfo(GetCuOrderReqBD00 getCuOrderReqBD00);

    //获取订单详情信息
    GetAllCuOrderInfoRes GetCOInfo(GetCuOrderReqBD00 getCuOrderReqBD00);

    //保存
    GetAllCuOrderInfoRes AddSaveCOInfo(SaveCuOrderReqBD00 reqBD00);

    //新增
    GetAllCuOrderInfoRes AddCOInfo(SaveCuOrderReqBD00 reqBD00);

    //删除
    GetAllCuOrderInfoRes RmDelCOInfo(SaveCuOrderReqBD00 reqBD00);

}
