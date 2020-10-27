package pnc.mesadmin.dto.GetMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMdInfoReqBD00 {

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonProperty("SysVerRd")
    private Integer SysVerRd;

    @JsonIgnore
    public int getModuleRd() {
        return ModuleRd;
    }

    @JsonIgnore
    public void setModuleRd(int moduleRd) {
        ModuleRd = moduleRd;
    }

    @JsonIgnore
    public Integer getSysVerRd() {
        return SysVerRd;
    }

    @JsonIgnore
    public void setSysVerRd(Integer sysVerRd) {
        SysVerRd = sysVerRd;
    }
}
