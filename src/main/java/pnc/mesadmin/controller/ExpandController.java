package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoRes;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoResB;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoReq00;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoRes;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoResB;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoReq00;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoRes;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoResB;
import pnc.mesadmin.dto.GetExpandInfo.GetExpandInfoReq00;
import pnc.mesadmin.dto.GetExpandInfo.GetExpandInfoRes;
import pnc.mesadmin.dto.GetExpandInfo.GetExpandInfoResB;
import pnc.mesadmin.dto.SaveExpandInfo.*;
import pnc.mesadmin.service.ExpandIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 16:06
 * @Description:
 */
@Controller
@Scope("prototype")
@RequestMapping("/Expand")
public class ExpandController {
    @Resource
    private ExpandIService expandIService;

    @RequestMapping(value = "/ExpandPG")
    public String home(){
        return "currency/expand/expandinfo";
    }

    //列表
    @RequestMapping(value = "/GetAllExpandInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllExpandInfoRes GetAllCDataInfo(GetAllReq getAllReq) {
        GetAllExpandInfoRes getAllExpandInfoRes = new GetAllExpandInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
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
                getAllExpandInfoRes = expandIService.QALLGetAllExpandInfoRes(objEInitDataFields, pageInfo);
                getAllExpandInfoRes.getBody().setMsgCode("0x00000");
                getAllExpandInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllExpandInfoResB getAllExpandInfoResB= new GetAllExpandInfoResB();
                getAllExpandInfoResB.setMsgCode(e.getMsgCode());
                getAllExpandInfoResB.setMsgDes(e.getMsgDes());
                getAllExpandInfoRes.setBody(getAllExpandInfoResB);
            }

        } else {
            GetAllExpandInfoResB getAllExpandInfoResB= new GetAllExpandInfoResB();
            getAllExpandInfoResB.setMsgCode("MG0006F");
            getAllExpandInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getAllExpandInfoRes.setBody(getAllExpandInfoResB);
        }
        getAllExpandInfoRes.setStatus("00");
        return getAllExpandInfoRes;
    }

    /**
     * 获取物料扩展字段列表(新)
     * @param req
     * @return
     */
    @RequestMapping(value = "/GetAllExpandNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllCDataNew(@RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(expandIService.QALLExpandNewRes(req));
        }catch (SystemException e){
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //查询单个
    @RequestMapping(value = "/GetExpandInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetExpandInfoRes GetFaInfo(GetAllReq getAllReq) {
        GetExpandInfoRes getExpandInfoRes = new GetExpandInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetExpandInfoReq00 getExpandInfoReq00 = CommonUtils.switchClass(GetExpandInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getExpandInfoRes = expandIService.GetGetExpandInfoRes(getExpandInfoReq00);
                    getExpandInfoRes.getBody().setMsgCode("0x00000");
                    getExpandInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetExpandInfoResB getExpandInfoResB = new GetExpandInfoResB();
                    getExpandInfoResB.setMsgCode(e.getMsgCode());
                    getExpandInfoResB.setMsgDes(e.getMsgDes());
                    getExpandInfoRes.setBody(getExpandInfoResB);
                }
            }
        } else {
            GetExpandInfoResB getExpandInfoResB = new GetExpandInfoResB();
            getExpandInfoResB.setMsgCode("MG0006F");
            getExpandInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getExpandInfoRes.setBody(getExpandInfoResB);
        }

        getExpandInfoRes.setStatus("00");
        return getExpandInfoRes;
    }

    //保存
    @RequestMapping(value = "/SaveExpandInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveExpandInfoRes SaveExpandInfo(SaveReq saveReq) {
        SaveExpandInfoRes saveExpandInfoRes = new SaveExpandInfoRes();
        SaveExpandInfoResB saveExpandInfoResB = new SaveExpandInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveExpandInfoReq00 saveExpandInfoReq00 = CommonUtils.switchClass(SaveExpandInfoReq00.class, saveReq.getBusData());


            try {
                saveExpandInfoRes = expandIService.AddSaveExpandInfoRes(saveExpandInfoReq00);
                saveExpandInfoRes.getBody().setMsgCode("0x00000");
                saveExpandInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                saveExpandInfoResB = new SaveExpandInfoResB();
                saveExpandInfoResB.setMsgCode(e.getMsgCode());
                saveExpandInfoResB.setMsgDes(e.getMsgDes());
                saveExpandInfoRes.setBody(saveExpandInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveExpandInfoReq01 saveExpandInfoReq01 = CommonUtils.switchClass(SaveExpandInfoReq01.class, saveReq.getBusData());

            try {
                saveExpandInfoRes = expandIService.RmSaveExpandInfoRes(saveExpandInfoReq01);
                saveExpandInfoResB = new SaveExpandInfoResB();
                saveExpandInfoResB.setMsgCode("0x00000");
                saveExpandInfoResB.setMsgDes("成功");
                saveExpandInfoRes.setBody(saveExpandInfoResB);
            } catch (SystemException e) {
                saveExpandInfoResB = new SaveExpandInfoResB();
                saveExpandInfoResB.setMsgCode(e.getMsgCode());
                saveExpandInfoResB.setMsgDes(e.getMsgDes());
                saveExpandInfoRes.setBody(saveExpandInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveExpandInfoReq02 saveExpandInfoReq02 = CommonUtils.switchClass(SaveExpandInfoReq02.class, saveReq.getBusData());
            try {
                saveExpandInfoRes = expandIService.ModSaveExpandInfoRes(saveExpandInfoReq02);
                saveExpandInfoResB = new SaveExpandInfoResB();
                saveExpandInfoResB.setMsgCode("0x00000");
                saveExpandInfoResB.setMsgDes("成功");
                saveExpandInfoRes.setBody(saveExpandInfoResB);
            } catch (SystemException e) {
                saveExpandInfoResB = new SaveExpandInfoResB();
                saveExpandInfoResB.setMsgCode(e.getMsgCode());
                saveExpandInfoResB.setMsgDes(e.getMsgDes());
                saveExpandInfoRes.setBody(saveExpandInfoResB);
            }
        }  else {
            saveExpandInfoResB.setMsgCode("MG0006F");
            saveExpandInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00");
            saveExpandInfoRes.setBody(saveExpandInfoResB);
        }
        saveExpandInfoRes.setStatus("00");
        return saveExpandInfoRes;
    }

    //获取扩展字段获取扩展字段对象信息
    @RequestMapping(value = "/GetDWExpandInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetDWExpandInfoRes GetDWExpandInfo(GetAllReq getAllReq) {
        GetDWExpandInfoRes getDWExpandInfoRes = new GetDWExpandInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetDWExpandInfoReq00 getDWExpandInfoReq00 = CommonUtils.switchClass(GetDWExpandInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getDWExpandInfoRes = expandIService.GetDWExpandInfo(getDWExpandInfoReq00);
                    getDWExpandInfoRes.getBody().setMsgCode("0x00000");
                    getDWExpandInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetDWExpandInfoResB getDWExpandInfoResB = new GetDWExpandInfoResB();
                    getDWExpandInfoResB.setMsgCode(e.getMsgCode());
                    getDWExpandInfoResB.setMsgDes(e.getMsgDes());
                    getDWExpandInfoRes.setBody(getDWExpandInfoResB);
                }
            }
        } else if("01".equals(getAllReq.getExecType())){


            GetDWExpandInfoReq00 getDWExpandInfoReq00 = CommonUtils.switchClass(GetDWExpandInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getDWExpandInfoRes = expandIService.GetDWExpandInfo01(getDWExpandInfoReq00);
                    getDWExpandInfoRes.getBody().setMsgCode("0x00000");
                    getDWExpandInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetDWExpandInfoResB getDWExpandInfoResB = new GetDWExpandInfoResB();
                    getDWExpandInfoResB.setMsgCode(e.getMsgCode());
                    getDWExpandInfoResB.setMsgDes(e.getMsgDes());
                    getDWExpandInfoRes.setBody(getDWExpandInfoResB);
                }
            }
        }else  {
            GetDWExpandInfoResB getDWExpandInfoResB = new GetDWExpandInfoResB();
            getDWExpandInfoResB.setMsgCode("MG0006F");
            getDWExpandInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getDWExpandInfoRes.setBody(getDWExpandInfoResB);
        }

        getDWExpandInfoRes.setStatus("00");
        return getDWExpandInfoRes;
    }

    @RequestMapping(value = "/GetExpandFieldInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetExpandFieldInfoRes GetGetExpandFieldInfoRes(GetAllReq getAllReq) {
        GetExpandFieldInfoRes getExpandFieldInfoRes = new GetExpandFieldInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetExpandFieldInfoReq00 getExpandFieldInfoReq00 = CommonUtils.switchClass(GetExpandFieldInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {


            } else {
                try {
                    getExpandFieldInfoRes = expandIService.GetGetExpandFieldInfoRes(getExpandFieldInfoReq00);
                    getExpandFieldInfoRes.getBody().setMsgCode("0x00000");
                    getExpandFieldInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetExpandFieldInfoResB getExpandFieldInfoResB = new GetExpandFieldInfoResB();
                    getExpandFieldInfoResB.setMsgCode(e.getMsgCode());
                    getExpandFieldInfoResB.setMsgDes(e.getMsgDes());
                    getExpandFieldInfoRes.setBody(getExpandFieldInfoResB);
                }
            }
        } else {
            GetExpandFieldInfoResB getExpandFieldInfoResB = new GetExpandFieldInfoResB();
            getExpandFieldInfoResB.setMsgCode("MG0006F");
            getExpandFieldInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            getExpandFieldInfoRes.setBody(getExpandFieldInfoResB);
        }

        getExpandFieldInfoRes.setStatus("00");
        return getExpandFieldInfoRes;
    }
}
