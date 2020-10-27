package pnc.mesadmin.dto.GetAllDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllDevSInfo.GetAllDevSInfoResB;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/21.
 */
public class GetAllDevSMInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllDevSMInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllDevSMInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllDevSMInfoResB body) {
        Body = body;
    }
}
