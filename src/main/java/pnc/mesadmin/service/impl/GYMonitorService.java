package pnc.mesadmin.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJReqBD00;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJReqBD00CollectParam;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJRes;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.GYMonitorIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/9
 */
@Service
@Transactional
public class GYMonitorService implements GYMonitorIService {
    @Resource
    private BDAO bdao;

    @Resource
    private SOPDlDAO sopDlDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private DCItemDAO dcItemDAO;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private DoDCDAO doDCDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private LineDao lineDao;

    @Resource
    private DCVerDAO dcVerDAO;

    @Resource
    private EmailUserDAO emailUserDAO;

    @Resource
    private EmailRoleDAO emailRoleDAO;

    @Resource
    private EmailAddressDAO emailAddressDAO;

    @Resource
    private EmailMessageDAO emailMessageDAO;

    @Resource
    private SMTPDAO smtpdao;

    @Resource
    private UserDAO userDAO;

    @Resource
    private UserRoleDAO userRoleDAO;

    //工艺参数预警
    @Override
    public SaveGYYJRes SaveGYYJ(SaveGYYJReqBD00 bd00) {
        SaveGYYJRes res = new SaveGYYJRes();
        SaveGYYJResB resB = new SaveGYYJResB();

        //查询批次
        BInfo bInfo = bdao.selectBatchInfoByBatch(bd00.getBatch());
        if(bInfo == null){
            throw new SystemException("", "当前批次{" + bd00.getBatch() + "}不存在");
        }

        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(bd00.getSpecVerGd());
        if(specVerInfo == null){
            throw new SystemException("", "工序信息不存在");
        }

        //线体
        Lineinfo lineinfo = lineDao.SelectLineInfoByguid(bd00.getLineGd());
        if(lineinfo == null){
            throw new SystemException("", "线体信息不存在");
        }

        //设备
        DevInfo devInfo = devDAO.SelectByguid(bd00.getDevGd());
        if(devInfo == null){
            throw new SystemException("", "设备信息不存在");
        }

        String dCVerGd = "";
        SOPDlInfo sopDlInfo = sopDlDAO.SelectByMS(bInfo.getMaVerGd(), bd00.getSpecVerGd());
        if(sopDlInfo == null){
            dCVerGd = specVerInfo.getdCVerGd();
        }else{
            dCVerGd = sopDlInfo.getDCVerGd();
        }

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        //数据采集项信息
        List<DCItemInfo> dcItemInfos = dcItemDAO.selectDCItemInfosByDCVerGd(dCVerGd);

        Map<String, String> map = new HashMap<>();
        List<SaveGYYJReqBD00CollectParam> params = bd00.getCollectParam();
        if (params != null) {
            for (SaveGYYJReqBD00CollectParam param : params) {
                map.put(param.getDataAlias(), param.getDataVal());
            }
        }

        for(DCItemInfo dcItemInfo : dcItemInfos){
            //批次事物操作
            AffairInfo objEAffairInfo = new AffairInfo();

            if(!map.containsKey(dcItemInfo.getItemName())){
                throw new SystemException("", dcItemInfo.getItemName() + "--数据采集不能为空");
            }
            String sCValue = map.get(dcItemInfo.getItemName());

            //判断是否合格
            if ("00".equals(dcItemInfo.getItemType()) || "01".equals(dcItemInfo.getItemType())) {
                float svalue = Float.valueOf(dcItemInfo.getsValue()), uplimit = Float.valueOf(dcItemInfo.getUpLimit()), dwlimit = Float.valueOf(dcItemInfo.getDwLimit());

                //最大上限值
                float nMax = svalue + uplimit;
                //最大下限值
                float nMin = svalue - dwlimit;
                //采集数据值
                if ("".equals(sCValue)) {
                    throw new SystemException("", dcItemInfo.getItemName() + "--数据采集不能为空");
                }

                if ("00".equals(dcItemInfo.getChecked())) {
                    float nNew = 0f;
                    try {
                        nNew = Float.valueOf(sCValue);
                    } catch (Exception e) {
                        throw new SystemException("", dcItemInfo.getItemName() + "数据采集填写有误");
                    }

                    if (nNew > nMax || nNew < nMin) {
                        //超过上下限
                        bInfo.setBad("00");
                        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                        objEAffairInfo.setBatch(bInfo.getBatch());
                        objEAffairInfo.setSpecVerGd(specVerInfo.getGuid());
                        objEAffairInfo.setSpecName(specVerInfo.getSpecName());
                        objEAffairInfo.setOptType("07");
                        objEAffairInfo.setCreator(userName);
                        objEAffairInfo.setCreateTime(date);
                        objEAffairInfo.setLineGd(lineinfo.getGuid());
                        affairDAO.InsertAffairInfo(objEAffairInfo);

                        //发送邮件
                        DCVerInfo dcVerInfo = dcVerDAO.selectDCVerInfoByGuid(dCVerGd);
                        if(dcVerInfo == null){
                            throw new SystemException("", "数据采集信息不存在");
                        }
                        List<EmailUserInfo> emailUserInfos = emailUserDAO.SelectEmailUserInfoByDistributionGd(dcVerInfo.getPresetEmailDistributionGd());
                        List<EmailRoleInfo> emailRoleInfos =  emailRoleDAO.SelectEmailRoleInfoByDistributionGd(dcVerInfo.getPresetEmailDistributionGd());
                        List<EmailAddressInfo> emailAddressInfos = emailAddressDAO.SelectEmailAddressInfoByDistributionGd(dcVerInfo.getPresetEmailDistributionGd());
                        List<String> listu = new ArrayList<>(), listru = new ArrayList<>();
                        for(EmailUserInfo u : emailUserInfos){
                            listu.add(u.getUserGd());
                        }
                        List<UserInfo> userInfos = new ArrayList<>();
                        if(listu.size() > 0){
                            userInfos = userDAO.SelectByGuids(listu);
                        }
                        if(listru.size() > 0){
                            userInfos.addAll(userRoleDAO.SelectByGuids(listru));
                        }
                        Set<String> addresss = new HashSet<>();
                        for(UserInfo u : userInfos){
                            addresss.add(u.getEmailAddress());
                        }
                        for(EmailAddressInfo e : emailAddressInfos){
                            addresss.add(e.getEmailAddress());
                        }

                        SendMessage(addresss, dcVerInfo.getPresetEmailMessageGd());
                    }
                }
            } else if ("02".equals(dcItemInfo.getItemType())) {
                if ("00".equals(dcItemInfo.getChecked())) {
                    if (!sCValue.equals(dcItemInfo.getsValue())) {
                        bInfo.setBad("00");
                        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                        objEAffairInfo.setBatch(bInfo.getBatch());
                        objEAffairInfo.setSpecVerGd(specVerInfo.getGuid());
                        objEAffairInfo.setSpecName(specVerInfo.getSpecName());
                        objEAffairInfo.setOptType("07");
                        objEAffairInfo.setCreator(userName);
                        objEAffairInfo.setCreateTime(date);
                        objEAffairInfo.setLineGd(lineinfo.getGuid());
                        affairDAO.InsertAffairInfo(objEAffairInfo);
                    }
                }
            }

            //如果不良则不允许出站
            if ("00".equals(dcItemInfo.getChecked()) && "00".equals(dcItemInfo.getAction())) {
                bInfo.setStatus("02"); //不良冻结批次

                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(bInfo.getBatch());
                objEAffairInfo.setSpecVerGd(specVerInfo.getGuid());
                objEAffairInfo.setSpecName(specVerInfo.getSpecName());
                objEAffairInfo.setOptType("09");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                objEAffairInfo.setLineGd(lineinfo.getGuid());
                affairDAO.InsertAffairInfo(objEAffairInfo);
            }

            //新增批次数据采集信息表
            DoDCInfo objEDoDCInfo = new DoDCInfo();
            objEDoDCInfo.setGuid(CommonUtils.getRandomNumber());
            objEDoDCInfo.setBatch(bInfo.getBatch());
            objEDoDCInfo.setSpecVerGd(specVerInfo.getGuid());
            objEDoDCInfo.setSpecName(specVerInfo.getSpecName());
            objEDoDCInfo.setDevGd(devInfo.getGuid());
            objEDoDCInfo.setDevName(devInfo.getDevName());
            objEDoDCInfo.setdCItemGd(dcItemInfo.getDcVerGd());
            objEDoDCInfo.setItemName(dcItemInfo.getItemName());
            objEDoDCInfo.setcValue(sCValue);
            objEDoDCInfo.setCreator(userName);
            objEDoDCInfo.setCreateTime(date);
            doDCDAO.InsertDoDcInfo(objEDoDCInfo);
        }

        res.setBody(resB);
        return res;
    }

