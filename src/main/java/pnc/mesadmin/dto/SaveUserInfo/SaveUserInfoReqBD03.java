package pnc.mesadmin.dto.SaveUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/5/25.
 */
public class SaveUserInfoReqBD03 implements Serializable{

    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("Password")
    private String Password;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }

    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }

    @JsonIgnore
    public String getPassword() {
        return Password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        Password = password;
    }
}
