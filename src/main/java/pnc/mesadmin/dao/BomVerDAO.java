package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.BomVerInfo;

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
public interface BomVerDAO {

    /**
     * 新增BOM清单版本信息  (By-pjf)
     * @param argBomVerInfo
     * @return
     */
    int InsertBomVerInfo(BomVerInfo argBomVerInfo);

    /**
     * 删除BOM清单版本信息  (By-pjf)
     * @param argRuid
     * @return
     */
    int DeleteBomVerInfo(int argRuid);

    /**
     * 根据BomGd删除BOM清单版本信息  (By-pjf)
     * @param argBomGd
     * @return
     */
    int DeleteByBomGd(String argBomGd);

    /**
     * 修改BOM清单版本信息  (By-pjf)
     * @param argBomVerInfo
     * @return
     */
    int UpdateBomVerInfo(BomVerInfo argBomVerInfo);

    /**
     * 根据BOM清单标识修改BOM是否为默认版本信息 (By-pjf)
     * @param argBomVerInfo
     * @return
     */
    int UpdateByBomGd(BomVerInfo argBomVerInfo);

    /**
     * 查询所有BOM清单版本信息  (By-pjf)
     * @return
     */
    List<BomVerInfo> SelectAllBomVerInfo();

    /**
     * 查询BOM清单版本信息  (By-pjf)
     * @param argGuid
     * @return
     */
    BomVerInfo SelectBomVerInfo(String argGuid);

    /**
     * 根据Ruid查询BOM清单版本信息  (By-pjf)
     * @param argRuid
     * @return
     */
    BomVerInfo SelectByRuid(int argRuid);

    /**
     * 根据BomGd查询BOM清单版本信息  (By-pjf)
     * @param argBomGd
     * @return
     */
    List<BomVerInfo> SelectByBomGd(String argBomGd);

    /**
     * 根据BomGd和Version查询BOM清单版本信息  (By-pjf)
     * @param argBomGd
     * @param argVersion
     * @return
     */
    List<BomVerInfo> SelectByBomGdVersion(@Param("argBomGd") String argBomGd, @Param("argVersion") String argVersion);

    /**
     * 根据guid查询无聊版本信息
     * @param argGuid
     * @return
     */
    List<BomVerInfo> SelectBomVerInfoByGuid(String argGuid);
}
