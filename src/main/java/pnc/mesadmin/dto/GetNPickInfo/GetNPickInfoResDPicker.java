package pnc.mesadmin.dto.GetNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetNPickInfoResDPicker {
    @JsonProperty("PickerRd")
    private int PickerRd;

    @JsonProperty("PickerName")
    private String PickerName;

    @JsonIgnore
    public int getPickerRd() {
        return PickerRd;
    }
    @JsonIgnore
    public void setPickerRd(int pickerRd) {
        PickerRd = pickerRd;
    }
    @JsonIgnore
    public String getPickerName() {
        return PickerName;
    }
    @JsonIgnore
    public void setPickerName(String pickerName) {
        PickerName = pickerName;
    }
}
