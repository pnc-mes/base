package pnc.mesadmin.dto.GetPackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPackSpecificationInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPackSpecificationInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetPackSpecificationInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetPackSpecificationInfoResB body) {
        Body = body;
    }
}
