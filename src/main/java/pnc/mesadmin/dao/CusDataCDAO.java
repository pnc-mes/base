package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CusDataCInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:01
 * @Description:
 */
public interface CusDataCDAO {
    //查询列表
    List<CusDataCInfo> SelectAllCusDataCInfo();

    //查询单个
    CusDataCInfo SelectCusDataCInfo(int ruid);

    //根据名字查询单个
    CusDataCInfo SelectCusDataCInfoByCusDataName(String cusDataName);

    //根据guid查询单个
    CusDataCInfo SelectCusDataCInfoByGuid(String guid);

    //新增
    void InsertCusDataCInfo(CusDataCInfo cusDataCInfo);

    //删除
    int DeleteCusDataCInfo(int ruid);

    //修改
    int UpdateCusDataCInfo(CusDataCInfo cusDataCInfo);


}
