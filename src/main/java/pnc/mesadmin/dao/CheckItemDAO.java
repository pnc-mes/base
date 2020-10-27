package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckItemInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 检验项信息DAO
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public interface CheckItemDAO {
    List<CheckItemInfo> SelectAll();

    CheckItemInfo SelectByRuid(Integer ruid);

    CheckItemInfo SelectByGuid(String guid);

    List<CheckItemInfo> SelectByCode(String checkItemCode);

    int Insert(CheckItemInfo checkItemInfo);

    int UpdateByGuid(CheckItemInfo checkItemInfo);

    int DeleteByGuid(String guid);
}
