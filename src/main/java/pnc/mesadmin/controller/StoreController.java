package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoRes;
import pnc.mesadmin.dto.GetAllLInfo.GetAllLInfoResB;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoReqBD00;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoRes;
import pnc.mesadmin.dto.GetAllStoreInfo.GetAllStoreInfoResB;
import pnc.mesadmin.dto.GetLInfo.GetLInfoReqBD00;
import pnc.mesadmin.dto.GetLInfo.GetLInfoRes;
import pnc.mesadmin.dto.GetLInfo.GetLInfoResB;
import pnc.mesadmin.dto.GetStoreInfo.GetStoreInfoReqBD00;
import pnc.mesadmin.dto.GetStoreInfo.GetStoreInfoRes;
import pnc.mesadmin.dto.GetStoreInfo.GetStoreInfoResB;
import pnc.mesadmin.dto.SaveLInfo.*;
import pnc.mesadmin.dto.SaveStoreInfo.*;
import pnc.mesadmin.entity.LocationInfo;
import pnc.mesadmin.entity.StoreInfo;
import pnc.mesadmin.service.StoreIService;
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
 * 子系统名称：仓库管理Controller
 * 创建人：刘福志
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Store")
public class StoreController {

    @Resource
    private StoreIService storeIService;

    //加载页面
    @RequestMapping("/StorePG")
    public String StorePG(){
        return "warehouse/store/storeinfo";
    }

    @RequestMapping("/LPG")
    public String LPG(){
        return "warehouse/location/locationinfo";
    }


    //获取仓库列表信息
    @RequestMapping(value ="/GetAllStoreInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllStoreInfoRes GetAllStoreInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllStoreInfoRes objEGetAllStoreInfoRes=new GetAllStoreInfoRes();

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
            String busData = getAllReq.getBusData();
            GetAllStoreInfoReqBD00 busData00=null;
            if(busData!=null) {
                busData00 = CommonUtils.switchClass(GetAllStoreInfoReqBD00.class, busData);
            }
            try {
                objEGetAllStoreInfoRes = storeIService.QALLselectAllStoreInfo(objEInitDataFields, pageInfo,busData00);
                objEGetAllStoreInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllStoreInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllStoreInfoResB objEGetAllStoreInfoResB = new GetAllStoreInfoResB();
                objEGetAllStoreInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllStoreInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllStoreInfoRes.setBody(objEGetAllStoreInfoResB);
            }
        }else{
            GetAllStoreInfoResB objEGetAllStoreInfoResB = new GetAllStoreInfoResB();
            objEGetAllStoreInfoResB.setMsgCode("MG0006F");
            objEGetAllStoreInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllStoreInfoRes.setBody(objEGetAllStoreInfoResB);
        }

