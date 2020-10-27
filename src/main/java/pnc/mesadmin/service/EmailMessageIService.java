package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllEmailInfo.GetAllEmailInfoRes;
import pnc.mesadmin.dto.GetEmailInfo.GetEmailInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveEmailInfo.SaveEmailInfoReqBD00;
import pnc.mesadmin.dto.SaveEmailInfo.SaveEmailInfoReqBD02;
import pnc.mesadmin.dto.SaveEmailInfo.SaveEmailInfoReqBD03;
import pnc.mesadmin.dto.SaveEmailInfo.SaveEmailInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.EmailMessageInfo;

import java.util.List;
/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：通知管理Service接口
 * 创建人：乔帅阳
 * 创建时间：2018-7-5
 * 修改人：
 * 修改时间：
 */
public interface EmailMessageIService  {
    //获取邮件内容列表信息
    GetAllEmailInfoRes QALLselectAllAllEmailInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;
    // 获取邮件内容信息
    GetEmailInfoRes GetselectEmailInfoId(int EmailRd) throws SystemException;
    //保存邮件内容信息
    SaveEmailInfoRes AddinsertEmailMessageInfo(SaveEmailInfoReqBD00 busData00, EmailMessageInfo emailMessageInfo) throws SystemException;
    //删除邮件内容信息
    SaveEmailInfoRes RmdeleteEmailMessageInfo(int Ruid) throws SystemException;
    //更新邮件内容信息
    SaveEmailInfoRes ModupdateEmailMessageInfo(SaveEmailInfoReqBD02 busData02, EmailMessageInfo emailMessageInfo) throws SystemException;
    //复制邮件内容信息
    SaveEmailInfoRes copyEmailMessageInfo(SaveEmailInfoReqBD03 busData03, EmailMessageInfo emailMessageInfo) throws SystemException;
}
