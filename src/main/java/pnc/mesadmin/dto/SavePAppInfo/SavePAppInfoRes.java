package pnc.mesadmin.dto.SavePAppInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/9/6.
 */
public class SavePAppInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SavePAppInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SavePAppInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SavePAppInfoResB body) {
        Body = body;
    }
}
