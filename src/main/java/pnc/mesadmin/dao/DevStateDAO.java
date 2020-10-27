package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevStateInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/18.
 */
public interface DevStateDAO {
    //查询设备状态列表
    List<DevStateInfo> SelectDevStateInfo();

    //查询设备状态信息
    DevStateInfo SelectDevStateInfoByRuid(int argRuid);

    //增加设备状态信息
    int InsertDevStateInfo(DevStateInfo argInsertDevStateInfo);

    //删除设备状态信息
    int DeleteDevStateInfoByRuid(int argRuid);

    //更新设备状态信息
    int UpdateDevStateInfo(DevStateInfo argInsertDevStateInfo);

    //查询设备状态信息根据名称
    DevStateInfo SelectDevStateInfoByDevSName(String argDevSName);

    //查询设备状态信息根据guid
    DevStateInfo SelectDevStateInfoByGuid(String argGuid);

    //根据设备状态模型Guid查询
    DevStateInfo SelectByDevGd(String argDevSMDtGd);
}
