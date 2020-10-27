package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CarrierBZInfo;

public interface CarrierBZDao {



    CarrierBZInfo SelectCarrierBZInfo(String CarrierGd);

    CarrierBZInfo SelectCarrierBZInfoGuid(String argguid);

    CarrierBZInfo SelectCarrierBZInfoRuid(String argRuid);

    int InsertCarrierBZInfo(CarrierBZInfo carrierBZInfo);

    int UpdateCarrierBZInfo(CarrierBZInfo carrierBZInfo);
    //载具标识删除
    int DeleteAllCarrierBZInfoRuid(String CarrierGd);
}
