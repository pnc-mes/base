package pnc.mesadmin.dto.GetAllCMethodInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetAllCMethodInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCMethodInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllCMethodInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllCMethodInfoResB body) {
        Body = body;
    }
}
