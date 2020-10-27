package pnc.mesadmin.dto.GetCKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResB;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKBarInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCKBarInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCKBarInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCKBarInfoResB body) {
        Body = body;
    }
}
