package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PDDtlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点明细信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public interface PDDtlDAO {
    //关联查询盘点单信息表
    List<PDDtlInfo> SelectByGuid(String guid);

    //查询盘点明细所有信息
    List<PDDtlInfo>  SelectAllPDDtlInfo();

    //插入盘点明细信息  王怀龙
    int InsertPDDtlInfo(PDDtlInfo argPDDtlInfo);

    //根据批次查询已盘点批次  王怀龙
    PDDtlInfo SelectPDDtlInfoByBatch(String argBatch);

    //查询批次
    List<PDDtlInfo> SelectByBatch(String argBatch);

    //查询物料代码
    List<PDDtlInfo> SelectBymaterialCode(String materialCode);


    //更新盘点信息的库位和数量
    int UpdatePDDtlInfoLocationAndNum(PDDtlInfo objEPDDtlInfo1);


}
