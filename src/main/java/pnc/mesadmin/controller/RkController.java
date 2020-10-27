package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoReqBD00;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoRes;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoResB;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoReqBD00;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoRes;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoResB;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoRes;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoResB;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoReqBD00;
import pnc.mesadmin.dto.GetRKInfo.GetRKInfoReqBD00;
import pnc.mesadmin.dto.GetRKInfo.GetRKInfoRes;
import pnc.mesadmin.dto.GetRKInfo.GetRKInfoResB;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoRes;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoResB;
import pnc.mesadmin.dto.SaveRKInfo.*;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveUserInfo.SaveUserInfoReqBD00;
import pnc.mesadmin.dto.SaveUserInfo.SaveUserInfoReqBD00Role;
import pnc.mesadmin.dto.SaveWCInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.CompleteRkTkIService;
import pnc.mesadmin.service.RkIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：入库单管理Controller
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Rk")
public class RkController {
    @Resource
    private RkIService rkIService;

    @Resource
    private CompleteRkTkIService completeRkTkIService;

    @RequestMapping("/RKPG")
    public String Rkinfo(){
        return "warehouse/rk/rkinfo";
    }

    @RequestMapping("/ADDRk")
    public String ADRk(){
        return "warehouse/rk/rkadd";
    }

    @RequestMapping("/ModifyRk/{RkRd}")
    public String ModifyRk(HttpServletRequest request, @PathVariable("RkRd") String RkRd){
        request.setAttribute("RkRd",RkRd);
        return "warehouse/rk/rkmodify";
    }

    //dto  获取入库单类型信息
    @RequestMapping(value = "/GetRKTInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRKTInfoRes GetRKTInfo(GetAllReq getAllReq){
        GetRKTInfoRes objEGetRKTInfoRes=new GetRKTInfoRes();
        JSONObject jsonObject = JSONObject.fromObject(getAllReq.getBusData());
        if("00".equals(getAllReq.getExecType())) {
            try {
                objEGetRKTInfoRes = rkIService.QALLGetRKTInfoRes();
                objEGetRKTInfoRes.getBody().setMsgCode("0x00000");
                objEGetRKTInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetRKTInfoResB objEGetRKTInfoResB = new GetRKTInfoResB();
                objEGetRKTInfoResB.setMsgCode(e.getMsgCode());
                objEGetRKTInfoResB.setMsgDes(e.getMsgDes());
                objEGetRKTInfoRes.setBody(objEGetRKTInfoResB);
            }
        }
        else{
            GetRKTInfoResB objEGetRKTInfoResB = new GetRKTInfoResB();
            objEGetRKTInfoResB.setMsgCode("MG0006F");
            objEGetRKTInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetRKTInfoRes.setBody(objEGetRKTInfoResB);
        }

        objEGetRKTInfoRes.setStatus("00");
        return objEGetRKTInfoRes;
    }

    //dto  获取入库单列表
    @RequestMapping(value = "/GetAllRKInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllRKInfoRes GetAllRKInfo(GetAllReq getAllReq){
        GetAllRKInfoRes objEGetAllRKInfoRes=new GetAllRKInfoRes();
        String str="";
        if("00".equals(getAllReq.getExecType())){
            GetAllRKInfoReqBD00 objEGetAllRKInfoReqBD00 = CommonUtils.switchClass(GetAllRKInfoReqBD00.class,getAllReq.getBusData());
            if("00".equals(objEGetAllRKInfoReqBD00.getRkTCode())){
                str="00";
            }
            if("01".equals(objEGetAllRKInfoReqBD00.getRkTCode())){
                str="02";
            }
            if("02".equals(objEGetAllRKInfoReqBD00.getRkTCode())){
                str="03";
            }
            objEGetAllRKInfoReqBD00.setRkTCode(str);

            if (getAllReq.getPageInfo() != null){


            }else{

                try{
                    objEGetAllRKInfoRes= rkIService.GetGetAllRKInfoRes(objEGetAllRKInfoReqBD00);
                    objEGetAllRKInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllRKInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetAllRKInfoResB objEGetAllRKInfoResB=new GetAllRKInfoResB();
                    objEGetAllRKInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllRKInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllRKInfoRes.setBody(objEGetAllRKInfoResB);
                }
            }
        }
        else{
            GetAllRKInfoResB objEGetAllRKInfoResB = new GetAllRKInfoResB();
            objEGetAllRKInfoResB.setMsgCode("MG0006F");
            objEGetAllRKInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllRKInfoRes.setBody(objEGetAllRKInfoResB);
        }

        objEGetAllRKInfoRes.setStatus("00");
        return objEGetAllRKInfoRes;
    }

