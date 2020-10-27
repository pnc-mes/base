package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoRes;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoResB;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoReqBD00;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoRes;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoResB;
import pnc.mesadmin.dto.SavePrInfo.*;
import pnc.mesadmin.entity.PrinterInfo;
import pnc.mesadmin.service.PrinterIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息Controller
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Printer")
public class PrinterController {
    @Resource
    private PrinterIService printerIService;

    //获取打印机页面
    @RequestMapping(value = "/PrinterPG")
    public String PrinterPG(){

        return "base/printer/printerinfo";
    }

    //获取打印机列表信息
    @RequestMapping(value = "/GetAllPrInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllPrInfoRes GetAllPrInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllPrInfoRes objEGetAllPrInfoRes=new GetAllPrInfoRes();

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
                objEGetAllPrInfoRes = printerIService.QALLselectAllPrinterInfo(objEInitDataFields, pageInfo);
                objEGetAllPrInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllPrInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllPrInfoResB objEGetAllPrInfoResB = new GetAllPrInfoResB();
                objEGetAllPrInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllPrInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllPrInfoRes.setBody(objEGetAllPrInfoResB);
            }
        }else{
            GetAllPrInfoResB objEGetAllPrInfoResB = new GetAllPrInfoResB();
            objEGetAllPrInfoResB.setMsgCode("MG0006F");
            objEGetAllPrInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllPrInfoRes.setBody(objEGetAllPrInfoResB);
        }

        objEGetAllPrInfoRes.setStatus("00");
        return objEGetAllPrInfoRes;
    }

    /**
     * 查询打印机列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllPrNew")
    @ResponseBody
    public BaseResponse GetAllPrNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(printerIService.QALLPrinterNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取打印机信息
    @RequestMapping(value = "/GetPrInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetPrInfoRes GetPrInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetPrInfoRes objEGetPrInfoRes=new GetPrInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetPrInfoReqBD00 busData00 = CommonUtils.switchClass(GetPrInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetPrInfoRes = printerIService.GetselectByRuid(busData00.getPrRd());
                    objEGetPrInfoRes.getBody().setMsgCode("0x00000");
                    objEGetPrInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetPrInfoResB objEGetPrInfoResB = new GetPrInfoResB();
                    objEGetPrInfoResB.setMsgCode(e.getMsgCode());
                    objEGetPrInfoResB.setMsgDes(e.getMsgDes());
                    objEGetPrInfoRes.setBody(objEGetPrInfoResB);
                }
            }
        }else{
            GetPrInfoResB objEGetPrInfoResB = new GetPrInfoResB();
            objEGetPrInfoResB.setMsgCode("MG0006F");
            objEGetPrInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetPrInfoRes.setBody(objEGetPrInfoResB);
        }

        objEGetPrInfoRes.setStatus("00");

        return objEGetPrInfoRes;
    }
    //保存打印机信息
    @RequestMapping(value = "/SavePrInfo",method = RequestMethod.POST)
    @ResponseBody
    public SavePrInfoRes SavePrInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SavePrInfoRes savePrInfoRes = new SavePrInfoRes();

        SavePrInfoResB savePrInfoB = new SavePrInfoResB();

        PrinterInfo printerInfo = new PrinterInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SavePrInfoReqBD00 busData00 = CommonUtils.switchClass(SavePrInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                savePrInfoRes = printerIService.AddinsertPrinterInfo(busData00,printerInfo);
                SavePrInfoResB body = new SavePrInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePrInfoRes.setBody(body);
            }catch (SystemException e){
                savePrInfoB.setMsgCode(e.getMsgCode());
                savePrInfoB.setMsgDes(e.getMsgDes());
                savePrInfoRes.setBody(savePrInfoB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SavePrInfoReqBD01 busData01 = CommonUtils.switchClass(SavePrInfoReqBD01.class,rowData);
            try {
                savePrInfoRes = printerIService.RmdeletePrinterInfo(busData01.getPrRd());
                SavePrInfoResB body = new SavePrInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePrInfoRes.setBody(body);
            }catch (SystemException e){
                savePrInfoB.setMsgCode(e.getMsgCode());
                savePrInfoB.setMsgDes(e.getMsgDes());
                savePrInfoRes.setBody(savePrInfoB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SavePrInfoReqBD02 busData02 = CommonUtils.switchClass(SavePrInfoReqBD02.class,rowData);
            try {
                savePrInfoRes = printerIService.ModupdatePrinterInfo(busData02,printerInfo);
                SavePrInfoResB body = new SavePrInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePrInfoRes.setBody(body);
            }catch (SystemException e){
                savePrInfoB.setMsgCode(e.getMsgCode());
                savePrInfoB.setMsgDes(e.getMsgDes());
                savePrInfoRes.setBody(savePrInfoB);
            }
        }
        //复制
        else if("03".equals(saveReq.getExecType())){
            SavePrInfoReqBD03 busData03 = CommonUtils.switchClass(SavePrInfoReqBD03.class,rowData);
            try {
                savePrInfoRes = printerIService.copyPrinterInfo(busData03,printerInfo);
                SavePrInfoResB body = new SavePrInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                savePrInfoRes.setBody(body);
            }catch (SystemException e){
                savePrInfoB.setMsgCode(e.getMsgCode());
                savePrInfoB.setMsgDes(e.getMsgDes());
                savePrInfoRes.setBody(savePrInfoB);
            }
        }
        else{
            savePrInfoB.setMsgCode("MG0006F");
            savePrInfoB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
        }

        savePrInfoRes.setStatus("00");
        return savePrInfoRes;
    }
}
