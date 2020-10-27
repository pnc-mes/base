package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DevBatchRelInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：机台批次关系信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-11-12
 * 修改人：
 * 修改时间：
 */
public interface DevBatchRelDAO {

    /**
     * 新增机台批次关系信息  (By-pjf)
     * @param devBatchRelInfo
     * @return
     */
    int InsertDevBatchRelInfo(DevBatchRelInfo devBatchRelInfo);

    /**
     * 删除机台批次关系信息  (By-pjf)
     * @param devGd
     * @param batch
     * @return
     */
    int DeleteByDevGdBatch(@Param("devGd") String devGd,
                           @Param("batch") String batch);

    /**
     * 根据DevGD查询机台批次关系信息
     * @param devGd
     * @return
     */
    List<DevBatchRelInfo> SelectByDevGdBatch(@Param("devGd") String devGd);

    /**
     * 根据batch查询机台批次关系信息
     * @param batch
     * @return
     */
    List<DevBatchRelInfo> SelectByBatch(String batch);




}
