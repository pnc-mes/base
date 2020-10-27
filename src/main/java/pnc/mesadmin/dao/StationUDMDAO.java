package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.StationUDMInfo;

import java.util.List;

/**
 * Created by 郝赞 on 2018/10/16.
 * 上下料配置信息DAO
 */
public interface StationUDMDAO {

    //根据工位GD查询上下料信息
    List<StationUDMInfo> seleceStationGd(String StationGd);
    //插入上下料信息
    int insertStationUDMInfo(StationUDMInfo stationUDMInfo);
    //根据工位标识删除上下料信息
    int deleteStationUDMInfo(String StationGd);

    StationUDMInfo seleceByStationGdUDMType(@Param("stationGd") String stationGd,
                                            @Param("uDMType") String uDMType);
}
