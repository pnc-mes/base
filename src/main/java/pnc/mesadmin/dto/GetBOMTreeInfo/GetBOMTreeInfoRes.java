package pnc.mesadmin.dto.GetBOMTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetBOMTreeInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetBOMTreeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetBOMTreeInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetBOMTreeInfoResB body) {
        Body = body;
    }
}
