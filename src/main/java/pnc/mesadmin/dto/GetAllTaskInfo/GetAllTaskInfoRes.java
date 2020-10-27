package pnc.mesadmin.dto.GetAllTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 11:03
 * @Description:
 */
public class GetAllTaskInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllTaskInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllTaskInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllTaskInfoResB body) {
        Body = body;
    }
}
