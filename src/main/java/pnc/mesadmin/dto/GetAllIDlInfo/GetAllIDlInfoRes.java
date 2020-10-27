package pnc.mesadmin.dto.GetAllIDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoResB;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllIDlInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllIDlInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllIDlInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllIDlInfoResB body) {
        Body = body;
    }
}
