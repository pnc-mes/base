package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.SkillDAO;
import pnc.mesadmin.dto.GetAllSkillInfo.GetAllSkillInfoResB;
import pnc.mesadmin.dto.GetAllSkillInfo.GetAllSkillInfoResD;
import pnc.mesadmin.dto.GetSkillInfo.GetSkillInfoResB;
import pnc.mesadmin.dto.GetSkillInfo.GetSkillInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoReq00;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoReq01;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoReq02;
import pnc.mesadmin.dto.SaveSkillInfo.SaveSkillInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SkillInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.SkillIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by test on 2017/8/16.
 */
@Transactional
@Service
public class SkillService implements SkillIService {
    @Resource
    private SkillDAO skillDAO;

    @Resource
    private BaseIService baseIService;
    /* 查询所有技能信息 */
    public GetAllSkillInfoResB GetAllSkillInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllSkillInfoResB objGetAllSkillInfoResB = new GetAllSkillInfoResB();
        List<GetAllSkillInfoResD> objGetAllSkillInfoResDList = new ArrayList<GetAllSkillInfoResD>(Collections.EMPTY_LIST);
        GetAllSkillInfoResD objGetAllSkillInfoResD = null;
        List<SkillInfo> skillInfoList =baseIService.QALLBaseInfo(SkillDAO.class, "SelectAllSkillInfo",
                argInitDataFields, argPageInfo);

