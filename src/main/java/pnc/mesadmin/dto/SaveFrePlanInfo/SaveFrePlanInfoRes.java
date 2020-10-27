package pnc.mesadmin.dto.SaveFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class SaveFrePlanInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveFrePlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveFrePlanInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveFrePlanInfoResB body) {
        Body = body;
    }
}
