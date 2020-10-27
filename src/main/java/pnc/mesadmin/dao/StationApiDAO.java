package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.common.StationApiInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 工位API配置DAO
 * @author: yin.yang
 * @create: 2018-12-13
 **/
public interface StationApiDAO {
    List<StationApiInfo> SelectALLByTriggerTypes(@Param("TriggerType") List<String> TriggerType, @Param("StationGd") String StationGd);

    //插入
    int InserBy(StationApiInfo stationApiInfo);

    //删除
    int DelAll(String StationGd);

    List<StationApiInfo> SelectStationGd(String StationGd);



}
