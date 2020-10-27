package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.AQLRuleInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-22
 **/
public interface AQLRuleDAO {
    List<AQLRuleInfo> SelectAll();

    AQLRuleInfo SelectByRuid(Integer ruid);

    AQLRuleInfo SelectByGuid(String guid);

    AQLRuleInfo SelectByName(String name);

    int Insert(AQLRuleInfo model);

    int UpdateByGuid(AQLRuleInfo model);

    int DeleteByGuid(String guid);

    //更具aqlGd和水平GD
    AQLRuleInfo SelectByAqlGdAndCLevelGd(@Param("aqlGd") String aqlGd, @Param("clevelGd") String clevelGd);
}
