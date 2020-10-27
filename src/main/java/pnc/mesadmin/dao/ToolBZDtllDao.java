package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ToolBZDtlInfo;

import java.util.List;

public interface ToolBZDtllDao {

    //设备标准值标识查询
    List<ToolBZDtlInfo> SelectToolBZDtlInfoToolBZGd(String ToolBZGd);
    //设备标准值标识查询
    ToolBZDtlInfo SelectToolBZDtlInfoargruid(String argruid);

    int InsertToolBZDtlInfo(ToolBZDtlInfo toolBZDtlInfo);

    int UpdateDevBZDtlInfo(ToolBZDtlInfo toolBZDtlInfo);

    int DeleteAllToolBZDtlInfoToolBZGd(String ToolBZGd);
}
