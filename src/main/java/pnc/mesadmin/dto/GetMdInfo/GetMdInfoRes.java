package pnc.mesadmin.dto.GetMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMdInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMdInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMdInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMdInfoResB body) {
        Body = body;
    }
}
