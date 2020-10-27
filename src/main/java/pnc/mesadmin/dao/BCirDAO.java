package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.BCirInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：生产流转信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-14
 * 修改人：
 * 修改时间：
 */
public interface BCirDAO {

    /**
     * 新增生产流转信息  (By-pjf)
     * @param argBCirInfo
     * @return
     */
    int InsertBCir(BCirInfo argBCirInfo);

    /**
     * 修改生产流转信息  (By-pjf)
     * @param argBCirInfo
     * @return
     */
    int UpdateBCir(BCirInfo argBCirInfo);

    /**
     * 根据Ruid查询生产流转信息  (By-pjf)
     * @param argRuid
     * @return
     */
    BCirInfo SelectByRuid(int argRuid);

    /**
     * 根据Guid查询生产流转信息  (By-pjf)
     * @param argGuid
     * @return
     */
    BCirInfo SelectByGuid(int argGuid);
    BCirInfo SelectSByGuid(String argGuid);
    /**
     * 根据Batch查询生产流转信息  (By-pjf)
     * @param argBatch
     * @return
     */
    BCirInfo SelectByBatch(String argBatch);

    /**
     * 判断当前工序是否被使用  (By-pjf)
     * @param argWFVerGd
     * @param argSpecVerGd
     * @return
     */
    int SelectByWFVerGdSpecVerGd(@Param("argWFVerGd") String argWFVerGd,
                                 @Param("argSpecVerGd") String argSpecVerGd);
}
