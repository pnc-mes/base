package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckDocDtlInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public interface CheckDocDtlDAO {
    List<CheckDocDtlInfo> SelectAllByDocGuid(String docGuid);

    CheckDocDtlInfo SelectByRuid(Integer ruid);

    CheckDocDtlInfo SelectByGuid(String guid);

    int Insert(CheckDocDtlInfo model);

    int DeleteByDocGuid(String docGuid);
}
