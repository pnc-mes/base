package pnc.mesadmin.dto.GetMenuInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetMenuInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMenuInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMenuInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMenuInfoResB body) {
        Body = body;
    }
}
