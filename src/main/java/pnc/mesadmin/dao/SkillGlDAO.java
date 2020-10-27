package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.SkillGlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public interface SkillGlDAO extends BaseMapper<SkillGlInfo> {

    //查询技能组信息
    List<SkillGlInfo> SelectSkillGlInfo();

    //查询技能信息
    SkillGlInfo SelectSkillGlInfoByRuid(int argruid);

    //新增技能信息
    int InsertSkillGlInfo(SkillGlInfo ageSkillGlInfo);

    //删除技能组信息
    int DeleteSkillGlInfo(int argruid);

    //更新技能组信息
    int UpdateSkillGlInfo(SkillGlInfo ageSkillGlInfo);

    //根据名字查询信息
    SkillGlInfo SelectSkillGlInfoBySkillGName(String argSkillGName);

    //根据guid查询技能组信息
    SkillGlInfo SelectSkillGlInfoByGuid(String argGuid);

}
