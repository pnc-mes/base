package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllDevFInfo.GetAllDevFInfoRes;
import pnc.mesadmin.dto.GetDevFInfo.GetDevFInfoReqBD00;
import pnc.mesadmin.dto.GetDevFInfo.GetDevFInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveDevFInfo.SaveDevFInfoReqBD00;
import pnc.mesadmin.dto.SaveDevFInfo.SaveDevFInfoReqBD01;
import pnc.mesadmin.dto.SaveDevFInfo.SaveDevFInfoReqBD02;
import pnc.mesadmin.dto.SaveDevFInfo.SaveDevFInfoRes;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备家族信息Service接口
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
public interface DeviceFIService {
    //获取设备家族列表
    GetAllDevFInfoRes QALLGetAllDevFInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取设备家族信息
    GetDevFInfoRes GetGetDevFInfoRes(GetDevFInfoReqBD00 argGetDevFInfoReqBD00);

    //新增设备家族
    SaveDevFInfoRes AddSaveDevFInfoRes(SaveDevFInfoReqBD00 argSaveDevFInfoReqBD00);

    //删除设备家族
    SaveDevFInfoRes RmSaveDevFInfoRes(SaveDevFInfoReqBD01 argSaveDevFInfoReqBD01);

    //更新设备家族
    SaveDevFInfoRes ModSaveDevFInfoRes(SaveDevFInfoReqBD02 argSaveDevFInfoReqBD02);

}
