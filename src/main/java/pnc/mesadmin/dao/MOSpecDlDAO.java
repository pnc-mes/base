package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MOSpecDlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料上工序明细信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-10-17
 * 修改人：
 * 修改时间：
 */
public interface MOSpecDlDAO {

    /**
     * 新增物料上工序明细信息 (pjf)
     * @param argMOSpecDlInfo
     * @return
     */
    int InsertMOSpecDl(MOSpecDlInfo argMOSpecDlInfo);

    /**
     * 删除物料上工序明细信息 (pjf)
     * @param argGuid
     * @return
     */
    int DeleteByGuid(String argGuid);

    /**
     * 根据batch删除物料上工序明细信息 (pjf)
     * @param argBatch
     * @return
     */
    int DeleteByBatch(String argBatch);

    /**
     * 根据工序标识查询物料上工序明细信息 (pjf)
     * @param argMOSpecGd
     * @return
     */
    List<MOSpecDlInfo> SelectByMOSpecGd(String argMOSpecGd);

    /**
     * 根据批次查询物料上工序明细信息 (pjf)
     * @param argBatch
     * @return
     */
    MOSpecDlInfo SelectByBatch(String argBatch);

    /**
     * 根据物料上机标识、关联单号查询物料上工序信息 (pjf)
     * @param argAssCode
     * @param argAssSource
     * @return
     */
    List<MOSpecDlInfo> SelectByMOAssCode(@Param("argMOSpecGd") String argMOSpecGd,
                                         @Param("argAssCode") String argAssCode,
                                         @Param("argAssSource") String argAssSource);

    /**
     * 根据MOSpecGd删除物料上工序明细信息
     * @param moSpecGd
     * @return
     */
    int DeleteByMOSpecGd(String moSpecGd);

    /**
     * 根据MOSpecGd和ExecGd删除物料上工序明细信息
     * @param moSpecGd
     * @param ExecGd
     * @return
     */
    int DeleteByMOSpecGdAndExecGd(@Param("moSpecGd") String moSpecGd, @Param("ExecGd") String ExecGd);

    List<MOSpecDlInfo> SelectByLineGdSpecVerGd(@Param("lineGd") String lineGd,
                                               @Param("specVerGd") String specVerGd);
}
