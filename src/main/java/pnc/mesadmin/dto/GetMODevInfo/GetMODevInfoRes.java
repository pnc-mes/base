package pnc.mesadmin.dto.GetMODevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/29.
 */
public class GetMODevInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMODevInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMODevInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMODevInfoResB body) {
        Body = body;
    }
}
