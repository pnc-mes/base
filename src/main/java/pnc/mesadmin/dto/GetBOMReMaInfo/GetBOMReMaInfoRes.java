package pnc.mesadmin.dto.GetBOMReMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetBOMReMaInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetBOMReMaInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetBOMReMaInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetBOMReMaInfoResB body) {
        Body = body;
    }
}
