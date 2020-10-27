package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetGCInfo.GetGCInfoRes;
import pnc.mesadmin.dto.GetGCInfo.GetGCInfoResB;
import pnc.mesadmin.dto.SaveGCInfo.SaveGCInfoReqBD00;
import pnc.mesadmin.dto.SaveGCInfo.SaveGCInfoRes;
import pnc.mesadmin.dto.SaveGCInfo.SaveGCInfoResB;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：全局配置信息Controller
 * 创建人：刘福志
 * 创建时间：2017-8-25
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/GConfig")
public class GConfigController {
    @Resource
    private GConfigIService gConfigIService;

    //获取全局配置页面
    @RequestMapping(value = "/GConfigPG")
    public String GConfigPG() {

        return "currency/gcinfo";
    }

    //dto获取全局配置信息列表
    @RequestMapping(value = "/GetGCInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetGCInfoRes GetGCInfo(GetAllReq getAllReq) {
        GetGCInfoRes objEGetGCInfoRes = new GetGCInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            try {
                objEGetGCInfoRes = gConfigIService.QALLselectAllGetGCInfo();
                objEGetGCInfoRes.getBody().setMsgCode("0x00000");
                objEGetGCInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetGCInfoResB objEGetGCInfoResB = new GetGCInfoResB();
                objEGetGCInfoResB.setMsgCode(e.getMsgCode());
                objEGetGCInfoResB.setMsgDes(e.getMsgDes());
                objEGetGCInfoRes.setBody(objEGetGCInfoResB);
            }
        } else {
            GetGCInfoResB objEGetGCInfoResB = new GetGCInfoResB();
            objEGetGCInfoResB.setMsgCode("MG0006F");
            objEGetGCInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetGCInfoRes.setBody(objEGetGCInfoResB);
        }
        objEGetGCInfoRes.setStatus("00");
        return objEGetGCInfoRes;
    }

    //保存全局配置
    @RequestMapping(value = "/SaveGCInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveGCInfoRes SaveGCInfo(SaveReq saveReq) {
        SaveGCInfoRes objESaveGCInfoRes = new SaveGCInfoRes();
        SaveGCInfoResB objESaveGCInfoResB = new SaveGCInfoResB();

        if ("00".equals(saveReq.getExecType())) {
            SaveGCInfoReqBD00 busData00 = CommonUtils.switchClass(SaveGCInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveGCInfoRes = gConfigIService.AddinsertSaveGCInfo(busData00);
                objESaveGCInfoRes.getBody().setMsgCode("0x00000");
                objESaveGCInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveGCInfoResB = new SaveGCInfoResB();
                objESaveGCInfoResB.setMsgCode(e.getMsgCode());
                objESaveGCInfoResB.setMsgDes(e.getMsgDes());
                objESaveGCInfoRes.setBody(objESaveGCInfoResB);
            }
        } else {
            objESaveGCInfoResB.setMsgCode("MG0006F");
            objESaveGCInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00");
            objESaveGCInfoRes.setBody(objESaveGCInfoResB);
        }
        objESaveGCInfoRes.setStatus("00");
        return objESaveGCInfoRes;
    }

}
