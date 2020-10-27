package pnc.mesadmin.dto.GetPtBFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/31.
 */
public class GetPtBFInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPtBFInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPtBFInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPtBFInfoResB body) {
        Body = body;
    }
}
