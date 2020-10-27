package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoRes;
import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoResB;
import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoResD;
import pnc.mesadmin.dto.GetEmailDisInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveEmailDisInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.EmailDisIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:29
 * @Description:
 */
@Service
@Transactional
public class EmailDisService implements EmailDisIService {

    @Resource
    private EmailDistributionDAO emailDistributionDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private EmailAddressDAO emailAddressDAO;

    @Resource
    private EmailRoleDAO emailRoleDAO;

    @Resource
    private EmailUserDAO emailUserDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private RoleDAO roleDAO;


    //获取分发列表信息
    @Override
    public GetAllEmailDisInfoRes QALLGetAllEmailDisInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllEmailDisInfoRes getAllEmailDisInfoRes=new GetAllEmailDisInfoRes();
        GetAllEmailDisInfoResB getAllEmailDisInfoResB=new GetAllEmailDisInfoResB();
        List<GetAllEmailDisInfoResD> getAllEmailDisInfoResDS=new ArrayList<GetAllEmailDisInfoResD>();

        List<EmailDistributionInfo> emailDistributionInfos= baseIService.QALLBaseInfo(EmailDistributionDAO.class, "SelectEmailDistributionInfo",
                argInitDataFields, argPageInfo);

        if(emailDistributionInfos!=null&&emailDistributionInfos.size()>0){
            for(EmailDistributionInfo emailDistributionInfo:emailDistributionInfos){
                GetAllEmailDisInfoResD getAllEmailDisInfoResD=new GetAllEmailDisInfoResD();
                getAllEmailDisInfoResD.setEmailDisRd(emailDistributionInfo.getRuid());
                getAllEmailDisInfoResD.setEmailDisName(emailDistributionInfo.getDistributionName());
                getAllEmailDisInfoResDS.add(getAllEmailDisInfoResD);
            }
        }

