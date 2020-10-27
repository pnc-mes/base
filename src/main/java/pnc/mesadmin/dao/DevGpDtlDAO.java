package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.DevGpDtlInfo;
import pnc.mesadmin.entity.OpertInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备组级信息DAO
 * 创建人：刘福志
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public interface DevGpDtlDAO extends BaseMapper<DevGpDtlInfo> {
    //关联设备组查询明细信息
    List<DevGpDtlInfo> SelectByguid(String guid);

    //新增设备组明细信息
    int InsertDevGpDtlInfo(DevGpDtlInfo devGpDtlInfo);

    //删除设备组明细信息
    int DeleteDevGpDtlInfo(String devGpGd);
}
