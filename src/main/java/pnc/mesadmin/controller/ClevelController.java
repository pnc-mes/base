package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCLevelInfo.GetAllCLevelInfoRes;
import pnc.mesadmin.dto.GetAllCLevelInfo.GetAllCLevelInfoResB;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoReqBD00;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoRes;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoResB;
import pnc.mesadmin.dto.SaveCLevelInfo.*;
import pnc.mesadmin.entity.ClevelInfo;
import pnc.mesadmin.service.CLeveIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
@Controller
@Scope("prototype")
@RequestMapping("/CLevel")
public class ClevelController {
    @Resource
     private CLeveIService cLeveIService;

    //获取紧急代码页面
    @RequestMapping(value = "/jyspPG")
    public String getjyspPage(){

        return "qa/jyx/jyspinfo";
    }


    @RequestMapping(value = "/GetAllCLevelInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllCLevelInfoRes GetAllCLevelInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllCLevelInfoRes objGetAllCLevelInfoRes=new GetAllCLevelInfoRes();

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
                objGetAllCLevelInfoRes = cLeveIService.QALLselectAllCLeveIInfo(objEInitDataFields, pageInfo);
                objGetAllCLevelInfoRes.getBody().setMsgCode("0x00000");
                objGetAllCLevelInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCLevelInfoResB objGetAllCLevelInfoResB = new GetAllCLevelInfoResB();
                objGetAllCLevelInfoResB.setMsgCode(e.getMsgCode());
                objGetAllCLevelInfoResB.setMsgDes(e.getMsgDes());
                objGetAllCLevelInfoRes.setBody(objGetAllCLevelInfoResB);
            }
        }else{
            GetAllCLevelInfoResB objGetAllCLevelInfoResB = new GetAllCLevelInfoResB();
            objGetAllCLevelInfoResB.setMsgCode("MG0006F");
            objGetAllCLevelInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetAllCLevelInfoRes.setBody(objGetAllCLevelInfoResB);
        }

        objGetAllCLevelInfoRes.setStatus("00");
        return objGetAllCLevelInfoRes;
    }

    //获取检验水平 信息
    @RequestMapping(value = "/GetCLevelInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCLevelInfoRes GetCLevelInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetCLevelInfoRes objGetCLevelInfoRes=new GetCLevelInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetCLevelInfoReqBD00 busData00 = CommonUtils.switchClass(GetCLevelInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objGetCLevelInfoRes = cLeveIService.GetselectByRuid(busData00.getCLevelRd());
                    objGetCLevelInfoRes.getBody().setMsgCode("0x00000");
                    objGetCLevelInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetCLevelInfoResB objGetCLevelInfoResB = new GetCLevelInfoResB();
                    objGetCLevelInfoResB.setMsgCode(e.getMsgCode());
                    objGetCLevelInfoResB.setMsgDes(e.getMsgDes());
                    objGetCLevelInfoRes.setBody(objGetCLevelInfoResB);
                }
            }
        }else{
            GetCLevelInfoResB objGetCLevelInfoResB= new GetCLevelInfoResB();
            objGetCLevelInfoResB.setMsgCode("MG0006F");
            objGetCLevelInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetCLevelInfoRes.setBody(objGetCLevelInfoResB);
        }

        objGetCLevelInfoRes.setStatus("00");

        return objGetCLevelInfoRes;
    }

    //保存紧急代码信息
    @RequestMapping(value = "/SaveCLevelInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveCLevelInfoRes SaveCLevelInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveCLevelInfoRes saveCLevelInfoRes = new SaveCLevelInfoRes();

        SaveCLevelInfoResB saveCLevelInfoResB = new SaveCLevelInfoResB();

        ClevelInfo cLevelInfo = new ClevelInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveCLevelInfoReqBD00 busData00 = CommonUtils.switchClass(SaveCLevelInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveCLevelInfoRes = cLeveIService.AddinsertCLevelInfo(busData00,cLevelInfo);
                SaveCLevelInfoResB body = new SaveCLevelInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCLevelInfoRes.setBody(body);
            }catch (SystemException e){
                saveCLevelInfoResB.setMsgCode(e.getMsgCode());
                saveCLevelInfoResB.setMsgDes(e.getMsgDes());
                saveCLevelInfoRes.setBody(saveCLevelInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveCLevelInfoReqBD01 busData01 = CommonUtils.switchClass(SaveCLevelInfoReqBD01.class,rowData);
            try {
                saveCLevelInfoRes = cLeveIService.RmdeleteCLevelInfo(busData01.getCLevelRd());
                SaveCLevelInfoResB body = new SaveCLevelInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCLevelInfoRes.setBody(body);
            }catch (SystemException e){
                saveCLevelInfoResB.setMsgCode(e.getMsgCode());
                saveCLevelInfoResB.setMsgDes(e.getMsgDes());
                saveCLevelInfoRes.setBody(saveCLevelInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveCLevelInfoReqBD02 busData02 = CommonUtils.switchClass(SaveCLevelInfoReqBD02.class,rowData);
            try {
                saveCLevelInfoRes = cLeveIService.ModupdateCLevelInfo(busData02,cLevelInfo);
                SaveCLevelInfoResB body = new SaveCLevelInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCLevelInfoRes.setBody(body);
            }catch (SystemException e){
                saveCLevelInfoResB.setMsgCode(e.getMsgCode());
                saveCLevelInfoResB.setMsgDes(e.getMsgDes());
                saveCLevelInfoRes.setBody(saveCLevelInfoResB);
            }
        }
        else{
            saveCLevelInfoResB.setMsgCode("MG0006F");
            saveCLevelInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveCLevelInfoRes.setStatus("00");
        return saveCLevelInfoRes;
    }
}
