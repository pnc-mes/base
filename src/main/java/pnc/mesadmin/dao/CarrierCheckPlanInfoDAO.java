package pnc.mesadmin.dao;


import pnc.mesadmin.entity.CarrierCheckPlanInfo;

import java.util.List;

//载具点检计划接口
public interface CarrierCheckPlanInfoDAO {
    //添加
    int AddCarrierCheckPlanInfo(CarrierCheckPlanInfo CarrierCheckPlanInfo);
    //根据GD查
    List<CarrierCheckPlanInfo> SelectCarrierCheckPlanInfoByGd(String Guid);

    int deleteByCarrierCheckPlanInfoGd(String CarrierGd);

    List<CarrierCheckPlanInfo> SelectCarrierCheckPlanInfoByCheckPlanGd(String CheckPlanGd);

    int deleteByCheckPlanGd(String CheckPlanGd);
}
