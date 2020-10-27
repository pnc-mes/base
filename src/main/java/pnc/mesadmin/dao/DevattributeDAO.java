package pnc.mesadmin.dao;

import pnc.mesadmin.entity.DevattributeInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 13:31
 * @Description:
 */
public interface DevattributeDAO {

    //根据线体id查询所有信息
    List<DevattributeInfo> selectDevattributeInfoBylineGD(String lineGD);

    //根据设备Guid查询所有信息
    List<DevattributeInfo> selectDevattributeInfoBydevGd(String devGd);

    //新增
    void inserteDevattributeInfo(DevattributeInfo devattributeInfo);

    //删除
    int deleteDevattributeInfoByruid(int ruid);
}
