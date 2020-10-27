package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CyclePlanDtllnfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:37
 * @Description:
 */
public interface CyclePlanDtlDAO {
    //新增
    void insertCyclePlanDtllnfo(CyclePlanDtllnfo cyclePlanDtllnfo);

    //根据主表标识查询所有明细
    List<CyclePlanDtllnfo> selectCyclePlanDtllnfoByCyclePlanGd(String cyclePlanGd);

    //删除
    int deleteCyclePlanDtllnfo(int ruid);

    int deleteCyclePlanDtlInfoByGuid(String guid);
}
