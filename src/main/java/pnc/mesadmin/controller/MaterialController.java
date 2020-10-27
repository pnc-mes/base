package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoReqBD00;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoRes;
import pnc.mesadmin.dto.GetAllMaInfo.GetAllMaInfoResB;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoReqBD00;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoRes;
import pnc.mesadmin.dto.GetBOMReMaInfo.GetBOMReMaInfoResB;
import pnc.mesadmin.dto.GetMVInfo.GetMVInfoReqBD00;
import pnc.mesadmin.dto.GetMVInfo.GetMVInfoRes;
import pnc.mesadmin.dto.GetMVInfo.GetMVInfoResB;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoRes;
import pnc.mesadmin.dto.GetMVTreeInfo.GetMVTreeInfoResB;
import pnc.mesadmin.dto.GetMaInfo.GetMaInfoReqBD00;
import pnc.mesadmin.dto.GetMaInfo.GetMaInfoReqBD01;
import pnc.mesadmin.dto.GetMaInfo.GetMaInfoRes;
import pnc.mesadmin.dto.GetMaInfo.GetMaInfoResB;
import pnc.mesadmin.dto.GetMaTypeInfo.GetMaTypeInfoRes;
import pnc.mesadmin.dto.GetMaTypeInfo.GetMaTypeInfoResB;
import pnc.mesadmin.dto.GetPdBOMDIInfo.*;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoReqBD00;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoRes;
import pnc.mesadmin.dto.GetPdBOMInfo.GetPdBOMInfoResB;
import pnc.mesadmin.dto.GetPdReGpInfo.GetPdReGpInfoReqBD00;
import pnc.mesadmin.dto.GetPdReGpInfo.GetPdReGpInfoRes;
import pnc.mesadmin.dto.GetPdReGpInfo.GetPdReGpInfoResB;
import pnc.mesadmin.dto.SaveImportRes.SaveImportRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveMaInfo.*;
import pnc.mesadmin.service.MaterialIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/Material")
public class MaterialController {

    @Resource
    private MaterialIService materialIService;

    //导入
    @RequestMapping(value = "/ImportMaterial")
    public String ImportMaterials(){

        return "process/material/importmaterial";
    }
    //主视图
    @RequestMapping(value = "/MaterialPG")
    public String MaterialView(){

        return "process/material/materialinfo";
    }

    //包装规格视图
    @RequestMapping(value = "/PackSet", method = RequestMethod.GET)
    public String PackSetView(){

        return "process/material/packset";
    }

    //替代料视图
    @RequestMapping(value = "/RemaSet", method = RequestMethod.GET)
    public String RemaSetView(){

        return "process/material/remaset";
    }

    //查看BOM层次试图
    @RequestMapping(value = "/Bomlevel", method = RequestMethod.GET)
    public String BomlevelView(){

        return "process/material/bomlevel";
    }

    //查看替代料视图
    @RequestMapping(value = "/SubMaterial", method = RequestMethod.GET)
    public String SubMaterialView(){

        return "process/material/submaterial";
    }

    //产品管理视图 ProductPG
    @RequestMapping(value = "/ProductPG", method = RequestMethod.GET)
    public String ProductView(){

        return "process/material/product";
    }

    //获取物料分类信息
    @RequestMapping(value = "/GetMaTypeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMaTypeInfoRes GetMaTypeInfo(GetAllReq getAllReq){
        GetMaTypeInfoRes objEGetMaTypeInfoRes = new GetMaTypeInfoRes();

        if("00".equals(getAllReq.getExecType())){
            try{
                objEGetMaTypeInfoRes = materialIService.QALLMaType();
                objEGetMaTypeInfoRes.getBody().setMsgCode("0x00000");
                objEGetMaTypeInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetMaTypeInfoResB objEGetMaTypeInfoResB = new GetMaTypeInfoResB();
                objEGetMaTypeInfoResB.setMsgCode(e.getMsgCode());
                objEGetMaTypeInfoResB.setMsgDes(e.getMsgDes());
                objEGetMaTypeInfoRes.setBody(objEGetMaTypeInfoResB);
            }
        }

        objEGetMaTypeInfoRes.setStatus("00");

        return objEGetMaTypeInfoRes;
    }

    //获取物料列表信息
    @RequestMapping(value = "/GetAllMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMaInfoRes GetAllMaInfo(GetAllReq getAllReq){
        GetAllMaInfoRes objEGetAllMaInfoResRes = new GetAllMaInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();
            try{
                GetAllMaInfoReqBD00 objEGetAllMaInfoReqBD00=null;

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

                objEGetAllMaInfoResRes = materialIService.QALLByMaType(objEGetAllMaInfoReqBD00, objEInitDataFields, pageInfo);
                objEGetAllMaInfoResRes.getBody().setMsgCode("0x00000");
                objEGetAllMaInfoResRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllMaInfoResB objEGetAllMaInfoResB = new GetAllMaInfoResB();
                objEGetAllMaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllMaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllMaInfoResRes.setBody(objEGetAllMaInfoResB);
            }
        }

