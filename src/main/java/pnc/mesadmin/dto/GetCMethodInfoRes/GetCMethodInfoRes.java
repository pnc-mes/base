package pnc.mesadmin.dto.GetCMethodInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetCMethodInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCMethodInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetCMethodInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetCMethodInfoResB body) {
        Body = body;
    }
}
