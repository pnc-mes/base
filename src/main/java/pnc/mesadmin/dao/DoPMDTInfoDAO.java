package pnc.mesadmin.dao;


import pnc.mesadmin.entity.DoPMDtlInfo;

import java.util.List;

//保养计划任务执行信息表
public interface DoPMDTInfoDAO {
    List<DoPMDtlInfo> SelectDoPMDtlInfoByDoPMGd(String DoPMGd);

    DoPMDtlInfo SelectDoPMDtlInfoByRuid(Integer ruid);

    int UpdateDoPMDtlInfoByRuid(DoPMDtlInfo model);

    int InsertDoPMDtl(DoPMDtlInfo doPMDtlInfo);
}
