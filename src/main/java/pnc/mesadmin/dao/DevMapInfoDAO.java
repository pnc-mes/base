package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DevMapInfo;

import java.util.List;

/**
 * Created by 郝赞 on 2018/10/9.
 */
public interface DevMapInfoDAO {
    //新增设备映射信息
    int InsertDevMapInfo(DevMapInfo devMapInfo);

    //修改设备映射信息
    int UpdateDevMapInfo(DevMapInfo devMapInfo);

    //根据DevGd查询设备映射
    List<DevMapInfo> SelectDevMap(String DevGd);

    int DeleteDevMapInfo(int Ruid);

    DevMapInfo SelectDevMapInfoByDevGdAndDevMapCode(@Param("DevGd") String DevGd, @Param("DevMapCode") String DevMapCode);

    DevMapInfo SelectGuid(String Guid);

    //根据设备映射Gd查询映射信息
    DevMapInfo SelectGuidDevMap(String Guid);
    //根据Ruid查询信息
    DevMapInfo SelectRuidDevMap(int Ruid);
}
