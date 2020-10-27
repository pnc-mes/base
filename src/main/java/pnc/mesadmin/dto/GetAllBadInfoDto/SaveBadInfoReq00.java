package pnc.mesadmin.dto.GetAllBadInfoDto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveBadInfoReq00 implements Serializable {
    @JsonProperty("BadRd")
    private int BadRd;

    @JsonIgnore
    public int getBadRd() {
        return BadRd;
    }
    @JsonIgnore
    public void setBadRd(int badRd) {
        BadRd = badRd;
    }
}
