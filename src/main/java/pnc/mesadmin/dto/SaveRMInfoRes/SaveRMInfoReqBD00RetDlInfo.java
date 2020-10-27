package pnc.mesadmin.dto.SaveRMInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class SaveRMInfoReqBD00RetDlInfo implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("SRetNum")
    private float SRetNum;
    @JsonProperty("UnitName")
    private String UnitName;

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
