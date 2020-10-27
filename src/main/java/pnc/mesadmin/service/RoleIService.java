package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoRes;
import pnc.mesadmin.dto.GetRoleInfo.GetRoleInfoBusData00;
import pnc.mesadmin.dto.GetRoleInfo.GetRoleInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveRoleInfo.SaveRoleInfoReqBD00;
import pnc.mesadmin.dto.SaveRoleInfo.SaveRoleInfoReqBD01;
import pnc.mesadmin.dto.SaveRoleInfo.SaveRoleInfoReqBD02;
import pnc.mesadmin.dto.SaveRoleInfo.SaveRoleInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.RoleInfo;

import java.util.List;


/**
 * Created by test on 2017/5/9.
 */
public interface RoleIService {

    GetAllRoleInfoRes getRoleInfoList(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    GetRoleInfoRes getRoleInfo(GetRoleInfoBusData00 busData00) throws SystemException;

    SaveRoleInfoRes insert(SaveRoleInfoReqBD00 busData00) throws SystemException;

    SaveRoleInfoRes save(SaveRoleInfoReqBD02 busData02) throws SystemException;

    SaveRoleInfoRes remove(SaveRoleInfoReqBD01 busData01) throws SystemException;



    RoleInfo selectRoleIdList(String ruid);

}
