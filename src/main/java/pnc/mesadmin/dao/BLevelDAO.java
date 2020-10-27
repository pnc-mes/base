package pnc.mesadmin.dao;

import pnc.mesadmin.entity.BLevelInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次等级信息DAO
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface BLevelDAO {
    //查询批次等级列表信息
    List<BLevelInfo> SelectAllBLevelInfo();
    //查询批次等级信息
    BLevelInfo SelectBybLRd(int bLRd);
    /*
    *查询批次等级信息
    * 王怀龙
    * */
    BLevelInfo SelectByGuid(String argGuid);
    //保存批次等级信息
    int InsertBLevelInfo(BLevelInfo bLevelInfo);
    //删除批次等级信息
    int DeleteBLevelInfo(int ruid);
    //更新批次等级信息
    int UpdateBLevelInfo(BLevelInfo bLevelInfo);
    //查询批次等级名称
    BLevelInfo SelectBybLName(String bLName);
}
