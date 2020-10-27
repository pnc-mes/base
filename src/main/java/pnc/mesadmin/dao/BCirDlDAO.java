package pnc.mesadmin.dao;


import pnc.mesadmin.entity.BCirDlInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：生产流转信息日志DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-14
 * 修改人：
 * 修改时间：
 */
public interface BCirDlDAO {

    //新增 (By-pjf)
    int InsertBCirDl(BCirDlInfo argBCirDlInfo);

    //根据Ruid查询生产流转日志信息  (By-pjf)
    BCirDlInfo SelectByRuid(int argRuid);

    //根据Guid查询生产流转日志信息  (By-pjf)
    BCirDlInfo SelectByGuid(int argGuid);

}
