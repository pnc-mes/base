package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ResInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：资源信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-10
 * 修改人：
 * 修改时间：
 */
public interface ResDAO {
    List<ResInfo> SelectAllResInfo();

    List<ResInfo> SelectAllModule(@Param("moduleGd") String moduleGd, @Param("argisClose") String argisClose);

    List<ResInfo> SelectAllModuleAdmin(String moduleGd);

    List<ResInfo> SelectResInfoName(String resName);

    ResInfo SelectResByRuid(int objRuid);

    ResInfo SelectByGuid(String argGuid);

    /**
     * 新增资源信息 (pjf)
     * @param argResInfo
     * @return
     */
    int InsertRes(ResInfo argResInfo);

    /**
     * 删除模块下所有资源 (pjf)
     * @param argModuleGd
     * @return
     */
    int DeleteResInfoByModuleGd(String argModuleGd);

    /**
     * 修改资源信息 (pjf)
     * @param argResInfo
     * @return
     */
    int UpdateResInfo(ResInfo argResInfo);

    /**
     * 删除资源信息 (pjf)
     * @param argGuid
     * @return
     */
    int DeleteResByGuid(String argGuid);

    /**
     * 获取模块下MaxPos
     * @param argModuleGd
     * @return
     */
    Integer SelectResPosByModuleGd(String argModuleGd);

    /**
     * 查询所有有url路径并且状态可用
     * @param
     * @return
     */
    List<ResInfo> SelectAllResInfoByGW();
}