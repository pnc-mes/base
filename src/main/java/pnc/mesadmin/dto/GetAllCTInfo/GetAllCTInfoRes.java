package pnc.mesadmin.dto.GetAllCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCusInfo.GetAllCusInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:44
 * @Description:
 */
public class GetAllCTInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllCTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllCTInfoResB body) {
        Body = body;
    }
}
