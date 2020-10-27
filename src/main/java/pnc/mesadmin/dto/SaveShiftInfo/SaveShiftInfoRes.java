package pnc.mesadmin.dto.SaveShiftInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/20.
 */
public class SaveShiftInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveShiftInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveShiftInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveShiftInfoResB body) {
        Body = body;
    }
}
