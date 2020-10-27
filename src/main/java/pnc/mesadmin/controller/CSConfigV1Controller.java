package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigReq;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigRes;
import pnc.mesadmin.service.CSConfigV1Service;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/CSConfig/V1")
public class CSConfigV1Controller {

    @RequestMapping(value = "/CSConfigPG")
    public String getcompanyinfoPage() {
        return "sys/tycx/tycxinfo";
    }

    @Resource
    CSConfigV1Service csConfigService;


    //获取通用查询信息列表
    @RequestMapping(value = "/GetAllCSInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllCSInfo(GetAllReq request) throws SystemException {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        List<InitDataField> objEInitDataFields = null;
        PageInfo pageInfo = null;

        if (request.getInitData() != null && !"".equals(request.getInitData())) {
            InitData objEInitData = CommonUtils.switchClass(InitData.class, request.getInitData());

            if (objEInitData != null) {
                objEInitDataFields = objEInitData.getFiledList();
            }
        }

        if (request.getPageInfo() != null && !"".equals(request.getPageInfo())) {
            pageInfo = CommonUtils.switchClass(PageInfo.class, request.getPageInfo());
        }
        try {
            baseRes = csConfigService.GetAllCSInfo(objEInitDataFields, pageInfo);
            baseRes.setStatus("00");
        } catch (SystemException e) {
            baseResB.setMsgCode(e.getMsgCode());
            baseResB.setMsgDes(e.getMsgDes());
            baseRes.setBody(baseResB);
        }
        baseRes.setStatus("00");
        return baseRes;
    }


    //获取通用查询
    @RequestMapping(value = "/GetCSInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetCSInfo(GetAllReq request) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        try {
            GetCsConfigReq getCsConfigReq = CommonUtils.switchClass(GetCsConfigReq.class, request.getBusData());
            baseRes = csConfigService.GetCSInfo(getCsConfigReq);
            baseRes.setStatus("00");
        } catch (SystemException e) {
            baseResB.setMsgCode(e.getMsgCode());
            baseResB.setMsgDes(e.getMsgDes());
            baseRes.setBody(baseResB);
        }
        return baseRes;
    }

    //保存用户信息
    @RequestMapping(value = "/SaveCSInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveCSInfo(SaveReq saveReq) throws SystemException {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();
        try {
            if ("00".equals(saveReq.getExecType())) {
                GetCsConfigRes getAllAppSetReq = CommonUtils.switchClass(GetCsConfigRes.class, saveReq.getBusData());
                //新增
                baseRes = csConfigService.AddSaveCSInfo00(getAllAppSetReq);
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("新增成功");
                baseRes.setBody(baseResB);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                GetCsConfigReq getCsConfigReq = CommonUtils.switchClass(GetCsConfigReq.class, saveReq.getBusData());
                baseRes = csConfigService.AddSaveCSInfo01(getCsConfigReq);
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("删除成功");
                baseRes.setBody(baseResB);
            } else if ("02".equals(saveReq.getExecType())) { //修改
                GetCsConfigRes getAllAppSetReq = CommonUtils.switchClass(GetCsConfigRes.class, saveReq.getBusData());
                baseRes = csConfigService.AddSaveCSInfo02(getAllAppSetReq);
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("修改成功");
                baseRes.setBody(baseResB);
            } else if ("03".equals(saveReq.getExecType())) { //菜单发布
                GetCsConfigRes getAllAppSetReq = CommonUtils.switchClass(GetCsConfigRes.class, saveReq.getBusData());
                baseRes = csConfigService.AddSaveCSInfo03(getAllAppSetReq);
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("菜单发布成功");
                baseRes.setBody(baseResB);
            }

        } catch (SystemException e) {
            baseResB.setMsgCode(e.getMsgCode());
            baseResB.setMsgDes(e.getMsgDes());
            baseRes.setBody(baseResB);
        }
        baseRes.setStatus("00");
        return baseRes;
    }
}
