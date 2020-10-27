package pnc.mesadmin.dto.GetAllPurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllRCGInfo.GetAllRCGInfoResB;

/**
 * Created by PNC on 2017/8/23.
 */
public class GetAllPurchInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllPurchInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllPurchInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllPurchInfoResB body) {
        Body = body;
    }
}