    public void SendMessage(Set<String> us, String id){
        EmailMessageInfo emailMessageInfo = emailMessageDAO.SelectEmailMessageInfoByGuid(id);
        if(emailMessageInfo == null){
            throw new SystemException("", "邮件内容信息不存在");
        }
        SMTPInfo smtpInfo = smtpdao.SelectByGuid(emailMessageInfo.getSMTPGd());
        if(smtpInfo == null){
            throw new SystemException("", "邮件服务器信息不存在");
        }
        //发送人
        UserInfo userInfo = userDAO.SelectUserRd(emailMessageInfo.getSenderGd());
        if(userInfo == null){
            throw new SystemException("", "邮件发送人不存在");
        }

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(smtpInfo.getSMTPURL());
        javaMailSender.setUsername(smtpInfo.getUserName());
        javaMailSender.setPassword(smtpInfo.getPassword());
        javaMailSender.setPort(smtpInfo.getPort());
        if(smtpInfo.getUseSSL()) {
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            javaMailSender.setJavaMailProperties(props);
        }

        String[] array = new String[us.size()];

        if(!emailMessageInfo.getMessageFormat()){
            SimpleMailMessage mess = new SimpleMailMessage();
            mess.setTo(us.toArray(array));
            mess.setFrom(userInfo.getEmailAddress());
            mess.setSubject(emailMessageInfo.getSubject());
            mess.setText(emailMessageInfo.getMessage());
            try {
                javaMailSender.send(mess);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = null;
            try {
                messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
                messageHelper.setTo(us.toArray(array));
                messageHelper.setFrom(userInfo.getEmailAddress());
                messageHelper.setSubject(emailMessageInfo.getSubject());
                messageHelper.setText(emailMessageInfo.getMessage(), true);

                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
