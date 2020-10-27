package pnc.mesadmin.controller;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ChangePwdInfo.ChangePwdInfoRes;
import pnc.mesadmin.dto.ChangePwdInfo.ChangePwdInfoResBody;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoReqBD00;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoRes;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoResB;
import pnc.mesadmin.dto.GetAllUserInfo.GetAllUserInfoRes;
import pnc.mesadmin.dto.GetAllUserInfo.GetAllUserInfoResB;
import pnc.mesadmin.dto.GetUserInfo.GetUserInfoReqBD00;
import pnc.mesadmin.dto.GetUserInfo.GetUserInfoRes;
import pnc.mesadmin.dto.GetUserInfo.GetUserInfoResB;
import pnc.mesadmin.dto.SaveUserInfo.*;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoRes;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResB;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResBD;
import pnc.mesadmin.dto.UserLogout.UserLogoutRes;
import pnc.mesadmin.dto.UserLogout.UserLogoutResB;
import pnc.mesadmin.entity.UserInfo;
import pnc.mesadmin.service.UserIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PNC on 2017/5/10.
 */
@Controller
@Scope("prototype")
@RequestMapping("/User")
public class UserController {

    @Resource
    private UserIService userIService;

    @RequestMapping(value = "/UserPG")
    public String userinfo(){
        return "sys/user/userinfo";
    }

    @RequestMapping(value = "/RePWD/{UserRd}")
    public String RePWD(HttpServletRequest request, @PathVariable("UserRd") String UserRd){
        request.setAttribute("UserRd",UserRd);
        return "sys/user/resetPwd";
    }

    @RequestMapping(value = "/AddUserRole")
    public String userroleinfo(){
        return "sys/user/addUserRole";
    }

    @RequestMapping(value = "/changePwd")
    public String changePwd(){
        return "changepwd";
    }

    //登录
    @ResponseBody
    @RequestMapping(value = "/UserLoginInfo", method = RequestMethod.POST)
    public UserLoginInfoRes UserLoginInfo(HttpServletRequest request, UserInfo userInfo/*, HttpSession httpSession*/){

        UserLoginInfoRes objEUserLoginInfoRes = new UserLoginInfoRes();

        UserLoginInfoResB objEUserLoginInfoResB = new UserLoginInfoResB();

        UserLoginInfoResBD objEUserLoginInfoResBD = new UserLoginInfoResBD();

        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassword());

