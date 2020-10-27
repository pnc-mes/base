package pnc.mesadmin.dto.newmove.GetAllQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/3.
 */
public class GetAllQCMInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllQCMInfoResB Body;


    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllQCMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllQCMInfoResB body) {
        Body = body;
    }
}
