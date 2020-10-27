package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoRes;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoResB;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoResD;
import pnc.mesadmin.dto.GetAllPickInfo.GetAllPickInfoRes;
import pnc.mesadmin.dto.GetAllPickInfo.GetAllPickInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetNPickInfo.GetNPickInfoReq00;
import pnc.mesadmin.dto.GetNPickInfo.GetNPickInfoRes;
import pnc.mesadmin.dto.GetNPickInfo.GetNPickInfoResB;
import pnc.mesadmin.dto.GetPickInfo.GetPickInfoReq00;
import pnc.mesadmin.dto.GetPickInfo.GetPickInfoRes;
import pnc.mesadmin.dto.GetPickInfo.GetPickInfoResB;
import pnc.mesadmin.dto.SaveImportRes.SaveImportRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveNPickInfo.*;
import pnc.mesadmin.dto.SavePickInfo.*;
import pnc.mesadmin.service.CkTkIService;
import pnc.mesadmin.service.PickIService;
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
 * 子系统名称：出库信息Controller
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Pick")
public class PickController {
    @Resource
    private CkTkIService ckTkIService;

    @Resource
    private PickIService pickIService;

    @RequestMapping(value = "/importpickPG")
    public String importpickPGView(){
        return "plan/pick/importpick";
    }

    @RequestMapping(value = "/importnpickPG")
    public String importnpickPGView(){
        return "plan/pick/importnpick";
    }


    @RequestMapping("/PickPG")
    private String GetCkTkInfo(){
        return "plan/pick/pickinfo";
    }

    @RequestMapping("/NPickPG")
    private String NewGetCkTkInfo(){
        return "plan/pick/newPickInfo";
    }

    //保存领料单
    @RequestMapping(value = "/SavePickInfo",method = RequestMethod.POST)
    @ResponseBody
    public SavePickInfoRes SavePickInfo(SaveReq saveReq){
        SavePickInfoRes objESavePickInfoRes = new SavePickInfoRes();

        if("00".equals(saveReq.getExecType())){
            SavePickInfoReq00 objESavePickInfoReq00 = CommonUtils.switchClass(SavePickInfoReq00.class,saveReq.getBusData());

            try{
                objESavePickInfoRes = ckTkIService.AddSavePickInfoRes(objESavePickInfoReq00);
                SavePickInfoResB objESavePickInfoResB = objESavePickInfoRes.getBody();
                objESavePickInfoResB.setMsgCode("0x00000");
                objESavePickInfoResB.setMsgDes("成功");
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }catch (SystemException e){
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode(e.getMsgCode());
                objESavePickInfoResB.setMsgDes(e.getMsgDes());
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }
        } else if("01".equals(saveReq.getExecType())){
            SavePickInfoReq01 objESavePickInfoReq01 = CommonUtils.switchClass(SavePickInfoReq01.class,saveReq.getBusData());

            try{
                objESavePickInfoRes = pickIService.RmSavePickInfoRes(objESavePickInfoReq01);
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode("0x00000");
                objESavePickInfoResB.setMsgDes("成功");
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }catch (SystemException e){
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode(e.getMsgCode());
                objESavePickInfoResB.setMsgDes(e.getMsgDes());
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }
        } else if("02".equals(saveReq.getExecType())){
            SavePickInfoReq02 objESavePickInfoReq02 = CommonUtils.switchClass(SavePickInfoReq02.class,saveReq.getBusData());

            try{
                objESavePickInfoRes = pickIService.ModSavePickInfoRes(objESavePickInfoReq02);
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode("0x00000");
                objESavePickInfoResB.setMsgDes("成功");
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }catch (SystemException e){
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode(e.getMsgCode());
                objESavePickInfoResB.setMsgDes(e.getMsgDes());
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }
        } else if("03".equals(saveReq.getExecType())){
            SavePickInfoReq03 objESavePickInfoReq03 = CommonUtils.switchClass(SavePickInfoReq03.class,saveReq.getBusData());

            try{
                objESavePickInfoRes = pickIService.ModPickInfoOn(objESavePickInfoReq03);
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode("0x00000");
                objESavePickInfoResB.setMsgDes("成功");
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }catch (SystemException e){
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode(e.getMsgCode());
                objESavePickInfoResB.setMsgDes(e.getMsgDes());
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }
        } else if("04".equals(saveReq.getExecType())){
            SavePickInfoReq04 objESavePickInfoReq04 = CommonUtils.switchClass(SavePickInfoReq04.class,saveReq.getBusData());

            try{
                objESavePickInfoRes = pickIService.ModPickInfoOff(objESavePickInfoReq04);
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode("0x00000");
                objESavePickInfoResB.setMsgDes("成功");
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }catch (SystemException e){
                SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
                objESavePickInfoResB.setMsgCode(e.getMsgCode());
                objESavePickInfoResB.setMsgDes(e.getMsgDes());
                objESavePickInfoRes.setBody(objESavePickInfoResB);
            }
        } else {
            SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
            objESavePickInfoResB.setMsgCode("MG0006F");
            objESavePickInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03、04");
            objESavePickInfoRes.setBody(objESavePickInfoResB);
        }

        objESavePickInfoRes.setStatus("00");
        return objESavePickInfoRes;
    }

