package pnc.mesadmin.dao;

import pnc.mesadmin.entity.BAttachInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次附加信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-07-22
 * 修改人：
 * 修改时间：
 */
public interface BAttachDAO {

    /**
     * 新增批次附加信息  (By-pjf)
     * @param argBAttachInfo
     * @return
     */
    int InsertBAttachInfo(BAttachInfo argBAttachInfo);

    /**
     * 根据批次查询批次附加信息 (By-pjf)
     * @param argBatch
     * @return
     */
    BAttachInfo SelectByBatch(String argBatch);

    /*更新批次查询附加信息*/
    int UpdateBAttachInfoByBatch(BAttachInfo argBAttachInfo);
}
