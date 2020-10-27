package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoRes;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoResB;
import pnc.mesadmin.dto.GetCarrierFamilyInfo.GetCarrierFamilyInfoReqBD00;
import pnc.mesadmin.dto.GetCarrierFamilyInfo.GetCarrierFamilyInfoRes;
import pnc.mesadmin.dto.GetCarrierFamilyInfo.GetCarrierFamilyInfoResB;
import pnc.mesadmin.dto.SaveCarrierFamilyInfo.*;
import pnc.mesadmin.service.CarrierFamilyIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具家族信息Controller
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/CarrierFamily")
public class CarrierFamilyController {

    @Resource
    private CarrierFamilyIService carrierFamilyIService;


    //获取工具家族信息页面
    @RequestMapping(value = "/CarrierFamilyPG")
    public String getToolFamilyPGPage() {

        return "res/carrierFamily/carrierFamilyinfo";
    }

    //获取工序列表信息新
    @RequestMapping(value = "/GetAllCarrierFamilyNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllCarrierFamilyNew(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(carrierFamilyIService.QALLGetAllCarrierFamilyNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto获取载具家族信息列表
    @RequestMapping(value = "/GetAllCarrierFamilyInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCarrierFamilyInfoRes GetAllCarrierFamilyInfo(GetAllReq getAllReq) {
        GetAllCarrierFamilyInfoRes objEGetAllFaInfoRes = new GetAllCarrierFamilyInfoRes();
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
                objEGetAllFaInfoRes = carrierFamilyIService.QALLGetAllFaInfoRes(objEInitDataFields, pageInfo);
                objEGetAllFaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCarrierFamilyInfoResB objEGetAllFaInfoResB = new GetAllCarrierFamilyInfoResB();
                objEGetAllFaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
            }

        } else {
            GetAllCarrierFamilyInfoResB objEGetAllFaInfoResB = new GetAllCarrierFamilyInfoResB();
            objEGetAllFaInfoResB.setMsgCode("MG0006F");
            objEGetAllFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
        }
        objEGetAllFaInfoRes.setStatus("00");
        return objEGetAllFaInfoRes;
    }

    //dto获取载具家族信息
    @RequestMapping(value = "/GetCarrierFamilyInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetCarrierFamilyInfoRes GetCarrierFamilyInfo(GetAllReq getAllReq) {
        GetCarrierFamilyInfoRes objEGetFaInfoRes = new GetCarrierFamilyInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetCarrierFamilyInfoReqBD00 objEGetFaInfoReqBD00 = CommonUtils.switchClass(GetCarrierFamilyInfoReqBD00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {

            } else {
                try {
                    objEGetFaInfoRes = carrierFamilyIService.GetGetCarrierFamilyInfoRes(objEGetFaInfoReqBD00);
                    objEGetFaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetCarrierFamilyInfoResB objEGetFaInfoResB = new GetCarrierFamilyInfoResB();
                    objEGetFaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFaInfoRes.setBody(objEGetFaInfoResB);
                }
            }
        } else {
            GetCarrierFamilyInfoResB objEGetFaInfoResB = new GetCarrierFamilyInfoResB();
            objEGetFaInfoResB.setMsgCode("MG0006F");
            objEGetFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetFaInfoRes.setBody(objEGetFaInfoResB);
        }

        objEGetFaInfoRes.setStatus("00");
        return objEGetFaInfoRes;
    }


    //dto保存载具家族信息
    @RequestMapping(value = "/SaveCarrierFamilyInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCarrierFamilyInfoRes SaveCarrierFamilyInfo(SaveReq saveReq) {
        SaveCarrierFamilyInfoRes objESaveFaInfoRes = new SaveCarrierFamilyInfoRes();
        SaveCarrierFamilyInfoResB objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveCarrierFamilyInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveCarrierFamilyInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierFamilyIService.AddGetCarrierFamilyInfoRes(objESaveCpInfoRes00);
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveCarrierFamilyInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveCarrierFamilyInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierFamilyIService.RmSaveCarrierFamilyInfoRes(objESaveCpInfoRes01);
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveCarrierFamilyInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveCarrierFamilyInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierFamilyIService.ModSaveCarrierFamilyInfoRes(objESaveCpInfoRes02);
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveCarrierFamilyInfoReqBD03 objESaveFaInfoReqBD03 = CommonUtils.switchClass(SaveCarrierFamilyInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = carrierFamilyIService.AddSaveCarrierFamilyInfoRes(objESaveFaInfoReqBD03);
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveCarrierFamilyInfoResB();
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
