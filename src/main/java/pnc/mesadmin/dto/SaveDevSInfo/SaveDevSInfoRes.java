package pnc.mesadmin.dto.SaveDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveDicInfo.SaveDicInfoResB;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class SaveDevSInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveDevSInfoResB Body;


    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveDevSInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveDevSInfoResB body) {
        Body = body;
    }

}
