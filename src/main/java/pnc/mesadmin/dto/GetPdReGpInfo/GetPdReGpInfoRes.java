package pnc.mesadmin.dto.GetPdReGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/6.
 */
public class GetPdReGpInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPdReGpInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPdReGpInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPdReGpInfoResB body) {
        Body = body;
    }
}
