package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.PickDtlInfo_1;

import java.util.List;

/**
 * Created by PNC on 2017/8/30.
 */
public interface PickDtlDAO_1 {

    /**
     * 根据领料单标识，查看领料单明细信息
     * @param argPickGd
     * @return
     */
    List<PickDtlInfo_1> SelectPickDtlInfoByPickGd_1(String argPickGd);

    void InsertPickDtlInfo_1(PickDtlInfo_1 pickDtlInfo);

    int UpdatePickDtlInfo_1(PickDtlInfo_1 pickDtlInfo_1);

    int DeletePickDtlByPickGd(String argPickGd);

    int DeleteByGuid(String argGuid);

    Float SelectByAssCodeAssSourceReMaVerGd(@Param("argAssCode") String argAssCode,
                                            @Param("argAssSource") String argAssSource,
                                            @Param("argReMaVerGd") String argReMaVerGd);
}
