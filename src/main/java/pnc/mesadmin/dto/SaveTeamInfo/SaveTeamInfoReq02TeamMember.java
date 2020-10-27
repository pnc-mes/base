package pnc.mesadmin.dto.SaveTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 10:27
 * @Description:
 */
public class SaveTeamInfoReq02TeamMember {
    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("UserName")
    private String UserName;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }
    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }
    @JsonIgnore
    public String getUserName() {
        return UserName;
    }
    @JsonIgnore
    public void setUserName(String userName) {
        UserName = userName;
    }
}
