package pnc.mesadmin.dto.UserLoginInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by test on 2017/6/27.
 */
public class UserLoginInfoResDShift {

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
