package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoReq;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoReqBaInfo;
import pnc.mesadmin.dto.SaveYMInfo.SaveYMInfoResB;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.OnLineMadIService;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zhaochao on 11/6 0006.
 */
@Service
@Transactional
public class OnLineMadService implements OnLineMadIService {

    @Resource
    private PickBatchDAO pickBatchDAO;
    @Resource
    private LineMVDAO lineMVDAO;
    @Resource
    private BDAO bDAO;
    @Resource
    private MaVerDAO maVerDAO;
    @Resource
    private UnitDAO unitDAO;

    @Override
    public SaveYMInfoResB AddYMInfo(SaveYMInfoReq objSaveYMInfoReq) {

        SaveYMInfoResB objSaveYMInfoResB = new SaveYMInfoResB();
        PickBatchInfo pickBatchInfo = null;
        LineMVInfo lineMVInfo = null;
        if(objSaveYMInfoReq.getMOWoCode() == null || "".equals(objSaveYMInfoReq.getMOWoCode().trim())){
            throw new SystemException("00","调出工单不能为空，调拨失败");
        }
        if(objSaveYMInfoReq.getMIWoCode() == null || "".equals(objSaveYMInfoReq.getMIWoCode().trim())){
            throw new SystemException("00","调入工单不能为空，调拨失败");
        }
        if(objSaveYMInfoReq.getMOWoCode().equals(objSaveYMInfoReq.getMIWoCode())){
            throw new SystemException("00","调入工单和调出工单不能相同，调拨失败");
        }
        //有工单批次调拨
        if("00".equals(objSaveYMInfoReq.getDPType())){
            //
            for(SaveYMInfoReqBaInfo objSaveYMInfoReqBaInfo: objSaveYMInfoReq.getBaInfo()) {
                //根据工单号和批次获取余料信息
                pickBatchInfo = pickBatchDAO.SelectByAB(objSaveYMInfoReq.getMOWoCode(),objSaveYMInfoReqBaInfo.getBatch());
                if(pickBatchInfo != null) {
                    pickBatchInfo.setAssCode(objSaveYMInfoReq.getMIWoCode());
                    pickBatchInfo.setAssSource("00");
                    pickBatchInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                    pickBatchInfo.setLastModifyTime(new Date());
                    if(pickBatchDAO.UpdatePickBatchInfo(pickBatchInfo) < 1){
                        throw new SystemException("01","更新产线余料信息出现错误，调拨失败");
                    }
                    //保存操作记录
                    lineMVInfo = new LineMVInfo();
                    lineMVInfo.setGuid(CommonUtils.getRandomNumber());
                    lineMVInfo.setfWoCode(objSaveYMInfoReq.getMOWoCode());
                    lineMVInfo.settWoCode(objSaveYMInfoReq.getMIWoCode());
                    lineMVInfo.setBatch(objSaveYMInfoReqBaInfo.getBatch());
                    lineMVInfo.setCreator(CommonUtils.readUser().getRealName());
                    lineMVInfo.setCreateTime(new Date());
                    BInfo bInfo = bDAO.selectBatchInfoByBatch(objSaveYMInfoReqBaInfo.getBatch());
                    if(bInfo != null) {
                        lineMVInfo.setNum(bInfo.getCanNum());
                        lineMVInfo.setMaVerGd(bInfo.getMaVerGd());
                        MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                        if(maVerInfo != null){
                            lineMVInfo.setMaterialName(maVerInfo.getMaterialName());
                            lineMVInfo.setMaterialCode(maVerInfo.getMaterialCode());
                            UnitInfo unitInfo = unitDAO.SelectByGuid(maVerInfo.getUnitGd());
                            if(unitInfo != null){
                                lineMVInfo.setUnitName(unitInfo.getUnitName());
                            }else{
                                throw new SystemException("02","批次:" + objSaveYMInfoReqBaInfo + "对应的物料单位不存在");
                            }
                            if (lineMVDAO.InsertLineMVInfo(lineMVInfo) < 1) {
                                throw new SystemException("03","新增调拨记录失败");
                            }
                        }else{
                            throw new SystemException("04","批次:" + objSaveYMInfoReqBaInfo + "对应的物料不存在");
                        }
                    }else{
                        throw new SystemException("05","批次:" + objSaveYMInfoReqBaInfo + "不存在");
                    }
                }else{
                    throw new SystemException("06","产线余料信息不存在");
                }
            }
            objSaveYMInfoResB.setMsgCode("0x00000");
            objSaveYMInfoResB.setMsgDes("成功");
        }
        //无工单物料批次调拨
        else if("01".equals(objSaveYMInfoReq.getDPType())){
            for(SaveYMInfoReqBaInfo objSaveYMInfoReqBaInfo: objSaveYMInfoReq.getBaInfo()) {
                //根据工单号和批次获取余料信息
                pickBatchInfo = pickBatchDAO.SelectPickBatchInfoByBatch(objSaveYMInfoReqBaInfo.getBatch());
                if(pickBatchInfo != null) {
                    pickBatchInfo.setAssCode("");
                    pickBatchInfo.setAssSource("01");
                    pickBatchInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
                    pickBatchInfo.setLastModifyTime(new Date());
                    if(pickBatchDAO.UpdatePickBatchInfo(pickBatchInfo) < 1){
                        throw new SystemException("07","更新产线余料信息出现错误，调拨失败");
                    }
                }else{
                    throw new SystemException("08","产线余料信息不存在");
                }
                //保存操作记录
                lineMVInfo = new LineMVInfo();
                lineMVInfo.setGuid(CommonUtils.getRandomNumber());
                lineMVInfo.setfWoCode(objSaveYMInfoReq.getMOWoCode());
                lineMVInfo.settWoCode(objSaveYMInfoReq.getMIWoCode());
                lineMVInfo.setBatch(objSaveYMInfoReqBaInfo.getBatch());
                lineMVInfo.setCreator(CommonUtils.readUser().getRealName());
                lineMVInfo.setCreateTime(new Date());
                BInfo bInfo = bDAO.selectBatchInfoByBatch(objSaveYMInfoReqBaInfo.getBatch());
                if(bInfo != null) {
                    lineMVInfo.setNum(bInfo.getCanNum());
                    lineMVInfo.setMaVerGd(bInfo.getMaVerGd());
                    MaVerInfo maVerInfo = maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                    if(maVerInfo != null){
                        lineMVInfo.setMaterialName(maVerInfo.getMaterialName());
                        lineMVInfo.setMaterialCode(maVerInfo.getMaterialCode());
                        UnitInfo unitInfo = unitDAO.SelectByGuid(maVerInfo.getUnitGd());
                        if(unitInfo != null){
                            lineMVInfo.setUnitName(unitInfo.getUnitName());
                        }else{
                            throw new SystemException("02","批次:" + objSaveYMInfoReqBaInfo + "对应的物料单位不存在");
                        }
                        if (lineMVDAO.InsertLineMVInfo(lineMVInfo) < 1) {
                            throw new SystemException("03","新增调拨记录失败");
                        }
                    }else{
                        throw new SystemException("04","批次:" + objSaveYMInfoReqBaInfo + "对应的物料不存在");
                    }
                }else{
                    throw new SystemException("05","批次:" + objSaveYMInfoReqBaInfo + "不存在");
                }
            }
            objSaveYMInfoResB.setMsgCode("0x00000");
            objSaveYMInfoResB.setMsgDes("成功");
        }
        //非法的请求
        else{
            objSaveYMInfoResB.setMsgCode("11");
            objSaveYMInfoResB.setMsgDes("非法请求，拒绝访问");
        }
        return objSaveYMInfoResB;
    }
}
