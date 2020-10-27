package pnc.mesadmin.service;

import pnc.mesadmin.dto.ChangePwdInfo.ChangePwdInfoRes;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoReqBD00;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoRes;
import pnc.mesadmin.dto.GetAllUserInfo.GetAllUserInfoRes;
import pnc.mesadmin.dto.GetUserInfo.GetUserInfoReqBD00;
import pnc.mesadmin.dto.GetUserInfo.GetUserInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveUserInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResBD;
import pnc.mesadmin.entity.UserInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户列表信息service层
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface UserIService {

    //dto获取用户列表信息 张亮亮
    GetAllUserInfoRes QALLGetAllUserInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);
    //dto 根据userid查询数据 张亮亮
    GetUserInfoRes GetGetUserInfoRes(GetUserInfoReqBD00 argGetUserInfoReqBD00);
    //dto新增用户 张亮亮
    SaveUserInfoRes AddSaveUserInfoRes(SaveUserInfoReqBD00 argSaveUserInfoReqBD00);
    //dto删除 张亮亮
    SaveUserInfoRes RmUserInfo(SaveUserInfoReqBD01 argSaveUserInfoReqBD01);
    //dto编辑 张亮亮
    SaveUserInfoRes ModSaveUserInfoRes(SaveUserInfoReqBD02 argSaveUserInfoReqBD02);
    //dto 重置密码 张亮亮
    SaveUserInfoRes ModSaveUserInfoRes(SaveUserInfoReqBD03 argSaveUserInfoReqBD03);
    //dto 复制 张亮亮
    SaveUserInfoRes AddSaveUserInfoReqBD04(SaveUserInfoReqBD04 argSaveUserInfoReqBD04);

    //查询用户角色信息 刘福志
    GetAllURInfoRes QALLGetAllURInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo, GetAllURInfoReqBD00 argBD00);

    //潘俊峰
    ChangePwdInfoRes updateUserPwd(int ruid, String oldPassword, String newPassword) throws SystemException;

    //登录
    UserLoginInfoResBD QALLUserLogin(UserInfo userInfo) throws SystemException;


   /* //根据主键 ruid 查询用户信息
    UserInfo selectUserInfo(String ruid);

    UserInfo selectUserInfoList(int ruid);


    int saveUserInfo(UserInfo userInfo);

    String selectUserName(String guid);



    int deleteGuid(List<String> guid);

    UserInfo selectAll(String guid);

    int updateUserinfo(UserInfo userInfo);


    UserInfo getUser(String userName);





    //查询用户列表信息 张亮亮
    List<UserInfo> SelectAllUserInfo();
    //dto 获取用户信息 张亮亮
    GetAllUserInfoRes  getAllUserInfoRes();

    //更新密码 张亮亮
    int updataUserInfoPassword(int ruid,String passWord);*/

}
