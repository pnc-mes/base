package pnc.mesadmin.dto.SaveMODevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/29.
 */
public class SaveMODevInfoReqBD00EoBaInfo {

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("Batch")
    private String Batch;

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
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
