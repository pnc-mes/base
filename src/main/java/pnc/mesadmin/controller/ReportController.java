package pnc.mesadmin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.BaseDto.BaseResB;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllBcirExcp.GetAllBcirExcpReq;
import pnc.mesadmin.dto.GetAllKCRecInfo.GetAllKCRecInfoRes;
import pnc.mesadmin.dto.GetAllKCRecInfo.GetAllKCRecInfoResB;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoRes;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoResB;
import pnc.mesadmin.dto.GetAllLYMaInfo.GetAllLYMaInfoResD1;
import pnc.mesadmin.dto.GetAllProdInfo.GetAllProdInfoRes;
import pnc.mesadmin.dto.GetAllProdInfo.GetAllProdInfoResB;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSWareInfoRes;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSWareInfoResB;
import pnc.mesadmin.dto.GetAllSWareInfo.GetAllSum;
import pnc.mesadmin.dto.GetAllWIPInfo.GetAllWIPInfoRes;
import pnc.mesadmin.dto.GetAllWIPInfo.GetAllWIPInfoResB;
import pnc.mesadmin.dto.GetOTInfo.GetOTInfoReqBD00;
import pnc.mesadmin.dto.GetOTInfo.GetOTInfoRes;
import pnc.mesadmin.dto.GetOTInfo.GetOTInfoResB;
import pnc.mesadmin.service.BcirExcpService;
import pnc.mesadmin.service.ReportIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称： 在线追踪Controller
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
@Controller
@Scope("prototype")
@RequestMapping("/Report")
public class ReportController {
    @Resource
    private ReportIService reportIService;

    @Resource
    private BcirExcpService bcirExcpService;

    //在线跟踪
    @RequestMapping("/OTGP")
    public String onTraveler() {
        return "search/ontraveler/onTraveler";
    }

    //库存预警
    @RequestMapping("/SWarePG")
    public String SWarePG() {
        return "search/swareinfo/swareinfo";
    }

    @RequestMapping("/wipreportPG")
    public String toWipreportPG() {
        return "mprocess/wipreport";
    }

    @RequestMapping("/prodPG")
    public String toprodPG() {
        return "mprocess/prodreport";
    }

    @RequestMapping("/OnLineMaPG")
    public String OnLineMaPG() {
        return "search/onlinema/onlinema";
    }

    //库存变动报表
    @RequestMapping("/KCRecordReportPG")
    public String KCRecordReportPG() {
        return "search/kcrecord/kcrecord";
    }

    @RequestMapping(value = "/BcirExcpPG")
    public String BOMChangeView() {
        return "search/gzyc/gzyc";
    }

    //生产日报表
    @ResponseBody
    @RequestMapping(value = "/GetAllProdInfo", method = RequestMethod.POST)
    public GetAllProdInfoRes getAllProdInfo(GetAllReq argGetAllReq) {
        GetAllProdInfoRes objGetAllProdInfoRes = new GetAllProdInfoRes();
        GetAllProdInfoResB objGetAllProdInfoResB = new GetAllProdInfoResB();

        InitData objInitData = null;
        List<InitDataField> initDataFieldList = new ArrayList<InitDataField>(Collections.<InitDataField>emptyList());
        if (!"".equals(argGetAllReq.getInitData()) && argGetAllReq.getInitData() != null) {
            objInitData = CommonUtils.switchClass(InitData.class, argGetAllReq.getInitData());
            if (objInitData.getFiledList() != null && objInitData.getFiledList().size() > 0) {
                initDataFieldList = objInitData.getFiledList();
            }
        }
        objGetAllProdInfoResB = reportIService.QALLProdRepInfo(initDataFieldList, null);

        objGetAllProdInfoRes.setBody(objGetAllProdInfoResB);
        objGetAllProdInfoRes.setStatus("00");
        return objGetAllProdInfoRes;
    }

