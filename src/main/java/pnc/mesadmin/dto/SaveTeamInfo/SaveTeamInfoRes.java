package pnc.mesadmin.dto.SaveTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveSupInfo.SaveSupInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 10:20
 * @Description:
 */
public class SaveTeamInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveTeamInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveTeamInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveTeamInfoResB body) {
        Body = body;
    }
}
