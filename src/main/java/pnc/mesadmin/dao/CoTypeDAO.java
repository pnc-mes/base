package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CoTypeInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:31
 * @Description:
 */
public interface CoTypeDAO {

    //查询列表
    List<CoTypeInfo> SelectAllCoTypeInfo();

    //增
    void InsertCoTypeInfo(CoTypeInfo coTypeInfo);

    //删除
    int DeleteCoTypeInfoByRuid(int ruid);

    //更新
    int UpdateCoTypeInfo(CoTypeInfo coTypeInfo);

    //查询 根据ruid
    CoTypeInfo SelectCoTypeInfoByRuid(int ruid);

    //查询 根据guid
    CoTypeInfo SelectCoTypeInfoByGuid(String guid);

    //查询 根据coTName
    CoTypeInfo SelectCoTypeInfoByCoTName(String coTName);
}
