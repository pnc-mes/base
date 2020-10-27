package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.StationAlarmInfo;

import java.util.List;

/**
 * Created by 郝赞 on 2018/10/16.
 * 警报提示信息DAO
 */
public interface StationAlarmDAO {
    //根据工位Guid查询警报信息
    List<StationAlarmInfo> selectStationAlarm(String StationGuid);
    //插入警报提示信息
    int insertStationAlarmInfo(StationAlarmInfo stationAlarmInfo);
    //根据工位标识删除信息
    int deleteStationAlarmInfo(String StationGuid);
    //根据工位GD和TriggerType查询警报预警信息
    List<StationAlarmInfo> selectStationGDAndExcepType(@Param("StationGd") String StationGd, @Param("ExcepType") List ExcepType);
}
