package pnc.mesadmin.dto.GetPtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetPtInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPtInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPtInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPtInfoResB body) {
        Body = body;
    }
}
