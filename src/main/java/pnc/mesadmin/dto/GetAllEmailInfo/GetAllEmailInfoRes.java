package pnc.mesadmin.dto.GetAllEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/4.
 */
public class GetAllEmailInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllEmailInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    } @JsonIgnore

    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllEmailInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllEmailInfoResB body) {
        Body = body;
    }
}
