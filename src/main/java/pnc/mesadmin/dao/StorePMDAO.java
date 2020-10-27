package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.StorePMInfo;

import java.util.List;

/**
 * Created by liufuzhi on 2018/1/22.
 */
public interface StorePMDAO {

    //根据仓库guid查询权限信息
    List<StorePMInfo> SelectStorePMInfostoreGd(String guid);

    //根据pMorGd查询信息
    List<StorePMInfo> SelectStorePMInfopMorGd(String argpMorGd);

    //保存仓库权限信息
    int InsertStorePMInfo(StorePMInfo storePMInfo);

    //删除仓库权限信息
    int DeleteStorePMInfo(String storeGd);

    //更新仓库权限信息
    int UpdateStorePMInfo(StorePMInfo storePMInfo);

    //根据仓库权限的ruid查询
    StorePMInfo SelectStorePMInfoRuid(int ruid);

    //根据仓库，用户/角色信息查询
    StorePMInfo SelectStorePMInfoByUserOrRole(@Param("argStoreGd") String argStoreGd, @Param("argPMType") String argPMType, @Param("argPMorGd") String argPMorGd);
}
