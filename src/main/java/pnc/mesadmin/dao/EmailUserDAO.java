package pnc.mesadmin.dao;

import pnc.mesadmin.entity.EmailUserInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:05
 * @Description:
 */
public interface EmailUserDAO {
    //根据分发主表guid 查询所有用户信息
    List<EmailUserInfo> SelectEmailUserInfoByDistributionGd(String distributionGd);
    //新增分发用户
    void InsertEmailUserInfo(EmailUserInfo emailUserInfo);
    //删除分发用户
    int DeleteEmailUserInfo(int ruid);
}
