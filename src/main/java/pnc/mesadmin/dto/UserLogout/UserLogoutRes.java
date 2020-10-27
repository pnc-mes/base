package pnc.mesadmin.dto.UserLogout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by test on 2017/6/27.
 */
public class UserLogoutRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private UserLogoutResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public UserLogoutResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(UserLogoutResB body) {
        Body = body;
    }
}
