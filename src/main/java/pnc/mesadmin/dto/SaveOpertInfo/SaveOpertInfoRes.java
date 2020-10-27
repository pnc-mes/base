package pnc.mesadmin.dto.SaveOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/3.
 */
public class SaveOpertInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveOpertInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveOpertInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveOpertInfoResB body) {
        Body = body;
    }


}
