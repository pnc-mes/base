package pnc.mesadmin.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoRes;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoResB;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoReqBD00;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoRes;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoResB;
import pnc.mesadmin.dto.SaveUrcyInfo.*;
import pnc.mesadmin.entity.UrgencyInfo;
import pnc.mesadmin.service.UrgencyIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息Controller
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Urgency")
public class UrgencyController {
    @Resource
    private UrgencyIService urgencyIService;

    //获取紧急代码页面
    @RequestMapping(value = "/UrgencyPG")
    public String getUrgencyPage(){

        return "base/urgency/urgencyinfo";
    }

    //获取紧急代码列表信息
    @RequestMapping(value = "/GetAllUrcyInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllUrcyInfoRes GetAllUrcyInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllUrcyInfoRes objEGetAllUrcyInfoRes=new GetAllUrcyInfoRes();

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
                objEGetAllUrcyInfoRes = urgencyIService.QALLselectAllUrgencyInfo(objEInitDataFields, pageInfo);
                objEGetAllUrcyInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllUrcyInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllUrcyInfoResB objEGetAllUrcyInfoResB = new GetAllUrcyInfoResB();
                objEGetAllUrcyInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllUrcyInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllUrcyInfoRes.setBody(objEGetAllUrcyInfoResB);
            }
        }else{
            GetAllUrcyInfoResB objEGetAllUrcyInfoResB = new GetAllUrcyInfoResB();
            objEGetAllUrcyInfoResB.setMsgCode("MG0006F");
            objEGetAllUrcyInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllUrcyInfoRes.setBody(objEGetAllUrcyInfoResB);
        }

        objEGetAllUrcyInfoRes.setStatus("00");
        return objEGetAllUrcyInfoRes;
    }

    //获取紧急代码列表信息
    @RequestMapping(value = "/GetAllUrcyInfoNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllUrcyInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(urgencyIService.QALLselectAllUrgencyInfoNew(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取紧急代码信息
    @RequestMapping(value = "/GetUrcyInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetUrcyInfoRes GetUrcyInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetUrcyInfoRes objEGetUrcyInfoRes=new GetUrcyInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetUrcyInfoReqBD00 busData00 = CommonUtils.switchClass(GetUrcyInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetUrcyInfoRes = urgencyIService.GetselectByRuid(busData00.getUrcyRd());
                    objEGetUrcyInfoRes.getBody().setMsgCode("0x00000");
                    objEGetUrcyInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetUrcyInfoResB objEGetUrcyInfoResB = new GetUrcyInfoResB();
                    objEGetUrcyInfoResB.setMsgCode(e.getMsgCode());
                    objEGetUrcyInfoResB.setMsgDes(e.getMsgDes());
                    objEGetUrcyInfoRes.setBody(objEGetUrcyInfoResB);
                }
            }
        }else{
            GetUrcyInfoResB objEGetUrcyInfoResB= new GetUrcyInfoResB();
            objEGetUrcyInfoResB.setMsgCode("MG0006F");
            objEGetUrcyInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetUrcyInfoRes.setBody(objEGetUrcyInfoResB);
        }

        objEGetUrcyInfoRes.setStatus("00");

        return objEGetUrcyInfoRes;
    }

    //保存紧急代码信息
    @RequestMapping(value = "/SaveUrcyInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveUrcyInfoRes SaveUrcyInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveUrcyInfoRes saveUrcyInfoRes = new SaveUrcyInfoRes();

        SaveUrcyInfoResB saveUrcyInfoResB = new SaveUrcyInfoResB();

        UrgencyInfo urgencyInfo = new UrgencyInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveUrcyInfoReqBD00 busData00 = CommonUtils.switchClass(SaveUrcyInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveUrcyInfoRes = urgencyIService.AddinsertUrgencyInfo(busData00,urgencyInfo);
                SaveUrcyInfoResB body = new SaveUrcyInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveUrcyInfoRes.setBody(body);
            }catch (SystemException e){
                saveUrcyInfoResB.setMsgCode(e.getMsgCode());
                saveUrcyInfoResB.setMsgDes(e.getMsgDes());
                saveUrcyInfoRes.setBody(saveUrcyInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveUrcyInfoReqBD01 busData01 = CommonUtils.switchClass(SaveUrcyInfoReqBD01.class,rowData);
            try {
                saveUrcyInfoRes = urgencyIService.RmdeleteUrgencyInfo(busData01.getUrcyRd());
                SaveUrcyInfoResB body = new SaveUrcyInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveUrcyInfoRes.setBody(body);
            }catch (SystemException e){
                saveUrcyInfoResB.setMsgCode(e.getMsgCode());
                saveUrcyInfoResB.setMsgDes(e.getMsgDes());
                saveUrcyInfoRes.setBody(saveUrcyInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveUrcyInfoReqBD02 busData02 = CommonUtils.switchClass(SaveUrcyInfoReqBD02.class,rowData);
            try {
                saveUrcyInfoRes = urgencyIService.ModupdateUrgencyInfo(busData02,urgencyInfo);
                SaveUrcyInfoResB body = new SaveUrcyInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveUrcyInfoRes.setBody(body);
            }catch (SystemException e){
                saveUrcyInfoResB.setMsgCode(e.getMsgCode());
                saveUrcyInfoResB.setMsgDes(e.getMsgDes());
                saveUrcyInfoRes.setBody(saveUrcyInfoResB);
            }
        }
        else{
            saveUrcyInfoResB.setMsgCode("MG0006F");
            saveUrcyInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveUrcyInfoRes.setStatus("00");
        return saveUrcyInfoRes;
    }
}
