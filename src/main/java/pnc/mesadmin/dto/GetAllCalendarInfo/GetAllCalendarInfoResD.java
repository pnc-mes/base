package pnc.mesadmin.dto.GetAllCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:29
 * @Description:
 */
public class GetAllCalendarInfoResD {
    @JsonProperty("CalendarRd")
    private int CalendarRd;

    @JsonProperty("CalendarName")
    private String CalendarName;
    @JsonIgnore
    public int getCalendarRd() {
        return CalendarRd;
    }
    @JsonIgnore
    public void setCalendarRd(int calendarRd) {
        CalendarRd = calendarRd;
    }
    @JsonIgnore
    public String getCalendarName() {
        return CalendarName;
    }
    @JsonIgnore
    public void setCalendarName(String calendarName) {
        CalendarName = calendarName;
    }
}
