package pnc.mesadmin.dto.GetRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetRoleInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetRoleInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetRoleInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetRoleInfoResB body) {
        Body = body;
    }
}
