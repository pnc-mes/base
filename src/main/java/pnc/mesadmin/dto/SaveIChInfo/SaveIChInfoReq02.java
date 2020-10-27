package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/7.
 */
public class SaveIChInfoReq02 {
    @JsonProperty("InCRd")
    private int InCRd;

    @JsonProperty("IChMaInfo")
    private List<SaveIChInfoReq02IChMa> IChMaInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getInCRd() {
        return InCRd;
    }

    @JsonIgnore
    public void setInCRd(int inCRd) {
        InCRd = inCRd;
    }

    @JsonIgnore
    public List<SaveIChInfoReq02IChMa> getIChMaInfo() {
        return IChMaInfo;
    }

    @JsonIgnore
    public void setIChMaInfo(List<SaveIChInfoReq02IChMa> IChMaInfo) {
        this.IChMaInfo = IChMaInfo;
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
