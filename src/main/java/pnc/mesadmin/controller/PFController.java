package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoRes;
import pnc.mesadmin.dto.GetAllPdfInfo.GetAllPdfInfoResB;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoReqBD00;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoRes;
import pnc.mesadmin.dto.GetPdfInfo.GetPdfInfoResB;
import pnc.mesadmin.dto.SavePdfInfo.*;
import pnc.mesadmin.service.PFIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：产品族管理控制器
 * 创建人：pjf
 * 创建时间：2017-05-26
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/PF")
public class PFController {

    @Resource
    private PFIService PFIService;

    //获取产品族页面
    @RequestMapping(value = "/PFPG")
    public String getPFPGPage(){
        return "process/prodfamily/prodfamily";
    }

    //获取产品族列表信息
    @RequestMapping(value = "/GetAllPdfInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllPdfInfoRes GetAllPdfInfoRequest(GetAllReq getAllReq){

        GetAllPdfInfoRes objEGetAllPdfInfoRes = new GetAllPdfInfoRes();

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
                objEGetAllPdfInfoRes = PFIService.QALLPdFamilyInfo(objEInitDataFields, pageInfo);
                objEGetAllPdfInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPdfInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllPdfInfoResB objEGetAllPdfInfoResB = new GetAllPdfInfoResB();
                objEGetAllPdfInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPdfInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPdfInfoRes.setBody(objEGetAllPdfInfoResB);
            }
        }

        objEGetAllPdfInfoRes.setStatus("00");

        return objEGetAllPdfInfoRes;
    }

    /**
     * 获取产品族列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllPdFamilyNew")
    @ResponseBody
    public BaseResponse GetAllPdFamilyNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(PFIService.QALLPdFamilyNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取产品族信息
    @RequestMapping(value = "/GetPdfInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetPdfInfoRes GetPdfInfoRequest(GetAllReq getAllReq){

        GetPdfInfoRes objEGetPdfInfoRes = new GetPdfInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetPdfInfoReqBD00 objGetPdfInfoReqBD00 = CommonUtils.switchClass(GetPdfInfoReqBD00.class,busData);

            try{
                objEGetPdfInfoRes = PFIService.GetPdFamilyInfo(objGetPdfInfoReqBD00);
                objEGetPdfInfoRes.getBody().setMsgCode("0x00000");
                objEGetPdfInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetPdfInfoResB objEGetPdfInfoResB = new GetPdfInfoResB();
                objEGetPdfInfoResB.setMsgCode(e.getMsgCode());
                objEGetPdfInfoResB.setMsgDes(e.getMsgDes());
                objEGetPdfInfoRes.setBody(objEGetPdfInfoResB);
            }
        }

        objEGetPdfInfoRes.setStatus("00");

        return objEGetPdfInfoRes;
    }

    //保存产品族信息
    @RequestMapping(value = "/SavePdfInfo", method = RequestMethod.POST)
    @ResponseBody
    public SavePdfInfoRes SavePdfInfoRequest(SaveReq saveReq){

        SavePdfInfoRes objESavePdfInfoRes = new SavePdfInfoRes();
        SavePdfInfoResB objESavePdfInfoResB = new SavePdfInfoResB();

        String busData = saveReq.getBusData();


        if("00".equals(saveReq.getExecType())){
            //新增
            SavePdfInfoReqBD00 objESavePdfInfoReqBD00 = CommonUtils.switchClass(SavePdfInfoReqBD00.class,busData);
            try{
                objESavePdfInfoRes  = PFIService.AddPdFamilyInfo(objESavePdfInfoReqBD00);
                objESavePdfInfoResB.setMsgCode("0x00000");
                objESavePdfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePdfInfoResB.setMsgCode(e.getMsgCode());
                objESavePdfInfoResB.setMsgDes(e.getMsgDes());
            }

        }else if("01".equals(saveReq.getExecType())){
            //删除
            //List<SavePdfInfoReqBD01> objESavePdfInfoReqBD01 = (List<SavePdfInfoReqBD01>) JSONObject.toBean(jsonObject, SaveRoleInfoReqBD01.class);
            SavePdfInfoReqBD01 objESavePdfInfoReqBD01 = CommonUtils.switchClass(SavePdfInfoReqBD01.class,busData);
            try {
                objESavePdfInfoRes = PFIService.RmPdFamilyInfo(objESavePdfInfoReqBD01);
                objESavePdfInfoResB.setMsgCode("0x00000");
                objESavePdfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePdfInfoResB.setMsgCode(e.getMsgCode());
                objESavePdfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("02".equals(saveReq.getExecType())){
            //编辑
            SavePdfInfoReqBD02 objESavePdfInfoReqBD02 = CommonUtils.switchClass(SavePdfInfoReqBD02.class,busData);
            try{
                objESavePdfInfoRes = PFIService.ModPdFamilyInfo(objESavePdfInfoReqBD02);
                objESavePdfInfoResB.setMsgCode("0x00000");
                objESavePdfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePdfInfoResB.setMsgCode(e.getMsgCode());
                objESavePdfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("03".equals(saveReq.getExecType())){
            //复制
            SavePdfInfoReqBD03 objESavePdfInfoReqBD03 = CommonUtils.switchClass(SavePdfInfoReqBD03.class,busData);
            try{
                objESavePdfInfoRes = PFIService.AddCopyPdFamilyInfo(objESavePdfInfoReqBD03);
                objESavePdfInfoResB.setMsgCode("0x00000");
                objESavePdfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESavePdfInfoResB.setMsgCode(e.getMsgCode());
                objESavePdfInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESavePdfInfoRes.setStatus("00");
        objESavePdfInfoRes.setBody(objESavePdfInfoResB);

        return objESavePdfInfoRes;
    }

}
