package pnc.mesadmin.dto.SaveCLevelInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class SaveCLevelInfoRes implements Serializable {


    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCLevelInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveCLevelInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveCLevelInfoResB body) {
        Body = body;
    }
}
