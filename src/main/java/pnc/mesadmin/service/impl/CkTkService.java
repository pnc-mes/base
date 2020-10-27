package pnc.mesadmin.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoReq00;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoRes;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoResB;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoResD;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoReq00;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoRes;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResB;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResD;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoReq00;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoRes;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoResB;
import pnc.mesadmin.dto.GetCKDlInfo.GetCKDlInfoResD;
import pnc.mesadmin.dto.GetCKTInfo.GetCKTInfoRes;
import pnc.mesadmin.dto.GetCKTInfo.GetCKTInfoResB;
import pnc.mesadmin.dto.GetCKTInfo.GetCKTInfoResD;
import pnc.mesadmin.dto.GetMaTotalInfo.*;
import pnc.mesadmin.dto.SaveCKInfo.*;
import pnc.mesadmin.dto.SavePickInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.CkTkIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：出库信息Service层实现层类
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class CkTkService implements CkTkIService {
    @Resource
    private PurChDtlDAO purChDtlDAO;

    @Resource
    private InCDtlDAO inCDtlDAO;
    @Resource
    private WODAO wodao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private CkTkDAO ckTkDAO;

    @Resource
    private BomReMaterialDAO bomReMaterialDAO;

    @Resource
    private CkDtlDAO ckDtlDAO;

    @Resource
    private InstockDAO instockDAO;

    @Resource
    private PickDAO pickDAO;

    @Resource
    private PickDtlDAO pickDtlDAO;

    @Resource
    private PickDtlDAO_1 pickDtlDAO_1;

    @Resource
    private NPickDtlDAO nPickDtlDAO;

    @Resource
    private RkTkDAO rkTkDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    private UnitDAO unitDAO;

    private static final String[] MATYPE = {"00", "01", "02"};

    private static final String[] TNAME = {"领料出库", "销售出库", "其他出库"};

    //获取用料汇总信息
    @Override
    public GetMaTotalInfoRes GetGetMaTotalInfoRes(GetMaTotalInfoReq00 argGetMaTotalInfoReq00) {
        GetMaTotalInfoRes objEGetMaTotalInfoRes = new GetMaTotalInfoRes();
        GetMaTotalInfoResB objEGetMaTotalInfoResB = new GetMaTotalInfoResB();
        List<GetMaTotalInfoResD> objGetMaTotalInfoResDs = new ArrayList<GetMaTotalInfoResD>();
        GetMaTotalInfoResDStore objEGetMaTotalInfoResDStore = null;

        //工单
        if ("00".equals(argGetMaTotalInfoReq00.getAssSource())) {
            WoInfo woInfo = wodao.SelectWoInfoBywoCode(argGetMaTotalInfoReq00.getAssCode());
            if (woInfo != null) {
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(woInfo.getMaVerGd());
                if (objEMaVerInfo != null) {
                    StoreInfo storeInfo = storeDAO.SelectByRuid(argGetMaTotalInfoReq00.getStoreRd());
                    //根据bom标识查询所有的消耗物料信息
                    List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(woInfo.getBomVerGd());
                    List<BomMaterialInfo> objEBomMaterialInfos1 = new ArrayList<BomMaterialInfo>();
                    objEBomMaterialInfos1.addAll(objEBomMaterialInfos);

                    for (int i = 0; i < objEBomMaterialInfos.size(); i++) {
                        MaVerInfo objEaVerInfo = maVerDAO.SelectMaverInfo(objEBomMaterialInfos.get(i).getMaVerGd());
                        int count = 0;
                        for (int j = 0; j < objEBomMaterialInfos1.size(); j++) {
                            MaVerInfo objEaVerInfo1 = maVerDAO.SelectMaverInfo(objEBomMaterialInfos1.get(j).getMaVerGd());
                            if (objEaVerInfo.getRuid() == objEaVerInfo1.getRuid()) {
                                count++;
                                if (count > 1) {
                                    objEBomMaterialInfos1.remove(j--);
                                }
                            }
                        }
                    }

                    //记录Bom所需物料数量
                    Map<String, Float> bomMap = new HashMap<String, Float>();
                    for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
                        if(bomMap.containsKey(bomMaterialInfo.getMaVerGd())){
                            bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum() + bomMap.get(bomMaterialInfo.getMaVerGd()));
                        }else{
                            bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum());
                        }
                    }
                    //去除已开单数量
                    for(Map.Entry<String, Float> map : bomMap.entrySet()){
                        Float num = pickDtlDAO_1.SelectByAssCodeAssSourceReMaVerGd(woInfo.getWoCode(),"00", map.getKey());
                        if(num == null){ num = 0f; }
                        bomMap.put(map.getKey(), map.getValue() - num);
                    }

                    for (BomMaterialInfo obj : objEBomMaterialInfos1) {
                        GetMaTotalInfoResD objEGetMaTotalInfoResD = new GetMaTotalInfoResD();
                        MaVerInfo objEaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());

                        //应领数量
                        if(bomMap.containsKey(objEaVerInfo.getGuid())){
                            objEGetMaTotalInfoResD.setSLNum(bomMap.get(objEaVerInfo.getGuid()));
                        }
                        if (objEGetMaTotalInfoResD.getSLNum() <= 0) {
                            continue;
                        }

                        objEGetMaTotalInfoResDStore = new GetMaTotalInfoResDStore();
                        if(storeInfo != null){
                            objEGetMaTotalInfoResDStore.setStoreRd(storeInfo.getRuid());
                            objEGetMaTotalInfoResDStore.setStoreName(storeInfo.getStoreName());
                            objEGetMaTotalInfoResD.setStoreInfo(objEGetMaTotalInfoResDStore);
                        }

                        if(objEaVerInfo != null){
                            objEGetMaTotalInfoResD.setMaVerRd(objEaVerInfo.getRuid());
                            //查询明细的物料描述
                            MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(objEaVerInfo.getMaGd());
                            if(materialInfo==null&&"".equals(materialInfo)){
                                objEGetMaTotalInfoResD.setMaDes("");
                            }else {
                                objEGetMaTotalInfoResD.setMaDes(materialInfo.getMaterialDes());
                            }
                            objEGetMaTotalInfoResD.setMaCode(objEaVerInfo.getMaterialCode());
                            objEGetMaTotalInfoResD.setMaName(objEaVerInfo.getMaterialName());
                        }

                        objEGetMaTotalInfoResD.setUnitName(obj.getUnitName());
                        //float Num = woInfo.getNum() * obj.getNum();

                        //库存数量
                        InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(objEaVerInfo.getGuid(), storeInfo.getGuid());
                        if (instockInfo != null) {
                            objEGetMaTotalInfoResD.setStoreNum(instockInfo.getNum());
                        }

                        List<BomReMaterialInfo> objEBomReMaterialInfos = bomReMaterialDAO.SelectByBomMaGd(obj.getGuid());
                        List<GetMaTotalInfoResDReMa> objEGetMaTotalInfoResDReMas = new ArrayList<GetMaTotalInfoResDReMa>();
                        for (BomReMaterialInfo bomReMaterialInfo : objEBomReMaterialInfos) {
                            GetMaTotalInfoResDReMa objEGetMaTotalInfoResDReMa = new GetMaTotalInfoResDReMa();
                            MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bomReMaterialInfo.getMaVerGd());
                            if(maVerInfo != null) {
                                objEGetMaTotalInfoResDReMa.setMaVerRd(maVerInfo.getRuid());
                                objEGetMaTotalInfoResDReMa.setMaName(maVerInfo.getMaterialName());
                                objEGetMaTotalInfoResDReMa.setMaCode(maVerInfo.getMaterialCode());
                                MaterialInfo materialInfo = materialDAO.SelectByGuid(maVerInfo.getMaGd());
                                objEGetMaTotalInfoResDReMa.setMaDes(materialInfo == null ? "" : materialInfo.getMaterialDes());


                                UnitInfo unitInfo=unitDAO.SelectByGuid(maVerInfo.getUnitGd());
                                if(unitInfo!=null){
                                    objEGetMaTotalInfoResDReMa.setUnitName(unitInfo.getUnitName());
                                }

                                objEGetMaTotalInfoResDReMas.add(objEGetMaTotalInfoResDReMa);



                            }
                        }
                        objEGetMaTotalInfoResD.setReMaInfo(objEGetMaTotalInfoResDReMas);
                        objGetMaTotalInfoResDs.add(objEGetMaTotalInfoResD);
                    }
                }
            }
        }

        //根据物料版本ID查询物料信息
        objEGetMaTotalInfoResB.setData(objGetMaTotalInfoResDs);
        objEGetMaTotalInfoRes.setBody(objEGetMaTotalInfoResB);

        return objEGetMaTotalInfoRes;
    }

    //保存领料单信息
    @Override
    public SavePickInfoRes AddSavePickInfoRes(SavePickInfoReq00 argSavePickInfoReq00) {
        SavePickInfoRes objESavePickInfoRes = new SavePickInfoRes();
        SavePickInfoResB objESavePickInfoResB = new SavePickInfoResB();
        SavePickInfoResD objESavePickInfoResD = new SavePickInfoResD();

        /*if ("".equals(argSavePickInfoReq00.getStoreRd())) {
            throw new SystemException("", "新增失败，领料仓不能为空");
        }*/

        if ("".equals(argSavePickInfoReq00.getAssCode()) || argSavePickInfoReq00.getAssCode() == null) {
            throw new SystemException("", "新增失败,关联单号不能为空");
        }

        if ("".equals(argSavePickInfoReq00.getAssSource()) || argSavePickInfoReq00.getAssSource() == null) {
            throw new SystemException("", "新增失败,关联单号来源不能为空");
        }

        /*if ("".equals(argSavePickInfoReq00.getPrePickDate()) || argSavePickInfoReq00.getPrePickDate() == null) {
            throw new SystemException("", "新增失败,预领料日期不能为空");
        }*/

        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //新增主表
        PickInfo objEPickInfo = new PickInfo();
        objEPickInfo.setGuid(CommonUtils.getRandomNumber());

        String SCode = "";
        if(!"".equals(argSavePickInfoReq00.getPickCode())) {
            SCode = argSavePickInfoReq00.getPickCode();
        }else {
            //查询代码生成
            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("06");
            if(codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())){
                SCode = gConfigIService.GetCreateSC(codeRuleInfo);
            }else{
                throw new SystemException("", "请输入{领料单号}，或请到全局配置进行代码生成配置");
            }
        }
        objEPickInfo.setPickCode(SCode);

        /*List<PickInfo> pickInfos = pickDAO.SelectPickInfo();
        if (pickInfos != null && pickInfos.size() > 0) {
            for (PickInfo obj : pickInfos) {
                if (argSavePickInfoReq00.getPickCode().equals(obj.getPickCode())) {
                    throw new SystemException("", "新增失败,领料单号已存在");
                }
            }
        }*/
        PickInfo pickInfos=pickDAO.SelectPickInfoByPickCode(argSavePickInfoReq00.getPickCode());
        if(pickInfos!=null){
            throw new SystemException("", "新增失败,领料单号已存在");
        }



        objEPickInfo.setAssCode(argSavePickInfoReq00.getAssCode());
        objEPickInfo.setAssSource(argSavePickInfoReq00.getAssSource());
        objEPickInfo.setExStatus("00");//待执行
        objEPickInfo.setsStatus("00");//未下达
        if((argSavePickInfoReq00.getPrePickDate()!=null)&&(!"".equals(argSavePickInfoReq00.getPrePickDate()))){
            objEPickInfo.setPrePickDate(DateUtil.format2(argSavePickInfoReq00.getPrePickDate()));
        }

        objEPickInfo.setRemark(argSavePickInfoReq00.getRemark());
        objEPickInfo.setCreator(userName);
        objEPickInfo.setCreateTime(date);
        pickDAO.InsertPickInfo(objEPickInfo);

        //领料明细
        if (argSavePickInfoReq00.getPKMaInfo() == null || argSavePickInfoReq00.getPKMaInfo().size() <= 0) {
            throw new SystemException("", "新增失败,领料明细不能为空");
        }

        //根据关联单号查询BOM消耗
        WoInfo woInfo = wodao.SelectWoInfoBywoCode(argSavePickInfoReq00.getAssCode());
        if(woInfo == null) {
            throw new SystemException("22", "工单号不存在");
        }
        if(!("01".equals(woInfo.getStatus()) || "03".equals(woInfo.getStatus()))){
            throw new SystemException("", "工单不处于下达状态");
        }
        if("01".equals(woInfo.getpKEFlag())){
            throw new SystemException("", "领料单开单结束");
        }
        //根据bom标识查询所有的消耗物料信息
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(woInfo.getBomVerGd());
        //记录Bom所需物料数量
        Map<String, Float> bomMap = new HashMap<String, Float>();
        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            if(bomMap.containsKey(bomMaterialInfo.getMaVerGd())){
                bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum() + bomMap.get(bomMaterialInfo.getMaVerGd()));
            }else{
                bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum());
            }
        }
        //去除已开单数量
        for(Map.Entry<String, Float> map : bomMap.entrySet()){
            Float num = pickDtlDAO_1.SelectByAssCodeAssSourceReMaVerGd(woInfo.getWoCode(),"00", map.getKey());
            if(num == null){ num = 0f; }
            bomMap.put(map.getKey(), map.getValue() - num);
        }

        //替代料问题--------------------------------
        //汇总替代料信息
        //Map<String, List<Float>> reMLMap = new HashMap<>();
        //汇总物料信息
        Map<String, Float> maMap = new HashMap<>();
        //汇总
        Map<String, SavePickInfoReq00RKMa> rkMaMap = new HashMap<>();
        SavePickInfoReq00RKMa ma = null;
        for (SavePickInfoReq00RKMa rkMa : argSavePickInfoReq00.getPKMaInfo()) {
            MaVerInfo objEMaVerInfo = maVerDAO.SelectByRuid(rkMa.getMaVerRd());
            if (objEMaVerInfo == null) {
                throw new SystemException("", "新增失败，物料信息不能为空");
            }
            if (rkMa.getNum() <= 0) {
                throw new SystemException("", "领料数量失败,数量不能小于等于零");
            }

            MaVerInfo objEReMaVerInfo = maVerDAO.SelectByRuid(rkMa.getReMaVerRd());
            if(objEReMaVerInfo != null){
                if(maMap.containsKey(objEReMaVerInfo.getGuid())){
                    maMap.put(objEReMaVerInfo.getGuid(), rkMa.getNum() + maMap.get(objEReMaVerInfo.getGuid()));
                }else {
                    maMap.put(objEReMaVerInfo.getGuid(), rkMa.getNum());
                }
            }else{
                if(maMap.containsKey(objEMaVerInfo.getGuid())){
                    maMap.put(objEMaVerInfo.getGuid(), rkMa.getNum() + maMap.get(objEMaVerInfo.getGuid()));
                }else {
                    maMap.put(objEMaVerInfo.getGuid(), rkMa.getNum());
                }
            }

            if(rkMaMap.containsKey(objEMaVerInfo.getGuid() + rkMa.getStoreRd())){
                ma = rkMaMap.get(objEMaVerInfo.getGuid() + rkMa.getStoreRd());
                ma.setNum(ma.getNum() + rkMa.getNum());
                rkMaMap.put(objEMaVerInfo.getGuid() + rkMa.getStoreRd(), ma);
            }else{
                rkMaMap.put(objEMaVerInfo.getGuid() + rkMa.getStoreRd(), rkMa);
            }
        }

        //判断数量+完成
        //总的Bom物料信息
        for(String key : maMap.keySet()){
            if(!bomMap.containsKey(key)){
                throw new SystemException("", "领料失败,数量过多");
            }
            Float f = bomMap.get(key) - maMap.get(key);

            /*if (f < 0) {
                throw new SystemException("", "领料失败,数量过多");
            }else */if(f == 0){
                bomMap.put(key, 0f);
            }
        }

        boolean flag = true;
        for (Float value : bomMap.values()) {
            if (value > 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            woInfo.setpKEFlag("01");
            woInfo.setLastModifyMan(userName);
            woInfo.setLastModifyTime(date);
            if(wodao.UpdateWoInfo(woInfo) <= 0) {
                throw new SystemException("", "领料数量失败");
            }
        }

        //新增细表(pickDtlInfo_1)
        PickDtlInfo_1 objEPickDtlInfo_1 = null;
        MaVerInfo objEMaVerInfo = null;
        StoreInfo objEStoreInfo = null;
        MaVerInfo objEReMaVerInfo = null;
        for (SavePickInfoReq00RKMa rkMa : argSavePickInfoReq00.getPKMaInfo()) {
            objEPickDtlInfo_1 = new PickDtlInfo_1();
            objEPickDtlInfo_1.setGuid(CommonUtils.getRandomNumber());
            objEPickDtlInfo_1.setPickGd(objEPickInfo.getGuid());
            objEPickDtlInfo_1.setPickCode(SCode);
            objEPickDtlInfo_1.setRemark(rkMa.getRemark());
            objEMaVerInfo = maVerDAO.SelectByRuid(rkMa.getMaVerRd());
            if (objEMaVerInfo == null) {
                throw new SystemException("", "新增失败，物料信息不能为空");
            }
            objEStoreInfo = storeDAO.SelectByRuid(rkMa.getStoreRd());
            if (objEStoreInfo == null) {
                throw new SystemException("", "新增失败，领料仓不能为空");
            }
            objEPickDtlInfo_1.setStoreGd(objEStoreInfo.getGuid());

            if (rkMa.getUnitName() == null || "".equals(rkMa.getUnitName())) {
                throw new SystemException("", "新增失败，单位信息不能为空");
            }

            objEReMaVerInfo = maVerDAO.SelectByRuid(rkMa.getReMaVerRd());
            if(objEReMaVerInfo != null){
                objEPickDtlInfo_1.setReMaVerGd(objEReMaVerInfo.getGuid());
            }

            objEPickDtlInfo_1.setReML("");
            objEPickDtlInfo_1.setMaVerGd(objEMaVerInfo.getGuid());
            objEPickDtlInfo_1.setNum(rkMa.getNum());
            //objEPickDtlInfo_1.setScanNum(0.00f);
            objEPickDtlInfo_1.setUnitName(rkMa.getUnitName());

            objEPickDtlInfo_1.setCreator(userName);
            objEPickDtlInfo_1.setCreateTime(date);
            pickDtlDAO_1.InsertPickDtlInfo_1(objEPickDtlInfo_1);
        }
        //新增细表(pickDtlInfo)
        PickDtlInfo objEPickDtlInfo = null;
        InstockInfo instockInfo = null;
        for (SavePickInfoReq00RKMa rkMa : rkMaMap.values()) {
            objEPickDtlInfo = new PickDtlInfo();
            objEPickDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objEPickDtlInfo.setPickGd(objEPickInfo.getGuid());
            objEPickDtlInfo.setPickCode(SCode);
            objEPickDtlInfo.setRemark(rkMa.getRemark());
            objEMaVerInfo = maVerDAO.SelectByRuid(rkMa.getMaVerRd());
            if (objEMaVerInfo == null) {
                throw new SystemException("", "新增失败，物料信息不能为空");
            }
            objEStoreInfo = storeDAO.SelectByRuid(rkMa.getStoreRd());
            if (objEStoreInfo == null) {
                throw new SystemException("", objEMaVerInfo.getMaterialCode()+"/"+objEMaVerInfo.getMaterialName() + "物料领用仓库不存在");
            }
            objEPickDtlInfo.setStoreGd(objEStoreInfo.getGuid());
            if (rkMa.getUnitName() == null || "".equals(rkMa.getUnitName())) {
                throw new SystemException("", "新增失败，单位信息不能为空");
            }

            //库存数量比较
            instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(objEMaVerInfo.getGuid(), objEStoreInfo.getGuid());
            if (instockInfo == null) {
                throw new SystemException("", "新增失败," + objEMaVerInfo.getMaterialCode()+"/"+objEMaVerInfo.getMaterialName()+ "库存数量为零");
            }
            if (rkMa.getNum() > instockInfo.getNum()) {
                throw new SystemException("", "新增失败," + objEMaVerInfo.getMaterialCode()+"/"+objEMaVerInfo.getMaterialName()+"的数量不能大于库存的数量" + instockInfo.getNum());
            }

            objEPickDtlInfo.setReML("");
            objEPickDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());

            objEPickDtlInfo.setNum(rkMa.getNum());
            objEPickDtlInfo.setScanNum(0.00f);
            objEPickDtlInfo.setUnitName(rkMa.getUnitName());
            objEPickDtlInfo.setCreator(userName);
            objEPickDtlInfo.setCreateTime(date);
            pickDtlDAO.InsertPickDtlInfo(objEPickDtlInfo);
        }

        PickInfo  pickInfo = pickDAO.SelectPickInfoGuid(objEPickInfo.getGuid());
        objESavePickInfoResD.setPickRd(pickInfo.getRuid());
        objESavePickInfoResD.setPickCode(pickInfo.getPickCode());

        objESavePickInfoResB.setData(objESavePickInfoResD);
        objESavePickInfoRes.setBody(objESavePickInfoResB);

        return objESavePickInfoRes;
    }

    //获取出库类型信息
    @Override
    public GetCKTInfoRes GetGetCKTInfoRes() {
        GetCKTInfoRes objEGetCKTInfoRes = new GetCKTInfoRes();
        GetCKTInfoResB objEGetCKTInfoResB = new GetCKTInfoResB();
        List<GetCKTInfoResD> objEGetCKTInfoResDs = new ArrayList<GetCKTInfoResD>();

        for (int i = 0; i < 3; i++) {
            GetCKTInfoResD objEGetCKTInfoResD = new GetCKTInfoResD();
            objEGetCKTInfoResD.setCkTCode(MATYPE[i]);
            objEGetCKTInfoResD.setCkTName(TNAME[i]);
            objEGetCKTInfoResDs.add(objEGetCKTInfoResD);
        }
        objEGetCKTInfoResB.setData(objEGetCKTInfoResDs);
        objEGetCKTInfoRes.setBody(objEGetCKTInfoResB);
        return objEGetCKTInfoRes;
    }

    //获取出库单列表信息
    @Override
    public GetAllCKInfoRes GetGetAllCKInfoRes(GetAllCKInfoReq00 argGetAllCKInfoReq00) {
        GetAllCKInfoRes objEGetAllCKInfoRes = new GetAllCKInfoRes();
        GetAllCKInfoResB objEGetAllCKInfoResB = new GetAllCKInfoResB();
        List<GetAllCKInfoResD> objEGetAllCKInfoResDs = new ArrayList<GetAllCKInfoResD>();

        List<CkTkInfo> objECkTkInfos = ckTkDAO.SelectByCkShaixuan(argGetAllCKInfoReq00.getCkTCode(), argGetAllCKInfoReq00.getAssCode(), argGetAllCKInfoReq00.getExecTime(), argGetAllCKInfoReq00.getExecTime1());
        if (objECkTkInfos != null && objECkTkInfos.size() > 0) {
            for (CkTkInfo obj : objECkTkInfos) {
                GetAllCKInfoResD objEGetAllCKInfoResD = new GetAllCKInfoResD();
                objEGetAllCKInfoResD.setCkRd(obj.getRuid());
                objEGetAllCKInfoResD.setCkCode(obj.getCkCode());
                objEGetAllCKInfoResD.setAssCode(obj.getAssCode());
                if (obj.getExecor() == null) {
                    objEGetAllCKInfoResD.setExecor("");
                } else {
                    objEGetAllCKInfoResD.setExecor(obj.getExecor());
                }
                if (obj.getCancelor() == null) {
                    objEGetAllCKInfoResD.setCancelor("");
                } else {
                    objEGetAllCKInfoResD.setCancelor(obj.getCancelor());
                }
                if (obj.getFinishor() == null) {
                    objEGetAllCKInfoResD.setFinishor("");
                } else {
                    objEGetAllCKInfoResD.setFinishor(obj.getFinishor());
                }
                objEGetAllCKInfoResD.setExecTime(DateUtil.format(obj.getExecTime()));

                objEGetAllCKInfoResD.setCancelTime(DateUtil.format(obj.getCancelTime()));

                objEGetAllCKInfoResD.setFinishTime(DateUtil.format(obj.getFinishTime()));
                if ("00".equals(obj.getExStatus())) {
                    objEGetAllCKInfoResD.setExStatus("待执行");
                }
                if ("01".equals(obj.getExStatus())) {
                    objEGetAllCKInfoResD.setExStatus("进行中");
                }
                if ("02".equals(obj.getExStatus())) {
                    objEGetAllCKInfoResD.setExStatus("已完成");
                }
                if ("03".equals(obj.getExStatus())) {
                    objEGetAllCKInfoResD.setExStatus("已取消");
                }


                objEGetAllCKInfoResDs.add(objEGetAllCKInfoResD);
            }
        }
        objEGetAllCKInfoResB.setData(objEGetAllCKInfoResDs);
        objEGetAllCKInfoRes.setBody(objEGetAllCKInfoResB);
        return objEGetAllCKInfoRes;
    }

    //获取出库单明细信息
    @Override
    public GetCKDlInfoRes GetGetCKDlInfoRes(GetCKDlInfoReq00 argGetCKDlInfoRes) {
        GetCKDlInfoRes objEGetCKDlInfoRes = new GetCKDlInfoRes();
        GetCKDlInfoResB objEGetCKDlInfoResB = new GetCKDlInfoResB();
        List<GetCKDlInfoResD> objEGetCKDlInfoResDs = new ArrayList<GetCKDlInfoResD>();

        CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByRuid(argGetCKDlInfoRes.getCkRd());
        if (objECkTkInfo != null) {
            //来料单单明细信息
            if ("00".equals(objECkTkInfo.getAssSource())) {
                List<PickDtlInfo> objEPickDtlInfos = pickDtlDAO.SelectPickDtlInfosByPickCode(objECkTkInfo.getAssCode());/*inCDtlDAO.SelectInCDtlInfoByInCCode();*/
                if (objEPickDtlInfos != null || objEPickDtlInfos.size() != 0) {
                    for (PickDtlInfo obj : objEPickDtlInfos) {
                        GetCKDlInfoResD objGetCKDlInfoResD = new GetCKDlInfoResD();
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                        objGetCKDlInfoResD.setCkDtlRd(obj.getRuid());
                        objGetCKDlInfoResD.setAssSource(objECkTkInfo.getAssSource());
                        objGetCKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objGetCKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objGetCKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objGetCKDlInfoResD.setMaDes("");
                        }
                        objGetCKDlInfoResD.setNum(obj.getNum());
                        objGetCKDlInfoResD.setUnitName(obj.getUnitName());
                        objGetCKDlInfoResD.setScanNum(obj.getScanNum());
                        if (obj.getNum() - obj.getScanNum() <= 0) {
                            objGetCKDlInfoResD.setUnScanNum(0.0f);
                        } else {
                            objGetCKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                        }
                        objEGetCKDlInfoResDs.add(objGetCKDlInfoResD);
                    }
                }
            }
            if ("01".equals(objECkTkInfo.getAssSource())) {
                List<NPickDtlInfo> objEPickDtlInfos = nPickDtlDAO.SelectNPickDtlInfoByPickCode(objECkTkInfo.getAssCode());/*inCDtlDAO.SelectInCDtlInfoByInCCode();*/
                if (objEPickDtlInfos != null || objEPickDtlInfos.size() != 0) {
                    for (NPickDtlInfo obj : objEPickDtlInfos) {
                        GetCKDlInfoResD objGetCKDlInfoResD = new GetCKDlInfoResD();
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                        objGetCKDlInfoResD.setCkDtlRd(obj.getRuid());
                        objGetCKDlInfoResD.setAssSource(objECkTkInfo.getAssSource());
                        objGetCKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objGetCKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objGetCKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objGetCKDlInfoResD.setMaDes("");
                        }
                        objGetCKDlInfoResD.setNum(obj.getNum());
                        objGetCKDlInfoResD.setUnitName(obj.getUnitName());
                        objGetCKDlInfoResD.setScanNum(obj.getScanNum());
                        if (obj.getNum() - obj.getScanNum() <= 0) {
                            objGetCKDlInfoResD.setUnScanNum(0.0f);
                        } else {
                            objGetCKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                        }
                        objEGetCKDlInfoResDs.add(objGetCKDlInfoResD);
                    }
                }
            }
        }
        objEGetCKDlInfoResB.setData(objEGetCKDlInfoResDs);
        objEGetCKDlInfoRes.setBody(objEGetCKDlInfoResB);
        return objEGetCKDlInfoRes;
    }

    //导出一条任务单和明细数据
    @Override
    public ByteArrayOutputStream ExportGetCKDlInfo(GetCKDlInfoReq00[] argGetCKDlInfoRess) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetCKDlInfoResD> objEGetCKDlInfoResDs = new ArrayList<GetCKDlInfoResD>();
            for(GetCKDlInfoReq00 argGetCKDlInfoRes:argGetCKDlInfoRess){
                CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByRuid(argGetCKDlInfoRes.getCkRd());
                if (objECkTkInfo != null) {
                    //有工单领料
                    if ("00".equals(objECkTkInfo.getAssSource())) {
                        List<PickDtlInfo> objEPickDtlInfos = pickDtlDAO.SelectPickDtlInfosByPickCode(objECkTkInfo.getAssCode());/*inCDtlDAO.SelectInCDtlInfoByInCCode();*/
                        if (objEPickDtlInfos != null || objEPickDtlInfos.size() != 0) {
                            for (PickDtlInfo obj : objEPickDtlInfos) {
                                GetCKDlInfoResD objGetCKDlInfoResD = new GetCKDlInfoResD();
                                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                                //出库单列表信息
                                objGetCKDlInfoResD.setAssCode(objECkTkInfo.getAssCode());
                                if (objECkTkInfo.getExecor() == null) {
                                    objGetCKDlInfoResD.setExecor("");
                                } else {
                                    objGetCKDlInfoResD.setExecor(objECkTkInfo.getExecor());
                                }

                                objGetCKDlInfoResD.setExecTime(DateUtil.format(objECkTkInfo.getExecTime()));

                                //明细数据
                                objGetCKDlInfoResD.setCkDtlRd(obj.getRuid());
                                objGetCKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objGetCKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objGetCKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                }else{
                                    objGetCKDlInfoResD.setMaDes("");
                                }
                                objGetCKDlInfoResD.setNum(obj.getNum());
                                objGetCKDlInfoResD.setUnitName(obj.getUnitName());
                                objGetCKDlInfoResD.setScanNum(obj.getScanNum());
                                if (obj.getNum() - obj.getScanNum() <= 0) {
                                    objGetCKDlInfoResD.setUnScanNum(0.0f);
                                } else {
                                    objGetCKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                                }
                                objEGetCKDlInfoResDs.add(objGetCKDlInfoResD);
                            }
                        }
                    }
                    if ("01".equals(objECkTkInfo.getAssSource())) {
                        List<NPickDtlInfo> objENPickDtlInfos = nPickDtlDAO.SelectNPickDtlInfoByPickCode(objECkTkInfo.getAssCode());/*inCDtlDAO.SelectInCDtlInfoByInCCode();*/
                        if (objENPickDtlInfos != null || objENPickDtlInfos.size() != 0) {
                            for (NPickDtlInfo obj : objENPickDtlInfos) {
                                GetCKDlInfoResD objGetCKDlInfoResD = new GetCKDlInfoResD();
                                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                                //出库单列表信息
                                objGetCKDlInfoResD.setAssCode(objECkTkInfo.getAssCode());
                                if (objECkTkInfo.getExecor() == null) {
                                    objGetCKDlInfoResD.setExecor("");
                                } else {
                                    objGetCKDlInfoResD.setExecor(objECkTkInfo.getExecor());
                                }

                                objGetCKDlInfoResD.setExecTime(DateUtil.format(objECkTkInfo.getExecTime()));

                                //明细数据
                                objGetCKDlInfoResD.setCkDtlRd(obj.getRuid());
                                objGetCKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objGetCKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objGetCKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objGetCKDlInfoResD.setMaDes("");
                                }
                                objGetCKDlInfoResD.setNum(obj.getNum());
                                objGetCKDlInfoResD.setUnitName(obj.getUnitName());
                                objGetCKDlInfoResD.setScanNum(obj.getScanNum());
                                if (obj.getNum() - obj.getScanNum() <= 0) {
                                    objGetCKDlInfoResD.setUnScanNum(0.0f);
                                } else {
                                    objGetCKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                                }

                                objEGetCKDlInfoResDs.add(objGetCKDlInfoResD);
                            }
                        }
                    }
                }
            }
            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"单号", "物料代码", "物料名称","物料描述", "出库数量", "已出库数量", "未出库数量", "单位", "出库人", "出库开始时间"}};

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


            /**********************************库存变动数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < objEGetCKDlInfoResDs.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getAssCode());
                    } else if (j == 1) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getMaCode());
                    } else if (j == 2) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getMaName());
                    } else if (j == 3) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getMaDes());
                    }else if (j == 4) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getNum());
                    } else if (j == 5) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getScanNum());
                    } else if (j == 6) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getUnScanNum());
                    } else if (j == 7) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getUnitName());
                    } else if (j == 8) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getExecor());
                    } else if (j == 9) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getExecTime());
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

    //导出所有出库数据
    @Override
    public ByteArrayOutputStream ExportGetCKDlInfo1(GetAllCKInfoReq00 argGetAllCKInfoReq00) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {

            List<GetCKDlInfoResD> objEGetCKDlInfoResDs = new ArrayList<GetCKDlInfoResD>();

            List<CkTkInfo> objECkTkInfo = ckTkDAO.SelectByCkShaixuan(argGetAllCKInfoReq00.getCkTCode(), argGetAllCKInfoReq00.getAssCode(), argGetAllCKInfoReq00.getExecTime(), argGetAllCKInfoReq00.getExecTime1());
            if (objECkTkInfo != null && objECkTkInfo.size()>0) {
                for (CkTkInfo obj1 : objECkTkInfo) {
                    //有工单领料
                    if ("00".equals(obj1.getAssSource())) {
                        List<PickDtlInfo> objEPickDtlInfos = pickDtlDAO.SelectPickDtlInfosByPickCode(obj1.getAssCode());/*inCDtlDAO.SelectInCDtlInfoByInCCode();*/
                        if (objEPickDtlInfos != null || objEPickDtlInfos.size() != 0) {
                            for (PickDtlInfo obj : objEPickDtlInfos) {
                                GetCKDlInfoResD objGetCKDlInfoResD = new GetCKDlInfoResD();
                                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                                //出库单列表信息
                                objGetCKDlInfoResD.setAssCode(obj1.getAssCode());
                                if (obj1.getExecor() == null) {
                                    objGetCKDlInfoResD.setExecor("");
                                } else {
                                    objGetCKDlInfoResD.setExecor(obj1.getExecor());
                                }

                                objGetCKDlInfoResD.setExecTime(DateUtil.format(obj1.getExecTime()));

                                //明细数据
                                objGetCKDlInfoResD.setCkDtlRd(obj.getRuid());
                                objGetCKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objGetCKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objGetCKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objGetCKDlInfoResD.setMaDes("");
                                }
                                objGetCKDlInfoResD.setNum(obj.getNum());
                                objGetCKDlInfoResD.setUnitName(obj.getUnitName());
                                objGetCKDlInfoResD.setScanNum(obj.getScanNum());
                                if (obj.getNum() - obj.getScanNum() <= 0) {
                                    objGetCKDlInfoResD.setUnScanNum(0.0f);
                                } else {
                                    objGetCKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                                }
                                if ("00".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("待执行");
                                }
                                if ("01".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("进行中");
                                }
                                if ("02".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("已完成");
                                }
                                if ("03".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("已取消");
                                }

                                objEGetCKDlInfoResDs.add(objGetCKDlInfoResD);
                            }
                        }
                    }
                    //无工单领料出库
                    if ("01".equals(obj1.getAssSource())) {
                        List<NPickDtlInfo> objENPickDtlInfos = nPickDtlDAO.SelectNPickDtlInfoByPickCode(obj1.getAssCode());/*inCDtlDAO.SelectInCDtlInfoByInCCode();*/
                        if (objENPickDtlInfos != null || objENPickDtlInfos.size() != 0) {
                            for (NPickDtlInfo obj : objENPickDtlInfos) {
                                GetCKDlInfoResD objGetCKDlInfoResD = new GetCKDlInfoResD();
                                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                                //出库单列表信息
                                objGetCKDlInfoResD.setAssCode(obj1.getAssCode());
                                if (obj1.getExecor() == null) {
                                    objGetCKDlInfoResD.setExecor("");
                                } else {
                                    objGetCKDlInfoResD.setExecor(obj1.getExecor());
                                }

                                objGetCKDlInfoResD.setExecTime(DateUtil.format(obj1.getExecTime()));

                                //明细数据
                                objGetCKDlInfoResD.setCkDtlRd(obj.getRuid());
                                objGetCKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objGetCKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objGetCKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objGetCKDlInfoResD.setMaDes("");
                                }
                                objGetCKDlInfoResD.setNum(obj.getNum());
                                objGetCKDlInfoResD.setUnitName(obj.getUnitName());
                                objGetCKDlInfoResD.setScanNum(obj.getScanNum());
                                if (obj.getNum() - obj.getScanNum() <= 0) {
                                    objGetCKDlInfoResD.setUnScanNum(0.0f);
                                } else {
                                    objGetCKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                                }
                                if ("00".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("待执行");
                                }
                                if ("01".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("进行中");
                                }
                                if ("02".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("已完成");
                                }
                                if ("03".equals(obj1.getExStatus())) {
                                    objGetCKDlInfoResD.setExStatus("已取消");
                                }

                                objEGetCKDlInfoResDs.add(objGetCKDlInfoResD);
                            }
                        }
                    }
                }
            }

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"执行状态","单号", "物料代码", "物料名称","物料描述", "出库数量", "已出库数量", "未出库数量", "单位", "出库人", "出库开始时间"}};

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


            /**********************************库存变动数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < objEGetCKDlInfoResDs.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getExStatus());
                    } else if (j == 1) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getAssCode());
                    } else if (j == 2) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getMaCode());
                    } else if (j == 3) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getMaName());
                    }else if (j == 4) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getMaDes());
                    } else if (j == 5) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getNum());
                    } else if (j == 6) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getScanNum());
                    } else if (j == 7) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getUnScanNum());
                    } else if (j == 8) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getUnitName());
                    } else if (j == 9) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getExecor());
                    }else if (j == 10) {
                        cell3.setCellValue(objEGetCKDlInfoResDs.get(i).getExecTime());
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

    //获取扫描出库信息
    @Override
    public GetCKBarInfoRes GetGetCKBarInfoRes(GetCKBarInfoReq00 argGetCKBarInfoRes) {
        GetCKBarInfoRes objEGetCKBarInfoRes = new GetCKBarInfoRes();
        GetCKBarInfoResB objEGetCKBarInfoResB = new GetCKBarInfoResB();
        List<GetCKBarInfoResD> objEGetCKBarInfoResDs = new ArrayList<GetCKBarInfoResD>();
//采购单明细信息
        if ("00".equals(argGetCKBarInfoRes.getAssSource())) {
            PickDtlInfo objEPurChDtlInfo = pickDtlDAO.SelectPickDtlInfoByRuid(argGetCKBarInfoRes.getCkDtlRd());
            if (objEPurChDtlInfo != null) {
                List<CkDtlInfo> objECkDtlInfo = ckDtlDAO.SelectCkDtlInfoByCkTkDtlGd(objEPurChDtlInfo.getGuid());
                if (objECkDtlInfo != null && objECkDtlInfo.size() > 0) {
                    for (CkDtlInfo obj : objECkDtlInfo) {
                        GetCKBarInfoResD objEGetGetCKBarInfoResD = new GetCKBarInfoResD();
                        objEGetGetCKBarInfoResD.setNum(obj.getNum());
                        objEGetGetCKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetGetCKBarInfoResD.setProductDate("");
                        objEGetGetCKBarInfoResD.setExpireDate("");
                        objEGetGetCKBarInfoResD.setRemark(obj.getRemark());
                        //扫描时间，即创建时间
                        objEGetGetCKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        objEGetCKBarInfoResDs.add(objEGetGetCKBarInfoResD);
                    }
                }
            }
        }

        if ("01".equals(argGetCKBarInfoRes.getAssSource())) {
            NPickDtlInfo objEInCDtlInfo = nPickDtlDAO.SelectNPickDtlInfoByRuid(argGetCKBarInfoRes.getCkDtlRd());
            if (objEInCDtlInfo != null) {
                List<CkDtlInfo> objECkDtlInfo = ckDtlDAO.SelectCkDtlInfoByCkTkDtlGd(objEInCDtlInfo.getGuid());
                if (objECkDtlInfo != null && objECkDtlInfo.size() > 0) {
                    for (CkDtlInfo obj : objECkDtlInfo) {
                        GetCKBarInfoResD objEGetGetCKBarInfoResD = new GetCKBarInfoResD();
                        objEGetGetCKBarInfoResD.setNum(obj.getNum());
                        objEGetGetCKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetGetCKBarInfoResD.setProductDate("");
                        objEGetGetCKBarInfoResD.setExpireDate("");
                        objEGetGetCKBarInfoResD.setRemark(obj.getRemark());
                        //扫描时间，即创建时间
                        objEGetGetCKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        objEGetCKBarInfoResDs.add(objEGetGetCKBarInfoResD);
                    }
                }
            }
        }
        objEGetCKBarInfoResB.setData(objEGetCKBarInfoResDs);
        objEGetCKBarInfoRes.setBody(objEGetCKBarInfoResB);
        return objEGetCKBarInfoRes;
    }

    //取消出库信息
    @Override
    public SaveCKInfoRes GetSaveCKInfoRes(SaveCKInfoReq03 argSaveCKInfoReq03) {
        SaveCKInfoRes objESaveCKInfoRes = new SaveCKInfoRes();
        SaveCKInfoResB objESaveCKInfoResB = new SaveCKInfoResB();
        SaveCKInfoResD objESaveCKInfoResD = new SaveCKInfoResD();
        CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByRuid(argSaveCKInfoReq03.getCkRd());
        if ("01".equals(objECkTkInfo.getExStatus())) {
            objECkTkInfo.setCancelor(CommonUtils.readUser().getRealName());
            objECkTkInfo.setCancelTime(new Date());
            objECkTkInfo.setExStatus("03");
            if (ckTkDAO.UpdateCkTkInfo(objECkTkInfo) <= 0) {
                throw new SystemException("xxx", "取消出库任务信息失败");
            }
        } else {
            throw new SystemException("xxx01", "取消失败，因为状态不符合");
        }
        objESaveCKInfoResB.setData(objESaveCKInfoResD);
        objESaveCKInfoRes.setBody(objESaveCKInfoResB);
        return objESaveCKInfoRes;
    }

    //删除出库单信息
    @Override
    public SaveCKInfoRes RmSaveCKInfoRes(SaveCKInfoReq01 argSaveCKInfoReq01) {
        SaveCKInfoRes objESaveCKInfoRes = new SaveCKInfoRes();
        SaveCKInfoResB objESaveCKInfoResB = new SaveCKInfoResB();
        SaveCKInfoResD objESaveCKInfoResD = new SaveCKInfoResD();
        CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByRuid(argSaveCKInfoReq01.getCkRd());
        if ("00".equals(objECkTkInfo.getExStatus()) || "03".equals(objECkTkInfo.getExStatus())) {
            if (ckTkDAO.DeleteCkTkInfoByRuid(objECkTkInfo.getRuid()) <= 0) {
                throw new SystemException("xxx2", "删除出库信息失败");
            }
        } else {
            throw new SystemException("xxx3", "删除失败,原因是执行状态已完成或者正在进行中!");
        }

        objESaveCKInfoResB.setData(objESaveCKInfoResD);
        objESaveCKInfoRes.setBody(objESaveCKInfoResB);
        return objESaveCKInfoRes;
    }
}
