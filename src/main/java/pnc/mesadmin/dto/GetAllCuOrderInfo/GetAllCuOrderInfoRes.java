package pnc.mesadmin.dto.GetAllCuOrderInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllCuOrderInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCuOrderInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllCuOrderInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllCuOrderInfoResB body) {
        Body = body;
    }
}
