package pnc.mesadmin.dto.SaveRCGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/8/14.
 */
public class SaveRCGpInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveRCGpInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String Status) {
        this.Status = Status;
    }
    @JsonIgnore
    public SaveRCGpInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveRCGpInfoResB Body) {
        this.Body = Body;
    }
}
