package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PickDtlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/30.
 */
public interface PickDtlDAO {

    //新增领料单明细
    int InsertPickDtlInfo(PickDtlInfo argPickDtlInfo);

    //根据领料单号查询领料单明细信息  --王怀龙
    List<PickDtlInfo> SelectPickDtlInfosByPickCode(String pickCode);

    //根据领料单明细Guid查询领料单明细信息  --王怀龙
    List<PickDtlInfo>  SelectPickDtlInfoByGuid(String argGuid);

    //根据领料单明细ID查询领料单明细信息  --王怀龙
    PickDtlInfo SelectPickDtlInfoByRuid(int argRuid);

    //根据领料单明细ID更新       --王怀龙
    int UpdatePickDtlInfo(PickDtlInfo pickDtlInfo);

    //根据领料单标识，查看领料单明细信息
    List<PickDtlInfo> SelectPickDtlInfoByPickGd(String argPickGd);

    List<PickDtlInfo> SelectPickDtlInfoByPickGdAndMaverGd(@Param("pickGd") String pickGd, @Param("maVerGd") String maVerGd);

    //删除领料单明细
    int DeletePickDtlInfoByRuid(int argRuid);

    PickDtlInfo SelectPickDtlInfoByPickCodeAndMaverGd(@Param("argPickCode") String argPickCode, @Param("argMaVerGd") String argMaVerGd);

    /**
     * 根据领料单查询物料已开单数量
     * @param argAssCode
     * @param argAssSource
     * @param argMaVerGd
     * @return
     */
    Float SelectByAssCodeAssSourceMaVerGd(@Param("argAssCode") String argAssCode,
                                          @Param("argAssSource") String argAssSource,
                                          @Param("argMaVerGd") String argMaVerGd);
}
