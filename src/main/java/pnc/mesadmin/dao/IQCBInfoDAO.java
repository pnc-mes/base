package pnc.mesadmin.dao;

import pnc.mesadmin.entity.IQCInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：质检批次DAO
 * 创建人：王怀龙
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public interface IQCBInfoDAO {
    int InsertIQCBInfo(IQCInfo iqcInfo);
    int DeleteIQCBInfoByRuid(int agrRuid);
    int UpdateIQCBInfo(IQCInfo argIQCBInfo);
    List<IQCInfo> SelectAllIQCBInfo();

    IQCInfo SelectIQCBInfoByRuid(int argIqcRd);

    IQCInfo SelectIQCBInfoByBatch(String batch);


}
