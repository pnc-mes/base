package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:16
 * @Description:
 */
public class CalendarDetailInfo {
    private int ruid;
    private String guid;
    private String calendarGD;
    private Date calendarDate;
    private String shiftGD;
    private String teamGD;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getCalendarGD() {
        return calendarGD;
    }

    public void setCalendarGD(String calendarGD) {
        this.calendarGD = calendarGD;
    }

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    public String getShiftGD() {
        return shiftGD;
    }

    public void setShiftGD(String shiftGD) {
        this.shiftGD = shiftGD;
    }

    public String getTeamGD() {
        return teamGD;
    }

    public void setTeamGD(String teamGD) {
        this.teamGD = teamGD;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
