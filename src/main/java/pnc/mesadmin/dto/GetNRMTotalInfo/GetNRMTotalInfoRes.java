package pnc.mesadmin.dto.GetNRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMTotalInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetNRMTotalInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetNRMTotalInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetNRMTotalInfoResB body) {
        Body = body;
    }
}
