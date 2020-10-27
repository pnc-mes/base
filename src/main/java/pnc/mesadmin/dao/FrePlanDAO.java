package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.FrePlanInfo;

import java.util.List;

/**
 * Created by haozan on 2018/9/7.
 * 次数保养计划信息表DAO
 */
public interface FrePlanDAO extends BaseMapper<FrePlanInfo> {
    //查询次数保养计划列表
    List<FrePlanInfo> selectAllFrePlan();

    //根据id查询次数保养计划
    FrePlanInfo selectFrePlan(int Ruid);

    FrePlanInfo selectFrePlanByGuid(String guid);

    //新增次数保养计划
    int insertFrePlan(FrePlanInfo frePlanInfo);

    //根据次数保养计划名字查询
    FrePlanInfo selectAllFrePlanName(String maxTimeWindowName);

    //删除次数保养计划
    int deleteFrePlan(int ruid);

    //更新次数保养计划
    int updateFrePlan(FrePlanInfo frePlanInfo);

}
