package pnc.mesadmin.dto.SaveCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:42
 * @Description:
 */
public class SaveCalendarInfoReq01 {
    @JsonProperty("CalendarRd")
    private int CalendarRd;
    @JsonIgnore
    public int getCalendarRd() {
        return CalendarRd;
    }
    @JsonIgnore
    public void setCalendarRd(int calendarRd) {
        CalendarRd = calendarRd;
    }
}
