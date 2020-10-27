package pnc.mesadmin.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoRes;
import pnc.mesadmin.dto.ExportSWareInfo.ExportSWareInfoResB;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoReqBD00;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoRes;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoResB;
import pnc.mesadmin.dto.GetAllRKInfo.GetAllRKInfoResD;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoReqBD00;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoRes;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoResB;
import pnc.mesadmin.dto.GetRKBarInfo.GetRKBarInfoResD;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoRes;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoResB;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoReqBD00;
import pnc.mesadmin.dto.GetRKDlInfo.GetRKDlInfoResD;
import pnc.mesadmin.dto.GetRKInfo.*;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoRes;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoResB;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoResD;
import pnc.mesadmin.dto.SaveRKInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.RkIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：入库单实现层
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class RkService implements RkIService {
    @Resource
    private RkTkDAO rkTkDAO;

    @Resource
    private PurChDtlDAO purChDtlDAO;

    @Resource
    private InCDtlDAO inCDtlDAO;

    @Resource
    private MaVerDAO maVerDAO;

    @Resource
    private MaterialDAO materialDAO;

    @Resource
    private RkTkIDAO rkTkIDAO;

    @Resource
    private UnitDAO unitDAO;

    @Resource
    private RetMaDtlDAO retMaDtlDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private PurChDAO purChDAO;

    @Resource
    private InCDAO inCDAO;

    @Resource
    private RMaNDtlDAO rMaNDtlDAO;

    @Resource
    private NRetMaDtlDAO nRetMaDtlDAO;

    private static final String[] MATYPE = {"00", "01","02"};

    private static final String[] TNAME = {"生产入库","原材料入库","生产退料"};


    //dto获取入库单类型
    public GetRKTInfoRes QALLGetRKTInfoRes() {
        GetRKTInfoRes objEGetRKTInfoRes = new GetRKTInfoRes();
        GetRKTInfoResB objEGetRKTInfoResB = new GetRKTInfoResB();
        List<GetRKTInfoResD> objEGetRKTInfoResDs = new ArrayList<GetRKTInfoResD>();

        for (int i = 0; i < 3; i++) {
            GetRKTInfoResD objEGetRKTInfoResD = new GetRKTInfoResD();
            objEGetRKTInfoResD.setPkTCode(MATYPE[i]);
            objEGetRKTInfoResD.setPkTName(TNAME[i]);
            objEGetRKTInfoResDs.add(objEGetRKTInfoResD);
        }

        objEGetRKTInfoResB.setData(objEGetRKTInfoResDs);
        objEGetRKTInfoRes.setBody(objEGetRKTInfoResB);
        return objEGetRKTInfoRes;
    }

    //dto查询入库单信息 根据入库类型
    public GetAllRKInfoRes GetGetAllRKInfoRes(GetAllRKInfoReqBD00 argGetAllRKInfoReqBD00) {
        GetAllRKInfoRes objEGetAllRKInfoRes = new GetAllRKInfoRes();
        GetAllRKInfoResB objEGetAllRKInfoResB = new GetAllRKInfoResB();
        List<GetAllRKInfoResD> objEGetAllRKInfoResDs = new ArrayList<GetAllRKInfoResD>();
        List<RkTkInfo> objERkTkInfos = rkTkDAO.SelectByRkShaixuan(argGetAllRKInfoReqBD00.getRkTCode(),argGetAllRKInfoReqBD00.getAssCode(),argGetAllRKInfoReqBD00.getExecTime(),argGetAllRKInfoReqBD00.getExecTime1());
        if (objERkTkInfos != null || objERkTkInfos.size() <= 0) {
            for (RkTkInfo obj : objERkTkInfos) {
                GetAllRKInfoResD objEGetAllRKInfoResD = new GetAllRKInfoResD();
                objEGetAllRKInfoResD.setRkRd(obj.getRuid());
                objEGetAllRKInfoResD.setRkCode(obj.getRkCode());
                objEGetAllRKInfoResD.setAssCode(obj.getAssCode());
                if (obj.getExecor() == null) {
                    objEGetAllRKInfoResD.setExecor("");
                } else {
                    objEGetAllRKInfoResD.setExecor(obj.getExecor());
                }
                if (obj.getCancelor() == null) {
                    objEGetAllRKInfoResD.setCancelor("");
                } else {
                    objEGetAllRKInfoResD.setCancelor(obj.getCancelor());
                }
                if (obj.getFinishor() == null) {
                    objEGetAllRKInfoResD.setFinishor("");
                } else {
                    objEGetAllRKInfoResD.setFinishor(obj.getFinishor());
                }
                objEGetAllRKInfoResD.setExecTime(DateUtil.format(obj.getExecTime()));
                objEGetAllRKInfoResD.setCancelTime(DateUtil.format(obj.getCancelTime()));
                objEGetAllRKInfoResD.setFinishTime(DateUtil.format(obj.getFinishTime()));
                if (("00").equals(obj.getExStatus())) {
                    objEGetAllRKInfoResD.setExStatus("待执行");
                }
                if (("01").equals(obj.getExStatus())) {
                    objEGetAllRKInfoResD.setExStatus("进行中");
                }
                if (("02").equals(obj.getExStatus())) {
                    objEGetAllRKInfoResD.setExStatus("已完成");
                }
                if (("03").equals(obj.getExStatus())) {
                    objEGetAllRKInfoResD.setExStatus("已取消");
                }
                objEGetAllRKInfoResDs.add(objEGetAllRKInfoResD);
            }
        }

        objEGetAllRKInfoResB.setData(objEGetAllRKInfoResDs);
        objEGetAllRKInfoRes.setBody(objEGetAllRKInfoResB);
        return objEGetAllRKInfoRes;
    }

    //dto获取入库单信息
    public GetRKInfoRes GetGetRKInfoRes(GetRKInfoReqBD00 argGetRKInfoReqBD00) {
        GetRKInfoRes objEGetRKInfoRes = new GetRKInfoRes();
        GetRKInfoResB objEGetRKInfoResB = new GetRKInfoResB();
        List<GetRKInfoResBDPKMa> objEGetRKInfoResBDPKMas = new ArrayList<GetRKInfoResBDPKMa>();
        //入库任务
        RkTkInfo objERkTkInfo = rkTkDAO.SelectRKTkInfoByruid(argGetRKInfoReqBD00.getRkRd());
        if (objERkTkInfo != null) {
            //采购单明细信息
            if ("00".equals(objERkTkInfo.getAssSource())) {
                List<PurChDtlInfo> objEPurChDtlInfos = purChDtlDAO.SelectPurChDtlInfoByPurChCode(objERkTkInfo.getAssCode());
                if (objEPurChDtlInfos != null || objEPurChDtlInfos.size() != 0) {
                        for(PurChDtlInfo obj:objEPurChDtlInfos){
                            GetRKInfoResD objEGetRKInfoResD=new GetRKInfoResD();
                            objEGetRKInfoResD.setRkRd(objERkTkInfo.getRuid());
                            objEGetRKInfoResD.setAssCode(objERkTkInfo.getAssCode());
                            objEGetRKInfoResD.setAssSource(objERkTkInfo.getAssSource());
                            objEGetRKInfoResD.setRkCode(obj.getPurChCode());
                            objEGetRKInfoResD.setRkType(objERkTkInfo.getRkType());
                            GetRKInfoResBDStore objEGetRKInfoResBDStore=new GetRKInfoResBDStore();
                            //仓库标识,因没有仓库标识信息，
                            StoreInfo objEStoreInfo=storeDAO.SelectByStoreGd(obj.getStoreGd());
                            if(objEStoreInfo!=null){
                                objEGetRKInfoResBDStore.setStoreRd(objEStoreInfo.getRuid());
                                objEGetRKInfoResBDStore.setStoreName(objEStoreInfo.getStoreName());
                            }

                            objEGetRKInfoResD.setStoreInfo(objEGetRKInfoResBDStore);

                            objEGetRKInfoResD.setAssCode(objERkTkInfo.getAssCode());
                            //物料
                            GetRKInfoResBDPKMa objEGetRKInfoResBDPKMa=new GetRKInfoResBDPKMa();
                            MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                            if(objEMaVerInfo!=null){
                                objEGetRKInfoResBDPKMa.setMaVerRd(objEMaVerInfo.getRuid());
                                objEGetRKInfoResBDPKMa.setMaCode(objEMaVerInfo.getMaterialCode());
                                objEGetRKInfoResBDPKMa.setMaName(objEMaVerInfo.getMaterialName());
                            }

                            objEGetRKInfoResBDPKMa.setRkDtlRd(obj.getRuid());
                            objEGetRKInfoResBDPKMa.setNum(obj.getNum());
                            objEGetRKInfoResBDPKMa.setUnitName(obj.getUnitName());
                            objEGetRKInfoResBDPKMas.add(objEGetRKInfoResBDPKMa);
                            objEGetRKInfoResD.setRKMaInfo(objEGetRKInfoResBDPKMas);
                            objEGetRKInfoResB.setData(objEGetRKInfoResD);
                    }
                }
            }

            if ("01".equals(objERkTkInfo.getAssSource())) {
                List<InCDtlInfo> objEInCDtlInfos = inCDtlDAO.SelectInCDtlInfoByInCCode(objERkTkInfo.getAssCode());
                if (objEInCDtlInfos != null || objEInCDtlInfos.size() != 0) {
                    for(InCDtlInfo obj:objEInCDtlInfos){
                        GetRKInfoResD objEGetRKInfoResD=new GetRKInfoResD();
                        objEGetRKInfoResD.setRkRd(objERkTkInfo.getRuid());
                        objEGetRKInfoResD.setAssCode(objERkTkInfo.getAssCode());
                        objEGetRKInfoResD.setRkCode(obj.getInCCode());
                        objEGetRKInfoResD.setRkType(objERkTkInfo.getRkType());
                        GetRKInfoResBDStore objEGetRKInfoResBDStore=new GetRKInfoResBDStore();
                        //仓库标识,因没有仓库标识信息，
                        StoreInfo objEStoreInfo=storeDAO.SelectByStoreGd(obj.getStoreGd());
                        if(objEStoreInfo!=null){
                            objEGetRKInfoResBDStore.setStoreRd(objEStoreInfo.getRuid());
                            objEGetRKInfoResBDStore.setStoreName(objEStoreInfo.getStoreName());
                        }

                        objEGetRKInfoResD.setStoreInfo(objEGetRKInfoResBDStore);

                        objEGetRKInfoResD.setAssCode(objERkTkInfo.getAssCode());
                        //物料
                        GetRKInfoResBDPKMa objEGetRKInfoResBDPKMa=new GetRKInfoResBDPKMa();
                        MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        if(objEMaVerInfo!=null){
                            objEGetRKInfoResBDPKMa.setMaVerRd(objEMaVerInfo.getRuid());
                            objEGetRKInfoResBDPKMa.setMaCode(objEMaVerInfo.getMaterialCode());
                            objEGetRKInfoResBDPKMa.setMaName(objEMaVerInfo.getMaterialName());
                        }

                        objEGetRKInfoResBDPKMa.setRkDtlRd(obj.getRuid());
                        objEGetRKInfoResBDPKMa.setNum(obj.getNum());
                        objEGetRKInfoResBDPKMa.setUnitName(obj.getUnitName());
                        objEGetRKInfoResBDPKMas.add(objEGetRKInfoResBDPKMa);
                        objEGetRKInfoResD.setRKMaInfo(objEGetRKInfoResBDPKMas);
                        objEGetRKInfoResB.setData(objEGetRKInfoResD);
                    }
                }
            }









         /*   List<RkTkDtlInfo> objERkTkDtlInfos=rkTkDtlDAO.SelectRkTkDtlInfoByrkTkGd(objERkTkInfo.getGuid());

            for(RkTkDtlInfo obj:objERkTkDtlInfos){
                GetRKInfoResD objEGetRKInfoResD=new GetRKInfoResD();
                objEGetRKInfoResD.setRkRd(objERkTkInfo.getRuid());

                objEGetRKInfoResD.setRkCode(obj.getRkCode());
                objEGetRKInfoResD.setRkType(objERkTkInfo.getRkType());
                GetRKInfoResBDStore objEGetRKInfoResBDStore=new GetRKInfoResBDStore();
                //仓库标识,因没有仓库标识信息，
                StoreInfo objEStoreInfo=storeDAO.SelectByStoreGd(objERkTkInfo.getStoreGd());
                if(objEStoreInfo!=null){
                    objEGetRKInfoResBDStore.setStoreRd(objEStoreInfo.getRuid());
                    objEGetRKInfoResBDStore.setStoreName(objEStoreInfo.getStoreName());
                }

                objEGetRKInfoResD.setStoreInfo(objEGetRKInfoResBDStore);

                objEGetRKInfoResD.setAssCode(objERkTkInfo.getAssCode());
                //物料
                GetRKInfoResBDPKMa objEGetRKInfoResBDPKMa=new GetRKInfoResBDPKMa();
                MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                if(objEMaVerInfo!=null){
                    objEGetRKInfoResBDPKMa.setMaVerRd(objEMaVerInfo.getRuid());
                }

                objEGetRKInfoResBDPKMa.setRkDtlRd(obj.getRuid());
                objEGetRKInfoResBDPKMa.setMaCode(obj.getMaterialCode());
                objEGetRKInfoResBDPKMa.setMaName(obj.getMaterialName());
                objEGetRKInfoResBDPKMa.setNum(obj.getNum());
                objEGetRKInfoResBDPKMa.setUnitName(obj.getUnitName());
                objEGetRKInfoResBDPKMas.add(objEGetRKInfoResBDPKMa);
                objEGetRKInfoResD.setRKMaInfo(objEGetRKInfoResBDPKMas);
                objEGetRKInfoResB.setData(objEGetRKInfoResD);
            }*/
        }


        objEGetRKInfoRes.setBody(objEGetRKInfoResB);
        return objEGetRKInfoRes;
    }

    //dto获取入库单明细信息
    public GetRKDlInfoRes GetGetRKDlInfoRes(GetRKDlInfoReqBD00 argGetRKDlInfoReqBD00) {
        GetRKDlInfoRes objEGetRKDlInfoRes = new GetRKDlInfoRes();
        GetRKDlInfoResB objEGetRKDlInfoResB = new GetRKDlInfoResB();
        List<GetRKDlInfoResD> objEGetRKDlInfoResDs = new ArrayList<GetRKDlInfoResD>();

        RkTkInfo objERkTkInfo = rkTkDAO.SelectRKTkInfoByruid(argGetRKDlInfoReqBD00.getRkRd());
        if (objERkTkInfo != null) {
            //采购单明细信息
            if ("00".equals(objERkTkInfo.getAssSource())) {
                List<PurChDtlInfo> objEPurChDtlInfos = purChDtlDAO.SelectPurChDtlInfoByPurChCode(objERkTkInfo.getAssCode());
                if (objEPurChDtlInfos != null && objEPurChDtlInfos.size() >0) {
                    for (PurChDtlInfo obj : objEPurChDtlInfos) {
                        GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                        objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                        objEGetRKDlInfoResD.setAssSource(objERkTkInfo.getAssSource());
                        objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objEGetRKDlInfoResD.setMaDes("");
                        }
                        objEGetRKDlInfoResD.setNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                        objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                        objEGetRKDlInfoResD.setScanNum(obj.getScanNum());
                        objEGetRKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                    }
                }
            }
            //来料单单明细信息
            if ("01".equals(objERkTkInfo.getAssSource())) {
                List<InCDtlInfo> objEInCDtlInfos = inCDtlDAO.SelectInCDtlInfoByInCCode(objERkTkInfo.getAssCode());
                if (objEInCDtlInfos != null && objEInCDtlInfos.size() > 0) {
                    for (InCDtlInfo obj : objEInCDtlInfos) {
                        GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                        objEGetRKDlInfoResD.setAssSource(objERkTkInfo.getAssSource());
                        objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objEGetRKDlInfoResD.setMaDes("");
                        }
                        objEGetRKDlInfoResD.setNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                        objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                        objEGetRKDlInfoResD.setScanNum(obj.getScanNum());
                        objEGetRKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());
                    }
                }
            }
            //退料明细
            if ("02".equals(objERkTkInfo.getAssSource())) {
                List<RetMaDtlInfo> objENRetMaDtlInfo = retMaDtlDAO.SelectRetMaDtlInfoByRetCode(objERkTkInfo.getAssCode());
                if (objENRetMaDtlInfo != null&& objENRetMaDtlInfo.size() > 0) {
                    for (RetMaDtlInfo obj : objENRetMaDtlInfo) {
                        GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                        objEGetRKDlInfoResD.setAssSource(objERkTkInfo.getAssSource());
                        objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objEGetRKDlInfoResD.setMaDes("");
                        }
                        objEGetRKDlInfoResD.setNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                        objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                        objEGetRKDlInfoResD.setScanNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnScanNum(0.0f);
                    }
                }
            }
            //产成品入库通知单明细
            if ("03".equals(objERkTkInfo.getAssSource())) {
                List<RMaNDtlInfo> objRMaNDtlInfo = rMaNDtlDAO.SelectRMaNDtlInfoByRMaNCode(objERkTkInfo.getAssCode());
                if (objRMaNDtlInfo != null && objRMaNDtlInfo.size() > 0) {
                    for (RMaNDtlInfo obj : objRMaNDtlInfo) {
                        GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                        objEGetRKDlInfoResD.setAssSource(objERkTkInfo.getAssSource());
                        objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objEGetRKDlInfoResD.setMaDes("");
                        }
                        objEGetRKDlInfoResD.setNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                        objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                        objEGetRKDlInfoResD.setScanNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnScanNum(0.0f);
                    }
                }
            } //快速退料
            if ("04".equals(objERkTkInfo.getAssSource())) {
                List<NRetMaDtlInfo> objRMaNDtlInfo = nRetMaDtlDAO.SelectRetMaDtlInfoByRetCode(objERkTkInfo.getAssCode());
                if (objRMaNDtlInfo != null && objRMaNDtlInfo.size() > 0) {
                    for (NRetMaDtlInfo obj : objRMaNDtlInfo) {
                        GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                        MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                        MaterialInfo objEMaterialInfo=materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                        objEGetRKDlInfoResD.setAssSource(objERkTkInfo.getAssSource());
                        objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                        objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                        if(objEMaterialInfo.getMaterialDes()!=null && !objEMaterialInfo.getMaterialDes().equals("")) {
                            objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                        }else{
                            objEGetRKDlInfoResD.setMaDes("");
                        }
                        objEGetRKDlInfoResD.setNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                        objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                        objEGetRKDlInfoResD.setScanNum(obj.getNum());
                        objEGetRKDlInfoResD.setUnScanNum(0.0f);
                    }
                }
            }
        }
            objEGetRKDlInfoResB.setData(objEGetRKDlInfoResDs);
            objEGetRKDlInfoRes.setBody(objEGetRKDlInfoResB);

            return objEGetRKDlInfoRes;
        }

    //导出入库单明细数据
    @Override
    public ByteArrayOutputStream ExportGetRKInfo(GetRKDlInfoReqBD00[] argGetRKDlInfoReqBD00s) {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();

        try {
            List<GetRKDlInfoResD> objEGetRKDlInfoResDs = new ArrayList<GetRKDlInfoResD>();
            for(GetRKDlInfoReqBD00 argGetRKDlInfoReqBD00:argGetRKDlInfoReqBD00s) {


                RkTkInfo objERkTkInfo = rkTkDAO.SelectRKTkInfoByruid(argGetRKDlInfoReqBD00.getRkRd());
                if (objERkTkInfo != null) {
                    //采购单明细信息
                    if ("00".equals(objERkTkInfo.getAssSource())) {
                        List<PurChDtlInfo> objEPurChDtlInfos = purChDtlDAO.SelectPurChDtlInfoByPurChCode(objERkTkInfo.getAssCode());
                        if (objEPurChDtlInfos != null || objEPurChDtlInfos.size() != 0) {
                            for (PurChDtlInfo obj : objEPurChDtlInfos) {
                                GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByGuid(objEMaVerInfo.getMaGd());
                                //入库列表信息
                                objEGetRKDlInfoResD.setAssCode(objERkTkInfo.getAssCode());
                                if (objERkTkInfo.getExecor() == null) {
                                    objEGetRKDlInfoResD.setExecor("");
                                } else {
                                    objEGetRKDlInfoResD.setExecor(objERkTkInfo.getExecor());
                                }
                                objEGetRKDlInfoResD.setExecTime(DateUtil.format(objERkTkInfo.getExecTime()));

                                objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                                objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objEGetRKDlInfoResD.setMaDes("");
                                }
                                objEGetRKDlInfoResD.setNum(obj.getNum());
                                objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                                objEGetRKDlInfoResD.setScanNum(obj.getScanNum());
                                objEGetRKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());

                                objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                            }
                        }
                    }
                    //来料单单明细信息
                    if ("01".equals(objERkTkInfo.getAssSource())) {
                        List<InCDtlInfo> objEInCDtlInfos = inCDtlDAO.SelectInCDtlInfoByInCCode(objERkTkInfo.getAssCode());
                        if (objEInCDtlInfos != null || objEInCDtlInfos.size() != 0) {
                            for (InCDtlInfo obj : objEInCDtlInfos) {
                                GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                                //入库列表信息
                                objEGetRKDlInfoResD.setAssCode(objERkTkInfo.getAssCode());
                                if (objEGetRKDlInfoResD.getExecor() == null) {
                                    objEGetRKDlInfoResD.setExecor("");
                                } else {
                                    objEGetRKDlInfoResD.setExecor(objERkTkInfo.getExecor());
                                }
                                objEGetRKDlInfoResD.setExecTime(DateUtil.format(objERkTkInfo.getExecTime()));

                                objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                                objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objEGetRKDlInfoResD.setMaDes("");
                                }
                                objEGetRKDlInfoResD.setNum(obj.getNum());
                                objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                                objEGetRKDlInfoResD.setScanNum(obj.getScanNum());
                                objEGetRKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());

                                objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                            }
                        }
                    }
                    //退料明细
                    if ("02".equals(objERkTkInfo.getAssSource())) {
                        List<RetMaDtlInfo> objENRetMaDtlInfo = retMaDtlDAO.SelectRetMaDtlInfoByRetCode(objERkTkInfo.getAssCode());
                        if (objENRetMaDtlInfo != null || objENRetMaDtlInfo.size() != 0) {
                            for (RetMaDtlInfo obj : objENRetMaDtlInfo) {
                                GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());

                                //入库列表信息
                                objEGetRKDlInfoResD.setAssCode(objERkTkInfo.getAssCode());
                                if (objEGetRKDlInfoResD.getExecor() == null) {
                                    objEGetRKDlInfoResD.setExecor("");
                                } else {
                                    objEGetRKDlInfoResD.setExecor(objERkTkInfo.getExecor());
                                }
                                objEGetRKDlInfoResD.setExecTime(DateUtil.format(objERkTkInfo.getExecTime()));

                                objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                                objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objEGetRKDlInfoResD.setMaDes("");
                                }
                                objEGetRKDlInfoResD.setNum(obj.getNum());
                                objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                                objEGetRKDlInfoResD.setScanNum(obj.getNum());
                                objEGetRKDlInfoResD.setUnScanNum(0.0f);

                                objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                            }
                        }
                    }
                    //产成品入库通知单明细
                    if ("03".equals(objERkTkInfo.getAssSource())) {
                        List<RMaNDtlInfo> objRMaNDtlInfo = rMaNDtlDAO.SelectRMaNDtlInfoByRMaNCode(objERkTkInfo.getAssCode());
                        if (objRMaNDtlInfo != null && objRMaNDtlInfo.size() > 0) {
                            for (RMaNDtlInfo obj : objRMaNDtlInfo) {
                                GetRKDlInfoResD objEGetRKDlInfoResD = new GetRKDlInfoResD();
                                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                                MaterialInfo objEMaterialInfo = materialDAO.SelectByMaCode(objEMaVerInfo.getMaterialCode());
                                //入库列表信息
                                objEGetRKDlInfoResD.setAssCode(objERkTkInfo.getAssCode());
                                if (objEGetRKDlInfoResD.getExecor() == null) {
                                    objEGetRKDlInfoResD.setExecor("");
                                } else {
                                    objEGetRKDlInfoResD.setExecor(objERkTkInfo.getExecor());
                                }
                                objEGetRKDlInfoResD.setExecTime(DateUtil.format(objERkTkInfo.getExecTime()));

                                objEGetRKDlInfoResD.setRkDtlRd(obj.getRuid());
                                objEGetRKDlInfoResD.setMaCode(objEMaterialInfo.getMaterialCode());
                                objEGetRKDlInfoResD.setMaName(objEMaterialInfo.getMaterialName());
                                if (objEMaterialInfo.getMaterialDes() != null && !objEMaterialInfo.getMaterialDes().equals("")) {
                                    objEGetRKDlInfoResD.setMaDes(objEMaterialInfo.getMaterialDes());
                                } else {
                                    objEGetRKDlInfoResD.setMaDes("");
                                }
                                objEGetRKDlInfoResD.setNum(obj.getNum());
                                objEGetRKDlInfoResD.setUnitName(obj.getUnitName());
                                objEGetRKDlInfoResD.setScanNum(obj.getScanNum());
                                objEGetRKDlInfoResD.setUnScanNum(obj.getNum() - obj.getScanNum());

                                objEGetRKDlInfoResDs.add(objEGetRKDlInfoResD);
                            }
                        }
                    }
                }
            }

            //创建table工作薄
            HSSFSheet sheet1 = wb.createSheet("table");
            Object[][] datas1 = {
                    {"单号","物料代码", "物料名称","物料描述","入库数量", "已入库数量", "未入库数量","单位","入库人","入库开始时间"}};

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


            /**********************************入库数据************************************/
            HSSFSheet sheet3 = wb.getSheet("table");
            HSSFRow row3;
            HSSFCell cell3;
            //建表
            for (int i = 0; i < objEGetRKDlInfoResDs.size(); i++) {
                row3 = sheet3.createRow(i + datas1.length );//创建表格行

                for (int j = 0; j < datas1[0].length; j++) {

                    cell3 = row3.createCell(j);//根据表格行创建单元格
                    if (j == 0) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getAssCode());
                    } else if (j == 1) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getMaCode());
                    } else if (j == 2) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getMaName());
                    }else if (j == 3) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getMaDes());
                    }else if (j == 4) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getNum());
                    }else if (j == 5) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getScanNum());
                    }else if (j == 6) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getUnScanNum());
                    }else if (j == 7) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getUnitName());
                    }else if (j == 8) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getExecor());
                    }else if (j == 9) {
                        cell3.setCellValue(objEGetRKDlInfoResDs.get(i).getExecTime());
                    }

                }

            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);

            return os;

        } catch(Exception e){
            if (e != null && e.getMessage() != null) {
                throw new SystemException("0x000000", e.getMessage());
            }
        } finally{
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


    //dto获取入库单条码信息
    public GetRKBarInfoRes GetGetRKBarInfoRes(GetRKBarInfoReqBD00 argGetRKBarInfoReqBD00) {
        GetRKBarInfoRes objEGetRKBarInfoRes=new GetRKBarInfoRes();
        GetRKBarInfoResB objEGetRKBarInfoResB=new GetRKBarInfoResB();
        List<GetRKBarInfoResD> objEGetRKBarInfoResDs=new ArrayList<GetRKBarInfoResD>();

        //采购单明细信息
        if("00".equals(argGetRKBarInfoReqBD00.getAssSource())){
            PurChDtlInfo objEPurChDtlInfo=purChDtlDAO.SelectPurChDtlInfoByRuid(argGetRKBarInfoReqBD00.getRkDtlRd());
            if(objEPurChDtlInfo!=null){
                List<RkDtlInfo> objERkDtlInfos=rkTkIDAO.SelectRkDtlInfoByrkTkDtlGd(objEPurChDtlInfo.getGuid());
                if(objERkDtlInfos!=null||objERkDtlInfos.size()!=0){
                    for (RkDtlInfo obj:objERkDtlInfos){
                        GetRKBarInfoResD objEGetRKBarInfoResD=new GetRKBarInfoResD();
                        objEGetRKBarInfoResD.setNum(obj.getNum());
                        objEGetRKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetRKBarInfoResD.setProductDate(DateUtil.format(obj.getProductDate()));
                        objEGetRKBarInfoResD.setExpireDate(DateUtil.format(obj.getExpireDate()));
                        if("".equals(obj.getRemark())||obj.getRemark()==null){
                            objEGetRKBarInfoResD.setRemark("");
                        }else {
                            objEGetRKBarInfoResD.setRemark(obj.getRemark());
                        }


                        //扫描时间，即创建时间
                        objEGetRKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        objEGetRKBarInfoResDs.add(objEGetRKBarInfoResD);
                    }
                }
            }
        }
        //来料单单明细信息
        if("01".equals(argGetRKBarInfoReqBD00.getAssSource())){
            InCDtlInfo objEInCDtlInfo=inCDtlDAO.SelectInCDtlInfoByRuid(argGetRKBarInfoReqBD00.getRkDtlRd());
            if(objEInCDtlInfo!=null){
                List<RkDtlInfo> objERkDtlInfos=rkTkIDAO.SelectRkDtlInfoByrkTkDtlGd(objEInCDtlInfo.getGuid());
                if(objERkDtlInfos!=null||objERkDtlInfos.size()!=0){
                    for (RkDtlInfo obj:objERkDtlInfos){
                        GetRKBarInfoResD objEGetRKBarInfoResD=new GetRKBarInfoResD();
                        objEGetRKBarInfoResD.setNum(obj.getNum());
                        objEGetRKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetRKBarInfoResD.setProductDate(DateUtil.format(obj.getProductDate()));
                        objEGetRKBarInfoResD.setExpireDate(DateUtil.format(obj.getExpireDate()));
                        //扫描时间，即创建时间
                        objEGetRKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        if("".equals(obj.getRemark())||obj.getRemark()==null){
                            objEGetRKBarInfoResD.setRemark("");
                        }else {
                            objEGetRKBarInfoResD.setRemark(obj.getRemark());
                        }
                        objEGetRKBarInfoResDs.add(objEGetRKBarInfoResD);
                    }
                }
            }
        }
        if("02".equals(argGetRKBarInfoReqBD00.getAssSource())){
            RetMaDtlInfo nRetMaDtlInfo=retMaDtlDAO.SelectRetMaDtlInfoByRuid(argGetRKBarInfoReqBD00.getRkDtlRd());
            if(nRetMaDtlInfo!=null){
                List<RkDtlInfo> objERkDtlInfos=rkTkIDAO.SelectRkDtlInfoByrkTkDtlGd(nRetMaDtlInfo.getGuid());
                if(objERkDtlInfos!=null||objERkDtlInfos.size()!=0){
                    for (RkDtlInfo obj:objERkDtlInfos){
                        GetRKBarInfoResD objEGetRKBarInfoResD=new GetRKBarInfoResD();
                        objEGetRKBarInfoResD.setNum(obj.getNum());
                        objEGetRKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetRKBarInfoResD.setProductDate(DateUtil.format(obj.getProductDate()));
                        objEGetRKBarInfoResD.setExpireDate(DateUtil.format(obj.getExpireDate()));
                        //扫描时间，即创建时间
                        objEGetRKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        if("".equals(obj.getRemark())||obj.getRemark()==null){
                            objEGetRKBarInfoResD.setRemark("");
                        }else {
                            objEGetRKBarInfoResD.setRemark(obj.getRemark());
                        }

                        objEGetRKBarInfoResDs.add(objEGetRKBarInfoResD);
                    }
                }
            }
        }
        if("03".equals(argGetRKBarInfoReqBD00.getAssSource())){
            RMaNDtlInfo objRMaNDtlInfo=rMaNDtlDAO.SelectRMaNDtlInfoByRuid(argGetRKBarInfoReqBD00.getRkDtlRd());
            if(objRMaNDtlInfo!=null){
                List<RkDtlInfo> objERkDtlInfos=rkTkIDAO.SelectRkDtlInfoByrkTkDtlGd(objRMaNDtlInfo.getGuid());
                if(objERkDtlInfos!=null||objERkDtlInfos.size()!=0){
                    for (RkDtlInfo obj:objERkDtlInfos){
                        GetRKBarInfoResD objEGetRKBarInfoResD=new GetRKBarInfoResD();
                        objEGetRKBarInfoResD.setNum(obj.getNum());
                        objEGetRKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetRKBarInfoResD.setProductDate(DateUtil.format(obj.getProductDate()));
                        objEGetRKBarInfoResD.setExpireDate(DateUtil.format(obj.getExpireDate()));
                        //扫描时间，即创建时间
                        objEGetRKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        if("".equals(obj.getRemark())||obj.getRemark()==null){
                            objEGetRKBarInfoResD.setRemark("");
                        }else {
                            objEGetRKBarInfoResD.setRemark(obj.getRemark());
                        }

                        objEGetRKBarInfoResDs.add(objEGetRKBarInfoResD);
                    }
                }
            }
        }

        if("04".equals(argGetRKBarInfoReqBD00.getAssSource())){
            NRetMaDtlInfo objRMaNDtlInfo=nRetMaDtlDAO.SelectRetMaDtlInfoByRuid(argGetRKBarInfoReqBD00.getRkDtlRd());
            if(objRMaNDtlInfo!=null){
                List<RkDtlInfo> objERkDtlInfos=rkTkIDAO.SelectRkDtlInfoByrkTkDtlGd(objRMaNDtlInfo.getGuid());
                if(objERkDtlInfos!=null||objERkDtlInfos.size()!=0){
                    for (RkDtlInfo obj:objERkDtlInfos){
                        GetRKBarInfoResD objEGetRKBarInfoResD=new GetRKBarInfoResD();
                        objEGetRKBarInfoResD.setNum(obj.getNum());
                        objEGetRKBarInfoResD.setBarCode(obj.getBarCode());
                        objEGetRKBarInfoResD.setProductDate(DateUtil.format(obj.getProductDate()));
                        objEGetRKBarInfoResD.setExpireDate(DateUtil.format(obj.getExpireDate()));
                        //扫描时间，即创建时间
                        objEGetRKBarInfoResD.setScannerTime(DateUtil.format(obj.getCreateTime()));
                        if("".equals(obj.getRemark())||obj.getRemark()==null){
                            objEGetRKBarInfoResD.setRemark("");
                        }else {
                            objEGetRKBarInfoResD.setRemark(obj.getRemark());
                        }

                        objEGetRKBarInfoResDs.add(objEGetRKBarInfoResD);
                    }
                }
            }
        }
        objEGetRKBarInfoResB.setData(objEGetRKBarInfoResDs);
        objEGetRKBarInfoRes.setBody(objEGetRKBarInfoResB);
        return objEGetRKBarInfoRes;
    }



    //dto新增入库单信息
    public SaveRKInfoRes AddSaveRKInfoRes(SaveRKInfoReqBD00 argSaveRKInfoReqBD00) {
        SaveRKInfoRes objESaveRKInfoRes=new SaveRKInfoRes();
        SaveRKInfoResB objESaveRKInfoResB=new SaveRKInfoResB();
        SaveRKInfoResD objESaveRKInfoResD=new SaveRKInfoResD();
/*
        RkTkInfo objERkTkInfo=new RkTkInfo();

        objERkTkInfo.setGuid(CommonUtils.getRandomNumber());

        objERkTkInfo.setRkType(argSaveRKInfoReqBD00.getRkType());
        //因为保存入库单号，要获取自增长id 因此这边先默认设置值 00，保存过后在更新
        objERkTkInfo.setRkCode("00");
        objERkTkInfo.setAssCode(argSaveRKInfoReqBD00.getAssCode());
        //仓库标识,因没有仓库标识信息，
        StoreInfo objEStoreInfo=storeDAO.SelectByRuid(argSaveRKInfoReqBD00.getStoreRd());
        if(objEStoreInfo==null){
            throw new SystemException("xx","查询仓库信息失败");
        }
        //objERkTkInfo.setStoreGd(objEStoreInfo.getGuid());

        //新增  设置执行状态 因为刚刚新增上去的数据默认是00 待执行
        objERkTkInfo.setExStatus("00");
        objERkTkInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        objERkTkInfo.setCreateTime(new Date());
        objERkTkInfo.setRemark(argSaveRKInfoReqBD00.getRemark());
        if(rkTkDAO.InsertRKTkInfo(objERkTkInfo)<=0){
            throw new SystemException("xxx","新增入库信息失败");
        }

        RkTkInfo objERkTkInfos=rkTkDAO.SelectRKTkInfoByguid(objERkTkInfo.getGuid());
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

        //循环遍历从客户端传过来的细心信息明细新增
      *//*  for(SaveRKInfoReqBD00RkMa objE:argSaveRKInfoReqBD00.getRKMaInfo()){
            //入库单明细
            RkTkDtlInfo objERkTkDtlInfo=new RkTkDtlInfo();
            objERkTkDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objERkTkDtlInfo.setRkTkGd(objERkTkInfo.getGuid());
            objERkTkDtlInfo.setRkCode(objERkTkInfos.getRkCode());
            //物料
            MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(objE.getMaVerRd());
            objERkTkDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
            objERkTkDtlInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
            objERkTkDtlInfo.setMaterialName(objEMaVerInfo.getMaterialName());
            if("".equals(objE.getNum())){
                throw new SystemException("xxx","新增数量不能为空");
            }
            objERkTkDtlInfo.setNum(objE.getNum());
            //已扫描数量 默认值 0
            objERkTkDtlInfo.setScanNum(0.00f);
            objERkTkDtlInfo.setUnitName(objE.getUnitName());
            objERkTkDtlInfo.setRemark(argSaveRKInfoReqBD00.getRemark());
            objERkTkDtlInfo.setCreateTime(new Date());
            objERkTkDtlInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
            if(rkTkDtlDAO.InsertRkTkDtlInfo(objERkTkDtlInfo)<=0){
                    throw new SystemException("xxx","新增入库单明细失败");
            }
        }*//**/
        objESaveRKInfoResB.setData(objESaveRKInfoResD);
        objESaveRKInfoRes.setBody(objESaveRKInfoResB);
        return objESaveRKInfoRes;
    }
    //删除入库单信息
    public SaveRKInfoRes RmSaveRKInfoRes(SaveRKInfoReqBD01 argSaveRKInfoReqBD01) {
        SaveRKInfoRes objESaveRKInfoRes=new SaveRKInfoRes();
        SaveRKInfoResB objESaveRKInfoResB=new SaveRKInfoResB();
        SaveRKInfoResD objESaveRKInfoResD=new SaveRKInfoResD();
        RkTkInfo objERkTkInfo=rkTkDAO.SelectRKTkInfoByruid(argSaveRKInfoReqBD01.getRkRd());
        if(objERkTkInfo==null){
            throw new SystemException("xxx","查询入库任务信息失败");
        }

        if("00".equals(objERkTkInfo.getExStatus())||"03".equals(objERkTkInfo.getExStatus())){
            if(rkTkDAO.DeleteRKTkInfo(objERkTkInfo.getRuid())<=0){
                throw new SystemException("xxx","删除入库单信息失败");
            }
        }
      /*  List<RkTkDtlInfo> objERkTkDtlInfos=rkTkDtlDAO.SelectRkTkDtlInfoByrkTkGd(objERkTkInfo.getGuid());
        if(objERkTkDtlInfos==null||objERkTkDtlInfos.size()<=0){
            throw new SystemException("xxx","查询入库任务明细信息失败");
        }*/
        //删除入库单信息 00待执行 03已取消
      /*  if("00".equals(objERkTkInfo.getExStatus())||"03".equals(objERkTkInfo.getExStatus())){
            for(RkTkDtlInfo obj:objERkTkDtlInfos){
            //这里删除的是先删除子，再删除自己
            if(rkTkDtlDAO.DeleteRkTkDtlInfo(obj.getRuid(),obj.getGuid())<=0){
                throw new SystemException("xxx","删除任务明细信息失败");
            }
            }
            if(rkTkDAO.DeleteRKTkInfo(objERkTkInfo.getRuid())<=0){
                throw new SystemException("xxx","删除入库单信息失败");
            }
        }*/

       /* else {
                throw new SystemException("xxx","删除失败,原因是执行状态已完成或者正在进行中!");
            }*/
        objESaveRKInfoResB.setData(objESaveRKInfoResD);
        objESaveRKInfoRes.setBody(objESaveRKInfoResB);
        return objESaveRKInfoRes;
    }
    //dto取消入库信息
    public SaveRKInfoRes MoSaveRKInfoRes(SaveRKInfoReqBD03 argSaveRKInfoReqBD03) {
        SaveRKInfoRes objESaveRKInfoRes=new SaveRKInfoRes();
        SaveRKInfoResB objESaveRKInfoResB=new SaveRKInfoResB();
        SaveRKInfoResD objESaveRKInfoResD=new SaveRKInfoResD();
        RkTkInfo objERkTkInfo=rkTkDAO.SelectRKTkInfoByruid(argSaveRKInfoReqBD03.getRkRd());
        if(objERkTkInfo==null){
            throw new SystemException("xxx","查询入库任务信息失败");
        }

        if("01".equals(objERkTkInfo.getExStatus())){
            objERkTkInfo.setCancelor(CommonUtils.readUser().getRealName());
            objERkTkInfo.setCancelTime(new Date());
            objERkTkInfo.setExStatus("03");
            if(rkTkDAO.UpdateRKTkInfo(objERkTkInfo)<=0){
                throw new SystemException("xxx","取消入库任务信息失败");
            }
        }
        else {
            throw new SystemException("xxx01","取消失败，因为状态不符合");
        }
        objESaveRKInfoResB.setData(objESaveRKInfoResD);
        objESaveRKInfoRes.setBody(objESaveRKInfoResB);
        return objESaveRKInfoRes;
    }
    //dto更新入库单信息
    public SaveRKInfoRes MoSaveRKInfoRess(SaveRKInfoReqBD02 argSaveRKInfoReqBD02) {
        SaveRKInfoRes objESaveRKInfoRes=new SaveRKInfoRes();
        SaveRKInfoResB objESaveRKInfoResB=new SaveRKInfoResB();
        SaveRKInfoResD objESaveRKInfoResD=new SaveRKInfoResD();

        RkTkInfo objERkTkInfo=rkTkDAO.SelectRKTkInfoByruid(argSaveRKInfoReqBD02.getRkRd());
        if(objERkTkInfo==null){
            throw new SystemException("xxx","查询入库任务信息失败");
        }
        if("00".equals(objERkTkInfo.getAssSource())){
            List<PurChDtlInfo> objEPurChDtlInfos=purChDtlDAO.SelectPurChDtlInfoByPurChCode(objERkTkInfo.getAssCode());
            if(objEPurChDtlInfos!=null){
                if("02".equals(objERkTkInfo.getExStatus())||"03".equals(objERkTkInfo.getExStatus())){
                    throw new SystemException("MES_001","更新任务明细失败,原因是该任务已完成或者是已取消");
                }

                for(SaveRKInfoReqBD02RkMa rkma:argSaveRKInfoReqBD02.getRKMaInfo()){
                    boolean flag = true;
                    for(int i=0; i<objEPurChDtlInfos.size(); i++){
                        if(rkma.getRkDtlRd() == objEPurChDtlInfos.get(i).getRuid()){
                            PurChDtlInfo objEPurChDtlInfo=new PurChDtlInfo();
                            objEPurChDtlInfo.setPurChCode(objERkTkInfo.getAssCode());
                            //物料
                            MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(rkma.getMaVerRd());
                            if(objEMaVerInfo==null){
                                throw new SystemException("xxx","查询物料版本失败");
                            }
                            objEPurChDtlInfo.setRuid(rkma.getRkDtlRd());
                            objEPurChDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                            objEPurChDtlInfo.setScanNum(objEPurChDtlInfos.get(i).getScanNum());
                            objEPurChDtlInfo.setNum(rkma.getNum());
                            objEPurChDtlInfo.setUnitName(rkma.getUnitName());
                            objEPurChDtlInfo.setRemark(argSaveRKInfoReqBD02.getRemark());
                            objEPurChDtlInfo.setLastModifyTime(new Date());
                            objEPurChDtlInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                            if(rkma.getNum()<objEPurChDtlInfos.get(i).getScanNum()){
                                throw new SystemException("MG_MES3001012010001F",String.format("入库单[%s]下物料[%s]不能小于已扫描数量[%s]",objEPurChDtlInfos.get(i).getPurChCode(),objEMaVerInfo.getMaterialName(),objEPurChDtlInfos.get(i).getScanNum()));
                            }
                            if(purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo)<=0){
                                throw new SystemException("xx","更新入库单明细失败");
                            }
                            objEPurChDtlInfos.remove(i);
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        PurChDtlInfo objEPurChDtlInfo=new PurChDtlInfo();
                        objEPurChDtlInfo.setGuid(CommonUtils.getRandomNumber());
                        //物料
                        MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(rkma.getMaVerRd());
                        if(objEMaVerInfo==null){
                            throw new SystemException("xxx","查询物料版本失败");
                        }
                        //采购单
                        PurChInfo objEPurChInfo=purChDAO.SelectPurChInfoByPurChCode(objERkTkInfo.getAssCode());
                        if(objEPurChInfo!=null){
                            objEPurChDtlInfo.setPurChGd(objEPurChInfo.getGuid());
                        }
                        //仓库
                        StoreInfo objEStoreInfo=storeDAO.SelectByRuid(argSaveRKInfoReqBD02.getStoreRd());
                        if(objEStoreInfo!=null){
                            objEPurChDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                        }

                        objEPurChDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                        objEPurChDtlInfo.setPurChCode(objERkTkInfo.getAssCode());
                        objEPurChDtlInfo.setScanNum(0);
                        objEPurChDtlInfo.setaFeed("01");
                        objEPurChDtlInfo.setNum(rkma.getNum());
                        objEPurChDtlInfo.setUnitName(rkma.getUnitName());
                        objEPurChDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                        objEPurChDtlInfo.setCreateTime(new Date());
                      purChDtlDAO.InsertPurChDtlInfo(objEPurChDtlInfo);
                    }
                }

            for(PurChDtlInfo obj : objEPurChDtlInfos) {
                if (purChDtlDAO.deletePurChDtlInfo(obj.getRuid())<= 0) {
                    throw new SystemException("xxx", "删除入库单明细失败");
                }
            }
        }
        }
        if("01".equals(objERkTkInfo.getAssSource())){
            List<InCDtlInfo> objEInCDtlInfos=inCDtlDAO.SelectInCDtlInfoByInCCode(objERkTkInfo.getAssCode());
            if(objEInCDtlInfos!=null){
                if("02".equals(objERkTkInfo.getExStatus())||"03".equals(objERkTkInfo.getExStatus())){
                    throw new SystemException("MES_001","更新任务明细失败,原因是该任务已完成或者是已取消");
                }

                for(SaveRKInfoReqBD02RkMa rkma:argSaveRKInfoReqBD02.getRKMaInfo()){
                    boolean flag = true;
                    for(int i=0; i<objEInCDtlInfos.size(); i++){
                        if(rkma.getRkDtlRd() == objEInCDtlInfos.get(i).getRuid()){
                            InCDtlInfo objEInCDtlInfo=new InCDtlInfo();
                            objEInCDtlInfo.setInCCode(objERkTkInfo.getRkCode());
                            //物料
                            MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(rkma.getMaVerRd());
                            if(objEMaVerInfo==null){
                                throw new SystemException("xxx","查询物料版本失败");
                            }

                            objEInCDtlInfo.setRuid(rkma.getRkDtlRd());
                            objEInCDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                            objEInCDtlInfo.setScanNum(objEInCDtlInfos.get(i).getScanNum());
                            objEInCDtlInfo.setNum(rkma.getNum());
                            objEInCDtlInfo.setUnitName(rkma.getUnitName());
                            objEInCDtlInfo.setRemark(argSaveRKInfoReqBD02.getRemark());
                            objEInCDtlInfo.setLastModifyTime(new Date());
                            objEInCDtlInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                            if(rkma.getNum()<objEInCDtlInfos.get(i).getScanNum()){
                                throw new SystemException("MG_MES3001012010001F",String.format("入库单[%s]下物料[%s]不能小于已扫描数量[%s]",objEInCDtlInfos.get(i).getInCCode(),objEMaVerInfo.getMaterialName(),objEInCDtlInfos.get(i).getScanNum()));
                            }
                            if(inCDtlDAO.UpdateInChDtlInfo(objEInCDtlInfo)<=0){
                                throw new SystemException("xx","更新入库单明细失败");
                            }
                            objEInCDtlInfos.remove(i);
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        InCDtlInfo objEInCDtlInfo=new InCDtlInfo();
                        objEInCDtlInfo.setGuid(CommonUtils.getRandomNumber());
                        //物料
                        MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(rkma.getMaVerRd());
                        if(objEMaVerInfo==null){
                            throw new SystemException("xxx","查询物料版本失败");
                        }
                        //采购单
                        InCInfo  objEInCInfo=inCDAO.SelectByInCCode(objERkTkInfo.getAssCode());
                        if(objEInCInfo!=null){
                            objEInCDtlInfo.setInChGd(objEInCInfo.getGuid());
                        }
                        //仓库
                        StoreInfo objEStoreInfo=storeDAO.SelectByRuid(argSaveRKInfoReqBD02.getStoreRd());
                        if(objEStoreInfo!=null){
                            objEInCDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                        }
                        objEInCDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                        objEInCDtlInfo.setInCCode(objERkTkInfo.getRkCode());
                        objEInCDtlInfo.setScanNum(0);
                        objEInCDtlInfo.setNum(rkma.getNum());
                        objEInCDtlInfo.setUnitName(rkma.getUnitName());
                        objEInCDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                        objEInCDtlInfo.setCreateTime(new Date());
                        objEInCDtlInfo.setaFeed("01");
                        inCDtlDAO.InsertInCDtlInfo(objEInCDtlInfo);
                    }
                }

                for(InCDtlInfo obj : objEInCDtlInfos) {
                    if (inCDtlDAO.DeleteInChDtlInfo(obj.getRuid())<= 0) {
                        throw new SystemException("xxx", "删除入库单明细失败");
                    }
                }
            }
        }

        //明细
       /* List<RkTkDtlInfo> objERkTkDtlInfos=rkTkDtlDAO.SelectRkTkDtlInfoByrkTkGd(objERkTkInfo.getGuid());
        if(objERkTkDtlInfos!=null){
            if("02".equals(objERkTkInfo.getExStatus())||"03".equals(objERkTkInfo.getExStatus())){
                throw new SystemException("MES_001","更新任务明细失败,原因是该任务已完成或者是已取消");
            }

            for(SaveRKInfoReqBD02RkMa rkma:argSaveRKInfoReqBD02.getRKMaInfo()){
                boolean flag = true;
                for(int i=0; i<objERkTkDtlInfos.size(); i++){
                    if(rkma.getRkDtlRd() == objERkTkDtlInfos.get(i).getRuid()){
                        RkTkDtlInfo objERkTkDtlInfo=new RkTkDtlInfo();
                        objERkTkDtlInfo.setRkCode(objERkTkInfo.getRkCode());
                        //物料
                        MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(rkma.getMaVerRd());
                        if(objEMaVerInfo==null){
                            throw new SystemException("xxx","查询物料版本失败");
                        }
                        objERkTkDtlInfo.setRuid(rkma.getRkDtlRd());
                        objERkTkDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                        objERkTkDtlInfo.setScanNum(objERkTkDtlInfos.get(i).getScanNum());
                        objERkTkDtlInfo.setNum(rkma.getNum());
                        objERkTkDtlInfo.setUnitName(rkma.getUnitName());
                        objERkTkDtlInfo.setRemark(argSaveRKInfoReqBD02.getRemark());
                        objERkTkDtlInfo.setLastModifyTime(new Date());
                        objERkTkDtlInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                        objERkTkDtlInfo.setCreateTime(new Date());
                        objERkTkDtlInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                        if(rkma.getNum()<objERkTkDtlInfos.get(i).getScanNum()){
                            throw new SystemException("MG_MES3001012010001F",String.format("入库单[%s]下物料[%s]不能小于已扫描数量[%s]",objERkTkDtlInfos.get(i).getRkCode(),objERkTkDtlInfos.get(i).getMaterialName(),objERkTkDtlInfos.get(i).getScanNum()));
                        }
                        if(rkTkDtlDAO.UpdateRkTkDtlInfo(objERkTkDtlInfo)<=0){
                            throw new SystemException("xx","更新入库单明细失败");
                        }
                        objERkTkDtlInfos.remove(i);
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    RkTkDtlInfo objERkTkDtlInfo=new RkTkDtlInfo();
                    objERkTkDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    objERkTkDtlInfo.setRkTkGd(objERkTkInfo.getGuid());
                    //物料
                    MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(rkma.getMaVerRd());
                    if(objEMaVerInfo==null){
                        throw new SystemException("xxx","查询物料版本失败");
                    }
                    objERkTkDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                    objERkTkDtlInfo.setRkCode(objERkTkInfo.getRkCode());
                    objERkTkDtlInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objERkTkDtlInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objERkTkDtlInfo.setScanNum(0);
                    objERkTkDtlInfo.setNum(rkma.getNum());
                    objERkTkDtlInfo.setUnitName(rkma.getUnitName());
                    objERkTkDtlInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                    objERkTkDtlInfo.setCreateTime(new Date());
                    if(rkTkDtlDAO.InsertRkTkDtlInfo(objERkTkDtlInfo)<=0){
                        throw new SystemException("xxx","新增入库单明细失败");
                    }
                }
            }*/

           /* for(RkTkDtlInfo obj : objERkTkDtlInfos) {
                if (rkTkDtlDAO.DeleteRkTkDtlInfo(obj.getRuid(), obj.getGuid()) <= 0) {
                    throw new SystemException("xxx", "删除入库单明细失败");
                }
            }
        }*/


        objESaveRKInfoResB.setData(objESaveRKInfoResD);
        objESaveRKInfoRes.setBody(objESaveRKInfoResB);
        return objESaveRKInfoRes;
    }
}
