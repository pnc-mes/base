package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoRes;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoResD;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResD;
import pnc.mesadmin.dto.GetDVInfo.GetDVInfoRes;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoRes;
import pnc.mesadmin.dto.GetDcInfo.GetDcInfoRes;
import pnc.mesadmin.dto.SaveDcInfo.*;
import pnc.mesadmin.entity.DCInfo;
import pnc.mesadmin.entity.DCVerInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息Service接口
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public interface DCIService {

    PageResult<GetDcInfoRes> QALLGetAllDcInfoRes(BaseRequest req);

    /**
     * 查询数据采集列表
     */
    GetAllDcInfoRes QALLselectDCInfos(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;
    /**
     * 查询数据采集信息
     */
    GetDcInfoRes  GetselectDCInfoByDcRd(int dcRd) throws SystemException;

    //tdo新增 张亮亮
    SaveDcInfoRes AddsaveDCInfo(SaveDcInfoReqBD00 argSaveDcInfoReqBD00) throws SystemException;

    //tdo删除 张亮亮
    SaveDcInfoRes RmdeleteDcInfoByRuid(int dcRd) throws SystemException;


    /**
     * 更新根节点以及下面所有的对应子节点信息
     */
    SaveDcInfoRes  Addupdate(SaveDcInfoReqBD02 busData02, DCInfo objDCInfo);

    /**
     * 获取数据采集所有版本信息
     */
    GetDVTreeInfoRes GetselectDCVerInfoByDcRd(int dcRd) throws SystemException;
    /**
     * 获取数据采集版本详细信息
     */
    GetDVInfoRes  GetselectDCVerInfoByDCVerRd(int dcVerRd) throws SystemException;
    /**
     * 新增版本
     */
    SaveDcInfoRes AddsaveDCVerInfo(SaveDcInfoReqBD04 busData04, DCVerInfo argObjDCVerInfo) throws SystemException;
    /**
     * 删除版本
     */
    SaveDcInfoRes RmdeleteDcVerInfoByRuid(int dcVerRd) throws SystemException;


    //复制
    SaveDcInfoRes AddargSaveDcInfoReqBD03(SaveDcInfoReqBD03 argSaveDcInfoReqBD03) throws SystemException;


}
