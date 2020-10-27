package pnc.mesadmin.dto.SaveCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCyclePlanInfoReq00DayInfo implements Serializable {
    @JsonProperty("TimeContent")
    private String TimeContent;

    @JsonProperty("TimeType")
    private String TimeType;

    @JsonIgnore
    public String getTimeContent() {
        return TimeContent;
    }
    @JsonIgnore
    public void setTimeContent(String timeContent) {
        TimeContent = timeContent;
    }
    @JsonIgnore
    public String getTimeType() {
        return TimeType;
    }
    @JsonIgnore
    public void setTimeType(String timeType) {
        TimeType = timeType;
    }
}
