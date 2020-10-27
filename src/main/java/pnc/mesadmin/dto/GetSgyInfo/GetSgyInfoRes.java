package pnc.mesadmin.dto.GetSgyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public class GetSgyInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetSgyInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetSgyInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetSgyInfoResB body) {
        Body = body;
    }
}
