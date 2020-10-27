package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.RkDtlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  入库单扫描信息表(DAO)
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public interface RkTkIDAO {
    //批量插入入库单扫描明细
    int InsertRkDtlInfoList(@Param("argRkDtlInfos") List<RkDtlInfo> argRkDtlInfos);
    //插入入库单扫描明细
    int InsertRkDtlInfo(RkDtlInfo argRkDtlInfo);

    //根据入库任务明细查询入库单扫描信息
    List<RkDtlInfo> SelectRkDtlInfoByrkTkDtlGd(String argrkTkDtlGd);

    //根据入库单号和条码查询入库单扫描信息
    RkDtlInfo SelectRkDtlInfoByRkCodeAndBarCode(@Param("rkCode") String argRkCode, @Param("barCode") String argBarCode);

    List<RkDtlInfo> SelectRkDtlInfoByRkCode(@Param("rkCode") String argRkCode);

    RkDtlInfo SelectRkDtlInfoByRetMaDtlGuid(String guid);

    RkDtlInfo SelectRkDtlInfoguid(String guid);

    List<RkDtlInfo> SelectRkDtlInfoMaVerGdandStoreGd(@Param("argMaVerGd") String argMaVerGd, @Param("argStoreGd") String argStoreGd);
}
