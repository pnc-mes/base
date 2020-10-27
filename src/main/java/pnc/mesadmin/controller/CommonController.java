package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoReq00;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoRes;
import pnc.mesadmin.dto.GetCMBBInfo.GetCMBBInfoResB;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoReqBD00;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoReqBD01;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoRes;
import pnc.mesadmin.dto.GetCMGCInfo.GetCMGCInfoResB;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoReqBD00;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoRes;
import pnc.mesadmin.dto.GetCMSRInfo.GetCMSRInfoResB;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoReqBD00;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoReqBD01;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoRes;
import pnc.mesadmin.dto.GetCMWFInfo.GetCMWFInfoResB;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoReqBD00;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoRes;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.CommonIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：公共Controller
 * 创建人：赵超
 * 创建时间：2017-06-28
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Commom")
public class CommonController {

    @Resource
    private CommonIService commonService;

    @RequestMapping(value = "/a")
    public String a(){
        return "mprocess/setupm";
    }

    @RequestMapping(value = "/ChangeQtyPG")
    public String GetChangeQty(){
        return "currency/changeQty";
    }

    @RequestMapping(value = "/DisassociatePG")
    public String GetDisassociate(){
        return "mprocess/Disassociate";
    }

    @RequestMapping(value = "/Prodreport")
    public String GetDisassociat(){
        return "reports/prodreport";
    }

    //批次冻结
    @RequestMapping(value = "/FrozenBatchPG")
    public String GetFrozenBatch(){
        return "qa/holdinfo";
    }

    //批次解冻
    @RequestMapping(value = "/UnFrozenBatchPG")
    public String GetUnFrozenBatch(){
        return "qa/unholdinfo";
    }

    //标记不良
    @RequestMapping(value = "/RejectPG")
    public String Reject(){
        return "qa/rejectinfo";
    }

    @ResponseBody
    @RequestMapping(value = "/GetCMWFInfo",method = RequestMethod.POST)
    public GetCMWFInfoRes GetCMWFInfo(GetAllReq getAllReq){
        GetCMWFInfoRes objEGetCMWFInfoRes = new GetCMWFInfoRes();
        GetCMWFInfoResB objEGetCMWFInfoResB = new GetCMWFInfoResB();

        // 根据物料ID查询工艺
        if("00".equals(getAllReq.getExecType())){
            try {
                GetCMWFInfoReqBD00 objEGetCMWFInfoReqBD00 = CommonUtils.switchClass(GetCMWFInfoReqBD00.class, getAllReq.getBusData());
                // 传递ruid到service查询信息之后并返回
                objEGetCMWFInfoRes = commonService.getCMWFInfo(objEGetCMWFInfoReqBD00);
            }catch (SystemException e){
                objEGetCMWFInfoResB.setData(null);
                objEGetCMWFInfoResB.setMsgCode(e.getMsgCode());
                objEGetCMWFInfoResB.setMsgDes(e.getMsgDes());
                objEGetCMWFInfoRes.setBody(objEGetCMWFInfoResB);
            }
        }

        // 根据工单查询工艺
        else if("01".equals(getAllReq.getExecType())){
            try {
                GetCMWFInfoReqBD01 objEGetCMWFInfoReqBD01 = CommonUtils.switchClass(GetCMWFInfoReqBD01.class, getAllReq.getBusData());
                // 传递WoCode到service查询信息之后并返回
                objEGetCMWFInfoRes = commonService.getCMWFInfoByWoCode(objEGetCMWFInfoReqBD01);
            }catch (SystemException e){
                objEGetCMWFInfoResB.setData(null);
                objEGetCMWFInfoResB.setMsgCode(e.getMsgCode());
                objEGetCMWFInfoResB.setMsgDes(e.getMsgDes());
                objEGetCMWFInfoRes.setBody(objEGetCMWFInfoResB);
            }
        }
        objEGetCMWFInfoRes.setStatus("00");
        return objEGetCMWFInfoRes;
    }

    //dto查询批次信息
    @ResponseBody
    @RequestMapping(value = "/GetCMBInfo",method = RequestMethod.POST)
    public GetCMBBInfoRes GetCMBInfo(GetAllReq getAllReq){
        GetCMBBInfoRes objEGetCMBBInfoRes=new GetCMBBInfoRes();
        if("00".equals(getAllReq.getExecType())){
            GetCMBBInfoReq00 objEGetCMBBInfoReq00=CommonUtils.switchClass(GetCMBBInfoReq00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){

            }
            else{
                try{
                    objEGetCMBBInfoRes= commonService.GetGetCMBBInfoRes(objEGetCMBBInfoReq00);
                    objEGetCMBBInfoRes.getBody().setMsgCode("0x00000");
                    objEGetCMBBInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetCMBBInfoResB objEGetCMBBInfoResB = new GetCMBBInfoResB();
                    objEGetCMBBInfoResB.setMsgCode(e.getMsgCode());
                    objEGetCMBBInfoResB.setMsgDes(e.getMsgDes());
                    objEGetCMBBInfoRes.setBody(objEGetCMBBInfoResB);
                }
            }
        }else {
            GetCMBBInfoResB objEGetCMBBInfoResB = new GetCMBBInfoResB();
            objEGetCMBBInfoResB.setMsgCode("MG0006F");
            objEGetCMBBInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetCMBBInfoRes.setBody(objEGetCMBBInfoResB);
        }
        objEGetCMBBInfoRes.setStatus("00");
        return objEGetCMBBInfoRes;
    }

