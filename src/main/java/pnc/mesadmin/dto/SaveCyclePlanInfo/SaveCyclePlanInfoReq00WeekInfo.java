package pnc.mesadmin.dto.SaveCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCyclePlanInfoReq00WeekInfo implements Serializable {
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
