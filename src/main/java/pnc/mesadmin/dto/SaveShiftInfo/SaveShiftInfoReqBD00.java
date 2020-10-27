package pnc.mesadmin.dto.SaveShiftInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/20.
 */
public class SaveShiftInfoReqBD00 implements Serializable {
    @JsonProperty("ShiftName")
    private String ShiftName;

    @JsonProperty("ShiftDes")
    private String ShiftDes;

    @JsonProperty("StartTime")
    private String StartTime;

    @JsonProperty("EndTime")
    private String EndTime;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getShiftName() {
        return ShiftName;
    }
    @JsonIgnore
    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }
    @JsonIgnore
    public String getShiftDes() {
        return ShiftDes;
    }
    @JsonIgnore
    public void setShiftDes(String shiftDes) {
        ShiftDes = shiftDes;
    }
    @JsonIgnore
    public String getStartTime() {
        return StartTime;
    }

    @JsonIgnore
    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    @JsonIgnore
    public String getEndTime() {
        return EndTime;
    }

    @JsonIgnore
    public void setEndTime(String endTime) {
        EndTime = endTime;
    }


    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
