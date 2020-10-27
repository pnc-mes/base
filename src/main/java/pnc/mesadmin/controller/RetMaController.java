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
import pnc.mesadmin.dto.GetAllNRetMaInfo.GetAllNRetMaInfoRes;
import pnc.mesadmin.dto.GetAllNRetMaInfo.GetAllNRetMaInfoResB;
import pnc.mesadmin.dto.GetAllNRetMaInfo.GetAllNRetMaInfoResD;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllRetMaInfo.GetAllRetMaInfoRes;
import pnc.mesadmin.dto.GetAllRetMaInfo.GetAllRetMaInfoResB;
import pnc.mesadmin.dto.GetAllRetMaInfo.GetAllRetMaInfoResD;
import pnc.mesadmin.dto.GetNRMInfo.GetNRMInfoReqBD00;
import pnc.mesadmin.dto.GetNRMInfo.GetNRMInfoRes;
import pnc.mesadmin.dto.GetNRMInfo.GetNRMInfoResB;
import pnc.mesadmin.dto.GetNRMInfo.GetNRMInfoResD;
import pnc.mesadmin.dto.GetNRMTotalInfo.GetNRMTotalInfoReqBD00;
import pnc.mesadmin.dto.GetNRMTotalInfo.GetNRMTotalInfoRes;
import pnc.mesadmin.dto.GetNRMTotalInfo.GetNRMTotalInfoResB;
import pnc.mesadmin.dto.GetNRMTotalInfo.GetNRMTotalInfoResD;
import pnc.mesadmin.dto.GetRMTotalInfo.GetRMTotalInfoReqBD00;
import pnc.mesadmin.dto.GetRMTotalInfo.GetRMTotalInfoRes;
import pnc.mesadmin.dto.GetRMTotalInfo.GetRMTotalInfoResB;
import pnc.mesadmin.dto.GetRMTotalInfo.GetRMTotalInfoResD;
import pnc.mesadmin.dto.GetRetMaInfo.GetRetMaInfoReqBD00;
import pnc.mesadmin.dto.GetRetMaInfo.GetRetMaInfoRes;
import pnc.mesadmin.dto.GetRetMaInfo.GetRetMaInfoResB;
import pnc.mesadmin.dto.GetRetMaInfo.GetRetMaInfoResD;
import pnc.mesadmin.dto.SaveImportRes.SaveImportRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveNRMInfo.SaveNRMInfoReqBD00;
import pnc.mesadmin.dto.SaveNRMInfo.SaveNRMInfoRes;
import pnc.mesadmin.dto.SaveNRMInfo.SaveNRMInfoResB;
import pnc.mesadmin.dto.SaveRMInfoRes.*;
import pnc.mesadmin.service.RetMaInfoIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by test on 2017/9/21.
 */
@Controller
@Scope("prototype")
@RequestMapping("/RetMa")
public class RetMaController {

    @Resource
    private RetMaInfoIService retMaInfoService;

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping(value = "/RetMaPG")
    public String toRetMaPG(){
        return "plan/returnma/returnmainfo";
    }

    @RequestMapping(value = "/NRetMaPG")
    public String toNRetMaPG(){
        return "plan/returnma/nretmainfo";
    }

    @RequestMapping(value = "/importPG")
    public String toimportPG(){
        return "plan/returnma/importnretmainfo";
    }

