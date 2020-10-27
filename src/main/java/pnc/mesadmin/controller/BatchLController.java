package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.GetAllBLInfo.GetAllBLInfoRes;
import pnc.mesadmin.dto.GetAllBLInfo.GetAllBLInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoReqBD00;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoRes;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoResB;
import pnc.mesadmin.dto.SaveBLInfo.*;
import pnc.mesadmin.entity.BLevelInfo;
import pnc.mesadmin.service.BatchLIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次等级信息Controller
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/BatchL")
public class BatchLController {
    @Resource
    private BatchLIService batchLIService;


    //获取批次等级页面
    @RequestMapping(value = "/BatchLPG")
    public String getBatchLinfoPage(){

        return "base/batchlevel/batchlevelinfo";
    }


    //获取批次等级列表信息
    @RequestMapping(value ="/GetAllBLInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllBLInfoRes GetAllBLInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllBLInfoRes objEGetAllBLInfoRes=new GetAllBLInfoRes();

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
                objEGetAllBLInfoRes = batchLIService.QALLselectAllBLevelInfo(objEInitDataFields, pageInfo);
                objEGetAllBLInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllBLInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllBLInfoResB objEGetAllBLInfoResB = new GetAllBLInfoResB();
                objEGetAllBLInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllBLInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllBLInfoRes.setBody(objEGetAllBLInfoResB);
            }
        }else{
            GetAllBLInfoResB objEGetAllBLInfoResB = new GetAllBLInfoResB();
            objEGetAllBLInfoResB.setMsgCode("MG0006F");
            objEGetAllBLInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllBLInfoRes.setBody(objEGetAllBLInfoResB);
        }

        objEGetAllBLInfoRes.setStatus("00");
        return objEGetAllBLInfoRes;
    }

    //获取批次等级信息
    @RequestMapping(value = "/GetBLInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetBLInfoRes GetBLInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetBLInfoRes objEGetBLInfoRes=new GetBLInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetBLInfoReqBD00 busData00 =  CommonUtils.switchClass(GetBLInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetBLInfoRes = batchLIService.GetselectBybLRd(busData00.getBLRd());
                    objEGetBLInfoRes.getBody().setMsgCode("0x00000");
                    objEGetBLInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetBLInfoResB objEGetBLInfoResB = new GetBLInfoResB();
                    objEGetBLInfoResB.setMsgCode(e.getMsgCode());
                    objEGetBLInfoResB.setMsgDes(e.getMsgDes());
                    objEGetBLInfoRes.setBody(objEGetBLInfoResB);
                }
            }
        }else{
            GetBLInfoResB objEGetBLInfoResB = new GetBLInfoResB();
            objEGetBLInfoResB.setMsgCode("MG0006F");
            objEGetBLInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetBLInfoRes.setBody(objEGetBLInfoResB);
        }

        objEGetBLInfoRes.setStatus("00");

        return objEGetBLInfoRes;
    }

    //保存批次等级信息
    @RequestMapping(value ="/SaveBLInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveBLInfoRes SaveBLInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveBLInfoRes saveBLInfoRes = new SaveBLInfoRes();

        SaveBLInfoResB saveBLInfoResB= new SaveBLInfoResB();

        BLevelInfo bLevelInfo = new BLevelInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveBLInfoReqBD00 busData00 = CommonUtils.switchClass(SaveBLInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveBLInfoRes = batchLIService.AddinsertBLevelInfo(busData00,bLevelInfo);
                SaveBLInfoResB body = new SaveBLInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveBLInfoRes.setBody(body);
            }catch (SystemException e){
                saveBLInfoResB.setMsgCode(e.getMsgCode());
                saveBLInfoResB.setMsgDes(e.getMsgDes());
                saveBLInfoRes.setBody(saveBLInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveBLInfoReqBD01 busData01 = CommonUtils.switchClass(SaveBLInfoReqBD01.class,rowData);
            try {
                saveBLInfoRes= batchLIService.RmdeleteBLevelInfo(busData01.getBLRd());
                SaveBLInfoResB body = new SaveBLInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveBLInfoRes.setBody(body);
            }catch (SystemException e){
                saveBLInfoResB.setMsgCode(e.getMsgCode());
                saveBLInfoResB.setMsgDes(e.getMsgDes());
                saveBLInfoRes.setBody(saveBLInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveBLInfoReqBD02 busData02 = CommonUtils.switchClass(SaveBLInfoReqBD02.class,rowData);
            try {
                saveBLInfoRes = batchLIService.ModupdateBLevelInfo(busData02,bLevelInfo);
                SaveBLInfoResB body = new SaveBLInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveBLInfoRes.setBody(body);
            }catch (SystemException e){
                saveBLInfoResB.setMsgCode(e.getMsgCode());
                saveBLInfoResB.setMsgDes(e.getMsgDes());
                saveBLInfoRes.setBody(saveBLInfoResB);
            }
        }
        //复制
        else if("03".equals(saveReq.getExecType())){
            SaveBLInfoReqBD03 busData03 = CommonUtils.switchClass(SaveBLInfoReqBD03.class,rowData);
            try {
                saveBLInfoRes = batchLIService.copyBLevelInfo(busData03,bLevelInfo);
                SaveBLInfoResB body = new SaveBLInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveBLInfoRes.setBody(body);
            }catch (SystemException e){
                saveBLInfoResB.setMsgCode(e.getMsgCode());
                saveBLInfoResB.setMsgDes(e.getMsgDes());
                saveBLInfoRes.setBody(saveBLInfoResB);
            }
        }else{
            saveBLInfoResB.setMsgCode("MG0006F");
            saveBLInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
        }

        saveBLInfoRes.setStatus("00");
        return saveBLInfoRes;
    }
}
