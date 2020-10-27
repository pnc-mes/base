package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoRes;
import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoResB;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoReq00;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoRes;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoResB;
import pnc.mesadmin.dto.SaveEmailDisInfo.*;
import pnc.mesadmin.service.EmailDisIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:34
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/EmailDis")
public class EmailDisController {
    @Resource
    private EmailDisIService emailDisIService;
    @RequestMapping(value = "/EmailDisPG",method = RequestMethod.GET)
    public String EmailDisHome(){
        return "notification/emailDis/emailDisinfo";
    }

    //获取分发列表
    @RequestMapping(value = "/GetAllEmailDisInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllEmailDisInfoRes QALLGetAllEmailDisInfo(GetAllReq getAllReq){

        GetAllEmailDisInfoRes getAllEmailDisInfoRes = new GetAllEmailDisInfoRes();
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
                getAllEmailDisInfoRes = emailDisIService.QALLGetAllEmailDisInfo(objEInitDataFields,pageInfo);
                getAllEmailDisInfoRes.getBody().setMsgCode("0x00000");
                getAllEmailDisInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllEmailDisInfoResB getAllEmailDisInfoResB = new GetAllEmailDisInfoResB();
                getAllEmailDisInfoResB.setMsgCode(e.getMsgCode());
                getAllEmailDisInfoResB.setMsgDes(e.getMsgDes());
                getAllEmailDisInfoRes.setBody(getAllEmailDisInfoResB);
            }

        } else {
            GetAllEmailDisInfoResB getAllEmailDisInfoResB = new GetAllEmailDisInfoResB();
            getAllEmailDisInfoResB.setMsgCode("MG0006F");
            getAllEmailDisInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllEmailDisInfoRes.setBody(getAllEmailDisInfoResB);
        }
        getAllEmailDisInfoRes.setStatus("00");
        return getAllEmailDisInfoRes;
    }

    //获取单个
    @RequestMapping(value = "/GetEmailDisInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetEmailDisInfoRes GetEmailDisInfo(GetAllReq getAllReq) {
        GetEmailDisInfoRes getEmailDisInfoRes=null;
        GetEmailDisInfoResB getEmailDisInfoResB=null;
        if ("00".equals(getAllReq.getExecType())) {
            getEmailDisInfoRes=new GetEmailDisInfoRes();
            getEmailDisInfoResB=new GetEmailDisInfoResB();
            GetEmailDisInfoReq00 getEmailDisInfoReq00 = CommonUtils.switchClass(GetEmailDisInfoReq00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getEmailDisInfoRes = emailDisIService.GetGetEmailDisInfoRes(getEmailDisInfoReq00);
                    getEmailDisInfoRes.getBody().setMsgCode("0x00000");
                    getEmailDisInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    getEmailDisInfoResB=new GetEmailDisInfoResB();
                    getEmailDisInfoResB.setMsgCode(e.getMsgCode());
                    getEmailDisInfoResB.setMsgDes(e.getMsgDes());
                    getEmailDisInfoRes.setBody(getEmailDisInfoResB);
                }
            }
        } else {
            getEmailDisInfoResB=new GetEmailDisInfoResB();
            getEmailDisInfoResB.setMsgCode("MG0006F");
            getEmailDisInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getEmailDisInfoRes.setBody(getEmailDisInfoResB);
        }

        getEmailDisInfoRes.setStatus("00");
        return getEmailDisInfoRes;
    }

    //保存
    @RequestMapping(value = "/SaveEmailDisInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveEmailDisInfoRes SaveTeamInfo(SaveReq saveReq){
        SaveEmailDisInfoRes saveEmailDisInfoRes=null;
        SaveEmailDisInfoResB saveEmailDisInfoResB=null;
        if ("00".equals(saveReq.getExecType())) {
            saveEmailDisInfoRes=new SaveEmailDisInfoRes();
            saveEmailDisInfoResB=new SaveEmailDisInfoResB();

            SaveEmailDisInfoReq00 saveEmailDisInfoReq00 = CommonUtils.switchClass(SaveEmailDisInfoReq00.class, saveReq.getBusData());

            try {
                saveEmailDisInfoRes = emailDisIService.AddSaveEmailDisInfoRes(saveEmailDisInfoReq00);
                saveEmailDisInfoResB.setMsgCode("0x00000");
                saveEmailDisInfoResB.setMsgDes("成功");
                saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
            } catch (SystemException e) {
                saveEmailDisInfoResB.setMsgCode(e.getMsgCode());
                saveEmailDisInfoResB.setMsgDes(e.getMsgDes());
                saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            saveEmailDisInfoRes=new SaveEmailDisInfoRes();
            saveEmailDisInfoResB=new SaveEmailDisInfoResB();
            SaveEmailDisInfoReq01 saveEmailDisInfoReq01 = CommonUtils.switchClass(SaveEmailDisInfoReq01.class, saveReq.getBusData());

            try {
                saveEmailDisInfoRes = emailDisIService.RmSaveEmailDisInfoRes(saveEmailDisInfoReq01);
                saveEmailDisInfoResB.setMsgCode("0x00000");
                saveEmailDisInfoResB.setMsgDes("成功");
                saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
            } catch (SystemException e) {
                saveEmailDisInfoResB.setMsgCode(e.getMsgCode());
                saveEmailDisInfoResB.setMsgDes(e.getMsgDes());
                saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
            }

        } else if ("02".equals(saveReq.getExecType())) {
            saveEmailDisInfoRes=new SaveEmailDisInfoRes();
            saveEmailDisInfoResB=new SaveEmailDisInfoResB();
            SaveEmailDisInfoReq02 saveEmailDisInfoReq02 = CommonUtils.switchClass(SaveEmailDisInfoReq02.class, saveReq.getBusData());

            try {
                saveEmailDisInfoRes = emailDisIService.ModSaveEmailDisInfoRes(saveEmailDisInfoReq02);

                saveEmailDisInfoResB.setMsgCode("0x00000");
                saveEmailDisInfoResB.setMsgDes("成功");
                saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
            } catch (SystemException e) {

                saveEmailDisInfoResB.setMsgCode(e.getMsgCode());
                saveEmailDisInfoResB.setMsgDes(e.getMsgDes());
                saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
            }
        } else {
            saveEmailDisInfoResB.setMsgCode("MG0006F");
            saveEmailDisInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            saveEmailDisInfoRes.setBody(saveEmailDisInfoResB);
        }
        saveEmailDisInfoRes.setStatus("00");

        return saveEmailDisInfoRes;
    }
}
