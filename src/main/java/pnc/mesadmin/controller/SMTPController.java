package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSMTPInfo.GetAllSMTPInfoRes;
import pnc.mesadmin.dto.GetAllSMTPInfo.GetAllSMTPInfoResB;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoRes;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoResB;
import pnc.mesadmin.dto.GetSMTPInfo.GetSMTPInfoResBD00;
import pnc.mesadmin.dto.SaveSMTPInfo.*;
import pnc.mesadmin.entity.SMTPInfo;
import pnc.mesadmin.service.SMTPIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：邮件服务器列表信息Controller
 * 创建人：乔帅阳
 * 创建时间：2018-7-3
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/SMTP")
public class SMTPController {
    @Resource
    private SMTPIService smtpiService;

    //  获取邮件服务器列表信息
    @RequestMapping(value = "/GetAllSMTPInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllSMTPInfoRes GetAllSMTPInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllSMTPInfoRes objGetAllSMTPInfoRes = new GetAllSMTPInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            try {
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
                objGetAllSMTPInfoRes = smtpiService.QALLselectAllSMTPInfoInfo(objEInitDataFields, pageInfo);
                objGetAllSMTPInfoRes.getBody().setMsgCode("0x00000");
                objGetAllSMTPInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSMTPInfoResB objGetAllSMTPInfoResB = new GetAllSMTPInfoResB();
                objGetAllSMTPInfoResB.setMsgCode(e.getMsgCode());
                objGetAllSMTPInfoResB.setMsgDes(e.getMsgDes());
                objGetAllSMTPInfoRes.setBody(objGetAllSMTPInfoResB);
            }
        }else{
            GetAllSMTPInfoResB objGetAllSMTPInfoResB = new GetAllSMTPInfoResB();
            objGetAllSMTPInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetAllSMTPInfoResB.setMsgCode("MG0006F");
            objGetAllSMTPInfoRes.setBody(objGetAllSMTPInfoResB);
        }
        objGetAllSMTPInfoRes.setStatus("00");
        return objGetAllSMTPInfoRes;
    }


    //  获取服务器配置信息
    @RequestMapping(value = "/GetSMTPInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSMTPInfoRes GetSMTPInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetSMTPInfoRes objGetSMTPInfoRes = new GetSMTPInfoRes();
        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetSMTPInfoResBD00 busData00 = CommonUtils.switchClass(GetSMTPInfoResBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objGetSMTPInfoRes = smtpiService.GetselectSMTPInfo();
                    objGetSMTPInfoRes.getBody().setMsgCode("0x00000");
                    objGetSMTPInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetSMTPInfoResB objGetSMTPInfoResB =  new GetSMTPInfoResB();
                    objGetSMTPInfoResB.setMsgCode(e.getMsgCode());
                    objGetSMTPInfoResB.setMsgDes(e.getMsgDes());
                    objGetSMTPInfoRes.setBody(objGetSMTPInfoResB);
                }
            }
        }else {
            GetSMTPInfoResB objGetSMTPInfoResB =  new GetSMTPInfoResB();
            objGetSMTPInfoResB.setMsgCode("MG0006F");
            objGetSMTPInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetSMTPInfoRes.setBody(objGetSMTPInfoResB);
        }
        objGetSMTPInfoRes.setStatus("00");
        return   objGetSMTPInfoRes;
    }
      //保存邮件服务器信息
      @RequestMapping(value = "/SaveSMTPInfo", method = RequestMethod.POST)
      @ResponseBody
      public SaveSMTPInfoRes SaveSMTPInfo(HttpServletRequest request, SaveReq saveReq){
          SaveSMTPInfoRes objSaveSMTPInfoRes = new SaveSMTPInfoRes();
          SaveSMTPInfoResB  saveSMTPInfoResB  =  new SaveSMTPInfoResB();
          SMTPInfo sMTPInfo = new SMTPInfo();
          String rowData = saveReq.getBusData();
          //新增
          if("00".equals(saveReq.getExecType())){
              // JsonObject转换成实体类
              SaveSMTPInfoReqBD00 busData00 = CommonUtils.switchClass(SaveSMTPInfoReqBD00.class,rowData);
              try {
                  objSaveSMTPInfoRes = smtpiService.AddinsertSMTPInfo(busData00,sMTPInfo);
                  saveSMTPInfoResB.setMsgCode("0x00000");
                  saveSMTPInfoResB.setMsgDes("成功");
                  objSaveSMTPInfoRes.setBody(saveSMTPInfoResB);
              }catch (SystemException e){
                  saveSMTPInfoResB.setMsgCode(e.getMsgCode());
                  saveSMTPInfoResB.setMsgDes(e.getMsgDes());
                  objSaveSMTPInfoRes.setBody(saveSMTPInfoResB);
              }
          }
          //删除
          else if ("01".equals(saveReq.getExecType())){
              SaveSMTPInfoReqBD01 busData01 = CommonUtils.switchClass(SaveSMTPInfoReqBD01.class,rowData);
              try {
                  objSaveSMTPInfoRes = smtpiService.RmdeleteSMTPInfo(busData01.getSMTPRd());
                  SaveSMTPInfoResB body = new SaveSMTPInfoResB();
                  body.setMsgCode("0x00000");
                  body.setMsgDes("成功！");
                  objSaveSMTPInfoRes.setBody(body);
              }catch (SystemException e){
                  saveSMTPInfoResB.setMsgCode(e.getMsgCode());
                  saveSMTPInfoResB.setMsgDes(e.getMsgDes());
                  objSaveSMTPInfoRes.setBody(saveSMTPInfoResB);
              }
          }
          //更新
          else if("02".equals(saveReq.getExecType())){
              SaveSMTPInfoReqBD02 busData02 = CommonUtils.switchClass(SaveSMTPInfoReqBD02.class,rowData);
              try {
                  objSaveSMTPInfoRes = smtpiService.ModupdateCustomerInfo(busData02,sMTPInfo);
                  SaveSMTPInfoResB  body  =  new SaveSMTPInfoResB();
                  body.setMsgCode("0x00000");
                  body.setMsgDes("成功！");
                  objSaveSMTPInfoRes.setBody(body);
              }catch (SystemException e){
                  saveSMTPInfoResB.setMsgCode(e.getMsgCode());
                  saveSMTPInfoResB.setMsgDes(e.getMsgDes());
                  objSaveSMTPInfoRes.setBody(saveSMTPInfoResB);
              }
          }else if ("03".equals(saveReq.getExecType())){
              SaveSMTPInfoReqBD03 busData03 = CommonUtils.switchClass(SaveSMTPInfoReqBD03.class,rowData);
              try {
                  objSaveSMTPInfoRes = smtpiService.copySMTPInfo(busData03,sMTPInfo);
                  SaveSMTPInfoResB body =  new SaveSMTPInfoResB();
                  body.setMsgCode("0x00000");
                  body.setMsgDes("成功！");
                  objSaveSMTPInfoRes.setBody(body);
              }catch (SystemException e){
                  saveSMTPInfoResB.setMsgCode(e.getMsgCode());
                  saveSMTPInfoResB.setMsgDes(e.getMsgDes());
                  objSaveSMTPInfoRes.setBody(saveSMTPInfoResB);
              }
          }
          objSaveSMTPInfoRes.setStatus("00");
          return objSaveSMTPInfoRes;
      }

    //获取通知管理管理页面
    @RequestMapping(value = "/SMTPsetupPG")
    public String getToolPGPage() {
        return "notification/smpt/SMTPsetup";
    }
}
