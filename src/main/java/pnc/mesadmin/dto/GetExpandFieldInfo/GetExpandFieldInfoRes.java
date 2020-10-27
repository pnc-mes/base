package pnc.mesadmin.dto.GetExpandFieldInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetEmailInfo.GetEmailInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/11 18:22
 * @Description:
 */
public class GetExpandFieldInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetExpandFieldInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetExpandFieldInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetExpandFieldInfoResB body) {
        Body = body;
    }
}
