package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.PrintTInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印模板信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public interface PrintTDAO extends BaseMapper<PrintTInfo> {

    //新增打印模板  (By-pjf)
    int InsertPrintTInfo(PrintTInfo argPrintTInfo);

    //根据guid删除打印模板  (By-pjf)
    int DeletePrintTInfo(String argGuid);

    //根据guid修改打印模板信息  (By-pjf)
    int UpdatePrintTInfo(PrintTInfo argPrintTInfo);

    //获取所有打印模板信息  (By-pjf)
    List<PrintTInfo> SelectAllPrintTInfo();

    //根据ruid获取打印模板信息  (By-pjf)
    PrintTInfo SelectPrintTInfo(int argRuid);

    //根据Guid获取打印模板信息  (By-pjf)
    PrintTInfo SelectByGuid(String argGuid);

    //根据TempName获取打印模板信息  (By-pjf)
    PrintTInfo SelectByTempName(String argTempName);
}
