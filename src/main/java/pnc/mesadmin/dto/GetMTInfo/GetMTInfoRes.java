package pnc.mesadmin.dto.GetMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/8/21.
 */
public class GetMTInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMTInfoResB body) {
        Body = body;
    }
}
