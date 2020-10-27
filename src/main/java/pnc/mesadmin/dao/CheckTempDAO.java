package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckTempInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-21
 **/
public interface CheckTempDAO {
    List<CheckTempInfo> SelectAll();

    CheckTempInfo SelectByRuid(Integer ruid);

    CheckTempInfo SelectByGuid(String guid);

    CheckTempInfo SelectByName(String name);

    int Insert(CheckTempInfo model);

    int UpdateByGuid(CheckTempInfo model);

    int DeleteByGuid(String guid);
}
