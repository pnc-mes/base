package pnc.mesadmin.dto.GetAllMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAllMdInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllMdInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllMdInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllMdInfoResB body) {
        Body = body;
    }
}
