package pnc.mesadmin.dto.GetAllToolDevDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetToolDevRes implements Serializable {
    @JsonProperty("DevRd")
    private Integer DevRd;

    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }
}
