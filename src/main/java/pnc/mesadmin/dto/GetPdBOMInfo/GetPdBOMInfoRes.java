package pnc.mesadmin.dto.GetPdBOMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/6.
 */
public class GetPdBOMInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPdBOMInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPdBOMInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPdBOMInfoResB body) {
        Body = body;
    }
}
