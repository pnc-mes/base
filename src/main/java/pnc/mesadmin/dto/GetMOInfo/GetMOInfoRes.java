package pnc.mesadmin.dto.GetMOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class GetMOInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMOInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMOInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMOInfoResB body) {
        Body = body;
    }
}
