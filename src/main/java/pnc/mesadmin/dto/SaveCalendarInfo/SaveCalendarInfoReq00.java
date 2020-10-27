package pnc.mesadmin.dto.SaveCalendarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 20:38
 * @Description:
 */
public class SaveCalendarInfoReq00 {
    @JsonProperty("CalendarName")
    private String CalendarName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("CalendarDetail")
    private List<SaveCalendarInfoReq00CalendarDetail> CalendarDetail;
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
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public List<SaveCalendarInfoReq00CalendarDetail> getCalendarDetail() {
        return CalendarDetail;
    }
    @JsonIgnore
    public void setCalendarDetail(List<SaveCalendarInfoReq00CalendarDetail> calendarDetail) {
        CalendarDetail = calendarDetail;
    }
}
