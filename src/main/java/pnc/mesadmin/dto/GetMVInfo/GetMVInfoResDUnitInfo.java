package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResDUnitInfo implements Serializable{

    @JsonProperty("UnitRd")
    private int UnitRd;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public int getUnitRd() {
        return UnitRd;
    }

    @JsonIgnore
    public void setUnitRd(int unitRd) {
        UnitRd = unitRd;
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
