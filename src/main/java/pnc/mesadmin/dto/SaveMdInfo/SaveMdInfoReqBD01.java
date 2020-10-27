package pnc.mesadmin.dto.SaveMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveMdInfoReqBD01 {

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonIgnore
    public int getModuleRd() {
        return ModuleRd;
    }

    @JsonIgnore
    public void setModuleRd(int moduleRd) {
        ModuleRd = moduleRd;
    }
}
