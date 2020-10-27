package pnc.mesadmin.dao;

import pnc.mesadmin.entity.StationAlarmRevInfo;

import java.util.List;

/**
 * Created by 郝赞 on 2018/10/16.
 * 警报 接收人员 信息DAO
 */
public interface StationAlarmRevDAO {
    //根据警报提示标识查询警报接收人员信息
    List<StationAlarmRevInfo> selectStationAlarmRevInfo(String StationAlarmGd);
    //插入警报接收人员信息
    int insertStationAlarmRevInfo(StationAlarmRevInfo stationAlarmRevInfo);
    //根据工位标识删除信息
    int deleteStationAlarmRevInfo(String StationAlarmGd);
}
