package pnc.mesadmin.dto.SaveMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveMdInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMdInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveMdInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveMdInfoResB body) {
        Body = body;
    }
}
