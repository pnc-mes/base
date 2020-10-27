package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoRes;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResB;
import pnc.mesadmin.dto.GetAllRejectInfo.GetAllRejectInfoResD1;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoReqBD00;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoRes;
import pnc.mesadmin.dto.SaveRejectInfo.SaveRejectInfoResB;
import pnc.mesadmin.service.RejectIService;
import pnc.mesadmin.utils.CommonUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：标记不良Controller
 * 创建人：haozan
 * 创建时间： 2018/6/7
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Reject")
public class RejectController {

    @Resource
    private RejectIService rejectIService;

    //获取标记不良页面
    @RequestMapping(value = "/RejectPG")
    public String geRejectPGPage() {

        return "mprocess/reject/rejectinfo";
    }
    @RequestMapping(value = "/RejectPG1")
    public String geRejectPGPage1() {

        return "mprocess/reject/rejectinfo1";
    }

    //获取不良标记信息列表
    @RequestMapping(value = "/GetAllRejectsInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllRejectInfoRes GetAllRejectInfoRes(GetAllReq getAllReq) {
        GetAllRejectInfoRes getAllRejectInfoRes = new GetAllRejectInfoRes();
        GetAllRejectInfoResB getAllRejectInfoResB = null;
        if("00".equals(getAllReq.getExecType())){
            GetAllRejectInfoResD1 getAllRejectInfoResD1 = CommonUtils.switchClass(GetAllRejectInfoResD1.class, getAllReq.getBusData());


            try {
                getAllRejectInfoRes = rejectIService.GetAllRejectInfoRes(getAllRejectInfoResD1);
                getAllRejectInfoRes.getBody().setMsgCode("0x00000");
                getAllRejectInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException objSystemException) {
                getAllRejectInfoRes = new GetAllRejectInfoRes();
                getAllRejectInfoResB = new GetAllRejectInfoResB();

                getAllRejectInfoResB.setMsgCode(objSystemException.getMsgCode());
                getAllRejectInfoResB.setMsgDes(objSystemException.getMsgDes());
                getAllRejectInfoRes.setStatus("01");
                getAllRejectInfoRes.setBody(getAllRejectInfoResB);
            }

        }
        return getAllRejectInfoRes;
    }

    //保存不良标记
    @RequestMapping(value = "/SaveRejectInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveRejectInfoRes SaveRejectInfo(HttpServletRequest request, SaveReq saveReq) {
        SaveRejectInfoRes saveRejectInfoRes = new SaveRejectInfoRes();
        SaveRejectInfoResB saveRejectInfoResB = new SaveRejectInfoResB();

        String busData = saveReq.getBusData();


        if ("00".equals(saveReq.getExecType())) { //新增

           SaveRejectInfoReqBD00[] objESaveBatchInfoReqBD00 = CommonUtils.switchClass(SaveRejectInfoReqBD00[].class, busData);

            try {
                saveRejectInfoRes = rejectIService.AddReject(objESaveBatchInfoReqBD00);

                saveRejectInfoRes.getBody().setMsgCode("0x00000");
                saveRejectInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveRejectInfoResB.setMsgCode(e.getMsgCode());
                saveRejectInfoResB.setMsgDes(e.getMsgDes());
                saveRejectInfoRes.setBody(saveRejectInfoResB);
            }
        }
        saveRejectInfoRes.setStatus("00");
        return saveRejectInfoRes;

    }

}
