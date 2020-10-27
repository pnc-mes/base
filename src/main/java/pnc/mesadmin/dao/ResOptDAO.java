package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ResOptInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：资源操作信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-10
 * 修改人：
 * 修改时间：
 */
public interface ResOptDAO {
    List<ResOptInfo> SelectResOptInfo(String resGd);

    List<ResOptInfo> SelectAllResOptInfo();

    ResOptInfo SelectResOptInfoRuid(int ruid);

    /**
     * 新增资源操作信息 pjf
     * @param argResOptInfo
     * @return
     */
    int InsertResOpt(ResOptInfo argResOptInfo);

    /**
     * 删除资源下所有操作 pjf
     * @param argResGd
     * @return
     */
    int DeleteResOptByResGd(String argResGd);

    /**
     * 删除资源操作 pjf
     * @param argGuid
     * @return
     */
    int DeleteResOptByGuid(@Param("argGuid") String argGuid);
}
