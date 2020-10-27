package pnc.mesadmin.dto.GetCLevelInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetCLevelInfoRes implements Serializable {


    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCLevelInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCLevelInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCLevelInfoResB body) {
        Body = body;
    }
}
