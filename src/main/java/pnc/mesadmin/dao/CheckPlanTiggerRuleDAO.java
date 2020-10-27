package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckPlanTiggerRulelnfo;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:32
 * @Description:
 */
public interface CheckPlanTiggerRuleDAO {
    //根据标识查询一条信息
    CheckPlanTiggerRulelnfo selectCheckPlanTiggerRulelnfoByCheckPlanGd(String checkPlanGd);

    //新增
    void insertCheckPlanTiggerRulelnfo(CheckPlanTiggerRulelnfo checkPlanTiggerRulelnfo);

    //删除
    int deleteCheckPlanTiggerRulelnfo(String guid);

    //修改
    int updateCheckPlanTiggerRulelnfo(CheckPlanTiggerRulelnfo checkPlanTiggerRulelnfo);
}
