package pnc.mesadmin.dto.GetCKTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKTInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCKTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCKTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCKTInfoResB body) {
        Body = body;
    }
}
