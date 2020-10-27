package pnc.mesadmin.dto.GetUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/23 11:17
 * @Description:
 */
public class GetUserInfoResBDShift implements Serializable {
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
