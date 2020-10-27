package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CusDataDtlCInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:02
 * @Description:
 */
public interface CusDataDtlCDAO {
    //根据主表标志查询细表所有信息
    List<CusDataDtlCInfo> SelectCusDataDtlCInfoByCusDataGd(String cusDataGd);

    //新增
    void InsertCusDataDtlCInfo(CusDataDtlCInfo cusDataDtlCInfo);

    //删除
    int DeleteCusDataDtlCInfo(int ruid);

    //根据CsuData关联查询
    List<CusDataDtlCInfo> selectCusData(String Guid);
}
