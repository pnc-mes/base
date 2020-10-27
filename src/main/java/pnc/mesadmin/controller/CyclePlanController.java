package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoRes;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoResB;
import pnc.mesadmin.dto.GetAllDevInfo.GetAllDevInfoRes;
import pnc.mesadmin.dto.GetAllDevInfo.GetAllDevInfoResB;
import pnc.mesadmin.dto.GetCyclePlanInfo.GetCyclePlanInfoReq00;
import pnc.mesadmin.dto.GetCyclePlanInfo.GetCyclePlanInfoRes;
import pnc.mesadmin.dto.GetCyclePlanInfo.GetCyclePlanInfoResB;
import pnc.mesadmin.dto.SaveCyclePlanInfo.*;
import pnc.mesadmin.service.CyclePlanIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:11
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/CyclePlan")
public class CyclePlanController {

    @Resource
    private CyclePlanIService cyclePlanIService;

    @RequestMapping("/CyclePlanPG")
    public String home(){
        return "res/rdate/rdateinfo";
    }

    //列表
    @RequestMapping(value = "/GetAllCyclePlanInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllCyclePlanInfoRes GetAllDevInfo(GetAllReq getAllReq){
        GetAllCyclePlanInfoRes getAllCyclePlanInfoRes=new GetAllCyclePlanInfoRes();
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
                getAllCyclePlanInfoRes = cyclePlanIService.QALLGetAllCyclePlanInfoRes(objEInitDataFields, pageInfo);
                getAllCyclePlanInfoRes.getBody().setMsgCode("0x00000");
                getAllCyclePlanInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCyclePlanInfoResB getAllCyclePlanInfoResB = new GetAllCyclePlanInfoResB();
                getAllCyclePlanInfoResB.setMsgCode(e.getMsgCode());
                getAllCyclePlanInfoResB.setMsgDes(e.getMsgDes());
                getAllCyclePlanInfoRes.setBody(getAllCyclePlanInfoResB);
            }
        } else {
            GetAllCyclePlanInfoResB getAllCyclePlanInfoResB = new GetAllCyclePlanInfoResB();
            getAllCyclePlanInfoResB.setMsgCode("MG0006F");
            getAllCyclePlanInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllCyclePlanInfoRes.setBody(getAllCyclePlanInfoResB);
        }
        getAllCyclePlanInfoRes.setStatus("00");
        return getAllCyclePlanInfoRes;
    }

    //列表（新）
    @RequestMapping(value="/GetAllCyclePlanNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllCyclePlanNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(cyclePlanIService.QALLGetAllCyclePlanNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }



    //单个
    @RequestMapping(value = "/GetCyclePlanInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCyclePlanInfoRes GetCyclePlanInfoRes(HttpServletRequest request, GetAllReq getAllReq){
        GetCyclePlanInfoRes getCyclePlanInfoRes=new GetCyclePlanInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetCyclePlanInfoReq00 getCyclePlanInfoReq00 = CommonUtils.switchClass(GetCyclePlanInfoReq00.class,busData);
            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    getCyclePlanInfoRes = cyclePlanIService.GetGetCyclePlanInfoRes(getCyclePlanInfoReq00);
                    getCyclePlanInfoRes.getBody().setMsgCode("0x00000");
                    getCyclePlanInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetCyclePlanInfoResB getCyclePlanInfoResB = new GetCyclePlanInfoResB();
                    getCyclePlanInfoResB.setMsgCode(e.getMsgCode());
                    getCyclePlanInfoResB.setMsgDes(e.getMsgDes());
                    getCyclePlanInfoRes.setBody(getCyclePlanInfoResB);
                }
            }
        }else{
            GetCyclePlanInfoResB getCyclePlanInfoResB = new GetCyclePlanInfoResB();
            getCyclePlanInfoResB.setMsgCode("MG0006F");
            getCyclePlanInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            getCyclePlanInfoRes.setBody(getCyclePlanInfoResB);
        }

        getCyclePlanInfoRes.setStatus("00");

        return getCyclePlanInfoRes;
    }


    //保存
    @RequestMapping(value = "/SaveCyclePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCyclePlanInfoRes SaveCyclePlanInfoRes(SaveReq saveReq){
        SaveCyclePlanInfoRes saveCyclePlanInfoRes=null;
        SaveCyclePlanInfoResB saveCyclePlanInfoResB=null;
        if ("00".equals(saveReq.getExecType())) {
            saveCyclePlanInfoRes=new SaveCyclePlanInfoRes();
            saveCyclePlanInfoResB=new SaveCyclePlanInfoResB();

            SaveCyclePlanInfoReq00 saveCyclePlanInfoReq00 = CommonUtils.switchClass(SaveCyclePlanInfoReq00.class, saveReq.getBusData());

            try {
                saveCyclePlanInfoRes = cyclePlanIService.AddSaveCyclePlanInfoRes(saveCyclePlanInfoReq00);
                saveCyclePlanInfoResB.setMsgCode("0x00000");
                saveCyclePlanInfoResB.setMsgDes("成功");
                saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
            } catch (SystemException e) {
                saveCyclePlanInfoResB.setMsgCode(e.getMsgCode());
                saveCyclePlanInfoResB.setMsgDes(e.getMsgDes());
                saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            saveCyclePlanInfoRes=new SaveCyclePlanInfoRes();
            saveCyclePlanInfoResB=new SaveCyclePlanInfoResB();
            SaveCyclePlanInfoReq01 saveCyclePlanInfoReq01 = CommonUtils.switchClass(SaveCyclePlanInfoReq01.class, saveReq.getBusData());

            try {
                saveCyclePlanInfoRes = cyclePlanIService.RmSaveCyclePlanInfoRes(saveCyclePlanInfoReq01);
                saveCyclePlanInfoResB.setMsgCode("0x00000");
                saveCyclePlanInfoResB.setMsgDes("成功");
                saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
            } catch (SystemException e) {
                saveCyclePlanInfoResB.setMsgCode(e.getMsgCode());
                saveCyclePlanInfoResB.setMsgDes(e.getMsgDes());
                saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
            }

        } else if ("02".equals(saveReq.getExecType())) {
            saveCyclePlanInfoRes=new SaveCyclePlanInfoRes();
            saveCyclePlanInfoResB=new SaveCyclePlanInfoResB();
            SaveCyclePlanInfoReq02 saveCyclePlanInfoReq02 = CommonUtils.switchClass(SaveCyclePlanInfoReq02.class, saveReq.getBusData());

            try {
                saveCyclePlanInfoRes = cyclePlanIService.ModSaveCyclePlanInfoReq02(saveCyclePlanInfoReq02);

                saveCyclePlanInfoResB.setMsgCode("0x00000");
                saveCyclePlanInfoResB.setMsgDes("成功");
                saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
            } catch (SystemException e) {

                saveCyclePlanInfoResB.setMsgCode(e.getMsgCode());
                saveCyclePlanInfoResB.setMsgDes(e.getMsgDes());
                saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
            }
        } else {
            saveCyclePlanInfoResB.setMsgCode("MG0006F");
            saveCyclePlanInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveCyclePlanInfoRes.setBody(saveCyclePlanInfoResB);
        }
        saveCyclePlanInfoRes.setStatus("00");

        return saveCyclePlanInfoRes;
    }

}
