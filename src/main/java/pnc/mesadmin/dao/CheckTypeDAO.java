package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckTypeInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public interface CheckTypeDAO {
    List<CheckTypeInfo> SelectAll();

    CheckTypeInfo SelectByRuid(Integer ruid);

    CheckTypeInfo SelectByGuid(String guid);

    CheckTypeInfo SelectByCode(String code);

    int Insert(CheckTypeInfo checkTypeInfo);

    int UpdateByGuid(CheckTypeInfo checkTypeInfo);

    int DeleteByGuid(String guid);
}
