package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoRes;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResB;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoReqBD00;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoRes;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoResB;
import pnc.mesadmin.dto.SaveToolInfo.*;
import pnc.mesadmin.service.ToolIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工具信息Controller
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Tool")
public class ToolController {

    @Resource
    private ToolIService toolIService;

    //获取工具信息页面
    @RequestMapping(value = "/ToolPG")
    public String getToolPGPage() { return "res/tool/toolinfo"; }

    //dto获取工具信息列表
    @RequestMapping(value = "/GetAllToolsInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllToolInfoRes GetAllToolInfo(GetAllReq getAllReq) {
        GetAllToolInfoRes objEGetAllFaInfoRes = new GetAllToolInfoRes();
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
                objEGetAllFaInfoRes = toolIService.QALLGetAllFaInfoRes(objEInitDataFields, pageInfo);
                objEGetAllFaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllToolInfoResB objEGetAllFaInfoResB = new GetAllToolInfoResB();
                objEGetAllFaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
            }

        } else {
            GetAllToolInfoResB objEGetAllFaInfoResB = new GetAllToolInfoResB();
            objEGetAllFaInfoResB.setMsgCode("MG0006F");
            objEGetAllFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
        }
        objEGetAllFaInfoRes.setStatus("00");
        return objEGetAllFaInfoRes;
    }

    //dto获取工具信息列表（新）
    @RequestMapping(value = "/GetAllToolsNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllToolsNew(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(toolIService.QALLGetAllToolsNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto获取工具信息
    @RequestMapping(value = "/GetToolInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetToolInfoRes GetToolInfo(GetAllReq getAllReq) {
        GetToolInfoRes objEGetFaInfoRes = new GetToolInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetToolInfoReqBD00 objEGetFaInfoReqBD00 = CommonUtils.switchClass(GetToolInfoReqBD00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {



            } else {
                try {
                    objEGetFaInfoRes = toolIService.GetGetToolInfoRes(objEGetFaInfoReqBD00);
                    objEGetFaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetToolInfoResB objEGetFaInfoResB = new GetToolInfoResB();
                    objEGetFaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFaInfoRes.setBody(objEGetFaInfoResB);
                }
            }
        } else {
            GetToolInfoResB objEGetFaInfoResB = new GetToolInfoResB();
            objEGetFaInfoResB.setMsgCode("MG0006F");
            objEGetFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetFaInfoRes.setBody(objEGetFaInfoResB);
        }

        objEGetFaInfoRes.setStatus("00");
        return objEGetFaInfoRes;
    }


    //dto保存工具信息
    @RequestMapping(value = "/SaveToolInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveToolInfoRes SaveToolInfo(SaveReq saveReq) {
        SaveToolInfoRes objESaveFaInfoRes = new SaveToolInfoRes();
        SaveToolInfoResB objESaveFaInfoResB = new SaveToolInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveToolInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveToolInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolIService.AddGetToolInfoRes(objESaveCpInfoRes00);
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveToolInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveToolInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolIService.RmSaveToolInfoRes(objESaveCpInfoRes01);
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveToolInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveToolInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolIService.ModSaveToolInfoRes(objESaveCpInfoRes02);
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveToolInfoReqBD03 objESaveFaInfoReqBD03 = CommonUtils.switchClass(SaveToolInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = toolIService.AddSaveToolInfoRes(objESaveFaInfoReqBD03);
                objESaveFaInfoResB = new SaveToolInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveToolInfoResB();
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
