package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCTInfo.GetAllCTInfoRes;
import pnc.mesadmin.dto.GetAllCTInfo.GetAllCTInfoResB;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoReq00;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoRes;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoResB;
import pnc.mesadmin.dto.SaveCTInfo.*;
import pnc.mesadmin.service.CoTypeIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:55
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/CoType")
public class CoTypeController {

    @Resource
    private CoTypeIService coTypeIService;


    @RequestMapping(value = "/CoTypePG")
    public String getcompanyinfoPage() {
        return "base/cotype/cotypeinfo";
    }

    //查询列表
    @RequestMapping(value = "/GetAllCTInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCTInfoRes GetAllCTInfo(GetAllReq getAllReq) {
        GetAllCTInfoRes getAllCTInfoRes = new GetAllCTInfoRes();
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
                getAllCTInfoRes = coTypeIService.QALLGetAllCTInfoRes(objEInitDataFields, pageInfo);
                getAllCTInfoRes.getBody().setMsgCode("0x00000");
                getAllCTInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCTInfoResB getAllCpInfoResB = new GetAllCTInfoResB();
                getAllCpInfoResB.setMsgCode(e.getMsgCode());
                getAllCpInfoResB.setMsgDes(e.getMsgDes());
                getAllCTInfoRes.setBody(getAllCpInfoResB);
            }

        } else {
            GetAllCTInfoResB getAllCpInfoResB = new GetAllCTInfoResB();
            getAllCpInfoResB.setMsgCode("MG0006F");
            getAllCpInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllCTInfoRes.setBody(getAllCpInfoResB);
        }
        getAllCTInfoRes.setStatus("00");
        return getAllCTInfoRes;
    }


    //查询单个
    @RequestMapping(value = "/GetCTInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetCTInfoRes GetGetCTInfoRes(GetAllReq getAllReq) {
        GetCTInfoRes getCTInfoRes = new GetCTInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetCTInfoReq00 getCTInfoReq00 = CommonUtils.switchClass(GetCTInfoReq00.class, getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null) {


            } else {  // 不分页
                try {
                    getCTInfoRes = coTypeIService.GetGetCTInfoRes(getCTInfoReq00);
                    getCTInfoRes.getBody().setMsgCode("0x00000");
                    getCTInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetCTInfoResB getCTInfoResB = new GetCTInfoResB();
                    getCTInfoResB.setMsgCode(e.getMsgCode());
                    getCTInfoResB.setMsgDes(e.getMsgDes());
                    getCTInfoRes.setBody(getCTInfoResB);
                }
            }
        } else {
            GetCTInfoResB getCTInfoResB = new GetCTInfoResB();
            getCTInfoResB.setMsgCode("MG0006F");
            getCTInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getCTInfoRes.setBody(getCTInfoResB);
        }
        getCTInfoRes.setStatus("00");
        return getCTInfoRes;

    }


    //保存信息
    @RequestMapping(value = "/SaveCTInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCTInfoRes SaveCTInfo(SaveReq saveReq) {
        SaveCTInfoRes saveCTInfoRes = new SaveCTInfoRes();
        SaveCTInfoResB saveCTInfoResB = new SaveCTInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveCTInfoReq00 saveCTInfoReq00 = CommonUtils.switchClass(SaveCTInfoReq00.class, saveReq.getBusData());


            try {
                saveCTInfoRes = coTypeIService.AddSaveCTInfoRes(saveCTInfoReq00);
                saveCTInfoRes.getBody().setMsgCode("0x00000");
                saveCTInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveCTInfoResB = new SaveCTInfoResB();
                saveCTInfoResB.setMsgCode(e.getMsgCode());
                saveCTInfoResB.setMsgDes(e.getMsgDes());
                saveCTInfoRes.setBody(saveCTInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveCTInfoReq01 saveCTInfoReq01 = CommonUtils.switchClass(SaveCTInfoReq01.class, saveReq.getBusData());

            try {
                saveCTInfoRes = coTypeIService.RmSaveCTInfoRes(saveCTInfoReq01);
                saveCTInfoResB = new SaveCTInfoResB();
                saveCTInfoResB.setMsgCode("0x00000");
                saveCTInfoResB.setMsgDes("成功");
                saveCTInfoRes.setBody(saveCTInfoResB);
            } catch (SystemException e) {
                saveCTInfoResB = new SaveCTInfoResB();
                saveCTInfoResB.setMsgCode(e.getMsgCode());
                saveCTInfoResB.setMsgDes(e.getMsgDes());
                saveCTInfoRes.setBody(saveCTInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveCTInfoReq02 saveCTInfoReq02 = CommonUtils.switchClass(SaveCTInfoReq02.class, saveReq.getBusData());
            try {
                saveCTInfoRes = coTypeIService.ModSaveCTInfoRes(saveCTInfoReq02);
                saveCTInfoResB = new SaveCTInfoResB();
                saveCTInfoResB.setMsgCode("0x00000");
                saveCTInfoResB.setMsgDes("成功");
                saveCTInfoRes.setBody(saveCTInfoResB);
            } catch (SystemException e) {
                saveCTInfoResB = new SaveCTInfoResB();
                saveCTInfoResB.setMsgCode(e.getMsgCode());
                saveCTInfoResB.setMsgDes(e.getMsgDes());
                saveCTInfoRes.setBody(saveCTInfoResB);
            }
        } else {
            saveCTInfoResB.setMsgCode("MG0006F");
            saveCTInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveCTInfoRes.setBody(saveCTInfoResB);
        }
        saveCTInfoRes.setStatus("00");
        return saveCTInfoRes;
    }
}
