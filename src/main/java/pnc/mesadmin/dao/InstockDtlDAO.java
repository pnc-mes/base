package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.InstockDtlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存明细信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-12
 * 修改人：
 * 修改时间：
 */
public interface InstockDtlDAO {
    //查询库存明细信息
    List<InstockDtlInfo> SelectByinsGd(String guid);

    //查询库存明细所有信息
    List<InstockDtlInfo> SelectAllInstockDtlInfo();

    //通过批次查询库存明细信息
    InstockDtlInfo SelectBybatch(String batch);

    //查询库存明细信息
    InstockDtlInfo SelectByguid(String guid);

    //新增库存明细信息
    int InsertInstockDtlInfo(InstockDtlInfo instockDtlInfo);

    //更新库存明细信息
    int UpdateInstockDtlInfo(InstockDtlInfo instockDtlInfo);

    //删除库存明细信息
    int DeleteInstockDtlInfo(int ruid);

    List<InstockDtlInfo> SelectByMaVerGd_Isstore_StoreGd_Batch(
            @Param("maVerGd") String maVerGd,
            @Param("storeGd") String storeGd, @Param("batch") String batchStr);

    //获取推荐批次
    List<InstockDtlInfo> SelectAllRecBatch(@Param("argStoreGd") String argStoreGd, @Param("argMaVerGd") String argMaVerGd);

    /**
     * 根据物料GD、仓库GD、空批次、库位GD查询库存明细
     *
     * @param argMaVerGd
     * @param argStoreGd
     * @param argLocationGd
     * @return
     */
    InstockDtlInfo SelectByMaVerStoreBatchLocation(@Param("argMaVerGd") String argMaVerGd,
                                                   @Param("argStoreGd") String argStoreGd,
                                                   @Param("argLocationGd") String argLocationGd);

    //根据maVerGd和storeGd查询细表信息
    List<InstockDtlInfo> SelectInstockDtlInfoByMaVerGdAndStoreGd(@Param("argMaVerGd") String argMaVerGd, @Param("argStoreGd") String argStoreGd);

    InstockDtlInfo SelectByMaVerGdAndStoreGdAndBatchAndLocationGd(@Param("argMaVerGd") String argMaVerGd,
                                                                  @Param("argStoreGd") String argStoreGd,
                                                                  @Param("argBatch") String argBatch,
                                                                  @Param("argLocationGd") String argLocationGd);

    InstockDtlInfo SelectByStoreGdAndLocationgGdAndBatch(@Param("argStoreGd") String argStoreGd,
                                                         @Param("argLocationGd") String argLocationGd,
                                                         @Param("argBatch") String argBatch);


    List<InstockDtlInfo> SelectByBarTypeAndBatch(@Param("argBarType") String argBarType,
                                                 @Param("argBatch") String argBatch);

    List<InstockDtlInfo> SelectByStoreGdAndBarTypeAndBatch(@Param("argStoreGd") String argStoreGd,
                                                           @Param("argBarType") String argBarType,
                                                           @Param("argBatch") String argBatch);


    InstockDtlInfo selectInstockDtlInfoByBarCodeAndBarType01(String batch);
}
