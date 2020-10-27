package pnc.mesadmin.dto.GetAllSgyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public class GetAllSgyInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllSgyInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllSgyInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllSgyInfoResB body) {
        Body = body;
    }
}
