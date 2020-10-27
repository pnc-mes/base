package pnc.mesadmin.dto.SaveSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/16.
 */
public class SaveSGInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveSGInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveSGInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveSGInfoResB body) {
        Body = body;
    }
}
