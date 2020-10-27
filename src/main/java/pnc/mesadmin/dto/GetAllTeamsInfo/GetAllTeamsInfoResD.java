package pnc.mesadmin.dto.GetAllTeamsInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 08:53
 * @Description:
 */
public class GetAllTeamsInfoResD {
    @JsonProperty("TeamRd")
    private int TeamRd;
    @JsonProperty("TeamName")
    private String TeamName;
    @JsonIgnore
    public int getTeamRd() {
        return TeamRd;
    }
    @JsonIgnore
    public void setTeamRd(int teamRd) {
        TeamRd = teamRd;
    }
    @JsonIgnore
    public String getTeamName() {
        return TeamName;
    }
    @JsonIgnore
    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
}
