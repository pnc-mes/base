package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PrintTBFInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印模板绑定字段信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
public interface PrintTBFDAO {

    //新增模板字段信息  (By-pjf)
    int InsertPrintTBFInfo(PrintTBFInfo argPrintTBFInfo);

    //删除打印模板字段信息  (By-pjf)
    int DeletePrintTBFInfo(String argPrintTGd);

    //根据Guid删除打印模板信息  (By-pjf)
    int DeleteByGuid(String argGuid);

    //修改打印模板绑定字段信息  (By-pjf)
    int UpdateByRuid(PrintTBFInfo argPrintTBFInfo);

    //根据模板信息Guid获取所有打印模板绑定字段信息  (By-pjf)
    List<PrintTBFInfo> SelectAllPrintTBFInfo(String argPrintTGd);

    //根据模板信息Ruid获取打印模板绑定字段信息  (By-pjf)
    PrintTBFInfo SelectPrintTBFInfo(int argRuid);
}
