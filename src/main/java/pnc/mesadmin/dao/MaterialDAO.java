package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.entity.MaterialInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface MaterialDAO extends BaseMapper<MaterialInfo> {

    //新增物料信息  (By-pjf)
    int InsertMaterialInfo(MaterialInfo argMaterialInfo);

    //删除物料信息  (By-pjf)
    int DeleteMaterialInfo(int argRuid);

    //修改物料信息  (By-pjf)
    int UpdateMaterialInfo(MaterialInfo argMaterialInfo);

    //获取所有物料信息  (By-pjf)
    List<MaterialInfo> SelectAllMaterialInfo();

    //查询物料信息guid，materialDes
    List<MaterialInfo>  SelectAllMaterialInfoguidmaterialDes();

    //根据物料分类获取物料信息  (By-pjf)
    List<MaterialInfo> SelectByMaType(String argMaType);

    //根据Ruid获取物料信息  (By-pjf)
    MaterialInfo SelectMaterialInfo(int argRuid);

    //根据Guid获取物料信息  (By-pjf)
    MaterialInfo SelectByGuid(String argGuid);

    //根据产品家族查询物料信息
    List<MaterialInfo> SelectByPdFamilyGd(String argPdGd);

    //根据MaName获取物料信息  (By-pjf)
    MaterialInfo SelectByMaName(String argMaterialName);

    //根据MaCode获取物料信息  (By-pjf)
    MaterialInfo SelectByMaCode(String argMaterialCode);

    //根据Gd集合获取产品信息
    List<MaterialInfo> SelectAllMaterialInfoByGDList(List<String> maVerGdList);
}
