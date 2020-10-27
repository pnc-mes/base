package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CarrierBZDtlInfo;

import java.util.List;

public interface CarrierBZDtlDao {

    //载具标准值标识查询
    List<CarrierBZDtlInfo> SelectCarrierBZDtlInfoCarrierBZGd(String CarrierBZGd);
    //载具标准值明细查询
    CarrierBZDtlInfo SelectCarrierBZDtlInfoargruid(String argruid);

    int InsertCarrierBZDtlInfo(CarrierBZDtlInfo carrierBZDtlInfo);

    int UpdateCarrierBZDtlInfo(CarrierBZDtlInfo carrierBZDtlInfo);

    int DeleteAllCarrierBZDtlToolBZGd(String CarrierBZGd);

}
