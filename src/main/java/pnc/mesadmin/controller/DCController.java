package pnc.mesadmin.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoRes;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoResB;
import pnc.mesadmin.dto.GetDVInfo.GetDVInfoReqBD00;
import pnc.mesadmin.dto.GetDVInfo.GetDVInfoRes;
import pnc.mesadmin.dto.GetDVInfo.GetDVInfoResB;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoRes;
import pnc.mesadmin.dto.GetDVTreeInfo.GetDVTreeInfoResB;
import pnc.mesadmin.dto.GetDcInfo.GetDcInfoReqBD00;
import pnc.mesadmin.dto.GetDcInfo.GetDcInfoRes;
import pnc.mesadmin.dto.GetDcInfo.GetDcInfoResB;
import pnc.mesadmin.dto.SaveDcInfo.*;
import pnc.mesadmin.entity.DCInfo;
import pnc.mesadmin.entity.DCVerInfo;
import pnc.mesadmin.entity.UserInfo;
import pnc.mesadmin.service.DCIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息Controller
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/DC")
public class DCController {

    @Resource
    private DCIService DCIService;

    @RequestMapping(value = "/DCPG")
    public String dcPG() {
        return "process/datacj/datacj";
    }

    @RequestMapping(value = "/DCAdd")
    public String dcAdd() {
        return "process/datacj/dataAdd";
    }

