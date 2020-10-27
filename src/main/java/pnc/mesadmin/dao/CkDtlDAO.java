package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.CkDtlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：出库扫描Dao层接口
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public interface CkDtlDAO {
    //新增扫描实体信息  --王怀龙
    int InsertCkDtlInfo(CkDtlInfo argCkDtlInfo);

    //根据出库明细的标识到扫描信息中查询信息
    List<CkDtlInfo> SelectCkDtlInfoByCkTkDtlGd(String argCkTkDtlGd);

    //根据批次号查询出库扫描明细信息
    CkDtlInfo SelectCkDtlInfoByBatch(String argBatch);

    CkDtlInfo SelectCkDtlInfoguid(String guid);

    //根据MaVerGd和StoreGd查询所有信息
    List<CkDtlInfo> SelectCkDtlInfoByMaVerGdAndStoreGd(@Param("argMaVerGd") String argMaVerGd, @Param("argStoreGd") String argStoreGd);
}
