package pnc.mesadmin.dto.SaveOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 15:38
 * @Description:
 */
public class SaveOutMaInfoReq00 implements Serializable {
    @JsonProperty("OutCode")
    private String OutCode;

    @JsonProperty("PickerRd")
    private int PickerRd;

    @JsonProperty("PreOutDate")
    private String PreOutDate;

    @JsonProperty("Use")
    private String Use;

    @JsonProperty("OutMaInfo")
    private List<SaveOutMaInfoReq00OutMa> OutMaInfo;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getOutCode() {
        return OutCode;
    }
    @JsonIgnore
    public void setOutCode(String outCode) {
        OutCode = outCode;
    }
    @JsonIgnore
    public int getPickerRd() {
        return PickerRd;
    }
    @JsonIgnore
    public void setPickerRd(int pickerRd) {
        PickerRd = pickerRd;
    }
    @JsonIgnore
    public String getPreOutDate() {
        return PreOutDate;
    }
    @JsonIgnore
    public void setPreOutDate(String preOutDate) {
        PreOutDate = preOutDate;
    }
    @JsonIgnore
    public String getUse() {
        return Use;
    }
    @JsonIgnore
    public void setUse(String use) {
        Use = use;
    }
    @JsonIgnore
    public List<SaveOutMaInfoReq00OutMa> getOutMaInfo() {
        return OutMaInfo;
    }
    @JsonIgnore
    public void setOutMaInfo(List<SaveOutMaInfoReq00OutMa> outMaInfo) {
        OutMaInfo = outMaInfo;
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
