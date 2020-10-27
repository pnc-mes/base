package pnc.mesadmin.dto.GetAllBadInfoDto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class GetAllBadInfoResB implements Serializable {
    @JsonProperty("BadRd")
    private Integer BadRd;
    @JsonProperty("BadTypeName")
    private String BadTypeName;

    @JsonIgnore
    public Integer getBadRd() {
        return BadRd;
    }

    @JsonIgnore
    public void setBadRd(Integer badRd) {
        BadRd = badRd;
    }

    @JsonIgnore
    public String getBadTypeName() {
        return BadTypeName;
    }

    @JsonIgnore
    public void setBadTypeName(String badTypeName) {
        BadTypeName = badTypeName;
    }
}
