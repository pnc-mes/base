package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoResDSpecGdInfo implements Serializable {

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
