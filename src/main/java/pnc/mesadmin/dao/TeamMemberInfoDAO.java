package pnc.mesadmin.dao;

import pnc.mesadmin.entity.TeamMemberInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:39
 * @Description:
 */
public interface TeamMemberInfoDAO {
    //根据主表的guid 查询所有的明细信息
    List<TeamMemberInfo> SelectTeamInfoByGuid(String guid);

    //新增团队明细信息
    void InsertTeamMemberInfo(TeamMemberInfo teamMemberInfo);

    //删除团队明细信息根据ruid
    int DeleteTeamMemberInfoByRuid(int ruid);
}
