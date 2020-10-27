package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllBLInfo.GetAllBLInfoRes;
import pnc.mesadmin.dto.GetAllSgyInfo.GetAllSgyInfoRes;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoRes;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoReqBD00;
import pnc.mesadmin.dto.GetSgyInfo.GetSgyInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveBLInfo.SaveBLInfoReqBD00;
import pnc.mesadmin.dto.SaveBLInfo.SaveBLInfoReqBD02;
import pnc.mesadmin.dto.SaveBLInfo.SaveBLInfoReqBD03;
import pnc.mesadmin.dto.SaveBLInfo.SaveBLInfoRes;
import pnc.mesadmin.dto.SaveSgyInfo.SaveSgyInfoReqBD00;
import pnc.mesadmin.dto.SaveSgyInfo.SaveSgyInfoReqBD01;
import pnc.mesadmin.dto.SaveSgyInfo.SaveSgyInfoReqBD02;
import pnc.mesadmin.dto.SaveSgyInfo.SaveSgyInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.BLevelInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次等级信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface BatchLIService {
    //查询批次等级列表信息
    GetAllBLInfoRes QALLselectAllBLevelInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询批次等级信息
    GetBLInfoRes GetselectBybLRd(int bLRd) throws SystemException;

    //保存批次等级信息
    SaveBLInfoRes AddinsertBLevelInfo(SaveBLInfoReqBD00 busData00, BLevelInfo bLevelInfo) throws SystemException;

    //更新批次等级信息
    SaveBLInfoRes ModupdateBLevelInfo(SaveBLInfoReqBD02 busData02, BLevelInfo bLevelInfo) throws SystemException;

    //复制批次等级信息
    SaveBLInfoRes copyBLevelInfo(SaveBLInfoReqBD03 busData03, BLevelInfo bLevelInfo) throws SystemException;

    //删除批次等级信息
    SaveBLInfoRes RmdeleteBLevelInfo(int ruid) throws SystemException;

    /**
     * Created by liufuzhi on 2018/1/30.
     */
    interface StrategyIService {
        //查询收货策略列表信息
        GetAllSgyInfoRes QALLGetAllSgyInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

        //查询收货策略信息
        GetSgyInfoRes GetGetSgyInfoRes(GetSgyInfoReqBD00 argGetSgyInfoReqBD00);

        //新增收货策略信息
        SaveSgyInfoRes AddSaveSgyInfo(SaveSgyInfoReqBD00 argSaveSgyInfoReqBD00);

        //删除收货策略信息
        SaveSgyInfoRes RmSaveSgyInfo(SaveSgyInfoReqBD01 argSaveSgyInfoReqBD01);

        //更新收货策略信息
        SaveSgyInfoRes ModSaveSgyInfo(SaveSgyInfoReqBD02 argSaveSgyInfoReqBD02);
    }
}
