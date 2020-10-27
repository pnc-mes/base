package pnc.mesadmin.dto.GetRMaNTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetRMaNInfo.GetRMaNInfoResB;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetRMaNTotalInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetRMaNTotalInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetRMaNTotalInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetRMaNTotalInfoResB body) {
        Body = body;
    }
}
