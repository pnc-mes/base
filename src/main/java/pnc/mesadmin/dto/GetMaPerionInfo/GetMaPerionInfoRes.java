package pnc.mesadmin.dto.GetMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @Auther: haozan
 * @Date: 2018/9/6
 * @Description:
 */
public class GetMaPerionInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMaPerionInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetMaPerionInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetMaPerionInfoResB body) {
        Body = body;
    }
}
