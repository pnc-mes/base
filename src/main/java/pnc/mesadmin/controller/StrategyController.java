package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSgyInfo.GetAllSgyInfoRes;
import pnc.mesadmin.dto.GetAllSgyInfo.GetAllSgyInfoResB;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoReqBD00;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoRes;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoResB;
import pnc.mesadmin.dto.SaveSgyInfo.*;
import pnc.mesadmin.service.BatchLIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liufuzhi on 2018/1/30.
 */
@Controller
@RequestMapping("/Strategy")
public class StrategyController {
    @Resource
    private BatchLIService.StrategyIService strategyIService;

    //加载页面
    @RequestMapping("/SygPG")
    public String FilePG(){
        return "warehouse/syg/syginfo";
    }

    //查询收货策略列表
    @RequestMapping(value = "/GetAllSgyInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllSgyInfoRes GetAllSgyInfo(GetAllReq getAllReq){
        GetAllSgyInfoRes objEGetAllSgyInfoRes=new GetAllSgyInfoRes();
        if("00".equals(getAllReq.getExecType())) {
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
                objEGetAllSgyInfoRes = strategyIService.QALLGetAllSgyInfoRes(objEInitDataFields, pageInfo);
                objEGetAllSgyInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllSgyInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSgyInfoResB objEGetAllSgyInfoResB = new GetAllSgyInfoResB();
                objEGetAllSgyInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllSgyInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllSgyInfoRes.setBody(objEGetAllSgyInfoResB);
            }
        } else {
            GetAllSgyInfoResB objEGetAllSgyInfoResB = new GetAllSgyInfoResB();
            objEGetAllSgyInfoResB.setMsgCode("MG0006F");
            objEGetAllSgyInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllSgyInfoRes.setBody(objEGetAllSgyInfoResB);
        }
        objEGetAllSgyInfoRes.setStatus("00");
        return objEGetAllSgyInfoRes;
    }


    //查询收货策略信息
    @RequestMapping(value = "/GetSgyInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetSgyInfoRes GetSgyInfo(GetAllReq getAllReq){
        GetSgyInfoRes objEGetSgyInfoRes=new GetSgyInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetSgyInfoReqBD00 objEGetSgyInfoReqBD00 = CommonUtils.switchClass(GetSgyInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{
                try{
                    objEGetSgyInfoRes= strategyIService.GetGetSgyInfoRes(objEGetSgyInfoReqBD00);
                    objEGetSgyInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSgyInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetSgyInfoResB objEGetSgyInfoResB=new GetSgyInfoResB();
                    objEGetSgyInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSgyInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSgyInfoRes.setBody(objEGetSgyInfoResB);
                }
            }

        }else {
            GetSgyInfoResB objEGetSgyInfoResB=new GetSgyInfoResB();
            objEGetSgyInfoResB.setMsgCode("MG0006F");
            objEGetSgyInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetSgyInfoRes.setBody(objEGetSgyInfoResB);
        }
        objEGetSgyInfoRes.setStatus("00");

        return objEGetSgyInfoRes;
    }

    //新增收货策略
    @RequestMapping(value = "/SaveSgyInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveSgyInfoRes SaveSgyInfo(SaveReq saveReq){
        SaveSgyInfoRes objESaveSgyInfoRes=new SaveSgyInfoRes();
        SaveSgyInfoResB objESaveSgyInfoResB=new SaveSgyInfoResB();

        if("00".equals(saveReq.getExecType())){
            SaveSgyInfoReqBD00 objESaveSgyInfoReqBD00=CommonUtils.switchClass(SaveSgyInfoReqBD00.class,saveReq.getBusData());

            try{
                objESaveSgyInfoRes= strategyIService.AddSaveSgyInfo(objESaveSgyInfoReqBD00);
                objESaveSgyInfoRes.getBody().setMsgCode("0x00000");
                objESaveSgyInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveSgyInfoResB=new SaveSgyInfoResB();
                objESaveSgyInfoResB.setMsgCode(e.getMsgCode());
                objESaveSgyInfoResB.setMsgDes(e.getMsgDes());
                objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
            }
        }
        else if("01".equals(saveReq.getExecType())){
            SaveSgyInfoReqBD01 objESaveSgyInfoReqBD01 =CommonUtils.switchClass(SaveSgyInfoReqBD01.class,saveReq.getBusData());
            try{
                objESaveSgyInfoRes=strategyIService.RmSaveSgyInfo(objESaveSgyInfoReqBD01);
                objESaveSgyInfoResB=new SaveSgyInfoResB();
                objESaveSgyInfoResB.setMsgCode("0x00000");
                objESaveSgyInfoResB.setMsgDes("成功");
                objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
            }catch (SystemException e){
                objESaveSgyInfoResB=new SaveSgyInfoResB();
                objESaveSgyInfoResB.setMsgCode(e.getMsgCode());
                objESaveSgyInfoResB.setMsgDes(e.getMsgDes());
                objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
            }

        }
        else if("02".equals(saveReq.getExecType())){
            SaveSgyInfoReqBD02 objESaveSgyInfoReqBD02 =CommonUtils.switchClass(SaveSgyInfoReqBD02.class,saveReq.getBusData());

            try{
                objESaveSgyInfoRes= strategyIService.ModSaveSgyInfo(objESaveSgyInfoReqBD02);
                objESaveSgyInfoResB=new SaveSgyInfoResB();
                objESaveSgyInfoResB.setMsgCode("0x00000");
                objESaveSgyInfoResB.setMsgDes("成功");
                objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
            }catch (SystemException e){
                objESaveSgyInfoResB=new SaveSgyInfoResB();
                objESaveSgyInfoResB.setMsgCode(e.getMsgCode());
                objESaveSgyInfoResB.setMsgDes(e.getMsgDes());
                objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
            }

        }
        else {
            objESaveSgyInfoResB.setMsgCode("MG0006F");
            objESaveSgyInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
        }
        objESaveSgyInfoRes.setStatus("00");
        return objESaveSgyInfoRes;
    }


}
