package pnc.mesadmin.dto.GetRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetRMaNInfoResDRMaNDl {
    @JsonProperty("RMaNDtlRd")
    private int RMaNDtlRd;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("RMaNNum")
    private float RMaNNum;
    @JsonProperty("SRMaNNum")
    private float SRMaNNum;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonIgnore
    public int getRMaNDtlRd() {
        return RMaNDtlRd;
    }
    @JsonIgnore
    public void setRMaNDtlRd(int RMaNDtlRd) {
        this.RMaNDtlRd = RMaNDtlRd;
    }
    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }
    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }
    @JsonIgnore
    public String getMaName() {
        return MaName;
    }
    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }
    @JsonIgnore
    public float getRMaNNum() {
        return RMaNNum;
    }
    @JsonIgnore
    public void setRMaNNum(float RMaNNum) {
        this.RMaNNum = RMaNNum;
    }
    @JsonIgnore
    public float getSRMaNNum() {
        return SRMaNNum;
    }
    @JsonIgnore
    public void setSRMaNNum(float SRMaNNum) {
        this.SRMaNNum = SRMaNNum;
    }
    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }
    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
    @JsonIgnore
    public String getMaDes() {
        return MaDes;
    }
    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }
}
