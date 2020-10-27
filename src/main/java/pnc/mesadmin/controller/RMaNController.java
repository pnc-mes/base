package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoResB;
import pnc.mesadmin.dto.GetAllRMaNInfo.GetAllRMaNInfoRes;
import pnc.mesadmin.dto.GetAllRMaNInfo.GetAllRMaNInfoResB;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoReq00;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoRes;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoResB;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoReq00;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoRes;
import pnc.mesadmin.dto.GetRMaNTotalInfo.GetRMaNTotalInfoResB;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoReqBD00;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoResB;
import pnc.mesadmin.dto.SaveRMaNInfo.*;
import pnc.mesadmin.dto.SaveSNInfo.SaveSNInfoReqBD00;
import pnc.mesadmin.dto.SaveSNInfo.SaveSNInfoResB;
import pnc.mesadmin.service.RMaNIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
@Controller
@Scope("prototype")
@RequestMapping("/RMaN")
public class RMaNController {

    @Resource
    private RMaNIService rMaNIService;

    @RequestMapping("/RMaNPG")
    public String rman(){
        return "plan/wip/wipinfo";
    }

    //查询产成品通知单列表信息
    @RequestMapping(value = "/GetAllRMaNInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllRMaNInfoRes GetAllRMaNInfo(GetAllReq getAllReq){
        GetAllRMaNInfoRes objEGetAllRMaNInfoRes=new GetAllRMaNInfoRes();

        if("00".equals(getAllReq.getExecType())){

            try {
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

                objEGetAllRMaNInfoRes = rMaNIService.QALLGetAllRMaNInfoRes(objEInitDataFields, pageInfo);
                objEGetAllRMaNInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllRMaNInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllRMaNInfoResB objEGetAllRMaNInfoResB  = new GetAllRMaNInfoResB();
                objEGetAllRMaNInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllRMaNInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllRMaNInfoRes.setBody(objEGetAllRMaNInfoResB);
            }

        }else{
            GetAllRMaNInfoResB objEGetAllRMaNInfoResB  = new GetAllRMaNInfoResB();
            objEGetAllRMaNInfoResB.setMsgCode("MG0006F");
            objEGetAllRMaNInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllRMaNInfoRes.setBody(objEGetAllRMaNInfoResB);
        }

        objEGetAllRMaNInfoRes.setStatus("00");
        return objEGetAllRMaNInfoRes;
    }

    //查询产成品通知单信息
    @RequestMapping(value = "/GetRMaNInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRMaNInfoRes GetRMaNInfo(GetAllReq getAllReq){
        GetRMaNInfoRes objEGetRMaNInfoRes=new GetRMaNInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetRMaNInfoReq00 objEGetRMaNInfoReq00 = CommonUtils.switchClass(GetRMaNInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {

                try {
                    objEGetRMaNInfoRes = rMaNIService.GetGetRMaNInfoRes(objEGetRMaNInfoReq00);
                    objEGetRMaNInfoRes.getBody().setMsgCode("0x00000");
                    objEGetRMaNInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetRMaNInfoResB objEGetRMaNInfoResB = new GetRMaNInfoResB();
                    objEGetRMaNInfoResB.setMsgCode(e.getMsgCode());
                    objEGetRMaNInfoResB.setMsgDes(e.getMsgDes());
                    objEGetRMaNInfoRes.setBody(objEGetRMaNInfoResB);
                }
            }
        } else {
            GetRMaNInfoResB objEGetRMaNInfoResB = new GetRMaNInfoResB();
            objEGetRMaNInfoResB.setMsgCode("MG0006F");
            objEGetRMaNInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetRMaNInfoRes.setBody(objEGetRMaNInfoResB);
        }
        objEGetRMaNInfoRes.setStatus("00");
        return objEGetRMaNInfoRes;
    }

    //获取汇总信息
    @RequestMapping(value = "/GetRMaNTotalInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRMaNTotalInfoRes GetRMaNTotalInfo(SaveReq saveReq){
        GetRMaNTotalInfoRes objEGetRMaNTotalInfoRes=new GetRMaNTotalInfoRes();
        GetRMaNTotalInfoResB objEGetRMaNTotalInfoResB=null;
        if("00".equals(saveReq.getExecType())){
            GetRMaNTotalInfoReq00 objEGetRMaNTotalInfoReq00 =CommonUtils.switchClass(GetRMaNTotalInfoReq00.class,saveReq.getBusData());

            try{
                objEGetRMaNTotalInfoRes=rMaNIService.QALLGetRMaNTotalInfoRes(objEGetRMaNTotalInfoReq00);
                objEGetRMaNTotalInfoResB=objEGetRMaNTotalInfoRes.getBody();
                objEGetRMaNTotalInfoResB.setMsgCode("0x00000");
                objEGetRMaNTotalInfoResB.setMsgDes("成功");
                objEGetRMaNTotalInfoRes.setBody(objEGetRMaNTotalInfoResB);
            }catch (SystemException e){
                objEGetRMaNTotalInfoResB=new GetRMaNTotalInfoResB();
                objEGetRMaNTotalInfoResB.setMsgCode(e.getMsgCode());
                objEGetRMaNTotalInfoResB.setMsgDes(e.getMsgDes());
                objEGetRMaNTotalInfoRes.setBody(objEGetRMaNTotalInfoResB);
            }
        }else {
            objEGetRMaNTotalInfoResB.setMsgCode("MG0006F");
            objEGetRMaNTotalInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00");
            objEGetRMaNTotalInfoRes.setBody(objEGetRMaNTotalInfoResB);
        }
        objEGetRMaNTotalInfoRes.setStatus("00");

        return  objEGetRMaNTotalInfoRes;
    }

