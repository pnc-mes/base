package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllWoTypeInfo.GetAllWoTypeInfoRes;
import pnc.mesadmin.dto.GetAllWoTypeInfo.GetAllWoTypeInfoResB;
import pnc.mesadmin.dto.GetWoTypeInfo.GetWoTypeInfoReqBD00;
import pnc.mesadmin.dto.GetWoTypeInfo.GetWoTypeInfoRes;
import pnc.mesadmin.dto.GetWoTypeInfo.GetWoTypeInfoResB;
import pnc.mesadmin.dto.SaveShiftInfo.*;
import pnc.mesadmin.dto.SaveWoTypeInfo.*;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.entity.WoTypeInfo;
import pnc.mesadmin.service.WoTypeIService;
import pnc.mesadmin.service.impl.WoTypeService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/WoType")
public class WoTypeController {

    @Resource
    private WoTypeIService woTypeIService;

    @RequestMapping(value = "/WoTypePG")
    public String getWoType() {
        return "base/wotype/wotype";
    }

    // 查询所有工单类型
    @ResponseBody
    @RequestMapping(value = "/GetAllWTInfo", method = RequestMethod.POST)
    public GetAllWoTypeInfoRes GetAllWTInfo(GetAllReq argGetAllReq) {
        GetAllWoTypeInfoRes objGetAllWoTypeInfoRes = new GetAllWoTypeInfoRes();
        GetAllWoTypeInfoResB objGetAllWoTypeInfoResB = new GetAllWoTypeInfoResB();
        if ("00".equals(argGetAllReq.getExecType())) {
            List<InitDataField> objEInitDataFields = null;
            PageInfo pageInfo = null;

            if (argGetAllReq.getInitData() != null && !"".equals(argGetAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, argGetAllReq.getInitData());

                if (objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if (argGetAllReq.getPageInfo() != null && !"".equals(argGetAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, argGetAllReq.getPageInfo());
            }
            objGetAllWoTypeInfoResB = woTypeIService.GetAllWTInfo(objEInitDataFields, pageInfo);
        }
        objGetAllWoTypeInfoRes.setBody(objGetAllWoTypeInfoResB);
        objGetAllWoTypeInfoRes.setStatus("00");
        return objGetAllWoTypeInfoRes;
    }

    // 查询所有工单类型
    @ResponseBody
    @RequestMapping(value = "/GetAllWTInfoNew", method = RequestMethod.POST)
    public BaseResponse GetAllWTInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(woTypeIService.QALLGetAllWTNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    // 查询工单类型明细
    @ResponseBody
    @RequestMapping(value = "/GetWTInfo", method = RequestMethod.POST)
    public GetWoTypeInfoRes GetWTInfo(GetAllReq argGetAllReq) {
        GetWoTypeInfoRes objGetWoTypeInfoRes = new GetWoTypeInfoRes();
        GetWoTypeInfoResB objGetWoTypeInfoResB = new GetWoTypeInfoResB();
        if ("00".equals(argGetAllReq.getExecType())) {
            GetWoTypeInfoReqBD00 busData00 = CommonUtils.switchClass(GetWoTypeInfoReqBD00.class, argGetAllReq.getBusData());
            objGetWoTypeInfoResB = woTypeIService.GetWTInfo(busData00.getWTRd());
        }
        objGetWoTypeInfoRes.setBody(objGetWoTypeInfoResB);
        objGetWoTypeInfoRes.setStatus("00");
        return objGetWoTypeInfoRes;
    }

    //保存工单信息
    @ResponseBody
    @RequestMapping(value = "/SaveWTInfo", method = RequestMethod.POST)

    public SaveWoTypeInfoRes SaveWTInfo(SaveReq argSaveReq) {
        SaveWoTypeInfoRes objSaveWoTypeInfoRes = new SaveWoTypeInfoRes();
        SaveWoTypeInfoResB objSaveWoTypeInfoResB = new SaveWoTypeInfoResB();

        if ("00".equals(argSaveReq.getExecType())) {
            try {
                SaveWoTypeInfoReqBD00 busData00 = CommonUtils.switchClass(SaveWoTypeInfoReqBD00.class, argSaveReq.getBusData());
                objSaveWoTypeInfoResB = woTypeIService.AddWoType(busData00);
            } catch (SystemException e) {
                objSaveWoTypeInfoResB.setMsgCode(e.getMsgCode());
                objSaveWoTypeInfoResB.setMsgDes(e.getMsgDes());
            }
        } else if ("01".equals(argSaveReq.getExecType())) {
            try {
                SaveWoTypeInfoReqBD01 busData01 = CommonUtils.switchClass(SaveWoTypeInfoReqBD01.class, argSaveReq.getBusData());
                objSaveWoTypeInfoResB = woTypeIService.RmWoType(busData01);
            } catch (SystemException e) {
                objSaveWoTypeInfoResB.setMsgCode(e.getMsgCode());
                objSaveWoTypeInfoResB.setMsgDes(e.getMsgDes());
            }
        } else if ("02".equals(argSaveReq.getExecType())) {
            try {
                SaveWoTypeInfoReqBD02 busData02 = CommonUtils.switchClass(SaveWoTypeInfoReqBD02.class, argSaveReq.getBusData());
                objSaveWoTypeInfoResB = woTypeIService.ModWoType(busData02);
            } catch (SystemException e) {
                objSaveWoTypeInfoResB.setMsgCode(e.getMsgCode());
                objSaveWoTypeInfoResB.setMsgDes(e.getMsgDes());
            }

        }
        objSaveWoTypeInfoRes.setStatus("00");
        objSaveWoTypeInfoRes.setBody(objSaveWoTypeInfoResB);
        return objSaveWoTypeInfoRes;
    }

}
//保存工单信息
//@ResponseBody
//@RequestMapping(value = "/SaveWTInfo", method = RequestMethod.POST)
//    public SaveWoTypeInfoRes SaveWTInfo(HttpServletRequest request, SaveReq saveReq) {
//        SaveWoTypeInfoRes objSaveWoTypeInfoRes = new SaveWoTypeInfoRes();
//        SaveWoTypeInfoResB saveWoTypeInfoResB = new SaveWoTypeInfoResB();
//        String rowData = saveReq.getBusData();
//
//        //新增
//        if ("00".equals(saveReq.getExecType())) {
//            // JsonObject转换成实体类
//            SaveWoTypeInfoReqBD00 busData00 = CommonUtils.switchClass(SaveWoTypeInfoReqBD00.class, rowData);
//            try {
//                saveWoTypeInfoResB = woTypeIService.AddWoType(busData00);
//                saveWoTypeInfoResB.setMsgCode("0x00000");
//                saveWoTypeInfoResB.setMsgDes("成功");
////                  objSaveWoTypeInfoRes.getBody().setMsgCode("0x00000");
////                  objSaveWoTypeInfoRes.getBody().setMsgDes("成功");
//                objSaveWoTypeInfoRes.setBody(saveWoTypeInfoResB);
//            } catch (SystemException e) {
//                saveWoTypeInfoResB.setMsgCode(e.getMsgCode());
//                saveWoTypeInfoResB.setMsgDes(e.getMsgDes());
//                objSaveWoTypeInfoRes.setBody(saveWoTypeInfoResB);
//            }
//        }
//        //删除
//        else if ("01".equals(saveReq.getExecType())) {
//            SaveWoTypeInfoReqBD01 busData01 = CommonUtils.switchClass(SaveWoTypeInfoReqBD01.class, rowData);
//            try {
//                saveWoTypeInfoResB = woTypeIService.RmWoType(busData01);
//                SaveWoTypeInfoResB body = new SaveWoTypeInfoResB();
//                body.setMsgCode("0x00000");
//                body.setMsgDes("成功！");
//                objSaveWoTypeInfoRes.setBody(saveWoTypeInfoResB);
//            } catch (SystemException e) {
//                saveWoTypeInfoResB.setMsgCode(e.getMsgCode());
//                saveWoTypeInfoResB.setMsgDes(e.getMsgDes());
//                objSaveWoTypeInfoRes.setBody(saveWoTypeInfoResB);
//            }
//        }
//        //更新
//        else if ("02".equals(saveReq.getExecType())) {
//            SaveWoTypeInfoReqBD02 busData02 = CommonUtils.switchClass(SaveWoTypeInfoReqBD02.class, rowData);
//            try {
//                saveWoTypeInfoResB = woTypeIService.ModWoType(busData02);
//                SaveShiftInfoResB body = new SaveShiftInfoResB();
//                body.setMsgCode("0x00000");
//                body.setMsgDes("成功！");
//                objSaveWoTypeInfoRes.setBody(saveWoTypeInfoResB);
//            } catch (SystemException e) {
//                saveWoTypeInfoResB.setMsgCode(e.getMsgCode());
//                saveWoTypeInfoResB.setMsgDes(e.getMsgDes());
//                objSaveWoTypeInfoRes.setBody(saveWoTypeInfoResB);
//            }
//        }
//        objSaveWoTypeInfoRes.setStatus("00");
//        return objSaveWoTypeInfoRes;
//    }
//}
//


