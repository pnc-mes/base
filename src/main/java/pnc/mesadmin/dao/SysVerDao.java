package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.SysVerInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-11
 **/
public interface SysVerDao {

    SysVerInfo SelectSysVerInfoByGuid(String guid);

    SysVerInfo SelectSysVerInfoByRuid(Integer ruid);

    SysVerInfo SelectSysVerInfoBySysVerName(String sysVerName);

    int InsertSysverInfo(SysVerInfo model);

    int DelSysverInfo(Integer ruid);

    List<SysVerInfo> SelectAllByStatus(String status);

    int UpSysverInfo(@Param("ruid") Integer ruid, @Param("status") String status);

    List<SysVerInfo> SelectSysVerInfoAll();

}
