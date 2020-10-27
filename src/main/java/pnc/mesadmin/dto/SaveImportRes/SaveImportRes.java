package pnc.mesadmin.dto.SaveImportRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/21 0021.
 */
public class SaveImportRes {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private SaveImportResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveImportResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveImportResB body) {
        this.Body = body;
    }
}
