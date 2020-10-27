package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoReqBD00;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoReqBD01;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoRes;
import pnc.mesadmin.dto.GetMWFInfo.GetMWFInfoResB;
import pnc.mesadmin.dto.SaveReq;
import pnc.mesadmin.dto.SaveSOPInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SOPInfo;
import pnc.mesadmin.service.SpecfileIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺文件信息Controller
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/SpecFile")
public class SpecfileController {
    @Resource
    private SpecfileIService specfileIService;

    //获取工艺文件页面
    @RequestMapping(value = "/SpecFilePG")
    public String getSpecFilePGPage(){

        return "process/specfile/specfileinfo";
    }



    @RequestMapping(value = "/GetAllWorkFoolControllerInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllWorkFoolControllerInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(specfileIService.GetAllWorkFoolControllerResD(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取物料工艺流程工序信息
    @RequestMapping(value = "/GetMWFInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetMWFInfoRes GetMWFInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetMWFInfoRes objEGetMWFInfoRes=new GetMWFInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetMWFInfoReqBD00 busData00 =  CommonUtils.switchClass(GetMWFInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetMWFInfoRes = specfileIService.GetSelectByRuid(busData00);
                    objEGetMWFInfoRes.getBody().setMsgCode("0x00000");
                    objEGetMWFInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetMWFInfoResB objEGetMWFInfoResB = new GetMWFInfoResB();
                    objEGetMWFInfoResB.setMsgCode(e.getMsgCode());
                    objEGetMWFInfoResB.setMsgDes(e.getMsgDes());
                    objEGetMWFInfoRes.setBody(objEGetMWFInfoResB);
                }
            }
        }else if("01".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetMWFInfoReqBD01 busData01 =  CommonUtils.switchClass(GetMWFInfoReqBD01.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetMWFInfoRes = specfileIService.GetSelectByruid(busData01);
                    objEGetMWFInfoRes.getBody().setMsgCode("0x00000");
                    objEGetMWFInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetMWFInfoResB objEGetMWFInfoResB = new GetMWFInfoResB();
                    objEGetMWFInfoResB.setMsgCode(e.getMsgCode());
                    objEGetMWFInfoResB.setMsgDes(e.getMsgDes());
                    objEGetMWFInfoRes.setBody(objEGetMWFInfoResB);
                }
            }
        }
        else{
            GetMWFInfoResB objEGetMWFInfoResB = new GetMWFInfoResB();
            objEGetMWFInfoResB.setMsgCode("MG0006F");
            objEGetMWFInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetMWFInfoRes.setBody(objEGetMWFInfoResB);
        }

        objEGetMWFInfoRes.setStatus("00");

        return objEGetMWFInfoRes;
    }

    //保存工艺文件信息
    @RequestMapping(value = "SaveSOPInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveSOPInfoRes SaveSOPInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveSOPInfoRes saveSOPInfoRes = new SaveSOPInfoRes();

        SaveSOPInfoResB saveSOPInfoResB = new SaveSOPInfoResB();

        SOPInfo sopInfo = new SOPInfo();

        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            SaveSOPInfoReqBD00 busData00 = CommonUtils.switchClass(SaveSOPInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveSOPInfoRes = specfileIService.AddinsertSOPInfo(busData00,sopInfo);
                SaveSOPInfoResB body = new SaveSOPInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveSOPInfoRes.setBody(body);
            }catch (SystemException e){
                saveSOPInfoResB.setMsgCode(e.getMsgCode());
                saveSOPInfoResB.setMsgDes(e.getMsgDes());
                saveSOPInfoRes.setBody(saveSOPInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveSOPInfoReqBD01 busData01 = CommonUtils.switchClass(SaveSOPInfoReqBD01.class,rowData);
            try {
                saveSOPInfoRes = specfileIService.RmdeleteSOPInfo(busData01);
                SaveSOPInfoResB body = new SaveSOPInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveSOPInfoRes.setBody(body);
            }catch (SystemException e){
                saveSOPInfoResB.setMsgCode(e.getMsgCode());
                saveSOPInfoResB.setMsgDes(e.getMsgDes());
                saveSOPInfoRes.setBody(saveSOPInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){

            SaveSOPInfoReqBD02 busData02 = CommonUtils.switchClass(SaveSOPInfoReqBD02.class,saveReq.getBusData());

            try {
                saveSOPInfoRes = specfileIService.ModupdateSOPInfo(busData02,sopInfo);
                SaveSOPInfoResB body = new SaveSOPInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveSOPInfoRes.setBody(body);
            }catch (SystemException e){
                saveSOPInfoResB.setMsgCode(e.getMsgCode());
                saveSOPInfoResB.setMsgDes(e.getMsgDes());
                saveSOPInfoRes.setBody(saveSOPInfoResB);
            }
        }

        saveSOPInfoRes.setStatus("00");
        return saveSOPInfoRes;
    }
}
