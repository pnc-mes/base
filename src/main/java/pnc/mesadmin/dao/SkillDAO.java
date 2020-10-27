package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SkillInfo;

import java.util.List;

/**
 * Created by test on 2017/8/16.
 */
public interface SkillDAO {

    /* 根据guid查询技能信息*/
    public SkillInfo SelectSkillInfoByGuid(String guid);
    /* 查询所有技能信息*/
    List<SkillInfo> SelectAllSkillInfo();
    /* 根据ruid查询技能信息*/
    SkillInfo SelectSkillInfoByRuid(int argRuid);
    /* 新增技能信息*/
    int InsertSkillInfo(SkillInfo skillInfo);
    /* 根据ruid删除技能信息*/
    int DeleteSkillInfoByRuid(int argRuid);
    /* 修改技能信息*/
    int UpdateSkillInfo(SkillInfo skillInfo);

    SkillInfo SelectSkillInfoByName(String skillName);

    int SelectCountByName(SkillInfo skill);
}
