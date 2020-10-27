package pnc.mesadmin.dto.SaveRePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/14.
 */
public class SaveRePrInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveRePrInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveRePrInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveRePrInfoResB body) {
        Body = body;
    }
}
