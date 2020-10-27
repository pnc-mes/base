package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCLevelInfo.GetAllCLevelInfoRes;
import pnc.mesadmin.dto.GetCLevelInfoRes.GetCLevelInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCLevelInfo.SaveCLevelInfoReqBD00;
import pnc.mesadmin.dto.SaveCLevelInfo.SaveCLevelInfoReqBD02;
import pnc.mesadmin.dto.SaveCLevelInfo.SaveCLevelInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.ClevelInfo;

import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
public interface CLeveIService {
    //获取检验水平列表信息
    GetAllCLevelInfoRes QALLselectAllCLeveIInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

   //获取检验水平信息
    GetCLevelInfoRes GetselectByRuid(int CLevelRd) throws SystemException;

     //保存紧急代码信息
    SaveCLevelInfoRes AddinsertCLevelInfo(SaveCLevelInfoReqBD00 busData00, ClevelInfo cLevelInfo) throws SystemException;

    //更新紧急代码信息
    SaveCLevelInfoRes ModupdateCLevelInfo(SaveCLevelInfoReqBD02 busData02, ClevelInfo cLevelInfo) throws SystemException;

    //删除紧急代码信息
    SaveCLevelInfoRes RmdeleteCLevelInfo(int ruid) throws SystemException;
}
