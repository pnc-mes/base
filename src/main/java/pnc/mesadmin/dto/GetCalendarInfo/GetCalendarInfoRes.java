package pnc.mesadmin.dto.GetCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetBVInfo.GetBVInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:31
 * @Description:
 */
public class GetCalendarInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetCalendarInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetCalendarInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetCalendarInfoResB body) {
        Body = body;
    }
}
