package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCuOrderInfo.GetAllCuOrderInfoRes;
import pnc.mesadmin.dto.GetAllCuOrderInfo.GetAllCuOrderInfoResB;
import pnc.mesadmin.dto.GetAllCuOrderInfo.GetCuOrderReqBD00;
import pnc.mesadmin.dto.GetAllCuOrderInfo.SaveCuOrderReqBD00;
import pnc.mesadmin.service.CuOrderService;
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

@Controller
@Scope("prototype")
@RequestMapping("/CO")
public class CoController {

    @Resource
    private CuOrderService cuOrderService;

    @RequestMapping("/COPG")
    public String specinfo(HttpServletRequest request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String time = format.format(new Date());
        request.setAttribute("time", time);
        return "plan/cuorder/order";
    }

    //获取订单列表信息
    @RequestMapping(value = "/GetAllCOInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCuOrderInfoRes GetAllCOInfo(GetAllReq request) {
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
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = null;
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = null;
        try {
            getAllCuOrderInfoRes = cuOrderService.GetAllCOInfo(InitDataFields, pageInfo);
            getAllCuOrderInfoRes.getBody().setMsgCode("0x00000");
            getAllCuOrderInfoRes.getBody().setMsgDes("成功");
        } catch (SystemException e) {
            getAllCuOrderInfoResB.setMsgCode(e.getMsgCode());
            getAllCuOrderInfoResB.setMsgDes(e.getMsgDes());
            getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        }
        return getAllCuOrderInfoRes;
    }


    //获取订单关联工单列表信息
    @RequestMapping(value = "/GetAllCOBInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCuOrderInfoRes GetAllCOBInfo(GetAllReq getAllReq) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = new GetAllCuOrderInfoResB();
        if ("00".equals(getAllReq.getExecType())) {
            GetCuOrderReqBD00 getWOInfoReqBD00 = CommonUtils.switchClass(GetCuOrderReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    getAllCuOrderInfoRes = cuOrderService.GetAllCOBInfo(getWOInfoReqBD00);
                    getAllCuOrderInfoRes.getBody().setMsgCode("0x00000");
                    getAllCuOrderInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    getAllCuOrderInfoResB.setMsgCode(e.getMsgCode());
                    getAllCuOrderInfoResB.setMsgDes(e.getMsgDes());
                    getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
                }
            }
        } else {
            getAllCuOrderInfoResB.setMsgCode("MG0006F");
            getAllCuOrderInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        }
        getAllCuOrderInfoRes.setStatus("00");
        return getAllCuOrderInfoRes;
    }

    //获取订单详情接口
    @RequestMapping(value = "/GetCOInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCuOrderInfoRes GetCOInfo(GetAllReq getAllReq) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = null;
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = null;
        if ("00".equals(getAllReq.getExecType())) {
            GetCuOrderReqBD00 getWOInfoReqBD00 = CommonUtils.switchClass(GetCuOrderReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    getAllCuOrderInfoRes = cuOrderService.GetCOInfo(getWOInfoReqBD00);
                    getAllCuOrderInfoRes.getBody().setMsgCode("0x00000");
                    getAllCuOrderInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    getAllCuOrderInfoResB.setMsgCode(e.getMsgCode());
                    getAllCuOrderInfoResB.setMsgDes(e.getMsgDes());
                    getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
                }
            }
        } else {
            getAllCuOrderInfoResB.setMsgCode("MG0006F");
            getAllCuOrderInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        }
        getAllCuOrderInfoRes.setStatus("00");
        return getAllCuOrderInfoRes;
    }

    //修改订单信息
    @RequestMapping(value = "/SaveCOInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCuOrderInfoRes SaveCOInfo(SaveReq saveReq) {
        GetAllCuOrderInfoRes getAllCuOrderInfoRes = new GetAllCuOrderInfoRes();
        GetAllCuOrderInfoResB getAllCuOrderInfoResB = new GetAllCuOrderInfoResB();
        SaveCuOrderReqBD00 saveCuOrderReqBD00 = CommonUtils.switchClass(SaveCuOrderReqBD00.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                getAllCuOrderInfoRes = cuOrderService.AddCOInfo(saveCuOrderReqBD00);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                getAllCuOrderInfoRes = cuOrderService.RmDelCOInfo(saveCuOrderReqBD00);
                getAllCuOrderInfoResB.setMsgCode("0x00000");
                getAllCuOrderInfoResB.setMsgDes("删除成功");
                getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
            } else if ("02".equals(saveReq.getExecType())) { //修改
                getAllCuOrderInfoRes = cuOrderService.AddSaveCOInfo(saveCuOrderReqBD00);
                getAllCuOrderInfoResB.setMsgCode("0x00000");
                getAllCuOrderInfoResB.setMsgDes("修改成功");
                getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
            }

        } catch (SystemException e) {
            getAllCuOrderInfoResB.setMsgCode(e.getMsgCode());
            getAllCuOrderInfoResB.setMsgDes(e.getMsgDes());
            getAllCuOrderInfoRes.setBody(getAllCuOrderInfoResB);
        }
        getAllCuOrderInfoRes.setStatus("00");
        return getAllCuOrderInfoRes;
    }


}
