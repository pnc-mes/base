package pnc.mesadmin.dto.GetAllBHChgInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetAllBHChgInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllBHChgInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllBHChgInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllBHChgInfoResB body) {
        Body = body;
    }
}
