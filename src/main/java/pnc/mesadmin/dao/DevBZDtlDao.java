package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevBZDtlInfo;

import java.util.List;

public interface DevBZDtlDao {
    //设备标准值标识查询
    List<DevBZDtlInfo> SelectDevBZDtlDevBZGd(String DevBZGd);
    //设备标准值标识查询
    DevBZDtlInfo SelectDevBZDtlargruid(String argruid);

    int InsertDevBZDtl(DevBZDtlInfo devBZDtlInfo);

    int UpdateDevBZDtlInfo(DevBZDtlInfo devBZInfo);

    int DeleteAllDevBZDtlDevBZGd(String DevBZGd);
}
