package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetMdInfo.GetMdInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 11:30
 * @Description:
 */
public class GetMaxTimeInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMaxTimeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetMaxTimeInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetMaxTimeInfoResB body) {
        Body = body;
    }
}
