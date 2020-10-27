package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSkillInfo.GetAllSkillInfoRes;
import pnc.mesadmin.dto.GetAllSkillInfo.GetAllSkillInfoResB;
import pnc.mesadmin.dto.GetSkillInfo.GetSkillInfoReqBD00;
import pnc.mesadmin.dto.GetSkillInfo.GetSkillInfoRes;
import pnc.mesadmin.dto.GetSkillInfo.GetSkillInfoResB;
import pnc.mesadmin.dto.SaveSkillInfo.*;
import pnc.mesadmin.service.SkillIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by test on 2017/8/16.
 */
@Controller
@Scope("prototype")
@RequestMapping("/Skill")
public class SkillController {

    @Resource
    private SkillIService skillIService;

    @RequestMapping("/SkillPG")
    public String SkillPG(){
        return "res/skill/skillinfo";
    }

    // 查询所有技能信息
    @ResponseBody
    @RequestMapping(value="/GetAllSkillInfo",method = RequestMethod.POST)
    public GetAllSkillInfoRes GetAllSkillInfo(GetAllReq getAllReq){
        GetAllSkillInfoRes objGetAllSkillInfoRes = new GetAllSkillInfoRes();
        GetAllSkillInfoResB objGetAllSkillInfoResB = null;
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



            // 查询所有信息
            objGetAllSkillInfoResB = skillIService.GetAllSkillInfo(objEInitDataFields, pageInfo);
        }
        objGetAllSkillInfoRes.setBody(objGetAllSkillInfoResB);
        objGetAllSkillInfoRes.setStatus("00");
        return objGetAllSkillInfoRes;
    }

    // 查询技能详细信息
    @ResponseBody
    @RequestMapping(value="/GetSkillInfo",method = RequestMethod.POST)
    public GetSkillInfoRes GetSkillInfo(GetAllReq argGetAll){
        GetSkillInfoRes objGetSkillInfoRes = new GetSkillInfoRes();
        GetSkillInfoResB objGetSkillInfoResB = null;
        if("00".equals(argGetAll.getExecType())){
            GetSkillInfoReqBD00 busData00 = CommonUtils.switchClass(GetSkillInfoReqBD00.class,argGetAll.getBusData());
            // 查询所有信息
            objGetSkillInfoResB = skillIService.GetSkillInfoByRuid(busData00.getSkillRd());
        }
        objGetSkillInfoRes.setBody(objGetSkillInfoResB);
        objGetSkillInfoRes.setStatus("00");
        return objGetSkillInfoRes;
    }

    // 保存
    @ResponseBody
    @RequestMapping(value = "/SaveSkillInfo",method = RequestMethod.POST)
    public SaveSkillInfoRes SaveSkillInfo(SaveReq argSaveReq){
        SaveSkillInfoRes objSaveSkillInfoRes = new SaveSkillInfoRes();
        SaveSkillInfoResB objSaveSkillInfoResB = new SaveSkillInfoResB();
        //新增
        if("00".equals(argSaveReq.getExecType())){
            try{
                SaveSkillInfoReq00 busData00 = CommonUtils.switchClass(SaveSkillInfoReq00.class,argSaveReq.getBusData());
                objSaveSkillInfoResB = skillIService.AddSkillInfo(busData00);
            }catch (SystemException e){
                objSaveSkillInfoResB.setMsgDes(e.getMsgDes());
                objSaveSkillInfoResB.setMsgCode(e.getMsgCode());
            }
        }

        //删除
        else if("01".equals(argSaveReq.getExecType())){
            try{
                SaveSkillInfoReq01 busData01 = CommonUtils.switchClass(SaveSkillInfoReq01.class,argSaveReq.getBusData());
                objSaveSkillInfoResB = skillIService.RmSkillInfo(busData01);
            }catch (SystemException e){
                objSaveSkillInfoResB.setMsgDes(e.getMsgDes());
                objSaveSkillInfoResB.setMsgCode(e.getMsgCode());
            }
        }

        //修改
        else if("02".equals(argSaveReq.getExecType())){
            try{
                SaveSkillInfoReq02 busData02 = CommonUtils.switchClass(SaveSkillInfoReq02.class,argSaveReq.getBusData());
                objSaveSkillInfoResB = skillIService.ModSkillInfo(busData02);
            }catch (SystemException e){
                objSaveSkillInfoResB.setMsgDes(e.getMsgDes());
                objSaveSkillInfoResB.setMsgCode(e.getMsgCode());
            }
        }

        //复制
        else if("03".equals(argSaveReq.getExecType())){
            try{
                SaveSkillInfoReq01 busData01 = CommonUtils.switchClass(SaveSkillInfoReq01.class,argSaveReq.getBusData());
                objSaveSkillInfoResB = skillIService.AddSkillInfoByRuid(busData01);
            }catch (SystemException e){
                objSaveSkillInfoResB.setMsgDes(e.getMsgDes());
                objSaveSkillInfoResB.setMsgCode(e.getMsgCode());
            }
        }

        objSaveSkillInfoRes.setBody(objSaveSkillInfoResB);
        objSaveSkillInfoRes.setStatus("00");
        return objSaveSkillInfoRes;
    }
}
