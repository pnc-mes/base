package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllWOBInfo.*;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoRes;
import pnc.mesadmin.dto.GetAllWOInfo.GetAllWOInfoResB;
import pnc.mesadmin.dto.GetWOInfo.GetWOInfoReqBD00;
import pnc.mesadmin.dto.GetWOInfo.GetWOInfoRes;
import pnc.mesadmin.dto.GetWOInfo.GetWOInfoResB;
import pnc.mesadmin.dto.SaveWOInfo.*;
import pnc.mesadmin.service.WOIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工单信息Controller
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */

@Controller
@Scope("prototype")
@RequestMapping("/WO")
public class WOController {

    @Resource
    private WOIService WOIService;

    @RequestMapping("/WOPG")
    public String specinfo(HttpServletRequest request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String time = format.format(new Date());
        request.setAttribute("time", time);
        return "plan/worder/worderinfo";
    }

    //dto查询工单信息列表
    @RequestMapping(value = "/GetAllWOInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllWOInfoRes GetAllWOInfo(GetAllReq getAllReq) {
        GetAllWOInfoRes objEGetAllWOInfoRes = new GetAllWOInfoRes();
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
                objEGetAllWOInfoRes = WOIService.QALLGetAllWOInfoRes(objEInitDataFields, pageInfo);
                objEGetAllWOInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllWOInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllWOInfoResB objEGetAllWOInfoResB = new GetAllWOInfoResB();
                objEGetAllWOInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllWOInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllWOInfoRes.setBody(objEGetAllWOInfoResB);
            }
        } else {
            GetAllWOInfoResB objEGetAllWOInfoResB = new GetAllWOInfoResB();
            objEGetAllWOInfoResB.setMsgCode("MG0006F");
            objEGetAllWOInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllWOInfoRes.setBody(objEGetAllWOInfoResB);
        }
        objEGetAllWOInfoRes.setStatus("00");
        return objEGetAllWOInfoRes;
    }

    //  获取班别列表信息
    @RequestMapping(value = "/GetAllNewWOInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllNewWOInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(WOIService.QALLGetAllNewWOInfoRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto查询工单线体关联信息列表
    @RequestMapping(value = "/GetAllWoLine", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllWoLine(GetAllReq request) {
        List<InitDataField> InitDataFields = null;
        PageInfo pageInfo = null;
        if (request.getInitData() != null && !"".equals(request.getInitData())) {
            InitData InitData = CommonUtils.switchClass(InitData.class, request.getInitData());
            if (InitData != null) {
                InitDataFields = InitData.getFiledList();
            }
        }

        if (request.getPageInfo() != null && !"".equals(request.getPageInfo())) {
            pageInfo = CommonUtils.switchClass(PageInfo.class, request.getPageInfo());
        }
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        try {
            baseResponse = WOIService.QALLGetAllWoLineALl(InitDataFields, pageInfo);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //dto查询工单信息
    @RequestMapping(value = "/GetWOInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetWOInfoRes GetWOInfo(GetAllReq getAllReq) {
        GetWOInfoRes objEGetWOInfoRes = new GetWOInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            GetWOInfoReqBD00 objEGetWOInfoReqBD00 = CommonUtils.switchClass(GetWOInfoReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {

                try {
                    objEGetWOInfoRes = WOIService.GetGetWOInfoRes(objEGetWOInfoReqBD00);
                    objEGetWOInfoRes.getBody().setMsgCode("0x00000");
                    objEGetWOInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetWOInfoResB objEGetWOInfoResB = new GetWOInfoResB();
                    objEGetWOInfoResB.setMsgCode(e.getMsgCode());
                    objEGetWOInfoResB.setMsgDes(e.getMsgDes());
                    objEGetWOInfoRes.setBody(objEGetWOInfoResB);
                }
            }
        } else {
            GetWOInfoResB objEGetWOInfoResB = new GetWOInfoResB();
            objEGetWOInfoResB.setMsgCode("MG0006F");
            objEGetWOInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetWOInfoRes.setBody(objEGetWOInfoResB);
        }
        objEGetWOInfoRes.setStatus("00");
        return objEGetWOInfoRes;
    }

    //dto新增工单信息
    @RequestMapping(value = "/SaveWOInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveWOInfoRes SaveWOInfo(SaveReq saveReq) {
        SaveWOInfoRes objESaveWOInfoRes = new SaveWOInfoRes();
        SaveWOInfoResB objESaveWOInfoResB = null;

        try {
            if ("00".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD00 objESaveWOInfoReqBD00 = CommonUtils.switchClass(SaveWOInfoReqBD00.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.AddSaveWOInfoRes(objESaveWOInfoReqBD00);
            } else if ("01".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD01 objESaveWOInfoReqBD01 = CommonUtils.switchClass(SaveWOInfoReqBD01.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.RmSaveWOInfoRes(objESaveWOInfoReqBD01);
            } else if ("02".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD02 objESaveWOInfoReqBD02 = CommonUtils.switchClass(SaveWOInfoReqBD02.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWOInfoRes(objESaveWOInfoReqBD02);
            } else if ("03".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD03 objESaveWOInfoReqBD03 = CommonUtils.switchClass(SaveWOInfoReqBD03.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD03);
            } else if ("04".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD04 objESaveWOInfoReqBD04 = CommonUtils.switchClass(SaveWOInfoReqBD04.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD04);
            } else if ("05".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD05 objESaveWOInfoReqBD05 = CommonUtils.switchClass(SaveWOInfoReqBD05.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD05);
            } else if ("06".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD06 objESaveWOInfoReqBD06 = CommonUtils.switchClass(SaveWOInfoReqBD06.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD06);
            } else if ("07".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD07 objESaveWOInfoReqBD07 = CommonUtils.switchClass(SaveWOInfoReqBD07.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD07);
            } else if ("08".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD08 objESaveWOInfoReqBD08 = CommonUtils.switchClass(SaveWOInfoReqBD08.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD08);
            } else if ("09".equals(saveReq.getExecType())) {

                SaveWOInfoReqBD09 objESaveWOInfoReqBD09 = CommonUtils.switchClass(SaveWOInfoReqBD09.class, saveReq.getBusData());
                objESaveWOInfoRes = WOIService.ModSaveWO(objESaveWOInfoReqBD09);
            }

            if (objESaveWOInfoRes.getBody() == null) {
                objESaveWOInfoResB = new SaveWOInfoResB();
            } else {
                objESaveWOInfoResB = objESaveWOInfoRes.getBody();
            }

            objESaveWOInfoResB.setMsgCode("0x00000");
            objESaveWOInfoResB.setMsgDes("成功");
        } catch (SystemException e) {
            objESaveWOInfoResB = new SaveWOInfoResB();
            objESaveWOInfoResB.setMsgCode(e.getMsgCode());
            objESaveWOInfoResB.setMsgDes(e.getMsgDes());
        }

        objESaveWOInfoRes.setStatus("00");
        objESaveWOInfoRes.setBody(objESaveWOInfoResB);
        return objESaveWOInfoRes;
    }

    //dto查询批次信息，根据工单id
    @RequestMapping(value = "/GetAllWOBInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllWOBInfoRes GetAllWOBInfo(GetAllReq getAllReq) {
        GetAllWOBInfoRes objEGetAllWOBInfoRes = new GetAllWOBInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            GetAllWOBInfoReqBD00 objEGetAllWOBInfoReqBD00 = CommonUtils.switchClass(GetAllWOBInfoReqBD00.class, getAllReq.getBusData());

            if (getAllReq.getPageInfo() != null) {

            } else {

                try {
                    objEGetAllWOBInfoRes = WOIService.GetGetAllWOBInfoRes(objEGetAllWOBInfoReqBD00);
                    objEGetAllWOBInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllWOBInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllWOBInfoResB objEGetAllWOBInfoResB = new GetAllWOBInfoResB();
                    objEGetAllWOBInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllWOBInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllWOBInfoRes.setBody(objEGetAllWOBInfoResB);
                }
            }

        } else if ("01".equals(getAllReq.getExecType())) {
            GetAllWOBInfoReqBD01 objEGetAllWOBInfoReqBD01 = CommonUtils.switchClass(GetAllWOBInfoReqBD01.class, getAllReq.getBusData());

            if (getAllReq.getPageInfo() != null) {


            } else {

                try {
                    objEGetAllWOBInfoRes = WOIService.GetGetAllWOBInfoReqBD01(objEGetAllWOBInfoReqBD01);
                    objEGetAllWOBInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllWOBInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllWOBInfoResB objEGetAllWOBInfoResB = new GetAllWOBInfoResB();
                    objEGetAllWOBInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllWOBInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllWOBInfoRes.setBody(objEGetAllWOBInfoResB);
                }
            }

        } else {
            GetAllWOBInfoResB objEGetAllWOBInfoResB = new GetAllWOBInfoResB();
            objEGetAllWOBInfoResB.setMsgCode("MG0006F");
            objEGetAllWOBInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00、01");
            objEGetAllWOBInfoRes.setBody(objEGetAllWOBInfoResB);
        }
        objEGetAllWOBInfoRes.setStatus("00");
        return objEGetAllWOBInfoRes;
    }


    /**
     * 根据线体查询工单信息
     *
     * @param request
     * @return MBaseRes
     * @description:
     */
    @RequestMapping(value = "/GetAllWoinfoByLineRd", method = RequestMethod.POST)
    @ResponseBody
    public MBaseRes GetAllWoinfoByLineRd(GetAllWOBInfoReqBD03 request) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        try {
            baseResponse = WOIService.GetAllWoinfoByLineRd(request);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

}
