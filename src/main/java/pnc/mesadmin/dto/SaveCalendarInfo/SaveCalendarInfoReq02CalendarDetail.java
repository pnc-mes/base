package pnc.mesadmin.dto.SaveCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:39
 * @Description:
 */
public class SaveCalendarInfoReq02CalendarDetail {
    @JsonProperty("CalendarDate")
    private String CalendarDate;


    @JsonProperty("ShiftRd")
    private int ShiftRd;


    @JsonProperty("TeamRd")
    private int TeamRd;
    @JsonIgnore
    public String getCalendarDate() {
        return CalendarDate;
    }
    @JsonIgnore
    public void setCalendarDate(String calendarDate) {
        CalendarDate = calendarDate;
    }

    @JsonIgnore
    public int getShiftRd() {
        return ShiftRd;
    }
    @JsonIgnore
    public void setShiftRd(int shiftRd) {
        ShiftRd = shiftRd;
    }

    @JsonIgnore
    public int getTeamRd() {
        return TeamRd;
    }
    @JsonIgnore
    public void setTeamRd(int teamRd) {
        TeamRd = teamRd;
    }
}
