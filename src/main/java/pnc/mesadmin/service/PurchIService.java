package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoRes;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoRes;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoReq00;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SavePurchInfo.*;

import java.util.List;

/**
 * Created by PNC on 2017/8/23.
 */
public interface PurchIService {
    //获取采购单列表信息
    GetAllPurchInfoRes QALLGetAllPurchInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取采购单信息
    GetPurchInfoRes GetGetPurchInfoRes(GetPurchInfoReq00 argGetPurchInfoReq00);

    //保存采购单信息
    SavePurchInfoRes AddSavePurchInfoRes(SavePurchInfoReq00 argSavePurchInfoReq00);

    //删除采购单信息
    SavePurchInfoRes RmSavePurchInfoRes(SavePurchInfoReq01 argSavePurchInfoReq01);

    //修改采购单信息
    SavePurchInfoRes ModSavePurchInfoRes(SavePurchInfoReq02 argSavePurchInfoReq02);

    //下达采购单
    SavePurchInfoRes ModSavePurchInfoRes03(SavePurchInfoReq03 argSavePurchInfoReq03);

    //取消采购单
    SavePurchInfoRes ModSavePurchInfoRes04(SavePurchInfoReq04 argSavePurchInfoReq04);

    //根据采购单任意字段查询采购单明细信息
    public GetAllPDlInfoRes QALLGetAllPDlInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
}
