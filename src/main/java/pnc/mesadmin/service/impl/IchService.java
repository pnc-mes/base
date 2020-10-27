package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoRes;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoResB;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoResD;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoRes;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoResB;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoResD;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoResDStore;
import pnc.mesadmin.dto.GetIncInfoRes.*;
import pnc.mesadmin.dto.GetWIncMaInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveIChInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.IchIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class IchService implements IchIService {

    @Resource
    private BaseIService baseIService;

    @Resource
    private InCDAO inCDAO;

    @Resource
    private InCDtlDAO inCDtlDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private PurChDtlDAO purChDtlDAO;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    private PurChDAO purChDAO;

    @Resource
    private RkTkDAO rkTkDAO;

    //获取来料单列表信息
    @Override
    public GetAllIChInfoRes QALLGetAllIChInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllIChInfoRes objEGetAllIChInfoRes = new GetAllIChInfoRes();
        GetAllIChInfoResB objEGetAllIChInfoResB = new GetAllIChInfoResB();
        List<GetAllIChInfoResD> objEGetAllIChInfoResDs = new ArrayList<GetAllIChInfoResD>();
        List<InCInfo> objEInCInfos = baseIService.QALLBaseInfo(InCDAO.class, "SelectInCInfo",
                argInitDataFields, argPageInfo);
        if (objEInCInfos != null || objEInCInfos.size() != 0) {
            for (InCInfo obj : objEInCInfos) {
                GetAllIChInfoResD objEGetAllIChInfoResD = new GetAllIChInfoResD();
                objEGetAllIChInfoResD.setInCRd(obj.getRuid());
                objEGetAllIChInfoResD.setInCCode(obj.getInCCode());
                objEGetAllIChInfoResD.setPurChCode(obj.getPurChCode());
                objEGetAllIChInfoResD.setStatus(obj.getStatus());
                objEGetAllIChInfoResDs.add(objEGetAllIChInfoResD);
            }
        }
        objEGetAllIChInfoResB.setData(objEGetAllIChInfoResDs);
        objEGetAllIChInfoRes.setBody(objEGetAllIChInfoResB);

        return objEGetAllIChInfoRes;
    }

    //获取来料单明细信息
    @Override
    public GetAllIDlInfoRes QALLGetAllIDlInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllIDlInfoRes objEGetAllIDlInfoRes = new GetAllIDlInfoRes();
        GetAllIDlInfoResB objEGetAllIDlInfoResB = new GetAllIDlInfoResB();
        List<GetAllIDlInfoResD> objEGetAllIDlInfoResDs = new ArrayList<GetAllIDlInfoResD>();

        List<InCDtlInfo> objEInCDtlInfos = baseIService.QALLBaseInfo(InCDtlDAO.class, "SelectInCDtlInfo",
                argInitDataFields, argPageInfo);
        if (objEInCDtlInfos != null || objEInCDtlInfos.size() != 0) {
            for (InCDtlInfo obj : objEInCDtlInfos) {
                GetAllIDlInfoResD objGetAllIDlInfoResD = new GetAllIDlInfoResD();
                objGetAllIDlInfoResD.setInCCode(obj.getInCCode());
                objGetAllIDlInfoResD.setInCDlRd(obj.getRuid());
                objGetAllIDlInfoResD.setNum(obj.getNum());
                objGetAllIDlInfoResD.setAFeed(obj.getaFeed());
                objGetAllIDlInfoResD.setScanNum(obj.getScanNum());
                objGetAllIDlInfoResD.setUnitName(obj.getUnitName());
                objGetAllIDlInfoResD.setIsPrint(obj.getIsPrint());

                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                if (objEMaVerInfo != null) {
                    objGetAllIDlInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
                    //查询明细的物料描述
                    MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(objEMaVerInfo.getMaGd());
                    if (materialInfo == null || "".equals(materialInfo)) {
                        objGetAllIDlInfoResD.setMaDes("");
                    } else {
                        objGetAllIDlInfoResD.setMaDes(materialInfo.getMaterialDes());
                    }
                    objGetAllIDlInfoResD.setMaName(materialInfo.getMaterialName());
                    objGetAllIDlInfoResD.setMaCode(materialInfo.getMaterialCode());
                }

                StoreInfo objEStoreInfo = storeDAO.SelectByStoreGd(obj.getStoreGd());
                if (objEStoreInfo != null) {
                    GetAllIDlInfoResDStore objEGetAllIDlInfoResDStore = new GetAllIDlInfoResDStore();
                    objEGetAllIDlInfoResDStore.setStoreRd(objEStoreInfo.getRuid());
                    objEGetAllIDlInfoResDStore.setStoreName(objEStoreInfo.getStoreName());
                    objGetAllIDlInfoResD.setStoreInfo(objEGetAllIDlInfoResDStore);
                }
                objEGetAllIDlInfoResDs.add(objGetAllIDlInfoResD);
            }
        }
        objEGetAllIDlInfoResB.setData(objEGetAllIDlInfoResDs);
        objEGetAllIDlInfoRes.setBody(objEGetAllIDlInfoResB);
        return objEGetAllIDlInfoRes;
    }

    //新增来料单
    @Override
    public SaveIChInfoRes AddSaveIChInfoRes(SaveIChInfoReq00 argBD00) {
        SaveIChInfoRes objESaveIChInfoRes = new SaveIChInfoRes();
        SaveIChInfoResB objESaveIChInfoResB = new SaveIChInfoResB();
        SaveIChInfoResD objESaveIChInfoResD = new SaveIChInfoResD();

        //用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //是否根据采购单(默认True-根据)
        boolean isSourceType = true;
        if ("01".equals(argBD00.getSourceType())) {
            isSourceType = false;
        }
        PurChInfo objEPurChInfo = null;
        if (isSourceType) {
            if (argBD00.getPurChCode() == null || "".equals(argBD00.getPurChCode())) {
                throw new SystemException("", argBD00.getPurChCode() + "采购单不能为空");
            }
            objEPurChInfo = purChDAO.SelectPurChInfoByPurChCode(argBD00.getPurChCode());
            if (objEPurChInfo == null) {
                throw new SystemException("", argBD00.getPurChCode() + "采购单不存在");
            }
            if (!"00".equals(objEPurChInfo.getcIEFlag())) {
                throw new SystemException("", "采购单{" + objEPurChInfo.getPurChCode() + "}开单已经结束");
            }
            if (!"01".equals(objEPurChInfo.getsStatus())) {
                throw new SystemException("", "采购单{" + objEPurChInfo.getPurChCode() + "}已取消,不能进行录入操作");
            }
        }

        //不允许重复
        if (!"".equals(argBD00.getInCCode())) {
            int a = inCDAO.SelectInCInfoByInCCode(argBD00.getInCCode());

            if (a > 0) {
                throw new SystemException("", "新增失败，来料单号重复");
            }
        }
        if (argBD00.getIChMaInfo().size() <= 0 || argBD00.getIChMaInfo() == null) {
            throw new SystemException("", "新增失败，来料单明细不能为空");
        }

        InCInfo objEInCInfo = new InCInfo();
        objEInCInfo.setGuid(CommonUtils.getRandomNumber());

        //查询代码生成
        String SCode = "";
        if (!"".equals(argBD00.getInCCode())) {
            SCode = argBD00.getInCCode();
        } else {
            CodeRuleInfo codeRuleInfo = codeRuleDAO.SelectCodeRuleInfocodeType("05");
            if (codeRuleInfo != null && "00".equals(codeRuleInfo.getStatus())) {
                SCode = gConfigIService.GetCreateSC(codeRuleInfo);
            } else {
                throw new SystemException("", "请输入{来料通知单号}，或请到全局配置进行代码生成配置");
            }
        }
        objEInCInfo.setInCCode(SCode);
        if (isSourceType) {
            objEInCInfo.setPurChCode(argBD00.getPurChCode());
        }
        objEInCInfo.setSourceType(argBD00.getSourceType());
        objEInCInfo.setStatus("00");
        objEInCInfo.setsStatus("00");
        objEInCInfo.setdSource("01");
        objEInCInfo.setCreator(userName);
        objEInCInfo.setCreateTime(date);
        objEInCInfo.setRemark(argBD00.getRemark());
        inCDAO.InsertInCInfo(objEInCInfo);

        //采购单明细
        PurChDtlInfo purChDtlInfo = null;
        //来料单明细
        InCDtlInfo objEInCDtlInfo = null;
        //物料
        MaVerInfo objEMaVerInfo = null;
        //收料仓
        StoreInfo objEStoreInfo = null;
        for (SaveIChInfoReq00IChMa obj : argBD00.getIChMaInfo()) {
            objEInCDtlInfo = new InCDtlInfo();

            if (obj.getNum() < 0) {
                throw new SystemException("", "的收获数量不能小于等于零");
            }

            if (isSourceType) {
                purChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByRuid(obj.getPurChDlRd());
                if (purChDtlInfo == null) {
                    throw new SystemException("", "采购单明细不存在");
                }
                if ("01".equals(purChDtlInfo.getcIEFlag())) {
                    throw new SystemException("", "物料开单结束,不能进行录入操作");
                }
                Float unclncNum = inCDtlDAO.SelectIncDtlNumsByPurGd(purChDtlInfo.getGuid());
                if (unclncNum == null) {
                    unclncNum = 0f;
                }
                unclncNum = purChDtlInfo.getNum() - unclncNum;
                if (obj.getNum() > unclncNum) {
                    throw new SystemException("", "新增失败，" + "收获数量不能大于" + unclncNum);
                } else if (obj.getNum() == unclncNum) {
                    purChDtlInfo.setcIEFlag("01");
                    if (purChDtlDAO.UpdatePurChDtlInfo(purChDtlInfo) <= 0) {
                        throw new SystemException("", "更新采购明细失败");
                    }
                }
                objEInCDtlInfo.setPurChDtlGd(purChDtlInfo.getGuid());
            }
            objEInCDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objEInCDtlInfo.setInChGd(objEInCInfo.getGuid());
            objEInCDtlInfo.setInCCode(SCode);

            objEMaVerInfo = maVerDAO.SelectByRuid(obj.getMaVerRd());
            if (objEMaVerInfo != null) {
                objEInCDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
            }
            objEInCDtlInfo.setNum(obj.getNum());
            objEInCDtlInfo.setScanNum(0.00f);
            objEInCDtlInfo.setUnitName(obj.getUnitName());
            objEInCDtlInfo.setaFeed(obj.getAFeed());

            objEStoreInfo = storeDAO.SelectByRuid(obj.getStoreRd());
            if (objEStoreInfo != null) {
                objEInCDtlInfo.setStoreGd(objEStoreInfo.getGuid());
            }
            objEInCDtlInfo.setCreator(userName);
            objEInCDtlInfo.setCreateTime(date);
            objEInCDtlInfo.setRemark(obj.getRemark());
            inCDtlDAO.InsertInCDtlInfo(objEInCDtlInfo);
        }

        if (isSourceType) {
            //判断采购单是否开单结束
            List<PurChDtlInfo> objEPurChDtlInfos = purChDtlDAO.SelectPurChDtlInfoBypurChGd(purChDtlInfo.getPurChGd());
            if (objEPurChDtlInfos.size() > 0) {
                boolean f = true;
                for (PurChDtlInfo obj : objEPurChDtlInfos) {
                    if ("00".equals(obj.getcIEFlag())) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    objEPurChInfo.setcIEFlag("01");
                    if (purChDAO.UpdatePurChInfoByRuid(objEPurChInfo) <= 0) {
                        throw new SystemException("", "更新采购单失败");
                    }
                }
            }
        }

        InCInfo objEInCInfos = inCDAO.SelectByGuid(objEInCInfo.getGuid());
        objESaveIChInfoResD.setInCRd(objEInCInfos.getRuid());
        objESaveIChInfoResD.setInCCode(objEInCInfos.getInCCode());

        objESaveIChInfoResB.setData(objESaveIChInfoResD);
        objESaveIChInfoRes.setBody(objESaveIChInfoResB);
        return objESaveIChInfoRes;
    }

    //获取来料单信息
    @Override
    public GetIncInfoRes GetGetIncInfoRes(GetIncInfoReq00 argGetIncInfoReq00) {
        GetIncInfoRes objEGetIncInfoRes = new GetIncInfoRes();
        GetIncInfoResB objEGetIncInfoResB = new GetIncInfoResB();
        GetIncInfoResD objEGetIncInfoResD = new GetIncInfoResD();

        InCInfo objEInCInfo = inCDAO.SelectByRuid(argGetIncInfoReq00.getInCRd());
        if (objEInCInfo == null) {
            throw new SystemException("", "该信息不存在");
        }
        //是否是采购单创建
        boolean isPur = false;
        if ("00".equals(objEInCInfo.getSourceType())) {
            isPur = true;
        }
        //明细
        if (objEInCInfo != null) {
            List<InCDtlInfo> objEInCDtlInfos = inCDtlDAO.SelectInCDtlInfoByInChGd(objEInCInfo.getGuid());
            if (objEInCDtlInfos == null || objEInCDtlInfos.size() <= 0) {
                throw new SystemException("", "查询明细失败");
            }
            PurChDtlInfo objEPurChDtlInfo = null;
            List<GetIncInfoResDIncDl> objEGetIncInfoResDIncDls = new ArrayList<GetIncInfoResDIncDl>();
            for (InCDtlInfo obj : objEInCDtlInfos) {
                GetIncInfoResDIncDl objEGetIncInfoResDIncDl = new GetIncInfoResDIncDl();

                objEGetIncInfoResDIncDl.setInCDlRd(obj.getRuid());
                objEGetIncInfoResDIncDl.setNum(obj.getNum());
                objEGetIncInfoResDIncDl.setAFeed(obj.getaFeed());
                objEGetIncInfoResDIncDl.setUnitName(obj.getUnitName());

                //物料
                MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                if (maVerInfo != null) {
                    GetIncInfoResDIncDlMa getIncInfoResDIncDlMa = new GetIncInfoResDIncDlMa();
                    getIncInfoResDIncDlMa.setMaVerRd(maVerInfo.getRuid());
                    getIncInfoResDIncDlMa.setMaCode(maVerInfo.getMaterialCode());
                    getIncInfoResDIncDlMa.setMaName(maVerInfo.getMaterialName());
                    //查询明细的物料描述
                    MaterialInfo materialInfo = maVerDAO.SelectMaverAndMater(maVerInfo.getMaGd());
                    if (materialInfo == null || null == materialInfo.getMaterialDes()) {
                        getIncInfoResDIncDlMa.setMaDes("");
                    } else {
                        getIncInfoResDIncDlMa.setMaDes(materialInfo.getMaterialDes());
                    }

                    objEGetIncInfoResDIncDl.setMaInfo(getIncInfoResDIncDlMa);
                }

                StoreInfo storeInfo = storeDAO.SelectByguid(obj.getStoreGd());
                if (storeInfo != null) {
                    GetIncInfoResDIncDlStore getIncInfoResDIncDlStore = new GetIncInfoResDIncDlStore();
                    getIncInfoResDIncDlStore.setStoreRd(storeInfo.getRuid());
                    getIncInfoResDIncDlStore.setStoreName(storeInfo.getStoreName());
                    objEGetIncInfoResDIncDl.setStoreInfo(getIncInfoResDIncDlStore);
                }

                if (isPur) {
                    objEPurChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByGuid(obj.getPurChDtlGd());
                    if (objEPurChDtlInfo != null) {
                        objEGetIncInfoResDIncDl.setPurChDlRd(objEPurChDtlInfo.getRuid());
                        objEGetIncInfoResDIncDl.setTotalNum(objEPurChDtlInfo.getNum());
                        objEGetIncInfoResDIncDl.setTotalScanNum(objEPurChDtlInfo.getScanNum());
                        Float uncincNum = inCDtlDAO.SelectIncDtlNumsByPurGd(objEPurChDtlInfo.getGuid());
                        if (uncincNum == null) {
                            uncincNum = 0f;
                        }
                        uncincNum = objEPurChDtlInfo.getNum() - uncincNum;
                        objEGetIncInfoResDIncDl.setUnCInCNum(uncincNum);
                    }
                } else {
                    objEGetIncInfoResDIncDl.setTotalNum(0f);
                    objEGetIncInfoResDIncDl.setTotalScanNum(0f);
                    objEGetIncInfoResDIncDl.setUnCInCNum(0f);
                }

                objEGetIncInfoResDIncDl.setScanNum(obj.getScanNum());
                objEGetIncInfoResDIncDl.setRemark(obj.getRemark() == null ? "" : obj.getRemark());
                objEGetIncInfoResDIncDls.add(objEGetIncInfoResDIncDl);
            }
            //主
            objEGetIncInfoResD.setStatus(objEInCInfo.getStatus());
            objEGetIncInfoResD.setInCRd(objEInCInfo.getRuid());
            objEGetIncInfoResD.setInCCode(objEInCInfo.getInCCode());
            objEGetIncInfoResD.setPurChCode(objEInCInfo.getPurChCode());
            objEGetIncInfoResD.setCreateTime(DateUtil.format(objEInCInfo.getCreateTime()));
            objEGetIncInfoResD.setLastModifyTime(DateUtil.format(objEInCInfo.getLastModifyTime()));
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ss");
            if (objEInCInfo.getCreateTime() == null) {
                objEGetIncInfoResD.setCreateTime("");
            } else {
                objEGetIncInfoResD.setCreateTime(sdf.format(objEInCInfo.getCreateTime()));
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-ss");
            if (objEInCInfo.getLastModifyTime() == null) {
                objEGetIncInfoResD.setLastModifyTime("");
            } else {
                objEGetIncInfoResD.setLastModifyTime(sdf1.format(objEInCInfo.getLastModifyTime()));
            }*/
            objEGetIncInfoResD.setSourceType(objEInCInfo.getSourceType());
            objEGetIncInfoResD.setSStatus(objEInCInfo.getsStatus());
            objEGetIncInfoResD.setDSource(objEInCInfo.getdSource());
            objEGetIncInfoResD.setRemark(objEInCInfo.getRemark());
            objEGetIncInfoResD.setLastModifyMan(objEInCInfo.getLastModifyMan());
            objEGetIncInfoResD.setCreator(objEInCInfo.getCreator());
            objEGetIncInfoResD.setIncDlInfo(objEGetIncInfoResDIncDls);
        }
        objEGetIncInfoResB.setData(objEGetIncInfoResD);
        objEGetIncInfoRes.setBody(objEGetIncInfoResB);
        return objEGetIncInfoRes;
    }

    //删除来料单
    @Override
    public SaveIChInfoRes RmSaveIChInfoRes(SaveIChInfoReq01 argSaveIChInfoReq01) {
        SaveIChInfoRes objESaveIChInfoRes = new SaveIChInfoRes();
        SaveIChInfoResB objESaveIChInfoResB = new SaveIChInfoResB();

        InCInfo objEInCInfo = inCDAO.SelectByRuid(argSaveIChInfoReq01.getInCRd());
        if (objEInCInfo == null) {
            throw new SystemException("", "删除失败该信息不存在");
        }
        if ("00".equals(objEInCInfo.getdSource())) {
            throw new SystemException("", "外部数据不允许删除");
        }

        //采购单
        PurChInfo objEPurChInfo = purChDAO.SelectPurChInfoByPurChCode(objEInCInfo.getPurChCode());

        if (!"00".equals(objEInCInfo.getsStatus())) {
            if (objEInCInfo.getPurChCode() != null) {
                throw new SystemException("", objEInCInfo.getPurChCode() + "采购单已下达或已取消");
            } else {
                throw new SystemException("", "自建单已下达或已取消");
            }
        }

        if ("00".equals(objEInCInfo.getStatus())) {
            List<InCDtlInfo> objEInCDtlInfos = inCDtlDAO.SelectInCDtlInfoByInChGd(objEInCInfo.getGuid());
            if (objEInCDtlInfos == null || objEInCDtlInfos.size() <= 0) {
                throw new SystemException("", "删除失败该明细信息不存在");
            }
            for (InCDtlInfo obj : objEInCDtlInfos) {
                PurChDtlInfo purChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByGuid(obj.getPurChDtlGd());
                if (purChDtlInfo != null && "01".equals(purChDtlInfo.getcIEFlag())) {
                    purChDtlInfo.setcIEFlag("00");
                    if (purChDtlDAO.UpdatePurChDtlInfo(purChDtlInfo) <= 0) {
                        throw new SystemException("", "更新采购明细失败");
                    }
                }

                if (inCDtlDAO.DeleteInChDtlInfo(obj.getRuid()) <= 0) {
                    throw new SystemException("", "删除明细信息失败");
                }
            }

            if (inCDAO.DeleteInCInfo(objEInCInfo.getRuid()) <= 0) {
                throw new SystemException("", "删除信息失败");
            }

            if (objEPurChInfo != null) {
                if ("01".equals(objEPurChInfo.getcIEFlag())) {
                    objEPurChInfo.setcIEFlag("00");
                    if (purChDAO.UpdatePurChInfoByRuid(objEPurChInfo) <= 0) {
                        throw new SystemException("", "更新采购单失败");
                    }
                }
            }
        } else {
            throw new SystemException("", "删除失败，此单子处于进行中、已完成、已取消中");
        }

        objESaveIChInfoRes.setBody(objESaveIChInfoResB);
        return objESaveIChInfoRes;
    }

    //更新来料单
    @Override
    public SaveIChInfoRes ModSaveIChInfoRes(SaveIChInfoReq02 argSaveIChInfoReq02) {
        SaveIChInfoRes objESaveIChInfoRes = new SaveIChInfoRes();
        SaveIChInfoResB objESaveIChInfoResB = new SaveIChInfoResB();

        //用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        InCInfo objEInCInfo = inCDAO.SelectByRuid(argSaveIChInfoReq02.getInCRd());
        if (objEInCInfo == null) {
            throw new SystemException("", "更新失败，该信息不存在");
        }
        if ("01".equals(objEInCInfo.getStatus())) {
            throw new SystemException("", "来料单进行中不允许修改");
        } else if ("02".equals(objEInCInfo.getStatus())) {
            throw new SystemException("", "来料单完成不允许修改");
        }
        if ("00".equals(objEInCInfo.getdSource())) {
            throw new SystemException("", "外部数据不允许修改");
        }
        if ("01".equals(objEInCInfo.getsStatus())) {
            throw new SystemException("", "来料单下达不允许修改");
        } else if ("02".equals(objEInCInfo.getsStatus())) {
            throw new SystemException("", "来料单已取消不允许修改");
        }
        if ("00".equals(objEInCInfo.getsStatus())) {
            objEInCInfo.setRemark(argSaveIChInfoReq02.getRemark());
            if (inCDAO.UpdateInCInfoByRuid(objEInCInfo) != 1) {
                throw new SystemException("", "更新失败");
            }
        }

        //领料单
        PurChInfo objEPurChInfo = null;
        //是否有领料单
        boolean isPurch = false;
        if ("00".equals(objEInCInfo.getSourceType())) {
            isPurch = true;
            //查询采购单
            objEPurChInfo = purChDAO.SelectPurChInfoByPurChCode(objEInCInfo.getPurChCode());
            if (objEPurChInfo == null) {
                throw new SystemException("", objEInCInfo.getPurChCode() + "采购单不存在");
            }
            if (!"01".equals(objEPurChInfo.getsStatus())) {
                throw new SystemException("", "采购单{" + objEPurChInfo.getPurChCode() + "}已取消，不能进行编辑操作");
            }
        }

        //物料
        MaVerInfo objEMaVerInfo = null;
        //仓库
        StoreInfo objEStoreInfo = null;
        //采购单明细
        PurChDtlInfo objEPurChDtlInfo = null;
        //来料单数据(old)
        InCDtlInfo objEInCDtlInfo = null;
        List<InCDtlInfo> objEInCDtlInfos = inCDtlDAO.SelectInCDtlInfoByInChGd(objEInCInfo.getGuid());
        Map<Integer, InCDtlInfo> incdtlMap = new HashMap<>();
        for (InCDtlInfo obj : objEInCDtlInfos) {
            incdtlMap.put(obj.getRuid(), obj);
        }

        if (argSaveIChInfoReq02.getIChMaInfo().size() <= 0) {
            throw new SystemException("", "来料单明细不能为空");
        }

        for (SaveIChInfoReq02IChMa iChMa : argSaveIChInfoReq02.getIChMaInfo()) {
            if (iChMa.getNum() < 0) {
                throw new SystemException("", "收获数量不能小于等于零");
            }
            if (incdtlMap.containsKey(iChMa.getInCDlRd())) {
                //修改
                objEInCDtlInfo = incdtlMap.get(iChMa.getInCDlRd());
                if (isPurch) {
                    objEPurChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByGuid(objEInCDtlInfo.getPurChDtlGd());
                    if (objEPurChDtlInfo == null) {
                        throw new SystemException("", "采购单明细不存在");
                    }
                    Float unclncNum = inCDtlDAO.SelectIncDtlNumsByPurGd(objEPurChDtlInfo.getGuid());
                    if (unclncNum == null) {
                        unclncNum = 0f;
                    }
                    unclncNum = objEPurChDtlInfo.getNum() - unclncNum + objEInCDtlInfo.getNum();
                    if (iChMa.getNum() > unclncNum) {
                        throw new SystemException("", "收获数量不能大于" + unclncNum);
                    } else if (iChMa.getNum() == unclncNum) {
                        objEPurChDtlInfo.setcIEFlag("01");
                    } else {
                        objEPurChDtlInfo.setcIEFlag("00");
                    }
                    objEPurChDtlInfo.setLastModifyMan(userName);
                    objEPurChDtlInfo.setLastModifyTime(date);
                    if (purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo) <= 0) {
                        throw new SystemException("", "来料收货单修改失败");
                    }
                }

                if (iChMa.getNum() < objEInCDtlInfo.getScanNum()) {
                    throw new SystemException("", "收获数量不能小于已扫描数量");
                }

                objEInCDtlInfo.setNum(iChMa.getNum());
                objEInCDtlInfo.setLastModifyMan(userName);
                objEInCDtlInfo.setLastModifyTime(date);
                objEInCDtlInfo.setRemark(iChMa.getRemark());
                if (inCDtlDAO.UpdateInChDtlInfo(objEInCDtlInfo) <= 0) {
                    throw new SystemException("", "来料收货单修改失败");
                }

                incdtlMap.remove(iChMa.getInCDlRd());
            } else {
                //新增
                objEInCDtlInfo = new InCDtlInfo();
                if (isPurch) {
                    objEPurChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByRuid(iChMa.getPurChDlRd());
                    if (objEPurChDtlInfo == null) {
                        throw new SystemException("", "采购单明细不存在");
                    }
                    if ("01".equals(objEPurChDtlInfo.getcIEFlag())) {
                        throw new SystemException("", "物料开单结束,不能进行录入操作");
                    }
                    Float unclncNum = inCDtlDAO.SelectIncDtlNumsByPurGd(objEPurChDtlInfo.getGuid());
                    if (unclncNum == null) {
                        unclncNum = 0f;
                    }
                    unclncNum = objEPurChDtlInfo.getNum() - unclncNum;
                    if (iChMa.getNum() > unclncNum) {
                        throw new SystemException("", "新增失败，" + "的收获数量不能大于" + unclncNum);
                    } else if (iChMa.getNum() == unclncNum) {
                        objEPurChDtlInfo.setcIEFlag("01");
                        objEPurChDtlInfo.setLastModifyMan(userName);
                        objEPurChDtlInfo.setLastModifyTime(date);
                        if (purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo) <= 0) {
                            throw new SystemException("", "更新采购明细失败");
                        }
                    }
                    objEInCDtlInfo.setPurChDtlGd(objEPurChDtlInfo.getGuid());
                }

                objEInCDtlInfo.setGuid(CommonUtils.getRandomNumber());
                objEInCDtlInfo.setInChGd(objEInCInfo.getGuid());
                objEInCDtlInfo.setInCCode(objEInCInfo.getInCCode());

                objEMaVerInfo = maVerDAO.SelectByRuid(iChMa.getMaVerRd());
                if (objEMaVerInfo != null) {
                    objEInCDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                }
                objEInCDtlInfo.setNum(iChMa.getNum());
                objEInCDtlInfo.setScanNum(0.00f);
                objEInCDtlInfo.setUnitName(iChMa.getUnitName());
                objEInCDtlInfo.setaFeed(iChMa.getAFeed());

                objEStoreInfo = storeDAO.SelectByRuid(iChMa.getStoreRd());
                if (objEStoreInfo != null) {
                    objEInCDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                }
                objEInCDtlInfo.setCreator(userName);
                objEInCDtlInfo.setCreateTime(date);
                objEInCDtlInfo.setRemark(iChMa.getRemark());
                inCDtlDAO.InsertInCDtlInfo(objEInCDtlInfo);
            }
        }

        for (Map.Entry<Integer, InCDtlInfo> map : incdtlMap.entrySet()) {
            objEInCDtlInfo = map.getValue();
            objEPurChDtlInfo = purChDtlDAO.SelectPurChDtlInfoByGuid(objEInCDtlInfo.getPurChDtlGd());
            if (objEPurChDtlInfo != null) {
                if ("01".equals(objEPurChDtlInfo.getcIEFlag()) && objEInCDtlInfo.getNum() > 0) {
                    objEPurChDtlInfo.setcIEFlag("00");
                    objEPurChDtlInfo.setLastModifyMan(userName);
                    objEPurChDtlInfo.setLastModifyTime(date);
                    if (purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo) <= 0) {
                        throw new SystemException("", "更新采购明细失败");
                    }
                }
            }

            if (inCDtlDAO.DeleteInChDtlInfo(map.getKey()) <= 0) {
                throw new SystemException("", "删除来料单明细失败");
            }
        }

        //判断采购单是否开单结束
        if (isPurch) {
            List<PurChDtlInfo> objEPurChDtlInfos = purChDtlDAO.SelectPurChDtlInfoBypurChGd(objEPurChInfo.getGuid());
            if (objEPurChDtlInfos.size() > 0) {
                boolean f = true;
                for (PurChDtlInfo obj : objEPurChDtlInfos) {
                    if ("00".equals(obj.getcIEFlag())) {
                        f = false;
                        break;
                    }
                }
                objEPurChInfo.setLastModifyMan(userName);
                objEPurChInfo.setLastModifyTime(date);
                if (f) {
                    objEPurChInfo.setcIEFlag("01");
                    if (purChDAO.UpdatePurChInfoByRuid(objEPurChInfo) <= 0) {
                        throw new SystemException("", "更新采购单失败");
                    }
                } else {
                    if ("01".equals(objEPurChInfo.getcIEFlag())) {
                        objEPurChInfo.setcIEFlag("00");
                        if (purChDAO.UpdatePurChInfoByRuid(objEPurChInfo) <= 0) {
                            throw new SystemException("", "更新采购单失败");
                        }
                    }
                }
            }
        }

        objESaveIChInfoRes.setBody(objESaveIChInfoResB);
        return objESaveIChInfoRes;
    }

    //获取来料收货未开单物料信息
    @Override
    public GetWIncMaInfoRes GetWIncMaInfo(GetWIncMaInfoReqBD00 argBD00) {
        //用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        GetWIncMaInfoRes objEGetWIncMaInfoRes = new GetWIncMaInfoRes();
        GetWIncMaInfoResB objEGetWIncMaInfoResB = new GetWIncMaInfoResB();
        List<GetWIncMaInfoResD> objEGetWIncMaInfoResDs = new ArrayList<>();
        GetWIncMaInfoResD objEGetWIncMaInfoResD = null;
        MaterialInfo objEMaterialInfo = null;
        MaVerInfo objEMaVerInfo = null;
        GetWIncMaInfoResDMaInfo objEMa = null;
        StoreInfo objEStoreInfo = null;
        GetWIncMaInfoResDStoreInfo objEStore = null;

        //查询采购单
        PurChInfo objEPurChInfo = purChDAO.SelectPurChInfoByPurChCode(argBD00.getPurChCode());
        if (objEPurChInfo != null && "01".equals(objEPurChInfo.getsStatus()) && "00".equals(objEPurChInfo.getcIEFlag())) {
            //查询采购单明细信息
            List<PurChDtlInfo> objEPurChDtlInfos = purChDtlDAO.SelectPurChDtlInfoByPurChCode(argBD00.getPurChCode());
            for (PurChDtlInfo purChDtlInfo : objEPurChDtlInfos) {
                if (!"00".equals(purChDtlInfo.getcIEFlag())) {
                    continue;
                }
                objEGetWIncMaInfoResD = new GetWIncMaInfoResD();
                objEGetWIncMaInfoResD.setPurChDtlRd(purChDtlInfo.getRuid());
                //查询物料
                objEMaVerInfo = maVerDAO.SelectMaverInfo(purChDtlInfo.getMaVerGd());
                if (objEMaVerInfo == null || "01".equals(objEMaVerInfo.getStatus())) {
                    continue;
                }
                objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                if (objEMaterialInfo == null) {
                    continue;
                }

                objEMa = new GetWIncMaInfoResDMaInfo();
                objEMa.setMaVerRd(objEMaVerInfo.getRuid());
                objEMa.setMaCode(objEMaterialInfo.getMaterialCode());
                objEMa.setMaName(objEMaterialInfo.getMaterialName());
                objEMa.setMaDes(objEMaterialInfo.getMaterialDes());
                objEGetWIncMaInfoResD.setMaInfo(objEMa);
                objEGetWIncMaInfoResD.setNum(purChDtlInfo.getNum());
                objEGetWIncMaInfoResD.setScanNum(purChDtlInfo.getScanNum());
                Float uncincNum = inCDtlDAO.SelectIncDtlNumsByPurGd(purChDtlInfo.getGuid());
                if (uncincNum == null) {
                    uncincNum = 0f;
                }
                uncincNum = purChDtlInfo.getNum() - uncincNum;
                objEGetWIncMaInfoResD.setUnCInCNum(uncincNum);
                objEGetWIncMaInfoResD.setAFeed(purChDtlInfo.getaFeed());
                objEStore = new GetWIncMaInfoResDStoreInfo();
                //查询收料仓
                objEStoreInfo = storeDAO.SelectByguid(purChDtlInfo.getStoreGd());
                if (objEStoreInfo == null) {
                    objEStore.setStoreRd(0);
                    objEStore.setStoreName("");
                } else {
                    objEStore.setStoreRd(objEStoreInfo.getRuid());
                    objEStore.setStoreName(objEStoreInfo.getStoreName());
                }
                objEGetWIncMaInfoResD.setStoreInfo(objEStore);
                objEGetWIncMaInfoResD.setUnitName(purChDtlInfo.getUnitName());
                objEGetWIncMaInfoResDs.add(objEGetWIncMaInfoResD);
            }
        }

        objEGetWIncMaInfoResB.setData(objEGetWIncMaInfoResDs);
        objEGetWIncMaInfoRes.setBody(objEGetWIncMaInfoResB);

        return objEGetWIncMaInfoRes;
    }

    //下达
    @Override
    public SaveIChInfoRes ModSaveIChOn(SaveIChInfoReq03 argBD03) {
        SaveIChInfoRes objESaveIChInfoRes = new SaveIChInfoRes();
        SaveIChInfoResB objESaveIChInfoResB = new SaveIChInfoResB();

        //当前用户名
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();

        //查询来料单
        InCInfo objEInCInfo = inCDAO.SelectByRuid(argBD03.getInCRd());
        if (objEInCInfo == null) {
            throw new SystemException("", "来料单不存在");
        }
        if ("01".equals(objEInCInfo.getsStatus())) {
            throw new SystemException("", "来料单已下达,不能重复下达");
        }
        if ("02".equals(objEInCInfo.getsStatus())) {
            throw new SystemException("", "来料单已取消,不能进行下达");
        }
        objEInCInfo.setsStatus("01");
        if (inCDAO.UpdateInCInfoByRuid(objEInCInfo) <= 0) {
            throw new SystemException("", "来料单下达失败");
        }

        RkTkInfo objERkTkInfo = new RkTkInfo();
        objERkTkInfo.setGuid(CommonUtils.getRandomNumber());
        objERkTkInfo.setRkType("02");
        objERkTkInfo.setRkCode("RK");
        objERkTkInfo.setAssCode(objEInCInfo.getInCCode());
        objERkTkInfo.setAssCodeGd(objEInCInfo.getGuid());
        objERkTkInfo.setAssSource("01");
        objERkTkInfo.setExStatus("00");
        objERkTkInfo.setSubmitStore("00");
        objERkTkInfo.setCreator(userName);
        objERkTkInfo.setCreateTime(date);
        objERkTkInfo.setRemark(objEInCInfo.getInCCode());
        rkTkDAO.InsertRKTkInfo(objERkTkInfo);

        objERkTkInfo = rkTkDAO.SelectRKTkInfoByguid(objERkTkInfo.getGuid());
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
        objERkTkInfo.setRkCode("RK" + sb.toString() + nf.format(objERkTkInfo.getRuid()));
        if (rkTkDAO.UpdateRKTkInfo(objERkTkInfo) <= 0) {
            throw new SystemException("", "来料单下达失败");
        }

        objESaveIChInfoRes.setBody(objESaveIChInfoResB);
        return objESaveIChInfoRes;
    }

    //取消
    @Override
    public SaveIChInfoRes ModSaveIChOff(SaveIChInfoReq04 argBD04) {
        SaveIChInfoRes objESaveIChInfoRes = new SaveIChInfoRes();
        SaveIChInfoResB objESaveIChInfoResB = new SaveIChInfoResB();
        //查询来料单
        InCInfo objEInCInfo = inCDAO.SelectByRuid(argBD04.getInCRd());
        if (objEInCInfo == null) {
            throw new SystemException("", "来料单不存在");
        }
        if ("00".equals(objEInCInfo.getsStatus())) {
            throw new SystemException("", "来料单未下达，不能取消");
        }
        if ("02".equals(objEInCInfo.getsStatus())) {
            throw new SystemException("", "来料单已取消，不能重复取消");
        }
        if ("01".equals(objEInCInfo.getsStatus())) {
            boolean b = false;
            if ("00".equals(objEInCInfo.getStatus())) {
                b = true;
            } else if ("01".equals(objEInCInfo.getStatus())) {
                objEInCInfo.setStatus("03");
                b = true;
            }
            if (b) {
                RkTkInfo objERkTkInfo = rkTkDAO.SelectByAssCode(objEInCInfo.getInCCode());
                if (objERkTkInfo == null) {
                    throw new SystemException("", "入库任务不存在");
                }
                objERkTkInfo.setExStatus("03");
                if (rkTkDAO.UpdateRKTkInfo(objERkTkInfo) <= 0) {
                    throw new SystemException("", "来料单取消失败");
                }
            }
        }
        objEInCInfo.setsStatus("02");
        if (inCDAO.UpdateInCInfoByRuid(objEInCInfo) <= 0) {
            throw new SystemException("", "来料单取消失败");
        }

        objESaveIChInfoRes.setBody(objESaveIChInfoResB);
        return objESaveIChInfoRes;
    }
}
