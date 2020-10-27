package pnc.mesadmin.dto.GetDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetDevInfo.GetDevInfoResB;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class GetDevSInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetDevSInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetDevSInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetDevSInfoResB body) {
        Body = body;
    }
}
