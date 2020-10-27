package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCMethodInfo.GetAllCMethodInfoRes;
import pnc.mesadmin.dto.GetAllCMethodInfo.GetAllCMethodInfoResB;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoReqBD00;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoRes;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoResB;
import pnc.mesadmin.dto.SaveCMethodInfo.*;
import pnc.mesadmin.entity.CmethodInfo;
import pnc.mesadmin.service.CLeveIService;
import pnc.mesadmin.service.CheckMethodInfoService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
@Controller
@Scope("prototype")
@RequestMapping("/CMethod")
public class CmethodController {
    @Resource
     private CLeveIService cLeveIService;
    @Resource
    private CheckMethodInfoService checkMethodInfoService;

    //获取紧急代码页面
    @RequestMapping(value = "/jyffPG")
    public String getjyspPage(){

        return "qa/jyx/jyffinfo";
    }


    @RequestMapping(value = "/GetAllCMethodInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllCMethodInfoRes GetAllCMethodInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllCMethodInfoRes objGetAllCMethodInfoRes=new GetAllCMethodInfoRes();
        GetAllCMethodInfoResB objGetAllCMethodInfoResB=new GetAllCMethodInfoResB();
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
                objGetAllCMethodInfoRes = checkMethodInfoService.QALLselectAllCMethodInfo(objEInitDataFields, pageInfo);
                objGetAllCMethodInfoRes.getBody().setMsgCode("0x00000");
                objGetAllCMethodInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCMethodInfoResB GetAllCMethodInfoResB=new GetAllCMethodInfoResB();
                GetAllCMethodInfoResB.setMsgCode(e.getMsgCode());
                GetAllCMethodInfoResB.setMsgDes(e.getMsgDes());
                objGetAllCMethodInfoRes.setBody(GetAllCMethodInfoResB);
            }
        }else{
            GetAllCMethodInfoResB GetAllCMethodInfoResB=new GetAllCMethodInfoResB();
            GetAllCMethodInfoResB.setMsgCode("MG0006F");
            GetAllCMethodInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetAllCMethodInfoRes.setBody(GetAllCMethodInfoResB);
        }

        objGetAllCMethodInfoRes.setStatus("00");
        return objGetAllCMethodInfoRes;
    }

  //获取检验水平 信息
    @RequestMapping(value = "/GetCMethodInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCMethodInfoRes GetCMethodInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetCMethodInfoRes objGetCMethodInfoRes=new GetCMethodInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetCMethodInfoReqBD00 busData00 = CommonUtils.switchClass(GetCMethodInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objGetCMethodInfoRes = checkMethodInfoService.GetselectByRuid(busData00.getCMethodRd());
                    objGetCMethodInfoRes.getBody().setMsgCode("0x00000");
                    objGetCMethodInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetCMethodInfoResB objGetCMethodInfoResB = new GetCMethodInfoResB();
                    objGetCMethodInfoResB.setMsgCode(e.getMsgCode());
                    objGetCMethodInfoResB.setMsgDes(e.getMsgDes());
                    objGetCMethodInfoRes.setBody(objGetCMethodInfoResB);
                }
            }
        }else{
            GetCMethodInfoResB objGetCMethodInfoResB = new GetCMethodInfoResB();
            objGetCMethodInfoResB.setMsgCode("MG0006F");
            objGetCMethodInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetCMethodInfoRes.setBody(objGetCMethodInfoResB);
        }

        objGetCMethodInfoRes.setStatus("00");

        return objGetCMethodInfoRes;
    }

      //保存紧急代码信息
    @RequestMapping(value = "/SaveCMethodInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveCMethodInfoRes SaveCMethodInfo(HttpServletRequest request, SaveReq saveReq){

        SaveCMethodInfoRes saveCMethodInfoRes = new SaveCMethodInfoRes();

        SaveCMethodInfoResB saveCMethodInfoResB = new SaveCMethodInfoResB();

        CmethodInfo cmethodInfo = new CmethodInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveCMethodInfoReqBD00 busData00 = CommonUtils.switchClass(SaveCMethodInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveCMethodInfoRes = checkMethodInfoService.AddinsertCMethodInfo(busData00,cmethodInfo);
                SaveCMethodInfoResB body = new SaveCMethodInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCMethodInfoRes.setBody(body);
            }catch (SystemException e){
                saveCMethodInfoResB.setMsgCode(e.getMsgCode());
                saveCMethodInfoResB.setMsgDes(e.getMsgDes());
                saveCMethodInfoRes.setBody(saveCMethodInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveCMethodInfoReqBD01 busData01 = CommonUtils.switchClass(SaveCMethodInfoReqBD01.class,rowData);
            try {
                saveCMethodInfoRes = checkMethodInfoService.RmdeleteCMethodInfo(busData01.getCMethodRd());
                SaveCMethodInfoResB body = new SaveCMethodInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCMethodInfoRes.setBody(body);
            }catch (SystemException e){
                saveCMethodInfoResB.setMsgCode(e.getMsgCode());
                saveCMethodInfoResB.setMsgDes(e.getMsgDes());
                saveCMethodInfoRes.setBody(saveCMethodInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveCMethodInfoReqBD02 busData02 = CommonUtils.switchClass(SaveCMethodInfoReqBD02.class,rowData);
            try {
                saveCMethodInfoRes = checkMethodInfoService.ModupdateCMethodInfo(busData02,cmethodInfo);
                SaveCMethodInfoResB body = new SaveCMethodInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCMethodInfoRes.setBody(body);
            }catch (SystemException e){
                saveCMethodInfoResB.setMsgCode(e.getMsgCode());
                saveCMethodInfoResB.setMsgDes(e.getMsgDes());
                saveCMethodInfoRes.setBody(saveCMethodInfoResB);
            }
        }
        else{
            saveCMethodInfoResB.setMsgCode("MG0006F");
            saveCMethodInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveCMethodInfoRes.setStatus("00");
        return saveCMethodInfoRes;
    }
}
