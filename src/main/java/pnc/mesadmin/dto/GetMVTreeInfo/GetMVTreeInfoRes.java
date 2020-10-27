package pnc.mesadmin.dto.GetMVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVTreeInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMVTreeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMVTreeInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMVTreeInfoResB body) {
        Body = body;
    }
}
