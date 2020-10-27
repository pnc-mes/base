package pnc.mesadmin.dto.GetAllShiftsInfo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/19.
 */
public class GetAllShiftsInfoRes implements Serializable{
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetAllShiftsInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllShiftsInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllShiftsInfoResB body) {
        Body = body;
    }
}
