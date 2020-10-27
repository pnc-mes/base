package pnc.mesadmin.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllToolDevDto.GetToolDevRes;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes00;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes01;
import pnc.mesadmin.service.ToolDevService;
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
 * 子系统名称：工具上下设备信息Controller
 * 创建人：yinyang
 * 创建时间：2018-08-27
 * 修改人：
 * 修改时间：
 */

@org.springframework.stereotype.Controller
@Scope("prototype")
@RequestMapping("/ToolDev")
public class ToolDevController {

    @Resource
    private ToolDevService service;

    //返回页面
    @RequestMapping("/ToolDevPG")
    public String specinfo(HttpServletRequest request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String time = format.format(new Date());
        request.setAttribute("time", time);
        return "mprocess/toolsxsb/toolsxsbinfo";
    }

    //获取工具设备工具信息
    @RequestMapping(value = "/GetToolDevRelInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetToolDevRelInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        GetToolDevRes request = CommonUtils.switchClass(GetToolDevRes.class, saveReq.getBusData());
        try {
            baseResponse = service.GetToolDevRelInfo(request);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //工具上/下设备操作信息
    @RequestMapping(value = "/SaveToolDevRel", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveToolDevRel(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();

        try {
            if ("00".equals(saveReq.getExecType())) {
                SaveToolDevRes00 request = CommonUtils.switchClass(SaveToolDevRes00.class, saveReq.getBusData());
                baseResponse = service.SaveToolDevRel00(request);
            } else if ("01".equals(saveReq.getExecType())) {
                List<SaveToolDevRes01> res01 = (List<SaveToolDevRes01>) JSONArray.parseArray(saveReq.getBusData(), SaveToolDevRes01.class);
                baseResponse = service.SaveToolDevRel01(res01);
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
