package pnc.mesadmin.dto.GetAllMdInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetAllMdInfoResDMenuInfo {

    @JsonProperty("ModuleRd")
    private int ModuleRd;

    @JsonProperty("ModuleName")
    private String ModuleName;

    @JsonProperty("MenuInfo")
    private List<GetAllMdInfoResDMenuInfo> MenuInfo;

    @JsonIgnore
    public int getModuleRd() {
        return ModuleRd;
    }

    @JsonIgnore
    public void setModuleRd(int moduleRd) {
        ModuleRd = moduleRd;
    }

    @JsonIgnore
    public String getModuleName() {
        return ModuleName;
    }

    @JsonIgnore
    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    @JsonIgnore
    public List<GetAllMdInfoResDMenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<GetAllMdInfoResDMenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }
}
