package pnc.mesadmin.dto.SaveCMethodInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class SaveCMethodInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCMethodInfoResB Body;
    @JsonIgnore

    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveCMethodInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveCMethodInfoResB body) {
        Body = body;
    }
}
