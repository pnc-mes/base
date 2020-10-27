package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.BomReMaterialInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM清单信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-06
 * 修改人：
 * 修改时间：
 */
public interface BomReMaterialDAO extends BaseMapper<BomReMaterialInfo> {

    //新增BOM替代料信息  (By-pjf)
    int InsertBomReMaterialInfo(BomReMaterialInfo argBomReMaterialInfo);

    //删除BOM替代料信息  (By-pjf)
    int DeleteByMaVerGd(String argMaVerGd);

    //查询所有BOM替代料信息  (By-pjf)
    List<BomReMaterialInfo> SelectAllBomReMaterialInfo();

    //根据Bom用料标识查询所有BOM替代料信息  (By-pjf)
    List<BomReMaterialInfo> SelectByBomMaGd(String argBomMaGd);

    //根据物料版本标识查询所有BOM替代料信息  (By-pjf)
    List<BomReMaterialInfo> SelectByMaVerGd(String argMaVerGd);

    //根据guid查询物料消耗信息
    BomReMaterialInfo SelectBomReMaterialInfoByGuid(String argGuid);

    //根据ruid查询物料消耗信息
    BomReMaterialInfo SelectBomReMaterialInfoByRuid(int argRuid);

    //根据WoGd,SpecVGd,查询【5.2.1. 工单信息表(tpm_WoInfo)】、【5.7.4. 替代料信息表(tpm_BomReMaterialInfo)】关联信息
    List<BomReMaterialInfo> SelectBomReMaterialInfo(@Param("WoGd") String WoGd, @Param("SpecVGd") String SpecVGd);

    List<BomReMaterialInfo> SelectBomVerGd(String BomVerGd);

    //根据物料版本标识查询所有BOM替代料信息  (By-pjf)
    List<BomReMaterialInfo> SelectByMaVerGdAndBomVerGd(@Param("BomVerGd") String BomVerGd, @Param("MaVerGd") String MaVerGd, @Param("specVerGd") String specVerGd);

}
