package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevFInfo.GetAllDevFInfoRes;
import pnc.mesadmin.dto.GetAllDevFInfo.GetAllDevFInfoResB;
import pnc.mesadmin.dto.GetDevFInfo.GetDevFInfoReqBD00;
import pnc.mesadmin.dto.GetDevFInfo.GetDevFInfoRes;
import pnc.mesadmin.dto.GetDevFInfo.GetDevFInfoResB;
import pnc.mesadmin.dto.SaveDevFInfo.*;
import pnc.mesadmin.service.DeviceFIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备家族信息Controller
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/DeviceF")
public class DeviceFController {

    @Resource
    private DeviceFIService deviceFIService;

    //加载设备家族页面
    @RequestMapping("/DeviceFPG")
    public String DeviceFPG(){
        return "res/devfamily/devfamilyinfo";
    }

    //查询设备家族列表
    @RequestMapping(value = "/GetAllDevFInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllDevFInfoRes GetAllDevFInfo(GetAllReq getAllReq){
        GetAllDevFInfoRes objEGetAllDevFInfoRes=new GetAllDevFInfoRes();
        if("00".equals(getAllReq.getExecType())){
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
                try{
                    objEGetAllDevFInfoRes= deviceFIService.QALLGetAllDevFInfoRes(objEInitDataFields, pageInfo);
                    objEGetAllDevFInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllDevFInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetAllDevFInfoResB objEGetAllDevFInfoResB=new GetAllDevFInfoResB();
                    objEGetAllDevFInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllDevFInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllDevFInfoRes.setBody(objEGetAllDevFInfoResB);
                }

        }else {
            GetAllDevFInfoResB objEGetAllDevFInfoResB=new GetAllDevFInfoResB();
            objEGetAllDevFInfoResB.setMsgCode("MG0006F");
            objEGetAllDevFInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllDevFInfoRes.setBody(objEGetAllDevFInfoResB);
        }
        objEGetAllDevFInfoRes.setStatus("00");
        return objEGetAllDevFInfoRes;
    }


    //查询设备家族信息
    @RequestMapping(value = "/GetDevFInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetDevFInfoRes GetDevInfo(GetAllReq getAllReq){
        GetDevFInfoRes objEGetDevFInfoRes=new GetDevFInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetDevFInfoReqBD00 objEGetDevFInfoReqBD00 = CommonUtils.switchClass(GetDevFInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{
                try{
                    objEGetDevFInfoRes= deviceFIService.GetGetDevFInfoRes(objEGetDevFInfoReqBD00);
                    objEGetDevFInfoRes.getBody().setMsgCode("0x00000");
                    objEGetDevFInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetDevFInfoResB objEGetDevFInfoResB=new GetDevFInfoResB();
                    objEGetDevFInfoResB.setMsgCode(e.getMsgCode());
                    objEGetDevFInfoResB.setMsgDes(e.getMsgDes());
                    objEGetDevFInfoRes.setBody(objEGetDevFInfoResB);
                }
            }

        }else {
            GetDevFInfoResB objEGetDevFInfoResB=new GetDevFInfoResB();
            objEGetDevFInfoResB.setMsgCode("MG0006F");
            objEGetDevFInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetDevFInfoRes.setBody(objEGetDevFInfoResB);
        }
        objEGetDevFInfoRes.setStatus("00");

        return objEGetDevFInfoRes;
    }

    //新增设备家族
    @RequestMapping(value = "/SaveDevFInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveDevFInfoRes SaveDevInfo(SaveReq saveReq){
        SaveDevFInfoRes objESaveDevFInfoRes=new SaveDevFInfoRes();
        SaveDevFInfoResB objESaveDevFInfoResB=new SaveDevFInfoResB();

        if("00".equals(saveReq.getExecType())){
            SaveDevFInfoReqBD00 objESaveDevFInfoReqBD00=CommonUtils.switchClass(SaveDevFInfoReqBD00.class,saveReq.getBusData());

            try{
                objESaveDevFInfoRes= deviceFIService.AddSaveDevFInfoRes(objESaveDevFInfoReqBD00);
                objESaveDevFInfoResB=new SaveDevFInfoResB();
                objESaveDevFInfoResB.setMsgCode("0x00000");
                objESaveDevFInfoResB.setMsgDes("成功");
                objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
            }catch (SystemException e){
                objESaveDevFInfoResB=new SaveDevFInfoResB();
                objESaveDevFInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevFInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
            }
        }
        else if("01".equals(saveReq.getExecType())){
            SaveDevFInfoReqBD01 objESaveDevFInfoReqBD01 =CommonUtils.switchClass(SaveDevFInfoReqBD01.class,saveReq.getBusData());
            try{
                objESaveDevFInfoRes=deviceFIService.RmSaveDevFInfoRes(objESaveDevFInfoReqBD01);
                objESaveDevFInfoResB=new SaveDevFInfoResB();
                objESaveDevFInfoResB.setMsgCode("0x00000");
                objESaveDevFInfoResB.setMsgDes("成功");
                objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
            }catch (SystemException e){
                objESaveDevFInfoResB=new SaveDevFInfoResB();
                objESaveDevFInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevFInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
            }

        }
        else if("02".equals(saveReq.getExecType())){
            SaveDevFInfoReqBD02 objESaveDevFInfoReqBD02 =CommonUtils.switchClass(SaveDevFInfoReqBD02.class,saveReq.getBusData());

            try{
                objESaveDevFInfoRes= deviceFIService.ModSaveDevFInfoRes(objESaveDevFInfoReqBD02);
                objESaveDevFInfoResB=new SaveDevFInfoResB();
                objESaveDevFInfoResB.setMsgCode("0x00000");
                objESaveDevFInfoResB.setMsgDes("成功");
                objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
            }catch (SystemException e){
                objESaveDevFInfoResB=new SaveDevFInfoResB();
                objESaveDevFInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevFInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
            }

        }
        else {
            objESaveDevFInfoResB.setMsgCode("MG0006F");
            objESaveDevFInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
            objESaveDevFInfoRes.setBody(objESaveDevFInfoResB);
        }
        objESaveDevFInfoRes.setStatus("00");
        return objESaveDevFInfoRes;
    }



}
