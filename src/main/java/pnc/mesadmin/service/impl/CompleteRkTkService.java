package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.SaveRKInfo.SaveRKInfoRes;
import pnc.mesadmin.dto.SaveRKInfo.SaveRKInfoResB;
import pnc.mesadmin.dto.SaveRKInfo.SaveRKInfoResD;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.CompleteRkTkIService;

import javax.annotation.Resource;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：强制完成入库任务Service
 * 创建人：王怀龙
 * 创建时间：2017-08-11
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class CompleteRkTkService implements CompleteRkTkIService {
  //  @Resource
    //RkTkDtlDAO objRkTkDtlDAO;      //任务单明细Dao
    @Resource
    RkTkIDAO objRkTkIDAO;           //扫描明细
    @Resource
    RkTkDAO objRkTkDAO; //入库任务信息
    @Resource
    BDAO objBatchDao;
    @Resource
    MaVerDAO maVerDAO;
    @Resource
    IQCBInfoDAO objIqcbInfoDAO;
    @Resource
    UnitDAO objUnitDAO;
    @Resource
    InstockDAO objInstockDAO;
    @Resource
    InstockDtlDAO objInstockDtlDAO;


    /*入库单强制完成*/
//    public  void  CompleteRkTk(String RkCode){
    public SaveRKInfoRes CompleteRkTk(int argRkRuid){
        SaveRKInfoRes objESaveRKInfoRes=new SaveRKInfoRes();
        SaveRKInfoResB objESaveRKInfoResB=new SaveRKInfoResB();
        SaveRKInfoResD objESaveRKInfoResD=new SaveRKInfoResD();

        //1. 检验入库任务信息
        RkTkInfo objERkTkInfo =  objRkTkDAO.SelectRKTkInfoByruid(argRkRuid);
        //1.1检验当前入库任务单是否存在
        if (null == objERkTkInfo) {
            throw new SystemException("MG_MES80013100001F", "入库单信息为空!");
        }
        if ("02".equals(objERkTkInfo.getExStatus())) {
            throw new SystemException("", "当前入库单已完成!");
        } else if ("03".equals(objERkTkInfo.getExStatus())) {

            throw new SystemException("", "当前入库单已取消!");
        }

        //List<RkTkDtlInfo> objRkTkDtlInfos = objRkTkDtlDAO.SelectRkTkDtlInfoByrkCode(objERkTkInfo.getRkCode());
   /*     for(int i=0;i<objRkTkDtlInfos.size();i++){
                //入库任务明细信息
                RkTkDtlInfo objRkTkDtlInfo2 = objRkTkDtlInfos.get(i);
             *//*   //查询入库单信息
                RkTkInfo objRkTkInfo = objRkTkDAO.SelectRKTkInfoByguid(objRkTkDtlInfo2.getRkTkGd());
                if (null == objRkTkInfo) {
                    throw new SystemException("MG_MES80013100001F", "入库单信息为空!");
                }*//*
                //查询物料版本信息
                MaVerInfo objEMaVerInfo = maVerDAO.SelectMaverInfo(objRkTkDtlInfo2.getMaVerGd());
                if(null == objEMaVerInfo){
                    throw new SystemException("MG_MES80013100005F","物料版本信息为空");
                }

                //生成库存标识
                String strInstockInfoGuid  = CommonUtils.getRandomNumber();

                InstockInfo objEInstockInfo  = objInstockDAO.SelectInstockInfoListByMaVerGd(objEMaVerInfo.getGuid());
                if(objEInstockInfo == null){
                    InstockInfo objInstockInfo = new InstockInfo();
                    objInstockInfo.setGuid(strInstockInfoGuid);
                    objInstockInfo.setMaVerGd(objRkTkDtlInfo2.getMaVerGd());
                    objInstockInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objInstockInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objInstockInfo.setMaterialType(objEMaVerInfo.getMaterialType());
                    objInstockInfo.setVersion(objEMaVerInfo.getVersion());
                    objInstockInfo.setNum(objRkTkDtlInfo2.getScanNum());
                    objInstockInfo.setCreator((String) SecurityUtils.getSubject().getPrincipal());
                    objInstockInfo.setCreateTime(new Date());

                    //任务单完成情况下,将任务单信息数据依次插入库存信息
                    objInstockDAO.InsertInStock(objInstockInfo);
                }else{
                    strInstockInfoGuid = objEInstockInfo.getGuid();
                    objEInstockInfo.setNum(objEInstockInfo.getNum()+objRkTkDtlInfo2.getScanNum());
                    objEInstockInfo.setLastModifyMan((String) SecurityUtils.getSubject().getPrincipal());
                    objEInstockInfo.setLastModifyTime(new Date());
                    objInstockDAO.UpdateInStockInfo(objEInstockInfo);
                }


                //扫描信息明细
                List<RkDtlInfo> RkDtlInfos = objRkTkIDAO.SelectRkDtlInfoByrkTkDtlGd(objRkTkDtlInfo2.getGuid());
                if(null == RkDtlInfos){
                    throw new SystemException("","入库扫描信息为空");
                }
                for(int n=0;n<RkDtlInfos.size();n++){
                    //根据任务单扫描明细得到对应的批次信息
                    BInfo objEBInfo2 = objBatchDao.selectBatchInfoByBatch(RkDtlInfos.get(n).getBatch());
                    if(null == objEBInfo2){
                        throw new SystemException("MG_MES80013100002F","批次"+objEBInfo2.getBatch()+"信息为空");
                    }

                    UnitInfo objEUnitInfo = objUnitDAO.SelectByGuid(objEBInfo2.getUnitGd());
                    if(null == objEUnitInfo){
                        throw new SystemException("MG_MES80013100006F","批次"+objEBInfo2.getBatch()+"物料单位信息为空");
                    }

                    InstockDtlInfo objInstockDtlInfo = new InstockDtlInfo();
                    objInstockDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    objInstockDtlInfo.setInsGd(strInstockInfoGuid);
                    objInstockDtlInfo.setOrderCode(objRkTkDtlInfo2.getRkCode());
                    objInstockDtlInfo.setOrderType("02");
                    objInstockDtlInfo.setMaVerGd(objRkTkDtlInfo2.getMaVerGd());
                    objInstockDtlInfo.setMaterialCode(objEMaVerInfo.getMaterialCode());
                    objInstockDtlInfo.setMaterialName(objEMaVerInfo.getMaterialName());
                    objInstockDtlInfo.setBatch(RkDtlInfos.get(n).getBatch());
                    objInstockDtlInfo.setStoreGd(objERkTkInfo.getStoreGd());
                    objInstockDtlInfo.setCreateTime(RkDtlInfos.get(n).getProductDate());
                    objInstockDtlInfo.setUnitName(objEUnitInfo.getUnitName());
                    objInstockDtlInfo.setCreator(RkDtlInfos.get(n).getCreator());
                    objInstockDtlInfo.setCreateTime(new Date());

                    //将插入库存信息以后将对应的任务单明细信息插入库存明细表中
                    objInstockDtlDAO.InsertInstockDtlInfo(objInstockDtlInfo);

                    //将批次在库状态修改为在库
                    objEBInfo2.setInStockStatus("00");
                    if(objBatchDao.UpdateBatchInfoByRuid(objEBInfo2) <= 0){
                        throw new SystemException("","批次信息更新失败!");
                    }
                    objEBInfo2 = null;
                }

                //更新入库单信息
            objERkTkInfo.setExecor((String) SecurityUtils.getSubject().getPrincipal());
            objERkTkInfo.setExecTime(new Date());
                if(i == objRkTkDtlInfos.size()-1){
                    objERkTkInfo.setFinishor((String) SecurityUtils.getSubject().getPrincipal());
                    objERkTkInfo.setFinishTime(new Date());
                    objERkTkInfo.setExStatus("02");
                }
                if( objRkTkDAO.UpdateRKTkInfo(objERkTkInfo) <= 0){
                    throw new SystemException("","批次信息更新失败!");
                }

            }*/
        objESaveRKInfoResB.setData(objESaveRKInfoResD);
        objESaveRKInfoRes.setBody(objESaveRKInfoResB);
        return objESaveRKInfoRes;
    }
}