package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.SkillDAO;
import pnc.mesadmin.dao.SkillLogDAO;
import pnc.mesadmin.dao.UserDAO;
import pnc.mesadmin.dao.UsrSkillDAO;
import pnc.mesadmin.dto.GetAllUrSGInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoReqBD02;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoRes;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoResB;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoResD;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SkillInfo;
import pnc.mesadmin.entity.SkillLogInfo;
import pnc.mesadmin.entity.UserInfo;
import pnc.mesadmin.entity.UsrSkillInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.UsrSkillIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：技能培训记录信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-8-28
 * 修改人：
 * 修改时间：
 */

@Transactional
@Service
public class UsrSkillService implements UsrSkillIService{

    @Resource
    private UsrSkillDAO usrSkillDAO;

    @Resource
    private SkillLogDAO skillLogDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private SkillDAO skillDAO;

    @Resource
    private BaseIService baseIService;

    //查询技能培训记录列表信息
    public GetAllUrSGInfoRes QALLGetAllUrSGInfo(GetAllUrSGInfoReqBD00 argBD00,List<InitDataField> argInitDataFields) throws SystemException {
        GetAllUrSGInfoRes objEGetAllUrSGInfoRes= new GetAllUrSGInfoRes();

        GetAllUrSGInfoResB body = new GetAllUrSGInfoResB();

        List<GetAllUrSGInfoResD> dataList =  new ArrayList<GetAllUrSGInfoResD>();

        if(argInitDataFields != null) {
            for (InitDataField field : argInitDataFields) {
                if("UserRd".equals(field.getFieldName())) {
                    //获取用户的gd
                    UserInfo objEUserInfo = userDAO.SelectUserInfoByruid(Integer.valueOf(field.getFieldVal()));
                    field.setFieldVal(objEUserInfo.getGuid());
                    field.setFieldName("UserGd");
                }
            }
        }


        // 获取人员技能信息列表信息
        List<UsrSkillInfo> usrSkillInfoList = baseIService.QALLBaseInfo(UsrSkillDAO.class,"SelectAllUsrSkillInfo",argInitDataFields,null);

        if(usrSkillInfoList.size()>0 || usrSkillInfoList!=null) {
          for(UsrSkillInfo obj:usrSkillInfoList){
              GetAllUrSGInfoResD objEGetAllUrSGInfoResD=new GetAllUrSGInfoResD();

              //查询人员信息
              UserInfo userInfo=userDAO.SelectUserRd(obj.getUserGd());

              //查询技能
              SkillInfo skillInfo=skillDAO.SelectSkillInfoByGuid(obj.getSkillGd());

              //查询技能培训记录信息
              List<SkillLogInfo> skillLogInfoList = skillLogDAO.SelectAllSkillLogInfo();

              if(userInfo!=null) {
                  objEGetAllUrSGInfoResD.setUserRd(userInfo.getRuid());
                  objEGetAllUrSGInfoResD.setRealName(userInfo.getRealName());
                  if(skillInfo!=null) {
                      objEGetAllUrSGInfoResD.setSkillRd(skillInfo.getRuid());
                      objEGetAllUrSGInfoResD.setSkillName(skillInfo.getSkillName());
                  }
              }
              SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
              objEGetAllUrSGInfoResD.setEVPDate(format.format((obj.geteVPDate())));
              objEGetAllUrSGInfoResD.setSVPDate(format.format((obj.getsVPDate())));
              //循环查询培训技能是否通过
              if(skillLogInfoList!=null && skillLogInfoList.size()>0) {
                  for (SkillLogInfo obj1 : skillLogInfoList) {
                      objEGetAllUrSGInfoResD.setIsPass(obj1.getIsPass());
                  }
              }
              dataList.add(objEGetAllUrSGInfoResD);
          }
        }


        body.setData(dataList);
        objEGetAllUrSGInfoRes.setBody(body);

        return objEGetAllUrSGInfoRes;
    }

