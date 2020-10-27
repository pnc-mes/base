package pnc.mesadmin.dto.SaveEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/5.
 */
public class SaveEmailInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveEmailInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveEmailInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveEmailInfoResB body) {
        Body = body;
    }
}
