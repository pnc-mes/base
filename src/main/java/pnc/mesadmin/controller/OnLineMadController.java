package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoReq;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoRes;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoResB;
import pnc.mesadmin.service.OnLineMadIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;

/**
 * Created by zhaochao on 11/6 0006.
 */
@Controller
@Scope("prototype")
@RequestMapping("/YM")
public class OnLineMadController {

    @Resource
    private OnLineMadIService onLineMadIService;

    @RequestMapping("/onlinemadPG")
    public String toOnLineMadPG(){
        return "mprocess/onlinemad";
    }

    @ResponseBody
    @RequestMapping(value = "/SaveYMInfo",method = RequestMethod.POST)
    public SaveYMInfoRes saveYMInfo(SaveReq argSaveReq){
        SaveYMInfoRes objSaveYMInfoRes = new SaveYMInfoRes();
        SaveYMInfoResB objSaveYMInfoResB = new SaveYMInfoResB();

        if("00".equals(argSaveReq.getExecType())){
            if(!"".equals(argSaveReq.getBusData())) {
                SaveYMInfoReq objSaveYMInfoReq = CommonUtils.switchClass(SaveYMInfoReq.class,argSaveReq.getBusData());
                objSaveYMInfoResB = onLineMadIService.AddYMInfo(objSaveYMInfoReq);
            }else{
                objSaveYMInfoResB.setMsgCode("0x00002");
                objSaveYMInfoResB.setMsgDes("没有接收到传递的参数");
            }
        }else{
            objSaveYMInfoResB.setMsgCode("0x00001");
            objSaveYMInfoResB.setMsgDes("非法请求");
        }
        objSaveYMInfoRes.setStatus("00");
        objSaveYMInfoRes.setBody(objSaveYMInfoResB);
        return objSaveYMInfoRes;
    }
}
