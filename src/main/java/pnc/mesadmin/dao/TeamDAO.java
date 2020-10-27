package pnc.mesadmin.dao;

import pnc.mesadmin.entity.TeamInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:00
 * @Description:
 */
public interface TeamDAO {
    //获取团队列表信息
    List<TeamInfo> SelectAllTeamInfo();

    //获取团队信息
    TeamInfo SelectTeamInfoByRuid(int ruid);

    //新增团队信息
    void InsertTeamInfo(TeamInfo teamInfo);

    //删除团队信息
    int DeleteTeamInfoByRuid(int ruid);

    //根据团队名字查询团队信息
    TeamInfo SelectTeamInfoByTeamName(String teamName);

    //修改团队信息
    int UpdateTeamInfo(TeamInfo teamInfo);

    //根据guid 查询一条信息
    TeamInfo SelectTeamInfoByGuid(String guid);


}
