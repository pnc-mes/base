package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoReqBD00;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoRes;
import pnc.mesadmin.dto.GetAllStationTInfo.GetAllStationTInfoResB;
import pnc.mesadmin.dto.GetStationDto.GetStationInfoRes;
import pnc.mesadmin.dto.GetStationDto.SaveStationInfoRes;
import pnc.mesadmin.service.StationService;
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
@RequestMapping("/Station")
public class StationController {

    @Resource
    private StationService service;

    @RequestMapping("/QHGWPG")
    public String homew(){
        return "mprocess/qhgw/qhgwinfo";
    }


    //返回页面
    @RequestMapping("/StationPG")
    public String specinfo(HttpServletRequest request) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String time = format.format(new Date());
        request.setAttribute("time", time);
        return "process/gw/gwinfo";
    }

    //获取工位列表信息
    @RequestMapping(value = "/GetAllStationInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllStationInfo(GetAllReq request) {
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
            baseResponse = service.GetAllStationInfo(InitDataFields, pageInfo);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //获取工位列表信息【树】
    @RequestMapping(value = "/GetAllStationInfoTree", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllStationInfoTree(GetAllReq request) {
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
            baseResponse = service.GetAllStationInfoTree(InitDataFields, pageInfo);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //获取根据线体RD获取工位列表信息【树】
    @RequestMapping(value = "/GetAllStationInfoByLineRd", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllStationInfoByLineRd(GetAllReq request) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        try {
            baseResponse = service.GetAllStationInfoByLineRd(request);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //获取工位 列表信息(GetAllStationTInfo)
    @RequestMapping(value = "/GetAllStationTInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllStationTInfoRes GetAllStationTInfo(GetAllReq request) {

        GetAllStationTInfoRes getAllStationTInfoRes = new GetAllStationTInfoRes();
        GetAllStationTInfoResB getAllStationTInfoResB = new GetAllStationTInfoResB();

        if(request.getExecType().equals("00")){
            GetAllStationTInfoReqBD00 getAllStationTInfoReqBD00 = CommonUtils.switchClass(GetAllStationTInfoReqBD00.class, request.getBusData());
            try {
                getAllStationTInfoRes = service.GetAllStationTInfo(getAllStationTInfoReqBD00);
                getAllStationTInfoRes.getBody().setMsgCode("0x00000");
                getAllStationTInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                getAllStationTInfoResB.setMsgCode(e.getMsgCode());
                getAllStationTInfoResB.setMsgDes(e.getMsgDes());
                getAllStationTInfoRes.setBody(getAllStationTInfoResB);
            }
        }
        getAllStationTInfoRes.setStatus("00");

        return getAllStationTInfoRes;
    }

    //获取工位信息
    @RequestMapping(value = "/GetStationInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetStationInfo(GetAllReq getAllReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        if ("00".equals(getAllReq.getExecType())) {
            GetStationInfoRes request = CommonUtils.switchClass(GetStationInfoRes.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    baseResponse = service.GetStationInfo(request);
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
    @RequestMapping(value = "/SaveStationInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveStationInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        SaveStationInfoRes request = CommonUtils.switchClass(SaveStationInfoRes.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                baseResponse = service.AddStationInfo(request);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                baseResponse = service.RmDelStationInfo(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("删除成功");
                baseResponse.setBody(baseResBody);
            } else if ("02".equals(saveReq.getExecType())) { //修改
                baseResponse = service.AddSaveStationInfo(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("修改成功");
                baseResponse.setBody(baseResBody);
            }else if("03".equals(saveReq.getExecType())){//保存
                baseResponse = service.AddSaveStationInfo03(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("修改成功");
                baseResponse.setBody(baseResBody);
            }else if("04".equals(saveReq.getExecType())){//复制
                baseResponse = service.AddSaveStationInfo04(request);
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
