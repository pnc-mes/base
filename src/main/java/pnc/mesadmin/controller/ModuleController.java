package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoReqBD00;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoRes;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResB;
import pnc.mesadmin.dto.GetAllBHChgInfo.GetAllBHChgInfoResBD;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoRes;
import pnc.mesadmin.dto.GetAllBOMInfo.GetAllBOMInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoRes;
import pnc.mesadmin.dto.GetAllMdInfo.GetAllMdInfoResB;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoReqBD00;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoRes;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResB;
import pnc.mesadmin.dto.GetBHChgDtlInfo.GetBHChgDtlInfoResBD;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoReqBD00;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoRes;
import pnc.mesadmin.dto.GetBOMInfo.GetBOMInfoResB;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoReqBD00;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoRes;
import pnc.mesadmin.dto.GetBOMTreeInfo.GetBOMTreeInfoResB;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoReqBD00;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoReqBD01;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoRes;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResB;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoReq00;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoRes;
import pnc.mesadmin.dto.GetMaTotalInfo.GetMaTotalInfoResB;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoReqBD00;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoRes;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoResB;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoReq00;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoRes;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoResB;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoReqBD00;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoReqBD03;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoRes;
import pnc.mesadmin.dto.SaveBomInfo.SaveBomInfoResB;
import pnc.mesadmin.dto.SaveImportRes.SaveImportRes;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveMdInfo.*;
import pnc.mesadmin.dto.SysVerInfoDto.SysVerInfoSaveDto;
import pnc.mesadmin.service.BOMIService;
import pnc.mesadmin.service.CkTkIService;
import pnc.mesadmin.service.ModuleIService;
import pnc.mesadmin.service.PickIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：菜单配置管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-12-14
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/Module")
public class ModuleController {

    @Resource
    private ModuleIService moduleIService;

    @RequestMapping(value = "/MenuConfigPG")
    public String MdView() {
        return "sys/menuconfig";
    }

    //获取模块列表信息
    @RequestMapping(value = "/GetAllMdInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllMdInfoRes getAllMdInfoRes(GetAllReq getAllReq) {
        GetAllMdInfoRes objEGetAllMdInfoRes = new GetAllMdInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            GetMdInfoReqBD00 objEGetMdInfoReqBD00 = CommonUtils.switchClass(GetMdInfoReqBD00.class, getAllReq.getBusData());
            try {
                objEGetAllMdInfoRes = moduleIService.GetAllMdInfo(objEGetMdInfoReqBD00);
                objEGetAllMdInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllMdInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllMdInfoResB objEGetAllMdInfoResB = new GetAllMdInfoResB();
                objEGetAllMdInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllMdInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllMdInfoRes.setBody(objEGetAllMdInfoResB);
            }
        }

        objEGetAllMdInfoRes.setStatus("00");

        return objEGetAllMdInfoRes;
    }

    //获取模块信息
    @RequestMapping(value = "/GetMdInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetMdInfoRes getMdInfoRes(GetAllReq getAllReq) {
        GetMdInfoRes objEGetMdInfoRes = new GetMdInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            String busData = getAllReq.getBusData();
            GetMdInfoReqBD00 objEGetMdInfoReqBD00 = CommonUtils.switchClass(GetMdInfoReqBD00.class, busData);

            try {
                objEGetMdInfoRes = moduleIService.GetMdInfo(objEGetMdInfoReqBD00);
                objEGetMdInfoRes.getBody().setMsgCode("0x00000");
                objEGetMdInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetMdInfoResB objEGetMdInfoResB = new GetMdInfoResB();
                objEGetMdInfoResB.setMsgCode(e.getMsgCode());
                objEGetMdInfoResB.setMsgDes(e.getMsgDes());
                objEGetMdInfoRes.setBody(objEGetMdInfoResB);
            }
        }

        objEGetMdInfoRes.setStatus("00");

        return objEGetMdInfoRes;
    }

    //保存模块信息
    @RequestMapping(value = "/SaveMdInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveMdInfoRes saveMdInfoRes(SaveReq saveReq) {
        SaveMdInfoRes objESaveMdInfoRes = new SaveMdInfoRes();
        SaveMdInfoResB objESaveMdInfoResB = new SaveMdInfoResB();
        String busData = saveReq.getBusData();

        try {
            if ("00".equals(saveReq.getExecType())) {
                SaveMdInfoReqBD00 objESaveMdInfoReqBD00 = CommonUtils.switchClass(SaveMdInfoReqBD00.class, busData);
                moduleIService.AddMdInfo(objESaveMdInfoReqBD00);

                objESaveMdInfoResB.setMsgCode("0x00000");
                objESaveMdInfoResB.setMsgDes("成功");
            } else if ("01".equals(saveReq.getExecType())) {
                SaveMdInfoReqBD01 objESaveMdInfoReqBD01 = CommonUtils.switchClass(SaveMdInfoReqBD01.class, busData);
                moduleIService.RmMdInfo(objESaveMdInfoReqBD01);

                objESaveMdInfoResB.setMsgCode("0x00000");
                objESaveMdInfoResB.setMsgDes("成功");
            } else if ("02".equals(saveReq.getExecType())) {
                SaveMdInfoReqBD02 objESaveMdInfoReqBD02 = CommonUtils.switchClass(SaveMdInfoReqBD02.class, busData);
                moduleIService.ModMdInfo(objESaveMdInfoReqBD02);

                objESaveMdInfoResB.setMsgCode("0x00000");
                objESaveMdInfoResB.setMsgDes("成功");
            }

        } catch (SystemException e) {
            objESaveMdInfoResB.setMsgCode(e.getMsgCode());
            objESaveMdInfoResB.setMsgDes(e.getMsgDes());
        }

        objESaveMdInfoRes.setBody(objESaveMdInfoResB);
        objESaveMdInfoRes.setStatus("00");

        return objESaveMdInfoRes;
    }


    //修改接口
    @RequestMapping(value = "/SaveSysVerInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes SaveSysVerInfo(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        SysVerInfoSaveDto request = CommonUtils.switchClass(SysVerInfoSaveDto.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) { //新增
                baseResponse = moduleIService.AddSysVerInfo00(request);
            } else if ("01".equals(saveReq.getExecType())) { //删除
                baseResponse = moduleIService.AddSysVerInfo01(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("删除成功");
                baseResponse.setBody(baseResBody);
            } else if ("03".equals(saveReq.getExecType())) {//激活
                baseResponse = moduleIService.AddSysVerInfo03(request);
                baseResBody.setMsgCode("0x00000");
                baseResBody.setMsgDes("修改成功");
                baseResponse.setBody(baseResBody);
            }
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //获取所有系统版本
    @RequestMapping(value = "/GetAllSysVerInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllSysVerInfo(GetAllReq getAllReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        SysVerInfoSaveDto request = null;
        if (getAllReq.getBusData() != null) {
            request = CommonUtils.switchClass(SysVerInfoSaveDto.class, getAllReq.getBusData());
        }
        try {
            baseResponse = moduleIService.GetAllSysVerInfo(request);
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

    //获取所有菜单信息
    @RequestMapping(value = "/GetAllResInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllResInfo() {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        try {
            baseResponse = moduleIService.GetAllResInfo();
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }
}
