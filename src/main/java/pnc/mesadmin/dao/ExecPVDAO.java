package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ExecPVInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 车间现场执行系统权限信息
 * @author: yin.yang
 * @create: 2018-11-08
 **/
public interface ExecPVDAO {
    List<ExecPVInfo> SelectAllExecPVInfo();
    //根据角色标识查询
    List<ExecPVInfo> SelectRoleGd(String RoleGd);

    int InsertExecpvinfo(ExecPVInfo model);

    int DeleteByRoleGd(String argRoleGd);

    //根据RoleGd、OpertMFlag查询车间权限信息
    List<ExecPVInfo> SelectByOpertMFlag(@Param("argRoleGd") String argRoleGd, @Param("argOpertMFlag") String argOpertMFlag);
}
