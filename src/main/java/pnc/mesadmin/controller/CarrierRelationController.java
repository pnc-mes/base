package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoReq00;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoRes;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoResB;
import pnc.mesadmin.dto.SaveCarrierRelationInfo.*;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.CarrierRelationIService;
import pnc.mesadmin.service.impl.CarrierRelationService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:53
 * @Description:
 */
@Controller
@RequestMapping("/CarrierRelation")
public class    CarrierRelationController {
    @Resource
    private CarrierRelationIService carrierRelationIService;

    //上下载具页面
    @RequestMapping(value = "/CarrierRelationPG")
    public String getCarrierRelationPGPGPage(){
        return "mprocess/carrierrelation/carrierrelation";
    }

    //上载具
    @RequestMapping(value = "/CLoadPG")
    public String getCarrierLoadPGPGPage(){
        return "mprocess/carrierload/carrierloadInfo";
    }

    //下载具
    @RequestMapping(value = "/CUnPG")
    public String getCarrierRunLoadPGPGPage(){
        return "mprocess/carrierunload/carrierunloadInfo";
    }
    //保存
    @ResponseBody
    @RequestMapping(value = "/SaveCarrierRelationInfo",method = RequestMethod.POST)
    public SaveCarrierRelationInfoRes AddCarrierRelationInfoRes(SaveReq saveReq) {
        SaveCarrierRelationInfoRes saveCarrierRelationInfoRes = new SaveCarrierRelationInfoRes();
        SaveCarrierRelationInfoResB saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveCarrierRelationInfoReq00 saveCarrierRelationInfoReq00 = CommonUtils.switchClass(SaveCarrierRelationInfoReq00.class, saveReq.getBusData());


            try {
                saveCarrierRelationInfoRes = carrierRelationIService.AddCarrierRelationInfoRes(saveCarrierRelationInfoReq00);
                saveCarrierRelationInfoRes.getBody().setMsgCode("0x00000");
                saveCarrierRelationInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode(e.getMsgCode());
                saveCarrierRelationInfoResB.setMsgDes(e.getMsgDes());
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveCarrierRelationInfoReq01 saveCarrierRelationInfoReq01 = CommonUtils.switchClass(SaveCarrierRelationInfoReq01.class, saveReq.getBusData());

            try {
                saveCarrierRelationInfoRes = carrierRelationIService.RmSaveCarrierRelationInfoRes(saveCarrierRelationInfoReq01);
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode("0x00000");
                saveCarrierRelationInfoResB.setMsgDes("成功");
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            } catch (SystemException e) {
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode(e.getMsgCode());
                saveCarrierRelationInfoResB.setMsgDes(e.getMsgDes());
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveCarrierRelationInfoReq02 saveCarrierRelationInfoReq02 = CommonUtils.switchClass(SaveCarrierRelationInfoReq02.class, saveReq.getBusData());

            try {
                saveCarrierRelationInfoRes = carrierRelationIService.RmSaveCarrierRelationInfo(saveCarrierRelationInfoReq02);
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode("0x00000");
                saveCarrierRelationInfoResB.setMsgDes("成功");
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            } catch (SystemException e) {
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode(e.getMsgCode());
                saveCarrierRelationInfoResB.setMsgDes(e.getMsgDes());
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            }
        }else if("03".equals(saveReq.getExecType())){
            SaveCarrierRelationInfoReq03 saveCarrierRelationInfoReq03=CommonUtils.switchClass(SaveCarrierRelationInfoReq03.class, saveReq.getBusData());

            try {
                saveCarrierRelationInfoRes = carrierRelationIService.RmSaveCarrierRelationInfo01(saveCarrierRelationInfoReq03);
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode("0x00000");
                saveCarrierRelationInfoResB.setMsgDes("成功");
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            } catch (SystemException e) {
                saveCarrierRelationInfoResB = new SaveCarrierRelationInfoResB();
                saveCarrierRelationInfoResB.setMsgCode(e.getMsgCode());
                saveCarrierRelationInfoResB.setMsgDes(e.getMsgDes());
                saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
            }

        }else {
            saveCarrierRelationInfoResB.setMsgCode("MG0006F");
            saveCarrierRelationInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01");
            saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
        }
        saveCarrierRelationInfoRes.setStatus("00");
        return saveCarrierRelationInfoRes;
    }
    //根据
    @ResponseBody
    @RequestMapping(value = "/GetCarrierRelationInfo",method = RequestMethod.POST)
    public GetCarrierRelationInfoRes QALLGetCarrierRelationInfoRes(GetAllReq getAllReq) {
        GetCarrierRelationInfoRes getCarrierRelationInfoRes=new GetCarrierRelationInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetCarrierRelationInfoReq00 getCarrierRelationInfoReq00 = CommonUtils.switchClass(GetCarrierRelationInfoReq00.class, getAllReq.getBusData());

            try {
                getCarrierRelationInfoRes = carrierRelationIService.QALLGetCarrierRelationInfoRes(getCarrierRelationInfoReq00);
                getCarrierRelationInfoRes.getBody().setMsgCode("0x00000");
                getCarrierRelationInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetCarrierRelationInfoResB getCarrierRelationInfoResB = new GetCarrierRelationInfoResB();
                getCarrierRelationInfoResB.setMsgCode(e.getMsgCode());
                getCarrierRelationInfoResB.setMsgDes(e.getMsgDes());
                getCarrierRelationInfoRes.setBody(getCarrierRelationInfoResB);
            }
        }
        else {
            GetCarrierRelationInfoResB getCarrierRelationInfoResB = new GetCarrierRelationInfoResB();
            getCarrierRelationInfoResB.setMsgCode("MG0006F");
            getCarrierRelationInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            getCarrierRelationInfoRes.setBody(getCarrierRelationInfoResB);
        }

        getCarrierRelationInfoRes.setStatus("00");
        return getCarrierRelationInfoRes;
    }
}
