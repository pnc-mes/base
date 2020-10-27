package pnc.mesadmin.dao;


import pnc.mesadmin.entity.DevRepairLogInfo;

/**
 * Created by PNC on 2017/8/18.
 *  设备维修
 */
public interface DevRepairLogDAO {
    //新增
    int InsertDevRepairLog(DevRepairLogInfo model);
}
