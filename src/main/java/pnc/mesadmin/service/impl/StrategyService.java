package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.InStrategyDAO;
import pnc.mesadmin.dto.GetAllSgyInfo.GetAllSgyInfoRes;
import pnc.mesadmin.dto.GetAllSgyInfo.GetAllSgyInfoResB;
import pnc.mesadmin.dto.GetAllSgyInfo.GetAllSgyInfoResD;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoReqBD00;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoRes;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoResB;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSgyInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.InStrategyInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.BatchLIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liufuzhi on 2018/1/30.
 */
@Transactional
@Service
public class StrategyService implements BatchLIService.StrategyIService {
    @Resource
    private InStrategyDAO inStrategyDAO;

    @Resource
    private BaseIService baseIService;


    //查询收货策略列表信息
    @Override
    public GetAllSgyInfoRes QALLGetAllSgyInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllSgyInfoRes objEGetAllSgyInfoRes=new GetAllSgyInfoRes();
        GetAllSgyInfoResB objEGetAllSgyInfoResB=new GetAllSgyInfoResB();
        List<GetAllSgyInfoResD> objEGetAllSgyInfoResDs=new ArrayList<GetAllSgyInfoResD>();

        List<InStrategyInfo> objEInStrategyInfo=baseIService.QALLBaseInfo(InStrategyDAO.class, "SelectAllByInStrategyInfo",
                argInitDataFields, argPageInfo);

        if(objEInStrategyInfo!=null||objEInStrategyInfo.size()>0) {

            for (InStrategyInfo obj : objEInStrategyInfo) {
                GetAllSgyInfoResD objGetAllSgyInfoResD = new GetAllSgyInfoResD();
                objGetAllSgyInfoResD.setSgyRd(obj.getRuid());
                objGetAllSgyInfoResD.setSgyName(obj.getStrategyName());

                objEGetAllSgyInfoResDs.add(objGetAllSgyInfoResD);
            }
        }
        objEGetAllSgyInfoResB.setData(objEGetAllSgyInfoResDs);
        objEGetAllSgyInfoRes.setBody(objEGetAllSgyInfoResB);

