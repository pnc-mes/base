package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoReqBD00;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoRes;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoResB;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveShiftInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.service.ShiftIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：班别管理Controller
 * 创建人：乔帅阳
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Shift")
public class ShiftController {

    @Resource
    private ShiftIService shiftIService;

    //获取班别管理页面
    @RequestMapping(value = "/ShiftPG")
    public String getToolPGPage() {
        return "base/shift/shift";
    }

    //  获取班别列表信息
    @RequestMapping(value = "/GetAllShiftsInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllShiftsInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(shiftIService.QALLselectAllShiftsInfoInfo(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //  获取班别信息
    @RequestMapping(value = "/GetShiftInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetShiftInfoRes GetShiftInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetShiftInfoRes objGetShiftInfoRes = new GetShiftInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetShiftInfoReqBD00 busData00 = CommonUtils.switchClass(GetShiftInfoReqBD00.class, busData);

            // 分页
            if (getAllReq.getPageInfo() != null) {

            } else {  // 不分页
                try {
                    objGetShiftInfoRes = shiftIService.GetselectByShiftsId(busData00.getShiftRd());
                    objGetShiftInfoRes.getBody().setMsgCode("0x00000");
                    objGetShiftInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetShiftInfoResB objGetShiftInfoResB = new GetShiftInfoResB();
                    objGetShiftInfoResB.setMsgCode(e.getMsgCode());
                    objGetShiftInfoResB.setMsgDes(e.getMsgDes());
                    objGetShiftInfoRes.setBody(objGetShiftInfoResB);
                }
            }
        } else {
            GetShiftInfoResB objGetShiftInfoResB = new GetShiftInfoResB();
            objGetShiftInfoResB.setMsgCode("MG0006F");
            objGetShiftInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objGetShiftInfoRes.setBody(objGetShiftInfoResB);
        }
        objGetShiftInfoRes.setStatus("00");
        return objGetShiftInfoRes;
    }

    //保存班别信息
    @RequestMapping(value = "/SaveShiftInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveShiftInfoRes SaveShiftInfo(HttpServletRequest request, SaveReq saveReq) {
        SaveShiftInfoRes objSaveShiftInfoRes = new SaveShiftInfoRes();
        SaveShiftInfoResB saveShiftInfoResB = new SaveShiftInfoResB();
        ShiftInfo shiftInfo = new ShiftInfo();
        String rowData = saveReq.getBusData();
        //新增
        if ("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveShiftInfoReqBD00 busData00 = CommonUtils.switchClass(SaveShiftInfoReqBD00.class, rowData);
            try {
                objSaveShiftInfoRes = shiftIService.AddinsertShiftInfo(busData00);
                saveShiftInfoResB.setMsgCode("0x00000");
                saveShiftInfoResB.setMsgDes("成功");
//                  objSaveShiftInfoRes.getBody().setMsgCode("0x00000");
//                  objSaveShiftInfoRes.getBody().setMsgDes("成功");
                objSaveShiftInfoRes.setBody(saveShiftInfoResB);
            } catch (SystemException e) {
                saveShiftInfoResB.setMsgCode(e.getMsgCode());
                saveShiftInfoResB.setMsgDes(e.getMsgDes());
                objSaveShiftInfoRes.setBody(saveShiftInfoResB);
            }
        }
        //删除
        else if ("01".equals(saveReq.getExecType())) {
            SaveShiftInfoReqBD01 busData01 = CommonUtils.switchClass(SaveShiftInfoReqBD01.class, rowData);
            try {
                objSaveShiftInfoRes = shiftIService.RmdeleteShiftInfo(busData01.getShiftRd());
                SaveShiftInfoResB body = new SaveShiftInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveShiftInfoRes.setBody(body);
            } catch (SystemException e) {
                saveShiftInfoResB.setMsgCode(e.getMsgCode());
                saveShiftInfoResB.setMsgDes(e.getMsgDes());
                objSaveShiftInfoRes.setBody(saveShiftInfoResB);
            }
        }
        //更新
        else if ("02".equals(saveReq.getExecType())) {
            SaveShiftInfoReqBD02 busData02 = CommonUtils.switchClass(SaveShiftInfoReqBD02.class, rowData);
            try {
                objSaveShiftInfoRes = shiftIService.ModupdateCustomerInfo(busData02);
                SaveShiftInfoResB body = new SaveShiftInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveShiftInfoRes.setBody(body);
            } catch (SystemException e) {
                saveShiftInfoResB.setMsgCode(e.getMsgCode());
                saveShiftInfoResB.setMsgDes(e.getMsgDes());
                objSaveShiftInfoRes.setBody(saveShiftInfoResB);
            }
        }
        //复制
        else if ("03".equals(saveReq.getExecType())) {

            SaveShiftInfoReqBD03 busData03 = CommonUtils.switchClass(SaveShiftInfoReqBD03.class, rowData);
            try {
                objSaveShiftInfoRes = shiftIService.copyShiftInfo(busData03, shiftInfo);
                SaveShiftInfoResB body = new SaveShiftInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                objSaveShiftInfoRes.setBody(body);
            } catch (SystemException e) {
                saveShiftInfoResB.setMsgCode(e.getMsgCode());
                saveShiftInfoResB.setMsgDes(e.getMsgDes());
                objSaveShiftInfoRes.setBody(saveShiftInfoResB);
            }
        }
        objSaveShiftInfoRes.setStatus("00");
        return objSaveShiftInfoRes;
    }

}