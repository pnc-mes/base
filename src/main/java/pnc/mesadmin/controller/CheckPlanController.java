package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoRes;
import pnc.mesadmin.dto.GetAllCheckPlanInfo.GetAllCheckPlanInfoResB;
import pnc.mesadmin.dto.GetCheckPlanInfo.GetCheckPlanInfoReq00;
import pnc.mesadmin.dto.GetCheckPlanInfo.GetCheckPlanInfoRes;
import pnc.mesadmin.dto.GetCheckPlanInfo.GetCheckPlanInfoResB;
import pnc.mesadmin.dto.SaveCheckPlanInfo.*;
import pnc.mesadmin.dto.SaveCyclePlanInfo.*;
import pnc.mesadmin.service.CheckPlanIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:57
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/CheckPlan")
public class CheckPlanController {
    @Resource
    private CheckPlanIService checkPlanIService;

    @RequestMapping("/CheckPlanPG")
    public String home(){
        return "process/check/checkinfo";
    }

    //列表
    @RequestMapping(value = "/GetAllCheckPlanInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllCheckPlanInfoRes GetAllCheckPlanInfo(GetAllReq getAllReq){
        GetAllCheckPlanInfoRes getAllCheckPlanInfoRes=new GetAllCheckPlanInfoRes();
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
                getAllCheckPlanInfoRes = checkPlanIService.QALLGetAllCheckPlanInfoRes(objEInitDataFields, pageInfo);
                getAllCheckPlanInfoRes.getBody().setMsgCode("0x00000");
                getAllCheckPlanInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCheckPlanInfoResB getAllCheckPlanInfoResB = new GetAllCheckPlanInfoResB();
                getAllCheckPlanInfoResB.setMsgCode(e.getMsgCode());
                getAllCheckPlanInfoResB.setMsgDes(e.getMsgDes());
                getAllCheckPlanInfoRes.setBody(getAllCheckPlanInfoResB);
            }
        } else {
            GetAllCheckPlanInfoResB getAllCheckPlanInfoResB = new GetAllCheckPlanInfoResB();
            getAllCheckPlanInfoResB.setMsgCode("MG0006F");
            getAllCheckPlanInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllCheckPlanInfoRes.setBody(getAllCheckPlanInfoResB);
        }
        getAllCheckPlanInfoRes.setStatus("00");
        return getAllCheckPlanInfoRes;
    }

    //获取点检计划列表（新）
    @RequestMapping(value="/GetAllCheckPlanNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllCheckPlanNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(checkPlanIService.QALLGetAllCheckPlanNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }



    //单个
    @RequestMapping(value = "/GetCheckPlanInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCheckPlanInfoRes getCheckPlanInfoRes(HttpServletRequest request, GetAllReq getAllReq){
        GetCheckPlanInfoRes checkPlanInfoRes=new GetCheckPlanInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetCheckPlanInfoReq00 getCheckPlanInfoReq00 = CommonUtils.switchClass(GetCheckPlanInfoReq00.class,busData);
            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    checkPlanInfoRes = checkPlanIService.GetGetCheckPlanInfoRes(getCheckPlanInfoReq00);
                    checkPlanInfoRes.getBody().setMsgCode("0x00000");
                    checkPlanInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetCheckPlanInfoResB getCheckPlanInfoResB = new GetCheckPlanInfoResB();
                    getCheckPlanInfoResB.setMsgCode(e.getMsgCode());
                    getCheckPlanInfoResB.setMsgDes(e.getMsgDes());
                    checkPlanInfoRes.setBody(getCheckPlanInfoResB);
                }
            }
        }else{
            GetCheckPlanInfoResB getCheckPlanInfoResB = new GetCheckPlanInfoResB();
            getCheckPlanInfoResB.setMsgCode("MG0006F");
            getCheckPlanInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            checkPlanInfoRes.setBody(getCheckPlanInfoResB);
        }

        checkPlanInfoRes.setStatus("00");

        return checkPlanInfoRes;
    }

    //保存
    @RequestMapping(value = "/SaveCheckPlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCheckPlanInfoRes SaveCheckPlanInfo(SaveReq saveReq){
        SaveCheckPlanInfoRes saveCheckPlanInfoRes=null;
        SaveCheckPlanInfoResB saveCheckPlanInfoResB=null;
        if ("00".equals(saveReq.getExecType())) {
            saveCheckPlanInfoRes=new SaveCheckPlanInfoRes();
            saveCheckPlanInfoResB=new SaveCheckPlanInfoResB();

            SaveCheckPlanInfoReq00 saveCheckPlanInfoReq00 = CommonUtils.switchClass(SaveCheckPlanInfoReq00.class, saveReq.getBusData());

            try {
                saveCheckPlanInfoRes = checkPlanIService.AddSaveCheckPlanInfoRes(saveCheckPlanInfoReq00);
                saveCheckPlanInfoResB.setMsgCode("0x00000");
                saveCheckPlanInfoResB.setMsgDes("成功");
                saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
            } catch (SystemException e) {
                saveCheckPlanInfoResB.setMsgCode(e.getMsgCode());
                saveCheckPlanInfoResB.setMsgDes(e.getMsgDes());
                saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            saveCheckPlanInfoRes=new SaveCheckPlanInfoRes();
            saveCheckPlanInfoResB=new SaveCheckPlanInfoResB();
            SaveCheckPlanInfoReq01 saveCheckPlanInfoReq01 = CommonUtils.switchClass(SaveCheckPlanInfoReq01.class, saveReq.getBusData());

            try {
                saveCheckPlanInfoRes = checkPlanIService.RmSaveCheckPlanInfoRes(saveCheckPlanInfoReq01);
                saveCheckPlanInfoResB.setMsgCode("0x00000");
                saveCheckPlanInfoResB.setMsgDes("成功");
                saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
            } catch (SystemException e) {
                saveCheckPlanInfoResB.setMsgCode(e.getMsgCode());
                saveCheckPlanInfoResB.setMsgDes(e.getMsgDes());
                saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
            }

        } else if ("02".equals(saveReq.getExecType())) {
            saveCheckPlanInfoRes=new SaveCheckPlanInfoRes();
            saveCheckPlanInfoResB=new SaveCheckPlanInfoResB();
            SaveCheckPlanInfoReq02 checkPlanInfoReq02 = CommonUtils.switchClass(SaveCheckPlanInfoReq02.class, saveReq.getBusData());

            try {
                saveCheckPlanInfoRes = checkPlanIService.ModSaveCheckPlanInfoRes(checkPlanInfoReq02);

                saveCheckPlanInfoResB.setMsgCode("0x00000");
                saveCheckPlanInfoResB.setMsgDes("成功");
                saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
            } catch (SystemException e) {

                saveCheckPlanInfoResB.setMsgCode(e.getMsgCode());
                saveCheckPlanInfoResB.setMsgDes(e.getMsgDes());
                saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
            }
        } else {
            saveCheckPlanInfoResB.setMsgCode("MG0006F");
            saveCheckPlanInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveCheckPlanInfoRes.setBody(saveCheckPlanInfoResB);
        }
        saveCheckPlanInfoRes.setStatus("00");

        return saveCheckPlanInfoRes;
    }

}
