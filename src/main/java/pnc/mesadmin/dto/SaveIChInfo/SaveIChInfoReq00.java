package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public class SaveIChInfoReq00 {
    @JsonProperty("PurChCode")
    private String PurChCode;

    @JsonProperty("InCCode")
    private String InCCode;

    @JsonProperty("SourceType")
    private String SourceType;

    @JsonProperty("IChMaInfo")
    private List<SaveIChInfoReq00IChMa> IChMaInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }

    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
    }

    @JsonIgnore
    public String getInCCode() {
        return InCCode;
    }

    @JsonIgnore
    public void setInCCode(String inCCode) {
        InCCode = inCCode;
    }

    @JsonIgnore
    public String getSourceType() {
        return SourceType;
    }

    @JsonIgnore
    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    @JsonIgnore
    public List<SaveIChInfoReq00IChMa> getIChMaInfo() {
        return IChMaInfo;
    }

    @JsonIgnore
    public void setIChMaInfo(List<SaveIChInfoReq00IChMa> IChMaInfo) {
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
