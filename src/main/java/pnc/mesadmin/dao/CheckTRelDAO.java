package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.CheckTRelInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 检验模板关联信息DAO
 * @author: yin.yang
 * @create: 2019-03-25
 **/
public interface CheckTRelDAO {
    List<CheckTRelInfo> SelectAll();

    CheckTRelInfo SelectByRuid(Integer ruid);

    CheckTRelInfo SelectByGuid(String guid);

    CheckTRelInfo SelectByName(String name);

    int Insert(CheckTRelInfo model);

    int UpdateByGuid(CheckTRelInfo model);

    int DeleteByGuid(String guid);

    //根据模板关联类型and 关联类型 and 关联标识
    List<CheckTRelInfo> SelectByTempRelTypeAndRelTypeAndRelGd(@Param("TempRelType") String TempRelType,
                                                              @Param("RelType") String RelType,
                                                              @Param("RelGd") String RelGd);
}
