package pnc.mesadmin.dto.GetAllIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllHPLInfo.GetAllHPLInfoResB;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllIChInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllIChInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllIChInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllIChInfoResB body) {
        Body = body;
    }
}
