package pnc.mesadmin.dto.GetAllTeamsInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllToolFamilyInfo.GetAllToolFamilyInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 08:52
 * @Description:
 */
public class GetAllTeamsInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllTeamsInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllTeamsInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllTeamsInfoResB body) {
        Body = body;
    }
}
