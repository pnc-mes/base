package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.BadDInfo;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：导电锌板信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-09-11
 * 修改人：
 * 修改时间：
 */
public interface BadDtDAO {
    List<BadDInfo> SelectByBadDInfoByBadGd(String badGd);

    List<BadDInfo> SelectByBadDInfoByBadCode(@Param("BadCode") String BadCode);

    int InsertBadDInfo(BadDInfo model);

    int DelBadDInfoByRuid(String badGd);

    int UpdateBadDInfoByRuid(BadDInfo model);

    BadDInfo SelectByBadDInfoByBadCodeone(String BadCode);

}
