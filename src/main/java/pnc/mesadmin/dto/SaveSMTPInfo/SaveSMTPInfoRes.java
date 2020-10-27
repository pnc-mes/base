package pnc.mesadmin.dto.SaveSMTPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/3.
 */
public class SaveSMTPInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveSMTPInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveSMTPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveSMTPInfoResB body) {
        Body = body;
    }
}
