package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCMWInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCMWInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCMWInfoResB body) {
        Body = body;
    }
}
