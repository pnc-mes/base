package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMinTimeWindowInfo.GetAllMinTimeWindowInfoRes;
import pnc.mesadmin.dto.GetAllMinTimeWindowInfo.GetAllMinTimeWindowInfoResB;
import pnc.mesadmin.dto.GetMinTimedowInfo.GetMinTimedowInfoReqBD00;
import pnc.mesadmin.dto.GetMinTimedowInfo.GetMinTimedowInfoRes;
import pnc.mesadmin.dto.GetMinTimedowInfo.GetMinTimedowInfoResB;
import pnc.mesadmin.dto.SaveMinTimedowInfo.*;
import pnc.mesadmin.service.MinTimeWindowIServie;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PNC on 2018/7/28.
 */
@Controller
@Scope("prototype")
@RequestMapping("/MTV")
public class MinTimeWindowController {

    @Resource
    private MinTimeWindowIServie minTimeWindowIServie;
    //最小时间管控页面
    @RequestMapping(value="/MinTimeWindowinfo")
    public String getMinTimeWindowPage(){
        return "base/mintimewindow/MinTimeWindowinfo";
    }

    //dto查询最小时间管控信息列表
    @RequestMapping(value = "/GetAllMinTimeWindowInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMinTimeWindowInfoRes GetAllMinTimeWindowInfo(GetAllReq getAllReq) {
        GetAllMinTimeWindowInfoRes objGetAllMinTimeWindowInfoRes = new GetAllMinTimeWindowInfoRes();
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
                objGetAllMinTimeWindowInfoRes = minTimeWindowIServie.QALLGetAllMinTimeWindowInfoRes(objEInitDataFields, pageInfo);
                objGetAllMinTimeWindowInfoRes.getBody().setMsgCode("0x00000");
                objGetAllMinTimeWindowInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllMinTimeWindowInfoResB objGetAllMinTimeWindowInfoResB = new GetAllMinTimeWindowInfoResB();
                objGetAllMinTimeWindowInfoResB.setMsgCode(e.getMsgCode());
                objGetAllMinTimeWindowInfoResB.setMsgDes(e.getMsgDes());
                objGetAllMinTimeWindowInfoRes.setBody(objGetAllMinTimeWindowInfoResB);
            }
        }

        else{
            GetAllMinTimeWindowInfoResB objGetAllMinTimeWindowInfoResB = new GetAllMinTimeWindowInfoResB();
            objGetAllMinTimeWindowInfoResB.setMsgCode("MG0006F");
            objGetAllMinTimeWindowInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objGetAllMinTimeWindowInfoRes.setBody(objGetAllMinTimeWindowInfoResB);
        }
        objGetAllMinTimeWindowInfoRes.setStatus("00");
        return objGetAllMinTimeWindowInfoRes;
    }

    //获取最小时间管控信息
    @RequestMapping(value = "/GetMinTimeWindowInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetMinTimedowInfoRes GetMinTimeWindowInfo(GetAllReq getAllReq){
        GetMinTimedowInfoRes objGetMinTimedowInfoRes=new GetMinTimedowInfoRes();

        if("00".equals(getAllReq.getExecType())){
            GetMinTimedowInfoReqBD00 objEGetWOInfoReqBD00 = CommonUtils.switchClass(GetMinTimedowInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }else{

                try{
                    objGetMinTimedowInfoRes= minTimeWindowIServie.GetGetMinTimedowInfoRes(objEGetWOInfoReqBD00);
                    objGetMinTimedowInfoRes.getBody().setMsgCode("0x00000");
                    objGetMinTimedowInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetMinTimedowInfoResB objGetMinTimedowInfoResB=new GetMinTimedowInfoResB();
                    objGetMinTimedowInfoResB.setMsgCode(e.getMsgCode());
                    objGetMinTimedowInfoResB.setMsgDes(e.getMsgDes());
                    objGetMinTimedowInfoRes.setBody(objGetMinTimedowInfoResB);
                }
            }
        }
        else {
            GetMinTimedowInfoResB objGetMinTimedowInfoResB=new GetMinTimedowInfoResB();
            objGetMinTimedowInfoResB.setMsgCode("MG0006F");
            objGetMinTimedowInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objGetMinTimedowInfoRes.setBody(objGetMinTimedowInfoResB);
        }
        objGetMinTimedowInfoRes.setStatus("00");
        return objGetMinTimedowInfoRes;
    }
    @RequestMapping(value = "/SaveMinTimedowInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveMinTimedowInfoRes SaveMinTimedowInfo(SaveReq saveReq){
        SaveMinTimedowInfoRes objSaveMinTimedowInfoRes = new SaveMinTimedowInfoRes();
        SaveMinTimedowInfoResB objSaveMinTimedowInfoResB = null;
        try{
            if("00".equals(saveReq.getExecType())){

                SaveMinTimedowInfoReqBD00 objESaveWOInfoReqBD00=CommonUtils.switchClass(SaveMinTimedowInfoReqBD00.class,saveReq.getBusData());
                objSaveMinTimedowInfoRes= minTimeWindowIServie.AddSaveMinTimedowInfoRes(objESaveWOInfoReqBD00);
            } else if("01".equals(saveReq.getExecType())){
                SaveMinTimedowInfoReqBD01 objSaveMinTimedowInfoReqBD01=CommonUtils.switchClass(SaveMinTimedowInfoReqBD01.class,saveReq.getBusData());
                objSaveMinTimedowInfoRes= minTimeWindowIServie.RmSaveMinTimedowInfoRes(objSaveMinTimedowInfoReqBD01);
            }
            else if("02".equals(saveReq.getExecType())){
                SaveMinTimedowInfoReqBD02 objESaveWOInfoReqBD02=CommonUtils.switchClass(SaveMinTimedowInfoReqBD02.class,saveReq.getBusData());
                objSaveMinTimedowInfoRes= minTimeWindowIServie.ModSaveWOInfoRes(objESaveWOInfoReqBD02);
            }
            if(objSaveMinTimedowInfoRes.getBody() == null){
                objSaveMinTimedowInfoResB = new SaveMinTimedowInfoResB();
            }else{
                objSaveMinTimedowInfoResB = objSaveMinTimedowInfoRes.getBody();
            }
            objSaveMinTimedowInfoResB.setMsgCode("0x00000");
            objSaveMinTimedowInfoResB.setMsgDes("成功");
        }catch (SystemException e){
            objSaveMinTimedowInfoResB = new SaveMinTimedowInfoResB();
            objSaveMinTimedowInfoResB.setMsgCode(e.getMsgCode());
            objSaveMinTimedowInfoResB.setMsgDes(e.getMsgDes());
        }
        objSaveMinTimedowInfoRes.setStatus("00");
        objSaveMinTimedowInfoRes.setBody(objSaveMinTimedowInfoResB);
        return objSaveMinTimedowInfoRes;
    }
}
