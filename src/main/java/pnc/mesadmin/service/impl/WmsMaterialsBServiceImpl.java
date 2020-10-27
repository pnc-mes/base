package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.dto.WmsMaterialsBDTO.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.HisPrintLogIService;
import pnc.mesadmin.service.WmsMaterialsBService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.ConstantUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次Service
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class WmsMaterialsBServiceImpl implements WmsMaterialsBService {
    @Resource
    private HisPrintLogIService hisPrintLogIService;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private PrintTDAO printTDAO;

    @Resource
    private PrinterDAO printerDAO;

    @Resource
    private AffairDAO affairDAO;

    @Resource
    private WmsMaterialsBDAO wmsMaterialsBDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    private SplitBatchDAO splitBatchDAO;

    @Resource
    private UnitDAO unitDAO;

    /**
     * 原材料批次创建
     * @param req
     * @throws SystemException
     */
    @Override
    public void AddMaterialsB(WmsMaterialsBAddReq req) throws SystemException {

    }


    /**
     * 原材料批次拆分
     * @param req
     * @throws SystemException
     */
    @Override
    public void AddSplitBatch(WmsMaterialsBSplitReq req) throws SystemException {
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询批次信息
        WmsMaterialsBInfo bInfo = wmsMaterialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>().eq("batch", req.getBatch()));
        if(bInfo == null){
            throw new SystemException("", "批次信息不存在");
        }

        if(!"00".equals(bInfo.getStatus())){
            throw new SystemException("", ConstantUtils.resultMaterialStatus(bInfo.getStatus()) + "不允许拆批");
        }

        //打印模板信息
        PrintTInfo objEPrintTInfo = null;
        //打印机信息
        PrinterInfo objEPrinterInfo = null;

        //查询物料版本
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "该批次物料为空");
        }

        WmsMaterialsBInfo bInfoCopy = new WmsMaterialsBInfo();
        BeanUtils.copyProperties(bInfo, bInfoCopy);
        bInfoCopy.setConSuNum(BigDecimal.ZERO);
        bInfoCopy.setDifferenceNum(BigDecimal.ZERO);
        bInfoCopy.setbSource("01");
        bInfoCopy.setLastModifier(null);
        bInfoCopy.setLastModifyTime(null);

        //记录消耗数量(总数量)
        BigDecimal num = BigDecimal.ZERO;

        List<PrintTaskInfo> objEPrintTaskInfos = new ArrayList<PrintTaskInfo>();

        //是否打印
        int print_count = 0;
        if("00".equals(req.getIsPrint())){
            //获取打印模板信息
            objEPrintTInfo = printTDAO.SelectPrintTInfo(req.getPrintTRd());
            if(objEPrintTInfo == null){
                throw new SystemException("","打印模板信息为空");
            }

            //获取打印机信息
            objEPrinterInfo = printerDAO.SelectByRuid(req.getPrintRd());
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

        if(req.getSplitInfo() == null || req.getSplitInfo().size() <= 0){
            throw new SystemException("", "要拆的批次个数不能少于一个");
        }

        //将可能需要打印的对象写入map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("MaVerInfo", objEMaVerInfo);
        map.put("PrintTInfo", objEPrintTInfo);
        map.put("PrinterInfo", objEPrinterInfo);

       /* WmsMaterialsInStoreInfo inStoreInfo = null;
        WmsMaterialsInStoreInfo inStoreInfoCopy = null;
        if("00".equals(bInfo.getInStockStatus())){
            //判断批次（在库、离库、未设置）
            inStoreInfo = wmsMaterialsInStoreDAO.selectOne(new QueryWrapper<WmsMaterialsInStoreInfo>().eq("batch", bInfo.getBatch()));
            if(inStoreInfo == null){
                throw new SystemException("", "批次显示在库，但物料不在库存中");
            }
            isInStockStatus = "00";
        }*/

        //批次事物操作
        AffairInfo objEAffairInfo = new AffairInfo();
        objEAffairInfo.setGuid(CommonUtils.getRandomNumber());
        objEAffairInfo.setBatch(bInfo.getBatch());
        objEAffairInfo.setSpecVerGd("");
        objEAffairInfo.setSpecName("");
        objEAffairInfo.setOptType("12");
        objEAffairInfo.setCreator(userName);
        objEAffairInfo.setCreateTime(date);
        affairDAO.InsertAffairInfo(objEAffairInfo);

        //查询代码生成
        CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("00");
        if(codeRuleInfo == null || !"00".equals(codeRuleInfo.getStatus())){
            throw new SystemException("", "请到全局配置进行物料代码生成配置");
        }

        for(WmsMaterialsBSplitReq.SplitInfo splitInfo : req.getSplitInfo()){

            num = num.add(splitInfo.getNum());

            //插入新的批次
            bInfoCopy.setGuid(CommonUtils.getRandomNumber());

            bInfoCopy.setBatch(gConfigIService.GetCreateSC(codeRuleInfo));

            if(splitInfo.getNum().compareTo(BigDecimal.ZERO) < 1){
                throw new SystemException("", "拆分的批次数量不能小于等于零");
            }
            bInfoCopy.setNum(splitInfo.getNum());
            bInfoCopy.setCanNum(splitInfo.getNum());

            bInfoCopy.setCreator(userName);
            bInfoCopy.setCreateTime(date);
            wmsMaterialsBDAO.insert(bInfoCopy);

            //插入拆分批次信息
            SplitBatchInfo objESplitBatchInfo = new SplitBatchInfo();
            objESplitBatchInfo.setGuid(CommonUtils.getRandomNumber());
            objESplitBatchInfo.setsBatch(bInfo.getBatch());
            objESplitBatchInfo.setBatch(bInfoCopy.getBatch());
            objESplitBatchInfo.setNum(bInfoCopy.getNum().floatValue());
            objESplitBatchInfo.setUnitName("");
            objESplitBatchInfo.setCreator(userName);
            objESplitBatchInfo.setCreateTime(date);
            objESplitBatchInfo.setRemark(bInfoCopy.getNum().toString());
            splitBatchDAO.InsertSplitBatch(objESplitBatchInfo);

           /* //如果批次是在库状态,则记录
            if("00".equals(isInStockStatus)){
                inStoreInfoCopy = new WmsMaterialsInStoreInfo();
                inStoreInfoCopy.setGuid(CommonUtils.getRandomNumber());
                inStoreInfoCopy.setMaVerGd(inStoreInfo.getMaVerGd());
                inStoreInfoCopy.setBatchno(inStoreInfo.getBatchno());
                inStoreInfoCopy.setGradeName(inStoreInfo.getGradeName());
                inStoreInfoCopy.setSupplierBatch(inStoreInfo.getSupplierBatch());
                inStoreInfoCopy.setLocationGd(inStoreInfo.getLocationGd());
                inStoreInfoCopy.setStotrGd(inStoreInfo.getStotrGd());
                inStoreInfoCopy.setIsCheck(inStoreInfo.getIsCheck());
                inStoreInfoCopy.setBatch(bInfoCopy.getBatch());
                inStoreInfoCopy.setNum(bInfoCopy.getNum());
                inStoreInfoCopy.setCreator(userName);
                inStoreInfoCopy.setCreateTime(date);
                inStoreInfoCopy.setRemark(inStoreInfo.getRemark());
                wmsMaterialsInStoreDAO.insert(inStoreInfoCopy);
            }*/

            //待插入
            PrintTaskInfo objEPrintTaskInfo = new PrintTaskInfo();
            objEPrintTaskInfo.setpTCode("PT" + CommonUtils.getRandomNumber());
            objEPrintTaskInfo.setReCode(bInfo.getBatch());
            objEPrintTaskInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            objEPrintTaskInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            objEPrintTaskInfo.setBarCode(bInfoCopy.getBatch());
            objEPrintTaskInfo.setOrderType("06");
            objEPrintTaskInfo.setBarType("02");
            objEPrintTaskInfo.setPrintCount(print_count);
            objEPrintTaskInfo.setPrintCopy(print_count);
            objEPrintTaskInfo.setPrinterName(objEPrinterInfo.getPrinterName());
            objEPrintTaskInfo.setPrintTGd(objEPrintTInfo.getGuid());
            objEPrintTaskInfo.setpTFileName(objEPrintTInfo.getFileName());
            objEPrintTaskInfo.setCreator(userName);
            objEPrintTaskInfo.setCreateTime(date);

            String strData = "";
            if("00".equals(req.getIsPrint())){
                map.put("WmsMaterialsBInfo", bInfoCopy);

                strData = hisPrintLogIService.PrintData(map, objEPrintTInfo.getGuid());
            }

            objEPrintTaskInfo.setPrintData(strData.toString());
            objEPrintTaskInfos.add(objEPrintTaskInfo);
        }

        //判断是否超出拆批总数
        if(num.compareTo(bInfo.getCanNum()) > -1){
            throw new SystemException("", bInfo.getBatch() + "批次所拆的数量大于等于该批次的可用数量");
        }

        bInfo.setCanNum(bInfo.getCanNum().subtract(num));
        bInfo.setLastModifier(userName);
        bInfo.setLastModifyTime(date);

        if(wmsMaterialsBDAO.updateById(bInfo) <= 0){
            throw new SystemException("", "修改批次信息失败");
        }

      /*  //修改库存信息
        if("00".equals(isInStockStatus)){
            if(num.compareTo(inStoreInfo.getNum()) > -1){
                throw new SystemException("", bInfo.getBatch() + "批次所拆的数量大于等于该批次的库存数量");
            }

            inStoreInfo.setNum(inStoreInfo.getNum().subtract(num));
            inStoreInfo.setLastModifier(userName);
            inStoreInfo.setLastModifyTime(date);
            wmsMaterialsInStoreDAO.updateById(inStoreInfo);
        }*/

        for(PrintTaskInfo printTaskInfo : objEPrintTaskInfos){
            hisPrintLogIService.PrintTQueue(printTaskInfo, objEPrinterInfo.getPrinterSer(), req.getIsPrint());
        }
    }

    /**
     * 原材料批次查询
     * @param batch
     * @return
     * @throws SystemException
     */
    @Override
    public WmsMaterialsBSubRes GetSplitBatch(String batch) throws SystemException {
        WmsMaterialsBSubRes res = new WmsMaterialsBSubRes();
        List<WmsMaterialsBSubRes.RefBatch> refBatchList = new ArrayList<>();

        //查询批次信息
        WmsMaterialsBInfo bInfo = wmsMaterialsBDAO.selectOne(new QueryWrapper<WmsMaterialsBInfo>().eq("batch", batch));
        if(bInfo == null){
            throw new SystemException("", "批次信息不存在");
        }

        //查询物料信息
        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
        if(objEMaVerInfo == null){
            throw new SystemException("", "物料信息不存在");
        }

        res.setMaVerRd(objEMaVerInfo.getRuid());
        res.setMaCode(objEMaVerInfo.getMaterialCode());
        res.setMaName(objEMaVerInfo.getMaterialName());
        res.setNum(bInfo.getCanNum());

        //查询单位
        UnitInfo objEUnitInfo = unitDAO.SelectByGuid(objEMaVerInfo.getUnitGd());
        if(objEUnitInfo != null){
            res.setUnitName(objEUnitInfo.getUnitName());
        }else{
            res.setUnitName("");
        }

        //查询拆分批次
        List<SplitBatchInfo> objESplitBatchInfos = splitBatchDAO.SelectBySBatch(bInfo.getBatch());
        for(SplitBatchInfo splitBatchInfo : objESplitBatchInfos){
            WmsMaterialsBSubRes.RefBatch refBatch = new WmsMaterialsBSubRes.RefBatch();
            refBatch.setBatch(splitBatchInfo.getBatch());
            refBatch.setNum(new BigDecimal(splitBatchInfo.getNum()));
            refBatch.setUnitName(res.getUnitName());
            refBatch.setSplitor(splitBatchInfo.getCreator());
            refBatch.setSplitTime(DateUtil.format(splitBatchInfo.getCreateTime()));

            refBatchList.add(refBatch);
        }

        res.setRefBatch(refBatchList);

        return res;
    }

}
