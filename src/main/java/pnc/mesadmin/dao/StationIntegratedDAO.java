package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.StationIntegratedInfo;

import java.util.List;

/**
 * Created by 郝赞 on 2018/10/16.
 * 设备集成信息DAO
 */
public interface StationIntegratedDAO {
    //根据工位GD查询设备集成信息
    List<StationIntegratedInfo> selectStationGD(String StationGd);

    //插入设备集成信息
    int insertStationIntegratedInfo(StationIntegratedInfo stationIntegratedInfo);

    //根据工位标识删除信息
    int delectStationIntegratedInfo(String StationGd);

    //根据工位GD和TriggerType查询设备集成信息
    List<StationIntegratedInfo> selectStationGDAndTriggerType(@Param("StationGd") String StationGd, @Param("TriggerType") List TriggerType);

    //查询关联设备
    List<StationIntegratedInfo> selectStationIntegratedByDevGd(String devGd);

    //查询关联设备映射
    List<StationIntegratedInfo> selectStationIntegratedByDevMapGd(String devMapGd);
}
