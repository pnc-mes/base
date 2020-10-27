package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ExStationDtlInfo;

import java.util.Date;
import java.util.List;

public interface ExStationDtlDao {
    List<ExStationDtlInfo> SelectAllExStationDtlInfoBySGD(String stationGuid);

    int InsertExStationDtlInfo(ExStationDtlInfo exStationDtlInfo);

    int delExStationDtlInfoByStationGuid(String stationGd);

    //根据用户id查询数据
    List<ExStationDtlInfo> SelectAllByExecGd(String ExecGd);

    List<ExStationDtlInfo> SelectAllByExecGdByUSer(String ExecGd);
    List<ExStationDtlInfo> SelectAllExStationDtlInfoBySGD1(String stationGuid);

    /*StationInfo SelectByRuId(Integer ruId);
,
    CuOrderInfo SelectByGuId(String guId);

    int SaveOrderInfo(CuOrderInfo orderInfo);
;*/
    //根据工位标识和设备/人员标识查询执行实体明细
    List<ExStationDtlInfo> SelectExStationDtlInfo(@Param("StationGd") String StationGd, @Param("ExecGd") String ExecGd);
    //根据工位标识和执行实体ID查询信息
    ExStationDtlInfo SelectSIDEID(@Param("StationGd") String StationGd, @Param("ExecGd") String ExecGd);

    //修改执行实体的工位标识StationGd
    int UpdataStationGd(@Param("StationGd") String StationGd, @Param("ExecGd") String ExecGd, @Param("Remark") String Remark,
                        @Param("LastModifyTime") Date LastModifyTime, @Param("LastModifyMan") String LastModifyMan);

    List<ExStationDtlInfo> SelectExecGd(String ExecGd);

    //只查询绑定的设备信息
    List<ExStationDtlInfo> SelectAllByDevGd(String devGd);

    //根据线体查询绑定的菜单信息
    List<ExStationDtlInfo> SelectAllByResGdAndLineGd(@Param("resGd") String resGd, @Param("lineGd") String lineGd);
}