        objEGetAllStoreInfoRes.setStatus("00");
        return objEGetAllStoreInfoRes;
    }

    @RequestMapping(value = "/GetStoreInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetStoreInfoRes GetStoreInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetStoreInfoRes objEGetStoreInfoRes=new GetStoreInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetStoreInfoReqBD00 busData00 =  CommonUtils.switchClass(GetStoreInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetStoreInfoRes = storeIService.GetStoreInfo(busData00.getStoreRd());
                    objEGetStoreInfoRes.getBody().setMsgCode("0x00000");
                    objEGetStoreInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetStoreInfoResB objEGetStoreInfoResB = new GetStoreInfoResB();
                    objEGetStoreInfoResB.setMsgCode(e.getMsgCode());
                    objEGetStoreInfoResB.setMsgDes(e.getMsgDes());
                    objEGetStoreInfoRes.setBody(objEGetStoreInfoResB);
                }
            }
        }else{
            GetStoreInfoResB objEGetStoreInfoResB = new GetStoreInfoResB();
            objEGetStoreInfoResB.setMsgCode("MG0006F");
            objEGetStoreInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetStoreInfoRes.setBody(objEGetStoreInfoResB);
        }

        objEGetStoreInfoRes.setStatus("00");

        return objEGetStoreInfoRes;
    }

    //获取库位列表信息
    @RequestMapping(value = "/GetAllLInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllLInfoRes GetAllLInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllLInfoRes objEGetAllLInfoRes=new GetAllLInfoRes();
        if("00".equals(getAllReq.getExecType())) {
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
                try {
                    objEGetAllLInfoRes = storeIService.QALLselectAllLocationInfo(objEInitDataFields, pageInfo);
                    objEGetAllLInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllLInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllLInfoResB objEGetAllLInfoResB = new GetAllLInfoResB();
                    objEGetAllLInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllLInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllLInfoRes.setBody(objEGetAllLInfoResB);
                }
        }else{
            GetAllLInfoResB objEGetAllLInfoResB = new GetAllLInfoResB();
            objEGetAllLInfoResB.setMsgCode("MG0006F");
            objEGetAllLInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllLInfoRes.setBody(objEGetAllLInfoResB);
        }

        objEGetAllLInfoRes.setStatus("00");

        return objEGetAllLInfoRes;
    }


    //获取库位信息
    @RequestMapping(value = "/GetLInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetLInfoRes GetLInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetLInfoRes objEGetLInfoRes=new GetLInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetLInfoReqBD00 busData00 =  CommonUtils.switchClass(GetLInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetLInfoRes = storeIService.GetselectLocationInfo(busData00.getLRd());
                    objEGetLInfoRes.getBody().setMsgCode("0x00000");
                    objEGetLInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetLInfoResB objEGetLInfoResB = new GetLInfoResB();
                    objEGetLInfoResB.setMsgCode(e.getMsgCode());
                    objEGetLInfoResB.setMsgDes(e.getMsgDes());
                    objEGetLInfoRes.setBody(objEGetLInfoResB);
                }
            }
        }else{
            GetLInfoResB objEGetLInfoResB = new GetLInfoResB();
            objEGetLInfoResB.setMsgCode("MG0006F");
            objEGetLInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetLInfoRes.setBody(objEGetLInfoResB);
        }

        objEGetLInfoRes.setStatus("00");

        return objEGetLInfoRes;
    }

    //筛选接口
    @RequestMapping(value = "/GetAllLInfo1",method = RequestMethod.POST)
    @ResponseBody
    public GetAllLInfoRes GetAllLInfo1(HttpServletRequest request, GetAllReq getAllReq){
        GetAllLInfoRes objEGetAllLInfoRes=new GetAllLInfoRes();
        if("00".equals(getAllReq.getExecType())) {

            String busData = getAllReq.getBusData();

            GetStoreInfoReqBD00 busData00 =  CommonUtils.switchClass(GetStoreInfoReqBD00.class,busData);
            try {
                objEGetAllLInfoRes = storeIService.QALLselectAllLocationInfo1(busData00);
                objEGetAllLInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllLInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllLInfoResB objEGetAllLInfoResB = new GetAllLInfoResB();
                objEGetAllLInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllLInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllLInfoRes.setBody(objEGetAllLInfoResB);
            }
        }else{
            GetAllLInfoResB objEGetAllLInfoResB = new GetAllLInfoResB();
            objEGetAllLInfoResB.setMsgCode("MG0006F");
            objEGetAllLInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllLInfoRes.setBody(objEGetAllLInfoResB);
        }

        objEGetAllLInfoRes.setStatus("00");

        return objEGetAllLInfoRes;
    }


    //导出库位信息
    @RequestMapping(value ="/ExportGetLInfo",method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportGetLInfo(HttpServletResponse response,GetAllReq getAllReq){
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        String busData = getAllReq.getBusData();

        GetStoreInfoReqBD00 busData00 =  CommonUtils.switchClass(GetStoreInfoReqBD00.class,busData);

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os = storeIService.ExportGetLInfo(busData00);
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



    //保存库位信息
    @RequestMapping(value = "/SaveLInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveLInfoRes SaveLInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveLInfoRes saveLInfoRes = new SaveLInfoRes();

        SaveLInfoResB saveLInfoResB = new SaveLInfoResB();

        LocationInfo locationInfo = new LocationInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveLInfoReqBD00 busData00 = CommonUtils.switchClass(SaveLInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveLInfoRes = storeIService.AddinsertLocationInfo(busData00,locationInfo);

                saveLInfoRes.getBody().setMsgCode("0x00000");
                saveLInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                saveLInfoResB.setMsgCode(e.getMsgCode());
                saveLInfoResB.setMsgDes(e.getMsgDes());
                saveLInfoRes.setBody(saveLInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveLInfoReqBD01 busData01 = CommonUtils.switchClass(SaveLInfoReqBD01.class,rowData);
            try {
                saveLInfoRes = storeIService.RmdeleteLocationInfo(busData01.getLRd());
                SaveLInfoResB body = new SaveLInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveLInfoRes.setBody(body);
            }catch (SystemException e){
                saveLInfoResB.setMsgCode(e.getMsgCode());
                saveLInfoResB.setMsgDes(e.getMsgDes());
                saveLInfoRes.setBody(saveLInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveLInfoReqBD02 busData02 = CommonUtils.switchClass(SaveLInfoReqBD02.class,rowData);
            try {
                saveLInfoRes = storeIService.ModupdateLocationInfo(busData02,locationInfo);
                SaveLInfoResB body = new SaveLInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveLInfoRes.setBody(body);
            }catch (SystemException e){
                saveLInfoResB.setMsgCode(e.getMsgCode());
                saveLInfoResB.setMsgDes(e.getMsgDes());
                saveLInfoRes.setBody(saveLInfoResB);
            }
        }

        saveLInfoRes.setStatus("00");
        return saveLInfoRes;
    }



    //保存仓库信息
    @RequestMapping(value = "/SaveStoreInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveStoreInfoRes SaveStoreInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveStoreInfoRes saveStoreInfoRes = new SaveStoreInfoRes();

        SaveStoreInfoResB saveStoreInfoResB = new SaveStoreInfoResB();

        StoreInfo storeInfo = new StoreInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveStoreInfoReqBD00 busData00 = CommonUtils.switchClass(SaveStoreInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveStoreInfoRes = storeIService.AddinsertStoreInfo(busData00,storeInfo);
                saveStoreInfoRes.getBody().setMsgCode("0x00000");
                saveStoreInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                saveStoreInfoResB.setMsgCode(e.getMsgCode());
                saveStoreInfoResB.setMsgDes(e.getMsgDes());
                saveStoreInfoRes.setBody(saveStoreInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveStoreInfoReqBD01 busData01 = CommonUtils.switchClass(SaveStoreInfoReqBD01.class,rowData);
            try {
                saveStoreInfoRes = storeIService.RmdeleteStoreInfo(busData01.getStoreRd());
                SaveStoreInfoResB body = new SaveStoreInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveStoreInfoRes.setBody(body);
            }catch (SystemException e){
                saveStoreInfoResB.setMsgCode(e.getMsgCode());
                saveStoreInfoResB.setMsgDes(e.getMsgDes());
                saveStoreInfoRes.setBody(saveStoreInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveStoreInfoReqBD02 busData02 = CommonUtils.switchClass(SaveStoreInfoReqBD02.class,rowData);
            try {
                saveStoreInfoRes = storeIService.ModupdateStoreInfo(busData02,storeInfo);
                SaveStoreInfoResB body = new SaveStoreInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveStoreInfoRes.setBody(body);
            }catch (SystemException e){
                saveStoreInfoResB.setMsgCode(e.getMsgCode());
                saveStoreInfoResB.setMsgDes(e.getMsgDes());
                saveStoreInfoRes.setBody(saveStoreInfoResB);
            }
        }
        else{
            saveStoreInfoResB.setMsgCode("MG0006F");
            saveStoreInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveStoreInfoRes.setStatus("00");
        return saveStoreInfoRes;
    }
}
