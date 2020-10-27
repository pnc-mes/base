package pnc.mesadmin.dao;

import pnc.mesadmin.entity.BomChgInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：变更记录信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-11-17
 * 修改人：
 * 修改时间：
 */
public interface BomChgDAO {

    /**
     * 新增变更记录信息 (pjf)
     * @param argBomChgInfo
     * @return
     */
    int InsertBomChg(BomChgInfo argBomChgInfo);

    List<BomChgInfo> SelectBomChgInfoByBomGd(String guid);

    BomChgInfo SelectByRuid(int bomChgRd);
}
