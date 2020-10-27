package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoRes;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResB;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoReq00;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoRes;
import pnc.mesadmin.dto.GetSGInfo.GetSGInfoResB;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoReqBD00;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoResB;
import pnc.mesadmin.dto.SaveRKInfo.SaveRKInfoResB;
import pnc.mesadmin.dto.SaveSGInfo.*;
import pnc.mesadmin.service.SkillGIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
@Controller
@Scope("prototype")
@RequestMapping("/SkillG")
public class SkillGController {

    @Resource
    private SkillGIService skillGIService;

    @RequestMapping("/SkillGPG")
    public String SkillG() {
        return "res/skillg/skillginfo";
    }

    //查询技能组列表
    @RequestMapping(value = "/GetAllSGInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllSGInfoRes GetAllSGInfo(GetAllReq getAllReq) {
        GetAllSGInfoRes objEGetAllSGInfoRes = new GetAllSGInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
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
            try {
                objEGetAllSGInfoRes = skillGIService.QALLGetAllSGInfoRes(objEInitDataFields, pageInfo);
                objEGetAllSGInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllSGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSGInfoResB objEGetAllSGInfoResB = new GetAllSGInfoResB();
                objEGetAllSGInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllSGInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllSGInfoRes.setBody(objEGetAllSGInfoResB);
            }


        } else {
            GetAllSGInfoResB objEGetAllSGInfoResB = new GetAllSGInfoResB();
            objEGetAllSGInfoResB.setMsgCode("MG0006F");
            objEGetAllSGInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllSGInfoRes.setBody(objEGetAllSGInfoResB);
        }

        objEGetAllSGInfoRes.setStatus("00");

        return objEGetAllSGInfoRes;
    }


    @RequestMapping(value="/GetAllSGNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllSGNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(skillGIService.QALLGetAllSGNew(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }


    //查询技能组信息
    @RequestMapping(value = "/GetSGInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSGInfoRes GetSGInfo(GetAllReq getAllReq) {
        GetSGInfoRes objEGetSGInfoRes = new GetSGInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetSGInfoReq00 objEGetSGInfoReq00 = CommonUtils.switchClass(GetSGInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {

                try {
                    objEGetSGInfoRes = skillGIService.GetGetSGInfoRes(objEGetSGInfoReq00);
                    objEGetSGInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSGInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetSGInfoResB objEGetSGInfoResB = new GetSGInfoResB();
                    objEGetSGInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSGInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSGInfoRes.setBody(objEGetSGInfoResB);
                }
            }
        } else {
            GetSGInfoResB objEGetSGInfoResB = new GetSGInfoResB();
            objEGetSGInfoResB.setMsgCode("MG0006F");
            objEGetSGInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetSGInfoRes.setBody(objEGetSGInfoResB);
        }
        objEGetSGInfoRes.setStatus("00");
        return objEGetSGInfoRes;
    }

    //保存技能信息
    @RequestMapping(value = "/SaveSGInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveSGInfoRes SaveSGInfo(SaveReq saveReq) {
        SaveSGInfoRes objESaveSGInfoRes = new SaveSGInfoRes();
        SaveSGInfoResB objESaveSGInfoResB = new SaveSGInfoResB();

        if ("00".equals(saveReq.getExecType())) {

            SaveSGInfoReq00 objESaveSGInfoReq00 = CommonUtils.switchClass(SaveSGInfoReq00.class, saveReq.getBusData());

            try {
                objESaveSGInfoRes = skillGIService.AddSaveSGInfoRes(objESaveSGInfoReq00);
                objESaveSGInfoRes.getBody().setMsgCode("0x00000");
                objESaveSGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveSGInfoResB = new SaveSGInfoResB();
                objESaveSGInfoResB.setMsgCode(e.getMsgCode());
                objESaveSGInfoResB.setMsgDes(e.getMsgDes());
                objESaveSGInfoRes.setBody(objESaveSGInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {

            SaveSGInfoReq01 objESaveSGInfoReq01 = CommonUtils.switchClass(SaveSGInfoReq01.class, saveReq.getBusData());

            try {
                objESaveSGInfoRes = skillGIService.RmSaveSGInfoRes(objESaveSGInfoReq01);
                objESaveSGInfoRes.getBody().setMsgCode("0x00000");
                objESaveSGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveSGInfoResB = new SaveSGInfoResB();
                objESaveSGInfoResB.setMsgCode(e.getMsgCode());
                objESaveSGInfoResB.setMsgDes(e.getMsgDes());
                objESaveSGInfoRes.setBody(objESaveSGInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {

            SaveSGInfoReq02 objESaveSGInfoReq02 = CommonUtils.switchClass(SaveSGInfoReq02.class, saveReq.getBusData());

            try {
                objESaveSGInfoRes = skillGIService.ModSaveSGInfoRes(objESaveSGInfoReq02);
                objESaveSGInfoRes.getBody().setMsgCode("0x00000");
                objESaveSGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveSGInfoResB = new SaveSGInfoResB();
                objESaveSGInfoResB.setMsgCode(e.getMsgCode());
                objESaveSGInfoResB.setMsgDes(e.getMsgDes());
                objESaveSGInfoRes.setBody(objESaveSGInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {

            SaveSGInfoReq03 objESaveSGInfoReq03 = CommonUtils.switchClass(SaveSGInfoReq03.class, saveReq.getBusData());

            try {
                objESaveSGInfoRes = skillGIService.AddSaveSGInfoReq03(objESaveSGInfoReq03);
                objESaveSGInfoRes.getBody().setMsgCode("0x00000");
                objESaveSGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveSGInfoResB = new SaveSGInfoResB();
                objESaveSGInfoResB.setMsgCode(e.getMsgCode());
                objESaveSGInfoResB.setMsgDes(e.getMsgDes());
                objESaveSGInfoRes.setBody(objESaveSGInfoResB);
            }
        } else {
            objESaveSGInfoResB = new SaveSGInfoResB();
            objESaveSGInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveSGInfoRes.setBody(objESaveSGInfoResB);
        }
        objESaveSGInfoRes.setStatus("00");
        return objESaveSGInfoRes;
    }

}
