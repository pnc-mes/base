package pnc.mesadmin.dto.GetAllSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class GetAllSMTPInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetAllSMTPInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllSMTPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllSMTPInfoResB body) {
        Body = body;
    }
}
