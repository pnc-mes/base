package pnc.mesadmin.dto.GetBHChgDtlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/20 0020.
 */
public abstract class Chg {

    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("SpecName")
    private String SpecName;
    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("ARate")
    private Float ARate;

    @JsonProperty("ConSMode")
    private String ConSMode;
    @JsonProperty("Checked")
    private String Checked;

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
    public String getSpecName() {
        return SpecName;
    }
    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }
    @JsonIgnore
    public Float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
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
    public Float getARate() {
        return ARate;
    }
    @JsonIgnore
    public void setARate(Float ARate) {
        this.ARate = ARate;
    }
}
