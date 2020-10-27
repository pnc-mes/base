package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoReqBD;
import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoRes;
import pnc.mesadmin.dto.ExportPDCInfo.ExportPDCInfoResB;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoRes;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoResB;
import pnc.mesadmin.dto.GetPDCInfo.GetPDCInfoReqBD00;
import pnc.mesadmin.dto.GetPDCInfo.GetPDCInfoRes;
import pnc.mesadmin.dto.GetPDCInfo.GetPDCInfoResB;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoReqBD00;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoRes;
import pnc.mesadmin.dto.GetPDInfo.GetPDInfoResB;
import pnc.mesadmin.dto.SavePDInfo.*;
import pnc.mesadmin.entity.PDTkInfo;
import pnc.mesadmin.service.PDIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点单信息Controller
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/PD")
public class PDController {

    @Resource
    private PDIService pdiService;

    //加载页面
    @RequestMapping("/PDPG")
    public String PD(){
        return "warehouse/instock/inventory/inventory";
    }

    @RequestMapping("/ADDPDPG")
    public String ADDPDPG(){
        return "warehouse/instock/inventory/inventoryadd";
    }


    @RequestMapping("/EditPDPG/{PDRd}")
    public String EditWOGP( HttpServletRequest request, @PathVariable("PDRd") int PDRd){
        request.setAttribute("PDRd",PDRd);
        return "warehouse/instock/inventory/inventoryedit";
    }

    @RequestMapping("/PDPDPG/{PDRd}")
    public String PDPDPG( HttpServletRequest request, @PathVariable("PDRd") int PDRd){
        request.setAttribute("PDRd",PDRd);
        return "warehouse/instock/inventory/inventorycy";
    }

    //加载盘点差异表页面  王怀龙
    @RequestMapping("/PDVariance/{PDRd}")
    public String PDVariance(HttpServletRequest request, @PathVariable("PDRd") int PDRd){
        request.setAttribute("PDRd",PDRd);
        return "warehouse/instock/inventory/inventoryVariance";
    }

