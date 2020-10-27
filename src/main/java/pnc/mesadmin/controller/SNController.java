package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoRes;
import pnc.mesadmin.dto.GetAllSNInfo.GetAllSNInfoResB;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoReqBD00;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoRes;
import pnc.mesadmin.dto.GetSNInfo.GetSNInfoResB;
import pnc.mesadmin.dto.SaveSNInfo.*;
import pnc.mesadmin.service.SNIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：序号管理Controller
 * 创建人：张亮亮
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/SN")
public class SNController {
    @Resource
    private SNIService sNIService;

    //获取序号页面
    @RequestMapping(value = "/SNPG")
    public String getSNPGPage() {

        return "base/serialnum/serialnum";
    }

    //dto查询序号管理列表
    @RequestMapping(value = "/GetAllSNInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllSNInfoRes GetAllSNInfo(GetAllReq getAllReq) {
        GetAllSNInfoRes objEGetAllSNInfoRes = new GetAllSNInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
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
                    objEGetAllSNInfoRes = sNIService.QALLGetAllSNInfoRes(objEInitDataFields, pageInfo);
                    objEGetAllSNInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllSNInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetAllSNInfoResB objEGetAllSNInfoResB = new GetAllSNInfoResB();
                    objEGetAllSNInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllSNInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllSNInfoRes.setBody(objEGetAllSNInfoResB);
                }
            }
        } else {
            GetAllSNInfoResB objEGetAllSNInfoResB = new GetAllSNInfoResB();
            objEGetAllSNInfoResB.setMsgCode("MG0006F");
            objEGetAllSNInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllSNInfoRes.setBody(objEGetAllSNInfoResB);
        }

        objEGetAllSNInfoRes.setStatus("00");
        return objEGetAllSNInfoRes;
    }

    /**
     * 查询序号规则列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllSNNew")
    @ResponseBody
    public BaseResponse GetAllSNNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(sNIService.QALLSNNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto查询序号管理
    @RequestMapping(value = "/GetSNInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSNInfoRes GetSNInfo(GetAllReq getAllReq) {
        GetSNInfoRes objEGetSNInfoRes = new GetSNInfoRes();

        if ("00".equals(getAllReq.getExecType())) {

            GetSNInfoReqBD00 objEGetSNInfoReqBD00 = CommonUtils.switchClass(GetSNInfoReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {

                try {
                    objEGetSNInfoRes = sNIService.GetGetSNInfoRes(objEGetSNInfoReqBD00);
                    objEGetSNInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSNInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetSNInfoResB objEGetSNInfoResB = new GetSNInfoResB();
                    objEGetSNInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSNInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSNInfoRes.setBody(objEGetSNInfoResB);
                }
            }
        } else {
            GetSNInfoResB objEGetSNInfoResB = new GetSNInfoResB();
            objEGetSNInfoResB.setMsgCode("MG0006F");
            objEGetSNInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetSNInfoRes.setBody(objEGetSNInfoResB);
        }
        objEGetSNInfoRes.setStatus("00");
        return objEGetSNInfoRes;
    }


    //dto保存序号管理
    @RequestMapping(value = "/SaveSNInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveSNInfoRes SaveSNInfo(SaveReq saveReq) {
        SaveSNInfoRes objESaveSNInfoRes = new SaveSNInfoRes();
        SaveSNInfoResB objESaveSNInfoResB = new SaveSNInfoResB();

        if ("00".equals(saveReq.getExecType())) {
            SaveSNInfoReqBD00 objESaveSNInfoReqBD00 = CommonUtils.switchClass(SaveSNInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveSNInfoRes = sNIService.AddSaveSNInfoRes(objESaveSNInfoReqBD00);
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode("0x00000");
                objESaveSNInfoResB.setMsgDes("成功");
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            } catch (SystemException e) {
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode(e.getMsgCode());
                objESaveSNInfoResB.setMsgDes(e.getMsgDes());
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveSNInfoReqBD01 objESaveSNInfoReqBD01 = CommonUtils.switchClass(SaveSNInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveSNInfoRes = sNIService.RmSaveSNInfoRes(objESaveSNInfoReqBD01);
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode("0x00000");
                objESaveSNInfoResB.setMsgDes("成功");
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            } catch (SystemException e) {
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode(e.getMsgCode());
                objESaveSNInfoResB.setMsgDes(e.getMsgDes());
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveSNInfoReqBD02 objESaveSNInfoReqBD02 = CommonUtils.switchClass(SaveSNInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveSNInfoRes = sNIService.ModSaveSNInfoRes(objESaveSNInfoReqBD02);
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode("0x00000");
                objESaveSNInfoResB.setMsgDes("成功");
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            } catch (SystemException e) {
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode(e.getMsgCode());
                objESaveSNInfoResB.setMsgDes(e.getMsgDes());
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveSNInfoReqBD03 objESaveSNInfoReqBD03 = CommonUtils.switchClass(SaveSNInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveSNInfoRes = sNIService.AddSaveSNInfoRess(objESaveSNInfoReqBD03);
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode("0x00000");
                objESaveSNInfoResB.setMsgDes("成功");
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            } catch (SystemException e) {
                objESaveSNInfoResB = new SaveSNInfoResB();
                objESaveSNInfoResB.setMsgCode(e.getMsgCode());
                objESaveSNInfoResB.setMsgDes(e.getMsgDes());
                objESaveSNInfoRes.setBody(objESaveSNInfoResB);
            }
        } else {
            objESaveSNInfoResB.setMsgCode("MG0006F");
            objESaveSNInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveSNInfoRes.setBody(objESaveSNInfoResB);
        }
        objESaveSNInfoRes.setStatus("00");
        return objESaveSNInfoRes;
    }


}