    @RequestMapping(value = "/GetAllNewDcInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllNewDcInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(DCIService.QALLGetAllDcInfoRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    // 主界面获取数据采集列表信息
    @ResponseBody
    @RequestMapping(value = "/GetAllDcInfo",method = RequestMethod.POST)
    public GetAllDcInfoRes GetAllDcInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建返回的DTO实体对象
        GetAllDcInfoRes objEGetAllDcInfoRes  = new GetAllDcInfoRes();
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
                    // 调用Service，获取数据采集列表信息
                    objEGetAllDcInfoRes = DCIService.QALLselectDCInfos(objEInitDataFields, pageInfo);
                    // 保存查询返回的成功消息
                    objEGetAllDcInfoRes.getBody().setMsgCode("0x00000");
                    objEGetAllDcInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetAllDcInfoResB objEGetAllDcInfoResB = new GetAllDcInfoResB();
                    // 保存查询返回的失败消息
                    objEGetAllDcInfoResB.setMsgCode(e.getMsgCode());
                    objEGetAllDcInfoResB.setMsgDes(e.getMsgDes());
                    objEGetAllDcInfoRes.setBody(objEGetAllDcInfoResB);
                }

        }
        else {
            GetAllDcInfoResB objEGetAllDcInfoResB = new GetAllDcInfoResB();
            objEGetAllDcInfoResB.setMsgCode("MG0006F");
            objEGetAllDcInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            objEGetAllDcInfoRes.setBody(objEGetAllDcInfoResB);
        }
        objEGetAllDcInfoRes.setStatus("00");

        return objEGetAllDcInfoRes;
    }

    // 获取数据采集信息，也即二级树形结构
    @ResponseBody
    @RequestMapping(value = "/GetDcInfo",method = RequestMethod.POST)
    public GetDcInfoRes GetDcInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建返回的DTO实体对象
        GetDcInfoRes objEGetDcInfoRes = new GetDcInfoRes();
        if("00".equals(getAllReq.getExecType())){
            // json转换成实体类对象
            GetDcInfoReqBD00 busData00 = CommonUtils.switchClass(GetDcInfoReqBD00.class,getAllReq.getBusData());
            // 不分页
            try {
                // 调用Service，获取数据采集信息
                objEGetDcInfoRes = DCIService.GetselectDCInfoByDcRd(busData00.getDcRd());
                // 保存查询返回的成功消息
                objEGetDcInfoRes.getBody().setMsgCode("0x00000");
                objEGetDcInfoRes.getBody().setMsgDes("成功");

            }catch (SystemException e){
                GetDcInfoResB objEGetDcInfoResB = new GetDcInfoResB();
                // 保存查询返回的失败消息
                objEGetDcInfoResB.setMsgCode(e.getMsgCode());
                objEGetDcInfoResB.setMsgDes(e.getMsgDes());
                objEGetDcInfoRes.setBody(objEGetDcInfoResB);
            }
        }

        objEGetDcInfoRes.setStatus("00");
        return objEGetDcInfoRes;
    }

    // 获取数据采集版本列表信息(GetDVTreeInfo)
    @ResponseBody
    @RequestMapping(value = "/GetDVTreeInfo",method = RequestMethod.POST)
    public GetDVTreeInfoRes GetDVTreeInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建返回的DTO实体对象
        GetDVTreeInfoRes objEGetDVTreeInfoRes = new GetDVTreeInfoRes();

        if("00".equals(getAllReq.getExecType())){
            // json转换成实体类对象
            GetDVTreeInfoReqBD00 busData00 = CommonUtils.switchClass(GetDVTreeInfoReqBD00.class,getAllReq.getBusData());

            // 不分页
            try {
                // 调用Service，获取数据采集所有版本信息
                 objEGetDVTreeInfoRes = DCIService.GetselectDCVerInfoByDcRd(busData00.getDcRd());
                // 保存查询返回的成功消息
                objEGetDVTreeInfoRes.getBody().setMsgCode("0x00000");
                objEGetDVTreeInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                // 保存查询返回的失败消息
                GetDVTreeInfoResB objEGetDVTreeInfoResB = new GetDVTreeInfoResB();
                 objEGetDVTreeInfoResB.setMsgCode(e.getMsgCode());
                 objEGetDVTreeInfoResB.setMsgDes(e.getMsgDes());
                objEGetDVTreeInfoRes.setBody(objEGetDVTreeInfoResB);
            }
        }
        objEGetDVTreeInfoRes.setStatus("00");
        return objEGetDVTreeInfoRes;
    }

    // 获取数据采集版本详细信息(GetDVInfo)
    @ResponseBody
    @RequestMapping(value = "/GetDVInfo",method = RequestMethod.POST)
    public GetDVInfoRes GetDVInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建实体对象
        GetDVInfoRes objEGetDVInfoRes = new GetDVInfoRes();

        if("00".equals(getAllReq.getExecType())){
            // json转换成实体类对象
            GetDVInfoReqBD00 busData00 = CommonUtils.switchClass(GetDVInfoReqBD00.class,getAllReq.getBusData());
            // 不分页
            try {
                // 调用Service，获取数据采集版本详细信息
                objEGetDVInfoRes = DCIService.GetselectDCVerInfoByDCVerRd(busData00.getDCVerRd());
                // 保存查询返回的成功消息
                objEGetDVInfoRes.getBody().setMsgCode("0x00000");
                objEGetDVInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetDVInfoResB objEGetDVInfoResB = new GetDVInfoResB();
                objEGetDVInfoResB.setMsgCode(e.getMsgCode());
                objEGetDVInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objEGetDVInfoRes.setStatus("00");

        return objEGetDVInfoRes;
    }

    // 保存数据采集信息(SaveDcInfo)
    @ResponseBody
    @RequestMapping(value = "/SaveDcInfo",method = RequestMethod.POST)
    public SaveDcInfoRes SaveDcInfo(SaveReq saveReq, HttpServletRequest request) {
        // 创建返回的DTO实体对象
        SaveDcInfoRes objESaveDcInfoRes = new SaveDcInfoRes();
        SaveDcInfoResB objESaveDcInfoResB = new SaveDcInfoResB();
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        //
        DCInfo objDCInfo = new DCInfo();
        // 转换成JsonObject实体类
        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveDcInfoReqBD00 argSaveDcInfoReqBD00 = CommonUtils.switchClass(SaveDcInfoReqBD00.class,saveReq.getBusData());
            try {
                objESaveDcInfoRes = DCIService.AddsaveDCInfo(argSaveDcInfoReqBD00);
                objESaveDcInfoResB.setMsgCode("0x00000");
                objESaveDcInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveDcInfoResB.setMsgCode(e.getMsgCode());
                objESaveDcInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        // 删除，这里删除的是根节点，以及对应下面所有的子节点
        else if("01".equals(saveReq.getExecType())){
            SaveDcInfoReqBD01 busData01 = CommonUtils.switchClass(SaveDcInfoReqBD01.class,saveReq.getBusData());
            try {
                objESaveDcInfoRes = DCIService.RmdeleteDcInfoByRuid(busData01.getDcRd());
                objESaveDcInfoResB.setMsgCode("0x00000");
                objESaveDcInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveDcInfoResB.setMsgCode(e.getMsgCode());
                objESaveDcInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveDcInfoReqBD02 busData02 = CommonUtils.switchClass(SaveDcInfoReqBD02.class,saveReq.getBusData());
            try {
                objDCInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                objDCInfo.setLastModifyTime(new Date());
                objDCInfo.setDcName(busData02.getDCName());
                // 更新根节点以及下面所有的对应子节点信息
                objESaveDcInfoRes = DCIService.Addupdate(busData02,objDCInfo);
                objESaveDcInfoResB.setMsgCode("0x00000");
                objESaveDcInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveDcInfoResB.setMsgCode(e.getMsgCode());
                objESaveDcInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        // 复制
        else if("03".equals(saveReq.getExecType())){
            SaveDcInfoReqBD03 busData03 = CommonUtils.switchClass(SaveDcInfoReqBD03.class,saveReq.getBusData());
            try {
                // 新增版本
                objESaveDcInfoRes = DCIService.AddargSaveDcInfoReqBD03(busData03);
                objESaveDcInfoResB.setMsgCode("0x00000");
                objESaveDcInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveDcInfoResB.setMsgCode(e.getMsgCode());
                objESaveDcInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        // 新增版本
        else if("04".equals(saveReq.getExecType())){
            SaveDcInfoReqBD04 busData04 = CommonUtils.switchClass(SaveDcInfoReqBD04.class,saveReq.getBusData());
            try {
                DCVerInfo objDCVerInfo = new DCVerInfo();
                objDCVerInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                objDCVerInfo.setLastModifyTime(new Date());
                objDCVerInfo.setDcName(busData04.getDCName());
                objDCVerInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                objDCVerInfo.setCreateTime(new Date());

                // 新增版本
                objESaveDcInfoRes = DCIService.AddsaveDCVerInfo(busData04,objDCVerInfo);
                objESaveDcInfoResB.setMsgCode("0x00000");
                objESaveDcInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveDcInfoResB.setMsgCode(e.getMsgCode());
                objESaveDcInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        // 删除版本 05
        else if ("05".equals(saveReq.getExecType())){
            SaveDcInfoReqBD05 busData05 = CommonUtils.switchClass(SaveDcInfoReqBD05.class,saveReq.getBusData());
            try {
                objESaveDcInfoRes = DCIService.RmdeleteDcVerInfoByRuid(busData05.getDCVerRd());
                objESaveDcInfoResB.setMsgCode("0x00000");
                objESaveDcInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveDcInfoResB.setMsgCode(e.getMsgCode());
                objESaveDcInfoResB.setMsgDes(e.getMsgDes());
            }

        }
        else{
            objESaveDcInfoResB.setMsgCode("MG0006F");
            objESaveDcInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03、04、05");
        }

        objESaveDcInfoRes.setBody(objESaveDcInfoResB);
        objESaveDcInfoRes.setStatus("00");
        return objESaveDcInfoRes;
    }
}
