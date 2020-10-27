package pnc.mesadmin.dao;

import pnc.mesadmin.entity.EmailDistributionInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:03
 * @Description:
 */
public interface EmailDistributionDAO {
    //查询邮件分发列表
    List<EmailDistributionInfo> SelectEmailDistributionInfo();

    EmailDistributionInfo SelectEmailDistributionInfoByGuid(String guid);

    //查询单个信息
    EmailDistributionInfo SelectEmailDistributionInfoByRuid(int ruid);

    //修改
    int UpdateEmailDistributionInfo(EmailDistributionInfo emailDistributionInfo);

    //新增
    void InsertEmailDistributionInfo(EmailDistributionInfo emailDistributionInfo);

    //删除
    int DeleteEmailDistributionInfo(int ruid);

    //根据name查询一条信息
    EmailDistributionInfo SelectEmailDistributionInfoBydistributionName(String distributionName);



}
