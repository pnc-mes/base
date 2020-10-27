package pnc.mesadmin.dto.GetPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetPrInfo.GetPrInfoResB;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetPickInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPickInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPickInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPickInfoResB body) {
        Body = body;
    }
}
