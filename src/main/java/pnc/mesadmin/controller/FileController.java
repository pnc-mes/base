package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoRes;
import pnc.mesadmin.dto.GetAllFileInfo.GetAllFileInfoResB;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoBD00;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoRes;
import pnc.mesadmin.dto.GetFileTreeInfo.GetFileTreeInfoResB;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoReqBD00;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoReqBD01;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoRes;
import pnc.mesadmin.dto.GetFileVInfo.GetFileVInfoResB;
import pnc.mesadmin.dto.SaveFileInfo.*;
import pnc.mesadmin.entity.FileInfo;
import pnc.mesadmin.entity.FileVerInfo;
import pnc.mesadmin.service.FileIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.MinIoUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件信息Controller
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/File")
public class FileController {

    @Resource
    private FileIService fileInfoService;

    @Resource
    private MinIoUtils minIoUtils;

    //加载页面
    @RequestMapping("/FilePG")
    public String FilePG(){
        return "process/file/fileinfo";
    }

    //获取文件列表信息
    @RequestMapping(value = "/GetAllFileInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllFileInfoRes GetAllFileInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllFileInfoRes objEGetAllFileInfoRes=new GetAllFileInfoRes();

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
                objEGetAllFileInfoRes = fileInfoService.QALLselectAllFileInfo(objEInitDataFields, pageInfo);
                objEGetAllFileInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFileInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllFileInfoResB objEGetAllFileInfoResB = new GetAllFileInfoResB();
                objEGetAllFileInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFileInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFileInfoRes.setBody(objEGetAllFileInfoResB);
            }
        }else{
            GetAllFileInfoResB objEGetAllFileInfoResB = new GetAllFileInfoResB();
            objEGetAllFileInfoResB.setMsgCode("MG0006F");
            objEGetAllFileInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllFileInfoRes.setBody(objEGetAllFileInfoResB);
        }

        objEGetAllFileInfoRes.setStatus("00");
        return objEGetAllFileInfoRes;
    }

    //获取文件版本列表信息
    @RequestMapping(value = "/GetFileTreeInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetFileTreeInfoRes GetSVTreeInfo(GetAllReq getAllReq){
        GetFileTreeInfoRes objEGetFileTreeInfoRes=new GetFileTreeInfoRes();
        if("00".equals(getAllReq.getExecType())){
            GetFileTreeInfoBD00 objEGetFileTreeInfoBD00=CommonUtils.switchClass(GetFileTreeInfoBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }else{
                try{
                    objEGetFileTreeInfoRes= fileInfoService.GetselectfileVerRd(objEGetFileTreeInfoBD00);
                    objEGetFileTreeInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFileTreeInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetFileTreeInfoResB objEGetFileTreeInfoResB =new GetFileTreeInfoResB();
                    objEGetFileTreeInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFileTreeInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFileTreeInfoRes.setBody(objEGetFileTreeInfoResB);
                }
            }
        }
        else {
            GetFileTreeInfoResB objEGetFileTreeInfoResB =new GetFileTreeInfoResB();
            objEGetFileTreeInfoResB.setMsgCode("MG0006F");
            objEGetFileTreeInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetFileTreeInfoRes.setBody(objEGetFileTreeInfoResB);
        }
        objEGetFileTreeInfoRes.setStatus("00");
        return objEGetFileTreeInfoRes;
    }

    //获取文件版本信息
    @RequestMapping(value = "/GetFileVInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetFileVInfoRes GetFileVInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetFileVInfoRes objEGetFileVInfoRes=new GetFileVInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetFileVInfoReqBD00 busData00 = CommonUtils.switchClass(GetFileVInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetFileVInfoRes = fileInfoService.GetselectByfileRd(busData00);
                    objEGetFileVInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFileVInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetFileVInfoResB objEGetFileVInfoResB = new GetFileVInfoResB();
                    objEGetFileVInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFileVInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFileVInfoRes.setBody(objEGetFileVInfoResB);
                }
            }
        }

        else if("01".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetFileVInfoReqBD01 busData01 = CommonUtils.switchClass(GetFileVInfoReqBD01.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetFileVInfoRes = fileInfoService.GetselectByfileVerRd(busData01);
                    objEGetFileVInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFileVInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetFileVInfoResB objEGetFileVInfoResB = new GetFileVInfoResB();
                    objEGetFileVInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFileVInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFileVInfoRes.setBody(objEGetFileVInfoResB);
                }
            }
        }else{
            GetFileVInfoResB objEGetFileVInfoResB = new GetFileVInfoResB();
            objEGetFileVInfoResB.setMsgCode("MG0006F");
            objEGetFileVInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetFileVInfoRes.setBody(objEGetFileVInfoResB);
        }

        objEGetFileVInfoRes.setStatus("00");

        return objEGetFileVInfoRes;
    }

    //保存文件信息
    @RequestMapping(value ="/SaveFileInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveFileInfoRes SaveFileInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveFileInfoRes saveFileInfoRes = new SaveFileInfoRes();

        SaveFileInfoResB saveFileInfoResB = new SaveFileInfoResB();

        FileInfo fileInfo = new FileInfo();

        FileVerInfo fileVerInfo=new FileVerInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveFileInfoReqBD00 busData00 = CommonUtils.switchClass(SaveFileInfoReqBD00.class,rowData);

            // 直接可以获取的表单数据
            try {
                saveFileInfoRes = fileInfoService.AddinsertFileInfo(request,busData00,fileInfo);
                SaveFileInfoResB body = new SaveFileInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileInfoResB.setMsgCode(e.getMsgCode());
                saveFileInfoResB.setMsgDes(e.getMsgDes());
                saveFileInfoRes.setBody(saveFileInfoResB);
            }catch (IOException e) {
                saveFileInfoResB.setMsgCode("");
                saveFileInfoResB.setMsgDes("上传失败");
            }

        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveFileInfoReqBD01 busData01 = CommonUtils.switchClass(SaveFileInfoReqBD01.class,rowData);
            try {
                saveFileInfoRes =fileInfoService.RmdeleteFileInfo(busData01);
                SaveFileInfoResB body = new SaveFileInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileInfoResB.setMsgCode(e.getMsgCode());
                saveFileInfoResB.setMsgDes(e.getMsgDes());
                saveFileInfoRes.setBody(saveFileInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveFileInfoReqBD02 busData02 = CommonUtils.switchClass(SaveFileInfoReqBD02.class,rowData);
            try {
                saveFileInfoRes = fileInfoService.ModupdateFileInfo(request,busData02,fileInfo);
                SaveFileInfoResB body = new SaveFileInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileInfoResB.setMsgCode(e.getMsgCode());
                saveFileInfoResB.setMsgDes(e.getMsgDes());
                saveFileInfoRes.setBody(saveFileInfoResB);
            }catch (IOException e) {
                saveFileInfoResB.setMsgCode("");
                saveFileInfoResB.setMsgDes("上传失败");
            }

        }
        //复制
        else if("03".equals(saveReq.getExecType())){
            SaveFileInfoReqBD03 busData03 = CommonUtils.switchClass(SaveFileInfoReqBD03.class,rowData);
            try {
                saveFileInfoRes = fileInfoService.copyFileInfo(busData03,fileInfo);
                SaveFileInfoResB body = new SaveFileInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileInfoResB.setMsgCode(e.getMsgCode());
                saveFileInfoResB.setMsgDes(e.getMsgDes());
                saveFileInfoRes.setBody(saveFileInfoResB);
            }
        }
        //新增版本
        else if("04".equals(saveReq.getExecType())){
            SaveFileInfoReqBD04 busData04 = CommonUtils.switchClass(SaveFileInfoReqBD04.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveFileInfoRes = fileInfoService.AddinsertFileVerInfo(request,busData04,fileVerInfo);
                SaveFileInfoResB body = new SaveFileInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileInfoResB.setMsgCode(e.getMsgCode());
                saveFileInfoResB.setMsgDes(e.getMsgDes());
                saveFileInfoRes.setBody(saveFileInfoResB);
            }catch (IOException e) {
                saveFileInfoResB.setMsgCode("");
                saveFileInfoResB.setMsgDes("上传失败");
            }
        }
        //删除版本
        else if("05".equals(saveReq.getExecType())){
            SaveFileInfoReqBD05 busData05 = CommonUtils.switchClass(SaveFileInfoReqBD05.class,rowData);
            try {
                saveFileInfoRes =fileInfoService.RmdeleteFileVerInfo(busData05);
                SaveFileInfoResB body = new SaveFileInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileInfoResB.setMsgCode(e.getMsgCode());
                saveFileInfoResB.setMsgDes(e.getMsgDes());
                saveFileInfoRes.setBody(saveFileInfoResB);
            }
        }
        else{
            saveFileInfoResB.setMsgCode("MG0006F");
            saveFileInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03、04、05");
        }

        saveFileInfoRes.setStatus("00");
        return saveFileInfoRes;
    }

    /**
     * 获取文件列表信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllFileNew")
    @ResponseBody
    public BaseResponse GetAllFileNew(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(fileInfoService.QALLFileInfoNew(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 获取所有文件版本列表信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllFileVerListNew")
    @ResponseBody
    public BaseResponse GetAllFileVerListNew(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(fileInfoService.QALLFileVerListNew(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 获取文件版本列表信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllFileVerNew")
    @ResponseBody
    public BaseResponse GetAllFileVerNew(@RequestBody BaseRequest<GetFileVInfoReqBD00> request){
        try {
            return BaseResponse.success(fileInfoService.QALLFileVerInfoNew(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 新增文件信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/AddFileInfo")
    @ResponseBody
    public BaseResponse AddFileInfo(@RequestBody BaseRequest<SaveFileInfoReqBD00> request){
        try {
            fileInfoService.AddFileInfo(request);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }

    /**
     * 新增文件版本信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/AddFileVerInfo")
    @ResponseBody
    public BaseResponse AddFileVerInfo(@RequestBody BaseRequest<SaveFileInfoReqBD04> request){
        try {
            fileInfoService.AddFileVerInfo(request);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }

    /**
     * 更新文件信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/ModFileInfo")
    @ResponseBody
    public BaseResponse ModFileInfo(@RequestBody BaseRequest<SaveFileInfoReqBD02> request){
        try {
            fileInfoService.ModFileInfo(request);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }

    /**
     * 删除文件信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/RmFileInfo")
    @ResponseBody
    public BaseResponse RmFileInfo(@RequestBody BaseRequest<SaveFileInfoReqBD01> request){
        try {
            fileInfoService.RmFileInfo(request);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }

    /**
     * 删除文件版本信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/RmFileVerInfo")
    @ResponseBody
    public BaseResponse RmFileVerInfo(@RequestBody BaseRequest<SaveFileInfoReqBD05> request){
        try {
            fileInfoService.RmFileVerInfo(request);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }

        return BaseResponse.success(null);
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public BaseResponse upload(@RequestParam("file") MultipartFile file){
        try {
            String filename = minIoUtils.putObject(file);
            Map<String, String> map = new HashMap<>();
            map.put("fileName", filename);
            return BaseResponse.success(map);
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 文件下载
     * @param filename
     * @param response
     * @throws IOException
     */
    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        //获取下载流文件
        InputStream inputStream = minIoUtils.getObject(filename);

        byte buf[] = new byte[1024];
        int length = 0;
        response.reset();

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        OutputStream outputStream = response.getOutputStream();
        while ((length = inputStream.read(buf)) > 0){
            outputStream.write(buf, 0, length);
        }

        outputStream.close();
    }
}
