package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/9.
 */
public class SaveBatchInfoRes {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveBatchInfoResB Body;
     @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveBatchInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveBatchInfoResB body) {
        Body = body;
    }
}
