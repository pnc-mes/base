package pnc.mesadmin.dto.GetMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMdInfoResDPMInfo {

    @JsonProperty("PModuleRd")
    private int PModuleRd;

    @JsonProperty("PModuleName")
    private String PModuleName;

    @JsonIgnore
    public int getPModuleRd() {
        return PModuleRd;
    }

    @JsonIgnore
    public void setPModuleRd(int PModuleRd) {
        this.PModuleRd = PModuleRd;
    }

    @JsonIgnore
    public String getPModuleName() {
        return PModuleName;
    }

    @JsonIgnore
    public void setPModuleName(String PModuleName) {
        this.PModuleName = PModuleName;
    }
}
