package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoRes;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoReqBD00;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoRes;
import pnc.mesadmin.dto.GetLInfo.GetLInfoRes;
import pnc.mesadmin.dto.GetStoreInfo.GetStoreInfoReqBD00;
import pnc.mesadmin.dto.GetStoreInfo.GetStoreInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveLInfo.SaveLInfoReqBD00;
import pnc.mesadmin.dto.SaveLInfo.SaveLInfoReqBD02;
import pnc.mesadmin.dto.SaveLInfo.SaveLInfoRes;
import pnc.mesadmin.dto.SaveStoreInfo.SaveStoreInfoReqBD00;
import pnc.mesadmin.dto.SaveStoreInfo.SaveStoreInfoReqBD02;
import pnc.mesadmin.dto.SaveStoreInfo.SaveStoreInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.LocationInfo;
import pnc.mesadmin.entity.StoreInfo;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：仓库信息Servcie
 * 创建人：刘福志
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public interface StoreIService {
    //查询仓库列表信息
    GetAllStoreInfoRes QALLselectAllStoreInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo, GetAllStoreInfoReqBD00 argBD00) throws SystemException;
    //查询仓库信息
    GetStoreInfoRes GetStoreInfo(int StoreRd) throws SystemException;
    //查询库位列表信息
    GetAllLInfoRes QALLselectAllLocationInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;
    //筛选库位信息
    GetAllLInfoRes QALLselectAllLocationInfo1(GetStoreInfoReqBD00 argGetStoreInfoReqBD00) throws SystemException;
    //查询库位信息
    GetLInfoRes GetselectLocationInfo(int lRd) throws SystemException;
    //导出库位信息
    ByteArrayOutputStream ExportGetLInfo(GetStoreInfoReqBD00 argGetStoreInfoReqBD00);
    //保存库位信息
    SaveLInfoRes AddinsertLocationInfo(SaveLInfoReqBD00 busData00, LocationInfo locationInfo) throws SystemException;
    //更新库位信息
    SaveLInfoRes ModupdateLocationInfo(SaveLInfoReqBD02 busData02, LocationInfo locationInfo) throws SystemException;
    //删除库位信息
    SaveLInfoRes RmdeleteLocationInfo(int ruid) throws SystemException;

    //保存仓库信息
    SaveStoreInfoRes AddinsertStoreInfo(SaveStoreInfoReqBD00 busData00, StoreInfo storeInfo) throws SystemException;
    //更新仓库信息
    SaveStoreInfoRes ModupdateStoreInfo(SaveStoreInfoReqBD02 busData02, StoreInfo storeInfo) throws SystemException;
    //删除仓库信息
    SaveStoreInfoRes RmdeleteStoreInfo(int ruid) throws SystemException;
}
