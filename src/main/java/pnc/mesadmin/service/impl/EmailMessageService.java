package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.EmailMessageDAO;
import pnc.mesadmin.dao.SMTPDAO;
import pnc.mesadmin.dao.UserDAO;
import pnc.mesadmin.dto.GetAllEmailInfo.GetAllEmailInfoRes;
import pnc.mesadmin.dto.GetAllEmailInfo.GetAllEmailInfoResB;
import pnc.mesadmin.dto.GetAllEmailInfo.GetAllEmailInfoResD;
import pnc.mesadmin.dto.GetEmailInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveEmailInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.EmailMessageInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.EmailMessageIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：邮件内容列表信息Service层
 * 创建人：乔帅阳
 * 创建时间：2018-7-5
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class EmailMessageService implements EmailMessageIService {
    @Resource
    private BaseIService baseIService;
    @Resource
    private EmailMessageDAO emailMessageDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private SMTPDAO smtpdao;


    //获取邮件内容列表信息
    @Override
    public GetAllEmailInfoRes QALLselectAllAllEmailInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllEmailInfoRes objGetAllEmailInfoRes = new GetAllEmailInfoRes();
        GetAllEmailInfoResB body = new GetAllEmailInfoResB();
        List<GetAllEmailInfoResD> dataList = new ArrayList<>();
        List<EmailMessageInfo> EmailMessageInfoList = baseIService.QALLBaseInfo(EmailMessageDAO.class, "SelectAllEmailMessageInfo", argInitDataFields, argPageInfo);//sMTPDAO.SelectAllEmailMessageInfo();
         if(EmailMessageInfoList.size()>0){
             for (int i=0;i<EmailMessageInfoList.size();i++){
                 GetAllEmailInfoResD date = new GetAllEmailInfoResD();
                 date.setEmailRd(EmailMessageInfoList.get(i).getRuid());
                 date.setEmailName(EmailMessageInfoList.get(i).getEmailName());
                 dataList.add(date);
             }
         }
        body.setData(dataList);
        objGetAllEmailInfoRes.setBody(body);
        return objGetAllEmailInfoRes;
    }
    // 获取邮件内容信息
    @Override
    public GetEmailInfoRes GetselectEmailInfoId(int EmailRd) throws SystemException {
        GetEmailInfoRes objGetEmailInfoRes = new GetEmailInfoRes();
        GetEmailInfoResB body =  new GetEmailInfoResB();
        GetEmailInfoResD data = new GetEmailInfoResD();
        EmailMessageInfo objEmailMessageInfo=  emailMessageDAO.SelectEmailMessageByID(EmailRd);
        if(objEmailMessageInfo!=null){
            data.setEmailRd(objEmailMessageInfo.getRuid());
            data.setEmailName(objEmailMessageInfo.getEmailName());
         /*   data.setDescription(objEmailMessageInfo.getDescription());*/
            data.setSubject(objEmailMessageInfo.getSubject());
            data.setMessage(objEmailMessageInfo.getMessage());

       /*     GetEmailInfoResDSender getEmailInfoResDSender=new GetEmailInfoResDSender();
            UserInfo userInfo=userDAO.SelectUserRd(objEmailMessageInfo.getSenderGd());
            if(userInfo!=null){
                getEmailInfoResDSender.setSendRd(userInfo.getRuid());
                getEmailInfoResDSender.setSendName(userInfo.getUserName());
            }
            data.setSender(getEmailInfoResDSender);*/


         /*   GetEmailInfoResDSMTP getEmailInfoResDSMTP=new GetEmailInfoResDSMTP();
            SMTPInfo smtpInfo=smtpdao.SelectByGuid(objEmailMessageInfo.getSMTPGd());
            if(smtpInfo!=null){
                getEmailInfoResDSMTP.setSMTPRd(smtpInfo.getRuid());
                getEmailInfoResDSMTP.setSMTPName(smtpInfo.getSMTPName());
            }
            data.setSMTP(getEmailInfoResDSMTP);*/
/*
            data.setMessageFormat(objEmailMessageInfo.getMessageFormat());*/
            data.setCreator(objEmailMessageInfo.getCreator());
            data.setCreateTime(objEmailMessageInfo.getCreateTime());
            data.setLastModifyMan(objEmailMessageInfo.getLastModifyMan());
            data.setLastModifyTime(objEmailMessageInfo.getLastModifyTime());
            data.setRemark(objEmailMessageInfo.getRemark());
        }
        body.setData(data);
        objGetEmailInfoRes.setBody(body);
        return objGetEmailInfoRes;
    }
    //保存邮件内容信息
    @Override
    public SaveEmailInfoRes AddinsertEmailMessageInfo(SaveEmailInfoReqBD00 busData00, EmailMessageInfo emailMessageInfo) throws SystemException {
        SaveEmailInfoRes saveEmailInfoRes = new SaveEmailInfoRes();
        SaveEmailInfoResB body = new SaveEmailInfoResB();
        SaveEmailInfoResD data = new SaveEmailInfoResD();

        if("".equals(busData00.getEmailName())){
            throw new SystemException("","名称必填项");
        }
        /*if("".equals(busData00.getSender())){
            throw new SystemException("","发件人必填项");
        }*/

        EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectAllEmailMessageInfoByEmailName(busData00.getEmailName());
        if(emailMessageInfo1!=null){
            throw new SystemException("","邮件内容名称已存在");
        }
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        emailMessageInfo.setGuid(CommonUtils.getRandomNumber());
        emailMessageInfo.setEmailName(busData00.getEmailName());

        emailMessageInfo.setSubject(busData00.getSubject());
        emailMessageInfo.setMessage(busData00.getMessage());





        emailMessageInfo.setCreator(userName);
        emailMessageInfo.setCreateTime(date);
        emailMessageInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        emailMessageInfo.setLastModifyTime(new Date());
        emailMessageInfo.setRemark(busData00.getRemark());
        emailMessageDAO.InsertEmailMessageInfo(emailMessageInfo);
        body.setData(data);
        saveEmailInfoRes.setBody(body);
        return saveEmailInfoRes;
    }
    //删除邮件内容信息
    @Override
    public SaveEmailInfoRes RmdeleteEmailMessageInfo(int Ruid) throws SystemException {
        SaveEmailInfoRes saveEmailInfoRes = new SaveEmailInfoRes();
        SaveEmailInfoResB body = new SaveEmailInfoResB();
        SaveEmailInfoResD data = new SaveEmailInfoResD();
        int count = emailMessageDAO.DeleteEmailMessageInfo(Ruid);
        if(count<=0)throw new SystemException("MG_MES","删除邮件内容失败！");
        body.setData(data);
        saveEmailInfoRes.setBody(body);
        return saveEmailInfoRes;
    }
    //更新邮件内容信息
    @Override
    public SaveEmailInfoRes ModupdateEmailMessageInfo(SaveEmailInfoReqBD02 busData02, EmailMessageInfo emailMessageInfo) throws SystemException {
        SaveEmailInfoRes SaveEmailInfoRes = new SaveEmailInfoRes();
        SaveEmailInfoResB body = new SaveEmailInfoResB();
        SaveEmailInfoResD data = new SaveEmailInfoResD();

        if("".equals(busData02.getEmailName())){
            throw new SystemException("","名称必填项");
        }

        //修改时名字不能重复
        EmailMessageInfo emailMessageInfo2=emailMessageDAO.SelectEmailMessageByID(busData02.getEmailRd());
        if(emailMessageInfo2!=null){
            EmailMessageInfo emailMessageInfo1=emailMessageDAO.SelectAllEmailMessageInfoByEmailName(busData02.getEmailName());
            if(emailMessageInfo1!=null&&!emailMessageInfo2.getEmailName().equals(busData02.getEmailName())){
                throw new SystemException("","邮件内容名称已存在");
            }
        }


        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        emailMessageInfo.setRuid(busData02.getEmailRd());
        emailMessageInfo.setEmailName(busData02.getEmailName());

        emailMessageInfo.setSubject(busData02.getSubject());
        emailMessageInfo.setMessage(busData02.getMessage());

        emailMessageInfo.setRemark(busData02.getRemark());
        emailMessageInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        emailMessageInfo.setLastModifyTime(new Date());
        emailMessageInfo.setCreator(userName);
        emailMessageInfo.setCreateTime(date);
        int count = emailMessageDAO.UpdateEmailMessageInfo(emailMessageInfo);
        if(count<=0){
            throw new SystemException("","更新邮件内容信息失败！");
        }
        body.setData(data);
        SaveEmailInfoRes.setBody(body);
        return SaveEmailInfoRes;
    }
   //复制邮件内容信息
    @Override
    public SaveEmailInfoRes copyEmailMessageInfo(SaveEmailInfoReqBD03 busData03, EmailMessageInfo emailMessageInfo) throws SystemException {
        SaveEmailInfoRes saveEmailInfoRes = new SaveEmailInfoRes();
        SaveEmailInfoResB body = new  SaveEmailInfoResB();
        SaveEmailInfoResD data = new SaveEmailInfoResD();
        EmailMessageInfo objEmailMessageInfo = emailMessageDAO.SelectEmailMessageByID(busData03.getEmailRd());
        if(objEmailMessageInfo == null){
            throw new SystemException("","查询邮件内容信息为空");
        }
      //复制一条邮件内容
        emailMessageInfo.setGuid(CommonUtils.getRandomNumber());
        emailMessageInfo.setEmailName(objEmailMessageInfo.getEmailName());
        emailMessageInfo.setDescription(objEmailMessageInfo.getDescription());
        emailMessageInfo.setSubject(objEmailMessageInfo.getSubject());
        emailMessageInfo.setMessage(objEmailMessageInfo.getMessage());
       // emailMessageInfo.setSender(objEmailMessageInfo.getSender());
        emailMessageInfo.setMessageFormat(objEmailMessageInfo.getMessageFormat());
        emailMessageInfo.setCreator(objEmailMessageInfo.getCreator());
        emailMessageInfo.setCreateTime(new Date());
        emailMessageInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        emailMessageInfo.setLastModifyTime(new Date());
        emailMessageInfo.setRemark(objEmailMessageInfo.getRemark());
        // 调用邮件邮件内容信息sql复制一条数据
        int count = emailMessageDAO.InsertEmailMessageInfo(emailMessageInfo);
        if(count <=0) throw new SystemException("","复制邮件内容信息失败！");
        //查询邮件内容信息
        EmailMessageInfo objemailMessageInfo = emailMessageDAO.SelectEmailMessageInfoByGuid(emailMessageInfo.getGuid());
        objemailMessageInfo.setEmailName(objemailMessageInfo.getEmailName()+objemailMessageInfo.getGuid());
        if(emailMessageDAO.UpdateEmailMessageInfo(objemailMessageInfo)<=0){
            throw new SystemException("","复制邮件内容信息失败！");
        }
        body.setData(data);
        saveEmailInfoRes.setBody(body);
        return saveEmailInfoRes;
    }


}
