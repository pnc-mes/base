package pnc.mesadmin.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoRes;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoResB;
import pnc.mesadmin.dto.GetAllNPickInfo.GetAllNPickInfoResD;
import pnc.mesadmin.dto.GetAllPickInfo.GetAllPickInfoRes;
import pnc.mesadmin.dto.GetAllPickInfo.GetAllPickInfoResB;
import pnc.mesadmin.dto.GetAllPickInfo.GetAllPickInfoResD;
import pnc.mesadmin.dto.GetMaInfo.GetMaInfoResDMaPropertyInfo;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoReq00;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoRes;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoResB;
import pnc.mesadmin.dto.GetNMaTotalInfo.GetNMaTotalInfoResD;
import pnc.mesadmin.dto.GetNPickInfo.*;
import pnc.mesadmin.dto.GetPickInfo.*;
import pnc.mesadmin.dto.GetPickInfoResDExcel.GetPickInfoResDExcel;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveImportRes.SaveImportResB;
import pnc.mesadmin.dto.SaveNPickInfo.*;
import pnc.mesadmin.dto.SavePickInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.PickIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by PNC on 2017/9/13.
 */
@Service
@Transactional
public class PickService implements PickIService {
    @Resource
    private PickDAO pickDAO;

    @Resource
    private PickDtlDAO pickDtlDAO;

    @Resource
    private PickDtlDAO_1 pickDtlDAO_1;

    @Resource
    private NPickDAO nPickDAO;

    @Resource
    private NPickDtlDAO nPickDtlDAO;

    @Resource
    private NPickDtlDAO_1 nPickDtlDAO_1;

    @Resource
    private BomMaterialDAO bomMaterialDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private WODAO wodao;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private BomReMaterialDAO bomReMaterialDAO;

    @Resource
    private CkTkDAO ckTkDAO;
    @Resource
    private BaseIService baseIService;

    @Resource
    private InstockDAO instockDAO;

    @Resource
    private UserDAO userDAO;

    @Resource
    private InstockDtlDAO instockDtlDAO;

    @Resource
    private LocationDAO locationDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    private MaPropertyDAO maPropertyDAO;

    @Resource
    private ExpandDtlDAO expandDtlDAO;

    @Resource
    private CusDataDtlCDAO cusDataDtlCDAO;

    //获取领料单列表信息
    @Override
    public GetAllPickInfoRes QALLGetAllPickInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllPickInfoRes objEGetAllPickInfoRes = new GetAllPickInfoRes();
        GetAllPickInfoResB objEGetAllPickInfoResB = new GetAllPickInfoResB();
        List<GetAllPickInfoResD> objEGetAllPickInfoResDs = new ArrayList<GetAllPickInfoResD>();

        List<PickInfo> objEPickInfos =
                baseIService.QALLBaseInfo(PickDAO.class, "SelectPickInfo",
                        argInitDataFields, argPageInfo);


