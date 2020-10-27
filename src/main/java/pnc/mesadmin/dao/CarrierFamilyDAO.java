package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.CarrierFamilyInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具家族信息DAO层
 * 创建人：HAOZAN
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public interface CarrierFamilyDAO extends BaseMapper<CarrierFamilyInfo>{

    //查询所有工具家族信息
    List<CarrierFamilyInfo> selectCarrierFamilyAll();

    //根据标识查询工具家族信息
    CarrierFamilyInfo selectCarrierFamily(String argGuid);

    //根据ID查询工具家族信息
    CarrierFamilyInfo selectCarrierFamilyID(Integer argRuid);

    //根据工具家族名称查询工具信息
    CarrierFamilyInfo SelectCarrierInfoByToolFaName(String argCarrierFamilyName);

    //增加工具家族信息
    int InsertCarrierFamilyInfo(CarrierFamilyInfo argCarrierFamilyinfo);

    //更新工具家族信息
    int UpdateCarrierFamilyInfoByruid(CarrierFamilyInfo argCarrierFamilyinfo);


    //删除工具家族信息根据工具id
    int DeleteCarrierFamilyInfoByruid(int argruid);

}
