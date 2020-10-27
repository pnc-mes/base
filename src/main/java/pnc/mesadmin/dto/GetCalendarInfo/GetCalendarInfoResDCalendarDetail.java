package pnc.mesadmin.dto.GetCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:32
 * @Description:
 */
public class GetCalendarInfoResDCalendarDetail {
    @JsonProperty("CalendarDate")
    private String CalendarDate;
    @JsonProperty("CalendarDetailRd")
    private int CalendarDetailRd;
    @JsonProperty("ShiftInfo")
    private ShiftInfo ShiftInfo;
    @JsonProperty("TeamInfo")
    private TeamInfo TeamInfo;
    public static class   ShiftInfo{
        @JsonProperty("ShiftRd")
        private int  ShiftRd;
        @JsonProperty("ShiftName")
        private String ShiftName;
        @JsonIgnore
        public int getShiftRd() {
            return ShiftRd;
        }
        @JsonIgnore
        public void setShiftRd(int shiftRd) {
            ShiftRd = shiftRd;
        }
        @JsonIgnore
        public String getShiftName() {
            return ShiftName;
        }
        @JsonIgnore
        public void setShiftName(String shiftName) {
            ShiftName = shiftName;
        }
    }

    public static class  TeamInfo{
        @JsonProperty("TeamRd")
        private int  TeamRd;

        @JsonProperty("TeamName")
        private String TeamName;
        @JsonIgnore
        public int getTeamRd() {
            return TeamRd;
        }
        @JsonIgnore
        public void setTeamRd(int teamRd) {
            TeamRd = teamRd;
        }
        @JsonIgnore
        public String getTeamName() {
            return TeamName;
        }
        @JsonIgnore
        public void setTeamName(String teamName) {
            TeamName = teamName;
        }
    }

    @JsonIgnore
    public String getCalendarDate() {
        return CalendarDate;
    }
    @JsonIgnore
    public void setCalendarDate(String calendarDate) {
        CalendarDate = calendarDate;
    }
    @JsonIgnore
    public int getCalendarDetailRd() {
        return CalendarDetailRd;
    }
    @JsonIgnore
    public void setCalendarDetailRd(int calendarDetailRd) {
        CalendarDetailRd = calendarDetailRd;
    }
    @JsonIgnore
    public GetCalendarInfoResDCalendarDetail.ShiftInfo getShiftInfo() {
        return ShiftInfo;
    }
    @JsonIgnore
    public void setShiftInfo(GetCalendarInfoResDCalendarDetail.ShiftInfo shiftInfo) {
        ShiftInfo = shiftInfo;
    }
    @JsonIgnore
    public GetCalendarInfoResDCalendarDetail.TeamInfo getTeamInfo() {
        return TeamInfo;
    }
    @JsonIgnore
    public void setTeamInfo(GetCalendarInfoResDCalendarDetail.TeamInfo teamInfo) {
        TeamInfo = teamInfo;
    }
}
