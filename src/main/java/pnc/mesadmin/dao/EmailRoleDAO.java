package pnc.mesadmin.dao;

import pnc.mesadmin.entity.EmailRoleInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:05
 * @Description:
 */
public interface EmailRoleDAO {
    //根据分发主表的guid，查询分发角色全部信息
    List<EmailRoleInfo> SelectEmailRoleInfoByDistributionGd(String distributionGd);

    //删除分发角色
    int DeleteEmailRoleInfo(int ruid);

    //新增分发角色
    void InsertEmailRoleInfo(EmailRoleInfo emailRoleInfo);
}
