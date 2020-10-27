package pnc.mesadmin.dto.SaveMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Created by xfxi on 2017/6/1.
 */
public class SaveMaInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMaInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveMaInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveMaInfoResB body) {
        Body = body;
    }
}