    //dto获取入库单信息
    @RequestMapping(value = "/GetRKInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRKInfoRes GetRKInfo(GetAllReq getAllReq){
        GetRKInfoRes objEGetRKInfoRes=new GetRKInfoRes();
        if("00".equals(getAllReq.getExecType())){
            /*JSONObject objEJSONObject=JSONObject.fromObject(getAllReq.getBusData());*/
            GetRKInfoReqBD00 objEGetRKInfoReqBD00 =CommonUtils.switchClass(GetRKInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }else{

                try{
                    objEGetRKInfoRes= rkIService.GetGetRKInfoRes(objEGetRKInfoReqBD00);
                    objEGetRKInfoRes.getBody().setMsgCode("0x00000");
                    objEGetRKInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetRKInfoResB objEGetRKInfoResB=new GetRKInfoResB();
                    objEGetRKInfoResB.setMsgCode(e.getMsgCode());
                    objEGetRKInfoResB.setMsgDes(e.getMsgDes());
                    objEGetRKInfoRes.setBody(objEGetRKInfoResB);
                }
            }

        }
        else{
            GetRKInfoResB objEGetRKInfoResB = new GetRKInfoResB();
            objEGetRKInfoResB.setMsgCode("MG0006F");
            objEGetRKInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetRKInfoRes.setBody(objEGetRKInfoResB);
        }

        objEGetRKInfoRes.setStatus("00");


        return objEGetRKInfoRes;
    }

    //dto获取入库单条码信息
    @RequestMapping(value = "/GetRKBarInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRKBarInfoRes GetRKBarInfo(GetAllReq getAllReq){
        GetRKBarInfoRes objEGetRKBarInfoRes=new GetRKBarInfoRes();
        if("00".equals(getAllReq.getExecType())){
            GetRKBarInfoReqBD00 objEGetRKBarInfoReqBD00 =CommonUtils.switchClass(GetRKBarInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }else{

                try{
                    objEGetRKBarInfoRes= rkIService.GetGetRKBarInfoRes(objEGetRKBarInfoReqBD00);
                    objEGetRKBarInfoRes.getBody().setMsgCode("0x00000");
                    objEGetRKBarInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetRKBarInfoResB objGetRKBarInfoResB=new GetRKBarInfoResB();
                    objGetRKBarInfoResB.setMsgCode(e.getMsgCode());
                    objGetRKBarInfoResB.setMsgDes(e.getMsgDes());
                    objEGetRKBarInfoRes.setBody(objGetRKBarInfoResB);
                }
            }
        }
        else{
            GetRKBarInfoResB objEGetRKBarInfoResB = new GetRKBarInfoResB();
            objEGetRKBarInfoResB.setMsgCode("MG0006F");
            objEGetRKBarInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetRKBarInfoRes.setBody(objEGetRKBarInfoResB);
        }
        objEGetRKBarInfoRes.setStatus("00");
        return objEGetRKBarInfoRes;
    }

    //dto获取入库单明细信息
    @RequestMapping(value = "/GetRKDlInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRKDlInfoRes GetRKDlInfo(GetAllReq getAllReq){
        GetRKDlInfoRes objEGetRKDlInfoRes=new GetRKDlInfoRes();
        if("00".equals(getAllReq.getExecType())){
           // JSONObject objEJSONObject=JSONObject.fromObject(getAllReq.getBusData());
            GetRKDlInfoReqBD00 objEGetRKDlInfoReqBD00 =CommonUtils.switchClass(GetRKDlInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }else{

                try{
                    objEGetRKDlInfoRes= rkIService.GetGetRKDlInfoRes(objEGetRKDlInfoReqBD00);
                    objEGetRKDlInfoRes.getBody().setMsgCode("0x00000");
                    objEGetRKDlInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetRKDlInfoResB objEGetRKDlInfoResB=new GetRKDlInfoResB();
                    objEGetRKDlInfoResB.setMsgCode(e.getMsgCode());
                    objEGetRKDlInfoResB.setMsgDes(e.getMsgDes());
                    objEGetRKDlInfoRes.setBody(objEGetRKDlInfoResB);
                }
            }

        }else{
            GetRKDlInfoResB objEGetRKDlInfoResB = new GetRKDlInfoResB();
            objEGetRKDlInfoResB.setMsgCode("MG0006F");
            objEGetRKDlInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetRKDlInfoRes.setBody(objEGetRKDlInfoResB);
        }
        objEGetRKDlInfoRes.setStatus("00");

        return objEGetRKDlInfoRes;
    }

