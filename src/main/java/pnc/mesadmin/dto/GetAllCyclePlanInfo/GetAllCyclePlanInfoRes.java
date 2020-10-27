package pnc.mesadmin.dto.GetAllCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllDcInfo.GetAllDcInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:41
 * @Description:
 */
public class GetAllCyclePlanInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCyclePlanInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllCyclePlanInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllCyclePlanInfoResB body) {
        Body = body;
    }
}
