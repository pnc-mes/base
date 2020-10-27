package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMaPerionInfo.GetAllMaPerionInfoRes;
import pnc.mesadmin.dto.GetAllMaPerionInfo.GetAllMaPerionInfoResB;
import pnc.mesadmin.dto.GetMaPerionInfo.GetMaPerionInfoReq00;
import pnc.mesadmin.dto.GetMaPerionInfo.GetMaPerionInfoRes;
import pnc.mesadmin.dto.GetMaPerionInfo.GetMaPerionInfoResB;
import pnc.mesadmin.dto.SaveMaPerionInfo.*;
import pnc.mesadmin.service.MaPerionIService;
import pnc.mesadmin.utils.CommonUtils;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 郝赞 on 2018/9/6.
 * 物料用料管控时间
 */
@Controller
@Scope("prototype")
@RequestMapping("/MaPerion")
public class MaPerionController {
    @Resource
    private MaPerionIService maPerionIService;
    //返回物料用料管控时间页面
    @RequestMapping(value = "/MaPerionPG")
    public String MaxTimeHome(){
        return "base/MaPerion/MaPerioninfo";
    }

    //获取在线物料有效期列表信息
    @RequestMapping(value = "/GetAllMaPerionInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMaPerionInfoRes GetAllMaPerionInfo(GetAllReq getAllReq) {
        GetAllMaPerionInfoRes getAllMaPerionInfoRes=new GetAllMaPerionInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            List<InitDataField> initDataFields = null;
            PageInfo pageInfo = null;

            if (getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if (objEInitData != null) {
                    initDataFields = objEInitData.getFiledList();
                }
            }

            if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }
            try {
                getAllMaPerionInfoRes =maPerionIService.GetAllMaPerionInfo(initDataFields,pageInfo);
                getAllMaPerionInfoRes.getBody().setMsgCode("0x00000");
                getAllMaPerionInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllMaPerionInfoResB getAllMaPerionInfoResB = new GetAllMaPerionInfoResB();
                getAllMaPerionInfoResB.setMsgCode(e.getMsgCode());
                getAllMaPerionInfoResB.setMsgDes(e.getMsgDes());
                getAllMaPerionInfoRes.setBody(getAllMaPerionInfoResB);
            }

        } else {
            GetAllMaPerionInfoResB getAllMaPerionInfoResB = new GetAllMaPerionInfoResB();
            getAllMaPerionInfoResB.setMsgCode("MG0006F");
            getAllMaPerionInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllMaPerionInfoRes.setBody(getAllMaPerionInfoResB);
        }
        getAllMaPerionInfoRes.setStatus("00");
        return getAllMaPerionInfoRes;
    }

    //获取在线物料有效期信息
    @RequestMapping(value = "/GetMaPerionInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMaPerionInfoRes GetMaPerionInfoRes(GetAllReq getAllReq) {
        GetMaPerionInfoRes getMaPerionInfoRes=new GetMaPerionInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetMaPerionInfoReq00 getMaPerionInfoReq00 = CommonUtils.switchClass(GetMaPerionInfoReq00.class, getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null) {


            } else {  // 不分页
                try {
                    getMaPerionInfoRes =maPerionIService.GetMaPerionInfo(getMaPerionInfoReq00);
                    getMaPerionInfoRes.getBody().setMsgCode("0x00000");
                    getMaPerionInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetMaPerionInfoResB getMaPerionInfoResB = new GetMaPerionInfoResB();
                    getMaPerionInfoResB.setMsgCode(e.getMsgCode());
                    getMaPerionInfoResB.setMsgDes(e.getMsgDes());
                    getMaPerionInfoRes.setBody(getMaPerionInfoResB);
                }
            }
        } else {
            GetMaPerionInfoResB getMaPerionInfoResB = new GetMaPerionInfoResB();
            getMaPerionInfoResB.setMsgCode("MG0006F");
            getMaPerionInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getMaPerionInfoRes.setBody(getMaPerionInfoResB);
        }
        getMaPerionInfoRes.setStatus("00");
        return  getMaPerionInfoRes;
    }

    //保存在线物料有效期信息
    @RequestMapping(value = "/SaveMaPerionInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveMaPerionInfoRes SaveMaPerionInfo(SaveReq saveReq){
        SaveMaPerionInfoRes saveMaPerionInfoRes=new SaveMaPerionInfoRes();
        SaveMaPerionInfoResB saveMaPerionInfoResB=new SaveMaPerionInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveMaPerionInfoReq00 saveMaPerionInfoReq00 = CommonUtils.switchClass(SaveMaPerionInfoReq00.class, saveReq.getBusData());

            try {
                saveMaPerionInfoRes = maPerionIService.AddSaveMaPerionInfo(saveMaPerionInfoReq00);
                saveMaPerionInfoRes.getBody().setMsgCode("0x00000");
                saveMaPerionInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveMaPerionInfoResB.setMsgCode(e.getMsgCode());
                saveMaPerionInfoResB.setMsgDes(e.getMsgDes());
                saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveMaPerionInfoReq01 saveMaPerionInfoReq01 = CommonUtils.switchClass(SaveMaPerionInfoReq01.class, saveReq.getBusData());

            try {
                saveMaPerionInfoRes = maPerionIService.RmSaveMaPerionInfo(saveMaPerionInfoReq01);
                saveMaPerionInfoResB=new SaveMaPerionInfoResB();
                saveMaPerionInfoResB.setMsgCode("0x00000");
                saveMaPerionInfoResB.setMsgDes("成功");
                saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
            } catch (SystemException e) {

                saveMaPerionInfoResB.setMsgCode(e.getMsgCode());
                saveMaPerionInfoResB.setMsgDes(e.getMsgDes());
                saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveMaPerionInfoReq02 saveMaPerionInfoReq02 = CommonUtils.switchClass(SaveMaPerionInfoReq02.class, saveReq.getBusData());
            try {
                saveMaPerionInfoRes =maPerionIService.ModSaveMaPerionInfo(saveMaPerionInfoReq02);

                saveMaPerionInfoResB.setMsgCode("0x00000");
                saveMaPerionInfoResB.setMsgDes("成功");
                saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
            } catch (SystemException e) {
                saveMaPerionInfoResB.setMsgCode(e.getMsgCode());
                saveMaPerionInfoResB.setMsgDes(e.getMsgDes());
                saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
            }
        } else {
            saveMaPerionInfoResB.setMsgCode("MG0006F");
            saveMaPerionInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveMaPerionInfoRes.setBody(saveMaPerionInfoResB);
        }
        saveMaPerionInfoRes.setStatus("00");
        return saveMaPerionInfoRes;
    }


}