    //更新技能培训记录信息
    public SaveUrSGInfoRes ModSaveUrSGInfoRes(SaveUrSGInfoReqBD02[] argSaveUrSGInfoReqBD02) {
        SaveUrSGInfoRes saveUrSGInfoRes = new SaveUrSGInfoRes();

        SaveUrSGInfoResB body = new SaveUrSGInfoResB();

        SaveUrSGInfoResD data = new SaveUrSGInfoResD();

        UsrSkillInfo usrSkillInfo=new UsrSkillInfo();

        SkillLogInfo skillLogInfo=new SkillLogInfo();

        // 获取人员技能信息列表信息
        List<UsrSkillInfo> usrSkillInfoList = usrSkillDAO.SelectAllUsrSkillInfo();

        //查询技能培训记录信息
        List<SkillLogInfo> skillLogInfoList = skillLogDAO.SelectAllSkillLogInfo();


        //删除人员技能表所有信息
        if(usrSkillInfoList!=null && usrSkillInfoList.size()>0) {
            for (UsrSkillInfo obj : usrSkillInfoList) {
                usrSkillDAO.DeleteUsrSkillInfo(obj.getRuid());
            }
        }

        //删除人员技能表所有信息
        if(skillLogInfoList!=null && skillLogInfoList.size()>0) {
            for (SkillLogInfo obj1 : skillLogInfoList) {
                skillLogDAO.DeleteSkillLogInfo(obj1.getRuid());
            }
        }

        //技能培训记录不能重复
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<argSaveUrSGInfoReqBD02.length;i++){
            set.add(argSaveUrSGInfoReqBD02[i].getSkillRd());
        }

        if(set.size() < argSaveUrSGInfoReqBD02.length){
            throw new SystemException("MG_MES2001012010001F","培训技能重复,不能保存");
        }


        for(SaveUrSGInfoReqBD02 obj:argSaveUrSGInfoReqBD02) {

            // 获取人员信息
            UserInfo objEUserInfo =userDAO.SelectUserInfoByruid(obj.getUserRd());

            //查询技能信息
            SkillInfo objESkillInfo=skillDAO.SelectSkillInfoByRuid(obj.getSkillRd());

            //新增人员技能信息
            usrSkillInfo.setGuid(CommonUtils.getRandomNumber());
            if (objEUserInfo != null) {
                usrSkillInfo.setUserGd(objEUserInfo.getGuid());
                usrSkillInfo.setRealName(objEUserInfo.getRealName());
            }
            if (objESkillInfo != null) {
                usrSkillInfo.setSkillGd(objESkillInfo.getGuid());
                usrSkillInfo.setSkillName(objESkillInfo.getSkillName());
            }
            Date date2=new Date();
            //字符串转日期
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = format.format(date2);
            Date date = null;
            Date date1 = null;
            Date date3=null;
            try {
                date = format.parse(obj.getSVPDate());
                usrSkillInfo.setsVPDate(date);
                date1 = format.parse(obj.getEVPDate());
                date3=format.parse(dateNowStr);
                if(date1.getTime()-date3.getTime()<0){
                    throw new SystemException("MG_MES2001012010001F","培训技能已过期,请选择开始日期或删除,再保存!");
                }
                usrSkillInfo.seteVPDate(date1);

            } catch (Exception e) {
                throw new SystemException("MG_MES2001012010001F","培训技能已过期,请选择开始日期或删除,再保存!");
            }
            if(obj.getIsPass().equals("01")){
                throw new SystemException("MG_MES2001012010001F","培训技能没通过,请勾选通过再保存");
            }

            usrSkillInfo.setCreator(CommonUtils.readUser().getRealName());
            usrSkillInfo.setCreateTime(new Date());

            usrSkillDAO.InsertUsrSkillInfo(usrSkillInfo);

            //新增技能培训信息
            skillLogInfo.setGuid(CommonUtils.getRandomNumber());
            if(objESkillInfo!=null) {
                skillLogInfo.setUserGd(objEUserInfo.getGuid());
                skillLogInfo.setRealName(objEUserInfo.getRealName());
            }
            if(objEUserInfo!=null) {
                skillLogInfo.setSkillGd(objESkillInfo.getGuid());
                skillLogInfo.setSkillName(objESkillInfo.getSkillName());
            }
            skillLogInfo.setIsPass(obj.getIsPass());
            skillLogInfo.setCreator(CommonUtils.readUser().getRealName());
            skillLogInfo.setCreateTime(new Date());
            skillLogDAO.InsertSkillLogInfo(skillLogInfo);
        }

        body.setData(data);
        saveUrSGInfoRes.setBody(body);

        return saveUrSGInfoRes;
    }
}
