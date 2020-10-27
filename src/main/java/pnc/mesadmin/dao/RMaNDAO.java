package pnc.mesadmin.dao;

import pnc.mesadmin.entity.RMaNInfo;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public interface RMaNDAO {
    //查询产成品入库通知单列表信息
    List<RMaNInfo> SelectRMaNInfo();

    //查询产成品入库通知单信息
    RMaNInfo SelectRMaNInfoByRuid(int argRuid);

    RMaNInfo SelectRMaNInfoGuid(String guid);

    //通过单号查询产成品入库通知单列表信息 WHL
    RMaNInfo SelectRMaNInfoByRMaNCode(String rmaNCode);

    //新增产成品入库信息
    int InsertRMaNInfo(RMaNInfo argRMaNInfo);

    //删除入库通知单信息
    int DeleteRMaNInfo(int argRuid);

    //更新入库通知单信息
    int UpdateRMaNInfo(RMaNInfo argRMaNInfo);

}
