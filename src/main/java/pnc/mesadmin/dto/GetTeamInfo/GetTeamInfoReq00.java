package pnc.mesadmin.dto.GetTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:48
 * @Description:
 */
public class GetTeamInfoReq00 {
    @JsonProperty("TeamRd")
    private int TeamRd;
    @JsonIgnore
    public int getTeamRd() {
        return TeamRd;
    }
    @JsonIgnore
    public void setTeamRd(int teamRd) {
        TeamRd = teamRd;
    }
}
