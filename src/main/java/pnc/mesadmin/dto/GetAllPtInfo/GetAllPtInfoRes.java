package pnc.mesadmin.dto.GetAllPtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetAllPtInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllPtInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllPtInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllPtInfoResB body) {
        Body = body;
    }
}
