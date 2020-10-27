package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllTeamsInfo.GetAllTeamsInfoRes;
import pnc.mesadmin.dto.GetAllTeamsInfo.GetAllTeamsInfoResB;
import pnc.mesadmin.dto.GetTeamInfo.GetTeamInfoReq00;
import pnc.mesadmin.dto.GetTeamInfo.GetTeamInfoRes;
import pnc.mesadmin.dto.GetTeamInfo.GetTeamInfoResB;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoReqBD00;
import pnc.mesadmin.dto.GetToolInfo.GetToolInfoResB;
import pnc.mesadmin.dto.SaveTeamInfo.*;
import pnc.mesadmin.dto.SaveUnitInfo.*;
import pnc.mesadmin.service.TeamIService;
import pnc.mesadmin.service.impl.TeamService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 08:51
 * @Description:团队管理
 */
@Controller
@RequestMapping("/Team")
@Scope("prototype")
public class TeamController {
    @Resource
    private TeamIService teamIService;

    @RequestMapping(value = "/TeamPG")
    public String teamHome(){
        return "base/team/teaminfo";
    }

    //获取团队列表接口
    @RequestMapping(value = "/GetAllTeamsInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllTeamsInfoRes SelectAllTeamInfo(GetAllReq getAllReq) {
        GetAllTeamsInfoRes getAllTeamsInfoRes=null;
        GetAllTeamsInfoResB getAllTeamsInfoResB=null;
        if("00".equals(getAllReq.getExecType())) {
            getAllTeamsInfoRes=new GetAllTeamsInfoRes();
            getAllTeamsInfoResB=new GetAllTeamsInfoResB();
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
                getAllTeamsInfoRes = teamIService.QALLTeamInfo(objEInitDataFields,pageInfo);
                getAllTeamsInfoRes.getBody().setMsgCode("0x00000");
                getAllTeamsInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                getAllTeamsInfoResB = new GetAllTeamsInfoResB();
                getAllTeamsInfoResB.setMsgCode(e.getMsgCode());
                getAllTeamsInfoResB.setMsgDes(e.getMsgDes());
                getAllTeamsInfoRes.setBody(getAllTeamsInfoResB);
            }
        }
        else{
            getAllTeamsInfoResB = new GetAllTeamsInfoResB();
            getAllTeamsInfoResB.setMsgCode("MG0006F");
            getAllTeamsInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            getAllTeamsInfoRes.setBody(getAllTeamsInfoResB);
        }
        getAllTeamsInfoRes.setStatus("00");
        return getAllTeamsInfoRes;
    }

    //获取团队接口
    @RequestMapping(value = "/GetTeamInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetTeamInfoRes GetGetTeamInfoRes(GetAllReq getAllReq) {
        GetTeamInfoRes getTeamInfoRes=null;
        GetTeamInfoResB getTeamInfoResB=null;
        if ("00".equals(getAllReq.getExecType())) {
            getTeamInfoRes=new GetTeamInfoRes();
            getTeamInfoResB=new GetTeamInfoResB();
            GetTeamInfoReq00 GetTeamInfoReq00 = CommonUtils.switchClass(GetTeamInfoReq00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getTeamInfoRes = teamIService.GetGetTeamInfoRes(GetTeamInfoReq00);
                    getTeamInfoRes.getBody().setMsgCode("0x00000");
                    getTeamInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    getTeamInfoResB=new GetTeamInfoResB();
                    getTeamInfoResB.setMsgCode(e.getMsgCode());
                    getTeamInfoResB.setMsgDes(e.getMsgDes());
                    getTeamInfoRes.setBody(getTeamInfoResB);
                }
            }
        } else {
            getTeamInfoResB=new GetTeamInfoResB();
            getTeamInfoResB.setMsgCode("MG0006F");
            getTeamInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getTeamInfoRes.setBody(getTeamInfoResB);
        }

        getTeamInfoRes.setStatus("00");
        return getTeamInfoRes;
    }

    //保存团队信息
    @RequestMapping(value = "/SaveTeamInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveTeamInfoRes SaveTeamInfo(SaveReq saveReq){
        SaveTeamInfoRes saveTeamInfoRes=null;
        SaveTeamInfoResB saveTeamInfoResB=null;
        if ("00".equals(saveReq.getExecType())) {
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();

            SaveTeamInfoReq00 saveTeamInfoReq00 = CommonUtils.switchClass(SaveTeamInfoReq00.class, saveReq.getBusData());

            try {
                saveTeamInfoRes = teamIService.AddSaveTeamInfoRes(saveTeamInfoReq00);

                saveTeamInfoResB.setMsgCode("0x00000");
                saveTeamInfoResB.setMsgDes("成功");
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            } catch (SystemException e) {
                saveTeamInfoResB.setMsgCode(e.getMsgCode());
                saveTeamInfoResB.setMsgDes(e.getMsgDes());
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();
            SaveTeamInfoReq01 saveTeamInfoReq01 = CommonUtils.switchClass(SaveTeamInfoReq01.class, saveReq.getBusData());

            try {
                saveTeamInfoRes = teamIService.RmGetTeamInfoReq00(saveTeamInfoReq01);
                saveTeamInfoResB.setMsgCode("0x00000");
                saveTeamInfoResB.setMsgDes("成功");
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            } catch (SystemException e) {
                saveTeamInfoResB.setMsgCode(e.getMsgCode());
                saveTeamInfoResB.setMsgDes(e.getMsgDes());
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            }

        } else if ("02".equals(saveReq.getExecType())) {
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();
            SaveTeamInfoReq02 saveTeamInfoReq02 = CommonUtils.switchClass(SaveTeamInfoReq02.class, saveReq.getBusData());

            try {
                saveTeamInfoRes = teamIService.ModSaveTeamInfoReq02(saveTeamInfoReq02);

                saveTeamInfoResB.setMsgCode("0x00000");
                saveTeamInfoResB.setMsgDes("成功");
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            } catch (SystemException e) {

                saveTeamInfoResB.setMsgCode(e.getMsgCode());
                saveTeamInfoResB.setMsgDes(e.getMsgDes());
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            }

        } else if ("03".equals(saveReq.getExecType())) {
            saveTeamInfoRes=new SaveTeamInfoRes();
            saveTeamInfoResB=new SaveTeamInfoResB();
            SaveTeamInfoReq01 saveTeamInfoReq01 = CommonUtils.switchClass(SaveTeamInfoReq01.class, saveReq.getBusData());

            try {
                saveTeamInfoRes = teamIService.AddGetTeamInfoReq00(saveTeamInfoReq01);

                saveTeamInfoResB.setMsgCode("0x00000");
                saveTeamInfoResB.setMsgDes("成功");
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            } catch (SystemException e) {

                saveTeamInfoResB.setMsgCode(e.getMsgCode());
                saveTeamInfoResB.setMsgDes(e.getMsgDes());
                saveTeamInfoRes.setBody(saveTeamInfoResB);
            }

        } else {
            saveTeamInfoResB.setMsgCode("MG0006F");
            saveTeamInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            saveTeamInfoRes.setBody(saveTeamInfoResB);
        }
        saveTeamInfoRes.setStatus("00");

        return saveTeamInfoRes;
    }
}
