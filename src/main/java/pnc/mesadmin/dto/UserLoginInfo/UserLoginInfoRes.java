package pnc.mesadmin.dto.UserLoginInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/6/27.
 */
public class UserLoginInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private UserLoginInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public UserLoginInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(UserLoginInfoResB body) {
        Body = body;
    }
}
