package pnc.mesadmin.dto.GetEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/4.
 */
public class GetEmailInfoRes implements Serializable{


    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetEmailInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetEmailInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetEmailInfoResB body) {
        Body = body;
    }
}
