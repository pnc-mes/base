package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevBatchLogInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次上下机台日志信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-11-24
 * 修改人：
 * 修改时间：
 */
public interface DevBatchLogDAO {

    /**
     * 新增批次上下机台日志信息  (By-pjf)
     * @param devBatchLogInfo
     * @return
     */
    int InsertDevBatchLog(DevBatchLogInfo devBatchLogInfo);
}
