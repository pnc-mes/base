package pnc.mesadmin.dao;

import pnc.mesadmin.entity.TeamLineInfo;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/14 09:33
 * @Description:
 */
public interface TeamLineDAO {
    List<TeamLineInfo> SelectAllTeamIineInfoByteamGd(String teamGd);

    void InsertTeamIineInfo(TeamLineInfo teamLineInfo);

    int deleteTeamIineInfo(int ruid);
}
