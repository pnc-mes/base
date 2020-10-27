package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoRes;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoResB;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoResD1;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetBatchInfo.GetBatchInfoRes;
import pnc.mesadmin.dto.GetBatchInfo.GetBatchInfoResB;
import pnc.mesadmin.dto.GetHoldBatchDTO.GetHoldBatchReq;
import pnc.mesadmin.dto.GetSUBInfo.GetSUBInfoReqBD00;
import pnc.mesadmin.dto.GetSUBInfo.GetSUBInfoRes;
import pnc.mesadmin.dto.GetSUBInfo.GetSUBInfoResB;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD00;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD02;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoRes;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoResB;
import pnc.mesadmin.dto.SaveBatchInfo.*;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoReqBD00;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoReqBD01;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoRes;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.BatchIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次拆分、合并管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-07-10
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/Batch")
public class BatchController {

    @Resource
    private BatchIService batchIService;

    @RequestMapping("/BatchPG")
    public String BatchPG() {
        return "search/batch/batchinfo";
    }

    @RequestMapping("/batchwadd")
    public String BatchWAdd() {
        return "plan/batch/batchwadd";
    }

    @RequestMapping("/batchwaddyougd")
    public String BatchWaddYougd() {
        return "plan/batch/batchwaddyougd";
    }

    @RequestMapping("/batchmv")
    public String BatchMa() {
        return "plan/batch/mallotadd";
    }

    @RequestMapping("/batchedit/{BRd}")
    public String batchedit(HttpServletRequest request, @PathVariable("BRd") String BRd) {
        request.setAttribute("BRd", BRd);
        return "plan/batch/batchedit";
    }

    //批次拆分
    @RequestMapping(value = "/SplitBatchPG")
    public String SplitBatchView() {
        return "batchtool/splitbatch";
    }

    //批次合并
    @RequestMapping(value = "/CbBatchPG")
    public String CbBatchView() {
        return "batchtool/cbbatch";
    }

    //冻结
    @RequestMapping(value = "/FreezersPG")
    public String FreezersPG(){
        return "qa/holdinfo";
    }

    //解冻
    @RequestMapping(value = "/ThawrsPG")
    public String ThawrsPG(){
        return "qa/unholdinfo";
    }

    //打开
    @RequestMapping(value = "/OpenPG")
    public String OpenPG(){
        return "batchtool/openinfo";
    }

    //关闭
    @RequestMapping(value = "/ClosePG")
    public String ClosePG(){
        return "batchtool/closeinfo";
    }

    //报废
    @RequestMapping(value = "/ScropPG")
    public String ScropPG(){
        return "batchtool/scropinfo";
    }

    //获取批次列表信息
    @RequestMapping(value = "/GetAllBatchInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllBatchInfoRes GetAllBatchInfo(GetAllReq getAllReq) {
        GetAllBatchInfoRes getAllBatchInfoRes = new GetAllBatchInfoRes();
        GetAllBatchInfoResB objEGetAllBatchInfoResB = null;
        if("00".equals(getAllReq.getExecType())){
            GetAllBatchInfoResD1 objEGetAllBatchInfoResD1 = CommonUtils.switchClass(GetAllBatchInfoResD1.class, getAllReq.getBusData());


            try {
                getAllBatchInfoRes = batchIService.QAllBatchInfo(objEGetAllBatchInfoResD1);
                getAllBatchInfoRes.getBody().setMsgCode("0x00000");
                getAllBatchInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException objSystemException) {
                getAllBatchInfoRes = new GetAllBatchInfoRes();
                objEGetAllBatchInfoResB = new GetAllBatchInfoResB();

                objEGetAllBatchInfoResB.setMsgCode(objSystemException.getMsgCode());
                objEGetAllBatchInfoResB.setMsgDes(objSystemException.getMsgDes());
                getAllBatchInfoRes.setStatus("01");
                getAllBatchInfoRes.setBody(objEGetAllBatchInfoResB);
            }

        }
        return getAllBatchInfoRes;
    }

    //导出批次查询
    @RequestMapping(value ="/ExportBatchInfo",method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportBatchInfo(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq){
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        GetAllBatchInfoResD1 objEGetAllBatchInfoResD1 = CommonUtils.switchClass(GetAllBatchInfoResD1.class, getAllReq.getBusData());
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os = batchIService.ExportBatchInfo(objEGetAllBatchInfoResD1);
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
            exportSWareInfoResB.setMsgCode(objSystemException.getMsgCode());
            exportSWareInfoResB.setMsgDes(objSystemException.getMsgDes());
            exportSWareInfoRes.setBody(exportSWareInfoResB);
            exportSWareInfoRes.setStatus("01");
        }

        return exportSWareInfoRes;
    }

