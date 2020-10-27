package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetPAppInfo.GetPAppInfoRes;
import pnc.mesadmin.dto.GetPAppInfo.GetPAppInfoResB;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoReqBD02;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoRes;
import pnc.mesadmin.dto.SavePAppInfo.SavePAppInfoResB;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.PubAppIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：发布App控制器
 * 创建人：潘俊峰
 * 创建时间：2017-09-06
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/PubApp")
public class PubAppController {

    @Resource
    private PubAppIService pubAppIService;

    @RequestMapping(value = "/PubAppPG")
    public String PubAppPG(){

        return "sys/pubapp";
    }

    //获取发布App信息
    @RequestMapping(value = "/GetPAppInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPAppInfoRes GetPubApp(GetAllReq getAllReq){

        GetPAppInfoRes objEGetPAppInfoRes = new GetPAppInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            try {
                objEGetPAppInfoRes = pubAppIService.GetPApp();
                objEGetPAppInfoRes.getBody().setMsgCode("0x00000");
                objEGetPAppInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPAppInfoResB objEGetPAppInfoResB = new GetPAppInfoResB();
                objEGetPAppInfoResB.setMsgCode(e.getMsgCode());
                objEGetPAppInfoResB.setMsgDes(e.getMsgDes());
                objEGetPAppInfoRes.setBody(objEGetPAppInfoResB);
            }
        }

        objEGetPAppInfoRes.setStatus("00");

        return objEGetPAppInfoRes;
    }

    //保存发布App信息
    @RequestMapping(value = "/SavePAppInfo", method = RequestMethod.POST)
    @ResponseBody
    public SavePAppInfoRes SavePubApp(HttpServletRequest request, SaveReq saveReq){

        SavePAppInfoRes objESavePAppInfoRes = new SavePAppInfoRes();
        SavePAppInfoResB objESavePAppInfoResB = new SavePAppInfoResB();

        String busData = saveReq.getBusData();

        if("02".equals(saveReq.getExecType())){ //编辑

            SavePAppInfoReqBD02 objESavePAppInfoReqBD02 = CommonUtils.switchClass(SavePAppInfoReqBD02.class, busData);

            try {
                objESavePAppInfoRes = pubAppIService.AddPApp(request, objESavePAppInfoReqBD02);
                objESavePAppInfoResB.setMsgCode("0x00000");
                objESavePAppInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePAppInfoResB.setMsgCode(e.getMsgCode());
                objESavePAppInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESavePAppInfoRes.setBody(objESavePAppInfoResB);
        objESavePAppInfoRes.setStatus("00");

        return objESavePAppInfoRes;
    }

}
