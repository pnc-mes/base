package pnc.mesadmin.dto.GetAllWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetAllWfInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllWfInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllWfInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllWfInfoResB body) {
        Body = body;
    }
}
