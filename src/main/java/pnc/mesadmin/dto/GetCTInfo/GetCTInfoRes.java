package pnc.mesadmin.dto.GetCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCusInfo.GetCusInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:58
 * @Description:
 */
public class GetCTInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCTInfoResB body) {
        Body = body;
    }
}
