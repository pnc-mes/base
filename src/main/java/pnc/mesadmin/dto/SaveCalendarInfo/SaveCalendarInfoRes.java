package pnc.mesadmin.dto.SaveCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCarrierFamilyInfo.SaveCarrierFamilyInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:37
 * @Description:
 */
public class SaveCalendarInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCalendarInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveCalendarInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveCalendarInfoResB body) {
        Body = body;
    }
}
