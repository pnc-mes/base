package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.common.GearInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：组件分档信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-09-26
 * 修改人：
 * 修改时间：
 */
public interface GearDAO {

    /**
     * 根据工单号、批次号查询 (By-pjf)
     *
     * @param argWoCode
     * @param argBatch
     * @return
     */
    GearInfo SelectByWoCodeBatch(@Param("argWoCode") String argWoCode,
                                 @Param("argBatch") String argBatch);

    int UpdateByGearInfoRuid(GearInfo model);

    int InsertGear(GearInfo argGearInfo);

    /**
     * 根据工单号、批次号查询
     *
     * @param argBatch
     * @return
     */
    GearInfo SelectByBatch(@Param("argBatch") String argBatch);

    List<GearInfo> SelectByAllBatch(@Param("argBatch") String argBatch);
}
