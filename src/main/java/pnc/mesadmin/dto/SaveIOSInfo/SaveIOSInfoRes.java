package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/14.
 */
public class SaveIOSInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveIOSInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveIOSInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveIOSInfoResB body) {
        Body = body;
    }
}
