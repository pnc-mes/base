package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoRes;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoResB;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoReqBD00;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoRes;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResB;
import pnc.mesadmin.dto.GetLineInfo.GetLineInfoReqBD00;
import pnc.mesadmin.dto.GetLineInfo.GetLineInfoRes;
import pnc.mesadmin.dto.GetLineInfo.GetLineInfoResB;
import pnc.mesadmin.dto.SaveFaInfo.*;
import pnc.mesadmin.dto.SaveLineInfo.*;
import pnc.mesadmin.service.FactoryIService;
import pnc.mesadmin.service.LineIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：线体信息Controller
 * 创建人：haozan
 * 创建时间： 2018/6/7
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Line")
public class LineController {
    @Resource
    private FactoryIService factoryIService;

    @Resource
    private LineIService lineIService;

    //获取线体页面
    @RequestMapping(value = "/LinePG")
    public String getLinePGPage() {

        return "base/line/lineinfo";
    }

    //dto获取线体列表信息
    @RequestMapping(value = "/GetAllLineInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllLinefoRes GetAllLineInfo(GetAllReq getAllReq) {
        GetAllLinefoRes data = new GetAllLinefoRes();
        if ("00".equals(getAllReq.getExecType())) {
            List<InitDataField> lists = null;
            PageInfo pageInfo = null;

            if (getAllReq.getInitData() != null && !"".equals(getAllReq.getInitData())) {
                InitData objEInitData = CommonUtils.switchClass(InitData.class, getAllReq.getInitData());

                if (objEInitData != null) {
                    lists = objEInitData.getFiledList();
                }
            }

            if (getAllReq.getPageInfo() != null && !"".equals(getAllReq.getPageInfo())) {
                pageInfo = CommonUtils.switchClass(PageInfo.class, getAllReq.getPageInfo());
            }
            try {
                data = lineIService.QALLGetLinefoRes(lists, pageInfo);
                data.getBody().setMsgCode("0x00000");
                data.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllLinefoResB getAllLinefoResB = new GetAllLinefoResB();
                getAllLinefoResB.setMsgCode(e.getMsgCode());
                getAllLinefoResB.setMsgDes(e.getMsgDes());
                data.setBody(getAllLinefoResB);
            }

        } else {
            GetAllLinefoResB getAllLinefoResB = new GetAllLinefoResB();
            getAllLinefoResB.setMsgCode("MG0006F");
            getAllLinefoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            data.setBody(getAllLinefoResB);
        }
        data.setStatus("00");
        return data;
    }

    //获取线体列表信息（新）
    @RequestMapping(value = "/GetAllLineNew", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse GetAllLineInfo(@RequestBody BaseRequest req) {
        try {
            return BaseResponse.success(lineIService.QALLGetLineNewRes(req));
        } catch (SystemException e) {
            return BaseResponse.error(e.getMsgCode(), e.getMsgDes());
        }
    }

    //dto获取获取线体信息
    @RequestMapping(value = "/GetLineInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetLineInfoRes GetLineInfo(GetAllReq getAllReq) {
        GetLineInfoRes objEGetFaInfoRes = new GetLineInfoRes();
        if ("00".equals(getAllReq.getExecType())) {

            GetLineInfoReqBD00 objEGetFaInfoReqBD00 = CommonUtils.switchClass(GetLineInfoReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {
            } else {
                try {
                    objEGetFaInfoRes = lineIService.GetGetLineInfoRes(objEGetFaInfoReqBD00);
                    objEGetFaInfoRes.getBody().setMsgCode("0x00000");
                    objEGetFaInfoRes.getBody().setMsgDes("成功");
                } catch (SystemException e) {
                    GetLineInfoResB objEGetFaInfoResB = new GetLineInfoResB();
                    objEGetFaInfoResB.setMsgCode(e.getMsgCode());
                    objEGetFaInfoResB.setMsgDes(e.getMsgDes());
                    objEGetFaInfoRes.setBody(objEGetFaInfoResB);
                }
            }
        } else {
            GetLineInfoResB objEGetFaInfoResB = new GetLineInfoResB();
            objEGetFaInfoResB.setMsgCode("MG0006F");
            objEGetFaInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetFaInfoRes.setBody(objEGetFaInfoResB);
        }

        objEGetFaInfoRes.setStatus("00");
        return objEGetFaInfoRes;
    }


    //dto保存线体信息
    @RequestMapping(value = "/SaveLineInfo", method = RequestMethod.POST)
    @ResponseBody
    public SaveLineInfoRes SaveLineInfo(SaveReq saveReq) {
        SaveLineInfoRes objESaveFaInfoRes = new SaveLineInfoRes();
        SaveLineInfoResB objESaveFaInfoResB = new SaveLineInfoResB();


        if ("00".equals(saveReq.getExecType())) {
            SaveLineInfoReqBD00 objESaveCpInfoRes00 = CommonUtils.switchClass(SaveLineInfoReqBD00.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = lineIService.AddGetLineInfoRes(objESaveCpInfoRes00);
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("01".equals(saveReq.getExecType())) {
            SaveLineInfoReqBD01 objESaveCpInfoRes01 = CommonUtils.switchClass(SaveLineInfoReqBD01.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = lineIService.RmSaveLineInfoRes(objESaveCpInfoRes01);
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("02".equals(saveReq.getExecType())) {
            SaveLineInfoReqBD02 objESaveCpInfoRes02 = CommonUtils.switchClass(SaveLineInfoReqBD02.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = lineIService.ModSaveLineInfoRes(objESaveCpInfoRes02);
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else if ("03".equals(saveReq.getExecType())) {
            SaveLineInfoReqBD03 objESaveFaInfoReqBD03 = CommonUtils.switchClass(SaveLineInfoReqBD03.class, saveReq.getBusData());

            try {
                objESaveFaInfoRes = lineIService.AddSaveLineInfoRes(objESaveFaInfoReqBD03);
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode("0x00000");
                objESaveFaInfoResB.setMsgDes("成功");
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            } catch (SystemException e) {
                objESaveFaInfoResB = new SaveLineInfoResB();
                objESaveFaInfoResB.setMsgCode(e.getMsgCode());
                objESaveFaInfoResB.setMsgDes(e.getMsgDes());
                objESaveFaInfoRes.setBody(objESaveFaInfoResB);
            }
        } else {
            objESaveFaInfoResB.setMsgCode("MG0006F");
            objESaveFaInfoResB.setMsgDes("参数名" + saveReq.getExecType() + "中值应该等于00、01、02、03");
            objESaveFaInfoRes.setBody(objESaveFaInfoResB);
        }
        objESaveFaInfoRes.setStatus("00");
        return objESaveFaInfoRes;
    }
}
