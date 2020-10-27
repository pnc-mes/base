package pnc.mesadmin.dto.GetCMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/6/28.
 */
public class GetCMWFInfoResDWSpecInfo implements Serializable{

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;
    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }
    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
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
