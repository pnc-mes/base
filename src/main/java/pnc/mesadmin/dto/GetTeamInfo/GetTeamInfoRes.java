package pnc.mesadmin.dto.GetTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetSysInfo.GetSysInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:30
 * @Description:
 */
public class GetTeamInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetTeamInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetTeamInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetTeamInfoResB body) {
        Body = body;
    }
}
