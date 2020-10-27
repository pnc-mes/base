package pnc.mesadmin.dto.GetRetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class GetRetMaInfoResDRetDlInfo implements Serializable{

    @JsonProperty("RetDtlRd")
    private int RetDtlRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("RetNum")
    private float RetNum;

    @JsonProperty("SRetNum")
    private float SRetNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public float getSRetNum() {
        return SRetNum;
    }
    @JsonIgnore
    public void setSRetNum(float SRetNum) {
        this.SRetNum = SRetNum;
    }
    @JsonIgnore
    public int getRetDtlRd() {
        return RetDtlRd;
    }
    @JsonIgnore
    public void setRetDtlRd(int retDtlRd) {
        RetDtlRd = retDtlRd;
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
    public float getRetNum() {
        return RetNum;
    }
    @JsonIgnore
    public void setRetNum(float retNum) {
        RetNum = retNum;
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
