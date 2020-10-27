package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSConfigColumnInfo;

import java.util.List;


/**
 * @program: mesadmin
 * @description: 通用查询 配置表格 信息DAO
 * @author: yin.yang
 * @create: 2018-11-19
 **/
public interface CSConfigColumnDAO {
    //根据Rd查询
    CSConfigColumnInfo SelectCSConfigColumnInfoByRd(Integer ruid);

    //根据GD查询
    CSConfigColumnInfo SelectCSConfigColumnInfoByGD(String guid);

    //根据configGd查询
    List<CSConfigColumnInfo> SelectAllByConfigGD(String configGuid);

    //新增
    int InsertCSConfigColumnInfo(CSConfigColumnInfo model);

    //更新
    int UpdateCsConfigColumnInfo(CSConfigColumnInfo model);

    //删除
    int DeleteCsConfigColumnInfoByConfigGuid(String configGuid);
}
