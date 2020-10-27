package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CheckPlanDtllnfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:32
 * @Description:
 */
public interface CheckPlanDtlDAO {
    //查询主系表关系 通过selectCheckPlanDtllnfoByCheckPlanGd
     List<CheckPlanDtllnfo> selectCheckPlanDtllnfoByCheckPlanGd(String checkPlanGd);

     //删除
    int deleteCheckPlanDtllnfo(String guid);

    //新增
    void insertCheckPlanDtllnfo(CheckPlanDtllnfo checkPlanDtllnfo);
}
