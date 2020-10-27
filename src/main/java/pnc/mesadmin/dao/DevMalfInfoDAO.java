package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevMalfInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:28
 * @Description:
 */
public interface DevMalfInfoDAO {
    void InsertDevMalfInfo(DevMalfInfo devMalfInfo);

    int DeleteDevMalfInfoByruid(int ruid);

    int UpdateDevMalfInfo(DevMalfInfo devMalfInfo);

    List<DevMalfInfo> SelectAllDevMalfInfo();

    DevMalfInfo SelectDevMalfInfoByruid(int ruid);

    DevMalfInfo SelectDevMalfInfoByguid(String guid);

    DevMalfInfo SelectDevMalfInfoByDevMalfCode(String devMalfCode);
}
