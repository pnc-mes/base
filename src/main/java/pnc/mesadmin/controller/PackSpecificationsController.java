package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPackSPInfo.GetAllPackSPInfoRes;
import pnc.mesadmin.dto.GetAllPackSPInfo.GetAllPackSPInfoResB;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoReq00;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoRes;
import pnc.mesadmin.dto.GetPackSpecificationInfo.GetPackSpecificationInfoResB;
import pnc.mesadmin.dto.SavePackSpecificationInfo.*;
import pnc.mesadmin.service.PackSpecificationIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备信息Controller
 * 创建人：ZC
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/PackSp")
public class PackSpecificationsController {

    @Resource
    private PackSpecificationIService packSpecificationIService;

    //加载设备页面
    @RequestMapping("/PackSpecificationPG")
    public String toPackSPPG(){
        return "base/packsp/packspinfo";
    }

    //加载所有的包装规格
    @ResponseBody
    @RequestMapping(value = "/GetAllMPKInfo",method = RequestMethod.POST)
    public GetAllPackSPInfoRes GetAllMPKInfo(GetAllReq argGetAllReq){
        GetAllPackSPInfoRes objGetAllPackSPInfoRes = new GetAllPackSPInfoRes();
        GetAllPackSPInfoResB objGetAllPackSPInfoResB = new GetAllPackSPInfoResB();
        if("00".equals(argGetAllReq.getExecType())){
            List<InitDataField> objEInitDataFields = null;
            PageInfo pageInfo = null;

            if (argGetAllReq.getInitData() != null && !"".equals(argGetAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, argGetAllReq.getInitData());

                if (objEInitData != null) {
                    objEInitDataFields = objEInitData.getFiledList();
                }
            }

            if (argGetAllReq.getPageInfo() != null && !"".equals(argGetAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, argGetAllReq.getPageInfo());
            }
            objGetAllPackSPInfoResB = packSpecificationIService.GetAllMPKInfo(objEInitDataFields, pageInfo);
        }
        objGetAllPackSPInfoRes.setBody(objGetAllPackSPInfoResB);
        objGetAllPackSPInfoRes.setStatus("00");
        return objGetAllPackSPInfoRes;
    }

    /**
     * 获取包装规则列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllMPKNew")
    @ResponseBody
    public BaseResponse GetAllMPKNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(packSpecificationIService.GetAllMPKNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //加载包装规格
    @ResponseBody
    @RequestMapping(value = "/GetMPKInfo",method = RequestMethod.POST)
    public GetPackSpecificationInfoRes GetMPKInfo(GetAllReq argGetAllReq){
        GetPackSpecificationInfoRes objGetPackSpecificationInfoRes = new GetPackSpecificationInfoRes();
        GetPackSpecificationInfoResB objGetPackSpecificationInfoResB = new GetPackSpecificationInfoResB();
        if("00".equals(argGetAllReq.getExecType())){
            GetPackSpecificationInfoReq00 busData00 = CommonUtils.switchClass(GetPackSpecificationInfoReq00.class,argGetAllReq.getBusData());
            objGetPackSpecificationInfoResB = packSpecificationIService.GetMPKInfoByRuid(busData00.getMPRd());
        }
        objGetPackSpecificationInfoRes.setBody(objGetPackSpecificationInfoResB);
        objGetPackSpecificationInfoRes.setStatus("00");
        return objGetPackSpecificationInfoRes;
    }
    //保存
    @ResponseBody
    @RequestMapping(value = "/SaveMPKInfo",method = RequestMethod.POST)
    public SavePackSpecificationInfoRes SaveMPKInfo(SaveReq argSaveReq){
        SavePackSpecificationInfoRes objSavePackSpecificationInfoRes = new SavePackSpecificationInfoRes();
        SavePackSpecificationInfoResB objSavePackSpecificationInfoResB = new SavePackSpecificationInfoResB();
        //新增
        if("00".equals(argSaveReq.getExecType())){
            SavePackSpecificationInfoReq00 busData00 = CommonUtils.switchClass(SavePackSpecificationInfoReq00.class,argSaveReq.getBusData());
            try{
                objSavePackSpecificationInfoResB = packSpecificationIService.AddPackSpecification(busData00);
            }catch(SystemException e){
                objSavePackSpecificationInfoResB.setMsgCode(e.getMsgCode());
                objSavePackSpecificationInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        //删除
        else if("01".equals(argSaveReq.getExecType())){
            SavePackSpecificationInfoReq01 busData01 = CommonUtils.switchClass(SavePackSpecificationInfoReq01.class,argSaveReq.getBusData());
            try{
                objSavePackSpecificationInfoResB = packSpecificationIService.RmPackSpecification(busData01.getMPRd());
            }catch(SystemException e){
                objSavePackSpecificationInfoResB.setMsgCode(e.getMsgCode());
                objSavePackSpecificationInfoResB.setMsgDes(e.getMsgDes());
            }
        }
        //修改
        else if("02".equals(argSaveReq.getExecType())){
            SavePackSpecificationInfoReq02 busData02 = CommonUtils.switchClass(SavePackSpecificationInfoReq02.class,argSaveReq.getBusData());
            try{
                objSavePackSpecificationInfoResB = packSpecificationIService.ModPackSpecification(busData02);
            }catch(SystemException e){
                objSavePackSpecificationInfoResB.setMsgCode(e.getMsgCode());
                objSavePackSpecificationInfoResB.setMsgDes(e.getMsgDes());
            }
        }

        objSavePackSpecificationInfoRes.setBody(objSavePackSpecificationInfoResB);
        objSavePackSpecificationInfoRes.setStatus("00");
        return objSavePackSpecificationInfoRes;
    }

}
