package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoRes;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResB;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResD;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoReqBD00;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoRes;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResB;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResD;
import pnc.mesadmin.dto.SaveRCGpInfo.*;
import pnc.mesadmin.service.ReasongIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by test on 2017/8/11.
 */
@Controller
@Scope("prototype")
@RequestMapping("/ReasonG")
public class ReasonGController {

    @Resource
    private ReasongIService reasongIService;

    @RequestMapping(value = "/ReasonGPG")
    public String getReasonGPG(){
        return "base/reasonginfo/reasonginfo";
    }

    @PostMapping(value = "/GetAllRCGInfoNew")
    @ResponseBody
    public BaseResponse GetAllRCGInfoNew(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(reasongIService.QALLReaInfoNew(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    // 查询所有的原因代码组列表
    @ResponseBody
    @RequestMapping(value = "/GetAllRCGInfo",method = RequestMethod.POST)
    public GetAllRCGInfoRes GetAllRCGInfo(GetAllReq argGetAllReq){
        GetAllRCGInfoRes objEGetAllRCGInfoRes = new GetAllRCGInfoRes();
        GetAllRCGInfoResB objEGetAllRCGInfoResB = new GetAllRCGInfoResB();
        GetAllRCGInfoResD objEGetAllRCGInfoResD = new GetAllRCGInfoResD();
        if("00".equals(argGetAllReq.getExecType())) {
            List<InitDataField> objEInitDataFields = null;
            PageInfo pageInfo = null;

            if (argGetAllReq.getInitData() != null && !"".equals(argGetAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, argGetAllReq.getInitData());

                if (objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if (argGetAllReq.getPageInfo() != null && !"".equals(argGetAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, argGetAllReq.getPageInfo());
            }
            try {
                objEGetAllRCGInfoResB = reasongIService.GetAllRCGInfo(objEInitDataFields, pageInfo);
            } catch (SystemException e) {
                objEGetAllRCGInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllRCGInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        objEGetAllRCGInfoRes.setBody(objEGetAllRCGInfoResB);
        objEGetAllRCGInfoRes.setStatus("00");
        return objEGetAllRCGInfoRes;
    }

    // 获取原因代码 获取原因代码 组 信息(GetRCGInfo)
    @ResponseBody
    @RequestMapping(value = "/GetRCGInfo",method = RequestMethod.POST)
    public GetRCGInfoRes GetRCGInfo(GetAllReq argGetAllReq){
        GetRCGInfoRes objEGetRCGInfoRes = new GetRCGInfoRes();
        GetRCGInfoResB objEGetRCGInfoResB = new GetRCGInfoResB();
        GetRCGInfoResD objEGetRCGInfoResD = new GetRCGInfoResD();
        GetRCGInfoReqBD00 busData = CommonUtils.switchClass(GetRCGInfoReqBD00.class,argGetAllReq.getBusData());
        if("00".equals(argGetAllReq.getExecType())) {
            try {
                objEGetRCGInfoResB = reasongIService.GetRCGInfoByRuid(busData.getRCGRd());
            } catch (SystemException e) {
                objEGetRCGInfoResB.setMsgCode(e.getMsgCode());
                objEGetRCGInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        objEGetRCGInfoRes.setBody(objEGetRCGInfoResB);
        objEGetRCGInfoRes.setStatus("00");
        return objEGetRCGInfoRes;
    }

    // 保存
    @ResponseBody
    @RequestMapping(value = "/SaveRCGPInfo",method = RequestMethod.POST)
    public SaveRCGpInfoRes saveRCGpInfo(SaveReq argSaveReq){
        SaveRCGpInfoRes objESaveRCGpInfoRes = new SaveRCGpInfoRes();
        SaveRCGpInfoResB objESaveRCGpInfoResB = new SaveRCGpInfoResB();

        // 新增
        if("00".equals(argSaveReq.getExecType())){
            SaveRCGpInfoReq00 busData00 = CommonUtils.switchClass(SaveRCGpInfoReq00.class,argSaveReq.getBusData());
            try{
                // 把数据提交给业务层
                objESaveRCGpInfoResB = reasongIService.AddRCGpInfo(busData00);
            }catch(SystemException e){
                objESaveRCGpInfoResB.setMsgCode(e.getMsgCode());
                objESaveRCGpInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        // 编辑
        else if("02".equals(argSaveReq.getExecType())){
            SaveRCGpInfoReq02 busData02 = CommonUtils.switchClass(SaveRCGpInfoReq02.class,argSaveReq.getBusData());
            try{
                // 把数据提交给业务层
                objESaveRCGpInfoResB = reasongIService.ModRCGpInfo(busData02);
            }catch(SystemException e){
                objESaveRCGpInfoResB.setMsgCode(e.getMsgCode());
                objESaveRCGpInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        // 删除
        else if("01".equals(argSaveReq.getExecType())){
            SaveRCGpInfoReq01 busData01 = CommonUtils.switchClass(SaveRCGpInfoReq01.class,argSaveReq.getBusData());
            try{
                // 把数据提交给业务层
                objESaveRCGpInfoResB = reasongIService.RmRCGpInfo(busData01);
            }catch(SystemException e){
                objESaveRCGpInfoResB.setMsgCode(e.getMsgCode());
                objESaveRCGpInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveRCGpInfoRes.setBody(objESaveRCGpInfoResB);
        objESaveRCGpInfoRes.setStatus("00");
        return objESaveRCGpInfoRes;
    }
}
