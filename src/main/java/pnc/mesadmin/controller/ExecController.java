package pnc.mesadmin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetUpMaInfoDto.GetUpMaRequest;
import pnc.mesadmin.dto.GetUpMaInfoDto.GetUpMaResponse;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称 物料 有效期 管控 信息
 * 创建时间：2019-04-03
 * 修改人：yin.yang
 * 修改时间：
 */
@Controller
@RequestMapping("/exec/ma")
public class ExecController {

    @Resource
    private OnLineMaPeriodDAO onLineMaPeriodDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private ModeCDAO modeCDAO;

    @Resource
    private EmailDistributionDAO emailDistributionDAO;
    @Resource
    private EmailMessageDAO emailMessageDAO;

    @Resource
    private EmailUserDAO emailUserDAO;

    @Resource
    private EmailRoleDAO emailRoleDAO;

    @Resource
    private EmailAddressDAO emailAddressDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private RoleDAO roleDAO;

    @Resource
    private UserRoleDAO userRoleDAO;

    //上料
    @RequestMapping(value = "/getUpMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public MBaseRes STKInfo(@RequestBody GetUpMaRequest requsest) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        try {
            GetUpMaResponse response = new GetUpMaResponse();
            //============逻辑校验============
            //校验物料过期【SERVICE_WLGQ】是否启用
            ModeCInfo modeCInfo = modeCDAO.SelectmodeName("SERVICE_WLGQ");
            if (modeCInfo == null)
                throw new SystemException("EEEE", "在线物料过期时间未开启");
            if (modeCInfo.getModeValue().equals("SERVICE_WLGQ_01"))
                throw new SystemException("EEEE", "在线物料过期时间未开启");

            //校验物料是否在物料管理里面设置在线物料有效期管控
            BInfo bInfo = bdao.SelectBInfo(requsest.getBatch(), "01");
            if (bInfo == null)
                throw new SystemException("EEEE", "当前不存在该批次信息,请确认");
            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
            if (maVerInfo == null)
                throw new SystemException("EEEE", "当前批次不存在对应的物料信息");
            if (StringUtils.isBlank(maVerInfo.getOnLineMaPeriodGd()))
                throw new SystemException("EEEE", "当前物料不要求管控在线物料有效期");
            OnLineMaPeriodInfo onLineMaPeriodInfo = onLineMaPeriodDAO.selectMaPeriodByGuid(maVerInfo.getOnLineMaPeriodGd());
            //查询提前邮箱地址以及提前内容
            GetUpMaResponse.TqInfo tqInfo = new GetUpMaResponse.TqInfo();
            Set<String> preseEmail = AddressInfo(onLineMaPeriodInfo.getPresetEmailDistributionGd());
            StringBuffer tqRevMai = new StringBuffer();
            for (String pressE : preseEmail) {
                tqRevMai.append(pressE);
                tqRevMai.append("|");
            }
            EmailMessageInfo preseEmailMessageInfo = emailMessageDAO.SelectEmailMessageInfoByGuid(onLineMaPeriodInfo.getPresetEmailMessageGd());
            tqInfo.setTqRevMail(tqRevMai != null && tqRevMai.length() > 0 ? tqRevMai.substring(0, tqRevMai.length() - 1) : "");
            tqInfo.setTqTimer(onLineMaPeriodInfo.getPresetTime().toString());
            tqInfo.setTqRevContent(preseEmailMessageInfo != null ? preseEmailMessageInfo.getMessage() : "");
            //查询超时邮箱地址以及超时内容
            GetUpMaResponse.GqInfo gqInfo = new GetUpMaResponse.GqInfo();
            Set<String> overEmail = AddressInfo(onLineMaPeriodInfo.getOverdueEmailDistributionGd());
            EmailMessageInfo overEmailMessageInfo = emailMessageDAO.SelectEmailMessageInfoByGuid(onLineMaPeriodInfo.getOverdueEmailMessageGd());
            StringBuffer ovRevMai = new StringBuffer();
            for (String overE : overEmail) {
                ovRevMai.append(overE);
                ovRevMai.append("|");
            }
            gqInfo.setGqRevMail(ovRevMai != null && ovRevMai.length() > 0 ? ovRevMai.substring(0, ovRevMai.length() - 1) : "");
            gqInfo.setGqRevContent(overEmailMessageInfo != null ? overEmailMessageInfo.getMessage() : "");
            response.setBatch(requsest.getBatch());
            response.setPeriodTime(onLineMaPeriodInfo.getPeriodTime().toString());
            response.setTqInfo(tqInfo);
            response.setGqInfo(gqInfo);
            baseResBody.setData(response);
            baseResBody.setMsgCode("0x00000");
            baseResBody.setMsgDes("成功");
            baseResponse.setBody(baseResBody);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    public Set<String> AddressInfo(String overdueEmailDistributionGd) {
        Set<String> userInfoAndaddress = new HashSet<>();
        if (!com.alibaba.druid.util.StringUtils.isEmpty(overdueEmailDistributionGd)) {
            EmailDistributionInfo emailDistributionInfo = emailDistributionDAO.SelectEmailDistributionInfoByGuid(overdueEmailDistributionGd);
            if (emailDistributionInfo != null) {
                List<EmailUserInfo> emailUserInfos = emailUserDAO.SelectEmailUserInfoByDistributionGd(emailDistributionInfo.getGuid());
                if (emailUserInfos != null) {
                    for (EmailUserInfo emailUserInfo : emailUserInfos
                    ) {
                        UserInfo userInfo = userDAO.SelectUserRd(emailUserInfo.getUserGd());
                        if (userInfo != null) {
                            if (!com.alibaba.druid.util.StringUtils.isEmpty(userInfo.getEmailAddress())) {
                                userInfoAndaddress.add(userInfo.getEmailAddress());
                            }
                        }


                    }
                }

                List<EmailRoleInfo> emailRoleInfos = emailRoleDAO.SelectEmailRoleInfoByDistributionGd(emailDistributionInfo.getGuid());
                if (emailRoleInfos != null) {
                    for (EmailRoleInfo emailRoleInfo : emailRoleInfos
                    ) {
                        if (emailRoleInfo != null && !com.alibaba.druid.util.StringUtils.isEmpty(emailRoleInfo.getRoleGd())) {
                            List<String> stringList = userRoleDAO.SelectByRoleGd(emailRoleInfo.getRoleGd());
                            if (stringList != null) {
                                for (String str : stringList
                                ) {
                                    UserInfo userInfo = userDAO.SelectUserRd(str);

                                    if (userInfo != null && !com.alibaba.druid.util.StringUtils.isEmpty(userInfo.getEmailAddress())) {

                                        userInfoAndaddress.add(userInfo.getEmailAddress());
                                    }
                                }
                            }
                        }


                    }
                }
                List<EmailAddressInfo> emailAddressInfos = emailAddressDAO.SelectEmailAddressInfoByDistributionGd(emailDistributionInfo.getGuid());
                if (emailAddressInfos != null) {
                    for (EmailAddressInfo emailAddressInfo : emailAddressInfos
                    ) {
                        if (!com.alibaba.druid.util.StringUtils.equals("", emailAddressInfo.getEmailAddress())) {
                            userInfoAndaddress.add(emailAddressInfo.getEmailAddress());
                        }

                    }
                }


            }
        }
        return userInfoAndaddress;
    }

}
