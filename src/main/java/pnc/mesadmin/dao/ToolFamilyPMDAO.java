package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ToolFamilyPMInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 19:06
 * @Description:
 */
public interface ToolFamilyPMDAO {

    List<ToolFamilyPMInfo> selectToolFamilyPMInfoByToolFamilyGd(String toolFamilyGd);

    void insertToolFamilyPMInfo(ToolFamilyPMInfo toolFamilyPMInfo);

    int deleteToolFamilyPMInfo(int ruid);
}
