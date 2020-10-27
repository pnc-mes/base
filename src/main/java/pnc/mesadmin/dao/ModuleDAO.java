package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ModuleInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：模块信息DAO
 * 创建人：潘俊峰
 * 创建时间：2017-05-17
 * 修改人：
 * 修改时间：
 */
public interface ModuleDAO {

    /**
     * 获取所有模块信息 (By-pjf)
     * @return
     */
    List<ModuleInfo> SelectAllModuleInfo();

    /**
     * 根据guid查模块信息 (By-pjf)
     * @param guid
     * @return
     */
    ModuleInfo SelectModuleInfo(String guid);

    /**
     * 根据ruid查模块信息 (By-pjf)
     * @param ruid
     * @return
     */
    ModuleInfo SelectModuleInfoRuid(int ruid);

    /**
     * 查询当前模块下是否存在子模块 (By-pjf)
     * @param argParentGd
     * @return
     */
    List<ModuleInfo> SelectAllChildModule(@Param("argParentGd") String argParentGd, @Param("argisClose") String argisClose, @Param("sysVerGd") String sysVerGd);

    /**
     * 查询当前模块下是否存在子模块 (By-pjf)
     * @param argParentGd
     * @return
     */
    List<ModuleInfo> SelectAllChildModuleAdmin(String argParentGd);

    /**
     * 根据系统版本查询当前模块下是否存在子模块
     * @param argParentGd
     * @return
     */
    List<ModuleInfo> SelectAllChildModuleAdminBySysVerGd(@Param("argParentGd") String argParentGd, @Param("sysVerGd") String sysVerGd);

    /**
     * 根据系统版本所有模块
     * @param sysVerGd
     * @return
     */
    List<ModuleInfo> SelectAllBySysVerGd(@Param("sysVerGd") String sysVerGd);

    /**
     * 新增模块信息 (By-pjf)
     * @param argModuleInfo
     * @return
     */
    int InsertModuleInfo(ModuleInfo argModuleInfo);

    /**
     * 删除模块信息 (By-pjf)
     * @param argRuid
     * @return
     */
    int DeleteModuleByRuid(int argRuid);

    /**
     * 根据系统版本删除模块信息
     * @param sysVerGd
     * @return
     */
    int DeleteModuleBySysVerGd(String sysVerGd);

    /**
     * 编辑模块信息 (By-pjf)
     * @param argModuleInfo
     * @return
     */
    int UpdateModule(ModuleInfo argModuleInfo);

    /**
     * 根据名称查询模块信息 (By-pjf)
     * @param argModuleName
     * @return
     */
    ModuleInfo SelectModuleByName(@Param("argModuleName") String argModuleName,
                                  @Param("argParentGd") String argParentGd,
                                  @Param("sysVerGd") String sysVerGd);
}
