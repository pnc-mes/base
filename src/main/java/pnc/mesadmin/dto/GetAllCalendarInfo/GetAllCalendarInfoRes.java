package pnc.mesadmin.dto.GetAllCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCarrierFamilyInfo.GetAllCarrierFamilyInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:28
 * @Description:
 */
public class GetAllCalendarInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllCalendarInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllCalendarInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllCalendarInfoResB body) {
        Body = body;
    }
}
