package pnc.mesadmin.dao;

import pnc.mesadmin.entity.EmailMessageInfo;

import java.util.List;

/**
 * Created by PNC on 2018/7/4.
 */
public interface EmailMessageDAO {
    //获取邮件内容列表信息
    List<EmailMessageInfo> SelectAllEmailMessageInfo();

    //获取邮件内容信息
    EmailMessageInfo SelectEmailMessageByID(int EmailRd);
    //保存邮件内容信息
    int InsertEmailMessageInfo(EmailMessageInfo emailMessageInfo);
    //删除邮件内容信息
    int DeleteEmailMessageInfo(int Ruid);
    //更新邮件服务器信息
    int UpdateEmailMessageInfo(EmailMessageInfo emailMessageInfo);
    //根据Guid查询
    EmailMessageInfo SelectEmailMessageInfoByGuid(String Guid);

    //SelectAllEmailMessageInfoByEmailName
    EmailMessageInfo SelectAllEmailMessageInfoByEmailName(String EmailName);
}
