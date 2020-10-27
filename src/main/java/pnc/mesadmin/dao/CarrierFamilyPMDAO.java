package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CarrierFamilyPMInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 20:25
 * @Description:
 */
public interface CarrierFamilyPMDAO {

    List<CarrierFamilyPMInfo> selectCarrierFamilyPMInfoByCarrierFamilyGd(String carrierFamilyGd);

    void insertCarrierFamilyPMInfo(CarrierFamilyPMInfo carrierFamilyPMInfo);

    int deleteCarrierFamilyPMInfo(int ruid);
}
