package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SynchCInfo;

import java.util.List;

/**
 * Created by liufuzhi on 2018/3/20.
 */
public interface SynchCDAO {

    //查询所有同步名称信息
    List<SynchCInfo> SelectAllSynchCInfo();

    //查询同步名称信息
    SynchCInfo SelectBysynchName(String synchName);

    //新增同步名称
    int InsertSynchcinfo(SynchCInfo synchCInfo);

    //更新同步信息
    int UpdateSynchcinfo(SynchCInfo synchCInfo);
}