    //导出入库单明细
    @RequestMapping(value ="/ExportGetRKInfo",method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportGetRKInfo(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq){
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetRKDlInfoReqBD00[] objEGetRKDlInfoReqBD00 =CommonUtils.switchClass(GetRKDlInfoReqBD00[].class,busData);
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = rkIService.ExportGetRKInfo(objEGetRKDlInfoReqBD00);
                exportSWareInfoRes.setStatus("00");
                exportSWareInfoResB.setMsgCode("0x00000");
                exportSWareInfoResB.setMsgDes("成功!");
                exportSWareInfoRes.setBody(exportSWareInfoResB);

                //设置response参数,可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + "test" + ".xls");

                byte[] content = os.toByteArray();
                InputStream is = new ByteArrayInputStream(content);

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;

                try {
                    ServletOutputStream out = response.getOutputStream();

                    bis = new BufferedInputStream(is);
                    bos = new BufferedOutputStream(out);
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                        }
                    }
                }

            } catch (SystemException objSystemException) {
                exportSWareInfoResB.setMsgCode(objSystemException.getMsgCode());
                exportSWareInfoResB.setMsgDes(objSystemException.getMsgDes());
                exportSWareInfoRes.setBody(exportSWareInfoResB);
                exportSWareInfoRes.setStatus("01");
            }
        }
        return exportSWareInfoRes;
    }


    //dto新增工作中心
    @RequestMapping(value = "/SaveRKInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveRKInfoRes SaveRKInfo(SaveReq saveReq){
        SaveRKInfoRes objESaveRKInfoRes= new SaveRKInfoRes();
        SaveRKInfoResB objESaveRKInfoResB=null;

      /*  if("00".equals(saveReq.getExecType())){

            SaveRKInfoReqBD00 objESaveRKInfoReqBD00=CommonUtils.switchClass(SaveRKInfoReqBD00.class,saveReq.getBusData());

            try{
                objESaveRKInfoRes= rkIService.AddSaveRKInfoRes(objESaveRKInfoReqBD00);
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode("0x00000");
                objESaveRKInfoResB.setMsgDes("成功");
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }catch (SystemException e){
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode(e.getMsgCode());
                objESaveRKInfoResB.setMsgDes(e.getMsgDes());
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }
        }
        else*/  if("01".equals(saveReq.getExecType())){
            SaveRKInfoReqBD01 objESaveRKInfoReqBD01=CommonUtils.switchClass(SaveRKInfoReqBD01.class,saveReq.getBusData());

            try{
                objESaveRKInfoRes= rkIService.RmSaveRKInfoRes(objESaveRKInfoReqBD01);
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode("0x00000");
                objESaveRKInfoResB.setMsgDes("成功");
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }catch (SystemException e){
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode(e.getMsgCode());
                objESaveRKInfoResB.setMsgDes(e.getMsgDes());
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }
        }
        else   if("02".equals(saveReq.getExecType())){

            SaveRKInfoReqBD02 objESaveRKInfoReqBD02=CommonUtils.switchClass(SaveRKInfoReqBD02.class,saveReq.getBusData());
            try{
                objESaveRKInfoRes= rkIService.MoSaveRKInfoRess(objESaveRKInfoReqBD02);

                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode("0x00000");
                objESaveRKInfoResB.setMsgDes("成功");
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }catch (SystemException e){
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode(e.getMsgCode());
                objESaveRKInfoResB.setMsgDes(e.getMsgDes());
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }
        }


      else  if("03".equals(saveReq.getExecType())){
            SaveRKInfoReqBD03 objESaveRKInfoReqBD03=CommonUtils.switchClass(SaveRKInfoReqBD03.class,saveReq.getBusData());

            try{
                objESaveRKInfoRes= rkIService.MoSaveRKInfoRes(objESaveRKInfoReqBD03);

                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode("0x00000");
                objESaveRKInfoResB.setMsgDes("成功");
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }catch (SystemException e){
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode(e.getMsgCode());
                objESaveRKInfoResB.setMsgDes(e.getMsgDes());
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }
        }
        else  if("04".equals(saveReq.getExecType())){
            SaveRKInfoReqBD04 objESaveRKInfoReqBD04=CommonUtils.switchClass(SaveRKInfoReqBD04.class,saveReq.getBusData());
            try{
                objESaveRKInfoRes= completeRkTkIService.CompleteRkTk(objESaveRKInfoReqBD04.getRkRd());
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode("0x00000");
                objESaveRKInfoResB.setMsgDes("成功");
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }catch (SystemException e){
                objESaveRKInfoResB=new SaveRKInfoResB();
                objESaveRKInfoResB.setMsgCode(e.getMsgCode());
                objESaveRKInfoResB.setMsgDes(e.getMsgDes());
                objESaveRKInfoRes.setBody(objESaveRKInfoResB);
            }
        }

        else{
            objESaveRKInfoResB.setMsgCode("MG0006F");
            objESaveRKInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            objESaveRKInfoRes.setBody(objESaveRKInfoResB);
        }

        objESaveRKInfoRes.setStatus("00");
        return objESaveRKInfoRes;
    }




}
