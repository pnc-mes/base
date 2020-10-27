package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SerialRuleInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：序号管理DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public interface SNDAO {
    //查询序号管理信息
    List<SerialRuleInfo> SelectSerialRuleInfo();

    //根据主键（ruid）查询序号管理信息
    SerialRuleInfo SelectSerialRuleInfoByruid(int argruid);

    //新增序号管理信息
     int InsertSerialRuleInfo(SerialRuleInfo argSerialRuleInfo);

     //删除序号管理
     int DeleteSerialRuleInfo(int argruid);

     //更新序号管理
    int UpdataSerialRuleInfo(SerialRuleInfo argSerialRuleInfo);

    //根据guid查询序号管理信息
    SerialRuleInfo  SelectSerialRuleInfoByguid(String argguid);
}
