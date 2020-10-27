package pnc.mesadmin.dto.SaveRejectInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by HAOZAN on 2018/8/6.
 */
public class SaveRejectInfoReqBD00 {
    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("BadNum")
    private Float BadNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
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
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
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
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }

    @JsonIgnore
    public Float getBadNum() {
        return BadNum;
    }

    @JsonIgnore
    public void setBadNum(Float badNum) {
        BadNum = badNum;
    }
}
