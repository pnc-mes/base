package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SaveBomInfoReqBD03BMInfo {
    @JsonProperty("BMRd")
    private int BMRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("ARate")
    private float ARate;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("ConSMode")
    private String ConSMode;

    @JsonProperty("Checked")
    private String Checked;

    @JsonProperty("ReMaInfo")
    private List<SaveBomInfoReqBD03BMReMaInfo> ReMaInfo;

    @JsonIgnore
    public float getARate() {
        return ARate;
    }

    @JsonIgnore
    public void setARate(float ARate) {
        this.ARate = ARate;
    }

    @JsonIgnore
    public int getBMRd() {
        return BMRd;
    }

    @JsonIgnore
    public void setBMRd(int BMRd) {
        this.BMRd = BMRd;
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
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
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
    public String getConSMode() {
        return ConSMode;
    }

    @JsonIgnore
    public void setConSMode(String conSMode) {
        ConSMode = conSMode;
    }

    @JsonIgnore
    public String getChecked() {
        return Checked;
    }

    @JsonIgnore
    public void setChecked(String checked) {
        Checked = checked;
    }

    @JsonIgnore
    public List<SaveBomInfoReqBD03BMReMaInfo> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<SaveBomInfoReqBD03BMReMaInfo> reMaInfo) {
        ReMaInfo = reMaInfo;
    }
}
