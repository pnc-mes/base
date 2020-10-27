package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDotaskInfo.*;
import pnc.mesadmin.service.DoTaskService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工单信息Controller
 * 创建人：yinyang
 * 创建时间：2018-08-27
 * 修改人：
 * 修改时间：
 */

@org.springframework.stereotype.Controller
@Scope("prototype")
@RequestMapping("/DoTask")
public class DoTaskController {

    @Resource
    private DoTaskService service;

    //返回页面保养
    @RequestMapping("/DoTaskPMPG")
    public String DoTaskPMPG(HttpServletRequest request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String time = format.format(new Date());
        request.setAttribute("time", time);
        return "mprocess/dopm/dopminfo";
    }

    //返回页面点检
    @RequestMapping("/DoTaskACPG")
    public String DoTaskACPG(HttpServletRequest request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String time = format.format(new Date());
        request.setAttribute("time", time);
        return "mprocess/docheck/docheckinfo";
    }

    //返回页面点检
    @RequestMapping("/HP")
    public String HPPG(HttpServletRequest request) {
        return "mprocess/hp/hpinfo";
    }

    //测试在线维修页面
    @RequestMapping("/RePart")
    public String RePart() {
        return "mprocess/repart/repartinfo";
    }

    //获取保养/点检计划列表信息
    @RequestMapping(value = "/GetPMACInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetPMACInfo(GetAllReq request) {
        List<InitDataField> InitDataFields = null;
        PageInfo pageInfo = null;
        if (request.getInitData() != null && !"".equals(request.getInitData())) {
            InitData InitData = CommonUtils.switchClass(InitData.class, request.getInitData());
            if (InitData != null) {
                InitDataFields = InitData.getFiledList();
            }
        }
        if (request.getPageInfo() != null && !"".equals(request.getPageInfo())) {
            pageInfo = CommonUtils.switchClass(PageInfo.class, request.getPageInfo());
        }
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (request.getExecType().equals("00")) {
            GetAllDotaskRes00 req00 = CommonUtils.switchClass(GetAllDotaskRes00.class, request.getBusData());
            try {
                baseResponse = service.GetAllDoTask00(req00);
            } catch (SystemException e) {
                baseResBody.setMsgCode(e.getMsgCode());
                baseResBody.setMsgDes(e.getMsgDes());
                baseResponse.setBody(baseResBody);
            }

        } else if (request.getExecType().equals("01")) {
            GetAllDotaskRes01 req01 = CommonUtils.switchClass(GetAllDotaskRes01.class, request.getBusData());
            try {
                baseResponse = service.GetAllDoTask01(req01);
            } catch (SystemException e) {
                baseResBody.setMsgCode(e.getMsgCode());
                baseResBody.setMsgDes(e.getMsgDes());
                baseResponse.setBody(baseResBody);
            }
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }


    //保养保养/点检执行信息
    @RequestMapping(value = "/SavePMACInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SavePMACInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if (saveReq.getExecType().equals("00")) {
            SaveDotaskRes req00 = CommonUtils.switchClass(SaveDotaskRes.class, saveReq.getBusData());
            try {
                baseResponse = service.SavePMACInfo00(req00);
            } catch (SystemException e) {
                baseResBody.setMsgCode(e.getMsgCode());
                baseResBody.setMsgDes(e.getMsgDes());
                baseResponse.setBody(baseResBody);
            }

        } else if (saveReq.getExecType().equals("01")) {
            SaveDoCheckRes req01 = CommonUtils.switchClass(SaveDoCheckRes.class, saveReq.getBusData());
            try {
                baseResponse = service.SavePMACInfo01(req01);
            } catch (SystemException e) {
                baseResBody.setMsgCode(e.getMsgCode());
                baseResBody.setMsgDes(e.getMsgDes());
                baseResponse.setBody(baseResBody);
            }
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }


    //生产保养/点检信息
    @RequestMapping(value = "/SaveCreateTaskInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveCreateTaskInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        SaveCreateTaskReqest request = CommonUtils.switchClass(SaveCreateTaskReqest.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                baseResponse = service.SaveCreateTaskInfo(request);
            }
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }
}
