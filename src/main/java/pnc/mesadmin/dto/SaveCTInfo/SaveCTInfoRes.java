package pnc.mesadmin.dto.SaveCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCusInfo.SaveCusInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 15:16
 * @Description:
 */
public class SaveCTInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCTInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveCTInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveCTInfoResB body) {
        Body = body;
    }
}