    //获取领料单列表信息 N
    @RequestMapping(value = "/GetAllNPickInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllNPickInfoRes GetAllNPickInfoRes(GetAllReq getAllReq){
        GetAllNPickInfoRes objEGetAllNPickInfoRes=new GetAllNPickInfoRes();
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
                objEGetAllNPickInfoRes =pickIService.QALLGetAllNPickInfoRes(objEInitDataFields, pageInfo);
                objEGetAllNPickInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllNPickInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllNPickInfoResB objEGetAllNPickInfoResB = new GetAllNPickInfoResB();
                objEGetAllNPickInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllNPickInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllNPickInfoRes.setBody(objEGetAllNPickInfoResB);
            }
        }
        else{
            GetAllNPickInfoResB objEGetAllNPickInfoResB = new GetAllNPickInfoResB();
            objEGetAllNPickInfoResB.setMsgCode("MG0006F");
            objEGetAllNPickInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllNPickInfoRes.setBody(objEGetAllNPickInfoResB);
        }
        objEGetAllNPickInfoRes.setStatus("00");
        return objEGetAllNPickInfoRes;
    }

    //筛选无工单领料
    @RequestMapping(value = "/GetAllNPickInfo1",method = RequestMethod.POST)
    @ResponseBody
    public GetAllNPickInfoRes GetAllNPickInfoRess(GetAllReq getAllReq){
        GetAllNPickInfoRes objEGetAllNPickInfoRes=new GetAllNPickInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            PageInfo pageInfo = null;
            if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }
            GetAllNPickInfoResD objEGetAllNPickInfoResD = CommonUtils.switchClass(GetAllNPickInfoResD.class, getAllReq.getBusData());

            try {
                objEGetAllNPickInfoRes =pickIService.QALLGetAllNPickInfoRes1(objEGetAllNPickInfoResD, pageInfo);
                objEGetAllNPickInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllNPickInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllNPickInfoResB objEGetAllNPickInfoResB = new GetAllNPickInfoResB();
                objEGetAllNPickInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllNPickInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllNPickInfoRes.setBody(objEGetAllNPickInfoResB);
            }
        }
        else{
            GetAllNPickInfoResB objEGetAllNPickInfoResB = new GetAllNPickInfoResB();
            objEGetAllNPickInfoResB.setMsgCode("MG0006F");
            objEGetAllNPickInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllNPickInfoRes.setBody(objEGetAllNPickInfoResB);
        }
        objEGetAllNPickInfoRes.setStatus("00");
        return objEGetAllNPickInfoRes;
    }

    //获取领料单列表信息
    @RequestMapping(value = "/GetAllPickInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllPickInfoRes GetAllPickInfo(GetAllReq getAllReq){
        GetAllPickInfoRes objEGetAllPickInfoRes=new GetAllPickInfoRes();
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
                objEGetAllPickInfoRes =pickIService.QALLGetAllPickInfoRes(objEInitDataFields, pageInfo);
                objEGetAllPickInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPickInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllPickInfoResB objEGetAllPickInfoResB = new GetAllPickInfoResB();
                objEGetAllPickInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPickInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPickInfoRes.setBody(objEGetAllPickInfoResB);
            }
        }
        else{
            GetAllPickInfoResB objEGetAllPickInfoResB = new GetAllPickInfoResB();
            objEGetAllPickInfoResB.setMsgCode("MG0006F");
            objEGetAllPickInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllPickInfoRes.setBody(objEGetAllPickInfoResB);
        }
        objEGetAllPickInfoRes.setStatus("00");
        return objEGetAllPickInfoRes;
    }

    //获取领料单信息
    @RequestMapping(value = "/GetPickInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetPickInfoRes GetPickInfo(GetAllReq getAllReq){
        GetPickInfoRes objEGetPickInfoRes=new GetPickInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetPickInfoReq00 objEGetPickInfoReq00 = CommonUtils.switchClass(GetPickInfoReq00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{

                try{
                    objEGetPickInfoRes= pickIService.GetGetPickInfoRes(objEGetPickInfoReq00);
                    objEGetPickInfoRes.getBody().setMsgCode("0x00000");
                    objEGetPickInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetPickInfoResB objEGetPickInfoResB=new GetPickInfoResB();
                    objEGetPickInfoResB.setMsgCode(e.getMsgCode());
                    objEGetPickInfoResB.setMsgDes(e.getMsgDes());
                    objEGetPickInfoRes.setBody(objEGetPickInfoResB);
                }
            }
        }
        else {
            GetPickInfoResB objEGetPickInfoResB=new GetPickInfoResB();
            objEGetPickInfoResB.setMsgCode("MG0006F");
            objEGetPickInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetPickInfoRes.setBody(objEGetPickInfoResB);
        }
        objEGetPickInfoRes.setStatus("00");
        return objEGetPickInfoRes;
    }

    //获取领料单信息 N
    @RequestMapping(value = "/GetNPickInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetNPickInfoRes GetGetNPickInfoRes(GetAllReq getAllReq){
        GetNPickInfoRes objEGetNPickInfoRes=new GetNPickInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetNPickInfoReq00 objEGetNPickInfoReq00 = CommonUtils.switchClass(GetNPickInfoReq00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{

                try{
                    objEGetNPickInfoRes= pickIService.GetGetNPickInfoRes(objEGetNPickInfoReq00);
                    objEGetNPickInfoRes.getBody().setMsgCode("0x00000");
                    objEGetNPickInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetNPickInfoResB objEGetNPickInfoResB=new GetNPickInfoResB();
                    objEGetNPickInfoResB.setMsgCode(e.getMsgCode());
                    objEGetNPickInfoResB.setMsgDes(e.getMsgDes());
                    objEGetNPickInfoRes.setBody(objEGetNPickInfoResB);
                }
            }
        }
        else {
            GetNPickInfoResB objEGetNPickInfoResB=new GetNPickInfoResB();
            objEGetNPickInfoResB.setMsgCode("MG0006F");
            objEGetNPickInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetNPickInfoRes.setBody(objEGetNPickInfoResB);
        }
        objEGetNPickInfoRes.setStatus("00");
        return  objEGetNPickInfoRes;
    }

    //新增领料单信息 N
    //保存领料单
    @RequestMapping(value = "/SaveNPickInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveNPickInfoRes SaveNPickInfo(SaveReq saveReq){
        SaveNPickInfoRes objESaveNPickInfoRes=new SaveNPickInfoRes();
        if("00".equals(saveReq.getExecType())){
            SaveNPickInfoReq00 objESaveNPickInfoReq00 = CommonUtils.switchClass(SaveNPickInfoReq00.class,saveReq.getBusData());
            try{
                if(objESaveNPickInfoReq00==null||"".equals(objESaveNPickInfoReq00)){
                    throw new SystemException("xx", "新增失败，明细信息不允许为空");
                }
                objESaveNPickInfoRes = pickIService.AddSaveNPickInfoRes(objESaveNPickInfoReq00);
                objESaveNPickInfoRes.getBody().setMsgCode("0x00000");
                objESaveNPickInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode(e.getMsgCode());
                objESaveNPickInfoResB.setMsgDes(e.getMsgDes());
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }
        }else if("01".equals(saveReq.getExecType())){
            SaveNPickInfoReq01 objESaveNPickInfoReq01 = CommonUtils.switchClass(SaveNPickInfoReq01.class,saveReq.getBusData());
            try{
                objESaveNPickInfoRes = pickIService.RmSaveNPickInfoRes(objESaveNPickInfoReq01);
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode("0x00000");
                objESaveNPickInfoResB.setMsgDes("成功");
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }catch (SystemException e){
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode(e.getMsgCode());
                objESaveNPickInfoResB.setMsgDes(e.getMsgDes());
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }
        }
        else if("02".equals(saveReq.getExecType())){
            SaveNPickInfoReq02 objESaveNPickInfoReq02 = CommonUtils.switchClass(SaveNPickInfoReq02.class,saveReq.getBusData());
            try{
                if(objESaveNPickInfoReq02==null||"".equals(objESaveNPickInfoReq02)){
                    throw new SystemException("xx", "更新失败，明细信息不允许为空");
                }
                objESaveNPickInfoRes = pickIService.ModSaveNPickInfoRes(objESaveNPickInfoReq02);
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode("0x00000");
                objESaveNPickInfoResB.setMsgDes("成功");
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }catch (SystemException e){
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode(e.getMsgCode());
                objESaveNPickInfoResB.setMsgDes(e.getMsgDes());
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }
        }
        else if("03".equals(saveReq.getExecType())){
            SaveNPickInfoReq03 objESaveNPickInfoReq03 = CommonUtils.switchClass(SaveNPickInfoReq03.class,saveReq.getBusData());
            try{
                objESaveNPickInfoRes = pickIService.GetSaveNPickInfoReq03(objESaveNPickInfoReq03);
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode("0x00000");
                objESaveNPickInfoResB.setMsgDes("成功");
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }catch (SystemException e){
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode(e.getMsgCode());
                objESaveNPickInfoResB.setMsgDes(e.getMsgDes());
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }
        }
        else if("04".equals(saveReq.getExecType())){
            SaveNPickInfoReq03 objESaveNPickInfoReq03 = CommonUtils.switchClass(SaveNPickInfoReq03.class,saveReq.getBusData());
            try{
                objESaveNPickInfoRes = pickIService.GetCancelSaveNPickInfoReq03(objESaveNPickInfoReq03);
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode("0x00000");
                objESaveNPickInfoResB.setMsgDes("成功");
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }catch (SystemException e){
                SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
                objESaveNPickInfoResB.setMsgCode(e.getMsgCode());
                objESaveNPickInfoResB.setMsgDes(e.getMsgDes());
                objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
            }
        }
        else {
            SaveNPickInfoResB objESaveNPickInfoResB=new SaveNPickInfoResB();
            objESaveNPickInfoResB.setMsgCode("MG0006F");
            objESaveNPickInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03、04");
            objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
        }
        objESaveNPickInfoRes.setStatus("00");

        return  objESaveNPickInfoRes;
    }

    //导入
    @ResponseBody
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    public SaveImportRes importExcel(HttpServletRequest request, @RequestParam("upload") CommonsMultipartFile file) throws IOException {
        SaveImportRes objSaveImportRes = new SaveImportRes();
        SaveImportResB objSaveImportResB = new SaveImportResB();
        try {
            objSaveImportResB = pickIService.AddImportPick(file);
        }catch(SystemException e){
            objSaveImportResB.setMsgCode(e.getMsgCode());
            objSaveImportResB.setMsgDes(e.getMsgDes());
        }
        objSaveImportRes.setBody(objSaveImportResB);
        objSaveImportRes.setStatus("00");
        return objSaveImportRes;
    }

    //导出
    @ResponseBody
    @RequestMapping(value = "/exportPickExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes exportPickExcel(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = pickIService.exportPickExcel(busData);
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

    //导出推荐库位单
    @ResponseBody
    @RequestMapping(value = "/ExportPickExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes ExportPickExcel(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = pickIService.ExportPickExcel(busData,response);
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

    //导入 N
    @ResponseBody
    @RequestMapping(value = "/importnExcel",method = RequestMethod.POST)
    public SaveImportRes importnExcelS(HttpServletRequest request, @RequestParam("upload") CommonsMultipartFile file) throws IOException {
        SaveImportRes objSaveImportRes = new SaveImportRes();
        SaveImportResB objSaveImportResB = new SaveImportResB();
        try {
            objSaveImportResB = pickIService.AddImportnPick(file);
        }catch(SystemException e){
            objSaveImportResB.setMsgCode(e.getMsgCode());
            objSaveImportResB.setMsgDes(e.getMsgDes());
        }
        objSaveImportRes.setBody(objSaveImportResB);
        objSaveImportRes.setStatus("00");
        return objSaveImportRes;
    }

    //导出备料清单 N
    @ResponseBody
    @RequestMapping(value = "/exportnPickExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes exportnPickExcels(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = pickIService.exportnPickExcel(busData);
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

    //导出领料确认单
    @ResponseBody
    @RequestMapping(value = "/exportnPickExcel1",method = RequestMethod.POST)
    public ExportSWareInfoRes exportnPickExcels1(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = pickIService.exportnPickExcel1(busData);
                exportSWareInfoRes.setStatus("00");
                exportSWareInfoResB.setMsgCode("0x00000");
                exportSWareInfoResB.setMsgDes("成功!");
                exportSWareInfoRes.setBody(exportSWareInfoResB);

                //设置response参数,可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + "test" + ".xlsx");

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

    //导出下载推荐库位单  N
    @ResponseBody
    @RequestMapping(value = "/ExportnPickExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes ExportnPickExcel(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = pickIService.ExportnPickExcel(busData);
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