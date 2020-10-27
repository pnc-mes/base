package pnc.mesadmin.dto.GetRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class GetRMTotalInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetRMTotalInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetRMTotalInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetRMTotalInfoResB body) {
        Body = body;
    }
}
