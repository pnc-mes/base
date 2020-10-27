package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoReqBD00;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoRes;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResB;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResBD;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoRes;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBomRes;
import pnc.mesadmin.dto.GetAllDevInfo.GetDevListsRequest;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoReqBD00;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoRes;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResB;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResBD;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoReqBD00;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoRes;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoResB;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoReqBD00;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoRes;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoResB;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoReqBD00;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoReqBD01;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoRes;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResB;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoReq00;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoRes;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoResB;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoReq00;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoRes;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoResB;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoReqBD00;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoReqBD03;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoRes;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoResB;
import pnc.mesadmin.dto.SaveImportRes.SaveImportRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.service.BOMIService;
import pnc.mesadmin.service.CkTkIService;
import pnc.mesadmin.service.PickIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：BOM管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/BOM")
public class BOMController {

    @Resource
    private CkTkIService ckTkIService;

    @Resource
    private BOMIService bomiService;

    @Resource
    private PickIService pickIService;

    //主视图
    @RequestMapping(value = "/BOMPG")
    public String BomInfoView(){
        return "process/bom/bominfo";
    }

    @RequestMapping(value = "/importbomPG")
    public String importbomPGView(){
        return "process/bom/importbom";
    }

    @RequestMapping(value = "/BOMChangePG")
    public String BOMChangeView(){
        return "process/bom/bzlog";
    }

    @RequestMapping(value = "/SubBOM", method = RequestMethod.GET)
    public String SubBomView(){
        return "process/bom/subbom";
    }

