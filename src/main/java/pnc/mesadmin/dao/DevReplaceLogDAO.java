package pnc.mesadmin.dao;


import pnc.mesadmin.entity.DevReplaceLogInfo;

/**
 * Created by PNC on 2017/8/18.
 *  设备更换
 */
public interface DevReplaceLogDAO {
    //新增
    int InsertDevReplaceLog(DevReplaceLogInfo model);
}
