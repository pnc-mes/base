package pnc.mesadmin.dao;

import pnc.mesadmin.entity.IQCBadBInfo;

import java.util.List;

/**
 * Created by PNC on 2017/6/10.
 */
public interface IQCBadBInfoDAO {
    int InsertBadBInfo(IQCBadBInfo argIqcBadBInfo);
    int DeleteBadBInfoByIqcGd(String argIqcGd);
    int DeleteBadBInfoByGuid(String argGuid);
    int UpdateBadBInfo(IQCBadBInfo argIQCBadBInfo);
    List<IQCBadBInfo> SelectBadBInfoByIQCGd(String argIQCGd);
    IQCBadBInfo SelectIQCBadInfoByRuid(int argRuid);

}
