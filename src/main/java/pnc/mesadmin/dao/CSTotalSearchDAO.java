package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CSTotalSearchInfo;

import java.util.List;

public interface CSTotalSearchDAO {
    List<CSTotalSearchInfo> SelectAllInfo();

    //根据统计标识统计设置查询条件配置
    List<CSTotalSearchInfo> SelectCSTotalSearchInfo(String CSTotalGd);

    //新增
    int InsertCSTotalSearchInfo(CSTotalSearchInfo model);
}
