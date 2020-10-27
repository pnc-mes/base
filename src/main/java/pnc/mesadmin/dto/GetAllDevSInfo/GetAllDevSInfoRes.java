package pnc.mesadmin.dto.GetAllDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllDevInfo.GetAllDevInfoResB;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class GetAllDevSInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllDevSInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllDevSInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllDevSInfoResB body) {
        Body = body;
    }
}
