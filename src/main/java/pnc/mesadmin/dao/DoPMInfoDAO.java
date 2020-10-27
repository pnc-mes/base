package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DoPMInfo;

import java.util.List;

//保养计划任务执行明细信息表
public interface DoPMInfoDAO {
    List<DoPMInfo> SelectByDotaskReq(DoPMInfo model);

    int InsertDoPm(DoPMInfo doPMInfo);

    List<DoPMInfo> SelectDoPMInfoByPMObjTypeandPMObjGdandStatus(@Param("PMObjType") String PMObjType, @Param("PMObjGd") String PMObjGd);

    List<DoPMInfo> SelectByPMTaskGd(String PMTaskGd);

}
