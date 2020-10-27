package pnc.mesadmin.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoRes;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoResB;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoReqBD00;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoRes;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoResB;
import pnc.mesadmin.dto.SaveDevTypeInfo.*;
import pnc.mesadmin.entity.DevTypeInfo;
import pnc.mesadmin.service.DeviceTypeIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备类型信息Controller
 * 创建人：蒋顾毅
 * 创建时间：2020-9-17
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
    @RequestMapping("/DeviceType")
public class DeviceTypeController {
    @Resource
    private DeviceTypeIService deviceTypeIService;

    //获取设备类型列表信息
    @RequestMapping(value = "/GetAllDevTypeInfo",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllDevTypeInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(deviceTypeIService.QALLselectAllDevTypeInfo(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取设备类型信息
    @RequestMapping(value = "/GetDevTypeInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetDevTypeInfoRes GetDevTypeInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetDevTypeInfoRes objEGetDevTypeInfoRes=new GetDevTypeInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetDevTypeInfoReqBD00 busData00 = CommonUtils.switchClass(GetDevTypeInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetDevTypeInfoRes = deviceTypeIService.GetselectByRuid(busData00.getDevTypeRd());
                    objEGetDevTypeInfoRes.getBody().setMsgCode("0x00000");
                    objEGetDevTypeInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetDevTypeInfoResB objEGetDevTypeInfoResB = new GetDevTypeInfoResB();
                    objEGetDevTypeInfoResB.setMsgCode(e.getMsgCode());
                    objEGetDevTypeInfoResB.setMsgDes(e.getMsgDes());
                    objEGetDevTypeInfoRes.setBody(objEGetDevTypeInfoResB);
                }
            }
        }else{
            GetDevTypeInfoResB objEGetDevTypeInfoResB= new GetDevTypeInfoResB();
            objEGetDevTypeInfoResB.setMsgCode("MG0006F");
            objEGetDevTypeInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetDevTypeInfoRes.setBody(objEGetDevTypeInfoResB);
        }

        objEGetDevTypeInfoRes.setStatus("00");

        return objEGetDevTypeInfoRes;
    }

    //保存设备类型信息
    @RequestMapping(value = "/SaveDevTypeInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveDevTypeInfoRes SaveDevTypeInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveDevTypeInfoRes saveDevTypeInfoRes = new SaveDevTypeInfoRes();

        SaveDevTypeInfoResB saveDevTypeInfoResB = new SaveDevTypeInfoResB();

        DevTypeInfo devTypeInfo = new DevTypeInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveDevTypeInfoReqBD00 busData00 = CommonUtils.switchClass(SaveDevTypeInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveDevTypeInfoRes = deviceTypeIService.AddinsertDevTypeInfo(busData00,devTypeInfo);
                SaveDevTypeInfoResB body = new SaveDevTypeInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevTypeInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevTypeInfoResB.setMsgCode(e.getMsgCode());
                saveDevTypeInfoResB.setMsgDes(e.getMsgDes());
                saveDevTypeInfoRes.setBody(saveDevTypeInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveDevTypeInfoReqBD01 busData01 = CommonUtils.switchClass(SaveDevTypeInfoReqBD01.class,rowData);
            try {
                saveDevTypeInfoRes = deviceTypeIService.RmdeleteDevTypeInfo(busData01.getDevTypeRd());
                SaveDevTypeInfoResB body = new SaveDevTypeInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevTypeInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevTypeInfoResB.setMsgCode(e.getMsgCode());
                saveDevTypeInfoResB.setMsgDes(e.getMsgDes());
                saveDevTypeInfoRes.setBody(saveDevTypeInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveDevTypeInfoReqBD02 busData02 = CommonUtils.switchClass(SaveDevTypeInfoReqBD02.class,rowData);
            try {
                saveDevTypeInfoRes = deviceTypeIService.ModupdateDevTypeInfo(busData02,devTypeInfo);
                SaveDevTypeInfoResB body = new SaveDevTypeInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevTypeInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevTypeInfoResB.setMsgCode(e.getMsgCode());
                saveDevTypeInfoResB.setMsgDes(e.getMsgDes());
                saveDevTypeInfoRes.setBody(saveDevTypeInfoResB);
            }
        }
        else{
            saveDevTypeInfoResB.setMsgCode("MG0006F");
            saveDevTypeInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveDevTypeInfoRes.setStatus("00");
        return saveDevTypeInfoRes;
    }
}
