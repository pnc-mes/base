package pnc.mesadmin.dto.GetAllToolDevDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SaveToolDevRes00 implements Serializable {
    @JsonProperty("DevRd")
    private Integer DevRd;

    @JsonProperty("VenderSN")
    private String VenderSN;


    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }

    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }
}