        getAllEmailDisInfoResB.setData(getAllEmailDisInfoResDS);
        getAllEmailDisInfoRes.setBody(getAllEmailDisInfoResB);
        return getAllEmailDisInfoRes;
    }


    //获取单个分发信息
    @Override
    public GetEmailDisInfoRes GetGetEmailDisInfoRes(GetEmailDisInfoReq00 getEmailDisInfoReq00) {
        GetEmailDisInfoRes getEmailDisInfoRes=new GetEmailDisInfoRes();
        GetEmailDisInfoResB getEmailDisInfoResB=new GetEmailDisInfoResB();
        GetEmailDisInfoResD getEmailDisInfoResD=new GetEmailDisInfoResD();
        if ("".equals(getEmailDisInfoReq00.getEmailDisRd())||getEmailDisInfoReq00.getEmailDisRd()<=0){
            throw new SystemException("xxx","邮件分发信息不存在");
        }
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(getEmailDisInfoReq00.getEmailDisRd());
        if(emailDistributionInfo!=null){
            getEmailDisInfoResD.setCreator(emailDistributionInfo.getCreator());
            getEmailDisInfoResD.setCreateTime(DateUtil.format(emailDistributionInfo.getCreateTime()));
            getEmailDisInfoResD.setRemark(emailDistributionInfo.getRemark());
            getEmailDisInfoResD.setLastModifyMan(emailDistributionInfo.getLastModifyMan());
            getEmailDisInfoResD.setLastModifyTime(DateUtil.format(emailDistributionInfo.getLastModifyTime()));
            getEmailDisInfoResD.setEmailDisRd(emailDistributionInfo.getRuid());
            getEmailDisInfoResD.setEmailDisName(emailDistributionInfo.getDistributionName());
            getEmailDisInfoResD.setDescription(emailDistributionInfo.getDescription());

            List<GetEmailDisInfoResDUserList> getEmailDisInfoResDUserLists=new ArrayList<GetEmailDisInfoResDUserList>();
            List<EmailUserInfo> emailUserInfos=emailUserDAO.SelectEmailUserInfoByDistributionGd(emailDistributionInfo.getGuid());
            if(emailUserInfos!=null&&emailUserInfos.size()>0){
                for(EmailUserInfo emailUserInfo:emailUserInfos){
                    GetEmailDisInfoResDUserList getEmailDisInfoResDUserList=new GetEmailDisInfoResDUserList();
                    UserInfo userInfo=userDAO.SelectUserRd(emailUserInfo.getUserGd());
                    if(userInfo!=null){
                        getEmailDisInfoResDUserList.setUserRd(userInfo.getRuid());
                        getEmailDisInfoResDUserList.setUserName(userInfo.getUserName());
                        if(userInfo.getEmailAddress()==null||"".equals(userInfo.getEmailAddress())){
                            getEmailDisInfoResDUserList.setEmailAddress("");
                        }else {
                            getEmailDisInfoResDUserList.setEmailAddress(userInfo.getEmailAddress());
                        }
                        getEmailDisInfoResDUserLists.add(getEmailDisInfoResDUserList);
                        getEmailDisInfoResD.setUserList(getEmailDisInfoResDUserLists);
                    }else {
                        getEmailDisInfoResD.setUserList(null);
                    }

                }
            }


            List<GetEmailDisInfoResDRoleList> getEmailDisInfoResDRoleLists=new ArrayList<GetEmailDisInfoResDRoleList>();
            List<EmailRoleInfo> emailRoleInfos=emailRoleDAO.SelectEmailRoleInfoByDistributionGd(emailDistributionInfo.getGuid());
            if(emailRoleInfos!=null&&emailRoleInfos.size()>0){
                for(EmailRoleInfo emailRoleInfo:emailRoleInfos){
                    GetEmailDisInfoResDRoleList getEmailDisInfoResDRoleList=new GetEmailDisInfoResDRoleList();

                    RoleInfo roleInfo=roleDAO.selectRoleIdGuid(emailRoleInfo.getRoleGd());
                    if(roleInfo!=null){
                        getEmailDisInfoResDRoleList.setRoleRd(roleInfo.getRuid());
                        getEmailDisInfoResDRoleList.setRoleName(roleInfo.getRoleName());
                    }
                    getEmailDisInfoResDRoleLists.add(getEmailDisInfoResDRoleList);
                }
            }
            getEmailDisInfoResD.setRoleList(getEmailDisInfoResDRoleLists);

            List<GetEmailDisInfoResDAddressList> getEmailDisInfoResDAddressLists=new ArrayList<GetEmailDisInfoResDAddressList>();
            List<EmailAddressInfo> emailAddressInfos=emailAddressDAO.SelectEmailAddressInfoByDistributionGd(emailDistributionInfo.getGuid());
            if (emailAddressInfos!=null&&emailAddressInfos.size()>0){
                for(EmailAddressInfo emailAddressInfo:emailAddressInfos){
                    GetEmailDisInfoResDAddressList getEmailDisInfoResDAddressList=new GetEmailDisInfoResDAddressList();
                    getEmailDisInfoResDAddressList.setEmailAddress(emailAddressInfo.getEmailAddress());
                    getEmailDisInfoResDAddressList.setRecipient(emailAddressInfo.getRecipient());
                    getEmailDisInfoResDAddressLists.add(getEmailDisInfoResDAddressList);
                }
            }

            getEmailDisInfoResD.setAddressList(getEmailDisInfoResDAddressLists);

        }
        getEmailDisInfoResB.setData(getEmailDisInfoResD);
        getEmailDisInfoRes.setBody(getEmailDisInfoResB);
        return getEmailDisInfoRes;
    }

    //新增分发信息
    @Override
    public SaveEmailDisInfoRes AddSaveEmailDisInfoRes(SaveEmailDisInfoReq00 saveEmailDisInfoReq00) {
        SaveEmailDisInfoRes saveEmailDisInfoRes=new SaveEmailDisInfoRes();
        SaveEmailDisInfoResB saveEmailDisInfoResB=new SaveEmailDisInfoResB();
        if(saveEmailDisInfoReq00.getEmailDisName()==null||"".equals(saveEmailDisInfoReq00.getEmailDisName())){
            throw new SystemException("xxx","新增失败，名称不能为空");
        }

        EmailDistributionInfo emailDistributionInfos=emailDistributionDAO.SelectEmailDistributionInfoBydistributionName(saveEmailDisInfoReq00.getEmailDisName());
        if(emailDistributionInfos!=null){
            throw new SystemException("xxx","新增失败，该名称已存在");
        }
        EmailDistributionInfo emailDistributionInfo=new EmailDistributionInfo();
        emailDistributionInfo.setGuid(CommonUtils.getRandomNumber());
        //先新增细表
        if(!(saveEmailDisInfoReq00.getUserList()==null||"".equals(saveEmailDisInfoReq00.getUserList())||saveEmailDisInfoReq00.getUserList().size()<=0)){
            //判断前端是否重复
            Set<Integer> set=new HashSet<Integer>();
            for(SaveEmailDisInfoReq00UserList saveEmailDisInfoReq00UserList:saveEmailDisInfoReq00.getUserList()){
                set.add(saveEmailDisInfoReq00UserList.getUserRd());
            }
            if(set.size()!=saveEmailDisInfoReq00.getUserList().size()){
                throw new SystemException("xxx","新增失败，收件人员工清单有重复数据");
            }
            int i=1;
            for(SaveEmailDisInfoReq00UserList saveEmailDisInfoReq00UserList:saveEmailDisInfoReq00.getUserList()){
                EmailUserInfo emailUserInfo=new EmailUserInfo();
                emailUserInfo.setDistributionGd(emailDistributionInfo.getGuid());
                UserInfo userInfo=userDAO.SelectUserInfoByruid(saveEmailDisInfoReq00UserList.getUserRd());
                if(userInfo!=null){
                    emailUserInfo.setUserGd(userInfo.getGuid());
                }
                emailUserInfo.setSequence(i);
                emailUserDAO.InsertEmailUserInfo(emailUserInfo);
                i++;
            }
        }
        if(!(saveEmailDisInfoReq00.getRoleList()==null||"".equals(saveEmailDisInfoReq00.getRoleList())||saveEmailDisInfoReq00.getRoleList().size()<=0)){
            //判断前端是否重复
            Set<Integer> set=new HashSet<Integer>();
            for(SaveEmailDisInfoReq00RoleList saveEmailDisInfoReq00RoleList:saveEmailDisInfoReq00.getRoleList()){
                set.add(saveEmailDisInfoReq00RoleList.getRoleRd());
            }
            if(set.size()!=saveEmailDisInfoReq00.getRoleList().size()){
                throw new SystemException("xxx","新增失败，收件人员工清单有重复数据");
            }
            int i=1;
            for(SaveEmailDisInfoReq00RoleList saveEmailDisInfoReq00RoleList:saveEmailDisInfoReq00.getRoleList()){
                EmailRoleInfo emailRoleInfo=new EmailRoleInfo();
                emailRoleInfo.setDistributionGd(emailDistributionInfo.getGuid());
                RoleInfo roleInfo=roleDAO.SelectRoleInfoByruid(saveEmailDisInfoReq00RoleList.getRoleRd());
                if(roleInfo!=null){
                    emailRoleInfo.setRoleGd(roleInfo.getGuid());
                }
                emailRoleInfo.setSequence(i);
                emailRoleDAO.InsertEmailRoleInfo(emailRoleInfo);
                i++;
            }
        }
        if(!(saveEmailDisInfoReq00.getAddressList()==null||"".equals(saveEmailDisInfoReq00.getAddressList())||saveEmailDisInfoReq00.getAddressList().size()<=0)){
            int i=1;
            for(SaveEmailDisInfoReq00AddressList saveEmailDisInfoReq00AddressList:saveEmailDisInfoReq00.getAddressList()){
                if(saveEmailDisInfoReq00AddressList.getEmailAddress()==null||"".equals(saveEmailDisInfoReq00AddressList.getEmailAddress())){
                    throw new SystemException("xxx","新增失败，第"+i+"的外部人员的地址不能为空");
                }
                if(saveEmailDisInfoReq00AddressList.getRecipient()==null||"".equals(saveEmailDisInfoReq00AddressList.getRecipient())){
                    throw new SystemException("xxx","新增失败，第"+i+"的外部人员的名称不能为空");
                }
         /*       String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
                if (saveEmailDisInfoReq00AddressList.getEmailAddress().matches(format)){*/
                    EmailAddressInfo emailAddressInfo=new EmailAddressInfo();
                    emailAddressInfo.setDistributionGd(emailDistributionInfo.getGuid());
                    emailAddressInfo.setEmailAddress(saveEmailDisInfoReq00AddressList.getEmailAddress());
                    emailAddressInfo.setRecipient(saveEmailDisInfoReq00AddressList.getRecipient());
                    emailAddressInfo.setSequence(i);
                    emailAddressDAO.InsertEmailAddressInfo(emailAddressInfo);
                    i++;
             /*   }*//*else {
                    throw new SystemException("xxx","新增失败，第"+i+"的邮箱格式不正确");
                }*/
            }
        }

        //最后新增主表
        emailDistributionInfo.setCreator(CommonUtils.readUser().getRealName());
        emailDistributionInfo.setCreateTime(new Date());
        emailDistributionInfo.setRemark(saveEmailDisInfoReq00.getRemark());
        emailDistributionInfo.setDescription(saveEmailDisInfoReq00.getDescription());
        emailDistributionInfo.setDistributionName(saveEmailDisInfoReq00.getEmailDisName());
        emailDistributionDAO.InsertEmailDistributionInfo(emailDistributionInfo);






        saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
        return saveEmailDisInfoRes;
    }
    //删除分发信息
    @Override
    public SaveEmailDisInfoRes RmSaveEmailDisInfoRes(SaveEmailDisInfoReq01 saveEmailDisInfoReq01) {
        SaveEmailDisInfoRes saveEmailDisInfoRes=new SaveEmailDisInfoRes();
        SaveEmailDisInfoResB saveEmailDisInfoResB=new SaveEmailDisInfoResB();
        if ("".equals(saveEmailDisInfoReq01.getEmailDisRd())||saveEmailDisInfoReq01.getEmailDisRd()<=0){
            throw new SystemException("xxx","删除失败，该信息不存在");
        }
        if(emailDistributionDAO.DeleteEmailDistributionInfo(saveEmailDisInfoReq01.getEmailDisRd())<=0){
            throw new SystemException("xxx","删除失败，该信息不存在");
        }
    /*    EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveEmailDisInfoReq01.getEmailDisRd());
        if(emailDistributionInfo==null){
            throw new SystemException("xxx","删除失败，该信息不存在");
        }
        List<EmailUserInfo> emailUserInfos=emailUserDAO.SelectEmailUserInfoByDistributionGd(emailDistributionInfo.getGuid());
        if(emailUserInfos!=null&&emailUserInfos.size()>0){
            for(EmailUserInfo emailUserInfo: emailUserInfos){
                if(emailUserDAO.DeleteEmailUserInfo(emailUserInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除失败，该分发用户信息不存在");
                }
            }
        }

        List<EmailRoleInfo> emailRoleInfos=emailRoleDAO.SelectEmailRoleInfoByDistributionGd(emailDistributionInfo.getGuid());
        if(emailRoleInfos!=null&&emailRoleInfos.size()>0){
            for(EmailRoleInfo emailRoleInfo: emailRoleInfos){
                if(emailRoleDAO.DeleteEmailRoleInfo(emailRoleInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除失败，该分发角色信息不存在");
                }
            }
        }

        List<EmailAddressInfo> emailAddressInfos=emailAddressDAO.SelectEmailAddressInfoByDistributionGd(emailDistributionInfo.getGuid());
        if(emailAddressInfos!=null&&emailAddressInfos.size()>0){
            for(EmailAddressInfo emailAddressInfo: emailAddressInfos){
                if(emailAddressDAO.DeleteEmailEmailAddressInfo(emailAddressInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除失败，该分发地址信息不存在");
                }
            }
        }*/

        saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
        return saveEmailDisInfoRes;
    }
    //修改分发信息
    @Override
    public SaveEmailDisInfoRes ModSaveEmailDisInfoRes(SaveEmailDisInfoReq02 saveEmailDisInfoReq02) {
        SaveEmailDisInfoRes saveEmailDisInfoRes=new SaveEmailDisInfoRes();
        SaveEmailDisInfoResB saveEmailDisInfoResB=new SaveEmailDisInfoResB();
        if ("".equals(saveEmailDisInfoReq02.getEmailDisRd())||saveEmailDisInfoReq02.getEmailDisRd()<=0){
            throw new SystemException("xxx","更新失败，该信息不存在");
        }
        EmailDistributionInfo emailDistributionInfo=emailDistributionDAO.SelectEmailDistributionInfoByRuid(saveEmailDisInfoReq02.getEmailDisRd());
        if(emailDistributionInfo==null){
            throw new SystemException("xxx","更新失败，该信息不存在");
        }
        EmailDistributionInfo emailDistributionInfos=emailDistributionDAO.SelectEmailDistributionInfoBydistributionName(saveEmailDisInfoReq02.getEmailDisName());
        if(emailDistributionInfos!=null&&!emailDistributionInfo.getDistributionName().equals(saveEmailDisInfoReq02.getEmailDisName())){
            throw new SystemException("xxx","更新失败，该名字已存在");
        }

        List<EmailUserInfo> emailUserInfos=emailUserDAO.SelectEmailUserInfoByDistributionGd(emailDistributionInfo.getGuid());
        if(emailUserInfos!=null&&emailUserInfos.size()>0){
            for(EmailUserInfo emailUserInfo: emailUserInfos){
                if(emailUserDAO.DeleteEmailUserInfo(emailUserInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除失败，该分发用户信息不存在");
                }
            }
        }
        if(!(saveEmailDisInfoReq02.getUserList()==null||"".equals(saveEmailDisInfoReq02.getUserList())||saveEmailDisInfoReq02.getUserList().size()<=0)){
            //判断前端是否重复
            Set<Integer> set=new HashSet<Integer>();
            for(SaveEmailDisInfoReq02UserList saveEmailDisInfoReq00UserList:saveEmailDisInfoReq02.getUserList()){
                set.add(saveEmailDisInfoReq00UserList.getUserRd());
            }
            if(set.size()!=saveEmailDisInfoReq02.getUserList().size()){
                throw new SystemException("xxx","新增失败，收件人员工清单有重复数据");
            }
        }
        int i=1;
        for(SaveEmailDisInfoReq02UserList saveEmailDisInfoReq00UserList:saveEmailDisInfoReq02.getUserList()){
            EmailUserInfo emailUserInfo=new EmailUserInfo();
            emailUserInfo.setDistributionGd(emailDistributionInfo.getGuid());
            UserInfo userInfo=userDAO.SelectUserInfoByruid(saveEmailDisInfoReq00UserList.getUserRd());
            if(userInfo!=null){
                emailUserInfo.setUserGd(userInfo.getGuid());
            }
            emailUserInfo.setSequence(i);
            emailUserDAO.InsertEmailUserInfo(emailUserInfo);
            i++;
        }
        List<EmailRoleInfo> emailRoleInfos=emailRoleDAO.SelectEmailRoleInfoByDistributionGd(emailDistributionInfo.getGuid());
        if(emailRoleInfos!=null&&emailRoleInfos.size()>0){
            for(EmailRoleInfo emailRoleInfo: emailRoleInfos){
                if(emailRoleDAO.DeleteEmailRoleInfo(emailRoleInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除失败，该分发角色信息不存在");
                }
            }
        }
        if(!(saveEmailDisInfoReq02.getRoleList()==null||"".equals(saveEmailDisInfoReq02.getRoleList())||saveEmailDisInfoReq02.getRoleList().size()<=0)){
            //判断前端是否重复
            Set<Integer> set=new HashSet<Integer>();
            for(SaveEmailDisInfoReq02RoleList saveEmailDisInfoReq00RoleList:saveEmailDisInfoReq02.getRoleList()){
                set.add(saveEmailDisInfoReq00RoleList.getRoleRd());
            }
            if(set.size()!=saveEmailDisInfoReq02.getRoleList().size()){
                throw new SystemException("xxx","新增失败，收件人员工清单有重复数据");
            }
        }
        int j=1;
        for(SaveEmailDisInfoReq02RoleList saveEmailDisInfoReq00RoleList:saveEmailDisInfoReq02.getRoleList()){
            EmailRoleInfo emailRoleInfo=new EmailRoleInfo();
            emailRoleInfo.setDistributionGd(emailDistributionInfo.getGuid());
            RoleInfo roleInfo=roleDAO.SelectRoleInfoByruid(saveEmailDisInfoReq00RoleList.getRoleRd());
            if(roleInfo!=null){
                emailRoleInfo.setRoleGd(roleInfo.getGuid());
            }
            emailRoleInfo.setSequence(j);
            emailRoleDAO.InsertEmailRoleInfo(emailRoleInfo);
            j++;
        }
        List<EmailAddressInfo> emailAddressInfos=emailAddressDAO.SelectEmailAddressInfoByDistributionGd(emailDistributionInfo.getGuid());
        if(emailAddressInfos!=null&&emailAddressInfos.size()>0){
            for(EmailAddressInfo emailAddressInfo: emailAddressInfos){
                if(emailAddressDAO.DeleteEmailEmailAddressInfo(emailAddressInfo.getRuid())<=0){
                    throw new SystemException("xxx","删除失败，该分发地址信息不存在");
                }
            }
        }

        if(!(saveEmailDisInfoReq02.getAddressList()==null||"".equals(saveEmailDisInfoReq02.getAddressList())||saveEmailDisInfoReq02.getAddressList().size()<=0)){
            //判断前端是否重复
            Set<String> set=new HashSet<String>();
            for(SaveEmailDisInfoReq02AddressList saveEmailDisInfoReq02AddressList:saveEmailDisInfoReq02.getAddressList()){
                set.add(saveEmailDisInfoReq02AddressList.getEmailAddress());
            }
            if(set.size()!=saveEmailDisInfoReq02.getAddressList().size()){
                throw new SystemException("xxx","新增失败，外部人员信息有重复数据");
            }
        }


        if(!(saveEmailDisInfoReq02.getAddressList()==null||"".equals(saveEmailDisInfoReq02.getAddressList())||saveEmailDisInfoReq02.getAddressList().size()<=0)){
            int k=1;
            for(SaveEmailDisInfoReq02AddressList saveEmailDisInfoReq00AddressList:saveEmailDisInfoReq02.getAddressList()){
                if(saveEmailDisInfoReq00AddressList.getEmailAddress()==null||"".equals(saveEmailDisInfoReq00AddressList.getEmailAddress())){
                    throw new SystemException("xxx","新增失败，第"+k+"的行外部人员的地址不能为空");
                }
                if(saveEmailDisInfoReq00AddressList.getRecipient()==null||"".equals(saveEmailDisInfoReq00AddressList.getRecipient())){
                    throw new SystemException("xxx","新增失败，第"+k+"的行外部人员的名称不能为空");
                }
           /*     String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
                Pattern p = Pattern.compile(str);
                Matcher m = p.matcher(saveEmailDisInfoReq00AddressList.getEmailAddress());

                if (m.matches()){*/
                    EmailAddressInfo emailAddressInfo=new EmailAddressInfo();
                    emailAddressInfo.setDistributionGd(emailDistributionInfo.getGuid());
                    emailAddressInfo.setEmailAddress(saveEmailDisInfoReq00AddressList.getEmailAddress());
                    emailAddressInfo.setRecipient(saveEmailDisInfoReq00AddressList.getRecipient());
                    emailAddressInfo.setSequence(k);
                    emailAddressDAO.InsertEmailAddressInfo(emailAddressInfo);
                    k++;
             /*   }else {
                    throw new SystemException("xxx","新增失败，第"+i+"行的邮箱格式不正确");
                }*/
            }
        }


        emailDistributionInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        emailDistributionInfo.setLastModifyTime(new Date());
        emailDistributionInfo.setDistributionName(saveEmailDisInfoReq02.getEmailDisName());
        emailDistributionInfo.setDescription(saveEmailDisInfoReq02.getDescription());
        emailDistributionInfo.setRemark(saveEmailDisInfoReq02.getRemark());
        if(emailDistributionDAO.UpdateEmailDistributionInfo(emailDistributionInfo)<=0){
            throw new SystemException("xxx","更新分发主表信息失败");
        }

        saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
        return saveEmailDisInfoRes;
    }
}
