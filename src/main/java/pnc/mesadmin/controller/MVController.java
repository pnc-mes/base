package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllMVMaInfo.GetAllMVMaInfoRes;
import pnc.mesadmin.dto.GetAllMVMaInfo.GetAllMVMaInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetMVMaInfo.GetMVMaInfoReq00;
import pnc.mesadmin.dto.GetMVMaInfo.GetMVMaInfoRes;
import pnc.mesadmin.dto.GetMVMaInfo.GetMVMaInfoResB;
import pnc.mesadmin.service.MVIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
@Controller
@RequestMapping("/MV")
public class MVController {

    @Resource
    private MVIService mviService;

    @RequestMapping("/MvMaPG")
    public String MVPage(){
        return "warehouse/instock/mvma/mvmaInfo";
    }

    //调拨列表
    @RequestMapping(value = "/GetAllMVMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMVMaInfoRes GetAllMVMaInfo(GetAllReq getAllReq){
        GetAllMVMaInfoRes objEGetAllMVMaInfoRes=new GetAllMVMaInfoRes();

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
                objEGetAllMVMaInfoRes = mviService.QALLGetAllMVMaInfoRes(objEInitDataFields, pageInfo);
                objEGetAllMVMaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllMVMaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllMVMaInfoResB objEGetAllMVMaInfoResB = new GetAllMVMaInfoResB();
                objEGetAllMVMaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllMVMaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllMVMaInfoRes.setBody(objEGetAllMVMaInfoResB);
            }
        }

        else{
            GetAllMVMaInfoResB objEGetAllMVMaInfoResB = new GetAllMVMaInfoResB();
            objEGetAllMVMaInfoResB.setMsgCode("MG0006F");
            objEGetAllMVMaInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllMVMaInfoRes.setBody(objEGetAllMVMaInfoResB);
        }
        objEGetAllMVMaInfoRes.setStatus("00");
        return objEGetAllMVMaInfoRes;
    }

    //调拨查询
    @RequestMapping(value = "/GetMVMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMVMaInfoRes GetMVMaInfo(GetAllReq getAllReq){
        GetMVMaInfoRes objEGetMVMaInfoRes=new GetMVMaInfoRes();
        if("00".equals(getAllReq.getExecType())){
            GetMVMaInfoReq00 objEGetMVMaInfoReq00 = CommonUtils.switchClass(GetMVMaInfoReq00.class,getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null){
            }else{
                try{
                    objEGetMVMaInfoRes= mviService.GetGetMVMaInfoRes(objEGetMVMaInfoReq00);
                    objEGetMVMaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetMVMaInfoRes.getBody().setMsgDes("成功");

                }catch (SystemException e){
                    GetMVMaInfoResB objEGetMVMaInfoResB=new GetMVMaInfoResB();
                    objEGetMVMaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetMVMaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetMVMaInfoRes.setBody(objEGetMVMaInfoResB);
                }
            }
        }
        else {
            GetMVMaInfoResB objEGetMVMaInfoResB=new GetMVMaInfoResB();
            objEGetMVMaInfoResB.setMsgCode("MG0006F");
            objEGetMVMaInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetMVMaInfoRes.setBody(objEGetMVMaInfoResB);
        }
        objEGetMVMaInfoRes.setStatus("00");
        return  objEGetMVMaInfoRes;
    }


}
