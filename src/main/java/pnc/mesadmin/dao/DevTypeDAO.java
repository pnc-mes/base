package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.DevTypeInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备类型DAO
 * 创建人：蒋顾毅
 * 创建时间：2020-09-17
 * 修改人：
 * 修改时间：
 */
public interface DevTypeDAO extends BaseMapper<DevTypeInfo> {
    //查询设备类型列表信息
    List<DevTypeInfo> SelectAllDevTypeInfo();

    //查询设备类型信息
    DevTypeInfo SelectByRuid(int devTypeRd);

    //查询设备类型
    DevTypeInfo SelectdevType(String devType);

    //根据gd查询设备类型
    DevTypeInfo SelectByguid(String guid);

    //保存设备类型信息
    int InsertDevTypeInfo(DevTypeInfo devTypeInfo);

    //删除设备类型信息
    int DeleteDevTypeInfo(int ruid);

    //更新设备类型信息
    int UpdateDevTypeInfo(DevTypeInfo devTypeInfo);

    //根据名称查询一条信息
    DevTypeInfo SelectAllDevTypeInfoByDevType(String DevType);
}
