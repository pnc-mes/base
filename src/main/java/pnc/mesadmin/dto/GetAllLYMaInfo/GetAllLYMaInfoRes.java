package pnc.mesadmin.dto.GetAllLYMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/11/2.
 */
public class GetAllLYMaInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllLYMaInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllLYMaInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllLYMaInfoResB body) {
        Body = body;
    }
}
