package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.CheckTempDTO.CheckTempBaseDto;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.service.CheckTempService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：检验项类型Controller
 * 创建人：yinyang
 * 创建时间：2019-03-20
 * 修改人：
 * 修改时间：
 */

@org.springframework.stereotype.Controller
@Scope("prototype")
@RequestMapping("/CTP")
public class CheckTempController {

    @Resource
    private CheckTempService service;

    //获取页面
    @RequestMapping(value = "/CTPPG", method = RequestMethod.GET)
    public String specinfo() {
        return "qa/jyx/jymbinfo";
    }

    //获取检验模板
    @RequestMapping(value = "/GetAllCTPInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllCTPInfo(GetAllReq request) {
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
        try {
            baseResponse = service.GetAllCTPInfo(InitDataFields, pageInfo);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }


    //获取检验项清单信息
    @RequestMapping(value = "/GetCTPInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetCTPInfo(GetAllReq getAllReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if ("00".equals(getAllReq.getExecType())) {
            CheckTempBaseDto request = CommonUtils.switchClass(CheckTempBaseDto.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    baseResponse = service.GetCTPInfo(request);
                } catch (SystemException e) {
                    baseResBody.setMsgCode(e.getMsgCode());
                    baseResBody.setMsgDes(e.getMsgDes());
                    baseResponse.setBody(baseResBody);
                }
            }
        } else {
            baseResBody.setMsgCode("MG0006F");
            baseResBody.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //修改接口
    @RequestMapping(value = "/SaveCTPInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveCTPInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        CheckTempBaseDto request = CommonUtils.switchClass(CheckTempBaseDto.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                baseResponse = service.AddCTPInfo(request);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                baseResponse = service.RmDelCTPInfo(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("删除成功");
                baseResponse.setBody(baseResBody);
            } else if ("02".equals(saveReq.getExecType())) { //修改
                baseResponse = service.AddSaveCTPInfo(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("修改成功");
                baseResponse.setBody(baseResBody);
            } else if ("03".equals(saveReq.getExecType())) { //复制
                baseResponse = service.AddCopyCTPInfo(request);
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
