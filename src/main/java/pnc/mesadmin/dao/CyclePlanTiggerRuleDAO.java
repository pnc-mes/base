package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CyclePlanTiggerRulelnfo;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:22
 * @Description:
 */
public interface CyclePlanTiggerRuleDAO {
    //根据主表标识查询一条信息
    CyclePlanTiggerRulelnfo selectCyclePlanTiggerRulelnfoByCyclePlanGd(String cyclePlanGd);

    //删除
    int deleteCyclePlanTiggerRulelnfo(int ruid);

    //新增
    void insertCyclePlanTiggerRulelnfo(CyclePlanTiggerRulelnfo cyclePlanTiggerRulelnfo);

    //更新
    int updateCyclePlanTiggerRulelnfo(CyclePlanTiggerRulelnfo cyclePlanTiggerRulelnfo);

    int deleteCyclePlanTiggerRulelnfoByGuid(String guid);
}
