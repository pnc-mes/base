package pnc.mesadmin.dto.GetPAppInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/9/6.
 */
public class GetPAppInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPAppInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPAppInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPAppInfoResB body) {
        Body = body;
    }
}
