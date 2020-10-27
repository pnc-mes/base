package pnc.mesadmin.dto.GetAllMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @Auther: haozan
 * @Date: 2018/9/6
 * @Description:
 */
public class GetAllMaPerionInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllMaPerionInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllMaPerionInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllMaPerionInfoResB body) {
        Body = body;
    }
}
