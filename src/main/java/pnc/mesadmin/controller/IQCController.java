package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pnc.mesadmin.dao.IQCCentBInfoDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllIQCBInfo.GetAllIQCBInfoRes;
import pnc.mesadmin.dto.GetAllIQCBInfo.GetAllIQCBInfoResB;
import pnc.mesadmin.dto.GetIQCBInfo.GetIQCBInfoRes;
import pnc.mesadmin.dto.GetIQCBInfo.GetIQCBInfoResB;
import pnc.mesadmin.dto.SaveIQCBInfo.SaveIQCBInfoRes;
import pnc.mesadmin.dto.SaveIQCBInfo.SaveIQCBInfoResB;
import pnc.mesadmin.entity.IQCCentBInfo;
import pnc.mesadmin.service.IQCIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：质检批次Controller
 * 创建人：王怀龙
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/IQC")
public class IQCController {
    @Resource
    private IQCIService iqciService;

    @Resource
    private IQCCentBInfoDAO iqcCentBInfoDAO;
    //加载页面
    @RequestMapping("/IQCPG")
    public String IQCPG(){
        return "qa/iqc/iqcinfo";
    }
    @RequestMapping("/iqcAdd")
    public String iqcAdd(){
        return "qa/iqc/iqcAdd";
    }
    @RequestMapping("/iqcFileAdd")
    public String iqcFileAdd(){
        return "qa/iqc/iqcFileAdd";
    }

    @RequestMapping(value = "/addIqcFileAdd",method = RequestMethod.POST)
    @ResponseBody
    public String addIqcFileAdd(HttpServletRequest request){
        String path=null;
        try {
            String fileName = "";

            //读取配置文件
            InputStream in = IQCController.class.getClassLoader().getResourceAsStream("config.properties");

            Properties props = new Properties();
            props.load(in);

            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());

            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile files = multiRequest.getFile(iter.next().toString());
                    if (files != null) {

                        fileName = files.getOriginalFilename();

                        fileName = CommonUtils.getRandomNumber() + fileName.substring(fileName.lastIndexOf("."));

                        path = request.getSession().getServletContext().getRealPath("/") + props.get("upload_url") + fileName;
                        //  String path = "E:\\headimg\\" + fileName;
                        System.out.println("fileName:" + path);
                        File file1 = new File(path);
                        IQCCentBInfo data=new IQCCentBInfo();
                       data.setGuid(CommonUtils.getRandomNumber());

                        iqcCentBInfoDAO.InsertIQCCentBInfo(data);
                        if (!file1.exists()) {
                            file1.mkdirs();
                        }

                        files.transferTo(file1);
                    }
                }
            }
        }catch(Exception e){
            return "01";
        }


        return "00";
    }

    @RequestMapping(value = "/GetAllIQCBInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllIQCBInfoRes GetAllIQCBInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllIQCBInfoRes objEGetAllIQCBInfoRes = null;
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
                objEGetAllIQCBInfoRes = iqciService.QAllIQCBInfo(objEInitDataFields, pageInfo);
            } catch (SystemException objSystemException) {
                objEGetAllIQCBInfoRes = new GetAllIQCBInfoRes();
                GetAllIQCBInfoResB objGetAllIQCBInfoResB = new GetAllIQCBInfoResB();
                objGetAllIQCBInfoResB.setMsgCode(objSystemException.getMsgCode());
                objGetAllIQCBInfoResB.setMsgDes(objSystemException.getMsgDes());
                objEGetAllIQCBInfoRes.setBody(objGetAllIQCBInfoResB);
            }
        }else{
            objEGetAllIQCBInfoRes = new GetAllIQCBInfoRes();
            GetAllIQCBInfoResB objGetAllIQCBInfoResB = new GetAllIQCBInfoResB();
            objGetAllIQCBInfoResB.setMsgCode("MG0006F");
            objGetAllIQCBInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllIQCBInfoRes.setBody(objGetAllIQCBInfoResB);
        }
        objEGetAllIQCBInfoRes.setStatus("00");
        return objEGetAllIQCBInfoRes;
    }

    @RequestMapping(value = "/GetIQCBInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetIQCBInfoRes GetIQCBInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetIQCBInfoRes objEGetIQCBInfoRes = null;
        try {
            objEGetIQCBInfoRes = iqciService.QIQCBInfo(request, getAllReq);
        }catch(SystemException objSystemException){
            objEGetIQCBInfoRes = new GetIQCBInfoRes();
            GetIQCBInfoResB objGetIQCBInfoResB = new GetIQCBInfoResB();
            objGetIQCBInfoResB.setMsgCode(objSystemException.getMsgCode());
            objGetIQCBInfoResB.setMsgDes(objSystemException.getMsgDes());

            objEGetIQCBInfoRes.setStatus("00");
            objEGetIQCBInfoRes.setBody(objGetIQCBInfoResB);
        }
        objEGetIQCBInfoRes.setStatus("00");
        return objEGetIQCBInfoRes;
    }

    @RequestMapping(value = "/SaveIQCBInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveIQCBInfoRes SaveIQCBInfo(HttpServletRequest request, SaveReq saveReq){
        SaveIQCBInfoRes objESaveIQCBInfoRes = null;
        try {

            objESaveIQCBInfoRes = iqciService.SaveIQCBInfo(request, saveReq);
        }catch(SystemException objSystemException){
            objESaveIQCBInfoRes = new SaveIQCBInfoRes();
            SaveIQCBInfoResB objSaveIQCBInfoResB = new SaveIQCBInfoResB();
            objSaveIQCBInfoResB.setMsgCode(objSystemException.getMsgCode());
            objSaveIQCBInfoResB.setMsgDes(objSystemException.getMsgDes());

            objESaveIQCBInfoRes.setStatus("01");
            objESaveIQCBInfoRes.setBody(objSaveIQCBInfoResB);
        }

        return objESaveIQCBInfoRes;
    }


}
