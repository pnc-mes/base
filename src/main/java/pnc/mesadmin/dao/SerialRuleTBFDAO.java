package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SerialRuleTBFInfo;

import java.util.List;

/**
 * Created by 郝赞 on 2018/8/27.
 */
public interface SerialRuleTBFDAO {
    //根据查询数据
    List<SerialRuleTBFInfo> selectS(String guid);
    //插入数据
    int InsertTBF(SerialRuleTBFInfo serialRuleTBFInfo);

    //删除数据
    int del(String guid);
}
