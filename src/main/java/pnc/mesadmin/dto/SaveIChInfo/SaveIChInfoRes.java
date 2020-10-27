package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveFileInfo.SaveFileInfoResB;

/**
 * Created by PNC on 2017/8/24.
 */
public class SaveIChInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveIChInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveIChInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveIChInfoResB body) {
        Body = body;
    }
}
