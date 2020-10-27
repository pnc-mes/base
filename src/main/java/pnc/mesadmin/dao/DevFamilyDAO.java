package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevFamilyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备家族信息DAO
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
public interface DevFamilyDAO {
    //查询设备家族所有信息
    List<DevFamilyInfo> SelectAllDevFamilyInfo();

    //查询设备家族信息
    DevFamilyInfo SelectBydevFRd(int devFRd);

    //查询设备家族名称信息
    DevFamilyInfo SelectByfamilyName(String familyName);

    //通过gd查询设备家族信息
    List<DevFamilyInfo> SelectByguid(String guid);

    //新增设备家族信息
    int InsertDevFamilyInfo(DevFamilyInfo devFamilyInfo);

    //删除设备家族信息
    int DeleteDevFamilyInfo(int ruid);

    //更新设备家族信息
    int UpdateDevFamilyInfo(DevFamilyInfo devFamilyInfo);

}