    /**
     * 通过批次ID获取批次信息
     *
     * @param request
     * @param getAllReq
     * @return
     */
    @RequestMapping(value = "/GetBatchInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetBatchInfoRes QBatchInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetBatchInfoRes objEGetBatchInfoRes = null;
        GetBatchInfoResB objEGetBatchInfoResB = new GetBatchInfoResB();
        try {
            objEGetBatchInfoRes = batchIService.QBatchInfo(request, getAllReq);
            objEGetBatchInfoRes.getBody().setMsgCode("0x00000");
        } catch (SystemException objSystemException) {
            objEGetBatchInfoResB.setMsgCode(objSystemException.getMsgCode());
            objEGetBatchInfoResB.setMsgDes(objSystemException.getMsgDes());
            objEGetBatchInfoRes = new GetBatchInfoRes();
            objEGetBatchInfoRes.setStatus("00");
            objEGetBatchInfoRes.setBody(objEGetBatchInfoResB);
        }
        return objEGetBatchInfoRes;
    }

    /**
     * 保存批次信息
     *
     * @param request
     * @param saveReq
     * @return
     */
    @RequestMapping(value = "/SaveBatchInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveBatchInfoRes SaveBatchInfo(HttpServletRequest request, SaveReq saveReq) {
        SaveBatchInfoRes objESaveBatchInfoRes = new SaveBatchInfoRes();
        SaveBatchInfoResB objESaveBatchInfoResB = new SaveBatchInfoResB();

        String busData = saveReq.getBusData();

        if ("00".equals(saveReq.getExecType())) { //新增

            SaveBatchInfoReqBD00 objESaveBatchInfoReqBD00 = CommonUtils.switchClass(SaveBatchInfoReqBD00.class, busData);

            try {
                objESaveBatchInfoRes = batchIService.AddBatch(objESaveBatchInfoReqBD00);

                objESaveBatchInfoRes.getBody().setMsgCode("0x00000");
                objESaveBatchInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveBatchInfoResB.setMsgCode(e.getMsgCode());
                objESaveBatchInfoResB.setMsgDes(e.getMsgDes());
                objESaveBatchInfoRes.setBody(objESaveBatchInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) { //删除

            SaveBatchInfoReqBD01 objESaveBatchInfoReqBD01 = CommonUtils.switchClass(SaveBatchInfoReqBD01.class, busData);

            try {
                objESaveBatchInfoRes = batchIService.RmBatch(objESaveBatchInfoReqBD01);

                objESaveBatchInfoRes.getBody().setMsgCode("0x00000");
                objESaveBatchInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveBatchInfoResB.setMsgCode(e.getMsgCode());
                objESaveBatchInfoResB.setMsgDes(e.getMsgDes());
                objESaveBatchInfoRes.setBody(objESaveBatchInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) { //编辑

            SaveBatchInfoReqBD02 objESaveBatchInfoReqBD02 = CommonUtils.switchClass(SaveBatchInfoReqBD02.class, busData);

            try {
                objESaveBatchInfoRes = batchIService.ModBatch(objESaveBatchInfoReqBD02);

                objESaveBatchInfoRes.getBody().setMsgCode("0x00000");
                objESaveBatchInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveBatchInfoResB.setMsgCode(e.getMsgCode());
                objESaveBatchInfoResB.setMsgDes(e.getMsgDes());
                objESaveBatchInfoRes.setBody(objESaveBatchInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) { //物料创建
            SaveBatchInfoReqBD03 objESaveBatchInfoReqBD03 = CommonUtils.switchClass(SaveBatchInfoReqBD03.class, busData);

            try {
                objESaveBatchInfoRes = batchIService.AddMVBatch(objESaveBatchInfoReqBD03);
                objESaveBatchInfoRes.getBody().setMsgCode("0x00000");
                objESaveBatchInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveBatchInfoResB.setMsgCode(e.getMsgCode());
                objESaveBatchInfoResB.setMsgDes(e.getMsgDes());
                objESaveBatchInfoRes.setBody(objESaveBatchInfoResB);
            }
        }

        objESaveBatchInfoRes.setStatus("00");
        return objESaveBatchInfoRes;
    }

    //获取批次拆分、合并信息
    @RequestMapping(value = "/GetSUBInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSUBInfoRes GetSUBInfo(GetAllReq getAllReq) {

        GetSUBInfoRes objEGetSUBInfoRes = new GetSUBInfoRes();

        String busData = getAllReq.getBusData();

        if ("00".equals(getAllReq.getExecType())) {

            GetSUBInfoReqBD00 objEGetSUBInfoReqBD00 = CommonUtils.switchClass(GetSUBInfoReqBD00.class, busData);

            try {

                objEGetSUBInfoRes = batchIService.GetSUBInfo(objEGetSUBInfoReqBD00);
                objEGetSUBInfoRes.getBody().setMsgCode("0x00000");
                objEGetSUBInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {

                GetSUBInfoResB objEGetSUBInfoResB = new GetSUBInfoResB();
                objEGetSUBInfoResB.setMsgCode(e.getMsgCode());
                objEGetSUBInfoResB.setMsgDes(e.getMsgDes());
                objEGetSUBInfoRes.setBody(objEGetSUBInfoResB);
            }

        }

        objEGetSUBInfoRes.setStatus("00");

        return objEGetSUBInfoRes;
    }

    //保存拆分、合并批次信息
    @RequestMapping(value = "/SaveSUBInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveSUBInfoRes SaveSUBInfo(SaveReq saveReq) {

        SaveSUBInfoRes objESaveSUBInfoRes = new SaveSUBInfoRes();
        SaveSUBInfoResB objESaveSUBInfoResB = new SaveSUBInfoResB();

        String busData = saveReq.getBusData();

        if ("00".equals(saveReq.getExecType())) {
            SaveSUBInfoReqBD00 objESaveSUBInfoReqBD00 = CommonUtils.switchClass(SaveSUBInfoReqBD00.class, busData);

            try {
                objESaveSUBInfoRes = batchIService.AddSplitBatch(objESaveSUBInfoReqBD00);
                objESaveSUBInfoResB.setMsgCode("0x00000");
                objESaveSUBInfoResB.setMsgDes("成功");
            } catch (SystemException e) {
                objESaveSUBInfoResB.setMsgCode(e.getMsgCode());
                objESaveSUBInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("01".equals(saveReq.getExecType())){
            SaveSUBInfoReqBD01 objESaveSUBInfoReqBD01 = CommonUtils.switchClass(SaveSUBInfoReqBD01.class, busData);

            try {
                objESaveSUBInfoRes = batchIService.AddAndBatch(objESaveSUBInfoReqBD01);
                objESaveSUBInfoResB.setMsgCode("0x00000");
                objESaveSUBInfoResB.setMsgDes("成功");
            } catch (SystemException e) {
                objESaveSUBInfoResB.setMsgCode(e.getMsgCode());
                objESaveSUBInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveSUBInfoRes.setStatus("00");
        objESaveSUBInfoRes.setBody(objESaveSUBInfoResB);

        return objESaveSUBInfoRes;
    }

    //批次操作
    @RequestMapping(value = "/SaveBOptInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveBOptInfoRes SaveBOpt(SaveReq saveReq) {
        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();
        SaveBOptInfoResB objESaveBOptInfoResB = new SaveBOptInfoResB();

        String busData = saveReq.getBusData();

        try {
            if ("00".equals(saveReq.getExecType())) { //冻结

                SaveBOptInfoReqBD00 objESaveBOptInfoReqBD00 = CommonUtils.switchClass(SaveBOptInfoReqBD00.class, busData);
                objESaveBOptInfoRes = batchIService.AddHold(objESaveBOptInfoReqBD00);
            } else if ("01".equals(saveReq.getExecType())) { //解冻

                SaveBOptInfoReqBD00 objESaveBOptInfoReqBD00 = CommonUtils.switchClass(SaveBOptInfoReqBD00.class, busData);
                objESaveBOptInfoRes = batchIService.AddUnHold(objESaveBOptInfoReqBD00);
            } else if ("02".equals(saveReq.getExecType())) { //更改数量

                SaveBOptInfoReqBD02[] objESaveBOptInfoReqBD02 = CommonUtils.switchClass(SaveBOptInfoReqBD02[].class, busData);
                objESaveBOptInfoRes = batchIService.AddChgQty(objESaveBOptInfoReqBD02);
            } else if ("03".equals(saveReq.getExecType())) { //不良

                SaveBOptInfoReqBD00 objESaveBOptInfoReqBD00 = CommonUtils.switchClass(SaveBOptInfoReqBD00.class, busData);
                objESaveBOptInfoRes = batchIService.AddReject(objESaveBOptInfoReqBD00);
            } else if ("04".equals(saveReq.getExecType())) { //打开

                SaveBOptInfoReqBD00 objESaveBOptInfoReqBD00 = CommonUtils.switchClass(SaveBOptInfoReqBD00.class, busData);
                objESaveBOptInfoRes = batchIService.AddOpen(objESaveBOptInfoReqBD00);
            } else if ("05".equals(saveReq.getExecType())) { //关闭

                SaveBOptInfoReqBD00 objESaveBOptInfoReqBD00 = CommonUtils.switchClass(SaveBOptInfoReqBD00.class, busData);
                objESaveBOptInfoRes = batchIService.AddClose(objESaveBOptInfoReqBD00);
            } else if ("06".equals(saveReq.getExecType())) { //报废原因

                SaveBOptInfoReqBD00 objESaveBOptInfoReqBD00 = CommonUtils.switchClass(SaveBOptInfoReqBD00.class, busData);
                objESaveBOptInfoRes = batchIService.AddScrop(objESaveBOptInfoReqBD00);
            }

            objESaveBOptInfoResB.setMsgCode("0x00000");
            objESaveBOptInfoResB.setMsgDes("成功");
        }catch (SystemException e){
            objESaveBOptInfoResB.setMsgCode(e.getMsgCode());
            objESaveBOptInfoResB.setMsgDes(e.getMsgDes());
        }

        objESaveBOptInfoRes.setBody(objESaveBOptInfoResB);
        objESaveBOptInfoRes.setStatus("00");

        return objESaveBOptInfoRes;
    }

    //获取冻结批次列表
    @RequestMapping(value = "/getHoldBatch", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getAllRVInfo(@RequestBody BaseRequest<GetHoldBatchReq> request) {
        try {
            return BaseResponse.success(batchIService.GetAllHoldBatch(request));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }
}
