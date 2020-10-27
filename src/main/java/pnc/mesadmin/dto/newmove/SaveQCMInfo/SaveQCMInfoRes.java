package pnc.mesadmin.dto.newmove.SaveQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/7.
 */
public class SaveQCMInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveQCMInfoResB Body;

    @JsonIgnore

    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveQCMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveQCMInfoResB body) {
        Body = body;
    }
}
