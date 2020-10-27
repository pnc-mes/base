package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoRes;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoResB;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoRes;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoResB;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoRes;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoResB;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoRes;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoResB;
import pnc.mesadmin.dto.GetIncInfoRes.GetIncInfoReq00;
import pnc.mesadmin.dto.GetIncInfoRes.GetIncInfoRes;
import pnc.mesadmin.dto.GetIncInfoRes.GetIncInfoResB;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoReq00;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoResB;
import pnc.mesadmin.dto.GetWIncMaInfo.GetWIncMaInfoReqBD00;
import pnc.mesadmin.dto.GetWIncMaInfo.GetWIncMaInfoRes;
import pnc.mesadmin.dto.GetWIncMaInfo.GetWIncMaInfoResB;
import pnc.mesadmin.dto.SaveIChInfo.*;
import pnc.mesadmin.dto.SavePurchInfo.SavePurchInfoReq00;
import pnc.mesadmin.dto.SavePurchInfo.SavePurchInfoRes;
import pnc.mesadmin.dto.SavePurchInfo.SavePurchInfoResB;
import pnc.mesadmin.service.IchIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/Ich")
public class IchController {
    @Resource
    private IchIService ichIService;

    @RequestMapping(value = "/IchPG")
    public String getIch(){
        return "plan/incadd/incadd";
    }

    //获取来料单列表信息
    @RequestMapping(value = "/GetAllIChInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllIChInfoRes GetAllIChInfo(GetAllReq getAllReq){
        GetAllIChInfoRes objEGetAllIChInfoRes=new GetAllIChInfoRes();
        GetAllIChInfoResB objEGetAllIChInfoResB=new GetAllIChInfoResB();
        if("00".equals(getAllReq.getExecType())){

            List<InitDataField> objEInitDataFields = null;

            PageInfo pageInfo = null;

            if( getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())){
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if(objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if(getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())){
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }

            try{
                objEGetAllIChInfoRes= ichIService.QALLGetAllIChInfoRes(objEInitDataFields, pageInfo);
                objEGetAllIChInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllIChInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objEGetAllIChInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllIChInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllIChInfoRes.setBody(objEGetAllIChInfoResB);
            }
        }
        else{
            objEGetAllIChInfoResB.setMsgCode("MG0006F");
            objEGetAllIChInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllIChInfoRes.setBody(objEGetAllIChInfoResB);
        }
        objEGetAllIChInfoRes.setStatus("00");
        return objEGetAllIChInfoRes;
    }

    //获取来料单明细信息
    @RequestMapping(value = "/GetAllIDlInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllIDlInfoRes GetAllIDlInfo(GetAllReq getAllReq){
        GetAllIDlInfoRes objEGetAllIDlInfoRes=new GetAllIDlInfoRes();
        GetAllIDlInfoResB objEGetAllIDlInfoResB=new GetAllIDlInfoResB();
        if("00".equals(getAllReq.getExecType())){

            List<InitDataField> objEInitDataFields = null;

            PageInfo pageInfo = null;

            if( getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())){
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if(objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if(getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())){
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }

            try{
                objEGetAllIDlInfoRes= ichIService.QALLGetAllIDlInfoRes(objEInitDataFields, pageInfo);
                objEGetAllIDlInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllIDlInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objEGetAllIDlInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllIDlInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllIDlInfoRes.setBody(objEGetAllIDlInfoResB);
            }
        }
        else{
            objEGetAllIDlInfoResB.setMsgCode("MG0006F");
            objEGetAllIDlInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllIDlInfoRes.setBody(objEGetAllIDlInfoResB);
        }
        objEGetAllIDlInfoRes.setStatus("00");

        return objEGetAllIDlInfoRes;
    }

