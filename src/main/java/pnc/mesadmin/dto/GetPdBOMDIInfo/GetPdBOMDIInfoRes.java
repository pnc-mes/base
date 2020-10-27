package pnc.mesadmin.dto.GetPdBOMDIInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPdBOMDIInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPdBOMDIInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPdBOMDIInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPdBOMDIInfoResB body) {
        Body = body;
    }
}
