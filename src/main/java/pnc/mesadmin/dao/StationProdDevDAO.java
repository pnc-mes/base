package pnc.mesadmin.dao;

import pnc.mesadmin.entity.StationProdDevInfo;

import java.util.List;

/**
 * Created by haozan on 2018/10/24.
 */
public interface StationProdDevDAO {
    /**
     * 根据工位标识查询
     * @param stationGd
     * @return
     */
    List<StationProdDevInfo> SelectByStationGd(String stationGd);

    //插入工位生产设备信息
    int insertByStation(StationProdDevInfo stationProdDevInfo);
    //清空工位生产设备信息
    int deleteStation(String stationGd);

    List<StationProdDevInfo> SelectStationProdDevByDevGD(String devGd);
}
