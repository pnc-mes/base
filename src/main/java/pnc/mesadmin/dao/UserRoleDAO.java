package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.UserInfo;
import pnc.mesadmin.entity.UserRoleInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户角色信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-10
 * 修改人：
 * 修改时间：
 */
public interface UserRoleDAO {
    //根据用户标识(UserGd)查询用户角色信息，张亮亮
    List<UserRoleInfo> SelectUserRoleInfoByuserGd(@Param("userGd") String arguserGd);

    //根据用户id和角色id 到用户角色查询信息,此目的是否被勾选，张亮亮
    UserRoleInfo SelectUseridAndRolridInfo(@Param("userGd") String userGd, @Param("roleGd") String roleGd);

    //新增用户角色信息 ，张亮亮
    int InsertUserRoleInfo(UserRoleInfo argUserRoleInfo);

    //删除用户角色信息 此语句是删除用户下的关联表也要删除掉 ，张亮亮
    int DeleteUserRoleByruid(@Param("ruid") int argruid, @Param("userGd") String arguserGd);


    List<UserRoleInfo> selectUserRoleruid(int ruid);

    //根据角色查询用户Gd  (pjf)
    List<String> SelectByRoleGd(String argRoleGd);

    int updateUserRoleInfo(UserRoleInfo userRoleInfo);

    List<UserInfo> SelectByGuids(@Param("ids") List ids);
}
