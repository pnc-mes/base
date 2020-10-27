package pnc.mesadmin.dto.GetAllWIPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/17.
 */
public class GetAllWIPInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllWIPInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllWIPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllWIPInfoResB body) {
        Body = body;
    }
}
