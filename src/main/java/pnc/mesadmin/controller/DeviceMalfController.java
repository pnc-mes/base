package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetDevMalfInfo.GetDevMalfInfoReq00;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq00;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq01;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq02;
import pnc.mesadmin.service.DeviceMalfIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 18:22
 * @Description:
 */
@Controller
@RequestMapping("/DeviceMalf")
public class DeviceMalfController {
    @Resource
    private DeviceMalfIService deviceMalfIService;

    @RequestMapping("/SGGZPG")
    public String home(){
        return "res/sbgz/sbgzinfo";
    }

    //获取列表信息
    @RequestMapping(value = "/GetAllDevMalfInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllSpecInfo(GetAllReq getAllReq) {
        BaseRes baseRes = new BaseRes();
        if ("00".equals(getAllReq.getExecType())) {
            List<InitDataField> objEInitDataFields = null;
            PageInfo pageInfo = null;

            if (getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if (objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }
            try {
                baseRes = deviceMalfIService.QALLBaseRes(objEInitDataFields, pageInfo);
                baseRes.getBody().setMsgCode("0x00000");
                baseRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                BaseResB baseResB = new BaseResB();
                baseResB.setMsgCode(e.getMsgCode());
                baseResB.setMsgDes(e.getMsgDes());
                baseRes.setBody(baseResB);
            }

        } else {
            BaseResB baseResB = new BaseResB();
            baseResB.setMsgCode("MG0006F");
            baseResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            baseRes.setBody(baseResB);
        }

        baseRes.setStatus("00");
        return baseRes;
    }

    //获取单个
    @RequestMapping(value = "/GetDevMalfInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetSPartInfo(GetAllReq getAllReq) {
        BaseRes baseRes = null;
        BaseResB baseResB = null;
        if ("00".equals(getAllReq.getExecType())) {

            GetDevMalfInfoReq00 getDevMalfInfoReq00 = CommonUtils.switchClass(GetDevMalfInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    baseRes = deviceMalfIService.GetBaseRes(getDevMalfInfoReq00);
                    baseRes.getBody().setMsgCode("0x00000");
                    baseRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    baseResB = new BaseResB();
                    baseResB.setMsgCode(e.getMsgCode());
                    baseResB.setMsgDes(e.getMsgDes());
                    baseRes.setBody(baseResB);
                }
            }
        } else {
            baseResB = new BaseResB();
            baseResB.setMsgCode("MG0006F");
            baseResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            baseRes.setBody(baseResB);
        }

        baseRes.setStatus("00");
        return baseRes;
    }

    //保存
    @RequestMapping(value = "/SaveDevMalfInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveFaInfo(SaveReq saveReq) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveDevMalfInfoReq00 saveDevMalfInfoReq00 = CommonUtils.switchClass(SaveDevMalfInfoReq00.class, saveReq.getBusData());

            try {
                baseRes = deviceMalfIService.AddBaseRes(saveDevMalfInfoReq00);
                baseResB = new BaseResB();
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("成功");
                baseRes.setBody(baseResB);
            } catch (SystemException e) {
                baseResB = new BaseResB();
                baseResB.setMsgCode(e.getMsgCode());
                baseResB.setMsgDes(e.getMsgDes());
                baseRes.setBody(baseResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveDevMalfInfoReq01 saveDevMalfInfoReq01 = CommonUtils.switchClass(SaveDevMalfInfoReq01.class, saveReq.getBusData());

            try {
                baseRes = deviceMalfIService.RmBaseRes(saveDevMalfInfoReq01);
                baseResB = new BaseResB();
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("成功");
                baseRes.setBody(baseResB);
            } catch (SystemException e) {
                baseResB = new BaseResB();
                baseResB.setMsgCode(e.getMsgCode());
                baseResB.setMsgDes(e.getMsgDes());
                baseRes.setBody(baseResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveDevMalfInfoReq02 saveDevMalfInfoReq02 = CommonUtils.switchClass(SaveDevMalfInfoReq02.class, saveReq.getBusData());

            try {
                baseRes = deviceMalfIService.ModBaseRes(saveDevMalfInfoReq02);
                baseResB = new BaseResB();
                baseResB.setMsgCode("0x00000");
                baseResB.setMsgDes("成功");
                baseRes.setBody(baseResB);
            } catch (SystemException e) {
                baseResB = new BaseResB();
                baseResB.setMsgCode(e.getMsgCode());
                baseResB.setMsgDes(e.getMsgDes());
                baseRes.setBody(baseResB);
            }
        } else {
            baseResB.setMsgCode("MG0006F");
            baseResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            baseRes.setBody(baseResB);
        }
        baseRes.setStatus("00");
        return baseRes;
    }
}
