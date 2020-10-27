package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCheckPlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveCheckPlanInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveCheckPlanInfoResB body) {
        Body = body;
    }
}
