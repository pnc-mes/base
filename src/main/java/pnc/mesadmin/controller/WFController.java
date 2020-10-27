package pnc.mesadmin.controller;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllWfInfo.GetAllRes;
import pnc.mesadmin.dto.GetAllWfInfo.GetAllWfInfoRes;
import pnc.mesadmin.dto.GetAllWfInfo.GetAllWfInfoResB;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoReqBD00;
import pnc.mesadmin.dto.GetWfSInfo.GetWfSInfoReqBD00;
import pnc.mesadmin.dto.GetWfSInfo.GetWfSInfoRes;
import pnc.mesadmin.dto.GetWfSInfo.GetWfSInfoResB;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoReqBD00;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoReqBD01;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoRes;
import pnc.mesadmin.dto.GetWfVInfo.GetWfVInfoResB;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoReqBD00;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoRes;
import pnc.mesadmin.dto.GetWfVTreeInfo.GetWfVTreeInfoResB;
import pnc.mesadmin.dto.SaveWfInfo.*;
import pnc.mesadmin.service.WFIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：流程管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/WF")
public class WFController {
    @Resource
    private WFIService wfiService;

    //主视图
    @RequestMapping(value = "/WFPG")
    public String WFView(){
        return "process/workflow/workflowinfo";
    }

    //弹出框
    @RequestMapping(value = "/SpecAttr")
    public String WFAttr(){
        return "process/workflow/specattr";
    }

    //获取流程信息
    @RequestMapping(value = "/GetAllWfInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllWfInfoRes GetAllWfInfo(GetAllReq getAllReq){
        GetAllWfInfoRes objEGetAllWfInfoRes = new GetAllWfInfoRes();

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
            try {
                objEGetAllWfInfoRes = wfiService.GetAllWfInfo(objEInitDataFields, pageInfo);
                objEGetAllWfInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllWfInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetAllWfInfoResB objEGetAllWfInfoResB = new GetAllWfInfoResB();
                objEGetAllWfInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllWfInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllWfInfoRes.setBody(objEGetAllWfInfoResB);
            }
        }

        objEGetAllWfInfoRes.setStatus("00");

