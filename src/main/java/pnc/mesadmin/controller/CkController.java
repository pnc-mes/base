package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dao.CkTkDAO;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoReq00;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoRes;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoResB;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoReqBD01;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoReqBD00;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoRes;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoResB;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoReq00;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoRes;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResB;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoReq00;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoRes;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoResB;
import pnc.mesadmin.dto.GetCKTInfo.GetCKTInfoRes;
import pnc.mesadmin.dto.GetCKTInfo.GetCKTInfoResB;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoReq01;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoReq03;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoRes;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoResB;
import pnc.mesadmin.dto.SaveRKInfo.SaveRKInfoResB;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.CkTkIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by PNC on 2017/8/9.
 */
@Controller
@RequestMapping("/Ck")
public class CkController {
    @Resource
    private CkTkIService ckTkIService;

    @RequestMapping("/CkPG")
    public String CkInfo(){
        return "warehouse/ck/ckinfo";
    }
    //获取出库单类型
    @RequestMapping(value = "/GetCKTInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCKTInfoRes GetCKTInfo(GetAllReq getAllReq){
        GetCKTInfoRes objEGetCKTInfoRes=new GetCKTInfoRes();
        if("00".equals(getAllReq.getExecType())) {
            try {
                objEGetCKTInfoRes = ckTkIService.GetGetCKTInfoRes();
                objEGetCKTInfoRes.getBody().setMsgCode("0x00000");
                objEGetCKTInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetCKTInfoResB objEGetCKTInfoResB = new GetCKTInfoResB();
                objEGetCKTInfoResB.setMsgCode(e.getMsgCode());
                objEGetCKTInfoResB.setMsgDes(e.getMsgDes());
                objEGetCKTInfoRes.setBody(objEGetCKTInfoResB);
            }
        }
        else{
            GetCKTInfoResB objEGetCKTInfoResB = new GetCKTInfoResB();
            objEGetCKTInfoResB.setMsgCode("MG0006F");
            objEGetCKTInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetCKTInfoRes.setBody(objEGetCKTInfoResB);
        }
        objEGetCKTInfoRes.setStatus("00");
        return objEGetCKTInfoRes;
    }

    //获取出库单列表信息
    @RequestMapping(value = "/GetAllCKInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllCKInfoRes GetAllCKInfo(GetAllReq getAllReq){
        GetAllCKInfoRes objEGetAllCKInfoRes=new GetAllCKInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetAllCKInfoReq00 objEGetAllCKInfoReq00 = CommonUtils.switchClass(GetAllCKInfoReq00.class, getAllReq.getBusData());

            try {
                objEGetAllCKInfoRes = ckTkIService.GetGetAllCKInfoRes(objEGetAllCKInfoReq00);
                objEGetAllCKInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllCKInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllCKInfoResB objEGetAllCKInfoResB = new GetAllCKInfoResB();
                objEGetAllCKInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllCKInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllCKInfoRes.setBody(objEGetAllCKInfoResB);
            }
        }
        else {
            GetAllCKInfoResB objEGetAllCKInfoResB = new GetAllCKInfoResB();
            objEGetAllCKInfoResB.setMsgCode("MG0006F");
            objEGetAllCKInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllCKInfoRes.setBody(objEGetAllCKInfoResB);
        }

