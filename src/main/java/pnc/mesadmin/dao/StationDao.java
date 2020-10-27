package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.dto.GetStationDto.GetAllTreeStationInfoListResB;
import pnc.mesadmin.entity.StationInfo;

import java.util.List;

public interface StationDao {
    List<StationInfo> SelectAllStationInfo();

    int InsertStationInfo(StationInfo stationInfo);

    int delStationInfo(Integer stationRd);

    StationInfo SelectByRuId(Integer ruId);

    StationInfo SelectByGuId(String guId);

    int SaveStationInfo(StationInfo stationInfo);

    List<StationInfo> SelectAllStationInfoByName(String stationName);

    /**
     * 根据用户Gd、执行类型查询工位信息
     *
     * @param argExecGd
     * @param argExecType
     * @return
     */
    List<StationInfo> SelectByExecGdExecType(@Param("argExecGd") String argExecGd,
                                             @Param("argExecType") String argExecType);

    /**
     * 根据用户Gd、执行状态查询工位信息
     *
     * @param argExecGd
     * @param argStatus
     * @return
     */
    List<StationInfo> SelectByExecGdStatus(@Param("argExecGd") String argExecGd,
                                           @Param("argStatus") String argStatus);

    //根据LineRd查询工位信息
    List<StationInfo> SelectStationTInfo(String LineGd);

    //根据ExecType查询工位信息
    List<StationInfo> SelectExecType(String ExecType);

    List<StationInfo> SelectByLineGdSpecVerGd(@Param("lineGd") String lineGd,
                                              @Param("specVerGd") String specVerGd);

    //根据用户UserID查询工位配置信息
    List<StationInfo> SelectUserStationInfo(String userID);

    //根据线体GD删除工位信息
    int delStationInfoByLineGd(String lineGd);

    //根据LineGD查询工位信息
    List<GetAllTreeStationInfoListResB> SelectStationInfoByLineGd(String LineGd);
}
