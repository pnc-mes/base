package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SkillGdlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public interface SkillGdlDAO {
    //根据技能组明细查询明细信息
    List<SkillGdlInfo> SelectSkillGdlInfoBySkillGGd(String argSkillGGd);

    //新增明细
    int InsertSkillGdlInfo(SkillGdlInfo argSkillGdlInfo);

    //删除明细
    int DeleteSkillGdlInfo(int argruid);

    //更新明细
    int UpdateSkillGdlInfo(SkillGdlInfo argSkillGdlInfo);

}
