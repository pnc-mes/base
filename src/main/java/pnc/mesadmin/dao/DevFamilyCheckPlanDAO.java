package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevFamilyCheckPlanInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 16:19
 * @Description:
 */
public interface DevFamilyCheckPlanDAO {

    List<DevFamilyCheckPlanInfo> selectDevFamilyCheckPlanInfoByDevFamilyGd(String devFamilyGd);

    void insertDevFamilyCheckPlanInfo(DevFamilyCheckPlanInfo devFamilyCheckPlanInfo);

    int deleteDevFamilyCheckPlanInfo(int ruid);
}
