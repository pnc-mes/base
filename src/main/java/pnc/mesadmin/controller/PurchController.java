package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoRes;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoResB;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoRes;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoResB;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoReq00;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoRes;
import pnc.mesadmin.dto.GetPurchInfo.GetPurchInfoResB;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoReqBD00;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoResB;
import pnc.mesadmin.dto.SaveCpInfo.SaveCpInfoReqBD00;
import pnc.mesadmin.dto.SaveCpInfo.SaveCpInfoResB;
import pnc.mesadmin.dto.SavePurchInfo.*;
import pnc.mesadmin.service.PurchIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PNC on 2017/8/23.
 */
@Controller
@Scope("prototype")
@RequestMapping("/Purch")
public class PurchController {

    @Resource
    private PurchIService purchIService;

    @RequestMapping(value = "/PurchPG")
    public String getPurchInfo(){
        return "plan/purchadd/purchadd";
    }

    //获取采购单列表信息
    @RequestMapping(value = "/GetAllPurchInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllPurchInfoRes GetAllPurchInfo(GetAllReq getAllReq){
        GetAllPurchInfoRes objEGetAllPurchInfoRes=new GetAllPurchInfoRes();
        GetAllPurchInfoResB objEGetAllPurchInfoResB=new GetAllPurchInfoResB();
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
                objEGetAllPurchInfoRes= purchIService.QALLGetAllPurchInfo(objEInitDataFields, pageInfo);
                objEGetAllPurchInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPurchInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objEGetAllPurchInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPurchInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPurchInfoRes.setBody(objEGetAllPurchInfoResB);
            }
        }
        else{
            objEGetAllPurchInfoResB.setMsgCode("MG0006F");
            objEGetAllPurchInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllPurchInfoRes.setBody(objEGetAllPurchInfoResB);
        }
        objEGetAllPurchInfoRes.setStatus("00");
        return objEGetAllPurchInfoRes;
    }

    // 保存采购单管理
    @RequestMapping(value = "/SavePurchInfo",method = RequestMethod.POST)
    @ResponseBody
    public SavePurchInfoRes SavePurchInfo(SaveReq saveReq){
        SavePurchInfoRes objESavePurchInfoRes=new SavePurchInfoRes();
        SavePurchInfoResB objESavePurchInfoResB=new SavePurchInfoResB();
        if("00".equals(saveReq.getExecType())){
            SavePurchInfoReq00 objESavePurchInfoReq00= CommonUtils.switchClass(SavePurchInfoReq00.class,saveReq.getBusData());

            try{
                objESavePurchInfoRes= purchIService.AddSavePurchInfoRes(objESavePurchInfoReq00);
                objESavePurchInfoRes.getBody().setMsgCode("0x00000");
                objESavePurchInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESavePurchInfoResB=new SavePurchInfoResB();
                objESavePurchInfoResB.setMsgCode(e.getMsgCode());
                objESavePurchInfoResB.setMsgDes(e.getMsgDes());
                objESavePurchInfoRes.setBody(objESavePurchInfoResB);
            }
        }

       else if("01".equals(saveReq.getExecType())){
            SavePurchInfoReq01 objESavePurchInfoReq01= CommonUtils.switchClass(SavePurchInfoReq01.class,saveReq.getBusData());


            try{
                objESavePurchInfoRes= purchIService.RmSavePurchInfoRes(objESavePurchInfoReq01);
                objESavePurchInfoRes.getBody().setMsgCode("0x00000");
                objESavePurchInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESavePurchInfoResB=new SavePurchInfoResB();
                objESavePurchInfoResB.setMsgCode(e.getMsgCode());
                objESavePurchInfoResB.setMsgDes(e.getMsgDes());
                objESavePurchInfoRes.setBody(objESavePurchInfoResB);
            }
        }
        else if("02".equals(saveReq.getExecType())){
            SavePurchInfoReq02 objESavePurchInfoReq02= CommonUtils.switchClass(SavePurchInfoReq02.class,saveReq.getBusData());

            try{
                objESavePurchInfoRes= purchIService.ModSavePurchInfoRes(objESavePurchInfoReq02);
                objESavePurchInfoRes.getBody().setMsgCode("0x00000");
                objESavePurchInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESavePurchInfoResB=new SavePurchInfoResB();
                objESavePurchInfoResB.setMsgCode(e.getMsgCode());
                objESavePurchInfoResB.setMsgDes(e.getMsgDes());
                objESavePurchInfoRes.setBody(objESavePurchInfoResB);
            }
        }
        else if("03".equals(saveReq.getExecType())){
            SavePurchInfoReq03 objESavePurchInfoReq03= CommonUtils.switchClass(SavePurchInfoReq03.class,saveReq.getBusData());

            try{
                objESavePurchInfoRes= purchIService.ModSavePurchInfoRes03(objESavePurchInfoReq03);
                objESavePurchInfoRes.getBody().setMsgCode("0x00000");
                objESavePurchInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESavePurchInfoResB=new SavePurchInfoResB();
                objESavePurchInfoResB.setMsgCode(e.getMsgCode());
                objESavePurchInfoResB.setMsgDes(e.getMsgDes());
                objESavePurchInfoRes.setBody(objESavePurchInfoResB);
            }
        }else if("04".equals(saveReq.getExecType())){
            SavePurchInfoReq04 objESavePurchInfoReq04= CommonUtils.switchClass(SavePurchInfoReq04.class,saveReq.getBusData());

            try{
                objESavePurchInfoRes= purchIService.ModSavePurchInfoRes04(objESavePurchInfoReq04);
                objESavePurchInfoRes.getBody().setMsgCode("0x00000");
                objESavePurchInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESavePurchInfoResB=new SavePurchInfoResB();
                objESavePurchInfoResB.setMsgCode(e.getMsgCode());
                objESavePurchInfoResB.setMsgDes(e.getMsgDes());
                objESavePurchInfoRes.setBody(objESavePurchInfoResB);
            }
        }
        else {
            objESavePurchInfoResB.setMsgCode("MG0006F");
            objESavePurchInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00");
            objESavePurchInfoRes.setBody(objESavePurchInfoResB);
        }
        objESavePurchInfoRes.setStatus("00");
        return objESavePurchInfoRes;
    }

    //根据采购单任意字段查询采购单明细信息
    @RequestMapping(value = "/GetAllPDlInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllPDlInfoRes GetAllPDlInfo(GetAllReq getAllReq){
        GetAllPDlInfoRes objEGetAllPDlInfoRes=new GetAllPDlInfoRes();
        GetAllPDlInfoResB objEGetAllPDlInfoResB=new GetAllPDlInfoResB();
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
                objEGetAllPDlInfoRes= purchIService.QALLGetAllPDlInfoRes(objEInitDataFields, pageInfo);
                objEGetAllPDlInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPDlInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objEGetAllPDlInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPDlInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPDlInfoRes.setBody(objEGetAllPDlInfoResB);
            }
        }
        else{
            objEGetAllPDlInfoResB.setMsgCode("MG0006F");
            objEGetAllPDlInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllPDlInfoRes.setBody(objEGetAllPDlInfoResB);
        }
        objEGetAllPDlInfoRes.setStatus("00");

        return objEGetAllPDlInfoRes;
    }

    //根据采购单信息
    @RequestMapping(value = "/GetPurchInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetPurchInfoRes  GetPurchInfo(GetAllReq getAllReq){
        GetPurchInfoRes objEGetPurchInfoRes=new GetPurchInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetPurchInfoReq00 objEGetPurchInfoReq00 = CommonUtils.switchClass(GetPurchInfoReq00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{

                try{
                    objEGetPurchInfoRes= purchIService.GetGetPurchInfoRes(objEGetPurchInfoReq00);
                    objEGetPurchInfoRes.getBody().setMsgCode("0x00000");
                    objEGetPurchInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetPurchInfoResB objEGetPurchInfoResB=new GetPurchInfoResB();
                    objEGetPurchInfoResB.setMsgCode(e.getMsgCode());
                    objEGetPurchInfoResB.setMsgDes(e.getMsgDes());
                    objEGetPurchInfoRes.setBody(objEGetPurchInfoResB);
                }
            }
        }
        else {
            GetPurchInfoResB objEGetPurchInfoResB=new GetPurchInfoResB();
            objEGetPurchInfoResB.setMsgCode("MG0006F");
            objEGetPurchInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetPurchInfoRes.setBody(objEGetPurchInfoResB);
        }
        objEGetPurchInfoRes.setStatus("00");

        return objEGetPurchInfoRes;
    }
}
