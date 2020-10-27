package pnc.mesadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoRes;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoResB;
import pnc.mesadmin.dto.GetAllDevInfo.GetDevListsRequest;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoReqBD00;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoRes;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoResB;
import pnc.mesadmin.dto.SaveDevGPInfo.*;
import pnc.mesadmin.entity.DevGpInfo;
import pnc.mesadmin.service.DeviceGIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备组信息Controller
 * 创建人：刘福志
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
@Controller
@RequestMapping("/DeviceG")
public class DeviceGController {
    @Resource
    private DeviceGIService deviceGIService;

    //加载设备组页面
    @RequestMapping("/DeviceGPG")
    public String DevicePG(){
        return "res/devg/devginfo";
    }



    /**
     * @Description 分页查询列表
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @RequestMapping(value = "/GetDevGroupList", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetDevGroupList(SaveDevGpInfoReqBD02 request) {
        return deviceGIService.GetDevGroupList(request);
    }


    //获取设备组列表信息
    @RequestMapping(value ="/GetAllDevGInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetAllDevGInfoRes GetAllDevGInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetAllDevGInfoRes objEGetAllDevGInfoRes=new GetAllDevGInfoRes();

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
                objEGetAllDevGInfoRes = deviceGIService.QALLGetAllDevGInfoRes(objEInitDataFields, pageInfo);
                objEGetAllDevGInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllDevGInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllDevGInfoResB objEGetAllDevGInfoResB = new GetAllDevGInfoResB();
                objEGetAllDevGInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllDevGInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllDevGInfoRes.setBody(objEGetAllDevGInfoResB);
            }
        }else{
            GetAllDevGInfoResB objEGetAllDevGInfoResB = new GetAllDevGInfoResB();
            objEGetAllDevGInfoResB.setMsgCode("MG0006F");
            objEGetAllDevGInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllDevGInfoRes.setBody(objEGetAllDevGInfoResB);
        }

        objEGetAllDevGInfoRes.setStatus("00");
        return objEGetAllDevGInfoRes;
    }


    /**
     * 获取设备组列表信息(新)
     * @param
     * @return
     */
    @RequestMapping(value="/GetAllDevGNew",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllDevGNew(HttpServletRequest request, @RequestBody BaseRequest req){
        try {
            return BaseResponse.success(deviceGIService.QALLGetAllDevGNew(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }


    //获取设备组信息
    @RequestMapping(value = "/GetDevGInfo",method = RequestMethod.POST)
    @ResponseBody
    public GetDevGInfoRes GetDevGInfo(HttpServletRequest request, GetAllReq getAllReq){
        GetDevGInfoRes objEGetDevGInfoRes=new GetDevGInfoRes();

        if("00".equals(getAllReq.getExecType())){
            String busData = getAllReq.getBusData();

            GetDevGInfoReqBD00 busData00 = CommonUtils.switchClass(GetDevGInfoReqBD00.class,busData);

            // 分页
            if (getAllReq.getPageInfo() != null){

            }else{  // 不分页
                try{
                    objEGetDevGInfoRes = deviceGIService.GetGetDevGInfoRes(busData00.getDevGRd());
                    objEGetDevGInfoRes.getBody().setMsgCode("0x00000");
                    objEGetDevGInfoRes.getBody().setMsgDes("成功");
                }catch (SystemException e){
                    GetDevGInfoResB objEGetDevGInfoResB = new GetDevGInfoResB();
                    objEGetDevGInfoResB.setMsgCode(e.getMsgCode());
                    objEGetDevGInfoResB.setMsgDes(e.getMsgDes());
                    objEGetDevGInfoRes.setBody(objEGetDevGInfoResB);
                }
            }
        }else{
            GetDevGInfoResB objEGetDevGInfoResB = new GetDevGInfoResB();
            objEGetDevGInfoResB.setMsgCode("MG0006F");
            objEGetDevGInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetDevGInfoRes.setBody(objEGetDevGInfoResB);
        }

        objEGetDevGInfoRes.setStatus("00");

        return objEGetDevGInfoRes;
    }


    //保存设备组信息
    @RequestMapping(value ="/SaveDevGpInfo",method = RequestMethod.POST)
    @ResponseBody
    public SaveDevGpInfoRes SaveDevGpInfo(HttpServletRequest request, SaveReq saveReq){

        // 创建实体对象
        SaveDevGpInfoRes saveDevGpInfoRes = new SaveDevGpInfoRes();

        SaveDevGpInfoResB saveDevGpInfoResB = new SaveDevGpInfoResB();

        DevGpInfo devGpInfo = new DevGpInfo();
        // 转换成JsonObject实体类
        String rowData = saveReq.getBusData();


        // 新增
        if("00".equals(saveReq.getExecType())) {
            // JsonObject转换成实体类
            SaveDevGpInfoReqBD00 busData00 = CommonUtils.switchClass(SaveDevGpInfoReqBD00.class,rowData);
            // 直接可以获取的表单数据
            try {
                saveDevGpInfoRes = deviceGIService.AddinsertDevGpInfo(busData00,devGpInfo);
                SaveDevGpInfoResB body = new SaveDevGpInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevGpInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevGpInfoResB.setMsgCode(e.getMsgCode());
                saveDevGpInfoResB.setMsgDes(e.getMsgDes());
                saveDevGpInfoRes.setBody(saveDevGpInfoResB);
            }
        }
        // 删除
        else if("01".equals(saveReq.getExecType())){
            SaveDevGpInfoReqBD01 busData01 =  CommonUtils.switchClass(SaveDevGpInfoReqBD01.class,rowData);
            try {
                saveDevGpInfoRes = deviceGIService.RmdeleteDevGpInfo(busData01.getDevGRd());
                SaveDevGpInfoResB body = new SaveDevGpInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevGpInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevGpInfoResB.setMsgCode(e.getMsgCode());
                saveDevGpInfoResB.setMsgDes(e.getMsgDes());
                saveDevGpInfoRes.setBody(saveDevGpInfoResB);
            }
        }
        // 编辑
        else if("02".equals(saveReq.getExecType())){
            SaveDevGpInfoReqBD02 busData02 =  CommonUtils.switchClass(SaveDevGpInfoReqBD02.class,rowData);
            try {
                saveDevGpInfoRes = deviceGIService.ModupdateDevGpInfo(busData02,devGpInfo);
                SaveDevGpInfoResB body = new SaveDevGpInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevGpInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevGpInfoResB.setMsgCode(e.getMsgCode());
                saveDevGpInfoResB.setMsgDes(e.getMsgDes());
                saveDevGpInfoRes.setBody(saveDevGpInfoResB);
            }
        }else if("03".equals(saveReq.getExecType())){
            SaveDevGpInfoReqBD03 busData03 = CommonUtils.switchClass(SaveDevGpInfoReqBD03.class,rowData);
            try {
                saveDevGpInfoRes = deviceGIService.AddinsertDevGpInfo(busData03,devGpInfo);
                SaveDevGpInfoResB body = new SaveDevGpInfoResB();
                body.setMsgCode("0x00000");
                body.setMsgDes("成功！");
                saveDevGpInfoRes.setBody(body);
            }catch (SystemException e){
                saveDevGpInfoResB.setMsgCode(e.getMsgCode());
                saveDevGpInfoResB.setMsgDes(e.getMsgDes());
                saveDevGpInfoRes.setBody(saveDevGpInfoResB);
            }
        }else{
            saveDevGpInfoResB.setMsgCode("MG0006F");
            saveDevGpInfoResB.setMsgDes("参数名"+saveReq.getExecType()+"中值应该等于00、01、02、03");
        }

        saveDevGpInfoRes.setStatus("00");
        return saveDevGpInfoRes;
    }
}
