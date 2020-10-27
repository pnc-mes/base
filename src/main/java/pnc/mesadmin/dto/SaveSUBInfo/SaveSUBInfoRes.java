package pnc.mesadmin.dto.SaveSUBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/10.
 */
public class SaveSUBInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveSUBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveSUBInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveSUBInfoResB body) {
        Body = body;
    }
}
