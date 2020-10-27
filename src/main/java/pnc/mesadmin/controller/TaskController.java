package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllTaskInfo.GetAllTaskInfoRes;
import pnc.mesadmin.dto.GetAllTaskInfo.GetAllTaskInfoResB;
import pnc.mesadmin.dto.GetTaskInfo.GetTaskInfoReq00;
import pnc.mesadmin.dto.GetTaskInfo.GetTaskInfoRes;
import pnc.mesadmin.dto.GetTaskInfo.GetTaskInfoResB;
import pnc.mesadmin.dto.SaveTaskInfo.*;
import pnc.mesadmin.service.TaskListIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 11:30
 * @Description:
 */
@Controller
@RequestMapping("/Task")
public class TaskController {

    @Resource
    private TaskListIService taskListIService;

    @RequestMapping(value = "/TaskPG",method = RequestMethod.GET)
    public String home(){
        return "base/task/taskinfo";
    }

    //获取所有任务列表信息
    @RequestMapping(value = "/GetAllTaskInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllTaskInfoRes QALLGetAllTaskListInfoRes(GetAllReq getAllReq){
        GetAllTaskInfoRes getAllTaskInfoRes =null;
        GetAllTaskInfoResB getAllTaskInfoResB =null;
        if("00".equals(getAllReq.getExecType())) {
            getAllTaskInfoRes =new GetAllTaskInfoRes();
            getAllTaskInfoResB =new GetAllTaskInfoResB();
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
                getAllTaskInfoRes = taskListIService.QALLGetAllTaskListInfoRes(objEInitDataFields,pageInfo);
                getAllTaskInfoRes.getBody().setMsgCode("0x00000");
                getAllTaskInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                getAllTaskInfoResB =new GetAllTaskInfoResB();
                getAllTaskInfoResB.setMsgCode(e.getMsgCode());
                getAllTaskInfoResB.setMsgDes(e.getMsgDes());
                getAllTaskInfoRes.setBody(getAllTaskInfoResB);
            }
        }
        else{
            getAllTaskInfoResB =new GetAllTaskInfoResB();
            getAllTaskInfoResB.setMsgCode("MG0006F");
            getAllTaskInfoResB.setMsgDes("参数名"+getAllReq.getExecType()+"中值应该等于00");
            getAllTaskInfoRes.setBody(getAllTaskInfoResB);
        }
        getAllTaskInfoRes.setStatus("00");
        return getAllTaskInfoRes;
    }
    //保存任务列表信息
    @RequestMapping(value = "/SaveTaskInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveTaskInfoRes AddSaveTaskListInfoRes(SaveReq saveReq){
        SaveTaskInfoRes saveTaskInfoRes =new SaveTaskInfoRes();
        SaveTaskInfoResB saveTaskInfoResB = new SaveTaskInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveTaskInfoReq00 saveTaskInfoReq00 = CommonUtils.switchClass(SaveTaskInfoReq00.class, saveReq.getBusData());

            try {
                saveTaskInfoRes = taskListIService.ADDSaveTaskListInfoRes(saveTaskInfoReq00);
                saveTaskInfoRes.getBody().setMsgCode("0x00000");
                saveTaskInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveTaskInfoResB = new SaveTaskInfoResB();
                saveTaskInfoResB.setMsgCode(e.getMsgCode());
                saveTaskInfoResB.setMsgDes(e.getMsgDes());
                saveTaskInfoRes.setBody(saveTaskInfoResB);
            }

        } else if("01".equals(saveReq.getExecType())){
            SaveTaskInfoReq01 saveTaskInfoReq01 = CommonUtils.switchClass(SaveTaskInfoReq01.class, saveReq.getBusData());

            try {
                saveTaskInfoRes = taskListIService.RmSaveTaskListInfoRes(saveTaskInfoReq01);
                saveTaskInfoRes.getBody().setMsgCode("0x00000");
                saveTaskInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveTaskInfoResB = new SaveTaskInfoResB();
                saveTaskInfoResB.setMsgCode(e.getMsgCode());
                saveTaskInfoResB.setMsgDes(e.getMsgDes());
                saveTaskInfoRes.setBody(saveTaskInfoResB);
            }
        }else if("02".equals(saveReq.getExecType())){
            SaveTaskInfoReq02 saveTaskInfoReq02 = CommonUtils.switchClass(SaveTaskInfoReq02.class, saveReq.getBusData());

            try {
                saveTaskInfoRes = taskListIService.ModSaveTaskListInfoRes(saveTaskInfoReq02);
                saveTaskInfoRes.getBody().setMsgCode("0x00000");
                saveTaskInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveTaskInfoResB = new SaveTaskInfoResB();
                saveTaskInfoResB.setMsgCode(e.getMsgCode());
                saveTaskInfoResB.setMsgDes(e.getMsgDes());
                saveTaskInfoRes.setBody(saveTaskInfoResB);
            }
        }
        else
        {
            saveTaskInfoResB.setMsgCode("MG0006F");
            saveTaskInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            saveTaskInfoRes.setBody(saveTaskInfoResB);
        }
        saveTaskInfoRes.setStatus("00");
        return saveTaskInfoRes;
    }
    //获取任务信息
    @RequestMapping(value = "/GetTaskInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetTaskInfoRes GetGetTaskListInfoRes(GetAllReq getAllReq) {
        GetTaskInfoRes getTaskInfoRes =null;
        GetTaskInfoResB getTaskInfoResB =null;
        if ("00".equals(getAllReq.getExecType())) {
            getTaskInfoRes =new GetTaskInfoRes();
            getTaskInfoResB =new GetTaskInfoResB();
            GetTaskInfoReq00 getTaskInfoReq00 = CommonUtils.switchClass(GetTaskInfoReq00.class, getAllReq.getBusData());
            if(getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getTaskInfoRes = taskListIService.GetGetTaskListInfoRes(getTaskInfoReq00);
                    getTaskInfoRes.getBody().setMsgCode("0x00000");
                    getTaskInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    getTaskInfoResB =new GetTaskInfoResB();
                    getTaskInfoResB.setMsgCode(e.getMsgCode());
                    getTaskInfoResB.setMsgDes(e.getMsgDes());
                    getTaskInfoRes.setBody(getTaskInfoResB);
                }
            }
        } else {
            getTaskInfoResB =new GetTaskInfoResB();
            getTaskInfoResB.setMsgCode("MG0006F");
            getTaskInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getTaskInfoRes.setBody(getTaskInfoResB);
        }

        getTaskInfoRes.setStatus("00");
        return getTaskInfoRes;
    }
}
