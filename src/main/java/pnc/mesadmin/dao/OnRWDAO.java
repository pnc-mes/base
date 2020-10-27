package pnc.mesadmin.dao;

import pnc.mesadmin.entity.OnRWInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线重工日志信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-09-28
 * 修改人：
 * 修改时间：
 */
public interface OnRWDAO {

    //新增在线重工日志信息  (By-pjf)
    int InsertOnRW(OnRWInfo argOnRWInfo);
}
