package pnc.mesadmin.dto.SaveRMInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class SaveRMInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveRMInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveRMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveRMInfoResB body) {
        Body = body;
    }
}
