package pnc.mesadmin.dto.GetAllBOMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetAllBOMInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllBOMInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllBOMInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllBOMInfoResB body) {
        Body = body;
    }
}
