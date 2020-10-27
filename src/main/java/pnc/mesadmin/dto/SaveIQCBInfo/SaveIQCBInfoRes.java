package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveIQCBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveIQCBInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveIQCBInfoResB body) {
        Body = body;
    }


}
