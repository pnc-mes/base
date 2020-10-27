package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DoCheckDtlInfo;

import java.util.List;

public interface DoCheckDtInfoDAO {
    List<DoCheckDtlInfo> SelectDoCheckInfoBy(String DoCheckGd);

    DoCheckDtlInfo SelectDoCheckInfoByRuid(Integer ruid);

    int UpdaDoCheckDtlInfoByRuid(DoCheckDtlInfo model);

    int InsertDoCheckDtlInfo(DoCheckDtlInfo model);
}
