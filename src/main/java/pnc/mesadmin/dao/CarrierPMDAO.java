package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.CarrierPMInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 19:48
 * @Description:
 */
public interface CarrierPMDAO {

    List<CarrierPMInfo> selectCarrierPMInfoByToolFamilyGd(String toolFamilyGd);

    void insertCarrierPMInfo(CarrierPMInfo carrierPMInfo);

    int deleteCarrierPMInfo(int ruid);

    List<CarrierPMInfo>  selectByCarrierGdPmType(@Param("CarrierGd") String CarrierGd,
                                                 @Param("pMType") String pMType);

    List<CarrierPMInfo>  SelectByPMGd(String PMGd);

    int deleteByPMGd(String PMGd);
}
