package pnc.mesadmin.dto.MBaseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MBaseRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private MBaseResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public MBaseResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(MBaseResB body) {
        Body = body;
    }
}
