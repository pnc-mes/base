package pnc.mesadmin.dto.GetCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:31
 * @Description:
 */
public class GetCalendarInfoResD {
    @JsonProperty("CalendarRd")
    private int CalendarRd;

    @JsonProperty("CalendarName")
    private String  CalendarName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("CalendarDetail")
    private List<GetCalendarInfoResDCalendarDetail> CalendarDetail;
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
    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public List<GetCalendarInfoResDCalendarDetail> getCalendarDetail() {
        return CalendarDetail;
    }
    @JsonIgnore
    public void setCalendarDetail(List<GetCalendarInfoResDCalendarDetail> calendarDetail) {
        CalendarDetail = calendarDetail;
    }
}
