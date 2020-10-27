package pnc.mesadmin.dto.SavePickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/13.
 */
public class SavePickInfoReq02 {
    @JsonProperty("PickRd")
    private int PickRd;

    @JsonProperty("PrePickDate")
    private String PrePickDate;

    @JsonProperty("PKMaInfo")
    private List<SavePickInfoReq02RKMa> PKMaInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getPrePickDate() {
        return PrePickDate;
    }
    @JsonIgnore
    public void setPrePickDate(String prePickDate) {
        PrePickDate = prePickDate;
    }
    @JsonIgnore
    public List<SavePickInfoReq02RKMa> getPKMaInfo() {
        return PKMaInfo;
    }
    @JsonIgnore
    public void setPKMaInfo(List<SavePickInfoReq02RKMa> PKMaInfo) {
        this.PKMaInfo = PKMaInfo;
    }
    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }
    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
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
