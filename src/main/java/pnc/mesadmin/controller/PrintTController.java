package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoRes;
import pnc.mesadmin.dto.GetAllPtInfo.GetAllPtInfoResB;
import pnc.mesadmin.dto.GetPtBFInfo.GetPtBFInfoRes;
import pnc.mesadmin.dto.GetPtBFInfo.GetPtBFInfoResB;
import pnc.mesadmin.dto.GetPtInfo.GetPtInfoReqBD00;
import pnc.mesadmin.dto.GetPtInfo.GetPtInfoRes;
import pnc.mesadmin.dto.GetPtInfo.GetPtInfoResB;
import pnc.mesadmin.dto.SavePtInfo.*;
import pnc.mesadmin.service.PrintTIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印模板控制器
 * 创建人：潘俊峰
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/PrintT")
public class PrintTController {

    @Resource
    private PrintTIService printTIService;

    //获取打印模板页面
    @RequestMapping(value = "/PrintTPG")
    public String getcompanyinfoPage(){
        return "base/printT/PrintTInfo";
    }

    /**
     * 获取打印模板列表信息
     * @param getAllReq
     * @return
     */
    @RequestMapping(value = "/GetAllPtInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllPtInfoRes GetAllPtInfo(GetAllReq getAllReq){
        GetAllPtInfoRes objEGetAllPtInfoRes = new GetAllPtInfoRes();

        if("00".equals(getAllReq.getExecType())){
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
            try{
                objEGetAllPtInfoRes = printTIService.QALLPrintTInfo(objEInitDataFields, pageInfo);
                objEGetAllPtInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPtInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllPtInfoResB objEGetAllPtInfoResB = new GetAllPtInfoResB();
                objEGetAllPtInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPtInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPtInfoRes.setBody(objEGetAllPtInfoResB);
            }
        }

        objEGetAllPtInfoRes.setStatus("00");

        return objEGetAllPtInfoRes;
    }

    /**
     * 获取打印模板列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllPtNew")
    @ResponseBody
    public BaseResponse GetAllPtNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(printTIService.QALLPrintTNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    /**
     * 获取打印模板信息
     * @param getAllReq
     * @return
     */
    @RequestMapping(value = "/GetPtInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPtInfoRes GetPtInfo(GetAllReq getAllReq){
        GetPtInfoRes objEGetPtInfoRes = new GetPtInfoRes();

        if("00".equals(getAllReq.getExecType())){

            GetPtInfoReqBD00 objEGetPtInfoReqBD00 = CommonUtils.switchClass(GetPtInfoReqBD00.class,getAllReq.getBusData());

            try {
                objEGetPtInfoRes = printTIService.GetPrintTInfo(objEGetPtInfoReqBD00);
                objEGetPtInfoRes.getBody().setMsgCode("0x00000");
                objEGetPtInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPtInfoResB objEGetPtInfoResB = new GetPtInfoResB();
                objEGetPtInfoResB.setMsgCode(e.getMsgCode());
                objEGetPtInfoResB.setMsgDes(e.getMsgDes());
                objEGetPtInfoRes.setBody(objEGetPtInfoResB);
            }
        }

        objEGetPtInfoRes.setStatus("00");

        return objEGetPtInfoRes;
    }

    /**
     * 获取模板可绑定字段信息
     * @param getAllReq
     * @return
     */
    @RequestMapping(value = "/GetPtBFInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPtBFInfoRes GetPtBFInfo(GetAllReq getAllReq){
        GetPtBFInfoRes objEGetPtBFInfoRes = new GetPtBFInfoRes();

        if("00".equals(getAllReq.getExecType())){
            try{
                objEGetPtBFInfoRes = printTIService.QALLPrintTFInfo();
                objEGetPtBFInfoRes.getBody().setMsgCode("0x00000");
                objEGetPtBFInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPtBFInfoResB objEGetPtBFInfoResB = new GetPtBFInfoResB();
                objEGetPtBFInfoResB.setMsgCode(e.getMsgCode());
                objEGetPtBFInfoResB.setMsgDes(e.getMsgDes());
                objEGetPtBFInfoRes.setBody(objEGetPtBFInfoResB);
            }
        }

        objEGetPtBFInfoRes.setStatus("00");

        return objEGetPtBFInfoRes;
    }

    /**
     * 保存打印模板信息
     * @param saveReq
     * @return
     */
    @RequestMapping(value = "/SavePtInfo", method = RequestMethod.POST)
    @ResponseBody
    public SavePtInfoRes SavePtInfo(HttpServletRequest request, SaveReq saveReq){
        SavePtInfoRes objESavePtInfoRes = new SavePtInfoRes();
        SavePtInfoResB objESavePtInfoResB = new SavePtInfoResB();

        if("00".equals(saveReq.getExecType())){
            //新增
            SavePtInfoReqBD00 objESavePtInfoReqBD00 = CommonUtils.switchClass(SavePtInfoReqBD00.class, saveReq.getBusData());

            try{
                objESavePtInfoRes = printTIService.AddPrintTInfo(request, objESavePtInfoReqBD00);
                objESavePtInfoResB.setMsgCode("0x00000");
                objESavePtInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePtInfoResB.setMsgCode(e.getMsgCode());
                objESavePtInfoResB.setMsgDes(e.getMsgDes());
            } catch (IOException e) {
                objESavePtInfoResB.setMsgCode("");
                objESavePtInfoResB.setMsgDes("上传失败");
            }

        }else if("01".equals(saveReq.getExecType())){
            //删除
            SavePtInfoReqBD01 objESavePtInfoReqBD01 = CommonUtils.switchClass(SavePtInfoReqBD01.class, saveReq.getBusData());

            try {
                objESavePtInfoRes = printTIService.RmPrintTInfo(objESavePtInfoReqBD01);
                objESavePtInfoResB.setMsgCode("0x00000");
                objESavePtInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePtInfoResB.setMsgCode(e.getMsgCode());
                objESavePtInfoResB.setMsgDes(e.getMsgDes());
            }

        }else if("02".equals(saveReq.getExecType())){
            //编辑
            SavePtInfoReqBD02 objESavePtInfoReqBD02 = CommonUtils.switchClass(SavePtInfoReqBD02.class, saveReq.getBusData());
            try {
                objESavePtInfoRes = printTIService.ModPrintTInfo(request, objESavePtInfoReqBD02);
                objESavePtInfoResB.setMsgCode("0x00000");
                objESavePtInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePtInfoResB.setMsgCode(e.getMsgCode());
                objESavePtInfoResB.setMsgDes(e.getMsgDes());
            } catch (IOException e) {
                objESavePtInfoResB.setMsgCode("");
                objESavePtInfoResB.setMsgDes("上传失败");
            }

        }


        objESavePtInfoRes.setStatus("00");
        objESavePtInfoRes.setBody(objESavePtInfoResB);

        return objESavePtInfoRes;
    }

}
