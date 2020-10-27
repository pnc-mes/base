package pnc.mesadmin.dto.SaveMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class SaveMinTimedowInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMinTimedowInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveMinTimedowInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveMinTimedowInfoResB body) {
        Body = body;
    }
}
