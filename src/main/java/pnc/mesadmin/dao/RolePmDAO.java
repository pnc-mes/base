package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.RolePmInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：角色权限信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-10
 * 修改人：
 * 修改时间：
 */
public interface RolePmDAO {
    List<RolePmInfo> SelectRolePmInfo(String roleGd);

    /**
     * 查询该用户下的所有模块 (By-pjf)
     * @param roleGd
     * @return
     */
    List<RolePmInfo> SelectAllRolePmInfoModule(String roleGd);

    /**
     * 查询该用户该模块下的所有资源 (By-pjf)
     * @param objERoleGd
     * @param objEModuleGd
     * @param objEResOptGd
     * @return
     */
    List<RolePmInfo> SelectAllRolePmInfoRes(@Param("objERoleGd") String objERoleGd,
                                            @Param("objEModuleGd") String objEModuleGd,
                                            @Param("objEResOptGd") String objEResOptGd);

    RolePmInfo SelectOptGd(@Param("resOptGd") String resOptGd, @Param("roleGd") String roleGd);

    int InsertRolePmInfo(RolePmInfo rolePmInfo);

    int DeleteRolePmInfo(String roleGd);

    int DeleteByGuid(String guid);

    RolePmInfo SelectByRuid(int ruid);

    /**
     * 删除模块下用户权限信息 (pjf)
     * @param argModuleGd
     * @return
     */
    int DeleteRolePmInfoByModuleGd(String argModuleGd);

    /**
     * 删除角色操作信息 (pjf)
     * @param argResOptGd
     * @return
     */
    int DeleteRPByResGdResOptGd(@Param("argResOptGd") String argResOptGd);

    /**
     * 根据资源标识删除角色操作信息 (pjf)
     * @param argResGd
     * @return
     */
    int DeleteRPByResGd(String argResGd);
}
