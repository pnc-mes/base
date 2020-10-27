package pnc.mesadmin.dto.GetAllBadInfoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

public class GetAllBadInfoRes implements Serializable {
    @com.fasterxml.jackson.annotation.JsonProperty("BadRd")
    private Integer BadRd;

    @JsonIgnore
    public Integer getBadRd() {
        return BadRd;
    }

    @JsonIgnore
    public void setBadRd(Integer badRd) {
        BadRd = badRd;
    }
}
