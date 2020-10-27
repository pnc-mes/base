package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllUrSGInfo.GetAllUrSGInfoReqBD00;
import pnc.mesadmin.dto.GetAllUrSGInfo.GetAllUrSGInfoRes;
import pnc.mesadmin.dto.GetAllUrSGInfo.GetAllUrSGInfoResB;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoReqBD02;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoRes;
import pnc.mesadmin.dto.SaveUrSGInfo.SaveUrSGInfoResB;
import pnc.mesadmin.service.UsrSkillIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：技能培训记录Controller
 * 创建人：刘福志
 * 创建时间：2017-08-28
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/UsrSkill")
public class UsrSkillController {
    @Resource
    private UsrSkillIService usrSkillIService;

    //获取技能培训记录页面
    @RequestMapping(value = "/UsrSkillPG")
    public String getUsrSkillPG(){

        return "res/usrskill/usrskillinfo";
    }

    //dto查询培训记录信息列表
    @RequestMapping(value = "/GetAllUrSGInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllUrSGInfoRes GetAllUrSGInfo(GetAllReq getAllReq) {
        GetAllUrSGInfoRes objEGetAllUrSGInfoRes = new GetAllUrSGInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    GetAllUrSGInfoReqBD00 objEGetAllUrSGInfoReqBD00 = null;

                    List<InitDataField> objEInitDataFields = null;


                    if( getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())){
                        InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                        if(objEInitData != null) {
                            objEInitDataFields = objEInitData.getFiledList();
                        }
                    }

                    objEGetAllUrSGInfoRes = usrSkillIService.QALLGetAllUrSGInfo(objEGetAllUrSGInfoReqBD00,objEInitDataFields);
                    objEGetAllUrSGInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllUrSGInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllUrSGInfoResB objEGetAllUrSGInfoResB = new GetAllUrSGInfoResB();
                    objEGetAllUrSGInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllUrSGInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllUrSGInfoRes.setBody(objEGetAllUrSGInfoResB);
                }
            }
        } else {
            GetAllUrSGInfoResB objEGetAllUrSGInfoResB = new GetAllUrSGInfoResB();
            objEGetAllUrSGInfoResB.setMsgCode("MG0006F");
            objEGetAllUrSGInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllUrSGInfoRes.setBody(objEGetAllUrSGInfoResB);
        }
        objEGetAllUrSGInfoRes.setStatus("00");
        return objEGetAllUrSGInfoRes;
    }

    //保存技能培训记录信息
    @RequestMapping(value = "/SaveUrSGInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveUrSGInfoRes SaveUnitInfo(SaveReq saveReq) {
        SaveUrSGInfoRes objESaveUrSGInfoRes = new SaveUrSGInfoRes();
        SaveUrSGInfoResB objESaveUrSGInfoResB = new SaveUrSGInfoResB();

        if ("02".equals(saveReq.getExecType())) {
            SaveUrSGInfoReqBD02[] objESaveUrSGInfoReqBD02 = CommonUtils.switchClass(SaveUrSGInfoReqBD02[].class, saveReq.getBusData());

            try {
                objESaveUrSGInfoRes = usrSkillIService.ModSaveUrSGInfoRes(objESaveUrSGInfoReqBD02);
                objESaveUrSGInfoResB = new SaveUrSGInfoResB();
                objESaveUrSGInfoResB.setMsgCode("0x00000");
                objESaveUrSGInfoResB.setMsgDes("成功");
                objESaveUrSGInfoRes.setBody(objESaveUrSGInfoResB);
            } catch (SystemException e) {
                objESaveUrSGInfoResB = new SaveUrSGInfoResB();
                objESaveUrSGInfoResB.setMsgCode(e.getMsgCode());
                objESaveUrSGInfoResB.setMsgDes(e.getMsgDes());
                objESaveUrSGInfoRes.setBody(objESaveUrSGInfoResB);
            }
        }
        else {
            objESaveUrSGInfoResB.setMsgCode("MG0006F");
            objESaveUrSGInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于02");
            objESaveUrSGInfoRes.setBody(objESaveUrSGInfoResB);
        }

        objESaveUrSGInfoRes.setStatus("00");
        return objESaveUrSGInfoRes;
    }
}
