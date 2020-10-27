package pnc.mesadmin.service;

import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoReqBD;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoRes;
import pnc.mesadmin.dto.GetPDCInfo.GetPDCInfoReqBD00;
import pnc.mesadmin.dto.GetPDCInfo.GetPDCInfoRes;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SavePDInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.PDTkInfo;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点管理Service接口
 * 创建人：刘福志
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public interface PDIService {
    //查询盘点单列表信息
    GetAllPDInfoRes QALLselectAllPDTkInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询盘点单信息
    GetPDInfoRes GetselectByRuid(int PDRd) throws SystemException;

    //查询盘点差异信息
    GetPDCInfoRes GetselectByPDRd(GetPDCInfoReqBD00 argGetPDCInfoReqBD00)throws SystemException;

    //新增盘点单信息
    SavePDInfoRes AddinsertPDTkInfo(SavePDInfoReqBD00 busData00, PDTkInfo pdTkInfo) throws SystemException;

    //更新盘点单信息
    SavePDInfoRes ModupdatePDTkInfo(SavePDInfoReqBD02 busData02, PDTkInfo pdTkInfo) throws SystemException;

    //盘点差异确认信息
    SavePDInfoRes confirmPDTkInfo(SavePDInfoReqBD04 busData04) throws SystemException;

    //删除盘点单信息
    SavePDInfoRes RmdeletePDTkInfo(int ruid) throws SystemException;

    //导出盘点差异信息  --王怀龙
    ByteArrayOutputStream ExportPDCInfo(ExportPDCInfoReqBD busData);
}
