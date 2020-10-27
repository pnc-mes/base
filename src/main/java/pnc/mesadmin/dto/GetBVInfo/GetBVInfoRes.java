package pnc.mesadmin.dto.GetBVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetBVInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetBVInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetBVInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetBVInfoResB body) {
        Body = body;
    }
}
