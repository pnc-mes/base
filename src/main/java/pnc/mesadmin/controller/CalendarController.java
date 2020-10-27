package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCalendarInfo.GetAllCalendarInfoRes;
import pnc.mesadmin.dto.GetAllCalendarInfo.GetAllCalendarInfoResB;
import pnc.mesadmin.dto.GetCalendarInfo.GetCalendarInfoReq00;
import pnc.mesadmin.dto.GetCalendarInfo.GetCalendarInfoRes;
import pnc.mesadmin.dto.GetCalendarInfo.GetCalendarInfoResB;
import pnc.mesadmin.dto.SaveCalendarInfo.*;
import pnc.mesadmin.service.CalendarIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:27
 * @Description:
 */
@Controller
@RequestMapping("/Calendar")
@Scope("prototype")
public class CalendarController {
    @Resource
    private CalendarIService calendarIService;

    //页面返回
    @RequestMapping(value = "/CalendarPG",method = RequestMethod.GET)
    public String calendarHome(){
        return "base/calendar/calendarinfo";
    }

    //查询所有
    @RequestMapping(value = "/GetAllCalendarsInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllCalendarInfoRes GetAllCalendarInfo(GetAllReq getAllReq) {
        GetAllCalendarInfoRes getAllCalendarInfoRes=null;
        GetAllCalendarInfoResB getAllCalendarInfoResB=null;
        if("00".equals(getAllReq.getExecType())) {
            getAllCalendarInfoRes=new GetAllCalendarInfoRes();
            getAllCalendarInfoResB=new GetAllCalendarInfoResB();
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
                getAllCalendarInfoRes = calendarIService.QALLGetAllCalendarInfo(objEInitDataFields,pageInfo);
                getAllCalendarInfoRes.getBody().setMsgCode("0x00000");
                getAllCalendarInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                getAllCalendarInfoResB=new GetAllCalendarInfoResB();
                getAllCalendarInfoResB.setMsgCode(e.getMsgCode());
                getAllCalendarInfoResB.setMsgDes(e.getMsgDes());
                getAllCalendarInfoRes.setBody(getAllCalendarInfoResB);
            }
        }
        else{
            getAllCalendarInfoResB=new GetAllCalendarInfoResB();
            getAllCalendarInfoResB.setMsgCode("MG0006F");
            getAllCalendarInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            getAllCalendarInfoRes.setBody(getAllCalendarInfoResB);
        }
        getAllCalendarInfoRes.setStatus("00");
        return getAllCalendarInfoRes;
    }

    //查询单个
    @RequestMapping(value = "/GetCalendarInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCalendarInfoRes GetCalendarInfo(GetAllReq getAllReq) {
        GetCalendarInfoRes getCalendarInfoRes=null;
        GetCalendarInfoResB getCalendarInfoResB=null;
        if ("00".equals(getAllReq.getExecType())) {
            getCalendarInfoRes=new GetCalendarInfoRes();
            getCalendarInfoResB=new GetCalendarInfoResB();
            GetCalendarInfoReq00 getCalendarInfoReq00 = CommonUtils.switchClass(GetCalendarInfoReq00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getCalendarInfoRes = calendarIService.GetGetCalendarInfoRes(getCalendarInfoReq00);
                    getCalendarInfoRes.getBody().setMsgCode("0x00000");
                    getCalendarInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    getCalendarInfoResB=new GetCalendarInfoResB();
                    getCalendarInfoResB.setMsgCode(e.getMsgCode());
                    getCalendarInfoResB.setMsgDes(e.getMsgDes());
                    getCalendarInfoRes.setBody(getCalendarInfoResB);
                }
            }
        } else {
            getCalendarInfoResB=new GetCalendarInfoResB();
            getCalendarInfoResB.setMsgCode("MG0006F");
            getCalendarInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getCalendarInfoRes.setBody(getCalendarInfoResB);
        }

        getCalendarInfoRes.setStatus("00");
        return getCalendarInfoRes;
    }

    //保存
    @RequestMapping(value = "/SaveCalendarInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCalendarInfoRes SaveCalendarInfo(SaveReq saveReq){
        SaveCalendarInfoRes saveCalendarInfoRes=null;
        SaveCalendarInfoResB saveCalendarInfoResB=null;
        if ("00".equals(saveReq.getExecType())) {
            saveCalendarInfoRes=new SaveCalendarInfoRes();
            saveCalendarInfoResB=new SaveCalendarInfoResB();

            SaveCalendarInfoReq00 saveCalendarInfoReq00 = CommonUtils.switchClass(SaveCalendarInfoReq00.class, saveReq.getBusData());

            try {
                saveCalendarInfoRes =calendarIService.AddSaveCalendarInfoReq00(saveCalendarInfoReq00);

                saveCalendarInfoResB.setMsgCode("0x00000");
                saveCalendarInfoResB.setMsgDes("成功");
                saveCalendarInfoRes.setBody(saveCalendarInfoResB);
            } catch (SystemException e) {
                saveCalendarInfoResB.setMsgCode(e.getMsgCode());
                saveCalendarInfoResB.setMsgDes(e.getMsgDes());
                saveCalendarInfoRes.setBody(saveCalendarInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            saveCalendarInfoRes=new SaveCalendarInfoRes();
            saveCalendarInfoResB=new SaveCalendarInfoResB();
            SaveCalendarInfoReq01 saveCalendarInfoReq01 = CommonUtils.switchClass(SaveCalendarInfoReq01.class, saveReq.getBusData());

            try {
                saveCalendarInfoRes = calendarIService.RomSaveCalendarInfoReq01(saveCalendarInfoReq01);
                saveCalendarInfoResB.setMsgCode("0x00000");
                saveCalendarInfoResB.setMsgDes("成功");
                saveCalendarInfoRes.setBody(saveCalendarInfoResB);
            } catch (SystemException e) {
                saveCalendarInfoResB.setMsgCode(e.getMsgCode());
                saveCalendarInfoResB.setMsgDes(e.getMsgDes());
                saveCalendarInfoRes.setBody(saveCalendarInfoResB);
            }

        } else if ("02".equals(saveReq.getExecType())) {
            saveCalendarInfoRes=new SaveCalendarInfoRes();
            saveCalendarInfoResB=new SaveCalendarInfoResB();
            SaveCalendarInfoReq02 saveCalendarInfoReq02= CommonUtils.switchClass(SaveCalendarInfoReq02.class, saveReq.getBusData());

            try {
                saveCalendarInfoRes = calendarIService.ModSaveCalendarInfoReq02(saveCalendarInfoReq02);

                saveCalendarInfoResB.setMsgCode("0x00000");
                saveCalendarInfoResB.setMsgDes("成功");
                saveCalendarInfoRes.setBody(saveCalendarInfoResB);
            } catch (SystemException e) {

                saveCalendarInfoResB.setMsgCode(e.getMsgCode());
                saveCalendarInfoResB.setMsgDes(e.getMsgDes());
                saveCalendarInfoRes.setBody(saveCalendarInfoResB);
            }
        } else {
            saveCalendarInfoResB.setMsgCode("MG0006F");
            saveCalendarInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveCalendarInfoRes.setBody(saveCalendarInfoResB);
        }
        saveCalendarInfoRes.setStatus("00");

        return saveCalendarInfoRes;
    }
}
