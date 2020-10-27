package pnc.mesadmin.dao;

import pnc.mesadmin.entity.NPickDtlInfo_1;

import java.util.List;

/**
 * Created by PNC on 2017/8/30.
 */
public interface NPickDtlDAO_1 {

    //根据领料单标识，查看领料单明细信息
    List<NPickDtlInfo_1> SelectNPickDtlInfoByPickGd_1(String argPickGd);

    void InsertNPickDtlInfo_1(NPickDtlInfo_1 argnpickDtlInfo);

    int DeleteNPickDtlInfo_1(int argruid);

    int UpdateNPickDtlInfo_1(NPickDtlInfo_1 argnpickDtlInfo);

    NPickDtlInfo_1 SelectNPickDtlInfoByRuid(int argRuid);
}
