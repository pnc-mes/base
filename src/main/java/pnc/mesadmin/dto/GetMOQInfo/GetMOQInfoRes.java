package pnc.mesadmin.dto.GetMOQInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class GetMOQInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMOQInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMOQInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMOQInfoResB body) {
        Body = body;
    }
}
