package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoRes;
import pnc.mesadmin.dto.GetAllSpecInfo.GetAllSpecInfoResB;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoReq00;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoReq01;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoRes;
import pnc.mesadmin.dto.GetSVInfo.GetSVInfoResB;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoReq00;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoRes;
import pnc.mesadmin.dto.GetSVTreeInfo.GetSVTreeInfoResB;
import pnc.mesadmin.dto.SaveSpecInfo.*;
import pnc.mesadmin.service.SpecIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序信息Controller
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Spec")
public class SpecController {
    @Resource
    private SpecIService specIService;

    //加载页面
    @RequestMapping("/SpecPG")
    public String Spec() {
        return "process/spec/spec";
    }

    //获取工序列表信息
    @RequestMapping(value = "/GetAllSpecInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllSpecInfoRes GetAllSpecInfo(GetAllReq getAllReq) {
        GetAllSpecInfoRes objEGetAllSpecInfoRes = new GetAllSpecInfoRes();
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
                objEGetAllSpecInfoRes = specIService.QALLGetAllSpecInfoRes(objEInitDataFields, pageInfo);
                objEGetAllSpecInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllSpecInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSpecInfoResB objEGetAllSpecInfoResB = new GetAllSpecInfoResB();
                objEGetAllSpecInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllSpecInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllSpecInfoRes.setBody(objEGetAllSpecInfoResB);
            }

        } else {
            GetAllSpecInfoResB objEGetAllSpecInfoResB = new GetAllSpecInfoResB();
            objEGetAllSpecInfoResB.setMsgCode("MG0006F");
            objEGetAllSpecInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetAllSpecInfoRes.setBody(objEGetAllSpecInfoResB);
        }

        objEGetAllSpecInfoRes.setStatus("00");
        return objEGetAllSpecInfoRes;
    }

    //获取工序列表信息新
    @RequestMapping(value = "/GetAllSpecNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllSpecNew(HttpServletRequest request, @RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(specIService.QALLGetAllSpecNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    @RequestMapping(value = "/GetSVTreeInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSVTreeInfoRes GetSVTreeInfo(GetAllReq getAllReq) {
        GetSVTreeInfoRes objEGetSVTreeInfoRes = new GetSVTreeInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            GetSVTreeInfoReq00 objEGetSVTreeInfoReq00 = CommonUtils.switchClass(GetSVTreeInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {
            } else {
                try {
                    objEGetSVTreeInfoRes = specIService.GetGetSVTreeInfoRes(objEGetSVTreeInfoReq00);
                    objEGetSVTreeInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSVTreeInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetSVTreeInfoResB objEGetSVTreeInfoResB = new GetSVTreeInfoResB();
                    objEGetSVTreeInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSVTreeInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSVTreeInfoRes.setBody(objEGetSVTreeInfoResB);
                }
            }
        } else {
            GetSVTreeInfoResB objEGetSVTreeInfoResB = new GetSVTreeInfoResB();
            objEGetSVTreeInfoResB.setMsgCode("MG0006F");
            objEGetSVTreeInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetSVTreeInfoRes.setBody(objEGetSVTreeInfoResB);
        }
        objEGetSVTreeInfoRes.setStatus("00");
        return objEGetSVTreeInfoRes;
    }


    //获取工序信息
    @RequestMapping(value = "/GetSVInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetSVInfoRes GetSVInfo(GetAllReq getAllReq) {
        GetSVInfoRes objEGetSVInfoRes = new GetSVInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            GetSVInfoReq00 objEGetSVInfoReq00 = CommonUtils.switchClass(GetSVInfoReq00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    objEGetSVInfoRes = specIService.GetGetSVInfoRes(objEGetSVInfoReq00);
                    objEGetSVInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSVInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetSVInfoResB objEGetSVInfoResB = new GetSVInfoResB();
                    objEGetSVInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSVInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSVInfoRes.setBody(objEGetSVInfoResB);
                }
            }

        } else if ("01".equals(getAllReq.getExecType())) {
            GetSVInfoReq01 objEGetSVInfoReq01 = CommonUtils.switchClass(GetSVInfoReq01.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {
                try {
                    objEGetSVInfoRes = specIService.GetGetSVInfoRes1(objEGetSVInfoReq01);
                    objEGetSVInfoRes.getBody().setMsgCode("0x00000");
                    objEGetSVInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetSVInfoResB objEGetSVInfoResB = new GetSVInfoResB();
                    objEGetSVInfoResB.setMsgCode(e.getMsgCode());
                    objEGetSVInfoResB.setMsgDes(e.getMsgDes());
                    objEGetSVInfoRes.setBody(objEGetSVInfoResB);
                }
            }
        } else {
            GetSVInfoResB objEGetSVInfoResB = new GetSVInfoResB();
            objEGetSVInfoResB.setMsgCode("MG0006F");
            objEGetSVInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00、01");
            objEGetSVInfoRes.setBody(objEGetSVInfoResB);
        }
        objEGetSVInfoRes.setStatus("00");
        return objEGetSVInfoRes;
    }

    //保存工序信息
    @RequestMapping(value = "/SaveSpecInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveSpecInfoRes SaveSpecInfo(SaveReq saveReq) {
        SaveSpecInfoRes objESaveSpecInfoRes = new SaveSpecInfoRes();
        SaveSpecInfoResB objESaveSpecInfoResB = null;
        if ("00".equals(saveReq.getExecType())) {
            SaveSpecInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveSpecInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveSpecInfoRes = specIService.AddSaveSpecInfoRes(objESaveCpInfoRes00);
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode("0x00000");
                objESaveSpecInfoResB.setMsgDes("成功");
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            } catch (SystemException e) {
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode(e.getMsgCode());
                objESaveSpecInfoResB.setMsgDes(e.getMsgDes());
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            }
        }
        if ("01".equals(saveReq.getExecType())) {
            SaveSpecInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveSpecInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveSpecInfoRes = specIService.RmSaveSpecInfoRes(objESaveCpInfoRes01);
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode("0x00000");
                objESaveSpecInfoResB.setMsgDes("成功");
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            } catch (SystemException e) {
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode(e.getMsgCode());
                objESaveSpecInfoResB.setMsgDes(e.getMsgDes());
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            }
        }
        if ("02".equals(saveReq.getExecType())) {
            SaveSpecInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveSpecInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveSpecInfoRes = specIService.ModSaveSpecInfoRes(objESaveCpInfoRes02);
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode("0x00000");
                objESaveSpecInfoResB.setMsgDes("成功");
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            } catch (SystemException e) {
                objESaveSpecInfoRes = new SaveSpecInfoRes();
                objESaveSpecInfoResB = new SaveSpecInfoResB();

                objESaveSpecInfoResB.setMsgCode(e.getMsgCode());
                objESaveSpecInfoResB.setMsgDes(e.getMsgDes());

                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            }
        }
        //
        if ("03".equals(saveReq.getExecType())) {
            SaveSpecInfoReqBD03 objESaveCpInfoRes03 = CommonUtils.switchClass(SaveSpecInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveSpecInfoRes = specIService.AddSaveSpecInfoRe(objESaveCpInfoRes03);
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode("0x00000");
                objESaveSpecInfoResB.setMsgDes("成功");
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            } catch (SystemException e) {
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode(e.getMsgCode());
                objESaveSpecInfoResB.setMsgDes(e.getMsgDes());
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            }
        }
        if ("04".equals(saveReq.getExecType())) {

            SaveSpecInfoReqBD04 objESaveCpInfoRes04 = CommonUtils.switchClass(SaveSpecInfoReqBD04.class, saveReq.getBusData());

            try {
                objESaveSpecInfoRes = specIService.AddSaveSpecInfoReq04(objESaveCpInfoRes04);
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode("0x00000");
                objESaveSpecInfoResB.setMsgDes("成功");
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            } catch (SystemException e) {
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode(e.getMsgCode());
                objESaveSpecInfoResB.setMsgDes(e.getMsgDes());
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            }
        }
        if ("05".equals(saveReq.getExecType())) {
        /*    JsonConfig config = new JsonConfig();
            config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {

                @Override
                public String transformToJavaIdentifier(String str) {
                    char[] chars = str.toCharArray();
                    chars[0] = Character.toLowerCase(chars[0]);
                    return new String(chars);
                }
            });
            config.setRootClass(SaveSpecInfoReqBD05.class);*/
            SaveSpecInfoReqBD05 objESaveCpInfoRes05 = CommonUtils.switchClass(SaveSpecInfoReqBD05.class, saveReq.getBusData());


            try {
                objESaveSpecInfoRes = specIService.RmSaveSpecInfoReq05(objESaveCpInfoRes05);
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode("0x00000");
                objESaveSpecInfoResB.setMsgDes("成功");
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            } catch (SystemException e) {
                objESaveSpecInfoResB = new SaveSpecInfoResB();
                objESaveSpecInfoResB.setMsgCode(e.getMsgCode());
                objESaveSpecInfoResB.setMsgDes(e.getMsgDes());
                objESaveSpecInfoRes.setBody(objESaveSpecInfoResB);
            }
        }
        objESaveSpecInfoRes.setStatus("00");
        return objESaveSpecInfoRes;
    }

}
