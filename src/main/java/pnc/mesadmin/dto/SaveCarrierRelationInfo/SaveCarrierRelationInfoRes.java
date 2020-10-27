package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:46
 * @Description:
 */
public class SaveCarrierRelationInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCarrierRelationInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public SaveCarrierRelationInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(SaveCarrierRelationInfoResB body) {
        Body = body;
    }
}
