package pnc.mesadmin.dto.SaveShiftInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/6/20.
 */
public class SaveShiftInfoReqBD03 implements Serializable{
    @JsonProperty("ShiftRd")
    private int ShiftRd;
    @JsonIgnore
    public int getShiftRd() {
        return ShiftRd;
    }
    @JsonIgnore
    public void setShiftRd(int shiftRd) {
        ShiftRd = shiftRd;
    }
}
