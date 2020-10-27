package pnc.mesadmin.dto.SaveUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 复制用户信息 2017/7/7.
 */
public class SaveUserInfoReqBD04 implements Serializable{
    @JsonProperty("UserRd")
    private int UserRd;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }

    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }
}
