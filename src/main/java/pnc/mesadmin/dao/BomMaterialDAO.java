package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.BomMaterialInfo;

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
public interface BomMaterialDAO extends BaseMapper<BomMaterialInfo> {

    //新增BOM用料信息  (By-pjf)
    int InsertBomMaterialInfo(BomMaterialInfo argBomMaterialInfo);

    //删除BOM用料信息  (By-pjf)
    int DeleteBomMaterialInfo(int argRuid);

    //根据BomVerGd删除BOM用料信息  (By-pjf)
    int DeleteByBomVerGd(String argBomVerGd);

    //修改BOM用料信息  (By-pjf)
    int UpdateByRuid(BomMaterialInfo argBomMaterialInfo);

    //查询所有BOM用料信息  (By-pjf)
    List<BomMaterialInfo> SelectAllBomMaterialInfo();

    //根据BomVerGd查询Bom用料信息  (By-pjf)
    List<BomMaterialInfo> SelectByBomVerGd(String argBomVerGd);

    List<BomMaterialInfo> selectBomMaterialInfoBymaVerGd(String maVerGd);

    //根据BomVerGd+SpecVerGd查询Bom用料信息  (By-pjf)
    List<BomMaterialInfo> SelectByBSGd(@Param("argBomVerGd") String argBomVerGd, @Param("argSpecVerGd") String argSpecVerGd);

    //查询Bom用料信息  (By-pjf)
    BomMaterialInfo SelectBomMaterialInfo(int argRuid);

    //同一个物料信息统计数量 张亮亮
    //int SelectBomMaterialInfoBymaterialCode(@Param("materialCode") String materialCode, @Param("bomVerGd") String bomVerGd);

    //根据Guid查询Bom用料信息
    BomMaterialInfo SelectBomMaterialInfoByGuid(String argGuid);

    //根据WoGd,SpecVGd,查询【5.2.1. 工单信息表(tpm_WoInfo)】、【5.7.3. BOM用料表(tpm_BomMaterialInfo)】关联信息
    List<BomMaterialInfo> SelectBomMaterialInfo1(@Param("WoGd") String WoGd, @Param("SpecVGd") String SpecVGd);

    //根据BomVerGd和物料Gd查询Bom用料信息
    List<BomMaterialInfo> SelectByBomVerGdAndMaVerGd(@Param("argBomVerGd") String argBomVerGd, @Param("MaVerGd") String MaVerGd, @Param("specVerGd") String specVerGd);
}
