package pnc.mesadmin.dto.GetAllRoleInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/17.
 */
public class GetAllRoleInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllRoleInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllRoleInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllRoleInfoResB body) {
        Body = body;
    }
}
