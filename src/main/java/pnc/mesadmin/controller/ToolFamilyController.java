package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoRes;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoResB;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoReqBD00;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoRes;
import pnc.mesadmin.dto.GetToolFamilyInfo.GetToolFamilyInfoResB;
import pnc.mesadmin.dto.SaveToolFamilyInfo.*;
import pnc.mesadmin.service.ToolFamilyIService;
import pnc.mesadmin.service.ToolIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具家族信息Controller
 * 创建人：郝赞
 * 创建时间：2018-6-14
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/ToolFamily")
public class ToolFamilyController {

    @Resource
    private ToolIService toolIService;

    @Resource
    private ToolFamilyIService toolFamilyIService;

    //获取工具家族信息页面
    @RequestMapping(value = "/ToolFamilyPG")
    public String getToolFamilyPGPage() {

        return "res/toolFamily/toolFamilyinfo";
    }


    //dto获取工具家族信息列表
    @RequestMapping(value = "/GetAllToolFamilyInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllToolFamilyInfoRes GetAllToolFamilyInfo(GetAllReq getAllReq) {
        GetAllToolFamilyInfoRes objEGetAllFaInfoRes = new GetAllToolFamilyInfoRes();
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
                objEGetAllFaInfoRes = toolFamilyIService.QALLGetAllFaInfoRes(objEInitDataFields, pageInfo);
                objEGetAllFaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllToolFamilyInfoResB objEGetAllFaInfoResB = new GetAllToolFamilyInfoResB();
                objEGetAllFaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
            }

        } else {
            GetAllToolFamilyInfoResB objEGetAllFaInfoResB = new GetAllToolFamilyInfoResB();
            objEGetAllFaInfoResB.setMsgCode("MG0006F");
            objEGetAllFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
        }
        objEGetAllFaInfoRes.setStatus("00");
        return objEGetAllFaInfoRes;
    }

    //dto获取工具家族信息列表(新)
    @RequestMapping(value="/GetAllToolFamilyNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllToolFamilyNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(toolFamilyIService.QALLGetAllToolFamilyNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }


    //dto获取工具家族信息
    @RequestMapping(value = "/GetToolFamilyInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetToolFamilyInfoRes GetToolFamilyInfo(GetAllReq getAllReq) {
        GetToolFamilyInfoRes objEGetFaInfoRes = new GetToolFamilyInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetToolFamilyInfoReqBD00 objEGetFaInfoReqBD00 = CommonUtils.switchClass(GetToolFamilyInfoReqBD00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {

            } else {
                try {
                    objEGetFaInfoRes = toolFamilyIService.GetGetToolFamilyInfoRes(objEGetFaInfoReqBD00);
                    objEGetFaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetToolFamilyInfoResB objEGetFaInfoResB = new GetToolFamilyInfoResB();
                    objEGetFaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFaInfoRes.setBody(objEGetFaInfoResB);
                }
            }
        } else {
            GetToolFamilyInfoResB objEGetFaInfoResB = new GetToolFamilyInfoResB();
            objEGetFaInfoResB.setMsgCode("MG0006F");
            objEGetFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetFaInfoRes.setBody(objEGetFaInfoResB);
        }

        objEGetFaInfoRes.setStatus("00");
        return objEGetFaInfoRes;
    }


    //dto保存工具家族信息
    @RequestMapping(value = "/SaveToolFamilyInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveToolFamilyInfoRes SaveToolFamilyInfo(SaveReq saveReq) {
        SaveToolFamilyInfoRes objESaveFaInfoRes = new SaveToolFamilyInfoRes();
        SaveToolFamilyInfoResB objESaveFaInfoResB = new SaveToolFamilyInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveToolFamilyInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveToolFamilyInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolFamilyIService.AddGetToolFamilyInfoRes(objESaveCpInfoRes00);
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveToolFamilyInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveToolFamilyInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolFamilyIService.RmSaveToolFamilyInfoRes(objESaveCpInfoRes01);
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveToolFamilyInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveToolFamilyInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolFamilyIService.ModSaveToolFamilyInfoRes(objESaveCpInfoRes02);
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveToolFamilyInfoReqBD03 objESaveFaInfoReqBD03 = CommonUtils.switchClass(SaveToolFamilyInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolFamilyIService.AddSaveToolFamilyInfoRes(objESaveFaInfoReqBD03);
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else {
            objESaveFaInfoResB.setMsgCode("MG0006F");
            objESaveFaInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        }
        objESaveFaInfoRes.setStatus("00");
        return objESaveFaInfoRes;
    }
}
