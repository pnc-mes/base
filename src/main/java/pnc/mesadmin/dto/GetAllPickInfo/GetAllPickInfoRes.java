package pnc.mesadmin.dto.GetAllPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllPrInfo.GetAllPrInfoResB;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetAllPickInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllPickInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllPickInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllPickInfoResB body) {
        Body = body;
    }
}
