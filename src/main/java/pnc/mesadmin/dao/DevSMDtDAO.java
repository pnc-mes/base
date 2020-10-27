package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevSMDtlnfo;

import java.util.List;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public interface DevSMDtDAO {

    //根据gd查询设备中状态明细信息
    List<DevSMDtlnfo>  SelectDevSMDtlnfoBydevSMGd(String guid);

    //新增设备状态模型
    int InsertDevSMDtlnfo(DevSMDtlnfo argDevSMDtlnfo);

    //删除设备状态明细
    int DeleteDevSMDtlnfodevSMGd(String guid);
}