               // skillDAO.SelectAllSkillInfo();
        if(skillInfoList != null && skillInfoList.size() > 0){
            for (SkillInfo skillInfo:skillInfoList) {
                objGetAllSkillInfoResD = new GetAllSkillInfoResD();
                objGetAllSkillInfoResD.setSkillRd(skillInfo.getRuid());
                objGetAllSkillInfoResD.setSkillName(skillInfo.getSkillName());
                objGetAllSkillInfoResDList.add(objGetAllSkillInfoResD);
            }
        }
        objGetAllSkillInfoResB.setData(objGetAllSkillInfoResDList);
        objGetAllSkillInfoResB.setMsgCode("0x00000");
        objGetAllSkillInfoResB.setMsgDes("成功");
        return objGetAllSkillInfoResB;
    }

    public GetSkillInfoResB GetSkillInfoByRuid(int argRuid) {
        GetSkillInfoResB objGetSkillInfoResB = new GetSkillInfoResB();
        GetSkillInfoResD objGetSkillInfoResD = new GetSkillInfoResD();;

        SkillInfo skillInfo = skillDAO.SelectSkillInfoByRuid(argRuid);
        if(skillInfo != null){
            objGetSkillInfoResD.setSkillRd(skillInfo.getRuid());
            objGetSkillInfoResD.setSkillName(skillInfo.getSkillName());
            objGetSkillInfoResD.setVPDate(skillInfo.getVPDate());
            objGetSkillInfoResD.setStatus(skillInfo.getStatus());
            objGetSkillInfoResD.setCreator(skillInfo.getCreator());
            objGetSkillInfoResD.setCreateTime(DateUtil.format(skillInfo.getCreateTime()));
            objGetSkillInfoResD.setLastModifyMan(skillInfo.getLastModifyMan());
            objGetSkillInfoResD.setLastModifyTime(DateUtil.format(skillInfo.getLastModifyTime()));
            objGetSkillInfoResD.setRemark(skillInfo.getRemark());
        }
        objGetSkillInfoResB.setData(objGetSkillInfoResD);
        objGetSkillInfoResB.setMsgCode("0x00000");
        objGetSkillInfoResB.setMsgDes("成功");
        return objGetSkillInfoResB;
    }

    // 修改
    public SaveSkillInfoResB ModSkillInfo(SaveSkillInfoReq02 busData02) {
        SaveSkillInfoResB objSaveSkillInfoResB = new SaveSkillInfoResB();
        SkillInfo skill = new SkillInfo();
        skill.setSkillName(busData02.getSkillName());
        skill.setRuid(busData02.getSkillRd());
        int count = skillDAO.SelectCountByName(skill);
        if(count > 0)
            throw new SystemException("0x00001","该名称已存在");
        SkillInfo skillInfo = skillDAO.SelectSkillInfoByRuid(busData02.getSkillRd());
        skillInfo.setRuid(busData02.getSkillRd());
        skillInfo.setSkillName(busData02.getSkillName());
        skillInfo.setVPDate(busData02.getVPDate());
        skillInfo.setStatus(busData02.getStatus());
        skillInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        skillInfo.setLastModifyTime(new Date());
        skillInfo.setRemark(busData02.getRemark());
        if(skillDAO.UpdateSkillInfo(skillInfo) <=0){
            throw new SystemException("0x00001","修改失败");
        }
        objSaveSkillInfoResB.setMsgCode("0x00000");
        objSaveSkillInfoResB.setMsgDes("成功");
        return objSaveSkillInfoResB;
    }

    // 删除
    public SaveSkillInfoResB RmSkillInfo(SaveSkillInfoReq01 busData01) {
        SaveSkillInfoResB objSaveSkillInfoResB = new SaveSkillInfoResB();
        if(busData01 == null)
            throw new SystemException("0x00001","删除失败");
        if(busData01.getSkillRd() == 0)
            throw new SystemException("0x00001","删除失败");
        if(skillDAO.DeleteSkillInfoByRuid(busData01.getSkillRd()) <= 0){
            throw new SystemException("0x00001","删除失败");
        }
        objSaveSkillInfoResB.setMsgCode("0x00000");
        objSaveSkillInfoResB.setMsgDes("成功");
        return objSaveSkillInfoResB;
    }
    // 复制
    public SaveSkillInfoResB AddSkillInfoByRuid(SaveSkillInfoReq01 busData01) {
        SaveSkillInfoResB objSaveSkillInfoResB = new SaveSkillInfoResB();
        SkillInfo skillInfo = skillDAO.SelectSkillInfoByRuid(busData01.getSkillRd());
        String guid = CommonUtils.getRandomNumber();
        skillInfo.setGuid(guid);
        skillInfo.setSkillName(skillInfo.getSkillName());
        skillInfo.setCreator(CommonUtils.readUser().getRealName());
        skillInfo.setCreateTime(new Date());
        if(skillDAO.InsertSkillInfo(skillInfo) <= 0){
            throw new SystemException("0x00001","复制失败");
        }
        skillInfo = skillDAO.SelectSkillInfoByGuid(guid);
        skillInfo.setSkillName(skillInfo.getSkillName()+skillInfo.getRuid());
        if(skillDAO.UpdateSkillInfo(skillInfo) <=0){
            throw new SystemException("0x00001","复制失败");
        }
        objSaveSkillInfoResB.setMsgCode("0x00000");
        objSaveSkillInfoResB.setMsgDes("成功");
        return objSaveSkillInfoResB;

    }

    // 新增
    public SaveSkillInfoResB AddSkillInfo(SaveSkillInfoReq00 busData00) {
        SaveSkillInfoResB objSaveSkillInfoResB = new SaveSkillInfoResB();
        SkillInfo skillInfo = skillDAO.SelectSkillInfoByName(busData00.getSkillName());
        if(skillInfo != null)
            throw new SystemException("0x00001","该名称已存在");
        skillInfo = new SkillInfo();
        skillInfo.setGuid(CommonUtils.getRandomNumber());
        skillInfo.setSkillName(busData00.getSkillName());
        skillInfo.setVPDate(busData00.getVPDate());
        skillInfo.setCreator(CommonUtils.readUser().getRealName());
        skillInfo.setStatus(busData00.getStatus());
        skillInfo.setCreateTime(new Date());
        skillInfo.setRemark(busData00.getRemark());
        if(skillDAO.InsertSkillInfo(skillInfo) <= 0){
            throw new SystemException("0x00001","新增失败");
        }
        objSaveSkillInfoResB.setMsgCode("0x00000");
        objSaveSkillInfoResB.setMsgDes("成功");
        return objSaveSkillInfoResB;
    }
}