    /**
     * 获取所有的退料单
     * @param argGetAllReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetAllRMInfo",method = RequestMethod.POST)
    public GetAllRetMaInfoRes getAllRetMaInfo(GetAllReq argGetAllReq){
        GetAllRetMaInfoRes objGetAllRetMaInfoRes = new GetAllRetMaInfoRes();
        GetAllRetMaInfoResB objGetAllRetMaInfoResB = new GetAllRetMaInfoResB();
        List<GetAllRetMaInfoResD> objGetAllRetMaInfoResDList = new ArrayList<GetAllRetMaInfoResD>(Collections.<GetAllRetMaInfoResD>emptyList());
        if("00".equals(argGetAllReq.getExecType())){
            String pageInfoStr = "";
            if(argGetAllReq.getPageInfo() != null)
                pageInfoStr = argGetAllReq.getPageInfo();
            PageInfo pageInfo = null;
            if(!"".equals(pageInfoStr))
                pageInfo = CommonUtils.switchClass(PageInfo.class,pageInfoStr);

            String initDataStr = "";
            if(argGetAllReq.getInitData() != null)
                initDataStr = argGetAllReq.getInitData();
            InitData initData = null;
            List<InitDataField> initDataFieldList = new ArrayList<InitDataField>(Collections.<InitDataField>emptyList());
            if(!"".equals(initDataStr)) {
                initData = CommonUtils.switchClass(InitData.class, initDataStr);
                if(initData != null){
                    initDataFieldList = initData.getFiledList();
                }
            }
            objGetAllRetMaInfoResB = retMaInfoService.GetAllRetMaInfo(initDataFieldList,pageInfo);
        }else{
            objGetAllRetMaInfoResB.setData(objGetAllRetMaInfoResDList);
            objGetAllRetMaInfoResB.setMsgCode("05");
            objGetAllRetMaInfoResB.setMsgDes("查询退料单列表信息失败！");
        }
        objGetAllRetMaInfoRes.setBody(objGetAllRetMaInfoResB);
        objGetAllRetMaInfoRes.setStatus("00");
        return objGetAllRetMaInfoRes;
    }

    /**
     * 查询退料单详细信息
     * @param argGetAllReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetRMInfo",method = RequestMethod.POST)
    public GetRetMaInfoRes GetRMInfo(GetAllReq argGetAllReq){
        GetRetMaInfoRes objGetRetMaInfoRes = new GetRetMaInfoRes();
        GetRetMaInfoResB objGetRetMaInfoResB = new GetRetMaInfoResB();
        GetRetMaInfoResD objGetRetMaInfoResD = new GetRetMaInfoResD();
        if("00".equals(argGetAllReq.getExecType())){
            String busData = argGetAllReq.getBusData();
            GetRetMaInfoReqBD00 busData00 = CommonUtils.switchClass(GetRetMaInfoReqBD00.class,busData);
            try {
                objGetRetMaInfoResB = retMaInfoService.GetRetMaInfoRes(busData00.getRetRd());
            }catch (SystemException e){
                objGetRetMaInfoResB.setData(objGetRetMaInfoResD);
                objGetRetMaInfoResB.setMsgCode(e.getMsgCode());
                objGetRetMaInfoResB.setMsgDes(e.getMsgDes());
            }
        }else{
            objGetRetMaInfoResB.setData(objGetRetMaInfoResD);
            objGetRetMaInfoResB.setMsgCode("06");
            objGetRetMaInfoResB.setMsgDes("查询退料单信息失败！");
        }
        objGetRetMaInfoRes.setBody(objGetRetMaInfoResB);
        objGetRetMaInfoRes.setStatus("00");
        return objGetRetMaInfoRes;
    }

    /**
     * 查询退料汇总信息
     * @param argGetAllReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetRMTotalInfo",method = RequestMethod.POST)
    public GetRMTotalInfoRes getRMTotalInfo(GetAllReq argGetAllReq){
        GetRMTotalInfoRes argGetRMTotalInfoRes = new GetRMTotalInfoRes();
        GetRMTotalInfoResB argGetRMTotalInfoResB = new GetRMTotalInfoResB();
        List<GetRMTotalInfoResD> argGetRMTotalInfoResDList = new ArrayList<GetRMTotalInfoResD>(Collections.<GetRMTotalInfoResD>emptyList());

        if("00".equals(argGetAllReq.getExecType())){
            String busData = argGetAllReq.getBusData();
            GetRMTotalInfoReqBD00 busData00 = CommonUtils.switchClass(GetRMTotalInfoReqBD00.class,busData);
            argGetRMTotalInfoResB = retMaInfoService.GetRMTotalInfo(busData00);
        }else {
            argGetRMTotalInfoResB.setData(argGetRMTotalInfoResDList);
            argGetRMTotalInfoResB.setMsgCode("07");
            argGetRMTotalInfoResB.setMsgDes("查询退料单汇总信息失败");
        }

        argGetRMTotalInfoRes.setBody(argGetRMTotalInfoResB);
        argGetRMTotalInfoRes.setStatus("00");
        return argGetRMTotalInfoRes;
    }

    /**
     * 保存退料信息
     * @param argSaveReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SaveRMInfo",method = RequestMethod.POST)
    public SaveRMInfoRes saveRMInfo(SaveReq argSaveReq){
        SaveRMInfoRes objSaveRMInfoRes = new SaveRMInfoRes();
        SaveRMInfoResB objSaveRMInfoResB = new SaveRMInfoResB();
        String busData = argSaveReq.getBusData();
        if("00".equals(argSaveReq.getExecType())){//新增
            SaveRMInfoReqBD00 busData00 = CommonUtils.switchClass(SaveRMInfoReqBD00.class,busData);
            try {
                objSaveRMInfoResB = retMaInfoService.AddRMInfo(busData00);
            }catch (SystemException e){
                objSaveRMInfoResB.setMsgCode(e.getMsgCode());
                objSaveRMInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        else if("01".equals(argSaveReq.getExecType())){//删除
            SaveRMInfoReqBD01 busData01 = CommonUtils.switchClass(SaveRMInfoReqBD01.class,busData);
            try{
                objSaveRMInfoResB = retMaInfoService.RmRMInfo(busData01);
            }catch(SystemException e){
                objSaveRMInfoResB.setMsgCode(e.getMsgCode());
                objSaveRMInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        else if("02".equals(argSaveReq.getExecType())){//修改
            SaveRMInfoReqBD02 busData02 = CommonUtils.switchClass(SaveRMInfoReqBD02.class,busData);
            try{
                objSaveRMInfoResB = retMaInfoService.ModRMInfo(busData02);
            }catch(SystemException e){
                objSaveRMInfoResB.setMsgCode(e.getMsgCode());
                objSaveRMInfoResB.setMsgDes(e.getMsgDes());
            }
        } else if("03".equals(argSaveReq.getExecType())) {//下达
            SaveRMInfoReqBD03 busData03 = CommonUtils.switchClass(SaveRMInfoReqBD03.class, busData);
            try {
                objSaveRMInfoResB = retMaInfoService.ModRMInfo03(busData03);
            } catch (SystemException e) {
                objSaveRMInfoResB.setMsgCode(e.getMsgCode());
                objSaveRMInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        else if("04".equals(argSaveReq.getExecType())) {//取消
            SaveRMInfoReqBD04 busData04 = CommonUtils.switchClass(SaveRMInfoReqBD04.class, busData);
            try {
                objSaveRMInfoResB = retMaInfoService.ModRMInfo04(busData04);
            } catch (SystemException e) {
                objSaveRMInfoResB.setMsgCode(e.getMsgCode());
                objSaveRMInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        else{
            objSaveRMInfoResB.setMsgCode("08");
            objSaveRMInfoResB.setMsgDes("保存失败，请稍候再进行保存!");
        }

        objSaveRMInfoRes.setBody(objSaveRMInfoResB);
        objSaveRMInfoRes.setStatus("00");
        return objSaveRMInfoRes;
    }

    /**
     * 获取所有的退料单
     * @param argGetAllReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetAllNRMInfo",method = RequestMethod.POST)
    public GetAllNRetMaInfoRes GetAllNRMInfo(GetAllReq argGetAllReq){
        GetAllNRetMaInfoRes objGetAllRetMaInfoRes = new GetAllNRetMaInfoRes();
        GetAllNRetMaInfoResB objGetAllRetMaInfoResB = new GetAllNRetMaInfoResB();
        List<GetAllNRetMaInfoResD> objGetAllNRetMaInfoResDList = new ArrayList<GetAllNRetMaInfoResD>(Collections.<GetAllNRetMaInfoResD>emptyList());
        if("00".equals(argGetAllReq.getExecType())){
            String pageInfoStr = "";
            if(argGetAllReq.getPageInfo() != null)
                pageInfoStr = argGetAllReq.getPageInfo();
            PageInfo pageInfo = null;
            if(!"".equals(pageInfoStr))
                pageInfo = CommonUtils.switchClass(PageInfo.class,pageInfoStr);

            String initDataStr = "";
            if(argGetAllReq.getInitData() != null)
                initDataStr = argGetAllReq.getInitData();
            InitData initData = null;
            List<InitDataField> initDataFieldList = new ArrayList<InitDataField>(Collections.<InitDataField>emptyList());
            if(!"".equals(initDataStr)) {
                initData = CommonUtils.switchClass(InitData.class, initDataStr);
                if(initData != null){
                    initDataFieldList = initData.getFiledList();
                }
            }
            objGetAllRetMaInfoResB = retMaInfoService.GetAllNRetMaInfo(initDataFieldList,pageInfo);
        }else{
            objGetAllRetMaInfoResB.setData(objGetAllNRetMaInfoResDList);
            objGetAllRetMaInfoResB.setMsgCode("05");
            objGetAllRetMaInfoResB.setMsgDes("查询退料单列表信息失败！");
        }
        objGetAllRetMaInfoRes.setBody(objGetAllRetMaInfoResB);
        objGetAllRetMaInfoRes.setStatus("00");
        return objGetAllRetMaInfoRes;
    }
    /**
     * 查询退料单详细信息
     * @param argGetAllReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/GetNRMInfo",method = RequestMethod.POST)
    public GetNRMInfoRes GetNRMInfo(GetAllReq argGetAllReq){
        GetNRMInfoRes objGetRetMaInfoRes = new GetNRMInfoRes();
        GetNRMInfoResB objGetRetMaInfoResB = new GetNRMInfoResB();
        GetNRMInfoResD objGetRetMaInfoResD = new GetNRMInfoResD();
        if("00".equals(argGetAllReq.getExecType())){
            String busData = argGetAllReq.getBusData();
            GetNRMInfoReqBD00 busData00 = CommonUtils.switchClass(GetNRMInfoReqBD00.class,busData);
            try {
                objGetRetMaInfoResB = retMaInfoService.GetNRetMaInfo(busData00.getRetRd());
            }catch (SystemException e){
                objGetRetMaInfoResB.setData(objGetRetMaInfoResD);
                objGetRetMaInfoResB.setMsgCode(e.getMsgCode());
                objGetRetMaInfoResB.setMsgDes(e.getMsgDes());
            }
        }else{
            objGetRetMaInfoResB.setData(objGetRetMaInfoResD);
            objGetRetMaInfoResB.setMsgCode("06");
            objGetRetMaInfoResB.setMsgDes("查询退料单信息失败！");
        }
        objGetRetMaInfoRes.setBody(objGetRetMaInfoResB);
        objGetRetMaInfoRes.setStatus("00");
        return objGetRetMaInfoRes;
    }

    //获取退料汇总信息
    @ResponseBody
    @RequestMapping(value = "/GetNRMTotalInfo",method = RequestMethod.POST)
    public GetNRMTotalInfoRes GetNRMTotalInfo(GetAllReq argGetAllReq) {
        GetNRMTotalInfoRes objGetNRMTotalInfoRes = new GetNRMTotalInfoRes();
        GetNRMTotalInfoResB objGetNRMTotalInfoResB = new GetNRMTotalInfoResB();

        if("00".equals(argGetAllReq.getExecType())){
            String initDataStr = "";
            if(argGetAllReq.getInitData() != null)
                initDataStr = argGetAllReq.getInitData();
            InitData initData = null;
            List<InitDataField> initDataFieldList = new ArrayList<InitDataField>(Collections.<InitDataField>emptyList());
            if(!"".equals(initDataStr)) {
                initData = CommonUtils.switchClass(InitData.class, initDataStr);
                if(initData != null){
                    initDataFieldList = initData.getFiledList();
                }
            }
            GetNRMTotalInfoReqBD00 busData00 = CommonUtils.switchClass(GetNRMTotalInfoReqBD00.class,argGetAllReq.getBusData());
            objGetNRMTotalInfoResB = retMaInfoService.GetNRMTotalInfo(busData00,initDataFieldList);
        }else{
            objGetNRMTotalInfoResB.setMsgCode("0x00001");
            objGetNRMTotalInfoResB.setMsgDes("非法请求");
            objGetNRMTotalInfoResB.setData(new GetNRMTotalInfoResD());
        }

        objGetNRMTotalInfoRes.setBody(objGetNRMTotalInfoResB);
        objGetNRMTotalInfoRes.setStatus("00");
        return objGetNRMTotalInfoRes;
    }

    /**
     * 保存退料信息
     * @param argSaveReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/SaveNRMInfo",method = RequestMethod.POST)
    public SaveNRMInfoRes saveNRMInfo(SaveReq argSaveReq){
        SaveNRMInfoRes objSaveRMInfoRes = new SaveNRMInfoRes();
        SaveNRMInfoResB objSaveNRMInfoResB = new SaveNRMInfoResB();
        String busData = argSaveReq.getBusData();
        if("00".equals(argSaveReq.getExecType())){//新增
            SaveNRMInfoReqBD00 busData00 = CommonUtils.switchClass(SaveNRMInfoReqBD00.class,busData);
            try {
                objSaveNRMInfoResB = retMaInfoService.AddNRMInfo(busData00);
            }catch (SystemException e){
                objSaveNRMInfoResB.setMsgCode(e.getMsgCode());
                objSaveNRMInfoResB.setMsgDes(e.getMsgDes());
            }
        }else{
            objSaveNRMInfoResB.setMsgCode("08");
            objSaveNRMInfoResB.setMsgDes("保存失败，请稍候再进行保存!");
        }

        objSaveRMInfoRes.setBody(objSaveNRMInfoResB);
        objSaveRMInfoRes.setStatus("00");
        return objSaveRMInfoRes;
    }
    //exportExcel
    //导入
    @ResponseBody
    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    public SaveImportRes importExcel(HttpServletRequest request, @RequestParam("upload") CommonsMultipartFile file) throws IOException {
        SaveImportRes objSaveImportRes = new SaveImportRes();
        SaveImportResB objSaveImportResB = new SaveImportResB();
        try {
            objSaveImportResB = retMaInfoService.AddImport(file);
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
    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST)
    public ExportSWareInfoRes exportBOMExcel(HttpServletResponse response, GetAllReq getAllReq) throws IOException {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if("00".equals(getAllReq.getExecType())) {
            Integer busData = CommonUtils.switchClass(Integer.class,getAllReq.getBusData());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = retMaInfoService.exportExcel(busData);
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
