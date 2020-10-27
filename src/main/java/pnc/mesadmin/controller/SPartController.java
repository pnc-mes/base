package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.GetSPartInfo.GetSPartInfoReq00;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq00;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq01;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq02;
import pnc.mesadmin.service.SPartIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:22
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/SPart")
public class SPartController {
    @Resource
    private SPartIService sPartIService;

    @RequestMapping("/BPBJPG")
    public String home1(){
        return "res/bpbj/bpbjinfo";
    }

    @RequestMapping("/SBGHPG")
    public String home2(){
        return "res/sbgh/sbghinfo";
    }

    @RequestMapping("/WXJLPG")
    public String home3(){
        return "res/wxjl/wxjlinfo";
    }

    //获取列表信息
    @RequestMapping(value = "/GetAllSPartInfo", method = RequestMethod.POST)
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
                baseRes = sPartIService.QALLBaseRes(objEInitDataFields, pageInfo);
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
    @RequestMapping(value = "/GetSPartInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetSPartInfo(GetAllReq getAllReq) {
        BaseRes baseRes = null;
        BaseResB baseResB = null;
        if ("00".equals(getAllReq.getExecType())) {

            GetSPartInfoReq00 getSPartInfoReq00 = CommonUtils.switchClass(GetSPartInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    baseRes = sPartIService.GetBaseRes(getSPartInfoReq00);
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
    @RequestMapping(value = "/SaveSPartInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveFaInfo(SaveReq saveReq) {
        BaseRes baseRes = new BaseRes();
        BaseResB baseResB = new BaseResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveSPartInfoReq00 saveSPartInfoReq00 = CommonUtils.switchClass(SaveSPartInfoReq00.class, saveReq.getBusData());

            try {
                baseRes = sPartIService.AddBaseRes(saveSPartInfoReq00);
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
            SaveSPartInfoReq01 saveSPartInfoReq01 = CommonUtils.switchClass(SaveSPartInfoReq01.class, saveReq.getBusData());

            try {
                baseRes = sPartIService.RmBaseRes(saveSPartInfoReq01);
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
            SaveSPartInfoReq02 saveSPartInfoReq02 = CommonUtils.switchClass(SaveSPartInfoReq02.class, saveReq.getBusData());

            try {
                baseRes = sPartIService.ModBaseRes(saveSPartInfoReq02);
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
