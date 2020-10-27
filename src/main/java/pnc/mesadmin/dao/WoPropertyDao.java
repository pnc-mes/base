package pnc.mesadmin.dao;

import pnc.mesadmin.entity.WoPropertyInfo;

public interface WoPropertyDao {
    int InsertWoPropertyInfo(WoPropertyInfo model);

    int UpdateWoPropertyInfo(WoPropertyInfo model);

    WoPropertyInfo SelectWoPropertyInfoByWoGd(String woGd);
}
