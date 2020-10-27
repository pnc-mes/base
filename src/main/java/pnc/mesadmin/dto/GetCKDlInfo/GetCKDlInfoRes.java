package pnc.mesadmin.dto.GetCKDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKDlInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetCKDlInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetCKDlInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetCKDlInfoResB body) {
        Body = body;
    }
}
