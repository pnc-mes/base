package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoRes;
import pnc.mesadmin.dto.GetAllReaInfo.GetAllReaInfoResB;
import pnc.mesadmin.dto.GetReaInfo.GetReaInfoReqBD00;
import pnc.mesadmin.dto.GetReaInfo.GetReaInfoRes;
import pnc.mesadmin.dto.GetReaInfo.GetReaInfoResB;
import pnc.mesadmin.dto.GetReaTypeInfo.GetReaTypeInfoRes;
import pnc.mesadmin.dto.GetReaTypeInfo.GetReaTypeInfoResB;
import pnc.mesadmin.dto.SaveReaInfo.*;
import pnc.mesadmin.service.ReasonIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码Controller
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Reason")
public class ReasonController {
    @Resource
    private ReasonIService reasonIService;

    //获取原因代码页面
    @RequestMapping(value = "/ReasonPG")
    public String getFactoryPGPage(){
        return "base/reason/reasoninfo";
    }

    //获取设备变更原因页面
    @RequestMapping(value = "/DeStateCRSPG")
    public String destatecrsPG(){
        return "res/destatecrs/destatecrsinfo";
    }

    //获取保养原因页面
    @RequestMapping(value = "/DevMatcrsPG")
    public String DevMatcrsPG(){
        return "res/devmatcrs/devmatcrsinfo";
    }

    //获取冻结原因页面
    @RequestMapping(value = "/FreezersPG")
    public String FreezersPG(){
        return "qa/freezers/freezerinfo";
    }

    //获取解冻原因页面
    @RequestMapping(value = "/ThawrsPG")
    public String ThawrsPG(){
        return "qa/thaw/thawinfo";
    }

    //dto获取原因类别信息
    @RequestMapping(value = "/GetReaTypeInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetReaTypeInfoRes GetReaTypeInfo(GetAllReq getAllReq){
        GetReaTypeInfoRes objEGetReaTypeInfoRes=new GetReaTypeInfoRes();
        GetReaTypeInfoResB objEGetReaTypeInfoResB=new GetReaTypeInfoResB();
        if("00".equals(getAllReq.getExecType())){
            if (getAllReq.getPageInfo() != null){

            }
            else{
                try{
                    objEGetReaTypeInfoRes= reasonIService.QALLGetReaTypeInfoRes();
                    objEGetReaTypeInfoRes.getBody().setMsgCode("0x00000");
                    objEGetReaTypeInfoRes.getBody().setMsgDes("成功");
                 }catch (SystemException e){
                    objEGetReaTypeInfoResB.setMsgCode(e.getMsgCode());
                    objEGetReaTypeInfoResB.setMsgDes(e.getMsgDes());
                    objEGetReaTypeInfoRes.setBody(objEGetReaTypeInfoResB);
             }
         }
        }
        else{
            objEGetReaTypeInfoResB.setMsgCode("MG0006F");
            objEGetReaTypeInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetReaTypeInfoRes.setBody(objEGetReaTypeInfoResB);
        }
        objEGetReaTypeInfoRes.setStatus("00");
        return  objEGetReaTypeInfoRes;
    }

