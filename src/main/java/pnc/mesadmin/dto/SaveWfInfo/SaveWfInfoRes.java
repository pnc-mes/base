package pnc.mesadmin.dto.SaveWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/10.
 */
public class SaveWfInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveWfInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveWfInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveWfInfoResB body) {
        Body = body;
    }
}
