package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoRes;
import pnc.mesadmin.dto.GetAllCaeeierInfo.GetAllCarrierInfoResB;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoRes;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoRes;
import pnc.mesadmin.dto.GetAllToolInfo.GetAllToolInfoResB;
import pnc.mesadmin.dto.GetCarrierInfo.GetCarrierInfoReqBD00;
import pnc.mesadmin.dto.GetCarrierInfo.GetCarrierInfoRes;
import pnc.mesadmin.dto.GetCarrierInfo.GetCarrierInfoResB;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoReqBD00;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoRes;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResB;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoReqBD00;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoRes;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoResB;
import pnc.mesadmin.dto.SaveCarrierInfo.*;
import pnc.mesadmin.dto.SaveFaInfo.*;
import pnc.mesadmin.dto.SaveToolInfo.*;
import pnc.mesadmin.service.CarrierIService;
import pnc.mesadmin.service.FactoryIService;
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
@RequestMapping("/Carrier")
public class CarrierController {
    @Resource
    private CarrierIService carrierIService;
    //获取载具信息页面
    @RequestMapping(value = "/CarrierPG")
    public String getCarrierPGPage() {

        return "res/carrier/carrierinfo";
    }

    //dto获取载具信息列表
    @RequestMapping(value = "/GetAllCarriersInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCarrierInfoRes GetAllCarriersInfos(GetAllReq getAllReq) {
        GetAllCarrierInfoRes objEGetAllFaInfoRes = new GetAllCarrierInfoRes();
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
                objEGetAllFaInfoRes = carrierIService.QALLGetAllCarrierInfoRes(objEInitDataFields, pageInfo);
                objEGetAllFaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCarrierInfoResB objEGetAllFaInfoResB = new GetAllCarrierInfoResB();
                objEGetAllFaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
            }

        } else {
            GetAllCarrierInfoResB objEGetAllFaInfoResB = new GetAllCarrierInfoResB();
            objEGetAllFaInfoResB.setMsgCode("MG0006F");
            objEGetAllFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
        }
        objEGetAllFaInfoRes.setStatus("00");
        return objEGetAllFaInfoRes;
    }

    //dto获取载具信息列表（新）
    @RequestMapping(value = "/GetAllCarriersNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllCarriersNew(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(carrierIService.QALLGetAllCarriersNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }


    //dto获取载具信息
    @RequestMapping(value = "/GetCarrierInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetCarrierInfoRes GetCarrierInfo(GetAllReq getAllReq) {
        GetCarrierInfoRes objEGetFaInfoRes = new GetCarrierInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetCarrierInfoReqBD00 objEGetFaInfoReqBD00 = CommonUtils.switchClass(GetCarrierInfoReqBD00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {



            } else {
                try {
                    objEGetFaInfoRes = carrierIService.GetGetCarrierInfoRes(objEGetFaInfoReqBD00);
                    objEGetFaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetCarrierInfoResB objEGetFaInfoResB = new GetCarrierInfoResB();
                    objEGetFaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFaInfoRes.setBody(objEGetFaInfoResB);
                }
            }
        } else {
            GetCarrierInfoResB objEGetFaInfoResB = new GetCarrierInfoResB();
            objEGetFaInfoResB.setMsgCode("MG0006F");
            objEGetFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetFaInfoRes.setBody(objEGetFaInfoResB);
        }

        objEGetFaInfoRes.setStatus("00");
        return objEGetFaInfoRes;
    }


    //dto保存载具信息
    @RequestMapping(value = "/SaveCarrierInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCarrierInfoRes SaveCarrierInfo(SaveReq saveReq) {
        SaveCarrierInfoRes objESaveFaInfoRes = new SaveCarrierInfoRes();
        SaveCarrierInfoResB objESaveFaInfoResB = new SaveCarrierInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveCarrierInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveCarrierInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierIService.AddGetCarrierInfoRes(objESaveCpInfoRes00);
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveCarrierInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveCarrierInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierIService.RmSaveCarrierInfoRes(objESaveCpInfoRes01);
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveCarrierInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveCarrierInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierIService.ModSaveCarrierInfoRes(objESaveCpInfoRes02);
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveCarrierInfoReqBD03 objESaveFaInfoReqBD03 = CommonUtils.switchClass(SaveCarrierInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierIService.AddSaveCarrierInfoRes(objESaveFaInfoReqBD03);
                objESaveFaInfoResB = new SaveCarrierInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierInfoResB();
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
