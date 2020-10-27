package pnc.mesadmin.dto.GetShiftInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/19.
 */
public class GetShiftInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetShiftInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetShiftInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetShiftInfoResB body) {
        Body = body;
    }
}
