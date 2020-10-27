package pnc.mesadmin.dao;

import pnc.mesadmin.entity.OEMLineinfo;

import java.util.List;

/**
 * Created by PNC on 2018/7/26.
 */
public interface OEMLineDAO {

    List<OEMLineinfo> SelectOEMLineinfoByGuid(String LineGD);
    List<OEMLineinfo> SelectOEMLineinfoByGui(String OEMLineGD);
    void InsertOEMLineinfo(OEMLineinfo orderLineinfo);

    int UpdateOEMLineinfo(OEMLineinfo orderLineinfo);

    //删除
    int deleteOEMLineinfoByruid(int ruid);
}
