package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoRes;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResB;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoReqBD00;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoRes;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoResB;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoReqBD00;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoRes;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoResB;
import pnc.mesadmin.dto.SaveUnitInfo.*;
import pnc.mesadmin.service.UnitIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：计量单位Controller
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Unit")
public class UnitController {

    @Resource
    private UnitIService unitIService;

    //获取计量单位页面
    @RequestMapping(value = "/UnitPG")
    public String getBatchLinfoPage() {
        return "base/unit/unit";
    }

    //dto查询计量单位列表
    @RequestMapping(value = "/GetAllUnitInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllUnitInfoRes GetAllUnitInfo(GetAllReq getAllReq) {
        GetAllUnitInfoRes objEGetAllUnitInfoRess = new GetAllUnitInfoRes();
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
                objEGetAllUnitInfoRess = unitIService.QALLGetAllUnitInfoRes(objEInitDataFields, pageInfo);
                objEGetAllUnitInfoRess.getBody().setMsgCode("0x00000");
                objEGetAllUnitInfoRess.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllUnitInfoResB objEGetAllUnitInfoResB = new GetAllUnitInfoResB();
                objEGetAllUnitInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllUnitInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllUnitInfoRess.setBody(objEGetAllUnitInfoResB);

            }
        } else {
            GetAllUnitInfoResB objEGetAllUnitInfoResB = new GetAllUnitInfoResB();
            objEGetAllUnitInfoResB.setMsgCode("MG0006F");
            objEGetAllUnitInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllUnitInfoRess.setBody(objEGetAllUnitInfoResB);
        }
        objEGetAllUnitInfoRess.setStatus("00");
        return objEGetAllUnitInfoRess;
    }

    /**
     * 查询计量单位列表(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllUnitNew")
    @ResponseBody
    public BaseResponse GetAllUnitNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(unitIService.QALLUnitNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto查询计量单位
    @RequestMapping(value = "/GetUnitInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetUnitInfoRes GetUnitInfo(GetAllReq getAllReq) {
        GetUnitInfoRes objEGetWCInfoRes = new GetUnitInfoRes();

        if ("00".equals(getAllReq.getExecType())) {

            GetUnitInfoReqBD00 objEGetUnitInfoReqBD00 = CommonUtils.switchClass(GetUnitInfoReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {

                try {
                    objEGetWCInfoRes = unitIService.GetGetUnitInfoRes(objEGetUnitInfoReqBD00);
                    objEGetWCInfoRes.getBody().setMsgCode("0x00000");
                    objEGetWCInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetUnitInfoResB objEGetWCInfoResB = new GetUnitInfoResB();
                    objEGetWCInfoResB.setMsgCode(e.getMsgCode());
                    objEGetWCInfoResB.setMsgDes(e.getMsgDes());
                    objEGetWCInfoRes.setBody(objEGetWCInfoResB);
                }
            }
        } else {
            GetUnitInfoResB objEGetWCInfoResB = new GetUnitInfoResB();
            objEGetWCInfoResB.setMsgCode("MG0006F");
            objEGetWCInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetWCInfoRes.setBody(objEGetWCInfoResB);
        }
        objEGetWCInfoRes.setStatus("00");
        return objEGetWCInfoRes;
    }

    //dto新增工作中心
    @RequestMapping(value = "/SaveUnitInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveUnitInfoRes SaveUnitInfo(SaveReq saveReq) {
        SaveUnitInfoRes objESaveUnitInfoRes = new SaveUnitInfoRes();
        SaveUnitInfoResB objESaveUnitInfoResB = new SaveUnitInfoResB();

        if ("00".equals(saveReq.getExecType())) {
            SaveUnitInfoReqBD00 objESaveUnitInfoReqBD00 = CommonUtils.switchClass(SaveUnitInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveUnitInfoRes = unitIService.AddSaveUnitInfoRes(objESaveUnitInfoReqBD00);
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode("0x00000");
                objESaveUnitInfoResB.setMsgDes("成功");
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            } catch (SystemException e) {
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode(e.getMsgCode());
                objESaveUnitInfoResB.setMsgDes(e.getMsgDes());
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveUnitInfoReqBD01 objESaveUnitInfoReqBD01 = CommonUtils.switchClass(SaveUnitInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveUnitInfoRes = unitIService.RmSaveUnitInfoRes(objESaveUnitInfoReqBD01);
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode("0x00000");
                objESaveUnitInfoResB.setMsgDes("成功");
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            } catch (SystemException e) {
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode(e.getMsgCode());
                objESaveUnitInfoResB.setMsgDes(e.getMsgDes());
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            }

        } else if ("02".equals(saveReq.getExecType())) {
            SaveUnitInfoReqBD02 objESaveUnitInfoReqBD02 = CommonUtils.switchClass(SaveUnitInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveUnitInfoRes = unitIService.ModSaveUnitInfoRes(objESaveUnitInfoReqBD02);
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode("0x00000");
                objESaveUnitInfoResB.setMsgDes("成功");
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            } catch (SystemException e) {
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode(e.getMsgCode());
                objESaveUnitInfoResB.setMsgDes(e.getMsgDes());
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            }

        } else if ("03".equals(saveReq.getExecType())) {
            SaveUnitInfoReqBD03 objESaveUnitInfoReqBD03 = CommonUtils.switchClass(SaveUnitInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveUnitInfoRes = unitIService.AddSaveUnitInfoRes(objESaveUnitInfoReqBD03);
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode("0x00000");
                objESaveUnitInfoResB.setMsgDes("成功");
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            } catch (SystemException e) {
                objESaveUnitInfoResB = new SaveUnitInfoResB();
                objESaveUnitInfoResB.setMsgCode(e.getMsgCode());
                objESaveUnitInfoResB.setMsgDes(e.getMsgDes());
                objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
            }

        } else {
            objESaveUnitInfoResB.setMsgCode("MG0006F");
            objESaveUnitInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveUnitInfoRes.setBody(objESaveUnitInfoResB);
        }
        objESaveUnitInfoRes.setStatus("00");
        return objESaveUnitInfoRes;
    }


}
