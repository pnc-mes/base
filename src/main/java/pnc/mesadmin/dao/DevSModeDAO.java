package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.DevSModeInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/21.
 */
public interface DevSModeDAO extends BaseMapper<DevSModeInfo> {

    //查询设备状态模型信息
    List<DevSModeInfo> SelectAllDevSModeInfo();

    //查询设备状态模型信息
    DevSModeInfo  SelectDevSModeInfo(int ruid);

    //查询设备状态模型名称
    DevSModeInfo  SelectDevSModeInfomodelName(String modelName);

    //根据gd查询设备状态模型信息
    DevSModeInfo SelectDevSModeInfoguid(String guid);

    //新增设备状态模型信息
    int InsertDevSModeInfo(DevSModeInfo argDevSModeInfo);

    //更新设备状态模型信息
    int UpdateDevSModeInfo(DevSModeInfo argDevSModeInfo);

    //删除设备状态模型信息
    int DeleteDevSModeInfo(int argRuid);

}