        objEGetAllCKInfoRes.setStatus("00");
        return objEGetAllCKInfoRes;
    }

    //获取出库单明细信息
    @RequestMapping(value = "/GetCKDlInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCKDlInfoRes GetCKDlInfo(GetAllReq getAllReq){
        GetCKDlInfoRes objEGetCKDlInfoRes=new GetCKDlInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetCKDlInfoReq00 objEGetCKDlInfoReq00 = CommonUtils.switchClass(GetCKDlInfoReq00.class, getAllReq.getBusData());

            try {
                objEGetCKDlInfoRes = ckTkIService.GetGetCKDlInfoRes(objEGetCKDlInfoReq00);
                objEGetCKDlInfoRes.getBody().setMsgCode("0x00000");
                objEGetCKDlInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetCKDlInfoResB objEGetCKDlInfoResB = new GetCKDlInfoResB();
                objEGetCKDlInfoResB.setMsgCode(e.getMsgCode());
                objEGetCKDlInfoResB.setMsgDes(e.getMsgDes());
                objEGetCKDlInfoRes.setBody(objEGetCKDlInfoResB);
            }
        }
        else {
            GetCKDlInfoResB objEGetCKDlInfoResB = new GetCKDlInfoResB();
            objEGetCKDlInfoResB.setMsgCode("MG0006F");
            objEGetCKDlInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetCKDlInfoRes.setBody(objEGetCKDlInfoResB);
        }

        objEGetCKDlInfoRes.setStatus("00");

        return objEGetCKDlInfoRes;
    }

    //导出一条出库数据
    @RequestMapping(value ="/ExportGetCKDlInfo",method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportGetCKDlInfo(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq){
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetCKDlInfoReq00[] objEGetCKDlInfoReq00 = CommonUtils.switchClass(GetCKDlInfoReq00[].class, busData);
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = ckTkIService.ExportGetCKDlInfo(objEGetCKDlInfoReq00);
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

    //导出所有出库单数据
    @RequestMapping(value ="/ExportGetCKDlInfo1",method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportGetCKDlInfo1(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq){
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllCKInfoReq00 objEGetAllCKInfoReq00 = CommonUtils.switchClass(GetAllCKInfoReq00.class, getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = ckTkIService.ExportGetCKDlInfo1(objEGetAllCKInfoReq00);
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

    //获取扫描出库信息
    @RequestMapping(value = "/GetCKBarInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCKBarInfoRes GetCKBarInfo(GetAllReq getAllReq){
        GetCKBarInfoRes objEGetCKBarInfoRes=new GetCKBarInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetCKBarInfoReq00 objEGetCKBarInfoReq00 = CommonUtils.switchClass(GetCKBarInfoReq00.class, getAllReq.getBusData());

            try {
                objEGetCKBarInfoRes = ckTkIService.GetGetCKBarInfoRes(objEGetCKBarInfoReq00);
                objEGetCKBarInfoRes.getBody().setMsgCode("0x00000");
                objEGetCKBarInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetCKBarInfoResB objEGetCKBarInfoResB = new GetCKBarInfoResB();
                objEGetCKBarInfoResB.setMsgCode(e.getMsgCode());
                objEGetCKBarInfoResB.setMsgDes(e.getMsgDes());
                objEGetCKBarInfoRes.setBody(objEGetCKBarInfoResB);
            }
        }
        else {
            GetCKBarInfoResB objEGetCKBarInfoResB = new GetCKBarInfoResB();
            objEGetCKBarInfoResB.setMsgCode("MG0006F");
            objEGetCKBarInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetCKBarInfoRes.setBody(objEGetCKBarInfoResB);
        }
        objEGetCKBarInfoRes.setStatus("00");
        return objEGetCKBarInfoRes;
    }

    //保存
    @RequestMapping(value = "/SaveCKInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveCKInfoRes SaveCKInfo(SaveReq saveReq){
        SaveCKInfoRes objESaveCKInfoRes=new SaveCKInfoRes();
        SaveCKInfoResB objESaveCKInfoResB=new SaveCKInfoResB();

        if("01".equals(saveReq.getExecType())){
            SaveCKInfoReq01 objESaveCKInfoReq01=CommonUtils.switchClass(SaveCKInfoReq01.class,saveReq.getBusData());
            try{
                objESaveCKInfoRes= ckTkIService.RmSaveCKInfoRes(objESaveCKInfoReq01);
                objESaveCKInfoResB=new SaveCKInfoResB();
                objESaveCKInfoResB.setMsgCode("0x00000");
                objESaveCKInfoResB.setMsgDes("成功");
                objESaveCKInfoRes.setBody(objESaveCKInfoResB);
            }catch (SystemException e){
                objESaveCKInfoResB=new SaveCKInfoResB();
                objESaveCKInfoResB.setMsgCode(e.getMsgCode());
                objESaveCKInfoResB.setMsgDes(e.getMsgDes());
                objESaveCKInfoRes.setBody(objESaveCKInfoResB);
            }
        }
        else if("03".equals(saveReq.getExecType())){
            SaveCKInfoReq03 objESaveCKInfoReq03=CommonUtils.switchClass(SaveCKInfoReq03.class,saveReq.getBusData());

            try{
                objESaveCKInfoRes= ckTkIService.GetSaveCKInfoRes(objESaveCKInfoReq03);
                objESaveCKInfoRes.getBody().setMsgCode("0x00000");
                objESaveCKInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objESaveCKInfoResB=new SaveCKInfoResB();
                objESaveCKInfoResB.setMsgCode(e.getMsgCode());
                objESaveCKInfoResB.setMsgDes(e.getMsgDes());
                objESaveCKInfoRes.setBody(objESaveCKInfoResB);
            }
        }
        else {
            objESaveCKInfoResB.setMsgCode("MG0006F");
            objESaveCKInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            objESaveCKInfoRes.setBody(objESaveCKInfoResB);
        }

        objESaveCKInfoRes.setStatus("00");
        return objESaveCKInfoRes;
    }
}
