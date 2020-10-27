package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWFInfo;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWSDevGInfo;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWSpecInfo;
import pnc.mesadmin.dto.GetWOInfo.*;
import pnc.mesadmin.dto.SaveUDMaterailDto.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.dto.GetAllWOBInfo.GetWOInfoResDByUDMaterial;
import pnc.mesadmin.service.UDMaterialPlusService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description 装卸物料
 * @Author yin.yang
 * @Date 2019/12/19
 * @Param
 * @Return
 * @Exception
 */
@Transactional
@Service
public class UDMaterialPlusServiceImpl implements UDMaterialPlusService {

    @Resource
    private WODAO wodao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private BomVerDAO bomVerDAO;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    @Resource
    private BomReMaterialDAO bomReMaterialDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private DevDAO devDAO;

    @Resource
    private UDMaterialLogDao udMaterialLogDao;
    @Resource
    private ULineMaterialInfoDao uLineMaterialInfoDao;
    @Resource
    private ULineMaterialDetailInfoDao uLineMaterialDetailInfoDao;

    @Resource
    private PackSpecificationDAO packSpecificationDAO;

    @Resource
    private WoTypeDAO woTypeDAO;

    @Resource
    private OrderLineDAO orderLineDAO;

    @Resource
    private LineDao lineDao;
    @Resource
    private WFVerDAO wfVerDAO;
    @Resource
    private WFSpecDAO wfSpecDAO;
    @Resource
    private UrgencyDAO urgencyDAO;
    @Resource
    private DevGpDtlDAO devGpDtlDAO;
    @Resource
    private DevStateDAO devStateDAO;

    @Resource
    private WmsMaterialsBDAO wmsMaterialsBDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;
    @Resource
    private GConfigIService gConfigIService;
    @Resource
    private SplitBatchDAO splitBatchDAO;

    @Resource
    private PrintTaskDAO printTaskDAO;

    @Resource
    private PrintTDAO printTDAO;


