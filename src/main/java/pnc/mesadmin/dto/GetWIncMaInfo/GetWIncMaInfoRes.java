package pnc.mesadmin.dto.GetWIncMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2018/1/28.
 */
public class GetWIncMaInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetWIncMaInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetWIncMaInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetWIncMaInfoResB body) {
        Body = body;
    }
}
