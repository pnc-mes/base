package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoRes;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoResB;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoReqBD00;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoRes;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoResB;
import pnc.mesadmin.dto.SaveFileGPInfo.*;
import pnc.mesadmin.entity.FileGpInfo;
import pnc.mesadmin.service.FileGroupIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息Controller
 * 创建人：刘福志
 * 创建时间：2017-6-6
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/FileGroup")
public class FileGroupController {
    @Resource
    private FileGroupIService fileGroupIService;


    //加载文件组页面
    @RequestMapping("/FileGroupPG")
    public String FileGroupPG(){
        return "process/filegroup/filegroupinfo";
    }

    /**
     * @Description 分页查询列表
     * @Author jgy
     * @Date 2020/10/13
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetFileGroupList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetFileGroupList(SaveFileGPInfoReqBD02 request) {
        return fileGroupIService.GetFileGroupList(request);
    }

    //获取文件组列表信息
    @RequestMapping(value ="/GetAllFileGInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllFileGInfoRes GetAllFileGInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllFileGInfoRes objEGetAllFileGInfoRes=new GetAllFileGInfoRes();

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
                objEGetAllFileGInfoRes = fileGroupIService.QALLselectAllFileGpInfo(objEInitDataFields, pageInfo);
                objEGetAllFileGInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFileGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllFileGInfoResB objEGetAllFileGInfoResB = new GetAllFileGInfoResB();
                objEGetAllFileGInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFileGInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFileGInfoRes.setBody(objEGetAllFileGInfoResB);
            }
        }else{
            GetAllFileGInfoResB objEGetAllFileGInfoResB = new GetAllFileGInfoResB();
            objEGetAllFileGInfoResB.setMsgCode("MG0006F");
            objEGetAllFileGInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllFileGInfoRes.setBody(objEGetAllFileGInfoResB);
        }

        objEGetAllFileGInfoRes.setStatus("00");
        return objEGetAllFileGInfoRes;
    }

    /**
     * 获取文件组列表信息(新)
     * @param
     * @return
     */
    @RequestMapping(value="/GetAllFileGNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllFileGNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(fileGroupIService.QALLGetAllFileGNew(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取文件组信息
    @RequestMapping(value = "/GetFileGInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetFileGInfoRes GetFVInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetFileGInfoRes objEGetFileGInfoRes=new GetFileGInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetFileGInfoReqBD00 busData00 = CommonUtils.switchClass(GetFileGInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetFileGInfoRes = fileGroupIService.GetselectByruid(busData00.getFileGRd());
                    objEGetFileGInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFileGInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetFileGInfoResB objEGetFileGInfoResB = new GetFileGInfoResB();
                    objEGetFileGInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFileGInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFileGInfoRes.setBody(objEGetFileGInfoResB);
                }
            }
        }else{
            GetFileGInfoResB objEGetFileGInfoResB = new GetFileGInfoResB();
            objEGetFileGInfoResB.setMsgCode("MG0006F");
            objEGetFileGInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetFileGInfoRes.setBody(objEGetFileGInfoResB);
        }

        objEGetFileGInfoRes.setStatus("00");

        return objEGetFileGInfoRes;
    }


    //保存文件组信息
    @RequestMapping(value ="/SaveFileGpInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveFileGPInfoRes SaveFileGPInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveFileGPInfoRes saveFileGPInfoRes = new SaveFileGPInfoRes();

        SaveFileGPInfoResB saveFileGPInfoResB = new SaveFileGPInfoResB();

        FileGpInfo fileGpInfo = new FileGpInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();


        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveFileGPInfoReqBD00 busData00 = CommonUtils.switchClass(SaveFileGPInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveFileGPInfoRes = fileGroupIService.AddinsertFileGpInfo(busData00,fileGpInfo);
                SaveFileGPInfoResB body = new SaveFileGPInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileGPInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileGPInfoResB.setMsgCode(e.getMsgCode());
                saveFileGPInfoResB.setMsgDes(e.getMsgDes());
                saveFileGPInfoRes.setBody(saveFileGPInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveFileGPInfoReqBD01 busData01 =  CommonUtils.switchClass(SaveFileGPInfoReqBD01.class,rowData);
            try {
                saveFileGPInfoRes = fileGroupIService.RmdeleteFileGpInfo(busData01.getFileGRd());
                SaveFileGPInfoResB body = new SaveFileGPInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileGPInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileGPInfoResB.setMsgCode(e.getMsgCode());
                saveFileGPInfoResB.setMsgDes(e.getMsgDes());
                saveFileGPInfoRes.setBody(saveFileGPInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveFileGPInfoReqBD02 busData02 =  CommonUtils.switchClass(SaveFileGPInfoReqBD02.class,rowData);
            try {
                saveFileGPInfoRes = fileGroupIService.ModupdateFileGpInfo(busData02,fileGpInfo);
                SaveFileGPInfoResB body = new SaveFileGPInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveFileGPInfoRes.setBody(body);
            }catch (SystemException e){
                saveFileGPInfoResB.setMsgCode(e.getMsgCode());
                saveFileGPInfoResB.setMsgDes(e.getMsgDes());
                saveFileGPInfoRes.setBody(saveFileGPInfoResB);
            }
        }
        else{
            saveFileGPInfoResB.setMsgCode("MG0006F");
            saveFileGPInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveFileGPInfoRes.setStatus("00");
        return saveFileGPInfoRes;
    }
}
