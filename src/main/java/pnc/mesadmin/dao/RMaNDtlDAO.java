package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.RMaNDtlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public interface RMaNDtlDAO {
    //查询产成品入库列表信息根据主表的Guid
    List<RMaNDtlInfo> SelectRMaNDtlInfoByRMaNGd(String argRMaNGd);
    //zll
    List<RMaNDtlInfo> SelectRMaNDtlInfoByRMaNCode(String argRMaNCode);

    //通过Ruid查询产成品入库通知单明细信息 WHL
    RMaNDtlInfo SelectRMaNDtlInfoByRuid(int ruid);

    //通过Guid查询产成品入库通知单明细信息 WHL
    RMaNDtlInfo SelectRMaNDtlInfoByGuid(String guid);

    //根据主表的Guid和物料guid查询明细信息
    RMaNDtlInfo SelectRMaNDtlInfoByRMaNGdAndMaVerGd(@Param("rMaNGd") String argRMaNGd, @Param("maVerGd") String argMaVerGd);

    //新增产成品明细信息
    int InsertRMaNDtlInfo(RMaNDtlInfo argRMaNDtlInfo);

    //删除入库通知单明细信息
    int DeleteRMaNDtlInfo(int argRuid);

    //更新入库通知单明细信息
    int UpdateRMaNInfo(RMaNDtlInfo argRMaNDtlInfo);
}
