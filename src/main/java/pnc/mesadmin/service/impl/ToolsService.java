package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoReqBD00;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoRes;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoResB;
import pnc.mesadmin.dto.SaveImportInfo.SaveImportInfoResD;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.SNIService;
import pnc.mesadmin.service.ToolsIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class ToolsService implements ToolsIService {

    @Resource
    private InstockDAO instockDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private InstockDtlDAO instockDtlDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private PDDtlDAO pdDtlDAO;

    @Resource
    private PDTkDAO pdTkDAO;

    @Resource
    private LocationDAO locationDAO;

    @Resource
    private SerialRuleDAO serialRuleDAO;

    @Resource
    private SNIService sniService;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private PrintTaskDAO printTaskDAO;

    @Resource
    private WFDAO wfdao;

    //导入库存数据
    @Override
    public SaveImportInfoRes AddSaveImportInfoRes(SaveImportInfoReqBD00[] argBD00) throws SystemException {
        SaveImportInfoRes saveImportInfoRes = new SaveImportInfoRes();

        String userName = CommonUtils.readUser().getRealName(); //当前用户
        Date date = new Date(); //当前时间

        InstockInfo objEInstockInfo = null; //库存信息

        InstockDtlInfo objEInstockDtlInfo = null; //库存明细

        BInfo objEBInfo = null; //批次
        MaterialInfo objEMaterialInfo = null; //物料信息
        MaVerInfo objEMaVerInfo = null; //物料版本信息
        UnitInfo objEUnitInfo = null; //单位信息

        Map<String, PDTkInfo> map = new HashMap<String, PDTkInfo>(); //存储盘点任务单
        PDTkInfo objEPdTkInfo = null; //盘点任务单
        PDDtlInfo objEPdDtlInfo = null; //盘点明细
        StoreInfo objEStoreInfo = null; //仓库信息
        SerialRuleInfo objESerialRuleInfo = null;
        AffairInfo objEAffairInfo = null;
        PrintTaskInfo objEPrintTaskInfo = null;
        WFInfo objEWFInfo = null;

        int count = 1;
        //预校验excel数据出现 同一批次不同物料的验证
   /*     Map<String,String> validation=new HashMap<String,String>();
        Set<String> validation1=new HashSet<String>();
        for (SaveImportInfoReqBD00 obj : argBD00) {
            validation.put(obj.getMaCode(), obj.getBatch());
            validation1.add(obj.getBatch());
        }
        if(validation.size()!=validation1.size()){
            throw new SystemException("xxx", "导入的Excel文件失败！不允许同一批次不同物料");
        }*/

        for (SaveImportInfoReqBD00 obj : argBD00) {
            count++;
            //判断数量是否为空
            if(obj.getNum() == null){
                throw new SystemException("MG_MES8001113030002F", "导入的Excel文件库存数据:"+"第"+count+"行数量为空！");
            }

            //判断物料代码是否为空
            if("".equals(obj.getMaName())){
                throw new SystemException("MG_MES8001113030002F", "导入的Excel文件库存数据:"+"第"+count+"行物料名称为空！");
            }

            //判断物料批次是否为空
            /*if("".equals(obj.getBatch())){
                throw new SystemException("MG_MES8001113030002F", "导入的Excel文件库存数据:"+"第"+count+"行批次号为空！");
            }*/

            //判断仓库是否为空
            if("".equals(obj.getStoreName())){
                throw new SystemException("MG_MES8001113030002F", "导入的Excel文件库存数据:"+"第"+count+"行仓库名称为空！");
            }

            //查询物料
            objEMaterialInfo = materialDAO.SelectByMaCode(obj.getMaCode());
            if(objEMaterialInfo == null){
                throw new SystemException("", "导入的Excel文件库存数据:"+"第"+count+"行物料信息不存在！");
            }
            //查询物料默认版本
            objEMaVerInfo = maVerDAO.SelectMaverInfo(objEMaterialInfo.getVerGd());
            if(objEMaVerInfo == null){
                throw new SystemException("", "导入的Excel文件库存数据:"+"第"+count+"行物料信息不存在！");
            }
            //查询单位
            objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
            if(objEUnitInfo == null){
                throw new SystemException("", "导入的Excel文件库存数据:"+"第"+count+"行物料单位不存在！");
            }

            //查询批次
            objEBInfo = bdao.selectBatchInfoByBatch(obj.getBatch());

            boolean isBatch = true;
            if("01".equals(objEMaVerInfo.getIsBatch()) && objEBInfo == null){
                isBatch = false;
            }else {
                if (objEBInfo == null) {
                    //如果批次不存在，则新增批次
                    String batch = obj.getBatch().trim();
                    if ("".equals(batch)) {
                        objESerialRuleInfo = serialRuleDAO.SelectSerialRuleInfoByguid(objEMaVerInfo.getSerialRuleGd());
                        if (objESerialRuleInfo == null) {
                            throw new SystemException("", "导入的Excel文件库存数据:" + "第" + count + "行序号管理信息为空");
                        }
                        batch = sniService.GetCreateSR(objESerialRuleInfo);
                    }

                    objEBInfo = new BInfo();
                    objEBInfo.setGuid(CommonUtils.getRandomNumber());
                    objEBInfo.setWoSource("00");
                    objEBInfo.setWoGd("");
                    objEBInfo.setWoDlGd("");
                    if ("00".equals(objEMaterialInfo.getMaterialType()) || "01".equals(objEMaterialInfo.getMaterialType())) {
                        objEBInfo.setbType("00");
                    } else {
                        objEBInfo.setbType("01");
                    }
                    if ("01".equals(objEMaVerInfo.getIsBatch())) {
                        objEBInfo.setIsBarBatch("00");
                    }
                    objEBInfo.setMaVerGd(objEMaterialInfo.getVerGd());
                    objEBInfo.setBatch(batch);
                    objEBInfo.setSupBatch("");
                    objEBInfo.setNum(obj.getNum());
                    objEBInfo.setCanNum(obj.getNum());
                    objEBInfo.setConsuNum(0f);
                    if (objEUnitInfo != null) {
                        objEBInfo.setUnitGd(objEUnitInfo.getGuid());
                    }
                    objEBInfo.setSpecVerGd("");
                    objEBInfo.setbSource("02");
                    objEBInfo.setbLName("");
                    objEBInfo.setPrintTGd("");
                    objEBInfo.setjStartDate(date);
                    objEBInfo.setsStartDate(date);
                    objEBInfo.setBad("01");
                    if ("00".equals(objEMaVerInfo.getIsExem())) {
                        objEBInfo.setiQCStatus("01");
                    } else {
                        objEBInfo.setiQCStatus("02");
                    }
                    objEBInfo.setCkResult("00");
                    objEBInfo.setStatus("04");
                    objEBInfo.setInStockStatus("02");
                    objEBInfo.setwFStatus("02");
                    objEBInfo.setCreator(userName);
                    objEBInfo.setCreateTime(date);

                    bdao.InsertBatch(objEBInfo);

                    //批次事物表
                    objEAffairInfo = new AffairInfo();
                    objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
                    objEAffairInfo.setBatch(batch);
                    objEAffairInfo.setOptType("14");
                    objEAffairInfo.setCreator(userName);
                    objEAffairInfo.setCreateTime(date);
                    affairDAO.InsertAffairInfo(objEAffairInfo);

                } else {
                    InstockDtlInfo instockDtlInfo = instockDtlDAO.SelectBybatch(objEBInfo.getBatch());
                    if (instockDtlInfo != null) {
                        objEStoreInfo = storeDAO.SelectBystoreName(obj.getStoreName());
                        if (objEStoreInfo == null) {
                            throw new SystemException("", "导入的Excel文件库存数据:" + "第" + count + "仓库名称不存在！");
                        }
                        if (!instockDtlInfo.getStoreGd().equals(objEStoreInfo.getGuid())) {
                            throw new SystemException("", "导入的Excel文件库存数据:" + "第" + count + "批次已经在其他仓库了！");
                        }
                    }
                    /*//查询物料默认版本
                    objEMaVerInfo = maVerDAO.SelectMaverInfo(objEBInfo.getMaVerGd());
                    if(objEMaVerInfo == null){
                        throw new SystemException("", "导入的Excel文件库存数据:"+"第"+count+"行物料信息不存在！");
                    }*/
                }
            }

            //查询盘点单
            if(map.containsKey(obj.getStoreName())){
                objEPdTkInfo = map.get(obj.getStoreName());
            }else{
                //查询仓库
                objEStoreInfo = storeDAO.SelectBystoreName(obj.getStoreName());
                if(objEStoreInfo == null){
                    throw new SystemException("", "导入的Excel文件库存数据:"+"第"+count+"行仓库名称不存在！");
                }
                //新建盘点单
                DateFormat format = new SimpleDateFormat("yyyyMMdd");
                String time = format.format(date);
                objEPdTkInfo = new PDTkInfo();
                objEPdTkInfo.setGuid(CommonUtils.getRandomNumber());
                objEPdTkInfo.setStoreGd(objEStoreInfo.getGuid());
                objEPdTkInfo.setpDCode("PD" + time + "0000000000");
                objEPdTkInfo.setExStatus("02");//执行状态00，01，02，03
                objEPdTkInfo.setExecor(userName);
                objEPdTkInfo.setExecTime(date);
                objEPdTkInfo.setFinishor(userName);
                objEPdTkInfo.setFinishTime(date);
                objEPdTkInfo.setCheckor(userName);
                objEPdTkInfo.setCheckTime(date);
                objEPdTkInfo.setCreator(userName);
                objEPdTkInfo.setCreateTime(date);
                pdTkDAO.InsertPDTkInfo(objEPdTkInfo);

                //更新盘点单号
                objEPdTkInfo = pdTkDAO.SelectByguid(objEPdTkInfo.getGuid());
                if (objEPdTkInfo == null) {
                    throw new SystemException("MG_MES8001111010001F", "新增盘点单信息为空！");
                }
                String str = String.format("%010d", objEPdTkInfo.getRuid());
                String str1 = "PD" + time + str;
                objEPdTkInfo.setpDCode(str1);
                //更新盘点单号
                if (pdTkDAO.UpdatePDTkInfo(objEPdTkInfo) <= 0) {
                    throw new SystemException("MG_MES8001113030002F", "新增盘点单信息为空！");
                }

                map.put(obj.getStoreName(), objEPdTkInfo);
            }

            //根据库位代码查询库位信息
            LocationInfo objELocationInfo = locationDAO.SelectlCode(obj.getLCode());

            String strLGd = objELocationInfo==null?"":objELocationInfo.getGuid();
            String strLName = objELocationInfo==null?"":objELocationInfo.getlName();

            //插入盘点明细
            objEPdDtlInfo = new PDDtlInfo();
            objEPdDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objEPdDtlInfo.setpDTkGd(objEPdTkInfo.getGuid());
            if(isBatch) {
                objEPdDtlInfo.setBarType("02");
                objEPdDtlInfo.setBatch(objEBInfo.getBatch());
                objEPdDtlInfo.setBarCode(objEBInfo.getBatch());
            }else{
                objEPdDtlInfo.setBarType("03");
            }

            objEPdDtlInfo.setStoreGd(objEStoreInfo.getGuid());
            objEPdDtlInfo.setStoreName(objEStoreInfo.getStoreName());
            objEPdDtlInfo.setLocationGd(strLGd);
            objEPdDtlInfo.setlName(strLName);
            objEPdDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
            objEPdDtlInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            //objEPdDtlInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            objEPdDtlInfo.setMaterialName(obj.getMaName());
            objEPdDtlInfo.setNum(obj.getNum());
            objEPdDtlInfo.setUnitName(objEUnitInfo.getUnitName());
            objEPdDtlInfo.setProductDate(date);
            objEPdDtlInfo.setCreator(userName);
            objEPdDtlInfo.setCreateTime(date);

            pdDtlDAO.InsertPDDtlInfo(objEPdDtlInfo);

            //查询库存信息
            objEInstockInfo = instockDAO.SelectBymaterialCode(objEMaVerInfo.getMaterialCode(), objEStoreInfo.getGuid());
            if(objEInstockInfo == null) {
                objEInstockInfo = new InstockInfo();
                objEInstockInfo.setGuid(CommonUtils.getRandomNumber());
                objEInstockInfo.setMaVerGd(objEMaVerInfo.getGuid());
                objEInstockInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                objEInstockInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                objEInstockInfo.setVersion(objEMaVerInfo.getVersion());
                objEInstockInfo.setMaterialType(objEMaVerInfo.getMaterialType());
                objEInstockInfo.setStoreGd(objEStoreInfo.getGuid());
                objEInstockInfo.setNum(0f);
                objEInstockInfo.setCreator(userName);
                objEInstockInfo.setCreateTime(date);
                instockDAO.InsertInStock(objEInstockInfo);
            }

            if(isBatch) {
                //判断批次状态
                if ("00".equals(objEBInfo.getInStockStatus())) {
                    //在库
                    //更改库存数量
                    BigDecimal bigNum1=new BigDecimal(objEInstockInfo.getNum());
                    BigDecimal bigNum2=new BigDecimal(obj.getNum());
                    BigDecimal bigNum3=new BigDecimal(objEBInfo.getNum());
                    BigDecimal bigNumA=bigNum1.add(bigNum2);
                    BigDecimal bigNumS=bigNumA.subtract(bigNum3);
                 //   objEInstockInfo.setNum(objEInstockInfo.getNum() + obj.getNum() - objEBInfo.getCanNum());
                    objEInstockInfo.setNum(bigNumS.floatValue());
                    objEBInfo.setCanNum(obj.getNum());

                    //修改库存明细信息
                    objEInstockDtlInfo = instockDtlDAO.SelectBybatch(objEBInfo.getBatch());
                    if (objEInstockDtlInfo == null) {
                        throw new SystemException("", objEBInfo.getBatch() + "批次库存明细不存在");
                    }
                    objEInstockDtlInfo.setLocationGd(strLGd);
                    if (instockDtlDAO.UpdateInstockDtlInfo(objEInstockDtlInfo) <= 0) {
                        throw new SystemException("", objEBInfo.getBatch() + "批次修改库存明细失败");
                    }
                } else if ("02".equals(objEBInfo.getInStockStatus())) {
                    //未设置
                    //更改批次状态
                    objEBInfo.setInStockStatus("00");
                    objEBInfo.setStatus("04");
                    objEBInfo.setCanNum(obj.getNum());

                    //新增库存明细
                    objEInstockDtlInfo = new InstockDtlInfo();
                    objEInstockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    objEInstockDtlInfo.setInsGd(objEInstockInfo.getGuid());
                    //objEInstockDtlInfo.setOrderCode(objEPdTkInfo.getpDCode());
                    //objEInstockDtlInfo.setOrderType("01");
                    objEInstockDtlInfo.setNum(0f);
                    objEInstockDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                    objEInstockDtlInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEInstockDtlInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEInstockDtlInfo.setBatch(objEBInfo.getBatch());
                    objEInstockDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                    objEInstockDtlInfo.setLocationGd(strLGd);
                    objEInstockDtlInfo.setProductDate(date);
                    objEInstockDtlInfo.setExpireDate(null);
                    objEInstockDtlInfo.setUnitName(objEUnitInfo.getUnitName());
                    objEInstockDtlInfo.setCreator(userName);
                    objEInstockDtlInfo.setCreateTime(date);

                    instockDtlDAO.InsertInstockDtlInfo(objEInstockDtlInfo);

                    //更改库存数量
                    BigDecimal bigNum1=new BigDecimal(objEInstockInfo.getNum());
                    BigDecimal bigNum2=new BigDecimal(obj.getNum());
                    BigDecimal bigNum3=bigNum1.add(bigNum2);
                   // objEInstockInfo.setNum(objEInstockInfo.getNum() + obj.getNum());
                    objEInstockInfo.setNum(bigNum3.floatValue());
                    //instockDAO.UpdateInStockInfoNum(objEInstockInfo);
                }
            }else{
                //非批次
                //根据物料GD、仓库GD、空批次、库位GD查询库存明细
                objEInstockDtlInfo = instockDtlDAO.SelectByMaVerStoreBatchLocation(objEMaVerInfo.getGuid(),
                        objEStoreInfo.getGuid(), strLGd);
                if(objEInstockDtlInfo == null){
                    //新增库存明细
                    objEInstockDtlInfo = new InstockDtlInfo();
                    objEInstockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    objEInstockDtlInfo.setInsGd(objEInstockInfo.getGuid());
                    objEInstockDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                    objEInstockDtlInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objEInstockDtlInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objEInstockDtlInfo.setBatch("");
                    objEInstockDtlInfo.setNum(obj.getNum());
                    objEInstockDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                    objEInstockDtlInfo.setLocationGd(strLGd);
                    objEInstockDtlInfo.setProductDate(date);
                    objEInstockDtlInfo.setExpireDate(null);
                    objEInstockDtlInfo.setUnitName(objEUnitInfo.getUnitName());
                    objEInstockDtlInfo.setCreator(userName);
                    objEInstockDtlInfo.setCreateTime(date);

                    instockDtlDAO.InsertInstockDtlInfo(objEInstockDtlInfo);

                    //更改库存数量
                    BigDecimal bigNum1=new BigDecimal(objEInstockInfo.getNum());
                    BigDecimal bigNum2=new BigDecimal(obj.getNum());
                    BigDecimal bigNum3=bigNum1.add(bigNum2);
                    //objEInstockInfo.setNum(objEInstockInfo.getNum() + obj.getNum());
                    objEInstockInfo.setNum(bigNum3.floatValue());
                }else{
                    //更改库存数量
                    BigDecimal bigNum1=new BigDecimal(objEInstockInfo.getNum());
                    BigDecimal bigNum2=new BigDecimal(objEInstockDtlInfo.getNum());
                    BigDecimal bigNumS=bigNum1.subtract(bigNum2);
                    BigDecimal bigNum3=new BigDecimal(obj.getNum());
                    BigDecimal bigNumA=bigNumS.add(bigNum3);
                    //objEInstockInfo.setNum(objEInstockInfo.getNum() - objEInstockDtlInfo.getNum() + obj.getNum());
                    objEInstockInfo.setNum(bigNumA.floatValue());
                    objEInstockDtlInfo.setNum(obj.getNum());
                    if (instockDtlDAO.UpdateInstockDtlInfo(objEInstockDtlInfo) <= 0) {
                        throw new SystemException("", objEMaVerInfo.getMaterialName() + "物料修改库存明细失败");
                    }
                }
            }

            objEInstockInfo.setLastModifyMan(userName);
            objEInstockInfo.setLastModifyTime(date);
            instockDAO.UpdateInStockInfoNum(objEInstockInfo);

            //打印任务信息
            objEPrintTaskInfo = new PrintTaskInfo();
            objEPrintTaskInfo.setGuid(CommonUtils.getRandomNumber());
            objEPrintTaskInfo.setpTCode("PT" + objEPrintTaskInfo.getGuid());
            objEPrintTaskInfo.setReCode(objEPdTkInfo.getpDCode());
            objEPrintTaskInfo.setMaterialCode(objEMaterialInfo.getMaterialCode());
            objEPrintTaskInfo.setMaterialName(objEMaterialInfo.getMaterialName());
            if(isBatch) {
                objEPrintTaskInfo.setBarCode(objEBInfo.getBatch());
                objEPrintTaskInfo.setBarType("02");
                //修改批次
                bdao.UpdateBatchInfoByGuid(objEBInfo);
            }else{
                objEPrintTaskInfo.setBarCode(objEMaVerInfo.getMaBarCode());
                objEPrintTaskInfo.setBarType("03");
            }
            objEPrintTaskInfo.setOrderType("03");
            objEPrintTaskInfo.setPrintCount(0);
            objEPrintTaskInfo.setPrintCopy(0);
            objEPrintTaskInfo.setPrinterName("");
            objEPrintTaskInfo.setPrintTGd("");
            objEPrintTaskInfo.setpTFileName("");
            objEPrintTaskInfo.setPrintData("");
            objEPrintTaskInfo.setCreator(userName);
            objEPrintTaskInfo.setCreateTime(date);
            if(printTaskDAO.SelectByBarCodeType(objEPrintTaskInfo.getBarCode(), objEPrintTaskInfo.getPrintTGd(), objEPrintTaskInfo.getBarType()) == null) {
                printTaskDAO.InsertPrintTaskInfo(objEPrintTaskInfo);
            }
        }

        SaveImportInfoResB saveImportInfoResB = new SaveImportInfoResB();
        List<SaveImportInfoResD> importInfoResDS = new ArrayList<SaveImportInfoResD>();
        SaveImportInfoResD importInfoResD = null;

        //记录盘点单号
        for(Map.Entry<String, PDTkInfo> entry : map.entrySet()){
            importInfoResD = new SaveImportInfoResD();
            importInfoResD.setPDCode(entry.getValue().getpDCode());
            importInfoResDS.add(importInfoResD);
        }

        saveImportInfoResB.setData(importInfoResDS);
        saveImportInfoRes.setBody(saveImportInfoResB);

        return saveImportInfoRes;
    }
}
