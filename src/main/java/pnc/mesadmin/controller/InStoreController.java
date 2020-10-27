package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllMaSDTInfo.GetAllMaSDTInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaSDTInfo.GetAllMaSDTInfoRes;
import pnc.mesadmin.dto.GetAllMaSDTInfo.GetAllMaSDTInfoResB;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoReqBD01;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoRes;
import pnc.mesadmin.dto.GetAllMaSTInfo.GetAllMaSTInfoResB;
import pnc.mesadmin.service.InStoreIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存信息Controller
 * 创建人：刘福志
 * 创建时间：2017-6-16
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/InStore")
public class InStoreController {
    @Resource
    private InStoreIService inStoreIService;

    //加载页面
    @RequestMapping("/InStorePG")
    public String InStore() {
        return "warehouse/instock/instock/instock";
    }


    //加载成品库页面
    @RequestMapping("/CPInStorePG")
    public String CPInStore() {
        return "warehouse/instock/cpinstock/cpinstock";
    }

    //导出加载成品库页面
    @RequestMapping(value ="/CPExport",method = RequestMethod.POST, produces = {"text/html;charset=UTF-8;", "application/json;"})
    @ResponseBody
    public String CPExport(HttpServletResponse response,String MaVerRd,String StoreRd,String LRd,String F1,String F2,String F3,String F4,String CreateTime1,String CreateTime2,String Batch){

            try{
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = inStoreIService.CPExport(MaVerRd   ,StoreRd ,LRd,F1,F2,F3,F4,CreateTime1,CreateTime2,Batch);
                //设置response参数,可以打开下载页面
                String exported = "导出" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
                exported = new String(exported.getBytes("gbk"), "iso8859-1");
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + exported + ".xls");
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
            }catch (Exception e){
                e.printStackTrace();
            }

      return  "";
    }




    //获取物料库存信息
    @RequestMapping(value = "/GetAllMaSTInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMaSTInfoRes GetAllMaSTInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllMaSTInfoRes objEGetAllMaSTInfoRes = new GetAllMaSTInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllMaSTInfoReqBD00 busData00 = CommonUtils.switchClass(GetAllMaSTInfoReqBD00.class, busData);

            // 分页
            if (getAllReq.getPageInfo() != null) {

            } else {  // 不分页
                try {
                    objEGetAllMaSTInfoRes = inStoreIService.GetInstockInfo(busData00);
                    objEGetAllMaSTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllMaSTInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllMaSTInfoResB objEGetAllMaSTInfoResB = new GetAllMaSTInfoResB();
                    objEGetAllMaSTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllMaSTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllMaSTInfoRes.setBody(objEGetAllMaSTInfoResB);
                }
            }
        }
        else if ("01".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllMaSTInfoReqBD01 busData01 = CommonUtils.switchClass(GetAllMaSTInfoReqBD01.class, busData);

            // 分页
            if (getAllReq.getPageInfo() != null) {

            } else {  // 不分页
                try {
                    objEGetAllMaSTInfoRes = inStoreIService.GetselectInstockInfo(busData01);
                    objEGetAllMaSTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllMaSTInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllMaSTInfoResB objEGetAllMaSTInfoResB = new GetAllMaSTInfoResB();
                    objEGetAllMaSTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllMaSTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllMaSTInfoRes.setBody(objEGetAllMaSTInfoResB);
                }
            }
        }
        else if ("02".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllMaSTInfoReqBD01 busData01 = CommonUtils.switchClass(GetAllMaSTInfoReqBD01.class, busData);

            try {
                    objEGetAllMaSTInfoRes = inStoreIService.GetInstockInfo1(busData01);
                    objEGetAllMaSTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllMaSTInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllMaSTInfoResB objEGetAllMaSTInfoResB = new GetAllMaSTInfoResB();
                    objEGetAllMaSTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllMaSTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllMaSTInfoRes.setBody(objEGetAllMaSTInfoResB);
                }
        } else {
            GetAllMaSTInfoResB objEGetAllMaSTInfoResB = new GetAllMaSTInfoResB();
            objEGetAllMaSTInfoResB.setMsgCode("MG0006F");
            objEGetAllMaSTInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00、01、02");
            objEGetAllMaSTInfoRes.setBody(objEGetAllMaSTInfoResB);
        }

        objEGetAllMaSTInfoRes.setStatus("00");

        return objEGetAllMaSTInfoRes;
    }

    //获取物料库存信息
    @RequestMapping(value = "/GetAllMaSDTInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMaSDTInfoRes GetAllMaSDTInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllMaSDTInfoRes objEGetAllMaSDTInfoRes = new GetAllMaSDTInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllMaSDTInfoReqBD00 busData00 = CommonUtils.switchClass(GetAllMaSDTInfoReqBD00.class, busData);

            // 分页
            if (getAllReq.getPageInfo() != null) {

            } else {  // 不分页
                try {
                    objEGetAllMaSDTInfoRes = inStoreIService.GetselectInstockDtlInfo(busData00);
                    objEGetAllMaSDTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllMaSDTInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllMaSDTInfoResB objEGetAllMaSDTInfoResB = new GetAllMaSDTInfoResB();
                    objEGetAllMaSDTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllMaSDTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllMaSDTInfoRes.setBody(objEGetAllMaSDTInfoResB);
                }
            }
        } else {
            GetAllMaSDTInfoResB objEGetAllMaSDTInfoResB = new GetAllMaSDTInfoResB();
            objEGetAllMaSDTInfoResB.setMsgCode("MG0006F");
            objEGetAllMaSDTInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllMaSDTInfoRes.setBody(objEGetAllMaSDTInfoResB);
        }

        objEGetAllMaSDTInfoRes.setStatus("00");

        return objEGetAllMaSDTInfoRes;
    }

    //筛选
    @RequestMapping(value = "/GetAllMaSTInfo1", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMaSTInfoRes GetAllMaSTInfo1(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllMaSTInfoRes objEGetAllMaSTInfoRes = new GetAllMaSTInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllMaSTInfoReqBD01 busData01 = CommonUtils.switchClass(GetAllMaSTInfoReqBD01.class, busData);

            // 分页
            if (getAllReq.getPageInfo() != null) {

            } else {  // 不分页
                try {
                    objEGetAllMaSTInfoRes = inStoreIService.GetselectInstockInfo1(busData01);
                    objEGetAllMaSTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllMaSTInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllMaSTInfoResB objEGetAllMaSTInfoResB = new GetAllMaSTInfoResB();
                    objEGetAllMaSTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllMaSTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllMaSTInfoRes.setBody(objEGetAllMaSTInfoResB);
                }
            }
        } else {
            GetAllMaSTInfoResB objEGetAllMaSTInfoResB = new GetAllMaSTInfoResB();
            objEGetAllMaSTInfoResB.setMsgCode("MG0006F");
            objEGetAllMaSTInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllMaSTInfoRes.setBody(objEGetAllMaSTInfoResB);
        }

        objEGetAllMaSTInfoRes.setStatus("00");

        return objEGetAllMaSTInfoRes;
    }

    //导出库存主表信息
    @RequestMapping(value ="/ExportInStore",method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportInStore(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq){
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        if ("01".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetAllMaSTInfoReqBD01 busData01 = CommonUtils.switchClass(GetAllMaSTInfoReqBD01.class, busData);
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = inStoreIService.ExportInStore(busData01);
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

}
