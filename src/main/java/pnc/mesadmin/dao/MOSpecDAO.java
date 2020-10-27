package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MOSpecInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料上工序信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-10-17
 * 修改人：
 * 修改时间：
 */
public interface MOSpecDAO {

    /**
     * 新增物料上工序信息 (pjf)
     * @param argMOSpecInfo
     * @return
     */
    int InsertMOSpec(MOSpecInfo argMOSpecInfo);

    /**
     * 根据guid删除物料上工序信息 (pjf)
     * @param argGuid
     * @return
     */
    int DeleteByGuid(String argGuid);

    /**
     * 修改物料上工序信息 (pjf)
     * @param argMOSpecInfo
     * @return
     */
    int UpdateMOSpec(MOSpecInfo argMOSpecInfo);

    /**
     * 根据工序标识查询物料上工序信息 (pjf)
     * @param argSpecGd
     * @return
     */
    List<MOSpecInfo> SelectBySpecGd(String argSpecGd);

    /**
     * 根据工序标识和物料标识查询物料上工序信息 (pjf)
     * @param argSpecGd
     * @param argMaVerGd
     * @return
     */
    MOSpecInfo SelectBySpecMa(@Param("argSpecGd") String argSpecGd, @Param("argMaVerGd") String argMaVerGd);

    /**
     * 查询物料上设备信息 (pjf)
     * @param argGuid
     * @return
     */
    MOSpecInfo SelectByGuid(String argGuid);

    /**
     * 根据设备标识、关联单号查询物料上工序信息 (pjf)
     * @param argSpecGd
     * @param argAssCode
     * @param argAssSource
     * @return
     */
    List<MOSpecInfo> SelectBySpecGdAssCode(@Param("argSpecGd") String argSpecGd,
                                           @Param("argAssCode") String argAssCode,
                                           @Param("argAssSource") String argAssSource);

    /**
     * 根据工序标识查询物料上工序信息分页 (pjf)
     * @param argSpecGd
     * @param argPageStart
     * @param argPageEnd
     * @return
     */
    List<MOSpecInfo> SelectBySpecGdPage(@Param("argSpecGd") String argSpecGd,
                                        @Param("argPageStart") int argPageStart,
                                        @Param("argPageEnd") int argPageEnd);

    /**
     * 根据工序标识查询物料上工序信息数量 (pjf)
     * @param argSpecGd
     * @return
     */
    int SelectBySpecGdCount(@Param("argSpecGd") String argSpecGd);

    //根据【输入参数LineGd】、【输入参数SpecVerGd】、【MaVerGd】查询【5.1.3. 物料上工序信息表(tpm_MOSpecInfo)】
    MOSpecInfo SelectMOSpecInfo(@Param("LineGd") String LineGd,
                                @Param("SpecVerGd") String SpecVerGd,
                                @Param("MaVerGd") String MaVerGd
    );

    /**
     * 根据线体标识和工序版本标识查询物料上工序信息
     * @param LineGd
     * @param SpecVerGd
     * @return
     */
    List<MOSpecInfo> SelectByLineGdAndSpecVerGd(@Param("LineGd") String LineGd, @Param("SpecVerGd") String SpecVerGd);

    /**
     * 根据线体标识和工序版本标识删除物料上工序信息
     * @param LineGd
     * @param SpecVerGd
     * @return
     */
    int DeleteByLineGdAndSpecVerGd(@Param("LineGd") String LineGd, @Param("SpecVerGd") String SpecVerGd);


    /**
     * 根据线体标识和工序版本标识和用户标识查询物料上工序信息
     * @param LineGd
     * @param SpecVerGd
     * @param SpecVerGd
     * @return
     */
    List<MOSpecInfo> SelectByLineGdAndSpecVerGdAndExecGd(@Param("LineGd") String LineGd,
                                                         @Param("SpecVerGd") String SpecVerGd,
                                                         @Param("ExecGd") String ExecGd);
}
