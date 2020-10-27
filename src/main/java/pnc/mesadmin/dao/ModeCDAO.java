package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ModeCInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：模式配置信息DAO
 * 创建人：刘福志
 * 创建时间：2017-08-25
 * 修改人：
 * 修改时间：
 */
public interface ModeCDAO {

    //查询模式配置所有信息
    List<ModeCInfo> SelectAllModeCInfo();

    //查询模式配置模式名称
    ModeCInfo SelectmodeName(String modeName);

    //保存模式配置信息
    int InsertModeCInfo(ModeCInfo modeCInfo);

    //更新模式配置信息
    int UpdateModeCInfo(ModeCInfo modeCInfo);
}
