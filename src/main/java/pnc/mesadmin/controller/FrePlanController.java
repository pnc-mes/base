package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoRes;
import pnc.mesadmin.dto.GetAllFrePlanInfo.GetAllFrePlanInfoResB;
import pnc.mesadmin.dto.GetFrePlanInfo.GetFrePlanInfoReq00;
import pnc.mesadmin.dto.GetFrePlanInfo.GetFrePlanInfoRes;
import pnc.mesadmin.dto.GetFrePlanInfo.GetFrePlanInfoResB;
import pnc.mesadmin.dto.SaveFrePlanInfo.*;
import pnc.mesadmin.service.FrePlanIService;
import pnc.mesadmin.service.MaPerionIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by LENOVO on 2018/9/7.
 * 次数保养计划
 */
@Controller
@RequestMapping("/FrePlan")
@Scope
public class FrePlanController {

    @Resource
    private FrePlanIService frePlanIService;

    @Resource
    private MaPerionIService maPerionIService;

    //返回次数保养页面

    @RequestMapping(value = "/FrePlanPG")
    public String getFrePlanPG(){

        return "res/cdate/cdateinfo";
    }

    //获取次数保养计划列表信息
    @RequestMapping(value = "GetAllFrePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllFrePlanInfoRes GetAllMaPerionInfo(GetAllReq getAllReq) {
        GetAllFrePlanInfoRes getAllFrePlanInfoRes=new GetAllFrePlanInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            List<InitDataField> initDataFields = null;
            PageInfo pageInfo = null;

            if (getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if (objEInitData != null) {
                    initDataFields = objEInitData.getFiledList();
                }
            }

            if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }
            try {
                getAllFrePlanInfoRes =frePlanIService.GetAllFrePlanInfo(initDataFields,pageInfo);
                getAllFrePlanInfoRes.getBody().setMsgCode("0x00000");
                getAllFrePlanInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllFrePlanInfoResB getAllFrePlanInfoResB = new GetAllFrePlanInfoResB();
                getAllFrePlanInfoResB.setMsgCode(e.getMsgCode());
                getAllFrePlanInfoResB.setMsgDes(e.getMsgDes());
                getAllFrePlanInfoRes.setBody(getAllFrePlanInfoResB);
            }

        } else {
            GetAllFrePlanInfoResB getAllFrePlanInfoResB = new GetAllFrePlanInfoResB();
            getAllFrePlanInfoResB.setMsgCode("MG0006F");
            getAllFrePlanInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllFrePlanInfoRes.setBody(getAllFrePlanInfoResB);
        }
        getAllFrePlanInfoRes.setStatus("00");
        return getAllFrePlanInfoRes;
    }

    //获取次数保养计划列表信息(新)
    @RequestMapping(value="/GetAllFrePlanNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllFrePlanNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(frePlanIService.QALLGetAllFrePlanNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取次数保养计划信息
    @RequestMapping(value = "/GetFrePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetFrePlanInfoRes GetFrePlanInfo(GetAllReq getAllReq) {
        GetFrePlanInfoRes getFrePlanInfoRes=new GetFrePlanInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetFrePlanInfoReq00 getFrePlanInfoReq00 = CommonUtils.switchClass(GetFrePlanInfoReq00.class, getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null) {


            } else {  // 不分页
                try {
                    getFrePlanInfoRes =frePlanIService.GetFrePlanInfo(getFrePlanInfoReq00);
                    getFrePlanInfoRes.getBody().setMsgCode("0x00000");
                    getFrePlanInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetFrePlanInfoResB getFrePlanInfoResB = new GetFrePlanInfoResB();
                    getFrePlanInfoResB.setMsgCode(e.getMsgCode());
                    getFrePlanInfoResB.setMsgDes(e.getMsgDes());
                    getFrePlanInfoRes.setBody(getFrePlanInfoResB);
                }
            }
        } else {
            GetFrePlanInfoResB getFrePlanInfoResB = new GetFrePlanInfoResB();
            getFrePlanInfoResB.setMsgCode("MG0006F");
            getFrePlanInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getFrePlanInfoRes.setBody(getFrePlanInfoResB);
        }
        getFrePlanInfoRes.setStatus("00");
        return  getFrePlanInfoRes;
    }

    //保存次数保养计划信息
    @RequestMapping(value = "/SaveFrePlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveFrePlanInfoRes SaveFrePlanInfo(SaveReq saveReq){
        SaveFrePlanInfoRes saveFrePlanInfoRes=new SaveFrePlanInfoRes();
        SaveFrePlanInfoResB saveFrePlanInfoResB=new SaveFrePlanInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveFrePlanInfoReq00 saveFrePlanInfoReq00 = CommonUtils.switchClass(SaveFrePlanInfoReq00.class, saveReq.getBusData());

            try {
                saveFrePlanInfoRes = frePlanIService.AddSaveFrePlanInfo(saveFrePlanInfoReq00);
                saveFrePlanInfoRes.getBody().setMsgCode("0x00000");
                saveFrePlanInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveFrePlanInfoResB.setMsgCode(e.getMsgCode());
                saveFrePlanInfoResB.setMsgDes(e.getMsgDes());
                saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveFrePlanInfoReq01[] saveFrePlanInfoReq01 = CommonUtils.switchClass(SaveFrePlanInfoReq01[].class, saveReq.getBusData());

            try {
                saveFrePlanInfoRes = frePlanIService.RmSaveFrePlanInfo(saveFrePlanInfoReq01);
                saveFrePlanInfoResB=new SaveFrePlanInfoResB();
                saveFrePlanInfoResB.setMsgCode("0x00000");
                saveFrePlanInfoResB.setMsgDes("成功");
                saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
            } catch (SystemException e) {

                saveFrePlanInfoResB.setMsgCode(e.getMsgCode());
                saveFrePlanInfoResB.setMsgDes(e.getMsgDes());
                saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveFrePlanInfoReq02 saveFrePlanInfoReq02 = CommonUtils.switchClass(SaveFrePlanInfoReq02.class, saveReq.getBusData());
            try {
                saveFrePlanInfoRes =frePlanIService.ModSaveFrePlanInfo(saveFrePlanInfoReq02);

                saveFrePlanInfoResB.setMsgCode("0x00000");
                saveFrePlanInfoResB.setMsgDes("成功");
                saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
            } catch (SystemException e) {
                saveFrePlanInfoResB.setMsgCode(e.getMsgCode());
                saveFrePlanInfoResB.setMsgDes(e.getMsgDes());
                saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
            }
        } else {
            saveFrePlanInfoResB.setMsgCode("MG0006F");
            saveFrePlanInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveFrePlanInfoRes.setBody(saveFrePlanInfoResB);
        }
        saveFrePlanInfoRes.setStatus("00");
        return saveFrePlanInfoRes;
    }



}
