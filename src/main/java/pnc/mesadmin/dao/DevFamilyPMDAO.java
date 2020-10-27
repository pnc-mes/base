package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevFamilyPMInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 16:15
 * @Description:
 */
public interface DevFamilyPMDAO {

    List<DevFamilyPMInfo> selectDevFamilyPMInfoByDevFamilyGd(String devFamilyGd);

    void insertDevCheckPlanInfo(DevFamilyPMInfo devFamilyPMInfo);

    int deleteDevCheckPlanInfo(int ruid);
}
