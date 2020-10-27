package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllBadInfoDto.GetAllBadInfoRes;
import pnc.mesadmin.dto.GetAllBadInfoDto.SaveBadInfoReq00;
import pnc.mesadmin.dto.GetAllBadInfoDto.SaveBadInfoRes;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.MBaseDto.MBaseResB;
import pnc.mesadmin.dto.SaveDcInfo.SaveDcInfoReqBD01;
import pnc.mesadmin.service.BadService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：不良原因Controller
 * 创建时间：2018-8-22
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/SunPort/Bad")
public class BadController {

    @Resource
    private BadService badService;

    //加载页面
    @RequestMapping("/BadPG")
    public String PrintSNPG() {
        return "process/Bad/badInfo";
    }


    @RequestMapping(value = "/GetAllNewBad", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllNewDcInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(badService.QALLGetAllDcInfoRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }





    //获取不良原因列表信息
    @RequestMapping(value = "/GetAllBadInfo", method = RequestMethod.POST)
    @ResponseBody
    public MBaseRes GetAllBadInfo(GetAllReq request) {
        List<InitDataField> InitDataFields = null;
        PageInfo pageInfo = null;
        if (request.getInitData() != null && !"".equals(request.getInitData())) {
            InitData InitData = CommonUtils.switchClass(pnc.mesadmin.dto.InitData.class, request.getInitData());
            if (InitData != null) {
                InitDataFields = InitData.getFiledList();
            }
        }

        if (request.getPageInfo() != null && !"".equals(request.getPageInfo())) {
            pageInfo = CommonUtils.switchClass(PageInfo.class, request.getPageInfo());
        }
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        try {
            baseResponse = badService.GetAllBadInfo(InitDataFields, pageInfo);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }


    //获取不良原因信息
    @RequestMapping(value = "/GetBadInfo", method = RequestMethod.POST)
    @ResponseBody
    public MBaseRes GetBadInfo(GetAllReq getAllReq) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
        if ("00".equals(getAllReq.getExecType())) {
            GetAllBadInfoRes request = CommonUtils.switchClass(GetAllBadInfoRes.class, getAllReq.getBusData());
            try {
                baseResponse = badService.GetBadInfo(request);
            } catch (SystemException e) {
                baseResBody.setMsgCode(e.getMsgCode());
                baseResBody.setMsgDes(e.getMsgDes());
                baseResponse.setBody(baseResBody);
            }
        } else {
            baseResBody.setMsgCode("MG0006F");
            baseResBody.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //保存接口
    @RequestMapping(value = "/SaveBadInfo", method = RequestMethod.POST)
    @ResponseBody
    public MBaseRes SaveBadInfo(SaveReq saveReq) {
        MBaseRes baseResponse = new MBaseRes();
        MBaseResB baseResBody = new MBaseResB();
       //
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                SaveBadInfoRes request = CommonUtils.switchClass(SaveBadInfoRes.class, saveReq.getBusData());
                baseResponse = badService.AddBadInfo(request);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                SaveDcInfoReqBD01 saveBadInfoReq00=CommonUtils.switchClass(SaveDcInfoReqBD01.class, saveReq.getBusData());
                baseResponse = badService.RmDelBadInfo(saveBadInfoReq00);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("删除成功");
                baseResponse.setBody(baseResBody);
            } else if ("02".equals(saveReq.getExecType())) { //修改
                SaveBadInfoRes request = CommonUtils.switchClass(SaveBadInfoRes.class, saveReq.getBusData());

                baseResponse = badService.AddSaveBadInfo(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("修改成功");
                baseResponse.setBody(baseResBody);
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
