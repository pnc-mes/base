package pnc.mesadmin.dto.GetOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 14:36
 * @Description:
 */
public class GetOutMaInfoResDPicker {
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
