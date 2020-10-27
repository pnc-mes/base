package pnc.mesadmin.dto.GetLPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/18.
 */
public class GetLPInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetLPInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetLPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetLPInfoResB body) {
        Body = body;
    }
}
