package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CSTableSearchInfo;

import java.util.List;

public interface CSTableSearchDAO {
    List<CSTableSearchInfo> SelectAllInfo();

    //根据表格设置标识 查询条件配置
    List<CSTableSearchInfo> SelectCSTableSearchInfo(String CSTableGd);

    //新增
    int InsertCSTableSearchInfo(CSTableSearchInfo model);
}
