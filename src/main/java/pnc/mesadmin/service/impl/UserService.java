package pnc.mesadmin.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ChangePwdInfo.ChangePwdInfoRes;
import pnc.mesadmin.dto.ChangePwdInfo.ChangePwdInfoResBody;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoReqBD00;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoRes;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoResB;
import pnc.mesadmin.dto.GetAllURInfo.GetAllURInfoResD;
import pnc.mesadmin.dto.GetAllUserInfo.GetAllUserInfoRes;
import pnc.mesadmin.dto.GetAllUserInfo.GetAllUserInfoResB;
import pnc.mesadmin.dto.GetAllUserInfo.GetAllUserInfoResD;
import pnc.mesadmin.dto.GetUserInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveUserInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResBD;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResDLOEMLine;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResDLine;
import pnc.mesadmin.dto.UserLoginInfo.UserLoginInfoResDShift;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ShiftIService;
import pnc.mesadmin.service.UserIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MD5Util;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户列表信息service实现层
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class UserService implements UserIService {


    @Resource
    private UserDAO userDAO;

    @Resource
    private UserRoleDAO userRoleDAO;

    @Resource
    private RoleDAO roleDAO;

    @Resource
    private ShiftDAO shiftDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private ShiftIService shiftIService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private StationDao stationDao;

    @Resource
    private LineDao lineDao;

    @Resource
    private OEMLineDAO oemLineDAO;

    public ChangePwdInfoRes updateUserPwd(int ruid, String oldPassword, String newPassword) throws SystemException{

        ChangePwdInfoRes objEChangePwdInfoRes = new ChangePwdInfoRes();
        ChangePwdInfoResBody objEChangePwdInfoRB = new ChangePwdInfoResBody();

        if(ruid == 0){
            if(redisTemplate.hasKey("sysPwd")){
                if (!MD5Util.md5(oldPassword).equals((String) redisTemplate.boundValueOps("sysPwd").get())) {
                    throw new SystemException("MG000001", "旧密码输入错误");
                }
            }else{
                if (!"1".equals(oldPassword)) {
                    throw new SystemException("MG000001", "旧密码输入错误");
                }
            }
            redisTemplate.opsForValue().set("sysPwd", MD5Util.md5(newPassword));
        }else {
            UserInfo userInfo = userDAO.SelectUserInfoByruid(ruid);

            if (userInfo == null) {
                throw new SystemException("MG000001", "该用户不存在");
            }

            if (!MD5Util.md5(oldPassword).equals(userInfo.getPassword())) {
                throw new SystemException("MG000001", "旧密码输入错误");
            }

            if (userDAO.UpdateUserPwd(userInfo.getGuid(), MD5Util.md5(newPassword)) <= 0) {
                throw new SystemException("MG000001", "密码修改失败");
            }
        }

        objEChangePwdInfoRes.setBody(objEChangePwdInfoRB);

        return objEChangePwdInfoRes;
    }

    public GetAllUserInfoRes QALLGetAllUserInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        //dto实体类
        GetAllUserInfoRes objEGetAllUserInfoRes = new GetAllUserInfoRes();
        GetAllUserInfoResB objEGetAllUserInfoRB = new GetAllUserInfoResB();
        List<GetAllUserInfoResD> objEGetAllUserInfoResDs = new ArrayList<GetAllUserInfoResD>();

        //查询数据库中所有的用户
        List<UserInfo> objEUserInfos= baseIService.QALLBaseInfo(UserDAO.class, "SelectAllUserInfo",
                argInitDataFields, argPageInfo);
        //判断有木有用户数据
        if(objEUserInfos!=null&&objEUserInfos.size()>=1){

            //对用户列表信息遍历
            for(UserInfo userInfo:objEUserInfos){
                GetAllUserInfoResD getAllUserInfoResDate=new GetAllUserInfoResD();
                getAllUserInfoResDate.setUserRd(userInfo.getRuid());
                getAllUserInfoResDate.setUserName(userInfo.getUserName());
                getAllUserInfoResDate.setRealName(userInfo.getRealName());
                objEGetAllUserInfoResDs.add(getAllUserInfoResDate);
            }
        }
        objEGetAllUserInfoRB.setData(objEGetAllUserInfoResDs);
        objEGetAllUserInfoRes.setBody(objEGetAllUserInfoRB);
        return objEGetAllUserInfoRes;
    }

    //dto根据userid 查询用户信息
    public GetUserInfoRes GetGetUserInfoRes(GetUserInfoReqBD00 argGetUserInfoReqBD00) {
        GetUserInfoRes objEGetUserInfoRes=new GetUserInfoRes();
        GetUserInfoResB objEGetUserInfoResBD=new GetUserInfoResB();
        GetUserInfoResD objEGetUserInfoRBDT=new GetUserInfoResD();
        List<GetUserInfoResBDRole>  getUserInfoResRoleInfoDBList=new ArrayList<GetUserInfoResBDRole>();
        //查询用户信息
        UserInfo uerInfo= userDAO.SelectUserInfoByruid(argGetUserInfoReqBD00.getUserRd());
        if(uerInfo==null){
            throw new SystemException("MG_MES1001111010001F", "该用户不存在");
        }
        objEGetUserInfoRBDT.setUserRd(uerInfo.getRuid());
        objEGetUserInfoRBDT.setUserName(uerInfo.getUserName());
        objEGetUserInfoRBDT.setRealName(uerInfo.getRealName());
        objEGetUserInfoRBDT.setCreateTime(DateUtil.format(uerInfo.getCreateTime()));
        objEGetUserInfoRBDT.setCreator(uerInfo.getCreator());
        objEGetUserInfoRBDT.setLastModifyMan(uerInfo.getLastModifyMan());
        objEGetUserInfoRBDT.setLastModifyTime(DateUtil.format(uerInfo.getLastModifyTime()));
        objEGetUserInfoRBDT.setRemark(uerInfo.getRemark());
        objEGetUserInfoRBDT.setMobileNo(uerInfo.getMobileNo());
        objEGetUserInfoRBDT.setEmailAddress(uerInfo.getEmailAddress());
        //根据用户的标识，到关联表查询信息。 此查询是为了查询属于该下面的角色标识
        List<UserRoleInfo> objEUserRoleInfos=userRoleDAO.SelectUserRoleInfoByuserGd(uerInfo.getGuid());
        if(objEUserRoleInfos!=null){
            //根据用户角色里面的角色白标识到角色表查询信息
            for (UserRoleInfo obj:objEUserRoleInfos){

                List<RoleInfo> objERoleInfos=roleDAO.SelectRoleInfoByguid(obj.getRoleGd());

                for(RoleInfo objE:objERoleInfos){
                    GetUserInfoResBDRole getUserInfoResRoleInfo=new GetUserInfoResBDRole();
                    getUserInfoResRoleInfo.setRoleRd(objE.getRuid());
                    getUserInfoResRoleInfo.setRoleName(objE.getRoleName());
                    //查询标识是否勾选
                    UserRoleInfo getUseridAndRoleid= userRoleDAO.SelectUseridAndRolridInfo(uerInfo.getGuid(),objE.getGuid());

                    getUserInfoResRoleInfoDBList.add(getUserInfoResRoleInfo);
                }
            }
        }
        GetUserInfoResBDShift getUserInfoResBDShift=new GetUserInfoResBDShift();
        ShiftInfo shiftInfo=shiftDAO.SelectByGuid(uerInfo.getShiftGd());
        if(shiftInfo!=null){
            getUserInfoResBDShift.setShiftRd(shiftInfo.getRuid());
            getUserInfoResBDShift.setShiftName(shiftInfo.getShiftName());

        }
        objEGetUserInfoRBDT.setShiftInfo(getUserInfoResBDShift);
        objEGetUserInfoRBDT.setRoleInfo(getUserInfoResRoleInfoDBList);
        objEGetUserInfoResBD.setData(objEGetUserInfoRBDT);
        objEGetUserInfoRes.setBody(objEGetUserInfoResBD);
        return objEGetUserInfoRes;
    }


    //dto新增
    public SaveUserInfoRes AddSaveUserInfoRes(SaveUserInfoReqBD00 argSaveUserInfoReqBD00) {
        //dto实体类
        SaveUserInfoRes objESaveUserInfoRes=new SaveUserInfoRes();
        SaveUserInfoResB objESaveUserInfoResB=new SaveUserInfoResB();
        SaveUserInfoResD objESaveUserInfoResD=new SaveUserInfoResD();

        UserInfo objEUserInfo=new UserInfo();
        objEUserInfo.setGuid(CommonUtils.getRandomNumber());
        //修改的名字不允许重复
        UserInfo userInfoname=userDAO.SelectUser(argSaveUserInfoReqBD00.getUserName());
        if("admin".equals(argSaveUserInfoReqBD00.getUserName())){
            throw new SystemException("MG_MES1001112020005F","新增失败，用户名admin为内置管理员账号，请更换成其他用户名");
        }
        if(userInfoname!=null&&!userInfoname.getUserName().equals(objEUserInfo.getUserName())){
            throw new SystemException("MG_MES1001112020003F","新增失败，用户名已存在");
        }
        if(argSaveUserInfoReqBD00.getEmailAddress()!=null&&!"".equals(argSaveUserInfoReqBD00.getEmailAddress())){
            if(!Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(argSaveUserInfoReqBD00.getEmailAddress()).matches()){
                throw new SystemException("MG_MES1001112020003F","新增失败，请输入正确的邮箱格式");
            }
            objEUserInfo.setEmailAddress(argSaveUserInfoReqBD00.getEmailAddress());
        }
        if(argSaveUserInfoReqBD00.getMobileNo()!=null&&!"".equals(argSaveUserInfoReqBD00.getMobileNo())){
            if(!Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(argSaveUserInfoReqBD00.getMobileNo()).matches()){
                throw new SystemException("MG_MES1001112020003F","新增失败，手机号输入有误");
            }
            objEUserInfo.setMobileNo(argSaveUserInfoReqBD00.getMobileNo());
        }

        ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(argSaveUserInfoReqBD00.getShiftRd());
        if(shiftInfo!=null){
            objEUserInfo.setShiftGd(shiftInfo.getGuid());
        }

        objEUserInfo.setUserName(argSaveUserInfoReqBD00.getUserName());
        objEUserInfo.setRealName(argSaveUserInfoReqBD00.getRealName());
        objEUserInfo.setRemark(argSaveUserInfoReqBD00.getRemark());
        objEUserInfo.setPassword(MD5Util.md5(argSaveUserInfoReqBD00.getPassword(),"pnc"));
        objEUserInfo.setCreateTime(new Date());
        objEUserInfo.setCreator(CommonUtils.readUser().getRealName());
        userDAO.InsertUserInfo(objEUserInfo);
        for(SaveUserInfoReqBD00Role obj:argSaveUserInfoReqBD00.getRoleInfo()){
            UserRoleInfo userInfos=new UserRoleInfo();
            userInfos.setGuid(CommonUtils.getRandomNumber());
            //设置该角色属于该用户下
            userInfos.setUserGd(objEUserInfo.getGuid());
            RoleInfo objERoleInfo = roleDAO.SelectRoleInfoByruid(obj.getRoleRd());
            if(objERoleInfo == null){
                throw new SystemException("MG_MES1001112010003F","查询角色失败");
            }
            userInfos.setRoleGd(objERoleInfo.getGuid());

            userInfos.setCreator(CommonUtils.readUser().getRealName());
            userInfos.setCreateTime(new Date());
            userRoleDAO.InsertUserRoleInfo(userInfos);
        }
        objESaveUserInfoResB.setData(objESaveUserInfoResD);
        objESaveUserInfoRes.setBody(objESaveUserInfoResB);
        return objESaveUserInfoRes;
    }

    //dto删除
    public SaveUserInfoRes RmUserInfo(SaveUserInfoReqBD01 argSaveUserInfoReqBD01) {
        SaveUserInfoRes objESaveUserInfoRes=new SaveUserInfoRes();
        SaveUserInfoResB objESaveUserInfoResB=new SaveUserInfoResB();

        //查询用户信息
        UserInfo userInfo= userDAO.SelectUserInfoByruid(argSaveUserInfoReqBD01.getUserRd());

        if(userInfo == null){
            throw new SystemException("MG_MES1001112020001", "该用户不存在");
        }

        if("admin".equals(userInfo.getUserName())){
            throw new SystemException("", "管理员账户不允许删除");
        }

        List<UserRoleInfo> objEUserRoleInfos=userRoleDAO.SelectUserRoleInfoByuserGd(userInfo.getGuid());

        if(objEUserRoleInfos!=null||objEUserRoleInfos.size()!=0){
          //  throw new SystemException("MG_MES1001112020002", "查询用户角色失败");
            for(UserRoleInfo obj:objEUserRoleInfos){
                if(userRoleDAO.DeleteUserRoleByruid(obj.getRuid(),obj.getUserGd())<=0){
                    throw new SystemException("MG_MES1001112020003", "删除用户角色失败");
                }
            }

            if(userDAO.DeleteUserInfo(argSaveUserInfoReqBD01.getUserRd()) <= 0){
                throw new SystemException("MG_MES1001112020004", "删除用户失败");
            }

        }




        objESaveUserInfoRes.setBody(objESaveUserInfoResB);

        return objESaveUserInfoRes;
    }
    //tdo更新
    public SaveUserInfoRes ModSaveUserInfoRes(SaveUserInfoReqBD02 argSaveUserInfoReqBD02) {
        SaveUserInfoRes objESaveUserInfoRes=new SaveUserInfoRes();
        SaveUserInfoResB objESaveUserInfoResB=new SaveUserInfoResB();

        //根据传过来的id先查询用户信息
        UserInfo objEUserInfo=userDAO.SelectUserInfoByruid(argSaveUserInfoReqBD02.getUserRd());
        if(objEUserInfo==null){
            throw new SystemException("MG_MES1001112020001F","查询用户失败");
        }
        if("admin".equals(argSaveUserInfoReqBD02.getUserName())){
            throw new SystemException("MG_MES1001112020005F","更新失败，用户名admin为内置管理员账号，请更换成其他用户名");
        }


        if(argSaveUserInfoReqBD02.getEmailAddress()!=null&&!"".equals(argSaveUserInfoReqBD02.getEmailAddress())){
            if(!Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(argSaveUserInfoReqBD02.getEmailAddress()).matches()){
                throw new SystemException("MG_MES1001112020003F","新增失败，请输入正确的邮箱格式");
            }
            objEUserInfo.setEmailAddress(argSaveUserInfoReqBD02.getEmailAddress());
        }
        if(argSaveUserInfoReqBD02.getMobileNo()!=null&&!"".equals(argSaveUserInfoReqBD02.getMobileNo())){
            if(!Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(argSaveUserInfoReqBD02.getMobileNo()).matches()){
                throw new SystemException("MG_MES1001112020003F","新增失败，手机号输入有误");
            }
            objEUserInfo.setMobileNo(argSaveUserInfoReqBD02.getMobileNo());
        }




        //修改的名字不允许重复
        UserInfo userInfoname=userDAO.SelectUser(argSaveUserInfoReqBD02.getUserName());
        if(userInfoname!=null&&!userInfoname.getUserName().equals(objEUserInfo.getUserName())){
            throw new SystemException("MG_MES1001112020003F","更新失败，用户名已存在");
        }


        ShiftInfo shiftInfo=shiftDAO.SelectShiftByID(argSaveUserInfoReqBD02.getShiftRd());
        if(shiftInfo!=null){
            objEUserInfo.setShiftGd(shiftInfo.getGuid());
        }else {
            objEUserInfo.setShiftGd("");
        }


        //根据用户标识到关联表查询所有属于该用户下的所有角色
        List<UserRoleInfo> objEUserRoleInfos=userRoleDAO.SelectUserRoleInfoByuserGd(objEUserInfo.getGuid());
        if(objEUserRoleInfos!=null){
          //  throw new SystemException("MG_MES1001112020005F", "查询用户下所属角色信息失败");
            //更新从页面传过来的所有信息 进行对比
            for(SaveUserInfoReqBD02Role objESaveUserInfoReqBD02Role : argSaveUserInfoReqBD02.getRoleInfo()){
                boolean flag = true;
                //遍历关联表
                for(int i=0;i<objEUserRoleInfos.size();i++){
                    if(objESaveUserInfoReqBD02Role.getRoleRd()==objEUserRoleInfos.get(i).getRuid()){
                        objEUserRoleInfos.remove(i);
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    UserRoleInfo userRoleInfo = new UserRoleInfo();
                    userRoleInfo.setGuid(CommonUtils.getRandomNumber());
                    userRoleInfo.setUserGd(objEUserInfo.getGuid());

                    RoleInfo roleInfo = roleDAO.SelectRoleInfoByruid(objESaveUserInfoReqBD02Role.getRoleRd());
                    if(roleInfo==null){
                        throw new SystemException("MG_MES1001112020007F", "查询角色信息失败");
                    }
                    userRoleInfo.setRoleGd(roleInfo.getGuid());
                    userRoleInfo.setCreator(CommonUtils.readUser().getRealName());
                    userRoleInfo.setCreateTime(new Date());
                    userRoleInfo.setRemark(argSaveUserInfoReqBD02.getRemark());
                    userRoleDAO.InsertUserRoleInfo(userRoleInfo);
                }
            }

            for(UserRoleInfo obj:objEUserRoleInfos){
                if(userRoleDAO.DeleteUserRoleByruid(obj.getRuid(),obj.getUserGd())<=0){
                    throw new SystemException("MG_MES1001112020003", "删除用户角色失败");
                }
            }




            //更新
            objEUserInfo.setRuid(argSaveUserInfoReqBD02.getUserRd());
            objEUserInfo.setRealName(argSaveUserInfoReqBD02.getRealName());
            objEUserInfo.setRemark(argSaveUserInfoReqBD02.getRemark());
            objEUserInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
            objEUserInfo.setLastModifyTime(new Date());
            objEUserInfo.setEmailAddress(argSaveUserInfoReqBD02.getEmailAddress());
            objEUserInfo.setMobileNo(argSaveUserInfoReqBD02.getMobileNo());
            objEUserInfo.setUserName(argSaveUserInfoReqBD02.getUserName());
            //修改用户信息
            if(userDAO.UpdateUserinfo(objEUserInfo)<=0){
                throw new SystemException("MG_MES1001112020004F", "更新用户信息失败");
            }
        }



        objESaveUserInfoRes.setBody(objESaveUserInfoResB);

        return objESaveUserInfoRes;
    }

    //dto重置密码
    public SaveUserInfoRes ModSaveUserInfoRes(SaveUserInfoReqBD03 argSaveUserInfoReqBD03) {
        SaveUserInfoRes objESaveUserInfoRes=new SaveUserInfoRes();
        SaveUserInfoResB objESaveUserInfoResB=new SaveUserInfoResB();
        int getUserRd=argSaveUserInfoReqBD03.getUserRd();

        if(userDAO.UpdataUserInfoPassword(getUserRd,MD5Util.md5(argSaveUserInfoReqBD03.getPassword(),"pnc"))<=0){
            throw new SystemException("MG_MES1001112030001F", "重置密码失败");
        }

        objESaveUserInfoRes.setBody(objESaveUserInfoResB);

        return objESaveUserInfoRes;
    }

    //tdo复制用户信息
    public SaveUserInfoRes AddSaveUserInfoReqBD04(SaveUserInfoReqBD04 argSaveUserInfoReqBD04) {
        SaveUserInfoRes objESaveUserInfoRes=new SaveUserInfoRes();
        SaveUserInfoResB objESaveUserInfoResB=new SaveUserInfoResB();
        //查出用户信息
        UserInfo objEUserInfo=userDAO.SelectUserInfoByruid(argSaveUserInfoReqBD04.getUserRd());
        if(objEUserInfo==null){
            throw new SystemException("xxx","查询用户信息失败");
        }
        List<UserRoleInfo> objEUserRoleInfos=userRoleDAO.SelectUserRoleInfoByuserGd(objEUserInfo.getGuid());
        objEUserInfo.setGuid(CommonUtils.getRandomNumber());
        if(objEUserRoleInfos!=null&&objEUserRoleInfos.size()>0){
            for( UserRoleInfo obj:objEUserRoleInfos){
                UserRoleInfo objEUserRoleInfo=new   UserRoleInfo();
                objEUserRoleInfo.setGuid(CommonUtils.getRandomNumber());
                objEUserRoleInfo.setUserGd(objEUserInfo.getGuid());
                objEUserRoleInfo.setRoleGd(obj.getRoleGd());
                objEUserRoleInfo.setCreator(CommonUtils.readUser().getRealName());
                objEUserRoleInfo.setCreateTime(new Date());
                objEUserRoleInfo.setRemark(obj.getRemark());
                userRoleDAO.InsertUserRoleInfo(objEUserRoleInfo);
            }
        }


        objEUserInfo.setUserName("XXX");//这边处理是因为数据库里做了唯一索引 所以这边先随便写个 测试发现的bug
        userDAO.InsertUserInfo(objEUserInfo);
        UserInfo objEUserInfoByGuid=userDAO.SelectUserRd(objEUserInfo.getGuid());
        objEUserInfoByGuid.setUserName(objEUserInfo.getUserName()+objEUserInfoByGuid.getRuid());
        if(userDAO.UpdateUserinfo(objEUserInfoByGuid)<=0){
            throw new SystemException("xxx","更新用户信息失败");
        }

        objESaveUserInfoRes.setBody(objESaveUserInfoResB);
        return objESaveUserInfoRes;
    }

    //查询用户角色信息
    @Override
    public GetAllURInfoRes QALLGetAllURInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo,GetAllURInfoReqBD00 argBD00) {
        GetAllURInfoRes objEGetAllURInfoRes= new GetAllURInfoRes();

        GetAllURInfoResB body = new GetAllURInfoResB();

        List<GetAllURInfoResD> dataList =  new ArrayList<GetAllURInfoResD>();

        // 获取用户信息列表信息
        List<UserInfo> userInfoList = baseIService.QALLBaseInfo(UserDAO.class, "SelectAllUserInfo",
                argInitDataFields, argPageInfo);
        GetAllURInfoResD data = null;
        if(userInfoList!=null || userInfoList.size()>0) {

            // 循环赋值
            for (int i = 0; i < userInfoList.size(); i++) {
                data = new GetAllURInfoResD();
                data.setPMorRd(userInfoList.get(i).getRuid());
                data.setPMor(userInfoList.get(i).getRealName());
                data.setPMorType("00");
                dataList.add(data);
            }
        }

        //查询角色信息
        List<RoleInfo> roleInfoList=roleDAO.SelectAllRoleInfo(argBD00.getRoleName());

        if(roleInfoList!=null && roleInfoList.size()>0){
            for (int i = 0; i < roleInfoList.size(); i++) {
                data = new GetAllURInfoResD();
                data.setPMorRd(roleInfoList.get(i).getRuid());
                data.setPMor(roleInfoList.get(i).getRoleName());
                data.setPMorType("01");
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllURInfoRes.setBody(body);

        return objEGetAllURInfoRes;
    }

    //登录
    @Override
    public UserLoginInfoResBD QALLUserLogin(UserInfo userInfo) throws SystemException {
        UserLoginInfoResBD objEUserLoginInfoResBD = new UserLoginInfoResBD();
        objEUserLoginInfoResBD.setUserRd(userInfo.getRuid());
        objEUserLoginInfoResBD.setUserName(userInfo.getUserName());
        objEUserLoginInfoResBD.setRealName(userInfo.getRealName());
        UserLoginInfoResDShift shift = new UserLoginInfoResDShift();
        ShiftInfo shiftInfo = shiftIService.GetShiftInfo();//shiftDAO.SelectByGuid(userInfo.getShiftGd());
        if(shiftInfo != null){
            shift.setShiftRd(shiftInfo.getRuid());
            shift.setShiftName(shiftInfo.getShiftName());
        }
        objEUserLoginInfoResBD.setShiftInfo(shift);

        UserLoginInfoResDLine userLoginInfoResDLine = new UserLoginInfoResDLine();
        List<StationInfo> stationInfos = stationDao.SelectByExecGdExecType(userInfo.getGuid(), "01");
        if(stationInfos.size() == 1){
            StationInfo station = stationInfos.get(0);
            //查询线体
            Lineinfo line = lineDao.SelectLineInfoByguid(station.getLineGd());
            if(line != null){
                userLoginInfoResDLine.setLineRd(line.getRuid());
                userLoginInfoResDLine.setLineName(line.getLineName());

                List<UserLoginInfoResDLOEMLine> dloemLines = new ArrayList<>();
                UserLoginInfoResDLOEMLine dloemLine = null;
                List<OEMLineinfo> oemLineinfos = oemLineDAO.SelectOEMLineinfoByGuid(line.getGuid());
                for(OEMLineinfo oemLineinfo : oemLineinfos){
                    Lineinfo oline = lineDao.SelectLineInfoByguid(oemLineinfo.getOEMLineGD());
                    if(oline != null) {
                        dloemLine = new UserLoginInfoResDLOEMLine();
                        dloemLine.setLineRd(oline.getRuid());
                        dloemLine.setLineName(oline.getLineName());
                        dloemLines.add(dloemLine);
                    }
                }
                userLoginInfoResDLine.setOEMLine(dloemLines);
            }
            objEUserLoginInfoResBD.setLineInfo(userLoginInfoResDLine);
        }

        return objEUserLoginInfoResBD;
    }

    /*
  public UserInfo getUser(String userName) {
        return userDAO.SelectUser(userName);
    }

    public UserInfo selectUserInfo(String ruid) {
        return userDAO.selectUserInfo(ruid);
    }

     public UserInfo selectUserInfoList(int ruid) {
        return userDAO.selectUserInfoList(ruid);
    }



   public int saveUserInfo(UserInfo userInfo) {
        return userDAO.saveUserInfo(userInfo);
    }


    public String selectUserName(String guid) {
        return userDAO.selectUserName(guid);
    }



    public UserInfo selectAll(String guid) {
        return userDAO.selectAll(guid);
    }

    public int updateUserinfo(UserInfo userInfo) {
        return userDAO.updateUserinfo(userInfo);
    }


    public int deleteGuid(List<String> guid) {
       int result=0;
        for(String guids : guid){
            result= userDAO.deleteGuid(1);
        }
        return result;
    }

//更新密码
    public int updataUserInfoPassword(int ruid,String passWord) {
        return userDAO.updataUserInfoPassword(ruid,passWord);
    }

    //查询用户列表信息 张亮亮
    public List<UserInfo> SelectAllUserInfo() {
        return userDAO.SelectAllUserInfo();
    }

*/






}
