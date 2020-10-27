package pnc.mesadmin.controller;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevSMInfo.GetAllDevSMInfoRes;
import pnc.mesadmin.dto.GetAllDevSMInfo.GetAllDevSMInfoResB;
import pnc.mesadmin.dto.GetDevSMInfo.GetDevSMInfoReqBD00;
import pnc.mesadmin.dto.GetDevSMInfo.GetDevSMInfoRes;
import pnc.mesadmin.dto.GetDevSMInfo.GetDevSMInfoResB;
import pnc.mesadmin.dto.SaveDevSMInfo.*;
import pnc.mesadmin.service.DevSMIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by PNC on 2017/8/21.
 */
@Controller
@RequestMapping("/DevSM")
public class DevSMController {

    @Resource
    private DevSMIService devSMIService;

    @RequestMapping(value = "/DevSMPG")
    public String getDevSM() {
        return "res/devsm/devsminfo";
    }


    //获取设备状态模型列表信息
    @RequestMapping(value = "/GetAllDevSMInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllDevSMInfoRes GetAllDevSMInfo(GetAllReq getAllReq) {
        GetAllDevSMInfoRes objEGetAllDevSMInfoRes = new GetAllDevSMInfoRes();
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
                objEGetAllDevSMInfoRes = devSMIService.QALLGetAllDevSMInfo(objEInitDataFields, pageInfo);
                objEGetAllDevSMInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllDevSMInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllDevSMInfoResB objEGetAllDevSMInfoResB = new GetAllDevSMInfoResB();
                objEGetAllDevSMInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllDevSMInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllDevSMInfoRes.setBody(objEGetAllDevSMInfoResB);
            }

        } else {
            GetAllDevSMInfoResB objEGetAllDevSMInfoResB = new GetAllDevSMInfoResB();
            objEGetAllDevSMInfoResB.setMsgCode("MG0006F");
            objEGetAllDevSMInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllDevSMInfoRes.setBody(objEGetAllDevSMInfoResB);
        }
        objEGetAllDevSMInfoRes.setStatus("00");

        return objEGetAllDevSMInfoRes;
    }

    //获取设备状态模型列表信息(新)
    @RequestMapping(value="/GetAllDevSMNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllDevSMNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(devSMIService.QALLGetAllDevSMNew(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }


    //获取设备状态模型信息
    @RequestMapping(value = "/GetDevSMInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetDevSMInfoRes GetDevSMInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetDevSMInfoRes objEGetDevSMInfoRes = new GetDevSMInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();

            GetDevSMInfoReqBD00 busData00 = CommonUtils.switchClass(GetDevSMInfoReqBD00.class, busData);

            // 分页
            if (getAllReq.getPageInfo() != null) {

            } else {  // 不分页
                try {
                    objEGetDevSMInfoRes = devSMIService.GetGetDevSMInfoRes(busData00.getDSMRd());
                    objEGetDevSMInfoRes.getBody().setMsgCode("0x00000");
                    objEGetDevSMInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetDevSMInfoResB objEGetDevSMInfoResB = new GetDevSMInfoResB();
                    objEGetDevSMInfoResB.setMsgCode(e.getMsgCode());
                    objEGetDevSMInfoResB.setMsgDes(e.getMsgDes());
                    objEGetDevSMInfoRes.setBody(objEGetDevSMInfoResB);
                }
            }
        } else {
            GetDevSMInfoResB objEGetDevSMInfoResB = new GetDevSMInfoResB();
            objEGetDevSMInfoResB.setMsgCode("MG0006F");
            objEGetDevSMInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetDevSMInfoRes.setBody(objEGetDevSMInfoResB);
        }

        objEGetDevSMInfoRes.setStatus("00");

        return objEGetDevSMInfoRes;
    }


    //保存设备状态模型
    @RequestMapping(value = "/SaveDevSMInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveDevSMInfoRes SaveDevSMInfo(SaveReq saveReq) {
        SaveDevSMInfoRes objESaveDevSMInfoRes = new SaveDevSMInfoRes();
        SaveDevSMInfoResB objESaveDevSMInfoResB = null;
        String rowData = saveReq.getBusData();
        // 新增
        if ("00".equals(saveReq.getExecType())) {
            SaveDevSMInfoReq00 objESaveDevSMInfoReq00 = CommonUtils.switchClass(SaveDevSMInfoReq00.class, rowData);

            try {
                objESaveDevSMInfoRes = devSMIService.AddSaveDevSMInfoRes(objESaveDevSMInfoReq00);
                objESaveDevSMInfoResB = new SaveDevSMInfoResB();
                objESaveDevSMInfoResB.setMsgCode("0x00000");
                objESaveDevSMInfoResB.setMsgDes("成功");
                objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
            } catch (SystemException e) {
                objESaveDevSMInfoResB = new SaveDevSMInfoResB();
                objESaveDevSMInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevSMInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
            }
        }
        // 删除
        else if ("01".equals(saveReq.getExecType())) {
            SaveDevSMInfoReq01 objESaveDevSMInfoReq01 = CommonUtils.switchClass(SaveDevSMInfoReq01.class, rowData);

            try {
                objESaveDevSMInfoRes = devSMIService.RmSaveDevSMInfoRes(objESaveDevSMInfoReq01);
                objESaveDevSMInfoResB = new SaveDevSMInfoResB();
                objESaveDevSMInfoResB.setMsgCode("0x00000");
                objESaveDevSMInfoResB.setMsgDes("成功");
                objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
            } catch (SystemException e) {
                objESaveDevSMInfoResB = new SaveDevSMInfoResB();
                objESaveDevSMInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevSMInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {

            SaveDevSMInfoReq02 objESaveDevSMInfoReq02 = CommonUtils.switchClass(SaveDevSMInfoReq02.class, rowData);

            try {
                objESaveDevSMInfoRes = devSMIService.ModSaveDevSMInfoRes(objESaveDevSMInfoReq02);
                objESaveDevSMInfoResB = new SaveDevSMInfoResB();
                objESaveDevSMInfoResB.setMsgCode("0x00000");
                objESaveDevSMInfoResB.setMsgDes("成功");
                objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
            } catch (SystemException e) {
                objESaveDevSMInfoResB = new SaveDevSMInfoResB();
                objESaveDevSMInfoResB.setMsgCode(e.getMsgCode());
                objESaveDevSMInfoResB.setMsgDes(e.getMsgDes());
                objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
            }
        } else {
            objESaveDevSMInfoResB = new SaveDevSMInfoResB();
            objESaveDevSMInfoResB.setMsgCode("MG0006F");
            objESaveDevSMInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02");
            objESaveDevSMInfoRes.setBody(objESaveDevSMInfoResB);
        }

        objESaveDevSMInfoRes.setStatus("00");
        return objESaveDevSMInfoRes;
    }
}
