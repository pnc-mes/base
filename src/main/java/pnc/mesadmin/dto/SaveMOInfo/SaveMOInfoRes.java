package pnc.mesadmin.dto.SaveMOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class SaveMOInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMOInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveMOInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveMOInfoResB body) {
        Body = body;
    }
}
