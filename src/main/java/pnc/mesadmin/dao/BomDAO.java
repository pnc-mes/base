package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.BomInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM清单信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface BomDAO extends BaseMapper<BomInfo> {

    /**
     * 新增Bom清单信息  (By-pjf)
     * @param argBomInfo
     * @return
     */
    int InsertBomInfo(BomInfo argBomInfo);

    /**
     * 删除Bom清单信息  (By-pjf)
     * @param argRuid
     * @return
     */
    int DeleteBomInfo(int argRuid);

    /**
     * 修改Bom清单信息根据（ruid）  (By-pjf)
     * @param argBomInfo
     * @return
     */
    int UpdateBomInfo(BomInfo argBomInfo);

    /**
     * 修改Bom清单信息根据（guid）  (By-pjf)
     * @param argBomInfo
     * @return
     */
    int UpdateByGuid(BomInfo argBomInfo);

    /**
     * 查询所有BOM清单信息  (By-pjf)
     * @return
     */
    List<BomInfo> SelectAllBomInfo();

    /**
     * 查询BOM清单信息  (By-pjf)
     * @param argRuid
     * @return
     */
    BomInfo SelectBomInfo(int argRuid);

    /**
     * 根据Guid查询BOM清单信息  (By-pjf)
     * @param argGuid
     * @return
     */
    BomInfo SelectByGuid(String argGuid);

    /**
     * 根据BomName查询BOM清单信息  (By-pjf)
     * @param argBomName
     * @return
     */
    BomInfo SelectByName(String argBomName);

    /**
     * 根据BomCode查询BOM清单信息  (By-pjf)
     * @param argBomCode
     * @return
     */
    BomInfo SelectByCode(String argBomCode);
}
