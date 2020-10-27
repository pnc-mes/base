package pnc.mesadmin.dto.GetIncInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetGCInfo.GetGCInfoResB;

/**
 * Created by PNC on 2017/9/7.
 */
public class GetIncInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetIncInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetIncInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetIncInfoResB body) {
        Body = body;
    }
}