    //dto获取原因代码列表
    @RequestMapping(value = "/GetAllReaInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllReaInfoRes GetAllReaInfo(GetAllReq getAllReq){
        GetAllReaInfoRes objEGetAllReaInfoRes=new   GetAllReaInfoRes();
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

            try{
                    objEGetAllReaInfoRes = reasonIService.QALLGetAllReaInfoRes(objEInitDataFields, pageInfo);
                    objEGetAllReaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllReaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllReaInfoResB objEGetAllReaInfoResB = new GetAllReaInfoResB();
                    objEGetAllReaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllReaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllReaInfoRes.setBody(objEGetAllReaInfoResB);
                }
        }else
        {
            GetAllReaInfoResB objEGetAllReaInfoResB = new GetAllReaInfoResB();
            objEGetAllReaInfoResB.setMsgCode("MG0006F");
            objEGetAllReaInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllReaInfoRes.setBody(objEGetAllReaInfoResB);
        }
        objEGetAllReaInfoRes.setStatus("00");
        return objEGetAllReaInfoRes;
    }

    //dto查询原因代码信息
    @RequestMapping(value = "/GetRealInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetReaInfoRes GetReaInfo(GetAllReq getAllReq){
        GetReaInfoRes objEGetReaInfoRes=new GetReaInfoRes();
        if("00".equals(getAllReq.getExecType())){

            GetReaInfoReqBD00 objEGetReaInfoReqBD00 = CommonUtils.switchClass(GetReaInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }else{

                try{
                    objEGetReaInfoRes= reasonIService.GetGetReaInfoRes(objEGetReaInfoReqBD00);
                    objEGetReaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetReaInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetReaInfoResB objEGetReaInfoResB=new GetReaInfoResB();
                    objEGetReaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetReaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetReaInfoRes.setBody(objEGetReaInfoResB);
                }
            }
        }else{
            GetReaInfoResB objEGetReaInfoResB=new GetReaInfoResB();
            objEGetReaInfoResB.setMsgCode("MG0006F");
            objEGetReaInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetReaInfoRes.setBody(objEGetReaInfoResB);
        }
        objEGetReaInfoRes.setStatus("00");
        return objEGetReaInfoRes;
    }

    //dto保存原因代码
    @RequestMapping(value = "/SaveReaInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveReaInfoRes SaveReaInfo(SaveReq saveReq){
        SaveReaInfoRes objESaveReaInfoRes=new SaveReaInfoRes();
        SaveReaInfoResB objESaveReaInfoResB=null;

        if("00".equals(saveReq.getExecType())){
            SaveReaInfoReq00 objESaveReaInfoReq00= CommonUtils.switchClass(SaveReaInfoReq00.class,saveReq.getBusData());
            try{
                objESaveReaInfoRes= reasonIService.AddSaveReaInfoRes(objESaveReaInfoReq00);
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode("0x00000");
                objESaveReaInfoResB.setMsgDes("成功");
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }catch (SystemException e){
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode(e.getMsgCode());
                objESaveReaInfoResB.setMsgDes(e.getMsgDes());
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }
        }
        else if("01".equals(saveReq.getExecType())){
            SaveReaInfoReq01 objESaveReaInfoReq01=CommonUtils.switchClass(SaveReaInfoReq01.class,saveReq.getBusData());
            try{
                objESaveReaInfoRes= reasonIService.RmSaveReaInfoRes(objESaveReaInfoReq01);
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode("0x00000");
                objESaveReaInfoResB.setMsgDes("成功");
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }catch (SystemException e){
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode(e.getMsgCode());
                objESaveReaInfoResB.setMsgDes(e.getMsgDes());
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }
        }
        else if("02".equals(saveReq.getExecType())){
            SaveReaInfoReq02 objESaveReaInfoReq02=CommonUtils.switchClass(SaveReaInfoReq02.class,saveReq.getBusData());
            try{
                objESaveReaInfoRes= reasonIService.ModSaveReaInfoRes(objESaveReaInfoReq02);
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode("0x00000");
                objESaveReaInfoResB.setMsgDes("成功");
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }catch (SystemException e){
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode(e.getMsgCode());
                objESaveReaInfoResB.setMsgDes(e.getMsgDes());
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }
        }
        else if("03".equals(saveReq.getExecType())){
            SaveReaInfoReq03 objESaveReaInfoReq03=CommonUtils.switchClass(SaveReaInfoReq03.class,saveReq.getBusData());
            try{
                objESaveReaInfoRes= reasonIService.AddSaveReaInfoRes(objESaveReaInfoReq03);
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode("0x00000");
                objESaveReaInfoResB.setMsgDes("成功");
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }catch (SystemException e){
                objESaveReaInfoResB=new SaveReaInfoResB();
                objESaveReaInfoResB.setMsgCode(e.getMsgCode());
                objESaveReaInfoResB.setMsgDes(e.getMsgDes());
                objESaveReaInfoRes.setBody(objESaveReaInfoResB);
            }
        }
        else{
            objESaveReaInfoResB=new SaveReaInfoResB();
            objESaveReaInfoResB.setMsgCode("MG0006F");
            objESaveReaInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            objESaveReaInfoRes.setBody(objESaveReaInfoResB);
        }
        objESaveReaInfoRes.setStatus("00");
        return objESaveReaInfoRes;
    }

    /**
     * 获取原因代码列表(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllReaNew")
    @ResponseBody
    public BaseResponse GetAllReaNew(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(reasonIService.QALLReaInfoNew(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }
}
