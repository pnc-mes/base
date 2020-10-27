package pnc.mesadmin.dto.GetAllHPLInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/14.
 */
public class GetAllHPLInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllHPLInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllHPLInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllHPLInfoResB body) {
        Body = body;
    }
}
