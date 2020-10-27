package pnc.mesadmin.dto.GetAllShiftsInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/19.
 */
public class GetAllShiftsInfoResD implements Serializable{
    @JsonProperty("ShiftRd")
    private int ShiftRd;
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
