package pnc.mesadmin.dto.GetAllRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllRoleInfo.GetAllRoleInfoResB;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetAllRMaNInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllRMaNInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllRMaNInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllRMaNInfoResB body) {
        Body = body;
    }
}
