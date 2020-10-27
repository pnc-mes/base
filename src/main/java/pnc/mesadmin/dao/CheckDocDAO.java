package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckDocInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public interface CheckDocDAO {
    List<CheckDocInfo> SelectAll();

    CheckDocInfo SelectByRuid(Integer ruid);

    CheckDocInfo SelectByGuid(String guid);

    CheckDocInfo SelectByName(String name);

    int Insert(CheckDocInfo model);

    int UpdateByGuid(CheckDocInfo model);

    int DeleteByGuid(String guid);
}
