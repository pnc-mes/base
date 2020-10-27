package pnc.mesadmin.service;

import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.dto.WmsMaterialsBDTO.*;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次Service
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public interface WmsMaterialsBService {

    /**
     * 原材料批次创建
     * @param req
     * @throws SystemException
     */
    void AddMaterialsB(WmsMaterialsBAddReq req) throws SystemException;

    /**
     * 原材料批次拆分
     * @param req
     * @throws SystemException
     */
    void AddSplitBatch(WmsMaterialsBSplitReq req) throws SystemException;

    /**
     * 原材料批次查询
     * @param batch
     * @return
     * @throws SystemException
     */
    WmsMaterialsBSubRes GetSplitBatch(String batch) throws SystemException;

}
