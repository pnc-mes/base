package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CloseInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：关闭日志DAO
 * 创建人：潘俊峰
 * 创建时间：2017-08-31
 * 修改人：
 * 修改时间：
 */
public interface CloseDAO {

    //新增 (By-pjf)
    int InsertClose(CloseInfo argCloseInfo);
}
