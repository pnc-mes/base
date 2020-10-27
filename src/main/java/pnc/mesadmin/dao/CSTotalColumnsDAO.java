package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CSTotalColumnsInfo;

import java.util.List;

public interface CSTotalColumnsDAO {
    List<CSTotalColumnsInfo> SelectAllInfo();

    List<CSTotalColumnsInfo> SelectAllByCSTotalGd(String CSTotalGd);

    int InsertCSTotalColumnsInfo(CSTotalColumnsInfo model);
}
