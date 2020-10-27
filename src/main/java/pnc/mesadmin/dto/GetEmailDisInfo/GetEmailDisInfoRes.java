package pnc.mesadmin.dto.GetEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:39
 * @Description:
 */
public class GetEmailDisInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetEmailDisInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetEmailDisInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetEmailDisInfoResB body) {
        Body = body;
    }
}
