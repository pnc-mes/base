package pnc.mesadmin.dto.GetCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCKBarInfo.GetCKBarInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/27 09:10
 * @Description:
 */
public class GetCarrierRelationInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCarrierRelationInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetCarrierRelationInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetCarrierRelationInfoResB body) {
        Body = body;
    }
}
