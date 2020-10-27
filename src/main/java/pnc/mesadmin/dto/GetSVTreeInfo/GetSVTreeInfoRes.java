package pnc.mesadmin.dto.GetSVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/7/14.
 */
public class GetSVTreeInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetSVTreeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetSVTreeInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetSVTreeInfoResB body) {
        Body = body;
    }
}
