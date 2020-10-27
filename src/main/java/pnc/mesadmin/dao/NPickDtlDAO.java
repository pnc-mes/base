package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.NPickDtlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/30.
 */
public interface NPickDtlDAO {

    //新增领料单明细
    int InsertNPickDtlInfo(NPickDtlInfo argPickDtlInfo);

    //根据领料单明细Guid查询领料单明细信息
    NPickDtlInfo SelectNPickDtlInfoByGuid(String argGuid);

    //根据领料单明细ID查询领料单明细信息
    NPickDtlInfo SelectNPickDtlInfoByRuid(int argRuid);

    //根据领料单明细ID更新
    int UpdateNPickDtlInfo(NPickDtlInfo argNPickDtlInfo);

    //根据领料单标识，查看领料单明细信息
    List<NPickDtlInfo> SelectNPickDtlInfoByPickGd(String argPickGd);

    List<NPickDtlInfo> SelectNPickDtlInfosByPickCode(String argPickCode);

    //删除领料单明细
    int DeleteNPickDtlInfoByRuid(int argRuid);

    //通过领料单号获取领料单信息
    List<NPickDtlInfo> SelectNPickDtlInfoByPickCode(String pickCode);

    //汇总
    List<NPickDtlInfo> SelectNPickDtlInfoByMaVerGd(String argMaVerGd);

    NPickDtlInfo SelectNPickDtlInfoByNPickCodeAndMaverGd(@Param("argPickCode") String argPickCode, @Param("argMaVerGd") String argMaVerGd);

}
