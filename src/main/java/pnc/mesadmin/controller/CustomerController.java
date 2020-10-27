package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoRes;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoResB;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoReqBD00;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoRes;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoResB;
import pnc.mesadmin.dto.SaveCusInfo.*;
import pnc.mesadmin.entity.CustomerInfo;
import pnc.mesadmin.service.CustomerIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：客户信息Controller
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Customer")
public class CustomerController {
    @Resource
    private CustomerIService customerIService;

    //获取供应商页面
    @RequestMapping(value = "/CustomerPG")
    public String getCustomerPGPage(){

        return "base/customer/customerinfo";
    }



    @RequestMapping(value = "/GetAllNewCusInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllNewDcInfo(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(customerIService.QALLGetAllCusInfoRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取客户列表信息
    @RequestMapping(value ="/GetAllCusInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllCusInfoRes GetAllCusInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllCusInfoRes objEGetAllCusInfoRes=new GetAllCusInfoRes();

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
                objEGetAllCusInfoRes = customerIService.QALLselectAllCustomerInfo(objEInitDataFields, pageInfo);
                objEGetAllCusInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllCusInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllCusInfoResB objEGetAllCusInfoResB = new GetAllCusInfoResB();
                objEGetAllCusInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllCusInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllCusInfoRes.setBody(objEGetAllCusInfoResB);
            }
        }else{
            GetAllCusInfoResB objEGetAllCusInfoResB = new GetAllCusInfoResB();
            objEGetAllCusInfoResB.setMsgCode("MG0006F");
            objEGetAllCusInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllCusInfoRes.setBody(objEGetAllCusInfoResB);
        }

        objEGetAllCusInfoRes.setStatus("00");
        return objEGetAllCusInfoRes;
    }


    //获取客户信息
    @RequestMapping(value = "/GetCusInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetCusInfoRes GetCusInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetCusInfoRes objEGetCusInfoRes=new GetCusInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetCusInfoReqBD00 busData00 = CommonUtils.switchClass(GetCusInfoReqBD00.class,busData);
            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetCusInfoRes = customerIService.GetselectByRuid(busData00.getCusRd());
                    objEGetCusInfoRes.getBody().setMsgCode("0x00000");
                    objEGetCusInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetCusInfoResB objEGetCusInfoResB = new GetCusInfoResB();
                    objEGetCusInfoResB.setMsgCode(e.getMsgCode());
                    objEGetCusInfoResB.setMsgDes(e.getMsgDes());
                    objEGetCusInfoRes.setBody(objEGetCusInfoResB);
                }
            }
        }else{
            GetCusInfoResB objEGetCusInfoResB = new GetCusInfoResB();
            objEGetCusInfoResB.setMsgCode("MG0006F");
            objEGetCusInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetCusInfoRes.setBody(objEGetCusInfoResB);
        }

        objEGetCusInfoRes.setStatus("00");

        return objEGetCusInfoRes;
    }
    //保存客户信息
    @RequestMapping(value ="/SaveCusInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveCusInfoRes SaveCusInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveCusInfoRes saveCusInfoRes = new SaveCusInfoRes();

        SaveCusInfoResB saveCusInfoB = new SaveCusInfoResB();

        CustomerInfo customerInfo = new CustomerInfo();

        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();


        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveCusInfoReqBD00 busData00 = CommonUtils.switchClass(SaveCusInfoReqBD00.class,rowData);

            // 直接可以获取的表单数据
            try {
                saveCusInfoRes = customerIService.AddinsertCustomerInfo(busData00,customerInfo);
                SaveCusInfoResB body = new SaveCusInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCusInfoRes.setBody(body);
            }catch (SystemException e){
                saveCusInfoB.setMsgCode(e.getMsgCode());
                saveCusInfoB.setMsgDes(e.getMsgDes());
                saveCusInfoRes.setBody(saveCusInfoB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveCusInfoReqBD01 busData01 = CommonUtils.switchClass(SaveCusInfoReqBD01.class,rowData);
            try {
                saveCusInfoRes = customerIService.RmdeleteCustomerInfo(busData01.getCusRd());
                SaveCusInfoResB body = new SaveCusInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCusInfoRes.setBody(body);
            }catch (SystemException e){
                saveCusInfoB.setMsgCode(e.getMsgCode());
                saveCusInfoB.setMsgDes(e.getMsgDes());
                saveCusInfoRes.setBody(saveCusInfoB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveCusInfoReqBD02 busData02 = CommonUtils.switchClass(SaveCusInfoReqBD02.class,rowData);
            try {
                saveCusInfoRes = customerIService.ModupdateCustomerInfo(busData02,customerInfo);
                SaveCusInfoResB body = new SaveCusInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCusInfoRes.setBody(body);
            }catch (SystemException e){
                saveCusInfoB.setMsgCode(e.getMsgCode());
                saveCusInfoB.setMsgDes(e.getMsgDes());
                saveCusInfoRes.setBody(saveCusInfoB);
            }
        }
        //复制
        else if("03".equals(saveReq.getExecType())){
            SaveCusInfoReqBD03 busData03 = CommonUtils.switchClass(SaveCusInfoReqBD03.class,rowData);
            try {
                saveCusInfoRes = customerIService.copyCustomerInfo(busData03,customerInfo);
                SaveCusInfoResB body = new SaveCusInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveCusInfoRes.setBody(body);
            }catch (SystemException e){
                saveCusInfoB.setMsgCode(e.getMsgCode());
                saveCusInfoB.setMsgDes(e.getMsgDes());
                saveCusInfoRes.setBody(saveCusInfoB);
            }
        }else{
            saveCusInfoB.setMsgCode("MG0006F");
            saveCusInfoB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
        }

        saveCusInfoRes.setStatus("00");
        return saveCusInfoRes;
    }
}
