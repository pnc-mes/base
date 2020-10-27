package pnc.mesadmin.dto.newmove.GetQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/3.
 */
public class GetQCMInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetQCMInfoResB Body;

    @JsonIgnore

    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetQCMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetQCMInfoResB body) {
        Body = body;
    }
}
