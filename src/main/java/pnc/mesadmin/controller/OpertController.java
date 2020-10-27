package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoRes;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResB;
import pnc.mesadmin.dto.GetOpertInfo.GetOpertInfoReqBD00;
import pnc.mesadmin.dto.GetOpertInfo.GetOpertInfoRes;
import pnc.mesadmin.dto.GetOpertInfo.GetOpertInfoResB;
import pnc.mesadmin.dto.SaveOpertInfo.*;
import pnc.mesadmin.service.OpertIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import org.apache.commons.io.IOUtils;
import pnc.mesadmin.utils.baseUtil;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：作业信息Controller
 * 创建人：王怀龙
 * 创建时间：2017-06-01
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Opert")
public class OpertController {
    @Resource
    private OpertIService opertIService;

    //加载页面
    @RequestMapping("/OpertPG")
    public String OpertPG(){
        return "/process/opert/operinfo";
    }

    //跳转到加载页面
    @RequestMapping(value = "/FileViewPG")
    public String FileView(HttpServletRequest request,String Url, String FileName) {
        request.setAttribute("fileName", FileName);
        request.setAttribute("fileUrl", Url);
        return "/mprocess/specopert/fileview";
    }

    //加载文件
    @RequestMapping(value = "/GetFileView", method = RequestMethod.GET)
    public void GetFileView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName=request.getParameter("fileName");
        String fileUrl=request.getParameter("fileUrl");
        try
        {
            InputStream inputStream =CommonUtils.getFile(baseUtil.decodeStr(fileUrl));
            response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".pdf");
            response.setContentType("multipart/form-data");
            OutputStream outputStream = response.getOutputStream();

