package pnc.mesadmin.dao;

import pnc.mesadmin.entity.FrePlanTiggerRulelnfo;


/**
 * Created by haozan on 2018/9/7.
 * 次数保养计划触发规则信息表DAO
 */
public interface FrePlanTiggerRuleDAO {
    //根据保养计划标识查询次数保养计划触发规则
    FrePlanTiggerRulelnfo selectFrePlanGd(String FrePlanGd);

    //新增次数保养计划触发规则信息
    int insertTiggerRulelnfo(FrePlanTiggerRulelnfo frePlanTiggerRulelnfo);

    //根据保养计划标识删除对应信息
    int deleteFrePlanGd(int Ruid);

    //修改保养计划触发规则信息
    int updateFrePlanInfo(FrePlanTiggerRulelnfo frePlanTiggerRulelnfo);


}