    @Override
    public void AddSaveUDMaterial(SaveUDMaterailRequest request) {
        //todo 检验基本信息
        if (StringUtils.isBlank(request.getBatch()))
            throw new SystemException("EEEE", "物料批次号不能为空！");
        //检验工单信息
        if (request.getWoRd() == null)
            throw new SystemException("EEEE", "工单号不能为空！");
        //检验工序信息
        if (request.getSpecRd() == null)
            throw new SystemException("EEEE", "工序信息有误！");
        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(request.getSpecRd());
        if (specVerInfo == null)
            throw new SystemException("EEEE", "工序信息有误！");
        //检验设备信息
        DevInfo devInfo = new DevInfo();
        if (request.getLuType().equals("01")) {
            devInfo = devDAO.selectById(request.getDevRd());
            if (devInfo == null)
                throw new SystemException("EEEE", "设备信息有误！");
        }
        //检验物料批次信息
        WmsMaterialsBInfo materialsBInfo = wmsMaterialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>()
                .eq("batch", request.getBatch()));
        if (materialsBInfo == null) {
            if (specVerInfo.getSpecName().equals("电池摆片")) {
                materialsBInfo = wmsMaterialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>()
                        .eq("batch", request.getBatch()));
            } else {
                throw new SystemException("EEEE", request.getBatch() + "物料条码批次号有误，在MES中未找到");
            }
        }
        //查询当前工单绑定产品BOM消耗物料
        WoInfo woInfo = wodao.SelectWoInfoByruid(request.getWoRd());
        if (woInfo == null)
            throw new SystemException("EEEE", "工单信息有误！");
        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(materialsBInfo.getMaVerGd());
        if (maVerInfo == null)
            throw new SystemException("EEEE", "物料条码批次号有误！");
        UnitInfo unitInfo = unitDAO.SelectByGuid(maVerInfo.getUnitGd());

        //定义是否替代料
        Boolean replaceMaterial = false;
        //检验BOM
        if ("01".equals(woInfo.getdSource())) { //自建工单
            //check BOM
            BomVerInfo bomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
            if (bomVerInfo == null)
                throw new SystemException("", "当前自建工单" + woInfo.getWoCode() + "数据异常，追溯不到绑定BOM信息");
            //根据物料标识查询BOM用料表
            List<BomMaterialInfo> bomMaterialInfoList = bomMaterialDAO.SelectByBomVerGdAndMaVerGd(bomVerInfo.getGuid(), materialsBInfo.getMaVerGd(), specVerInfo.getGuid());
            List<BomReMaterialInfo> bomReMaterialInfoList = new ArrayList<>();
            if (bomMaterialInfoList == null || bomMaterialInfoList.size() < 1) {
                //查询替代物料
                bomReMaterialInfoList = bomReMaterialDAO.SelectByMaVerGdAndBomVerGd(bomVerInfo.getGuid(), materialsBInfo.getMaVerGd(), specVerInfo.getGuid());
                if (bomReMaterialInfoList == null || bomReMaterialInfoList.size() < 1)
                    throw new SystemException("", "工序：【" + specVerInfo.getSpecName() + "】当前物料代码：【" + maVerInfo.getMaterialCode() + "】物料名称：【" + maVerInfo.getMaterialName() + "】不在BOM设定的范围中");
                else {
                    replaceMaterial = true;
                }
            }
        } else if ("00".equals(woInfo.getdSource())) { //外部来源工单

        }

        //查询产线执行物料信息
        ULineMaterialInfo uLineMaterialInfo = uLineMaterialInfoDao.selectOne(new QueryWrapper<ULineMaterialInfo>()
                .eq("woCode", woInfo.getWoCode())
                .eq("specGuid", specVerInfo.getGuid()));
        //定义errorName;
        String statusErrorName = materialsBInfo.getStatus().equals("00") ? "待执行" :
                materialsBInfo.getStatus().equals("01") ? "生产使用中冻结" :
                        materialsBInfo.getStatus().equals("02") ? "出库审核冻结" :
                                materialsBInfo.getStatus().equals("03") ? "入库审核冻结" :
                                        materialsBInfo.getStatus().equals("04") ? "消耗完作废" : "未知";
        //定义log实体
        UDMaterialLogInfo udMaterialLogInfo = new UDMaterialLogInfo();
        if (request.getType().equals("00")) {//todo 上料逻辑
            if (!"00".equals(materialsBInfo.getStatus()))
                throw new SystemException("EEEE", request.getBatch() + "当前物料批次状态:{" + statusErrorName + "或者原损制损}不允许进行上料操作！");
            if (materialsBInfo.getCanNum().floatValue() <= 0) {
                throw new SystemException("EEEE", "当前物料可用量不足！");
            }
            //检验物料批次是否已经进行过上料操作
            if (uLineMaterialInfo != null) {
                ULineMaterialDetailInfo uLineMaterialDetailInfo = new ULineMaterialDetailInfo();
                uLineMaterialDetailInfo.setUlmGuid(uLineMaterialInfo.getGuid());
                uLineMaterialDetailInfo.setLotNo(request.getBatch());
                uLineMaterialDetailInfo.setStatus("00");
                uLineMaterialDetailInfo = uLineMaterialDetailInfoDao.selectOne(new QueryWrapper<ULineMaterialDetailInfo>()
                        .eq("ulmGuid", uLineMaterialInfo.getGuid())
                        .eq("lotNo", request.getBatch())
                        .eq("status", "00"));
                if (uLineMaterialDetailInfo != null)
                    throw new SystemException("EEEE", "当前物料批次：" + request.getBatch() + "已经在当前工单当前工序进行过上料操作请勿重复操作！");
                else {
                    List<ULineMaterialDetailInfo> uLineMaterialDetailInfoList = uLineMaterialDetailInfoDao.selectList(new QueryWrapper<ULineMaterialDetailInfo>()
                            .eq("lotNo", request.getBatch())
                            .eq("status", "00"));
                    if (uLineMaterialDetailInfoList.size() > 0)
                        throw new SystemException("EEEE", "当前物料批次：" + request.getBatch() + "已经在其他工单其他工序进行过上料操作请勿重复操作！");
                }
                //产线执行物料信息
                uLineMaterialInfo.setWarningVaule(request.getWarningVaule() == null ? BigDecimal.ZERO : new BigDecimal(request.getWarningVaule().toString()));
                uLineMaterialInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                uLineMaterialInfo.setLastModifyTime(new Date());
                uLineMaterialInfoDao.updateById(uLineMaterialInfo);
            } else { //新增当前工序的产线执行物料信息
                uLineMaterialInfo = new ULineMaterialInfo();
                uLineMaterialInfo.setGuid(CommonUtils.getRandomNumber());
                uLineMaterialInfo.setCreator(CommonUtils.readUser().getRealName());
                uLineMaterialInfo.setCreateTime(new Date());
                uLineMaterialInfo.setSpecGuid(specVerInfo.getGuid());
                uLineMaterialInfo.setSpecName(specVerInfo.getSpecName());
                uLineMaterialInfo.setWoCode(woInfo.getWoCode());
                uLineMaterialInfo.setWarningVaule(request.getWarningVaule() == null ? BigDecimal.ZERO : new BigDecimal(request.getWarningVaule().toString()));
                uLineMaterialInfoDao.insert(uLineMaterialInfo);
            }
            //新增明细信息
            ULineMaterialDetailInfo uLineMaterialDetailInfo = new ULineMaterialDetailInfo();
            uLineMaterialDetailInfo.setGuid(CommonUtils.getRandomNumber());
            uLineMaterialDetailInfo.setCreator(CommonUtils.readUser().getRealName());
            uLineMaterialDetailInfo.setCreateTime(new Date());
            uLineMaterialDetailInfo.setUlmGuid(uLineMaterialInfo.getGuid());
            uLineMaterialDetailInfo.setLuType(request.getLuType());
            if (request.getLuType().equals("01")) { //设备上料操作
                uLineMaterialDetailInfo.setDevGd(devInfo.getGuid());
                uLineMaterialDetailInfo.setDevName(devInfo.getDevName());
            }
            uLineMaterialDetailInfo.setBatchNo(materialsBInfo.getBatchNo());
            uLineMaterialDetailInfo.setLotNo(request.getBatch());
            uLineMaterialDetailInfo.setMaterialCode(maVerInfo.getMaterialCode());
            uLineMaterialDetailInfo.setMaterialDes(maVerInfo.getMaterialDes());
            uLineMaterialDetailInfo.setMaterialName(maVerInfo.getMaterialName());
            uLineMaterialDetailInfo.setReplaceMaterial(replaceMaterial ? "00" : "01"); //是否替代物料 00-是，01-否
            uLineMaterialDetailInfo.setSumNum(new BigDecimal(String.valueOf(materialsBInfo.getCanNum()))); //当前物料批次可用量
            uLineMaterialDetailInfo.setCanNum(new BigDecimal(String.valueOf(materialsBInfo.getCanNum()))); //当前物料批次可用量
            uLineMaterialDetailInfo.setUnitName(unitInfo != null ? unitInfo.getUnitName() : "");
            uLineMaterialDetailInfo.setStatus("00");
            uLineMaterialDetailInfo.setRemark(request.getRemark());
            uLineMaterialDetailInfoDao.insert(uLineMaterialDetailInfo);
            //冻结物料批次
            materialsBInfo.setStatus("01"); //生产使用中冻结
            materialsBInfo.setLastModifyTime(new Date());
            materialsBInfo.setLastModifier(CommonUtils.readUser().getRealName());
            materialsBInfo.setRemark("上料冻结");
            wmsMaterialsBDAO.updateById(materialsBInfo);
            udMaterialLogInfo.setSumNum(new BigDecimal(String.valueOf(materialsBInfo.getCanNum())));
        } else if (request.getType().equals("01")) { //todo 下料逻辑
            udMaterialLogInfo.setCanNum(materialsBInfo.getCanNum()); //下料时系统剩余数量
            if (!"01".equals(materialsBInfo.getStatus()))
                throw new SystemException("EEEE", request.getBatch() + "当前物料批次状态:{" + statusErrorName + "}不允许进行下料操作！");
            if (uLineMaterialInfo == null)
                throw new SystemException("EEEE", "当前物料批次：" + request.getBatch() + "在当前工单下未找到对应上料信息！");
            ULineMaterialDetailInfo uLineMaterialDetailInfo = new ULineMaterialDetailInfo();
            uLineMaterialDetailInfo.setUlmGuid(uLineMaterialInfo.getGuid());
            uLineMaterialDetailInfo.setLotNo(request.getBatch());
            uLineMaterialDetailInfo.setStatus("00");
            uLineMaterialDetailInfo = uLineMaterialDetailInfoDao.selectOne(new QueryWrapper<ULineMaterialDetailInfo>()
                    .eq("ulmGuid", uLineMaterialInfo.getGuid())
                    .eq("lotNo", request.getBatch())
                    .eq("luType", request.getLuType())
                    .eq(request.getLuType().equals("01"), "devGd", devInfo.getGuid())
                    .eq("status", "00"));
            if (uLineMaterialDetailInfo == null)
                throw new SystemException("EEEE", "当前物料批次：" + request.getBatch() + "在当前工单下未找到对应上料信息！");
            uLineMaterialDetailInfo.setStatus("02"); //删除状态

            //计算物料剩余数量
            //当存在差异数量时
            if (!StringUtils.isBlank(request.getIsDifference()) && request.getIsDifference().equals("01")) {
                BigDecimal Num = new BigDecimal(String.valueOf(materialsBInfo.getNum())); //当前系统总量
                BigDecimal differenceNum = BigDecimal.ZERO; //下料累计剩余

                //当前批次剩余数量计算
                if (request.getDifferenceNumber() != null) {
                    differenceNum = differenceNum.add(new BigDecimal(request.getDifferenceNumber()));
                    materialsBInfo.setCanNum(new BigDecimal(request.getDifferenceNumber()));
                    materialsBInfo.setStatus("00"); //解冻
                } else {
                    if (request.getDifferenceNumber1() != null || request.getDifferenceNumber2() != null) {
                        materialsBInfo.setCanNum(BigDecimal.ZERO);
                        materialsBInfo.setStatus("04"); //作废
                    } else {
                        materialsBInfo.setStatus("00"); //解冻
                    }
                }
                if (request.getDifferenceNumber1() != null)
                    differenceNum = differenceNum.add(new BigDecimal(request.getDifferenceNumber1()));
                if (request.getDifferenceNumber2() != null)
                    differenceNum = differenceNum.add(new BigDecimal(request.getDifferenceNumber2()));
                //三种类型数量相加
                //更新系统剩余数量
                if (Num.compareTo(differenceNum) < 0) { //系统数量比实际下料数量少的情况
                    throw new SystemException("EEEE", "当前物料批次：" + request.getBatch() + "下料时填写的下料数量经过系统计算(" + Num + "-" + differenceNum + ")得出的结果小于零！");
                }
            } else {
                //解冻物料批次
                materialsBInfo.setStatus("00"); //解冻
            }

            materialsBInfo.setConSuNum(materialsBInfo.getNum().subtract(materialsBInfo.getCanNum()));
            materialsBInfo.setLastModifyTime(new Date());
            materialsBInfo.setLastModifier(CommonUtils.readUser().getRealName());
            materialsBInfo.setRemark("下料合格");
            wmsMaterialsBDAO.updateById(materialsBInfo);
            //更新以前上料记录变成下料
            uLineMaterialDetailInfo.setCanNum(new BigDecimal(String.valueOf(materialsBInfo.getCanNum())));
            uLineMaterialDetailInfoDao.updateById(uLineMaterialDetailInfo);
            //记录日志信息
            udMaterialLogInfo.setSumNum(materialsBInfo.getCanNum()); //本次下料数量
            udMaterialLogInfo.setRealityNum(request.getDifferenceNumber() != null ? new BigDecimal(request.getDifferenceNumber()) : null); //下料时手动输入数量
        }

        //新增上/下料日志信息
        udMaterialLogInfo.setGuid(CommonUtils.getRandomNumber());
        udMaterialLogInfo.setBatchNo(materialsBInfo.getBatchNo());
        udMaterialLogInfo.setLotNo(request.getBatch());
        udMaterialLogInfo.setWOCode(woInfo.getWoCode());
        udMaterialLogInfo.setCreator(CommonUtils.readUser().getRealName());
        udMaterialLogInfo.setCreateTime(new Date());
        udMaterialLogInfo.setMaterialCode(maVerInfo.getMaterialCode());
        udMaterialLogInfo.setMaterialName(maVerInfo.getMaterialName());
        udMaterialLogInfo.setMaterialDes(maVerInfo.getMaterialDes());
        udMaterialLogInfo.setUnitName(unitInfo != null ? unitInfo.getUnitName() : "");
        udMaterialLogInfo.setUdType(request.getType());
        udMaterialLogInfo.setLuType(request.getLuType());
        if (request.getLuType().equals("01")) {
            udMaterialLogInfo.setDevGd(devInfo.getGuid());
            udMaterialLogInfo.setDevName(devInfo.getDevName());
        }
        udMaterialLogInfo.setReplaceMaterial(replaceMaterial ? "00" : "01");
        udMaterialLogInfo.setRemark(StringUtils.isBlank(request.getRemark()) ? "" : request.getRemark());
        udMaterialLogInfo.setSpecGuid(specVerInfo.getGuid());
        udMaterialLogInfo.setSpecName(specVerInfo.getSpecName());
        udMaterialLogInfo.setRealityType(request.getDifferenceNumber() != null ? "00" : "");
        udMaterialLogDao.insert(udMaterialLogInfo);

        if (request.getType().equals("01")) { //下料
            //制损和原损批次拆分,生成打印记录，生成下料日志
            if (request.getDifferenceNumber1() != null) { //原损
                materialsBInfo.setStatus("04");
                materialsBInfo.setRemark("原损");
                String newBatch = AddMaterialBinfo(materialsBInfo, request.getDifferenceNumber1(), maVerInfo);
                //生成下料日志
                udMaterialLogInfo.setGuid(CommonUtils.getRandomNumber());
                udMaterialLogInfo.setLotNo(newBatch);
                udMaterialLogInfo.setRealityType("01");
                udMaterialLogInfo.setSumNum(new BigDecimal(request.getDifferenceNumber1()));
                udMaterialLogInfo.setCanNum(new BigDecimal(request.getDifferenceNumber1()));
                udMaterialLogInfo.setRealityNum(new BigDecimal(request.getDifferenceNumber1()));
                udMaterialLogDao.insert(udMaterialLogInfo);
            }
            if (request.getDifferenceNumber2() != null) { //制损
                materialsBInfo.setStatus("04");
                materialsBInfo.setRemark("制损");
                String newBatch = AddMaterialBinfo(materialsBInfo, request.getDifferenceNumber2(), maVerInfo);
                //生成下料日志
                udMaterialLogInfo.setGuid(CommonUtils.getRandomNumber());
                udMaterialLogInfo.setLotNo(newBatch);
                udMaterialLogInfo.setRealityType("02");
                udMaterialLogInfo.setSumNum(new BigDecimal(request.getDifferenceNumber2()));
                udMaterialLogInfo.setCanNum(new BigDecimal(request.getDifferenceNumber2()));
                udMaterialLogInfo.setRealityNum(new BigDecimal(request.getDifferenceNumber2()));
                udMaterialLogDao.insert(udMaterialLogInfo);
            }
        }
    }


    //原料批次拆分创建
    public String AddMaterialBinfo(WmsMaterialsBInfo wmsMaterialsBInfo, Float num, MaVerInfo maVerInfo) {
        //查询代码生成
        CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("00");
        if (codeRuleInfo == null || !"00".equals(codeRuleInfo.getStatus())) {
            throw new SystemException("", "请到全局配置进行物料代码生成配置");
        }
        WmsMaterialsBInfo newBInfo = new WmsMaterialsBInfo();
        BeanUtils.copyProperties(wmsMaterialsBInfo, newBInfo);
        newBInfo.setGuid(CommonUtils.getRandomNumber());
        newBInfo.setCreator(CommonUtils.readUser().getRealName());
        newBInfo.setCreateTime(new Date());
        newBInfo.setLastModifyTime(null);
        newBInfo.setLastModifier(null);
        newBInfo.setNum(new BigDecimal(num));
        newBInfo.setCanNum(new BigDecimal(num));
        newBInfo.setConSuNum(BigDecimal.ZERO);
        newBInfo.setDifferenceNum(BigDecimal.ZERO);
        newBInfo.setBatch(gConfigIService.GetCreateSC(codeRuleInfo));
        newBInfo.setInStockStatus("01");
        newBInfo.setBeforeCheckQC("00");
        newBInfo.setSupBatch("");
        newBInfo.setbSource("01");
        wmsMaterialsBDAO.insert(newBInfo);

        //新增批次拆分信息
        SplitBatchInfo splitBatchInfo = new SplitBatchInfo();
        splitBatchInfo.setGuid(CommonUtils.getRandomNumber());
        splitBatchInfo.setsBatch(wmsMaterialsBInfo.getBatch());
        splitBatchInfo.setBatch(newBInfo.getBatch());
        splitBatchInfo.setNum(newBInfo.getNum().floatValue());
        splitBatchInfo.setUnitName("");
        splitBatchInfo.setCreator(CommonUtils.readUser().getRealName());
        splitBatchInfo.setCreateTime(new Date());
        splitBatchInfo.setRemark(String.valueOf(newBInfo.getNum()));
        splitBatchDAO.InsertSplitBatch(splitBatchInfo);
        //插入打印记录
        PrintTaskInfo printTaskInfo = new PrintTaskInfo();
        printTaskInfo.setGuid(CommonUtils.getRandomNumber());
        printTaskInfo.setpTCode("PT" + printTaskInfo.getGuid());
        printTaskInfo.setReCode("PT" + printTaskInfo.getGuid());
        printTaskInfo.setOrderType("04");
        printTaskInfo.setMaterialCode(maVerInfo.getMaterialCode());
        printTaskInfo.setMaterialName(maVerInfo.getMaterialName());
        printTaskInfo.setBarCode(newBInfo.getBatch());
        printTaskInfo.setBarType("04");
        printTaskInfo.setPrintCount(1);
        printTaskInfo.setPrintCopy(1);
        printTaskInfo.setPrintTGd("");
        printTaskInfo.setpTFileName("");
        if (maVerInfo.getPrintTGd() != null) {
            PrintTInfo printTInfo = printTDAO.SelectByGuid(maVerInfo.getPrintTGd());
            if (printTInfo != null) {
                printTaskInfo.setPrintTGd(printTInfo.getGuid());
                printTaskInfo.setpTFileName(printTInfo.getFileName());
            }
        }
        printTaskInfo.setCreator(CommonUtils.readUser().getRealName());
        printTaskInfo.setCreateTime(new Date());

        if (printTaskDAO.SelectByBarCodeType(printTaskInfo.getBarCode(), printTaskInfo.getPrintTGd(), printTaskInfo.getBarType()) == null) {
            printTaskDAO.InsertPrintTaskInfo(printTaskInfo);
        }
        return newBInfo.getBatch();
    }

    @Override
    public BaseResponse GetAllByWoCode(SaveUDMaterailRequest request) {
        if (request.getWoRd() == null)
            throw new SystemException("EEEE", "请选择工单！");
        if (request.getSpecRd() == null)
            throw new SystemException("EEEE", "请选择工序！");
        GetAllByWoCodeResponse response = new GetAllByWoCodeResponse();
        List<GetAllByWoCodeResponse.StockBomDetail> stockBomDetails = new ArrayList<>();
        WoInfo woInfo = wodao.SelectWoInfoByruid(request.getWoRd());
        if (woInfo == null)
            throw new SystemException("EEEE", "当前工单在系统中存在！");
        SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByruid(request.getSpecRd());
        if (specVerInfo == null)
            throw new SystemException("EEEE", "当前工单在系统中存在！");
        //查询上下料记录
        List<UDMaterialLogInfo> udMaterialLogInfos = udMaterialLogDao.selectList(new QueryWrapper<UDMaterialLogInfo>()
                .eq("woCode", woInfo.getWoCode())
                .eq("specGuid", specVerInfo.getGuid())
                .eq(!StringUtils.isBlank(request.getLuType()), "luType", request.getLuType())
                .eq(request.getDevRd() != null, "devRd", request.getDevRd())
        );
        response.setWoNum(woInfo.getNum());
        BomVerInfo bomVerInfo = bomVerDAO.SelectBomVerInfo(woInfo.getBomVerGd());
        if (bomVerInfo == null) {
            response.setDetails(udMaterialLogInfos);
            return BaseResponse.success(response);
        }
        List<BomMaterialInfo> stockBomMaterialInfoList = bomMaterialDAO.SelectByBSGd(bomVerInfo.getGuid(), specVerInfo.getGuid());
        if (stockBomMaterialInfoList.size() > 0) {
            stockBomMaterialInfoList.forEach(model -> {
                GetAllByWoCodeResponse.StockBomDetail stockBomDetail = new GetAllByWoCodeResponse.StockBomDetail();
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(model.getMaVerGd());
                BigDecimal USumNum = BigDecimal.ZERO; //累计上料数量
                BigDecimal DSumNum = BigDecimal.ZERO; //累计下料数量
                //汇总出当前料号的上下料信息
                for (UDMaterialLogInfo udMaterialLogInfo : udMaterialLogInfos) {
                    if (udMaterialLogInfo.getMaterialCode().equals(maVerInfo.getMaterialCode())) {
                        if (udMaterialLogInfo.getUdType().equals("00")) { //累计上料数量
                            if (udMaterialLogInfo.getSumNum() != null)
                                USumNum = USumNum.add(udMaterialLogInfo.getSumNum());
                        } else if (udMaterialLogInfo.getUdType().equals("01")) { //累计下料数量
                            if (udMaterialLogInfo.getSumNum() != null)
                                DSumNum = DSumNum.add(udMaterialLogInfo.getSumNum());
                        }
                    }
                }
                stockBomDetail.setBomDetailRd(model.getRuid());
                stockBomDetail.setUpSumNum(USumNum); //累计上料数量
                stockBomDetail.setUpdSumNum(DSumNum); //累计下料数量
                stockBomDetail.setUnitName(model.getUnitName()); //单位名称
                stockBomDetail.setMaCode(maVerInfo.getMaterialCode());
                stockBomDetail.setMaName(maVerInfo.getMaterialName());
                stockBomDetail.setStockSumNum(new BigDecimal(woInfo.getNum()).multiply(new BigDecimal(model.getNum())));

                List<GetAllByWoCodeResponse.StockBomDetail> reStockBomDetails = new ArrayList<>();
                //查询替代料
                List<BomReMaterialInfo> stockBomReMaterialInfos = bomReMaterialDAO.selectList(new QueryWrapper<BomReMaterialInfo>().eq("bomMaGd", model.getGuid())
                        .eq("specVerGd", specVerInfo.getGuid()));
                stockBomReMaterialInfos.forEach(remodel -> {
                    GetAllByWoCodeResponse.StockBomDetail reStockBomDetail = new GetAllByWoCodeResponse.StockBomDetail();
                    MaVerInfo reMaVerInfo = maVerDAO.SelectMaverInfo(remodel.getMaVerGd());
                    if (reMaVerInfo != null) {
                        //汇总出当前料号的上下料信息
                        //累计上料数量
                        BigDecimal ReUSumNum = udMaterialLogInfos.stream()
                                .filter(UDMaterialLogInfo -> UDMaterialLogInfo.getMaterialCode().equals(reMaVerInfo.getMaterialCode()))
                                .filter(UDMaterialLogInfo -> UDMaterialLogInfo.getUdType().equals("00"))
                                .filter(UDMaterialLogInfo -> UDMaterialLogInfo.getSumNum() != null)
                                .collect(Collectors.toList())
                                .stream()
                                .map(UDMaterialLogInfo::getSumNum).reduce(BigDecimal.ZERO, BigDecimal::add);

                        //累计下料数量
                        BigDecimal ReDSumNum = udMaterialLogInfos.stream()
                                .filter(UDMaterialLogInfo -> UDMaterialLogInfo.getMaterialCode().equals(reMaVerInfo.getMaterialCode()))
                                .filter(UDMaterialLogInfo -> UDMaterialLogInfo.getUdType().equals("01"))
                                .filter(UDMaterialLogInfo -> UDMaterialLogInfo.getSumNum() != null)
                                .collect(Collectors.toList())
                                .stream()
                                .map(UDMaterialLogInfo::getSumNum).reduce(BigDecimal.ZERO, BigDecimal::add);
                        reStockBomDetail.setMaCode(reMaVerInfo.getMaterialCode());
                        reStockBomDetail.setMaName(reMaVerInfo.getMaterialName());
                        reStockBomDetail.setStockSumNum(BigDecimal.ZERO);
                        reStockBomDetail.setUpSumNum(ReUSumNum); //累计上料数量
                        reStockBomDetail.setUpdSumNum(ReDSumNum); //累计下料数量
                        reStockBomDetail.setUnitName(model.getUnitName()); //单位名称
                        reStockBomDetails.add(reStockBomDetail);
                    }
                });
                stockBomDetail.setStockReBomDetails(reStockBomDetails);
                stockBomDetails.add(stockBomDetail);
            });
            response.setSpecSumNum(BigDecimal.ZERO);//总量
            response.setStockBomDetails(stockBomDetails);
        }
        response.setDetails(udMaterialLogInfos);
        return BaseResponse.success(response);
    }

    //dto查询工单信息，根据工单id
    @Override
    public BaseResponse GetGetWOInfoRes(GetWOInfoReqBD00 argGetWOInfoReqBD00) {
        GetWOInfoResDByUDMaterial objEGetWOInfoResD = new GetWOInfoResDByUDMaterial();
        WoInfo objEWoInfo = wodao.SelectWoInfoByruid(argGetWOInfoReqBD00.getWoRd());
        if (objEWoInfo != null) {
            objEGetWOInfoResD.setWoRd(objEWoInfo.getRuid());
            objEGetWOInfoResD.setWoCode(objEWoInfo.getWoCode());
            objEGetWOInfoResD.setNum(objEWoInfo.getNum());
            objEGetWOInfoResD.setOrderCode(objEWoInfo.getOrderCode());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            objEGetWOInfoResD.setJStartDate(format.format(objEWoInfo.getjStartDate()));
            objEGetWOInfoResD.setJFinishDate(format.format(objEWoInfo.getjFinishDate()));
            objEGetWOInfoResD.setCreator(objEWoInfo.getCreator());
            objEGetWOInfoResD.setCreateTime(DateUtil.format(objEWoInfo.getCreateTime()));
            objEGetWOInfoResD.setLastModifyMan(objEWoInfo.getLastModifyMan());
            objEGetWOInfoResD.setLastModifyTime(DateUtil.format(objEWoInfo.getLastModifyTime()));
            objEGetWOInfoResD.setRemark(objEWoInfo.getRemark());
            objEGetWOInfoResD.setFinishNum(objEWoInfo.getFinishNum());
            objEGetWOInfoResD.setUnCBatNum(objEWoInfo.getUnCBatNum());
            objEGetWOInfoResD.setStatus(objEWoInfo.getStatus());
            objEGetWOInfoResD.setDSource(objEWoInfo.getdSource());
            //物料版本
            GetWOInfoResBDMa objEGetWOInfoResBDMa = new GetWOInfoResBDMa();

            MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objEWoInfo.getMaVerGd());
            if (objEMaVerInfo != null) {
                MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                if (objEMaterialInfo != null) {
                    objEGetWOInfoResBDMa.setMaVerRd(objEMaVerInfo.getRuid());
                    objEGetWOInfoResBDMa.setMaCode(objEMaterialInfo.getMaterialCode());
                    objEGetWOInfoResBDMa.setMaName(objEMaterialInfo.getMaterialName());
                    objEGetWOInfoResBDMa.setMaDes(objEMaterialInfo.getMaterialDes());
                }
            }
            objEGetWOInfoResD.setMaInfo(objEGetWOInfoResBDMa);

            //暂般
            PackSpecificationInfo objEPackSpecificationInfo = packSpecificationDAO.SelectPackSpecificationByGuid(objEWoInfo.getTrayPackGd());
            GetWOInfoResBDTrayPack objEGetWOInfoResBDTrayPack = new GetWOInfoResBDTrayPack();
            if (objEPackSpecificationInfo != null) {
                objEGetWOInfoResBDTrayPack.setPackSpRd(objEPackSpecificationInfo.getRuid());
                objEGetWOInfoResBDTrayPack.setPackName(objEPackSpecificationInfo.getPackName());
            }

            //箱包
            PackSpecificationInfo objEPackSpecificationInfo1 = packSpecificationDAO.SelectPackSpecificationByGuid(objEWoInfo.getBoxPackGd());
            GetWOInfoResBDBoxPack objEGetWOInfoResBDBoxPack = new GetWOInfoResBDBoxPack();
            if (objEPackSpecificationInfo1 != null) {
                objEGetWOInfoResBDBoxPack.setPackSpRd(objEPackSpecificationInfo1.getRuid());
                objEGetWOInfoResBDBoxPack.setPackName(objEPackSpecificationInfo1.getPackName());
            }
            //工单类型
            GetWOInfoResBDWOT objEGetWOInfoResBDWOT = new GetWOInfoResBDWOT();
            WoTypeInfo objEWoTypeInfo = woTypeDAO.SelectWoTypeByGuid(objEWoInfo.getWoTGd());
            if (objEWoTypeInfo != null) {
                objEGetWOInfoResBDWOT.setWTRd(objEWoTypeInfo.getRuid());
                objEGetWOInfoResBDWOT.setWTName(objEWoTypeInfo.getWoTName());
            }
            //线体   乔帅阳
  /*          List<GetWOInfoResBDOrderLine> objGetDevInfoResDLine = new ArrayList<>();
            List<OrderLineinfo> OrderList = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
            if(OrderList!=null&&OrderList.size()>0){
                for(OrderLineinfo obj:OrderList){
                    GetWOInfoResBDOrderLine orderLineinfo = new GetWOInfoResBDOrderLine();
                    Lineinfo lineinfo=lineDao.SelectLineInfoByguid(obj.getLineGd());
                    if(lineinfo!=null){
                        orderLineinfo.setLineRd(lineinfo.getRuid());
                        orderLineinfo.setLineName(lineinfo.getLineName());
                    }
                    objGetDevInfoResDLine.add(orderLineinfo);
                }
            }*/
            List<GetWOInfoResBDOrderLine> objGetWOInfoResBDOrderLines = new ArrayList<GetWOInfoResBDOrderLine>();
            if (objEWoInfo.getGuid() != null) {
                List<OrderLineinfo> orderLineinfos = orderLineDAO.SelectOrderLineByGuid(objEWoInfo.getGuid());
                if (orderLineinfos != null && orderLineinfos.size() > 0) {
                    for (OrderLineinfo orderLineinfo : orderLineinfos) {
                        GetWOInfoResBDOrderLine orderLineinfo1 = new GetWOInfoResBDOrderLine();
                        Lineinfo Lineinfo = lineDao.SelectLineInfoByguid(orderLineinfo.getLineGd());
                        if (Lineinfo != null) {
                            orderLineinfo1.setLineRd(Lineinfo.getRuid());
                            orderLineinfo1.setLineName(Lineinfo.getLineName());
                        }
                        objGetWOInfoResBDOrderLines.add(orderLineinfo1);
                    }
                }
            }
            //单位
            GetWOInfoResBDUnit objEGetWOInfoResBDUnit = new GetWOInfoResBDUnit();
            UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEWoInfo.getUnitGd());
            if (objEUnitInfo != null) {
                objEGetWOInfoResBDUnit.setUnitRd(objEUnitInfo.getRuid());
                objEGetWOInfoResBDUnit.setUnitName(objEUnitInfo.getUnitName());
            }

            //todo 添加工序操作
            WFVerInfo wfVerInfo = wfVerDAO.SelectByGuid(objEWoInfo.getwFVerGd());
            if (wfVerInfo != null) {
                GetCMWInfoResDWFInfo objEGetCMWInfoResDWFInfo = new GetCMWInfoResDWFInfo();
                List<GetCMWInfoResDWSpecInfo> objEGetCMWInfoResDWSpecInfos = new ArrayList<>();
                GetCMWInfoResDWSpecInfo objEGetCMWInfoResDWSpecInfo = null;
                SpecVerInfo specVerInfo = null;

                objEGetCMWInfoResDWFInfo.setWFVerRd(wfVerInfo.getRuid());
                objEGetCMWInfoResDWFInfo.setWFName(wfVerInfo.getwFName());
                //查询该流程下面的工序
                List<WFSpecInfo> wfSpecInfos = wfSpecDAO.SelectByWFVerGd(wfVerInfo.getGuid());
                // 遍历工艺工序的工序ID，查询所有的工序版本信息
                for (WFSpecInfo wfSpecInfo : wfSpecInfos) {
                    specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                    if (specVerInfo != null) {
                        objEGetCMWInfoResDWSpecInfo = new GetCMWInfoResDWSpecInfo();
                        objEGetCMWInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                        objEGetCMWInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                        objEGetCMWInfoResDWSpecInfo.setSpecStatus(specVerInfo.getStatus());
                        objEGetCMWInfoResDWSpecInfo.setDevGInfo(readDev(specVerInfo.getDevGrGd()));

                        if (wfVerInfo.getsSpecVerGd().equals(specVerInfo.getGuid())) {
                            objEGetCMWInfoResDWSpecInfos.add(0, objEGetCMWInfoResDWSpecInfo);
                        } else {
                            objEGetCMWInfoResDWSpecInfos.add(objEGetCMWInfoResDWSpecInfo);
                        }

                    /*if("00".equals(wfSpecInfo.geteOSpec()) || "00".equals(wfSpecInfo.geteRSpec())){
                        //非正常流转信息
                        List<WFCirInfo> objEWFCirInfos = wfCirDAO.SelectByWFSpecGd(wfSpecInfo.getGuid());
                        if(objEWFCirInfos != null){
                            specVerInfo = specVerDAO.SelectSpecVerInfoByguid(wfSpecInfo.getSpecVerGd());
                            if(specVerInfo != null){
                                objEGetCMWInfoResDWSpecInfo = new GetCMWInfoResDWSpecInfo();
                                objEGetCMWInfoResDWSpecInfo.setSpecVerRd(specVerInfo.getRuid());
                                objEGetCMWInfoResDWSpecInfo.setSpecName(specVerInfo.getSpecName());
                                objEGetCMWInfoResDWSpecInfo.setDevGInfo(readDev(specVerInfo.getDevGrGd()));

                                objEGetCMWInfoResDWSpecInfos.add(objEGetCMWInfoResDWSpecInfo);
                            }
                        }
                    }*/
                    }
                }

                objEGetCMWInfoResDWFInfo.setSpecInfo(objEGetCMWInfoResDWSpecInfos);
                objEGetWOInfoResD.setWFInfo(objEGetCMWInfoResDWFInfo);
            }


            //紧急度
            GetWOInfoResBDUrcy objEGetWOInfoResBDUrcy = new GetWOInfoResBDUrcy();
            UrgencyInfo objEUrgencyInfo = urgencyDAO.SelectByguid(objEWoInfo.getUrcyGd());
            if (objEUrgencyInfo != null) {
                objEGetWOInfoResBDUrcy.setUrcyRd(objEUrgencyInfo.getRuid());
                objEGetWOInfoResBDUrcy.setUrcyDes(objEUrgencyInfo.getUrcyDes());
            }
            objEGetWOInfoResD.setUrcyInfo(objEGetWOInfoResBDUrcy);
            objEGetWOInfoResD.setWOTInfo(objEGetWOInfoResBDWOT);
            objEGetWOInfoResD.setBoxPackInfo(objEGetWOInfoResBDBoxPack);
            objEGetWOInfoResD.setTrayPackInfo(objEGetWOInfoResBDTrayPack);
            objEGetWOInfoResD.setUnitInfo(objEGetWOInfoResBDUnit);
            /* objEGetWOInfoResD.setOrderLineInfo(objGetDevInfoResDLine);*/
            objEGetWOInfoResD.setLineInfo(objGetWOInfoResBDOrderLines);
        }
        return BaseResponse.success(objEGetWOInfoResD);
    }

    //根据工序查询设备
    public List<GetCMWInfoResDWSDevGInfo> readDev(String id) {
        List<GetCMWInfoResDWSDevGInfo> objEGetCMWInfoResDWSDevGInfos = new ArrayList<>();
        GetCMWInfoResDWSDevGInfo objEGetCMWInfoResDWSDevGInfo = null;
        DevInfo objEDevInfo = null;
        DevStateInfo objEDevStateInfo = null;

        //查询设备组明细
        List<DevGpDtlInfo> objEDevGpDtInfos = devGpDtlDAO.SelectByguid(id);
        if (objEDevGpDtInfos != null && objEDevGpDtInfos.size() > 0) {
            for (DevGpDtlInfo devGpDtlInfo : objEDevGpDtInfos) {
                //查询设备
                objEDevInfo = devDAO.SelectByguid(devGpDtlInfo.getDevGd());
                if (objEDevInfo != null) {
                    objEGetCMWInfoResDWSDevGInfo = new GetCMWInfoResDWSDevGInfo();
                    objEGetCMWInfoResDWSDevGInfo.setDevRd(objEDevInfo.getRuid());
                    objEGetCMWInfoResDWSDevGInfo.setDevName(objEDevInfo.getDevName());
                    objEGetCMWInfoResDWSDevGInfos.add(objEGetCMWInfoResDWSDevGInfo);
                }
            }
        }

        return objEGetCMWInfoResDWSDevGInfos;
    }

    /**
     * @Description 分页查询上下料日志信息
     * @Author yin.yang
     * @Date 2020/10/15
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse GetAllUDMaterialLogs(GetAllUdMaterialLogsPageRequest pageRequest) {
        //必须选择工单才进行查询
        if (pageRequest.getWoCodes() == null || pageRequest.getWoCodes().size() < 1)
            return BaseResponse.SUCCESS;
        Page<UDMaterialLogInfo> page = new Page<>();
        page.setSize(pageRequest.getLimit());
        page.setCurrent(pageRequest.getPage());
        IPage<UDMaterialLogInfo> response = udMaterialLogDao.selectPage(page, new QueryWrapper<UDMaterialLogInfo>()
                .eq(!StringUtils.isBlank(pageRequest.getUdType()), "udType", pageRequest.getUdType())
                .in(pageRequest.getSpecGuids() != null && pageRequest.getSpecGuids().size() > 0, "specGuid", pageRequest.getSpecGuids())
                .in(pageRequest.getWoCodes() != null && pageRequest.getWoCodes().size() > 0, "woCode", pageRequest.getWoCodes())
                .orderByDesc("CreateTime")
        );
        return BaseResponse.success(response);
    }

    @Override
    public BaseResponse GetAllUDMaterials(GetAllUdMaterialLogsPageRequest request) {
        //必须选择工单才进行查询
        if (StringUtils.isBlank(request.getWoCode()))
            return BaseResponse.SUCCESS;
        Page<UDMaterialLogInfo> page = new Page<>();
        page.setCurrent(request.getPage());
        page.setSize(request.getLimit());
        IPage<GetAllUDMaterialsRespons> response = uLineMaterialDetailInfoDao.selectPageUDMD(page, request.getWoCode(), request.getSpecGuid());
        return BaseResponse.success(response);
    }

    @Override
    public BaseResponse SaveUdMateriaLallot(SaveUdMateriaLallotRequest request) {
        if (request.getWoCode().equals(request.getWoCodeIn()) && request.getSpecGuid().equals(request.getSpecGuidIn()))
            throw new SystemException("EEEE", "不能同工单同工序进行调拨");
        WoInfo woInfo = wodao.SelectWoInfoBywoCode(request.getWoCode());
        WoInfo woInfoIn = wodao.SelectWoInfoBywoCode(request.getWoCodeIn());
        if (request.getUdType().equals("00")) { //按工序
            //check BOM
            request.getDetail().forEach(detail -> {
                BomVerInfo bomVerInfo = bomVerDAO.SelectBomVerInfo(woInfoIn.getBomVerGd());
                if (bomVerInfo == null)
                    throw new SystemException("EEEE", woInfo.getWoCode() + "数据异常，追溯不到绑定BOM信息");
                MaVerInfo maVerInfo = maVerDAO.selectOne(new QueryWrapper<MaVerInfo>().eq("materialCode", detail.getMaterialCode()));
                //根据物料标识查询BOM用料表
                List<BomMaterialInfo> bomMaterialInfoList = bomMaterialDAO.SelectByBomVerGdAndMaVerGd(bomVerInfo.getGuid(), maVerInfo.getGuid(), request.getSpecGuidIn());
                List<BomReMaterialInfo> bomReMaterialInfoList = new ArrayList<>();
                if (bomMaterialInfoList == null || bomMaterialInfoList.size() < 1) {
                    //查询替代物料
                    bomReMaterialInfoList = bomReMaterialDAO.SelectByMaVerGdAndBomVerGd(bomVerInfo.getGuid(), maVerInfo.getGuid(), request.getSpecGuidIn());
                    if (bomReMaterialInfoList == null || bomReMaterialInfoList.size() < 1)
                        throw new SystemException("", "当前调入工序: 物料代码：【" + maVerInfo.getMaterialCode() + "】物料名称：【" + maVerInfo.getMaterialName() + "】不在BOM设定的范围中");
                }

                ULineMaterialInfo uLineMaterialInfo = uLineMaterialInfoDao.selectOne(new QueryWrapper<ULineMaterialInfo>()
                        .eq("woCode", request.getWoCodeIn())
                        .eq("specGuid", request.getSpecGuidIn()));
                SpecVerInfo specVerInfo = specVerDAO.SelectSpecVerInfoByguid(request.getSpecGuidIn());
                if (uLineMaterialInfo == null) {
                    uLineMaterialInfo = new ULineMaterialInfo();
                    uLineMaterialInfo.setGuid(CommonUtils.getRandomNumber());
                    uLineMaterialInfo.setCreator(CommonUtils.readUser().getRealName());
                    uLineMaterialInfo.setCreateTime(new Date());
                    uLineMaterialInfo.setSpecGuid(request.getSpecGuidIn());
                    uLineMaterialInfo.setSpecName(specVerInfo.getSpecName());
                    uLineMaterialInfo.setWoCode(request.getWoCodeIn());
                    uLineMaterialInfo.setWarningVaule(BigDecimal.ZERO);
                    uLineMaterialInfoDao.insert(uLineMaterialInfo);
                }
                detail.setGuid(CommonUtils.getRandomNumber());
                detail.setStatus("00");
                detail.setUlmGuid(uLineMaterialInfo.getGuid());
                detail.setCreator(CommonUtils.readUser().getRealName());
                detail.setCreateTime(new Date());
                uLineMaterialDetailInfoDao.deleteById(detail.getRuid());
                uLineMaterialDetailInfoDao.insert(detail);
            });

        } else { //按工单
            if (!woInfo.getBomVerGd().equals(woInfoIn.getBomVerGd()))
                throw new SystemException("EEEE", "调出工单和调入工单使用BOM不同无法进行按工单调拨");
            List<ULineMaterialInfo> uLineMaterialInfoList = uLineMaterialInfoDao.selectList(new QueryWrapper<ULineMaterialInfo>().eq("woCode", request.getWoCode()));
            uLineMaterialInfoList.forEach(uLineMaterialInfo -> {
                uLineMaterialInfo.setWoCode(request.getWoCodeIn());
                uLineMaterialInfo.setCreator(CommonUtils.readUser().getCreator());
                uLineMaterialInfo.setCreateTime(new Date());
                List<ULineMaterialDetailInfo> uLineMaterialDetailInfos = uLineMaterialDetailInfoDao.selectList(new QueryWrapper<ULineMaterialDetailInfo>()
                        .eq("ulmGuid", uLineMaterialInfo.getGuid())
                        .eq("status", "00"));
                uLineMaterialDetailInfos.forEach(uLineMaterialDetailInfo -> {
                    uLineMaterialDetailInfo.setCreator(CommonUtils.readUser().getCreator());
                    uLineMaterialDetailInfo.setCreateTime(new Date());
                    uLineMaterialDetailInfoDao.updateById(uLineMaterialDetailInfo);
                });
                uLineMaterialInfoDao.updateById(uLineMaterialInfo);
            });
        }
        return BaseResponse.SUCCESS;
    }
}
