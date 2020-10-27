package pnc.mesadmin.dto.GetPurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetRCGInfo.GetRCGInfoResB;

/**
 * Created by PNC on 2017/9/6.
 */
public class GetPurchInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPurchInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetPurchInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetPurchInfoResB body) {
        Body = body;
    }
}
