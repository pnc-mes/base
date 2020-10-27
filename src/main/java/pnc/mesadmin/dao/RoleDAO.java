package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.RoleInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：角色信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-09
 * 修改人：
 * 修改时间：
 */
public interface RoleDAO {
    List<RoleInfo> SelectRoleInfoList();

    List<RoleInfo>  SelectAllRoleInfo(@Param("RoleName") String RoleName);

    int InsertRoleInfo(RoleInfo roleInfo);

    int UpdateRoleInfo(RoleInfo roleInfo);

    int DeleteRoleInfo(int ruid);

    RoleInfo selectRoleIdList(String ruid);

    RoleInfo selectRoleIdGuid(String guid);

    /**
     * 根据ruid查询角色信息 张亮亮
     * @param argruid
     * @return
     */
    RoleInfo SelectRoleInfoByruid(int argruid);

    /**
     * 根据guid查询角色信息 张亮亮
     * @param argguid
     * @return
     */
    List<RoleInfo> SelectRoleInfoByguid(String argguid);

    /**
     * 根据RoleName查询角色信息 (pjf)
     * @param argRoleName
     * @return
     */
    RoleInfo SelectByRoleName(String argRoleName);

    /**
     * 查询用户所拥有的角色 (pjf)
     * @param argUserGd
     * @return
     */
    List<RoleInfo> SelectByUserGd(String argUserGd);
}