        objEGetAllMaInfoResRes.setStatus("00");

        return objEGetAllMaInfoResRes;
    }

    @PostMapping(value = "/GetAllMaNew")
    @ResponseBody
    public BaseResponse GetAllMaNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(materialIService.QALLAllMaNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取物料信息
    @RequestMapping(value = "/GetMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMaInfoRes GetMaInfo(GetAllReq getAllReq){
        GetMaInfoRes objEGetMaInfoRes = new GetMaInfoRes();

        String busData = getAllReq.getBusData();
        int ruid = 0, status = 0;

        if("00".equals(getAllReq.getExecType())){

            GetMaInfoReqBD00 objEGetMaInfoReqBD00  = CommonUtils.switchClass(GetMaInfoReqBD00.class, busData);
            ruid = objEGetMaInfoReqBD00.getMaRd();
            status = 1;
        }else{
            GetMaInfoReqBD01 objEGetMaInfoReqBD01 = CommonUtils.switchClass(GetMaInfoReqBD01.class, busData);
            ruid = objEGetMaInfoReqBD01.getMaVerRd();
            status = 2;
        }

        if(status != 0) {
            try {
                objEGetMaInfoRes = materialIService.GetMaInfo(status, ruid);
                objEGetMaInfoRes.getBody().setMsgCode("0x00000");
                objEGetMaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetMaInfoResB objEGetMaInfoResB = new GetMaInfoResB();
                objEGetMaInfoResB.setMsgCode(e.getMsgCode());
                objEGetMaInfoResB.setMsgDes(e.getMsgDes());
                objEGetMaInfoRes.setBody(objEGetMaInfoResB);
            }
        }

        objEGetMaInfoRes.setStatus("00");

        return objEGetMaInfoRes;
    }

    //获取物料版本列表信息
    @RequestMapping(value = "/GetMVTreeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMVTreeInfoRes GetMVTreeInfo(GetAllReq getAllReq){
        GetMVTreeInfoRes objEGetMVTreeInfoRes = new GetMVTreeInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetMVTreeInfoReqBD00 objEGetMVTreeInfoReqBD00 = CommonUtils.switchClass(GetMVTreeInfoReqBD00.class, busData);

            try {
                objEGetMVTreeInfoRes = materialIService.QALLMVTreeInfo(objEGetMVTreeInfoReqBD00);
                objEGetMVTreeInfoRes.getBody().setMsgCode("0x00000");
                objEGetMVTreeInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetMVTreeInfoResB objEGetMVTreeInfoResB = new GetMVTreeInfoResB();
                objEGetMVTreeInfoResB.setMsgCode(e.getMsgCode());
                objEGetMVTreeInfoResB.setMsgDes(e.getMsgDes());
                objEGetMVTreeInfoRes.setBody(objEGetMVTreeInfoResB);
            }
        }

        objEGetMVTreeInfoRes.setStatus("00");

        return objEGetMVTreeInfoRes;
    }

    //获取物料版本信息
    /*@RequestMapping(value = "/GetMVInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMVInfoRes GetMVInfo(GetAllReq getAllReq){
        GetMVInfoRes objEGetMVInfoRes = new GetMVInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetMVInfoReqBD00 objEGetMVInfoReqBD00 = CommonUtils.switchClass(GetMVInfoReqBD00.class, busData);

            try {
                objEGetMVInfoRes = materialIService.GetMVInfo(objEGetMVInfoReqBD00);
                objEGetMVInfoRes.getBody().setMsgCode("0x00000");
                objEGetMVInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetMVInfoResB objEGetMVInfoResB = new GetMVInfoResB();
                objEGetMVInfoResB.setMsgCode(e.getMsgCode());
                objEGetMVInfoResB.setMsgDes(e.getMsgDes());
                objEGetMVInfoRes.setBody(objEGetMVInfoResB);
            }
        }

        objEGetMVInfoRes.setStatus("00");

        return objEGetMVInfoRes;
    }*/

    //新增物料信息
    @RequestMapping(value = "/SaveMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveMaInfoRes SaveMaInfo(SaveReq saveReq){
        SaveMaInfoRes objESaveMaInfoRes = new SaveMaInfoRes();
        SaveMaInfoResB objESaveMaInfoResB = new SaveMaInfoResB();

        String busData = saveReq.getBusData();

        if("00".equals(saveReq.getExecType())){
            //新增
            SaveMaInfoReqBD00 objESaveMaInfoReqBD00 = CommonUtils.switchClass(SaveMaInfoReqBD00.class, busData);

            try{
                /*if("".equals(objESaveMaInfoReqBD00.getMaCode())){
                    throw new SystemException("", "物料代码不能为空!");
                }*/
                if("".equals(objESaveMaInfoReqBD00.getMaName())){
                    throw new SystemException("", "物料名称不能为空!");
                }
                if("".equals(objESaveMaInfoReqBD00.getVersion())){
                    throw new SystemException("", "物料版本不能为空!");
                }
                objESaveMaInfoRes = materialIService.AddMaInfo(objESaveMaInfoReqBD00);
                objESaveMaInfoResB=objESaveMaInfoRes.getBody();
                objESaveMaInfoResB.setMsgCode("0x00000");
                objESaveMaInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveMaInfoResB.setMsgCode(e.getMsgCode());
                objESaveMaInfoResB.setMsgDes(e.getMsgDes());
            }

        }else if("01".equals(saveReq.getExecType())){
            //删除
            SaveMaInfoReqBD01 objESaveMaInfoReqBD01 = CommonUtils.switchClass(SaveMaInfoReqBD01.class, busData);

            try {
                objESaveMaInfoRes = materialIService.RmMaInfo(objESaveMaInfoReqBD01);
                objESaveMaInfoResB.setMsgCode("0x00000");
                objESaveMaInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveMaInfoResB.setMsgCode(e.getMsgCode());
                objESaveMaInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("02".equals(saveReq.getExecType())){
            //编辑
            SaveMaInfoReqBD02 objESaveMaInfoReqBD02 = CommonUtils.switchClass(SaveMaInfoReqBD02.class, busData);

            try{
                if("".equals(objESaveMaInfoReqBD02.getMaCode())){
                    throw new SystemException("", "物料代码不能为空!");
                }
                if("".equals(objESaveMaInfoReqBD02.getMaName())){
                    throw new SystemException("", "物料名称不能为空!");
                }
                if("".equals(objESaveMaInfoReqBD02.getVersion())){
                    throw new SystemException("", "物料版本不能为空!");
                }
                objESaveMaInfoRes = materialIService.ModMaInfo(objESaveMaInfoReqBD02);
                objESaveMaInfoResB.setMsgCode("0x00000");
                objESaveMaInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveMaInfoResB.setMsgCode(e.getMsgCode());
                objESaveMaInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("03".equals(saveReq.getExecType())){
            //新增版本
            SaveMaInfoReqBD03 objESaveMaInfoReqBD03 = CommonUtils.switchClass(SaveMaInfoReqBD03.class, busData);

            try {
                if("".equals(objESaveMaInfoReqBD03.getVersion())){
                    throw new SystemException("", "物料版本不能为空!");
                }
                objESaveMaInfoRes = materialIService.AddMaVerInfo(objESaveMaInfoReqBD03);
                objESaveMaInfoResB.setMsgCode("0x00000");
                objESaveMaInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveMaInfoResB.setMsgCode(e.getMsgCode());
                objESaveMaInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveMaInfoRes.setBody(objESaveMaInfoResB);
        objESaveMaInfoRes.setStatus("00");

        return objESaveMaInfoRes;
    }

    //获取产品关联产品族信息
    @RequestMapping(value = "/GetPdReGpInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPdReGpInfoRes GetPdReGpInfo(GetAllReq getAllReq){
        GetPdReGpInfoRes objEGetPdReGpInfoRes = new GetPdReGpInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetPdReGpInfoReqBD00 objEGetPdReGpInfoReqBD00 = CommonUtils.switchClass(GetPdReGpInfoReqBD00.class, busData);

            try {
                objEGetPdReGpInfoRes = materialIService.GetPdReGpInfo(objEGetPdReGpInfoReqBD00);
                objEGetPdReGpInfoRes.getBody().setMsgCode("0x00000");
                objEGetPdReGpInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPdReGpInfoResB objEGetPdReGpInfoResB = new GetPdReGpInfoResB();
                objEGetPdReGpInfoResB.setMsgCode(e.getMsgCode());
                objEGetPdReGpInfoResB.setMsgDes(e.getMsgDes());
                objEGetPdReGpInfoRes.setBody(objEGetPdReGpInfoResB);
            }
        }

        objEGetPdReGpInfoRes.setStatus("00");

        return  objEGetPdReGpInfoRes;
    }

    //获取产品物料清单信息
    @RequestMapping(value = "/GetPdBOMInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPdBOMInfoRes GetPdBOMInfo(GetAllReq getAllReq){
        GetPdBOMInfoRes objEGetPdBOMInfoRes = new GetPdBOMInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetPdBOMInfoReqBD00 objEGetPdBOMInfoReqBD00 = CommonUtils.switchClass(GetPdBOMInfoReqBD00.class, busData);

            try {
                objEGetPdBOMInfoRes = materialIService.GetPdBOMInfo(objEGetPdBOMInfoReqBD00);
                objEGetPdBOMInfoRes.getBody().setMsgCode("0x00000");
                objEGetPdBOMInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPdBOMInfoResB objEGetPdBOMInfoResB = new GetPdBOMInfoResB();
                objEGetPdBOMInfoResB.setMsgCode(e.getMsgCode());
                objEGetPdBOMInfoResB.setMsgDes(e.getMsgDes());
                objEGetPdBOMInfoRes.setBody(objEGetPdBOMInfoResB);
            }
        }

        objEGetPdBOMInfoRes.setStatus("00");

        return objEGetPdBOMInfoRes;
    }

    //获取产品物料清单明细信息
    @RequestMapping(value = "/GetPdBOMDIInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPdBOMDIInfoRes GetPdBOMDIInfo(GetAllReq getAllReq){
        GetPdBOMDIInfoRes objEGetPdBOMDIInfoRes = new GetPdBOMDIInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetPdBOMDIInfoReqBD00 objEGetPdBOMDIInfoReqBD00 = CommonUtils.switchClass(GetPdBOMDIInfoReqBD00.class, busData);

            try{
                objEGetPdBOMDIInfoRes = materialIService.GetPdBOMDIInfo(objEGetPdBOMDIInfoReqBD00);
                objEGetPdBOMDIInfoRes.getBody().setMsgCode("0x00000");
                objEGetPdBOMDIInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPdBOMDIInfoResB objEGetPdBOMDIInfoResB = new GetPdBOMDIInfoResB();
                objEGetPdBOMDIInfoResB.setMsgCode(e.getMsgCode());
                objEGetPdBOMDIInfoResB.setMsgDes(e.getMsgDes());
                objEGetPdBOMDIInfoRes.setBody(objEGetPdBOMDIInfoResB);
            }
        }

        objEGetPdBOMDIInfoRes.setStatus("00");

        return objEGetPdBOMDIInfoRes;
    }

    //获取物料清单替代料信息
    @RequestMapping(value = "/GetBOMReMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetBOMReMaInfoRes GetBOMReMaInfo(GetAllReq getAllReq){
        GetBOMReMaInfoRes objEGetBOMReMaInfoRes = new GetBOMReMaInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetBOMReMaInfoReqBD00 objEGetBOMReMaInfoReqBD00 = CommonUtils.switchClass(GetBOMReMaInfoReqBD00.class, busData);

            try {
                objEGetBOMReMaInfoRes = materialIService.GetBOMReMaInfo(objEGetBOMReMaInfoReqBD00);
                objEGetBOMReMaInfoRes.getBody().setMsgCode("0x00000");
                objEGetBOMReMaInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetBOMReMaInfoResB objEGetBOMReMaInfoResB = new GetBOMReMaInfoResB();
                objEGetBOMReMaInfoResB.setMsgCode(e.getMsgCode());
                objEGetBOMReMaInfoResB.setMsgDes(e.getMsgDes());
                objEGetBOMReMaInfoRes.setBody(objEGetBOMReMaInfoResB);
            }
        }

        objEGetBOMReMaInfoRes.setStatus("00");

        return objEGetBOMReMaInfoRes;
    }



    //导入
    @ResponseBody
    @RequestMapping(value = "/importMExcel/{isRadio}",method = RequestMethod.POST)
    public SaveImportRes importExcel(HttpServletRequest request, @RequestParam("upload") CommonsMultipartFile file,@PathVariable("isRadio")  String isRadio) throws IOException {
        SaveImportRes objSaveImportRes = new SaveImportRes();
        SaveImportResB objSaveImportResB = new SaveImportResB();
        try {
            objSaveImportResB = materialIService.AddImportMater(file,isRadio);
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
    @RequestMapping(value = "/exportMaterialExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes exportPickExcel(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
          /*  GetAllMaInfoReqBD00 busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());*/
            try {
                GetAllMaInfoReqBD00 objEGetAllMaInfoReqBD00 = null;//CommonUtils.switchClass(GetAllMaInfoReqBD00.class, busData);

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


                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = materialIService.exportMaterialsExcel(objEGetAllMaInfoReqBD00, objEInitDataFields, pageInfo);
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