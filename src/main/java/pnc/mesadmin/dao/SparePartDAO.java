package pnc.mesadmin.dao;

import pnc.mesadmin.entity.SparePartInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 15:58
 * @Description:
 */
public interface SparePartDAO {
    void InsertSparePartInfo(SparePartInfo sparePartInfo);

    int DeleteSparePartInfoByruid(int ruid);

    int UpdateSparePartInfo(SparePartInfo sparePartInfo);

    List<SparePartInfo> SelectSparePartInfo();

    SparePartInfo SelectSparePartInfoByruid(int ruid);

    SparePartInfo SelectSparePartInfoByguid(String guid);

    SparePartInfo SelectSparePartInfoByPartName(String partName);
}
