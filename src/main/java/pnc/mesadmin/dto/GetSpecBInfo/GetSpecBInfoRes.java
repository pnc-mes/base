package pnc.mesadmin.dto.GetSpecBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/13.
 */
public class GetSpecBInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetSpecBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetSpecBInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetSpecBInfoResB body) {
        Body = body;
    }
}

