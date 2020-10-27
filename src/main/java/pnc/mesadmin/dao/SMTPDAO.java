package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SMTPInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：邮箱服务器管理Dao
 * 创建人：乔帅阳
 * 创建时间：2018-7-3
 * 修改人：
 * 修改时间：
 */
public interface SMTPDAO {
    //获取邮件服务器列表信息
    List<SMTPInfo> SelectAllSMTPInfo();

    //获取邮箱服务器信息  熊伟
    SMTPInfo SelectSMTP();


    //获取邮件服务器信息
    SMTPInfo SelectSMTPByID(int SMTPRd);
    //保存邮件服务器信息
    int InsertSMTPInfo(SMTPInfo sMTPInfo);
    //删除邮件服务器信息
    int DeleteSMTPInfo(int Ruid);
    //更新邮件服务器信息
    int UpdateSMTPInfo(SMTPInfo shiftInfo);
    //根据Guid查询
    SMTPInfo SelectByGuid(String Guid);
    //根据名称查询 zll
    SMTPInfo SelectAllSMTPInfoBySMTPName();
}
