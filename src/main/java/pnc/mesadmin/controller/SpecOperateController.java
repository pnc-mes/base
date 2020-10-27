package pnc.mesadmin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.SaveIOSInfo.*;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.SpecOperateIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：车间管理控制器
 * 创建人：潘俊峰
 * 创建时间：2020-09-16
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/SpecOperate")
public class SpecOperateController {

    @Resource
    private SpecOperateIService specOperateIService;

    /**
     * 获取工序批次信息
     * @param batch
     * @return
     */
    @RequestMapping(value = "/GetSpecBNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetSpecBInfo(String batch){
        try {
            return BaseResponse.success(specOperateIService.GetSpecBInfo(batch));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //进站出站操作
    @RequestMapping(value = "/GetIOSInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveIOSInfoRes SaveIOSInfo(SaveReq saveReq){
        SaveIOSInfoRes objESaveIOSInfoRes = new SaveIOSInfoRes();
        SaveIOSInfoResB objESaveIOSInfoResB = new SaveIOSInfoResB();

        String busData = saveReq.getBusData();

        if("00".equals(saveReq.getExecType())){
            //入站
            SaveIOSInfoReqBD00 objESaveIOSInfoReqBD00 = CommonUtils.switchClass(SaveIOSInfoReqBD00.class, busData);

            try {
                objESaveIOSInfoRes = specOperateIService.AddInput(objESaveIOSInfoReqBD00);
                objESaveIOSInfoResB.setMsgCode("0x00000");
                objESaveIOSInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveIOSInfoResB.setMsgCode(e.getMsgCode());
                objESaveIOSInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("01".equals(saveReq.getExecType())){
            //出站
            SaveIOSInfoReqBD01 objESaveIOSInfoReqBD01 = CommonUtils.switchClass(SaveIOSInfoReqBD01.class, busData);

            try {
                objESaveIOSInfoRes = specOperateIService.AddOutput(objESaveIOSInfoReqBD01);
                objESaveIOSInfoResB = objESaveIOSInfoRes.getBody();
                objESaveIOSInfoResB.setMsgCode("0x00000");
            }catch (SystemException e){
                objESaveIOSInfoResB.setMsgCode(e.getMsgCode());
                objESaveIOSInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("02".equals(saveReq.getExecType())){
            //上机
            SaveIOSInfoReqBD02 objESaveIOSInfoReqBD02 = CommonUtils.switchClass(SaveIOSInfoReqBD02.class, busData);

            try {
                objESaveIOSInfoRes = specOperateIService.AddUp(objESaveIOSInfoReqBD02);
                objESaveIOSInfoResB = objESaveIOSInfoRes.getBody();
                objESaveIOSInfoResB.setMsgCode("0x00000");
                objESaveIOSInfoResB.setMsgDes(StringUtils.isBlank(objESaveIOSInfoResB.getMsgDes()) ? "成功" : objESaveIOSInfoResB.getMsgDes());
            }catch (SystemException e){
                objESaveIOSInfoResB.setMsgCode(e.getMsgCode());
                objESaveIOSInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("03".equals(saveReq.getExecType())){
            //下机
            SaveIOSInfoReqBD03 objESaveIOSInfoReqBD03 = CommonUtils.switchClass(SaveIOSInfoReqBD03.class, busData);

            try {
                objESaveIOSInfoRes = specOperateIService.AddDown(objESaveIOSInfoReqBD03);
                objESaveIOSInfoResB = objESaveIOSInfoRes.getBody();
                objESaveIOSInfoResB.setMsgCode("0x00000");
            }catch (SystemException e){
                objESaveIOSInfoResB.setMsgCode(e.getMsgCode());
                objESaveIOSInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("04".equals(saveReq.getExecType())){
            //非标准移动
            SaveIOSInfoReqBD04[] objESaveIOSInfoReqBD04 = CommonUtils.switchClass(SaveIOSInfoReqBD04[].class, busData);

            try {
                objESaveIOSInfoRes = specOperateIService.AddMove(objESaveIOSInfoReqBD04);
                objESaveIOSInfoResB.setMsgCode("0x00000");
                objESaveIOSInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveIOSInfoResB.setMsgCode(e.getMsgCode());
                objESaveIOSInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("05".equals(saveReq.getExecType())){
            //在线重工
            SaveIOSInfoReqBD05 objESaveIOSInfoReqBD05 = CommonUtils.switchClass(SaveIOSInfoReqBD05.class, busData);

            try {
                objESaveIOSInfoRes = specOperateIService.AddRe(objESaveIOSInfoReqBD05);
                objESaveIOSInfoResB.setMsgCode("0x00000");
                objESaveIOSInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveIOSInfoResB.setMsgCode(e.getMsgCode());
                objESaveIOSInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveIOSInfoRes.setBody(objESaveIOSInfoResB);
        objESaveIOSInfoRes.setStatus("00");

        return objESaveIOSInfoRes;
    }
}
