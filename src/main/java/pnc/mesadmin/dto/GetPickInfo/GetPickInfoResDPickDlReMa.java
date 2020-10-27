package pnc.mesadmin.dto.GetPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetPickInfoResDPickDlReMa {
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("SLNum")
    private float SLNum;
    @JsonProperty("LQNum")
    private float LQNum;
    @JsonProperty("UNNum")
    private float UNNum;
    @JsonProperty("StoreNum")
    private float StoreNum;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonIgnore
    public String getMaDes() {
        return MaDes;
    }
    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }
    @JsonIgnore
    public float getSLNum() {
        return SLNum;
    }
    @JsonIgnore
    public void setSLNum(float SLNum) {
        this.SLNum = SLNum;
    }
    @JsonIgnore
    public float getLQNum() {
        return LQNum;
    }
    @JsonIgnore
    public void setLQNum(float LQNum) {
        this.LQNum = LQNum;
    }
    @JsonIgnore
    public float getUNNum() {
        return UNNum;
    }
    @JsonIgnore
    public void setUNNum(float UNNum) {
        this.UNNum = UNNum;
    }

    @JsonIgnore
    public float getStoreNum() {
        return StoreNum;
    }
    @JsonIgnore
    public void setStoreNum(float storeNum) {
        StoreNum = storeNum;
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
    public String getUnitName() {
        return UnitName;
    }
    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}
