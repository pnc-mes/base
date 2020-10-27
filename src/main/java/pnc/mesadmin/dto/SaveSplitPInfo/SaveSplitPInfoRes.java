package pnc.mesadmin.dto.SaveSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/27.
 */
public class SaveSplitPInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveSplitPInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveSplitPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveSplitPInfoResB body) {
        Body = body;
    }
}
