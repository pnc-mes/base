package pnc.mesadmin.dto.SaveRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveRMInfoRes.SaveRMInfoResB;

/**
 * Created by PNC on 2017/9/26.
 */
public class SaveRMaNInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveRMaNInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveRMaNInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveRMaNInfoResB body) {
        Body = body;
    }
}
