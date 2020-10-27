package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoRes;
import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoResB;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoReq00;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoRes;
import pnc.mesadmin.dto.GetDevSInfo.GetDevSInfoResB;
import pnc.mesadmin.dto.SaveDevSInfo.*;
import pnc.mesadmin.service.DevStateIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PNC on 2017/8/18.
 */
@Controller
@Scope("prototype")
@RequestMapping("/DevState")
public class DevStateController {

    @Resource
    private DevStateIService devStateIService;

    @RequestMapping("/DevStatePG")
    public  String DevState(){
        return "res/devstate/devstateinfo";
    }

    //查询列表信息
    @RequestMapping(value = "/GetAllDevSInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllDevSInfoRes GetAllDevInfo(GetAllReq getAllReq){
        GetAllDevSInfoRes objEGetAllDevSInfoRes=new GetAllDevSInfoRes();
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
                    objEGetAllDevSInfoRes=devStateIService.QALLGetAllDevSInfoRes(objEInitDataFields, pageInfo);
                    objEGetAllDevSInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllDevSInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetAllDevSInfoResB objEGetAllDevSInfoResB=new GetAllDevSInfoResB();
                    objEGetAllDevSInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllDevSInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllDevSInfoRes.setBody(objEGetAllDevSInfoResB);
                }

        }
        else {
            GetAllDevSInfoResB objEGetAllDevSInfoResB=new GetAllDevSInfoResB();
            objEGetAllDevSInfoResB.setMsgCode("MG0006F");
            objEGetAllDevSInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllDevSInfoRes.setBody(objEGetAllDevSInfoResB);
        }
        objEGetAllDevSInfoRes.setStatus("00");
        return objEGetAllDevSInfoRes;
    }

    //查询设备状态信息
    @RequestMapping(value = "/GetDevSInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetDevSInfoRes GetGetDevSInfoRes(GetAllReq getAllReq){
        GetDevSInfoRes objEGetDevSInfoRes=new GetDevSInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetDevSInfoReq00 objEGetDevSInfoReq00= CommonUtils.switchClass(GetDevSInfoReq00.class,getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null){


            }else{  // 不分页
                try{
                    objEGetDevSInfoRes = devStateIService.GetGetDevSInfoRes(objEGetDevSInfoReq00);
                    objEGetDevSInfoRes.getBody().setMsgCode("0x00000");
                    objEGetDevSInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetDevSInfoResB objEGetDevSInfoResB = new GetDevSInfoResB();
                    objEGetDevSInfoResB.setMsgCode(e.getMsgCode());
                    objEGetDevSInfoResB.setMsgDes(e.getMsgDes());
                    objEGetDevSInfoRes.setBody(objEGetDevSInfoResB);
                }
            }
        }
        else {
            GetDevSInfoResB objEGetDevSInfoResB = new GetDevSInfoResB();
            objEGetDevSInfoResB.setMsgCode("MG0006F");
            objEGetDevSInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetDevSInfoRes.setBody(objEGetDevSInfoResB);
        }
        objEGetDevSInfoRes.setStatus("00");

        return objEGetDevSInfoRes;
    }

    //新增设备状态信息
    @RequestMapping(value = "/SaveDevSInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveDevSInfoRes SaveDevSInfo(SaveReq saveReq){
        SaveDevSInfoRes objESaveDevSInfoRes=new SaveDevSInfoRes();
        SaveDevSInfoResB objESaveDevSInfoResB=new SaveDevSInfoResB();
        if("00".equals(saveReq.getExecType())){
            SaveDevSInfoReq00 objESaveDevSInfoReq00=CommonUtils.switchClass(SaveDevSInfoReq00.class,saveReq.getBusData());

            try{
                objESaveDevSInfoRes= devStateIService.AddSaveDevSInfoRes(objESaveDevSInfoReq00);
                objESaveDevSInfoRes.getBody().setMsgCode("0x00000");
                objESaveDevSInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveDevSInfoResB=new SaveDevSInfoResB();
                objESaveDevSInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevSInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
            }
        }
        else if("01".equals(saveReq.getExecType())){
            SaveDevSInfoReq01 objESaveDevSInfoReq01=CommonUtils.switchClass(SaveDevSInfoReq01.class,saveReq.getBusData());


            try{
                objESaveDevSInfoRes= devStateIService.RmSaveDevSInfoRes(objESaveDevSInfoReq01);
                objESaveDevSInfoRes.getBody().setMsgCode("0x00000");
                objESaveDevSInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveDevSInfoResB=new SaveDevSInfoResB();
                objESaveDevSInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevSInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
            }
        }
        else if("02".equals(saveReq.getExecType())){
            SaveDevSInfoReq02 objESaveDevSInfoReq02=CommonUtils.switchClass(SaveDevSInfoReq02.class,saveReq.getBusData());

            try{
                objESaveDevSInfoRes= devStateIService.ModSaveDevSInfoRes(objESaveDevSInfoReq02);
                objESaveDevSInfoRes.getBody().setMsgCode("0x00000");
                objESaveDevSInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveDevSInfoResB=new SaveDevSInfoResB();
                objESaveDevSInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevSInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
            }
        }
        else {
            objESaveDevSInfoResB.setMsgCode("MG0006F");
            objESaveDevSInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
            objESaveDevSInfoRes.setBody(objESaveDevSInfoResB);
        }
        objESaveDevSInfoRes.setStatus("00");


        return objESaveDevSInfoRes;
    }

}