    //保存产成品信息
    @RequestMapping(value = "/SaveRMaNInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveRMaNInfoRes SaveRMaNInfo(SaveReq saveReq){
        SaveRMaNInfoRes objESaveRMaNInfoRes=new SaveRMaNInfoRes();
        SaveRMaNInfoResB objESaveRMaNInfoResB = new SaveRMaNInfoResB();

        if("00".equals(saveReq.getExecType())){
            SaveRMaNInfoReq00 objESaveRMaNInfoReq00 =CommonUtils.switchClass(SaveRMaNInfoReq00.class,saveReq.getBusData());

            try{
                objESaveRMaNInfoRes=rMaNIService.AddSaveRMaNInfoRes(objESaveRMaNInfoReq00);
                objESaveRMaNInfoRes.getBody().setMsgCode("0x00000");
                objESaveRMaNInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode(e.getMsgCode());
                objESaveRMaNInfoResB.setMsgDes(e.getMsgDes());
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }
        }
        else if("01".equals(saveReq.getExecType())){
            SaveRMaNInfoReq01 objESaveRMaNInfoReq01 =CommonUtils.switchClass(SaveRMaNInfoReq01.class,saveReq.getBusData());

            try{
                objESaveRMaNInfoRes=rMaNIService.RmSaveRMaNInfoRes(objESaveRMaNInfoReq01);
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode("0x00000");
                objESaveRMaNInfoResB.setMsgDes("成功");
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }catch (SystemException e){
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode(e.getMsgCode());
                objESaveRMaNInfoResB.setMsgDes(e.getMsgDes());
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }
        }
        else if("02".equals(saveReq.getExecType())){
            SaveRMaNInfoReq02 objESaveRMaNInfoReq02 =CommonUtils.switchClass(SaveRMaNInfoReq02.class,saveReq.getBusData());

            try{
               objESaveRMaNInfoRes=rMaNIService.ModSaveRMaNInfoRes(objESaveRMaNInfoReq02);
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode("0x00000");
                objESaveRMaNInfoResB.setMsgDes("成功");
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }catch (SystemException e){
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode(e.getMsgCode());
                objESaveRMaNInfoResB.setMsgDes(e.getMsgDes());
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }
        }
        else if("03".equals(saveReq.getExecType())){
            SaveRMaNInfoReq03 objESaveRMaNInfoReq03=CommonUtils.switchClass(SaveRMaNInfoReq03.class,saveReq.getBusData());

            try{
                objESaveRMaNInfoRes=rMaNIService.ModSaveRMaNInfoRes03(objESaveRMaNInfoReq03);
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode("0x00000");
                objESaveRMaNInfoResB.setMsgDes("成功");
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }catch (SystemException e){
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode(e.getMsgCode());
                objESaveRMaNInfoResB.setMsgDes(e.getMsgDes());
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }
        }
        else if("04".equals(saveReq.getExecType())){
            SaveRMaNInfoReq04 objESaveRMaNInfoReq04 =CommonUtils.switchClass(SaveRMaNInfoReq04.class,saveReq.getBusData());

            try{
                objESaveRMaNInfoRes=rMaNIService.ModSaveRMaNInfoRes04(objESaveRMaNInfoReq04);
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode("0x00000");
                objESaveRMaNInfoResB.setMsgDes("成功");
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }catch (SystemException e){
                objESaveRMaNInfoResB=new SaveRMaNInfoResB();
                objESaveRMaNInfoResB.setMsgCode(e.getMsgCode());
                objESaveRMaNInfoResB.setMsgDes(e.getMsgDes());
                objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
            }
        }
        else {
            objESaveRMaNInfoResB.setMsgCode("MG0006F");
            objESaveRMaNInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            objESaveRMaNInfoRes.setBody(objESaveRMaNInfoResB);
        }
        objESaveRMaNInfoRes.setStatus("00");
        return objESaveRMaNInfoRes;
    }
}