    //汇总 N
    @RequestMapping(value = "/GetNMaTotalInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetNMaTotalInfoRes QALLGetNMaTotalInfoRes(GetAllReq getAllReq){
        GetNMaTotalInfoRes objEGetNMaTotalInfoRes=new GetNMaTotalInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetNMaTotalInfoReq00 objGetNMaTotalInfoReq00= CommonUtils.switchClass(GetNMaTotalInfoReq00.class,getAllReq.getBusData());

            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetNMaTotalInfoRes = pickIService.QALLGetNMaTotalInfoRes(objGetNMaTotalInfoReq00);
                    objEGetNMaTotalInfoRes.getBody().setMsgCode("0x00000");
                    objEGetNMaTotalInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetNMaTotalInfoResB objEGetNMaTotalInfoResB = new GetNMaTotalInfoResB();
                    objEGetNMaTotalInfoResB.setMsgCode(e.getMsgCode());
                    objEGetNMaTotalInfoResB.setMsgDes(e.getMsgDes());
                    objEGetNMaTotalInfoRes.setBody(objEGetNMaTotalInfoResB);
                }
            }
        }
        else {
            GetNMaTotalInfoResB objEGetNMaTotalInfoResB = new GetNMaTotalInfoResB();
            objEGetNMaTotalInfoResB.setMsgCode("MG0006F");
            objEGetNMaTotalInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetNMaTotalInfoRes.setBody(objEGetNMaTotalInfoResB);
        }
        objEGetNMaTotalInfoRes.setStatus("00");
        return objEGetNMaTotalInfoRes;
    }

    //获取用料汇总信息
    @RequestMapping(value = "/GetMaTotalInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetMaTotalInfoRes GetMaTotalInfo(GetAllReq getAllReq){
        GetMaTotalInfoRes objEGetMaTotalInfoRes=new GetMaTotalInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetMaTotalInfoReq00 objGetMaTotalInfoReq00= CommonUtils.switchClass(GetMaTotalInfoReq00.class,getAllReq.getBusData());

            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetMaTotalInfoRes = ckTkIService.GetGetMaTotalInfoRes(objGetMaTotalInfoReq00);
                    objEGetMaTotalInfoRes.getBody().setMsgCode("0x00000");
                    objEGetMaTotalInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetMaTotalInfoResB objEGetMaTotalInfoResB = new GetMaTotalInfoResB();
                    objEGetMaTotalInfoResB.setMsgCode(e.getMsgCode());
                    objEGetMaTotalInfoResB.setMsgDes(e.getMsgDes());
                    objEGetMaTotalInfoRes.setBody(objEGetMaTotalInfoResB);
                }
            }
        }
        else {
            GetMaTotalInfoResB objEGetMaTotalInfoResB = new GetMaTotalInfoResB();
            objEGetMaTotalInfoResB.setMsgCode("MG0006F");
            objEGetMaTotalInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetMaTotalInfoRes.setBody(objEGetMaTotalInfoResB);
        }
        objEGetMaTotalInfoRes.setStatus("00");
        return objEGetMaTotalInfoRes;
    }

    //获取BOM列表信息
    @RequestMapping(value = "/GetAllBOMInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllBOMInfoRes GetAllBOMInfo(GetAllReq getAllReq){
        GetAllBOMInfoRes objEGetAllBOMInfoRes = new GetAllBOMInfoRes();

        String busData = getAllReq.getBusData();

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

                objEGetAllBOMInfoRes = bomiService.QALLBOMInfo(objEInitDataFields, pageInfo);
                objEGetAllBOMInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllBOMInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllBOMInfoResB objEGetAllBOMInfoResB  = new GetAllBOMInfoResB();
                objEGetAllBOMInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllBOMInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllBOMInfoRes.setBody(objEGetAllBOMInfoResB);
            }

        }else{
            GetAllBOMInfoResB objEGetAllBOMInfoResB  = new GetAllBOMInfoResB();
            objEGetAllBOMInfoResB.setMsgCode("MG0006F");
            objEGetAllBOMInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllBOMInfoRes.setBody(objEGetAllBOMInfoResB);
        }

        objEGetAllBOMInfoRes.setStatus("00");

        return objEGetAllBOMInfoRes;
    }
    /**
     * @Description 获取BOM分页列表信息_V2
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetAllBOMInfo_V2", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllBOMInfo(GetAllBomRes request) {
        return  bomiService.GetAllBOMInfo_V2(request);
    }

    /**
     * 获取BOM列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllBOMNew")
    @ResponseBody
    public BaseResponse GetAllBOMNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(bomiService.QALLBOMNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取BOM信息
    @RequestMapping(value = "/GetBOMInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetBOMInfoRes GetBOMInfo(GetAllReq getAllReq){
        GetBOMInfoRes objEGetBOMInfoRes = new GetBOMInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetBOMInfoReqBD00 objEGetBOMInfoReqBD00 = CommonUtils.switchClass(GetBOMInfoReqBD00.class, busData);

            try{
                objEGetBOMInfoRes = bomiService.GetBOMInfo(objEGetBOMInfoReqBD00);
                objEGetBOMInfoRes.getBody().setMsgCode("0x00000");
                objEGetBOMInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetBOMInfoResB objEGetBOMInfoResB = new GetBOMInfoResB();
                objEGetBOMInfoResB.setMsgCode(e.getMsgCode());
                objEGetBOMInfoResB.setMsgDes(e.getMsgDes());
                objEGetBOMInfoRes.setBody(objEGetBOMInfoResB);
            }

        }
        else {
            GetBOMInfoResB objEGetBOMInfoResB = new GetBOMInfoResB();
            objEGetBOMInfoResB.setMsgCode("MG0006F");
            objEGetBOMInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetBOMInfoRes.setBody(objEGetBOMInfoResB);
        }

        objEGetBOMInfoRes.setStatus("00");

        return objEGetBOMInfoRes;
    }

    //获取BOM版本列表信息
    @RequestMapping(value = "/GetBOMTreeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetBOMTreeInfoRes GetBOMTreeInfo(GetAllReq getAllReq){
        GetBOMTreeInfoRes objEGetBOMTreeInfoRes = new GetBOMTreeInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetBOMTreeInfoReqBD00 objEGetBOMTreeInfoReqBD00 = CommonUtils.switchClass(GetBOMTreeInfoReqBD00.class, busData);

            try {
                objEGetBOMTreeInfoRes = bomiService.GetBOMTreeInfo(objEGetBOMTreeInfoReqBD00);
                objEGetBOMTreeInfoRes.getBody().setMsgCode("0x00000");
                objEGetBOMTreeInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetBOMTreeInfoResB objEGetBOMTreeInfoResB = new GetBOMTreeInfoResB();
                objEGetBOMTreeInfoResB.setMsgCode(e.getMsgCode());
                objEGetBOMTreeInfoResB.setMsgDes(e.getMsgDes());
                objEGetBOMTreeInfoRes.setBody(objEGetBOMTreeInfoResB);
            }

        }
        else {
            GetBOMTreeInfoResB objEGetBOMTreeInfoResB = new GetBOMTreeInfoResB();
            objEGetBOMTreeInfoResB.setMsgCode("MG0006F");
            objEGetBOMTreeInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetBOMTreeInfoRes.setBody(objEGetBOMTreeInfoResB);
        }

        objEGetBOMTreeInfoRes.setStatus("00");

        return objEGetBOMTreeInfoRes;
    }

    //获取Bom清单版本信息
    @RequestMapping(value = "/GetBVInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetBVInfoRes GetBVInfo(GetAllReq getAllReq){
        GetBVInfoRes objEGetBVInfoRes = new GetBVInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetBVInfoReqBD00 objEGetBVInfoReqBD00 = CommonUtils.switchClass(GetBVInfoReqBD00.class, busData);

            try {
                objEGetBVInfoRes = bomiService.GetBVInfo(objEGetBVInfoReqBD00);
                objEGetBVInfoRes.getBody().setMsgCode("0x00000");
                objEGetBVInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetBVInfoResB objEGetBVInfoResB = new GetBVInfoResB();
                objEGetBVInfoResB.setMsgCode(e.getMsgCode());
                objEGetBVInfoResB.setMsgDes(e.getMsgDes());
                objEGetBVInfoRes.setBody(objEGetBVInfoResB);
            }
        }else if("01".equals(getAllReq.getExecType())){
            GetBVInfoReqBD01 objEGetBVInfoReqBD01 = CommonUtils.switchClass(GetBVInfoReqBD01.class, busData);

            try {
                objEGetBVInfoRes = bomiService.GetBVInfo(objEGetBVInfoReqBD01);
                objEGetBVInfoRes.getBody().setMsgCode("0x00000");
                objEGetBVInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetBVInfoResB objEGetBVInfoResB = new GetBVInfoResB();
                objEGetBVInfoResB.setMsgCode(e.getMsgCode());
                objEGetBVInfoResB.setMsgDes(e.getMsgDes());
                objEGetBVInfoRes.setBody(objEGetBVInfoResB);
            }

        }else {
            GetBVInfoResB objEGetBVInfoResB = new GetBVInfoResB();
            objEGetBVInfoResB.setMsgCode("MG0006F");
            objEGetBVInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetBVInfoRes.setBody(objEGetBVInfoResB);
        }

        objEGetBVInfoRes.setStatus("00");

        return objEGetBVInfoRes;
    }

    //保存Bom清单信息
    @RequestMapping(value = "/SaveBomInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveBomInfoRes SaveBomInfo(SaveReq saveReq){
        SaveBomInfoRes objESaveBomInfoRes = new SaveBomInfoRes();
        SaveBomInfoResB objESaveBomInfoResB = new SaveBomInfoResB();

        String busData = saveReq.getBusData();

        if("00".equals(saveReq.getExecType())){
            //新增
            SaveBomInfoReqBD00 objESaveBomInfoReqBD00 = CommonUtils.switchClass(SaveBomInfoReqBD00.class, busData);

            try {
                if("".equals(objESaveBomInfoReqBD00.getBomName())){
                    throw new SystemException("", "BOM名称不能为空!");
                }
                if("".equals(objESaveBomInfoReqBD00.getVersion())){
                    throw new SystemException("", "BOM变更单号不能为空!");
                }
                objESaveBomInfoRes = bomiService.AddBomInfo(objESaveBomInfoReqBD00);
                objESaveBomInfoRes.getBody().setMsgCode("0x00000");
                objESaveBomInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                SaveBomInfoResB objESaveBomInfoResBs = new SaveBomInfoResB();
                objESaveBomInfoResBs.setMsgCode(e.getMsgCode());
                objESaveBomInfoResBs.setMsgDes(e.getMsgDes());
                objESaveBomInfoRes.setBody(objESaveBomInfoResBs);
            }
        }else if("03".equals(saveReq.getExecType())){
            //编辑
            SaveBomInfoReqBD03 objESaveBomInfoReqBD03 = CommonUtils.switchClass(SaveBomInfoReqBD03.class, busData);

            try{
                objESaveBomInfoRes = bomiService.ModBomInfo(objESaveBomInfoReqBD03);
                objESaveBomInfoResB.setMsgCode("0x00000");
                objESaveBomInfoResB.setMsgDes("成功");
                objESaveBomInfoRes.setBody(objESaveBomInfoResB);
            }catch (SystemException e){
                objESaveBomInfoResB.setMsgCode(e.getMsgCode());
                objESaveBomInfoResB.setMsgDes(e.getMsgDes());
                objESaveBomInfoRes.setBody(objESaveBomInfoResB);
            }
        }else {
            objESaveBomInfoResB.setMsgCode("MG0006F");
            objESaveBomInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、03");
            objESaveBomInfoRes.setBody(objESaveBomInfoResB);
        }
        objESaveBomInfoRes.setStatus("00");

        return objESaveBomInfoRes;
    }

    /**
     * @Description 新增BOM接口V2
     * @Author yin.yang
     * @Date 2020/9/17
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/AddBomInfoV2", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse AddBomInfoV2(@RequestBody SaveBomInfoReqBD00 request){
       try {
           bomiService.AddBomInfo(request);
       } catch (SystemException e){
           return BaseResponse.error(e.getMsgCode(),e.getMsgDes());
       }
        return BaseResponse.SUCCESS;
    }

    /**
     * @Description 变更BOM接口V2
     * @Author yin.yang
     * @Date 2020/9/17
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/BomInfoChangeV2", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse BomInfoChangeV2(@RequestBody SaveBomInfoReqBD03 request){
       try {
           bomiService.ModBomInfo(request);
       } catch (SystemException e){
           return BaseResponse.error(e.getMsgCode(),e.getMsgDes());
       }
        return BaseResponse.SUCCESS;
    }

    //查询BOM变更记录列表
    @ResponseBody
    @RequestMapping(value = "/GetAllBHChgInfo",method = RequestMethod.POST)
    public GetAllBHChgInfoRes GetAllBHChgInfo(GetAllReq argGetAllReq){
        GetAllBHChgInfoRes objGetAllBHChgInfoRes = new GetAllBHChgInfoRes();
        GetAllBHChgInfoResB objGetAllBHChgInfoResB = new GetAllBHChgInfoResB();
        if("00".equals(argGetAllReq.getExecType())){
            GetAllBHChgInfoReqBD00 busData00 = CommonUtils.switchClass(GetAllBHChgInfoReqBD00.class,argGetAllReq.getBusData());
            objGetAllBHChgInfoResB = bomiService.QALLBOMChgInfo(busData00.getBomRd());
        }else{
            objGetAllBHChgInfoResB.setData(new ArrayList<GetAllBHChgInfoResBD>());
            objGetAllBHChgInfoResB.setMsgCode("02");
            objGetAllBHChgInfoResB.setMsgDes("未知错误");
        }
        objGetAllBHChgInfoRes.setStatus("00");
        objGetAllBHChgInfoRes.setBody(objGetAllBHChgInfoResB);
        return objGetAllBHChgInfoRes;
    }
    //查询BOM变更明细
    @ResponseBody
    @RequestMapping(value = "/GetBHChgDtlInfo",method = RequestMethod.POST)
    public GetBHChgDtlInfoRes GetBHChgDtlInfo(GetAllReq objGetAllReq){
        GetBHChgDtlInfoRes objGetBHChgDtlInfoRes = new GetBHChgDtlInfoRes();
        GetBHChgDtlInfoResB objGetBHChgDtlInfoResB = new GetBHChgDtlInfoResB();
        if("00".equals(objGetAllReq.getExecType())){
            GetBHChgDtlInfoReqBD00 busData00 = CommonUtils.switchClass(GetBHChgDtlInfoReqBD00.class,objGetAllReq.getBusData());
            objGetBHChgDtlInfoResB = bomiService.QALLBOMChgInfoDetail(busData00.getBomChgRd());
        }else{
            objGetBHChgDtlInfoResB.setData(new ArrayList<GetBHChgDtlInfoResBD>());
            objGetBHChgDtlInfoResB.setMsgCode("02");
            objGetBHChgDtlInfoResB.setMsgDes("未知错误");
        }
        objGetBHChgDtlInfoRes.setStatus("00");
        objGetBHChgDtlInfoRes.setBody(objGetBHChgDtlInfoResB);
        return objGetBHChgDtlInfoRes;
    }
    //导入BOM
    @ResponseBody
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    public SaveImportRes importExcel(HttpServletRequest request, @RequestParam("upload") CommonsMultipartFile file) throws IOException {
        SaveImportRes objSaveImportRes = new SaveImportRes();
        SaveImportResB objSaveImportResB = new SaveImportResB();
        try {
            objSaveImportResB = bomiService.AddImportBOM(file);
        }catch(SystemException e){
            objSaveImportResB.setMsgCode(e.getMsgCode());
            objSaveImportResB.setMsgDes(e.getMsgDes());
        }
        objSaveImportRes.setBody(objSaveImportResB);
        objSaveImportRes.setStatus("00");
        return objSaveImportRes;
    }
    //导出BOM
    @ResponseBody
    @RequestMapping(value = "/exportBOMExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes exportBOMExcel(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = bomiService.exportBOMExcel(busData);
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
