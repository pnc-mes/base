package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.entity.MaterialInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料版本信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface MaVerDAO extends BaseMapper<MaVerInfo> {

    //新增物料版本信息  (By-pjf)
    int InsertMaverInfo(MaVerInfo argMaVerInfo);

    //根据MaGd删除物料版本信息  (By-pjf)
    int DeleteByMaGd(String argMaGd);

    //根据Guid删除物料版本信息  (By-pjf)
    int DeleteByGuid(String argGuid);

    //修改物料版本信息  (By-pjf)
    int UpdateMaVerInfo(MaVerInfo argMaVerInfo);

    //根据Guid获取物料版本信息  (By-pjf)
    MaVerInfo SelectMaverInfo(String argGuid);

    MaterialInfo SelectMaverAndMater(String MaGd);

    //根据Ruid获取物料版本信息  (By-pjf)
    MaVerInfo SelectByRuid(int argRuid);

    //根据MaGd获取物料版本信息  (By-pjf)
    List<MaVerInfo> SelectByMaGd(String argMaGd);

    //根据MaGd与Version获取物料版本信息  (By-pjf)
    MaVerInfo SelectByMaGdVersion(@Param("argMaGd") String argMaGd, @Param("argVersion") String argVersion);

    //根据PdFamilyGd获取物料版本信息  (By-pjf)
    List<MaVerInfo> SelectByPdFamilyGd(String argPdGd);

    /**
     * 通过版本guid集合查询对应的版本集合信息
     * @param maVerGdList
     * @return
     */
    List<MaVerInfo> SelectMaVerListByGuidList(List<String> maVerGdList);

    /**
     * 通过版本ruid集合查询对应的版本集合信息
     * @param maVerRdList
     * @return
     */
    List<MaVerInfo> SelectMaVerListByRuidList(List<Integer> maVerRdList);

    MaVerInfo SelectByCompanyCodeMaCodeDSource(@Param("argCompanyCode") String argCompanyCode,
                                               @Param("argMaCode") String argMaCode,
                                               @Param("argDSource") String argDSource);

    MaVerInfo SelectMaterialInfoBymaBarCode(String argmaBarCode);

    List<MaVerInfo> SelectMaVerInfoByInfosAndPageInfo(@Param("argMaInfo") String argMaInfo,
                                                      @Param("argOffSize") int argOffSize,
                                                      @Param("argPageSize") int argPageSize);

    //查询所有的物料信息
    List<MaVerInfo> SelectAllMaVerInfo();

    List<MaVerInfo> SelectByMaCode(String argMaCode);
}