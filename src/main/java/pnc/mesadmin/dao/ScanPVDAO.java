package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ScanPVInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：Scanner权限信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-08-21
 * 修改人：
 * 修改时间：
 */
public interface ScanPVDAO {

    //新增Scanner权限信息  (pjf)
    int InsertScanPV(ScanPVInfo argScanPVInfo);

    //根据RoleGd删除Scanner权限信息  (pjf)
    int DeleteByRoleGd(String argRoleGd);

    //根据Guid删除Scanner权限信息  (pjf)
    int DeleteScanPV(String argGuid);

    //根据RoleGd、OpertMFlag查询Scanner权限信息  (pjf)
    List<ScanPVInfo> SelectByOpertMFlag(@Param("argRoleGd") String argRoleGd, @Param("argOpertMFlag") String argOpertMFlag);

    /*根据用户标识查询Scanner权限细信息  王怀龙*/
    List<ScanPVInfo> SelectScanPVInfoByRoleGd(@Param("argRoleGd") String argRoleGd);

    //查询所有Scanner权限信息  (pjf)
    List<ScanPVInfo> SelectAllScanPV();
}
