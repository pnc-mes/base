package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoRes;
import pnc.mesadmin.dto.GetAllWCInfo.GetAllWCInfoResB;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoReqBD00;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoRes;
import pnc.mesadmin.dto.GetWCInfo.GetWCInfoResB;
import pnc.mesadmin.dto.SaveWCInfo.*;
import pnc.mesadmin.service.WorkCIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工作中心Controller
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/WorkC")
public class WorkCController {

    @Resource
    private WorkCIService workCIService;

    //获取工厂页面
    @RequestMapping(value = "/WorkCPG")
    public String getWorkCPGPage(){

        return "base/wcenter/wcenter";
    }

    //dto查询工作中心列表
    @RequestMapping(value = "/GetAllWCInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllWCInfoRes GetAllWCInfo(GetAllReq getAllReq){
        GetAllWCInfoRes objEGetAllWCInfoRes=new GetAllWCInfoRes();
        if("00".equals(getAllReq.getExecType())){
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
                try{
                    objEGetAllWCInfoRes= workCIService.QALLGetAllWCInfoRes(objEInitDataFields, pageInfo);
                    objEGetAllWCInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllWCInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetAllWCInfoResB objEGetAllWCInfoResB=new GetAllWCInfoResB();
                    objEGetAllWCInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllWCInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllWCInfoRes.setBody(objEGetAllWCInfoResB);
            }
        }else {
            GetAllWCInfoResB objEGetAllWCInfoResB=new GetAllWCInfoResB();
            objEGetAllWCInfoResB.setMsgCode("MG0006F");
            objEGetAllWCInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllWCInfoRes.setBody(objEGetAllWCInfoResB);
        }
        objEGetAllWCInfoRes.setStatus("00");
        return objEGetAllWCInfoRes;
    }

    /**
     * dto查询工作中心列表(新)
     * @param req
     * @return
     */
    @RequestMapping(value = "/GetAllWCNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllWCNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(workCIService.QALLGetAllWCInfoRes(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto查询工作中心
    @RequestMapping(value = "/GetWCInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetWCInfoRes GetWCInfo(GetAllReq getAllReq){
        GetWCInfoRes objEGetWCInfoRes=new GetWCInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetWCInfoReqBD00 objEGetWCInfoReqBD00 = CommonUtils.switchClass(GetWCInfoReqBD00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){


            }else{
                try{
                    objEGetWCInfoRes= workCIService.GetGetWCInfoRes(objEGetWCInfoReqBD00);
                    objEGetWCInfoRes.getBody().setMsgCode("0x00000");
                    objEGetWCInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetWCInfoResB objEGetWCInfoResB=new GetWCInfoResB();
                    objEGetWCInfoResB.setMsgCode(e.getMsgCode());
                    objEGetWCInfoResB.setMsgDes(e.getMsgDes());
                    objEGetWCInfoRes.setBody(objEGetWCInfoResB);
                }
            }


        }else {
            GetWCInfoResB objEGetWCInfoResB=new GetWCInfoResB();
            objEGetWCInfoResB.setMsgCode("MG0006F");
            objEGetWCInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetWCInfoRes.setBody(objEGetWCInfoResB);
        }
        objEGetWCInfoRes.setStatus("00");

        return objEGetWCInfoRes;
    }

    //dto新增工作中心
    @RequestMapping(value = "/SaveWCInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveWCInfoRes SaveWCInfo(SaveReq saveReq){
        SaveWCInfoRes objESaveWCInfoRes=new SaveWCInfoRes();
        SaveWCInfoResB objESaveWCInfoResB=new SaveWCInfoResB();

        if("00".equals(saveReq.getExecType())){
            SaveWCInfoReqBD00 objESaveCpInfoRes00=CommonUtils.switchClass(SaveWCInfoReqBD00.class,saveReq.getBusData());

            try{
                objESaveWCInfoRes= workCIService.AddSaveWCInfoRes(objESaveCpInfoRes00);
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode("0x00000");
                objESaveWCInfoResB.setMsgDes("成功");
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }catch (SystemException e){
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode(e.getMsgCode());
                objESaveWCInfoResB.setMsgDes(e.getMsgDes());
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }
        }
        else if("01".equals(saveReq.getExecType())){
            SaveWCInfoReqBD01 objESaveWCInfoReqBD01 =CommonUtils.switchClass(SaveWCInfoReqBD01.class,saveReq.getBusData());
            try{
                objESaveWCInfoRes= workCIService.RmSaveWCInfoRes(objESaveWCInfoReqBD01);
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode("0x00000");
                objESaveWCInfoResB.setMsgDes("成功");
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }catch (SystemException e){
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode(e.getMsgCode());
                objESaveWCInfoResB.setMsgDes(e.getMsgDes());
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }

        }
        else if("02".equals(saveReq.getExecType())){
            SaveWCInfoReqBD02 objESaveWCInfoReqBD02 =CommonUtils.switchClass(SaveWCInfoReqBD02.class,saveReq.getBusData());

            try{
                objESaveWCInfoRes= workCIService.ModSaveWCInfoRes(objESaveWCInfoReqBD02);
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode("0x00000");
                objESaveWCInfoResB.setMsgDes("成功");
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }catch (SystemException e){
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode(e.getMsgCode());
                objESaveWCInfoResB.setMsgDes(e.getMsgDes());
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }

        }
        else if("03".equals(saveReq.getExecType())){
            SaveWCInfoReqBD03 objESaveWCInfoReqBD03 =CommonUtils.switchClass(SaveWCInfoReqBD03.class,saveReq.getBusData());

            try{
                objESaveWCInfoRes= workCIService.AddSaveWCInfoRes(objESaveWCInfoReqBD03);
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode("0x00000");
                objESaveWCInfoResB.setMsgDes("成功");
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }catch (SystemException e){
                objESaveWCInfoResB=new SaveWCInfoResB();
                objESaveWCInfoResB.setMsgCode(e.getMsgCode());
                objESaveWCInfoResB.setMsgDes(e.getMsgDes());
                objESaveWCInfoRes.setBody(objESaveWCInfoResB);
            }

        }
        else {
            objESaveWCInfoResB.setMsgCode("MG0006F");
            objESaveWCInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
            objESaveWCInfoRes.setBody(objESaveWCInfoResB);
        }
        objESaveWCInfoRes.setStatus("00");
        return objESaveWCInfoRes;
    }


}
