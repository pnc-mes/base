package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSConfigCdInfo;

import java.util.List;
import java.util.Map;


/**
 * @program: mesadmin
 * @description: 通用查询配置表格 查询 条件 信息DAO
 * @author: yin.yang
 * @create: 2018-11-19
 **/
public interface CSConfigCdDAO {
    //根据Rd查询
    CSConfigCdInfo SelectCSConfigCdInfoByRd(Integer ruid);

    //根据GD查询
    CSConfigCdInfo SelectCSConfigCdInfoByGD(String guid);

    //根据configGd查询
    List<CSConfigCdInfo> SelectAllByConfigGD(String configGuid);

    //新增
    int InsertCSConfigCdInfo(CSConfigCdInfo model);

    //更新
    int UpdateCsConfigCdInfo(CSConfigCdInfo model);

    //删除
    int DelectCsConfigCdInfoByConfigGuid(String configGuid);

    List<Map<String,String>> SelectSQL(String sql);
}
