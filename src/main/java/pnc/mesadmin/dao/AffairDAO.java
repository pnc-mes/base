package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.AffairInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  批次事务操作信息表DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-14
 * 修改人：
 * 修改时间：
 */
public interface AffairDAO {

    //新增  (By-pjf)
    int InsertAffairInfo(AffairInfo argAffairInfo);

    // 根据批次信息查询批次事务操作信息 张亮亮
    List<AffairInfo> SelectAffairInfoBybatch(String strbatch);

    //根据时间段查询和
    List<Integer> SelectAffairByStartTimeAndEndTiemAndLineGd(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("lineGd") String lineGd);
}