    @ResponseBody
    @RequestMapping(value = "GetAllWIPInfo", method = RequestMethod.POST)
    public GetAllWIPInfoRes GetAllWIPInfo(GetAllReq argGetAllReq) {
        GetAllWIPInfoRes objGetAllWIPInfoRes = new GetAllWIPInfoRes();
        GetAllWIPInfoResB objGetAllWIPInfoResB = new GetAllWIPInfoResB();

        InitData objInitData = null;
        List<InitDataField> initDataFieldList = new ArrayList<InitDataField>(Collections.<InitDataField>emptyList());
        if (!"".equals(argGetAllReq.getInitData()) && argGetAllReq.getInitData() != null) {
            objInitData = CommonUtils.switchClass(InitData.class, argGetAllReq.getInitData());
            if (objInitData.getFiledList() != null && objInitData.getFiledList().size() > 0) {
                initDataFieldList = objInitData.getFiledList();
            }
        }
        objGetAllWIPInfoResB = reportIService.QALLWIPInfo(initDataFieldList, null);

        objGetAllWIPInfoRes.setBody(objGetAllWIPInfoResB);
        objGetAllWIPInfoRes.setStatus("00");
        return objGetAllWIPInfoRes;
    }

    //dto查看在线追踪
    @RequestMapping(value = "/GetOTInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetOTInfoRes GetOTInfo(GetAllReq getAllReq) {
        GetOTInfoRes objEGetOTInfoRes = new GetOTInfoRes();
        if ("00".equals(getAllReq.getExecType())) {
            GetOTInfoReqBD00 objEGetOTInfoReqBD00 = CommonUtils.switchClass(GetOTInfoReqBD00.class, getAllReq.getBusData());
            if (getAllReq.getPageInfo() != null) {

            } else {

                try {
                    objEGetOTInfoRes = reportIService.GetGetOTInfoRes(objEGetOTInfoReqBD00);
                    objEGetOTInfoRes.getBody().setMsgCode("0x00000");
                    objEGetOTInfoRes.getBody().setMsgDes("成功");

                } catch (SystemException e) {
                    GetOTInfoResB objEGetOTInfoResB = new GetOTInfoResB();
                    objEGetOTInfoResB.setMsgCode(e.getMsgCode());
                    objEGetOTInfoResB.setMsgDes(e.getMsgDes());
                    objEGetOTInfoRes.setBody(objEGetOTInfoResB);
                }
            }

        } else {
            GetOTInfoResB objEGetOTInfoResB = new GetOTInfoResB();
            objEGetOTInfoResB.setMsgCode("MG0006F");
            objEGetOTInfoResB.setMsgDes("参数名" + getAllReq.getExecType() + "中值应该等于00");
            objEGetOTInfoRes.setBody(objEGetOTInfoResB);
        }
        objEGetOTInfoRes.setStatus("00");
        return objEGetOTInfoRes;
    }