        //Subject subject= SecurityUtils.getSubject();
        try{
            //subject.login(token);
            //UserInfo objEUserInfo = (UserInfo) subject.getSession().getAttribute("user");
            objEUserLoginInfoResBD = userIService.QALLUserLogin(userInfo);
            objEUserLoginInfoResB.setData(objEUserLoginInfoResBD);
            objEUserLoginInfoResB.setMsgCode("0x00000");
            objEUserLoginInfoResB.setMsgDes("登录成功!");
            objEUserLoginInfoRes.setBody(objEUserLoginInfoResB);
        }catch(UnknownAccountException uae){
            objEUserLoginInfoResB.setData(objEUserLoginInfoResBD);
            objEUserLoginInfoResB.setMsgCode("MG000001");
            objEUserLoginInfoResB.setMsgDes("用户名不存在!");
            objEUserLoginInfoRes.setBody(objEUserLoginInfoResB);
        }catch (IncorrectCredentialsException ice) {
            objEUserLoginInfoResB.setData(objEUserLoginInfoResBD);
            objEUserLoginInfoResB.setMsgCode("MG000001");
            objEUserLoginInfoResB.setMsgDes("密码错误!");
            objEUserLoginInfoRes.setBody(objEUserLoginInfoResB);
        }catch ( ExcessiveAttemptsException eae ) {
            //尝试认证次数多余系统指定次数 ...
            objEUserLoginInfoResB.setData(objEUserLoginInfoResBD);
            objEUserLoginInfoResB.setMsgCode("MG000001");
            objEUserLoginInfoResB.setMsgDes("请求次数过多，用户被锁定!");
            objEUserLoginInfoRes.setBody(objEUserLoginInfoResB);
        }
        objEUserLoginInfoRes.setStatus("00");
        return objEUserLoginInfoRes;
    }

    @RequestMapping(value = "/ChangePwdPG", method = RequestMethod.GET)
    public String ChangePwdInfo(){
        return "changepwd";
    }
    // 修改密码
    @RequestMapping(value = "/ChangePwdInfo", method = RequestMethod.POST)
    @ResponseBody
    public ChangePwdInfoRes ChangePwdInfo(Integer UserRd, String UserName, String OldPassword, String NewPassword){

        ChangePwdInfoRes objEChangePwdInfoRes = new ChangePwdInfoRes();

        try {
            objEChangePwdInfoRes = userIService.updateUserPwd(UserRd, OldPassword, NewPassword);
            objEChangePwdInfoRes.getBody().setMsgCode("0x00000");
            objEChangePwdInfoRes.getBody().setMsgDes("密码修改成功");
        }catch (SystemException e){
            ChangePwdInfoResBody objEChangePwdInfoRB = new ChangePwdInfoResBody();
            objEChangePwdInfoRB.setMsgCode(e.getMsgCode());
            objEChangePwdInfoRB.setMsgDes(e.getMsgDes());
            objEChangePwdInfoRes.setBody(objEChangePwdInfoRB);
        }

        objEChangePwdInfoRes.setStatus("00");

        return objEChangePwdInfoRes;
    }

    // 注销
    @ResponseBody
    @RequestMapping(value="/LoginoutInfo", method=RequestMethod.GET)
    public String LoginoutInfoRequest(){
        UserLogoutRes objEUserLogoutRes = new UserLogoutRes();
        UserLogoutResB objEUserLogoutResB = new UserLogoutResB();

//        SecurityUtils.getSubject().logout();

        objEUserLogoutResB.setMsgDes("MG000001");
        objEUserLogoutResB.setMsgCode("注销成功!");
        objEUserLogoutRes.setStatus("00");
        objEUserLogoutRes.setBody(objEUserLogoutResB);

        return JSON.toJSONString(objEUserLogoutRes);
    }


    //点击出现页面
    @RequestMapping("/query")
    public  String getQyery(){
        return "sys/user/user";
    }


    //dto查询用户列表信息 张亮亮
    @RequestMapping(value = "/GetAllUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllUserInfoRes  GetAllUserInfo(GetAllReq getAllReq){
        GetAllUserInfoRes objEGetAllUserInfoRes =new GetAllUserInfoRes();
        if("00".equals(getAllReq.getExecType())){
            List<InitDataField> objEInitDataFields = null;
            PageInfo pageInfo = null;

            if (getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if (objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }
                try{
                    objEGetAllUserInfoRes= userIService.QALLGetAllUserInfoRes(objEInitDataFields, pageInfo);
                    objEGetAllUserInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllUserInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetAllUserInfoResB objEGetAllUserInfoRB = new GetAllUserInfoResB();
                    objEGetAllUserInfoRB.setMsgCode(e.getMsgCode());
                    objEGetAllUserInfoRB.setMsgDes(e.getMsgDes());
                    objEGetAllUserInfoRes.setBody(objEGetAllUserInfoRB);
                }

        }
        else{
            GetAllUserInfoResB objEGetAllUserInfoRB = new GetAllUserInfoResB();
            objEGetAllUserInfoRB.setMsgCode("MG0006F");
            objEGetAllUserInfoRB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllUserInfoRes.setBody(objEGetAllUserInfoRB);
        }
        objEGetAllUserInfoRes.setStatus("00");
        return objEGetAllUserInfoRes;

    }

    //根据id查询用户信息 张亮亮
    @RequestMapping(value = "/GetUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetUserInfoRes GetUserInfo(GetAllReq getAllReq){
        GetUserInfoRes getUserInfoRes=new GetUserInfoRes();
        if("00".equals(getAllReq.getExecType())){
            GetUserInfoReqBD00 objEGetUserInfoReqBD00 = CommonUtils.switchClass(GetUserInfoReqBD00.class,getAllReq.getBusData());
            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    getUserInfoRes = userIService.GetGetUserInfoRes(objEGetUserInfoReqBD00);
                    getUserInfoRes.getBody().setMsgCode("0x00000");
                    getUserInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetUserInfoResB objEGetUserInfoResBody = new GetUserInfoResB();
                    objEGetUserInfoResBody.setMsgCode(e.getMsgCode());
                    objEGetUserInfoResBody.setMsgDes(e.getMsgDes());
                    getUserInfoRes.setBody(objEGetUserInfoResBody);
                }
            }
        }else {
            GetUserInfoResB objEGetUserInfoResB = new GetUserInfoResB();
            objEGetUserInfoResB.setMsgCode("MG0006F");
            objEGetUserInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            getUserInfoRes.setBody(objEGetUserInfoResB);
        }
        getUserInfoRes.setStatus("00");
        return getUserInfoRes;
    }

 //查询用户角色信息
 @RequestMapping(value ="/GetAllURInfo",method = RequestMethod.POST)
 @ResponseBody
 public GetAllURInfoRes GetAllURInfo(HttpServletRequest request, GetAllReq getAllReq){
     GetAllURInfoRes objEGetAllURInfoRes=new GetAllURInfoRes();

     if("00".equals(getAllReq.getExecType())) {
         List<InitDataField> objEInitDataFields = null;
         PageInfo pageInfo = null;

         if (getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())) {
             InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

             if (objEInitData != null) {
                 objEInitDataFields = objEInitData.getFiledList();
             }
         }

         if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
             pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
         }
         GetAllURInfoReqBD00 objEGetAllURInfoReqBD00= CommonUtils.switchClass(GetAllURInfoReqBD00.class,getAllReq.getBusData());
         try {
             objEGetAllURInfoRes = userIService.QALLGetAllURInfoRes(objEInitDataFields, pageInfo,objEGetAllURInfoReqBD00);
             objEGetAllURInfoRes.getBody().setMsgCode("0x00000");
             objEGetAllURInfoRes.getBody().setMsgDes("成功");
         } catch (SystemException e) {
             GetAllURInfoResB objEGetAllURInfoResB = new GetAllURInfoResB();
             objEGetAllURInfoResB.setMsgCode(e.getMsgCode());
             objEGetAllURInfoResB.setMsgDes(e.getMsgDes());
             objEGetAllURInfoRes.setBody(objEGetAllURInfoResB);
         }
     }else{
         GetAllURInfoResB objEGetAllURInfoResB = new GetAllURInfoResB();
         objEGetAllURInfoResB.setMsgCode("MG0006F");
         objEGetAllURInfoResB.setMsgDes("参数名ExecType中值应该等于00");
         objEGetAllURInfoRes.setBody(objEGetAllURInfoResB);
     }

     objEGetAllURInfoRes.setStatus("00");
     return objEGetAllURInfoRes;
 }



    //dto 保存
    @ResponseBody
    @RequestMapping(value = "/SaveUserInfo",method = RequestMethod.POST)
    public SaveUserInfoRes SaveUserInfoRe(SaveReq saveReq){
        SaveUserInfoRes saveUserInfoRes=new SaveUserInfoRes();
        if("00".equals(saveReq.getExecType())){

            SaveUserInfoReqBD00 objESaveUserInfoReqBD00 = CommonUtils.switchClass(SaveUserInfoReqBD00.class,saveReq.getBusData());

            try{
                saveUserInfoRes = userIService.AddSaveUserInfoRes(objESaveUserInfoReqBD00);
                SaveUserInfoResB objESaveUserInfoResB=new SaveUserInfoResB();
                objESaveUserInfoResB.setMsgCode("0x00000");
                objESaveUserInfoResB.setMsgDes("成功");
                saveUserInfoRes.setBody(objESaveUserInfoResB);
            }catch (SystemException e){
                SaveUserInfoResB objESaveUserInfoResB = new SaveUserInfoResB();
                objESaveUserInfoResB.setMsgCode(e.getMsgCode());
                objESaveUserInfoResB.setMsgDes(e.getMsgDes());
                saveUserInfoRes.setBody(objESaveUserInfoResB);
            }

        }else if("01".equals(saveReq.getExecType())){
            SaveUserInfoReqBD01 objESaveUserInfoReqBD01  = CommonUtils.switchClass(SaveUserInfoReqBD01.class,saveReq.getBusData());
            try{
                saveUserInfoRes = userIService.RmUserInfo(objESaveUserInfoReqBD01);
                saveUserInfoRes.getBody().setMsgCode("0x00000");
                saveUserInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                SaveUserInfoResB objESaveUserInfoResB = new SaveUserInfoResB();
                objESaveUserInfoResB.setMsgCode(e.getMsgCode());
                objESaveUserInfoResB.setMsgDes(e.getMsgDes());
                saveUserInfoRes.setBody(objESaveUserInfoResB);
            }
        }else if("02".equals(saveReq.getExecType())) {
           /* Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("roleInfo", SaveUserInfoReqBD02Role.class);*/
            SaveUserInfoReqBD02 busData00 = CommonUtils.switchClass(SaveUserInfoReqBD02.class,saveReq.getBusData());
            try{
                saveUserInfoRes = userIService.ModSaveUserInfoRes(busData00);
                saveUserInfoRes.getBody().setMsgCode("0x00000");
                saveUserInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                SaveUserInfoResB objESaveRoleInfoRB = new SaveUserInfoResB();
                objESaveRoleInfoRB.setMsgCode(e.getMsgCode());
                objESaveRoleInfoRB.setMsgDes(e.getMsgDes());
                saveUserInfoRes.setBody(objESaveRoleInfoRB);
            }
        }else if("03".equals(saveReq.getExecType())) {

            SaveUserInfoReqBD03 busData03 = CommonUtils.switchClass(SaveUserInfoReqBD03.class,saveReq.getBusData());

            try{
                saveUserInfoRes = userIService.ModSaveUserInfoRes(busData03);
                saveUserInfoRes.getBody().setMsgCode("0x00000");
                saveUserInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                SaveUserInfoResB objESaveRoleInfoRB = new SaveUserInfoResB();
                objESaveRoleInfoRB.setMsgCode(e.getMsgCode());
                objESaveRoleInfoRB.setMsgDes(e.getMsgDes());
                saveUserInfoRes.setBody(objESaveRoleInfoRB);
            }
        }

        else if("04".equals(saveReq.getExecType())) {

            SaveUserInfoReqBD04 busData04 = CommonUtils.switchClass(SaveUserInfoReqBD04.class,saveReq.getBusData());

            try{
                saveUserInfoRes = userIService.AddSaveUserInfoReqBD04(busData04);
                saveUserInfoRes.getBody().setMsgCode("0x00000");
                saveUserInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                SaveUserInfoResB objESaveRoleInfoRB = new SaveUserInfoResB();
                objESaveRoleInfoRB.setMsgCode(e.getMsgCode());
                objESaveRoleInfoRB.setMsgDes(e.getMsgDes());
                saveUserInfoRes.setBody(objESaveRoleInfoRB);
            }
        }
        else {
            SaveUserInfoResB objESaveRoleInfoRB = new SaveUserInfoResB();
            objESaveRoleInfoRB.setMsgCode("MG0006F");
            objESaveRoleInfoRB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            saveUserInfoRes.setBody(objESaveRoleInfoRB);
        }
        saveUserInfoRes.setStatus("00");
        return saveUserInfoRes;
    }
}