    //获取序号信息
    @RequestMapping(value = "/GetCMSRInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetCMSRInfoRes GetCMSRInfo(GetAllReq getAllReq){
        GetCMSRInfoRes objEGetCMSRInfoRes = new GetCMSRInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetCMSRInfoReqBD00 objEGetCMSRInfoReqBD00 = CommonUtils.switchClass(GetCMSRInfoReqBD00.class, busData);

            try {

                objEGetCMSRInfoRes = commonService.GetCMSRInfoByMV(objEGetCMSRInfoReqBD00);

                objEGetCMSRInfoRes.getBody().setMsgCode("0x00000");
                objEGetCMSRInfoRes.getBody().setMsgDes("成功");

            }catch (SystemException e){
                GetCMSRInfoResB objEGetCMSRInfoResB = new GetCMSRInfoResB();
                objEGetCMSRInfoResB.setMsgCode(e.getMsgCode());
                objEGetCMSRInfoResB.setMsgDes(e.getMsgDes());
                objEGetCMSRInfoRes.setBody(objEGetCMSRInfoResB);
            }
        }

        objEGetCMSRInfoRes.setStatus("00");

        return objEGetCMSRInfoRes;
    }

    //查询全局配置信息
    @ResponseBody
    @RequestMapping(value = "/GetCMGCInfo",method = RequestMethod.POST)
    public GetCMGCInfoRes GetCMGCInfo(GetAllReq getAllReq){
        GetCMGCInfoRes objEGetCMGCInfoRes = new GetCMGCInfoRes();
        GetCMGCInfoResB objEGetCMGCInfoResB = new GetCMGCInfoResB();

        // 根据模式名称查询
        if("00".equals(getAllReq.getExecType())){
            try {
                GetCMGCInfoReqBD00 objEGetCMGCInfoReqBD00 = CommonUtils.switchClass(GetCMGCInfoReqBD00.class, getAllReq.getBusData());
                // 传递ruid到service查询信息之后并返回
                objEGetCMGCInfoRes = commonService.GetModeName(objEGetCMGCInfoReqBD00);

                objEGetCMGCInfoRes.getBody().setMsgCode("0x00000");
                objEGetCMGCInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objEGetCMGCInfoResB.setData(null);
                objEGetCMGCInfoResB.setMsgCode(e.getMsgCode());
                objEGetCMGCInfoResB.setMsgDes(e.getMsgDes());
                objEGetCMGCInfoRes.setBody(objEGetCMGCInfoResB);
            }
        }

        // 根据消息名称查询
        else if("01".equals(getAllReq.getExecType())){
            try {
                GetCMGCInfoReqBD01 objEGetCMGCInfoReqBD01 = CommonUtils.switchClass(GetCMGCInfoReqBD01.class, getAllReq.getBusData());
                // 传递WoCode到service查询信息之后并返回
                objEGetCMGCInfoRes = commonService.GetMsgName(objEGetCMGCInfoReqBD01);

                objEGetCMGCInfoRes.getBody().setMsgCode("0x00000");
                objEGetCMGCInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                objEGetCMGCInfoResB.setData(null);
                objEGetCMGCInfoResB.setMsgCode(e.getMsgCode());
                objEGetCMGCInfoResB.setMsgDes(e.getMsgDes());
                objEGetCMGCInfoRes.setBody(objEGetCMGCInfoResB);
            }
        }
        objEGetCMGCInfoRes.setStatus("00");
        return objEGetCMGCInfoRes;
    }

    //获取工单关联信息
    @RequestMapping(value = "/GetCMWInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCMWInfoRes GetCMW(GetAllReq getAllReq){
        GetCMWInfoRes objEGetCMWInfoRes = new GetCMWInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())) {
            try {
                GetCMWInfoReqBD00 objEGetCMWInfoReqBD00 = CommonUtils.switchClass(GetCMWInfoReqBD00.class, busData);

                objEGetCMWInfoRes = commonService.GetCMW(objEGetCMWInfoReqBD00);
                objEGetCMWInfoRes.getBody().setMsgCode("0x00000");
                objEGetCMWInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetCMWInfoResB objEGetCMWInfoResB = new GetCMWInfoResB();
                objEGetCMWInfoResB.setMsgCode(e.getMsgCode());
                objEGetCMWInfoResB.setMsgDes(e.getMsgDes());
                objEGetCMWInfoRes.setBody(objEGetCMWInfoResB);
            }
        }

        objEGetCMWInfoRes.setStatus("00");

        return objEGetCMWInfoRes;
    }

    /**
     * 查询批次信息(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/GetCMBNew")
    @ResponseBody
    public BaseResponse GetBatchInfos(@RequestBody BaseRequest<GetCMBBInfoReq00> request){
        try {
            return BaseResponse.success(commonService.GetBatchInfos(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 查询冻结批次记录
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllHold")
    @ResponseBody
    public BaseResponse GetAllHoldInfo(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(commonService.GetAllHoldInfo(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 查询解冻批次记录
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllUnHold")
    @ResponseBody
    public BaseResponse GetAllUnHoldInfo(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(commonService.GetAllUnHoldInfo(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }
}
