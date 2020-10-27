package pnc.mesadmin.dto.SaveDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveDevSInfo.SaveDevSInfoResB;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/21.
 */
public class SaveDevSMInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveDevSMInfoResB Body;


    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveDevSMInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveDevSMInfoResB body) {
        Body = body;
    }
}
