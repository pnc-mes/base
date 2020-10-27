package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCMethodInfo.GetAllCMethodInfoRes;
import pnc.mesadmin.dto.GetCMethodInfoRes.GetCMethodInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCMethodInfo.SaveCMethodInfoReqBD00;
import pnc.mesadmin.dto.SaveCMethodInfo.SaveCMethodInfoReqBD02;
import pnc.mesadmin.dto.SaveCMethodInfo.SaveCMethodInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.CmethodInfo;

import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
public interface CheckMethodInfoService {
    //获取检验水平列表信息
    GetAllCMethodInfoRes QALLselectAllCMethodInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

   //获取检验水平信息
    GetCMethodInfoRes GetselectByRuid(int CMethodRd) throws SystemException;

     //保存检验水平信息
     SaveCMethodInfoRes AddinsertCMethodInfo(SaveCMethodInfoReqBD00 busData00, CmethodInfo cmethodInfo) throws SystemException;

    //更新检验水平信息
    SaveCMethodInfoRes ModupdateCMethodInfo(SaveCMethodInfoReqBD02 busData02, CmethodInfo cmethodInfo) throws SystemException;

    //删除检验水平信息
    SaveCMethodInfoRes RmdeleteCMethodInfo(int ruid) throws SystemException;
}
