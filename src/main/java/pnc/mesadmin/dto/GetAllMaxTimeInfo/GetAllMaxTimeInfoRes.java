package pnc.mesadmin.dto.GetAllMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:37
 * @Description:
 */
public class GetAllMaxTimeInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllMaxTimeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllMaxTimeInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllMaxTimeInfoResB body) {
        Body = body;
    }
}
