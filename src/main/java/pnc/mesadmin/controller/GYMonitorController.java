package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJReqBD00;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJRes;
import pnc.mesadmin.dto.GYMonitor.SaveGYYJResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.GYMonitorIService;

import javax.annotation.Resource;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/9
 */
@Controller
@RequestMapping("/GYMonitor")
public class GYMonitorController {
    @Resource
    private GYMonitorIService gyMonitorIService;

    @ResponseBody
    @RequestMapping(value = "/SaveGYYJInfo", method = RequestMethod.POST)
    public SaveGYYJRes SaveGYYJ(@RequestBody SaveGYYJReqBD00 bd00){
        SaveGYYJRes res = new SaveGYYJRes();
        SaveGYYJResB resB = null;

        try{
            res = gyMonitorIService.SaveGYYJ(bd00);
            res.getBody().setMsgCode("0x00000");
            res.getBody().setMsgDes("成功");
        }catch (SystemException e){
            resB = new SaveGYYJResB();
            resB.setMsgCode(e.getMsgCode());
            resB.setMsgDes(e.getMsgDes());
            res.setBody(resB);
        }

        res.setStatus("00");
        return res;
    }
}