        return objEGetAllWfInfoRes;
    }

    /**
     * 获取流程列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllWfNew")
    @ResponseBody
    public BaseResponse GetAllWfNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(wfiService.GetAllWfNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取流程版本列表信息
    @RequestMapping(value = "/GetWfVTreeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetWfVTreeInfoRes GetWfVTreeInfo(GetAllReq getAllReq){
        GetWfVTreeInfoRes objEGetWfVTreeInfoRes = new GetWfVTreeInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetWfVTreeInfoReqBD00 objEGetWfVTreeInfoReqBD00 = CommonUtils.switchClass(GetWfVTreeInfoReqBD00.class, busData);

            try {

                objEGetWfVTreeInfoRes = wfiService.GetWfVTreeInfo(objEGetWfVTreeInfoReqBD00);
                objEGetWfVTreeInfoRes.getBody().setMsgCode("0x00000");
                objEGetWfVTreeInfoRes.getBody().setMsgDes("成功");

            }catch (SystemException e){
                GetWfVTreeInfoResB objEGetWfVTreeInfoResB = new GetWfVTreeInfoResB();
                objEGetWfVTreeInfoResB.setMsgCode(e.getMsgCode());
                objEGetWfVTreeInfoResB.setMsgDes(e.getMsgDes());
                objEGetWfVTreeInfoRes.setBody(objEGetWfVTreeInfoResB);
            }
        }

        objEGetWfVTreeInfoRes.setStatus("00");

        return  objEGetWfVTreeInfoRes;
    }

    //获取流程版本信息
    @RequestMapping(value = "/GetWfVInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetWfVInfoRes GetWfVInfo(GetAllReq getAllReq){
        GetWfVInfoRes objEGetWfVInfoRes = new GetWfVInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetWfVInfoReqBD00 objEGetWfVInfoReqBD00 = CommonUtils.switchClass(GetWfVInfoReqBD00.class, busData);

            try {
                objEGetWfVInfoRes = wfiService.GetWfVInfo(objEGetWfVInfoReqBD00);
                objEGetWfVInfoRes.getBody().setMsgCode("0x00000");
                objEGetWfVInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetWfVInfoResB objEGetWfVInfoResB = new GetWfVInfoResB();
                objEGetWfVInfoResB.setMsgCode(e.getMsgCode());
                objEGetWfVInfoResB.setMsgDes(e.getMsgDes());
                objEGetWfVInfoRes.setBody(objEGetWfVInfoResB);
            }
        }else if("01".equals(getAllReq.getExecType())){

            GetWfVInfoReqBD01 objEGetWfVInfoReqBD01 = CommonUtils.switchClass(GetWfVInfoReqBD01.class, busData);

            try {
                objEGetWfVInfoRes = wfiService.GetWfVInfo(objEGetWfVInfoReqBD01);
                objEGetWfVInfoRes.getBody().setMsgCode("0x00000");
                objEGetWfVInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetWfVInfoResB objEGetWfVInfoResB = new GetWfVInfoResB();
                objEGetWfVInfoResB.setMsgCode(e.getMsgCode());
                objEGetWfVInfoResB.setMsgDes(e.getMsgDes());
                objEGetWfVInfoRes.setBody(objEGetWfVInfoResB);
            }
        }

        objEGetWfVInfoRes.setStatus("00");

        return objEGetWfVInfoRes;
    }

    //获取流程工序信息
    @RequestMapping(value = "/GetWfSInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetWfSInfoRes GetWfSInfo(GetAllReq getAllReq){
        GetWfSInfoRes objEGetWfSInfoRes = new GetWfSInfoRes();

        String busData = getAllReq.getBusData();

        if("00".equals(getAllReq.getExecType())){

            GetWfSInfoReqBD00 objEGetWfSInfoReqBD00 = CommonUtils.switchClass(GetWfSInfoReqBD00.class, busData);

            try {
                objEGetWfSInfoRes = wfiService.GetWfSInfo(objEGetWfSInfoReqBD00);
                objEGetWfSInfoRes.getBody().setMsgCode("0x00000");
                objEGetWfSInfoRes.getBody().setMsgDes("成功");
            }catch (SystemException e){
                GetWfSInfoResB objEGetWfSInfoResB = new GetWfSInfoResB();
                objEGetWfSInfoResB.setMsgCode(e.getMsgCode());
                objEGetWfSInfoResB.setMsgDes(e.getMsgDes());
                objEGetWfSInfoRes.setBody(objEGetWfSInfoResB);
            }
        }

        objEGetWfSInfoRes.setStatus("00");

        return objEGetWfSInfoRes;
    }

    //保存流程信息
    @RequestMapping(value = "/SaveWfInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveWfInfoRes SaveWfInfo(SaveReq saveReq){
        SaveWfInfoRes objESaveWfInfoRes = new SaveWfInfoRes();
        SaveWfInfoResB objESaveWfInfoResB = new SaveWfInfoResB();

        String busData = saveReq.getBusData();

        if("00".equals(saveReq.getExecType())){
            //新增
            SaveWfInfoReqBD00 objESaveWfInfoReqBD00 = CommonUtils.switchClass(SaveWfInfoReqBD00.class, busData);

            try{
                if("".equals(objESaveWfInfoReqBD00.getWfName())){
                    throw new SystemException("", "工艺流程名称不能为空!");
                }
                if("".equals(objESaveWfInfoReqBD00.getVersion())){
                    throw new SystemException("", "工艺流程版本不能为空!");
                }
                objESaveWfInfoRes = wfiService.AddWfInfo(objESaveWfInfoReqBD00);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }

        }else if("01".equals(saveReq.getExecType())){
            //删除
            SaveWfInfoReqBD01 objESaveWfInfoReqBD01 = CommonUtils.switchClass(SaveWfInfoReqBD01.class, busData);

            try {
                objESaveWfInfoRes = wfiService.RmWfInfo(objESaveWfInfoReqBD01);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("02".equals(saveReq.getExecType())){
            //编辑
            SaveWfInfoReqBD02 objESaveWfInfoReqBD02 = CommonUtils.switchClass(SaveWfInfoReqBD02.class, busData);

            try {
                if("".equals(objESaveWfInfoReqBD02.getWfName())){
                    throw new SystemException("", "工艺流程名称不能为空!");
                }
                if("".equals(objESaveWfInfoReqBD02.getVersion())){
                    throw new SystemException("", "工艺流程版本不能为空!");
                }
                objESaveWfInfoRes = wfiService.ModWfInfo(objESaveWfInfoReqBD02);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("03".equals(saveReq.getExecType())){
            //复制
            SaveWfInfoReqBD03 objESaveWfInfoReqBD03 = CommonUtils.switchClass(SaveWfInfoReqBD03.class, busData);

            try {
                objESaveWfInfoRes = wfiService.AddCopyWfInfo(objESaveWfInfoReqBD03);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("04".equals(saveReq.getExecType())){
            //新增版本
            SaveWfInfoReqBD04 objESaveWfInfoReqBD04 = CommonUtils.switchClass(SaveWfInfoReqBD04.class, busData);

            try {
                if("".equals(objESaveWfInfoReqBD04.getWfName())){
                    throw new SystemException("", "工艺流程名称不能为空!");
                }
                if("".equals(objESaveWfInfoReqBD04.getVersion())){
                    throw new SystemException("", "工艺流程版本不能为空!");
                }
                objESaveWfInfoRes = wfiService.AddWfVerInfo(objESaveWfInfoReqBD04);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("05".equals(saveReq.getExecType())){
            //删除版本
            SaveWfInfoReqBD05 objESaveWfInfoReqBD05 = CommonUtils.switchClass(SaveWfInfoReqBD05.class, busData);

            try {
                objESaveWfInfoRes = wfiService.RmWfVerInfo(objESaveWfInfoReqBD05);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }
        }else if("06".equals(saveReq.getExecType())){
            //判断当前工序能不能删除
            SaveWfInfoReqBD06 objESaveWfInfoReqBD06 = CommonUtils.switchClass(SaveWfInfoReqBD06.class, busData);

            try {
                objESaveWfInfoRes = wfiService.GetWfVerInfo(objESaveWfInfoReqBD06);
                objESaveWfInfoResB.setMsgCode("0x00000");
                objESaveWfInfoResB.setMsgDes("成功");
            }catch (SystemException e){
                objESaveWfInfoResB.setMsgCode(e.getMsgCode());
                objESaveWfInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objESaveWfInfoRes.setBody(objESaveWfInfoResB);
        objESaveWfInfoRes.setStatus("00");

        return objESaveWfInfoRes;
    }
}
