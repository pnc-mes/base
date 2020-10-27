package pnc.mesadmin.dto.GetCMSRInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/10.
 */
public class GetCMSRInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCMSRInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCMSRInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCMSRInfoResB body) {
        Body = body;
    }
}
