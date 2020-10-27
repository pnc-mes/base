package pnc.mesadmin.dto.GetSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/26.
 */
public class GetSplitPInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetSplitPInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetSplitPInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetSplitPInfoResB body) {
        Body = body;
    }
}
