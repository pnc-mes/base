package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.AQLRuleDtlInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-22
 **/
public interface AQLRuleDtlDAO {
    List<AQLRuleDtlInfo> SelectAllByAQLGd(String aqlGd);

    AQLRuleDtlInfo SelectByRuid(Integer ruid);

    AQLRuleDtlInfo SelectByGuid(String guid);

    int Insert(AQLRuleDtlInfo model);

    int DeleteByAQLGd(String aqlGd);

    List<AQLRuleDtlInfo> SelectAllByAQLGdAndQJNum(@Param("aqlGd") String aqlGd, @Param("qjNum") Integer qjNum);
}
