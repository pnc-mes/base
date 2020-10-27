package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMaxTimeInfo.GetAllMaxTimeInfoRes;
import pnc.mesadmin.dto.GetAllMaxTimeInfo.GetAllMaxTimeInfoResB;
import pnc.mesadmin.dto.GetMaxTimeInfo.GetMaxTimeInfoReq00;
import pnc.mesadmin.dto.GetMaxTimeInfo.GetMaxTimeInfoRes;
import pnc.mesadmin.dto.GetMaxTimeInfo.GetMaxTimeInfoResB;
import pnc.mesadmin.dto.SaveMaxTimeInfo.*;
import pnc.mesadmin.service.MaxTimeWindowIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:51
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/MTW")
public class MaxTimeWindowController {

    @Resource
    private MaxTimeWindowIService maxTimeWindowIService;

    //返回页面
    @RequestMapping(value = "/MaxTimeWindowPG")
    public String MaxTimeHome(){
        return "base/maxtimewindow/maxtimewindowinfo";
    }

    //查询列表
    @RequestMapping(value = "/GetAllMaxTimeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMaxTimeInfoRes QALLGetAllMaxTimeInfoRes(GetAllReq getAllReq) {
        GetAllMaxTimeInfoRes getAllMaxTimeInfoRes=new GetAllMaxTimeInfoRes();
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
                getAllMaxTimeInfoRes =maxTimeWindowIService.QALLGetAllMaxTimeInfoRes(initDataFields,pageInfo);
                getAllMaxTimeInfoRes.getBody().setMsgCode("0x00000");
                getAllMaxTimeInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllMaxTimeInfoResB getAllMaxTimeInfoResB = new GetAllMaxTimeInfoResB();
                getAllMaxTimeInfoResB.setMsgCode(e.getMsgCode());
                getAllMaxTimeInfoResB.setMsgDes(e.getMsgDes());
                getAllMaxTimeInfoRes.setBody(getAllMaxTimeInfoResB);
            }

        } else {
            GetAllMaxTimeInfoResB getAllMaxTimeInfoResB = new GetAllMaxTimeInfoResB();
            getAllMaxTimeInfoResB.setMsgCode("MG0006F");
            getAllMaxTimeInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllMaxTimeInfoRes.setBody(getAllMaxTimeInfoResB);
        }
        getAllMaxTimeInfoRes.setStatus("00");
        return getAllMaxTimeInfoRes;
    }

    //查询单个
    @RequestMapping(value = "/GetMaxTimeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMaxTimeInfoRes GetGetMaxTimeInfoRes(GetAllReq getAllReq) {
        GetMaxTimeInfoRes getMaxTimeInfoRes=new GetMaxTimeInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetMaxTimeInfoReq00 getMaxTimeInfoReq00 = CommonUtils.switchClass(GetMaxTimeInfoReq00.class, getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null) {


            } else {  // 不分页
                try {
                    getMaxTimeInfoRes =maxTimeWindowIService.GetGetMaxTimeInfoRes(getMaxTimeInfoReq00);
                    getMaxTimeInfoRes.getBody().setMsgCode("0x00000");
                    getMaxTimeInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetMaxTimeInfoResB getMaxTimeInfoResB = new GetMaxTimeInfoResB();
                    getMaxTimeInfoResB.setMsgCode(e.getMsgCode());
                    getMaxTimeInfoResB.setMsgDes(e.getMsgDes());
                    getMaxTimeInfoRes.setBody(getMaxTimeInfoResB);
                }
            }
        } else {
            GetMaxTimeInfoResB getMaxTimeInfoResB = new GetMaxTimeInfoResB();
            getMaxTimeInfoResB.setMsgCode("MG0006F");
            getMaxTimeInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getMaxTimeInfoRes.setBody(getMaxTimeInfoResB);
        }
        getMaxTimeInfoRes.setStatus("00");
        return  getMaxTimeInfoRes;
    }

    //保存信息
    @RequestMapping(value = "/SaveMaxTimeInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveMaxTimeInfoRes SaveMaxTimeInfoRes(SaveReq saveReq){
        SaveMaxTimeInfoRes saveMaxTimeInfoRes=new SaveMaxTimeInfoRes();
        SaveMaxTimeInfoResB saveMaxTimeInfoResB=new SaveMaxTimeInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveMaxTimeInfoReq00 saveMaxTimeInfoReq00 = CommonUtils.switchClass(SaveMaxTimeInfoReq00.class, saveReq.getBusData());

            try {
                saveMaxTimeInfoRes = maxTimeWindowIService.AddSaveMaxTimeInfoRes(saveMaxTimeInfoReq00);
                saveMaxTimeInfoRes.getBody().setMsgCode("0x00000");
                saveMaxTimeInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveMaxTimeInfoResB.setMsgCode(e.getMsgCode());
                saveMaxTimeInfoResB.setMsgDes(e.getMsgDes());
                saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveMaxTimeInfoReq01 saveMaxTimeInfoReq01 = CommonUtils.switchClass(SaveMaxTimeInfoReq01.class, saveReq.getBusData());

            try {
                saveMaxTimeInfoRes = maxTimeWindowIService.RmSaveMaxTimeInfoRes(saveMaxTimeInfoReq01);
                saveMaxTimeInfoResB=new SaveMaxTimeInfoResB();
                saveMaxTimeInfoResB.setMsgCode("0x00000");
                saveMaxTimeInfoResB.setMsgDes("成功");
                saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
            } catch (SystemException e) {

                saveMaxTimeInfoResB.setMsgCode(e.getMsgCode());
                saveMaxTimeInfoResB.setMsgDes(e.getMsgDes());
                saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveMaxTimeInfoReq02 saveMaxTimeInfoReq02 = CommonUtils.switchClass(SaveMaxTimeInfoReq02.class, saveReq.getBusData());
            try {
                saveMaxTimeInfoRes =maxTimeWindowIService.ModSaveMaxTimeInfoRes(saveMaxTimeInfoReq02);

                saveMaxTimeInfoResB.setMsgCode("0x00000");
                saveMaxTimeInfoResB.setMsgDes("成功");
                saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
            } catch (SystemException e) {
                saveMaxTimeInfoResB.setMsgCode(e.getMsgCode());
                saveMaxTimeInfoResB.setMsgDes(e.getMsgDes());
                saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
            }
        } else {
            saveMaxTimeInfoResB.setMsgCode("MG0006F");
            saveMaxTimeInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveMaxTimeInfoRes.setBody(saveMaxTimeInfoResB);
        }
        saveMaxTimeInfoRes.setStatus("00");
        return saveMaxTimeInfoRes;
    }
}
