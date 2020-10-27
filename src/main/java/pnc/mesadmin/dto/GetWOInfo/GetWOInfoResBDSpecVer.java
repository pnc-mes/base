package pnc.mesadmin.dto.GetWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/8/10.
 */
public class GetWOInfoResBDSpecVer implements Serializable {

    @JsonProperty("SpecRd")
    private int SpecRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonIgnore

    public int getSpecRd() {
        return SpecRd;
    }
    @JsonIgnore
    public void setSpecRd(int specRd) {
        SpecRd = specRd;
    }
    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }
    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }
}
