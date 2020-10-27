package pnc.mesadmin.dto.GetSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/16.
 */
public class GetSGInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetSGInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetSGInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetSGInfoResB body) {
        Body = body;
    }
}
