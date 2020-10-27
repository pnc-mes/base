package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevBZInfo;

public interface DevBZDAO {



    //设备标识查询
    DevBZInfo SelectDevBZInfoDevGd(String DevGd);
    //设备标识查询
    DevBZInfo SelectDevBZInfoGuid(String argguid);
    //设备Ruid查询
    DevBZInfo SelectDevBZInfoRuid(String argRuid);

    int InsertDevBZInfo(DevBZInfo devBZInfo);

    int UpdateDevBZInfo(DevBZInfo devBZInfo);

    int DeleteAllDevBZInfoRuid(String DevGd);
}
