package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PrintTFInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印模板业务字段信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
public interface PrintTFDAO {

    //获取所有打印模板业务字段信息  (By-pjf)
    List<PrintTFInfo> SelectAllPrintTFInfo();

}
