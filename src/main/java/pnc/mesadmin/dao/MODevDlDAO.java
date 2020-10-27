package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MODevDlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料上机明细信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-08-29
 * 修改人：
 * 修改时间：
 */
public interface MODevDlDAO {

    /**
     * 新增物料上机明细信息 (pjf)
     * @param argMODevDlInfo
     * @return
     */
    int InsertMODevDl(MODevDlInfo argMODevDlInfo);

    /**
     * 删除物料上机明细信息 (pjf)
     * @param argGuid
     * @return
     */
    int DeleteByGuid(String argGuid);

    /**
     * 根据batch删除物料上机明细信息 (pjf)
     * @param argBatch
     * @return
     */
    int DeleteByBatch(String argBatch);

    /**
     * 根据设备标识查询物料上机明细信息 (pjf)
     * @param argMODevGd
     * @return
     */
    List<MODevDlInfo> SelectByMODevGd(String argMODevGd);

    /**
     * 根据设备标识和批次查询物料上机明细信息 (pjf)
     * @param argMODevGd
     * @param argBatch
     * @return
     */
    MODevDlInfo SelectByMB(@Param("argMODevGd") String argMODevGd, @Param("argBatch") String argBatch);

    /**
     * 根据关联单号+批次号查询 (pjf)
     * @param argAssCode
     * @param argBatch
     * @return
     */
    MODevDlInfo SelectByAB(@Param("argAssCode") String argAssCode, @Param("argBatch") String argBatch);

    /**
     * 根据batch查询物料上机明细信息 (pjf)
     * @param argBatch
     * @return
     */
    MODevDlInfo SelectByBatch(String argBatch);

    /**
     * 根据设备标识、关联单号查询物料上机明细信息 (pjf)
     * @param argAssCode
     * @param argAssSource
     * @return
     */
    List<MODevDlInfo> SelectByMOGdAssCode(@Param("argMODevGd") String argMODevGd,
                                          @Param("argAssCode") String argAssCode,
                                          @Param("argAssSource") String argAssSource);

    /**
     * 删除物料上机明细信息 (pjf)
     * @param mODevGd
     * @return
     */
    int DeleteByMODevGd(String mODevGd);

    /**
     * 删除物料上机明细信息
     * @param mODevGd
     * @param ExecGd
     * @return
     */
    int DeleteByMODevGdAndExecGd(@Param("mODevGd") String mODevGd, @Param("ExecGd") String ExecGd);

    List<MODevDlInfo> SelectByDevGd(String devGd);

    MODevDlInfo SelectModevinfoByDevGdAndBatch(@Param("devGd") String devGd, @Param("batch") String batch);
}
