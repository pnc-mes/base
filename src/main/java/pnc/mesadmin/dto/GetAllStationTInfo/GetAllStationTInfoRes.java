package pnc.mesadmin.dto.GetAllStationTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by haozan on 2018/11/12.
 */
public class GetAllStationTInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllStationTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllStationTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllStationTInfoResB body) {
        Body = body;
    }
}