        return objEGetAllSgyInfoRes;
    }

    //查询收货策略信息
    @Override
    public GetSgyInfoRes GetGetSgyInfoRes(GetSgyInfoReqBD00 argGetSgyInfoReqBD00) {
        GetSgyInfoRes objEGetSgyInfoRes=new GetSgyInfoRes();
        GetSgyInfoResB objEGetSgyInfoResB=new GetSgyInfoResB();
        GetSgyInfoResD objEGetSgyInfoResD=new GetSgyInfoResD();

        //查询收货策略信息
        InStrategyInfo objInStrategyInfo= inStrategyDAO.SelectAllByRuid(argGetSgyInfoReqBD00.getSgyRd());

        if(objInStrategyInfo!=null) {
            objEGetSgyInfoResD.setSgyRd(objInStrategyInfo.getRuid());
            objEGetSgyInfoResD.setSgyName(objInStrategyInfo.getStrategyName());
            objEGetSgyInfoResD.setStatus(objInStrategyInfo.getStatus());
            objEGetSgyInfoResD.setActOn(objInStrategyInfo.getActOn());
            objEGetSgyInfoResD.setIsDef(objInStrategyInfo.getIsDef());
            objEGetSgyInfoResD.setPartRev(objInStrategyInfo.getPartRev());
            objEGetSgyInfoResD.setSInCome(objInStrategyInfo.getsInCome());
            objEGetSgyInfoResD.setCreator(objInStrategyInfo.getCreator());
            objEGetSgyInfoResD.setCreateTime(DateUtil.format(objInStrategyInfo.getCreateTime()));
            objEGetSgyInfoResD.setLastModifyMan(objInStrategyInfo.getLastModifyMan());
            objEGetSgyInfoResD.setLastModifyTime(DateUtil.format(objInStrategyInfo.getLastModifyTime()));
            objEGetSgyInfoResD.setRemark(objInStrategyInfo.getRemark());
        }

        objEGetSgyInfoResB.setData(objEGetSgyInfoResD);
        objEGetSgyInfoRes.setBody(objEGetSgyInfoResB);
        return objEGetSgyInfoRes;
    }

    //新增收货策略信息
    @Override
    public SaveSgyInfoRes AddSaveSgyInfo(SaveSgyInfoReqBD00 argSaveSgyInfoReqBD00) {
        SaveSgyInfoRes objESaveSgyInfoRes=new SaveSgyInfoRes();
        SaveSgyInfoResB objESaveSgyInfoResB=new SaveSgyInfoResB();
        SaveSgyInfoResD objESaveSgyInfoResD=new SaveSgyInfoResD();

        InStrategyInfo objEInStrategyInfo=new InStrategyInfo();

        //校验收货策略名称
        List<InStrategyInfo> objEInStrategyInfos= inStrategyDAO.SelectAllByActOn(argSaveSgyInfoReqBD00.getActOn());
        if(objEInStrategyInfos.size()!=0) {
            for (InStrategyInfo obj : objEInStrategyInfos) {
                if (argSaveSgyInfoReqBD00.getSgyName().equals(obj.getStrategyName())) {
                    throw new SystemException("MG0006F", "作用于该收货策略名称已存在");
                }
            }
        }

        //当前有默认策略，并新增一个默认策略时更新原来的默认策略
        if(objEInStrategyInfos.size()!=0 &&"00".equals(argSaveSgyInfoReqBD00.getIsDef())) {
            InStrategyInfo objEsInStrategyInfo = inStrategyDAO.SelectisDefANDactOn(argSaveSgyInfoReqBD00.getIsDef(), argSaveSgyInfoReqBD00.getActOn());

            if (objEsInStrategyInfo != null) {
                objEInStrategyInfo.setRuid(objEsInStrategyInfo.getRuid());
                objEInStrategyInfo.setIsDef("01");

                if(inStrategyDAO.UpdateIInStrategyInfo(objEInStrategyInfo)<=0){
                    throw new SystemException("MG_MES4001312030002F","更新策略信息失败！");
                }
            }
        }

        objEInStrategyInfo.setGuid(CommonUtils.getRandomNumber());
        objEInStrategyInfo.setStrategyName(argSaveSgyInfoReqBD00.getSgyName());
        objEInStrategyInfo.setPartRev(argSaveSgyInfoReqBD00.getPartRev());
        objEInStrategyInfo.setActOn(argSaveSgyInfoReqBD00.getActOn());
        objEInStrategyInfo.setsInCome(argSaveSgyInfoReqBD00.getSInCome());
        objEInStrategyInfo.setStatus(argSaveSgyInfoReqBD00.getStatus());
        if(objEInStrategyInfos.size()!=0) {
            objEInStrategyInfo.setIsDef(argSaveSgyInfoReqBD00.getIsDef());
        }else{
            objEInStrategyInfo.setIsDef("00");
        }
        objEInStrategyInfo.setCreateTime(new Date());
        objEInStrategyInfo.setCreator(CommonUtils.readUser().getRealName());
        objEInStrategyInfo.setRemark(argSaveSgyInfoReqBD00.getRemark());

        inStrategyDAO.InsertInStrategyInfo(objEInStrategyInfo);

        //返回收货策略ruid
        InStrategyInfo objInStrategyInfo= inStrategyDAO.SelectAllByGuid(objEInStrategyInfo.getGuid());

        objESaveSgyInfoResD.setSgyRd(objInStrategyInfo.getRuid());

        objESaveSgyInfoResB.setData(objESaveSgyInfoResD);
        objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
        return objESaveSgyInfoRes;
    }

    //删除收货策略信息
    @Override
    public SaveSgyInfoRes RmSaveSgyInfo(SaveSgyInfoReqBD01 argSaveSgyInfoReqBD01) {
        SaveSgyInfoRes objESaveSgyInfoRes=new SaveSgyInfoRes();
        SaveSgyInfoResB objESaveSgyInfoResB=new SaveSgyInfoResB();

        if(inStrategyDAO.DeleteInStrategyInfoByRuid(argSaveSgyInfoReqBD01.getSgyRd())<=0){
            throw  new SystemException("MG_MES2001013020001F","删除策略失败");
        }
        objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
        return objESaveSgyInfoRes;
    }

    //更新收货策略
    @Override
    public SaveSgyInfoRes ModSaveSgyInfo(SaveSgyInfoReqBD02 argSaveSgyInfoReqBD02) {
        SaveSgyInfoRes objESaveSgyInfoRes=new SaveSgyInfoRes();
        SaveSgyInfoResB objESaveSgyInfoResB=new SaveSgyInfoResB();

        //查询收货策略信息
        InStrategyInfo objInStrategyInfo= inStrategyDAO.SelectAllByRuid(argSaveSgyInfoReqBD02.getSgyRd());

        //校验名称不能重复
        InStrategyInfo objEInStrategyInfo=inStrategyDAO.SelectstrategyNameANDactOn(argSaveSgyInfoReqBD02.getSgyName(), argSaveSgyInfoReqBD02.getActOn());

        if(objEInStrategyInfo != null && (!objEInStrategyInfo.getStrategyName().equals(objInStrategyInfo.getStrategyName()) || !objEInStrategyInfo.getActOn().equals(objInStrategyInfo.getActOn()))){
            throw new SystemException("MG_MES3001013030002F","更新失败，策略名称已存在");
        }

        InStrategyInfo inStrategyInfo=new InStrategyInfo();

        if("01".equals(objInStrategyInfo.getIsDef()) && "00".equals(argSaveSgyInfoReqBD02.getIsDef())) {
            InStrategyInfo objEsInStrategyInfo = inStrategyDAO.SelectisDefANDactOn(argSaveSgyInfoReqBD02.getIsDef(), argSaveSgyInfoReqBD02.getActOn());

            if (objEsInStrategyInfo != null) {
                inStrategyInfo.setRuid(objEsInStrategyInfo.getRuid());
                inStrategyInfo.setIsDef("01");

                if(inStrategyDAO.UpdateIInStrategyInfo(inStrategyInfo)<=0){
                    throw new SystemException("MG_MES4001312030002F","更新策略信息失败！");
                }
            }
        }


        inStrategyInfo.setRuid(argSaveSgyInfoReqBD02.getSgyRd());
        inStrategyInfo.setStrategyName(argSaveSgyInfoReqBD02.getSgyName());
        inStrategyInfo.setIsDef(argSaveSgyInfoReqBD02.getIsDef());
        inStrategyInfo.setsInCome(argSaveSgyInfoReqBD02.getSInCome());
        inStrategyInfo.setStatus(argSaveSgyInfoReqBD02.getStatus());
        inStrategyInfo.setActOn(argSaveSgyInfoReqBD02.getActOn());
        inStrategyInfo.setPartRev(argSaveSgyInfoReqBD02.getPartRev());
        inStrategyInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        inStrategyInfo.setLastModifyTime(new Date());
        inStrategyInfo.setRemark(argSaveSgyInfoReqBD02.getRemark());

        if(inStrategyDAO.UpdateIInStrategyInfo(inStrategyInfo)<=0){
            throw new SystemException("MG_MES4001312030002F","更新策略信息失败！");
        }

        objESaveSgyInfoRes.setBody(objESaveSgyInfoResB);
        return objESaveSgyInfoRes;
    }
}
