package pnc.mesadmin.dto.GetNRMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetNRMInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetNRMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetNRMInfoResB body) {
        Body = body;
    }
}
