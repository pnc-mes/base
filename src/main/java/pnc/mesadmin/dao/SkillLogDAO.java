package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SkillLogInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：技能培训记录信息接口DAO
 * 创建人：刘福志
 * 创建时间：2017-8-28
 * 修改人：
 * 修改时间：
 */
public interface SkillLogDAO {
    //查询培训技能记录列表信息
    List<SkillLogInfo> SelectAllSkillLogInfo();

    //新增培训记录信息
    int InsertSkillLogInfo(SkillLogInfo skillLogInfo);

    //删除培训记录信息
    int DeleteSkillLogInfo(int ruid);
}
