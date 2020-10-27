package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevCheckPlanInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 11:03
 * @Description:
 */
public interface DevCheckPlanDAO {
    //查询列表
    List<DevCheckPlanInfo> selectDevCheckPlanInfoDevGd(String devGd);

    //新增
    void insertDevCheckPlanInfo(DevCheckPlanInfo devCheckPlanInfo);

    //删除
    int deleteDevCheckPlanInfo(int ruid);

    List<DevCheckPlanInfo>  SelectByCheckPlanGd(String CheckPlanGd);

    int  detelteByCheckPlanGd(String CheckPlanGd);
}
