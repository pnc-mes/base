package pnc.mesadmin.dto.SavePackInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Created by xfxi on 2017/6/21.
 */
public class SavePackInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SavePackInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SavePackInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SavePackInfoResB body) {
        Body = body;
    }
}
