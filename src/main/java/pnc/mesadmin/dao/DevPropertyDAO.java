package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevPropertyInfo;


/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备特性信息DAO
 * 创建人：潘俊峰
 * 创建时间：2018-09-16
 * 修改人：
 * 修改时间：
 */
public interface DevPropertyDAO {

    /**
     * 根据F1()查询设备特性信息  (By-pjf)
     * @param argF1
     * @return
     */
    DevPropertyInfo SelectByF1(String argF1);

    //根据设备Guid查询物料特性
    DevPropertyInfo selectByGuid(String Guid);

    //新增设备特性
    int InsertDevPropertyInfo(DevPropertyInfo devPropertyInfo);

    //修改设备特性
    int UpdateDevPropertyInfo(DevPropertyInfo devPropertyInfo);

    //删除设备特性
    int DeleteDevPropertyInfo(String Guid);
}
