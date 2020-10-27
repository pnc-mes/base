package pnc.mesadmin.dao;

import pnc.mesadmin.entity.common.PowerGearInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/27 15:30
 * @Description:
 */
public interface PowerGearDAO {
    List<PowerGearInfo> selectAllPowerGearInfo();

    PowerGearInfo selectPowerGearInfoByPowerName(String powerName);

    PowerGearInfo selectPowerGearInfoByRuid(int ruid);

    PowerGearInfo selectPowerGearInfoByGuid(String guid);

    void insertPowerGearInfo(PowerGearInfo powerGearInfo);

    int deletePowerGearInfo(int ruid);

    int updatePowerGearInfo(PowerGearInfo powerGearInfo);





}
