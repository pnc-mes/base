package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PrintScriptOutInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：脚本输出参数信息表
 * 创建人：潘俊峰
 * 创建时间：2018-12-08
 * 修改人：
 * 修改时间：
 */
public interface PrintScriptOutDAO {

    /**
     * 根据模板标识获取脚本输出参数
     *
     * @param printTGd
     * @return
     */
    List<PrintScriptOutInfo> selectByPrintTGd(String printTGd);

    //新增
    int InsertPrintScriptOutInfo(PrintScriptOutInfo model);

    //根据打印GD删除
    int DelPrintScriptOutInfoBYPGD(String printTGd);
}