    //保存来料单
    @RequestMapping(value = "/SaveIChInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveIChInfoRes SaveIChInfo(SaveReq saveReq){
        SaveIChInfoRes objESaveIChInfoRes=new SaveIChInfoRes();
        SaveIChInfoResB objESaveIChInfoResB=new SaveIChInfoResB();
        if("00".equals(saveReq.getExecType())){
            SaveIChInfoReq00 objESaveIChInfoReq00= CommonUtils.switchClass(SaveIChInfoReq00.class,saveReq.getBusData());
            try{
                objESaveIChInfoRes= ichIService.AddSaveIChInfoRes(objESaveIChInfoReq00);
                objESaveIChInfoRes.getBody().setMsgCode("0x00000");
                objESaveIChInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveIChInfoResB=new SaveIChInfoResB();
                objESaveIChInfoResB.setMsgCode(e.getMsgCode());
                objESaveIChInfoResB.setMsgDes(e.getMsgDes());
                objESaveIChInfoRes.setBody(objESaveIChInfoResB);
            }
        } else if("01".equals(saveReq.getExecType())){
            SaveIChInfoReq01 objESaveIChInfoReq01= CommonUtils.switchClass(SaveIChInfoReq01.class,saveReq.getBusData());
            try{
                objESaveIChInfoRes= ichIService.RmSaveIChInfoRes(objESaveIChInfoReq01);
                objESaveIChInfoRes.getBody().setMsgCode("0x00000");
                objESaveIChInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveIChInfoResB=new SaveIChInfoResB();
                objESaveIChInfoResB.setMsgCode(e.getMsgCode());
                objESaveIChInfoResB.setMsgDes(e.getMsgDes());
                objESaveIChInfoRes.setBody(objESaveIChInfoResB);
            }
        } else if("02".equals(saveReq.getExecType())){
            SaveIChInfoReq02 objESaveIChInfoReq02= CommonUtils.switchClass(SaveIChInfoReq02.class,saveReq.getBusData());
            try{
                objESaveIChInfoRes= ichIService.ModSaveIChInfoRes(objESaveIChInfoReq02);
                objESaveIChInfoRes.getBody().setMsgCode("0x00000");
                objESaveIChInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveIChInfoResB=new SaveIChInfoResB();
                objESaveIChInfoResB.setMsgCode(e.getMsgCode());
                objESaveIChInfoResB.setMsgDes(e.getMsgDes());
                objESaveIChInfoRes.setBody(objESaveIChInfoResB);
            }
        } else if("03".equals(saveReq.getExecType())){
            SaveIChInfoReq03 objESaveIChInfoReq03= CommonUtils.switchClass(SaveIChInfoReq03.class,saveReq.getBusData());
            try{
                objESaveIChInfoRes= ichIService.ModSaveIChOn(objESaveIChInfoReq03);
                objESaveIChInfoRes.getBody().setMsgCode("0x00000");
                objESaveIChInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveIChInfoResB=new SaveIChInfoResB();
                objESaveIChInfoResB.setMsgCode(e.getMsgCode());
                objESaveIChInfoResB.setMsgDes(e.getMsgDes());
                objESaveIChInfoRes.setBody(objESaveIChInfoResB);
            }
        } else if("04".equals(saveReq.getExecType())){
            SaveIChInfoReq04 objESaveIChInfoReq04= CommonUtils.switchClass(SaveIChInfoReq04.class,saveReq.getBusData());
            try{
                objESaveIChInfoRes= ichIService.ModSaveIChOff(objESaveIChInfoReq04);
                objESaveIChInfoRes.getBody().setMsgCode("0x00000");
                objESaveIChInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveIChInfoResB=new SaveIChInfoResB();
                objESaveIChInfoResB.setMsgCode(e.getMsgCode());
                objESaveIChInfoResB.setMsgDes(e.getMsgDes());
                objESaveIChInfoRes.setBody(objESaveIChInfoResB);
            }
        } else {
            objESaveIChInfoResB.setMsgCode("MG0006F");
            objESaveIChInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00");
            objESaveIChInfoRes.setBody(objESaveIChInfoResB);
        }
        objESaveIChInfoRes.setStatus("00");
        return objESaveIChInfoRes;
    }

    //获取来料单信息
    @RequestMapping(value = "/GetIncInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetIncInfoRes GetIncInfo(GetAllReq getAllReq){
        GetIncInfoRes objEGetIncInfoRes=new GetIncInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetIncInfoReq00 objEGetIncInfoReq00 = CommonUtils.switchClass(GetIncInfoReq00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{

                try{
                    objEGetIncInfoRes=ichIService.GetGetIncInfoRes(objEGetIncInfoReq00);
                    objEGetIncInfoRes.getBody().setMsgCode("0x00000");
                    objEGetIncInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetIncInfoResB objEGetIncInfoResB=new GetIncInfoResB();
                    objEGetIncInfoResB.setMsgCode(e.getMsgCode());
                    objEGetIncInfoResB.setMsgDes(e.getMsgDes());
                    objEGetIncInfoRes.setBody(objEGetIncInfoResB);
                }
            }
        }
        else {
            GetIncInfoResB objEGetIncInfoResB=new GetIncInfoResB();
            objEGetIncInfoResB.setMsgCode("MG0006F");
            objEGetIncInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetIncInfoRes.setBody(objEGetIncInfoResB);
        }
        objEGetIncInfoRes.setStatus("00");

        return  objEGetIncInfoRes;
    }

    //获取来料收货未开单物料信息
    @RequestMapping(value = "/GetWIncMaInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetWIncMaInfoRes GetWIncMaInfo(GetAllReq getAllReq){
        GetWIncMaInfoRes objEGetWIncMaInfoRes = new GetWIncMaInfoRes();

        if("00".equals(getAllReq.getExecType())){
            GetWIncMaInfoReqBD00 objEGetWIncMaInfoReqBD00 = CommonUtils.switchClass(GetWIncMaInfoReqBD00.class, getAllReq.getBusData());

            try {
                objEGetWIncMaInfoRes = ichIService.GetWIncMaInfo(objEGetWIncMaInfoReqBD00);
                objEGetWIncMaInfoRes.getBody().setMsgCode("0x00000");
                objEGetWIncMaInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetWIncMaInfoResB objEGetWIncMaInfoResB = new GetWIncMaInfoResB();
                objEGetWIncMaInfoResB.setMsgCode(e.getMsgCode());
                objEGetWIncMaInfoResB.setMsgDes(e.getMsgDes());
                objEGetWIncMaInfoRes.setBody(objEGetWIncMaInfoResB);
            }
        }

        objEGetWIncMaInfoRes.setStatus("00");

        return objEGetWIncMaInfoRes;
    }
}
