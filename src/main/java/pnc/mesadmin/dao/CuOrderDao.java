package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CuOrderInfo;

import java.util.List;

public interface CuOrderDao {
    List<CuOrderInfo> SelectAllCuOrderInfo();

    CuOrderInfo SelectByRuId(Integer coRd);

    CuOrderInfo SelectByGuId(String guId);

    int SaveOrderInfo(CuOrderInfo orderInfo);

    int AddOrderInfo(CuOrderInfo orderInfo);

    int delOrderInfo(Integer coRd);

    List<CuOrderInfo> SelectAllByOrderCode(String code);
}
