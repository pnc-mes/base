package pnc.mesadmin.dto.GetAllFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class GetAllFrePlanInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllFrePlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllFrePlanInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllFrePlanInfoResB body) {
        Body = body;
    }
}
