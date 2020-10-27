package pnc.mesadmin.dto.SavePtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/31.
 */
public class SavePtInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SavePtInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SavePtInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SavePtInfoResB body) {
        Body = body;
    }
}
