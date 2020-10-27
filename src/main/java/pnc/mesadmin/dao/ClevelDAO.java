package pnc.mesadmin.dao;

import pnc.mesadmin.entity.ClevelInfo;

import java.util.List;

/**
 * Created by PNC on 2019/3/22.
 */
public interface ClevelDAO {


    List<ClevelInfo> SelectAllCLevelInfo();


    ClevelInfo SelectByRuid(int CLevelRd);

    ClevelInfo SelectByGuid(String CLevelGd);

    ClevelInfo SelectCheckLevelInfo(String CheckLevelCode);


    int UpdateCheckLevelInfo(ClevelInfo cLevelInfo);


    int DeleteCLevelInfo(int CLevelRd);

    int InsertCLevelInfo(ClevelInfo cLevelInfo);


}
