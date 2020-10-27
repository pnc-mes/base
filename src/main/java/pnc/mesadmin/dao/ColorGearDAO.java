package pnc.mesadmin.dao;

import pnc.mesadmin.entity.common.ColorGearInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/27 17:13
 * @Description:
 */
public interface ColorGearDAO {
    List<ColorGearInfo> selectAllColorGearInfo();

    ColorGearInfo selectColorGearInfoByColorName(String colorName);

    ColorGearInfo selectColorGearInfoByRuid(int ruid);

    ColorGearInfo selectColorGearInfoByGuid(String guid);

    void insertColorGearInfo(ColorGearInfo powerGearInfo);

    int deleteColorGearInfo(int ruid);

    int updateColorGearInfo(ColorGearInfo powerGearInfo);
}
