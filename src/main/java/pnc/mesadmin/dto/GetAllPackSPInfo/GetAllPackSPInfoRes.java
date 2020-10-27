package pnc.mesadmin.dto.GetAllPackSPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetAllPackSPInfoRes implements Serializable {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Body")
    private GetAllPackSPInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public GetAllPackSPInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllPackSPInfoResB body) {
        Body = body;
    }
}
