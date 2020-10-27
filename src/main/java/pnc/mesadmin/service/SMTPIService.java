package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllSMTPInfo.GetAllSMTPInfoRes;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoReqBD00;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoReqBD02;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoReqBD03;
import pnc.mesadmin.dto.SaveSMTPInfo.SaveSMTPInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SMTPInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：通知管理Service接口
 * 创建人：乔帅阳
 * 创建时间：2018-7-3
 * 修改人：
 * 修改时间：
 */
public interface SMTPIService {
    //获取邮件服务器列表信息
    GetAllSMTPInfoRes QALLselectAllSMTPInfoInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;
    //获取邮件服务器信息
    /*GetSMTPInfoRes GetselectSMTPInfoId(int SMTPRd) throws SystemException;*/
    GetSMTPInfoRes GetselectSMTPInfo() throws SystemException;
    //保存邮件服务器信息
    SaveSMTPInfoRes AddinsertSMTPInfo(SaveSMTPInfoReqBD00 busData00, SMTPInfo sMTPInfo) throws SystemException;
    //删除邮件服务器信息
    SaveSMTPInfoRes RmdeleteSMTPInfo(int Ruid) throws SystemException;
    //更新邮件服务器信息
    SaveSMTPInfoRes ModupdateCustomerInfo(SaveSMTPInfoReqBD02 busData02, SMTPInfo SMTPInfo) throws SystemException;
    //复制邮件服务器信息
    SaveSMTPInfoRes copySMTPInfo(SaveSMTPInfoReqBD03 busData03, SMTPInfo sMTPInfo) throws SystemException;
}
