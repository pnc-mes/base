package pnc.mesadmin.dto.GetRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetRKTInfo.GetRKTInfoResB;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetRMaNInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetRMaNInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetRMaNInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetRMaNInfoResB body) {
        Body = body;
    }
}
