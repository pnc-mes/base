package pnc.mesadmin.dto.SaveCKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveBdFileInfo.SaveBdFileInfoResB;

/**
 * Created by PNC on 2017/8/11.
 */
public class SaveCKInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCKInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveCKInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveCKInfoResB body) {
        Body = body;
    }
}
