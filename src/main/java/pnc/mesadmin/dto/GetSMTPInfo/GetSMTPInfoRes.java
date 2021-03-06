package pnc.mesadmin.dto.GetSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class GetSMTPInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetSMTPInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetSMTPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetSMTPInfoResB body) {
        Body = body;
    }
}
