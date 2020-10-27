package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllMTInfo.GetAllMTInfoRes;
import pnc.mesadmin.dto.GetAllMTInfo.GetAllMTInfoResB;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoReqBD00;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoRes;
import pnc.mesadmin.dto.GetMTInfo.GetMTInfoResB;
import pnc.mesadmin.dto.SaveMTInfo.*;
import pnc.mesadmin.entity.MaTypeInfo;
import pnc.mesadmin.service.MaTypeIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息Controller
 * 创建人：刘福志
 * 创建时间：2017-8-21
 * 修改人：pjf
 * 修改时间：2020-09-10
 */
@Controller
@Scope("prototype")
@RequestMapping("/MaType")
public class MaTypeController {
    @Resource
    private MaTypeIService maTypeIService;

    //获取物料类别页面
    @RequestMapping(value = "/MaTypePG")
    public String getBatchLinfoPage(){
        return "process/matype/matypeinfo";
    }

    //获取物料类别列表信息
    @RequestMapping(value ="/GetAllMTInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllMTInfoRes GetAllMTInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllMTInfoRes objEGetAllMTInfoRes=new GetAllMTInfoRes();

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
                objEGetAllMTInfoRes = maTypeIService.QALLselectAllMaTypeInfo(objEInitDataFields, pageInfo);
                objEGetAllMTInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllMTInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllMTInfoResB objEGetAllMTInfoResB = new GetAllMTInfoResB();
                objEGetAllMTInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllMTInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllMTInfoRes.setBody(objEGetAllMTInfoResB);
            }
        }else{
            GetAllMTInfoResB objEGetAllMTInfoResB = new GetAllMTInfoResB();
            objEGetAllMTInfoResB.setMsgCode("MG0006F");
            objEGetAllMTInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllMTInfoRes.setBody(objEGetAllMTInfoResB);
        }

        objEGetAllMTInfoRes.setStatus("00");
        return objEGetAllMTInfoRes;
    }

    /**
     * 查询物料类别列表信息(新)
     * @param req
     * @return
     */
    @PostMapping(value = "/GetAllMTNew")
    @ResponseBody
    public BaseResponse GetAllMTNew(@RequestBody BaseRequest req){
        try {
            return BaseResponse.success(maTypeIService.QALLMaTypeNew(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //获取物料类别信息
    @RequestMapping(value = "/GetMTInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetMTInfoRes GetMTInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetMTInfoRes objEGetMTInfoRes=new GetMTInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetMTInfoReqBD00 busData00 = CommonUtils.switchClass(GetMTInfoReqBD00.class,busData);
            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetMTInfoRes = maTypeIService.GetselectMaTypeInfo(busData00.getMTRd());
                    objEGetMTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetMTInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetMTInfoResB objEGetMTInfoResB = new GetMTInfoResB();
                    objEGetMTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetMTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetMTInfoRes.setBody(objEGetMTInfoResB);
                }
            }
        }else{
            GetMTInfoResB objEGetMTInfoResB = new GetMTInfoResB();
            objEGetMTInfoResB.setMsgCode("MG0006F");
            objEGetMTInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetMTInfoRes.setBody(objEGetMTInfoResB);
        }

        objEGetMTInfoRes.setStatus("00");

        return objEGetMTInfoRes;
    }

    //保存物料类别信息
    @RequestMapping(value ="/SaveMTInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveMTInfoRes SaveBLInfo(HttpServletRequest request, SaveReq saveReq){
        // 创建实体对象
        SaveMTInfoRes saveMTInfoRes = new SaveMTInfoRes();

        SaveMTInfoResB saveMTInfoResB= new SaveMTInfoResB();

        MaTypeInfo maTypeInfo = new MaTypeInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();

        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveMTInfoReqBD00 busData00 = CommonUtils.switchClass(SaveMTInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveMTInfoRes = maTypeIService.AddinsertMaTypeInfo(busData00,maTypeInfo);
                SaveMTInfoResB body = new SaveMTInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveMTInfoRes.setBody(body);
            }catch (SystemException e){
                saveMTInfoResB.setMsgCode(e.getMsgCode());
                saveMTInfoResB.setMsgDes(e.getMsgDes());
                saveMTInfoRes.setBody(saveMTInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveMTInfoReqBD01 busData01 = CommonUtils.switchClass(SaveMTInfoReqBD01.class,rowData);
            try {
                saveMTInfoRes= maTypeIService.RmdeleteMaTypeInfo(busData01.getMTRd());
                SaveMTInfoResB body = new SaveMTInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveMTInfoRes.setBody(body);
            }catch (SystemException e){
                saveMTInfoResB.setMsgCode(e.getMsgCode());
                saveMTInfoResB.setMsgDes(e.getMsgDes());
                saveMTInfoRes.setBody(saveMTInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveMTInfoReqBD02 busData02 = CommonUtils.switchClass(SaveMTInfoReqBD02.class,rowData);
            try {
                saveMTInfoRes = maTypeIService.ModupdateMaTypeInfo(busData02,maTypeInfo);
                SaveMTInfoResB body = new SaveMTInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveMTInfoRes.setBody(body);
            }catch (SystemException e){
                saveMTInfoResB.setMsgCode(e.getMsgCode());
                saveMTInfoResB.setMsgDes(e.getMsgDes());
                saveMTInfoRes.setBody(saveMTInfoResB);
            }
        }
        else{
            saveMTInfoResB.setMsgCode("MG0006F");
            saveMTInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02");
        }

        saveMTInfoRes.setStatus("00");
        return saveMTInfoRes;
    }
}
