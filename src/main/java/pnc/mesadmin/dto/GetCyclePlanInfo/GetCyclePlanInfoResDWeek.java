package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDWeek implements Serializable {
    @JsonProperty("WeekContent")
    private String WeekContent;
    @JsonIgnore
    public String getWeekContent() {
        return WeekContent;
    }
    @JsonIgnore
    public void setWeekContent(String weekContent) {
        WeekContent = weekContent;
    }
}
