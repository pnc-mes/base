package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetMODevInfo.GetMODevInfoReqBD00;
import pnc.mesadmin.dto.GetMODevInfo.GetMODevInfoRes;
import pnc.mesadmin.dto.GetMODevInfo.GetMODevInfoResB;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoReqBD00;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoRes;
import pnc.mesadmin.dto.SaveMODevInfo.SaveMODevInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.MODevIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：装料管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-08-29
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/MODev")
public class MODevController {

    @Resource
    private MODevIService moDevIService;

    //装料页面
    @RequestMapping(value = "/MODevPG")
    public String getMODevPage(){

        return "mprocess/specopert/loadmainfo";
    }

    //获取装料信息
    @RequestMapping(value = "/GetMODevInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMODevInfoRes GetMODev(GetAllReq getAllReq) {

        GetMODevInfoRes objEGetMODevInfoRes = new GetMODevInfoRes();

        String busData = getAllReq.getBusData();

        if ("00".equals(getAllReq.getExecType())) {

            GetMODevInfoReqBD00 objEGetMODevInfoReqBD00 = CommonUtils.switchClass(GetMODevInfoReqBD00.class, busData);

            try {
                objEGetMODevInfoRes = moDevIService.GetMODev(objEGetMODevInfoReqBD00);
                objEGetMODevInfoRes.getBody().setMsgCode("0x00000");
                objEGetMODevInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetMODevInfoResB objEGetMODevInfoResB = new GetMODevInfoResB();
                objEGetMODevInfoResB.setMsgCode(e.getMsgCode());
                objEGetMODevInfoResB.setMsgDes(e.getMsgDes());
                objEGetMODevInfoRes.setBody(objEGetMODevInfoResB);
            }
        }

        objEGetMODevInfoRes.setStatus("00");

        return objEGetMODevInfoRes;
    }

    //保存装料信息
    @RequestMapping(value = "/SaveMODevInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveMODevInfoRes SaveMODev(GetAllReq getAllReq) {

        SaveMODevInfoRes objESaveMODevInfoRes = new SaveMODevInfoRes();
        SaveMODevInfoResB objESaveMODevInfoResB = new SaveMODevInfoResB();

        String busData = getAllReq.getBusData();

        if ("00".equals(getAllReq.getExecType())) {

            SaveMODevInfoReqBD00 objESaveMODevInfoReqBD00 = CommonUtils.switchClass(SaveMODevInfoReqBD00.class, busData);

            try {
                objESaveMODevInfoRes = moDevIService.AddMODev(objESaveMODevInfoReqBD00);
                objESaveMODevInfoResB.setMsgCode("0x00000");
                objESaveMODevInfoResB.setMsgDes("成功");
            } catch (SystemException e) {
                objESaveMODevInfoResB.setMsgCode(e.getMsgCode());
                objESaveMODevInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveMODevInfoRes.setBody(objESaveMODevInfoResB);
        objESaveMODevInfoRes.setStatus("00");

        return objESaveMODevInfoRes;
    }
}