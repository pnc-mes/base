package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.InstockInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-12
 * 修改人：
 * 修改时间：
 */
public interface InstockDAO {
    //插入库存信息
    int InsertInStock(InstockInfo argInstockInfo);

    //根据库存ID删除库存信息  王怀龙
    int DeleteInStockInfoByRuid(int argInStockInfoRuid);

    //更新库存信息
    int UpdateInStockInfo(InstockInfo argInstockInfo);

    //根据物料版本标识和仓库标识更新库存信息
    int UpdateInStockInfoByMaVerGdAndStoreGd(InstockInfo argInstockInfo);

    //查询根据物料版本gd
    InstockInfo SelectBymaVerGd(String maVerGd);

    //查询库存信息
    InstockInfo SelectByInsRd(int insRd);

    InstockInfo SelectBymaterialCode(@Param("materialCode") String materialCode, @Param("storeGd") String storeGd);

    //查询库存gd
    InstockInfo SelectByinsGd(String insGd);

    //查询库存物料类型
    List<InstockInfo> SelectmaterialType(String materialType);

    //查询库存所有数据
    List<InstockInfo> SelectAllByinstockinfo();

    //根据物料的Guid查询库存信息
    InstockInfo SelectInstockInfoListByMaVerGdAndStoreGd(@Param("argMaVerGd") String argMaVerGd, @Param("argStoreGd") String argStoreGd);

    //更新库存数量
    int UpdateInStockInfoNum(InstockInfo argInstockInfo);


    //筛选关联查询
    List<InstockInfo> SelectBystoreGdShaixuan(@Param("StoreGd") String StoreGd, @Param("macode") String macode, @Param("maname") String maname);

    //筛选查询
    List<InstockInfo>  SelectBystoreGdShaixuanMaterial_Storeinfo_Maverinfo(
            @Param("StoreRd") int StoreRd, @Param("MaterialDes") String MaterialDes,
            @Param("Brand") String Brand, @Param("OrderNum") String OrderNum,
            @Param("macode") String macode, @Param("maname") String maname,
            @Param("Size") String Size, @Param("Material") String Material,
            @Param("Norm") String Norm, @Param("Model") String Model
    );
    //筛选查询
    List<InstockInfo>  SelectBystoreGdShaixuanMaterial_Storeinfo_Maverinfo1(
            @Param("StoreRd") int StoreRd, @Param("MaterialDes") String MaterialDes,
            @Param("Brand") String Brand, @Param("OrderNum") String OrderNum,
            @Param("macode") String macode, @Param("maname") String maname,
            @Param("Size") String Size, @Param("Material") String Material,
            @Param("Norm") String Norm, @Param("Model") String Model
    );
    //根据仓库信息查询所有的料
    List<InstockInfo> SelectBystoreGd(String StoreGd);

    //OR查询
    List<InstockInfo> SelectBystoreGdShaixuan00(@Param("StoreGd") String StoreGd, @Param("macode") String macode, @Param("maname") String maname);

    List<InstockInfo> SelectInstockInfoByStoreInfoAndMaInfoAndPage(
            @Param("StoreGd") String StoreGd,
            @Param("MaInfo") String MaInfo,
            @Param("StartIndex") int StartIndex,
            @Param("PageSize") int PageSize);
}
