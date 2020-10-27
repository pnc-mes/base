package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllMaSDTInfo.GetAllMaSDTInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaSDTInfo.GetAllMaSDTInfoRes;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoReqBD01;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-6-16
 * 修改人：
 * 修改时间：
 */
public interface InStoreIService {
    //查询物料库存信息00
    GetAllMaSTInfoRes GetInstockInfo(GetAllMaSTInfoReqBD00 argGetAllMaSTInfoReqBD00)throws SystemException;

    //查询物料库存信息01
    GetAllMaSTInfoRes GetselectInstockInfo(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01)throws SystemException;

    //查询物料库存信息02InitData查询
    GetAllMaSTInfoRes GetInstockInfo1(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01)throws SystemException;

    //查询物料库存明细列表信息
    GetAllMaSDTInfoRes GetselectInstockDtlInfo(GetAllMaSDTInfoReqBD00 argGetAllMaSDTInfoReqBD00)throws SystemException;

    //筛选库存主表
    GetAllMaSTInfoRes GetselectInstockInfo1(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01)throws SystemException;

    //导出库存主表信息
    ByteArrayOutputStream ExportInStore(GetAllMaSTInfoReqBD01 argGetAllMaSTInfoReqBD01);

    //导出库存主表信息
    ByteArrayOutputStream CPExport(String MaVerRd, String StoreRd, String LRd, String F1, String F2, String F3, String F4, String CreateTime1, String CreateTime2, String Batch);
}
