package pnc.mesadmin.dto.GetAllURInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/23.
 */
public class GetAllURInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllURInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllURInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllURInfoResB body) {
        Body = body;
    }
}
