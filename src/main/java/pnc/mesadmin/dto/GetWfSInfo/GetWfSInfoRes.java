package pnc.mesadmin.dto.GetWfSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetWfSInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetWfSInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetWfSInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetWfSInfoResB body) {
        Body = body;
    }
}
