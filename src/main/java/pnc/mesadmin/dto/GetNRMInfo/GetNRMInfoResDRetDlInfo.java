package pnc.mesadmin.dto.GetNRMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMInfoResDRetDlInfo implements Serializable{

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
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("Num")
    private Float Num;
    @JsonProperty("StoreName")
    private String StoreName;
    @JsonProperty("LName")
    private String LName;
    @JsonProperty("SRetNum")
    private Float SRetNum;
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
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getStoreName() {
        return StoreName;
    }
    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
    @JsonIgnore
    public String getLName() {
        return LName;
    }
    @JsonIgnore
    public void setLName(String LName) {
        this.LName = LName;
    }
    @JsonIgnore
    public Float getSRetNum() {
        return SRetNum;
    }
    @JsonIgnore
    public void setSRetNum(Float SRetNum) {
        this.SRetNum = SRetNum;
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
