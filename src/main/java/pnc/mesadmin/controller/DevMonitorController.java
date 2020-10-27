package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoReq00;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoReq01;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoRes;
import pnc.mesadmin.dto.SaveDExLogInfo.SaveDExLogInfoResB;
import pnc.mesadmin.dto.SaveDevSMInfo.*;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.DevMonitorIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/10/22 18:15
 * @Description:
 */
@Controller
@RequestMapping("/DevMonitor")
public class DevMonitorController {
    @Resource
    private DevMonitorIService devMonitorIService;



    //保存
    @RequestMapping(value = "/SaveDExLogInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveDExLogInfoRes SaveDExLogInfoRes(SaveReq saveReq) {
        SaveDExLogInfoRes saveDExLogInfoRes = new SaveDExLogInfoRes();
        SaveDExLogInfoResB saveDExLogInfoResB = null;
        String rowData = saveReq.getBusData();
        // 新增
        if ("00".equals(saveReq.getExecType())) {
            //该接口的请求dto严格遵守驼峰命名法
            SaveDExLogInfoReq00 saveDExLogInfoReq00 = CommonUtils.switchClass(SaveDExLogInfoReq00.class, rowData);

            try {
                saveDExLogInfoRes = devMonitorIService.AddSaveDExLogInfoRes(saveDExLogInfoReq00);
                saveDExLogInfoResB = new SaveDExLogInfoResB();
                saveDExLogInfoResB.setMsgCode("0x00000");
                saveDExLogInfoResB.setMsgDes("成功");
                saveDExLogInfoRes.setBody(saveDExLogInfoResB);
            } catch (SystemException e) {
                saveDExLogInfoResB = new SaveDExLogInfoResB();
                saveDExLogInfoResB.setMsgCode(e.getMsgCode());
                saveDExLogInfoResB.setMsgDes(e.getMsgDes());
                saveDExLogInfoRes.setBody(saveDExLogInfoResB);
            }
        }
       else if ("01".equals(saveReq.getExecType())) {
            //该接口的请求dto严格遵守驼峰命名法
            SaveDExLogInfoReq01 saveDExLogInfoReq01 = CommonUtils.switchClass(SaveDExLogInfoReq01.class, rowData);

            try {
                saveDExLogInfoRes = devMonitorIService.AddSaveDExLogInfoReq01(saveDExLogInfoReq01);
                saveDExLogInfoResB = new SaveDExLogInfoResB();
                saveDExLogInfoResB.setMsgCode("0x00000");
                saveDExLogInfoResB.setMsgDes("成功");
                saveDExLogInfoRes.setBody(saveDExLogInfoResB);
            } catch (SystemException e) {
                saveDExLogInfoResB = new SaveDExLogInfoResB();
                saveDExLogInfoResB.setMsgCode(e.getMsgCode());
                saveDExLogInfoResB.setMsgDes(e.getMsgDes());
                saveDExLogInfoRes.setBody(saveDExLogInfoResB);
            }
        }
    else {
            saveDExLogInfoResB = new SaveDExLogInfoResB();
            saveDExLogInfoResB.setMsgCode("MG0006F");
            saveDExLogInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01");
            saveDExLogInfoRes.setBody(saveDExLogInfoResB);
        }

        saveDExLogInfoRes.setStatus("00");
        return saveDExLogInfoRes;
    }
}
