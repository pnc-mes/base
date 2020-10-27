package pnc.mesadmin.dao;

import pnc.mesadmin.entity.EmailAddressInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:06
 * @Description:
 */
public interface EmailAddressDAO {

    //根据分发guid 查询所有的分发地址信息
    List<EmailAddressInfo> SelectEmailAddressInfoByDistributionGd(String distributionGd);

    //删除
    int DeleteEmailEmailAddressInfo(int ruid);

    //新增
    void InsertEmailAddressInfo(EmailAddressInfo emailAddressInfo);
}
