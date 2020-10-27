package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoRes;
import pnc.mesadmin.dto.GetAllCpInfo.GetAllCpInfoResB;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoReqBD00;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoRes;
import pnc.mesadmin.dto.GetCpInfo.GetCpInfoResB;
import pnc.mesadmin.dto.SaveCpInfo.*;
import pnc.mesadmin.service.CpIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：企业信息Controller
 * 创建人：张亮亮
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Cp")
public class CpController {
    @Resource
    private CpIService cpIService;

    //获取供应商页面
    @RequestMapping(value = "/CPPG")
    public String getcompanyinfoPage() {
        return "base/company/companyinfo";
    }


    //dto获取企业信息列表
    @RequestMapping(value = "/GetAllCpInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCpInfoRes GetAllCpInfo(GetAllReq getAllReq) {
        GetAllCpInfoRes objEGetAllCpInfoRes = new GetAllCpInfoRes();
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
                objEGetAllCpInfoRes = cpIService.QALLCompanyInfo(objEInitDataFields, pageInfo);
                objEGetAllCpInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllCpInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCpInfoResB objEGetAllCpInfoResB = new GetAllCpInfoResB();
                objEGetAllCpInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllCpInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllCpInfoRes.setBody(objEGetAllCpInfoResB);
            }

        } else {
            GetAllCpInfoResB objEGetAllCpInfoResB = new GetAllCpInfoResB();
            objEGetAllCpInfoResB.setMsgCode("MG0006F");
            objEGetAllCpInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllCpInfoRes.setBody(objEGetAllCpInfoResB);
        }
        objEGetAllCpInfoRes.setStatus("00");
        return objEGetAllCpInfoRes;
    }

    /**
     * 查询企业信息列表(新)
     * @param request
     * @return
     */
    @PostMapping(value = "/GetAllCpNew")
    @ResponseBody
    public BaseResponse GetAllCpNew(@RequestBody BaseRequest request){
        try {
            return BaseResponse.success(cpIService.QALLCompanyNew(request));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取企业信息
    @RequestMapping(value = "/GetCpInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetCpInfoRes GetCpInfo(GetAllReq getAllReq) {
        GetCpInfoRes objEGetCpInfoRes = new GetCpInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            //讲传过来的字符串转换成对象

            GetCpInfoReqBD00 objGetCpInfoRes00 = CommonUtils.switchClass(GetCpInfoReqBD00.class, getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null) {


            } else {  // 不分页
                try {
                    objEGetCpInfoRes = cpIService.GetGetCpInfoRes(objGetCpInfoRes00);
                    objEGetCpInfoRes.getBody().setMsgCode("0x00000");
                    objEGetCpInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetCpInfoResB objEGetCpInfoResB = new GetCpInfoResB();
                    objEGetCpInfoResB.setMsgCode(e.getMsgCode());
                    objEGetCpInfoResB.setMsgDes(e.getMsgDes());
                    objEGetCpInfoRes.setBody(objEGetCpInfoResB);
                }
            }
        } else {
            GetCpInfoResB objEGetCpInfoResB = new GetCpInfoResB();
            objEGetCpInfoResB.setMsgCode("MG0006F");
            objEGetCpInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetCpInfoRes.setBody(objEGetCpInfoResB);
        }
        objEGetCpInfoRes.setStatus("00");
        return objEGetCpInfoRes;

    }

    //新增企业信息
    @RequestMapping(value = "/SaveCpInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveCpInfoRes SaveCpInfo(SaveReq saveReq) {
        SaveCpInfoRes objESaveCpInfoRes = new SaveCpInfoRes();
        SaveCpInfoResB objESaveCpInfoResB = new SaveCpInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveCpInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveCpInfoReqBD00.class, saveReq.getBusData());


            try {
                objESaveCpInfoRes = cpIService.ADDSaveCpInfoRes(objESaveCpInfoRes00);
                objESaveCpInfoRes.getBody().setMsgCode("0x00000");
                objESaveCpInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode(e.getMsgCode());
                objESaveCpInfoResB.setMsgDes(e.getMsgDes());
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveCpInfoReqBD01 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveCpInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveCpInfoRes = cpIService.RmSaveCpInfoRes(objESaveCpInfoRes00);
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode("0x00000");
                objESaveCpInfoResB.setMsgDes("成功");
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            } catch (SystemException e) {
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode(e.getMsgCode());
                objESaveCpInfoResB.setMsgDes(e.getMsgDes());
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveCpInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveCpInfoReqBD02.class, saveReq.getBusData());
            try {
                objESaveCpInfoRes = cpIService.ModSaveCpInfoRes(objESaveCpInfoRes02);
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode("0x00000");
                objESaveCpInfoResB.setMsgDes("成功");
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            } catch (SystemException e) {
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode(e.getMsgCode());
                objESaveCpInfoResB.setMsgDes(e.getMsgDes());
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveCpInfoReqBD03 objESaveCpInfoRes03 = CommonUtils.switchClass(SaveCpInfoReqBD03.class, saveReq.getBusData());
            try {
                objESaveCpInfoRes = cpIService.ModSaveCpInfoRes(objESaveCpInfoRes03);
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode("0x00000");
                objESaveCpInfoResB.setMsgDes("成功");
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            } catch (SystemException e) {
                objESaveCpInfoResB = new SaveCpInfoResB();
                objESaveCpInfoResB.setMsgCode(e.getMsgCode());
                objESaveCpInfoResB.setMsgDes(e.getMsgDes());
                objESaveCpInfoRes.setBody(objESaveCpInfoResB);
            }
        } else {
            objESaveCpInfoResB.setMsgCode("MG0006F");
            objESaveCpInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveCpInfoRes.setBody(objESaveCpInfoResB);
        }
        objESaveCpInfoRes.setStatus("00");
        return objESaveCpInfoRes;
    }

}
