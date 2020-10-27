package pnc.mesadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.DevGpInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备组级信息DAO
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface DevGpDAO extends BaseMapper<DevGpInfo>  {
    //查询设备组信息
    List<DevGpInfo> SelectAllDevGpInfo();

    //关联设备表查询
    DevGpInfo SelectById(String devGpGd);

    //查询设备组信息
    DevGpInfo SelectDevGpById(int devGRd);

    //查询设备组名称
    DevGpInfo SelectdevGpName(String DevGpInfo);

    //根据gd查询设备组信息
    DevGpInfo SelectByguid(String guid);

    //保存设备组信息
    int InsertDevGpInfo(DevGpInfo devGpInfo);

    //删除设备组信息
    int DeleteDevGpInfo(int ruid);

    //更新设备组信息
    int UpdateDevGpInfo(DevGpInfo devGpInfo);

}
