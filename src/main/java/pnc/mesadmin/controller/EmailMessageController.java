package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllEmailInfo.GetAllEmailInfoRes;
import pnc.mesadmin.dto.GetAllEmailInfo.GetAllEmailInfoResB;
import pnc.mesadmin.dto.GetEmailInfo.GetEmailInfoRes;
import pnc.mesadmin.dto.GetEmailInfo.GetEmailInfoResB;
import pnc.mesadmin.dto.GetEmailInfo.GetEmailInfoReq00;
import pnc.mesadmin.dto.SaveEmailInfo.*;
import pnc.mesadmin.entity.EmailMessageInfo;
import pnc.mesadmin.service.EmailMessageIService;
import pnc.mesadmin.utils.CommonUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * Created by PNC on 2018/7/4.
 */
@Controller
@Scope("prototype")
@RequestMapping("/Email")
public class EmailMessageController {
     @Resource
     private EmailMessageIService emailMessageIService;

    //获取通知管理管理页面
    @RequestMapping(value = "/EmailmessagePG")
    public String getToolPGPage() {
        return "notification/email/emailinfo";
    }

    //dto获取工厂信息列表
    @RequestMapping(value = "/GetAllEmailInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllEmailInfoRes GetAllEmailInfo(GetAllReq getAllReq) {
        GetAllEmailInfoRes objGetAllEmailInfoRes = new GetAllEmailInfoRes();
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
                objGetAllEmailInfoRes = emailMessageIService.QALLselectAllAllEmailInfo(objEInitDataFields,pageInfo);
                objGetAllEmailInfoRes.getBody().setMsgCode("0x00000");
                objGetAllEmailInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllEmailInfoResB objGetAllEmailInfoResB = new GetAllEmailInfoResB();
                objGetAllEmailInfoResB.setMsgCode(e.getMsgCode());
                objGetAllEmailInfoResB.setMsgDes(e.getMsgDes());
                objGetAllEmailInfoRes.setBody(objGetAllEmailInfoResB);
            }

        } else {
            GetAllEmailInfoResB objGetAllEmailInfoResB = new GetAllEmailInfoResB();
            objGetAllEmailInfoResB.setMsgCode("MG0006F");
            objGetAllEmailInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objGetAllEmailInfoRes.setBody(objGetAllEmailInfoResB);
        }
        objGetAllEmailInfoRes.setStatus("00");
        return objGetAllEmailInfoRes;
    }

    //  获取服务器配置信息
    @RequestMapping(value = "/GetEmailInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetEmailInfoRes GetEmailInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetEmailInfoRes objGetSMTPInfoRes = new GetEmailInfoRes();
        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetEmailInfoReq00 busData00 = CommonUtils.switchClass(GetEmailInfoReq00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objGetSMTPInfoRes = emailMessageIService.GetselectEmailInfoId(busData00.getEmailRd());
                    objGetSMTPInfoRes.getBody().setMsgCode("0x00000");
                    objGetSMTPInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetEmailInfoResB objGetEmailInfoResB =  new GetEmailInfoResB();
                    objGetEmailInfoResB.setMsgCode(e.getMsgCode());
                    objGetEmailInfoResB.setMsgDes(e.getMsgDes());
                    objGetSMTPInfoRes.setBody(objGetEmailInfoResB);
                }
            }
        }else {
            GetEmailInfoResB objGetEmailInfoResB =  new GetEmailInfoResB();
            objGetEmailInfoResB.setMsgCode("MG0006F");
            objGetEmailInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetSMTPInfoRes.setBody(objGetEmailInfoResB);
        }
        objGetSMTPInfoRes.setStatus("00");
        return   objGetSMTPInfoRes;
    }

    //保存邮件内容信息
    @RequestMapping(value = "/SaveEmailInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveEmailInfoRes SaveEmailInfo(HttpServletRequest request, SaveReq saveReq) {
        SaveEmailInfoRes objSaveEmailInfoRes = new SaveEmailInfoRes();
        SaveEmailInfoResB saveEmailInfoResB = new SaveEmailInfoResB();
        EmailMessageInfo emailMessageInfo = new EmailMessageInfo();
        String rowData = saveReq.getBusData();
        //新增
        if("00".equals(saveReq.getExecType())){
            // JsonObject转换成实体类
            SaveEmailInfoReqBD00 busData00 = CommonUtils.switchClass(SaveEmailInfoReqBD00.class,rowData);
            try {
                objSaveEmailInfoRes = emailMessageIService.AddinsertEmailMessageInfo(busData00,emailMessageInfo);
                saveEmailInfoResB.setMsgCode("0x00000");
                saveEmailInfoResB.setMsgDes("成功");
                objSaveEmailInfoRes.setBody(saveEmailInfoResB);
            }catch (SystemException e){
                saveEmailInfoResB.setMsgCode(e.getMsgCode());
                saveEmailInfoResB.setMsgDes(e.getMsgDes());
                objSaveEmailInfoRes.setBody(saveEmailInfoResB);
            }
        }
        //删除
         else if("01".equals(saveReq.getExecType())){
            SaveEmailInfoReqBD01 busData01 = CommonUtils.switchClass(SaveEmailInfoReqBD01.class,rowData);
            try {
                objSaveEmailInfoRes = emailMessageIService.RmdeleteEmailMessageInfo(busData01.getEmailRd());
                SaveEmailInfoResB body = new SaveEmailInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveEmailInfoRes.setBody(body);
            }catch (SystemException e){
                saveEmailInfoResB.setMsgCode(e.getMsgCode());
                saveEmailInfoResB.setMsgDes(e.getMsgDes());
                objSaveEmailInfoRes.setBody(saveEmailInfoResB);
            }
        }
        //更新
        else if("02".equals(saveReq.getExecType())){
            SaveEmailInfoReqBD02 busData02 = CommonUtils.switchClass(SaveEmailInfoReqBD02.class,rowData);
            try {
                objSaveEmailInfoRes = emailMessageIService.ModupdateEmailMessageInfo(busData02,emailMessageInfo);
                saveEmailInfoResB.setMsgCode("0x00000");
                saveEmailInfoResB.setMsgDes("成功！");
                objSaveEmailInfoRes.setBody(saveEmailInfoResB);
            }catch (SystemException e){
                saveEmailInfoResB.setMsgCode(e.getMsgCode());
                saveEmailInfoResB.setMsgDes(e.getMsgDes());
                objSaveEmailInfoRes.setBody(saveEmailInfoResB);
            }
        }
        //复制
        else if("03".equals(saveReq.getExecType())){
            SaveEmailInfoReqBD03 busData03 = CommonUtils.switchClass(SaveEmailInfoReqBD03.class,rowData);
            try {
                objSaveEmailInfoRes = emailMessageIService.copyEmailMessageInfo(busData03,emailMessageInfo);
                SaveEmailInfoResB body =  new SaveEmailInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveEmailInfoRes.setBody(body);
            }catch (SystemException e){
                saveEmailInfoResB.setMsgCode(e.getMsgCode());
                saveEmailInfoResB.setMsgDes(e.getMsgDes());
                objSaveEmailInfoRes.setBody(saveEmailInfoResB);
            }
        }
        objSaveEmailInfoRes.setStatus("00");
        return  objSaveEmailInfoRes;
    }


}
