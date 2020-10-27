package pnc.mesadmin.dao;

import pnc.mesadmin.entity.FrePlanDtlDataInfo;

import java.util.List;

/**
 * Created by haozan on 2018/9/7.
 * 次数保养计划明细信息表DAO
 */
public interface FrePlanDtlDAO {
   //次数保养计划明细信息
    List<FrePlanDtlDataInfo> selectAllFrePlanDtlInfo(String FrePlanGd);

    //添加次数保养计划明细
    int insertFrePlanDtlDataInfo(FrePlanDtlDataInfo frePlanDtlDataInfo);

    //删除次数保养计划明细
    int deleteFrePlanDtlInfo(int Ruid);

}
