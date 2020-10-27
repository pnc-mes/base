package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CarrierRelationLogInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具关系信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-12-10
 * 修改人：
 * 修改时间：
 */
public interface CarrierRelationLogDAO {

    /**
     * 新增载具关系日志信息  (By-pjf)
     * @param carrierRelationLogInfo
     * @return
     */
    int InsertCarrierRelationLog(CarrierRelationLogInfo carrierRelationLogInfo);
}
