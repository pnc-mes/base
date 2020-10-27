package pnc.mesadmin.dto.GetAllRCGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by test on 2017/8/11.
 */
public class GetAllRCGInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllRCGInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllRCGInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllRCGInfoResB body) {
        Body = body;
    }
}
