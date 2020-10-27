package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CmethodInfo;

import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
public interface CmethodDAO {


    List<CmethodInfo> SelectAllCmethodInfo();


    CmethodInfo SelectByRuid(int CMethodRd);

    CmethodInfo SelectByGuid(String guid);

    CmethodInfo SelectCheckCmethodInfo(String CheckMethodCode);


    int UpdateCheckCmethodInfo(CmethodInfo cmethodInfo);


    int DeleteCmethodInfo(int CMethodRd);

    int InsertCmethodInfo(CmethodInfo cmethodInfo);


    CmethodInfo SelectCmethodGd(String CMethodGd);
}
