package pnc.mesadmin.dto.SavePackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/26.
 */
public class SavePackSpecificationInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SavePackSpecificationInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SavePackSpecificationInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SavePackSpecificationInfoResB body) {
        Body = body;
    }
}
