package pnc.mesadmin.dto.GetMaTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/2.
 */
public class GetMaTypeInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMaTypeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetMaTypeInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetMaTypeInfoResB body) {
        Body = body;
    }
}
