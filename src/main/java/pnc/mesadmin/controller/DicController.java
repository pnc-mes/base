package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoReqBD00;
import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoRes;
import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoResB;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoReqBD00;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoRes;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoResB;
import pnc.mesadmin.dto.GetDicLanTypeInfo.GetDicLanTypeInfoRes;
import pnc.mesadmin.dto.GetDicLanTypeInfo.GetDicLanTypeInfoResB;
import pnc.mesadmin.dto.SaveDicInfo.*;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.DicInfo;
import pnc.mesadmin.entity.UserInfo;
import pnc.mesadmin.service.DicIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息Controller
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Dic")
public class DicController {

    @Resource
    private DicIService dicIService;
    // 加载页面
    @RequestMapping(value = "/DicPG")
    public String toDicInfo(){
        // 页面跳转
        return "sys/dic/dicInfo";
    }
    /*// 新增一条表格数据的弹窗链接
    @RequestMapping(value = "/addDicPG")
    public String addDicInfo(){
        // 页面跳转
        return "sys/dic/dicAdd";
    }
    // 编辑一条表格数据的弹窗链接
    @RequestMapping(value = "/editDicPG")
    public String editDicInfo(){
        // 页面跳转
        return "sys/dic/dicEdit";
    }*/

    // 获取语言类别信息
    @ResponseBody
    @RequestMapping(value = "/GetDicLanType",method = RequestMethod.POST)
    public GetDicLanTypeInfoRes GetDicLanTypeInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建实体对象
        GetDicLanTypeInfoRes getDicLanTypeInfoRes = new GetDicLanTypeInfoRes();

        GetDicLanTypeInfoResB getDicLanTypeInfoB = new GetDicLanTypeInfoResB();

        if("00".equals(getAllReq.getExecType())){
            // 不分页处理
            try {
                // 调用Service，加载树形结构
                getDicLanTypeInfoRes = dicIService.getDicTree();
                // 保存查询返回的成功消息
                getDicLanTypeInfoRes.getBody().setMsgCode("0x00000");
                getDicLanTypeInfoRes.getBody().setMsgDes("成功！");

            }catch (SystemException e){
                // 保存查询返回的失败消息
                getDicLanTypeInfoB.setMsgCode(e.getMsgCode());
                getDicLanTypeInfoB.setMsgDes(e.getMsgDes());
                getDicLanTypeInfoRes.setBody(getDicLanTypeInfoB);
            }

        }
        getDicLanTypeInfoRes.setStatus("00");

        return getDicLanTypeInfoRes;
    }

    // 获取字典列表信息
    @ResponseBody
    @RequestMapping(value = "/GetAllDicInfo",method = RequestMethod.POST)
    public GetAllDicInfoRes GetAllDicInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建实体对象
        GetAllDicInfoRes getAllDicInfoRes = new GetAllDicInfoRes();

        GetAllDicInfoResB getAllDicInfoB = new GetAllDicInfoResB();

        if("00".equals(getAllReq.getExecType())){
            GetAllDicInfoReqBD00 busData00 = CommonUtils.switchClass(GetAllDicInfoReqBD00.class,getAllReq.getBusData());
            // 分页
            // TODO 待处理分页
            try {
                // 调用Service，查询语言信息列表
                getAllDicInfoRes = dicIService.getDicInfoListByLanCode(busData00.getLanCode());
                getAllDicInfoRes.getBody().setMsgCode("0x00000");
                getAllDicInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                getAllDicInfoB.setMsgCode(e.getMsgCode());
                getAllDicInfoB.setMsgDes(e.getMsgDes());
                getAllDicInfoRes.setBody(getAllDicInfoB);
            }

        }
        getAllDicInfoRes.setStatus("00");

        return getAllDicInfoRes;
    }

    // 获取字典信息
    @ResponseBody
    @RequestMapping(value = "/GetDicInfo",method = RequestMethod.POST)
    public GetDicInfoRes GetDicInfo(GetAllReq getAllReq, HttpServletRequest request){
        // 创建实体对象
        GetDicInfoRes getDicInfoRes = new GetDicInfoRes();

        GetDicInfoResB getDicInfoB = new GetDicInfoResB();

        if("00".equals(getAllReq.getExecType())){

            GetDicInfoReqBD00 busData00 = CommonUtils.switchClass(GetDicInfoReqBD00.class,getAllReq.getBusData());

            // 不分页
            try {
                getDicInfoRes = dicIService.getDicInfoByGuid(busData00.getDicRd());
                getDicInfoRes.getBody().setMsgCode("0x00000");
                getDicInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                getDicInfoB.setMsgCode(e.getMsgCode());
                getDicInfoB.setMsgDes(e.getMsgDes());
                getDicInfoRes.setBody(getDicInfoB);
            }
            }
        getDicInfoRes.setStatus("00");
        return getDicInfoRes;
    }

    // 保存字典信息
    @ResponseBody
    @RequestMapping(value = "/SaveDicInfo",method = RequestMethod.POST)
    public SaveDicInfoRes SaveDicInfo(SaveReq saveReq, HttpServletRequest request){
        // 创建实体对象
        SaveDicInfoRes saveDicInfoRes = new SaveDicInfoRes();

        SaveDicInfoResB saveDicInfoBody = new SaveDicInfoResB();

        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");

        DicInfo dicInfo = new DicInfo();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveDicInfoReqBD00 busData00 = CommonUtils.switchClass(SaveDicInfoReqBD00.class,saveReq.getBusData());
            // 直接可以获取的表单数据
            dicInfo.setCreator(userInfo.getRealName());
            dicInfo.setCreateTime(new Date());
            try {
                saveDicInfoRes = dicIService.saveDicInfo(busData00, dicInfo);
                saveDicInfoRes.getBody().setMsgCode("0x00000");
                saveDicInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                saveDicInfoBody.setMsgCode(e.getMsgCode());
                saveDicInfoBody.setMsgDes(e.getMsgDes());
                saveDicInfoRes.setBody(saveDicInfoBody);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            List<SaveDicInfoReqBD01> objESaveDIList = Arrays.asList(CommonUtils.switchClass(SaveDicInfoReqBD01[].class,saveReq.getBusData()));
            try {
                saveDicInfoRes = dicIService.deleteDicInfoByRuid(objESaveDIList);
                saveDicInfoRes.getBody().setMsgCode("0x00000");
                saveDicInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                saveDicInfoBody.setMsgCode(e.getMsgCode());
                saveDicInfoBody.setMsgDes(e.getMsgDes());
                saveDicInfoRes.setBody(saveDicInfoBody);
            }
        }
        // 编辑
        else{
            SaveDicInfoReqBD02 busData02 = CommonUtils.switchClass(SaveDicInfoReqBD02.class,saveReq.getBusData());
            try {
                dicInfo.setLastModifyMan(userInfo.getRealName());
                dicInfo.setLastModifyTime(new Date());
                saveDicInfoRes = dicIService.update(busData02,dicInfo);
                saveDicInfoRes.getBody().setMsgCode("0x00000");
                saveDicInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                saveDicInfoBody.setMsgCode(e.getMsgCode());
                saveDicInfoBody.setMsgDes(e.getMsgDes());
                saveDicInfoRes.setBody(saveDicInfoBody);
            }
        }
        saveDicInfoRes.setStatus("00");
        return saveDicInfoRes;
    }
}
