package pnc.mesadmin.dto.GetTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/6 13:38
 * @Description:
 */
public class GetTaskInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetTaskInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetTaskInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetTaskInfoResB body) {
        Body = body;
    }
}
