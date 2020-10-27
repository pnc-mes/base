package pnc.mesadmin.dto.GetWfVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/14.
 */
public class GetWfVTreeInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetWfVTreeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetWfVTreeInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetWfVTreeInfoResB body) {
        Body = body;
    }
}
