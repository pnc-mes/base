package pnc.mesadmin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.UserInfo;

/**
 * Created by PNC on 2017/5/10.
 */
public interface UserDAO {
    UserInfo SelectUser(String userName);

    UserInfo SelectUserRd(String guid);

    int UpdateUserPwd(@Param("guid") String guid, @Param("password") String password);

    //重置密码 张亮亮
    int UpdataUserInfoPassword(@Param("ruid") int ruid, @Param("password") String passWord);

    //根据主键 ruid 查询用户信息 张亮亮
    UserInfo SelectUserInfoByruid(int argruid);

    //查询用户列表信息 张亮亮
    List<UserInfo> SelectAllUserInfo();

    //保存用户信息 张亮亮
    int InsertUserInfo(UserInfo argUserInfo);

    //删除用户 张亮亮
    int DeleteUserInfo(int argruid);

    //更新用户 张亮亮
    int UpdateUserinfo(UserInfo arguserInfo);

    UserInfo SelectUserInfoByUserName(String argUserName);

    //查询用户列表信息 WHL
    List<UserInfo> SelectAllUserInfoByRealName(@Param("argRealName") String argRealName);

    List<UserInfo> SelectByGuids(@Param("ids") List ids);
}
