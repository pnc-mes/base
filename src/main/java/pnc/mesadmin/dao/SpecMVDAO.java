package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SpecMVInfo;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：非标准移动信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-09-28
 * 修改人：
 * 修改时间：
 */
public interface SpecMVDAO {

    //新增非标准移动信息  (By-pjf)
    int InsertSpecMV(SpecMVInfo argSpecMVInfo);
}
