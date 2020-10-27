package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SplitBatchInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次拆分信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-07-10
 * 修改人：
 * 修改时间：
 */
public interface SplitBatchDAO {

    //新增批次拆分信息  (By-pjf)
    int InsertSplitBatch(SplitBatchInfo argSplitBatchInfo);

    //根据源批次查询批次拆分信息  (By-pjf)
    List<SplitBatchInfo> SelectBySBatch(String argSBatch);

}
