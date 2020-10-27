package pnc.mesadmin.dto.GetAllCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCyclePlanInfo.GetAllCyclePlanInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:51
 * @Description:
 */
public class GetAllCheckPlanInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCheckPlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllCheckPlanInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllCheckPlanInfoResB body) {
        Body = body;
    }
}
