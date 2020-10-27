package pnc.mesadmin.dto.SaveRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/26.
 */
public class SaveRMaNInfoReq00RMaNDl {
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("SRMaNNum")
    private float SRMaNNum;
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
    public float getSRMaNNum() {
        return SRMaNNum;
    }
    @JsonIgnore
    public void setSRMaNNum(float SRMaNNum) {
        this.SRMaNNum = SRMaNNum;
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
