package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CSTotalInfo;

import java.util.List;

public interface CSTotalDAO {
    List<CSTotalInfo> SelectAllInfo();

    //查询统计设置
    CSTotalInfo SelectCSTotalInfo(String Guid);

    //新增
    int InsertCSTotalInfo(CSTotalInfo model);
}
