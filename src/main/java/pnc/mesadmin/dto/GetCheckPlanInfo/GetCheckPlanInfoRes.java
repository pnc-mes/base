package pnc.mesadmin.dto.GetCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:24
 * @Description:
 */
public class GetCheckPlanInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCheckPlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCheckPlanInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCheckPlanInfoResB body) {
        Body = body;
    }
}