    //获取盘点单列表信息
    @RequestMapping(value = "/GetAllPDInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllPDInfoRes GetAllPDInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllPDInfoRes objEGetAllPDInfoRes=new GetAllPDInfoRes();

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
                objEGetAllPDInfoRes = pdiService.QALLselectAllPDTkInfo(objEInitDataFields, pageInfo);
                objEGetAllPDInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPDInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllPDInfoResB objEGetAllPDInfoResB = new GetAllPDInfoResB();
                objEGetAllPDInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPDInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPDInfoRes.setBody(objEGetAllPDInfoResB);
            }
        }else{
            GetAllPDInfoResB objEGetAllPDInfoResB = new GetAllPDInfoResB();
            objEGetAllPDInfoResB.setMsgCode("MG0006F");
            objEGetAllPDInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllPDInfoRes.setBody(objEGetAllPDInfoResB);
        }
        objEGetAllPDInfoRes.setStatus("00");
        return objEGetAllPDInfoRes;
    }

    //获取盘点单信息
    @RequestMapping(value = "/GetPDInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetPDInfoRes GetPDInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetPDInfoRes objEGetPDInfoRes=new GetPDInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetPDInfoReqBD00 busData00 =  CommonUtils.switchClass(GetPDInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetPDInfoRes = pdiService.GetselectByRuid(busData00.getPDRd());
                    objEGetPDInfoRes.getBody().setMsgCode("0x00000");
                    objEGetPDInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetPDInfoResB objEGetPDInfoResB = new GetPDInfoResB();
                    objEGetPDInfoResB.setMsgCode(e.getMsgCode());
                    objEGetPDInfoResB.setMsgDes(e.getMsgDes());
                    objEGetPDInfoRes.setBody(objEGetPDInfoResB);
                }
            }
        }else{
            GetPDInfoResB objEGetPDInfoResB = new GetPDInfoResB();
            objEGetPDInfoResB.setMsgCode("MG0006F");
            objEGetPDInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetPDInfoRes.setBody(objEGetPDInfoResB);
        }

        objEGetPDInfoRes.setStatus("00");

        return objEGetPDInfoRes;
    }

    //获取盘点差异信息
    @RequestMapping(value = "/GetPDCInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetPDCInfoRes GetPDCInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetPDCInfoRes objEGetPDCInfoRes=new GetPDCInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetPDCInfoReqBD00 busData00 = CommonUtils.switchClass(GetPDCInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetPDCInfoRes = pdiService.GetselectByPDRd(busData00);
                    objEGetPDCInfoRes.getBody().setMsgCode("0x00000");
                    objEGetPDCInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetPDCInfoResB objEGetPDCInfoResB = new GetPDCInfoResB();
                    objEGetPDCInfoResB.setMsgCode(e.getMsgCode());
                    objEGetPDCInfoResB.setMsgDes(e.getMsgDes());
                    objEGetPDCInfoRes.setBody(objEGetPDCInfoResB);
                }
            }
        }else{
            GetPDCInfoResB objEGetPDCInfoResB = new GetPDCInfoResB();
            objEGetPDCInfoResB.setMsgCode("MG0006F");
            objEGetPDCInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetPDCInfoRes.setBody(objEGetPDCInfoResB);
        }

        objEGetPDCInfoRes.setStatus("00");

        return objEGetPDCInfoRes;
    }



   //保存盘点单信息
    @RequestMapping(value ="/SavePDInfo",method = RequestMethod.POST)
    @ResponseBody
    public SavePDInfoRes SavePDInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SavePDInfoRes savePDInfoRes = new SavePDInfoRes();

        SavePDInfoResB savePDInfoResB = new SavePDInfoResB();

        PDTkInfo pdTkInfo = new PDTkInfo();

        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SavePDInfoReqBD00 busData00 = CommonUtils.switchClass(SavePDInfoReqBD00.class,rowData);

            // 直接可以获取的表单数据
            try {
                savePDInfoRes = pdiService.AddinsertPDTkInfo(busData00,pdTkInfo);
                savePDInfoRes.getBody().setMsgCode("0x00000");
                savePDInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                savePDInfoResB.setMsgCode(e.getMsgCode());
                savePDInfoResB.setMsgDes(e.getMsgDes());
                savePDInfoRes.setBody(savePDInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SavePDInfoReqBD01 busData01 = CommonUtils.switchClass(SavePDInfoReqBD01.class,rowData);

            try {
                savePDInfoRes = pdiService.RmdeletePDTkInfo(busData01.getPDRd());
                SavePDInfoResB body = new SavePDInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePDInfoRes.setBody(body);
            }catch (SystemException e){
                savePDInfoResB.setMsgCode(e.getMsgCode());
                savePDInfoResB.setMsgDes(e.getMsgDes());
                savePDInfoRes.setBody(savePDInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SavePDInfoReqBD02 busData02 = CommonUtils.switchClass(SavePDInfoReqBD02.class,rowData);

            try {
                savePDInfoRes = pdiService.ModupdatePDTkInfo(busData02,pdTkInfo);
                SavePDInfoResB body = new SavePDInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePDInfoRes.setBody(body);
            }catch (SystemException e){
                savePDInfoResB.setMsgCode(e.getMsgCode());
                savePDInfoResB.setMsgDes(e.getMsgDes());
                savePDInfoRes.setBody(savePDInfoResB);
            }
        }
        //盘点差异确认
        else if("04".equals(saveReq.getExecType())){
            SavePDInfoReqBD04 busData04 = CommonUtils.switchClass(SavePDInfoReqBD04.class,rowData);

            try {
                savePDInfoRes = pdiService.confirmPDTkInfo(busData04);
                SavePDInfoResB body = new SavePDInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePDInfoRes.setBody(body);
            }catch (SystemException e){
                savePDInfoResB.setMsgCode(e.getMsgCode());
                savePDInfoResB.setMsgDes(e.getMsgDes());
                savePDInfoRes.setBody(savePDInfoResB);
            }
        }
        else{
            savePDInfoResB.setMsgCode("MG0006F");
            savePDInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、04");
        }

        savePDInfoRes.setStatus("00");
        return savePDInfoRes;
    }


    //盘点差异导出  --王怀龙
    @RequestMapping(value ="/ExportPDCInfo",method = RequestMethod.POST)
    @ResponseBody
    public ExportPDCInfoRes ExportPDCInfo(HttpServletResponse response, SaveReq saveReq){
        ExportPDCInfoRes exportPDCInfoRes = new ExportPDCInfoRes();
        ExportPDCInfoResB exportPDCInfoResB = new ExportPDCInfoResB();

        try {
        ExportPDCInfoReqBD objEExportPDCInfoReqBD =
                CommonUtils.switchClass(ExportPDCInfoReqBD.class,saveReq.getBusData());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os = pdiService.ExportPDCInfo(objEExportPDCInfoReqBD);
            exportPDCInfoRes.setStatus("00");
            exportPDCInfoResB.setMsgCode("0x00000");
            exportPDCInfoResB.setMsgDes("成功!");
            exportPDCInfoRes.setBody(exportPDCInfoResB);

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
                while(-1 != (bytesRead = bis.read(buff, 0, buff.length))){
                    bos.write(buff, 0, bytesRead);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }finally{
                if(bis != null) { try { bis.close(); } catch (IOException e) {} }
                if(bos != null) { try { bos.close(); } catch (IOException e) {} }
            }

        }catch(SystemException objSystemException){
            exportPDCInfoResB.setMsgCode(objSystemException.getMsgCode());
            exportPDCInfoResB.setMsgDes(objSystemException.getMsgDes());
            exportPDCInfoRes.setBody(exportPDCInfoResB);
            exportPDCInfoRes.setStatus("01");
        }

        return exportPDCInfoRes;
    }
}
