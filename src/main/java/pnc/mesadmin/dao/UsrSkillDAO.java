package pnc.mesadmin.dao;

import pnc.mesadmin.entity.UsrSkillInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：人员技能信息接口DAO
 * 创建人：刘福志
 * 创建时间：2017-8-28
 * 修改人：
 * 修改时间：
 */
public interface UsrSkillDAO {
    //查询人员技能信息列表
    List<UsrSkillInfo> SelectAllUsrSkillInfo();

    //根据用户标识查询技能信息列表
    List<UsrSkillInfo> SelectByUserGd(String argUserGd);

    //新增人员技能信息
    int InsertUsrSkillInfo(UsrSkillInfo usrSkillInfo);

    //删除人员技能信息
    int DeleteUsrSkillInfo(int ruid);
}
