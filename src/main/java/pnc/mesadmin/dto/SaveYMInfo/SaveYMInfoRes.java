package pnc.mesadmin.dto.SaveYMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/6 0006.
 */
public class SaveYMInfoRes {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveYMInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveYMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveYMInfoResB body) {
        Body = body;
    }
}
