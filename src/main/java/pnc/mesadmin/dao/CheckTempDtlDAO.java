package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.CheckTempDtlInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-21
 **/
public interface CheckTempDtlDAO {
    List<CheckTempDtlInfo> SelectAllByTempGuid(String tempGuid);

    CheckTempDtlInfo SelectByRuid(Integer ruid);

    CheckTempDtlInfo SelectByGuid(String guid);

    int Insert(CheckTempDtlInfo model);

    int DeleteByTempGuid(String tempGuid);

    //条件：检验类型
    List<CheckTempDtlInfo> SelectAllByTempGuidAndRelType(@Param("tempGuid") String tempGuid, @Param("relType") String relType);
}
