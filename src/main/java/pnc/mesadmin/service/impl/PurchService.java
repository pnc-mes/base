package pnc.mesadmin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoRes;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoResB;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoResD;
import pnc.mesadmin.dto.GetAllPDlInfo.GetAllPDlInfoResDStore;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoRes;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoResB;
import pnc.mesadmin.dto.GetAllPurchInfo.GetAllPurchInfoResD;
import pnc.mesadmin.dto.GetPurchInfo.*;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SavePurchInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.PurchIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by PNC on 2017/8/23.
 */
@Service
@Transactional
public class PurchService implements PurchIService {

    @Resource
    private PurChDAO purChDAO;

    @Resource
    private SupplierDAO supplierDAO;

    @Resource
    private PurChDtlDAO purChDtlDAO;

    @Resource
    private StoreDAO storeDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private CodeRuleDAO codeRuleDAO;

    @Resource
    private GConfigIService gConfigIService;

    @Resource
    private MaVerDAO maVerDAO;

    //获取采购单列表信息
    public GetAllPurchInfoRes QALLGetAllPurchInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllPurchInfoRes objEGetAllPurchInfoRes=new GetAllPurchInfoRes();
        GetAllPurchInfoResB objEGetAllPurchInfoResB=new GetAllPurchInfoResB();
        List<GetAllPurchInfoResD> objEGetAllPurchInfoResDs=new ArrayList<GetAllPurchInfoResD>();
        List<PurChInfo> objEPurChInfos=baseIService.QALLBaseInfo(PurChDAO.class, "SelectPurChInfo",
                argInitDataFields, argPageInfo);
            if(objEPurChInfos!=null||objEPurChInfos.size()!=0){
                for(PurChInfo obj:objEPurChInfos){
                    GetAllPurchInfoResD objEGetAllPurchInfoResD=new GetAllPurchInfoResD();
                    objEGetAllPurchInfoResD.setPurChRd(obj.getRuid());
                    objEGetAllPurchInfoResD.setPurChCode(obj.getPurChCode());
                    objEGetAllPurchInfoResD.setBuyer(obj.getBuyer());
                    objEGetAllPurchInfoResD.setSupCode(obj.getSupplierCode());
                    objEGetAllPurchInfoResD.setSupName(obj.getSupplierName());
                  //  objEGetAllPurchInfoResD.setArrivalTime(DateUtil.format(obj.getArrivalTime()));
                    objEGetAllPurchInfoResD.setStatus(obj.getStatus());
                    objEGetAllPurchInfoResDs.add(objEGetAllPurchInfoResD);
                    objEGetAllPurchInfoResD.setRemark(obj.getRemark());
                }
            }
        objEGetAllPurchInfoResB.setData(objEGetAllPurchInfoResDs);
        objEGetAllPurchInfoRes.setBody(objEGetAllPurchInfoResB);
        return objEGetAllPurchInfoRes;
    }

    //保存采购单信息
    public SavePurchInfoRes AddSavePurchInfoRes(SavePurchInfoReq00 argSavePurchInfoReq00) {
        SavePurchInfoRes objESavePurchInfoRes=new SavePurchInfoRes();
        SavePurchInfoResB objESavePurchInfoResB=new SavePurchInfoResB();
        SavePurchInfoResD objESavePurchInfoResD=new SavePurchInfoResD();

        if("".equals(argSavePurchInfoReq00.getBuyer())||argSavePurchInfoReq00.getBuyer()==null){
            throw new SystemException("","新增失败，采购员不能为空");
        }
        //不允许重复
        if(!"".equals(argSavePurchInfoReq00.getPurChCode())) {
            int a = purChDAO.SelectPurChInfoBypurChCode(argSavePurchInfoReq00.getPurChCode());

            if (a > 0) {
                throw new SystemException("", "新增失败，采购单号重复");
            }
        }
        if(argSavePurchInfoReq00.getPurMaInfo().size()<=0||argSavePurchInfoReq00.getPurMaInfo()==null){
            throw new SystemException("","新增失败，采购单明细不能为空");
        }

        PurChInfo objEPurChInfo=new PurChInfo();
        objEPurChInfo.setGuid(CommonUtils.getRandomNumber());

        //查询代码生成
        CodeRuleInfo codeRuleInfo=codeRuleDAO.SelectCodeRuleInfocodeType("04");

        String SCode="";

        if(!"".equals(argSavePurchInfoReq00.getPurChCode())) {
            objEPurChInfo.setPurChCode(argSavePurchInfoReq00.getPurChCode());
            objEPurChInfo.setRemark(argSavePurchInfoReq00.getPurChCode());
        }else if(codeRuleInfo!=null && "00".equals(codeRuleInfo.getStatus())){
            SCode=gConfigIService.GetCreateSC(codeRuleInfo);
            objEPurChInfo.setPurChCode(SCode);
            objEPurChInfo.setRemark(SCode);
        }else{
            throw new SystemException("", "请输入{采购单号}，或请到全局配置进行代码生成配置");
        }

        objEPurChInfo.setBuyer(argSavePurchInfoReq00.getBuyer());
        objEPurChInfo.setSupplierCode(argSavePurchInfoReq00.getSupCode());
        objEPurChInfo.setSupplierName(argSavePurchInfoReq00.getSupName());



        /*objEPurChInfo.setArrivalTime(DateUtil.format(argSavePurchInfoReq00.getArrivalTime()));*/

        objEPurChInfo.setStatus("00");
        objEPurChInfo.setsStatus("00");
        objEPurChInfo.setdSource("01");
        objEPurChInfo.setcIEFlag("00");
        objEPurChInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        objEPurChInfo.setCreateTime(new Date());
        objEPurChInfo.setRemark(objEPurChInfo.getPurChCode());
        purChDAO.InsertPurChInfo(objEPurChInfo);

        List<SavePurchInfoReq00PurMa> savePurchInfoReq00PurMas = new ArrayList<SavePurchInfoReq00PurMa>();
        Map<String,SavePurchInfoReq00PurMa> map = new HashMap<String,SavePurchInfoReq00PurMa>();
        savePurchInfoReq00PurMas.addAll(argSavePurchInfoReq00.getPurMaInfo());
        for(SavePurchInfoReq00PurMa savePurchInfoReq00PurMa : savePurchInfoReq00PurMas){
            if(map.containsKey(savePurchInfoReq00PurMa.getMaCode())){
                map.get(savePurchInfoReq00PurMa.getMaCode()).setNum(map.get(savePurchInfoReq00PurMa.getMaCode()).getNum() + savePurchInfoReq00PurMa.getNum());
            }else{
                map.put(savePurchInfoReq00PurMa.getMaCode(),savePurchInfoReq00PurMa);
            }
        }

        for(String key : map.keySet()){
            SavePurchInfoReq00PurMa obj = map.get(key);
            if(obj.getNum()<=0){
                throw new SystemException("xxxx","新增失败,"+obj.getMaCode()+"的数量不能小于等于零");
            }

            PurChDtlInfo objEPurChDtlInfo=new PurChDtlInfo();
            objEPurChDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objEPurChDtlInfo.setPurChGd(objEPurChInfo.getGuid());
            if(!"".equals(argSavePurchInfoReq00.getPurChCode())) {
                objEPurChDtlInfo.setPurChCode(argSavePurchInfoReq00.getPurChCode());
            }else{
                objEPurChDtlInfo.setPurChCode(SCode);
            }
            objEPurChDtlInfo.setNum(obj.getNum());
            objEPurChDtlInfo.setArrivalTime(DateUtil.format2(argSavePurchInfoReq00.getArrivalTime()));

            objEPurChDtlInfo.setScanNum(0.00f);
            objEPurChDtlInfo.setUnitName(obj.getUnitName());
            objEPurChDtlInfo.setaFeed(obj.getAFeed());

            MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(obj.getMaVerRd());
            if(objEMaVerInfo!=null){
                objEPurChDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
            }
            StoreInfo objEStoreInfo=storeDAO.SelectByRuid(obj.getStoreRd());
            if(objEStoreInfo!=null){
                objEPurChDtlInfo.setStoreGd(objEStoreInfo.getGuid());
            }
            objEPurChDtlInfo.setcIEFlag("00");
            objEPurChDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            objEPurChDtlInfo.setCreateTime(new Date());
            purChDtlDAO.InsertPurChDtlInfo(objEPurChDtlInfo);
        }

        PurChInfo purChInfo=purChDAO.SelectByGuid(objEPurChInfo.getGuid());

        objESavePurchInfoResD.setPurChRd(purChInfo.getRuid());
        objESavePurchInfoResD.setPurChCode(purChInfo.getPurChCode());

        objESavePurchInfoResB.setData(objESavePurchInfoResD);
        objESavePurchInfoRes.setBody(objESavePurchInfoResB);
        return objESavePurchInfoRes;
    }

    //根据采购单任意字段查询采购单明细信息
    public GetAllPDlInfoRes QALLGetAllPDlInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllPDlInfoRes objEGetAllPDlInfoRes=new GetAllPDlInfoRes();
        GetAllPDlInfoResB objEGetAllPDlInfoResB=new GetAllPDlInfoResB();
        List<GetAllPDlInfoResD> objEGetAllPDlInfoResDs=new ArrayList<GetAllPDlInfoResD>();

        List<PurChDtlInfo> objEPurChDtlInfos=baseIService.QALLBaseInfo(PurChDtlDAO.class, "SelectPurChDtlInfo",
                argInitDataFields, argPageInfo);
        if(objEPurChDtlInfos!=null||objEPurChDtlInfos.size()!=0){
            for(PurChDtlInfo obj:objEPurChDtlInfos){
                GetAllPDlInfoResD objGetAllPDlInfoResD=new GetAllPDlInfoResD();
                objGetAllPDlInfoResD.setPurChDlRd(obj.getRuid());
                objGetAllPDlInfoResD.setNum(obj.getNum());
                objGetAllPDlInfoResD.setPurChCode(obj.getPurChCode());
                objGetAllPDlInfoResD.setArrivalTime(DateUtil.format(obj.getArrivalTime()));
                objGetAllPDlInfoResD.setScanNum(obj.getScanNum());
                objGetAllPDlInfoResD.setAFeed(obj.getaFeed());
                objGetAllPDlInfoResD.setUnitName(obj.getUnitName());
                MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                if(objEMaVerInfo!=null){
                    objGetAllPDlInfoResD.setMaVerRd(objEMaVerInfo.getRuid());
                    objGetAllPDlInfoResD.setMaCode(objEMaVerInfo.getMaterialCode());
                    objGetAllPDlInfoResD.setMaName(objEMaVerInfo.getMaterialName());
                    //查询明细的物料描述
                    MaterialInfo materialInfo=maVerDAO.SelectMaverAndMater(objEMaVerInfo.getMaGd());
                    if(materialInfo==null&&"".equals(materialInfo)){
                       objGetAllPDlInfoResD.setMaDes("");
                    }else {
                        if(materialInfo.getMaterialDes()==null||"".equals(materialInfo.getMaterialDes())){
                            objGetAllPDlInfoResD.setMaDes("");
                        }else {
                            objGetAllPDlInfoResD.setMaDes(materialInfo.getMaterialDes());
                        }
                    }
                }else{
                    continue;
                }

                StoreInfo objEStoreInfo=storeDAO.SelectByStoreGd(obj.getStoreGd());
                GetAllPDlInfoResDStore objEGetAllPDlInfoResDStore=new GetAllPDlInfoResDStore();
                if(objEStoreInfo!=null){
                    objEGetAllPDlInfoResDStore.setStoreRd(objEStoreInfo.getRuid());
                    objEGetAllPDlInfoResDStore.setStoreName(objEStoreInfo.getStoreName());
                    objGetAllPDlInfoResD.setStoreInfo(objEGetAllPDlInfoResDStore);
                }else {
                    objGetAllPDlInfoResD.setStoreInfo(null);
                }

                objEGetAllPDlInfoResDs.add(objGetAllPDlInfoResD);
            }
        }
        objEGetAllPDlInfoResB.setData(objEGetAllPDlInfoResDs);
        objEGetAllPDlInfoRes.setBody(objEGetAllPDlInfoResB);
        return objEGetAllPDlInfoRes;
    }

    //获取采购单信息
    @Override
    public GetPurchInfoRes GetGetPurchInfoRes(GetPurchInfoReq00 argGetPurchInfoReq00) {
        GetPurchInfoRes objEGetPurchInfoRes=new GetPurchInfoRes();
        GetPurchInfoResB objEGetPurchInfoResB=new GetPurchInfoResB();
        GetPurchInfoResD objEGetPurchInfoResD=new GetPurchInfoResD();
        List<GetPurchInfoResDPurChDl> objEGetPurchInfoResDPurChDls=new ArrayList<GetPurchInfoResDPurChDl>();

        PurChInfo objEPurChInfo=purChDAO.SelectByRuid(argGetPurchInfoReq00.getPurChRd());
        if(objEPurChInfo==null){
            throw new SystemException("","查询失败，该信息不存在");
        }
        List<PurChDtlInfo> objEPurChDtlInfo=purChDtlDAO.SelectPurChDtlInfoBypurChGd(objEPurChInfo.getGuid());
        if(objEPurChDtlInfo!=null||objEPurChDtlInfo.size()!=0){
                for(PurChDtlInfo obj:objEPurChDtlInfo){
                    objEGetPurchInfoResD.setArrivalTime(DateUtil.format(obj.getArrivalTime()));
                    GetPurchInfoResDPurChDl bjEGetPurchInfoResDPurChDl=new GetPurchInfoResDPurChDl();
                    bjEGetPurchInfoResDPurChDl.setPurChDlRd(obj.getRuid());
                    bjEGetPurchInfoResDPurChDl.setNum(obj.getNum());
                    bjEGetPurchInfoResDPurChDl.setAFeed(obj.getaFeed());
                    bjEGetPurchInfoResDPurChDl.setUnitName(obj.getUnitName());

                    GetPurchInfoResDPurChDlMa objEGetPurchInfoResDPurChDlMa=new GetPurchInfoResDPurChDlMa();
                    MaVerInfo objEMaVerInfo=maVerDAO.SelectMaverInfo(obj.getMaVerGd());
                    if(objEMaVerInfo!=null){
                        objEGetPurchInfoResDPurChDlMa.setMaVerRd(objEMaVerInfo.getRuid());
                        objEGetPurchInfoResDPurChDlMa.setMaName(objEMaVerInfo.getMaterialName());
                        objEGetPurchInfoResDPurChDlMa.setMaCode(objEMaVerInfo.getMaterialCode());
                        //查询明细的物料描述
                        MaterialInfo materialInfo=maVerDAO.SelectMaverAndMater(objEMaVerInfo.getMaGd());
                        if(materialInfo==null&&"".equals(materialInfo)){
                            objEGetPurchInfoResDPurChDlMa.setMaDes("");
                        }else {
                            if(materialInfo.getMaterialDes()==null||"".equals(materialInfo.getMaterialDes())){
                                objEGetPurchInfoResDPurChDlMa.setMaDes("");
                            }else {
                                objEGetPurchInfoResDPurChDlMa.setMaDes(materialInfo.getMaterialDes());
                            }
                        }

                    }
                    bjEGetPurchInfoResDPurChDl.setMaInfo(objEGetPurchInfoResDPurChDlMa);

                    GetPurchInfoResDPurChDlStore objEGetPurchInfoResDPurChDlStore=new GetPurchInfoResDPurChDlStore();
                    StoreInfo storeInfo=storeDAO.SelectByguid(obj.getStoreGd());
                    if(storeInfo!=null){
                        objEGetPurchInfoResDPurChDlStore.setStoreRd(storeInfo.getRuid());
                        objEGetPurchInfoResDPurChDlStore.setStoreName(storeInfo.getStoreName());
                        bjEGetPurchInfoResDPurChDl.setStoreInfo(objEGetPurchInfoResDPurChDlStore);
                    }else {

                        bjEGetPurchInfoResDPurChDl.setStoreInfo(null);
                    }

                    objEGetPurchInfoResDPurChDls.add(bjEGetPurchInfoResDPurChDl);
                }
        }

        if(objEPurChInfo==null){
            throw new SystemException("xx","查询失败，该信息不存在");
        }
        objEGetPurchInfoResD.setStatus(objEPurChInfo.getStatus());
        objEGetPurchInfoResD.setSStatus(objEPurChInfo.getsStatus());
        objEGetPurchInfoResD.setDSource(objEPurChInfo.getdSource());
        objEGetPurchInfoResD.setPurChRd(objEPurChInfo.getRuid());
        objEGetPurchInfoResD.setPurChCode(objEPurChInfo.getPurChCode());
        objEGetPurchInfoResD.setBuyer(objEPurChInfo.getBuyer());
        objEGetPurchInfoResD.setSupCode(objEPurChInfo.getSupplierCode());
        objEGetPurchInfoResD.setSupName(objEPurChInfo.getSupplierName());
        objEGetPurchInfoResD.setCreator(objEPurChInfo.getCreator());

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        objEGetPurchInfoResD.setCreateTime(sdf.format(objEPurChInfo.getCreateTime()));

        if(objEPurChInfo.getLastModifyMan()==null){
            objEGetPurchInfoResD.setLastModifyMan("");
        }
        else {
            objEGetPurchInfoResD.setLastModifyMan(objEPurChInfo.getLastModifyMan());
        }



        SimpleDateFormat sdf1 =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(objEPurChInfo.getLastModifyMan()==null){
            objEGetPurchInfoResD.setLastModifyTime("");
        }
        else {
            objEGetPurchInfoResD.setLastModifyTime(sdf1.format(objEPurChInfo.getLastModifyTime()));
        }

        objEGetPurchInfoResD.setRemark(objEPurChInfo.getRemark());
        objEGetPurchInfoResD.setPurChDlInfo(objEGetPurchInfoResDPurChDls);

        objEGetPurchInfoResB.setData(objEGetPurchInfoResD);
        objEGetPurchInfoRes.setBody(objEGetPurchInfoResB);
        return objEGetPurchInfoRes;
    }


    //删除采购单信息
    @Override
    public SavePurchInfoRes RmSavePurchInfoRes(SavePurchInfoReq01 argSavePurchInfoReq01) {
        SavePurchInfoRes objESavePurchInfoRes=new SavePurchInfoRes();
        SavePurchInfoResB objESavePurchInfoResB=new SavePurchInfoResB();

        PurChInfo objEPurChInfo=purChDAO.SelectByRuid(argSavePurchInfoReq01.getPurChRd());
        if(objEPurChInfo==null){
            throw new SystemException("","删除失败，查询失败");
        }
        if("00".equals(objEPurChInfo.getdSource())){
            throw new SystemException("","外部数据不能删除");
        }
        if("00".equals(objEPurChInfo.getStatus())){
            List<PurChDtlInfo> objEPurChDtlInfos=purChDtlDAO.SelectPurChDtlInfoBypurChGd(objEPurChInfo.getGuid());
            if(objEPurChDtlInfos==null||objEPurChDtlInfos.size()<=0){
                throw new SystemException("","删除失败，查询明细失败");
            }
            for(PurChDtlInfo obj:objEPurChDtlInfos){
                if(purChDtlDAO.deletePurChDtlInfo(obj.getRuid())<=0){
                    throw new SystemException("","删除明细失败");
                }
            }
            if(purChDAO.DeletePurChInfo(objEPurChInfo.getRuid())<=0){
                throw new SystemException("","删除失败");
            }

        }else {
            throw new SystemException("","删除失败，此单子处于进行中、已完成、已取消中");
        }



        objESavePurchInfoRes.setBody(objESavePurchInfoResB);
        return objESavePurchInfoRes;
    }

    //修改采购单信息
    @Override
    public SavePurchInfoRes ModSavePurchInfoRes(SavePurchInfoReq02 argSavePurchInfoReq02) {
        SavePurchInfoRes objESavePurchInfoRes=new SavePurchInfoRes();
        SavePurchInfoResB objESavePurchInfoResB=new SavePurchInfoResB();

        PurChInfo objEPurChInfo=purChDAO.SelectByRuid(argSavePurchInfoReq02.getPurChRd());
        if(objEPurChInfo==null){
            throw  new SystemException("","更新失败，该信息不存在");
        }
        if("00".equals(objEPurChInfo.getdSource())){
            throw new SystemException("","外部数据不能更新");
        }
        if(("00".equals(objEPurChInfo.getStatus())||("01".equals(objEPurChInfo.getStatus()))) && ("00".equals(objEPurChInfo.getsStatus()))){
            if("".equals(argSavePurchInfoReq02.getBuyer())||argSavePurchInfoReq02.getBuyer()==null){
                throw new SystemException("","更新失败，采购员不能为空");
            }

            if("".equals(argSavePurchInfoReq02.getPurMaInfo())||argSavePurchInfoReq02.getPurMaInfo().size()<=0){
                throw new SystemException("","更新失败,明细不能为空");
            }


            List<PurChDtlInfo> objEPurChDtlInfos=purChDtlDAO.SelectPurChDtlInfoBypurChGd(objEPurChInfo.getGuid());

            if(objEPurChDtlInfos!=null||objEPurChDtlInfos.size()!=0){
                for(SavePurchInfoReq02PurMa obj:argSavePurchInfoReq02.getPurMaInfo()){
                    boolean flag = true;
                    for(int i=0;i<objEPurChDtlInfos.size();i++){
                        if(obj.getPurChDlRd()==objEPurChDtlInfos.get(i).getRuid()){
                            //更新
                            PurChDtlInfo objEPurChDtlInfo1=new PurChDtlInfo();
                            objEPurChDtlInfo1.setRuid(obj.getPurChDlRd());

                            MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(obj.getMaVerRd());
                            if(objEMaVerInfo!=null){
                                objEPurChDtlInfo1.setMaVerGd(objEMaVerInfo.getGuid());
                            }

                            objEPurChDtlInfo1.setArrivalTime(DateUtil.format2(argSavePurchInfoReq02.getArrivalTime()));


                            objEPurChDtlInfo1.setUnitName(obj.getUnitName());
                            objEPurChDtlInfo1.setaFeed(obj.getAFeed());
                            StoreInfo objEStoreInfo=storeDAO.SelectByRuid(obj.getStoreRd());
                            if(objEStoreInfo!=null){
                                objEPurChDtlInfo1.setStoreGd(objEStoreInfo.getGuid());
                            }
                            objEPurChDtlInfo1.setLastModifyMan(CommonUtils.readUser().getRealName());
                            objEPurChDtlInfo1.setLastModifyTime(new Date());
                            //判断传过来的数据不能小于等于零
                            if(obj.getNum()<=0){
                                throw new SystemException("","更新失败，"+obj.getMaName()+"的数量不能小于等于零");
                            }
                            if(objEPurChDtlInfos.get(i).getScanNum()==0.0f){
                                objEPurChDtlInfo1.setNum(obj.getNum());

                                objEPurChDtlInfo1.setLastModifyMan(CommonUtils.readUser().getRealName());
                                objEPurChDtlInfo1.setLastModifyTime(new Date());
                                if(purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo1)<=0){
                                    throw new SystemException("","更新明细失败");
                                }
                            }else {
                                //这是原材料
                              /*  if(objEPurChDtlInfos.get(i).getNum()==objEPurChDtlInfos.get(i).getUnCInCNum()){*/
                                    if(obj.getNum()>=objEPurChDtlInfos.get(i).getScanNum()){
                                        float aa=(obj.getNum()-objEPurChDtlInfos.get(i).getScanNum())+objEPurChDtlInfos.get(i).getScanNum();
                                        objEPurChDtlInfo1.setNum(aa);
                                        /*objEPurChDtlInfo1.setUnCInCNum(aa);*/
                                    }else {
                                        throw new SystemException("","更新失败，"+obj.getMaName()+"的数量已经入库了,当前入库数量为:"+objEPurChDtlInfos.get(i).getScanNum());
                                    }
                               /* }else {
                                    //这是退料
                                    if((obj.getNum()>=objEPurChDtlInfos.get(i).getScanNum())&&(obj.getNum()>=objEPurChDtlInfos.get(i).getUnCInCNum())){

                                    }*/

                           /* //更新的数据不能大于等于已扫描数量
                            if(obj.getNum()>objEPurChDtlInfos.get(i).getScanNum()){
                                throw new SystemException("","更细失败，该数量不能大于:"+objEPurChDtlInfos.get(i).getScanNum());
                            }else {
                                float addnumb=objEPurChDtlInfos.get(i).getNum()-obj.getNum();
                                objEPurChDtlInfo1.setUnCInCNum(objEPurChDtlInfos.get(i).getUnCInCNum()+addnumb);
                                objEPurChDtlInfo1.setNum(obj.getNum());
                                if(purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo1)<=0){
                                    throw new SystemException("","更新明细失败");
                                }
                            }*/

                                    //数量
                            /*if(obj.getNum()>objEPurChDtlInfos.get(i).getNum()){
                                float addnumb=obj.getNum()-objEPurChDtlInfos.get(i).getNum();
                                objEPurChDtlInfo1.setUnCInCNum(objEPurChDtlInfos.get(i).getUnCInCNum()+addnumb);
                                objEPurChDtlInfo1.setNum(obj.getNum());
                                if(purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo1)<=0){
                                    throw new SystemException("","更新明细失败");
                                }
                            }else {
                                float value=obj.getNum()-(objEPurChDtlInfos.get(i).getNum()-objEPurChDtlInfos.get(i).getUnCInCNum());

                                float unValue=objEPurChDtlInfos.get(i).getNum()-objEPurChDtlInfos.get(i).getUnCInCNum();
                                if(obj.getNum()>=unValue){
                                    //更新
                                    objEPurChDtlInfo1.setUnCInCNum(value);
                                    objEPurChDtlInfo1.setNum(obj.getNum());
                                    if(purChDtlDAO.UpdatePurChDtlInfo(objEPurChDtlInfo1)<=0){
                                        throw new SystemException("","更新明细失败");
                                    }
                                }
                                else {
                                    throw new SystemException("Xxx","更新失败，"+obj.getMaName()+"+数量不能低于"+unValue);
                                }*/

                                //}
                            }



                            objEPurChDtlInfos.remove(i);
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        PurChDtlInfo objEPurChDtlInfo = new PurChDtlInfo();

                        objEPurChDtlInfo.setGuid(CommonUtils.getRandomNumber());
                        objEPurChDtlInfo.setPurChGd(objEPurChInfo.getGuid());
                        objEPurChDtlInfo.setPurChCode(objEPurChInfo.getPurChCode());
                        MaVerInfo objEMaVerInfo=maVerDAO.SelectByRuid(obj.getMaVerRd());
                        if(objEMaVerInfo!=null){
                            objEPurChDtlInfo.setMaVerGd(objEMaVerInfo.getGuid());
                        }
                        if(obj.getNum()<=0){
                            throw new SystemException("xxxx","更新失败,"+obj.getMaCode()+"d的数量不能小于等于零");
                        }
                        objEPurChDtlInfo.setNum(obj.getNum());
                        objEPurChDtlInfo.setArrivalTime(DateUtil.format2(argSavePurchInfoReq02.getArrivalTime()));

                        objEPurChDtlInfo.setScanNum(0.00f);
                        //objEPurChDtlInfo.setUnCInCNum(obj.getNum());
                        objEPurChDtlInfo.setUnitName(obj.getUnitName());
                        objEPurChDtlInfo.setaFeed(obj.getAFeed());
                        objEPurChDtlInfo.setcIEFlag("00");

                        StoreInfo objEStoreInfo=storeDAO.SelectByRuid(obj.getStoreRd());
                        if(objEStoreInfo!=null){
                            objEPurChDtlInfo.setStoreGd(objEStoreInfo.getGuid());
                        }
                        objEPurChDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                        objEPurChDtlInfo.setCreateTime(new Date());
                        purChDtlDAO.InsertPurChDtlInfo(objEPurChDtlInfo);
                    }
                }
                for(PurChDtlInfo obj:objEPurChDtlInfos){
                    if(purChDtlDAO.deletePurChDtlInfo(obj.getRuid())<=0){
                        throw new SystemException("", "删除明细失败");
                    }
                }
                //更新主
                objEPurChInfo.setBuyer(argSavePurchInfoReq02.getBuyer());

                objEPurChInfo.setSupplierCode(argSavePurchInfoReq02.getSupCode());
                objEPurChInfo.setSupplierName(argSavePurchInfoReq02.getSupName());
                objEPurChInfo.setRemark(argSavePurchInfoReq02.getRemark());
                objEPurChInfo.setLastModifyTime(new Date());
                objEPurChInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                if(purChDAO.UpdatePurChInfoByRuid(objEPurChInfo)<=0){
                    throw new SystemException("","更新失败");
                }
            }
        }else {
            throw new  SystemException("","修改失败，此单子处于已完成或者已下达、已取消状态");
        }




        objESavePurchInfoRes.setBody(objESavePurchInfoResB);
        return objESavePurchInfoRes;
    }

    //下达采购单
    @Override
    public SavePurchInfoRes ModSavePurchInfoRes03(SavePurchInfoReq03 argSavePurchInfoReq03) {
        SavePurchInfoRes objESavePurchInfoRes=new SavePurchInfoRes();
        SavePurchInfoResB objESavePurchInfoResB=new SavePurchInfoResB();

        PurChInfo objEPurChInfo=purChDAO.SelectByRuid(argSavePurchInfoReq03.getPurChRd());
        if("01".equals(objEPurChInfo.getsStatus())){
            throw new SystemException("","采购单已下达,不能重复下达");
        }
        if("02".equals(objEPurChInfo.getsStatus())){
            throw new SystemException("","采购单已取消,不能进行下达");
        }
        PurChInfo purChInfo=null;
        if(objEPurChInfo!=null){
            purChInfo=new PurChInfo();
            purChInfo.setRuid(objEPurChInfo.getRuid());
            purChInfo.setsStatus("01");

            purChDAO.UpdatePurChInfoByRuid(purChInfo);
        }

        objESavePurchInfoRes.setBody(objESavePurchInfoResB);
        return objESavePurchInfoRes;
    }

    //取消采购单
    @Override
    public SavePurchInfoRes ModSavePurchInfoRes04(SavePurchInfoReq04 argSavePurchInfoReq04) {
        SavePurchInfoRes objESavePurchInfoRes=new SavePurchInfoRes();
        SavePurchInfoResB objESavePurchInfoResB=new SavePurchInfoResB();

        PurChInfo objEPurChInfo=purChDAO.SelectByRuid(argSavePurchInfoReq04.getPurChRd());

        if("00".equals(objEPurChInfo.getsStatus())){
            throw new SystemException("","采购单未下达，不能取消");
        }
        if("02".equals(objEPurChInfo.getsStatus())){
            throw new SystemException("","采购单已取消，不能重复取消");
        }
        PurChInfo purChInfo=null;
        if(objEPurChInfo!=null && "01".equals(objEPurChInfo.getsStatus())){
            purChInfo=new PurChInfo();
            purChInfo.setRuid(objEPurChInfo.getRuid());
            purChInfo.setsStatus("02");

            purChDAO.UpdatePurChInfoByRuid(purChInfo);
        }

        if(objEPurChInfo!=null && "01".equals(objEPurChInfo.getStatus()) && "01".equals(objEPurChInfo.getsStatus())){
            purChInfo=new PurChInfo();
            purChInfo.setRuid(objEPurChInfo.getRuid());
            purChInfo.setsStatus("02");
            purChInfo.setStatus("03");

            purChDAO.UpdatePurChInfoByRuid(purChInfo);
        }

        objESavePurchInfoRes.setBody(objESavePurchInfoResB);
        return objESavePurchInfoRes;
    }
}
