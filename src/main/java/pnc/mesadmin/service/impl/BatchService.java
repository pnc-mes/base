package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoRes;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoResB;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoResD;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoResD1;
import pnc.mesadmin.dto.GetBatchInfo.*;
import pnc.mesadmin.dto.GetHoldBatchDTO.GetHoldBatchReq;
import pnc.mesadmin.dto.GetHoldBatchDTO.GetHoldBatchResD;
import pnc.mesadmin.dto.GetSUBInfo.*;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD00;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD00BatchInfo;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD02;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoRes;
import pnc.mesadmin.dto.SaveBatchInfo.*;
import pnc.mesadmin.dto.SaveIOSInfo.SaveIOSInfoReqBD00;
import pnc.mesadmin.dto.SaveSUBInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.*;
import pnc.mesadmin.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次拆分、合并管理控制器
 * 创建人：潘俊峰
 * 创建时间：2017-07-10
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class BatchService implements BatchIService {

    @Resource
    private SplitBatchDAO splitBatchDAO;

    @Resource
    private HisPrintLogIService hisPrintLogIService;

    @Resource
    BDAO objBatchDAO;   //批次信息Dao

    @Resource
    MaVerDAO maVerDAO;   //物料版本DAO

    @Resource
    UnitDAO unitDAO;       //物料单位DAO

    @Resource
    SpecVerDAO specVerDAO;     //工序版本DAO

    @Resource
    PrintTDAO printTDAO;      //打印模板信息DAO

    @Resource
    PrinterDAO printerDAO;     //打印机信息DAO

    @Resource
    WFVerDAO wfVerDAO;     //工艺版本信息DAO

    @Resource
    WODAO woDAO;       //工单DAO

    @Resource
    SerialRuleDAO serialRuleDAO;       //序号管理DAO

    @Resource
    PdFamilyDAO objEPdFamilyDAO;

    @Resource
    private SNIService sniService;

    @Resource
    private InstockDtlDAO instockDtlDAO;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private RkTkDAO rkTkDAO;

    @Resource
    private PurChDAO purChDAO;

    @Resource
    private InCDAO inCDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private UserRoleDAO userRoleDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private MsgCDAO msgCDAO;

    @Resource
    private HoldDAO holdDAO;

    @Resource
    private UnHoldDAO unHoldDAO;

    @Resource
    private RejectDAO rejectDAO;

    @Resource
    private OpenDAO openDAO;

    @Resource
    private CloseDAO closeDAO;

    @Resource
    private ScropDAO scropDAO;

    @Resource
    private ChgQtyDAO chgQtyDAO;

    @Resource
    private PurChDtlDAO purChDtlDAO;

    @Resource
    private InCDtlDAO inCDtlDAO;

    @Resource
    private PickBatchDAO pickBatchDAO;

    @Resource
    private OpertDAO opertDAO;

    private SpecOperateIService specOperateIService;

    @Resource
    private BAttachDAO bAttachDAO;

    @Resource
    private PdFamilyDAO pdFamilyDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private BCirDAO bCirDAO;

    @Resource
    private SerialRuleTBFDAO serialRuleTBFDAO;

    @Resource
    private LineDao lineDao;

    @Resource
    private OrderLineDAO orderLineDAO;

    @Resource
    private WmsMaterialsBDAO wmsMaterialsBDAO;

    /**
     * 获取批次列表信息
     */
    @Override
    public GetAllBatchInfoRes QAllBatchInfo(GetAllBatchInfoResD1 argGetAllBatchInfoResD1) {
        GetAllBatchInfoResB objGetAllBatchInfoResB = new GetAllBatchInfoResB();

        List<GetAllBatchInfoResD> objGetAllBatchInfoResDS = new ArrayList<GetAllBatchInfoResD>();

        GetAllBatchInfoRes getAllBatchInfoRes = new GetAllBatchInfoRes();


        List<BInfo> batchInfoList = bdao.SelectBInfoBaoBiao(argGetAllBatchInfoResD1.getMaCode(),argGetAllBatchInfoResD1.getMaName(),argGetAllBatchInfoResD1.getInStockStatus(),argGetAllBatchInfoResD1.getCreateDate(),argGetAllBatchInfoResD1.getCreateDate1());

        if (null != batchInfoList && batchInfoList.size() > 0) {
            for (BInfo batchInfo : batchInfoList) {
                GetAllBatchInfoResD getAllBatchInfoResD = new GetAllBatchInfoResD();

                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(batchInfo.getMaVerGd()); //获取物料版本信息

                UnitInfo unitInfo = unitDAO.SelectByGuid(batchInfo.getUnitGd()); //获取物料单位信息

                if (maVerInfo != null) {
                    getAllBatchInfoResD.setMaVerRd(maVerInfo.getRuid());
                    getAllBatchInfoResD.setMaCode(maVerInfo.getMaterialCode());
                    getAllBatchInfoResD.setMaName(maVerInfo.getMaterialName());
                    MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                    if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                        getAllBatchInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                    }else{
                        getAllBatchInfoResD.setMaDes("");
                    }
                } else {
                    getAllBatchInfoResD.setMaCode("");
                    getAllBatchInfoResD.setMaName("");
                }

                getAllBatchInfoResD.setNum(batchInfo.getCanNum());
                getAllBatchInfoResD.setBatch(batchInfo.getBatch());
                if (unitInfo != null) {
                    getAllBatchInfoResD.setUnitName(unitInfo.getUnitName());
                } else {
                    getAllBatchInfoResD.setUnitName("");
                }

                getAllBatchInfoResD.setCreateDate(DateUtil.format(batchInfo.getCreateTime()));
                objGetAllBatchInfoResDS.add(getAllBatchInfoResD);
            }
        }


        objGetAllBatchInfoResB.setData(objGetAllBatchInfoResDS);

        getAllBatchInfoRes.setStatus("00");
        getAllBatchInfoRes.setBody(objGetAllBatchInfoResB);


        return getAllBatchInfoRes;
    }

    //导出批次列表
    @Override
    public ByteArrayOutputStream ExportBatchInfo(GetAllBatchInfoResD1 argGetAllBatchInfoResD1) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetAllBatchInfoResD> objGetAllBatchInfoResDS = new ArrayList<GetAllBatchInfoResD>();

            GetAllBatchInfoRes getAllBatchInfoRes = new GetAllBatchInfoRes();
            //PageInfo pageInfo  = argGetAllReq.getPageInfo();

            List<BInfo> batchInfoList = bdao.SelectBInfoBaoBiao(argGetAllBatchInfoResD1.getMaCode(),argGetAllBatchInfoResD1.getMaName(),argGetAllBatchInfoResD1.getInStockStatus(),argGetAllBatchInfoResD1.getCreateDate(),argGetAllBatchInfoResD1.getCreateDate1());
            if (null != batchInfoList && batchInfoList.size() > 0) {
                for (BInfo batchInfo : batchInfoList) {
                    GetAllBatchInfoResD getAllBatchInfoResD = new GetAllBatchInfoResD();

                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(batchInfo.getMaVerGd()); //获取物料版本信息

                    UnitInfo unitInfo = unitDAO.SelectByGuid(batchInfo.getUnitGd()); //获取物料单位信息


                    if (maVerInfo != null) {
                        getAllBatchInfoResD.setMaCode(maVerInfo.getMaterialCode());
                        getAllBatchInfoResD.setMaName(maVerInfo.getMaterialName());
                    } else {
                        getAllBatchInfoResD.setMaCode("");
                        getAllBatchInfoResD.setMaName("");
                    }
                    getAllBatchInfoResD.setNum(batchInfo.getCanNum());
                    getAllBatchInfoResD.setBatch(batchInfo.getBatch());
                    if (unitInfo != null) {
                        getAllBatchInfoResD.setUnitName(unitInfo.getUnitName());
                    } else {
                        getAllBatchInfoResD.setUnitName("");
                    }

                    getAllBatchInfoResD.setCreateDate(DateUtil.format(batchInfo.getCreateTime()));
                    objGetAllBatchInfoResDS.add(getAllBatchInfoResD);
                }

            }


            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"物料代码", "物料名称", "批次号", "数量"}};

            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 1; i++) {
                row1 = sheet1.createRow(i);//创建表格行

                for (int j = 0; j < datas1[i].length; j++) {

                    cell1 = row1.createCell(j);//根据表格行创建单元格

                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }

            /**********************************批次查询数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < objGetAllBatchInfoResDS.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(objGetAllBatchInfoResDS.get(i).getMaCode());
                    } else if (j == 1) {
                        cell3.setCellValue(objGetAllBatchInfoResDS.get(i).getMaName());
                    } else if (j == 2) {
                        cell3.setCellValue(objGetAllBatchInfoResDS.get(i).getBatch());
                    } else if (j == 3) {
                        cell3.setCellValue(objGetAllBatchInfoResDS.get(i).getNum());
                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
            }
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ExportSWareInfoResB exportSWareInfoResB = new ExportSWareInfoResB();
        exportSWareInfoResB.setMsgCode("");
        exportSWareInfoResB.setMsgDes("成功!");

        ExportSWareInfoRes exportSWareInfoRes = new ExportSWareInfoRes();
        exportSWareInfoRes.setBody(exportSWareInfoResB);

        return null;

    }

    /**
     * 根据批次ID查询指定的批次信息
     * @param request
     * @param argGetAllReq
     * @return
     */
    @Override
    public GetBatchInfoRes QBatchInfo(HttpServletRequest request, GetAllReq argGetAllReq) {

        GetBatchInfoReqBD00 getBatchInfoReqBD00 = null;
        getBatchInfoReqBD00 = CommonUtils.switchClass(GetBatchInfoReqBD00.class,argGetAllReq.getBusData());

        if(getBatchInfoReqBD00== null){
            throw new SystemException("","批次ID为空");
        }

        BInfo bInfo =  objBatchDAO.SelectBatchInfoByruid(Integer.parseInt(getBatchInfoReqBD00.getBRd()));
        if(bInfo==null){
            throw new SystemException("","批次信息为空");
        }

        GetBatchInfoResDSpec getBatchInfoResDSpec = new GetBatchInfoResDSpec();
        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(bInfo.getSpecVerGd());//查询工序版本信息
        if(specVerInfo!=null){
            getBatchInfoResDSpec.setSpecVerRd(specVerInfo.getRuid());
            getBatchInfoResDSpec.setSpecName(specVerInfo.getSpecName());
        }

        GetBatchInfoResDPrintTInfo getBatchInfoResDPrintTInfo = new GetBatchInfoResDPrintTInfo();
        PrintTInfo printTInfo = printTDAO.SelectByGuid(bInfo.getPrintTGd());//查询打印模板信息
        if(printTInfo!=null){
            getBatchInfoResDPrintTInfo.setPrintTRd(printTInfo.getRuid());
            getBatchInfoResDPrintTInfo.setPrintTName(getBatchInfoResDPrintTInfo.getPrintTName());
        }

        GetBatchInfoResDPrintInfo getBatchInfoResDPrintInfo = new GetBatchInfoResDPrintInfo();
        /*PrinterInfo printerInfo = printerDAO.SelectByGuid(bInfo.getPinterGd());//查询打印机信息
        if(printerInfo==null){
            getBatchInfoResDPrintInfo.setPrintRd(printerInfo.getRuid());
            getBatchInfoResDPrintInfo.setPrintName(printerInfo.getPrinterName());
        }*/

        GetBatchInfoResD getBatchInfoResD = new GetBatchInfoResD();

        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());  //查询物料版本信息
        if(maVerInfo!=null){
            getBatchInfoResD.setMaName(maVerInfo.getMaterialName());
        }

        WFVerInfo wfVerInfo = wfVerDAO.SelectByGuid(bInfo.getwFVerGd()); //查询工艺版本信息
        if(wfVerInfo!=null){
            getBatchInfoResD.setWFName(wfVerInfo.getwFName());
        }

        UnitInfo unitInfo = unitDAO.SelectByGuid(bInfo.getUnitGd());//查询单位信息
        if(unitInfo!=null){
            getBatchInfoResD.setUnitName(unitInfo.getUnitName());
        }

        //查询工单信息
        WoInfo woInfo = woDAO.SelectWoInfoByGuid(bInfo.getWoGd());
        if(woInfo!=null){
            getBatchInfoResD.setWoCode(woInfo.getWoCode());
            getBatchInfoResD.setNum(woInfo.getNum());
        }

        getBatchInfoResD.setBRd(bInfo.getRuid());
        getBatchInfoResD.setSpec(getBatchInfoResDSpec);
        getBatchInfoResD.setPrintTInfo(getBatchInfoResDPrintTInfo);
        getBatchInfoResD.setPrintInfo(getBatchInfoResDPrintInfo);
        getBatchInfoResD.setBCount(bInfo.getNum());
        getBatchInfoResD.setJStartDate(DateUtil.format(bInfo.getjStartDate()));
        getBatchInfoResD.setJFinishDate(DateUtil.format(bInfo.getjFinishDate()));
        getBatchInfoResD.setCreator(bInfo.getCreator());
        getBatchInfoResD.setCreateTime(DateUtil.format(bInfo.getCreateTime()));
        getBatchInfoResD.setLastModifyMan(bInfo.getLastModifyMan());
        getBatchInfoResD.setLastModifyTime(DateUtil.format(bInfo.getLastModifyTime()));
        getBatchInfoResD.setRemark(bInfo.getRemark());

        GetBatchInfoResB getBatchInfoResB = new GetBatchInfoResB();
        getBatchInfoResB.setMsgCode("");
        getBatchInfoResB.setMsgDes("成功");
        getBatchInfoResB.setData(getBatchInfoResD);

        GetBatchInfoRes getBatchInfoRes = new GetBatchInfoRes();
        getBatchInfoRes.setStatus("00");
        getBatchInfoRes.setBody(getBatchInfoResB);

        return getBatchInfoRes;
    }

    /**
     * 新增批次信息
     * @param saveBatchInfoReqBD00
     * @return
     */
    @Override
    public SaveBatchInfoRes AddBatch(SaveBatchInfoReqBD00 saveBatchInfoReqBD00){
        SaveBatchInfoResB saveBatchInfoResB00 = new SaveBatchInfoResB();
        SaveBatchInfoRes saveBatchInfoRes = new SaveBatchInfoRes();
        SaveBatchInfoResD saveBatchInfoResD = new SaveBatchInfoResD();
        List<SaveBatchInfoResDBInfo> dbInfos = new ArrayList<>();
        SaveBatchInfoResDBInfo dbInfo = null;
        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //无工单
        if("00".equals(saveBatchInfoReqBD00.getWoSource())){
            //获取物料版本信息
            MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(saveBatchInfoReqBD00.getMaVerRd());
            if(objEMaVerInfo == null){
                throw new SystemException("","物料版本信息为空");
            }

            //获取打印模板信息
            PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(saveBatchInfoReqBD00.getPrintTRd());

            //获取打印机信息
            PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(saveBatchInfoReqBD00.getPrintRd());

            //将可能需要打印的对象写入map
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("MaVerInfo", objEMaVerInfo);

            int batchCount = -1;
            if (saveBatchInfoReqBD00.getIsPrintPack().equals("00")) {
                batchCount = saveBatchInfoReqBD00.getSplitBCount() + 1;
            } else {
                batchCount = saveBatchInfoReqBD00.getSplitBCount();
            }

            if("01".equals(objEMaVerInfo.getIsBatch())){
                throw new SystemException("", "该物料未启用批次，不能使用创批功能");
            }

            WmsMaterialsBInfo objEBInfo = SaveBatchInfoADDSetBInfo2(saveBatchInfoReqBD00);


            objEBInfo.setMaVerGd(objEMaVerInfo.getGuid());

            //判断是否免检
            if ("00".equals(objEMaVerInfo.getIsExem())) {
                objEBInfo.setiQCStatus("01");
            } else {
                objEBInfo.setiQCStatus("00");
            }

            objEBInfo.setCkResult("03");

            List<WmsMaterialsBInfo> objEBInfos = new ArrayList<WmsMaterialsBInfo>();

            for (int i = 0; i < batchCount; i++) {
                WmsMaterialsBInfo objBInfo = SaveBatchInfoADDSetBInfo2(objEBInfo);
                //获取序号管理信息
                SerialRuleInfo serialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaVerInfo.getSerialRuleGd());
                PdFamilyInfo objEPdFamilyInfo = null;
                String batch = "";
                //创建批次号
                if (serialRuleInfo == null) {
                    objEPdFamilyInfo = objEPdFamilyDAO.SelectByGuid(objEMaVerInfo.getPdFamilyGd());
                    if (objEPdFamilyInfo == null) {
                        throw new SystemException("", "序号管理信息为空");
                    }
                    serialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEPdFamilyInfo.getSerialRuleGd());
                    if (serialRuleInfo == null) {
                        throw new SystemException("", "序号管理信息为空");
                    }
                    batch = sniService.GetCreateSR(serialRuleInfo);
                } else {
                    batch = sniService.GetCreateSR(serialRuleInfo);
                }
                objBInfo.setBatch(batch);
                objEBInfos.add(objBInfo);
            }

            for (int i = 0; i < objEBInfos.size(); i++) {
                WmsMaterialsBInfo bInfo = objEBInfos.get(i);
                bInfo.setGuid(CommonUtils.getRandomNumber());
                if (saveBatchInfoReqBD00.getSplitBCount() < batchCount && i == (objEBInfos.size() - 1)) {
                    break;
                }
                /*if ("01".equals(objEMaVerInfo.getIsBatch())) {
                    bInfo.setIsBarBatch("00");
                }*/
                if (saveBatchInfoReqBD00.getSplitBCount() < batchCount) {
                    bInfo.setSupBatch(objEBInfos.get(objEBInfos.size() - 1).getBatch());
                }

                if ("00".equals(objEMaVerInfo.getIsExem())) {
                    bInfo.setiQCStatus("01");
                } else {
                    bInfo.setiQCStatus("00");
                }
                //bInfo.setbType("01");

                //插入批次信息
                try {
                    if(wmsMaterialsBDAO.insert(bInfo) <= 0){
                        throw new SystemException("", "新增批次失败");
                    }
                } catch (Exception e) {
                    throw new SystemException("", "新增批次失败,失败原因:" + e.getMessage());
                }

                //批次事物操作
                AffairInfo objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(bInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("14");
                objEAffairInfo.setCreator(bInfo.getCreator());
                objEAffairInfo.setCreateTime(bInfo.getCreateTime());
                affairDAO.InsertAffairInfo(objEAffairInfo);
            }

            map.put("PrintTInfo", objEPrintTInfo);
            map.put("PrinterInfo", objEPrinterInfo);

            //是否打印
            int print_count = 0;
            if ("00".equals(saveBatchInfoReqBD00.getIsPrint())) {
                if(saveBatchInfoReqBD00.getPrintCopy() <= 0) {
                    print_count = 1;
                }else{
                    print_count = saveBatchInfoReqBD00.getPrintCopy();
                }
            }

            if(objEPrinterInfo != null && objEPrintTInfo != null) {
                //打印
                printBar2(map, objEBInfos, print_count, objEPrinterInfo, objEPrintTInfo, objEMaVerInfo, "04");
            }

            for(WmsMaterialsBInfo b : objEBInfos){
                dbInfo = new SaveBatchInfoResDBInfo();
                dbInfo.setBatch(b.getBatch());
                dbInfos.add(dbInfo);
            }
        }else if("01".equals(saveBatchInfoReqBD00.getWoSource())){
            //有工单
            //获取工单信息
            WoInfo objEWoInfo = woDAO.SelectWoInfoByruid(saveBatchInfoReqBD00.getWoRd());
            if(objEWoInfo==null){
                throw new SystemException("","工单信息为空");
            }
            switch (objEWoInfo.getStatus()){
                case "00":
                    throw new SystemException("", objEWoInfo.getWoCode() + "工单处于创建状态");
                case "02":
                    throw new SystemException("", objEWoInfo.getWoCode() + "工单处于取消状态");
                case "04":
                    throw new SystemException("", objEWoInfo.getWoCode() + "工单处于冻结状态");
                case "05":
                    throw new SystemException("", objEWoInfo.getWoCode() + "工单处于终止状态");
                case "06":
                    throw new SystemException("", objEWoInfo.getWoCode() + "工单处于完成状态");
                case "07":
                    throw new SystemException("", objEWoInfo.getWoCode() + "工单处于关闭状态");
                default:
                    break;
            }

            //获取未创建批次量
            float num = objEWoInfo.getUnCBatNum();
            //分批数
            int SplitBCount = saveBatchInfoReqBD00.getSplitBCount();
            //每批数
            float BCount = saveBatchInfoReqBD00.getBCount();
            //本次创建批次数数量
            float unCBatNum = 0f;

            BInfo objEBInfo = SaveBatchInfoADDSetBInfo(saveBatchInfoReqBD00);
            objEBInfo.setWoGd(objEWoInfo.getGuid());

            //是否自动创批
            boolean isABatch = true;
            if("01".equals(saveBatchInfoReqBD00.getIsABatch())){
                isABatch = false;
                if(saveBatchInfoReqBD00.getBInfo().size() != saveBatchInfoReqBD00.getSplitBCount()){
                    throw new SystemException("", "拆批数量和手动填写的批次数量不一致!");
                }
                Float sum = 0f;
                for(SaveBatchInfoReqBD00BInfo b : saveBatchInfoReqBD00.getBInfo()){
                    sum += b.getNum();
                }
                if(sum > num){
                    throw new SystemException("","分批总量不能大于未创批次数量");
                }
            }else{
                if( num < (SplitBCount*BCount) ) {
                    throw new SystemException("","分批总量不能大于未创批次数量");
                }
            }

            //获取物料版本信息
            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEWoInfo.getMaVerGd());
            if(objEMaVerInfo == null){
                throw new SystemException("","物料版本信息为空");
            }
            objEBInfo.setMaVerGd(objEMaVerInfo.getGuid());

            //判断是否免检
            if("00".equals(objEMaVerInfo.getIsExem())){
                objEBInfo.setiQCStatus("01");
            }else{
                objEBInfo.setiQCStatus("00");
            }

            objEBInfo.setCkResult("03");

            //获取单位信息
            UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
            if(objEUnitInfo == null){
                throw new SystemException("","单位信息为空");
            }
            objEBInfo.setUnitGd(objEUnitInfo.getGuid());

            //获取工艺流程信息
            WFVerInfo objEWFVerInfo = wfVerDAO.SelectByGuid(objEWoInfo.getwFVerGd());
            if(objEWFVerInfo == null){
                throw new SystemException("","工艺流程信息为空");
            }
            objEBInfo.setwFVerGd(objEWFVerInfo.getGuid());

            //获取工序信息为空
            SpecVerInfo objESpecVerInfo = specVerDAO.SelectSpecVerInfoByruid(saveBatchInfoReqBD00.getSpecVerRd());
            if(objESpecVerInfo == null){
                //查询开始工序
                objESpecVerInfo = specVerDAO.SelectSpecVerInfoByguid(objEWFVerInfo.getsSpecVerGd());
                if(objESpecVerInfo == null) {
                    throw new SystemException("","开始工序信息为空");
                }
            }
            objEBInfo.setSpecVerGd(objESpecVerInfo.getGuid());

            //获取作业信息
            OpertInfo objEOpertInfo = opertDAO.GetOpertInfoByGuid(objESpecVerInfo.getOpertGd());

            //获取打印模板信息
            PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(saveBatchInfoReqBD00.getPrintTRd());
            if(objEPrintTInfo == null){
                objEPrintTInfo = printTDAO.SelectByGuid(objEMaVerInfo.getPrintTGd());
                if(objEPrintTInfo == null) {
                    PdFamilyInfo pdFamilyInfo = pdFamilyDAO.SelectByGuid(objEMaVerInfo.getPdFamilyGd());
                    if(pdFamilyInfo == null){
                        throw new SystemException("", "没法找到关联的打印模板信息,可以到物料家族或者物料管理中进行设置");
                    }
                    objEPrintTInfo = printTDAO.SelectByGuid(pdFamilyInfo.getPrintTGd());
                    if(objEPrintTInfo == null){
                        throw new SystemException("", "没法找到关联的打印模板信息,可以到物料家族或者物料管理中进行设置");
                    }
                }
            }

            /*if(!FastfdsUtils.isFile(objEPrintTInfo.getFileName())){
                throw new SystemException("", "模板文件不存在,请重新上传");
            }*/
            objEBInfo.setPrintTGd(objEPrintTInfo.getGuid());

            //获取打印机信息
            PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(saveBatchInfoReqBD00.getPrintRd());

            //将可能需要打印的对象写入map
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("MaVerInfo", objEMaVerInfo);
            map.put("UnitInfo", objEUnitInfo);
            map.put("WFVerInfo", objEWFVerInfo);
            map.put("SpecVerInfo", objESpecVerInfo);
            map.put("PrintTInfo", objEPrintTInfo);
            map.put("PrinterInfo", objEPrinterInfo);
            map.put("WoInfo", objEWoInfo);

            List<BInfo> objEBInfos = new ArrayList<BInfo>();
            int batchCount =-1;
            if(saveBatchInfoReqBD00.getIsPrintPack().equals("00")){
                batchCount = saveBatchInfoReqBD00.getSplitBCount()+1;
            }else {
                batchCount = saveBatchInfoReqBD00.getSplitBCount();
            }
            //获取序号管理信息
            SerialRuleInfo serialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaVerInfo.getSerialRuleGd());
            for(int i=0;i<batchCount;i++) {
                BInfo objBInfo = SaveBatchInfoADDSetBInfo(objEBInfo);
                PdFamilyInfo objEPdFamilyInfo = null;
                String batch = "";
                //创建批次号
                if(isABatch) {
                    if (serialRuleInfo == null) {
                        objEPdFamilyInfo = objEPdFamilyDAO.SelectByGuid(objEMaVerInfo.getPdFamilyGd());
                        if (objEPdFamilyInfo == null) {
                            throw new SystemException("", "序号管理信息为空!");
                        }
                        serialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEPdFamilyInfo.getSerialRuleGd());
                        if (serialRuleInfo == null) {
                            throw new SystemException("", "序号管理信息为空!");
                        }
                    }
                    if("00".equals(serialRuleInfo.getIsScript())){
                        //读取存储过程
                        Lineinfo lineinfo = lineDao.SelectLineInfoByguid(saveBatchInfoReqBD00.getLineGd());
                        if (lineinfo == null) {
                            List<OrderLineinfo> orderLineinfo = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
                            if(orderLineinfo.size() <= 0) {
                                throw new SystemException("", "线体信息不存在");
                            }
                            lineinfo = lineDao.SelectLineInfoByguid(orderLineinfo.get(0).getLineGd());
                            if(lineinfo == null){
                                throw new SystemException("", "线体信息不存在");
                            }
                        }

                        List<SerialRuleTBFInfo> serialRuleTBFInfoList = serialRuleTBFDAO.selectS(serialRuleInfo.getGuid());
                        if(serialRuleTBFInfoList.size() <= 0){
                            throw new SystemException("", "序号规则设置有误");
                        }
                        String[] strings = serialRuleTBFInfoList.get(0).getFieldCode().split("_");

                        String s = CommonUtils.getProperty(map.get(strings[0]), strings[1]);
                        Map<String, String> mapList = bdao.SelectByList(serialRuleInfo.getScriptName(), s, lineinfo.getLineCode(), serialRuleInfo.getGuid(), new Date());
                        if(mapList == null){
                            throw new SystemException("", "批次生成失败，请重试");
                        }
                        if(mapList.containsKey("errorCode") && !"1".equals(mapList.get("errorCode"))){
                            throw new SystemException("", mapList.get("errorCode"));
                        }
                        if(!mapList.containsKey("prCode")){
                            throw new SystemException("", "批次生成失败，请重试");
                        }
                        batch = mapList.get("prCode");
                        if("".equals(batch)){
                            throw new SystemException("", "批次不能为空!");
                        }
                        if(bdao.selectBatchInfoByBatch(batch) != null){
                            throw new SystemException("", batch + "批次已经存在!");
                        }
                    }else {
                        batch = sniService.GetCreateSR(serialRuleInfo);
                    }
                }else{
                    batch = saveBatchInfoReqBD00.getBInfo().get(i).getBatch();
                    if("".equals(batch)){
                        throw new SystemException("", "批次不能为空!");
                    }
                    if(bdao.selectBatchInfoByBatch(batch) != null){
                        throw new SystemException("", batch + "批次已经存在!");
                    }
                    objBInfo.setNum(saveBatchInfoReqBD00.getBInfo().get(i).getNum());
                    objBInfo.setCanNum(objBInfo.getNum());
                    if(objBInfo.getNum() <= 0){
                        throw new SystemException("", "批次数量要大于零!");
                    }
                }
                objBInfo.setBatch(batch);
                objBInfo.setLineGd(saveBatchInfoReqBD00.getLineGd());

                unCBatNum += objBInfo.getNum();
                objEBInfos.add(objBInfo);
            }

            for(int i=0;i<objEBInfos.size();i++){
                BInfo bInfo = objEBInfos.get(i);
                bInfo.setGuid(CommonUtils.getRandomNumber());
                if(saveBatchInfoReqBD00.getSplitBCount()<batchCount && i == (objEBInfos.size()-1) ){
                    break ;
                }

                if(saveBatchInfoReqBD00.getSplitBCount() < batchCount){
                    bInfo.setSupBatch(objEBInfos.get(objEBInfos.size()-1).getBatch());
                }

                bInfo.setbType("00");

                //插入批次信息
                try {
                    objBatchDAO.InsertBatch(bInfo);
                } catch (Exception e) {
                    throw new SystemException("", "新增批次失败,失败原因:" + e.getMessage());
                }

                //批次事物操作
                AffairInfo objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(bInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("14");
                objEAffairInfo.setCreator(bInfo.getCreator());
                objEAffairInfo.setCreateTime(bInfo.getCreateTime());
                affairDAO.InsertAffairInfo(objEAffairInfo);

                //批次附加表
                BAttachInfo objEBAttachInfo = new BAttachInfo();
                objEBAttachInfo.setGuid(CommonUtils.getRandomNumber());
                objEBAttachInfo.setBatch(bInfo.getBatch());
                objEBAttachInfo.setCreator(bInfo.getCreator());
                objEBAttachInfo.setCreateTime(bInfo.getCreateTime());
                bAttachDAO.InsertBAttachInfo(objEBAttachInfo);

                boolean b = ("01".equals(objEWoInfo.getStatus()) || "03".equals(objEWoInfo.getStatus())) && objEWoInfo.getwIPNum() == 0;

                if(b){
                    if(objEOpertInfo != null && !"00".equals(objEOpertInfo.getSpecoption())){
                        //入站
                        SaveIOSInfoReqBD00 reqBD00 = new SaveIOSInfoReqBD00();
                        reqBD00.setBatch(bInfo.getBatch());
                        //specOpertIService.AddInput(reqBD00);

                        List<BInfo> bInfos = bdao.SelectByWoGd(objEWoInfo.getGuid());
                        if(bInfos != null && bInfos.size() == 1){
                            objEWoInfo.setStatus("03");
                            objEWoInfo.setwIPNum(bInfo.getNum());
                        }
                    }
                }
            }

            //更新工单信息
            objEWoInfo.setUnCBatNum(objEWoInfo.getUnCBatNum() - unCBatNum);
            int updateWoInfoResCode = woDAO.UpdateWoInfo(objEWoInfo);
            if (updateWoInfoResCode <= 0) {
                throw new SystemException("","更新工单信息失败");
            }

            map.put("MaVerInfo", objEMaVerInfo);
            map.put("UnitInfo", objEUnitInfo);
            map.put("WFVerInfo", objEWFVerInfo);
            map.put("SpecVerInfo", objESpecVerInfo);
            map.put("PrintTInfo", objEPrintTInfo);
            map.put("PrinterInfo", objEPrinterInfo);
            map.put("WoInfo", objEWoInfo);

            //是否打印
            int print_count = 0;
            if("00".equals(saveBatchInfoReqBD00.getIsPrint())){
                if(saveBatchInfoReqBD00.getPrintCopy() <= 0) {
                    print_count = 1;
                }else{
                    print_count = saveBatchInfoReqBD00.getPrintCopy();
                }
            }

            for(BInfo b : objEBInfos){
                dbInfo = new SaveBatchInfoResDBInfo();
                dbInfo.setBatch(b.getBatch());
                dbInfos.add(dbInfo);
            }

            //打印
            printBar(map, objEBInfos, print_count, objEPrinterInfo, objEPrintTInfo, objEMaVerInfo, "02", objEWoInfo.getWoCode());
        }

        saveBatchInfoResD.setBInfo(dbInfos);
        saveBatchInfoResB00.setMsgCode("");
        saveBatchInfoResB00.setMsgDes("成功");
        saveBatchInfoResB00.setData(saveBatchInfoResD);
        saveBatchInfoRes.setStatus("00");
        saveBatchInfoRes.setBody(saveBatchInfoResB00);

        return saveBatchInfoRes;
    }

    /**
     * 删除批次信息
     * @param saveBatchInfoReqBD01
     * @return
     */
    @Override
    public SaveBatchInfoRes RmBatch(SaveBatchInfoReqBD01 saveBatchInfoReqBD01){
        BInfo objEBInfo = objBatchDAO.SelectBatchInfoByruid(saveBatchInfoReqBD01.getBRd());
        if(objEBInfo == null){
            throw new SystemException("","批次信息为空");
        }
        if(objEBInfo.getWoSource().equals("00")){    //无工单
            try{
                objBatchDAO.DeleteBatchInfoByRuid(saveBatchInfoReqBD01.getBRd());
            }catch(Exception e){
                throw new SystemException("","刪除批次失敗");
            }
        }

        if(objEBInfo.getWoSource().equals("01")){
            WoInfo objEWoInfo = woDAO.SelectWoInfoByGuid(objEBInfo.getWoGd());
            if(objEWoInfo == null){
                throw new SystemException("","工单信息为空");
            }

            if(objEWoInfo.getStatus().equals("00")){  //是否已经开始执行
                try{
                    objBatchDAO.DeleteBatchInfoByRuid(objEBInfo.getRuid());
                }catch(Exception e){
                    throw new SystemException("","刪除批次失敗");
                }
            }else{
                throw  new SystemException("","当前批次所属工单已开始或已完成");
            }
        }



        SaveBatchInfoRes saveBatchInfoRes01 = new SaveBatchInfoRes();
        SaveBatchInfoResB saveBatchInfoResB = new SaveBatchInfoResB();
        saveBatchInfoResB.setMsgCode("0x0000");
        saveBatchInfoResB.setMsgDes("成功");

        saveBatchInfoRes01.setStatus("00");
        saveBatchInfoRes01.setBody(saveBatchInfoResB);

        return saveBatchInfoRes01;
    }

    /**
     * 编辑批次信息
     * @param saveBatchInfoReqBD02
     * @return
     */
    @Override
    public SaveBatchInfoRes ModBatch(SaveBatchInfoReqBD02 saveBatchInfoReqBD02){
        SaveBatchInfoRes saveBatchInfoRes02 = new SaveBatchInfoRes();
        SaveBatchInfoResB saveBatchInfoResB02 = new SaveBatchInfoResB();
        BInfo bInfo = objBatchDAO.SelectBatchInfoByruid(saveBatchInfoReqBD02.getBRd());
        if(bInfo == null){
            throw new SystemException("","批次信息为空");
        }
        if(!bInfo.getStatus().equals("00")){
            throw new SystemException("","当前批次所属工单已开始执行,无法编辑批次");
        }
        WoInfo objEWoInfo = woDAO.SelectWoInfoByGuid(bInfo.getWoGd());

        float fChangeNum = saveBatchInfoReqBD02.getBCount()-bInfo.getNum();
        if(fChangeNum > objEWoInfo.getUnCBatNum() && "00".equals(bInfo.getWoSource())){
            throw new SystemException("","批次数量不能大于当前工单的未完成量");
        }
        bInfo.setRuid(saveBatchInfoReqBD02.getBRd());
        bInfo.setNum(saveBatchInfoReqBD02.getBCount());
        bInfo.setSpecVerGd(saveBatchInfoReqBD02.getSpecVerRd()+"");
        bInfo.setPrintTGd(saveBatchInfoReqBD02.getPrintTRd()+"");
        bInfo.setjStartDate(DateUtil.format(saveBatchInfoReqBD02.getJStartDate()));
        bInfo.setjFinishDate(DateUtil.format(saveBatchInfoReqBD02.getJFinishDate()));
        bInfo.setInStockStatus("02");
        bInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        bInfo.setLastModifyTime(new Date());
        bInfo.setRemark(saveBatchInfoReqBD02.getRemark());


        objEWoInfo.setUnCBatNum(objEWoInfo.getUnCBatNum()-fChangeNum);
        objEWoInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEWoInfo.setLastModifyTime(new Date());
        //更新工单信息
        if(woDAO.UpdateWoInfo(objEWoInfo)!=1){
            throw new SystemException("","更新工单信息失败");
        }
        //更新批次信息
        if(objBatchDAO.UpdateBatchInfoByRuid(bInfo)!=1){
            throw new SystemException("","更新批次信息失败");
        }

        saveBatchInfoResB02.setMsgCode("");
        saveBatchInfoResB02.setMsgDes("成功");
        saveBatchInfoResB02.setData(null);
        saveBatchInfoRes02.setStatus("01");
        saveBatchInfoRes02.setBody(saveBatchInfoResB02);

        return saveBatchInfoRes02;
    }

    //创建物料批次
    @Override
    public SaveBatchInfoRes AddMVBatch(SaveBatchInfoReqBD03 argBD03) {
        SaveBatchInfoRes objESaveBatchInfoRes = new SaveBatchInfoRes();
        SaveBatchInfoResB objESaveBatchInfoResB = new SaveBatchInfoResB();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        if(null == argBD03.getBInfo()){
            return objESaveBatchInfoRes;
        }

        //打印清单
        List<PrintTaskInfo> objEPrintTaskInfos = new ArrayList<PrintTaskInfo>();
        //打印机实例IP
        Map<String, String> printMap = new HashMap<String, String>();

        //是否打印
        int print_count = 0;
        if("00".equals(argBD03.getIsPrint())){
            if(argBD03.getPrintCopy() <= 0) {
                print_count = 1;
            }else{
                print_count = argBD03.getPrintCopy();
            }
        }

        //TODO 创建入库单
        RkTkInfo objERkTkInfo = new RkTkInfo();
        objERkTkInfo.setGuid(CommonUtils.getRandomNumber());
        objERkTkInfo.setRkType("02");
        objERkTkInfo.setRkCode("RK");

        //查询标识
        String woGd = "";
        String woCode = "";
        if("02".equals(argBD03.getWoSource())){
            //查询采购单
            PurChInfo objEPurChInfo = purChDAO.SelectByRuid(argBD03.getWoRd());
            if(objEPurChInfo == null){
                throw new SystemException("", "采购单不存在");
            }
            woGd = objEPurChInfo.getGuid();
            //关联单号
            objERkTkInfo.setAssCode(objEPurChInfo.getPurChCode());
            //00采购单，01来料通知单
            objERkTkInfo.setAssSource("00");
            objERkTkInfo.setAssCodeGd(objEPurChInfo.getGuid());
            woCode = objEPurChInfo.getPurChCode();
            objERkTkInfo.setRemark(objEPurChInfo.getRemark());
        }else if("03".equals(argBD03.getWoSource())){
            //查询来料通知单
            InCInfo objEInCInfo = inCDAO.SelectByRuid(argBD03.getWoRd());
            if(objEInCInfo == null){
                throw new SystemException("", "来料单不存在");
            }
            woGd = objEInCInfo.getGuid();
            //关联单号
            objERkTkInfo.setAssCode(objEInCInfo.getInCCode());
            //00采购单，01来料通知单
            objERkTkInfo.setAssSource("01");
            objERkTkInfo.setAssCodeGd(objEInCInfo.getGuid());
            woCode = objEInCInfo.getInCCode();
            objERkTkInfo.setRemark(objEInCInfo.getInCCode());
        }

        RkTkInfo rkTkInfo = rkTkDAO.SelectByAssCode(woCode);
        String rkCode = "";
        //判断入库单是否存在
        if(rkTkInfo == null){
            objERkTkInfo.setExStatus("00");
            objERkTkInfo.setCreator(userName);
            objERkTkInfo.setCreateTime(date);
            rkTkDAO.InsertRKTkInfo(objERkTkInfo);
            //更新入库单单号
            rkCode = updateRK(objERkTkInfo.getGuid());
        }else{
            rkCode = rkTkInfo.getRkCode();
        }

        //通知的数据
        List<Map<String, String>> listP = new ArrayList<Map<String, String>>();
        Map<String, String> mapP = null;
        Map<String, Object> map = null;

        //判断是否通知执行人员 true通知，false不通知
        boolean f = false;

        MsgCInfo objEMsgCInfo = msgCDAO.SelectmsgName("C1");

        if(null != objEMsgCInfo && "C1_00".equals(objEMsgCInfo.getMsgValue())){
            f = true;
        }

        if(argBD03.getBInfo() == null){
            throw new SystemException("", "批次数据有问题，请重新操作");
        }

        //所有批次
        //List<BInfo> bInfoList = new ArrayList<BInfo>();
        AffairInfo objEAffairInfo = null;
        MaVerInfo objEMaVerInfo = null;
        UnitInfo objEUnitInfo = null;
        SerialRuleInfo objESerialRuleInfo = null;
        PrintTInfo objEPrintTInfo = null;
        PrinterInfo objEPrinterInfo = null;
        BAttachInfo objEBAttachInfo = null;

        //获取打印模板信息
        objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD03.getPrintTRd());
        if(objEPrintTInfo == null){
            throw new SystemException("","打印模板信息为空");
        }
        if(!FastfdsUtils.isFile(objEPrintTInfo.getFileName())){
            throw new SystemException("", "模板文件不存在,请重新上传");
        }
        //获取打印机信息
        objEPrinterInfo = printerDAO.SelectByRuid(argBD03.getPrintRd());
        if(objEPrinterInfo == null){
            throw new SystemException("","打印机信息为空");
        }

        for(SaveBatchInfoReqBD03BInfo bInfo : argBD03.getBInfo()){
            //获取物料版本信息
            objEMaVerInfo = maVerDAO.SelectByRuid(bInfo.getMaVerRd());
            if(objEMaVerInfo == null){
                throw new SystemException("","物料版本信息为空");
            }
            if("01".equals(objEMaVerInfo.getStatus())){
                throw new SystemException("", objEMaVerInfo.getMaterialName() + "不可用");
            }
            if("01".equals(objEMaVerInfo.getIsBatch())){
                //待创建批次总数
                float allNum = 0f;
                if ("02".equals(argBD03.getWoSource())) {
                    PurChDtlInfo purChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByRuid(bInfo.getWoDlRd());
                    if (purChDtlInfo != null) {
                        allNum = new BigDecimal(String.valueOf(purChDtlInfo.getNum())).subtract(new BigDecimal(String.valueOf(purChDtlInfo.getScanNum()))).floatValue();
                    }
                } else if ("03".equals(argBD03.getWoSource())) {
                    InCDtlInfo inCDtlInfo = inCDtlDAO.SelectInCDtlInfoByRuid(bInfo.getWoDlRd());
                    if (inCDtlInfo != null) {
                        allNum = new BigDecimal(String.valueOf(inCDtlInfo.getNum())).subtract(new BigDecimal(String.valueOf(inCDtlInfo.getScanNum()))).floatValue();
                        //inCDtlInfo.setIsPrint("00");
                        if (inCDtlDAO.UpdateInChDtlInfo(inCDtlInfo) <= 0) {
                            throw new SystemException("", "物料创批失败");
                        }
                    }
                }

                //判断拆批数量是否大于应收总数
                if ("00".equals(bInfo.getIsPrintWP())) {
                    if (allNum <= new BigDecimal(String.valueOf(bInfo.getBCount())).multiply(new BigDecimal(String.valueOf(bInfo.getSplitBCount() - 1))).floatValue()) {
                        throw new SystemException("", "拆批总数量大于应收总数");
                    }
                } else {
                    if (allNum < new BigDecimal(String.valueOf(bInfo.getBCount())).multiply(new BigDecimal(String.valueOf(bInfo.getSplitBCount()))).floatValue()) {
                        throw new SystemException("", "拆批总数量大于应收总数");
                    }
                }

                PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
                objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                objEPrintTaskInfo.setReCode(woCode);
                objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                objEPrintTaskInfo.setBarCode(objEMaVerInfo.getMaBarCode());
                objEPrintTaskInfo.setOrderType(objERkTkInfo.getAssSource());
                objEPrintTaskInfo.setBarType("03");
                objEPrintTaskInfo.setPrintCount(print_count);
                objEPrintTaskInfo.setPrintCopy(print_count);
                objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
                objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                objEPrintTaskInfo.setCreator(userName);
                objEPrintTaskInfo.setCreateTime(date);
                //将可能需要打印的对象写入map
                map = new HashMap<String, Object>();
                map.put("MaVerInfo", objEMaVerInfo);
                map.put("UnitInfo", objEUnitInfo);
                map.put("PrintTInfo", objEPrintTInfo);
                map.put("PrinterInfo", objEPrinterInfo);
                map.put("BAttachInfo", objEBAttachInfo);
                BInfo b = new BInfo();
                b.setBatch(objEMaVerInfo.getMaBarCode());
                b.setCreateTime(date);
                b.setCanNum(bInfo.getBCount() * bInfo.getSplitBCount());
                map.put("BInfo", b);

                objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));

                objEPrintTaskInfos.add(objEPrintTaskInfo);
                printMap.put(objEPrintTaskInfo.getpTCode(), objEPrinterInfo.getPrinterSer());
            }else {
                BInfo objEBInfo = new BInfo();
                //工单来源
                objEBInfo.setWoSource(argBD03.getWoSource());
                //工单标识
                objEBInfo.setWoGd(woGd);

                objEBInfo.setMaVerGd(objEMaVerInfo.getGuid());

                //获取单位信息
                objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
                if (objEUnitInfo == null) {
                    throw new SystemException("", "单位信息为空");
                }
                objEBInfo.setUnitGd(objEUnitInfo.getGuid());

                //获取序号规则Z
                objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaVerInfo.getSerialRuleGd());
                if (objESerialRuleInfo == null) {
                    PdFamilyInfo objEPdFamilyInfo = pdFamilyDAO.SelectByGuid(objEMaVerInfo.getPdFamilyGd());
                    if (objEPdFamilyInfo == null) {
                        throw new SystemException("", "请在物料" + objEMaVerInfo.getMaterialName() + "上绑定序号规则信息!");
                    }
                    objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEPdFamilyInfo.getSerialRuleGd());
                    if (objESerialRuleInfo == null) {
                        throw new SystemException("", "请在物料" + objEMaVerInfo.getMaterialName() + "上绑定序号规则信息!");
                    }
                }

                objEBInfo.setPrintTGd(objEPrintTInfo.getGuid());

                objEBInfo.setBad("01");
                objEBInfo.setCkResult("03");
                objEBInfo.setStatus("00");

                //TODO 批次来源
                objEBInfo.setbSource("00");
                if ("00".equals(objEMaVerInfo.getIsExem())) {
                    objEBInfo.setiQCStatus("01");
                } else {
                    objEBInfo.setiQCStatus("00");
                }
                objEBInfo.setInStockStatus("02");
                objEBInfo.setCreator(userName);
                objEBInfo.setCreateTime(date);
                objEBInfo.setRemark(argBD03.getRemark());

                if (bInfo.getBCount() <= 0) {
                    throw new SystemException("", bInfo.getBCount() + "批次数量不能小于零");
                }
                //TODO 判断是否打印包裹批次
                String supBatch = "";
                if ("00".equals(bInfo.getIsPrintPack())) {
                    supBatch = "P" + sniService.GetCreateSR(objESerialRuleInfo);
                    objEBInfo.setBatch(supBatch);
                    objEBInfo.setGuid(CommonUtils.getRandomNumber());
                    objEBInfo.setNum(0);
                    objEBInfo.setCanNum(0);
                    objEBInfo.setConsuNum(0);
                    objBatchDAO.InsertBatch(objEBInfo);

                    //将可能需要打印的对象写入map
                    map = new HashMap<String, Object>();
                    map.put("MaVerInfo", objEMaVerInfo);
                    map.put("UnitInfo", objEUnitInfo);
                    map.put("PrintTInfo", objEPrintTInfo);
                    map.put("PrinterInfo", objEPrinterInfo);
                    map.put("BInfo", objEBInfo);

                    PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
                    objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                    objEPrintTaskInfo.setReCode(woCode);
                    objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEPrintTaskInfo.setBarCode(objEBInfo.getBatch());
                    objEPrintTaskInfo.setOrderType(objERkTkInfo.getAssSource());
                    objEPrintTaskInfo.setBarType("02");
                    objEPrintTaskInfo.setPrintCount(print_count);
                    objEPrintTaskInfo.setPrintCopy(print_count);
                    objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
                    objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                    objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                    objEPrintTaskInfo.setCreator(userName);
                    objEPrintTaskInfo.setCreateTime(date);

                    objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));

                    objEPrintTaskInfos.add(objEPrintTaskInfo);
                    printMap.put(objEPrintTaskInfo.getpTCode(), objEPrinterInfo.getPrinterSer());
                }

                if ("00".equals(bInfo.getIsPrintPack())) {
                    objEBInfo.setSupBatch(supBatch);
                }

                //待创建批次总数
                float allNum = 0f;
                if ("02".equals(argBD03.getWoSource())) {
                    PurChDtlInfo purChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByRuid(bInfo.getWoDlRd());
                    if (purChDtlInfo != null) {
                        objEBInfo.setWoDlGd(purChDtlInfo.getGuid());
                        allNum = new BigDecimal(String.valueOf(purChDtlInfo.getNum())).subtract(new BigDecimal(String.valueOf(purChDtlInfo.getScanNum()))).floatValue();
                    }
                } else if ("03".equals(argBD03.getWoSource())) {
                    InCDtlInfo inCDtlInfo = inCDtlDAO.SelectInCDtlInfoByRuid(bInfo.getWoDlRd());
                    if (inCDtlInfo != null) {
                        objEBInfo.setWoDlGd(inCDtlInfo.getGuid());
                        allNum = new BigDecimal(String.valueOf(inCDtlInfo.getNum())).subtract(new BigDecimal(String.valueOf(inCDtlInfo.getScanNum()))).floatValue();
                        //inCDtlInfo.setIsPrint("00");
                        if (inCDtlDAO.UpdateInChDtlInfo(inCDtlInfo) <= 0) {
                            throw new SystemException("", "物料创批失败");
                        }
                    }
                }

                //判断拆批数量是否大于应收总数
                if ("00".equals(bInfo.getIsPrintWP())) {
                    if (allNum <= new BigDecimal(String.valueOf(bInfo.getBCount())).multiply(new BigDecimal(String.valueOf(bInfo.getSplitBCount() - 1))).floatValue()) {
                        throw new SystemException("", "拆批总数量大于应收总数");
                    }
                } else {
                    if (allNum < new BigDecimal(String.valueOf(bInfo.getBCount())).multiply(new BigDecimal(String.valueOf(bInfo.getSplitBCount()))).floatValue()) {
                        throw new SystemException("", "拆批总数量大于应收总数");
                    }
                }

                //插入批次信息
                String batch = sniService.GetCreateSR(objESerialRuleInfo);
                float bcount = 0f;
                for (int i = 0, length = bInfo.getSplitBCount(); i < length; i++) {
                    bcount = 0f;
                    if (i == length - 1) {
                        if (i == 0) {
                            //throw new SystemException("", objEMaVerInfo.getMaterialName() + "物料拆批数为一不打尾批,则没有批次生成!");
                            bcount = bInfo.getBCount();
                        } else {
                            if ("00".equals(bInfo.getIsPrintWP())) {
                                BigDecimal b1 = new BigDecimal(String.valueOf(bInfo.getBCount()));
                                BigDecimal b2 = new BigDecimal(String.valueOf(bInfo.getSplitBCount() - 1));
                                BigDecimal b3 = new BigDecimal(String.valueOf(allNum));
                                bcount = b3.subtract(b1.multiply(b2)).floatValue();
                                if (bInfo.getBCount() < bcount) {
                                    bcount = bInfo.getBCount();
                                }
                            } else {
                                continue;
                            }
                        }
                    } else {
                        bcount = bInfo.getBCount();
                    }

                    objEBInfo.setGuid(CommonUtils.getRandomNumber());
                    objEBInfo.setBatch(batch + batchTail(i + 1));
                    objEBInfo.setNum(bcount);
                    objEBInfo.setCanNum(bcount);
                    objEBInfo.setConsuNum(0);

                    objEBInfo.setbType("01");

                    objBatchDAO.InsertBatch(objEBInfo);

                    //批次附加表
                    objEBAttachInfo = new BAttachInfo();
                    objEBAttachInfo.setGuid(CommonUtils.getRandomNumber());
                    objEBAttachInfo.setBatch(objEBInfo.getBatch());
                    if ("甲供料".equals(bInfo.getF1())) {
                        objEBAttachInfo.setF1("甲供料");
                    } else {
                        objEBAttachInfo.setF1("");
                    }
                    objEBAttachInfo.setCreator(userName);
                    objEBAttachInfo.setCreateTime(date);
                    bAttachDAO.InsertBAttachInfo(objEBAttachInfo);

                    //批次事物操作
                    objEAffairInfo = new AffairInfo();
                    objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                    objEAffairInfo.setBatch(objEBInfo.getBatch());
                    objEAffairInfo.setSpecVerGd("");
                    objEAffairInfo.setSpecName("");
                    objEAffairInfo.setOptType("14");
                    objEAffairInfo.setCreator(userName);
                    objEAffairInfo.setCreateTime(date);
                    affairDAO.InsertAffairInfo(objEAffairInfo);

                    PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
                    objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
                    objEPrintTaskInfo.setReCode(woCode);
                    objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEPrintTaskInfo.setBarCode(objEBInfo.getBatch());
                    objEPrintTaskInfo.setOrderType(objERkTkInfo.getAssSource());
                    objEPrintTaskInfo.setBarType("02");
                    objEPrintTaskInfo.setPrintCount(print_count);
                    objEPrintTaskInfo.setPrintCopy(print_count);
                    objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
                    objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                    objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                    objEPrintTaskInfo.setCreator(userName);
                    objEPrintTaskInfo.setCreateTime(date);

                    //-----------------------------------------
                    mapP = new HashMap<String, String>();
                    mapP.put("Batch", objEBInfo.getBatch());
                    mapP.put("MaCode", objEMaVerInfo.getMaterialCode());
                    mapP.put("MaName", objEMaVerInfo.getMaterialName());
                    mapP.put("Num", String.valueOf(objEBInfo.getNum()));
                    mapP.put("IsExem", objEMaVerInfo.getIsExem());
                    listP.add(mapP);

                    //将可能需要打印的对象写入map
                    map = new HashMap<String, Object>();
                    map.put("MaVerInfo", objEMaVerInfo);
                    map.put("UnitInfo", objEUnitInfo);
                    map.put("PrintTInfo", objEPrintTInfo);
                    map.put("PrinterInfo", objEPrinterInfo);
                    map.put("BInfo", objEBInfo);
                    map.put("BAttachInfo", objEBAttachInfo);

                    objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));

                    objEPrintTaskInfos.add(objEPrintTaskInfo);
                    printMap.put(objEPrintTaskInfo.getpTCode(), objEPrinterInfo.getPrinterSer());
                }
            }
        }

        for (PrintTaskInfo printTaskInfo : objEPrintTaskInfos) {
            hisPrintLogIService.PrintTQueue(printTaskInfo, printMap.get(printTaskInfo.getpTCode()), "00");
        }

        if(f){
            switchData("00", rkCode, woCode, listP);
        }

        objESaveBatchInfoResB.setMsgCode("0x00000");
        objESaveBatchInfoResB.setMsgDes("成功");
        objESaveBatchInfoRes.setBody(objESaveBatchInfoResB);

        return objESaveBatchInfoRes;
    }

    /**
     * 将 SaveBatchInfoADD方法中对bInfo对象的数据设置提取出来
     * @param saveBatchInfoReqBD00
     * @return
     */
    public BInfo SaveBatchInfoADDSetBInfo(SaveBatchInfoReqBD00 saveBatchInfoReqBD00){
        float bCount =  saveBatchInfoReqBD00.getBCount();

        if(bCount <= 0){
            throw new SystemException("", "批次数量不能为空或负数");
        }

        int splitCount = saveBatchInfoReqBD00.getSplitBCount();
        BInfo bInfo = new BInfo();
        bInfo.setGuid(CommonUtils.getRandomNumber());
        bInfo.setWoSource(saveBatchInfoReqBD00.getWoSource());
        bInfo.setNum(bCount);
        bInfo.setCanNum(bCount);
        bInfo.setBad("01");
        /*bInfo.setInvalidStatus("01");
        bInfo.setIsFreeze("00");
        bInfo.setIsOpen("00");*/
        bInfo.setjStartDate(DateUtil.format2(saveBatchInfoReqBD00.getJStartDate()));
        bInfo.setjFinishDate(DateUtil.format2(saveBatchInfoReqBD00.getJFinishDate()));
        bInfo.setStatus("00");
        bInfo.setInStockStatus("02");
        bInfo.setCreator(CommonUtils.readUser().getRealName());
        bInfo.setCreateTime(new Date());
        bInfo.setRemark(saveBatchInfoReqBD00.getRemark());
        return bInfo;
    }

    /**
     * 将 SaveBatchInfoADD方法中对bInfo对象的数据设置提取出来2
     * @param saveBatchInfoReqBD00
     * @return
     */
    public WmsMaterialsBInfo SaveBatchInfoADDSetBInfo2(SaveBatchInfoReqBD00 saveBatchInfoReqBD00){
        float bCount =  saveBatchInfoReqBD00.getBCount();

        if(bCount <= 0){
            throw new SystemException("", "批次数量不能为空或负数");
        }

        int splitCount = saveBatchInfoReqBD00.getSplitBCount();
        WmsMaterialsBInfo bInfo = new WmsMaterialsBInfo();
        bInfo.setGuid(CommonUtils.getRandomNumber());
        bInfo.setWoSource(saveBatchInfoReqBD00.getWoSource());
        bInfo.setNum(new BigDecimal(String.valueOf(bCount)));
        bInfo.setCanNum(new BigDecimal(String.valueOf(bCount)));
        bInfo.setBad("01");
        bInfo.setStatus("00");
        bInfo.setInStockStatus("02");
        bInfo.setCreator(CommonUtils.readUser().getRealName());
        bInfo.setCreateTime(new Date());
        bInfo.setRemark(saveBatchInfoReqBD00.getRemark());
        return bInfo;
    }

    /**
     * 将 SaveBatchInfoADD方法中对bInfo对象的数据设置提取出来
     * @param argBInfo
     * @return
     */
    public BInfo SaveBatchInfoADDSetBInfo(BInfo argBInfo){
        BInfo bInfo = new BInfo();
        bInfo.setGuid(argBInfo.getGuid());
        bInfo.setWoSource(argBInfo.getWoSource());
        bInfo.setWoGd(argBInfo.getWoGd());
        bInfo.setBatch(argBInfo.getBatch());
        bInfo.setMaVerGd(argBInfo.getMaVerGd());
        bInfo.setNum(argBInfo.getNum());
        bInfo.setCanNum(argBInfo.getCanNum());
        bInfo.setUnitGd(argBInfo.getUnitGd());
        bInfo.setwFVerGd(argBInfo.getwFVerGd());
        bInfo.setSpecVerGd(argBInfo.getSpecVerGd());
        bInfo.setbLName(argBInfo.getbLName());
        bInfo.setPrintTGd(argBInfo.getPrintTGd());
        bInfo.setBad(argBInfo.getBad());
        bInfo.setiQCStatus(argBInfo.getiQCStatus());
        bInfo.setCkResult(argBInfo.getCkResult());
        bInfo.setbSource("00");
        bInfo.setjStartDate(argBInfo.getjStartDate());
        bInfo.setjFinishDate(argBInfo.getjFinishDate());
        bInfo.setsStartDate(argBInfo.getsStartDate());
        bInfo.setsFinishDate(argBInfo.getsFinishDate());
        bInfo.setStatus(argBInfo.getStatus());
        bInfo.setInStockStatus(argBInfo.getInStockStatus());
        bInfo.setCreator(argBInfo.getCreator());
        bInfo.setCreateTime(argBInfo.getCreateTime());
        bInfo.setLastModifyMan(argBInfo.getLastModifyMan());
        bInfo.setLastModifyTime(argBInfo.getLastModifyTime());
        bInfo.setRemark(argBInfo.getRemark());
        return bInfo;
    }

    /**
     * SaveBatchInfoADD方法中对bInfo对象的数据设置提取出来2
     * @param argBInfo
     * @return
     */
    public WmsMaterialsBInfo SaveBatchInfoADDSetBInfo2(WmsMaterialsBInfo argBInfo){
        WmsMaterialsBInfo bInfo = new WmsMaterialsBInfo();
        bInfo.setGuid(argBInfo.getGuid());
        bInfo.setWoSource(argBInfo.getWoSource());
        bInfo.setWoGd(argBInfo.getWoGd());
        bInfo.setBatch(argBInfo.getBatch());
        bInfo.setMaVerGd(argBInfo.getMaVerGd());
        bInfo.setNum(argBInfo.getNum());
        bInfo.setCanNum(argBInfo.getCanNum());
        bInfo.setBad(argBInfo.getBad());
        bInfo.setiQCStatus(argBInfo.getiQCStatus());
        bInfo.setCkResult(argBInfo.getCkResult());
        bInfo.setbSource("00");
        bInfo.setStatus(argBInfo.getStatus());
        bInfo.setInStockStatus(argBInfo.getInStockStatus());
        bInfo.setCreator(argBInfo.getCreator());
        bInfo.setCreateTime(argBInfo.getCreateTime());
        bInfo.setLastModifier(argBInfo.getLastModifier());
        bInfo.setLastModifyTime(argBInfo.getLastModifyTime());
        bInfo.setRemark(argBInfo.getRemark());
        return bInfo;
    }

    //获取批次拆分、合并信息
    @Override
    public GetSUBInfoRes GetSUBInfo(GetSUBInfoReqBD00 argBD00) throws SystemException {
        GetSUBInfoRes objEGetSUBInfoRes = new GetSUBInfoRes();
        GetSUBInfoResB objEGetSUBInfoResB = new GetSUBInfoResB();
        GetSUBInfoResD objEGetSUBInfoResD = new GetSUBInfoResD();
        List<GetSUBInfoResDRefBatch> objEGetSUBInfoResDRefBatchs = new ArrayList<GetSUBInfoResDRefBatch>();

        //查询批次信息
        BInfo objEBInfo = objBatchDAO.selectBatchInfoByBatch(argBD00.getBatch());
        if(objEBInfo == null){
            throw new SystemException("", "批次信息不存在");
        }

        //查询物料信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料信息不存在");
        }

        objEGetSUBInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
        objEGetSUBInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
        objEGetSUBInfoResD.setMaName(objEMaVerInfo.getMaterialName());
        objEGetSUBInfoResD.setNum(objEBInfo.getCanNum());
        objEGetSUBInfoResD.setBadNum(objEBInfo.getBadNum());
        objEGetSUBInfoResD.setWoCode("");
        //查询工单号
        if("01".equals(objEBInfo.getWoSource())) {
            WoInfo woInfo = woDAO.SelectWoInfoByGuid(objEBInfo.getWoGd());
            if(woInfo != null) {
                objEGetSUBInfoResD.setWoCode(woInfo.getWoCode());
            }
        }

        //查询打印机、打印模板
        PrintTInfo objEPrintTInfo = printTDAO.SelectByGuid(objEBInfo.getPrintTGd());
        if(objEPrintTInfo != null){
            objEGetSUBInfoResD.setPrintTRd(objEPrintTInfo.getRuid());
            objEGetSUBInfoResD.setPtName(objEPrintTInfo.getTempName());
        }
        /*PrinterInfo objEPrinterInfo = printerDAO.SelectByGuid(objEBInfo.getPinterGd());
        if(objEPrinterInfo != null){
            objEGetSUBInfoResD.setPrintRd(objEPrinterInfo.getRuid());
            objEGetSUBInfoResD.setPrName(objEPrinterInfo.getPrinterName());
        }*/

        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if(objEUnitInfo != null){
            objEGetSUBInfoResD.setUnitName(objEUnitInfo.getUnitName());
        }

        //查询拆分批次
        List<SplitBatchInfo> objESplitBatchInfos = splitBatchDAO.SelectBySBatch(argBD00.getBatch());
        for(SplitBatchInfo splitBatchInfo : objESplitBatchInfos){

            GetSUBInfoResDRefBatch objEGetSUBInfoResDRefBatch = new GetSUBInfoResDRefBatch();
            objEGetSUBInfoResDRefBatch.setBatch(splitBatchInfo.getBatch());
            objEGetSUBInfoResDRefBatch.setNum(splitBatchInfo.getNum());
            objEGetSUBInfoResDRefBatch.setUnitName(splitBatchInfo.getUnitName());
            objEGetSUBInfoResDRefBatch.setSplitor(splitBatchInfo.getCreator());
            objEGetSUBInfoResDRefBatch.setSplitTime(DateUtil.format(splitBatchInfo.getCreateTime()));

            objEGetSUBInfoResDRefBatchs.add(objEGetSUBInfoResDRefBatch);
        }

        objEGetSUBInfoResD.setRefBatch(objEGetSUBInfoResDRefBatchs);
        objEGetSUBInfoResB.setData(objEGetSUBInfoResD);
        objEGetSUBInfoRes.setBody(objEGetSUBInfoResB);

        return objEGetSUBInfoRes;
    }

    //保存拆分批次信息
    @Override
    public SaveSUBInfoRes AddSplitBatch(SaveSUBInfoReqBD00 argBD00) throws SystemException {
        SaveSUBInfoRes objESaveSUBInfoRes = new SaveSUBInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询批次信息
        BInfo objEBInfo = objBatchDAO.selectBatchInfoByBatch(argBD00.getBatch());
        if(objEBInfo == null){
            throw new SystemException("", "批次信息不存在");
        }
        if("00".equals(objEBInfo.getPackStatus())){
            throw new SystemException("", "该批次已被打包过");
        }

        if("00".equals(objEBInfo.getSpecLFlag())){
            throw new SystemException("", "工序留存,不允许拆批!");
        }

        String status = objEBInfo.getStatus();
        if("01".equals(status)){
            throw new SystemException("", "该批次正在处理中");
        }
        if("02".equals(status)){
            throw new SystemException("", "该批次已经冻结");
        }
        if("03".equals(status)){
            throw new SystemException("", "该批次已经报废");
        }
        /*if("04".equals(status)){
            throw new SystemException("", "该批次已经完工");
        }*/

        String barType = "", barCode = "";
        switch (objEBInfo.getWoSource()){
            case "00":
                barType = "04";
                break;
            case "01":
                barType = "02";
                WoInfo objEWoInfo = woDAO.SelectWoInfoByGuid(objEBInfo.getWoGd());
                if(objEWoInfo != null){
                    barCode = objEWoInfo.getWoCode();
                }
                break;
            case "02":
                barType = "00";
                PurChInfo objEPurChInfo = purChDAO.SelectByGuid(objEBInfo.getWoGd());
                if(objEPurChInfo != null){
                    barCode = objEPurChInfo.getPurChCode();
                }
                break;
            case "03":
                barType = "01";
                InCInfo objEInCInfo = inCDAO.SelectByGuid(objEBInfo.getWoGd());
                if(objEInCInfo != null){
                    barCode = objEInCInfo.getInCCode();
                }
                break;
            default:
                break;
        }

        //打印模板信息
        PrintTInfo objEPrintTInfo = null;
        //打印机信息
        PrinterInfo objEPrinterInfo = null;

        //查询物料版本
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "该批次物料为空");
        }

        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if(objEUnitInfo == null){
            throw new SystemException("", "单位为空");
        }

        BInfo objEBInfoCopy = new BInfo();
        objEBInfoCopy.setWoSource(objEBInfo.getWoSource());
        objEBInfoCopy.setWoGd(objEBInfo.getWoGd());
        objEBInfoCopy.setWoDlGd(objEBInfo.getWoDlGd());
        objEBInfoCopy.setbType(objEBInfo.getbType());
        objEBInfoCopy.setIsBarBatch(objEBInfo.getIsBarBatch());
        objEBInfoCopy.setMaVerGd(objEBInfo.getMaVerGd());
        objEBInfoCopy.setBatch(objEBInfo.getBatch());
        objEBInfoCopy.setSupBatch(objEBInfo.getSupBatch());
        objEBInfoCopy.setUnitGd(objEBInfo.getUnitGd());
        objEBInfoCopy.setwFVerGd(objEBInfo.getwFVerGd());
        objEBInfoCopy.setSpecVerGd(objEBInfo.getSpecVerGd());
        objEBInfoCopy.setbLName(objEBInfo.getbLName());
        objEBInfoCopy.setPrintTGd(objEBInfo.getPrintTGd());
        objEBInfoCopy.setbSource("01");
        objEBInfoCopy.setjStartDate(objEBInfo.getjStartDate());
        objEBInfoCopy.setjFinishDate(objEBInfo.getjFinishDate());
        objEBInfoCopy.setsStartDate(objEBInfo.getsStartDate());
        objEBInfoCopy.setsFinishDate(objEBInfo.getsFinishDate());
        objEBInfoCopy.setBad(objEBInfo.getBad());
        objEBInfoCopy.setiQCStatus(objEBInfo.getiQCStatus());
        objEBInfoCopy.setCkResult(objEBInfo.getCkResult());
        objEBInfoCopy.setStatus(objEBInfo.getStatus());
        objEBInfoCopy.setInStockStatus(objEBInfo.getInStockStatus());
        objEBInfoCopy.setwFStatus(objEBInfo.getwFStatus());
        objEBInfoCopy.setPackStatus(objEBInfo.getPackStatus());
        objEBInfoCopy.setRemark(objEBInfo.getRemark());

        //生产流转信息表
        BCirInfo bCirInfo = bCirDAO.SelectByBatch(objEBInfo.getBatch());

        //记录消耗数量(总数量，不良数量, 良数量)
        float num = 0f, badNum = 0f, lNum = 0f;

        List<PrintTaskInfo> objEPrintTaskInfos = new ArrayList<PrintTaskInfo>();

        //是否打印
        int print_count = 0;
        if("00".equals(argBD00.getIsPrint())){
            //获取打印模板信息
            objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD00.getPrintTRd());
            if(objEPrintTInfo == null){
                throw new SystemException("","打印模板信息为空");
            }

            //获取打印机信息
            objEPrinterInfo = printerDAO.SelectByRuid(argBD00.getPrintRd());
            if(objEPrinterInfo == null){
                throw new SystemException("","打印机信息为空");
            }
            print_count = 1;
        }else{
            objEPrintTInfo = new PrintTInfo();
            objEPrintTInfo.setGuid("");
            objEPrintTInfo.setFileName("");
            objEPrinterInfo = new PrinterInfo();
            objEPrinterInfo.setPrinterSer("");
            objEPrinterInfo.setPrinterName("");
        }

        //判断批次状态 （在库00、离库01、未设置02）
        String isInStockStatus = "02";

        if(argBD00.getSplitInfo() == null || argBD00.getSplitInfo().size() <= 0){
            throw new SystemException("", "要拆的批次个数不能少于一个");
        }

        //将可能需要打印的对象写入map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("MaVerInfo", objEMaVerInfo);
        map.put("UnitInfo", objEUnitInfo);
        map.put("PrintTInfo", objEPrintTInfo);
        map.put("PrinterInfo", objEPrinterInfo);

        InstockDtlInfo objEInstockDtlInfo = null;
        List<PickBatchInfo> objEPickBatchInfos = null;
        if("00".equals(objEBInfo.getInStockStatus())){
            //判断批次（在库、离库、未设置）
            objEInstockDtlInfo = instockDtlDAO.SelectBybatch(objEBInfo.getBatch());
            if(objEInstockDtlInfo == null){
                throw new SystemException("", "批次在库，但物料不在库存中");
            }
            isInStockStatus = "00";
        }else if("01".equals(objEBInfo.getInStockStatus())){
            //TODO 查询领料批次明细
            objEPickBatchInfos = pickBatchDAO.SelectByBatch(objEBInfo.getBatch());

            isInStockStatus = "01";
        }

        //批次事物操作
        AffairInfo objEAffairInfo = new AffairInfo();
        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
        objEAffairInfo.setBatch(objEBInfo.getBatch());
        objEAffairInfo.setSpecVerGd("");
        objEAffairInfo.setSpecName("");
        objEAffairInfo.setOptType("12");
        objEAffairInfo.setCreator(userName);
        objEAffairInfo.setCreateTime(date);
        affairDAO.InsertAffairInfo(objEAffairInfo);

        for(SaveSUBInfoReqBD00SplitInfo splitInfo : argBD00.getSplitInfo()){

            num += splitInfo.getNum();

            //判断拆分的批次良于不良
            if("00".equals(splitInfo.getBadFlag())){
                objEBInfoCopy.setBad("00");
                objEBInfoCopy.setBadNum(splitInfo.getNum());
                objEBInfoCopy.setCanNum(0f);
                badNum += splitInfo.getNum();
            }else{
                objEBInfoCopy.setBad("01");
                objEBInfoCopy.setBadNum(0f);
                objEBInfoCopy.setCanNum(splitInfo.getNum());
                lNum += splitInfo.getNum();
            }

            //插入新的批次
            objEBInfoCopy.setGuid(CommonUtils.getRandomNumber());

            //判断批次是否已经存在
            BInfo bInfo = bdao.selectBatchInfoByBatch(splitInfo.getSplitBatch());
            if(bInfo != null){
                throw new SystemException("", splitInfo.getSplitBatch() + "批次已经存在,请删除之后重新添加");
            }

            objEBInfoCopy.setBatch(splitInfo.getSplitBatch());

            if(splitInfo.getNum() <= 0){
                throw new SystemException("", "拆分的批次数量不能小于零");
            }
            objEBInfoCopy.setNum(splitInfo.getNum());

            objEBInfoCopy.setConsuNum(0);
            //TODO 批次来源
            objEBInfoCopy.setbSource(objEBInfo.getbSource());
            objEBInfoCopy.setCreator(userName);
            objEBInfoCopy.setCreateTime(date);
            objBatchDAO.InsertBatch(objEBInfoCopy);

            //插入生产流转信息
            if(bCirInfo != null){
                bCirInfo.setGuid(CommonUtils.getRandomNumber());
                bCirInfo.setBatch(splitInfo.getSplitBatch());
                bCirDAO.InsertBCir(bCirInfo);
            }

            //插入拆分批次信息
            SplitBatchInfo objESplitBatchInfo = new SplitBatchInfo();
            objESplitBatchInfo.setGuid(CommonUtils.getRandomNumber());
            objESplitBatchInfo.setsBatch(objEBInfo.getBatch());
            objESplitBatchInfo.setBatch(splitInfo.getSplitBatch());
            objESplitBatchInfo.setNum(splitInfo.getNum());
            objESplitBatchInfo.setUnitName(objEUnitInfo.getUnitName());
            objESplitBatchInfo.setCreator(userName);
            objESplitBatchInfo.setCreateTime(date);
            splitBatchDAO.InsertSplitBatch(objESplitBatchInfo);

            //如果批次是在库状态,则记录
            if("00".equals(isInStockStatus)){
                objEInstockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                objEInstockDtlInfo.setBatch(splitInfo.getSplitBatch());
                objEInstockDtlInfo.setCreator(userName);
                objEInstockDtlInfo.setCreateTime(date);
                objEInstockDtlInfo.setLastModifyMan(null);
                objEInstockDtlInfo.setLastModifyTime(null);
                instockDtlDAO.InsertInstockDtlInfo(objEInstockDtlInfo);
            }else if("01".equals(isInStockStatus)){
                //如果是离库状态，则写入领料批次明细表
                if(objEPickBatchInfos != null){
                    for(PickBatchInfo pickBatchInfo : objEPickBatchInfos){
                        pickBatchInfo.setGuid(CommonUtils.getRandomNumber());
                        pickBatchInfo.setBatch(splitInfo.getSplitBatch());
                        pickBatchInfo.setCreator(userName);
                        pickBatchInfo.setCreateTime(date);
                        pickBatchInfo.setLastModifyMan(null);
                        pickBatchInfo.setLastModifyTime(null);
                        pickBatchDAO.InsertPickBatch(pickBatchInfo);
                    }
                }
            }

            //待插入
            PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
            objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
            objEPrintTaskInfo.setReCode(barCode);
            objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            objEPrintTaskInfo.setBarCode(objEBInfoCopy.getBatch());
            objEPrintTaskInfo.setOrderType(barType);
            objEPrintTaskInfo.setBarType("02");
            objEPrintTaskInfo.setPrintCount(print_count);
            objEPrintTaskInfo.setPrintCopy(print_count);
            objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
            objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
            objEPrintTaskInfo.setCreator(userName);
            objEPrintTaskInfo.setCreateTime(date);

            //批次附加表
            BAttachInfo objEBAttachInfo = new BAttachInfo();
            objEBAttachInfo.setGuid(CommonUtils.getRandomNumber());
            objEBAttachInfo.setBatch(objEBInfoCopy.getBatch());
            objEBAttachInfo.setCreator(userName);
            objEBAttachInfo.setCreateTime(date);
            bAttachDAO.InsertBAttachInfo(objEBAttachInfo);

            String strData = "";
            if("00".equals(argBD00.getIsPrint())){
                map.put("BInfo", objEBInfoCopy);

                strData = hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid());
            }

            objEPrintTaskInfo.setPrintData(strData.toString());
            objEPrintTaskInfos.add(objEPrintTaskInfo);
        }

        //判断是否超出拆批总数
        if(badNum > objEBInfo.getBadNum()){
            throw new SystemException("", objEBInfo.getBatch() + "批次所拆的不良品批次数量大于该批次的总不良品数量");
        }
        objEBInfo.setBadNum(objEBInfo.getBadNum() - badNum);
        if(objEBInfo.getBadNum() <= 0){
            objEBInfo.setBad("01");
        }

        if(lNum > objEBInfo.getCanNum()){
            throw new SystemException("", objEBInfo.getBatch() + "批次所拆的良品批次数量大于该批次的总良品数量");
        }

        //修改源批次信息
        /*if("00".equals(isInStockStatus)){
            objEBInfo.setNum(objEBInfo.getCanNum() - num);
        }else {
            objEBInfo.setConsuNum(objEBInfo.getConsuNum() + num);
        }*/

        objEBInfo.setCanNum(objEBInfo.getCanNum() - lNum);
        objEBInfo.setConsuNum(objEBInfo.getConsuNum() + lNum);

        if(objEBInfo.getCanNum() == 0 && objEBInfo.getBadNum() == 0){

            objEBInfo.setStatus("04");//setIsOpen("01");

        }/*else if(objEBInfo.getCanNum() < 0){
            throw new SystemException("", "拆分数量大于实际数量");
        }*/

        if(objBatchDAO.UpdateBatchInfoByRuid(objEBInfo) <= 0){
            throw new SystemException("", "修改批次信息失败");
        }

        for(PrintTaskInfo printTaskInfo : objEPrintTaskInfos){
            hisPrintLogIService.PrintTQueue(printTaskInfo, objEPrinterInfo.getPrinterSer(), argBD00.getIsPrint());
        }

        return objESaveSUBInfoRes;
    }

    /**
     * 手机领料拆批
     * @param argBatch
     * @param argSplitBatch
     * @param argNum
     * @param argReCode
     * @param argOrderType
     * @param userName
     * @return
     */
    @Override
    public SaveSUBInfoRes AddSplitBatchPhone(String argBatch, String argSplitBatch, float argNum, String argReCode, String argOrderType, String userName) {
        //当前时间
        Date date = new Date();

        //查询批次信息
        BInfo objEBInfo = objBatchDAO.selectBatchInfoByBatch(argBatch);
        if(objEBInfo == null){
            throw new SystemException("", "批次信息不存在");
        }
        if("00".equals(objEBInfo.getPackStatus())){
            throw new SystemException("", "该批次已被打包过");
        }

        if("00".equals(objEBInfo.getSpecLFlag())){
            throw new SystemException("", "工序留存,不允许拆批!");
        }

        String status = objEBInfo.getStatus();
        if("01".equals(status)){
            throw new SystemException("", "该批次正在处理中");
        }
        if("02".equals(status)){
            throw new SystemException("", "该批次已经冻结");
        }
        if("03".equals(status)){
            throw new SystemException("", "该批次已经报废");
        }

        //查询物料版本
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "该批次物料为空");
        }

        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if(objEUnitInfo == null){
            throw new SystemException("", "单位为空");
        }

        BInfo objEBInfoCopy = new BInfo();
        objEBInfoCopy.setWoSource(objEBInfo.getWoSource());
        objEBInfoCopy.setWoGd(objEBInfo.getWoGd());
        objEBInfoCopy.setWoDlGd(objEBInfo.getWoDlGd());
        objEBInfoCopy.setbType(objEBInfo.getbType());
        objEBInfoCopy.setIsBarBatch(objEBInfo.getIsBarBatch());
        objEBInfoCopy.setMaVerGd(objEBInfo.getMaVerGd());
        objEBInfoCopy.setBatch(objEBInfo.getBatch());
        objEBInfoCopy.setSupBatch(objEBInfo.getSupBatch());
        objEBInfoCopy.setUnitGd(objEBInfo.getUnitGd());
        objEBInfoCopy.setwFVerGd(objEBInfo.getwFVerGd());
        objEBInfoCopy.setSpecVerGd(objEBInfo.getSpecVerGd());
        objEBInfoCopy.setbLName(objEBInfo.getbLName());
        objEBInfoCopy.setPrintTGd(objEBInfo.getPrintTGd());
        objEBInfoCopy.setbSource("01");
        objEBInfoCopy.setjStartDate(objEBInfo.getjStartDate());
        objEBInfoCopy.setjFinishDate(objEBInfo.getjFinishDate());
        objEBInfoCopy.setsStartDate(objEBInfo.getsStartDate());
        objEBInfoCopy.setsFinishDate(objEBInfo.getsFinishDate());
        objEBInfoCopy.setBad(objEBInfo.getBad());
        objEBInfoCopy.setiQCStatus(objEBInfo.getiQCStatus());
        objEBInfoCopy.setCkResult(objEBInfo.getCkResult());
        objEBInfoCopy.setStatus(objEBInfo.getStatus());
        objEBInfoCopy.setInStockStatus(objEBInfo.getInStockStatus());
        objEBInfoCopy.setwFStatus(objEBInfo.getwFStatus());
        objEBInfoCopy.setPackStatus(objEBInfo.getPackStatus());
        objEBInfoCopy.setRemark(objEBInfo.getRemark());

        //记录消耗数量(总数量，不良数量, 良数量)
        float num = 0f, badNum = 0f, lNum = 0f;

        //判断批次状态 （在库00、离库01、未设置02）
        String isInStockStatus = "02";

        InstockDtlInfo objEInstockDtlInfo = null;
        List<PickBatchInfo> objEPickBatchInfos = null;
        if("00".equals(objEBInfo.getInStockStatus())){
            //判断批次（在库、离库、未设置）
            objEInstockDtlInfo = instockDtlDAO.SelectBybatch(objEBInfo.getBatch());
            if(objEInstockDtlInfo == null){
                throw new SystemException("", "批次在库，但物料不在库存中");
            }
            isInStockStatus = "00";
        }else if("01".equals(objEBInfo.getInStockStatus())){
            //查询领料批次明细
            objEPickBatchInfos = pickBatchDAO.SelectByBatch(objEBInfo.getBatch());

            isInStockStatus = "01";
        }

        //批次事物操作
        AffairInfo objEAffairInfo = new AffairInfo();
        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
        objEAffairInfo.setBatch(objEBInfo.getBatch());
        objEAffairInfo.setSpecVerGd("");
        objEAffairInfo.setSpecName("");
        objEAffairInfo.setOptType("12");
        objEAffairInfo.setCreator(userName);
        objEAffairInfo.setCreateTime(date);
        affairDAO.InsertAffairInfo(objEAffairInfo);

        //批次附加表
        BAttachInfo objEBAttachInfo = new BAttachInfo();
        objEBAttachInfo.setGuid(CommonUtils.getRandomNumber());
        objEBAttachInfo.setBatch(argSplitBatch);
        objEBAttachInfo.setCreator(userName);
        objEBAttachInfo.setCreateTime(date);
        bAttachDAO.InsertBAttachInfo(objEBAttachInfo);

        objEBInfoCopy.setBad("01");
        objEBInfoCopy.setBadNum(0f);
        objEBInfoCopy.setCanNum(argNum);

        //插入新的批次
        objEBInfoCopy.setGuid(CommonUtils.getRandomNumber());

        //判断批次是否已经存在
        BInfo bInfo = bdao.selectBatchInfoByBatch(argSplitBatch);
        if(bInfo != null){
            throw new SystemException("", argSplitBatch + "批次已经存在,请删除之后重新添加");
        }
        objEBInfoCopy.setBatch(argSplitBatch);

        if(argNum <= 0){
            throw new SystemException("", "拆分的批次数量不能小于零");
        }
        objEBInfoCopy.setNum(argNum);

        objEBInfoCopy.setConsuNum(0);
        //TODO 批次来源
        objEBInfoCopy.setbSource(objEBInfo.getbSource());
        objEBInfoCopy.setCreator(userName);
        objEBInfoCopy.setCreateTime(date);
        objBatchDAO.InsertBatch(objEBInfoCopy);

        //插入拆分批次信息
        SplitBatchInfo objESplitBatchInfo = new SplitBatchInfo();
        objESplitBatchInfo.setGuid(CommonUtils.getRandomNumber());
        objESplitBatchInfo.setsBatch(objEBInfo.getBatch());
        objESplitBatchInfo.setBatch(argSplitBatch);
        objESplitBatchInfo.setNum(argNum);
        objESplitBatchInfo.setUnitName(objEUnitInfo.getUnitName());
        objESplitBatchInfo.setCreator(userName);
        objESplitBatchInfo.setCreateTime(date);
        splitBatchDAO.InsertSplitBatch(objESplitBatchInfo);

        //如果批次是在库状态,则记录
        if("00".equals(isInStockStatus)){
            objEInstockDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objEInstockDtlInfo.setBatch(argSplitBatch);
            objEInstockDtlInfo.setCreator(userName);
            objEInstockDtlInfo.setCreateTime(date);
            objEInstockDtlInfo.setLastModifyMan(null);
            objEInstockDtlInfo.setLastModifyTime(null);
            instockDtlDAO.InsertInstockDtlInfo(objEInstockDtlInfo);
        }else if("01".equals(isInStockStatus)){
            //如果是离库状态，则写入领料批次明细表
            if(objEPickBatchInfos != null){
                for(PickBatchInfo pickBatchInfo : objEPickBatchInfos){
                    pickBatchInfo.setGuid(CommonUtils.getRandomNumber());
                    pickBatchInfo.setBatch(argSplitBatch);
                    pickBatchInfo.setCreator(userName);
                    pickBatchInfo.setCreateTime(date);
                    pickBatchInfo.setLastModifyMan(null);
                    pickBatchInfo.setLastModifyTime(null);
                    pickBatchDAO.InsertPickBatch(pickBatchInfo);
                }
            }
        }
        //查询打印模板信息
        PrintTInfo printTInfo=printTDAO.SelectByGuid(objEBInfoCopy.getPrintTGd());
        if(printTInfo==null||printTInfo.getFileName()==null){
            throw new SystemException("","打印模板信息不存在");
        }
        //待插入
        PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
        objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
        objEPrintTaskInfo.setReCode(argReCode);
        objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
        objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
        objEPrintTaskInfo.setBarCode(objEBInfoCopy.getBatch());
        objEPrintTaskInfo.setOrderType(argOrderType);
        objEPrintTaskInfo.setBarType("02");
        objEPrintTaskInfo.setPrintCount(0);
        objEPrintTaskInfo.setPrintCopy(0);
        objEPrintTaskInfo.setPrinterName("");
        objEPrintTaskInfo.setPrintTGd(printTInfo.getGuid());
        objEPrintTaskInfo.setpTFileName(printTInfo.getFileName());
        objEPrintTaskInfo.setCreator(userName);
        objEPrintTaskInfo.setCreateTime(date);
        objEPrintTaskInfo.setPrintData("");

        //判断是否超出拆批总数
        if(argNum > objEBInfo.getCanNum()){
            throw new SystemException("", objEBInfo.getBatch() + "批次所拆的良品批次数量大于该批次的总良品数量");
        }

        objEBInfo.setCanNum(objEBInfo.getCanNum() - argNum);
        objEBInfo.setConsuNum(objEBInfo.getConsuNum() + argNum);

        if(objEBInfo.getCanNum() == 0 && objEBInfo.getBadNum() == 0){
            objEBInfo.setStatus("04");
        }

        if(objBatchDAO.UpdateBatchInfoByRuid(objEBInfo) <= 0){
            throw new SystemException("", "修改批次信息失败");
        }

        hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, "", "01");
        /*try {
            String s="\"RePrInfo\"[{\"BarCode\":"+argSplitBatch+"}],\"PrintRd\":77,\"PrintCount\":1,\"PrintCopy\":1";
            String content = "ExecType=" + URLEncoder.encode("02", "utf-8") + "&BusData=" + URLEncoder.encode(s, "utf-8") + "";
            CommonUtils.remoteJsonRequest("/pncmes/HisPrintLog/SaveRePrInfo", 5000, content);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return null;
    }

    //合并批次
    @Override
    public SaveSUBInfoRes AddAndBatch(SaveSUBInfoReqBD01 argBD01) {
        SaveSUBInfoRes objESaveSUBInfoRes = new SaveSUBInfoRes();

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        if(argBD01.getBatchInfo() == null || argBD01.getBatchInfo().size() <= 1){
            throw new SystemException("", "要合并的批次个数不能少于二个");
        }
        //判断批次是否同类型、并记录数量
        BInfo objEBInfoFirst = null;
        BInfo objEBinfoLast = null;
        BCirInfo objEBCirInfo = null;
        List<PickBatchInfo> objEPickBatchInfos = null;
        Set<String> setMV = new HashSet<String>();
        float allNum = 0f;
        for(int i=0, length=argBD01.getBatchInfo().size(); i<length; i++){
            if(i == 0){
                objEBInfoFirst = bdao.selectBatchInfoByBatch(argBD01.getBatchInfo().get(i).getBatch());
                if(objEBInfoFirst == null){
                    throw new SystemException("", argBD01.getBatchInfo().get(i).getBatch() + "批次不存在");
                }
                //判断批次是否已经打包
                if("00".equals(objEBInfoFirst.getPackStatus())){
                    throw new SystemException("", objEBInfoFirst.getBatch() + "批次已经被打包");
                }

                if("00".equals(objEBInfoFirst.getSpecLFlag())){
                    throw new SystemException("", objEBInfoFirst.getBatch() + "工序留存,不允许合并!");
                }

                String status = objEBInfoFirst.getStatus();
                if(objEBInfoFirst.getCanNum() == 0){
                    throw new SystemException("", objEBInfoFirst.getBatch() + "批次数量为零");
                }
                allNum += objEBInfoFirst.getCanNum();
                objEBInfoFirst.setCanNum(0f);
                objEBInfoFirst.setConsuNum(objEBInfoFirst.getNum());
                objEBInfoFirst.setStatus("04");
                objEBInfoFirst.setLastModifyMan(userName);
                objEBInfoFirst.setLastModifyTime(date);
                bdao.UpdateBatchInfoByRuid(objEBInfoFirst);
                objEBInfoFirst.setStatus(status);

                //生产流转信息
                objEBCirInfo = bCirDAO.SelectByBatch(objEBInfoFirst.getBatch());
            }else{
                objEBinfoLast = bdao.selectBatchInfoByBatch(argBD01.getBatchInfo().get(i).getBatch());
                if(objEBinfoLast == null){
                    throw new SystemException("", argBD01.getBatchInfo().get(i).getBatch() + "批次不存在");
                }
                //判断批次是否已经打包
                if("00".equals(objEBinfoLast.getPackStatus())){
                    throw new SystemException("", objEBinfoLast.getBatch() + "批次已经被打包");
                }

                if("00".equals(objEBinfoLast.getSpecLFlag())){
                    throw new SystemException("", objEBinfoLast.getBatch() + "工序留存,不允许合并!");
                }

                if(objEBinfoLast.getCanNum() == 0){
                    throw new SystemException("", objEBinfoLast.getBatch() + "批次数量为零");
                }
                if(batchEqauls(objEBInfoFirst, objEBinfoLast)){
                    allNum += objEBinfoLast.getCanNum();
                    objEBinfoLast.setCanNum(0f);
                    objEBinfoLast.setConsuNum(objEBInfoFirst.getNum());
                    objEBinfoLast.setStatus("04");
                    objEBinfoLast.setLastModifyMan(userName);
                    objEBinfoLast.setLastModifyTime(date);
                    bdao.UpdateBatchInfoByRuid(objEBinfoLast);
                }
            }

            if("01".equals(objEBInfoFirst.getInStockStatus())){
                //TODO
                objEPickBatchInfos = pickBatchDAO.SelectByBatch(argBD01.getBatchInfo().get(i).getBatch());
                if(objEPickBatchInfos != null){
                    for(PickBatchInfo pickBatchInfo : objEPickBatchInfos){
                        String[] reML = pickBatchInfo.getReML().split("|");
                        for(String s : reML){
                            setMV.add(s);
                        }
                    }
                }
            }

            //批次事物操作
            AffairInfo objEAffairInfo = new AffairInfo();
            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
            objEAffairInfo.setBatch(argBD01.getBatchInfo().get(i).getBatch());
            objEAffairInfo.setSpecVerGd("");
            objEAffairInfo.setSpecName("");
            objEAffairInfo.setOptType("13");
            objEAffairInfo.setCreator(userName);
            objEAffairInfo.setCreateTime(date);
            affairDAO.InsertAffairInfo(objEAffairInfo);

            //批次附加表
            BAttachInfo objEBAttachInfo = new BAttachInfo();
            objEBAttachInfo.setGuid(CommonUtils.getRandomNumber());
            objEBAttachInfo.setBatch(argBD01.getBatchInfo().get(i).getBatch());
            objEBAttachInfo.setCreator(userName);
            objEBAttachInfo.setCreateTime(date);
            bAttachDAO.InsertBAttachInfo(objEBAttachInfo);
        }

        //查询物料
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfoFirst.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "批次所属的物料不存在");
        }
        SerialRuleInfo objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaVerInfo.getSerialRuleGd());
        if(objESerialRuleInfo == null){
            throw new SystemException("", objEMaVerInfo.getMaterialName() + "物料的序号规则为空");
        }
        String batch = sniService.GetCreateSR(objESerialRuleInfo);

        if("00".equals(objEBInfoFirst.getInStockStatus())){ //在库
            InstockDtlInfo objEInstockDtlInfo = instockDtlDAO.SelectBybatch(objEBInfoFirst.getBatch());
            if(objEInstockDtlInfo == null){
                throw new SystemException("", "批次在库，但物料不在库存中");
            }
            objEInstockDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objEInstockDtlInfo.setBatch(batch);
            objEInstockDtlInfo.setCreator(userName);
            objEInstockDtlInfo.setCreateTime(date);
            objEInstockDtlInfo.setLastModifyMan(null);
            objEInstockDtlInfo.setLastModifyTime(null);
            instockDtlDAO.InsertInstockDtlInfo(objEInstockDtlInfo);

        }else if("01".equals(objEBInfoFirst.getInStockStatus())){ //离库
            if(objEPickBatchInfos != null && objEPickBatchInfos.size() > 0){
                PickBatchInfo objEPickBatchInfo = objEPickBatchInfos.get(0);
                StringBuilder reML = new StringBuilder();
                for(String s : setMV){
                    reML.append(s + "|");
                }
                if(setMV.size() > 0){
                    reML.deleteCharAt(reML.length() - 1);
                }
                objEPickBatchInfo.setGuid(CommonUtils.getRandomNumber());
                objEPickBatchInfo.setBatch(batch);
                objEPickBatchInfo.setReML(reML.toString());
                objEPickBatchInfo.setLastModifyMan(null);
                objEPickBatchInfo.setLastModifyTime(null);
                pickBatchDAO.InsertPickBatch(objEPickBatchInfo);
            }
        }

        //插入新批次
        objEBInfoFirst.setGuid(CommonUtils.getRandomNumber());
        objEBInfoFirst.setBatch(batch);
        objEBInfoFirst.setSupBatch(null);
        objEBInfoFirst.setNum(allNum);
        objEBInfoFirst.setCanNum(allNum);
        objEBInfoFirst.setConsuNum(0f);
        objEBInfoFirst.setbSource("02");
        objEBInfoFirst.setCreator(userName);
        objEBInfoFirst.setCreateTime(date);
        objEBInfoFirst.setLastModifyMan(null);
        objEBInfoFirst.setLastModifyTime(null);
        bdao.InsertBatch(objEBInfoFirst);

        //新增生产流转信息
        if(objEBCirInfo != null){
            objEBCirInfo.setGuid(CommonUtils.getRandomNumber());
            objEBCirInfo.setBatch(batch);
            bCirDAO.InsertBCir(objEBCirInfo);
        }

        //获取打印模板信息
        PrintTInfo objEPrintTInfo = printTDAO.SelectPrintTInfo(argBD01.getPrintTRd());
        if(objEPrintTInfo == null){
            throw new SystemException("","打印模板信息为空");
        }

        //获取打印机信息
        PrinterInfo objEPrinterInfo = printerDAO.SelectByRuid(argBD01.getPrintRd());
        if(objEPrinterInfo == null){
            throw new SystemException("","打印机信息为空");
        }

        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if(objEUnitInfo == null){
            throw new SystemException("", "物料单位为空");
        }

        //将可能需要打印的对象写入map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("MaVerInfo", objEMaVerInfo);
        map.put("UnitInfo", objEUnitInfo);
        map.put("PrintTInfo", objEPrintTInfo);
        map.put("PrinterInfo", objEPrinterInfo);
        map.put("BInfo", objEBInfoFirst);

        //是否打印
        int print_count = 0;
        if("00".equals(argBD01.getIsPrint())){

            print_count = 1;
        }

        //待插入
        PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
        objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
        objEPrintTaskInfo.setReCode(objEBInfoFirst.getBatch());
        objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
        objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
        objEPrintTaskInfo.setBarCode(objEBInfoFirst.getBatch());
        objEPrintTaskInfo.setOrderType("02");
        objEPrintTaskInfo.setBarType("02");
        objEPrintTaskInfo.setPrintCount(print_count);
        objEPrintTaskInfo.setPrintCopy(print_count);
        objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
        objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
        objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
        objEPrintTaskInfo.setCreator(userName);
        objEPrintTaskInfo.setCreateTime(date);

        objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));

        if(print_count == 1){
            hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, objEPrinterInfo.getPrinterSer(), "00");
        }else{
            hisPrintLogIService.PrintTQueue(objEPrintTaskInfo,  objEPrinterInfo.getPrinterSer(), "01");
        }

        return objESaveSUBInfoRes;
    }

    //批次操作（冻结）
    @Override
    public SaveBOptInfoRes AddHold(SaveBOptInfoReqBD00 argBD00) {

        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null){

            BInfo objEBInfo = null;
            AffairInfo objEAffairInfo = null;
            HoldInfo objEHoldInfo = null;

            for(SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos){
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if(objEBInfo == null){
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }

                switch (objEBInfo.getStatus()){
                    /*case "01":
                        throw new SystemException("", batchInfo.getBatch() + "批次处理中不允许冻结");*/
                    case "02":
                        throw new SystemException("", batchInfo.getBatch() + "批次已经冻结");
                    case "03":
                        throw new SystemException("", batchInfo.getBatch() + "批次报废不允许冻结");
                    case "04":
                        throw new SystemException("", batchInfo.getBatch() + "批次完工不允许冻结");
                    default:
                        break;
                }

                //批次事物操作
                objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(objEBInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("09");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                affairDAO.InsertAffairInfo(objEAffairInfo);

                //批次冻结日志
                objEHoldInfo = new HoldInfo();
                objEHoldInfo.setGuid(CommonUtils.getRandomNumber());
                objEHoldInfo.setBatch(objEBInfo.getBatch());
                objEHoldInfo.setReaDes(argBD00.getReaDes());
                objEHoldInfo.setCreator(userName);
                objEHoldInfo.setCreateTime(date);
                holdDAO.InsertHold(objEHoldInfo);

                //修改批次状态
                objEBInfo.setStatus("02");
                objEBInfo.setLastModifyMan(userName);
                objEBInfo.setLastModifyTime(date);
                if(bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0){
                    throw new SystemException("", objEBInfo.getBatch() + "批次冻结失败");
                }
            }
        }

        return objESaveBOptInfoRes;
    }

    //批次操作（解冻）
    @Override
    public SaveBOptInfoRes AddUnHold(SaveBOptInfoReqBD00 argBD00) {

        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null) {

            BInfo objEBInfo = null;
            AffairInfo objEAffairInfo = null;
            UnHoldInfo objEUnHoldInfo = null;

            for (SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos) {
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if (objEBInfo == null) {
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }
                if (!"02".equals(objEBInfo.getStatus())) {
                    throw new SystemException("", batchInfo.getBatch() + "批次没有冻结");
                }

                if("00".equals(objEBInfo.getSpecLFlag())){
                    throw new SystemException("", objEBInfo.getBatch() + "工序留存,不允许解冻!");
                }

                //批次事物操作
                objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(objEBInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("10");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                affairDAO.InsertAffairInfo(objEAffairInfo);

                //批次解冻日志
                objEUnHoldInfo = new UnHoldInfo();
                objEUnHoldInfo.setGuid(CommonUtils.getRandomNumber());
                objEUnHoldInfo.setBatch(objEBInfo.getBatch());
                objEUnHoldInfo.setReaDes(argBD00.getReaDes());
                objEUnHoldInfo.setCreator(userName);
                objEUnHoldInfo.setCreateTime(date);
                unHoldDAO.InsertUnHold(objEUnHoldInfo);

                //修改批次状态
                objEBInfo.setStatus("00");
                objEBInfo.setLastModifyMan(userName);
                objEBInfo.setLastModifyTime(date);
                if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", objEBInfo.getBatch() + "批次解冻失败");
                }
            }
        }

        return objESaveBOptInfoRes;
    }

    //批次操作（不良）
    @Override
    public SaveBOptInfoRes AddReject(SaveBOptInfoReqBD00 argBD00) {

        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null) {

            BInfo objEBInfo = null;
            AffairInfo objEAffairInfo = null;
            RejectInfo objERejectInfo = null;

            for (SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos) {
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if (objEBInfo == null) {
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }

                switch (objEBInfo.getStatus()){
                    case "03":
                        throw new SystemException("", batchInfo.getBatch() + "批次报废不允许标记不良");
                    default:
                        break;
                }

                //批次事物操作
                objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(objEBInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("07");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                affairDAO.InsertAffairInfo(objEAffairInfo);

                //批次不良日志
                objERejectInfo = new RejectInfo();
                objERejectInfo.setGuid(CommonUtils.getRandomNumber());
                objERejectInfo.setBatch(objEBInfo.getBatch());
                objERejectInfo.setReaDes(argBD00.getReaDes());
                objERejectInfo.setNum(batchInfo.getNum());
                objERejectInfo.setCreator(userName);
                objERejectInfo.setCreateTime(date);
                rejectDAO.InsertReject(objERejectInfo);

                if(batchInfo.getNum() < 0){
                    throw new SystemException("", "批次标记不良数量不能小于零");
                }
                if(objEBInfo.getCanNum() < batchInfo.getNum()){
                    throw new SystemException("", "批次标记不良数量超过批次可用数量");
                }
                //修改批次状态
                objEBInfo.setBad("00");
                objEBInfo.setBadNum(objEBInfo.getBadNum() + batchInfo.getNum());
                //objEBInfo.setCanNum(objEBInfo.getCanNum() - batchInfo.getNum());

                if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", objEBInfo.getBatch() + "批次标记不良失败");
                }
            }
        }

        return objESaveBOptInfoRes;
    }

    //批次操作（解除不良）
    @Override
    public SaveBOptInfoRes AddUnReject(SaveBOptInfoReqBD00 argBD00) {
        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null) {
            BInfo objEBInfo = null;

            for (SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos) {
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if (objEBInfo == null) {
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }

                //修改批次状态
                objEBInfo.setBad("01");
                /*objEBInfo.setBadNum(objEBInfo.getCanNum());
                objEBInfo.setCanNum(0f);*/
                objEBInfo.setCanNum(objEBInfo.getCanNum() + objEBInfo.getBadNum());
                objEBInfo.setBadNum(0f);
                objEBInfo.setLastModifyMan(userName);
                objEBInfo.setLastModifyTime(date);

                if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", objEBInfo.getBatch() + "批次解除不良失败");
                }
            }
        }

        return objESaveBOptInfoRes;
    }

    //批次操作（打开）
    @Override
    public SaveBOptInfoRes AddOpen(SaveBOptInfoReqBD00 argBD00) {

        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null) {

            BInfo objEBInfo = null;
            AffairInfo objEAffairInfo = null;
            OpenInfo objEOpenInfo = null;

            for (SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos) {
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if (objEBInfo == null) {
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }
                if (!"04".equals(objEBInfo.getStatus())) {
                    throw new SystemException("", batchInfo.getBatch() + "批次已经是开启状态");
                }
                //批次事物操作
                objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(objEBInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("04");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                affairDAO.InsertAffairInfo(objEAffairInfo);
                //批次打开日志
                objEOpenInfo = new OpenInfo();
                objEOpenInfo.setGuid(CommonUtils.getRandomNumber());
                objEOpenInfo.setBatch(objEBInfo.getBatch());
                objEOpenInfo.setReaDes(argBD00.getReaDes());
                objEOpenInfo.setCreator(userName);
                objEOpenInfo.setCreateTime(date);
                openDAO.InsertOpen(objEOpenInfo);

                //修改批次状态
                objEBInfo.setStatus("00");

                if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", objEBInfo.getBatch() + "批次打开失败");
                }
            }
        }

        return objESaveBOptInfoRes;
    }

    //批次操作（关闭）
    @Override
    public SaveBOptInfoRes AddClose(SaveBOptInfoReqBD00 argBD00) {
        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null) {

            BInfo objEBInfo = null;
            AffairInfo objEAffairInfo = null;
            CloseInfo objECloseInfo = null;

            for (SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos) {
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if (objEBInfo == null) {
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }

                if("00".equals(objEBInfo.getSpecLFlag())){
                    throw new SystemException("", objEBInfo.getBatch() + "工序留存,不允许关闭!");
                }

                switch (objEBInfo.getStatus()){
                    case "01":
                        throw new SystemException("", batchInfo.getBatch() + "批次处理中不允许关闭");
                    case "02":
                        throw new SystemException("", batchInfo.getBatch() + "批次冻结不允许关闭");
                    case "03":
                        throw new SystemException("", batchInfo.getBatch() + "批次报废不允许关闭");
                    case "04":
                        throw new SystemException("", batchInfo.getBatch() + "批次完工不允许关闭");
                    default:
                        break;
                }

                //批次事物操作
                objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(objEBInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("05");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                affairDAO.InsertAffairInfo(objEAffairInfo);
                //批次关闭日志
                objECloseInfo = new CloseInfo();
                objECloseInfo.setGuid(CommonUtils.getRandomNumber());
                objECloseInfo.setBatch(objEBInfo.getBatch());
                objECloseInfo.setReaDes(argBD00.getReaDes());
                objECloseInfo.setCreator(userName);
                objECloseInfo.setCreateTime(date);
                closeDAO.InsertClose(objECloseInfo);

                //修改批次状态
                objEBInfo.setStatus("04");

                if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", objEBInfo.getBatch() + "批次关闭失败");
                }
            }
        }

        return objESaveBOptInfoRes;
    }

    //批次操作（报废原因）
    @Override
    public SaveBOptInfoRes AddScrop(SaveBOptInfoReqBD00 argBD00) {
        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        List<SaveBOptInfoReqBD00BatchInfo> objEBatchInfos = argBD00.getBatchInfo();
        if(objEBatchInfos != null) {

            BInfo objEBInfo = null;
            AffairInfo objEAffairInfo = null;
            ScropInfo objEScropInfo = null;

            for (SaveBOptInfoReqBD00BatchInfo batchInfo : objEBatchInfos) {
                //查询批次
                objEBInfo = bdao.selectBatchInfoByBatch(batchInfo.getBatch());
                if (objEBInfo == null) {
                    throw new SystemException("", batchInfo.getBatch() + "批次不存在");
                }

                if("00".equals(objEBInfo.getSpecLFlag())){
                    throw new SystemException("", objEBInfo.getBatch() + "工序留存,不允许报废!");
                }

                switch (objEBInfo.getStatus()){
                    case "00":
                        throw new SystemException("", batchInfo.getBatch() + "批次待处理不允许报废");
                    case "01":
                        throw new SystemException("", batchInfo.getBatch() + "批次处理中不允许报废");
                    case "03":
                        throw new SystemException("", batchInfo.getBatch() + "批次已经报废");
                    case "04":
                        throw new SystemException("", batchInfo.getBatch() + "批次完工不允许报废");
                    default:
                        break;
                }

                //批次事物操作
                objEAffairInfo = new AffairInfo();
                objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                objEAffairInfo.setBatch(objEBInfo.getBatch());
                objEAffairInfo.setSpecVerGd("");
                objEAffairInfo.setSpecName("");
                objEAffairInfo.setOptType("08");
                objEAffairInfo.setCreator(userName);
                objEAffairInfo.setCreateTime(date);
                affairDAO.InsertAffairInfo(objEAffairInfo);
                //批次报废日志
                objEScropInfo = new ScropInfo();
                objEScropInfo.setGuid(CommonUtils.getRandomNumber());
                objEScropInfo.setBatch(objEBInfo.getBatch());
                objEScropInfo.setReaDes(argBD00.getReaDes());
                objEScropInfo.setCreator(userName);
                objEScropInfo.setCreateTime(date);
                scropDAO.InsertScrop(objEScropInfo);

                //修改批次状态
                objEBInfo.setStatus("03");

                if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                    throw new SystemException("", objEBInfo.getBatch() + "批次报废失败");
                }
            }
        }
        return objESaveBOptInfoRes;
    }

    //批次操作（更改数量）
    @Override
    public SaveBOptInfoRes AddChgQty(SaveBOptInfoReqBD02[] argBD02) {

        SaveBOptInfoRes objESaveBOptInfoRes = new SaveBOptInfoRes();

        String userName = CommonUtils.readUser().getRealName();
        Date date = new Date();

        BInfo objEBInfo = null;
        AffairInfo objEAffairInfo = null;
        ChgQtyInfo objEChgQtyInfo = null;
        for(SaveBOptInfoReqBD02 bd02 : argBD02){
            //查询批次
            objEBInfo = bdao.selectBatchInfoByBatch(bd02.getBatch());
            if (objEBInfo == null) {
                throw new SystemException("", bd02.getBatch() + "批次不存在");
            }
            if(!"00".equals(objEBInfo.getbType())){
                throw new SystemException("", bd02.getBatch() + "批次不是产成品");
            }

            if("00".equals(objEBInfo.getSpecLFlag())){
                throw new SystemException("", objEBInfo.getBatch() + "工序留存,不允许更改数量!");
            }

            switch (objEBInfo.getStatus()){
                case "01":
                    throw new SystemException("", bd02.getBatch() + "批次处理中不允许更改数量");
                case "02":
                    throw new SystemException("", bd02.getBatch() + "批次冻结不允许更改数量");
                case "03":
                    throw new SystemException("", bd02.getBatch() + "批次报废不允许更改数量");
                case "04":
                    throw new SystemException("", bd02.getBatch() + "批次完工不允许更改数量");
                default:
                    break;
            }

            if(bd02.getAfNum() < 0){
                throw new SystemException("", objEBInfo.getBatch() + "批次数量不能小于零");
            }else if(objEBInfo.getNum() < bd02.getAfNum()){
                throw new SystemException("", objEBInfo.getBatch() + "批次数量不能超过最大批次数");
            }else if(bd02.getAfNum() == 0){
                objEBInfo.setCanNum(0f);
                objEBInfo.setConsuNum(objEBInfo.getNum());
                objEBInfo.setStatus("04");
            }else{
                objEBInfo.setCanNum(bd02.getAfNum());
                objEBInfo.setConsuNum(objEBInfo.getNum() - bd02.getAfNum());
            }
            //修改批次状态
            if (bdao.UpdateBatchInfoByRuid(objEBInfo) <= 0) {
                throw new SystemException("", objEBInfo.getBatch() + "批次关闭失败");
            }

            //批次更改数量日志
            objEChgQtyInfo = new ChgQtyInfo();
            objEChgQtyInfo.setGuid(CommonUtils.getRandomNumber());
            objEChgQtyInfo.setBatch(bd02.getBatch());
            objEChgQtyInfo.setBeNum(bd02.getBeNum());
            objEChgQtyInfo.setAfNum(bd02.getAfNum());
            objEChgQtyInfo.setReaDes(bd02.getReaDes());
            objEChgQtyInfo.setCreator(userName);
            objEChgQtyInfo.setCreateTime(date);
            objEChgQtyInfo.setRemark(bd02.getRemark());
            chgQtyDAO.InsertChgQty(objEChgQtyInfo);

            //批次事物操作
            objEAffairInfo = new AffairInfo();
            objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
            objEAffairInfo.setBatch(objEBInfo.getBatch());
            objEAffairInfo.setSpecVerGd("");
            objEAffairInfo.setSpecName("");
            objEAffairInfo.setOptType("11");
            objEAffairInfo.setCreator(userName);
            objEAffairInfo.setCreateTime(date);
            affairDAO.InsertAffairInfo(objEAffairInfo);
        }

        return objESaveBOptInfoRes;
    }

    /**
     * 获取冻结批次列表
     * @param request
     * @return
     */
    @Override
    public PageResult<GetHoldBatchResD> GetAllHoldBatch(BaseRequest<GetHoldBatchReq> request) {
        IPage<GetHoldBatchResD> iPage = bdao.selectHoldBatch(new MyPage(request), request.getData());

        return new PageResult<>(iPage, iPage.getRecords());
    }

    /**
     * 更新入库单单号
     * @param guid
     * @return
     */
    public String updateRK(String guid){
        RkTkInfo objERkTkInfos=rkTkDAO.SelectRKTkInfoByguid(guid);
        //获取左边设置为0
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(10);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(10);

        //获取当前的年月日字符串
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] str= format.format(new Date()).substring(0, 10).split("-");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length; i++){
            sb. append(str[i]);
        }
        objERkTkInfos.setRkCode("RK"+sb.toString()+nf.format(objERkTkInfos.getRuid()));
        if(rkTkDAO.UpdateRKTkInfo(objERkTkInfos)<=0){
            throw new SystemException("xxx","更细入库单位号失败");
        }
        return objERkTkInfos.getRkCode();
    }

    /**
     * 数据转换
     * @param optType
     * @param rkCode
     * @param orderCode
     * @param list
     */
    public void switchData(String optType, String rkCode, String orderCode, List<Map<String, String>> list){

        if(list != null && list.size() > 0){

            StringBuilder builder1 = new StringBuilder();//入库
            StringBuilder builder2 = new StringBuilder();//iqc
            String s = "";

            for(int i=0, length=list.size(); i<length; i++){
                s = JSONObject.fromObject(list.get(i)).toString();
                builder1.append(s + ",");
                if(!"00".equals(list.get(i).get("IsExem"))){
                    builder2.append(s + ",");
                }
            }

            s = "{\"OrderCode\":\"" + orderCode
                    + "\",\"MOCode\":\"" + rkCode
                    + "\",\"OptType\":\"" + optType
                    + "\",\"OrderInfo\":[";

            builder1.deleteCharAt(builder1.length() - 1);

            searchP("00", s + builder1.toString() + "]}");

            //判断iqc存不存在
            if(!"".equals(builder2.toString())){
                builder2.deleteCharAt(builder2.length() - 1);
                searchP("10", s + builder2.toString() + "]}");
            }
        }
    }

    /**
     * 查找原材料入库权限的人、或者iqc权限的人,并发送到队列中去
     * @param pvflag
     * @param data
     */
    public void searchP(String pvflag, String data){
        List<InitDataField> objEInitDataFields = new ArrayList<InitDataField>();
        InitDataField objEInitDataField = new InitDataField();
        objEInitDataField.setFieldName("PVFlag");
        objEInitDataField.setFieldOpt("=");
        objEInitDataField.setFieldVal(pvflag);

        //查询拥有改权限的角色
        List<ScanPVInfo> objEScanPVInfos = baseIService.QALLBaseInfo(ScanPVDAO.class, "SelectAllScanPV", null, null);
        if(objEScanPVInfos != null && objEScanPVInfos.size() > 0){

            Set<String> set = new HashSet<String>();

            //查询拥有这权限的用户
            for(int i=0, length=objEScanPVInfos.size(); i<length; i++){
                set.addAll(userRoleDAO.SelectByRoleGd(objEScanPVInfos.get(i).getRoleGd()));
            }

            /*ActiveMQTopic activeMQTopic = null;

            for(String s : set){
                //查询用户
                UserInfo userInfo = userDAO.SelectUserRd(s);
                if(userInfo != null){
                    //发送通知
                    activeMQTopic = new ActiveMQTopic(userInfo.getUserName());
                    ActiveMQUtil.sendMessageT(jmsTemplate, activeMQTopic, data);
                }
            }*/
        }
    }

    /**
     * 根据数据打印条码(有工单)
     * @param map
     * @param objEBInfos
     * @param print_count
     * @param objEPrinterInfo
     * @param objEPrintTInfo
     * @param objEMaVerInfo
     */
    public void printBar(Map<String, Object> map, List<BInfo> objEBInfos, int print_count
            , PrinterInfo objEPrinterInfo, PrintTInfo objEPrintTInfo, MaVerInfo objEMaVerInfo, String orderType, String woCode){

        for(int i=0;i<objEBInfos.size();i++){

            map.put("BInfo", objEBInfos.get(i));

            PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
            objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
            objEPrintTaskInfo.setReCode(woCode);
            objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            objEPrintTaskInfo.setBarCode(objEBInfos.get(i).getBatch());
            objEPrintTaskInfo.setOrderType(orderType);
            objEPrintTaskInfo.setBarType("02");
            objEPrintTaskInfo.setPrintCount(print_count);
            objEPrintTaskInfo.setPrintCopy(print_count);
            objEPrintTaskInfo.setCreator(CommonUtils.readUser().getRealName());
            objEPrintTaskInfo.setCreateTime(new Date());

            if(objEPrinterInfo != null){
                objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            }
            if(objEPrintTInfo != null){
                objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));
            }else{
                objEPrintTaskInfo.setPrintTGd("");
                objEPrintTaskInfo.setpTFileName("");
                objEPrintTaskInfo.setPrintData("");
            }

            if(objEPrinterInfo != null && objEPrintTInfo != null) {
                if (print_count > 0) {
                    hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, objEPrinterInfo.getPrinterSer(), "00");
                } else {
                    hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, objEPrinterInfo.getPrinterSer(), "01");
                }
            }
        }
    }


    /**
     * 根据数据打印条码(无工单)
     * @param map
     * @param objEBInfos
     * @param print_count
     * @param objEPrinterInfo
     * @param objEPrintTInfo
     * @param objEMaVerInfo
     */
    public void printBar2(Map<String, Object> map, List<WmsMaterialsBInfo> objEBInfos, int print_count
            , PrinterInfo objEPrinterInfo, PrintTInfo objEPrintTInfo, MaVerInfo objEMaVerInfo, String orderType){

        for(int i=0;i<objEBInfos.size();i++){

            map.put("WmsMaterialsBInfo", objEBInfos.get(i));

            PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
            objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
            objEPrintTaskInfo.setReCode(objEBInfos.get(i).getBatch());
            objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            objEPrintTaskInfo.setBarCode(objEBInfos.get(i).getBatch());
            objEPrintTaskInfo.setOrderType(orderType);
            objEPrintTaskInfo.setBarType("04");
            objEPrintTaskInfo.setPrintCount(print_count);
            objEPrintTaskInfo.setPrintCopy(print_count);
            objEPrintTaskInfo.setCreator(CommonUtils.readUser().getRealName());
            objEPrintTaskInfo.setCreateTime(new Date());

            if(objEPrinterInfo != null){
                objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            }
            if(objEPrintTInfo != null){
                objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
                objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
                objEPrintTaskInfo.setPrintData(hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid()));
            }else{
                objEPrintTaskInfo.setPrintTGd("");
                objEPrintTaskInfo.setpTFileName("");
                objEPrintTaskInfo.setPrintData("");
            }

            if(objEPrinterInfo != null && objEPrintTInfo != null) {
                if (print_count > 0) {
                    hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, objEPrinterInfo.getPrinterSer(), "00");
                } else {
                    hisPrintLogIService.PrintTQueue(objEPrintTaskInfo, objEPrinterInfo.getPrinterSer(), "01");
                }
            }
        }
    }

    /**
     * 比较批次属性是否相同
     * @param bInfo1
     * @param bInfo2
     * @return
     */
    public boolean batchEqauls(BInfo bInfo1, BInfo bInfo2){

        if(!"00".equals(bInfo1.getWoSource())) {
            //是否同工单
            if (!bInfo1.getWoGd().equals(bInfo2.getWoGd())) {
                throw new SystemException("", bInfo2.getBatch() + "不与其他批次同工单");
            }
        }
        //是否同物料
        if(!bInfo1.getMaVerGd().equals(bInfo2.getMaVerGd())){
            throw new SystemException("", bInfo2.getBatch() + "不与其他批次同物料");
        }
        //是否同不良
        if(!bInfo1.getBad().equals(bInfo2.getBad())){
            throw new SystemException("", bInfo2.getBatch() + "不与其他批次同不良");
        }
        //是否同状态
        if(!bInfo1.getStatus().equals(bInfo2.getStatus())){
            throw new SystemException("", bInfo2.getBatch() + "不与其他批次同状态");
        }
        //是否同在库、离库
        if(!bInfo1.getInStockStatus().equals(bInfo2.getInStockStatus())){
            throw new SystemException("", bInfo2.getBatch() + "不与其他批次同在库、离库");
        }

        return true;
    }

    /**
     * 生成批次序号末尾标识
     * @param i
     * @return
     */
    private String batchTail(int i){
        if(i < 10){
            return String.format("00%d", i);
        }else if(i < 100){
            return String.format("0%d", i);
        }

        return String.valueOf(i);
    }
}
