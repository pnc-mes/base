package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCDataInfo.GetAllCDataInfoRes;
import pnc.mesadmin.dto.GetAllCDataInfo.GetAllCDataInfoResB;
import pnc.mesadmin.dto.GetCDataInfo.GetCDataInfoReq00;
import pnc.mesadmin.dto.GetCDataInfo.GetCDataInfoRes;
import pnc.mesadmin.dto.GetCDataInfo.GetCDataInfoResB;
import pnc.mesadmin.dto.SaveCDataInfo.*;
import pnc.mesadmin.service.CusDataIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:22
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/CusData")
public class CusDataController {
    @Resource
    private CusDataIService cusDataIService;

    @RequestMapping(value = "/CusDataPG")
    public String home(){
        return "currency/cusData/cusDatainfo";
    }


    //列表
    @RequestMapping(value = "/GetAllCDataInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCDataInfoRes GetAllCDataInfo(GetAllReq getAllReq) {
        GetAllCDataInfoRes getAllCDataInfoRes = new GetAllCDataInfoRes();
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
                getAllCDataInfoRes = cusDataIService.QALLGetAllCDataInfoRes(objEInitDataFields, pageInfo);
                getAllCDataInfoRes.getBody().setMsgCode("0x00000");
                getAllCDataInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCDataInfoResB getAllCDataInfoResB = new GetAllCDataInfoResB();
                getAllCDataInfoResB.setMsgCode(e.getMsgCode());
                getAllCDataInfoResB.setMsgDes(e.getMsgDes());
                getAllCDataInfoRes.setBody(getAllCDataInfoResB);
            }

        } else {
            GetAllCDataInfoResB getAllCDataInfoResB = new GetAllCDataInfoResB();
            getAllCDataInfoResB.setMsgCode("MG0006F");
            getAllCDataInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllCDataInfoRes.setBody(getAllCDataInfoResB);
        }
        getAllCDataInfoRes.setStatus("00");
        return getAllCDataInfoRes;
    }

    //单个
    @RequestMapping(value = "/GetCDataInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetCDataInfoRes GetCDataInfo(GetAllReq getAllReq) {
        GetCDataInfoRes getCDataInfoRes = new GetCDataInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            //讲传过来的字符串转换成对象

            GetCDataInfoReq00 getCDataInfoReq00 = CommonUtils.switchClass(GetCDataInfoReq00.class, getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null) {


            } else {  // 不分页
                try {
                    getCDataInfoRes = cusDataIService.GetGetCDataInfoRes(getCDataInfoReq00);
                    getCDataInfoRes.getBody().setMsgCode("0x00000");
                    getCDataInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetCDataInfoResB getCDataInfoResB = new GetCDataInfoResB();
                    getCDataInfoResB.setMsgCode(e.getMsgCode());
                    getCDataInfoResB.setMsgDes(e.getMsgDes());
                    getCDataInfoRes.setBody(getCDataInfoResB);
                }
            }
        } else {
            GetCDataInfoResB getCDataInfoResB = new GetCDataInfoResB();
            getCDataInfoResB.setMsgCode("MG0006F");
            getCDataInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getCDataInfoRes.setBody(getCDataInfoResB);
        }
        getCDataInfoRes.setStatus("00");
        return getCDataInfoRes;
    }

    //保存
    @RequestMapping(value = "/SaveCDataInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCDataInfoRes SaveCDataInfo(SaveReq saveReq) {
        SaveCDataInfoRes saveCDataInfoRes = new SaveCDataInfoRes();
        SaveCDataInfoResB saveCDataInfoResB = new SaveCDataInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveCDataInfoReq00 saveCDataInfoReq00 = CommonUtils.switchClass(SaveCDataInfoReq00.class, saveReq.getBusData());


            try {
                saveCDataInfoRes = cusDataIService.AddSaveCDataInfoRes(saveCDataInfoReq00);
                saveCDataInfoRes.getBody().setMsgCode("0x00000");
                saveCDataInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveCDataInfoResB = new SaveCDataInfoResB();
                saveCDataInfoResB.setMsgCode(e.getMsgCode());
                saveCDataInfoResB.setMsgDes(e.getMsgDes());
                saveCDataInfoRes.setBody(saveCDataInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveCDataInfoReq01 saveCDataInfoReq01 = CommonUtils.switchClass(SaveCDataInfoReq01.class, saveReq.getBusData());

            try {
                saveCDataInfoRes = cusDataIService.RmSaveCDataInfoRes(saveCDataInfoReq01);
                saveCDataInfoResB = new SaveCDataInfoResB();
                saveCDataInfoResB.setMsgCode("0x00000");
                saveCDataInfoResB.setMsgDes("成功");
                saveCDataInfoRes.setBody(saveCDataInfoResB);
            } catch (SystemException e) {
                saveCDataInfoResB = new SaveCDataInfoResB();
                saveCDataInfoResB.setMsgCode(e.getMsgCode());
                saveCDataInfoResB.setMsgDes(e.getMsgDes());
                saveCDataInfoRes.setBody(saveCDataInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveCDataInfoReq02 saveCDataInfoReq02 = CommonUtils.switchClass(SaveCDataInfoReq02.class, saveReq.getBusData());
            try {
                saveCDataInfoRes = cusDataIService.ModSaveCDataInfoRes(saveCDataInfoReq02);
                saveCDataInfoResB = new SaveCDataInfoResB();
                saveCDataInfoResB.setMsgCode("0x00000");
                saveCDataInfoResB.setMsgDes("成功");
                saveCDataInfoRes.setBody(saveCDataInfoResB);
            } catch (SystemException e) {
                saveCDataInfoResB = new SaveCDataInfoResB();
                saveCDataInfoResB.setMsgCode(e.getMsgCode());
                saveCDataInfoResB.setMsgDes(e.getMsgDes());
                saveCDataInfoRes.setBody(saveCDataInfoResB);
            }
        }  else {
            saveCDataInfoResB.setMsgCode("MG0006F");
            saveCDataInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveCDataInfoRes.setBody(saveCDataInfoResB);
        }
        saveCDataInfoRes.setStatus("00");
        return saveCDataInfoRes;
    }
}
