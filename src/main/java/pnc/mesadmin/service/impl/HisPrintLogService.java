package pnc.mesadmin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.json.JSONObject;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllHPLInfo.GetAllHPLInfoResD;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveRePrInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.HisPrintLogIService;
import pnc.mesadmin.utils.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印记录查询业务逻辑
 * 创建人：pjf
 * 创建时间：2020-10-12
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class HisPrintLogService implements HisPrintLogIService {

    @Resource
    private PrintTaskDAO printTaskDAO;

    @Resource
    private RePrintLogDAO rePrintLogDAO;

    @Resource
    private PrinterDAO printerDAO;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private BDAO bdao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private PrintTBFDAO printTBFDAO;

    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private BAttachDAO bAttachDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private PrintScriptInDAO printScriptInDAO;

    @Resource
    private PrintScriptOutDAO printScriptOutDAO;

    @Resource
    private WmsMaterialsBDAO wmsMaterialsBDAO;

    /**
     * 获取打印记录
     * @param request
     * @return
     */
    @Override
    public PageResult<GetAllHPLInfoResD> QALLPrintTaskInfo(BaseRequest request) {
        List<GetAllHPLInfoResD> resDList = new ArrayList<>();
        GetAllHPLInfoResD resD = null;

        Page<PrintTaskInfo> page = printTaskDAO.selectPage(new MyPage(request), CommonUtils.getQueryWrapper(request.getFields()));

        for (PrintTaskInfo printTaskInfo : page.getRecords()) {
            resD = new GetAllHPLInfoResD();
            resD.setPTCode(printTaskInfo.getpTCode());
            resD.setReCode(printTaskInfo.getReCode());
            resD.setOrderType(printTaskInfo.getOrderType());
            resD.setBarCode(printTaskInfo.getBarCode());
            resD.setBarType(printTaskInfo.getBarType());
            resD.setPrintCount(printTaskInfo.getPrintCount());
            resD.setPrintCopy(printTaskInfo.getPrintCopy());
            resD.setMaName(printTaskInfo.getMaterialName());
            resD.setMaCode(printTaskInfo.getMaterialCode());
            if (printTaskInfo.getLastModifyTime() != null) {
                resD.setLastPTime(DateUtil.format(printTaskInfo.getLastModifyTime()));
            } else {
                resD.setLastPTime(DateUtil.format(printTaskInfo.getCreateTime()));
            }

            resDList.add(resD);
        }

        return new PageResult<>(page, resDList);
    }

    /**
     * 重打
     * @param argBD00
     */
    @Override
    public void AddRePrintLogInfo(SaveRePrInfoReqBD00 argBD00) {
        if (argBD00.getRePrInfo() != null) {
            //当前用户
            String userName = CommonUtils.readUser().getRealName();
            //当前时间
            Date date = new Date();
            //批次
            BInfo objEBInfo = null;
            MaVerInfo objEMaVerInfo = null;
            Map<String, Object> map = null;

            //打印机
            PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(argBD00.getPrintRd());
            if (objEPrinterInfo == null) {
                throw new SystemException("", "打印机不存在!");
            }

            //重打记录信息
            RePrintLogInfo objERePrintLogInfo = new RePrintLogInfo();
            objERePrintLogInfo.setPrintCount(argBD00.getPrintCount());
            objERePrintLogInfo.setPrintCopy(argBD00.getPrintCopy());
            objERePrintLogInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            objERePrintLogInfo.setBarType("02");
            objERePrintLogInfo.setCreator(userName);
            objERePrintLogInfo.setCreateTime(date);

            //打印模板
            PrintTInfo objEPrintTInfo = null;

            for (SaveRePrInfoReqBD00RePrInfo reprinfo : argBD00.getRePrInfo()) {
                //查询打印任务信息
                PrintTaskInfo objEPrintTaskInfo = printTaskDAO.SelectByPTCode(reprinfo.getPTCode());
                if (objEPrintTaskInfo != null) {
                    if ("02".equals(objEPrintTaskInfo.getBarType())) {
                        //查询批次
                        objEBInfo = bdao.selectBatchInfoByBatch(objEPrintTaskInfo.getBarCode());
                        if (objEBInfo == null) {
                            throw new SystemException("", objEPrintTaskInfo.getBarCode() + "批次不存在!");
                        }
                        if (objEBInfo.getCanNum() <= 0) {
                            throw new SystemException("", objEPrintTaskInfo.getBarCode() + "批次数量小于零!");
                        }
                        objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
                        if (objEMaVerInfo == null) {
                            throw new SystemException("", objEBInfo.getBatch() + "批次所表示的物料不存在!");
                        }
                    } else if ("03".equals(objEPrintTaskInfo.getBarType())) {
                        MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEPrintTaskInfo.getMaterialCode());
                        if (objEMaterialInfo == null) {
                            throw new SystemException("", "物料不存在");
                        }
                        objEMaVerInfo = maVerDAO.SelectMaverInfo(objEMaterialInfo.getVerGd());
                        if (objEMaVerInfo == null) {
                            throw new SystemException("", "物料不存在!");
                        }
                        objEBInfo = new BInfo();
                        objEBInfo.setBatch(objEMaVerInfo.getMaBarCode());
                    }

                    objEPrintTInfo = printTDAO.SelectByGuid(objEPrintTaskInfo.getPrintTGd());

                    if (objEPrintTInfo == null) {
                        throw new SystemException("", "当前{" + objEPrintTaskInfo.getpTFileName() + "}不存在，可能已被删除");
                    }

                    map = new HashMap<String, Object>(4);
                    map.put("BInfo", objEBInfo);
                    map.put("MaVerInfo", objEMaVerInfo);

                    String data = PrintData(map, objEPrintTInfo.getGuid());

                    //新增重打记录信息
                    objERePrintLogInfo.setGuid(CommonUtils.getRandomNumber());
                    objERePrintLogInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                    objERePrintLogInfo.setRePTCode(objEPrintTaskInfo.getpTCode());
                    objERePrintLogInfo.setBarCode(objEPrintTaskInfo.getBarCode());
                    objERePrintLogInfo.setpTFileName(objEPrintTaskInfo.getpTFileName());
                    objERePrintLogInfo.setPrintData(data);
                    objERePrintLogInfo.setOrderType(objEPrintTaskInfo.getOrderType());
                    rePrintLogDAO.InsertRePrintLog(objERePrintLogInfo);

                    //调打印机
                    //将数据写入到ActiveMQ
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("PTCode", objERePrintLogInfo.getpTCode());
                    jsonObject.put("PrinterName", objERePrintLogInfo.getPrinterName());
                    jsonObject.put("PrintType", "重打");
                    jsonObject.put("PTFileUrl", FastfdsUtils.readProps() + objEPrintTaskInfo.getpTFileName());
                    jsonObject.put("PrintData", data);
                    jsonObject.put("PrintCount", argBD00.getPrintCount());
                    jsonObject.put("PrintCopy", argBD00.getPrintCopy());
                    //ActiveMQQueue activeMQQueue = new ActiveMQQueue("PrintQueue" + objEPrinterInfo.getPrinterSer());
                    //ActiveMQUtil.sendMessage(jmsTemplate, activeMQQueue, jsonObject.toString());
                    jmsMessagingTemplate.convertAndSend("PrintQueue" + objEPrinterInfo.getPrinterSer(), jsonObject.toString());
                }
            }
        }
    }

    /**
     * 原材料打印
     * @param argBD01
     */
    @Override
    public void AddRePr(SaveRePrInfoReqBD01 argBD01) {
        if (argBD01.getRePrInfo() != null) {
            //当前用户
            String userName = CommonUtils.readUser().getRealName();
            //当前时间
            Date date = new Date();
            //批次
            BInfo objEBInfo = null;
            MaVerInfo objEMaVerInfo = null;
            boolean flag = true;
            Map<String, Object> map = null;
            BAttachInfo objEBAttachInfo = null;
            WmsMaterialsBInfo wmsMaterialsBInfo = null;

            //打印机
            PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(argBD01.getPrintRd());
            if (objEPrinterInfo == null) {
                throw new SystemException("", "打印机不存在!");
            }

            //重打记录信息
            RePrintLogInfo objERePrintLogInfo = new RePrintLogInfo();
            objERePrintLogInfo.setPrintCount(argBD01.getPrintCount());
            objERePrintLogInfo.setPrintCopy(argBD01.getPrintCopy());
            objERePrintLogInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            objERePrintLogInfo.setBarType("02");
            objERePrintLogInfo.setCreator(userName);
            objERePrintLogInfo.setCreateTime(date);

            //打印模板
            PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD01.getPrintTRd());
            if(objEPrintTInfo == null){
                throw new SystemException("", "模板信息不存在");
            }

            for (SaveRePrInfoReqBD01RePrInfo reprinfo : argBD01.getRePrInfo()) {
                map = new HashMap<String, Object>(4);
                //查询批次信息
                objEBInfo = bdao.selectBatchInfoByBatch(reprinfo.getReCode());

                if (objEBInfo == null) {
                    wmsMaterialsBInfo = wmsMaterialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>().eq("batch", reprinfo.getReCode()));
                    if(wmsMaterialsBInfo == null) {
                        throw new SystemException("", reprinfo.getReCode() + "批次不存在!");
                    }
                    map.put("WmsMaterialsBInfo", wmsMaterialsBInfo);
                    objEMaVerInfo = maVerDAO.SelectMaverInfo(wmsMaterialsBInfo.getMaVerGd());
                    if (objEMaVerInfo == null) {
                        throw new SystemException("", wmsMaterialsBInfo.getBatch() + "批次所表示的物料不存在!");
                    }
                }else{
                    map.put("BInfo", objEBInfo);
                    if (objEBInfo.getCanNum() <= 0) {
                        throw new SystemException("", reprinfo.getReCode() + "批次数量小于零!");
                    }
                    objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
                    if (objEMaVerInfo == null) {
                        throw new SystemException("", objEBInfo.getBatch() + "批次所表示的物料不存在!");
                    }

                    objEBAttachInfo = bAttachDAO.SelectByBatch(objEBInfo.getBatch());
                    if (objEBAttachInfo != null) {
                        if (null != objEBAttachInfo.getF4() && !"".equals(objEBAttachInfo.getF4())) {
                            objEMaVerInfo.setOrderNum(objEBAttachInfo.getF4());
                        }
                    } else {
                        if (null == objEMaVerInfo.getOrderNum()) {
                            objEMaVerInfo.setOrderNum("");
                        }
                    }
                }

                map.put("MaVerInfo", objEMaVerInfo);

                String data = PrintData(map, objEPrintTInfo.getGuid());

                //查询打印任务信息
                PrintTaskInfo objEPrintTaskInfo = printTaskDAO.SelectByBarCodeType(reprinfo.getReCode(), objEPrintTInfo.getGuid(), "02");
                if (objEPrintTaskInfo == null) {
                    objEPrintTaskInfo = new PrintTaskInfo();
                    objEPrintTaskInfo.setGuid(CommonUtils.getRandomNumber());
                    objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                    objEPrintTaskInfo.setReCode(reprinfo.getReCode());
                    objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEPrintTaskInfo.setBarCode(reprinfo.getReCode());
                    objEPrintTaskInfo.setOrderType("02");
                    objEPrintTaskInfo.setBarType("02");
                    objEPrintTaskInfo.setPrintData(data);
                    objEPrintTaskInfo.setPrintCount(argBD01.getPrintCount());
                    objEPrintTaskInfo.setPrintCopy(argBD01.getPrintCopy());
                    objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
                    objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                    objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                    objEPrintTaskInfo.setCreator(userName);
                    objEPrintTaskInfo.setCreateTime(date);
                    printTaskDAO.InsertPrintTaskInfo(objEPrintTaskInfo);
                }

                //新增重打记录信息
                objERePrintLogInfo.setGuid(CommonUtils.getRandomNumber());
                objERePrintLogInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                objERePrintLogInfo.setRePTCode(objEPrintTaskInfo.getpTCode());
                objERePrintLogInfo.setBarCode(objEPrintTaskInfo.getBarCode());
                objERePrintLogInfo.setpTFileName(objEPrintTInfo.getFileName());
                objERePrintLogInfo.setPrintData(data);

                objERePrintLogInfo.setOrderType(objEPrintTaskInfo.getOrderType());
                rePrintLogDAO.InsertRePrintLog(objERePrintLogInfo);

                //调打印机
                //将数据写入到ActiveMQ
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("PTCode", objERePrintLogInfo.getpTCode());
                jsonObject.put("PrinterName", objERePrintLogInfo.getPrinterName());
                jsonObject.put("PrintType", "重打");
                jsonObject.put("PTFileUrl", FastfdsUtils.readProps() + objERePrintLogInfo.getpTFileName());
                jsonObject.put("PrintData", data);
                jsonObject.put("PrintCount", argBD01.getPrintCount());
                jsonObject.put("PrintCopy", argBD01.getPrintCopy());
                //ActiveMQQueue activeMQQueue = new ActiveMQQueue("PrintQueue" + objEPrinterInfo.getPrinterSer());
                //ActiveMQUtil.sendMessage(jmsTemplate, activeMQQueue, jsonObject.toString());
                jmsMessagingTemplate.convertAndSend("PrintQueue" + objEPrinterInfo.getPrinterSer(), jsonObject.toString());
            }
        }
    }

    /**
     * 条码打印
     * @param argBD02
     */
    @Override
    public void AddBarCode(SaveRePrInfoReqBD02 argBD02) {
        if (argBD02.getRePrInfo() != null) {
            //当前用户
            String userName = CommonUtils.readUser().getRealName();
            //当前时间
            Date date = new Date();

            //批次
            BInfo objEBInfo = null;
            MaVerInfo objEMaVerInfo = null;
            Map<String, Object> map = null;
            BAttachInfo objEBAttachInfo = null;
            WmsMaterialsBInfo wmsMaterialsBInfo = null;

            //打印机
            PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(argBD02.getPrintRd());
            if (objEPrinterInfo == null) {
                throw new SystemException("", "打印机不存在!");
            }

            //重打记录信息
            RePrintLogInfo objERePrintLogInfo = new RePrintLogInfo();
            objERePrintLogInfo.setPrintCount(argBD02.getPrintCount());
            objERePrintLogInfo.setPrintCopy(argBD02.getPrintCopy());
            objERePrintLogInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            objERePrintLogInfo.setBarType("02");
            objERePrintLogInfo.setCreator(userName);
            objERePrintLogInfo.setCreateTime(date);

            //打印模板
            PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD02.getPrintTRd());
            if(objEPrintTInfo == null){
                throw new SystemException("", "模板信息不存在");
            }

            for (SaveRePrInfoReqBD02RePrInfo reprinfo : argBD02.getRePrInfo()) {
                map = new HashMap<String, Object>(4);
                //查询批次信息
                objEBInfo = bdao.selectBatchInfoByBatch(reprinfo.getBarCode());

                if (objEBInfo == null) {
                    wmsMaterialsBInfo = wmsMaterialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>().eq("batch", reprinfo.getBarCode()));
                    if(wmsMaterialsBInfo == null) {
                        throw new SystemException("", reprinfo.getBarCode() + "批次不存在!");
                    }
                    map.put("WmsMaterialsBInfo", wmsMaterialsBInfo);
                    objEMaVerInfo = maVerDAO.SelectMaverInfo(wmsMaterialsBInfo.getMaVerGd());
                    if (objEMaVerInfo == null) {
                        throw new SystemException("", wmsMaterialsBInfo.getBatch() + "批次所表示的物料不存在!");
                    }
                }else{
                    map.put("BInfo", objEBInfo);
                    if (objEBInfo.getCanNum() <= 0) {
                        throw new SystemException("", reprinfo.getBarCode() + "批次数量小于零!");
                    }
                    objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
                    if (objEMaVerInfo == null) {
                        throw new SystemException("", objEBInfo.getBatch() + "批次所表示的物料不存在!");
                    }

                    objEBAttachInfo = bAttachDAO.SelectByBatch(objEBInfo.getBatch());
                    if (objEBAttachInfo != null) {
                        if (null != objEBAttachInfo.getF4() && !"".equals(objEBAttachInfo.getF4())) {
                            objEMaVerInfo.setOrderNum(objEBAttachInfo.getF4());
                        }
                    } else {
                        if (null == objEMaVerInfo.getOrderNum()) {
                            objEMaVerInfo.setOrderNum("");
                        }
                    }
                }

                map.put("MaVerInfo", objEMaVerInfo);

                String data = PrintData(map, objEPrintTInfo.getGuid());

                //查询打印任务信息
                PrintTaskInfo objEPrintTaskInfo = printTaskDAO.SelectByBarCodeType(reprinfo.getBarCode(), objEPrintTInfo.getGuid(), "02");
                if (objEPrintTaskInfo == null) {
                    objEPrintTaskInfo = new PrintTaskInfo();
                    objEPrintTaskInfo.setGuid(CommonUtils.getRandomNumber());
                    objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                    objEPrintTaskInfo.setReCode(reprinfo.getBarCode());
                    objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEPrintTaskInfo.setBarCode(reprinfo.getBarCode());
                    objEPrintTaskInfo.setOrderType("02");
                    objEPrintTaskInfo.setBarType("02");
                    objEPrintTaskInfo.setPrintData(data);
                    objEPrintTaskInfo.setPrintCount(argBD02.getPrintCount());
                    objEPrintTaskInfo.setPrintCopy(argBD02.getPrintCopy());
                    objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
                    objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                    objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                    objEPrintTaskInfo.setCreator(userName);
                    objEPrintTaskInfo.setCreateTime(date);
                    printTaskDAO.InsertPrintTaskInfo(objEPrintTaskInfo);
                }

                //新增重打记录信息
                objERePrintLogInfo.setGuid(CommonUtils.getRandomNumber());
                objERePrintLogInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                objERePrintLogInfo.setRePTCode(objEPrintTaskInfo.getpTCode());
                objERePrintLogInfo.setBarCode(objEPrintTaskInfo.getBarCode());
                objERePrintLogInfo.setpTFileName(objEPrintTInfo.getFileName());
                objERePrintLogInfo.setPrintData(data);

                objERePrintLogInfo.setOrderType(objEPrintTaskInfo.getOrderType());
                rePrintLogDAO.InsertRePrintLog(objERePrintLogInfo);

                //调打印机
                //将数据写入到ActiveMQ
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("PTCode", objERePrintLogInfo.getpTCode());
                jsonObject.put("PrinterName", objERePrintLogInfo.getPrinterName());
                jsonObject.put("PrintType", "重打");
                jsonObject.put("PTFileUrl", FastfdsUtils.readProps() + objERePrintLogInfo.getpTFileName());
                jsonObject.put("PrintData", data);
                jsonObject.put("PrintCount", argBD02.getPrintCount());
                jsonObject.put("PrintCopy", argBD02.getPrintCopy());
                //ActiveMQQueue activeMQQueue = new ActiveMQQueue("PrintQueue" + objEPrinterInfo.getPrinterSer());
                //ActiveMQUtil.sendMessage(jmsTemplate, activeMQQueue, jsonObject.toString());
                jmsMessagingTemplate.convertAndSend("PrintQueue" + objEPrinterInfo.getPrinterSer(), jsonObject.toString());
            }
        }
    }

    /**
     * 打印任务信息
     * @param argPrintTaskInfo
     * @param argPrinterSer
     * @param argIsPrint
     */
    @Override
    public void PrintTQueue(PrintTaskInfo argPrintTaskInfo, String argPrinterSer, String argIsPrint) {
        argPrintTaskInfo.setGuid(CommonUtils.getRandomNumber());

        if (printTaskDAO.SelectByBarCodeType(argPrintTaskInfo.getBarCode(), argPrintTaskInfo.getPrintTGd(), argPrintTaskInfo.getBarType()) == null) {
            printTaskDAO.InsertPrintTaskInfo(argPrintTaskInfo);
        }

        if ("00".equals(argIsPrint)) {
            //将数据写入到ActiveMQ
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PTCode", argPrintTaskInfo.getpTCode());
            jsonObject.put("PrinterName", argPrintTaskInfo.getPrinterName());
            jsonObject.put("PrintType", "正常打印");
            jsonObject.put("PTFileUrl", CommonUtils.getUrl() + argPrintTaskInfo.getpTFileName());
            jsonObject.put("PrintData", argPrintTaskInfo.getPrintData());
            jsonObject.put("PrintCount", argPrintTaskInfo.getPrintCount());
            jsonObject.put("PrintCopy", argPrintTaskInfo.getPrintCopy());

            //ActiveMQQueue activeMQQueue = new ActiveMQQueue("PrintQueue" + argPrinterSer);
            //ActiveMQUtil.sendMessage(jmsTemplate, activeMQQueue, jsonObject.toString());
            jmsMessagingTemplate.convertAndSend("PrintQueue" + argPrinterSer, jsonObject.toString());
        }
    }

    /**
     * 获取打印参数数据
     * @param map
     * @param printTGd
     * @return
     */
    @Override
    public String PrintData(Map<String, Object> map, String printTGd) {
        if (map.containsKey("MaVerInfo")) {
            MaVerInfo maVerInfo = (MaVerInfo) map.get("MaVerInfo");
            if ("01".equals(maVerInfo.getIsBatch())) {
                if (!map.containsKey("BInfo")) {
                    BInfo bInfo = new BInfo();
                    bInfo.setBatch(maVerInfo.getMaBarCode());
                    map.put("BInfo", bInfo);
                }
            }
        }
        StringBuilder strData = new StringBuilder();

        //查询模板信息
        PrintTInfo printTInfo = printTDAO.SelectByGuid(printTGd);
        Map<String, String> mapList = null;
        if (printTInfo != null) {
            if ("00".equals(printTInfo.getIsScript())) {
                List<PrintScriptInInfo> printScriptInInfos = printScriptInDAO.selectByPrintTGd(printTGd);
                List<String> inList = new ArrayList<>();
                for (PrintScriptInInfo in : printScriptInInfos) {
                    if ("00".equals(in.getFieldSource())) {
                        String[] strings = in.getFieldCode().split("_");
                        inList.add(CommonUtils.getProperty(map.get(strings[0]), strings[1]));
                    } else {
                        inList.add(in.getVal());
                    }
                }
                List<PrintScriptOutInfo> printScriptOutInfos = printScriptOutDAO.selectByPrintTGd(printTGd);
                List<String> outList = new ArrayList<>();
                for (PrintScriptOutInfo out : printScriptOutInfos) {
                    outList.add(out.getFieldCode());
                }

                mapList = bdao.SelectByGear(printTInfo.getScriptName(), inList, outList);
            }
        }

        if (mapList == null) {
            mapList = new HashMap<>();
        }

        //打印参数
        List<PrintTBFInfo> objEPrintTBFInfos = printTBFDAO.SelectAllPrintTBFInfo(printTGd);
        for (PrintTBFInfo printTBFInfo : objEPrintTBFInfos) {
            if ("00".equals(printTBFInfo.getFSouce())) {
                String[] strings = printTBFInfo.getFieldCode().split("_");

                strData.append(printTBFInfo.getFieldName() + "="
                        + CommonUtils.getProperty(map.get(strings[0]), strings[1]) + "|");
            } else {
                if (mapList.containsKey(printTBFInfo.getFieldCode())) {
                    strData.append(printTBFInfo.getFieldName() + "="
                            + mapList.get(printTBFInfo.getFieldCode()) + "|");
                }
            }
        }

        int strDataLength = strData.length();
        if (strDataLength > 0) {
            strData.delete(strDataLength - 1, strDataLength);
        }

        return strData.toString();
    }
}
