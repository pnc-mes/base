package pnc.mesadmin.dto.GetMOQInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class GetMOQInfoResD {

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("DoNum")
    private float DoNum;

    @JsonProperty("EoNum")
    private float EoNum;

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
    public float getDoNum() {
        return DoNum;
    }

    @JsonIgnore
    public void setDoNum(float doNum) {
        DoNum = doNum;
    }

    @JsonIgnore
    public float getEoNum() {
        return EoNum;
    }

    @JsonIgnore
    public void setEoNum(float eoNum) {
        EoNum = eoNum;
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
