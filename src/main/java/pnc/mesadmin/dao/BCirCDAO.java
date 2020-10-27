package pnc.mesadmin.dao;


import pnc.mesadmin.entity.BCirCInfo;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：生产流转条件日志信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-14
 * 修改人：
 * 修改时间：
 */
public interface BCirCDAO {

    //新增  (By-pjf)
    int InsertBCirCInfo(BCirCInfo argBCirCInfo);

    //根据Ruid生产流转条件日志信息  (By-pjf)
    BCirCInfo SelectByRuid(int argRuid);

    //根据Guid生产流转条件日志信息  (By-pjf)
    BCirCInfo SelectByGuid(int argGuid);

}
