package pnc.mesadmin.dao;

import pnc.mesadmin.entity.common.CurrentGearInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/27 18:09
 * @Description:
 */
public interface CurrentGearDAO {
    List<CurrentGearInfo> selectAllCurrentGearInfo();

    CurrentGearInfo selectCurrentGearInfoByCurrentName(String currentName);

    CurrentGearInfo selectCurrentGearInfoByRuid(int ruid);

    CurrentGearInfo selectCurrentGearInfoByGuid(String guid);

    void insertCurrentGearInfo(CurrentGearInfo currentGearInfo);

    int deleteCurrentGearInfo(int ruid);

    int updateCurrentGearInfo(CurrentGearInfo currentGearInfo);
}
