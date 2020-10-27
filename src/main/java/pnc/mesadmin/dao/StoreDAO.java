package pnc.mesadmin.dao;

import pnc.mesadmin.entity.StoreInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序信息DAO
 * 创建人：刘福志
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public interface StoreDAO {
    //查询仓库信息
    StoreInfo SelectByRuid(int StoreRd);

    //关联查询盘点表
    List<StoreInfo> SelectBystoreGd(String storeGd);

    //关联查询库位表
    StoreInfo SelectByStoreGd(String storeGd);

    //查询仓库列表信息
    List<StoreInfo> SelectAllStoreInfo();

    //保存仓库信息
    int InsertStoreInfo(StoreInfo storeInfo);

    //删除仓库信息
    int DeleteStoreInfo(int ruid);

    //更新仓库信息
    int UpdateStoreInfo(StoreInfo storeInfo);

    //查询仓库名称
    StoreInfo SelectBystoreName(String storeName);

    //关联查询库存明细表
    StoreInfo SelectByguid(String storeGd);

    //查询仓库代码
    StoreInfo SelectByStoreCode(String storeCode);

}
