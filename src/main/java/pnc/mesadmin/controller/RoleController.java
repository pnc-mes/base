package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoRes;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoResB;
import pnc.mesadmin.dto.GetRoleInfo.GetRoleInfoBusData00;
import pnc.mesadmin.dto.GetRoleInfo.GetRoleInfoRes;
import pnc.mesadmin.dto.GetRoleInfo.GetRoleInfoResB;
import pnc.mesadmin.dto.SaveRoleInfo.*;
import pnc.mesadmin.service.RoleIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by test on 2017/5/9.
 */
@Controller
@Scope("prototype")
@RequestMapping("/Role")
public class RoleController {
    @Resource
    private RoleIService roleIService;

    @RequestMapping("/RolePG")
    public String roleinfo(){
        return "sys/role/roleinfo";
    }

    //获取角色列表信息
    @RequestMapping("/GetAllRoleInfo")
    @ResponseBody
    public GetAllRoleInfoRes GetAllRoleInfo(GetAllReq getAllReq){

        GetAllRoleInfoRes objEGetAllRoleInfoRes = new GetAllRoleInfoRes();

        if("00".equals(getAllReq.getExecType())) {
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
                objEGetAllRoleInfoRes = roleIService.getRoleInfoList(objEInitDataFields, pageInfo);
                objEGetAllRoleInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllRoleInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllRoleInfoResB objEGetAllRoleInfoRB = new GetAllRoleInfoResB();
                objEGetAllRoleInfoRB.setMsgCode(e.getMsgCode());
                objEGetAllRoleInfoRB.setMsgDes(e.getMsgDes());
                objEGetAllRoleInfoRes.setBody(objEGetAllRoleInfoRB);
            }
        }
        objEGetAllRoleInfoRes.setStatus("00");

        return objEGetAllRoleInfoRes;
    }

    //获取角色页面
    @RequestMapping(value = "/query")
    public String getRoleInfoPage(){

        return "sys/role/role";
    }

    //获取角色新增页面
    @RequestMapping(value = "/add")
    public String getRoleInfoAddPage(){

        return "sys/role/roleAdd";
    }

    //获取角色编辑页面
    @RequestMapping(value = "/edit")
    public String getRoleInfoEditPage(){

        return "sys/role/roleEdit";
    }


    //获取角色信息
    @RequestMapping(value = "/GetRoleInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetRoleInfoRes GetRoleInfo(HttpServletRequest request, GetAllReq getAllReq){

        GetRoleInfoRes objEGetRoleInfoRes = new GetRoleInfoRes();

        if("00".equals(getAllReq.getExecType())){
            GetRoleInfoBusData00 busData00 = CommonUtils.switchClass(GetRoleInfoBusData00.class,getAllReq.getBusData());

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetRoleInfoRes = roleIService.getRoleInfo(busData00);
                    objEGetRoleInfoRes.getBody().setMsgCode("0x00000");
                    objEGetRoleInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetRoleInfoResB objEGetRoleInfoResBody = new GetRoleInfoResB();
                    objEGetRoleInfoResBody.setMsgCode(e.getMsgCode());
                    objEGetRoleInfoResBody.setMsgDes(e.getMsgDes());
                    objEGetRoleInfoRes.setBody(objEGetRoleInfoResBody);
                }
            }
        }

        objEGetRoleInfoRes.setStatus("00");

        return objEGetRoleInfoRes;
    }

    //保存角色信息
    @RequestMapping("/SaveRoleInfo")
    @ResponseBody
    public SaveRoleInfoRes SaveRoleInfo(HttpServletRequest request, SaveReq saveReq){

        SaveRoleInfoRes objESaveRoleInfoRes = new SaveRoleInfoRes();
        SaveRoleInfoResB objESaveRoleInfoRB = new SaveRoleInfoResB();

        String busData = saveReq.getBusData();

        if("00".equals(saveReq.getExecType())){
            //新增

            SaveRoleInfoReqBD00 busData00 = CommonUtils.switchClass(SaveRoleInfoReqBD00.class, busData);

            try{
                objESaveRoleInfoRes = roleIService.insert(busData00);
                objESaveRoleInfoRB.setMsgCode("0x00000");
                objESaveRoleInfoRB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveRoleInfoRB.setMsgCode(e.getMsgCode());
                objESaveRoleInfoRB.setMsgDes(e.getMsgDes());
            }

        }else if("01".equals(saveReq.getExecType())){
            //删除
            SaveRoleInfoReqBD01 busData01 =  CommonUtils.switchClass(SaveRoleInfoReqBD01.class, busData);

            try{
                objESaveRoleInfoRes = roleIService.remove(busData01);
                objESaveRoleInfoRB.setMsgCode("0x00000");
                objESaveRoleInfoRB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveRoleInfoRB.setMsgCode(e.getMsgCode());
                objESaveRoleInfoRB.setMsgDes(e.getMsgDes());
            }
        }else if("02".equals(saveReq.getExecType())) {
            //编辑
            SaveRoleInfoReqBD02 busData02 =   CommonUtils.switchClass(SaveRoleInfoReqBD02.class, busData);

            try{
                objESaveRoleInfoRes = roleIService.save(busData02);
                objESaveRoleInfoRB.setMsgCode("0x00000");
                objESaveRoleInfoRB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveRoleInfoRB.setMsgCode(e.getMsgCode());
                objESaveRoleInfoRB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveRoleInfoRes.setBody(objESaveRoleInfoRB);
        objESaveRoleInfoRes.setStatus("00");

        return objESaveRoleInfoRes;
    }
}
