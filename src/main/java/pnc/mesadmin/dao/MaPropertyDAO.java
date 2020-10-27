package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MaPropertyInfo;

/**
 * Created by liufuzhi on 2018/1/31.
 */
public interface MaPropertyDAO {
    MaPropertyInfo SelectByMaVerGd(String argMaVerGd);
    int InsertMaPropertyInfo(MaPropertyInfo maPropertyInfo);
    int UpdateMaPropertyInfo(MaPropertyInfo maPropertyInfo);
    int DeleteMaPropertyInfo(int argRuid);

    //根据MaVerGd查询F信息
    MaPropertyInfo SelectByMaVerF(@Param("MaVerGd") String MaVerGd);


}
