package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.DevInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：资源信息DAO
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public interface DevDAO extends BaseMapper<DevInfo> {
    /**
     * 查询所有设备信息
     * @return
     */
    List<DevInfo> SelectAllDevInfo();

    /**
     * 查询设备信息
     * @param devRd
     * @return
     */
    DevInfo SelectBydevRd(int devRd);

    /**
     * 查询设备信息
     * @param guid
     * @return
     */
    DevInfo SelectByguid(String guid);

    /**
     * 保存设备信息
     * @param devInfo
     * @return
     */
    int InsertDevInfo(DevInfo devInfo);

    /**
     * 删除设备信息
     * @param ruid
     * @return
     */
    int DeleteDevInfo(int ruid);

    /**
     * 更新设备信息
     * @param prresInfo
     * @return
     */
    int UpdateDevInfo(DevInfo prresInfo);

    /**
     * 查询设备名称
     * @param DevInfo
     * @return
     */
    DevInfo SelectBydevName(String DevInfo);

    /**
     * 根据sop物料+工序查询设备 (By-pjf)
     * @param argMaGd
     * @param argSpecGd
     * @return
     */
    List<DevInfo> SelectByMaGdSpecVerGd(@Param("argMaGd") String argMaGd, @Param("argSpecGd") String argSpecGd);

    /**
     * 根据设备组查询设备 (By-pjf)
     * @param argDevGpGd
     * @return
     */
    List<DevInfo> SelectByDevGpGd(@Param("argDevGpGd") String argDevGpGd);

    DevInfo SelectAllDevInfoBydevCode(String devCode);

    /**
     * 根据设备ip查询设备
     * @param ip
     * @return
     */
    DevInfo SelectDevInfoByIP(String ip);
}
