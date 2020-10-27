package pnc.mesadmin.dto.SaveTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 10:51
 * @Description:
 */
public class SaveTeamInfoReq01 {
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
