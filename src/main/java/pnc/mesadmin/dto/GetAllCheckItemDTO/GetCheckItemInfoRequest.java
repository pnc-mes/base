package pnc.mesadmin.dto.GetAllCheckItemDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetCheckItemInfoRequest implements Serializable {
    @JsonProperty("CIRd")
    private Integer CIRd;

    @JsonIgnore
    public Integer getCIRd() {
        return CIRd;
    }

    @JsonIgnore
    public void setCIRd(Integer CIRd) {
        this.CIRd = CIRd;
    }
}
