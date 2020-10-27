package pnc.mesadmin.dto.GYMonitor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/9
 */
public class SaveGYYJRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveGYYJResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveGYYJResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveGYYJResB body) {
        Body = body;
    }
}