            IOUtils.write(IOUtils.toByteArray(inputStream), outputStream);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value="/GetAllOpertInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllOpertInfoRes GetAllOpertInfo(HttpServletRequest request, GetAllReq argGetAllReq){
        GetAllOpertInfoRes objEGetAllOpertInfoRes = new GetAllOpertInfoRes();
        //argGetAllReq.setPageInfo(pageInfo);
        if("00".equals(argGetAllReq.getExecType())) {
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
            try {

                objEGetAllOpertInfoRes = opertIService.QAllOpertInfo(objEInitDataFields, pageInfo);
                objEGetAllOpertInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllOpertInfoRes.getBody().setMsgDes("成功！");
            } catch (SystemException e) {
                objEGetAllOpertInfoRes = new GetAllOpertInfoRes();
                GetAllOpertInfoResB objEGetAllOpertInfoResB = new GetAllOpertInfoResB();
                objEGetAllOpertInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllOpertInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllOpertInfoRes.setBody(objEGetAllOpertInfoResB);
            }
        }
        return objEGetAllOpertInfoRes;
    }


    @RequestMapping(value="/GetAllOpertNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllOpertNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(opertIService.QALLGetAllOpertNew(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }


    @RequestMapping(value="/GetOpertInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetOpertInfoRes GetOpertInfo(HttpServletRequest request,  GetAllReq argGetAllReq){
        GetOpertInfoRes objEGetOpertInfoRes = null;
        GetOpertInfoReqBD00 getOpertInfoReqBD00  = CommonUtils.switchClass(GetOpertInfoReqBD00.class,argGetAllReq.getBusData());

        try {
            objEGetOpertInfoRes = opertIService.QOpertInfo(getOpertInfoReqBD00);
            objEGetOpertInfoRes.getBody().setMsgCode("0x00000");
            objEGetOpertInfoRes.getBody().setMsgDes("成功！");
        }catch(SystemException e){
            objEGetOpertInfoRes = new GetOpertInfoRes();
            GetOpertInfoResB objGetOpertInfoResB = new GetOpertInfoResB();
            objGetOpertInfoResB.setMsgCode(e.getMsgCode());
            objGetOpertInfoResB.setMsgDes(e.getMsgDes());
            objEGetOpertInfoRes.setBody(objGetOpertInfoResB);
        }
        objEGetOpertInfoRes.setStatus("00");
        return objEGetOpertInfoRes;
    }

    @RequestMapping(value="/SaveOpertInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveOpertInfoRes SaveOpertInfo(HttpServletRequest request,SaveReq argSaveReq){
        String execType = argSaveReq.getExecType();
        SaveOpertInfoRes objSaveOpertInfoRes = new SaveOpertInfoRes();
        SaveOpertInfoResB saveOpertInfoResB = new SaveOpertInfoResB();
        if("00".equals(execType)){
            SaveOpertInfoReqBD00 objSaveOpertInfoReqBD00 = CommonUtils.switchClass(SaveOpertInfoReqBD00.class,argSaveReq.getBusData());
           try {
               objSaveOpertInfoRes = opertIService.SaveOpertInfo(objSaveOpertInfoReqBD00);
               SaveOpertInfoResB body = new SaveOpertInfoResB();
               body.setMsgCode("0x00000");
               body.setMsgDes("成功！");
               objSaveOpertInfoRes.setBody(body);
           }catch (SystemException e){
               saveOpertInfoResB.setMsgCode(e.getMsgCode());
               saveOpertInfoResB.setMsgDes(e.getMsgDes());
               objSaveOpertInfoRes.setBody(saveOpertInfoResB);
           }
        }else if("01".equals(execType)){
            SaveOpertInfoReqBD01 objSaveOpertInfoReqBD01 =  CommonUtils.switchClass(SaveOpertInfoReqBD01.class,argSaveReq.getBusData());

            try{
                objSaveOpertInfoRes = opertIService.RmSaveOpertInfo(objSaveOpertInfoReqBD01);
                SaveOpertInfoResB body = new SaveOpertInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveOpertInfoRes.setBody(body);
            }catch (SystemException e){
                saveOpertInfoResB.setMsgCode(e.getMsgCode());
                saveOpertInfoResB.setMsgDes(e.getMsgDes());
                objSaveOpertInfoRes.setBody(saveOpertInfoResB);
            }

        }else if("02".equals(execType)){
            SaveOpertInfoReqBD02 objSaveOpertInfoReqBD02 =  CommonUtils.switchClass(SaveOpertInfoReqBD02.class,argSaveReq.getBusData());

            try{
                objSaveOpertInfoRes = opertIService.ModSaveOpertInfo(objSaveOpertInfoReqBD02);
                SaveOpertInfoResB body = new SaveOpertInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveOpertInfoRes.setBody(body);
            }catch (SystemException e){
                saveOpertInfoResB.setMsgCode(e.getMsgCode());
                saveOpertInfoResB.setMsgDes(e.getMsgDes());
                objSaveOpertInfoRes.setBody(saveOpertInfoResB);
            }

        }else if("03".equals(execType)){
            SaveOpertInfoReqBD03 objSaveOpertInfoReqBD03 =CommonUtils.switchClass(SaveOpertInfoReqBD03.class,argSaveReq.getBusData());
            try{
                objSaveOpertInfoRes = opertIService.SaveOpertInfoCopy(objSaveOpertInfoReqBD03);
                SaveOpertInfoResB body = new SaveOpertInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveOpertInfoRes.setBody(body);
            }catch (SystemException e){
                saveOpertInfoResB.setMsgCode(e.getMsgCode());
                saveOpertInfoResB.setMsgDes(e.getMsgDes());
                objSaveOpertInfoRes.setBody(saveOpertInfoResB);
            }
        }else{
            saveOpertInfoResB.setMsgCode("MG0006F");
            saveOpertInfoResB.setMsgDes("参数名"+execType+"中值应该等于00、01、02、03");
        }
        objSaveOpertInfoRes.setStatus("00");
        return objSaveOpertInfoRes;

    }

}
