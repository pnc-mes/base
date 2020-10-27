package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CSTableInfo;

import java.util.List;
import java.util.Map;

public interface CSTableDAO {
    List<CSTableInfo> SelectAllInfo();

    //根据表格设置标识查询信息
    CSTableInfo SelectCSTableInfo(String Guid);

    //新增
    int InsertCSTableInfo(CSTableInfo csTableInfo);

    List<Map<String, String>> SelectSQL(String tableSql);

    Map<String, Integer> SelectSQL1(String tableSql);
}
