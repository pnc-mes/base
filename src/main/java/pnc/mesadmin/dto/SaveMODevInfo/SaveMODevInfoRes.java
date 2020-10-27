package pnc.mesadmin.dto.SaveMODevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/29.
 */
public class SaveMODevInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMODevInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveMODevInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveMODevInfoResB body) {
        Body = body;
    }
}
