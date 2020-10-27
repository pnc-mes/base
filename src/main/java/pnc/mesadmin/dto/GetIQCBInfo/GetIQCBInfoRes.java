package pnc.mesadmin.dto.GetIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/10.
 */
public class GetIQCBInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetIQCBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetIQCBInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetIQCBInfoResB body) {
        Body = body;
    }


}
