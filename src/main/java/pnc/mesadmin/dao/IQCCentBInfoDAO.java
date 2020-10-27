
package pnc.mesadmin.dao;

import pnc.mesadmin.entity.IQCCentBInfo;

import java.util.List;

/**
 * Created by PNC on 2017/6/10.
 */
public interface IQCCentBInfoDAO {
    int InsertIQCCentBInfo(IQCCentBInfo argIQCCentBInfo);
    int DeleteIQCCentBInfoByIqcGd(String argIqcGd);
    int UpdateIQCCentBInfo(IQCCentBInfo iqcCentBInfo);
    List<IQCCentBInfo> SelectIQCCentBInfoByIQCGd(String argIQCGd);
    IQCCentBInfo SelectIQCCentBInfoByRuid(int argRuid);
}
