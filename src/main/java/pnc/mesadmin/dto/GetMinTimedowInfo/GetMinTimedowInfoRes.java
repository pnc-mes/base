package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMinTimedowInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetMinTimedowInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetMinTimedowInfoResB body) {
        Body = body;
    }
}
