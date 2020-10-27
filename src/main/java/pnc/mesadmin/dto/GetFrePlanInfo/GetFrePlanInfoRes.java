package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class GetFrePlanInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetFrePlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetFrePlanInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetFrePlanInfoResB body) {
        Body = body;
    }
}
