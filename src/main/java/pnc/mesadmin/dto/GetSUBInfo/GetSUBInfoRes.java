package pnc.mesadmin.dto.GetSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/10.
 */
public class GetSUBInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetSUBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetSUBInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetSUBInfoResB body) {
        Body = body;
    }
}
