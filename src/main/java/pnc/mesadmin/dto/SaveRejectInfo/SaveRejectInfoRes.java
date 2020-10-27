package pnc.mesadmin.dto.SaveRejectInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by HAOZAN on 2018/8/6.
 */
public class SaveRejectInfoRes {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveRejectInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveRejectInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveRejectInfoResB body) {
        Body = body;
    }
}