        //  pickDAO.SelectPickInfo();
        if (objEPickInfos != null || objEPickInfos.size() != 0) {
            for (PickInfo obj : objEPickInfos) {
                GetAllPickInfoResD objEGetAllPickInfoResD = new GetAllPickInfoResD();
                objEGetAllPickInfoResD.setPickRd(obj.getRuid());
                objEGetAllPickInfoResD.setPickCode(obj.getPickCode());
                objEGetAllPickInfoResDs.add(objEGetAllPickInfoResD);
            }
        }
        objEGetAllPickInfoResB.setData(objEGetAllPickInfoResDs);
        objEGetAllPickInfoRes.setBody(objEGetAllPickInfoResB);
        return objEGetAllPickInfoRes;
    }

    //获取领料单信息
    @Override
    public GetPickInfoRes GetGetPickInfoRes(GetPickInfoReq00 argGetPickInfoReq00) {
        GetPickInfoRes objEGetPickInfoRes = new GetPickInfoRes();
        GetPickInfoResB objEGetPickInfoResB = new GetPickInfoResB();
        GetPickInfoResD objEGetPickInfoResD = new GetPickInfoResD();
        GetPickInfoResDMaInfo objEGetPickInfoResDMaInfo = new GetPickInfoResDMaInfo();

        PickInfo objEPickInfo = pickDAO.SelectPickInfoByRuid(argGetPickInfoReq00.getPickRd());
        if (objEPickInfo == null) {
            throw new SystemException("", "查询领料单信息失败，该信息不存在");
        }

        if (objEPickInfo != null) {
            objEGetPickInfoResD.setPickRd(objEPickInfo.getRuid());
            objEGetPickInfoResD.setPickCode(objEPickInfo.getPickCode());
            objEGetPickInfoResD.setAssCode(objEPickInfo.getAssCode());
            objEGetPickInfoResD.setAssSource(objEPickInfo.getAssSource());
            objEGetPickInfoResD.setDSource(objEPickInfo.getdSource());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if ((objEPickInfo.getPrePickDate() != null) && (!"".equals(objEPickInfo.getPrePickDate()))) {
                objEGetPickInfoResD.setPrePickDate(sdf.format(objEPickInfo.getPrePickDate()));
            } else {
                objEGetPickInfoResD.setPrePickDate("");
            }

            objEGetPickInfoResD.setExStatus(objEPickInfo.getExStatus());
            objEGetPickInfoResD.setSStatus(objEPickInfo.getsStatus());

            objEGetPickInfoResD.setCreator(objEPickInfo.getCreator());
            objEGetPickInfoResD.setCreateTime(DateUtil.format(objEPickInfo.getCreateTime()));
            objEGetPickInfoResD.setLastModifyMan(objEPickInfo.getLastModifyMan());
            objEGetPickInfoResD.setLastModifyTime(DateUtil.format(objEPickInfo.getLastModifyTime()));
            objEGetPickInfoResD.setRemark(objEPickInfo.getRemark());
            objEGetPickInfoResD.setExStatus(objEPickInfo.getExStatus());

            WoInfo woInfo = null;
            if ("00".equals(objEPickInfo.getAssSource())) {
                woInfo = wodao.SelectWoInfoBywoCode(objEPickInfo.getAssCode());
                if (woInfo != null) {
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(woInfo.getMaVerGd());
                    objEGetPickInfoResDMaInfo.setMaVerRd(maVerInfo.getRuid());
                    objEGetPickInfoResDMaInfo.setMaName(maVerInfo.getMaterialName());
                    objEGetPickInfoResDMaInfo.setMaCode(maVerInfo.getMaterialCode());
                    objEGetPickInfoResD.setMaInfo(objEGetPickInfoResDMaInfo);
                    objEGetPickInfoResD.setNum(woInfo.getNum());
                } else {
                    throw new SystemException("", "查询失败，该工单不存在");
                }

                UnitInfo objEUnitInfo = unitDAO.SelectByGuid(woInfo.getUnitGd());
                if (objEUnitInfo != null) {
                    objEGetPickInfoResD.setUnitName(objEUnitInfo.getUnitName());
                }
            } else if ("01".equals(objEPickInfo.getAssSource())) {
                BInfo bInfo = bdao.selectBatchInfoByBatch(objEPickInfo.getAssCode());
                if (bInfo != null) {
                    MaVerInfo maVerInfo1 = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                    objEGetPickInfoResDMaInfo.setMaVerRd(maVerInfo1.getRuid());
                    objEGetPickInfoResDMaInfo.setMaName(maVerInfo1.getMaterialName());
                    objEGetPickInfoResDMaInfo.setMaCode(maVerInfo1.getMaterialCode());
                    objEGetPickInfoResD.setMaInfo(objEGetPickInfoResDMaInfo);
                }
                objEGetPickInfoResD.setNum(bInfo.getNum());

                UnitInfo objEUnitInfo = unitDAO.SelectByGuid(bInfo.getUnitGd());
                if (objEUnitInfo != null) {
                    objEGetPickInfoResD.setUnitName(objEUnitInfo.getUnitName());
                }
            }

            List<PickDtlInfo_1> pickDtlInfos_1 = pickDtlDAO_1.SelectPickDtlInfoByPickGd_1(objEPickInfo.getGuid());
            if (pickDtlInfos_1.size() > 0) {
                List<GetPickInfoResDPickDl> objEGetPickInfoResDPickDls = new ArrayList<GetPickInfoResDPickDl>();
                //float number = 0.0f;

                //根据bom标识查询所有的消耗物料信息
                List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(woInfo.getBomVerGd());
                //记录Bom所需物料数量
                Map<String, Float> bomMap = new HashMap<String, Float>();
                for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
                    if (bomMap.containsKey(bomMaterialInfo.getMaVerGd())) {
                        bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum() + bomMap.get(bomMaterialInfo.getMaVerGd()));
                    } else {
                        bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum());
                    }
                }
                //去除已开单数量
                for (Map.Entry<String, Float> map : bomMap.entrySet()) {
                    Float num = pickDtlDAO_1.SelectByAssCodeAssSourceReMaVerGd(woInfo.getWoCode(), "00", map.getKey());
                    if (num == null) {
                        num = 0f;
                    }
                    bomMap.put(map.getKey(), map.getValue() - num);
                }

                MaVerInfo maVerInfo = null;
                MaVerInfo reMaVerInfo = null;
                for (PickDtlInfo_1 oo : pickDtlInfos_1) {
                    GetPickInfoResDPickDl objEGetPickInfoResDPickDl = new GetPickInfoResDPickDl();

                    objEGetPickInfoResDPickDl.setPKDtlRd(oo.getRuid());
                    maVerInfo = maVerDAO.SelectMaverInfo(oo.getMaVerGd());

                    //SLNum 应领数量
                    objEGetPickInfoResDPickDl.setSLNum(0f);
                    if (oo.getReMaVerGd() != null && !"".equals(oo.getReMaVerGd())) {
                        reMaVerInfo = maVerDAO.SelectMaverInfo(oo.getReMaVerGd());
                        if (bomMap.containsKey(oo.getReMaVerGd())) {
                            objEGetPickInfoResDPickDl.setSLNum(bomMap.get(oo.getReMaVerGd()));
                        }
                    } else {
                        if (bomMap.containsKey(oo.getMaVerGd())) {
                            objEGetPickInfoResDPickDl.setSLNum(bomMap.get(oo.getMaVerGd()));
                        }
                    }

                    if (maVerInfo != null) {
                        objEGetPickInfoResDPickDl.setMaVerRd(maVerInfo.getRuid());
                        //查询明细的物料描述
                        MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                        if (materialInfo == null && "".equals(materialInfo)) {
                            objEGetPickInfoResDPickDl.setMaDes("");
                        } else {
                            if (materialInfo.getMaterialDes() == null || "".equals(materialInfo.getMaterialDes())) {
                                objEGetPickInfoResDPickDl.setMaDes("");
                            } else {
                                objEGetPickInfoResDPickDl.setMaDes(materialInfo.getMaterialDes());
                            }
                            objEGetPickInfoResDPickDl.setMaName(materialInfo.getMaterialName());
                            objEGetPickInfoResDPickDl.setMaCode(materialInfo.getMaterialCode() +
                                    (reMaVerInfo == null ? "" : "(" + reMaVerInfo.getMaterialCode() + ")"));
                        }
                    }

                    StoreInfo objEStoreInfo = storeDAO.SelectByguid(oo.getStoreGd());
                    if (objEStoreInfo != null) {
                        GetPickInfoResDPStore objEGetPickInfoResDPStore = new GetPickInfoResDPStore();
                        objEGetPickInfoResDPStore.setStoreRd(objEStoreInfo.getRuid());
                        objEGetPickInfoResDPStore.setStoreName(objEStoreInfo.getStoreName());
                        objEGetPickInfoResDPickDl.setStoreInfo(objEGetPickInfoResDPStore);
                    }

                    InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), oo.getStoreGd());
                    if (instockInfo != null) {
                        //库存数量
                        objEGetPickInfoResDPickDl.setStoreNum(instockInfo.getNum());
                    } else {
                        objEGetPickInfoResDPickDl.setStoreNum(0f);
                    }

                    //UNNum 领用数量
                    objEGetPickInfoResDPickDl.setUNNum(oo.getNum());

                    objEGetPickInfoResDPickDl.setUnitName(oo.getUnitName());

                    if (oo.getRemark() == null || "".equals(oo.getRemark())) {
                        objEGetPickInfoResDPickDl.setRemark("");
                    } else {
                        objEGetPickInfoResDPickDl.setRemark(oo.getRemark());
                    }
                    //应领数量计算SLNum  工单下该物料总共领用数量-已开领料单该物料总数-已开领料单该物料替代料总数

                    //已发数量LQNum


                    List<BomReMaterialInfo> objEBomReMaterialInfos = bomReMaterialDAO.SelectByMaVerGd(oo.getMaVerGd());
                    List<GetPickInfoResDPReMa> objEGetPickInfoResDPReMas = new ArrayList<GetPickInfoResDPReMa>();
                    MaterialInfo objEMaterialInfo = null;
                    MaVerInfo objEMaVerInfo = null;
                    for (BomReMaterialInfo oob : objEBomReMaterialInfos) {
                        GetPickInfoResDPReMa getPickInfoResDPReMa = new GetPickInfoResDPReMa();
                        objEMaVerInfo = maVerDAO.SelectMaverInfo(oob.getMaVerGd());
                        if (objEMaVerInfo != null) {
                            getPickInfoResDPReMa.setMaVerRd(objEMaVerInfo.getRuid());
                            getPickInfoResDPReMa.setMaName(objEMaVerInfo.getMaterialName());
                            getPickInfoResDPReMa.setMaCode(objEMaVerInfo.getMaterialCode());
                            objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                            if (objEMaterialInfo == null) {
                                getPickInfoResDPReMa.setMaDes("");
                            } else {
                                getPickInfoResDPReMa.setMaDes(objEMaterialInfo.getMaterialDes());
                            }
                            objEGetPickInfoResDPReMas.add(getPickInfoResDPReMa);
                        }
                    }
                    objEGetPickInfoResDPickDl.setReMaInfo(objEGetPickInfoResDPReMas);
                    objEGetPickInfoResDPickDls.add(objEGetPickInfoResDPickDl);
                }
                objEGetPickInfoResD.setPickDlInfo(objEGetPickInfoResDPickDls);
            }
        }

        objEGetPickInfoResB.setData(objEGetPickInfoResD);
        objEGetPickInfoRes.setBody(objEGetPickInfoResB);

        return objEGetPickInfoRes;
    }

    //删除领料信息
    @Override
    public SavePickInfoRes RmSavePickInfoRes(SavePickInfoReq01 argSavePickInfoReq01) {
        SavePickInfoRes objESavePickInfoRes = new SavePickInfoRes();
        SavePickInfoResB objESSavePickInfoResB = new SavePickInfoResB();

        PickInfo pickInfo = pickDAO.SelectPickInfoByRuid(argSavePickInfoReq01.getPickRd());
        if (pickInfo == null) {
            throw new SystemException("", "删除失败，该信息不存在");
        }

        if (!"00".equals(pickInfo.getsStatus())) {
            throw new SystemException("", "领料单审核状态不是未下达，不允许删除操作");
        }

        //删除领用明细
        if (pickDtlDAO_1.DeletePickDtlByPickGd(pickInfo.getGuid()) <= 0) {
            throw new SystemException("", "删除领料单明细失败");
        }

        List<PickDtlInfo> pickDtlInfos = pickDtlDAO.SelectPickDtlInfoByPickGd(pickInfo.getGuid());
        for (PickDtlInfo obj : pickDtlInfos) {
            if (pickDtlDAO.DeletePickDtlInfoByRuid(obj.getRuid()) <= 0) {
                throw new SystemException("", "删除领料单明细失败");
            }
        }
        if (pickDAO.DeletePickInfoByRuid(pickInfo.getRuid()) <= 0) {
            throw new SystemException("", "删除领料单失败");
        }

        //查询工单号
        WoInfo woInfo = wodao.SelectWoInfoBywoCode(pickInfo.getAssCode());
        if (woInfo != null) {
            //setpKEFlag
            if ("01".equals(woInfo.getpKEFlag())) {
                woInfo.setpKEFlag("00");
                if (wodao.UpdateWoInfo(woInfo) <= 0) {
                    throw new SystemException("", "更新工单数量失败");
                }
            }
        }

        /*CkTkInfo ckDtlInfo = ckTkDAO.SelectCkTkInfoByAssCode(pickInfo.getPickCode());
        if (ckDtlInfo != null) {
            if (ckTkDAO.DeleteCkTkInfoByRuid(ckDtlInfo.getRuid()) <= 0) {
                throw new SystemException("", "删除出库信息失败");
            }
        }*/

        objESavePickInfoRes.setBody(objESSavePickInfoResB);
        return objESavePickInfoRes;
    }

    //修改领料单信息
    @Override
    public SavePickInfoRes ModSavePickInfoRes(SavePickInfoReq02 argSavePickInfoReq02) {
        SavePickInfoRes objESavePickInfoRes = new SavePickInfoRes();
        SavePickInfoResB objESSavePickInfoResB = new SavePickInfoResB();

        PickInfo pickInfo = pickDAO.SelectPickInfoByRuid(argSavePickInfoReq02.getPickRd());
        if (pickInfo == null) {
            throw new SystemException("", "修改失败，该信息不存在");
        }
        if ("01".equals(pickInfo.getsStatus())) {
            throw new SystemException("", "领料单已经下达，不允许修改");
        }

        //用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        WoInfo woInfo = wodao.SelectWoInfoBywoCode(pickInfo.getAssCode());
        if (woInfo == null) {
            throw new SystemException("22", "工单号不存在");
        }

        if ((argSavePickInfoReq02.getPrePickDate()) != null && (!"".equals(argSavePickInfoReq02.getPrePickDate()))) {
            pickInfo.setPrePickDate(DateUtil.format2(argSavePickInfoReq02.getPrePickDate()));
        }
        pickInfo.setRemark(argSavePickInfoReq02.getRemark());
        pickInfo.setLastModifyMan(userName);
        pickInfo.setLastModifyTime(date);
        if (pickDAO.UpdatePickInfo(pickInfo) <= 0) {
            throw new SystemException("", "更新领料单信息失败");
        }

        //根据bom标识查询所有的消耗物料信息
        List<BomMaterialInfo> objEBomMaterialInfos = bomMaterialDAO.SelectByBomVerGd(woInfo.getBomVerGd());
        //记录Bom所需物料数量
        Map<String, Float> bomMap = new HashMap<String, Float>();
        for (BomMaterialInfo bomMaterialInfo : objEBomMaterialInfos) {
            if (bomMap.containsKey(bomMaterialInfo.getMaVerGd())) {
                bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum() + bomMap.get(bomMaterialInfo.getMaVerGd()));
            } else {
                bomMap.put(bomMaterialInfo.getMaVerGd(), woInfo.getNum() * bomMaterialInfo.getNum());
            }
        }
        //去除已开单数量
        for (Map.Entry<String, Float> map : bomMap.entrySet()) {
            Float num = pickDtlDAO_1.SelectByAssCodeAssSourceReMaVerGd(woInfo.getWoCode(), "00", map.getKey());
            if (num == null) {
                num = 0f;
            }
            bomMap.put(map.getKey(), map.getValue() - num);
        }

        //领料明细(old)
        List<PickDtlInfo_1> pickDtlInfos_1 = pickDtlDAO_1.SelectPickDtlInfoByPickGd_1(pickInfo.getGuid());
        Map<Integer, PickDtlInfo_1> pickDtlInfoMap_1 = new HashMap<>();

        for (PickDtlInfo_1 obj : pickDtlInfos_1) {
            pickDtlInfoMap_1.put(obj.getRuid(), obj);
        }
        //记录物料数量
        Map<String, Float> maMap = new HashMap<>();
        //记录替代料数量
        //Map<String, List<Float>> reMLMap = new HashMap<>();
        //记录汇总信息
        Map<String, SavePickInfoReq02RKMa> rkMaMap = new HashMap<>();

        PickDtlInfo_1 pickDtlInfo_1 = null;
        MaVerInfo maVerInfo = null;
        SavePickInfoReq02RKMa ma = null;
        StoreInfo objEStoreInfo = null;
        MaVerInfo reMaVerInfo = null;
        for (SavePickInfoReq02RKMa rkMa : argSavePickInfoReq02.getPKMaInfo()) {
            Float num_ = 0f;
            maVerInfo = maVerDAO.SelectByRuid(rkMa.getMaVerRd());
            if (maVerInfo == null) {
                throw new SystemException("", "ID为" + rkMa.getMaVerRd() + "的物料不存在");
            }
            objEStoreInfo = storeDAO.SelectByRuid(rkMa.getStoreRd());
            if (objEStoreInfo == null) {
                throw new SystemException("", maVerInfo.getMaterialName() + "物料领用仓库不存在");
            }
            reMaVerInfo = maVerDAO.SelectByRuid(rkMa.getReMaVerRd());

            if (pickDtlInfoMap_1.containsKey(rkMa.getPKDtlRd())) {
                //更新
                pickDtlInfo_1 = pickDtlInfoMap_1.get(rkMa.getPKDtlRd());
                num_ = pickDtlInfo_1.getNum();
                pickDtlInfo_1.setNum(rkMa.getNum());
                pickDtlInfo_1.setStoreGd(objEStoreInfo.getGuid());
                pickDtlInfo_1.setLastModifyMan(userName);
                pickDtlInfo_1.setLastModifyTime(date);
                pickDtlInfo_1.setRemark(rkMa.getRemark());

                if (pickDtlDAO_1.UpdatePickDtlInfo_1(pickDtlInfo_1) <= 0) {
                    throw new SystemException("", "更新明细失败");
                }

                pickDtlInfoMap_1.remove(rkMa.getPKDtlRd());
            } else {
                //新增
                pickDtlInfo_1 = new PickDtlInfo_1();
                pickDtlInfo_1.setGuid(CommonUtils.getRandomNumber());
                pickDtlInfo_1.setPickGd(pickInfo.getGuid());
                pickDtlInfo_1.setPickCode(pickInfo.getPickCode());
                pickDtlInfo_1.setMaVerGd(maVerInfo.getGuid());
                if (reMaVerInfo != null) {
                    pickDtlInfo_1.setReMaVerGd(reMaVerInfo.getGuid());
                }
                pickDtlInfo_1.setReML("");
                pickDtlInfo_1.setNum(rkMa.getNum());
                pickDtlInfo_1.setUnitName(rkMa.getUnitName());
                pickDtlInfo_1.setStoreGd(objEStoreInfo.getGuid());
                pickDtlInfo_1.setCreator(userName);
                pickDtlInfo_1.setCreateTime(date);
                pickDtlDAO_1.InsertPickDtlInfo_1(pickDtlInfo_1);
            }

            /*if (!maVerInfo.getGuid().equals(rkMa.getReML())) {
                List<Float> list = new ArrayList<>();
                list.add(rkMa.getNum() - num_);

                String[] ss = rkMa.getReML().split("\\|");
                for (String s : ss) {
                    if (reMLMap.containsKey(s)) {
                        List<Float> list1 = new ArrayList<>();
                        list1.add(rkMa.getNum() + reMLMap.get(s).get(0));
                        reMLMap.put(s, list1);
                    } else {
                        reMLMap.put(s, list);
                    }
                }
            } else {
                if (maMap.containsKey(maVerInfo.getGuid())) {
                    maMap.put(maVerInfo.getGuid(), rkMa.getNum() + maMap.get(maVerInfo.getGuid()));

                    ma = rkMaMap.get(maVerInfo.getGuid());
                    if (!ma.getReML().equals(rkMa.getReML())) {
                        ma.setReML(ma.getReML() + "|" + rkMa.getReML());
                    }
                    ma.setNum(ma.getNum() + rkMa.getNum());
                    rkMaMap.put(maVerInfo.getGuid(), ma);
                } else {
                    maMap.put(maVerInfo.getGuid(), rkMa.getNum() - num_);
                    rkMaMap.put(maVerInfo.getGuid(), rkMa);
                }
            }*/
            if (reMaVerInfo == null) {
                if (maMap.containsKey(maVerInfo.getGuid())) {
                    maMap.put(maVerInfo.getGuid(), rkMa.getNum() + maMap.get(maVerInfo.getGuid()));
                } else {
                    maMap.put(maVerInfo.getGuid(), rkMa.getNum() - num_);
                }
            } else {
                if (maMap.containsKey(reMaVerInfo.getGuid())) {
                    maMap.put(reMaVerInfo.getGuid(), rkMa.getNum() + maMap.get(reMaVerInfo.getGuid()));
                } else {
                    maMap.put(reMaVerInfo.getGuid(), rkMa.getNum() - num_);
                }
            }
            if (rkMaMap.containsKey(maVerInfo.getGuid() + rkMa.getStoreRd())) {
                ma = rkMaMap.get(maVerInfo.getGuid() + rkMa.getStoreRd());
                ma.setNum(ma.getNum() + rkMa.getNum());
                rkMaMap.put(maVerInfo.getGuid() + rkMa.getStoreRd(), ma);
            } else {
                rkMaMap.put(maVerInfo.getGuid() + rkMa.getStoreRd(), rkMa);
            }
        }

        //判断数量+完成
        //总的Bom物料信息
        for (String key : maMap.keySet()) {
            if (!bomMap.containsKey(key)) {
                throw new SystemException("", "领料失败,数量过多");
            }
            Float f = bomMap.get(key) - maMap.get(key);

            /*if (f < 0) {
                throw new SystemException("", "领料失败,数量过多");
            }else */
            if (f == 0) {
                bomMap.put(key, 0f);
            }
        }

        String pkeflag = "01";
        for (Float value : bomMap.values()) {
            if (value > 0) {
                pkeflag = "00";
                break;
            }
        }
        if (!pkeflag.equals(woInfo.getpKEFlag())) {
            woInfo.setpKEFlag(pkeflag);
            woInfo.setLastModifyMan(userName);
            woInfo.setLastModifyTime(date);
            if (wodao.UpdateWoInfo(woInfo) <= 0) {
                throw new SystemException("", "领料数量失败");
            }
        }

        for (Map.Entry<Integer, PickDtlInfo_1> map : pickDtlInfoMap_1.entrySet()) {
            if (pickDtlDAO_1.DeleteByGuid(map.getValue().getGuid()) <= 0) {
                throw new SystemException("", "领料单更新失败");
            }
        }

        //查询工单领料明细(PickDtlInfo)old
        List<PickDtlInfo> pickDtlInfos = pickDtlDAO.SelectPickDtlInfoByPickGd(pickInfo.getGuid());
        PickDtlInfo pickDtlInfo = null;
        Map<String, PickDtlInfo> pickDtlInfoMap = new HashMap<>();
        for (PickDtlInfo obj : pickDtlInfos) {
            pickDtlInfoMap.put(obj.getMaVerGd(), obj);
        }
        InstockInfo instockInfo = null;
        for (Map.Entry<String, SavePickInfoReq02RKMa> rkMaEntry : rkMaMap.entrySet()) {
            maVerInfo = maVerDAO.SelectByRuid(rkMaEntry.getValue().getMaVerRd());
            if (maVerInfo == null) {
                throw new SystemException("", "ID为" + rkMaEntry.getValue().getMaVerRd() + "的物料不存在");
            }
            ma = rkMaEntry.getValue();
            objEStoreInfo = storeDAO.SelectByRuid(ma.getStoreRd());
            if (objEStoreInfo == null) {
                throw new SystemException("", maVerInfo.getMaterialName() + "物料领用仓库不存在");
            }

            //库存数量比较
            instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), objEStoreInfo.getGuid());
            if (instockInfo == null) {
                throw new SystemException("", "新增失败," + maVerInfo.getMaterialName() + "库存数量为零");
            }
            if (ma.getNum() > instockInfo.getNum()) {
                throw new SystemException("", "新增失败," + maVerInfo.getMaterialName() + "的数量不能大于库存的数量" + instockInfo.getNum());
            }

            if (pickDtlInfoMap.containsKey(maVerInfo.getGuid())) {
                //修改
                pickDtlInfo = pickDtlInfoMap.get(maVerInfo.getGuid());
                pickDtlInfo.setMaVerGd(maVerInfo.getGuid());
                pickDtlInfo.setReML("");
                pickDtlInfo.setNum(ma.getNum());
                pickDtlInfo.setUnitName(ma.getUnitName());
                pickDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                pickDtlInfo.setRemark(ma.getRemark());
                pickDtlInfo.setLastModifyMan(userName);
                pickDtlInfo.setLastModifyTime(date);
                if (pickDtlDAO.UpdatePickDtlInfo(pickDtlInfo) <= 0) {
                    throw new SystemException("", "领料单修改失败");
                }
                pickDtlInfoMap.remove(maVerInfo.getGuid());
            } else {
                //新增
                pickDtlInfo = new PickDtlInfo();
                pickDtlInfo.setGuid(CommonUtils.getRandomNumber());
                pickDtlInfo.setPickGd(pickInfo.getGuid());
                pickDtlInfo.setPickCode(pickInfo.getPickCode());
                pickDtlInfo.setMaVerGd(maVerInfo.getGuid());
                pickDtlInfo.setReML("");
                pickDtlInfo.setNum(ma.getNum());
                pickDtlInfo.setUnitName(ma.getUnitName());
                pickDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                pickDtlInfo.setRemark(ma.getRemark());
                pickDtlInfo.setCreator(userName);
                pickDtlInfo.setCreateTime(date);
                pickDtlDAO.InsertPickDtlInfo(pickDtlInfo);
            }
        }

        for (Map.Entry<String, PickDtlInfo> rkMaEntry : pickDtlInfoMap.entrySet()) {
            if (pickDtlDAO.DeletePickDtlInfoByRuid(rkMaEntry.getValue().getRuid()) <= 0) {
                throw new SystemException("", "领料单修改失败");
            }
        }

        /*for(Map.Entry<String, Float> entry : maMap.entrySet()){
            if(!bomMap.containsKey(entry.getKey())){
                throw new SystemException("", "ID为" + entry.getKey() + "的物料Bom中不存在");
            }
            pickDtlInfo = pickDtlInfoMap.get(entry.getKey());
            if(entry.getValue() > bomMap.get(entry.getKey()) - pickDtlInfo.getScanNum()){
                throw new SystemException("", "ID为" + entry.getKey() + "的物料领用过多");
            }
        }*/

        /*if (obj.getScanNum() == 0) {
            if (instockDAO.DeleteInStockInfoByRuid(obj.getRuid()) <= 0) {
                throw new SystemException("", "更新失败,该单子在进行中");
            }
        } else {
            throw new SystemException("", "删除失败，此数量已被扫描");
        }*/

        objESavePickInfoRes.setBody(objESSavePickInfoResB);
        return objESavePickInfoRes;
    }

    //获取领料单列表信息 N
    @Override
    public GetAllNPickInfoRes QALLGetAllNPickInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllNPickInfoRes objEGetAllNPickInfoRes = new GetAllNPickInfoRes();
        GetAllNPickInfoResB objEGetAllNPickInfoResB = new GetAllNPickInfoResB();
        List<GetAllNPickInfoResD> objEGetAllNPickInfoResDs = new ArrayList<GetAllNPickInfoResD>();

        List<NPickInfo> objEPickInfos =
                baseIService.QALLBaseInfo(NPickDAO.class, "SelectNPickInfo",
                        argInitDataFields, argPageInfo);

        if (objEPickInfos != null && objEPickInfos.size() > 0) {
            for (NPickInfo obj : objEPickInfos) {
                GetAllNPickInfoResD objEGetAllNPickInfoResD = new GetAllNPickInfoResD();
                objEGetAllNPickInfoResD.setPickRd(obj.getRuid());
                objEGetAllNPickInfoResD.setPickCode(obj.getPickCode());
                objEGetAllNPickInfoResDs.add(objEGetAllNPickInfoResD);
            }
        }
        objEGetAllNPickInfoResB.setData(objEGetAllNPickInfoResDs);
        objEGetAllNPickInfoRes.setBody(objEGetAllNPickInfoResB);
        return objEGetAllNPickInfoRes;
    }

    //筛选无工单领料
    @Override
    public GetAllNPickInfoRes QALLGetAllNPickInfoRes1(GetAllNPickInfoResD getAllNPickInfoResD, PageInfo argPageInfo) {
        GetAllNPickInfoRes objEGetAllNPickInfoRes = new GetAllNPickInfoRes();
        GetAllNPickInfoResB objEGetAllNPickInfoResB = new GetAllNPickInfoResB();
        List<GetAllNPickInfoResD> objEGetAllNPickInfoResDs = new ArrayList<GetAllNPickInfoResD>();

        List<NPickInfo> objEPickInfos = nPickDAO.SelectNPickInfoShaiXuan(getAllNPickInfoResD.getUserRd(), getAllNPickInfoResD.getPickCode(),
                getAllNPickInfoResD.getExStatus(), getAllNPickInfoResD.getCreateDate(), getAllNPickInfoResD.getCreateDate1());

        if (objEPickInfos != null && objEPickInfos.size() > 0) {
            for (NPickInfo obj : objEPickInfos) {
                GetAllNPickInfoResD objEGetAllNPickInfoResD = new GetAllNPickInfoResD();
                objEGetAllNPickInfoResD.setPickRd(obj.getRuid());
                objEGetAllNPickInfoResD.setPickCode(obj.getPickCode());
                objEGetAllNPickInfoResDs.add(objEGetAllNPickInfoResD);
            }
        }
        objEGetAllNPickInfoResB.setData(objEGetAllNPickInfoResDs);
        objEGetAllNPickInfoRes.setBody(objEGetAllNPickInfoResB);
        return objEGetAllNPickInfoRes;
    }

    //获取领料单信息 N
    @Override
    public GetNPickInfoRes GetGetNPickInfoRes(GetNPickInfoReq00 argGetNPickInfoReq00) {
        GetNPickInfoRes objEGetNPickInfoRes = new GetNPickInfoRes();
        GetNPickInfoResB objEGetNPickInfoResB = new GetNPickInfoResB();
        GetNPickInfoResD objEGetNPickInfoResD = new GetNPickInfoResD();


        List<GetNPickInfoResDPickDl> objEGetNPickInfoResDPickDls = new ArrayList<GetNPickInfoResDPickDl>();

        NPickInfo objENPickInfo = nPickDAO.SelectNPickInfoByRuid(argGetNPickInfoReq00.getPickRd());
        if (objENPickInfo == null) {
            throw new SystemException("", "查询领料单信息失败，该信息不存在");
        }
        if (objENPickInfo != null) {
            objEGetNPickInfoResD.setPickRd(objENPickInfo.getRuid());
            objEGetNPickInfoResD.setPickCode(objENPickInfo.getPickCode());
            objEGetNPickInfoResD.setDSource(objENPickInfo.getdSource());
            UserInfo objEUserInfo = userDAO.SelectUserRd(objENPickInfo.getPickerGd());
            if (objEUserInfo != null) {
                GetNPickInfoResDPicker objEGetNPickInfoResDPicker = new GetNPickInfoResDPicker();
                objEGetNPickInfoResDPicker.setPickerRd(objEUserInfo.getRuid());
                objEGetNPickInfoResDPicker.setPickerName(objEUserInfo.getRealName());
                objEGetNPickInfoResD.setPickerInfo(objEGetNPickInfoResDPicker);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            objEGetNPickInfoResD.setPrePickDate(sdf.format(objENPickInfo.getPrePickDate()));


            objEGetNPickInfoResD.setUse(objENPickInfo.getUse());
            objEGetNPickInfoResD.setExStatus(objENPickInfo.getExStatus());
            objEGetNPickInfoResD.setSStatus(objENPickInfo.getsStatus());
            objEGetNPickInfoResD.setCreator(objENPickInfo.getCreator());
            objEGetNPickInfoResD.setCreateTime(DateUtil.format(objENPickInfo.getCreateTime()));
            objEGetNPickInfoResD.setLastModifyMan(objENPickInfo.getLastModifyMan());
            objEGetNPickInfoResD.setLastModifyTime(DateUtil.format(objENPickInfo.getLastModifyTime()));
            objEGetNPickInfoResD.setRemark(objENPickInfo.getRemark());

            List<NPickDtlInfo_1> objENPickDtlInfos = nPickDtlDAO_1.SelectNPickDtlInfoByPickGd_1(objENPickInfo.getGuid());
            if (objENPickDtlInfos != null && objENPickDtlInfos.size() > 0) {
                for (NPickDtlInfo_1 obj : objENPickDtlInfos) {
                    GetNPickInfoResDPickDl objEGetNPickInfoResDPickDl = new GetNPickInfoResDPickDl();


                    objEGetNPickInfoResDPickDl.setPKDtlRd(obj.getRuid());
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                    if (maVerInfo != null) {
                        objEGetNPickInfoResDPickDl.setMaVerRd(maVerInfo.getRuid());
                        objEGetNPickInfoResDPickDl.setMaCode(maVerInfo.getMaterialCode());
                        objEGetNPickInfoResDPickDl.setMaName(maVerInfo.getMaterialName());
                        //查询明细的物料描述
                        MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                        if (materialInfo == null && "".equals(materialInfo)) {
                            objEGetNPickInfoResDPickDl.setMaDes("");
                        } else {
                            if (materialInfo.getMaterialDes() == null || "".equals(materialInfo.getMaterialDes())) {
                                objEGetNPickInfoResDPickDl.setMaDes("");
                            } else {
                                objEGetNPickInfoResDPickDl.setMaDes(materialInfo.getMaterialDes());
                            }
                        }
                    } else {
                        objEGetNPickInfoResDPickDl.setMaCode("");
                        objEGetNPickInfoResDPickDl.setMaName("");
                        objEGetNPickInfoResDPickDl.setMaDes("");
                    }

                    StoreInfo storeInfo = storeDAO.SelectByguid(obj.getStoreGd());
                    if (storeInfo != null) {
                        GetNPickInfoResDStore objEGetNPickInfoResDStore = new GetNPickInfoResDStore();
                        objEGetNPickInfoResDStore.setStoreRd(storeInfo.getRuid());
                        objEGetNPickInfoResDStore.setStoreName(storeInfo.getStoreName());
                        objEGetNPickInfoResDPickDl.setStoreInfo(objEGetNPickInfoResDStore);
                    }

                    objEGetNPickInfoResDPickDl.setNum(obj.getNum());
                    objEGetNPickInfoResDPickDl.setUnitName(obj.getUnitName());
                    if (obj.getRemark() != null && !"".equals(obj.getRemark())) {
                        objEGetNPickInfoResDPickDl.setRemark(obj.getRemark());
                    } else {
                        objEGetNPickInfoResDPickDl.setRemark("");
                    }

                    objEGetNPickInfoResDPickDls.add(objEGetNPickInfoResDPickDl);
                    objEGetNPickInfoResD.setPickDlInfo(objEGetNPickInfoResDPickDls);
                }

            }
        }
        objEGetNPickInfoResB.setData(objEGetNPickInfoResD);
        objEGetNPickInfoRes.setBody(objEGetNPickInfoResB);
        return objEGetNPickInfoRes;
    }

    //汇总 N
    @Override
    public GetNMaTotalInfoRes QALLGetNMaTotalInfoRes(GetNMaTotalInfoReq00 argGetNMaTotalInfoReq00) {
        GetNMaTotalInfoRes objEGetNMaTotalInfoRes = new GetNMaTotalInfoRes();
        GetNMaTotalInfoResB objEGetNMaTotalInfoResB = new GetNMaTotalInfoResB();
        List<GetNMaTotalInfoResD> getNMaTotalInfoResDS = new ArrayList<GetNMaTotalInfoResD>();

        StoreInfo storeInfo = storeDAO.SelectByRuid(argGetNMaTotalInfoReq00.getStoreRd());
        if (storeInfo == null) {
            throw new SystemException("", "没有仓库数据");
        }

        List<InstockInfo> instockInfos = new ArrayList<>();
        //当无筛选条件时查询所有
        if (StringUtils.isBlank(argGetNMaTotalInfoReq00.getMaVerDesc())) {
            instockInfos = instockDAO.SelectBystoreGd(storeInfo.getGuid());
        } else {
            instockInfos = instockDAO.SelectBystoreGdShaixuan00(storeInfo.getGuid(), argGetNMaTotalInfoReq00.getMaVerDesc(), argGetNMaTotalInfoReq00.getMaVerDesc());
        }
        if (instockInfos != null && instockInfos.size() > 0) {
            for (InstockInfo instockInfo : instockInfos) {
                GetNMaTotalInfoResD objEGetNMaTotalInfoResD = new GetNMaTotalInfoResD();
                objEGetNMaTotalInfoResD.setStoreNum(instockInfo.getNum());
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(instockInfo.getMaVerGd());
                if (maVerInfo != null) {
                    objEGetNMaTotalInfoResD.setMaVerRd(maVerInfo.getRuid());
                    objEGetNMaTotalInfoResD.setMaName(maVerInfo.getMaterialName());
                    objEGetNMaTotalInfoResD.setMaCode(maVerInfo.getMaterialCode());
                    //查询明细的物料描述
                    MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                    if (materialInfo == null && "".equals(materialInfo)) {
                        objEGetNMaTotalInfoResD.setMaDes("");
                    } else {
                        if (materialInfo.getMaterialDes() == null || "".equals(materialInfo.getMaterialDes())) {
                            objEGetNMaTotalInfoResD.setMaDes("");
                        } else {
                            objEGetNMaTotalInfoResD.setMaDes(materialInfo.getMaterialDes());
                        }
                    }
                    UnitInfo unitInfo = unitDAO.SelectByGuid(maVerInfo.getUnitGd());
                    if (unitInfo != null) {
                        objEGetNMaTotalInfoResD.setUnitName(unitInfo.getUnitName());
                    } else {
                        objEGetNMaTotalInfoResD.setUnitName("");
                    }
                }
                getNMaTotalInfoResDS.add(objEGetNMaTotalInfoResD);
            }
        } else {
            throw new SystemException("", "该仓库下没有数据");
        }
        objEGetNMaTotalInfoResB.setData(getNMaTotalInfoResDS);
        objEGetNMaTotalInfoRes.setBody(objEGetNMaTotalInfoResB);
        return objEGetNMaTotalInfoRes;
    }

    //新增 N
    @Override
    public SaveNPickInfoRes AddSaveNPickInfoRes(SaveNPickInfoReq00 argSaveNPickInfoReq00) {
        SaveNPickInfoRes objESaveNPickInfoRes = new SaveNPickInfoRes();
        SaveNPickInfoResB objESaveNPickInfoResB = new SaveNPickInfoResB();
        SaveNPickInfoResD objESaveNPickInfoResD = new SaveNPickInfoResD();
        NPickInfo nPickInfo = new NPickInfo();
        nPickInfo.setGuid(CommonUtils.getRandomNumber());

        //查询代码生成
        CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("06");
        String SCode = "";
        if (argSaveNPickInfoReq00.getPickCode() != null && !"".equals(argSaveNPickInfoReq00.getPickCode())) {
            //新增的时候，手动写的的单号不允许重复
            NPickInfo nPickInfo1 = nPickDAO.SelectNPickInfoByPickCode(argSaveNPickInfoReq00.getPickCode());
            if (nPickInfo1 != null) {
                throw new SystemException("xx1", "新增失败，领料单号已存在");
            }
            SCode = argSaveNPickInfoReq00.getPickCode();
            nPickInfo.setPickCode(SCode);
        } else {
            if (argSaveNPickInfoReq00.getPickCode() == null || "".equals(argSaveNPickInfoReq00.getPickCode())) {
                if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                    SCode = gConfigIService.GetCreateSC(codeRuleInfo);
                    nPickInfo.setPickCode(SCode);
                } else {
                    throw new SystemException("", "请输入{领料单号}，或请到全局配置进行代码生成配置");
                }
            }

        }

        UserInfo userInfo = userDAO.SelectUserInfoByruid(argSaveNPickInfoReq00.getPickerRd());
        if (userInfo != null) {
            nPickInfo.setPickerGd(userInfo.getGuid());
        } else {
            throw new SystemException("", "该领料人不存在");
        }
        nPickInfo.setUse(argSaveNPickInfoReq00.getUse());
        nPickInfo.setExStatus("00");//执行状态
        nPickInfo.setdSource("01");//数据来源
        nPickInfo.setsStatus("00");//审核状态
        nPickInfo.setPrePickDate(DateUtil.format2(argSaveNPickInfoReq00.getPrePickDate()));
        nPickInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        nPickInfo.setCreateTime(new Date());

        //此处先将 同一个仓库下 料相同合并，数量累加
        Map<String, SaveNPickInfoReq00RKMa> maMap = new HashMap<String, SaveNPickInfoReq00RKMa>();

        for (SaveNPickInfoReq00RKMa obj : argSaveNPickInfoReq00.getPKMaInfo()) {
            //下面的判断是因为前端的字段不允许为空
            if ("".equals(obj.getMaVerRd())) {
                throw new SystemException("xx", "新增失败，物料信息不允许为空");
            }


            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(obj.getMaVerRd());

            if (maVerInfo == null || "".equals(maVerInfo)) {
                throw new SystemException("xx", "新增失败，物料不允许为空");
            }

            StoreInfo storeInfo = storeDAO.SelectByRuid(obj.getStoreRd());
            if (storeInfo == null || "".equals(storeInfo)) {
                throw new SystemException("xx", "新增失败，" + maVerInfo.getMaterialCode() + ":的仓库不允许为空");
            }

            if (obj.getNum() <= 0) {
                throw new SystemException("xx", "新增失败，" + maVerInfo.getMaterialCode() + ":的数量不能小于等于零");
            }

            if (obj.getUnitName() == null || "".equals(obj.getUnitName())) {
                throw new SystemException("xx", "新增失败，" + maVerInfo.getMaterialCode() + ":的单位不允许为空");
            }
            InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), storeInfo.getGuid());
            if (instockInfo != null) {
                if (obj.getNum() > instockInfo.getNum()) {
                    throw new SystemException("", "新增失败" + maVerInfo.getMaterialCode() + ":的数量不能大于" + instockInfo.getNum());
                } else {


                    NPickDtlInfo_1 nPickDtlInfo_1 = new NPickDtlInfo_1();
                    nPickDtlInfo_1.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                    nPickDtlInfo_1.setCreateTime(new Date());
                    nPickDtlInfo_1.setRemark(obj.getRemark());
                    nPickDtlInfo_1.setGuid(CommonUtils.getRandomNumber());
                    nPickDtlInfo_1.setPickGd(nPickInfo.getGuid());
                    nPickDtlInfo_1.setPickCode(nPickInfo.getPickCode());
                    nPickDtlInfo_1.setMaVerGd(maVerInfo.getGuid());
                    nPickDtlInfo_1.setNum(obj.getNum());
                    nPickDtlInfo_1.setUnitName(obj.getUnitName());
                    nPickDtlInfo_1.setStoreGd(storeInfo.getGuid());
                    //新增系表
                    nPickDtlDAO_1.InsertNPickDtlInfo_1(nPickDtlInfo_1);
                }
            } else {
                throw new SystemException("", "新增失败,该" + maVerInfo.getMaterialCode() + "不在库存中");
            }
        }
        for (SaveNPickInfoReq00RKMa obj : argSaveNPickInfoReq00.getPKMaInfo()) {
            //map集合接收值
            String string = obj.getStoreRd() + "_" + obj.getMaVerRd();
            if (maMap.containsKey(string)) {
                obj.setNum(maMap.get(string).getNum() + obj.getNum());
                maMap.put(string, obj);
            } else {
                maMap.put(string, obj);
            }
        }


        //新增主表
        nPickDAO.InsertNPickInfo(nPickInfo);


        //同一个仓库下，料一样 进行累加 即去重
        for (String s1 : maMap.keySet()) {         //遍历map的键
            String key = s1;
            String[] str = key.split("_");
            if (str != null && str.length > 0) {
                //获取仓库id
                Integer storeID = Integer.valueOf(str[0]);
                //获取物料id
                Integer maverRd = Integer.valueOf(str[1]);
                MaVerInfo maVerInfo = maVerDAO.SelectByRuid(maverRd);
                StoreInfo storeInfo = storeDAO.SelectByRuid(storeID);
                InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), storeInfo.getGuid());
                if (instockInfo != null) {
                    if (maMap.get(key).getNum() > instockInfo.getNum()) {
                        throw new SystemException("", "新增失败" + maVerInfo.getMaterialCode() + ":的数量不能大于" + instockInfo.getNum());
                    }
                }

                NPickDtlInfo nPickDtlInfo = new NPickDtlInfo();
                nPickDtlInfo.setGuid(CommonUtils.getRandomNumber());
                nPickDtlInfo.setPickGd(nPickInfo.getGuid());
                nPickDtlInfo.setPickCode(SCode);
                nPickDtlInfo.setMaVerGd(maVerInfo.getGuid());
                nPickDtlInfo.setScanNum(0.0f);
                nPickDtlInfo.setNum(maMap.get(key).getNum());
                nPickDtlInfo.setUnitName(maMap.get(key).getUnitName());
                nPickDtlInfo.setStoreGd(storeInfo.getGuid());
                nPickDtlInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                nPickDtlInfo.setCreateTime(new Date());
                nPickDtlDAO.InsertNPickDtlInfo(nPickDtlInfo);
            }
        }
        objESaveNPickInfoResD.setPickCode(nPickInfo.getPickCode());
        objESaveNPickInfoResD.setPickRd(nPickInfo.getRuid());
        objESaveNPickInfoResB.setData(objESaveNPickInfoResD);
        objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
        return objESaveNPickInfoRes;
    }

    //删除 N
    @Override
    public SaveNPickInfoRes RmSaveNPickInfoRes(SaveNPickInfoReq01 argSaveNPickInfoReq01) {
        SaveNPickInfoRes objESaveNPickInfoRes = new SaveNPickInfoRes();
        SaveNPickInfoResB objESaveNPickInfoResB = new SaveNPickInfoResB();
        NPickInfo nPickInfo = nPickDAO.SelectNPickInfoByRuid(argSaveNPickInfoReq01.getPickRd());
        if (nPickInfo == null) {
            throw new SystemException("", "删除失败，该信息不存在");
        }
        if ("00".equals(nPickInfo.getdSource())) {
            throw new SystemException("", "删除失败，该数据来自外部数据");
        }
        //唯一的状态审核状态未下达
        if ("00".equals(nPickInfo.getsStatus())) {
            //删除系表
            List<NPickDtlInfo_1> pickDtlInfoss_1 = nPickDtlDAO_1.SelectNPickDtlInfoByPickGd_1(nPickInfo.getGuid());
            if (pickDtlInfoss_1 != null && pickDtlInfoss_1.size() > 0) {
                for (NPickDtlInfo_1 obj : pickDtlInfoss_1) {
                    if (nPickDtlDAO_1.DeleteNPickDtlInfo_1(obj.getRuid()) <= 0) {
                        throw new SystemException("", "删除领料单明细失败");
                    }
                }
            }
            //删除汇总表
            List<NPickDtlInfo> nPickDtlInfos = nPickDtlDAO.SelectNPickDtlInfoByPickGd(nPickInfo.getGuid());
            if (nPickDtlInfos != null && nPickDtlInfos.size() > 0) {
                for (NPickDtlInfo obj1 : nPickDtlInfos) {
                    if (nPickDtlDAO.DeleteNPickDtlInfoByRuid(obj1.getRuid()) <= 0) {
                        throw new SystemException("", "删除领料单明细失败");
                    }
                }
            }
            //删除主表
            if (nPickDAO.DeleteNPickInfoByRuid(nPickInfo.getRuid()) <= 0) {
                throw new SystemException("", "删除领料单失败");
            }
        } else {
            throw new SystemException("", "删除领料单失败,该单子处于已下达或者已取消状态");
        }
        objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
        return objESaveNPickInfoRes;
    }

    //修改 N
    @Override
    public SaveNPickInfoRes ModSaveNPickInfoRes(SaveNPickInfoReq02 argSaveNPickInfoReq02) {
        SaveNPickInfoRes objESaveNPickInfoRes = new SaveNPickInfoRes();
        SaveNPickInfoResB objESaveNPickInfoResB = new SaveNPickInfoResB();
        SaveNPickInfoResD saveNPickInfoResD = new SaveNPickInfoResD();
        NPickInfo npickInfo = nPickDAO.SelectNPickInfoByRuid(argSaveNPickInfoReq02.getPickRd());
        if (npickInfo == null) {
            throw new SystemException("", "修改失败，该信息不存在");
        }
        //编辑的前置条件
        if ("00".equals(npickInfo.getdSource())) {
            throw new SystemException("", "更新失败，该数据来自外部数据");
        }
        //此单子的审核在已下达或者已取消 不可编辑
        if ("01".equals(npickInfo.getsStatus()) || "02".equals(npickInfo.getsStatus())) {
            throw new SystemException("", "更新失败，该单子处于已下达或者已取消状态");
        }

        UserInfo userInfo = userDAO.SelectUserInfoByruid(argSaveNPickInfoReq02.getPickerRd());
        if (userInfo != null) {
            npickInfo.setPickerGd(userInfo.getGuid());
        } else {
            throw new SystemException("", "该领料人不存在");
        }
        npickInfo.setUse(argSaveNPickInfoReq02.getUse());
        npickInfo.setPrePickDate(DateUtil.format2(argSaveNPickInfoReq02.getPrePickDate()));
        npickInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
        npickInfo.setLastModifyTime(new Date());
        if (nPickDAO.UpdatenNPickInfo(npickInfo) <= 0) {
            throw new SystemException("", "更新领料单信息失败");
        }
        List<NPickDtlInfo_1> npickDtlInfoss_1 = nPickDtlDAO_1.SelectNPickDtlInfoByPickGd_1(npickInfo.getGuid());

        //此处先将 同一个仓库下 料相同合并，数量累加 --
        Map<String, SaveNPickInfoReq00RKMa> maMap = new HashMap<String, SaveNPickInfoReq00RKMa>();

        if (npickDtlInfoss_1 != null && npickDtlInfoss_1.size() > 0) {
            //遍历前端传的值
            for (SaveNPickInfoReq00RKMa saveSaveNPickInfoReq02 : argSaveNPickInfoReq02.getPKMaInfo()) {

                //下面的判断是因为前端的字段不允许为空
                if ("".equals(saveSaveNPickInfoReq02.getMaVerRd())) {
                    throw new SystemException("xx", "更新失败，物料信息不允许为空");
                }

                MaVerInfo maVerInfo = maVerDAO.SelectByRuid(saveSaveNPickInfoReq02.getMaVerRd());

                if (maVerInfo == null || "".equals(maVerInfo)) {
                    throw new SystemException("xx", "更新失败，物料不允许为空");
                }
                if (saveSaveNPickInfoReq02.getNum() <= 0) {
                    throw new SystemException("xx", "新增失败，" + maVerInfo.getMaterialCode() + ":的数量不能小于等于零");
                }

                StoreInfo storeInfo = storeDAO.SelectByRuid(saveSaveNPickInfoReq02.getStoreRd());
                if (storeInfo == null || "".equals(storeInfo)) {
                    throw new SystemException("xx", "更新失败，" + maVerInfo.getMaterialCode() + ":的仓库不允许为空");
                }
                if (saveSaveNPickInfoReq02.getUnitName() == null || "".equals(saveSaveNPickInfoReq02.getUnitName())) {
                    throw new SystemException("xx", "更新失败，" + maVerInfo.getMaterialCode() + ":的单位不允许为空");
                }


                //判断更新还是新增
                boolean flag = true;
                //遍历后端获取的值
                for (int i = 0; i < npickDtlInfoss_1.size(); i++) {
                    if (npickDtlInfoss_1.get(i).getRuid() == saveSaveNPickInfoReq02.getPKDtlRd()) {
                        //更新
                        NPickDtlInfo_1 npickDtlInfo_1 = nPickDtlDAO_1.SelectNPickDtlInfoByRuid(saveSaveNPickInfoReq02.getPKDtlRd());
                        npickDtlInfo_1.setLastModifyTime(new Date());
                        npickDtlInfo_1.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());

                        if (maVerInfo != null) {
                            InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), storeInfo.getGuid());
                            if (instockInfo != null) {
                                if (saveSaveNPickInfoReq02.getNum() > instockInfo.getNum()) {
                                    throw new SystemException("", "更新失败，该" + maVerInfo.getMaterialName() + "的数量不能大于库存" + instockInfo.getNum());
                                }
                            }
                        }
                        npickDtlInfo_1.setNum(saveSaveNPickInfoReq02.getNum());
                        if (nPickDtlDAO_1.UpdateNPickDtlInfo_1(npickDtlInfo_1) <= 0) {
                            throw new SystemException("", "更新明细失败");
                        }
                        npickDtlInfoss_1.remove(i);
                        flag = false;
                        break;
                    }
                }
                if (flag) {

                    NPickDtlInfo_1 nPickDtlInfo_1 = new NPickDtlInfo_1();
                    nPickDtlInfo_1.setGuid(CommonUtils.getRandomNumber());
                    nPickDtlInfo_1.setPickGd(npickInfo.getGuid());
                    nPickDtlInfo_1.setPickCode(npickInfo.getPickCode());

                    if (maVerInfo != null) {
                        nPickDtlInfo_1.setMaVerGd(maVerInfo.getGuid());
                    }
                    InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), storeInfo.getGuid());
                    if (instockInfo != null) {
                        if (saveSaveNPickInfoReq02.getNum() > instockInfo.getNum()) {
                            throw new SystemException("", "更新失败" + maVerInfo.getMaterialCode() + ":的数量不能大于" + instockInfo.getNum());
                        }
                    }

                    nPickDtlInfo_1.setNum(saveSaveNPickInfoReq02.getNum());
                    nPickDtlInfo_1.setUnitName(saveSaveNPickInfoReq02.getUnitName());
                    nPickDtlInfo_1.setStoreGd(storeInfo.getGuid());
                    nPickDtlInfo_1.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                    nPickDtlInfo_1.setCreateTime(new Date());
                    nPickDtlDAO_1.InsertNPickDtlInfo_1(nPickDtlInfo_1);
                }
            }
            for (NPickDtlInfo_1 obj1 : npickDtlInfoss_1) {
                if (nPickDtlDAO_1.DeleteNPickDtlInfo_1(obj1.getRuid()) <= 0) {
                    throw new SystemException("", "更新明细失败");
                }
            }
        }

        for (SaveNPickInfoReq00RKMa saveSaveNPickInfoReq02 : argSaveNPickInfoReq02.getPKMaInfo()) {
            //此处接收前端的值，目的是为了 去重，同一个仓库下 料相同合并，数量累加 --
            String string = saveSaveNPickInfoReq02.getStoreRd() + "_" + saveSaveNPickInfoReq02.getMaVerRd();
            if (maMap.containsKey(string)) {
                saveSaveNPickInfoReq02.setNum(maMap.get(string).getNum() + saveSaveNPickInfoReq02.getNum());
                maMap.put(string, saveSaveNPickInfoReq02);
            } else {
                maMap.put(string, saveSaveNPickInfoReq02);
            }
        }

        //查询所有的明细信息
        List<NPickDtlInfo> nPickDtlInfos = nPickDtlDAO.SelectNPickDtlInfoByPickGd(npickInfo.getGuid());
        for (NPickDtlInfo obj1 : nPickDtlInfos) {
            if (nPickDtlDAO.DeleteNPickDtlInfoByRuid(obj1.getRuid()) <= 0) {
                throw new SystemException("", "更新明细失败");
            }
        }

        //同一个仓库下，料一样 进行累加 即去重
        for (String s1 : maMap.keySet()) {
            String key = s1;
            MaVerInfo maVerInfo = maVerDAO.SelectByRuid(maMap.get(key).getMaVerRd());

            if (maVerInfo == null || "".equals(maVerInfo)) {
                throw new SystemException("xx", "更新失败，物料不允许为空");
            }

            StoreInfo storeInfo = storeDAO.SelectByRuid(maMap.get(key).getStoreRd());
            if (storeInfo == null || "".equals(storeInfo)) {
                throw new SystemException("xx", "更新失败，" + maVerInfo.getMaterialCode() + ":的仓库不允许为空");
            }

            NPickDtlInfo nPickDtlInfo1 = new NPickDtlInfo();
            nPickDtlInfo1.setGuid(CommonUtils.getRandomNumber());
            nPickDtlInfo1.setPickGd(npickInfo.getGuid());
            nPickDtlInfo1.setPickCode(npickInfo.getPickCode());

            if (maVerInfo != null) {
                nPickDtlInfo1.setMaVerGd(maVerInfo.getGuid());
            }
            InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(maVerInfo.getGuid(), storeInfo.getGuid());
            if (instockInfo != null) {
                if (maMap.get(key).getNum() > instockInfo.getNum()) {
                    throw new SystemException("", "更新失败" + maVerInfo.getMaterialCode() + ":的数量不能大于" + instockInfo.getNum());
                }
            }
            nPickDtlInfo1.setNum(maMap.get(key).getNum());
            nPickDtlInfo1.setUnitName(maMap.get(key).getUnitName());
            nPickDtlInfo1.setStoreGd(storeInfo.getGuid());
            nPickDtlInfo1.setCreator((String) SecurityUtils.getSubject().getPrincipal());
            nPickDtlInfo1.setCreateTime(new Date());
            nPickDtlDAO.InsertNPickDtlInfo(nPickDtlInfo1);
        }
        saveNPickInfoResD.setPickCode(npickInfo.getPickCode());
        saveNPickInfoResD.setPickRd(npickInfo.getRuid());
        objESaveNPickInfoResB.setData(saveNPickInfoResD);
        objESaveNPickInfoRes.setBody(objESaveNPickInfoResB);
        return objESaveNPickInfoRes;
    }

    //导入
    @Override
    public SaveImportResB AddImportPick(CommonsMultipartFile file) throws IOException {
        SaveImportResB objSaveImportResB = new SaveImportResB();
        //把领料单明细保存在集合中，在插入数据库的时候把相同的物料合并，数量相加
        Map<String, Map<String, PickDtlInfo_1>> map = new HashMap<String, Map<String, PickDtlInfo_1>>();

        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());

        List<PickInfo> pickInfoList = new ArrayList<PickInfo>();

        List<String> pickCodeList = new ArrayList<String>();

        Sheet sheet = wb.getSheetAt(0);
        int m = 0;
        Iterator<Row> rows = sheet.rowIterator();
        int count = 2;
        a:
        while (rows.hasNext()) {

            PickInfo pickInfo = null;
            Row row = rows.next();
            if (sheet.getRow(2) == null) {
                throw new SystemException("xxx", "导入失败，导入的数据为空!");
            }
            if (m == 0) {
                m++;
                continue;
            }
            PickDtlInfo_1 pickDtlInfo_1 = new PickDtlInfo_1();
            Boolean flag = true;
            for (int i = 0; i < 9; i++) {
                Cell cell = row.getCell(i);
                /*if (i == 8) {//源单单号
                    Cell temp_cell = row.getCell(6);
                    temp_cell.setCellType(0);
                    if(temp_cell.getNumericCellValue() <= 0) {
                        if("".equals(cell.getStringCellValue().trim())) {
                            break a;
                        }else {
                            //查询源单单号
                            break a;
                        }
                    }
                }*/
                if (cell != null && !"".equals(cell.toString().trim())) {
                    cell.setCellType(1);
                    if (i == 0) {//领料单
                        pickInfo = pickDAO.SelectPickInfoByPickCode(cell.getStringCellValue().trim());
                        if (pickInfo == null) {
                            pickCodeList.add(cell.getStringCellValue().trim());
                            pickInfo = new PickInfo();
                            pickInfo.setPickCode(cell.getStringCellValue().trim());
                        } else {
                            if (!pickCodeList.contains(cell.getStringCellValue().trim())) {
                                throw new SystemException("01", "第" + count + "行，领料单：" + cell.getStringCellValue().trim() + "，已存在");
                            }
                            if (!"00".equals(pickInfo.getExStatus())) {
                                //不是待执行状态，直接结束本次读取，循环下一次
                                flag = !flag;
                                break;
                            }
                        }
                        pickDtlInfo_1.setPickCode(pickInfo.getPickCode());
                    } else if (i == 1) {//领料日期
                        if (DateUtil.format3(cell.getStringCellValue().trim()) == null)
                            throw new SystemException("02", "第" + count + "行，领料日期格式不对，必须是：YYYY/MM/DD的格式");
                        pickInfo.setPrePickDate(DateUtil.format3(cell.getStringCellValue().trim()));
                    } else if (i == 2) {//领料仓库
                        StoreInfo storeInfo = storeDAO.SelectBystoreName(cell.getStringCellValue().trim());
                        if (storeInfo == null) {
                            throw new SystemException("03", "第" + count + "行，领料仓库：" + cell.getStringCellValue().trim() + "，不存在");
                        }

                        //todo pickInfo.setStoreGd(storeInfo.getGuid());

                        pickDtlInfo_1.setStoreGd(storeInfo.getGuid());
                    } else if (i == 3) {//领用物料代码
                        MaterialInfo materialInfo = materialDAO.SelectByMaCode(cell.getStringCellValue().trim());
                        if (materialInfo == null) {
                            throw new SystemException("04", "第" + count + "行，物料代码：" + cell.getStringCellValue().trim() + "，不存在");
                        }
                        pickDtlInfo_1.setMaVerGd(materialInfo.getVerGd());
                        pickDtlInfo_1.setReML(materialInfo.getVerGd());
                    } else if (i == 4) {//领用物料名称
                        /*MaterialInfo materialInfo = materialDAO.SelectByMaName(cell.getStringCellValue().trim());
                        if (materialInfo == null) {
                            throw new SystemException("05", "第" + count + "行，物料名称：" + cell.getStringCellValue().trim() + "，不存在");
                        }*/
                    } else if (i == 5) {//领用物料单位
                        UnitInfo unitInfo = unitDAO.SelectByUnitName(cell.getStringCellValue().trim());
                        if (unitInfo == null) {
                            throw new SystemException("06", "第" + count + "行，单位：" + cell.getStringCellValue().trim() + "，不存在");
                        }
                        pickDtlInfo_1.setUnitName(unitInfo.getUnitName());
                    } else if (i == 6) {//领用物料实发数量
                        if (Float.parseFloat(cell.getStringCellValue()) <= 0) {
                            throw new SystemException("06", "第" + count + "行，数量：" + cell.getStringCellValue().trim() + "，不能小于等于零");
                        }
                        pickDtlInfo_1.setNum(Float.parseFloat(cell.getStringCellValue()));
                    } else if (i == 7) {//生产任务单号


                        if (cell.getStringCellValue() != null) {
                            pickInfo.setAssSource("00");
                            WoInfo woInfo = wodao.SelectWoInfoBywoCode(cell.getStringCellValue());
                            if (woInfo != null) {
                                pickInfo.setAssCode(cell.getStringCellValue());
                            } else {
                                throw new SystemException("", "导入失败，" + cell.getStringCellValue() + "领料单不存在");
                            }
                        }

                        //todo pickDtlInfo_1.setWorkOrder(cell.getStringCellValue());
                    } else if (i == 8) {
                        pickDtlInfo_1.setRemark(cell.getStringCellValue());
                    }
                } else {
                    if (i == 1) {
                        continue;
                    }
                    if (i == 7) {//生产任务单号
                        //pickInfo.setAssSource(cell.getStringCellValue());
                        //todo pickDtlInfo_1.setWorkOrder("");
                    /*if("".equals(cell.toString().trim())) {
                        pickInfo.setAssCode("");
                    }
                    else {
                        pickInfo.setAssCode("00");

                    }*/
                        continue;
                    }
                    if (i == 8) {
                        pickDtlInfo_1.setRemark("");
                        continue;
                    }
                    throw new SystemException("07", "第" + count + "行，第" + (i + 1) + "列不能为空");
                }
            }
            if (!flag) {
                m++;
                continue;
            }
            //保存物料信息到领料单明细中
            if ("".equals(pickInfo.getGuid()) || pickInfo.getGuid() == null) {
                pickInfo.setGuid(CommonUtils.getRandomNumber());
                pickInfo.setExStatus("00");
                pickInfo.setCreator(CommonUtils.readUser().getRealName());
                pickInfo.setCreateTime(new Date());
                pickInfo.setdSource("00");
                //新增领料单处理
                if (pickDAO.InsertPickInfo(pickInfo) != 1) {
                    throw new SystemException("08", "第" + count + "行，新增领料单失败");
                }
                pickInfoList.add(pickInfo);
            }
            pickDtlInfo_1.setPickGd(pickInfo.getGuid());
            pickDtlInfo_1.setGuid(CommonUtils.getRandomNumber());
            pickDtlInfo_1.setCreator(CommonUtils.readUser().getRealName());
            pickDtlInfo_1.setCreateTime(new Date());
            //todo pickDtlInfo_1.setScanNum(0);
            //不是合并的处理
            pickDtlDAO_1.InsertPickDtlInfo_1(pickDtlInfo_1);
            //------
            if (map.containsKey(pickDtlInfo_1.getPickCode())) {
                if (map.get(pickDtlInfo_1.getPickCode()).containsKey(pickDtlInfo_1.getMaVerGd())) {
                    map.get(pickDtlInfo_1.getPickCode()).get(pickDtlInfo_1.getMaVerGd()).setNum(map.get(pickDtlInfo_1.getPickCode()).get(pickDtlInfo_1.getMaVerGd()).getNum() + pickDtlInfo_1.getNum());
                } else {
                    map.get(pickDtlInfo_1.getPickCode()).put(pickDtlInfo_1.getMaVerGd(), pickDtlInfo_1);
                }
            } else {
                Map<String, PickDtlInfo_1> pickDtlMap = new HashMap<String, PickDtlInfo_1>();
                pickDtlMap.put(pickDtlInfo_1.getMaVerGd(), pickDtlInfo_1);
                map.put(pickDtlInfo_1.getPickCode(), pickDtlMap);
            }
            count++;
            m++;
        }
        //插入领料单明细:合并的处理
        PickDtlInfo pickDtlInfo = null;
        PickDtlInfo_1 pickDtlInfo_1 = null;
        for (String pickCode : map.keySet()) {
            for (String maCodeStr : map.get(pickCode).keySet()) {
                pickDtlInfo = new PickDtlInfo();
                pickDtlInfo_1 = map.get(pickCode).get(maCodeStr);
                pickDtlInfo.setGuid(CommonUtils.getRandomNumber());
                pickDtlInfo.setPickGd(pickDtlInfo_1.getPickGd());
                pickDtlInfo.setPickCode(pickDtlInfo_1.getPickCode());
                pickDtlInfo.setReML(pickDtlInfo_1.getReML());
                pickDtlInfo.setNum(pickDtlInfo_1.getNum());
                pickDtlInfo.setUnitName(pickDtlInfo_1.getUnitName());
                pickDtlInfo.setStoreGd(pickDtlInfo_1.getStoreGd());
                pickDtlInfo.setCreator(pickDtlInfo_1.getCreator());
                pickDtlInfo.setCreateTime(pickDtlInfo_1.getCreateTime());
                pickDtlInfo.setRemark(pickDtlInfo_1.getRemark());
                if (pickDtlDAO.InsertPickDtlInfo(pickDtlInfo) != 1) {
                    throw new SystemException("09", "第" + count + "行，新增领料单明细失败");
                }
            }
        }

        //创建出库任务
        for (PickInfo _pickInfo : pickInfoList) {
            CkTkInfo objECkTkInfo1 = new CkTkInfo();
            objECkTkInfo1.setGuid(CommonUtils.getRandomNumber());
            objECkTkInfo1.setAssCode(_pickInfo.getPickCode());
            objECkTkInfo1.setAssSource("00");//TODO 标记来源是否可以为空？
            objECkTkInfo1.setCkType("00");
            objECkTkInfo1.setExStatus("00");

            objECkTkInfo1.setCreator(CommonUtils.readUser().getRealName());

            objECkTkInfo1.setCreateTime(new Date());
            ckTkDAO.InsertCkTkInfo(objECkTkInfo1);
            CkTkInfo obbjCkTkInfo = ckTkDAO.SelectCkTkInfoByGuid(objECkTkInfo1.getGuid());
            //获取左边设置为0
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            //设置最大整数位数
            nf.setMaximumIntegerDigits(10);
            //设置最小整数位数
            nf.setMinimumIntegerDigits(10);

            //获取当前的年月日字符串
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] str = format.format(new Date()).substring(0, 10).split("-");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length; i++) {
                sb.append(str[i]);
            }
            obbjCkTkInfo.setCkCode("CK" + sb.toString() + nf.format(obbjCkTkInfo.getRuid()));
            if (ckTkDAO.UpdateCkTkInfo(obbjCkTkInfo) <= 0) {
                throw new SystemException("xxx11", "更细失败");
            }
        }

        objSaveImportResB.setMsgCode("0x00000");
        objSaveImportResB.setMsgDes("成功");
        return objSaveImportResB;
    }

    //导入 N
    @Override
    public SaveImportResB AddImportnPick(CommonsMultipartFile file) throws IOException {
        SaveImportResB objSaveImportResB = new SaveImportResB();
        //把领料单明细保存在集合中，在插入数据库的时候把相同的物料合并，数量相加
        Map<String, Map<String, NPickDtlInfo>> map = new HashMap<String, Map<String, NPickDtlInfo>>();

        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());

        List<String> npickCodeList = new ArrayList<String>();

        List<NPickInfo> pickInfoList = new ArrayList<NPickInfo>();

        Sheet sheet = wb.getSheetAt(0);
        int m = 0;
        Iterator<Row> rows = sheet.rowIterator();
        int count = 2;
        a:
        while (rows.hasNext()) {
            NPickInfo npickInfo = null;
            Row row = rows.next();
            if (sheet.getRow(2) == null) {
                throw new SystemException("xxx", "导入失败，导入的数据为空!");
            }
            if (m == 0) {
                m++;
                continue;
            }
            NPickDtlInfo npickDtlInfo = new NPickDtlInfo();
            Boolean flag = true;
            StoreInfo storeInfo = null;
            MaterialInfo materialInfo = null;
            for (int i = 0; i <= 7; i++) {
                Cell cell = row.getCell(i);
                if (cell != null && !"".equals(cell.toString().trim())) {
                    cell.setCellType(1);
                    if (i == 0) {//领料单
                        npickInfo = nPickDAO.SelectNPickInfoByPickCode(cell.getStringCellValue().trim());
                        if (npickInfo == null) {
                            npickCodeList.add(cell.getStringCellValue().trim());
                            npickInfo = new NPickInfo();
                            npickInfo.setPickCode(cell.getStringCellValue().trim());
                        } else {
                            if (!npickCodeList.contains(cell.getStringCellValue().trim())) {
                                throw new SystemException("02", "第" + count + "行，领料单：" + cell.getStringCellValue().trim() + "，已存在");
                            }
                            if (!"00".equals(npickInfo.getExStatus())) {
                                //不是待执行状态，直接结束本次读取，循环下一次
                                flag = !flag;
                                break;
                            }
                        }
                        npickDtlInfo.setPickCode(npickInfo.getPickCode());
                    } else if (i == 1) {//领料日期
                        if (DateUtil.format3(cell.getStringCellValue().trim()) == null)
                            throw new SystemException("02", "第" + count + "行，领料日期格式不对，必须是：YYYY/MM/DD的格式");
                        npickInfo.setPrePickDate(DateUtil.format3(cell.getStringCellValue().trim()));
                    } else if (i == 2) {//领料仓库
                        storeInfo = storeDAO.SelectBystoreName(cell.getStringCellValue().trim());
                        if (storeInfo == null) {
                            throw new SystemException("03", "第" + count + "行，领料仓库：" + cell.getStringCellValue().trim() + "，不存在");
                        }

                        // npickInfo.setStoreGd(storeInfo.getGuid());
                        npickDtlInfo.setStoreGd(storeInfo.getGuid());
                    } else if (i == 3) {//领用物料代码
                        materialInfo = materialDAO.SelectByMaCode(cell.getStringCellValue().trim());
                        if (materialInfo == null) {
                            throw new SystemException("04", "第" + count + "行，物料代码：" + cell.getStringCellValue().trim() + "，不存在");
                        }
                        npickDtlInfo.setMaVerGd(materialInfo.getVerGd());
                    } else if (i == 4) {//领用物料名称
                        /*MaterialInfo materialInfo = materialDAO.SelectByMaName(cell.getStringCellValue().trim());
                        if (materialInfo == null) {
                            throw new SystemException("05", "第" + count + "行，物料名称：" + cell.getStringCellValue().trim() + "，不存在");
                        }*/
                    } else if (i == 5) {//领用物料单位
                        UnitInfo unitInfo = unitDAO.SelectByUnitName(cell.getStringCellValue().trim());
                        if (unitInfo == null) {
                            throw new SystemException("06", "第" + count + "行，单位：" + cell.getStringCellValue().trim() + "，不存在");
                        }
                        npickDtlInfo.setUnitName(unitInfo.getUnitName());
                    } else if (i == 6) {//领用物料实发数量

                        InstockInfo instockInfo = instockDAO.SelectInstockInfoListByMaVerGdAndStoreGd(materialInfo.getVerGd(), storeInfo.getGuid());
                        if (instockInfo != null) {
                            if (Float.parseFloat(cell.getStringCellValue()) > instockInfo.getNum()) {
                                throw new SystemException("", "新增失败" + materialInfo.getMaterialCode() + ":的数量不能大于" + instockInfo.getNum());
                            } else {
                                if (Float.parseFloat(cell.getStringCellValue()) <= 0) {
                                    throw new SystemException("06", "第" + count + "行，数量：" + cell.getStringCellValue().trim() + "，不能小于等于零");
                                }
                                npickDtlInfo.setNum(Float.parseFloat(cell.getStringCellValue()));
                            }
                        } else {
                            throw new SystemException("06", "第" + count + "行，仓库中物料：" + materialInfo.getMaterialCode() + "-" + materialInfo.getMaterialName() + "，不存在");

                        }
                    } else if (i == 7) {
                        npickDtlInfo.setRemark(cell.getStringCellValue());
                    }
                } else {
                    if (i == 7) {
                        continue;
                    }
                    throw new SystemException("07", "第" + count + "行，第" + (i + 1) + "列不能为空");
                }
            }
            if (!flag) {
                m++;
                continue;
            }
            //保存物料信息到领料单明细中
            if ("".equals(npickInfo.getGuid()) || npickInfo.getGuid() == null) {
                npickInfo.setGuid(CommonUtils.getRandomNumber());
                npickInfo.setExStatus("00");
                npickInfo.setPickerGd("");
                npickInfo.setUse("");
                npickInfo.setCreator(CommonUtils.readUser().getRealName());
                npickInfo.setCreateTime(new Date());
                npickInfo.setdSource("00");
                //新增领料单处理
                if (nPickDAO.InsertNPickInfo(npickInfo) != 1) {
                    throw new SystemException("08", "第" + count + "行，新增领料单失败");
                }
                pickInfoList.add(npickInfo);
            }
            npickDtlInfo.setPickGd(npickInfo.getGuid());
            npickDtlInfo.setGuid(CommonUtils.getRandomNumber());
            npickDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            npickDtlInfo.setCreateTime(new Date());
            npickDtlInfo.setScanNum(0);
            //不是合并的处理
            //  nPickDtlDAO_1.InsertNPickDtlInfo_1(npickDtlInfo);
            //------
            if (map.containsKey(npickDtlInfo.getPickCode())) {
                if (map.get(npickDtlInfo.getPickCode()).containsKey(npickDtlInfo.getMaVerGd())) {
                    map.get(npickDtlInfo.getPickCode()).get(npickDtlInfo.getMaVerGd()).setNum(map.get(npickDtlInfo.getPickCode()).get(npickDtlInfo.getMaVerGd()).getNum() + npickDtlInfo.getNum());
                } else {
                    map.get(npickDtlInfo.getPickCode()).put(npickDtlInfo.getMaVerGd(), npickDtlInfo);
                }
            } else {
                Map<String, NPickDtlInfo> pickDtlMap = new HashMap<String, NPickDtlInfo>();
                pickDtlMap.put(npickDtlInfo.getMaVerGd(), npickDtlInfo);
                map.put(npickDtlInfo.getPickCode(), pickDtlMap);
            }
            count++;
            m++;
        }
        //插入领料单明细
        for (String pickCode : map.keySet()) {
            for (String maCodeStr : map.get(pickCode).keySet()) {
                if (nPickDtlDAO.InsertNPickDtlInfo(map.get(pickCode).get(maCodeStr)) != 1) {
                    throw new SystemException("09", "第" + count + "行，新增领料单明细失败");
                }
            }
        }

        //创建出库任务
        for (NPickInfo _pickInfo : pickInfoList) {
            CkTkInfo objECkTkInfo1 = new CkTkInfo();
            objECkTkInfo1.setGuid(CommonUtils.getRandomNumber());
            objECkTkInfo1.setAssCode(_pickInfo.getPickCode());
            objECkTkInfo1.setAssSource("01");
            objECkTkInfo1.setCkType("00");
            objECkTkInfo1.setExStatus("00");

            objECkTkInfo1.setCreator(CommonUtils.readUser().getRealName());

            objECkTkInfo1.setCreateTime(new Date());
            ckTkDAO.InsertCkTkInfo(objECkTkInfo1);
            CkTkInfo obbjCkTkInfo = ckTkDAO.SelectCkTkInfoByGuid(objECkTkInfo1.getGuid());
            //获取左边设置为0
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            //设置最大整数位数
            nf.setMaximumIntegerDigits(10);
            //设置最小整数位数
            nf.setMinimumIntegerDigits(10);

            //获取当前的年月日字符串
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] str = format.format(new Date()).substring(0, 10).split("-");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length; i++) {
                sb.append(str[i]);
            }
            obbjCkTkInfo.setCkCode("CK" + sb.toString() + nf.format(obbjCkTkInfo.getRuid()));
            if (ckTkDAO.UpdateCkTkInfo(obbjCkTkInfo) <= 0) {
                throw new SystemException("xxx11", "更细失败");
            }
        }

        objSaveImportResB.setMsgCode("0x00000");
        objSaveImportResB.setMsgDes("成功");
        return objSaveImportResB;
    }

    //导出
    @Override
    public ByteArrayOutputStream exportPickExcel(Integer busData) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            //查询领料单信息
            PickInfo pickInfo = pickDAO.SelectPickInfoByRuid(busData);
            if (pickInfo == null) {
                throw new SystemException("11", "领料单不存在");
            }
            //根据领料单查询明细
            List<PickDtlInfo> pickDtlInfoList = new ArrayList<PickDtlInfo>();
            pickDtlInfoList = pickDtlDAO.SelectPickDtlInfoByPickGd(pickInfo.getGuid());
            //物料特性
            List<GetMaInfoResDMaPropertyInfo> getMaInfoResDMaPropertyInfos = new ArrayList<GetMaInfoResDMaPropertyInfo>();
            // for(PickDtlInfo p:pickDtlInfoList){
            //查询物料特性
            List<ExpandDtlInfo> expandDtlInfos = expandDtlDAO.selectEE("00");
            if (expandDtlInfos.size() > 0 && expandDtlInfos != null) {
                for (ExpandDtlInfo expandDtlInfo : expandDtlInfos) {
                    GetMaInfoResDMaPropertyInfo getMaInfoResDMaPropertyInfo = new GetMaInfoResDMaPropertyInfo();
                    //添加字段名称
                    getMaInfoResDMaPropertyInfo.setFieldName(expandDtlInfo.getFieldName());
                    //添加定义类型
                    getMaInfoResDMaPropertyInfo.setFiledType(expandDtlInfo.getFiledType());
                    //添加字段显示名
                    getMaInfoResDMaPropertyInfo.setDisplayName(expandDtlInfo.getDisplayName());
                    //通过MaVerGd 查询对应的值
                        /*MaPropertyInfo maPropertyInfos=maPropertyDAO.SelectByMaVerF(p.getMaVerGd());

                        if(expandDtlInfo.getFiledType().equals("00")){
                            if(maPropertyInfos==null){
                                    String s = "";
                                    Field f = null;
                                    //通过反射传入字段名查出对应的值
                                    f = maPropertyInfos.getClass().getDeclaredField(expandDtlInfo.getFieldName());
                                    f.setAccessible(true);
                                    s = (String) f.get(maPropertyInfos);
                                    //添加字段值val
                                    getMaInfoResDMaPropertyInfo.setVal(s);
                            }else {
                                    //添加字段值val
                                    getMaInfoResDMaPropertyInfo.setVal(expandDtlInfo.getCusData());
                            }
                        }

                        if(expandDtlInfo.getFiledType().equals("01")){
                            String s = "";
                            Field f = null;
                            //通过反射传入字段名查出对应的值
                            f = maPropertyInfos.getClass().getDeclaredField(expandDtlInfo.getFieldName());
                            f.setAccessible(true);
                            s = (String) f.get(maPropertyInfos);
                            //添加字段值val
                            getMaInfoResDMaPropertyInfo.setVal(s);
                        }*/
                    getMaInfoResDMaPropertyInfos.add(getMaInfoResDMaPropertyInfo);
                }
            }
            // }


            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("领料单");
            sheet1.setPrintGridlines(false);
            sheet1.setGridsPrinted(false);

            String date = DateUtil.format(pickInfo.getPrePickDate()).replace("-", "/").split(" ")[0];
            //TODO ---------------------------------------------------------------
            /*StoreInfo storeInfo = storeDAO.SelectByStoreGd(pickInfo.getStoreGd());
            if (storeInfo == null) {
                throw new SystemException("12", "领料仓库不存在");
            }*/
            String storeName = "";//storeInfo.getStoreName();
            String code = pickInfo.getAssCode();
            if (code == null) {
                code = "";
            }

            /*第一行和第二行数据*/
            Object[][] datas1 = {
                    {"预领料日期", date, "工单号", code, "领料单号", pickInfo.getPickCode(), "领料仓库"},
                    {"物料代码", "物料名称", "物料描述", "规格型号", "单位", "数量", "备注", ""}
            };
            HSSFRow row1;
            HSSFCell cell1;
            //给datas1动态添加值
            Object[][] arr = new Object[datas1.length][datas1[0].length + getMaInfoResDMaPropertyInfos.size()];
            int jj = 0;
            for (int i = 0; i < arr[0].length; i++) {
                if (i < datas1[0].length) {
                    arr[0][i] = datas1[0][i];
                    arr[1][i] = datas1[1][i];
                } else {
                    arr[0][i] = "";
                    arr[1][i] = getMaInfoResDMaPropertyInfos.get(jj).getDisplayName();
                    jj++;
                }

            }
            datas1 = arr;

            //建表
            for (int i = 0; i < 2; i++) {
                row1 = sheet1.createRow(i);//创建表格行
                for (int j = 0; j < datas1[0].length; j++) {
                    cell1 = row1.createCell(j);//根据表格行创建单元格
                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }

            /**********************************写入领料单明细************************************/
            HSSFSheet sheet4 = wb.getSheet("领料单");
            HSSFRow row4;
            HSSFCell cell4;
            //建表
            int i = 2;
            for (PickDtlInfo pickDtlInfo : pickDtlInfoList) {
                row4 = sheet4.createRow(i++);//创建表格行
                //查询物料
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(pickDtlInfo.getMaVerGd());

                //通过MaVerGd 查询对应的值
                MaPropertyInfo maPropertyInfos = maPropertyDAO.SelectByMaVerF(pickDtlInfo.getMaVerGd());
                List<String> list = new ArrayList();
                List<ExpandDtlInfo> e = expandDtlDAO.selectEE("00");
                if (e.size() > 0 && e != null) {
                    for (ExpandDtlInfo expandDtlInfo : expandDtlInfos) {
                        if (expandDtlInfo.getFiledType().equals("00")) {
                            if (maPropertyInfos != null) {
                                String s = "";
                                Field f = null;
                                //通过反射传入字段名查出对应的值
                                f = maPropertyInfos.getClass().getDeclaredField(expandDtlInfo.getFieldName());
                                f.setAccessible(true);
                                s = (String) f.get(maPropertyInfos);
                                //添加字段值val
                                list.add(s);
                            } else {
                                //添加字段值val
                                list.add(expandDtlInfo.getCusData());
                            }
                        }

                        if (expandDtlInfo.getFiledType().equals("01")) {
                            String s = "";
                            Field f = null;
                            //通过反射传入字段名查出对应的值
                            f = maPropertyInfos.getClass().getDeclaredField(expandDtlInfo.getFieldName());
                            f.setAccessible(true);
                            s = (String) f.get(maPropertyInfos);
                            //添加字段值val
                            if (s == null) {
                                list.add("");
                            } else {
                                list.add(s);
                            }

                        }
                    }
                }
                int sax = 0;
                for (int j = 0; j < datas1[0].length; j++) {
                    cell4 = row4.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        if (maVerInfo != null) {
                            cell4.setCellValue(maVerInfo.getMaterialCode());
                        }
                    } else if (j == 1) {
                        if (maVerInfo != null) {
                            cell4.setCellValue(maVerInfo.getMaterialName());
                        }
                    } else if (j == 2) {
                        if (maVerInfo != null) {
                            //查询明细的物料描述
                            MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                            if (materialInfo == null || "".equals(materialInfo)) {
                                cell4.setCellValue("");
                            } else {
                                cell4.setCellValue(materialInfo.getMaterialDes());
                            }
                        } else {
                            continue;
                        }
                    } else if (j == 3) {
                        cell4.setCellValue("");
                    } else if (j == 4) {
                        cell4.setCellValue(pickDtlInfo.getUnitName());

                    } else if (j == 5) {
                        cell4.setCellValue(pickDtlInfo.getNum());

                    } else if (j == 6) {
                        cell4.setCellValue(pickDtlInfo.getRemark());
                    } else {
                        cell4.setCellValue(list.get(sax));
                        sax++;
                    }
                }
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x00000", "Export False");
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

    //导出下载推荐库位单
    @Override
    public ByteArrayOutputStream ExportPickExcel(Integer busData, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset = utf-8");
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {
            List<GetPickInfoResDExcel> dExcelList = new ArrayList<GetPickInfoResDExcel>();
            GetPickInfoResDExcel dExcel = null;

            PickInfo pickInfo = pickDAO.SelectPickInfoByRuid(busData);

            Map<String, GetPickInfoResDExcel> map = new HashMap<>();

            if (pickInfo != null) {
                List<PickDtlInfo> objEPickDtlInfo = pickDtlDAO.SelectPickDtlInfoByPickGd(pickInfo.getGuid());

                if (objEPickDtlInfo != null && objEPickDtlInfo.size() > 0) {
                    for (PickDtlInfo obj : objEPickDtlInfo) {

                        List<InstockDtlInfo> objEInstockDtlInfo = instockDtlDAO.SelectAllRecBatch(obj.getStoreGd(), obj.getMaVerGd());
                        if (objEInstockDtlInfo != null && objEInstockDtlInfo.size() > 0) {
                            for (InstockDtlInfo obj1 : objEInstockDtlInfo) {

                                BInfo bInfo = bdao.selectBatchInfoByBatch(obj1.getBatch());

                                if (bInfo != null && bInfo.getCanNum() > 0) {
                                    LocationInfo locationInfo = locationDAO.SelectlocationGd(obj1.getLocationGd());

                                    if (map.containsKey(obj1.getLocationGd() + obj1.getMaVerGd())) {
                                        dExcel = map.get(obj1.getLocationGd() + obj1.getMaVerGd());

                                        dExcel.setNum(dExcel.getNum() + bInfo.getCanNum());
                                    } else {
                                        dExcel = new GetPickInfoResDExcel();
                                        if (locationInfo != null) {
                                            dExcel.setLName(locationInfo.getlName());
                                        } else {
                                            dExcel.setLName("");
                                        }

                                        dExcel.setPickCode(obj.getPickCode());
                                        //获取物料
                                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                        if (maVerInfo != null) {
                                            dExcel.setMaCode(maVerInfo.getMaterialCode());
                                            dExcel.setMaName(maVerInfo.getMaterialName());
                                            MaterialInfo materialInfo = materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                                            if (materialInfo.getMaterialDes() != null && !materialInfo.getMaterialDes().equals("")) {
                                                dExcel.setMaDes(materialInfo.getMaterialDes());
                                            } else {
                                                dExcel.setMaDes("");
                                            }
                                        } else {
                                            dExcel.setMaCode("");
                                            dExcel.setMaName("");
                                            dExcel.setMaDes("");
                                        }

                                        StoreInfo storeInfo = storeDAO.SelectByStoreGd(obj.getStoreGd());
                                        if (storeInfo != null) {
                                            dExcel.setStoreName(storeInfo.getStoreName());
                                        } else {
                                            dExcel.setStoreName("");
                                        }
                                        dExcel.setUnitName(obj.getUnitName());
                                        dExcel.setRemark(obj.getRemark());
                                        dExcel.setNum(bInfo.getCanNum());
                                        map.put(obj1.getLocationGd() + obj1.getMaVerGd(), dExcel);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (Map.Entry<String, GetPickInfoResDExcel> entry : map.entrySet()) {
                dExcelList.add(entry.getValue());
            }
            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"领料单号", "物料代码", "物料名称", "物料描述", "仓库", "库位", "数量", "单位", "备注"}};

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


            /**********************************数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dExcelList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(dExcelList.get(i).getPickCode());
                    } else if (j == 1) {
                        cell3.setCellValue(dExcelList.get(i).getMaCode());
                    } else if (j == 2) {
                        cell3.setCellValue(dExcelList.get(i).getMaName());
                    } else if (j == 3) {
                        cell3.setCellValue(dExcelList.get(i).getMaDes());
                    } else if (j == 4) {
                        cell3.setCellValue(dExcelList.get(i).getStoreName());
                    } else if (j == 5) {
                        cell3.setCellValue(dExcelList.get(i).getLName());
                    } else if (j == 6) {
                        cell3.setCellValue(dExcelList.get(i).getNum());
                    } else if (j == 7) {
                        cell3.setCellValue(dExcelList.get(i).getUnitName());
                    } else if (j == 8) {
                        cell3.setCellValue(dExcelList.get(i).getRemark());
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

    //导出 N
    @Override
    public ByteArrayOutputStream exportnPickExcel(Integer busData) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            //查询领料单信息
            NPickInfo npickInfo = nPickDAO.SelectNPickInfoByRuid(busData);
            if (npickInfo == null) {
                throw new SystemException("11", "领料单不存在");
            }
            //根据领料单查询明细
            List<NPickDtlInfo> npickDtlInfoList = new ArrayList<NPickDtlInfo>();
            npickDtlInfoList = nPickDtlDAO.SelectNPickDtlInfoByPickGd(npickInfo.getGuid());
            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("领料单");
            sheet1.setPrintGridlines(false);
            sheet1.setGridsPrinted(false);

            String date = DateUtil.format(npickInfo.getPrePickDate()).replace("-", "/").split(" ")[0];

            /*第一行和第二行数据*/
            Object[][] datas1 = {
                    {"预领料日期", date, "工单号", "", "领料单号", "", "", "", npickInfo.getPickCode(), "", ""},
                    {"物料代码", "物料名称", "物料描述", "领料仓库", "尺寸", "材质", "规格", "型号", "单位", "数量", "备注", "", ""}
            };
            HSSFRow row1;
            HSSFCell cell1;
            //建表
            for (int i = 0; i < 2; i++) {
                row1 = sheet1.createRow(i);//创建表格行
                for (int j = 0; j < 11; j++) {
                    cell1 = row1.createCell(j);//根据表格行创建单元格
                    cell1.setCellValue(String.valueOf(datas1[i][j]));
                }
            }

            /**********************************写入领料单明细************************************/
            HSSFSheet sheet4 = wb.getSheet("领料单");
            HSSFRow row4;
            HSSFCell cell4;
            //建表
            int i = 2;
            for (NPickDtlInfo pickDtlInfo : npickDtlInfoList) {
                row4 = sheet4.createRow(i++);//创建表格行
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(pickDtlInfo.getMaVerGd());
                for (int j = 0; j < 10; j++) {
                    cell4 = row4.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        if (maVerInfo != null) {
                            cell4.setCellValue(maVerInfo.getMaterialCode());
                        }
                    } else if (j == 1) {
                        if (maVerInfo != null) {
                            cell4.setCellValue(maVerInfo.getMaterialName());
                        }
                    } else if (j == 2) {
                        if (maVerInfo != null) {
                            //查询明细的物料描述
                            MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                            if (materialInfo == null || "".equals(materialInfo)) {
                                cell4.setCellValue("");
                            } else {
                                cell4.setCellValue(materialInfo.getMaterialDes());
                            }
                        } else {
                            continue;
                        }
                    } else if (j == 3) {
                        if (npickInfo != null) {
                            StoreInfo storeInfo = storeDAO.SelectByStoreGd(pickDtlInfo.getStoreGd());
                            if (storeInfo != null) {
                                cell4.setCellValue(storeInfo.getStoreName());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else {
                            continue;
                        }
                    } else if (j == 4) {
                        if (maVerInfo != null) {
                            MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                            if (objEMaPropertyInfo != null) {
                                // cell4.setCellValue(objEMaPropertyInfo.getSize());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else {
                            continue;
                        }

                    } else if (j == 5) {
                        if (maVerInfo != null) {
                            MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                            if (objEMaPropertyInfo != null) {
                                //cell4.setCellValue(objEMaPropertyInfo.getMaterial());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else {
                            continue;
                        }

                    } else if (j == 6) {
                        if (maVerInfo != null) {
                            MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                            if (objEMaPropertyInfo != null) {
                                // cell4.setCellValue(objEMaPropertyInfo.getNorm());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else {
                            continue;
                        }

                    } else if (j == 7) {
                        if (maVerInfo != null) {
                            MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                            if (objEMaPropertyInfo != null) {
                                // cell4.setCellValue(objEMaPropertyInfo.getModel());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else {
                            continue;
                        }

                    } else if (j == 8) {
                        cell4.setCellValue(pickDtlInfo.getUnitName());

                    } else if (j == 9) {
                        cell4.setCellValue(pickDtlInfo.getNum());

                    } else if (j == 10) {
                        cell4.setCellValue(pickDtlInfo.getRemark());
                    }
                }
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x00000", "Export False");
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

    //导出领料确认单 N
    @Override
    public ByteArrayOutputStream exportnPickExcel1(Integer busData) throws IOException {

        String file = CommonUtils.class.getResource("/upload/sss.xlsx").getPath();
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook(file);
        try {
            //查询领料单信息
            NPickInfo npickInfo = nPickDAO.SelectNPickInfoByRuid(busData);
            if (npickInfo == null) {
                throw new SystemException("11", "领料单不存在");
            }

            //根据领料单查询明细
            List<NPickDtlInfo> npickDtlInfoList = nPickDtlDAO.SelectNPickDtlInfoByPickGd(npickInfo.getGuid());

            //过滤仓库
            Map<String, NPickDtlInfo> map = new HashMap<String, NPickDtlInfo>();

            for (NPickDtlInfo obj : npickDtlInfoList) {
                map.put(obj.getStoreGd(), obj);

            }
            StoreInfo storeInfos = null;
            String f1 = null;
            String storename = null;
            String fss = "";
            for (Map.Entry<String, NPickDtlInfo> entry : map.entrySet()) {
                storeInfos = storeDAO.SelectByguid(entry.getKey());
                storename = storeInfos.getStoreName();
                f1 = storeInfos.getF1();

                if (f1 == null && "".equals(f1)) {
                    throw new SystemException("11", "仓库" + storename + "项目号为空");
                }

                if (f1 == null) {
                    f1 = "";
                }
                fss += f1 + ";";
            }


            XSSFSheet sheet1 = wb.getSheetAt(0);
            sheet1.setPrintGridlines(false);

            UserInfo userInfo = userDAO.SelectUserRd(npickInfo.getPickerGd());
            if (userInfo == null) {
                throw new SystemException("11", "领用人不存在");
            }


            /*第一行和第二行数据*/
            Object[][] datas1 = {
                    {"", "领料单-" + fss, "", "", "", "", ""},
                    {"", npickInfo.getUse(), "", "", "", "", ""},
                    {"", userInfo.getRealName(), "", "", "", "", ""},
            };

            XSSFRow row1;
            XSSFCell cell1;
            //建表
            for (int i = 0; i < 3; i++) {
                row1 = sheet1.getRow(i);//创建表格行

                cell1 = row1.getCell(1);//根据表格行创建单元格
                cell1.setCellValue(String.valueOf(datas1[i][1]));

            }

            /**********************************写入领料单明细************************************/
            //XSSFSheet sheet4 = wb.getSheetAt(0);

            XSSFRow row4;
            XSSFCell cell4;
            //建表
            int i = 4;
            for (int i1 = 0; i1 <= npickDtlInfoList.size(); i1++) {
                //sheet1.shiftRows(i+1, sheet1.getLastRowNum(), 1);
                row4 = sheet1.createRow(i++);//创建表格行
                row4.setHeightInPoints(25);

                if (i1 == npickDtlInfoList.size()) {
                    for (int j = 0; j < 7; j++) {
                        cell4 = row4.createCell(j);
                        cell4.setCellValue("");
                    }
                } else {
                    for (int j = 0; j < 7; j++) {
                        cell4 = row4.createCell(j);//根据表格行创建单元格
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(npickDtlInfoList.get(i1).getMaVerGd());
                        if (j == 0) {
                            if (maVerInfo != null) {
                                cell4.setCellValue(maVerInfo.getMaterialName());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else if (j == 1) {
                            if (maVerInfo != null) {
                                MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                                if (objEMaPropertyInfo != null) {
                                    //  cell4.setCellValue(objEMaPropertyInfo.getSize());
                                } else {
                                    cell4.setCellValue("");
                                }
                            }
                        } else if (j == 2) {
                            if (maVerInfo != null) {
                                MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                                if (objEMaPropertyInfo != null) {
                                    // cell4.setCellValue(objEMaPropertyInfo.getMaterial());
                                } else {
                                    cell4.setCellValue("");
                                }
                            }
                        } else if (j == 3) {
                            if (maVerInfo != null) {
                                MaPropertyInfo objEMaPropertyInfo = maPropertyDAO.SelectByMaVerGd(maVerInfo.getGuid());
                                if (objEMaPropertyInfo != null) {
                                    //cell4.setCellValue(objEMaPropertyInfo.getNorm());
                                } else {
                                    cell4.setCellValue("");
                                }
                            }
                        } else if (j == 4) {
                            if (maVerInfo != null) {
                                cell4.setCellValue(maVerInfo.getSuName());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else if (j == 5) {
                            if (npickDtlInfoList.get(i1).getNum() != 0.0) {
                                cell4.setCellValue(npickDtlInfoList.get(i1).getNum());
                            } else {
                                cell4.setCellValue("");
                            }
                        } else if (j == 6) {
                            if (npickDtlInfoList.get(i1).getRemark() == null) {
                                cell4.setCellValue(npickDtlInfoList.get(i1).getRemark());
                            } else {
                                cell4.setCellValue("");
                            }
                        }

                        XSSFCellStyle cellStyle = wb.createCellStyle();

                        XSSFFont font = wb.createFont();
                        font.setFontHeightInPoints((short) 15);
                        cellStyle.setFont(font);
                        cellStyle.setBorderTop(BorderStyle.THIN);
                        cellStyle.setBorderBottom(BorderStyle.THIN);
                        cellStyle.setBorderLeft(BorderStyle.THIN);
                        cellStyle.setBorderRight(BorderStyle.THIN);

                        cell4.setCellStyle(cellStyle);
                    }
                }
            }

            Date date;
            if (npickInfo.getLastModifyTime() == null && npickInfo.getCreateTime() != null) {
                date = npickInfo.getCreateTime();
            } else {
                date = npickInfo.getLastModifyTime();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = sdf.format(date);

            String name = "";
            if (npickInfo.getLastModifyMan() == null && npickInfo.getCreator() != null) {
                name = npickInfo.getCreator();
            } else {
                name = npickInfo.getLastModifyMan();
            }

            Object[][] datas2 = {
                    {"", name, "", "", "", "", s1}
            };
/*
             row2;
             cell2;*/
            int cc = npickDtlInfoList.size() + 1;
            int bb = 6 + cc;
            //建表

            for (int a = bb; a <= bb; a++) {
                XSSFRow row2 = sheet1.getRow(a);//创建表格行

                for (int j = 0; j < 7; j++) {

                    if (j == 1 || j == 6) {
                        XSSFCell cell2 = row2.createCell(j);//根据表格行创建单元格
                        cell2.setCellValue(String.valueOf(datas2[0][j]));

                        XSSFCellStyle cellStyle = wb.createCellStyle();

                        XSSFFont font = wb.createFont();
                        font.setFontHeightInPoints((short) 15);
                        cellStyle.setFont(font);

                        cell2.setCellStyle(cellStyle);
                    }
                }
            }


            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch (Exception e) {
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x00000", "Export False");
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

    //导出下载推荐库位单  N
    @Override
    public ByteArrayOutputStream ExportnPickExcel(Integer busData) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {
            List<GetPickInfoResDExcel> dExcelList = new ArrayList<GetPickInfoResDExcel>();
            GetPickInfoResDExcel dExcel = null;

            NPickInfo nPickInfo = nPickDAO.SelectNPickInfoByRuid(busData);

            Map<String, GetPickInfoResDExcel> map = new HashMap<>();

            if (nPickInfo != null) {
                List<NPickDtlInfo> objENPickDtlInfo = nPickDtlDAO.SelectNPickDtlInfoByPickGd(nPickInfo.getGuid());

                if (objENPickDtlInfo != null && objENPickDtlInfo.size() > 0) {
                    for (NPickDtlInfo obj : objENPickDtlInfo) {

                        List<InstockDtlInfo> objEInstockDtlInfo = instockDtlDAO.SelectAllRecBatch(obj.getStoreGd(), obj.getMaVerGd());
                        if (objEInstockDtlInfo != null && objEInstockDtlInfo.size() > 0) {
                            for (InstockDtlInfo obj1 : objEInstockDtlInfo) {

                                BInfo bInfo = bdao.selectBatchInfoByBatch(obj1.getBatch());

                                if (bInfo != null && bInfo.getCanNum() > 0) {
                                    LocationInfo locationInfo = locationDAO.SelectlocationGd(obj1.getLocationGd());

                                    if (map.containsKey(obj1.getLocationGd() + obj1.getMaVerGd())) {
                                        dExcel = map.get(obj1.getLocationGd() + obj1.getMaVerGd());

                                        dExcel.setNum(dExcel.getNum() + bInfo.getCanNum());
                                    } else {
                                        dExcel = new GetPickInfoResDExcel();
                                        if (locationInfo != null) {
                                            dExcel.setLName(locationInfo.getlName());
                                        } else {
                                            dExcel.setLName("");
                                        }

                                        dExcel.setPickCode(obj.getPickCode());
                                        //获取物料
                                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                        if (maVerInfo != null) {
                                            dExcel.setMaCode(maVerInfo.getMaterialCode());
                                            dExcel.setMaName(maVerInfo.getMaterialName());
                                            MaterialInfo materialInfo = materialDAO.SelectByMaCode(maVerInfo.getMaterialCode());
                                            if (materialInfo.getMaterialDes() != null && !materialInfo.getMaterialDes().equals("")) {
                                                dExcel.setMaDes(materialInfo.getMaterialDes());
                                            } else {
                                                dExcel.setMaDes("");
                                            }
                                        } else {
                                            dExcel.setMaCode("");
                                            dExcel.setMaName("");
                                            dExcel.setMaDes("");
                                        }

                                        StoreInfo storeInfo = storeDAO.SelectByStoreGd(obj.getStoreGd());
                                        if (storeInfo != null) {
                                            dExcel.setStoreName(storeInfo.getStoreName());
                                        } else {
                                            dExcel.setStoreName("");
                                        }
                                        dExcel.setUnitName(obj.getUnitName());
                                        dExcel.setRemark(obj.getRemark());
                                        dExcel.setNum(bInfo.getCanNum());
                                        map.put(obj1.getLocationGd() + obj1.getMaVerGd(), dExcel);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (Map.Entry<String, GetPickInfoResDExcel> entry : map.entrySet()) {
                dExcelList.add(entry.getValue());
            }
            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"领料单号", "物料代码", "物料名称", "物料描述", "仓库", "库位", "数量", "单位", "备注"}};

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


            /**********************************数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < dExcelList.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length);//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(dExcelList.get(i).getPickCode());
                    } else if (j == 1) {
                        cell3.setCellValue(dExcelList.get(i).getMaCode());
                    } else if (j == 2) {
                        cell3.setCellValue(dExcelList.get(i).getMaName());
                    } else if (j == 3) {
                        cell3.setCellValue(dExcelList.get(i).getMaDes());
                    } else if (j == 4) {
                        cell3.setCellValue(dExcelList.get(i).getStoreName());
                    } else if (j == 5) {
                        cell3.setCellValue(dExcelList.get(i).getLName());
                    } else if (j == 6) {
                        cell3.setCellValue(dExcelList.get(i).getNum());
                    } else if (j == 7) {
                        cell3.setCellValue(dExcelList.get(i).getUnitName());
                    } else if (j == 8) {
                        cell3.setCellValue(dExcelList.get(i).getRemark());
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

    //下达 N
    @Override
    public SaveNPickInfoRes GetSaveNPickInfoReq03(SaveNPickInfoReq03 argSaveNPickInfoReq03) {
        SaveNPickInfoRes saveNPickInfoRes = new SaveNPickInfoRes();
        SaveNPickInfoResB saveNPickInfoResB = new SaveNPickInfoResB();
        SaveNPickInfoResD saveNPickInfoResD = new SaveNPickInfoResD();
        NPickInfo nPickInfo = nPickDAO.SelectNPickInfoByRuid(argSaveNPickInfoReq03.getPickRd());
        if (nPickInfo == null) {
            throw new SystemException("", "下达失败，该单子不存在");
        }
        //下达的前提
        if ("00".equals(nPickInfo.getdSource())) {
            throw new SystemException("", "下达失败，该单子来源于外部数据");
        }
        //审核状态 00  01  02
        if ("01".equals(nPickInfo.getsStatus())) {
            throw new SystemException("", "领料单已下达,不能重复下达");
        } else if ("02".equals(nPickInfo.getsStatus())) {
            throw new SystemException("", "领料单已取消,不能进行下达");
        } else {
            nPickInfo.setsStatus("01");
            nPickInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
            nPickInfo.setLastModifyTime(new Date());
            if (nPickDAO.UpdatenNPickInfo(nPickInfo) <= 0) {
                throw new SystemException("", "下达失败");
            }
            //向出库任务中新增数据
            CkTkInfo objECkTkInfo1 = new CkTkInfo();
            objECkTkInfo1.setGuid(CommonUtils.getRandomNumber());
            objECkTkInfo1.setCkType("00");
            objECkTkInfo1.setCkCode("aa");
            objECkTkInfo1.setAssCode(nPickInfo.getPickCode());
            objECkTkInfo1.setAssSource("01");
            objECkTkInfo1.setExStatus("00");
            objECkTkInfo1.setAssCodeGd(nPickInfo.getGuid());
//            objECkTkInfo1.setSubmitStore("00");
            objECkTkInfo1.setCreator((String) SecurityUtils.getSubject().getPrincipal());
            objECkTkInfo1.setCreateTime(new Date());
            ckTkDAO.InsertCkTkInfo(objECkTkInfo1);
            //先新增 后更新
            CkTkInfo obbjCkTkInfo = ckTkDAO.SelectCkTkInfoByGuid(objECkTkInfo1.getGuid());
            //获取左边设置为0
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            //设置最大整数位数
            nf.setMaximumIntegerDigits(10);
            //设置最小整数位数
            nf.setMinimumIntegerDigits(10);

            //获取当前的年月日字符串
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] str = format.format(new Date()).substring(0, 10).split("-");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length; i++) {
                sb.append(str[i]);
            }
            obbjCkTkInfo.setCkCode("CK" + sb.toString() + nf.format(obbjCkTkInfo.getRuid()));
            if (ckTkDAO.UpdateCkTkInfo(obbjCkTkInfo) <= 0) {
                throw new SystemException("xxx11", "更细失败");
            }
        }
        saveNPickInfoResD.setPickRd(nPickInfo.getRuid());
        saveNPickInfoResD.setPickCode(nPickInfo.getPickCode());
        saveNPickInfoResB.setData(saveNPickInfoResD);
        saveNPickInfoRes.setBody(saveNPickInfoResB);
        return saveNPickInfoRes;
    }

    //取消 N  这边用同一个实体类
    @Override
    public SaveNPickInfoRes GetCancelSaveNPickInfoReq03(SaveNPickInfoReq03 argSaveNPickInfoReq03) {
        SaveNPickInfoRes saveNPickInfoRes = new SaveNPickInfoRes();
        SaveNPickInfoResB saveNPickInfoResB = new SaveNPickInfoResB();
        SaveNPickInfoResD saveNPickInfoResD = new SaveNPickInfoResD();
        NPickInfo nPickInfo = nPickDAO.SelectNPickInfoByRuid(argSaveNPickInfoReq03.getPickRd());
        if (nPickInfo == null) {
            throw new SystemException("", "取消失败，该单子不存在");
        }
        //取消的前提
        if ("00".equals(nPickInfo.getdSource())) {
            throw new SystemException("", "取消失败，该单子来源于外部数据");
        }

        if ("00".equals(nPickInfo.getsStatus())) {
            throw new SystemException("", "领料单未下达，不能取消");
        } else if ("02".equals(nPickInfo.getsStatus())) {
            throw new SystemException("", "领料单已取消，不能重复取消");
        } else if ("01".equals(nPickInfo.getsStatus()) && "02".equals(nPickInfo.getExStatus())) {
            throw new SystemException("", "领料单已完成不能取消");
        } else if ("01".equals(nPickInfo.getsStatus()) && "01".equals(nPickInfo.getExStatus())) {
            throw new SystemException("", "目前该单处于出货中，不能进行取消");
        } else if ("01".equals(nPickInfo.getsStatus()) && "00".equals(nPickInfo.getExStatus())) {
            nPickInfo.setsStatus("02");
            nPickInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
            nPickInfo.setLastModifyTime(new Date());
            if (nPickDAO.UpdatenNPickInfo(nPickInfo) <= 0) {
                throw new SystemException("", "取消失败");
            }
            CkTkInfo ckTkInfo = ckTkDAO.SelectCkTkInfoByAssCodeAndAssSource(nPickInfo.getPickCode(), "01");
            if (ckTkInfo == null) {
                throw new SystemException("", "取消失败,出库表中没有该条信息");
            }
            ckTkInfo.setCancelor((String) SecurityUtils.getSubject().getPrincipal());
            ckTkInfo.setCancelTime(new Date());
            ckTkInfo.setRuid(ckTkInfo.getRuid());
            ckTkInfo.setExStatus("03");
            if (ckTkDAO.UpdateCkTkInfo(ckTkInfo) <= 0) {
                throw new SystemException("", "取消失败");
            }
        } else {
            nPickInfo.setsStatus("02");
            nPickInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
            nPickInfo.setLastModifyTime(new Date());
            if (nPickDAO.UpdatenNPickInfo(nPickInfo) <= 0) {
                throw new SystemException("", "取消失败");
            }

        }
        saveNPickInfoResD.setPickRd(nPickInfo.getRuid());
        saveNPickInfoResD.setPickCode(nPickInfo.getPickCode());
        saveNPickInfoResB.setData(saveNPickInfoResD);
        saveNPickInfoRes.setBody(saveNPickInfoResB);
        return saveNPickInfoRes;
    }

    //下达
    @Override
    public SavePickInfoRes ModPickInfoOn(SavePickInfoReq03 argBD03) {
        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询工单领料
        PickInfo objEPickInfo = pickDAO.SelectPickInfoByRuid(argBD03.getPickRd());
        if (objEPickInfo == null) {
            throw new SystemException("", "领料单不存在");
        }
        if ("01".equals(objEPickInfo.getsStatus())) {
            throw new SystemException("", "领料单已下达,不能重复下达");
        } else if ("02".equals(objEPickInfo.getsStatus())) {
            throw new SystemException("", "领料单已取消,不能进行下达");
        }

        objEPickInfo.setsStatus("01");
        objEPickInfo.setLastModifyMan(userName);
        objEPickInfo.setLastModifyTime(date);
        if (pickDAO.UpdatePickInfo(objEPickInfo) <= 0) {
            throw new SystemException("", "领料单下达失败");
        }
        CkTkInfo objECkTkInfo = new CkTkInfo();
        objECkTkInfo.setGuid(CommonUtils.getRandomNumber());
        objECkTkInfo.setCkType("00");
        objECkTkInfo.setCkCode(objECkTkInfo.getGuid());
        objECkTkInfo.setAssCodeGd(objEPickInfo.getGuid());
        objECkTkInfo.setAssCode(objEPickInfo.getPickCode());
        objECkTkInfo.setAssSource("00");
        objECkTkInfo.setExStatus("00");
        objECkTkInfo.setCreator(userName);
        objECkTkInfo.setCreateTime(date);
        ckTkDAO.InsertCkTkInfo(objECkTkInfo);

        objECkTkInfo = ckTkDAO.SelectCkTkInfoByGuid(objECkTkInfo.getGuid());

        //获取左边设置为0
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(10);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(10);

        //获取当前的年月日字符串
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] str = format.format(new Date()).substring(0, 10).split("-");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
        }
        objECkTkInfo.setCkCode("CK" + sb.toString() + nf.format(objECkTkInfo.getRuid()));
        if (ckTkDAO.UpdateCkTkInfo(objECkTkInfo) <= 0) {
            throw new SystemException("", "领料单下达生成出库任务失败");
        }

        return new SavePickInfoRes();
    }

    //取消
    @Override
    public SavePickInfoRes ModPickInfoOff(SavePickInfoReq04 argBD04) {
        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询工单领料
        PickInfo objEPickInfo = pickDAO.SelectPickInfoByRuid(argBD04.getPickRd());
        if (objEPickInfo == null) {
            throw new SystemException("", "领料单不存在");
        }
        if ("00".equals(objEPickInfo.getsStatus())) {
            throw new SystemException("", "领料单未下达，不能取消");
        } else if ("02".equals(objEPickInfo.getsStatus())) {
            throw new SystemException("", "领料单已取消，不能重复取消");
        } else if ("01".equals(objEPickInfo.getsStatus())) {
            if ("01".equals(objEPickInfo.getExStatus())) {
                throw new SystemException("", "状态进行中不能取消");
            } else if ("02".equals(objEPickInfo.getExStatus())) {
                throw new SystemException("", "状态已完成不能取消");
            } else if ("03".equals(objEPickInfo.getExStatus())) {
                throw new SystemException("", "状态已取消不能取消");
            } else if ("00".equals(objEPickInfo.getExStatus())) {
                objEPickInfo.setsStatus("02");
                objEPickInfo.setLastModifyMan(userName);
                objEPickInfo.setLastModifyTime(date);
                if (pickDAO.UpdatePickInfo(objEPickInfo) <= 0) {
                    throw new SystemException("", "领料单取消失败");
                }
                CkTkInfo objECkTkInfo = ckTkDAO.SelectCkTkInfoByAssCodeAndAssSource(objEPickInfo.getPickCode(), "00");
                if (objECkTkInfo == null) {
                    throw new SystemException("", "出库任务信息不存在");
                }
                objECkTkInfo.setExStatus("03");
                if (ckTkDAO.UpdateCkTkInfo(objECkTkInfo) <= 0) {
                    throw new SystemException("", "领料单取消失败");
                }
            }
        }

        return new SavePickInfoRes();
    }
}
