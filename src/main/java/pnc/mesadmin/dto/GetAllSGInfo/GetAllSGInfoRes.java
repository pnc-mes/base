package pnc.mesadmin.dto.GetAllSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/16.
 */
public class GetAllSGInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllSGInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllSGInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllSGInfoResB body) {
        Body = body;
    }
}
