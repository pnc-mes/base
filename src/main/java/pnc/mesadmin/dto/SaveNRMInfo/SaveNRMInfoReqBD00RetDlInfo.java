package pnc.mesadmin.dto.SaveNRMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class SaveNRMInfoReqBD00RetDlInfo implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("LRd")
    private int LRd;
    @JsonProperty("InSStatus")
    private String InSStatus;
    @JsonProperty("SRetNum")
    private float SRetNum;
    @JsonProperty("UnitName")
    private String UnitName;
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
    public int getLRd() {
        return LRd;
    }
    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
    }
    @JsonIgnore
    public String getInSStatus() {
        return InSStatus;
    }
    @JsonIgnore
    public void setInSStatus(String inSStatus) {
        InSStatus = inSStatus;
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
    public float getSRetNum() {
        return SRetNum;
    }
    @JsonIgnore
    public void setSRetNum(float SRetNum) {
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
