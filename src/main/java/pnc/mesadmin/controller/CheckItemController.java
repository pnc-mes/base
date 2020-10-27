package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetCheckItemInfoRequest;
import pnc.mesadmin.dto.GetAllCheckItemDTO.GetCheckItemInfoResponse;
import pnc.mesadmin.service.CheckItemService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：检验项清单Controller
 * 创建人：yinyang
 * 创建时间：2019-03-19
 * 修改人：
 * 修改时间：
 */

@org.springframework.stereotype.Controller
@Scope("prototype")
@RequestMapping("/CI")
public class  CheckItemController {

    @Resource
    private CheckItemService service;

    //返回页面
    @RequestMapping("/jyxPG")
    public String jyxinfo() {
        return "qa/jyx/jyxinfo";
    }



    @RequestMapping(value = "/gldwPG")
    public String home(){
        return "qa/jyx/zjglinfo";
    }
    //获取检验项清单列表信息
    @RequestMapping(value = "/GetAllCIInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllCIInfo(GetAllReq request) {
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
            baseResponse = service.GetAllCIInfo(InitDataFields, pageInfo);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }


    //获取检验项清单信息
    @RequestMapping(value = "/GetCIInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetCIInfo(GetAllReq getAllReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if ("00".equals(getAllReq.getExecType())) {
            GetCheckItemInfoRequest request = CommonUtils.switchClass(GetCheckItemInfoRequest.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    baseResponse = service.GetCIInfo(request);
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
    @RequestMapping(value = "/SaveCIInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveCIInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        GetCheckItemInfoResponse request = CommonUtils.switchClass(GetCheckItemInfoResponse.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                baseResponse = service.AddCIInfo(request);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                baseResponse = service.RmDelCIInfo(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("删除成功");
                baseResponse.setBody(baseResBody);
            } else if ("02".equals(saveReq.getExecType())) { //修改
                baseResponse = service.AddSaveCIInfo(request);
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
