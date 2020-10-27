package pnc.mesadmin.dto.GetSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/26.
 */
public class GetSplitPInfoResDBarInfo implements Serializable{
    @JsonProperty("RelRd")
    private int RelRd;
    @JsonProperty("BarCode")
    private String BarCode;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("Num")
    private float num;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonIgnore
    public int getRelRd() {
        return RelRd;
    }
    @JsonIgnore
    public void setRelRd(int relRd) {
        RelRd = relRd;
    }
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }
    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
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
    public float getNum() {
        return num;
    }
    @JsonIgnore
    public void setNum(float num) {
        this.num = num;
    }
    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }
    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
