package pnc.mesadmin.dto.GetAllMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/2.
 */
public class GetAllMaInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllMaInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllMaInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllMaInfoResB body) {
        Body = body;
    }
}
