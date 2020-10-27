package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoRes;
import pnc.mesadmin.dto.GetAllFaInfo.GetAllFaInfoResB;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoReqBD00;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoRes;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResB;
import pnc.mesadmin.dto.SaveFaInfo.*;
import pnc.mesadmin.service.FactoryIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工厂信息Controller
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Factory")
public class FactoryController {

    @Resource
    private FactoryIService factoryIService;

    //获取工厂页面
    @RequestMapping(value = "/FactoryPG")
    public String getFactoryPGPage() {

        return "base/factory/factoryinfo";
    }

    //dto获取工厂信息列表
    @RequestMapping(value = "/GetAllFaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllFaInfoRes GetAllFaInfo(GetAllReq getAllReq) {
        GetAllFaInfoRes objEGetAllFaInfoRes = new GetAllFaInfoRes();
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
                objEGetAllFaInfoRes = factoryIService.QALLGetAllFaInfoRes(objEInitDataFields, pageInfo);
                objEGetAllFaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllFaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllFaInfoResB objEGetAllFaInfoResB = new GetAllFaInfoResB();
                objEGetAllFaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllFaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
            }

        } else {
            GetAllFaInfoResB objEGetAllFaInfoResB = new GetAllFaInfoResB();
            objEGetAllFaInfoResB.setMsgCode("MG0006F");
            objEGetAllFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllFaInfoRes.setBody(objEGetAllFaInfoResB);
        }
        objEGetAllFaInfoRes.setStatus("00");
        return objEGetAllFaInfoRes;
    }

    //dto获取工厂信息列表
    @RequestMapping(value = "/GetAllFaInfoV2", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllFaInfoV2(SaveFaInfoReqBD00 req) {
        return  factoryIService.GetAllFaInfoV2(req);
    }

    //获取工厂信息列表
    @PostMapping(value = "/GetAllFaNew")
    @ResponseBody
    public BaseResponse GetAllFaNew(@RequestBody BaseRequest res){
        try{
            return BaseResponse.success(factoryIService.QALLGetAllFaNewRes(res));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto获取工厂信息
    @RequestMapping(value = "/GetFaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetFaInfoRes GetFaInfo(GetAllReq getAllReq) {
        GetFaInfoRes objEGetFaInfoRes = new GetFaInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetFaInfoReqBD00 objEGetFaInfoReqBD00 = CommonUtils.switchClass(GetFaInfoReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    objEGetFaInfoRes = factoryIService.GetGetFaInfoRes(objEGetFaInfoReqBD00);
                    objEGetFaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetFaInfoResB objEGetFaInfoResB = new GetFaInfoResB();
                    objEGetFaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFaInfoRes.setBody(objEGetFaInfoResB);
                }
            }
        } else {
            GetFaInfoResB objEGetFaInfoResB = new GetFaInfoResB();
            objEGetFaInfoResB.setMsgCode("MG0006F");
            objEGetFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetFaInfoRes.setBody(objEGetFaInfoResB);
        }

        objEGetFaInfoRes.setStatus("00");
        return objEGetFaInfoRes;
    }


    //dto保存工厂信息
    @RequestMapping(value = "/SaveFaInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveFaInfoRes SaveFaInfo(SaveReq saveReq) {
        SaveFaInfoRes objESaveFaInfoRes = new SaveFaInfoRes();
        SaveFaInfoResB objESaveFaInfoResB = new SaveFaInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveFaInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveFaInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = factoryIService.AddGetFaInfoRes(objESaveCpInfoRes00);
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("00");
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("01");
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveFaInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveFaInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = factoryIService.RmSaveFaInfoRes(objESaveCpInfoRes01);
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("00");
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("01");
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveFaInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveFaInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = factoryIService.ModSaveFaInfoRes(objESaveCpInfoRes02);
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("00");
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("01");
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveFaInfoReqBD03 objESaveFaInfoReqBD03 = CommonUtils.switchClass(SaveFaInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = factoryIService.AddSaveFaInfoRes(objESaveFaInfoReqBD03);
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("00");
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveFaInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
                objESaveFaInfoRes.setStatus("01");
            }
        } else {
            objESaveFaInfoResB.setMsgCode("MG0006F");
            objESaveFaInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        }
        return objESaveFaInfoRes;
    }
}
