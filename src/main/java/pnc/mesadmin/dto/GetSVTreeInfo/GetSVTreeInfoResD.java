package pnc.mesadmin.dto.GetSVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/7/14.
 */
public class GetSVTreeInfoResD implements Serializable {
    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("Version")
    private String Version;

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }
}
