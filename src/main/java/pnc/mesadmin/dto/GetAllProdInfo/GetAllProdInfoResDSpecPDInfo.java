package pnc.mesadmin.dto.GetAllProdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/18.
 */
public class GetAllProdInfoResDSpecPDInfo implements Serializable{

    @JsonProperty("SpecName")
    private String SpecName;
    @JsonProperty("MINum")
    private Float MINum;
    @JsonProperty("MONum")
    private Float MONum;
    @JsonProperty("Yield")
    private Float Yield;
    @JsonProperty("WHours")
    private Float WHours;
    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }
    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }
    @JsonIgnore
    public Float getMINum() {
        return MINum;
    }
    @JsonIgnore
    public void setMINum(Float MINum) {
        this.MINum = MINum;
    }
    @JsonIgnore
    public Float getMONum() {
        return MONum;
    }
    @JsonIgnore
    public void setMONum(Float MONum) {
        this.MONum = MONum;
    }
    @JsonIgnore
    public Float getYield() {
        return Yield;
    }
    @JsonIgnore
    public void setYield(Float yield) {
        Yield = yield;
    }
    @JsonIgnore
    public Float getWHours() {
        return WHours;
    }
    @JsonIgnore
    public void setWHours(Float WHours) {
        this.WHours = WHours;
    }
}
