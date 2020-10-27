package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoRes;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoResB;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoReqBD00;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoRes;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoResB;
import pnc.mesadmin.dto.SaveSupInfo.*;
import pnc.mesadmin.entity.SupplierInfo;
import pnc.mesadmin.service.SupplierIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：供应商信息Controller
 * 创建人：刘福志
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Supplier")
public class SupplierController {
    @Resource
    private SupplierIService supplierIService;

    //获取供应商页面
    @RequestMapping(value = "/SupPG")
    public String getSupplierinfoPage(){
        return "base/supplier/supplierinfo";
    }

    //获取供应商列表信息
    @RequestMapping(value ="/GetAllSupInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllSupInfoRes GetAllSupInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllSupInfoRes objEGetAllSupInfoRes=new GetAllSupInfoRes();

        if("00".equals(getAllReq.getExecType())) {
            try {

                List<InitDataField> objEInitDataFields = null;
                PageInfo pageInfo = null;

                if( getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())){
                    InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                    if(objEInitData != null) {
                        objEInitDataFields = objEInitData.getFiledList();
                    }
                }

                if(getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())){
                    pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
                }

                objEGetAllSupInfoRes = supplierIService.QALLselectAllSupplierInfo(objEInitDataFields, pageInfo);
                objEGetAllSupInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllSupInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSupInfoResB objEGetAllSupInfoResB = new GetAllSupInfoResB();
                objEGetAllSupInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllSupInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllSupInfoRes.setBody(objEGetAllSupInfoResB);
            }
        }else{
            GetAllSupInfoResB objEGetAllSupInfoResB = new GetAllSupInfoResB();
            objEGetAllSupInfoResB.setMsgCode("MG0006F");
            objEGetAllSupInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllSupInfoRes.setBody(objEGetAllSupInfoResB);
        }

        objEGetAllSupInfoRes.setStatus("00");
        return objEGetAllSupInfoRes;
    }

    /**
     * 查询供应商列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllSupNew")
    @ResponseBody
    public BaseResponse GetAllSupNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(supplierIService.QALLSupplierNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取供应商信息
    @RequestMapping(value = "/GetSupInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetSupInfoRes GetSupInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetSupInfoRes objEGetSupInfoRes=new GetSupInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetSupInfoReqBD00 busData00 = CommonUtils.switchClass(GetSupInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetSupInfoRes = supplierIService.GetselectBySuppId(busData00.getSupRd());
                    objEGetSupInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSupInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetSupInfoResB objEGetSupInfoResB = new GetSupInfoResB();
                    objEGetSupInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSupInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSupInfoRes.setBody(objEGetSupInfoResB);
                }
            }
        }else{
            GetSupInfoResB objEGetSupInfoResB = new GetSupInfoResB();
            objEGetSupInfoResB.setMsgCode("MG0006F");
            objEGetSupInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetSupInfoRes.setBody(objEGetSupInfoResB);
        }

        objEGetSupInfoRes.setStatus("00");

        return objEGetSupInfoRes;
    }

    //保存供应商信息
    @RequestMapping(value = "/SaveSupInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveSupInfoRes SaveSupInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveSupInfoRes saveSupInfoRes = new SaveSupInfoRes();

        SaveSupInfoResB saveSupInfoB = new SaveSupInfoResB();

        SupplierInfo supplierInfo = new SupplierInfo();

        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveSupInfoReqBD00 busData00 = CommonUtils.switchClass(SaveSupInfoReqBD00.class,rowData);

            // 直接可以获取的表单数据
            try {
                saveSupInfoRes = supplierIService.AddinsertSupplierInfo(busData00,supplierInfo);
                saveSupInfoRes.getBody().setMsgCode("0x00000");
                saveSupInfoRes.getBody().setMsgDes("成功！");
            }catch (SystemException e){
                saveSupInfoB.setMsgCode(e.getMsgCode());
                saveSupInfoB.setMsgDes(e.getMsgDes());
                saveSupInfoRes.setBody(saveSupInfoB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveSupInfoReqBD01 busData01 =  CommonUtils.switchClass(SaveSupInfoReqBD01.class,rowData);
            try {
                saveSupInfoRes = supplierIService.RmdeleteSupplierInfo(busData01.getSupRd());
                SaveSupInfoResB body = new SaveSupInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveSupInfoRes.setBody(body);
            }catch (SystemException e){
                saveSupInfoB.setMsgCode(e.getMsgCode());
                saveSupInfoB.setMsgDes(e.getMsgDes());
                saveSupInfoRes.setBody(saveSupInfoB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveSupInfoReqBD02 busData02 = CommonUtils.switchClass(SaveSupInfoReqBD02.class,rowData);
            try {
                saveSupInfoRes = supplierIService.ModupdateSupplierInfo(busData02,supplierInfo);
                SaveSupInfoResB body = new SaveSupInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveSupInfoRes.setBody(body);
            }catch (SystemException e){
                saveSupInfoB.setMsgCode(e.getMsgCode());
                saveSupInfoB.setMsgDes(e.getMsgDes());
                saveSupInfoRes.setBody(saveSupInfoB);
            }
        }
        //复制
        else if("03".equals(saveReq.getExecType())){

            SaveSupInfoReqBD03 busData03 = CommonUtils.switchClass(SaveSupInfoReqBD03.class,rowData);
            try {
                saveSupInfoRes = supplierIService.copySupplierInfo(busData03, supplierInfo);
                SaveSupInfoResB body = new SaveSupInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveSupInfoRes.setBody(body);
            }catch (SystemException e){
                saveSupInfoB.setMsgCode(e.getMsgCode());
                saveSupInfoB.setMsgDes(e.getMsgDes());
                saveSupInfoRes.setBody(saveSupInfoB);
            }
        }else{
            saveSupInfoB.setMsgCode("MG0006F");
            saveSupInfoB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
        }

        saveSupInfoRes.setStatus("00");
        return saveSupInfoRes;
    }

}
