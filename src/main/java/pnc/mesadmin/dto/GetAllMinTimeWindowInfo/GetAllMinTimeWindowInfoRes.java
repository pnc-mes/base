package pnc.mesadmin.dto.GetAllMinTimeWindowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetAllMinTimeWindowInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllMinTimeWindowInfoResB Body;

    @JsonIgnore

    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllMinTimeWindowInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllMinTimeWindowInfoResB body) {
        Body = body;
    }
}
