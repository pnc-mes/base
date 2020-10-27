package pnc.mesadmin.dto.GetBHChgDtlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetBHChgDtlInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetBHChgDtlInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetBHChgDtlInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetBHChgDtlInfoResB body) {
        Body = body;
    }
}
