package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDDay implements Serializable {
    @JsonProperty("TimeType")
    private String TimeType;
    @JsonProperty("TimeContent")
    private String TimeContent;
    @JsonIgnore
    public String getTimeType() {
        return TimeType;
    }
    @JsonIgnore
    public void setTimeType(String timeType) {
        TimeType = timeType;
    }
    @JsonIgnore
    public String getTimeContent() {
        return TimeContent;
    }
    @JsonIgnore
    public void setTimeContent(String timeContent) {
        TimeContent = timeContent;
    }
}
