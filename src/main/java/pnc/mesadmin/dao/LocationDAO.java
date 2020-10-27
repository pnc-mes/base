package pnc.mesadmin.dao;

import pnc.mesadmin.entity.LocationInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库位信息DAO
 * 创建人：刘福志
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public interface LocationDAO {
    //查询库位所有信息
    List<LocationInfo> SelectAllLocationInfo();

    //查询库位所有信息BYargstoreGd
    List<LocationInfo> SelectAllLocationInfoBystoreGd(String argstoreGd);
    //查询库位信息
    LocationInfo SelectLocationInfo(int lRd);

    //保存库位信息
    int InsertLocationInfo(LocationInfo locationInfo);

    //删除库位信息
    int DeleteLocationInfo(int ruid);

    int DeleteStoreGd(String storeGd);
    //更新库位信息
    int UpdateLocationInfo(LocationInfo locationInfo);

    //查询关联仓库表
    List<LocationInfo> Selectguid(String guid);

    //查询库位代码
    LocationInfo SelectlCode(String lCode);

    //查询库位名称
    LocationInfo SelectlName(String lName);

    //关联查询库存明细表
    LocationInfo SelectlocationGd(String locationGd);

    //通过批次查找
    LocationInfo SelectByBatch(String batch);
}
