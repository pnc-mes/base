package pnc.mesadmin.dto.SaveImportInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/2.
 */
public class SaveImportInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveImportInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveImportInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveImportInfoResB body) {
        Body = body;
    }
}