    //库存预警报表
    @RequestMapping(value = "/GetAllSWareInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllSWareInfoRes GetAllSWareInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllSWareInfoRes objEGetAllSWareInfoRes = new GetAllSWareInfoRes();

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
                objEGetAllSWareInfoRes = reportIService.QALLGetAllSWareInfoRes(objEInitDataFields, pageInfo);
                objEGetAllSWareInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllSWareInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSWareInfoResB objEGetAllSWareInfoResB = new GetAllSWareInfoResB();
                objEGetAllSWareInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllSWareInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllSWareInfoRes.setBody(objEGetAllSWareInfoResB);
            }
        } else {
            GetAllSWareInfoResB objEGetAllSWareInfoResB = new GetAllSWareInfoResB();
            objEGetAllSWareInfoResB.setMsgCode("MG0006F");
            objEGetAllSWareInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllSWareInfoRes.setBody(objEGetAllSWareInfoResB);
        }

        objEGetAllSWareInfoRes.setStatus("00");
        return objEGetAllSWareInfoRes;
    }

    //库存变动报表
    @RequestMapping(value = "/GetAllKCRecInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllKCRecInfoRes GetAllKCRecInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllKCRecInfoRes objEGetAllKCRecInfoRes = new GetAllKCRecInfoRes();

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
                objEGetAllKCRecInfoRes = reportIService.QALLGetAllKCRecInfoRes(objEInitDataFields, pageInfo);
                objEGetAllKCRecInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllKCRecInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllKCRecInfoResB objEGetAllKCRecInfoResB = new GetAllKCRecInfoResB();
                objEGetAllKCRecInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllKCRecInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllKCRecInfoRes.setBody(objEGetAllKCRecInfoResB);
            }
        } else {
            GetAllKCRecInfoResB objEGetAllKCRecInfoResB = new GetAllKCRecInfoResB();
            objEGetAllKCRecInfoResB.setMsgCode("MG0006F");
            objEGetAllKCRecInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllKCRecInfoRes.setBody(objEGetAllKCRecInfoResB);
        }

        objEGetAllKCRecInfoRes.setStatus("00");
        return objEGetAllKCRecInfoRes;
    }

    //筛选汇总
    @RequestMapping(value = "/GetAllSWareInfo1", method = RequestMethod.POST)
    @ResponseBody
    public GetAllSWareInfoRes GetAllSum(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllSWareInfoRes objEGetAllSWareInfoRes = new GetAllSWareInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            GetAllSum objEGetAllSum = CommonUtils.switchClass(GetAllSum.class, getAllReq.getBusData());

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
                objEGetAllSWareInfoRes = reportIService.GetAllSum(objEGetAllSum, objEInitDataFields, pageInfo);
                objEGetAllSWareInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllSWareInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllSWareInfoResB objEGetAllSWareInfoResB = new GetAllSWareInfoResB();
                objEGetAllSWareInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllSWareInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllSWareInfoRes.setBody(objEGetAllSWareInfoResB);
            }
        } else {
            GetAllSWareInfoResB objEGetAllSWareInfoResB = new GetAllSWareInfoResB();
            objEGetAllSWareInfoResB.setMsgCode("MG0006F");
            objEGetAllSWareInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllSWareInfoRes.setBody(objEGetAllSWareInfoResB);
        }

        objEGetAllSWareInfoRes.setStatus("00");
        return objEGetAllSWareInfoRes;
    }


    //导出库存预警
    @RequestMapping(value = "/ExportSWareInfo", method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportSWareInfo(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq) {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

        if ("00".equals(getAllReq.getExecType())) {
            GetAllSum objEGetAllSum = CommonUtils.switchClass(GetAllSum.class, getAllReq.getBusData());

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
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = reportIService.ExportSWareInfo(objEGetAllSum, objEInitDataFields, pageInfo);
                exportSWareInfoRes.setStatus("00");
                exportSWareInfoResB.setMsgCode("0x00000");
                exportSWareInfoResB.setMsgDes("成功!");
                exportSWareInfoRes.setBody(exportSWareInfoResB);

                //设置response参数,可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + "test" + ".xls");

                byte[] content = os.toByteArray();
                InputStream is = new ByteArrayInputStream(content);

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;

                try {
                    ServletOutputStream out = response.getOutputStream();

                    bis = new BufferedInputStream(is);
                    bos = new BufferedOutputStream(out);
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                        }
                    }
                }

            } catch (SystemException objSystemException) {
                exportSWareInfoResB.setMsgCode(objSystemException.getMsgCode());
                exportSWareInfoResB.setMsgDes(objSystemException.getMsgDes());
                exportSWareInfoRes.setBody(exportSWareInfoResB);
                exportSWareInfoRes.setStatus("01");
            }
        }
        return exportSWareInfoRes;
    }

    //产线余料报表
    @RequestMapping(value = "/GetAllLYMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public GetAllLYMaInfoRes GetAllLYMaInfo(HttpServletRequest request, GetAllReq getAllReq) {
        GetAllLYMaInfoRes objEGetAllLYMaInfoRes = new GetAllLYMaInfoRes();

        if ("00".equals(getAllReq.getExecType())) {
            GetAllLYMaInfoResD1 objEGetAllLYMaInfoResD1 = CommonUtils.switchClass(GetAllLYMaInfoResD1.class, getAllReq.getBusData());
            try {
                objEGetAllLYMaInfoRes = reportIService.QALLGetAllLYMaInfoRes(objEGetAllLYMaInfoResD1);
                objEGetAllLYMaInfoRes.getBody().setMsgCode("0x00000");
                objEGetAllLYMaInfoRes.getBody().setMsgDes("成功");
            } catch (SystemException e) {
                GetAllLYMaInfoResB objEGetAllLYMaInfoResB = new GetAllLYMaInfoResB();
                objEGetAllLYMaInfoResB.setMsgCode(e.getMsgCode());
                objEGetAllLYMaInfoResB.setMsgDes(e.getMsgDes());
                objEGetAllLYMaInfoRes.setBody(objEGetAllLYMaInfoResB);
            }
        } else {
            GetAllLYMaInfoResB objEGetAllLYMaInfoResB = new GetAllLYMaInfoResB();
            objEGetAllLYMaInfoResB.setMsgCode("MG0006F");
            objEGetAllLYMaInfoResB.setMsgDes("参数名ExecType中值应该等于00");
            objEGetAllLYMaInfoRes.setBody(objEGetAllLYMaInfoResB);
        }

        objEGetAllLYMaInfoRes.setStatus("00");
        return objEGetAllLYMaInfoRes;
    }

    //导出产线余料报表
    @RequestMapping(value = "/ExportYMaInfo", method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportYMaInfo(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq) {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        GetAllLYMaInfoResD1 objEGetAllLYMaInfoResD1 = CommonUtils.switchClass(GetAllLYMaInfoResD1.class, getAllReq.getBusData());
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os = reportIService.ExportYMaInfo(objEGetAllLYMaInfoResD1);
            exportSWareInfoRes.setStatus("00");
            exportSWareInfoResB.setMsgCode("0x00000");
            exportSWareInfoResB.setMsgDes("成功!");
            exportSWareInfoRes.setBody(exportSWareInfoResB);

            //设置response参数,可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "test" + ".xls");

            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                ServletOutputStream out = response.getOutputStream();

                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                    }
                }
            }

        } catch (SystemException objSystemException) {
            exportSWareInfoResB.setMsgCode(objSystemException.getMsgCode());
            exportSWareInfoResB.setMsgDes(objSystemException.getMsgDes());
            exportSWareInfoRes.setBody(exportSWareInfoResB);
            exportSWareInfoRes.setStatus("01");
        }

        return exportSWareInfoRes;
    }

    //导出库存变动
    @RequestMapping(value = "/ExportGetAllKCRecInfo", method = RequestMethod.POST)
    @ResponseBody
    public ExportSWareInfoRes ExportGetAllKCRecInfo(HttpServletResponse response, SaveReq saveReq, GetAllReq getAllReq) {
        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();

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
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os = reportIService.ExportGetAllKCRecInfo(objEInitDataFields, pageInfo);
                exportSWareInfoRes.setStatus("00");
                exportSWareInfoResB.setMsgCode("0x00000");
                exportSWareInfoResB.setMsgDes("成功!");
                exportSWareInfoRes.setBody(exportSWareInfoResB);

                //设置response参数,可以打开下载页面
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + "test" + ".xls");

                byte[] content = os.toByteArray();
                InputStream is = new ByteArrayInputStream(content);

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;

                try {
                    ServletOutputStream out = response.getOutputStream();

                    bis = new BufferedInputStream(is);
                    bos = new BufferedOutputStream(out);
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                        }
                    }
                }

            } catch (SystemException objSystemException) {
                exportSWareInfoResB.setMsgCode(objSystemException.getMsgCode());
                exportSWareInfoResB.setMsgDes(objSystemException.getMsgDes());
                exportSWareInfoRes.setBody(exportSWareInfoResB);
                exportSWareInfoRes.setStatus("01");
            }
        }
        return exportSWareInfoRes;
    }


    @RequestMapping(value = "/GetAllGZYCInfo", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes GetAllBcirExcp(SaveReq saveReq) {
        BaseRes baseResponse = new BaseRes();
        BaseResB baseResBody = new BaseResB();
        GetAllBcirExcpReq request = CommonUtils.switchClass(GetAllBcirExcpReq.class, saveReq.getBusData());
        try {
            if ("00".equals(saveReq.getExecType())) {
                baseResponse = bcirExcpService.GetAllBcirExcp(request);
            }
        } catch (SystemException e) {
            baseResBody.setMsgCode(e.getMsgCode());
            baseResBody.setMsgDes(e.getMsgDes());
            baseResponse.setBody(baseResBody);
        }
        baseResponse.setStatus("00");
        return baseResponse;
    }

}
