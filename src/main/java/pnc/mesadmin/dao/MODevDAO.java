package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MODevInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料上机信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-08-29
 * 修改人：
 * 修改时间：
 */
public interface MODevDAO {

    /**
     * 新增物料上机信息 (pjf)
     * @param argMODevInfo
     * @return
     */
    int InsertMODev(MODevInfo argMODevInfo);

    /**
     * 根据guid删除物料上机信息 (pjf)
     * @param argGuid
     * @return
     */
    int DeleteByGuid(String argGuid);

    /**
     * 修改物料上机信息 (pjf)
     * @param argMODevInfo
     * @return
     */
    int UpdateMODev(MODevInfo argMODevInfo);

    /**
     * 根据设备标识查询物料上机信息 (pjf)
     * @param argDevGd
     * @return
     */
    List<MODevInfo> SelectByDevGd(String argDevGd);

    /**
     * 根据设备标识和物料标识查询物料上机信息 (pjf)
     * @param argDevGd
     * @param argMaVerGd
     * @return
     */
    MODevInfo SelectByDevMa(@Param("argDevGd") String argDevGd, @Param("argMaVerGd") String argMaVerGd);

    /**
     * 查询物料上机信息 (pjf)
     * @param argGuid
     * @return
     */
    MODevInfo SelectByGuid(String argGuid);

    /**
     * 根据设备标识、关联单号查询物料上机信息 (pjf)
     * @param argDevGd
     * @param argAssCode
     * @param argAssSource
     * @return
     */
    List<MODevInfo> SelectByDevGdAssCode(@Param("argDevGd") String argDevGd,
                                         @Param("argAssCode") String argAssCode,
                                         @Param("argAssSource") String argAssSource);

    /**
     * 根据DevGd删除物料上机信息
     * @param devGd
     * @return
     */
    int DeleteByDevGd(String devGd);


    /**
     * 根据设备标识、上料执行者标识查询物料上机信息 (pjf)
     * @param argDevGd
     * @param ExecGd
     * @return
     */
    List<MODevInfo> SelectByDevGdAndExecGd(@Param("argDevGd") String argDevGd,
                                           @Param("ExecGd") String ExecGd);
}
