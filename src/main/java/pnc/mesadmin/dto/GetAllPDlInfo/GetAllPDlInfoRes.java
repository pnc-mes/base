package pnc.mesadmin.dto.GetAllPDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllPDInfo.GetAllPDInfoResB;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllPDlInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllPDlInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllPDlInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllPDlInfoResB body) {
        Body = body;
    }
}
